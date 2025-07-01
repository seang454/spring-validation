package seang.spring.testingmvc.model.dto;

import jakarta.validation.constraints.*;

public record UserCreateDto(

        @NotBlank(message = "Name is required")
        @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
        String name,

        @NotBlank(message = "Gender is required")
        @Pattern(regexp = "Male|Female|Other", message = "Gender must be Male, Female, or Other")
        String gender,

        @NotBlank(message = "Email is required")
        @Email(message = "Email should be valid")
        String email,

        @NotBlank(message = "Password is required")
        @Size(min = 8, message = "Password must be at least 8 characters long")
        String password,

        @NotBlank(message = "Phone number is required")
        @Pattern(regexp = "\\d{10,15}", message = "Phone number must be between 10 and 15 digits") //default message
        String phoneNumber

) {
}
