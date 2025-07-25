package seang.spring.testingmvc.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import seang.spring.testingmvc.apiResponse.ApiResponse;
import seang.spring.testingmvc.model.RoleService;
import seang.spring.testingmvc.model.dto.role.RoleCreateDto;
import seang.spring.testingmvc.model.dto.role.RoleResponseDto;
import seang.spring.testingmvc.model.entity.Users;

import java.util.List;

@RestController
@RequestMapping("api/v1/roles")
@RequiredArgsConstructor
public class RoleController {
    private final RoleService roleService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<List<RoleResponseDto>>> findAll() {
        List<RoleResponseDto> roles = roleService.getAllRoles();
        ApiResponse<List<RoleResponseDto>> apiResponse = new ApiResponse<>(
                HttpStatus.OK.value(),
                HttpStatus.OK.toString(),
                roles
        );
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @PostMapping
    ResponseEntity<RoleResponseDto> createUser(@Valid @RequestBody RoleCreateDto roleCreateDto) {
        RoleResponseDto roleResponseDto = roleService.saveRole(roleCreateDto);
        return new ResponseEntity<>(roleResponseDto, HttpStatus.CREATED);
    }
    @DeleteMapping("/{uuid}")
    ResponseEntity<Users> deleteUserByUuid(@PathVariable String uuid) {
        roleService.deleteRole(uuid);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PatchMapping("/{uuid}")
    ResponseEntity<RoleResponseDto> updateUser(@PathVariable String uuid,@Valid @RequestBody RoleCreateDto roleCreateDto) {
        RoleResponseDto roleResponseDto = roleService.updateRole(uuid,roleCreateDto);
        return new ResponseEntity<>(roleResponseDto,HttpStatus.OK);
    }
    @GetMapping("/{uuid}")
    public ResponseEntity<RoleResponseDto> getRoleByUuid(@PathVariable String uuid) {
        RoleResponseDto roleResponseDto = roleService.getRoleByUuid(uuid);
        return new ResponseEntity<>(roleResponseDto,HttpStatus.FOUND);
    }

}
