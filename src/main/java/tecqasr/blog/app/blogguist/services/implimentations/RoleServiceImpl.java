package tecqasr.blog.app.blogguist.services.implimentations;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tecqasr.blog.app.blogguist.entities.Role;
import tecqasr.blog.app.blogguist.exceptions.ResourceNotFoundException;
import tecqasr.blog.app.blogguist.payloads.RoleDto;
import tecqasr.blog.app.blogguist.repositories.RoleRepository;
import tecqasr.blog.app.blogguist.services.RoleService;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepository roleRepository;

    ModelMapper mapper = new ModelMapper();

    @Override
    public RoleDto createRole(RoleDto roleDto) {
        Role role = mapper.map(roleDto, Role.class);
        Role savedRole = roleRepository.save(role);

        return mapper.map(savedRole, RoleDto.class);
    }

    @Override
    public void deleteRole(int id) {
        Role role = roleRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Role","id",id));
        roleRepository.delete(role);
    }

    @Override
    public List<RoleDto> getRoleByName(String name) {
        List<Role> role = roleRepository.findByName(name);
        return role.stream().map(role1 -> mapper.map(role1, RoleDto.class)).toList();
    }

    @Override
    public RoleDto updateRole(int id, RoleDto roleDto) {
        Role role = roleRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Role","id",id));
        role.setName(roleDto.getName());
        Role updatedRole = roleRepository.save(role);
        return mapper.map(updatedRole, RoleDto.class);
    }
}
