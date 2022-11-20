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
    public void checkId(){
        assertNotNull(affiliatedStudent.getId());
    }

    @Test
    public void checkName(){
        assertNotNull(affiliatedStudent.getNome());
    }
    
    @Test
    public void checkRegistroCbj(){
        assertNotNull(affiliatedStudent.getRegistroCbj());
    }    

    @Test
    public void checkBirthdate(){
        assertNotNull(affiliatedStudent.getDataNascimento());
    }

    @Test
    public void checkRegistrationDate(){
        assertNotNull(affiliatedStudent.getDataCadastro());
    }  

    @Test
    public void checkPhone1(){
        assertNotNull(affiliatedStudent.getTelefone1());
    }     

    @Test
    public void checkPhone2(){
        assertNotNull(affiliatedStudent.getTelefone2());
    }     

    @Test
    public void checkEmail(){
        assertNotNull(affiliatedStudent.getEmail());
    }

    @Test
    public void checkCPF(){
        assertNotNull(affiliatedStudent.getCpf());
    }

    @Test
    public void checkComments(){
        assertNotNull(affiliatedStudent.getObservacoes());
    }     

    @Test
    public void checkRg(){
        assertNotNull(affiliatedStudent.getRg());
    }       
 
    @Test
    public void checkAddress(){
        assertNotNull(affiliatedStudent.getEndereco());
    }     

    @Test
    public void checkBelts(){
        assertNotNull(affiliatedStudent.getFaixas());
    }

    @Test
    public void checkHashCode(){
        assertNotNull(affiliatedStudent.hashCode());
    }    

    @Test 
    public void copyPropertiesTest(){
        affiliatedStudent.copyProperties(affiliatedStudent);
    }

    @Test
    public void checkIsEqualsName(){

        Filiado affiliatedOther = new Filiado();
        affiliatedOther.setId(456465645L);
        affiliatedOther.setNome("Aluno Equals Teste");

        assertEquals(false,affiliatedStudent.equals(affiliatedOther));
    }

}
