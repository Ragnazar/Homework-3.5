package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hogwarts.school.model.Student;

import java.util.Collection;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    Collection<Student> findByNameIgnoreCaseAndAge(String name, int age);

    Collection<Student> findStudentsByAgeIsBetween(int age1, int age2);

    Collection<Student> findStudentsByFacultyId(Long facultyId);
}
