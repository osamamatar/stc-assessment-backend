package com.stc.filemanagements.mapper;

import com.stc.filemanagements.dto.SimpleItemDto;
import com.stc.filemanagements.dto.SpaceDto;
import com.stc.filemanagements.entity.Item;
import com.stc.filemanagements.entity.PermissionGroup;
import com.stc.filemanagements.enums.ItemType;
import org.springframework.stereotype.Component;

@Component
public class ItemMapper {

	public Item spaceToItem(SpaceDto spaceDto) {
		PermissionGroup permissionGroup = new PermissionGroup();
		permissionGroup.setId(spaceDto.getPermissionGroupId());
		Item item = new Item();
		item.setName(spaceDto.getName());
		item.setParent(null);
		item.setType(ItemType.Space);
		item.setPermissionGroup(permissionGroup);
		return item;
	}
	public Item folderToItem(SimpleItemDto folder, Item space) {
		PermissionGroup permissionGroup = new PermissionGroup();
		permissionGroup.setId(space.getPermissionGroup().getId());
		Item item = new Item();
		item.setName(folder.getName());
		item.setParent(space);
		item.setType(ItemType.Folder);
		item.setPermissionGroup(permissionGroup);
		return item;
	}

	public Item fileToItem(SimpleItemDto file, Item parent) {
		PermissionGroup permissionGroup = new PermissionGroup();
		permissionGroup.setId(parent.getPermissionGroup().getId());
		Item item = new Item();
		item.setName(file.getName());
		item.setParent(parent);
		item.setType(ItemType.File);
		item.setPermissionGroup(permissionGroup);
		return item;
	}
}
