package org.fpij.jitakyoei.business;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.fpij.jitakyoei.business.ProfessorEntidadeBOImpl;
import org.fpij.jitakyoei.facade.AppFacade;
import org.fpij.jitakyoei.model.beans.Endereco;
import org.fpij.jitakyoei.model.beans.Entidade;
import org.fpij.jitakyoei.model.beans.Filiado;
import org.fpij.jitakyoei.model.beans.Professor;
import org.fpij.jitakyoei.model.beans.ProfessorEntidade;
import org.fpij.jitakyoei.model.beans.Rg;
import org.fpij.jitakyoei.view.AppView;
import org.junit.BeforeClass;
import org.junit.Test;


public class ProfessorEntidadeBOTest {

    private static ProfessorEntidadeBOImpl profEntidadeTest;
    static List<Entidade> entityLst = new ArrayList<Entidade>();  
    private static Endereco address;
    private static Filiado affiliatedStudent;
    private static Professor teacher;
    private static Entidade entity;
    static List<ProfessorEntidade> teacherEntityLst = new ArrayList<ProfessorEntidade>();

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
        address = new Endereco();
        address.setBairro("Assunção");
        address.setCep("12345-678");
        address.setCidade("São Bernardo do Campo");
        address.setEstado("SP");
        address.setRua("Av Castelo Branco");

        entity = new Entidade();
        entity.setCnpj("1641646541654");
        entity.setNome("Entidade Teste");
        entity.setTelefone1("91457-4514");
        entity.setTelefone2("99595-5616");
        entity.setEndereco(address);


        affiliatedStudent = new Filiado();
        affiliatedStudent.setId(12345678910L);
        affiliatedStudent.setNome("Aluno Teste");
        affiliatedStudent.setTelefone1("91457-4514");
        affiliatedStudent.setEmail("teste@teste.com");
        affiliatedStudent.setTelefone2("99595-5616");
        affiliatedStudent.setRegistroCbj("123456");
        affiliatedStudent.setCpf("123.456.789-10");
        affiliatedStudent.setDataNascimento(new Date());
        affiliatedStudent.setDataCadastro(new Date());
        affiliatedStudent.setRg(new Rg("012345-X", "SP"));
        affiliatedStudent.setObservacoes("Observacao teste");
        affiliatedStudent.setEndereco(address); 
        
        teacher = new Professor();
        teacher.setFiliado(affiliatedStudent);

        Entidade entity2 = new Entidade();
        entity2.setCnpj("115616516516");
        entity2.setNome("Entidade Teste 2");
        entity2.setTelefone1("92145-0215");
        entity2.setTelefone2("92323-3265");
        entity2.setEndereco(address); 

        ProfessorEntidade teacherEntity = new ProfessorEntidade(teacher, entity2);
        teacherEntityLst.add(teacherEntity);
        
        profEntidadeTest = new ProfessorEntidadeBOImpl(appView());

    }
    
    @Test
    public void createProfessorEntidadeTest() throws Exception{
        ProfessorEntidade teacherEntity1 = new ProfessorEntidade(teacher, entity);
        List<ProfessorEntidade> teacherEntityLst2 = new ArrayList<ProfessorEntidade>();
        teacherEntityLst2.add(teacherEntity1);
        
        profEntidadeTest.createProfessorEntidade(teacherEntityLst2);      
    }

    @Test
    public void createProfessorEntidadeExceptionTest() throws Exception {

        Exception exception = assertThrows(Exception.class, () -> {
            teacherEntityLst.add(new ProfessorEntidade(null, null));
            profEntidadeTest.createProfessorEntidade(teacherEntityLst);
        });
        String expectedMessage = "Desculpe, ocorreu um erro desconhecido ao salvar os relacionamentos.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    
    /*@Test(expected=IllegalArgumentException.class)
    public void createProfessorEntidadeILE() throws IllegalArgumentException {

        List<ProfessorEntidade> teacherEntityLstILE = new ArrayList<ProfessorEntidade>();
        ProfessorEntidade teste = new ProfessorEntidade(null, null);
        //teacherEntityLstILE.add(teste);

        assertThrows(IllegalArgumentException.class, () -> profEntidadeTest.createProfessorEntidade(teacherEntityLstILE));

    }*/

}
