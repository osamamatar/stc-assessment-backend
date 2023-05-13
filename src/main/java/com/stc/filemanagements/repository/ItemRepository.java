package com.stc.filemanagements.repository;

import com.stc.filemanagements.dto.ProjectedItemDto;
import com.stc.filemanagements.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

	@Query( value =
			"select i.id as id,i.name as name ,i.type as type ,i.parent.id as parent from Item i" +
			" Join  PermissionGroup pg on i.permissionGroup.id = pg.id " +
			"join Permission p  on pg.id = p.permissionGroup.id " +
			"join User au on au.userName  = p.authUser.userName " +
			"where au.userName =:userName")
    List<ProjectedItemDto> findAllSpacesForUser(@Param("userName") String userName);
}
