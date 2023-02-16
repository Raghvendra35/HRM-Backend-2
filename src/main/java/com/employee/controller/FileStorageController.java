package com.employee.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import com.employee.service.FileStorageService;

@RestController
@RequestMapping("api")
public class FileStorageController
{
	
		 
	@Autowired
	private FileStorageService fileStorageService;
	


	
    //Save Multiple files with EmployeeId
	@PostMapping("/uploadmultiple/{employeeId}")
	public ResponseEntity<?> uploadMultipartFiles(@PathVariable ("employeeId") int employeeId, @RequestParam("files") MultipartFile[] files) throws IllegalStateException, IOException
	{
		//System.out.println(files.length);
	    String path=this.fileStorageService.multipleFile(employeeId, files);
		return ResponseEntity.status(HttpStatus.OK).body(path);
	}

	
	
	
	
	
	//Get Image
	@GetMapping("/getimage/{imageId}")
	public ResponseEntity<?> downloadImageFromFileSystem(@PathVariable long imageId) throws IOException 
	{
		byte[] imageData=this.fileStorageService.downloadImageFromFileSystem(imageId);
		
		return ResponseEntity.status(HttpStatus.OK)
//				.contentType(MediaType.valueOf("image/int"))
				.contentType(MediaType.ALL)
				.body(imageData);

	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//Save Image
	@PostMapping("/filesystem")
	public ResponseEntity<?> uploadImageToFileSystem(@RequestParam("image") MultipartFile file) throws IOException 
	{
		String uploadImage = this.fileStorageService.uploadImageToFileSystem(file);
		
		System.out.println("Inside Controller ");
		return ResponseEntity.status(HttpStatus.OK)
				.body(uploadImage);
	
	}

	
	
	
	
	private Logger logger=LoggerFactory.getLogger(FileStorageController.class);
	
//	//Save Multiple files
//	@PostMapping("/uploadmultiple")
//	public ResponseEntity<?> uploadMultipartFiles(@RequestParam("files") MultipartFile[] files) throws IllegalStateException, IOException
//	{
//		//System.out.println(files.length);
//	
//	 String path=this.imageAndFileService.multipleFile(files);
//		
//		return ResponseEntity.status(HttpStatus.OK).body(path);
//	}
	
	
	
			
		
		
	    //Save Multiple files without employeeId
		
			@PostMapping("/uploadmultiple")
			public ResponseEntity<?> uploadMultipartFilesOnly(@RequestParam("files") MultipartFile[] files) throws IllegalStateException, IOException
			{
				//System.out.println(files.length);
			    String path=this.fileStorageService.multipleFileOnly(files);
				return ResponseEntity.status(HttpStatus.OK).body(path);
			}
		
	

			
}
