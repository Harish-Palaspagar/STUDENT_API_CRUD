package com.student.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.student.entities.Student;
import com.student.service.StudentService;

@RestController
public class StudentController {
	
	@Autowired
	private StudentService studentService;


	
	@PostMapping("/students")
	public ResponseEntity<Student> addStudent(@RequestBody Student student)
	{
		Student student2 = null;
		try 
		{
			student2 = this.studentService.addStudent(student);
			System.out.println(student2);
			return ResponseEntity.status(HttpStatus.CREATED).build();
			
		} catch (Exception e) 
		{
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
	}
	
	@GetMapping("/students") 
	public ResponseEntity<List<Student>> getAllStudents()
	{
	
		List<Student> allStudents = this.studentService.getAllStudents();
		if(allStudents.size()<=0)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(allStudents);
		
	}
	
	@GetMapping("/students/{studentId}")
	public ResponseEntity<Student> getStudentByStudentId(@PathVariable("studentId") int studentId)
	{
		Student studentById = this.studentService.getStudentById(studentId);
		if(studentById==null)
		{
			 return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		 return ResponseEntity.of(Optional.of(studentById));
	}
	
	@DeleteMapping("/students/{studentId}")
	public ResponseEntity<Void> deleteBook(@PathVariable("studentId") int studentId)//you can use ? in place of void too
	{
		try {
			this.studentService.deleteStudent(studentId);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); // successfully process the request but not returning content
		} catch (Exception e) {
		  e.printStackTrace();
		  return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}	
	}
	
	@PutMapping("/students/{studentId}")
	public ResponseEntity<Student> updateBook(@RequestBody Student student, @PathVariable("studentId") int studentId)
	{
		try {
			this.studentService.updateStudent(student, studentId);
			return ResponseEntity.ok(student); // you can use this too
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
	}
	
}
