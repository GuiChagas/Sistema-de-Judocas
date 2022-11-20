package org.fpij.jitakyoei.model.beans;

import org.fpij.jitakyoei.util.CorFaixa;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Date;

public class FaixaTest {
    private static Faixa judoBelt;

    @BeforeClass
    public static void setUp(){
        CorFaixa beltColor = CorFaixa.BRANCA; 

        judoBelt = new Faixa(null, null);
        judoBelt.setCor(beltColor);
        judoBelt.setDataEntrega(new Date());

    }
    
    @Test
    public void checkBeltColorIsNotBlank(){
        assertNotNull(judoBelt.getCor());
    }

    @Test
    public void checkDeliveryDateIsNotBlank(){
        assertNotNull(judoBelt.getDataEntrega());
    }

    @Test
    public void checkStringMethod(){
        String stringToTest =  judoBelt.getCor() + " - "+ judoBelt.getDataEntrega();
        assertEquals(judoBelt.toString(), stringToTest);
    }
}
