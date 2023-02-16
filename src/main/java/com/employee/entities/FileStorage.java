package com.employee.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Entity
public class FileStorage 
{
	

	public FileStorage(FileStorage build) {
		// TODO Auto-generated constructor stub
	}
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long fileId;
	private String name;
	private String fileType;
	private String filePath;
	
	
//	@ManyToMany(mappedBy = "fileStorage", cascade= {CascadeType.ALL})
//	private Set<Employee> employee=new HashSet<Employee>();
//	

	
	@ManyToOne
	private Employee employee;
	
	
	
}
