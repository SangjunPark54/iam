package com.example.iam.service;

import com.example.iam.dto.UserRegistrationRecord;
import org.keycloak.representations.idm.UserRepresentation;

public interface KeycloakUserService {

    UserRegistrationRecord createUser(UserRegistrationRecord userRegistrationRecord);

    UserRepresentation getUserById(String userId); // Keycloak에서 사용자 정보를 조회 해온다.

    UserRepresentation deleteUserById(String userId);

}
