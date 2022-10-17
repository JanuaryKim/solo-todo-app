package code.solo.todoapp.tasks.dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Positive;


public class TasksDto {

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public static class Post{

        @Length(min = 1, max = 100)
        private String title;

        @Positive
        private Integer order;

        private Boolean completed;

    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public static class Patch{

        @Length(min = 1, max = 100)
        private String title;

        @Positive
        private Integer order;

        private Boolean completed;

    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public static class Response{

        private Long id;

        private String title;

        private Integer order;

        private Boolean completed;

    }
}
