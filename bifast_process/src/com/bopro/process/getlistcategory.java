/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bopro.process;

import com.bopro.database.BackendDBProcess;
import com.bo.parameter.FieldParameter;
import com.bo.parameter.FieldParameterMatapos;
import com.bopro.model.category;
import com.bopro.model.item;
import com.bopro.model.itemdeleted;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import java.util.ArrayList;
import java.util.HashMap;
import org.apache.log4j.Logger;

/**
 *
 * @author suhanda
 */
public class getlistcategory {

    private static Logger log = Logger.getLogger(getlistcategory.class);

    public HashMap process(HashMap input) {
        BackendDBProcess dp = new BackendDBProcess();
        Gson gson = new Gson();
        ArrayList<category> ListMerchant = dp.getlistcategory(input.get(FieldParameterMatapos.brand_id).toString(),input.get(FieldParameterMatapos.outlet_id).toString(),input.get(FieldParameterMatapos.userlevel).toString());
        ArrayList<item> ListItem = dp.getlistitem(input.get(FieldParameterMatapos.brand_id).toString(),input.get(FieldParameterMatapos.outlet_id).toString(),input.get(FieldParameterMatapos.userlevel).toString());
        ArrayList<itemdeleted> ListItemDeleted = dp.getlistitemdeleted(input.get(FieldParameterMatapos.brand_id).toString(),input.get(FieldParameterMatapos.outlet_id).toString(),input.get(FieldParameterMatapos.userlevel).toString());
        try {
            if (ListMerchant != null) {
                JsonElement element = gson.toJsonTree(ListMerchant);
                JsonElement element1 = gson.toJsonTree(ListItem);
                JsonElement element2 = gson.toJsonTree(ListItemDeleted);

                input.put(FieldParameterMatapos.listcategory, element);
                input.put(FieldParameterMatapos.listitem, element1);
                input.put(FieldParameterMatapos.listitemdeleted, element2);
                input.put(FieldParameter.resp_code, "0000");
                input.put(FieldParameter.resp_desc, "Success");
                return input;
            } else {
                input.put(FieldParameter.resp_code, "0001");
                input.put(FieldParameter.resp_code, "Failed");
                return input;
            }
        } catch (Exception e) {
            input.put(FieldParameter.resp_code, "0099");
            input.put(FieldParameter.resp_code, "unknown error");
            return input;
        } finally {
            dp = null;
            gson = null;
            ListMerchant = null;
            ListItem = null;
            ListItemDeleted = null;
        }
    }
}
