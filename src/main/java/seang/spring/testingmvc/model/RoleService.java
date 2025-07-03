package seang.spring.testingmvc.model;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import seang.spring.testingmvc.Mapper.RoleMapper;
import seang.spring.testingmvc.model.dto.role.RoleCreateDto;
import seang.spring.testingmvc.model.dto.role.RoleResponseDto;
import seang.spring.testingmvc.model.entity.Roles;
import seang.spring.testingmvc.model.repository.RoleRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public List<RoleResponseDto> getAllRoles() {
        List<RoleResponseDto> roles = new ArrayList<>();
        roleRepository.findAll().forEach(r -> roles.add(RoleMapper.mapFromRoleToRoleResponseDto(r)));
        return roles;
    }
    public RoleResponseDto saveRole(RoleCreateDto roleCreateDto) {
        Roles roles = RoleMapper.mapFromCreateResponseDtoToRoles(roleCreateDto);
        log.info("Saving role: {}", roles);
        roleRepository.save(roles);
        Roles savedRole = roleRepository.findRoleByUuid(roles.getUuid()).get();
        return RoleMapper.mapFromRoleToRoleResponseDto(savedRole);
    }
    public RoleResponseDto updateRole(String uuid,RoleCreateDto roleCreateDto){
        Roles roles = roleRepository.findRoleByUuid(uuid).get();
        roles.setName(roleCreateDto.name());
        roleRepository.save(roles);
        Roles updatedRole = roleRepository.findRoleByUuid(uuid).get();
        return RoleMapper.mapFromRoleToRoleResponseDto(updatedRole);
    }
    public void deleteRole(String uuid){
        Roles roles = roleRepository.findRoleByUuid(uuid).get();
        roleRepository.delete(roles);
    }
    public RoleResponseDto getRoleByUuid(String uuid){
        Roles roles = roleRepository.findRoleByUuid(uuid).orElseThrow(()-> new RuntimeException("role not found"));
        return RoleMapper.mapFromRoleToRoleResponseDto(roles);
    }
}
