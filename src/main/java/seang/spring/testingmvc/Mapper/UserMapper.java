package seang.spring.testingmvc.Mapper;

import seang.spring.testingmvc.model.dto.UserCreateDto;
import seang.spring.testingmvc.model.dto.UserResponseDto;
import seang.spring.testingmvc.model.dto.UserUpdateDto;
import seang.spring.testingmvc.model.entity.Users;

import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

public class UserMapper {
    public static Users MapFromCreateUserResponseToUsers(String endCode,UserCreateDto userCreateDto) {
        return Users.builder()
                .id(null)
                .uuid(UUID.randomUUID().toString())
                .name(userCreateDto.name())
                .gender(userCreateDto.gender())
                .email(userCreateDto.email())
                .password(endCode)
                .phoneNumber(userCreateDto.phoneNumber())
                .createdAt(Date.valueOf(LocalDate.now()))
                .updatedAt(Date.valueOf(LocalDate.now()))
                .isDeleted(false)
                .build();
    }
    public static UserResponseDto MapFromUserToUserResponseDto(Users users) {
        return UserResponseDto.builder()
                .id(users.getId())
                .uuid(users.getUuid())
                .name(users.getName())
                .gender(users.getGender())
                .email(users.getEmail())
                .phoneNumber(users.getPhoneNumber())
                .createdAt(users.getCreatedAt())
                .updatedAt(users.getUpdatedAt())
                .build();
    }
}
