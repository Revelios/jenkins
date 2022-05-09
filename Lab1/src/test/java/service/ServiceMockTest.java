package service;

import domain.Homework;
import domain.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import repository.GradeXMLRepository;
import repository.HomeworkXMLRepository;
import repository.StudentXMLRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ServiceMockTest {

    @Mock
    StudentXMLRepository fileRepository1;
    @Mock
    HomeworkXMLRepository fileRepository2;
    @Mock
    GradeXMLRepository fileRepository3;

    Service service;

    @BeforeEach
    void init() {
        service = new Service(fileRepository1, fileRepository2, fileRepository3);
    }

    @Test
    @DisplayName("Save student")
    public void shouldSaveStudent() {
        Student student = new Student("81", "Joe", 533);
        assertNotNull(fileRepository1);
        when(fileRepository1.save(student)).thenReturn(null);
        int res = service.saveStudent("81", "Joe", 533);
        assertEquals(0, res);
    }

    @Test
    @DisplayName("Delete homework")
    void shouldDeleteHomework() {
        assertNotNull(fileRepository2);
        when(fileRepository2.delete("42")).thenReturn(null);
        int res = service.deleteHomework("42");

        verify(fileRepository2).delete(ArgumentMatchers.eq("42"));
        assertEquals(0, res);
    }

    @Test
    @DisplayName("Save homework")
    void shouldSaveHomework() {
        Homework homework = new Homework("36", "Description", 5, 3);
        assertNotNull(fileRepository2);
        when(fileRepository2.save(homework)).thenReturn(null);
        int res = service.saveHomework("36", "Description", 5, 3);
        assertEquals(0, res);
    }
}
