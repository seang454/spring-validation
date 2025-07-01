package seang.spring.testingmvc.model.dto;

public record UserUpdateDto(
        String name,
        String gender,
        String email,
        String phoneNumber
) {

}
