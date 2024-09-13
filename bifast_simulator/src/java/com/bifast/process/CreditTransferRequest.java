/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bifast.process;

import com.bifast.db.DatabaseProcess;
import com.boserverpos.function.SendHttpProcess;
import com.boserverpos.parameter.StaticParameter;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.Namespace;
import org.jdom2.input.SAXBuilder;

/**
 *
 * @author suhanda
 */
public class CreditTransferRequest {

    private static Logger log = Logger.getLogger(CreditTransferRequest.class);
//

    public String process(String root1, Element input, Namespace ns, Namespace ns1) throws UnsupportedEncodingException, JDOMException, IOException {
        DatabaseProcess dp = new DatabaseProcess();
        Namespace ns2 = Namespace.getNamespace("ns2", "urn:iso:std:iso:20022:tech:xsd:pacs.008.001.08");
        String msg_id = input.getChild("FIToFICstmrCdtTrf", ns).getChild("GrpHdr", ns2).getChild("MsgId", ns2).getValue();
        String EndToEndId = input.getChild("FIToFICstmrCdtTrf", ns).getChild("CdtTrfTxInf", ns2).getChild("PmtId", ns2).getChild("EndToEndId", ns2).getValue();
        String TxId = input.getChild("FIToFICstmrCdtTrf", ns).getChild("CdtTrfTxInf", ns2).getChild("PmtId", ns2).getChild("TxId", ns2).getValue();
        String trx_type = "payment";//trx_type
        String fromaccount = input.getChild("FIToFICstmrCdtTrf", ns).getChild("CdtTrfTxInf", ns2).getChild("DbtrAcct", ns2).getChild("Id", ns2).getValue();
        String toaccount = input.getChild("FIToFICstmrCdtTrf", ns).getChild("CdtTrfTxInf", ns2).getChild("CdtrAcct", ns2).getChild("Id", ns2).getValue();
        String IntrBkSttlmAmt = input.getChild("FIToFICstmrCdtTrf", ns).getChild("CdtTrfTxInf", ns2).getChild("IntrBkSttlmAmt", ns2).getValue();
        String frombank = input.getChild("FIToFICstmrCdtTrf", ns).getChild("CdtTrfTxInf", ns2).getChild("DbtrAgt", ns2).getValue();
        String tobank = input.getChild("FIToFICstmrCdtTrf", ns).getChild("CdtTrfTxInf", ns2).getChild("CdtrAgt", ns2).getValue();
        String fromname = input.getChild("FIToFICstmrCdtTrf", ns).getChild("CdtTrfTxInf", ns2).getChild("Dbtr", ns2).getChild("Nm", ns2).getValue();
        String toname = input.getChild("FIToFICstmrCdtTrf", ns).getChild("CdtTrfTxInf", ns2).getChild("Cdtr", ns2).getChild("Nm", ns2).getValue();
        String from_num_id = input.getChild("FIToFICstmrCdtTrf", ns).getChild("CdtTrfTxInf", ns2).getChild("Dbtr", ns2).getChild("Id", ns2).getValue();
        String to_num_id = input.getChild("FIToFICstmrCdtTrf", ns).getChild("CdtTrfTxInf", ns2).getChild("Cdtr", ns2).getChild("Id", ns2).getValue();

        String responsebank;
        HashMap resp = dp.debt_balance(msg_id, EndToEndId, TxId, trx_type, fromaccount, toaccount, IntrBkSttlmAmt, frombank, tobank, fromname, toname, from_num_id, to_num_id);
        if (resp.get("status").equals("ACTC")) {
            SendHttpProcess http = new SendHttpProcess();
            responsebank = http.sendHttpRequest(resp.get("url").toString(), root1);

            SAXBuilder builder1 = new SAXBuilder();
            InputStream stream = new ByteArrayInputStream(responsebank.getBytes("UTF-8"));
            Document doc = builder1.build(stream);
            Element root2 = doc.getRootElement();
            Namespace ns_resp = Namespace.getNamespace("ns", "urn:iso");
            Namespace ns2_resp = Namespace.getNamespace("ns2", "urn:iso:std:iso:20022:tech:xsd:pacs.002.001.10");
            Element Document = root2.getChild("Document", ns_resp);
            String stat = Document.getChild("FIToFIPmtStsRpt", ns_resp).getChild("TxInfAndSts", ns2_resp).getChild("TxSts", ns2_resp).getValue();
            dp.updateStatus_fin(msg_id, stat);
        } else {
            responsebank = new MessageRejectResponse().process();
        }
        return responsebank;
    }
}
