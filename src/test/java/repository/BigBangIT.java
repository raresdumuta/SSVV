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

import static org.junit.jupiter.api.Assertions.*;

public class BigBangIT {
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
    public void testAddNotaWithCorrectInput(){
        LocalDate now = LocalDate.of(2018,10,18);
        Nota nota = new Nota("2","1","2",10.0,now);
        assertEquals(service.addNota(nota,"buna"),10.0);
        service.deleteNota("2");
    }

    @Test
    public void testSaveTema(){
        Tema tema = new Tema("123889","frumoasa",4,6);
        assertNull(service.addTema(tema));
        service.deleteTema("123889");
    }

    @Test
    public void testAddStudent(){
        Student student = new Student("55", "Anna", 200,"rares@gmail.com");
        assertNull(service.addStudent(student));
        service.deleteStudent("55");
    }

    @Test
    public void testAddAll(){
        Student student = new Student("55", "Anna", 200,"rares@gmail.com");
        Tema tema = new Tema("123889","frumoasa",4,6);
        LocalDate now = LocalDate.of(2018,10,18);
        Nota nota = new Nota("2","55","123889",10.0,now);

        assertEquals(service.addNota(nota,"buna"),10.0);
        assertNull(service.addTema(tema));
        assertNull(service.addStudent(student));
        service.deleteNota("2");
        service.deleteTema("123889");
        service.deleteStudent("55");
    }
}
