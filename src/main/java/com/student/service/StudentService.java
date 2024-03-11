package com.student.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.student.entities.Student;
import com.student.repository.StudentRepository;

@Service
public class StudentService {
	
	@Autowired
	private StudentRepository studentRepository;
	
	
	public List<Student> getAllStudents()
	{
		List<Student> allStudents = (List<Student>) this.studentRepository.findAll();
		return allStudents;
	}
	
	
	public Student addStudent(Student student)
	{
		Student save = this.studentRepository.save(student);
		return save;
	}
	
	public Student getStudentById(int studentId)
	{
		Student student = null;
		try 
		{
		  student = this.studentRepository.findByStudentId(studentId);
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		return student;
	}
	
	public void deleteStudent(int studentId)
	{
		this.studentRepository.deleteById(studentId);
	}

	public void updateStudent(Student updateStudent, int studentId)
	{
		
		updateStudent.setStudentId(studentId);
		this.studentRepository.save(updateStudent);
		
	}
	
	
}
