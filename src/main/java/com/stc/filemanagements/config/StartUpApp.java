package com.stc.filemanagements.config;

import com.stc.filemanagements.entity.Permission;
import com.stc.filemanagements.entity.PermissionGroup;
import com.stc.filemanagements.entity.User;
import com.stc.filemanagements.enums.PermissionGroupEnum;
import com.stc.filemanagements.enums.PermissionLevel;
import com.stc.filemanagements.repository.PermissionGroupRepository;
import com.stc.filemanagements.repository.PermissionRepository;
import com.stc.filemanagements.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class StartUpApp implements CommandLineRunner {
	@Autowired
	private PermissionGroupRepository permissionGroupRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private PermissionRepository permissionRepository;


	@Override
	public void run(String... args) throws Exception {

		if (permissionGroupRepository.findAll().isEmpty()) {

			//init  PermissionGroups
			PermissionGroup admin = new PermissionGroup();
			admin.setId(0L);
			admin.setGroupName(PermissionGroupEnum.ADMIN.name());


			PermissionGroup user = new PermissionGroup();
			user.setId(1L);
			user.setGroupName(PermissionGroupEnum.USER.name());

			permissionGroupRepository.save(admin);
			permissionGroupRepository.save(user);

			//init  users
			User user1 = new User();
			user1.setUserName("userAdminEdit");
			user1.setEmail("userAdminEdit@mail.com");
			user1.setPassword(passwordEncoder.encode("123"));
			userRepository.save(user1);

			User user2 = new User();
			user2.setUserName("userAdminView");
			user2.setEmail("userAdminView@mail.com");
			user2.setPassword(passwordEncoder.encode("123"));
			userRepository.save(user2);

			//init Permissions for users
			Permission permission1 = new Permission();
			permission1.setAuthUser(user1);
			permission1.setPermissionGroup(admin);
			permission1.setPermissionLevel(PermissionLevel.EDIT);
			permissionRepository.save(permission1);

			Permission permission2 = new Permission();
			permission2.setAuthUser(user2);
			permission2.setPermissionGroup(admin);
			permission2.setPermissionLevel(PermissionLevel.VIEW);
			permissionRepository.save(permission2);


		}


	}
}
