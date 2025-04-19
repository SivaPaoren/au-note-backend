package app.aunotes.service;


import app.aunotes.dto.NoteDTO;
import app.aunotes.model.Note;
import app.aunotes.repository.NoteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.*;
import java.sql.SQLOutput;
import java.util.UUID;
import java.nio.file.Files;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class NoteService {

    private final NoteRepository noteRepository;

    @Value("${file.upload-dir}")
    private String uploadDir;


    //constructor to inject Note Repository
    @Autowired
    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }


    //Save note and Save file
    public Note storeFileWithMeta(MultipartFile file, NoteDTO meta) throws IOException {

        //create directory if does not exist,otherwise store in the directory
        Files.createDirectories(Paths.get(uploadDir));
        String originalFilename = file.getOriginalFilename();
        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        String storedName = UUID.randomUUID().toString() + extension;

        Path filePath = Paths.get(uploadDir, storedName);
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);//actually copy and store bou

        Note note = new Note();
        note.setFileName(originalFilename);
        note.setFileType(file.getContentType());
        note.setFileSize(file.getSize());
        note.setFilePath(filePath.toString());

        // Set extra metadata
        note.setTitle(meta.getTitle());
        note.setContent(meta.getDescription());

        note.setSubject(meta.getSubject());
        return noteRepository.save(note);
    }

    public Note getFile(Long id) {
        return noteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("File not found"));
    }



    public List<NoteDTO> getAllNotes() {
        List<Note> notes = noteRepository.findAll();

        return notes.stream()
                .map(note -> new NoteDTO(
                        note.getTitle(),            // map to name
                        note.getContent(),           // map to description
                        note.getSubject()
                ))
                .collect(Collectors.toList());
    }





}
