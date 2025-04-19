package app.aunotes.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NoteDTO {

        private String title;
        private String description;
        private String subject;

}
