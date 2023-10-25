package tecqasr.blog.app.blogguist.services;

import tecqasr.blog.app.blogguist.payloads.UserDto;

import java.util.List;

public interface UserService {

    UserDto registerUser(UserDto userDto);
    UserDto saveUser(UserDto userDto);
    UserDto updateUser(UserDto userDto, int userId);
    UserDto showUserById(int userId);
    List<UserDto> showAllUsers();
    void deleteUser(int userId);
}
