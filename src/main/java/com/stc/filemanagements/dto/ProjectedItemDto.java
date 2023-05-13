package com.stc.filemanagements.dto;

import com.stc.filemanagements.enums.ItemType;

public interface ProjectedItemDto {
	Long getId();

	String getName();

	ItemType getType();

	Long getParent();
}
