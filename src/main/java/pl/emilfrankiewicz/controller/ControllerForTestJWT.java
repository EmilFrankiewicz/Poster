package pl.emilfrankiewicz.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControllerForTestJWT {

	@GetMapping("/hello")
	public String securedPage() {
		return "Working fine";
	}

}
