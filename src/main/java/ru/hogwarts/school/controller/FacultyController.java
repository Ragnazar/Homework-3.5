package ru.hogwarts.school.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.FacultyService;

import java.util.Collection;

@RestController
@RequestMapping("faculty")
public class FacultyController {
    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @GetMapping(path = "{id}")   //GET http://localhost:8080/faculty
    public ResponseEntity<Faculty> getFacultyInfo(@PathVariable Long id) {
        Faculty faculty = facultyService.findFaculty(id);
        if (faculty == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(faculty);
    }

    public ResponseEntity<Faculty> findFacultyByNameOrColor(@RequestParam String name,
                                                            @RequestParam String color) {
        return ResponseEntity.ok(facultyService.findByNameOrColor(name, color));
    }
    @GetMapping(path = "all")  //GET http://localhost:8080/faculty/all
    public ResponseEntity<Collection<Faculty>> findAll() {
        return ResponseEntity.ok(facultyService.getAll());
    }

    @PostMapping //POST http://localhost:8080/faculty
    public Faculty addFaculty(Faculty faculty) {
        return facultyService.createFaculty(faculty);
    }

    @DeleteMapping(path = "{id}") //DELETE http://localhost:8080/faculty/23
    public ResponseEntity<Faculty> removeFaculty(@PathVariable Long id) {
        facultyService.deleteFaculty(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping  //PUT http://localhost:8080/faculty/23
    public ResponseEntity<Faculty> updateFaculty(@RequestBody Faculty faculty) {
        Faculty foundFaculty = facultyService.editFaculty(faculty);
        if (foundFaculty == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(foundFaculty);
    }

    @GetMapping(path="{id}/students")
    public ResponseEntity<Collection<Student>> getStudentsByFaculty(@PathVariable Long id){
        return ResponseEntity.ok(facultyService.findStudentsById(id));
    }
}
