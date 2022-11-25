package org.fpij.jitakyoei.model.beans;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.BeforeClass;
import org.junit.Test;

public class EnderecoTest {
    private static Endereco address;
    Endereco e = new Endereco();
    
    @BeforeClass
    public static void setUp() {
        address = new Endereco();
        address.setBairro("Assunção");
        address.setNumero("140");
        address.setCep("12345-678");
        address.setCidade("São Bernardo do Campo");
        address.setEstado("SP");
        address.setRua("Av Castelo Branco");
    }
    
    @Test
    public void checkDistrictIsBlankTest(){
        assertNotNull(address.getBairro());
    }
    
    @Test
    public void checkStreetIsBlankTest(){
        assertNotNull(address.getRua());
    }
    
    @Test
    public void checkStateIsBlankTest(){
        assertNotNull(address.getEstado());
    }
    
    @Test
    public void checkCEPIsBlankTest(){
        assertNotNull(address.getCep());
    }  

    @Test
    public void checkNumberIsBlankTest(){
        assertNotNull(address.getNumero());
    }  

    @Test
    public void checkCityIsBlankTest(){
        assertNotNull(address.getCidade());
    }      

    @Test
    public void addressToStringTest(){
        String stringTestEqual =  "\nRua: "+address.getRua()+
        "\nNumero: "+address.getNumero()+
        "\nBairro: "+address.getBairro()+
        "\nCidade: "+address.getCidade()+
        "\nEstado: "+address.getEstado()+
        "\nCep: "+address.getCep();

        assertEquals(stringTestEqual, address.toString());
    }
}


