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
    private static Entidade entidade;
    private static Endereco endereco;
    
    @BeforeClass
    public static void setUp() {
        DatabaseManager.setEnviroment(DatabaseManager.TEST);

        endereco = new Endereco();
        endereco.setBairro("Dirceu");
        endereco.setCep("64078-213");
        endereco.setCidade("Teresina");
        endereco.setEstado("PI");
        endereco.setRua("Rua Des. Berilo Mota");
        endereco.setNumero("100");

        entidade = new Entidade();
		entidade.setEndereco(endereco);
		entidade.setNome("Academia 1");
		entidade.setTelefone1("(086)1234-5432");

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
	public void  testSalvarEntidadeComAssociassoes() throws Exception{
		clearDatabase();
		
		entidadeDao.save(entidade);
	        
        assertEquals("Dirceu", entidadeDao.get(entidade).getEndereco().getBairro());
	    assertEquals("100", entidadeDao.get(entidade).getEndereco().getNumero());
	}

    @Test
    public void updateEntidade() throws Exception {
        clearDatabase();
        assertEquals(0, entidadeDao.list().size());

        entidadeDao.save(entidade);

        Entidade e1 = entidadeDao.get(entidade);
        e1.setNome("TesteUpdate");
        entidadeDao.save(e1);

        Entidade e2 = entidadeDao.get(e1);
        assertEquals("TesteUpdate", e2.getNome());
        assertEquals(1, entidadeDao.list().size());
    }

    
	@Test
	public void testListarEAdicionarEntidades(){
        clearDatabase();

		int qtd = entidadeDao.list().size();

		entidadeDao.save(entidade);
		assertEquals(qtd+1, entidadeDao.list().size());
		
		Entidade entidade2 = new Entidade();
		entidade2.setEndereco(endereco);

		entidadeDao.save(entidade2);
		assertEquals(qtd+2, entidadeDao.list().size());

		Entidade entidade3 = new Entidade();
		entidade3.setEndereco(endereco);
		
		entidadeDao.save(entidade3);
		assertEquals(qtd+3, entidadeDao.list().size());
		
		Entidade entidade4 = new Entidade();
		entidade4.setEndereco(endereco);

		entidadeDao.save(entidade4);
		assertEquals(qtd+4, entidadeDao.list().size());
		
		clearDatabase();
		assertEquals(0, entidadeDao.list().size());
		
		entidadeDao.save(entidade);
		assertEquals(1, entidadeDao.list().size());
	}
    

    @Test
    public void testSearchEntidade() throws Exception {
        clearDatabase();

        Entidade a = new Entidade();
        a.setNome("Academia 1");

        entidadeDao.save(a);

        List<Entidade> result = entidadeDao.search(a);
        assertEquals(1, result.size());
        assertEquals("Academia 1", result.get(0).getNome());

        clearDatabase();
        assertEquals(0, entidadeDao.search(a).size());
    }
}
