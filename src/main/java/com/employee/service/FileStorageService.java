package com.employee.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.employee.dao.FileStorageRepository;
import com.employee.dao.ImageAndFileRepository;
import com.employee.entities.Employee;
import com.employee.entities.FileStorage;
import com.employee.entities.ImageAndFile;

@Service
public class FileStorageService 
{

	@Autowired
	private ImageAndFileRepository imageAndFileRepo;
	@Autowired
	private FileStorageRepository fileStorageRepo;
	@Autowired
	private EmployeeService employeeService;
	
	
	// private final String FOLDER_PATH="C:\\Users\\Lenovo\\eclipse-workspace\\HRManagements\\src\\main\\resources\\MyFiles\\";
	 
	   
	 
	 
	
	   //Save multipartFile and EmployeeId 
	 
	   @Value("${project.files}")
	   private String path2;
	
	   public String multipleFile(int employeeId, MultipartFile[] files) throws IllegalStateException, IOException
	   {

			 System.out.println("inside Service +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			 System.out.println(files.length);
			  
			  // String imageFilePath2=path2;
			   FileStorage fileStorage2=new FileStorage();
			 
			   Employee employee= this.employeeService.getEmployeeById(employeeId).get();
			  // fileStorage2.setEmployee(employee);
			 
					 
			   List<FileStorage> fileList2=new ArrayList<FileStorage>();
				
				for(MultipartFile file2: files)
				{
					 fileStorage2.setEmployee(employee);
					 
				String imageFilePath2=path2+file2.getOriginalFilename();
				
			    FileStorage fileStorage=this.fileStorageRepo.save(fileStorage2.builder()
						                                           .name(file2.getOriginalFilename())
						                                           .fileType(file2.getContentType())
						                                           .filePath(imageFilePath2)
						                                           .employee(employee).build());
						                                          
						                                        
				
				file2.transferTo(new File(imageFilePath2));
				System.out.println("Inside Service ++++++++++++++++++++++++++");
				System.out.println(fileStorage.getName());
				
				}
				
				//this.fileStorageRepo.saveAll(fileList2);
				return "Saved Data !!!";
	   }
	   
	   
	   
	  
	   
	
	   
	   
	   
	   
	   //Fetch Image

	    public byte[] downloadImageFromFileSystem(long imageId) throws IOException {
	        Optional<FileStorage> fileData =this.fileStorageRepo.findById(imageId);
	        String filePath=fileData.get().getFilePath();
	        byte[] images = Files.readAllBytes(new File(filePath).toPath());
	        return images;
	    }
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    //Save Image only
	    @Value("${project.emlimage}")
	    private String path;
	
	    public String uploadImageToFileSystem(MultipartFile file) throws IOException
	    {
	    	
	        String imageFilePath=path+file.getOriginalFilename();
	        System.out.println("Path "+imageFilePath);
           
	        
	        ImageAndFile fileData=this.imageAndFileRepo.save(ImageAndFile.builder()
	                                                                     .name(file.getOriginalFilename())
	                                                                     .imageType(file.getContentType())
	                                                                     .filePath(imageFilePath).build());

	        file.transferTo(new File(imageFilePath));

	        System.out.println("Inside Service ");
	        
	        
	        
	        if (fileData != null) 
	        {
	            return "file uploaded successfully : " + imageFilePath;
	        }
	        return null;
	    }
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    // Upload Only Files
		   @Value("${project.files}")
		   private String path3;
		   //Save multipart
		   public String multipleFileOnly(MultipartFile[] files) throws IllegalStateException, IOException
		   {

				 System.out.println("inside Service +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
				 System.out.println(files.length);
				  
				  // String imageFilePath2=path2;
				   FileStorage fileStorage2=new FileStorage();
				  // fileStorage2.setEmployee(employee);
				 				 
				   List<FileStorage> fileList2=new ArrayList<FileStorage>();
					
					for(MultipartFile file2: files)
					{
					
					String imageFilePath2=path2+file2.getOriginalFilename();
					
				    FileStorage fileStorage=this.fileStorageRepo.save(fileStorage2.builder()
							                                           .name(file2.getOriginalFilename())
							                                           .fileType(file2.getContentType())
							                                           .filePath(imageFilePath2)
							                                           .build());
							                                          
							                                        
					
					file2.transferTo(new File(imageFilePath2));
					System.out.println("Inside Service ++++++++++++++++++++++++++");
					System.out.println(fileStorage.getName());
					
					}
					
					//this.fileStorageRepo.saveAll(fileList2);
					return "Saved Data !!!";
		   }
		   

	   
}
