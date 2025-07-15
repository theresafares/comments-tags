package com.task.handle.Comments;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ComDTO {

    @NotBlank(message = "Content must not be blank")
    @Size(min = 2, max = 255, message = "Content must be between 2 and 255 characters")
    private String content;

    @NotNull(message = "Tag ID must not be null")
    private Long tagId;
}