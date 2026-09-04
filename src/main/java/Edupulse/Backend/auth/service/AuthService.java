package Edupulse.Backend.auth.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import Edupulse.Backend.auth.dto.RegisterRequest;
import Edupulse.Backend.auth.entity.Role;
import Edupulse.Backend.auth.entity.User;
import Edupulse.Backend.auth.repository.RoleRepository;
import Edupulse.Backend.auth.repository.UserRepository;

@Service
public class AuthService {
    
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    
    public AuthService(
        UserRepository userRepository,
        RoleRepository roleRepository,
        PasswordEncoder passwordEncoder
    ){
    this.userRepository = userRepository;
    this.roleRepository = roleRepository;
    this.passwordEncoder = passwordEncoder;
    
    }
    public void register(RegisterRequest request){
        if(userRepository.findByEmail(request.getEmail()).isPresent()){
            throw new RuntimeException("Email already Exist");
        }
        String encodedPassword = passwordEncoder.encode(request.getPassword());

        Role userRole = roleRepository.findByName("ROLE_USER")
        .orElseThrow(() -> new RuntimeException("USER_ROLE is not found"));
        User user = new User(
            request.getEmail(),
            encodedPassword,
            request.getFirstName(),
            request.getLastName()
        );
        user.getRoles().add(userRole);
        userRepository.save(user);
    }
}
