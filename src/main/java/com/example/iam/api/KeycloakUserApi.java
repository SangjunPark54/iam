package com.example.iam.api;

import com.example.iam.dto.UserRegistrationRecord;
import com.example.iam.service.KeycloakUserService;
import lombok.AllArgsConstructor;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class KeycloakUserApi extends RuntimeException {

    @Autowired
    private KeycloakUserService keycloakUserService;

    @PostMapping
    public UserRegistrationRecord craeteUser(@RequestBody UserRegistrationRecord userRegistrationRecord) {

        return keycloakUserService.createUser(userRegistrationRecord);
    }
    @GetMapping
    public UserRepresentation getUser(Principal principal) {

        return keycloakUserService.getUserById(principal.getName());
    }

    @DeleteMapping("/{userid}")
    public void deleteUserById(@PathVariable String userid) {
        keycloakUserService.deleteUserById(userid);
    }
}
