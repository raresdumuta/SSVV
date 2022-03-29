package repository;

import domain.Student;
import domain.Tema;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;

import static org.junit.jupiter.api.Assertions.*;

public class AbstractXMLRepositoryTest {

    @Test
    public void Test_save_student_when_id_is_null_returns_null() {
        Student student = new Student(null, "Rares", 200,"rares@gmail.com");
        StudentXMLRepo studentXMLRepository = new StudentXMLRepo( "src/main/resources/fisiere/Studenti.xml");
        assertNull(studentXMLRepository.save(student));
    }

    @Test
    public void Test_save_student_when_id_is_empty() {
        Student student = new Student("", "Rares", 200,"rares@gmail.com");
        StudentXMLRepo studentXMLRepository = new StudentXMLRepo( "src/main/resources/fisiere/Studenti.xml");
        assertEquals(student,studentXMLRepository.save(student));
    }

    @Test
    public void Test_save_student_when_data_is_valid_success() {
        Student student = new Student("55", "Anna", 200,"rares@gmail.com");
        StudentXMLRepo studentXMLRepository = new StudentXMLRepo( "src/main/resources/fisiere/Studenti.xml");

        assertNull(studentXMLRepository.save(student));
        studentXMLRepository.delete("55");
    }

    @Test
    public void Test_save_student_when_email_is_null() {
        Student student = new Student("1", "Rares", 200,null);
        StudentXMLRepo studentXMLRepository = new StudentXMLRepo( "src/main/resources/fisiere/Studenti.xml");

        assertEquals(student,studentXMLRepository.save(student));
    }

    @Test
    public void Test_save_student_when_email_is_empty() {
        Student student = new Student("1", "Rares", 200,"");
        StudentXMLRepo studentXMLRepository = new StudentXMLRepo( "src/main/resources/fisiere/Studenti.xml");
        assertEquals(student,studentXMLRepository.save(student));
    }

    @Test
    public void Test_save_student_when_grupa_negative() {
        Student student = new Student("1", "Rares", -1,"rares@gmail.com");
        StudentXMLRepo studentXMLRepository = new StudentXMLRepo( "src/main/resources/fisiere/Studenti.xml");

        assertEquals(student,studentXMLRepository.save(student));
    }

    @Test
    public void testSaveTema(){
        Tema tema = new Tema("123","frumoasa",4,6);
        TemaXMLRepo temaXMLRepo = new TemaXMLRepo("src/main/resources/fisiere/Teme.xml");

       assertNull(temaXMLRepo.save(tema));
       temaXMLRepo.delete("123");
    }



}