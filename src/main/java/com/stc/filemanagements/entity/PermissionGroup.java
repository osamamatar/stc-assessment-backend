package com.stc.filemanagements.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "PERMISSION_GROUP")
@Data
public class PermissionGroup {

	@Id
	private Long id;
	@Column
	private String groupName;

	@OneToMany(mappedBy = "permissionGroup")
	private List<Item> items = new ArrayList<>();


}
