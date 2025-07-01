package seang.spring.testingmvc.model.dto.role;

import lombok.Builder;

@Builder
public record RoleResponseDto(
        Integer roleId,
        String uuid,
        String name
) {
}
