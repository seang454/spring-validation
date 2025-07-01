package seang.spring.testingmvc.model.dto;

public record UserCreateDto(
        String name,
        String gender,
        String email,
        String password,
        String phoneNumber
) {
}
