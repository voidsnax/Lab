package com.example.studentapi;

import java.util.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final Map<Integer, Student> students = new HashMap<>();

    @GetMapping
    public List<Student> listStudents() {
        return new ArrayList<>(students.values());
    }

    @PostMapping
    public ResponseEntity<?> addStudent(@RequestBody Student student) {
        if (students.containsKey(student.getId())) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Student ID already exists.");
        }

        students.put(student.getId(), student);
        return ResponseEntity.status(HttpStatus.CREATED).body(student);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable int id) {
        Student student = students.get(id);

        if (student == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(student);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateStudent(
            @PathVariable int id, @RequestBody Student student) {

        if (!students.containsKey(id)) {
            return ResponseEntity.notFound().build();
        }

        student.setId(id);
        students.put(id, student);
        return ResponseEntity.ok(student);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable int id) {
        if (students.remove(id) == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok("Student deleted.");
    }
}