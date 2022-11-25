package org.fpij.jitakyoei.facade;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.ArrayList;
import java.util.List;

import org.fpij.jitakyoei.model.beans.Aluno;
import org.fpij.jitakyoei.model.beans.Entidade;
import org.fpij.jitakyoei.model.beans.Filiado;
import org.fpij.jitakyoei.model.beans.Professor;
import org.fpij.jitakyoei.model.beans.ProfessorEntidade;
import org.fpij.jitakyoei.view.AppView;
import org.junit.BeforeClass;
import org.junit.Test;

public class AppFacadeImplTest {
    private static AppFacadeImpl appFacade;
    private static Aluno student;
    private static Filiado affiliated;
    private static Entidade entity;
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
        affiliated = new Filiado();
        affiliated.setId(456465645L);
        affiliated.setNome("Aluno Equals Teste");

        student = new Aluno();
        student.setFiliado(affiliated);

        appFacade = new AppFacadeImpl(appView());
        appFacade.createAluno(student);

        entity = new Entidade();
        entity.setNome("Entidade Teste");
        appFacade.createEntidade(entity);

        Filiado affiliatedTeacher = new Filiado();
        affiliatedTeacher.setId(30303030L);
        affiliatedTeacher.setNome("Professor Equals Teste");   

        teacher = new Professor();
        teacher.setFiliado(affiliatedTeacher);
        appFacade.createProfessor(teacher);

    }

    @Test
    public void createAlunoTest() throws Exception {
        Filiado newAffiliated = new Filiado();

        Aluno newStudent = new Aluno();
        newStudent.setFiliado(null);

        Aluno wantedStudent =  appFacade.searchAluno(student).get(0);

        assertEquals(wantedStudent, student);
        assertNotEquals(newAffiliated, student);
    }

    @Test
    public void updateAlunoTest() throws Exception {
        Aluno wantedStudent = appFacade.searchAluno(student).get(0);

        wantedStudent.getFiliado().setNome("Aluno Update Teste");

        appFacade.updateAluno(wantedStudent);

        assertEquals("Aluno Update Teste", wantedStudent.getFiliado().getNome());
        assertNotEquals("Aluno Teste Update invalido", wantedStudent.getFiliado().getNome());
    }

    @Test
    public void listAlunoTest() throws Exception {
        appFacade.listAlunos();
    }    

    @Test
    public void createEntidade() throws Exception {


        Entidade wantedEntity = appFacade.searchEntidade(entity).get(0);

        assertEquals(entity.getNome(), wantedEntity.getNome());
        assertNotEquals(new Entidade(), wantedEntity);
    }

    @Test
    public void updateEntidade() throws Exception {
      
        List<Entidade> retornoLista = appFacade.listEntidade();
        Entidade alunoReceived = retornoLista.get(0);

        alunoReceived.setNome("Entidade Update Teste");
        appFacade.updateEntidade(alunoReceived);

        List<Entidade> retornoLista2 = appFacade.listEntidade();
        assertEquals("Entidade Update Teste", retornoLista2.get(0).getNome());
        assertNotEquals("Entidade teste Update invalido", retornoLista2.get(0).getNome());
    }

    @Test
    public void createProfessorTest() throws Exception {      
        Professor wantedTeacher = appFacade.searchProfessor(teacher).get(0);

        assertEquals(wantedTeacher, teacher);
        assertNotEquals(new Professor(), teacher);
    }

    @Test
    public void updateProfessorTest() throws Exception {
        appFacade.createProfessor(teacher);

        Professor wantedTeacher = appFacade.listProfessores().get(0);

        wantedTeacher.getFiliado().setNome("Professor Update Teste");
        appFacade.updateProfessor(wantedTeacher);

        assertEquals("Professor Update Teste", appFacade.listProfessores().get(0).getFiliado().getNome());
        assertNotEquals("Professor teste Update invalido", appFacade.listProfessores().get(0).getFiliado().getNome());
    }

    @Test
    public void createProfessorEntidadeTest() throws Exception{
        ProfessorEntidade teacherEntity1 = new ProfessorEntidade(teacher, entity);
        List<ProfessorEntidade> teacherEntityLst2 = new ArrayList<ProfessorEntidade>();
        teacherEntityLst2.add(teacherEntity1);
        
        appFacade.createProfessorEntidade(teacherEntityLst2);      
    }

}
