package org.fpij.jitakyoei.model.beans;


import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class RgTest {
    private static Rg rg;

    @BeforeClass
    public static void setUp(){
        rg = new Rg(null, null);
        rg.setNumero("1215451-X");
        rg.setOrgaoExpedidor("SP");
    }

    @Test
    public void checkIsEqualsNumber(){
        Rg rgOther = new Rg("121215-X", "MT");
        assertEquals(false,rg.equals(rgOther));
        assertEquals(true,rg.equals(rg));
    }

    @Test
    public void checkNumberIsNotBlank(){
        assertNotNull(rg.getNumero());
    }   

    @Test
    public void checkIssuingAgencyIsNotBlank(){
        assertNotNull(rg.getOrgaoExpedidor());
    }     

    @Test
    public void checkHashCode(){
        assertNotNull(rg.hashCode());
    }      
}
