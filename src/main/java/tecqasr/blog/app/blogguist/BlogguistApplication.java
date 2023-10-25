package tecqasr.blog.app.blogguist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;
import tecqasr.blog.app.blogguist.configs.AppConstants;
import tecqasr.blog.app.blogguist.entities.Role;
import tecqasr.blog.app.blogguist.repositories.RoleRepository;

import java.util.List;

@SpringBootApplication
public class BlogguistApplication implements CommandLineRunner {
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RoleRepository roleRepository;


	public static void main(String[] args) {
		SpringApplication.run(BlogguistApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {
		try{
			Role roleAdmin;
			roleAdmin = new Role();
			roleAdmin.setId(AppConstants.ADMIN_USER);
			roleAdmin.setName("ADMIN_USER");

			Role roleNirmal;
			roleNirmal = new Role();
			roleNirmal.setId(AppConstants.NORMAL_USER);
			roleNirmal.setName("NORMAL_USER");

			List<Role> roles = List.of(roleAdmin,roleNirmal);
			List<Role> userSavedRoles = roleRepository.saveAll(roles);
			userSavedRoles.forEach((role)->{
				System.out.println(role.getName());
			});

		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
}
