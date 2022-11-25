package org.fpij.jitakyoei.model.dao;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;

import org.fpij.jitakyoei.model.beans.Aluno;
import org.fpij.jitakyoei.model.beans.Endereco;
import org.fpij.jitakyoei.model.beans.Entidade;
import org.fpij.jitakyoei.model.beans.Filiado;
import org.fpij.jitakyoei.model.beans.Professor;
import org.fpij.jitakyoei.model.beans.Rg;
import org.fpij.jitakyoei.model.validator.AlunoValidator;
import org.fpij.jitakyoei.util.DatabaseManager;
import org.junit.BeforeClass;
import org.junit.Test;

public class AlunoDaoTest {
	
	private static DAO<Aluno> alunoDao;
	private static Aluno student;
	private static Filiado affiliatedStudent;

	@BeforeClass
	public static void setUp(){
		DatabaseManager.setEnviroment(DatabaseManager.TEST);
		Endereco address = new Endereco();
		address.setBairro("Dirceu");
		address.setCep("64078-213");
		address.setCidade("Teresina");
		address.setEstado("PI");
		address.setRua("Rua Des. Berilo Mota");
		address.setNumero("200");

		affiliatedStudent = new Filiado();
        affiliatedStudent.setId(12345678910L);
        affiliatedStudent.setNome("Aécio");
        affiliatedStudent.setTelefone1("91457-4514");
        affiliatedStudent.setEmail("teste@teste.com");
        affiliatedStudent.setTelefone2("99595-5616");
        affiliatedStudent.setRegistroCbj("123456");
        affiliatedStudent.setCpf("036.464.453-27");
        affiliatedStudent.setDataNascimento(new Date());
        affiliatedStudent.setDataCadastro(new Date());
        affiliatedStudent.setRg(new Rg("012345-X", "SP"));
        affiliatedStudent.setObservacoes("Observacao teste");
		affiliatedStudent.setEndereco(address);

		Filiado affiliatedTeacher = new Filiado();
		affiliatedTeacher.setNome("Professor");
		affiliatedTeacher.setCpf("036.464.453-27");
		affiliatedTeacher.setDataNascimento(new Date());
		affiliatedTeacher.setDataCadastro(new Date());
		affiliatedTeacher.setId(3332L);
		affiliatedTeacher.setEndereco(address);

		Professor teacher = new Professor();
		teacher.setFiliado(affiliatedTeacher);

		Entidade entity = new Entidade();
		entity.setEndereco(address);
		entity.setNome("Academia 1");
		entity.setTelefone1("(086)1234-5432");

		student = new Aluno();
		student.setFiliado(affiliatedStudent);
		student.setProfessor(teacher);
		student.setEntidade(entity);
		
		alunoDao = new DAOImpl<Aluno>(Aluno.class);
	
	}

	public static void clearDatabase(){
		List<Aluno> all = alunoDao.list();
		for (Aluno each : all) {
			alunoDao.delete(each);
		}
		assertEquals(0, alunoDao.list().size());
	}
	
	@Test
	public void  DAOCoverageTest() throws Exception{
		DAO<Aluno> daoCovarageTestBol = new DAOImpl<Aluno>(Aluno.class, true);
		DAO<Aluno> daoCovarageTestValBol = new DAOImpl<Aluno>(Aluno.class, new AlunoValidator(), true);
	
		daoCovarageTestBol.save(student);
		daoCovarageTestValBol.save(student);

	}

	@Test
	public void  saveStudentDaoTest() throws Exception{
		clearDatabase();
		
		alunoDao.save(student);
		assertEquals("036.464.453-27", alunoDao.get(student).getFiliado().getCpf());
		assertEquals("Professor", alunoDao.get(student).getProfessor().getFiliado().getNome());
		assertEquals("Dirceu", alunoDao.get(student).getProfessor().getFiliado().getEndereco().getBairro());
	}
	
	@Test
	public void updateStudentDaoTest() throws Exception{
		clearDatabase();
		assertEquals(0, alunoDao.list().size());

		alunoDao.save(student);
		assertEquals(1, alunoDao.list().size());
		assertEquals("Aécio", student.getFiliado().getNome());

		Aluno a1 = alunoDao.get(student);
		a1.getFiliado().setNome("TesteUpdate");
		alunoDao.save(a1);

		Aluno a2 = alunoDao.get(a1);
		assertEquals("TesteUpdate", a2.getFiliado().getNome());
		assertEquals(1, alunoDao.list().size());
	}
	
	@Test
	public void listStudentDaoTest(){
		clearDatabase();

		int qtd = alunoDao.list().size();

		alunoDao.save(student);
		assertEquals(qtd+1, alunoDao.list().size());
		
		Aluno aluno2 = new Aluno();
		aluno2.setFiliado(affiliatedStudent);

		alunoDao.save(aluno2);
		assertEquals(qtd+2, alunoDao.list().size());

		Aluno aluno3 = new Aluno();
		aluno3.setFiliado(affiliatedStudent);
		
		alunoDao.save(aluno3);
		assertEquals(qtd+3, alunoDao.list().size());
		
		Aluno aluno4 = new Aluno();
		aluno4.setFiliado(affiliatedStudent);

		alunoDao.save(aluno4);
		assertEquals(qtd+4, alunoDao.list().size());
		
		clearDatabase();
		assertEquals(0, alunoDao.list().size());
		
		alunoDao.save(student);
		assertEquals(1, alunoDao.list().size());
	}
	
	@Test
	public void searchStudentDaoTest() throws Exception{
		clearDatabase();
		alunoDao.save(student);
		
		Filiado f = new Filiado();
		f.setNome("Aécio");
		Aluno a = new Aluno();
		a.setFiliado(f);
		
		List<Aluno> result = alunoDao.search(a);
		assertEquals(1, result.size());
		assertEquals("036.464.453-27", result.get(0).getFiliado().getCpf());
		
		clearDatabase();
		assertEquals(0, alunoDao.search(a).size());
	}
}
