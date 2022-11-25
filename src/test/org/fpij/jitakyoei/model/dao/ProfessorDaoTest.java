package org.fpij.jitakyoei.model.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.fpij.jitakyoei.model.beans.Endereco;
import org.fpij.jitakyoei.model.beans.Entidade;
import org.fpij.jitakyoei.model.beans.Filiado;
import org.fpij.jitakyoei.model.beans.Professor;
import org.fpij.jitakyoei.util.DatabaseManager;
import org.junit.BeforeClass;
import org.junit.Test;

public class ProfessorDaoTest {
    private static DAO<Professor> professorDao;
	static List<Entidade> entityLst = new ArrayList<Entidade>();
	private static Filiado affiliated;
	private static Professor professor;
	
	@BeforeClass
	public static void setUp(){
		DatabaseManager.setEnviroment(DatabaseManager.TEST);
		affiliated = new Filiado();
		affiliated.setNome("Aécio");
		affiliated.setCpf("036.464.453-27");
		affiliated.setDataNascimento(new Date());
		affiliated.setDataCadastro(new Date());
		affiliated.setId(1332L);

		Endereco address = new Endereco();
		address.setBairro("Dirceu");
		address.setCep("64078-213");
		address.setCidade("Teresina");
		address.setEstado("PI");
		address.setRua("Rua Des. Berilo Mota");

		Filiado affiliatedTeacher = new Filiado();
		affiliatedTeacher.setNome("Professor");
		affiliatedTeacher.setCpf("036.464.453-27");
		affiliatedTeacher.setDataNascimento(new Date());
		affiliatedTeacher.setDataCadastro(new Date());
		affiliatedTeacher.setId(3332L);
		affiliatedTeacher.setEndereco(address);

        Entidade entidade = new Entidade();
		entidade.setEndereco(address);
		entidade.setNome("Academia 1");
		entidade.setTelefone1("(086)1234-5432");
		entityLst.add(entidade);
        
		professor = new Professor();
		professor.setFiliado(affiliatedTeacher);
		professor.setEntidades(entityLst);
		
		professorDao = new DAOImpl<Professor>(Professor.class);
	}

	public static void clearDatabase(){
		List<Professor> all = professorDao.list();
		for (Professor each : all) {
			professorDao.delete(each);
		}
		assertEquals(0, professorDao.list().size());
	}
	
	
	@Test
	public void  saveProfessorDaoTest() throws Exception{
		clearDatabase();
		
		professorDao.save(professor);
		        
        assertEquals("036.464.453-27", professorDao.get(professor).getFiliado().getCpf());
		assertEquals("Academia 1", professorDao.get(professor).getEntidades().get(0).getNome());
		assertEquals("(086)1234-5432", professorDao.get(professor).getEntidades().get(0).getTelefone1());
	}
	
	@Test
	public void updateProfessorDaoTest() throws Exception{
		clearDatabase();
		assertEquals(0, professorDao.list().size());
		
		professorDao.save(professor);
		assertEquals(1, professorDao.list().size());
		assertEquals("Professor", professor.getFiliado().getNome());
		
		Professor p1 = professorDao.get(professor);
		p1.getFiliado().setNome("TesteUpdate");
		professorDao.save(p1);
		
		Professor p2 = professorDao.get(p1);
		assertEquals("TesteUpdate", p2.getFiliado().getNome());
		assertEquals(1, professorDao.list().size());
	}
	
	@Test
	public void listProfessorDaoTest(){
        clearDatabase();
		int qtd = professorDao.list().size();

		professorDao.save(professor);
		assertEquals(qtd+1, professorDao.list().size());
		
		Professor professor2 = new Professor();
		professor2.setFiliado(affiliated);

		professorDao.save(professor2);
		assertEquals(qtd+2, professorDao.list().size());

		Professor professor3 = new Professor();
		professor3.setFiliado(affiliated);
		
		professorDao.save(professor3);
		assertEquals(qtd+3, professorDao.list().size());
		
		Professor professor4 = new Professor();
		professor4.setFiliado(affiliated);

		professorDao.save(professor4);
		assertEquals(qtd+4, professorDao.list().size());
		
		clearDatabase();
		assertEquals(0, professorDao.list().size());
		
		professorDao.save(professor);
		assertEquals(1, professorDao.list().size());
	}
	
	@Test
	public void searchProfessorDaoTest() throws Exception{
		clearDatabase();
		professorDao.save(professor);
		
		Filiado f = new Filiado();
		f.setNome("Professor");
        f.setCpf("036.464.453-27");
		Professor p = new Professor();
		professor.setFiliado(f);
		
		List<Professor> result = professorDao.search(p);
		assertEquals(1, result.size());
		assertEquals("036.464.453-27", result.get(0).getFiliado().getCpf());
		
		clearDatabase();
		assertEquals(0, professorDao.search(p).size());
	}
}
