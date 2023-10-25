package tecqasr.blog.app.blogguist.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tecqasr.blog.app.blogguist.entities.User;
import tecqasr.blog.app.blogguist.exceptions.ResourceNotFoundException;
import tecqasr.blog.app.blogguist.repositories.UserRepository;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("USERNAME: "+username);
        User user = this.userRepository.findByEmail(username).stream().findFirst().orElseThrow(()-> new ResourceNotFoundException("User not found", "User with email "+username+" not found",404));
        System.out.println("USER: "+user.getEmail());
        return user;
    }
}
