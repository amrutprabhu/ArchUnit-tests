package com.amrut.spring.repository;

import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    public String userInfo(String user){
        return user;
    }
}
