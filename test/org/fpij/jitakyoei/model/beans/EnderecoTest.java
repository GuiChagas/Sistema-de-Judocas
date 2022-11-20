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
    public void checkDistrictIsBlank(){
        assertNotNull(address.getBairro());
    }
    
    @Test
    public void checkStreetIsBlank(){
        assertNotNull(address.getRua());
    }
    
    @Test
    public void checkStateIsBlank(){
        assertNotNull(address.getEstado());
    }
    
    @Test
    public void checkCEPIsBlank(){
        assertNotNull(address.getCep());
    }  

    @Test
    public void checkNumberIsBlank(){
        assertNotNull(address.getNumero());
    }  

    @Test
    public void checkCityIsBlank(){
        assertNotNull(address.getCidade());
    }      

    @Test
    public void toStringTest(){
        String stringTestEqual =  "\nRua: "+address.getRua()+
        "\nNumero: "+address.getNumero()+
        "\nBairro: "+address.getBairro()+
        "\nCidade: "+address.getCidade()+
        "\nEstado: "+address.getEstado()+
        "\nCep: "+address.getCep();

        assertEquals(stringTestEqual, address.toString());
    }      


   

}


