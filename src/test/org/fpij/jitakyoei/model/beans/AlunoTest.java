package org.fpij.jitakyoei.model.beans;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;

public class AlunoTest {

    private static Aluno student;
    private static Professor teacher;

    @BeforeClass
    public static void setUp() {
        Endereco address = new Endereco();
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


        Filiado affiliatedStudent = new Filiado();
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

        ProfessorEntidade teacherEntity = new ProfessorEntidade(null, null);
        teacherEntity.setEntidade(entity2);
        teacherEntity.setProfessor(teacher);

        student = new Aluno();
        student.setEntidade(entity);
        student.setFiliado(affiliatedStudent);
        student.setProfessor(teacher);
    }    
    
    @Test
    public void checkAffiliatedIsNotBlankTest(){
        assertNotNull(student.getFiliado());
    }

    @Test
    public void checkEntityIsNotBlankTest(){
        assertNotNull(student.getEntidade());
    }
    
    @Test
    public void checkTeacherIsNotBlankTest(){
        assertNotNull(student.getProfessor());
    }
    
    @Test 
    public void studentCopyPropertiesTest(){
        Entidade entityOther = new Entidade();
        Professor teacherOther = new Professor();
        Filiado affiliatedOther = new Filiado();
        affiliatedOther.setNome("TESTE FILIADO COPY");
        Aluno studentNew = new Aluno();
        studentNew.setEntidade(entityOther);
        studentNew.setFiliado(affiliatedOther);
        studentNew.setProfessor(teacherOther);

        assertNotEquals(student.getFiliado(), studentNew.getFiliado());
        student.copyProperties(studentNew);
        assertEquals(student.getFiliado(), studentNew.getFiliado());

    }  
    
    @Test
    public void studentHashCodeTest(){
        Integer affiliatedId = (int)(long)student.filiado.getId();

        assertNotEquals(0, teacher.hashCode());
        assertEquals(((29 * 7) + affiliatedId), student.hashCode());
    } 

    @Test
    public void studentEqualsTest(){

        Filiado affiliatedOther = new Filiado();
        affiliatedOther.setId(456465645L);
        affiliatedOther.setNome("Aluno Equals Teste");

        Aluno studentNew = new Aluno();
        studentNew.setFiliado(affiliatedOther);

        assertEquals(true,student.equals(student));
        assertEquals(false,student.equals(studentNew));
    }    
}
