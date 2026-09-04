package Edupulse.Backend.auth.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import Edupulse.Backend.auth.entity.User;
import Edupulse.Backend.auth.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    
    private final UserRepository userRepository;
    public CustomUserDetailsService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public UserDetails loadUserByUsername (String email)throws UsernameNotFoundException{
        User user = userRepository.findByEmail(email)
                     .orElseThrow(() ->
                    new UsernameNotFoundException("User not found with email : "+email));

        return org.springframework.security.core.userdetails.User
               .withUsername(user.getEmail())
               .password(user.getPassword())
               .authorities(
                user.getRoles()
                .stream()
                .map(role -> role.getName())
                .toArray(String[] :: new)
               )
               .disabled(!user.isActive())
               .build();
    }
}
