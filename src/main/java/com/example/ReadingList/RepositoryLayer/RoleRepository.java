package com.example.ReadingList.RepositoryLayer;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.ReadingList.ModelLayer.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
//	@Query("CREATE TABLE IF NOT EXISTS role( ID int PRIMARY KEY AUTO_INCREMENT,username VARCHAR(50) NOT NULL )")
	Optional<Role> findByName(String name);
}
