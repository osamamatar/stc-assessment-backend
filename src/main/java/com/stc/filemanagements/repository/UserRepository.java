package com.stc.filemanagements.repository;

import com.stc.filemanagements.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
	User findByUserName(String username);
}
