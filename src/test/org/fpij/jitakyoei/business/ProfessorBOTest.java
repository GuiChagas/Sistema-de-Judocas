package org.fpij.jitakyoei.business;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.fpij.jitakyoei.business.ProfessorBOImpl;
import org.fpij.jitakyoei.facade.AppFacade;
import org.fpij.jitakyoei.model.beans.Filiado;
import org.fpij.jitakyoei.model.beans.Professor;
import org.fpij.jitakyoei.view.AppView;
import org.junit.BeforeClass;
import org.junit.Test;

public class ProfessorBOTest {
    private static ProfessorBOImpl professorTest;
    private static Professor teacher;

    public static AppView appView(){
        return new AppView() {
            @Override
            public void handleModelChange(Object obj) {}

            @Override
            public void displayException(Exception e) {}

            @Override
            public void registerFacade(AppFacade facade) {}
        };
    }

    @BeforeClass
	public static void setUp(){
        Filiado affiliated = new Filiado();
        affiliated.setId(456465645L);
        affiliated.setNome("Professor Equals Teste");

        teacher = new Professor();
        teacher.setFiliado(affiliated);

        professorTest = new ProfessorBOImpl(appView());
    }

    @Test
    public void createProfessorTest() throws Exception {
        professorTest.createProfessor(teacher);
        Professor wantedTeacher = professorTest.searchProfessor(teacher).get(0);

        assertEquals(wantedTeacher, teacher);
        assertNotEquals(new Professor(), teacher);
    }

    @Test
    public void updateProfessorTest() throws Exception {
        professorTest.createProfessor(teacher);

        Professor wantedTeacher = professorTest.listAll().get(0);

        wantedTeacher.getFiliado().setNome("Professor Update Teste");
        professorTest.updateProfessor(wantedTeacher);

        assertEquals("Professor Update Teste", professorTest.listAll().get(0).getFiliado().getNome());
        assertNotEquals("Professor teste Update invalido", professorTest.listAll().get(0).getFiliado().getNome());
    }

    @Test
    public void createProfessorExceptionTest() throws Exception {
        Professor teacherNull = new Professor();
        teacherNull.setFiliado(null);
        
        Exception exception = assertThrows(Exception.class, () -> {
            professorTest.createProfessor(teacherNull);
        });
        
        String expectedMessage = "Desculpe, ocorreu um erro desconhecido ao salvar o professor.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }   

    @Test
    public void updateProfessorExceptionTest() throws Exception {
        Professor teacherNull = new Professor();
        teacherNull.setFiliado(null);

        Exception exception = assertThrows(Exception.class, () -> {
            professorTest.updateProfessor(teacherNull);
        });
        
        String expectedMessage = "Desculpe, ocorreu um erro desconhecido ao atualizar o professor.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }   

    /*@Test
    public void searchProfessorExceptionTest() throws Exception {
        Professor professor = new Professor();
        professor.setFiliado(null);
        
        Exception exception = assertThrows(Exception.class, () -> {
            professorTest.searchProfessor(null);
        });
        
        String expectedMessage = "Desculpe, ocorreu um erro desconhecido ao buscar os professores.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    } 

    @Test
    public void listAllProfessorExceptionTest() throws Exception {
        ProfessorBOImpl professorTestNull = new ProfessorBOImpl(appView());
        professorTestNull.listAll().clear();

        System.out.println(professorTestNull.listAll());

        Exception exception = assertThrows(Exception.class, () -> {
            professorTestNull.listAll().clear();;
        });
        
        String expectedMessage = "Desculpe, ocorreu um erro desconhecido ao listar professores.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }*/  

}
