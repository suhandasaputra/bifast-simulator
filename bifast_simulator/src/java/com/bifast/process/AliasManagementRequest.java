/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bifast.process;

import com.bifast.db.DatabaseProcess;
import java.util.HashMap;
import org.apache.log4j.Logger;
import org.jdom2.Element;
import org.jdom2.Namespace;

/**
 *
 * @author suhanda
 */
public class AliasManagementRequest {

    private static Logger log = Logger.getLogger(AliasManagementRequest.class);
//

    public String process(Element input, Namespace ns, Namespace ns1) {
        Namespace ns2 = Namespace.getNamespace("ns2", "urn:iso:std:iso:20022:tech:xsd:prxy.001.001.01");
        DatabaseProcess dp = new DatabaseProcess();
        String resp = "";

        String msg_id = input.getChild("PrxyRegn", ns).getChild("GrpHdr", ns2).getChild("MsgId", ns2).getValue();
        String MsgSndr = input.getChild("PrxyRegn", ns).getChild("GrpHdr", ns2).getChild("MsgSndr", ns2).getValue();
        String alias = input.getChild("PrxyRegn", ns).getChild("Regn", ns2).getChild("Prxy", ns2).getChild("Val", ns2).getValue();
        String alias_type = input.getChild("PrxyRegn", ns).getChild("Regn", ns2).getChild("Prxy", ns2).getChild("Tp", ns2).getValue();
        String regis_type = input.getChild("PrxyRegn", ns).getChild("Regn", ns2).getChild("RegnTp", ns2).getValue();
        String display_name = input.getChild("PrxyRegn", ns).getChild("Regn", ns2).getChild("PrxyRegn", ns2).getChild("DsplNm", ns2).getValue();
        String bank = input.getChild("PrxyRegn", ns).getChild("Regn", ns2).getChild("PrxyRegn", ns2).getChild("Agt", ns2).getValue();
        String bank_account = input.getChild("PrxyRegn", ns).getChild("Regn", ns2).getChild("PrxyRegn", ns2).getChild("Acct", ns2).getChild("Id", ns2).getValue();
        String name = input.getChild("PrxyRegn", ns).getChild("Regn", ns2).getChild("PrxyRegn", ns2).getChild("Acct", ns2).getChild("Nm", ns2).getValue();
        String id_type = input.getChild("PrxyRegn", ns).getChild("Regn", ns2).getChild("PrxyRegn", ns2).getChild("ScndId", ns2).getChild("Tp", ns2).getValue();
        String num_id = input.getChild("PrxyRegn", ns).getChild("Regn", ns2).getChild("PrxyRegn", ns2).getChild("ScndId", ns2).getChild("Val", ns2).getValue();
        String regis_status = input.getChild("PrxyRegn", ns).getChild("Regn", ns2).getChild("PrxyRegn", ns2).getChild("RegnSts", ns2).getValue();

        HashMap status = dp.addagent_bifast(msg_id,alias, alias_type, regis_type, display_name, bank, bank_account, name, id_type, num_id, regis_status);

        String xml_nya = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
                + "<ns:BusMsg xmlns:ns2=\"urn:iso:std:iso:20022:tech:xsd:prxy.002.001.01\" xmlns:ns=\"urn:iso\" \n"
                + "xmlns:ns1=\"urn:iso:std:iso:20022:tech:xsd:head.001.001.01\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" \n"
                + "xsi:schemaLocation=\"urn:iso ../../../xsd/phase1/MainCIHub.xsd \">\n"
                + "<ns:AppHdr>\n"
                + "<ns1:Fr>\n"
                + "<ns1:FIId>\n"
                + "<ns1:FinInstnId>\n"
                + "<ns1:Othr>\n"
                + "<ns1:Id>FASTIDJA</ns1:Id>\n"
                + "<!-- Sending system -->\n"
                + "</ns1:Othr>\n"
                + "</ns1:FinInstnId>\n"
                + "</ns1:FIId>\n"
                + "</ns1:Fr>\n"
                + "<ns1:To>\n"
                + "<ns1:FIId>\n"
                + "<ns1:FinInstnId>\n"
                + "<ns1:Othr>\n"
                + "<ns1:Id>INDOIDJA</ns1:Id>\n"
                + "<!-- Receiving system -->\n"
                + "</ns1:Othr>\n"
                + "</ns1:FinInstnId>\n"
                + "</ns1:FIId>\n"
                + "</ns1:To>\n"
                + "<ns1:BizMsgIdr>20210301INDOIDJA710HRB12345678</ns1:BizMsgIdr>\n"
                + "<ns1:MsgDefIdr>prxy.002.001.01</ns1:MsgDefIdr>\n"
                + "<ns1:CreDt>2021-03-01T12:00:00Z</ns1:CreDt>\n"
                + "</ns:AppHdr>\n"
                + "<ns:Document>\n"
                + "<ns:PrxyRegnRspn>\n"
                + "<ns2:GrpHdr>\n"
                + "<ns2:MsgId>"+msg_id+"</ns2:MsgId>\n"
                + "<ns2:CreDtTm>2021-03-01T19:00:00.000</ns2:CreDtTm>\n"
                + "<ns2:MsgRcpt>\n"
                + "<ns2:Agt>\n"
                + "<ns2:FinInstnId>\n"
                + "<ns2:Othr>\n"
                + "<ns2:Id>" + bank + "</ns2:Id>\n"
                + "</ns2:Othr>\n"
                + "</ns2:FinInstnId>\n"
                + "</ns2:Agt>\n"
                + "</ns2:MsgRcpt>\n"
                + "</ns2:GrpHdr>\n"
                + "<ns2:OrgnlGrpInf>\n"
                + "<ns2:OrgnlMsgId>"+msg_id+"</ns2:OrgnlMsgId>\n"
                + "<ns2:OrgnlMsgNmId>prxy.001.001.01</ns2:OrgnlMsgNmId>\n"
                + "<ns2:OrgnlCreDtTm>2021-09-27T18:59:58.000</ns2:OrgnlCreDtTm>\n"
                + "</ns2:OrgnlGrpInf>\n"
                + "<ns2:RegnRspn>\n"
                + "<ns2:PrxRspnSts>" +status.get("status")+ "</ns2:PrxRspnSts>\n"
                + "<ns2:StsRsnInf>\n"
                + "<ns2:Prtry>U000</ns2:Prtry>\n"
                + "</ns2:StsRsnInf>\n"
                + "<ns2:OrgnlRegnTp>"+alias_type+"</ns2:OrgnlRegnTp>\n"
                + "<ns2:PrxyRegn>\n"
                + "<ns2:RegnId>"+status.get("regis_number")+"</ns2:RegnId>\n"
                + "<!-- for NEWR requests, The new RegnId is generated by CI hub and returned -->\n"
                + "<ns2:Agt>\n"
                + "<ns2:FinInstnId>\n"
                + "<ns2:Othr>\n"
                + "<ns2:Id>"+bank+"</ns2:Id>\n"
                + "</ns2:Othr>\n"
                + "</ns2:FinInstnId>\n"
                + "</ns2:Agt>\n"
                + "<ns2:SplmtryData>\n"
                + "<ns2:Envlp>\n"
                + "<ns2:Cstmr>\n"
                + "<ns2:Tp>01</ns2:Tp>\n"
                + "<ns2:Id>1020304050607080</ns2:Id>\n"
                + "<ns2:RsdntSts>01</ns2:RsdntSts>\n"
                + "<ns2:TwnNm>0300</ns2:TwnNm>\n"
                + "</ns2:Cstmr>\n"
                + "</ns2:Envlp>\n"
                + "</ns2:SplmtryData>\n"
                + "</ns2:PrxyRegn>\n"
                + "</ns2:RegnRspn>\n"
                + "</ns:PrxyRegnRspn>\n"
                + "</ns:Document>\n"
                + "</ns:BusMsg>";
        return xml_nya;
    }
}
