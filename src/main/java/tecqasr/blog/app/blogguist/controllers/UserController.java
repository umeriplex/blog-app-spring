package tecqasr.blog.app.blogguist.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tecqasr.blog.app.blogguist.payloads.ApiResponse;
import tecqasr.blog.app.blogguist.payloads.UserDto;
import tecqasr.blog.app.blogguist.services.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    // save user
    @PostMapping("/")
    public ResponseEntity<UserDto> saveUser(@Valid @RequestBody UserDto userDto){
        UserDto savedUser = userService.saveUser(userDto);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    // update user
    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(
            @Valid
            @RequestBody UserDto userDto,
            @PathVariable("userId") int userId
            ){
        UserDto updatedUser = userService.updateUser(userDto, userId);
        return new ResponseEntity<>(updatedUser, HttpStatus.CREATED);
    }

    // show user by id
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> showUserById(@PathVariable("userId") int userId){
        UserDto user = userService.showUserById(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    // show all users
    @GetMapping("/")
    public ResponseEntity<List<UserDto>> showAllUsers(){
        List<UserDto> users = userService.showAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    // ADMIN
    // delete user
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userId") int userId){
        userService.deleteUser(userId);
        return new ResponseEntity<>(new ApiResponse("User Deleted", true), HttpStatus.OK);
    }
}
