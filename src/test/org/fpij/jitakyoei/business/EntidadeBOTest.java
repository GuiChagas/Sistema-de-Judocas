package org.fpij.jitakyoei.business;

import java.util.List;

import org.fpij.jitakyoei.business.EntidadeBOImpl;
import org.fpij.jitakyoei.facade.AppFacade;
import org.fpij.jitakyoei.model.beans.Entidade;
import org.fpij.jitakyoei.model.beans.ProfessorEntidade;
import org.fpij.jitakyoei.view.AppView;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EntidadeBOTest {
    private static EntidadeBOImpl entidadeTest;
    private static Entidade entity;

    public static AppView appView(){
        return new AppView() {
            @Override
            public void handleModelChange(Object obj) {}

            @Override
            public void displayException(Exception e) {}

            @Override
            public void registerFacade(AppFacade facade) {}
        };
    }

    @BeforeClass
	public static void setUp(){
        entidadeTest = new EntidadeBOImpl(appView());
        entity = new Entidade();
        entity.setNome("Entidade Teste");
    }

    @Test
    public void createEntidadeTest() throws Exception {
        entidadeTest.createEntidade(entity);

        Entidade wantedEntity = entidadeTest.searchEntidade(entity).get(0);

        //colocar um equals aqui
        assertEquals(entity.getNome(), wantedEntity.getNome());
    }

    @Test
    public void updateEntidadeTest() throws Exception {
      
        Entidade wantedEntity = entidadeTest.listAll().get(0);

        wantedEntity.setNome("Entidade Update Teste");
        entidadeTest.updateEntidade(wantedEntity);

        List<Entidade> retornoLista2 = entidadeTest.listAll();
        assertEquals("Entidade Update Teste", retornoLista2.get(0).getNome());
        assertNotEquals("Entidade Teste Update invalido", retornoLista2.get(0).getNome());
    }



    /*@Test
    public void createEntityExceptionTest() throws Exception {
        Entidade entityNull = new Entidade();

        Exception exception = assertThrows(Exception.class, () -> {
            entidadeTest.createEntidade(entityNull);
        });

        String actualMessage = exception.getMessage();
    }

    @Test
    public void updateEntityExceptionTest() throws Exception {
        Entidade entityNull = new Entidade();

        Exception exception = assertThrows(Exception.class, () -> {
            entidadeTest.updateEntidade(entityNull);
        });

        String actualMessage = exception.getMessage();
    }

    @Test
    public void searchEntityExceptionTest() throws Exception {
        Entidade entityNull = new Entidade();

        Exception exception = assertThrows(Exception.class, () -> {
            entidadeTest.searchEntidade(entityNull);
        });

        String actualMessage = exception.getMessage();
    }

    @Test
    public void listAllProfessorExceptionTest() throws Exception {
        EntidadeBOImpl entidadeTestNew = new EntidadeBOImpl(appView());

        Exception exception = assertThrows(Exception.class, () -> {
            entidadeTestNew.listAll();
        });

        String actualMessage = exception.getMessage();
        
    }*/
}
