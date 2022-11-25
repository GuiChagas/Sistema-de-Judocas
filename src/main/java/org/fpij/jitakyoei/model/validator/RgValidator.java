package org.fpij.jitakyoei.model.validator;

import org.fpij.jitakyoei.model.beans.Rg;

public class RgValidator {
    public boolean validate(Rg rg) {
        if(rg.getNumero().isEmpty() ||
            rg.getOrgaoExpedidor().isEmpty()){
            return true;
        }
        return false;
    }
}
