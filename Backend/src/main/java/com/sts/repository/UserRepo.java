package com.sts.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sts.model.User;

public interface UserRepo extends JpaRepository<User, Long>{
	
}
