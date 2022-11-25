package org.fpij.jitakyoei.model.validator;

import org.fpij.jitakyoei.model.beans.Aluno;
import org.fpij.jitakyoei.model.beans.Endereco;
import org.fpij.jitakyoei.model.beans.Entidade;
import org.fpij.jitakyoei.model.beans.Faixa;
import org.fpij.jitakyoei.model.beans.Filiado;
import org.fpij.jitakyoei.model.beans.Professor;
import org.fpij.jitakyoei.model.beans.ProfessorEntidade;
import org.fpij.jitakyoei.model.beans.Rg;
import org.fpij.jitakyoei.util.CorFaixa;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AlunoValidatorTest {
    private static Aluno student;
    private static Endereco address;
    private static Filiado affiliatedStudent;
    private static Professor teacher;
    private static ProfessorEntidade teacherEntity;
    static List<Faixa> judoBelLst = new ArrayList<Faixa>();

    @BeforeClass
    public static void setUp() {
        address = new Endereco();
        address.setBairro("Assunção");
        address.setCep("12345-678");
        address.setCidade("São Bernardo do Campo");
        address.setEstado("SP");
        address.setRua("Av Castelo Branco");
        address.setNumero("200");

        Entidade entity = new Entidade();
        entity.setCnpj("1641646541654");
        entity.setNome("Entidade Teste");
        entity.setTelefone1("91457-4514");
        entity.setTelefone2("99595-5616");
        entity.setEndereco(address);


        affiliatedStudent = new Filiado();
        affiliatedStudent.setId(12345678910L);
        affiliatedStudent.setNome("Aluno Teste");
        affiliatedStudent.setTelefone1("91457-4514");
        affiliatedStudent.setEmail("");
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

        teacherEntity = new ProfessorEntidade(null, null);
        teacherEntity.setEntidade(entity2);
        teacherEntity.setProfessor(teacher);

        CorFaixa beltColor = CorFaixa.BRANCA; 
        Faixa judoBelt = new Faixa(beltColor, new Date());

        judoBelLst.add(judoBelt);

        student = new Aluno();
        student.setFiliado(affiliatedStudent);
        student.getFiliado().setFaixas(judoBelLst);
        student.setEntidade(entity);
        student.setProfessor(teacher);
    } 

    @Test
    public void studentValidateTest(){
        AlunoValidator validator = new AlunoValidator();

        assertEquals(false, validator.validate(student));

        student.getFiliado().setEmail("filiado@filiado.com");
        assertEquals(true, validator.validate(student));

    }
}