package app.aunotes.controller;

import app.aunotes.model.Note;
import app.aunotes.service.NoteService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/notes")
@NoArgsConstructor
public class NoteController {


    private NoteService noteService;


    @Autowired
    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }


    //api to fetch the list of notes or get the list of note ('/api/notes')
    @GetMapping()
    public ResponseEntity<List<Note>> getAllNotes() {
        List<Note> notes =  noteService.getAllNotes();
        return ResponseEntity.ok(notes);
    }


    //saving Note controller  ('/api/
    @PostMapping()
    public ResponseEntity<String> CreateNote(@RequestBody Note note) {
        noteService.saveNote(note);
        return ResponseEntity.ok("Saved Note Successfully");
    }
}
