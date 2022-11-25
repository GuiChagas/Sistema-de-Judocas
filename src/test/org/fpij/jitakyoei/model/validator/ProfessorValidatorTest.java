package org.fpij.jitakyoei.model.validator;

import org.fpij.jitakyoei.model.beans.Endereco;
import org.fpij.jitakyoei.model.beans.Entidade;
import org.fpij.jitakyoei.model.beans.Faixa;
import org.fpij.jitakyoei.model.beans.Filiado;
import org.fpij.jitakyoei.model.beans.Professor;
import org.fpij.jitakyoei.model.beans.Rg;
import org.fpij.jitakyoei.util.CorFaixa;
import org.junit.BeforeClass;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ProfessorValidatorTest {
    static List<Entidade> entityLst = new ArrayList<Entidade>();  
    private static Endereco address;
    private static Filiado affiliatedStudent;
    private static Professor teacher;
    static List<Faixa> judoBelLst = new ArrayList<Faixa>();  ;

    @BeforeClass
    public static void setUp() {

        address = new Endereco();
        address.setBairro("Assunção");
        address.setNumero("300");
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
        entity2.setCnpj("");
        entity2.setNome("Entidade Teste 2");
        entity2.setTelefone1("92145-0215");
        entity2.setTelefone2("92323-3265");
        entity2.setEndereco(address);        

        entityLst.add(entity2);

        Faixa judoBelt = new Faixa(null, null);
        CorFaixa beltColor = CorFaixa.BRANCA;

        judoBelt.setCor(beltColor);
        judoBelt.setDataEntrega(new Date());

        judoBelLst.add(judoBelt);

        affiliatedStudent = new Filiado();
        affiliatedStudent.setId(12345678910L);
        affiliatedStudent.setNome("Professor Validator Test");
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
        affiliatedStudent.setFaixas(judoBelLst);


        teacher = new Professor();
        teacher.setFiliado(affiliatedStudent);
        teacher.setEntidades(entityLst);

    }

    @Test
    public void teacherValidateTest(){
        ProfessorValidator validator = new ProfessorValidator();

        assertEquals(true, validator.validate(teacher));

        teacher.getEntidades().get(1).setCnpj("115616516516");
        assertEquals(false, validator.validate(teacher));


    }
}
