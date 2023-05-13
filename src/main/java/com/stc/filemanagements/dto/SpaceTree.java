package com.stc.filemanagements.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class SpaceTree extends SimpleItemDto {
	private List<FolderTree> folders = new ArrayList<>();
	private List<SimpleItemDto> files = new ArrayList<>();
}
