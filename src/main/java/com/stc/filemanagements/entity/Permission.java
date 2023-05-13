package com.stc.filemanagements.entity;

import com.stc.filemanagements.enums.PermissionLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "PERMISSIONS")
@Data
public class Permission {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

    @ManyToOne
	private User authUser;

	@Enumerated(EnumType.STRING)
	@Column
	private PermissionLevel permissionLevel;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "group_id")
	private PermissionGroup permissionGroup;

}
