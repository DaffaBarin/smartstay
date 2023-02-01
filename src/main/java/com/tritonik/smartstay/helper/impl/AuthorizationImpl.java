package com.tritonik.smartstay.helper.impl;


import com.tritonik.smartstay.helper.Authorization;
import org.springframework.stereotype.Service;

/**
 * Hello! Thanks for checking on this
 *
 * Let me briefly explain the purpose of this class
 *
 * To save significant amount of development time spent,
 * instead of doing the complex and sophisticated authorization and authentication method such as OAuth or JWT,
 * I simply created this helper method to check whether current active user is admin or user
 * in some endpoints where supposedly only admin is eligible to do the task!
 * Thanks for understanding :)
 */
@Service
public class AuthorizationImpl implements Authorization {

    @Override
    public Boolean isAdmin(String role) {
        return role.equals("ADMIN");
    }
}
