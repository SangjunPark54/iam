package com.example.iam.service;

import com.example.iam.dto.UserRegistrationRecord;
import jakarta.ws.rs.core.Response;
import org.keycloak.admin.client.Keycloak;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class KeycloakUserServiceImpl implements KeycloakUserService {

    @Value("master")
    private String realm;

    private Keycloak keycloak;

    public KeycloakUserServiceImpl(Keycloak keycloak) {
        this.keycloak = keycloak;
    }


    @Override
    public UserRegistrationRecord createUser(UserRegistrationRecord userRegistrationRecord) {

        // Create User
        UserRepresentation user = new UserRepresentation();
        user.setEnabled(true);
        user.setUsername(userRegistrationRecord.username());
        user.setEmail(userRegistrationRecord.email());
        user.setFirstName(userRegistrationRecord.firstName());
        user.setLastName(userRegistrationRecord.lastName());
        user.setEmailVerified(true);

        // Set Credential
        CredentialRepresentation cred = new CredentialRepresentation();
        cred.setValue(userRegistrationRecord.password());
        cred.setTemporary(false);
        cred.setType(CredentialRepresentation.PASSWORD);

        // Multiple Credential will apply
        List<CredentialRepresentation> creds = new ArrayList<>();
        creds.add(cred);
        user.setCredentials(creds);

        UsersResource usersResource = getUsersResource();

        Response response = usersResource.create(user);

        if (Objects.equals(201, response.getStatus())) {
            return userRegistrationRecord;
        } //응답이 201일 때 사용자 생성

//        response.readEntity()

        return null;
    }

    private UsersResource getUsersResource() {
        RealmResource realm1 = keycloak.realm(realm);
        return realm1.users();
    }

    @Override
    public UserRepresentation getUserById(String userId) {

        return getUsersResource().get(userId).toRepresentation();
    }

    @Override
    public UserRepresentation deleteUserById(String userId) {

        getUsersResource().delete(userId);
        return null;
    }
}
