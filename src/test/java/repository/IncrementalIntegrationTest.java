package repository;

import domain.Nota;
import domain.Student;
import domain.Tema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.Service;
import validation.NotaValidator;
import validation.StudentValidator;
import validation.TemaValidator;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class IncrementalIntegrationTest {
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
    public void testAddStudent(){
        Student student = new Student("55", "Anna", 200,"rares@gmail.com");
        assertNull(service.addStudent(student));
        service.deleteStudent("55");
    }

    @Test
    public void testAddTemaIT(){
        Student student = new Student("57", "Anna", 200,"rares@gmail.com");
        Tema tema = new Tema("123889","frumoasa",4,6);
        assertNull(service.addStudent(student));
        assertNull(service.addTema(tema));
        service.deleteStudent("57");
        service.deleteTema("123889");
    }

    @Test
    public void testAddNota(){
        Student student = new Student("56", "a", 200,"rares@gmail.com");
        Tema tema = new Tema("123889","frumoasa",3,3);
        LocalDate now = LocalDate.of(2018,10,18);
        Nota nota = new Nota("2","56","123889",10.0,now);

        assertNull(service.addStudent(student));
        assertNull(service.addTema(tema));
        assertEquals(service.addNota(nota,"buna"),10.0);
        service.deleteNota("2");
        service.deleteTema("123889");
        service.deleteStudent("56");
    }
}
