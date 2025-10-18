package com.wecp.progressive.controller;

import com.wecp.progressive.dto.StudentDTO;
import com.wecp.progressive.entity.Student;
import com.wecp.progressive.service.StudentService;
import com.wecp.progressive.service.impl.StudentServiceImplArraylist;
import com.wecp.progressive.service.impl.StudentServiceImplJpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping
public class StudentController {
    @Autowired
    private StudentServiceImplJpa studentServiceImplJpa;

    @Autowired
    private StudentServiceImplArraylist studentServiceImplArraylist;

    @GetMapping("/student")
    public ResponseEntity<List<Student>> getAllStudents() throws Exception {
        return new ResponseEntity<>(studentServiceImplJpa.getAllStudents(), HttpStatus.OK);
    }
  
    @GetMapping("/student/{studentId}")
    public ResponseEntity<Student> getStudentById(@PathVariable int studentId) {
        try {
            return new ResponseEntity<>(studentServiceImplJpa.getStudentById(studentId),HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping("/student")
    public ResponseEntity<Integer> addStudent(@RequestBody Student student) {
        {
            try
            {
                Integer t=studentServiceImplJpa.addStudent(student);
                return new ResponseEntity<>(t, HttpStatus.CREATED);
            }
            catch(RuntimeException e)
            {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            catch(Exception e)
            {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @PutMapping("/student/{studentId}")
    public ResponseEntity<Void> updateStudent(@PathVariable int studentId, @RequestBody StudentDTO studentDTO) {
        try {
            studentDTO.setStudentId(studentId);
            studentServiceImplJpa.modifyStudentDetails(studentDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/student/{studentId}")
    public ResponseEntity<Void> deleteStudent(@PathVariable int studentId) {
        studentServiceImplJpa.deleteStudent(studentId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @GetMapping("/student/fromArrayList")
    public ResponseEntity<List<Student>> getAllStudentFromArrayList() throws Exception {
        return new ResponseEntity<>(studentServiceImplArraylist.getAllStudents(),HttpStatus.OK);
    }

    @PostMapping("/student/toArrayList")
    public ResponseEntity<Integer> addStudentToArrayList(@RequestBody Student student) throws Exception {
        return new ResponseEntity<>(studentServiceImplArraylist.addStudent(student),HttpStatus.CREATED);
    }

    @GetMapping("/student/fromArrayList/sorted")
    public ResponseEntity<List<Student>> getAllStudentSortedByNameFromArrayList() throws Exception {
        return new ResponseEntity<>(studentServiceImplArraylist.getAllStudentSortedByName(),HttpStatus.OK);
    }
}