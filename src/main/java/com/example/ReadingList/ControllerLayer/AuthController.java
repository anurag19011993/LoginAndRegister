package com.example.ReadingList.ControllerLayer;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.ReadingList.DTOorPayload.LoginDto;
import com.example.ReadingList.DTOorPayload.SignUpDto;
import com.example.ReadingList.ModelLayer.Role;
import com.example.ReadingList.ModelLayer.User;
import com.example.ReadingList.RepositoryLayer.RoleRepository;
import com.example.ReadingList.RepositoryLayer.UserRepository;

@Controller
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	private AuthenticationManager  authMang;
	
	@Autowired
	private UserRepository userRepo;
	

	@Autowired
	private RoleRepository roleRepo;
	

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@PostMapping("/SignUp")
	public ResponseEntity<?> registerUser(@RequestBody SignUpDto signUpDto){
		if(userRepo.existsByUsername(signUpDto.getUsername())) {
			return new ResponseEntity<>("Username already Exist" ,HttpStatus.BAD_REQUEST);
		}
		
		if(userRepo.existsByemail(signUpDto.getEmail())) {
			return new ResponseEntity<>("Email alredy exists",HttpStatus.BAD_REQUEST);
		}
		
		User user = new User();
		user.setEmail(signUpDto.getEmail());
		user.setUsername(signUpDto.getUsername());
		user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
		
		Role roles = roleRepo.findByName("ROLE_ADMIN").get();
		user.setRoles(Collections.singleton(roles));
		
		userRepo.save(user);
		
		return new ResponseEntity<>("Successfully registered" , HttpStatus.OK);
		
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> authenticateUser(@RequestBody LoginDto loginDto){
		Authentication auth = authMang.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(),loginDto.getPassword()));
		return new ResponseEntity<>("Login Success",HttpStatus.OK);
	}
}
