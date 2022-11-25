package org.fpij.jitakyoei.model.validator;

import org.fpij.jitakyoei.model.beans.Aluno;

public class AlunoValidator implements Validator<Aluno>{

	private RgValidator rgValidator = new RgValidator();
	private EnderecoValidator addressValidator = new EnderecoValidator();
	public boolean validate(Aluno obj) {
		Object entity = obj.getEntidade();
		Object teacher = obj.getProfessor();


		if(obj.getFiliado().getCpf().isEmpty() ||
			obj.getFiliado().getDataNascimento() == null ||
			obj.getFiliado().getEmail().isEmpty() ||
			obj.getFiliado().getNome().isEmpty() ||
			obj.getFiliado().getRegistroCbj().isEmpty() ||
			(obj.getFiliado().getTelefone1().isEmpty() || obj.getFiliado().getTelefone2().isEmpty()) ||
			entity == null ||
			teacher == null ||
			obj.getFiliado().getFaixas().isEmpty() ||
			rgValidator.validate(obj.getFiliado().getRg()) ||
			addressValidator.validate(obj.getFiliado().getEndereco())
		)
		{
			return false;
		}

		return true;
	}
}