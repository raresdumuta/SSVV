package repository;

import domain.Student;
import org.junit.jupiter.api.Test;
import validation.StudentValidator;
import validation.Validator;

import static org.junit.jupiter.api.Assertions.*;

class AbstractXMLRepositoryTest {

    @Test
    public void Test_save_student_when_id_is_null_returns_null() {
        Student student = new Student(null, "Rares", 200,"rares@gmail.com");
        StudentXMLRepo studentXMLRepository = new StudentXMLRepo( "src/main/resources/fisiere/Studenti.xml");

        assertNull(studentXMLRepository.save(student));
    }

    @Test
    public void Test_save_student_when_data_is_valid_success() {
        Student student = new Student("55", "Anna", 200,"rares@gmail.com");
        StudentXMLRepo studentXMLRepository = new StudentXMLRepo( "src/main/resources/fisiere/Studenti.xml");

        assertNull(studentXMLRepository.save(student));

        studentXMLRepository.delete("55");
    }
}