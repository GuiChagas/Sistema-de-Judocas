package org.fpij.jitakyoei.model.beans;

import org.junit.BeforeClass;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;



public class ProfessorTest {
    static List<Entidade> entityLst = new ArrayList<Entidade>();  
    private static Endereco address;
    private static Filiado affiliatedStudent;
    private static Professor teacher;


    @BeforeClass
    public static void setUp() {

        address = new Endereco();
        address.setBairro("Assunção");
        address.setCep("12345-678");
        address.setCidade("São Bernardo do Campo");
        address.setEstado("SP");
        address.setRua("Av Castelo Branco");

        Entidade entity = new Entidade();
        entity.setCnpj("1641646541654");
        entity.setNome("Entidade Teste");
        entity.setTelefone1("91457-4514");
        entity.setTelefone2("99595-5616");
        entity.setEndereco(address);

        entityLst.add(entity);

        Entidade entity2 = new Entidade();
        entity2.setCnpj("115616516516");
        entity2.setNome("Entidade Teste 2");
        entity2.setTelefone1("92145-0215");
        entity2.setTelefone2("92323-3265");
        entity2.setEndereco(address);        

        entityLst.add(entity2);

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
        teacher.setEntidades(entityLst);

    }

    @Test
    public void checkEntityIsNotBlank(){
        assertNotNull(teacher.getEntidades());
    }   

    @Test
    public void checkAffiliatedIsNotBlank(){
        assertNotNull(teacher.getFiliado());
    }      
    
    @Test 
    public void teacherToStringTest(){
        assertEquals(teacher.filiado.getNome(), teacher.toString());
    }
    
    @Test
    public void teacherHashCodeTest(){
        Integer affiliatedId = (int)(long)teacher.filiado.getId();

        assertNotEquals(0, teacher.hashCode());
        assertEquals(((29 * 7) + affiliatedId), teacher.hashCode());
    }   

    @Test
    public void teacherEqualsTest(){
        Filiado affiliatedOther = new Filiado();
        affiliatedOther.setId(14616251621L);

        Professor teacherOther = new Professor();
        teacherOther.setFiliado(affiliatedOther);

        assertEquals(false,teacher.equals(teacherOther));        
        assertEquals(true,teacher.equals(teacher));        
    }    
}
