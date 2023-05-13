package com.stc.filemanagements.entity;

import com.stc.filemanagements.enums.ItemType;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "ITEM")
@Data
@RequiredArgsConstructor
public class Item {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(EnumType.STRING)
	private ItemType type;

	@Column(unique = true)
	private String name;

	@OneToOne
	private Item parent;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "permission_group_id")
	private PermissionGroup permissionGroup;


}
