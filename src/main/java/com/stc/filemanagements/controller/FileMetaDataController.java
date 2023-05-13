package com.stc.filemanagements.controller;

import com.stc.filemanagements.dto.SpaceTree;
import com.stc.filemanagements.service.FileManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class FileMetaDataController {

	@Autowired
	private FileManagementService fileManagementService;

	@QueryMapping
	public List<SpaceTree> getItems() {
		return fileManagementService.findAllItemsForCurrentUser();
	}
}
