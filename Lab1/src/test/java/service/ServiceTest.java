package service;

import domain.Grade;
import domain.Homework;
import domain.Student;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import repository.GradeXMLRepository;
import repository.HomeworkXMLRepository;
import repository.StudentXMLRepository;
import validation.GradeValidator;
import validation.HomeworkValidator;
import validation.StudentValidator;
import validation.Validator;

import static org.junit.jupiter.api.Assertions.*;

class ServiceTest {
    static Service service;

    @BeforeAll
    static void init() {
        Validator<Student> studentValidator = new StudentValidator();
        Validator<Homework> homeworkValidator = new HomeworkValidator();
        Validator<Grade> gradeValidator = new GradeValidator();

        StudentXMLRepository fileRepository1 = new StudentXMLRepository(studentValidator, "students.xml");
        HomeworkXMLRepository fileRepository2 = new HomeworkXMLRepository(homeworkValidator, "homework.xml");
        GradeXMLRepository fileRepository3 = new GradeXMLRepository(gradeValidator, "grades.xml");

        service = new Service(fileRepository1, fileRepository2, fileRepository3);
    }

    @Test
    @DisplayName("Save student")
    void shouldSaveStudent() {
        assertEquals(0, service.saveStudent("66", "Joe", 522));
    }

    @Test
    @DisplayName("Save student when name null")
    void shouldNotSaveStudentWhenNameNull() {
        assertNotEquals(0, service.saveStudent("6", null, 522));
    }

    @ParameterizedTest
    @DisplayName("Group number should match the required format")
    @ValueSource(ints = {10, 999, 1231234})
    void shouldTestGroupNumberFormatUsingValueSource(Integer groupNumber) {
        assertFalse(service.saveStudent("6", "Doe", groupNumber) == 0);
    }

    @Test
    @DisplayName("Save homework")
    void shouldSaveHomework() {
        assertEquals(0, service.saveHomework("36", "Description", 5, 3));
    }

    @Test
    @DisplayName("Delete homework")
    void shouldDeleteHomework() {
        assertEquals(0, service.deleteHomework("42"));
    }
}