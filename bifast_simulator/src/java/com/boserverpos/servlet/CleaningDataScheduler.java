package com.boserverpos.servlet;

///*
// * To change this template, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.balimuda.servlet;
//
//import com.ppob.database.DatabaseProcess;
//import com.ppob.singleton.InitParameterSingleton;
//import java.util.GregorianCalendar;
//import org.apache.log4j.Logger;
//
///**
// *
// * author MATAJARI MITRA SOLUSI
// */
//public class CleaningDataScheduler extends Thread {
//
//    private static Logger log = Logger.getLogger(CleaningDataScheduler.class);
//
//    @Override
//    public void run() {
//        DatabaseProcess dp = new DatabaseProcess();
//        while (InitParameterSingleton.getInstance().isStatusThread()) {
//            try {
//                GregorianCalendar cal = new GregorianCalendar();
//                if (cal.get(GregorianCalendar.HOUR_OF_DAY) == 0) {
//                    System.out.println("cleaning data running !");
//                    log.info("cleaning data running !");
//                    dp.cleanData();
//                }
//                cal = null;
//                Thread.sleep(1000 * 60 * 60);
//                
//                //test dev
////                Thread.sleep(1000 * 60 * 5);
//            } catch (InterruptedException ex) {
//                log.error(ex.getMessage());
//            }
//        }
//    }
//}
