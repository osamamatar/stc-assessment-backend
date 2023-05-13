package com.stc.filemanagements.service;

import com.stc.filemanagements.dto.FileDto;
import com.stc.filemanagements.dto.SimpleItemDto;
import com.stc.filemanagements.dto.SpaceDto;
import com.stc.filemanagements.dto.SpaceTree;

import java.util.List;

public interface FileManagementService {
	SpaceDto createSpace(SpaceDto spaceDto);

	SimpleItemDto createFolder(SimpleItemDto folder, Long spaceId);

	SimpleItemDto createFile(FileDto file, Long parentId);

	 List<SpaceTree> findAllItemsForCurrentUser();
}
