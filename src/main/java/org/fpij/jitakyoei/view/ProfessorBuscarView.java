package org.fpij.jitakyoei.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

import org.fpij.jitakyoei.facade.AppFacade;
import org.fpij.jitakyoei.model.beans.Filiado;
import org.fpij.jitakyoei.view.forms.CamposBuscaForm;

import org.fpij.jitakyoei.model.beans.Professor;
import org.fpij.jitakyoei.view.gui.ProfessorBuscarPanel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
public class ProfessorBuscarView implements ViewComponent {

	private ProfessorBuscarPanel gui;
	private AppFacade facade;
	private CamposBuscaForm campoBusca;
	private DefaultTableModel professorTableModel;
	private List<Professor> professorList;
	private Professor professor;
	private Professor selectedProfessor;
	public int MODO;


	public static int ALTERACAO = 1;
	public static int BUSCA = 2;

	public ProfessorBuscarView(int MODO) {
		this();
		if(MODO == ALTERACAO){
			gui.getProfessores().addMouseListener(new TableProfessoresMouseClickedHandler());
		}
	}

	public ProfessorBuscarView() {
		gui = new ProfessorBuscarPanel();
		gui.registerView(this);
		gui.getBuscar().addActionListener(new BuscarActionHandler());
		campoBusca = new CamposBuscaForm(gui.getBuscaCamposPanel());
		professorTableModel = (DefaultTableModel) gui.getProfessorTable().getModel();
	}

	@Override
	public JPanel getGui() {
		return gui;
	}

	@Override
	public void registerFacade(AppFacade fac) {
		this.facade = fac;
		buscar();
	}

	public void buscar() {
		Filiado filiado = new Filiado();

		if(campoBusca.getNome()!=null && !campoBusca.getNome().trim().equals("")){
			filiado.setNome(campoBusca.getNome());
		}

		if((campoBusca.getRegistroFpij()!=null && !campoBusca.getRegistroFpij().trim().equals(""))){
			try{
				filiado.setId(Long.parseLong(campoBusca.getRegistroFpij()));
			}catch (Exception e) {
				JOptionPane.showMessageDialog(gui,"Nº de Registro inválido! No resgistro só pode haver números.");
				return;
			}
		}
		Professor professor = new Professor();
		professor.setFiliado(filiado);
		professorList = facade.searchProfessor(professor);
		setProfessorList(professorList);
	}

	public void setProfessorList(List<Professor> professorList) {
		Object[][] dados = new Object[professorList.size()][4];
		for (int i = 0; i < professorList.size(); i++) {
			dados[i][0] = professorList.get(i).getFiliado().getId();
			dados[i][1] = professorList.get(i).getFiliado().getNome();
			dados[i][2] = professorList.get(i).getFiliado().getTelefone1();
			dados[i][3] = professorList.get(i).getFiliado().getTelefone2();
		}
		professorTableModel.setDataVector(dados, new String[]{
				"Registro FPIJ", "Nome", "Telefone 1", "Telefone 2" });
	}

	public class BuscarActionHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			buscar();
		}
	}

	public List<Professor> getprofessorList() {
		return professorList;
	}


	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public Professor getSelectedProfessor() {
		return selectedProfessor;
	}

	public void setselectedProfessor(Professor selectedProfessor) {
		this.selectedProfessor = selectedProfessor;
	}

	public class TableProfessoresMouseClickedHandler implements MouseListener {
		@Override
		public void mouseClicked(MouseEvent arg0) {
			selectedProfessor = professorList.get(gui.getProfessores().getSelectedRow());
			gui.getParent().getParent().getParent().getParent().setVisible(false);
		}
		@Override
		public void mouseEntered(MouseEvent arg0) {}
		@Override
		public void mouseExited(MouseEvent arg0) {}
		@Override
		public void mousePressed(MouseEvent arg0) {}
		@Override
		public void mouseReleased(MouseEvent arg0) {}
	}

}
