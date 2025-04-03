package app.aunotes.controller;

import app.aunotes.model.User;
import app.aunotes.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<String>registerUser(@RequestBody User user) {
        String reponse = userService.registerUser(user);

        if(reponse.equalsIgnoreCase("User registered successfully")) {
            return ResponseEntity.ok("User registered successfully");
        }
        return ResponseEntity.badRequest().body(reponse);
    }

    @PostMapping("/login")
    public ResponseEntity<String>loginUser(@RequestBody User user) {
        String response = userService.loginUser(user);
        if(response.equalsIgnoreCase("User logged in successfully")) {
            return ResponseEntity.ok("User logged in successfully");
        }
        return ResponseEntity.badRequest().body(response);
    }

}
