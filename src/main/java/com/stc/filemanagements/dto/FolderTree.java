package com.stc.filemanagements.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class FolderTree extends SimpleItemDto{
	private List<SimpleItemDto> files = new ArrayList<>();
}
