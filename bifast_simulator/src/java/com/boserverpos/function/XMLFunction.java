///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.boserverpos.function;
//
////import com.ppob.parameter.FieldParameter;
//import java.io.IOException;
//import java.io.StringReader;
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.List;
//import org.apache.log4j.Logger;
//import org.jdom2.Document;
//import org.jdom2.Element;
//import org.jdom2.JDOMException;
//import org.jdom2.input.SAXBuilder;
//import org.jdom2.output.Format;
//import org.jdom2.output.XMLOutputter;
//import org.xml.sax.InputSource;
//
///**
// *
// * author MATAJARI MITRA SOLUSI
// */
//public class XMLFunction {
//
//    private static Logger log = Logger.getLogger(XMLFunction.class);
//
//    public static HashMap convertXMLtoHashmap(String xmltext) {
//        HashMap result = new HashMap();
//        try {
//            InputSource is = new InputSource(new StringReader(xmltext));
//            SAXBuilder saxBuilder = new SAXBuilder();
//            Document document = saxBuilder.build(is);
//            Element rootElement = document.getRootElement();
//            List<Element> content = rootElement.getChildren();
//            System.out.println("content size : " + content.size());
//            for (int temp = 0; temp < content.size(); temp++) {
//                Element data = content.get(temp);
//                System.out.println(data.getName() + " - " + data.getValue());
//                result.put(data.getName(), data.getValue());
//            }
//        } catch (JDOMException e) {
//            e.printStackTrace();
//        } catch (IOException ioe) {
//            ioe.printStackTrace();
//        }
//        return result;
//    }
//
//    public static String convertHashmapToXML(HashMap map, String rootelement) {
//        Element root = new Element(rootelement);
//        Document doc = new Document(root);
//        Iterator iter = map.keySet().iterator();
//        String item;
//        while (iter.hasNext()) {
//            item = iter.next().toString();
//            Element node = new Element(item);
//            node.setText(map.get(item).toString());
//            doc.getRootElement().addContent(node);
//
//        }
//        XMLOutputter xmlOutput = new XMLOutputter();
//        xmlOutput.setFormat(Format.getCompactFormat());
//        return xmlOutput.outputString(doc);
//    }
//
//    public static Element setElementDetail(String elementName, String elementValue, Element parent) {
//
//        Element objElemenet = new Element(elementName);
//        objElemenet.setText(elementValue);
//        parent.addContent(objElemenet);
//
//        return parent;
//    }
//
//}
