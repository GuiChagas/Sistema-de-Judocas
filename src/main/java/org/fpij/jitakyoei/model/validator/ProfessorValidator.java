package org.fpij.jitakyoei.model.validator;

import org.fpij.jitakyoei.model.beans.Professor;


public class ProfessorValidator {

    private RgValidator rgValidator = new RgValidator();
    private EnderecoValidator addressValidator = new EnderecoValidator();
    private EntidadeValidator entityValidator = new EntidadeValidator();

    public boolean validate(Professor teacher) {
        Object belt = teacher.getFiliado().getFaixas();

        System.out.println(teacher.getFiliado().getCpf().isEmpty());
        System.out.println(teacher.getFiliado().getDataNascimento() == null);
        System.out.println(teacher.getFiliado().getEmail().isEmpty());
        System.out.println(teacher.getFiliado().getNome().isEmpty());
        System.out.println(teacher.getFiliado().getRegistroCbj().isEmpty());
        System.out.println((teacher.getFiliado().getTelefone1().isEmpty() || teacher.getFiliado().getTelefone2().isEmpty()));
        System.out.println(belt);
        System.out.println(rgValidator.validate(teacher.getFiliado().getRg()));
        System.out.println(addressValidator.validate(teacher.getFiliado().getEndereco()));
        System.out.println(entityValidator.validateList(teacher.getEntidades()));

        if(teacher.getFiliado().getCpf().isEmpty() ||
                teacher.getFiliado().getDataNascimento() == null ||
                teacher.getFiliado().getEmail().isEmpty() ||
                teacher.getFiliado().getNome().isEmpty() ||
                teacher.getFiliado().getRegistroCbj().isEmpty() ||
                (teacher.getFiliado().getTelefone1().isEmpty() || teacher.getFiliado().getTelefone2().isEmpty()) ||
                rgValidator.validate(teacher.getFiliado().getRg()) ||
                addressValidator.validate(teacher.getFiliado().getEndereco()) ||
                entityValidator.validateList(teacher.getEntidades()) ||
                belt == null)
        {
            return false;
        }
        return true;
    }



}
