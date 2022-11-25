package org.fpij.jitakyoei.model.validator;

import static org.junit.Assert.assertEquals;

import org.fpij.jitakyoei.model.beans.Endereco;
import org.fpij.jitakyoei.model.beans.Entidade;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class EntidadeValidatorTest {
    private static Entidade entity;
    private static Endereco address;
    static List<Entidade> entityLst = new ArrayList<Entidade>();

    @BeforeClass
    public static void setUp() {
        address = new Endereco();
        address.setBairro("Assunção");
        address.setCep("12345-678");
        address.setCidade("São Bernardo do Campo");
        address.setEstado("SP");
        address.setRua("Av Castelo Branco");
        address.setNumero("");

        entity = new Entidade();
        entity.setCnpj("1641646541654");
        entity.setNome("Entidade Teste");
        entity.setTelefone1("91457-4514");
        entity.setTelefone2("99595-5616");
        entity.setEndereco(address);

        Entidade entity2 = new Entidade();
        entity2.setCnpj("");
        entity2.setNome("Entidade Teste2");
        entity2.setTelefone1("91457-4514");
        entity2.setTelefone2("99595-5616");
        entity2.setEndereco(address);

        entityLst.add(entity);
        entityLst.add(entity2);
    }


    @Test
    public void entityValidateTest(){
        EntidadeValidator validator = new EntidadeValidator();
        assertEquals(false, validator.validate(entity));

        entity.getEndereco().setNumero("200");
        assertEquals(true, validator.validate(entity));

    }

    @Test
    public void entityValidateListTest(){
        EntidadeValidator validator = new EntidadeValidator();

        assertEquals(false, validator.validateList(entityLst));
    }
}
