package seang.spring.testingmvc.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import seang.spring.testingmvc.apiResponse.ApiResponse;
import seang.spring.testingmvc.model.UserService;
import seang.spring.testingmvc.model.dto.user.UserCreateDto;
import seang.spring.testingmvc.model.dto.user.UserResponseDto;
import seang.spring.testingmvc.model.dto.user.UserUpdateDto;
import seang.spring.testingmvc.model.entity.Users;
import seang.spring.testingmvc.model.pojo.Product;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/user")
@RequiredArgsConstructor
public class UserController {
    final UserService userService;

    @GetMapping(value = "/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<List<UserResponseDto>>> findAll() {
        List<UserResponseDto> users = userService.getAllUsers();
        ApiResponse<List<UserResponseDto>> apiResponse = new ApiResponse<>(
                HttpStatus.OK.value(),
                HttpStatus.OK.toString(),
                users
        );
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @PostMapping("/save")
    ResponseEntity<UserResponseDto> createUser(@Valid @RequestBody UserCreateDto userCreateDto) {
        UserResponseDto userResponseDto = userService.saveUser(userCreateDto);
        return new ResponseEntity<>(userResponseDto, HttpStatus.CREATED);
    }
    @DeleteMapping("/{uuid}")
    ResponseEntity<Users> deleteUserByUuid(@PathVariable String uuid) {
        userService.deleteUserByUuid(uuid);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PatchMapping("/{uuid}")
    ResponseEntity<UserResponseDto> updateUser(@PathVariable String uuid,@Valid @RequestBody UserUpdateDto userUpdateDto) {
        UserResponseDto userResponseDto = userService.updateUserAllColum(uuid,userUpdateDto);
        return new ResponseEntity<>(userResponseDto,HttpStatus.OK);
    }


    @GetMapping("/products")
    public String getStudent(Model model) {
        Product product1 = new Product(1, "Dara", "male", "dara@gmail.com", "mong", "0987774", "17733");
        Product product2 = new Product(2, "Sok", "female", "sok@gmail.com", "kandal", "0123456", "18888");
        Product product3 = new Product(3, "Rith", "male", "rith@gmail.com", "battambang", "0971234", "19999");

        List<Product> productList = new ArrayList<>();
        productList.add(product1);
        productList.add(product2);
        productList.add(product3);

        model.addAttribute("products", productList); // ✅ Use plural name
        return "products";
    }


}
