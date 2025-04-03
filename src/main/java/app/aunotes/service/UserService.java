package app.aunotes.service;

import app.aunotes.model.User;
import app.aunotes.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public String registerUser(User user) {

        //check if the user already exist
        User userbyEmail = userRepository.findByEmail(user.getEmail());
        User userByuserName = userRepository.findByUserName(user.getUserName());

        if(userbyEmail != null) {
            return "Email Already Exists";
        }

        if( userByuserName!= null) {
            return "Username Already Exists";
        }

        //hashing the password
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        //saved user successfully
        userRepository.save(user);

        return "User registered successfully";
    }

    public String loginUser(User user) {
        User userByEmail = userRepository.findByEmail(user.getEmail());

        if(userByEmail == null) {
            return "Email Not Found";
        }
        if(!passwordEncoder.matches(user.getPassword(), userByEmail.getPassword())) {
            return "Incorrect Password";
        }

        return "User logged in successfully";
    }
}
