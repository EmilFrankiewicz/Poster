package pl.emilfrankiewicz.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import pl.emilfrankiewicz.exception.ResourceDoesNotExistException;
import pl.emilfrankiewicz.model.User;
import pl.emilfrankiewicz.model.UserDTO;
import pl.emilfrankiewicz.service.CustomUserDetailsService;
import pl.emilfrankiewicz.service.UserService;

@RestController
public class UserController {

	private UserService userService;
	private CustomUserDetailsService customUserDetailsService;

	@Autowired
	public UserController(UserService userService, CustomUserDetailsService customUserDetailsService) {
		this.userService = userService;
		this.customUserDetailsService = customUserDetailsService;
	}

	@RequestMapping(value = "/users/register", method = RequestMethod.POST)
	public ResponseEntity<User> registerUser(@Valid @RequestBody UserDTO userDTO, BindingResult result)
			throws ResourceDoesNotExistException {
		if (result.hasErrors()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}

		userService.createUserFromDTO(userDTO);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@RequestMapping(value = "/users/{username}", method = RequestMethod.GET)
	public UserDetails getUserByUsername(@PathVariable("username")String username) {  // already only for test; for authentication 
		return customUserDetailsService.loadUserByUsername(username);
	}

}
