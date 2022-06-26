package com.example.ReadingList.RepositoryLayer;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.ReadingList.ModelLayer.User;

public interface UserRepository extends JpaRepository<User, Long> {
//	@Query("CREATE TABLE IF NOT EXISTS user( ID int NOT NULL AUTO_INCREMENT,username VARCHAR(225) NOT NULL , password VARCHAR(225) NOT NULL, email VARCHAR(50) NOT NULL,PRIMARY KEY (ID))")
	Optional<User> findByEmail(String email);
	Optional<User> findByUsername(String username);
	Optional<User> findByUsernameOrEmail(String username , String email);
	
	Boolean existsByUsername(String username);
	Boolean existsByemail(String email);
}
