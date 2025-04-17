package app.aunotes.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    @Getter
    @Setter
    private String title;

    @Column(columnDefinition = "TEXT",nullable = false)
    @Getter
    @Setter
    private String content;

    @Column(columnDefinition = "TEXT",nullable = false)
    @Getter
    @Setter
    private String subject;


    @Column(nullable = false,updatable = false)
    @Getter
    @Setter
    private LocalDateTime createdAt;     //created the file Time


    @Column(nullable = false)
    @Getter
    @Setter
    private String fileName;     // original name like "report.pdf"

    @Column(nullable = false)
    @Getter
    @Setter
    private String fileType;     // MIME type like "application/pdf"

    @Column(nullable = false)
    @Getter
    @Setter
    private String filePath;     // full disk path or relative path

    @Column(nullable = false)
    @Getter
    @Setter
    private Long fileSize;       // size in bytes



    @Column(nullable = false,updatable = true)
    @Getter
    @Setter
    private LocalDateTime updatedAt;    //Modified or updated time

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

}
