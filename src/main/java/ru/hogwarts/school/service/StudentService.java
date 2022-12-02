package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.FacultyRepository;
import ru.hogwarts.school.repository.StudentRepository;

import javax.transaction.Transactional;
import java.util.Collection;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final FacultyRepository facultyRepository;

    public StudentService(StudentRepository studentRepository, FacultyRepository facultyRepository) {
        this.studentRepository = studentRepository;
        this.facultyRepository = facultyRepository;
    }

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student findStudent(long id) {
        return studentRepository.findById(id).orElse(null);
    }

    @Transactional
    public Collection<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student editStudent(Student student) {
        return studentRepository.save(student);
    }

    public void deleteStudent(long id) {
        studentRepository.deleteById(id);
    }


    public Collection<Student> findStudentsByAgeIsBetween(int age1, int age2) {
        return studentRepository.findStudentsByAgeIsBetween(age1, age2);
    }

    public Collection<Student> findByNameAndAge(String name, int age) {
        return studentRepository.findByNameIgnoreCaseAndAge(name,age);
    }


    public Faculty findFaculty(Long id) {
        return facultyRepository.findByStudentsId(id);
    }
}