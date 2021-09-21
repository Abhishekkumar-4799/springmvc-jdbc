package spring_security.jdbc_connection.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RoleBasedController {
	
	@GetMapping("/loginPage")
	public String showLogin() {
		System.out.println("Inside Controller-----------------------------");
		return "loginpage";
	}
	
	@GetMapping("/")
	public String showHome() {
		System.out.println("Inside Controller-----------------------------");
		return "home";
	}
	@GetMapping("/leaders")
	public String showLeader() {
		System.out.println("Inside Controller-----------------------------");
		return "leader";
	}
	@GetMapping("/system")
	public String showAdminPage() {
		return "admin";
	}
	@GetMapping("/access-denied")
	public String accessDenied() {
		return "access-denied";
	}
}
