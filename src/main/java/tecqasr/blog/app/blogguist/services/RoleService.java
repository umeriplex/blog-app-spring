package tecqasr.blog.app.blogguist.services;

import tecqasr.blog.app.blogguist.payloads.RoleDto;

import java.util.List;

public interface RoleService {

    RoleDto createRole(RoleDto roleDto);
    void deleteRole(int id);
    List<RoleDto> getRoleByName(String name);
    RoleDto updateRole(int id, RoleDto roleDto);


}
