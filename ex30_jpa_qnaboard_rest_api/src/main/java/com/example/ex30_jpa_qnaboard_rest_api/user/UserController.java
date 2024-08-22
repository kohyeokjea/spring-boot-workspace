package com.example.ex30_jpa_qnaboard_rest_api.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody UserDTO userDTO) {
        try {
            if (userDTO == null || userDTO.getPassword() == null) {
                throw new RuntimeException("Invalid Password value.");
            }

            // UserEntity user = new UserEntity();
            // user.setUsername(userDTO.getUsername());
            // user.setUsername(userDTO.getPassword());
            // user.setUsername(userDTO.getEmail());

            UserEntity user = UserEntity.builder().username(userDTO.getUsername()).password(userDTO.getPassword())
                    .email(userDTO.getEmail()).build();

            UserEntity registeredUser = userService.create(user);
            UserDTO responseUserDTO = UserDTO.builder().id(registeredUser.getId())
                    .username(registeredUser.getUsername()).email(registeredUser.getEmail()).build();

            return ResponseEntity.ok().body(responseUserDTO);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("User creation failde");
        }
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticate(@RequestBody UserDTO userDTO) {
        try {
            UserEntity user = userService.getByCredentials(userDTO.getEmail(), userDTO.getPassword());

            if (user != null) {
                final UserDTO responseUserDTO = UserDTO.builder().username(user.getUsername()).id(user.getId())
                        .email(user.getEmail()).build();
                return ResponseEntity.ok().body(responseUserDTO);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid email or password");
            }

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while preocessing the request");
        }
    }
}
