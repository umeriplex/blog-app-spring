package tecqasr.blog.app.blogguist.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tecqasr.blog.app.blogguist.payloads.ApiResponse;
import tecqasr.blog.app.blogguist.payloads.RoleDto;
import tecqasr.blog.app.blogguist.services.RoleService;

import java.util.List;

@RestController
@RequestMapping("/api/role")
public class RoleController {

    @Autowired
    RoleService roleService;

    @PostMapping("/create")
    public ResponseEntity<RoleDto> saveRole(@Valid @RequestBody RoleDto roleDto) {
        RoleDto role = roleService.createRole(roleDto);
        System.out.println("Role Added: "+role.getName());
        return new ResponseEntity<RoleDto>(role, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteRole(@PathVariable int id) {
        roleService.deleteRole(id);
        return ResponseEntity.ok(new ApiResponse("Role Deleted!",true));
    }

    @GetMapping("/get/{name}")
    public ResponseEntity<List<RoleDto>> getRoleByName(@PathVariable String name) {
        List<RoleDto> roles = roleService.getRoleByName(name);
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<RoleDto> updateRole (@PathVariable int id,@Valid @RequestBody RoleDto roleDto){
        RoleDto updateRole = roleService.updateRole(id,roleDto);
        return new ResponseEntity<>(updateRole, HttpStatus.OK);
    }

}
