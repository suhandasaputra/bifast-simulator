/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boserverpos.function;


//import com.mios.entity.Messagein;
//import com.mios.function.StringFunction;
//import com.iso20022.parameter.StaticParameterVerdana;
//import com.iso20022.function.ISO20022XMLFunction;
//import com.ppob.function.StringFunction;
//import com.ppob.parameter.FieldParameter;
import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 *
 * @author herrysuganda
 */
public class ISOS20022HEAD {

    public static String createHead() {
        HashMap rootNS = new LinkedHashMap();
        rootNS.put("ns", "urn:iso");
        rootNS.put("ns1", "urn:iso:std:iso:20022:tech:xsd:head.001.001.01");

        HashMap AppHdr = new LinkedHashMap();
        HashMap Othr = new LinkedHashMap();
        HashMap FinInstnId = new LinkedHashMap();
        HashMap FIId = new LinkedHashMap();
        Othr.put("ns", "ns1");
        Othr.put("Id", "INDOIDJA");
        FinInstnId.put("Othr", Othr);
        FinInstnId.put("ns", "ns1");
        FIId.put("FinInstnId", FinInstnId);
        FIId.put("ns", "ns1");
        HashMap Fr = new LinkedHashMap();
        Fr.put("ns", "ns1");
        Fr.put("FIId", FIId);

        Othr = new LinkedHashMap();
        FinInstnId = new LinkedHashMap();
        FIId = new LinkedHashMap();
        Othr.put("ns", "ns1");
        Othr.put("Id", "FASTIDJA");
        FinInstnId.put("Othr", Othr);
        FinInstnId.put("ns", "ns1");
        FIId.put("FinInstnId", FinInstnId);
        FIId.put("ns", "ns1");
        HashMap To = new LinkedHashMap();
        To.put("ns", "ns1");
        To.put("FIId", FIId);

        AppHdr.put("ns", "ns1");
        AppHdr.put("Fr", Fr);
        AppHdr.put("To", To);
//        AppHdr.put("BizMsgIdr", req.get("rrn").toString());//"710ORB12345678");
        AppHdr.put("BizMsgIdr", StringFunction.getCurrentDateYYYYMMDD()+"INDOIDJA"+StringFunction.randomNumber(14));//"710ORB12345678");
//        AppHdr.put("MsgDefIdr", req.get("MsgDefIdr").toString());
        AppHdr.put("MsgDefIdr", StringFunction.getCurrentDateYYYYMMDD()+"INDOIDJA"+StringFunction.randomNumber(14));

//        AppHdr.put("CreDt", req.get("currentDate").toString());
        AppHdr.put("CreDt", StringFunction.getCurrentDateYYYYMMDDHHMMSS());

        HashMap BusMsg = new LinkedHashMap();
        BusMsg.put("ns", "ns");
        BusMsg.put("AppHdr", AppHdr);

        return ISO20022XMLFunction.convertHashmapToXML(BusMsg, "BusMsg", rootNS);

    }
}
