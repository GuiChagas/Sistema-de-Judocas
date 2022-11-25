package org.fpij.jitakyoei.model.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.fpij.jitakyoei.model.beans.Endereco;
import org.fpij.jitakyoei.model.beans.Entidade;
import org.fpij.jitakyoei.util.DatabaseManager;
import org.junit.BeforeClass;

import org.junit.Test;

public class EntidadeDaoTest {
    private static DAO<Entidade> entidadeDao;
    private static Entidade entity;
    private static Endereco address;
    
    @BeforeClass
    public static void setUp() {
        DatabaseManager.setEnviroment(DatabaseManager.TEST);

        address = new Endereco();
        address.setBairro("Dirceu");
        address.setCep("64078-213");
        address.setCidade("Teresina");
        address.setEstado("PI");
        address.setRua("Rua Des. Berilo Mota");
        address.setNumero("100");

        entity = new Entidade();
        entity.setEndereco(address);
        entity.setNome("Academia 1");
        entity.setTelefone1("(086)1234-5432");

        entidadeDao = new DAOImpl<Entidade>(Entidade.class);
    }

    public static void clearDatabase() {
        List<Entidade> all = entidadeDao.list();
        for (Entidade each : all) {
            entidadeDao.delete(each);
        }
        assertEquals(0, entidadeDao.list().size());
    }

	@Test
	public void  saveEntidadeDaoTest() throws Exception{
		clearDatabase();
		
		entidadeDao.save(entity);
	        
        assertEquals("Dirceu", entidadeDao.get(entity).getEndereco().getBairro());
	    assertEquals("100", entidadeDao.get(entity).getEndereco().getNumero());
	}

    @Test
    public void updateEntidadeDaoTest() throws Exception {
        clearDatabase();
        assertEquals(0, entidadeDao.list().size());

        entidadeDao.save(entity);

        Entidade e1 = entidadeDao.get(entity);
        e1.setNome("TesteUpdate");
        entidadeDao.save(e1);

        Entidade e2 = entidadeDao.get(e1);
        assertEquals("TesteUpdate", e2.getNome());
        assertEquals(1, entidadeDao.list().size());
    }

    
	@Test
	public void listEntidadeDaoTest(){
        clearDatabase();

		int qtd = entidadeDao.list().size();

		entidadeDao.save(entity);
		assertEquals(qtd+1, entidadeDao.list().size());
		
		Entidade entidade2 = new Entidade();
		entidade2.setEndereco(address);

		entidadeDao.save(entidade2);
		assertEquals(qtd+2, entidadeDao.list().size());

		Entidade entidade3 = new Entidade();
		entidade3.setEndereco(address);
		
		entidadeDao.save(entidade3);
		assertEquals(qtd+3, entidadeDao.list().size());
		
		Entidade entidade4 = new Entidade();
		entidade4.setEndereco(address);

		entidadeDao.save(entidade4);
		assertEquals(qtd+4, entidadeDao.list().size());
		
		clearDatabase();
		assertEquals(0, entidadeDao.list().size());
		
		entidadeDao.save(entity);
		assertEquals(1, entidadeDao.list().size());
	}
    

    @Test
    public void searchEntidadeDaoTest() throws Exception {
        clearDatabase();

        Entidade entityNew = new Entidade();
        entityNew.setNome("Academia 1");

        entidadeDao.save(entityNew);

        List<Entidade> result = entidadeDao.search(entityNew);
        assertEquals(1, result.size());
        assertEquals("Academia 1", result.get(0).getNome());

        clearDatabase();
        assertEquals(0, entidadeDao.search(entityNew).size());
    }
}
