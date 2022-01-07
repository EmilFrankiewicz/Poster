package pl.emilfrankiewicz.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import pl.emilfrankiewicz.exception.ResourceDoesNotExistException;
import pl.emilfrankiewicz.model.User;
import pl.emilfrankiewicz.model.UserDTO;
import pl.emilfrankiewicz.service.UserService;

@RestController
public class UserController {

	private UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping(value = "/users/register", method = RequestMethod.POST)
	public ResponseEntity<User> registerUser(@Valid @RequestBody UserDTO userDTO, BindingResult result) throws ResourceDoesNotExistException {
		if (result.hasErrors()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}

		userService.createUserFromDTO(userDTO);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

}
