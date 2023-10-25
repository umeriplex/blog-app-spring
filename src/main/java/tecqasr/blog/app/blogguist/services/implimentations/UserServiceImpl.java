package tecqasr.blog.app.blogguist.services.implimentations;
import org.springframework.security.crypto.password.PasswordEncoder;
import tecqasr.blog.app.blogguist.configs.AppConstants;
import tecqasr.blog.app.blogguist.entities.Role;
import tecqasr.blog.app.blogguist.entities.User;
import tecqasr.blog.app.blogguist.exceptions.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tecqasr.blog.app.blogguist.payloads.UserDto;
import tecqasr.blog.app.blogguist.repositories.RoleRepository;
import tecqasr.blog.app.blogguist.repositories.UserRepository;
import tecqasr.blog.app.blogguist.services.UserService;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repo;
    @Autowired
    private RoleRepository roleRepository;
    private final ModelMapper mapper = new ModelMapper();
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDto registerUser(UserDto userDto) {
        User user = mapper.map(userDto, User.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role role = roleRepository.findById(AppConstants.NORMAL_USER).orElseThrow(()-> new ResourceNotFoundException("Role","id",AppConstants.NORMAL_USER));
        user.getRole().add(role);
        User savedUser = repo.save(user);


        return mapper.map(savedUser, UserDto.class);
    }

    @Override
    public UserDto saveUser(UserDto userDto) {
        User user = mapper.map(userDto, User.class);
        User savedUser = repo.save(user);
        return mapper.map(savedUser, UserDto.class);
    }

    @Override
    public UserDto updateUser(UserDto userDto, int userId) {
        User user = repo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","id",userId));
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());
        User updatedUser = repo.save(user);
        return mapper.map(updatedUser, UserDto.class);
    }

    @Override
    public UserDto showUserById(int userId) {
        User user = repo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","id",userId));
        return mapper.map(user, UserDto.class);
    }

    @Override
    public List<UserDto> showAllUsers() {
        List<User> users = repo.findAll();
        return users.stream().map(user -> mapper.map(user, UserDto.class)).toList();
    }

    @Override
    public void deleteUser(int userId) {
        User user = repo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","id",userId));
        repo.delete(user);
    }
}
