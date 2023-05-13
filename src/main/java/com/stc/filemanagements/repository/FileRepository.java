package com.stc.filemanagements.repository;

import com.stc.filemanagements.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository  extends JpaRepository<File, Long> {
}
