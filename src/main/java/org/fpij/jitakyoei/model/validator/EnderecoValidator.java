package org.fpij.jitakyoei.model.validator;

import org.fpij.jitakyoei.model.beans.Endereco;

public class EnderecoValidator {
    public boolean validate(Endereco address) {

        if(address.getBairro().isEmpty() ||
            address.getCep().isEmpty() ||
            address.getCidade().isEmpty() ||
            address.getEstado().isEmpty() ||
            address.getNumero().isEmpty() ||
            address.getRua().isEmpty())
        {
            return true;
        }
        return false;
    }

}
