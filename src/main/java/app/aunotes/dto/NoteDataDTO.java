package app.aunotes.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public class NoteDataDTO {

        @Getter
        @Setter
        private String title;


        @Getter
        @Setter
        private String description;

}
