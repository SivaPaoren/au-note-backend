package app.aunotes.controller;

import app.aunotes.dto.NoteDataDTO;
import app.aunotes.model.Note;
import app.aunotes.service.NoteService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    // Upload a file (image, pdf, doc, etc.)
    @PostMapping(value = "/upload", consumes = "multipart/form-data")
    public ResponseEntity<?> uploadFile(
            @RequestPart("file") MultipartFile file,
            @RequestPart("meta") NoteDataDTO meta) {
        System.out.println("File ContentType: " + file.getContentType());
        try {
            Note stored = noteService.storeFileWithMeta(file, meta);
            return ResponseEntity.ok(stored);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Upload failed: " + e.getMessage());
        }
    }



    // Download or view the file by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> downloadFile(@PathVariable Long id) {
        try {
            Note file = noteService.getFile(id);
            Path path = Path.of(file.getFilePath());
            byte[] fileBytes = Files.readAllBytes(path);

            MediaType mediaType = Optional.ofNullable(file.getFileType())
                    .map(MediaType::parseMediaType)
                    .orElse(MediaType.APPLICATION_OCTET_STREAM);

            return ResponseEntity.ok()
                    .contentType(mediaType)
                    .header(HttpHeaders.CONTENT_DISPOSITION,
                            "inline; filename=\"" + file.getFileName() + "\"")
                    .body(fileBytes);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("File not found");
        }
    }

}
