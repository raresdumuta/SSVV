package repository;

import domain.Tema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.Service;
import validation.NotaValidator;
import validation.StudentValidator;
import validation.TemaValidator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class TestTema {
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
        Tema tema = new Tema("12334556","dw",12,0);
        assertEquals(tema,service.addTema(tema));
    }
    @Test
    public void testSaveTema(){
        Tema tema = new Tema("123889","frumoasa",4,6);
        assertNull(service.addTema(tema));
        service.deleteTema("123889");
    }

}
