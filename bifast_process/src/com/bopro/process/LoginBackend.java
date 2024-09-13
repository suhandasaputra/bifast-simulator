/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bopro.process;

import com.bopro.database.BackendDBProcess;
import com.bo.entity.Tempmsg;
import com.bo.parameter.FieldParameter;
import com.bo.parameter.FieldParameterMatapos;
import java.util.HashMap;
import org.apache.log4j.Logger;
import com.bopro.model.merchant;

/**
 *
 * @author suhanda
 */
public class LoginBackend {

    private static Logger log = Logger.getLogger(LoginBackend.class);

    public HashMap process(HashMap input) {
        BackendDBProcess dp = new BackendDBProcess();
        HashMap status = dp.validate(input.get(FieldParameterMatapos.user_id).toString(), input.get(FieldParameterMatapos.password).toString());
        return status;
    }
}
