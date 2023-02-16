package com.employee.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employee.entities.FileStorage;
import com.employee.entities.ImageAndFile;

public interface FileStorageRepository extends JpaRepository<FileStorage, Long> 
{
	 Optional<FileStorage> findById(long fileId);
}
