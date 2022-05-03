package repository;

import domain.Student;
import domain.Tema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.junit.platform.commons.util.StringUtils;
import service.Service;
import validation.NotaValidator;
import validation.StudentValidator;
import validation.TemaValidator;
import validation.ValidationException;

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
        Tema tema = new Tema("123889","frumoasa",4,6);
        TemaXMLRepo temaXMLRepo = new TemaXMLRepo("src/main/resources/fisiere/Teme.xml");

       assertNull(temaXMLRepo.save(tema));
       temaXMLRepo.delete("123889");
    }

    Service service;
    @BeforeEach
    public void init(){
        StudentValidator studentValidator = new StudentValidator();
        TemaValidator temaValidator = new TemaValidator();
        String filenameStudent = "src/main/resources/fisiere/Studenti.xml";
        String filenameTema = "src/main/resources/fisiere/Teme.xml";
        String filenameNota = "src/main/resources/fisiere/Note.xml";

        StudentXMLRepo studentXMLRepository = new StudentXMLRepo(filenameStudent);
        TemaXMLRepo temaXMLRepository = new TemaXMLRepo(filenameTema);
        NotaValidator notaValidator = new NotaValidator(studentXMLRepository, temaXMLRepository);
        NotaXMLRepo notaXMLRepository = new NotaXMLRepo(filenameNota);
        service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);
    }

    @Test
    public void testSaveTemaInvalid(){
        Tema tema = new Tema("","",0,18);

        assertEquals(tema,service.addTema(tema));
    }
    @Test
    public void testSaveTemaDeadline0(){
        Tema tema = new Tema("1233455","dw",0,12);
        assertEquals(tema,service.addTema(tema));
    }

    @Test
    public void testSaveTemaPrimire0(){
        Tema tema = new Tema("12334556","dw",0,12);
        assertEquals(tema,service.addTema(tema));
    }




}