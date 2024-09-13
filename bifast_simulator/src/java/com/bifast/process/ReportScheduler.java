/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bifast.process;

import com.bifast.db.DatabaseProcess;
import java.util.GregorianCalendar;
import java.util.HashMap;
import org.apache.log4j.Logger;

/**
 *
 * @author herrysuganda
 */
public class ReportScheduler extends Thread {

    private static Logger log = Logger.getLogger(ReportScheduler.class);
    DatabaseProcess dp = new DatabaseProcess();

    public void run() {
        while (true) {
            try {
                HashMap status1 = dp.getStatus();
                if (status1.get("notif").equals(true)) {
                    String rrn = status1.get("rrn").toString();
                    dp.updateStatus(rrn);
                }
                status1 = null;
                Thread.sleep(1000 * 10);
            } catch (InterruptedException ex) {
                log.error(ex.getMessage());
            }
        }
    }
}
