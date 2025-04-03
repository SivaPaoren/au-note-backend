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


    @Column(nullable = false,updatable = false)
    @Getter
    @Setter
    private LocalDateTime createdAt;


    @Column(nullable = false,updatable = true)
    @Getter
    @Setter
    private LocalDateTime updatedAt;

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
