/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bifast.db;

import com.bifast.model.Model_Conn;
import com.bifast.model.Model_Report;
import com.bifast.model.Model_User;
import com.boserverpos.function.SendHttpProcess;
import com.boserverpos.function.StringFunction;
import com.boserverpos.parameter.StaticParameter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Locale;
import org.apache.log4j.Logger;

public class DatabaseProcess {

    private static final Logger log = Logger.getLogger(DatabaseProcess.class);
    SendHttpProcess http = new SendHttpProcess();

    private void clearStatment(PreparedStatement stat) {
//        log.info("stat 2 : " + stat);
        if (stat != null) {
            try {
//                log.info("stat A");
                stat.clearBatch();
//                log.info("stat B");
                stat.clearParameters();
//                log.info("stat C");
                stat.close();
//                log.info("stat D");
                stat = null;
//                log.info("stat E");
            } catch (SQLException ex) {
//                log.error("clearStatment : " +ex.getMessage());
//                ex.printStackTrace();
            }
        }
    }

    private void clearDBConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
                conn = null;
            } catch (SQLException ex) {
//                log.error("clearDBConnection : "+ex.getMessage());
            }
        }
    }

    private void clearResultset(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
                rs = null;
            } catch (SQLException ex) {
//                log.error("clearResultset : "+ex.getMessage());
            }
        }
    }

    private void clearAllConnStatRS(Connection conn, PreparedStatement stat, ResultSet rs) {
        clearResultset(rs);
        clearStatment(stat);
        clearDBConnection(conn);
    }

    public HashMap addagent_bifast(String msg_id, String alias, String alias_type, String regis_type, String display_name, String bank, String bank_account, String name, String id_type, String num_id, String regis_status) {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        String status = "RJCT";
        HashMap ggwp1 = new HashMap();
        try {
            conn = DatasourceEntry.getInstance().getPostgreDS().getConnection();
            stat = conn.prepareStatement("insert into bifast_log_transaction (end_to_end_id,rrn,trx_id,trx_type,from_account,to_account,amount,status,from_bank,to_bank) values(?,?,?,?,?,?,?,?,?,?)");
            stat.setString(1, msg_id);
            stat.setString(2, msg_id);
            stat.setString(3, msg_id);
            stat.setString(4, "registration");
            stat.setString(5, "-");
            stat.setString(6, bank_account);
            stat.setString(7, "-");
            stat.setString(8, "-");
            stat.setString(9, bank);
            stat.setString(10, "-");
            stat.executeUpdate();

            stat = conn.prepareStatement("INSERT INTO bifast_agent (alias, alias_type, regis_type, display_name, bank, bank_account, name, id_type, num_id, regis_status) VALUES (?,?,?,?,?,?,?,?,?,?)");
            stat.setString(1, alias);
            stat.setString(2, alias_type);
            stat.setString(3, regis_type);
            stat.setString(4, display_name);
            stat.setString(5, bank);
            stat.setString(6, bank_account);
            stat.setString(7, name);
            stat.setString(8, id_type);
            stat.setString(9, num_id);
            stat.setString(10, regis_status);
            stat.executeUpdate();

            stat = conn.prepareStatement("select regis_id from bifast_agent where alias = ?");
            stat.setString(1, alias);
            rs = stat.executeQuery();
            if (rs.next()) {
                status = "ACTC";
                ggwp1.put("status", status);
                ggwp1.put("regis_number", status);
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return ggwp1;
    }

//    public HashMap check_balance(String EndToEndId, String rrn, String TxId, String trx_type, String toaccount, String IntrBkSttlmAmt, String frombank, String tobank) {
//        Connection conn = null;
//        PreparedStatement stat = null;
//        ResultSet rs = null;
//        PreparedStatement stat1 = null;
//        ResultSet rs1 = null;
//        HashMap ggwp = new HashMap();
//        String status1 = "RJCT";
//        try {
//            conn = DatasourceEntry.getInstance().getPostgreDS().getConnection();
//            stat = conn.prepareStatement("insert into bifast_log_transaction (end_to_end_id,rrn,trx_id,trx_type,from_account,to_account,amount,status,from_bank,to_bank) values(?,?,?,?,?,?,?,?,?,?)");
//            stat.setString(1, EndToEndId);
//            stat.setString(2, rrn);
//            stat.setString(3, TxId);
//            stat.setString(4, trx_type);
//            stat.setString(5, "-");
//            stat.setString(6, toaccount);
//            stat.setString(7, IntrBkSttlmAmt);
//            stat.setString(8, "-");
//            stat.setString(9, frombank);
//            stat.setString(10, tobank);
//            stat.executeUpdate();
//
//            stat = conn.prepareStatement("SELECT * from bifast_agent where bank_account = ?");
//            stat.setString(1, toaccount);
//            rs = stat.executeQuery();
//            if (rs.next()) {
//                stat1 = conn.prepareStatement("SELECT a.*, b.curr_balance FROM bank_am_user a inner join bank_am_balance b on a.account_number = b.account_number where a.account_number = ?");
//                stat1.setString(1, toaccount);
//                rs1 = stat1.executeQuery();
//                if (rs1.next()) {
//                    int balance = rs1.getInt("curr_balance");
//                    if (balance > Double.valueOf(IntrBkSttlmAmt)) {
//                        status1 = "ACTC";
//                    }
//                    ggwp.put("status", status1);
//                    ggwp.put("name", rs1.getString("name"));
//                    ggwp.put("as_alias", rs1.getString("as_alias"));
//                    ggwp.put("account_number", rs1.getString("account_number"));
//                    ggwp.put("number_id", rs1.getString("number_id"));
//                }
//            }
//        } catch (SQLException e) {
//            System.out.println(e);
//        } finally {
//            clearAllConnStatRS(conn, stat, rs);
//        }
//        return ggwp;
//    }
    public HashMap check_account(String EndToEndId, String rrn, String TxId, String trx_type, String toaccount, String IntrBkSttlmAmt, String frombank, String tobank) {
        System.out.println("tes1");
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        HashMap ggwp = new HashMap();
        String status = "RJCT";
        try {
            System.out.println("tes2");
            conn = DatasourceEntry.getInstance().getPostgreDS().getConnection();
            stat = conn.prepareStatement("SELECT a.*, b.url FROM bifast_agent a inner join bifast_connection b on a.bank = b.bank_code where a.bank_account = ?");
            stat.setString(1, toaccount);
            rs = stat.executeQuery();
            if (rs.next()) {
                status = "ACTC";
                stat = conn.prepareStatement("insert into bifast_log_transaction (end_to_end_id,rrn,trx_id,trx_type,from_account,to_account,amount,status,from_bank,to_bank) values(?,?,?,?,?,?,?,?,?,?)");
                stat.setString(1, EndToEndId);
                stat.setString(2, rrn);
                stat.setString(3, TxId);
                stat.setString(4, trx_type);
                stat.setString(5, "-");
                stat.setString(6, toaccount);
                stat.setString(7, IntrBkSttlmAmt);
                stat.setString(8, "-");
                stat.setString(9, frombank);
                stat.setString(10, tobank);
                stat.executeUpdate();
                ggwp.put("url", rs.getString("url"));
            }
            ggwp.put("status", status);
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return ggwp;
    }

//    public HashMap debt_balance(String rrn, String EndToEndId, String TxId, String trx_type, String fromaccount, String toaccount, String IntrBkSttlmAmt, String frombank, String tobank, String fromname, String toname, String from_num_id, String to_num_id) {
//        Connection conn = null;
//        PreparedStatement stat = null;
//        ResultSet rs = null;
//        PreparedStatement stat1 = null;
//        ResultSet rs1 = null;
//        HashMap ggwp = new HashMap();
//        String status = "RJCT";
//        try {
//            conn = DatasourceEntry.getInstance().getPostgreDS().getConnection();
//            stat = conn.prepareStatement("insert into bifast_log_transaction (end_to_end_id,rrn,trx_id,trx_type,from_account,to_account,amount,status,from_bank,to_bank,notif,from_name,to_name,from_num_id,to_num_id) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
//            stat.setString(1, EndToEndId);
//            stat.setString(2, rrn);
//            stat.setString(3, TxId);
//            stat.setString(4, trx_type);
//            stat.setString(5, fromaccount);
//            stat.setString(6, toaccount);
//            stat.setString(7, IntrBkSttlmAmt);
//            stat.setString(8, "-");
//            stat.setString(9, frombank);
//            stat.setString(10, tobank);
//            stat.setBoolean(11, true);
//            stat.setString(12, fromname);
//            stat.setString(13, toname);
//            stat.setString(14, from_num_id);
//            stat.setString(15, to_num_id);
//            stat.executeUpdate();
//            stat = conn.prepareStatement("SELECT * from bifast_agent where bank_account = ?");
//            stat.setString(1, toaccount);
//            rs = stat.executeQuery();
//            if (rs.next()) {
//                stat1 = conn.prepareStatement("SELECT a.*, b.curr_balance FROM bank_am_user a inner join bank_am_balance b on a.account_number = b.account_number where a.account_number = ?");
//                stat1.setString(1, toaccount);
//                rs1 = stat1.executeQuery();
//                if (rs1.next()) {
//                    int balance = rs1.getInt("curr_balance");
//                    if (balance > Double.valueOf(IntrBkSttlmAmt)) {
//                        Double amn = Double.valueOf(IntrBkSttlmAmt);
//
//                        Double netnya = balance - amn;
//                        stat = conn.prepareStatement("UPDATE bank_am_balance set curr_balance = ?");
//                        stat.setDouble(1, netnya);
//                        stat.executeUpdate();
//                        status = "ACTC";
//                    }
//                    ggwp.put("status", status);
//                    ggwp.put("name", rs1.getString("name"));
//                    ggwp.put("type_id", rs1.getString("type_id"));
//                    ggwp.put("number_id", rs1.getString("number_id"));
//                }
//            }
//        } catch (SQLException e) {
//            System.out.println(e);
//        } finally {
//            clearAllConnStatRS(conn, stat, rs);
//        }
//        return ggwp;
//    }
    public HashMap debt_balance(String rrn, String EndToEndId, String TxId, String trx_type, String fromaccount, String toaccount, String IntrBkSttlmAmt, String frombank, String tobank, String fromname, String toname, String from_num_id, String to_num_id) {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        HashMap ggwp = new HashMap();
        String status = "RJCT";
        try {
            conn = DatasourceEntry.getInstance().getPostgreDS().getConnection();
            stat = conn.prepareStatement("SELECT a.*, b.url FROM bifast_agent a inner join bifast_connection b on a.bank = b.bank_code where a.bank_account = ?");
            stat.setString(1, toaccount);
            rs = stat.executeQuery();
            if (rs.next()) {
                status = "ACTC";
                stat = conn.prepareStatement("insert into bifast_log_transaction (end_to_end_id,rrn,trx_id,trx_type,from_account,to_account,amount,status,from_bank,to_bank,notif,from_name,to_name,from_num_id,to_num_id) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                stat.setString(1, EndToEndId);
                stat.setString(2, rrn);
                stat.setString(3, TxId);
                stat.setString(4, trx_type);
                stat.setString(5, fromaccount);
                stat.setString(6, toaccount);
                stat.setString(7, IntrBkSttlmAmt);
                stat.setString(8, "-");
                stat.setString(9, frombank);
                stat.setString(10, tobank);
                stat.setBoolean(11, true);
                stat.setString(12, fromname);
                stat.setString(13, toname);
                stat.setString(14, from_num_id);
                stat.setString(15, to_num_id);
                stat.executeUpdate();
                ggwp.put("url", rs.getString("url"));
            }
            ggwp.put("status", status);
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return ggwp;
    }

    public HashMap getStatus() {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        HashMap huhu = new HashMap();
        try {
            conn = DatasourceEntry.getInstance().getPostgreDS().getConnection();
            stat = conn.prepareStatement("SELECT rrn,notif from bifast_log_transaction where trx_type = 'payment' and notif = true order by date_internal limit 1");
            rs = stat.executeQuery();
            if (rs.next()) {
                huhu.put("rrn", rs.getString("rrn"));
                huhu.put("notif", rs.getBoolean("notif"));
            } else {
                huhu.put("notif", false);
            }
        } catch (SQLException ex) {
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return huhu;
    }

    public boolean updateStatus(String rrn) {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        try {
            conn = DatasourceEntry.getInstance().getPostgreDS().getConnection();
            stat = conn.prepareStatement("SELECT * from bifast_log_transaction where rrn = ?");
            stat.setString(1, rrn);
            rs = stat.executeQuery();
            if (rs.next()) {
                stat = conn.prepareStatement("UPDATE bifast_log_transaction set notif = ?");
                stat.setBoolean(1, false);
                stat.executeUpdate();
                String bismsgidr = StringFunction.getCurrentDateYYYYMMDDHHMMSS();
                String reqMsg = "<?xml version=\"1.0\" encoding=\"utf-8\" standalone=\"no\" ?>\n"
                        + "<ns:BusMsg xmlns:ns=\"urn:iso\" xmlns:ns1=\"urn:iso:std:iso:20022:tech:xsd:head.001.001.01\" \n"
                        + "xmlns:ns2=\"urn:iso:std:iso:20022:tech:xsd:pacs.002.001.10\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" \n"
                        + "xsi:schemaLocation=\"urn:iso ../../../xsd/phase1/MainCIHub.xsd \">\n"
                        + "<ns:AppHdr>\n"
                        + "<ns1:Fr>\n"
                        + "<ns1:FIId>\n"
                        + "<ns1:FinInstnId>\n"
                        + "<ns1:Othr>\n"
                        + "<ns1:Id>FASTIDJA</ns1:Id>\n"
                        + "</ns1:Othr>\n"
                        + "</ns1:FinInstnId>\n"
                        + "</ns1:FIId>\n"
                        + "</ns1:Fr>\n"
                        + "<ns1:To>\n"
                        + "<ns1:FIId>\n"
                        + "<ns1:FinInstnId>\n"
                        + "<ns1:Othr>\n"
                        + "<ns1:Id>" + rs.getString("from_bank") + "</ns1:Id>\n"
                        + "</ns1:Othr>\n"
                        + "</ns1:FinInstnId>\n"
                        + "</ns1:FIId>\n"
                        + "</ns1:To>\n"
                        + "<ns1:BizMsgIdr>" + bismsgidr + "</ns1:BizMsgIdr>\n"
                        + "<ns1:MsgDefIdr>pacs.002.001.10</ns1:MsgDefIdr>\n"
                        + "<ns1:BizSvc>SETTLEMENTCONFIRMATION</ns1:BizSvc>\n"
                        + "<!-- To distinguish this usage of pacs.002 as a settlement confirmation notification -->\n"
                        + "<ns1:CreDt>2021-03-01T12:00:00Z</ns1:CreDt>\n"
                        + "</ns:AppHdr>\n"
                        + "<ns:Document>\n"
                        + "<ns:FIToFIPmtStsRpt>\n"
                        + "<ns2:GrpHdr>\n"
                        + "<ns2:MsgId>" + bismsgidr + "</ns2:MsgId>\n"
                        + "<ns2:CreDtTm>2021-03-01T19:00:00.000</ns2:CreDtTm>\n"
                        + "</ns2:GrpHdr>\n"
                        + "<ns2:OrgnlGrpInfAndSts>\n"
                        + "<ns2:OrgnlMsgId>" + rs.getString("rrn") + "</ns2:OrgnlMsgId>\n"
                        + "<ns2:OrgnlMsgNmId>pacs.008.001.08</ns2:OrgnlMsgNmId>\n"
                        + "<ns2:OrgnlCreDtTm>2021-03-01T19:00:00.000</ns2:OrgnlCreDtTm>\n"
                        + "</ns2:OrgnlGrpInfAndSts>\n"
                        + "<ns2:TxInfAndSts>\n"
                        + "<ns2:OrgnlEndToEndId>" + rs.getString("end_to_end_id") + "</ns2:OrgnlEndToEndId>\n"
                        + "<ns2:OrgnlTxId>" + rs.getString("trx_id") + "</ns2:OrgnlTxId>\n"
                        + "<ns2:TxSts>ACTC</ns2:TxSts>\n"
                        + "<ns2:StsRsnInf>\n"
                        + "<ns2:Rsn>\n"
                        + "<ns2:Prtry>U000</ns2:Prtry>\n"
                        + "</ns2:Rsn>\n"
                        + "<ns2:AddtlInf>O 1234</ns2:AddtlInf>\n"
                        + "</ns2:StsRsnInf>\n"
                        + "<ns2:OrgnlTxRef>\n"
                        + "<ns2:IntrBkSttlmDt>" + StringFunction.getGMTCurrentDateMMDDHHMMSS() + "</ns2:IntrBkSttlmDt>\n"
                        + "<ns2:Dbtr>\n"
                        + "<ns2:Pty>\n"
                        + "<ns2:Nm>" + rs.getString("from_name") + "</ns2:Nm>\n"
                        + "</ns2:Pty>\n"
                        + "</ns2:Dbtr>\n"
                        + "<ns2:DbtrAcct>\n"
                        + "<ns2:Id>\n"
                        + "<ns2:Othr>\n"
                        + "<ns2:Id>" + rs.getString("from_account") + "</ns2:Id>\n"
                        + "<!-- Debtor Account ID - copy from pacs.008 -->\n"
                        + "</ns2:Othr>\n"
                        + "</ns2:Id>\n"
                        + "<ns2:Tp>\n"
                        + "<ns2:Prtry>CACC</ns2:Prtry>\n"
                        + "</ns2:Tp>\n"
                        + "</ns2:DbtrAcct>\n"
                        + "<ns2:DbtrAgt>\n"
                        + "<ns2:FinInstnId>\n"
                        + "<ns2:Othr>\n"
                        + "<ns2:Id>" + rs.getString("from_bank") + "</ns2:Id>\n"
                        + "</ns2:Othr>\n"
                        + "</ns2:FinInstnId>\n"
                        + "</ns2:DbtrAgt>\n"
                        + "<ns2:CdtrAgt>\n"
                        + "<ns2:FinInstnId>\n"
                        + "<ns2:Othr>\n"
                        + "<ns2:Id>" + rs.getString("from_bank") + "</ns2:Id>\n"
                        + "</ns2:Othr>\n"
                        + "</ns2:FinInstnId>\n"
                        + "</ns2:CdtrAgt>\n"
                        + "<ns2:Cdtr>\n"
                        + "<ns2:Pty>\n"
                        + "<ns2:Nm>" + rs.getString("to_name") + "</ns2:Nm>\n"
                        + "</ns2:Pty>\n"
                        + "</ns2:Cdtr>\n"
                        + "<ns2:CdtrAcct>\n"
                        + "<ns2:Id>\n"
                        + "<ns2:Othr>\n"
                        + "<ns2:Id>" + rs.getString("to_account") + "</ns2:Id>\n"
                        + "</ns2:Othr>\n"
                        + "</ns2:Id>\n"
                        + "<ns2:Tp>\n"
                        + "<ns2:Prtry>SVGS</ns2:Prtry>\n"
                        + "</ns2:Tp>\n"
                        + "</ns2:CdtrAcct>\n"
                        + "</ns2:OrgnlTxRef>\n"
                        + "<ns2:SplmtryData>\n"
                        + "<ns2:Envlp>\n"
                        + "<ns2:Dbtr>\n"
                        + "<ns2:Tp>01</ns2:Tp>\n"
                        + "<ns2:Id>" + rs.getString("from_num_id") + "</ns2:Id>\n"
                        + "<ns2:RsdntSts>01</ns2:RsdntSts>\n"
                        + "<ns2:TwnNm>0300</ns2:TwnNm>\n"
                        + "</ns2:Dbtr>\n"
                        + "<ns2:Cdtr>\n"
                        + "<ns2:Tp>01</ns2:Tp>\n"
                        + "<ns2:Id>" + rs.getString("to_num_id") + "</ns2:Id>\n"
                        + "<ns2:RsdntSts>01</ns2:RsdntSts>\n"
                        + "<ns2:TwnNm>0300</ns2:TwnNm>\n"
                        + "</ns2:Cdtr>\n"
                        + "<ns2:DbtrAgtAcct>\n"
                        + "<ns2:Id>\n"
                        + "<ns2:Othr>\n"
                        + "<ns2:Id>654321098</ns2:Id>\n"
                        + "</ns2:Othr>\n"
                        + "</ns2:Id>\n"
                        + "</ns2:DbtrAgtAcct>\n"
                        + "<ns2:CdtrAgtAcct>\n"
                        + "<ns2:Id>\n"
                        + "<ns2:Othr>\n"
                        + "<ns2:Id>890123456</ns2:Id>\n"
                        + "</ns2:Othr>\n"
                        + "</ns2:Id>\n"
                        + "</ns2:CdtrAgtAcct>\n"
                        + "</ns2:Envlp>\n"
                        + "</ns2:SplmtryData>\n"
                        + "</ns2:TxInfAndSts>\n"
                        + "</ns:FIToFIPmtStsRpt>\n"
                        + "</ns:Document>\n"
                        + "</ns:BusMsg>";
                if (http.sendNotif(StaticParameter.url_mifast_bank_A, reqMsg)) {
                    http.sendNotif(StaticParameter.url_mifast_bank_B, reqMsg);
                    stat.executeUpdate();
                }
            }
        } catch (SQLException ex) {
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return false;
    }

    public ArrayList<Model_User> getAllUser() throws ParseException {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        ArrayList<Model_User> listUser = new ArrayList<>();
        try {
            conn = DatasourceEntry.getInstance().getPostgreDS().getConnection();
            stat = conn.prepareStatement("SELECT * from bifast_agent");
            rs = stat.executeQuery();
            while (rs.next()) {
                Model_User mu = new Model_User();
                mu.setAlias(rs.getString("alias"));
                mu.setAlias_type(rs.getString("alias_type"));
                mu.setBank(rs.getString("bank"));
                mu.setBank_account(rs.getString("bank_account"));
                mu.setDate_create(rs.getString("date_create"));
                mu.setDisplay_name(rs.getString("display_name"));
                mu.setId_type(rs.getString("id_type"));
                mu.setName(rs.getString("name"));
                mu.setNum_id(rs.getString("num_id"));
                mu.setRegis_id(rs.getString("regis_id"));
                mu.setRegis_status(rs.getString("regis_status"));
                mu.setRegis_type(rs.getString("regis_type"));
                listUser.add(mu);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return listUser;
    }

    public ArrayList<Model_Report> getAllTrx() throws ParseException {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        ArrayList<Model_Report> listTrx = new ArrayList<>();
        try {
            conn = DatasourceEntry.getInstance().getPostgreDS().getConnection();
            stat = conn.prepareStatement("select * from bifast_log_transaction");
            rs = stat.executeQuery();
            while (rs.next()) {
                Model_Report reportyabes = new Model_Report();
                reportyabes.setAmount(rs.getString("amount"));
                reportyabes.setDate_internal(rs.getString("date_internal"));
                reportyabes.setEnd_to_end_id(rs.getString("end_to_end_id"));
                reportyabes.setFrom_account(rs.getString("from_account"));
                reportyabes.setFrom_bank(rs.getString("from_bank"));
                reportyabes.setFrom_name(rs.getString("from_name"));
                reportyabes.setFrom_num_id(rs.getString("from_num_id"));
                reportyabes.setNotif(rs.getString("notif"));
                reportyabes.setRrn(rs.getString("rrn"));
                reportyabes.setStatus(rs.getString("status"));
                reportyabes.setTo_account(rs.getString("to_account"));
                reportyabes.setTo_bank(rs.getString("to_bank"));
                reportyabes.setTo_name(rs.getString("to_name"));
                reportyabes.setTo_num_id(rs.getString("to_num_id"));
                reportyabes.setTrx_id(rs.getString("trx_id"));
                reportyabes.setTrx_type(rs.getString("trx_type"));
                listTrx.add(reportyabes);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return listTrx;
    }

    public ArrayList<Model_Conn> getAllConn() throws ParseException {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        ArrayList<Model_Conn> listConn = new ArrayList<>();
        try {
            conn = DatasourceEntry.getInstance().getPostgreDS().getConnection();
            stat = conn.prepareStatement("select * from bifast_connection");
            rs = stat.executeQuery();
            while (rs.next()) {
                Model_Conn mu = new Model_Conn();
                mu.setBank(rs.getString("bank_code"));
                mu.setUrl(rs.getString("url"));
                listConn.add(mu);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return listConn;
    }

    public boolean updateStatus_fin(String rrn, String status) {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        try {
            conn = DatasourceEntry.getInstance().getPostgreDS().getConnection();
            stat = conn.prepareStatement("SELECT * from bifast_log_transaction where rrn = ?");
            stat.setString(1, rrn);
            rs = stat.executeQuery();
            if (rs.next()) {
                stat = conn.prepareStatement("UPDATE bifast_log_transaction set status = ?");
                stat.setString(1, status);
                stat.executeUpdate();
            }
        } catch (SQLException ex) {
        } finally {
            clearAllConnStatRS(conn, stat, rs);
        }
        return true;
    }
}
