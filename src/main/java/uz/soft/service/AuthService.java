package uz.soft.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.soft.dtos.user.AuthenticationRequest;
import uz.soft.dtos.user.RegisterRequest;
import uz.soft.entity.AuthUser;
import uz.soft.entity.enums.UserRole;
import uz.soft.jwt.JwtService;
import uz.soft.repository.UserRepository;
import uz.soft.response.AuthenticationResponse;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private  final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        var user = AuthUser.builder()
                .email(request.getEmail())
                .passport(request.getPassport())
                .password(passwordEncoder.encode(request.getPassword()))
                .userRole(UserRole.COSTUMER)
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return  AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getPassport(),
                request.getPassword()
        ));
        var user = userRepository.findByPassport(request.getPassport())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return  AuthenticationResponse.builder()
                .token(jwtToken)
                .build();

    }

    public AuthUser getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String passNum;
        if (principal instanceof UserDetails) {
            passNum = ((UserDetails) principal).getUsername();
        } else {
            passNum = principal.toString();
        }
        return userRepository.findByPassport(passNum).orElseThrow(() -> {
            throw new UsernameNotFoundException("User not found");
        });
    }


}
