package org.fpij.jitakyoei.model.validator;

import org.fpij.jitakyoei.model.beans.Entidade;

import java.util.List;


public class EntidadeValidator {
    private EnderecoValidator addressValidator = new EnderecoValidator();

    public boolean validate(Entidade entity) {

        System.out.println(entity.getCnpj().isEmpty());
        System.out.println(entity.getNome().isEmpty());
        System.out.println(entity.getTelefone1().isEmpty());
        System.out.println(entity.getTelefone2().isEmpty());
        System.out.println(addressValidator.validate(entity.getEndereco()));

        if(entity.getCnpj().isEmpty() ||
           entity.getNome().isEmpty() ||
           (entity.getTelefone1().isEmpty() || entity.getTelefone2().isEmpty()) ||
           addressValidator.validate(entity.getEndereco()))
        {
            return false;
        }
        return true;
    }

    public boolean validateList(List<Entidade> entity) {
        for(Entidade e : entity){
            if(e.getCnpj().isEmpty() ||
               e.getNome().isEmpty() ||
               (e.getTelefone1().isEmpty() || e.getTelefone2().isEmpty()) ||
               addressValidator.validate(e.getEndereco()))
            {
                return false;
            }
        }
        return true;
    }


}
