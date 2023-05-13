package com.stc.filemanagements.service;

import com.stc.filemanagements.config.UserSecurityService;
import com.stc.filemanagements.dto.*;
import com.stc.filemanagements.entity.File;
import com.stc.filemanagements.entity.Item;
import com.stc.filemanagements.enums.ItemType;
import com.stc.filemanagements.exceptions.NotFoundException;
import com.stc.filemanagements.mapper.ItemMapper;
import com.stc.filemanagements.repository.FileRepository;
import com.stc.filemanagements.repository.ItemRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FileManagementServiceImpl implements FileManagementService {

	@Autowired
	private ItemRepository itemRepository;
	@Autowired
	private FileRepository fileRepository;
	@Autowired
	private ItemMapper itemMapper;
	@Autowired
	private UserSecurityService userSecurityService;

	@Override
	public SpaceDto createSpace(SpaceDto spaceDto) {
		Item item = itemMapper.spaceToItem(spaceDto);
		item = itemRepository.save(item);
		spaceDto.setId(item.getId());
		return spaceDto;
	}

	@Override
	public SimpleItemDto createFolder(SimpleItemDto folder, Long spaceId) {
		Item space = itemRepository.findById(spaceId).orElseThrow(() -> new NotFoundException("space not found"));
		Item item = itemMapper.folderToItem(folder, space);
		item = itemRepository.save(item);
		folder.setId(item.getId());
		return folder;
	}

	@SneakyThrows
	@Override
	@Transactional
	public SimpleItemDto createFile(FileDto file, Long parentId) {
		Item parent = itemRepository.findById(parentId).orElseThrow(() -> new NotFoundException("folder or space not found"));
		Item item = itemMapper.fileToItem(file, parent);
		item = itemRepository.save(item);
		file.setId(item.getId());
		ByteArrayOutputStream fos = new ByteArrayOutputStream();
		fos.write(file.getContent().getBytes());
		File fileEntity = new File(null, fos.toByteArray(), item);
		fileRepository.save(fileEntity);
		file.setParent(parentId);
		return file;
	}

	public List<SpaceTree> findAllItemsForCurrentUser() {
		List<ProjectedItemDto> items = itemRepository.findAllSpacesForUser(userSecurityService.getLoggedInUserName());
		if (!items.isEmpty()) {
			return buildSpaceTrees(items);
		} else {
			return new ArrayList<>();
		}
	}

	private List<SpaceTree> buildSpaceTrees(List<ProjectedItemDto> items) {
		Map<ItemType, List<ProjectedItemDto>> itemTypeListMap = items.stream()
				.collect(Collectors.groupingBy(ProjectedItemDto::getType));

		List<SpaceTree> spaces = itemTypeListMap.get(ItemType.Space).stream().map(projectedItemDto -> {
			SpaceTree spaceTree = new SpaceTree();
			spaceTree.setId(projectedItemDto.getId());
			spaceTree.setName(projectedItemDto.getName());
			return spaceTree;
		}).collect(Collectors.toList());

		List<ProjectedItemDto> projectedItemFolderDtos = itemTypeListMap.get(ItemType.Folder);
		if (projectedItemFolderDtos != null) {
			List<FolderTree> folderList = projectedItemFolderDtos.stream()
					.map(projectedItemDto -> {
						FolderTree folderTree = new FolderTree();
						folderTree.setId(projectedItemDto.getId());
						folderTree.setName(projectedItemDto.getName());
						folderTree.setParent(projectedItemDto.getParent());
						return folderTree;
					}).collect(Collectors.toList());

			List<ProjectedItemDto> projectedItemFilesDtos = itemTypeListMap
					.get(ItemType.File);

			if (projectedItemFilesDtos != null) {
				projectedItemFilesDtos.stream()
						.map(projectedItemDto -> {
							SimpleItemDto simpleItemDto = new SimpleItemDto();
							simpleItemDto.setId(projectedItemDto.getId());
							simpleItemDto.setName(projectedItemDto.getName());
							simpleItemDto.setParent(projectedItemDto.getParent());
							return simpleItemDto;
						}).forEach(simpleItemDto -> {
							Optional<SpaceTree> space = spaces.stream()
									.filter(spaceTree -> spaceTree.getId().equals(simpleItemDto.getParent()))
									.findFirst();
							if (space.isPresent()) {
								space.get().getFiles().add(simpleItemDto);
							} else {
								FolderTree folder = folderList.stream().filter(folderTree -> folderTree.getId().equals(simpleItemDto.getParent())).findFirst().get();
								folder.getFiles().add(simpleItemDto);
							}
						});
			}

			folderList.forEach(folderTree -> {
				SpaceTree space = spaces.stream().filter(spaceTree -> spaceTree.getId().equals(folderTree.getParent())).findFirst().get();
				space.getFolders().add(folderTree);
			});
		}


		return spaces;
	}
}
