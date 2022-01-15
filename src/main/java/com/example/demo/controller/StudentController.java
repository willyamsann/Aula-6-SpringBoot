package com.example.demo.controller;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.RestController;

import com.example.demo.controller.model.Student;
import com.example.demo.repository.StudentRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class StudentController {

  
	@Autowired
	StudentRepository studentRepository;

  @GetMapping("/students")
	public ResponseEntity<List<Student>> getAllTutorials() {
		try {
			List<Student> student = new ArrayList<Student>();

			return new ResponseEntity<>( studentRepository.findAll(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

		@GetMapping("/students/{id}")
		public ResponseEntity<Student> getStudentById(@PathVariable("id") long id) {
			Optional<Student> studentData = studentRepository.findById(id);

			if (studentData.isPresent()) {
				return new ResponseEntity<>(studentData.get(), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
	}

		@DeleteMapping("/students/{id}")
		public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") long id) {
			try {
				studentRepository.deleteById(id);
				return new ResponseEntity<>(HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

		@PostMapping("/students")
		public ResponseEntity<Student> createStudent(@RequestBody Student student) {
			try {
				Student _student = studentRepository
						.save(new Student(student.getName()));
				return new ResponseEntity<>(_student, HttpStatus.CREATED);
			} catch (Exception e) {
				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

			@PutMapping("/students/{id}")
			public ResponseEntity<Student> updateStudent(@PathVariable("id") long id, @RequestBody Student student) {
				Optional<Student> studentData = studentRepository.findById(id);

				if (studentData.isPresent()) {
					Student _student = studentData.get();
					_student.setName(student.getName());
					return new ResponseEntity<>(studentRepository.save(_student), HttpStatus.OK);
				} else {
					return new ResponseEntity<>(HttpStatus.NOT_FOUND);
				}
			}
}
