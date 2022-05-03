package repository;


import domain.Nota;
import domain.Tema;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.Service;
import validation.NotaValidator;
import validation.StudentValidator;
import validation.TemaValidator;
import validation.ValidationException;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class TestNota {
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
    }

    @Test
    public void testAddNotaWithInCorrectGrade(){
        LocalDate now = LocalDate.of(2018,10,18);
        Nota nota = new Nota("2","1","2",11.0,now);
        ValidationException exception = assertThrows(ValidationException.class,() -> service.addNota(nota,"buna"));

        assertEquals(exception.getMessage(),"Valoarea notei nu este corecta!");
    }

    @Test
    public void testAddNotaWithInCorrectDate(){
        LocalDate now = LocalDate.now();
        Nota nota = new Nota("2","1","2",8.0,now);
        ValidationException exception = assertThrows(ValidationException.class,() -> service.addNota(nota,"buna"));

        assertEquals(exception.getMessage(),"Studentul nu mai poate preda aceasta tema!");
    }

    @Test
    public void testAddNotaWithNonExistentStudent(){
        LocalDate now = LocalDate.of(2018,10,18);
        Nota nota = new Nota("2","555555","2",8.0,now);
        ValidationException exception = assertThrows(ValidationException.class,() -> service.addNota(nota,"buna"));

        assertEquals(exception.getMessage(),"Studentul nu exista!");
    }

    @Test
    public void testAddNotaWithNonExistentTema(){
        LocalDate now = LocalDate.of(2018,10,18);
        Nota nota = new Nota("2","1","5552",8.0,now);
        ValidationException exception = assertThrows(ValidationException.class,() -> service.addNota(nota,"buna"));

        assertEquals(exception.getMessage(),"Tema nu exista!");
    }
}
