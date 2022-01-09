package pl.emilfrankiewicz.service;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.emilfrankiewicz.exception.ResourceDoesNotExistException;
import pl.emilfrankiewicz.model.User;
import pl.emilfrankiewicz.model.UserDTO;
import pl.emilfrankiewicz.repository.UserRepository;
import pl.emilfrankiewicz.repository.UserRoleRepository;
import pl.emilfrankiewicz.model.UserRole;

@Service
public class UserService {

	private static final String DEFAULT_ROLE = "ROLE_USER";
	private UserRepository userRepository;
	private UserRoleRepository userRoleRepository;
	private PasswordEncoder passwordEncoder;

	@Autowired
	public UserService(UserRepository userRepository, UserRoleRepository userRoleRepository,
			PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.userRoleRepository = userRoleRepository;
		this.passwordEncoder = passwordEncoder;
	}

	public void createUserFromDTO(UserDTO userDTO) throws ResourceDoesNotExistException {
		User user = new User();

		if (userRepository.existsUserByEmail(userDTO.getEmail())
				|| userRepository.existsUserByUsername(userDTO.getUsername()) == true) {
			throw new ResourceDoesNotExistException("This email or username is already taken.");
		}

		user.setUsername(userDTO.getUsername());
		user.setEmail(userDTO.getEmail());
		UserRole defaultRole = userRoleRepository.findByRole(DEFAULT_ROLE);
		user.getRoles().add(defaultRole);
		String passwordHash = passwordEncoder.encode(userDTO.getPassword());
		user.setPassword(passwordHash);
		user.setCreationDate(new Date());
		userRepository.save(user);
	}

	public void saveUser(User user) {
		userRepository.save(user);
	}
}
