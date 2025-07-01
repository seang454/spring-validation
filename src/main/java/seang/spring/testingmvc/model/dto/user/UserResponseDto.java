package seang.spring.testingmvc.model.dto.user;

import lombok.Builder;

import java.sql.Date;

@Builder
public record UserResponseDto(
        Integer id,
        String uuid,
        String name,
        String gender,
        String email,
        String phoneNumber,
        Date createdAt,
        Date updatedAt
) {

}
