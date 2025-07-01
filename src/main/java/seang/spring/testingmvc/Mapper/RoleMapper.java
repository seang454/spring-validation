package seang.spring.testingmvc.Mapper;

import seang.spring.testingmvc.model.dto.role.RoleCreateDto;
import seang.spring.testingmvc.model.dto.role.RoleResponseDto;
import seang.spring.testingmvc.model.entity.Roles;

import java.util.UUID;

public class RoleMapper {
    public static RoleResponseDto mapFromRoleToRoleResponseDto(Roles role) {
        return RoleResponseDto.builder()
                .roleId(role.getId())
                .uuid(role.getUuid())
                .name(role.getName())
                .build();
    }
    public static Roles mapFromCreateResponseDtoToRoles(RoleCreateDto role) {
        return Roles.builder()
                .id(null)
                .uuid(UUID.randomUUID().toString())
                .name(role.name())
                .build();
    }
}
