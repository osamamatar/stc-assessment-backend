package com.stc.filemanagements.repository;

import com.stc.filemanagements.entity.Permission;
import com.stc.filemanagements.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {
	List<Permission> findAllByAuthUser(User user);
}
