package app.aunotes.service;


import app.aunotes.model.Note;
import app.aunotes.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NoteService {

    private final NoteRepository noteRepository;


    @Autowired
    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }


    public List<Note> getAllNotes(){
        return noteRepository.findAll();
    }



    //Saving notes to database service
    public void saveNote(Note note) {
        noteRepository.save(note);
    }


}
