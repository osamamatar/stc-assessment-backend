package com.stc.filemanagements.controller;


import com.stc.filemanagements.dto.*;
import com.stc.filemanagements.enums.ItemType;
import com.stc.filemanagements.repository.ItemRepository;
import com.stc.filemanagements.service.FileManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class FileManagementController {
	@Autowired
	private FileManagementService fileManagementService;

	@Autowired
	private ItemRepository itemRepository;

	@PostMapping("/spaces")
	public ResponseEntity<SpaceDto> createSpace(@RequestBody SpaceDto spaceDto) {
		SpaceDto createdSpaceDto = fileManagementService.createSpace(spaceDto);
		return new ResponseEntity<>(createdSpaceDto, HttpStatus.CREATED);
	}

	@PostMapping("/spaces/{space-id}/folders")
	public ResponseEntity<SimpleItemDto> createFolder(@PathVariable(name = "space-id") Long spaceId, @RequestBody SimpleItemDto folder) {
		SimpleItemDto createdFolder = fileManagementService.createFolder(folder, spaceId);
		return new ResponseEntity<>(createdFolder, HttpStatus.CREATED);
	}

	@PostMapping("/spaces/files")
	public ResponseEntity<SimpleItemDto> createFile(@RequestParam Long parentId, @RequestBody FileDto file) {
		SimpleItemDto createdFile = fileManagementService.createFile(file, parentId);
         return new ResponseEntity<>(createdFile, HttpStatus.CREATED);
	}


    @GetMapping("/spaces/view-meta-date")
	public List<SpaceTree> findAllItemsForCurrentUser(){
      return  fileManagementService.findAllItemsForCurrentUser();
	}
}
