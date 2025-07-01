package seang.spring.testingmvc.model;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import seang.spring.testingmvc.Mapper.UserMapper;
import seang.spring.testingmvc.model.dto.UserCreateDto;
import seang.spring.testingmvc.model.dto.UserResponseDto;
import seang.spring.testingmvc.model.dto.UserUpdateDto;
import seang.spring.testingmvc.model.entity.Users;
import seang.spring.testingmvc.model.repository.UserRepository;
import seang.spring.testingmvc.model.security.HashUtil;

import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    final UserRepository userRepository;
    public UserResponseDto saveUser(UserCreateDto userCreateDto) {
        String hashing = HashUtil.hashWithSalt(userCreateDto.password());

        Users user = UserMapper.MapFromCreateUserResponseToUsers(hashing,userCreateDto);
        userRepository.save(user);
        Optional<Users> savedUser = userRepository.findByUuid(user.getUuid());
        return UserMapper.MapFromUserToUserResponseDto(savedUser.get());
    }
    public List<UserResponseDto> getAllUsers() {
        List<Users> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        List<UserResponseDto> userResponseDto = new ArrayList<>();
        users.forEach(user -> {
            userResponseDto.add(UserMapper.MapFromUserToUserResponseDto(user));
        });
        return userResponseDto;
    }

    public void deleteUserByUuid(String uuid) {
        log.info("Deleting user in service : " + uuid);
        userRepository.deleteByUuid(uuid);
    }

    public UserResponseDto updateUserAllColum(String uuid, UserUpdateDto userUpdateDto) {
        Users users = userRepository.findAll().stream().filter(user1 -> user1.getUuid().equals(uuid)).findFirst().get();
        users.setName(userUpdateDto.name());
        users.setGender(userUpdateDto.gender());
        users.setEmail(userUpdateDto.email());
        users.setPhoneNumber(userUpdateDto.phoneNumber());
        users.setUpdatedAt(Date.valueOf(LocalDate.now()));
        userRepository.save(users);
        Users updatedUser = userRepository.findAll().stream().filter(user1 -> user1.getUuid().equals(user1.getUuid())).findFirst().get();
        return UserMapper.MapFromUserToUserResponseDto(users);

    }
}
