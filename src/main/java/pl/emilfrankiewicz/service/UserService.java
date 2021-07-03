package pl.emilfrankiewicz.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.emilfrankiewicz.model.User;
import pl.emilfrankiewicz.model.UserDTO;
import pl.emilfrankiewicz.repository.UserRepository;

@Service
public class UserService {

	private UserRepository userRepository;

	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public void createUserFromDTO(UserDTO userDTO) {
		User user = new User();
		user.setUsername(userDTO.getUsername());
		user.setEmail(userDTO.getEmail());
		user.setPassword(userDTO.getPassword());
		user.setCreationDate(new Date());
		userRepository.save(user);
	}

	public void saveUser(User user) {
		userRepository.save(user);
	}
}
