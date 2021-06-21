package pl.emilfrankiewicz.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;

public class UserDTO {

	@NotEmpty
	@Length(min = 3, max = 10, message = "Username length is invalid ( must be 3-10 characters )")
	private String username;

	@NotEmpty
	@Email
	private String email;

	@NotEmpty
	@Length(min = 6, max = 20, message = "Password length is invalid ( must be 6-20 characters )")
	private String password;

	public UserDTO() {
	}

	public UserDTO(String username, String email, String password) {
		this.username = username;
		this.email = email;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
