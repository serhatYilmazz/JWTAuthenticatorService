package com.java.jwt;

import com.java.jwt.common.AuthenticationRequestDTO;
import com.java.jwt.common.AuthenticationResponseDTO;
import com.java.jwt.security.service.UserDetailsServiceImpl;
import com.java.jwt.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestContoller {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("/test")
    public String test() {
        return "Hello";
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponseDTO> authenticate(@RequestBody AuthenticationRequestDTO authenticationRequestDTO) {
        try {
            authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(authenticationRequestDTO.getUsername(),
                            authenticationRequestDTO.getPassword()));
        } catch (BadCredentialsException e) {
            System.out.println("Bad credentials");
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequestDTO.getUsername());
        String token = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponseDTO(token));
    }
}
