package org.fpij.jitakyoei.business;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.fpij.jitakyoei.business.AlunoBOImpl;
import org.fpij.jitakyoei.facade.AppFacade;
import org.fpij.jitakyoei.model.beans.Aluno;
import org.fpij.jitakyoei.model.beans.Filiado;
import org.fpij.jitakyoei.view.AppView;
import org.junit.BeforeClass;
import org.junit.Test;

public class AlunoBOTest {
    private static AlunoBOImpl alunoTest;
    private static Aluno student;
    private static Filiado affiliated;

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
        affiliated = new Filiado();
        affiliated.setId(456465645L);
        affiliated.setNome("Aluno Equals Teste");

        student = new Aluno();
        student.setFiliado(affiliated);

       alunoTest = new AlunoBOImpl(appView());
    }

    @Test
    public void createAlunoTest() throws Exception {
        alunoTest.createAluno(student);

        Filiado newAffiliated = new Filiado();

        Aluno newStudent = new Aluno();
        newStudent.setFiliado(null);

        Aluno wantedStudent =  alunoTest.searchAluno(student).get(0);

        assertEquals(wantedStudent, student);
        assertNotEquals(newAffiliated, student);
    }

    @Test
    public void updateAlunoTest() throws Exception {
        alunoTest.createAluno(student);

        Aluno alunoReceived = alunoTest.listAll().get(0);

        alunoReceived.getFiliado().setNome("Aluno Update Teste");
        alunoTest.updateAluno(alunoReceived);

        assertEquals("Aluno Update Teste", alunoTest.listAll().get(0).getFiliado().getNome());
        assertNotEquals("Aluno Teste Update invalido", alunoTest.listAll().get(0).getFiliado().getNome());
    }

    @Test
    public void createAlunoExceptionTest() throws Exception {
        Aluno aluno = new Aluno();
        aluno.setFiliado(null);
        
        Exception exception = assertThrows(Exception.class, () -> {
            alunoTest.createAluno(aluno);
        });
        
        String expectedMessage = "Desculpe, ocorreu um erro desconhecido ao salvar o aluno.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test()
    public void updateAlunoExceptionTest() throws Exception {
        Aluno aluno = new Aluno();
        
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            alunoTest.updateAluno(aluno);
        });
        
        String expectedMessage = "Ocorreu um erro ao salvar os dados do aluno. Verifique se todos os dados foram preenchidos corretamente!";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }   

    /*@Test
    public void searchAlunoExceptionTest() throws Exception {
        
        System.out.println(alunoTest.listAll());
        
        Exception exception = assertThrows(Exception.class, () -> {
            alunoTest.searchAluno(null);
        });
        
        String expectedMessage = "Desculpe, ocorreu um erro desconhecido ao buscar os aluno.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }*/
}
