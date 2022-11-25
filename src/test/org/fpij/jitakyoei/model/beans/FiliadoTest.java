package org.fpij.jitakyoei.model.beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.fpij.jitakyoei.util.CorFaixa;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;


public class FiliadoTest {
    private static Filiado affiliatedStudent;
    private static Endereco address;
    static List<Faixa> beltLst = new ArrayList<Faixa>();  

    @BeforeClass
    public static void setUp() {
        address = new Endereco();
        address.setBairro("Assunção");
        address.setCep("12345-678");
        address.setCidade("São Bernardo do Campo");
        address.setEstado("SP");
        address.setRua("Av Castelo Branco");

        CorFaixa beltColor1 = CorFaixa.BRANCA; 

        Faixa judoBelt = new Faixa();
        judoBelt.setCor(beltColor1);
        judoBelt.setDataEntrega(new Date());

        beltLst.add(judoBelt);

        CorFaixa beltColor2 = CorFaixa.AZUL; 

        Faixa judoBelt2 = new Faixa();
        judoBelt2.setCor(beltColor2);
        judoBelt2.setDataEntrega(new Date());

        beltLst.add(judoBelt2);

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
        affiliatedStudent.setFaixas(beltLst);
    }

    @Test
    public void checkIdIsNotBlankTest(){
        assertNotNull(affiliatedStudent.getId());
    }

    @Test
    public void checkNameIsNotBlankTest(){
        assertNotNull(affiliatedStudent.getNome());
    }
    
    @Test
    public void checkRegistroCbjIsNotBlankTest(){
        assertNotNull(affiliatedStudent.getRegistroCbj());
    }    

    @Test
    public void checkBirthdateIsNotBlankTest(){
        assertNotNull(affiliatedStudent.getDataNascimento());
    }

    @Test
    public void checkRegistrationDateIsNotBlankTest(){
        assertNotNull(affiliatedStudent.getDataCadastro());
    }  

    @Test
    public void checkTelephone1IsNotBlankTest(){
        assertNotNull(affiliatedStudent.getTelefone1());
    }     

    @Test
    public void checkTelephone2IsNotBlankTest(){
        assertNotNull(affiliatedStudent.getTelefone2());
    }     

    @Test
    public void checkEmailIsNotBlankTest(){
        assertNotNull(affiliatedStudent.getEmail());
    }

    @Test
    public void checkCPFIsNotBlankTest(){
        assertNotNull(affiliatedStudent.getCpf());
    }

    @Test
    public void checkCommentsIsNotBlankTest(){
        assertNotNull(affiliatedStudent.getObservacoes());
    }     

    @Test
    public void checkRgIsNotBlankTest(){
        assertNotNull(affiliatedStudent.getRg());
    }       
 
    @Test
    public void checkAddressIsNotBlankTest(){
        assertNotNull(affiliatedStudent.getEndereco());
    }     

    @Test
    public void checkBeltsIsNotBlankTest(){
        assertNotNull(affiliatedStudent.getFaixas());
    }

    @Test
    public void checkHashCodeIsNotBlankTest(){
        assertNotNull(affiliatedStudent.hashCode());
    }    

    @Test 
    public void affiliatedCopyPropertiesTest(){
        affiliatedStudent.copyProperties(affiliatedStudent);
    }

    @Test
    public void affiliatedEqualsTest(){

        Filiado affiliatedOther = new Filiado();
        affiliatedOther.setId(456465645L);
        affiliatedOther.setNome("Aluno Equals Teste");

        assertEquals(false,affiliatedStudent.equals(affiliatedOther));
    }

}
