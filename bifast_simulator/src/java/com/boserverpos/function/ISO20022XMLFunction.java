package com.boserverpos.function;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
//import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.Namespace;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.xml.sax.InputSource;

/**
 *
 * @author adi
 */
public class ISO20022XMLFunction {

    public static HashMap convertXMLtoHashmap(String xmltext) {
        HashMap hm1 = new LinkedHashMap();
//        HashMap hm2 = new LinkedHashMap();
//        HashMap hm3 = new LinkedHashMap();
//        HashMap hm4 = new LinkedHashMap();
//        HashMap hm5 = new LinkedHashMap();
        try {
            InputSource is = new InputSource(new StringReader(xmltext));
            SAXBuilder saxBuilder = new SAXBuilder();
            Document document = saxBuilder.build(is);
            Element rootElement = document.getRootElement();
            List<Element> content = rootElement.getChildren();
            for (int temp = 0; temp < content.size(); temp++) {
                Element data = content.get(temp);
                List<Element> content2 = data.getChildren();
                if (content2.size() > 0) {
                    HashMap hm2 = new LinkedHashMap();
                    for (int i = 0; i < content2.size(); i++) {
                        Element data2 = content2.get(i);
                        List<Element> content3 = data2.getChildren();
                        if (content3.size() > 0) {
                            HashMap hm3 = new LinkedHashMap();
                            for (int j = 0; j < content3.size(); j++) {
                                Element data3 = content3.get(j);
                                List<Element> content4 = data3.getChildren();
                                if (content4.size() > 0) {
                                    HashMap hm4 = new LinkedHashMap();
                                    for (int k = 0; k < content4.size(); k++) {
                                        Element data4 = content4.get(k);
                                        List<Element> content5 = data4.getChildren();

                                        if (content5.size() > 0) {
                                            HashMap hm5 = new LinkedHashMap();
                                            for (int l = 0; l < content5.size(); l++) {
                                                Element data5 = content5.get(l);
                                                List<Element> content6 = data5.getChildren();

                                                if (content6.size() > 0) {
                                                    HashMap hm6 = new LinkedHashMap();
                                                    for (int m = 0; m < content6.size(); m++) {
                                                        Element data6 = content6.get(m);
                                                        List<Element> content7 = data6.getChildren();

                                                        if (content7.size() > 0) {
                                                            HashMap hm7 = new LinkedHashMap();
                                                            for (int n = 0; n < content7.size(); n++) {
                                                                Element data7 = content7.get(n);
                                                                List<Element> content8 = data7.getChildren();
///////////////////////////////////////////////////////////////////////////////////////////////                                        
                                                                if (content8.size() > 0) {
                                                                    HashMap hm8 = new LinkedHashMap();
                                                                    for (int o = 0; o < content8.size(); o++) {
                                                                        Element data8 = content8.get(o);
                                                                        hm8.put(data8.getName(), data8.getValue());
                                                                    }
                                                                    hm7.put(data7.getName(), hm8);
                                                                } else {
                                                                    hm7.put(data7.getName(), data7.getValue());
                                                                }
///////////////////////////////////////////////////////////////////////////////////////////////
                                                            }
                                                            hm6.put(data6.getName(), hm7);
                                                        } else {
                                                            hm6.put(data6.getName(), data6.getValue());
                                                        }
                                                    }
                                                    hm5.put(data5.getName(), hm6);
                                                } else {
                                                    hm5.put(data5.getName(), data5.getValue());
                                                }
                                            }
                                            hm4.put(data4.getName(), hm5);
                                        } else {
                                            hm4.put(data4.getName(), data4.getValue());
                                        }
                                    }
                                    hm3.put(data3.getName(), hm4);
                                } else {
                                    hm3.put(data3.getName(), data3.getValue());
                                }
                            }
                            hm2.put(data2.getName(), hm3);
                        } else {
                            hm2.put(data2.getName(), data2.getValue());
                        }
                    }
                    hm1.put(data.getName(), hm2);

                } else {
                    hm1.put(data.getName(), data.getValue());
                    System.out.println(data.getAttributes());
                }
            }

//            System.out.println("HM 5: " + hm5);
//            System.out.println("HM 4: " + hm4);
//            System.out.println("HM 3: " + hm3);
//            System.out.println("HM 2: " + hm2);
//            System.out.println("HM 1: " + hm1);
        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return hm1;
    }

    public static String Merger2XMLString(String xml1, String xml2, HashMap rootNS) throws UnsupportedEncodingException, JDOMException, IOException {

//        System.out.println("");
//        System.out.println("=======================================");
//        System.out.println("Imcoming xml1 : " + xml1);
//        System.out.println("");
//        System.out.println("");
//        System.out.println("Imcoming xml2 : " + xml2);
//        System.out.println("=======================================");
        XMLOutputter xmlOutput = new XMLOutputter();
        xmlOutput.setFormat(Format.getCompactFormat());
        SAXBuilder builder = new SAXBuilder();
//            String exampleXML = "<your-xml-string>";
        InputStream stream = new ByteArrayInputStream(xml1.getBytes("UTF-8"));
        InputStream stream2 = new ByteArrayInputStream(xml2.getBytes("UTF-8"));
//            Document anotherDocument = builder.build(stream);
        Document doc = builder.build(stream);
        Document doc2 = builder.build(stream2);
        Element root1 = doc.getRootElement();
        Element root2 = doc2.getRootElement();
        Element AppHdr = root1.getChildren().get(0);
        Element Document = root2.getChildren().get(0);

        String rootName = doc.getRootElement().getName();
        Element newRoot = new Element(rootName);
        newRoot.addContent(AppHdr.clone());
        newRoot.addContent(Document.clone());
        String nsname = null;

        if (!rootNS.isEmpty()) {
            Iterator NS = rootNS.keySet().iterator();
            while (NS.hasNext()) {
//                Namespace.getNamespace(nsname)
                nsname = NS.next().toString();
                Namespace ns = Namespace.getNamespace(nsname, rootNS.get(nsname).toString());
                newRoot.addNamespaceDeclaration(ns);
            }
        }
//                        newRoot.addNamespaceDeclaration();

//        newRoot.
        Document newDoc = new Document(newRoot.clone());

//        newDoc.addContent(((Element)Document.clone()).detach());
//        System.out.println("AppHdr.getChildren() : "+ AppHdr.getChildren());
//        System.out.println("Document.getChildren() : " + Document.getChildren());
//        BusMsg.detach();
//        BusMsg.addContent(AppHdr.getChildren());
//Element root = doc.getRootElement().clone();
//        BusMsg=root1.clone();
//        newRoot.detach();
//        newRoot.addContent(root2.getChild("Document").clone()); // add declaration element to new document
//            newRoot.addContent(root1.getChild("template").clone());
        // directly adding template from document XML1, 
        //after getting template child,
        //it needs to be cloned to detached  from its parent  
//            newRoot.addContent(root2.getChild("template").clone());
        // same for document XML2
        /**
         * * now code yourself for system element here **
         */
//            XMLOutputter outputter = new XMLOutputter();
//        System.out.println("merge : " + newDoc);
//            outputter.output(newDoc, System.out);
//        System.out.println("the xml merge : " + xmlOutput.outputString(newDoc));
//            return xmlOutput.outputString(doc);
        return xmlOutput.outputString(newDoc);

    }

    public static String convertHashmapToXML(HashMap map, String rootelement, HashMap rootNS) {
        XMLOutputter xmlOutput = new XMLOutputter();
        xmlOutput.setFormat(Format.getCompactFormat());
        Element root = new Element(rootelement);
        String nsname = null;
        if (!rootNS.isEmpty()) {
            Iterator NS = rootNS.keySet().iterator();
            while (NS.hasNext()) {
//                Namespace.getNamespace(nsname)
                nsname = NS.next().toString();
                Namespace ns = Namespace.getNamespace(nsname, rootNS.get(nsname).toString());
                root.addNamespaceDeclaration(ns);
            }
        }

//        Namespace ns = Namespace.getNamespace("ns", "urn:iso");
//        Namespace ns1 = Namespace.getNamespace("ns1", "urn:iso:std:iso:20022:tech:xsd:head.001.001.01");
//        Namespace ns2 = Namespace.getNamespace("ns2", "urn:iso:std:iso:20022:tech:xsd:prxy.001.001.01");
//        root.addNamespaceDeclaration(ns);
//        root.addNamespaceDeclaration(ns1);
//        root.addNamespaceDeclaration(ns2);
//        root.addNamespaceDeclaration(new Namespace());
        Document doc = new Document(root);
        Element Node1st, Node2nd, Node3rd, Node4th, Node5th, Node6th, Node7th, Node8th, Node9th, Node10th = null;
        Iterator iter1, iter2, iter3, iter4, iter5, iter6, iter7, iter8, iter9, iter10 = null;
        HashMap sub2, sub3, sub4, sub5, sub6, sub7, sub8, sub9, sub10, sub11 = null;
        String subiterkey1, subiterkey2, subiterkey3, subiterkey4, subiterkey5, subiterkey6, subiterkey7, subiterkey8, subiterkey9, subiterkey10 = null;

        try {
            iter1 = map.keySet().iterator();
            while (iter1.hasNext()) {
                subiterkey1 = iter1.next().toString();
                if (!subiterkey1.equals("ns")) {
                    if (map.containsKey("ns")) {
                        Namespace ns = root.getNamespace(map.get("ns").toString());
                        Node1st = new Element(subiterkey1, ns);
                    } else {
                        Node1st = new Element(subiterkey1);
                    }

                    if (map.get(subiterkey1).toString().substring(0, 1).equals("{")) {
                        sub2 = (HashMap) map.get(subiterkey1);
                        iter2 = sub2.keySet().iterator();
                        while (iter2.hasNext()) {
                            subiterkey2 = iter2.next().toString();
                            if (!subiterkey2.equals("ns")) {

//                                Node2nd = new Element(subiterkey2);
                                if (sub2.containsKey("ns")) {
                                    Namespace ns = root.getNamespace(sub2.get("ns").toString());
                                    Node2nd = new Element(subiterkey2, ns);
                                } else {
                                    Node2nd = new Element(subiterkey2);
                                }

                                if (sub2.get(subiterkey2).toString().substring(0, 1).equals("{")) {
                                    sub3 = (HashMap) sub2.get(subiterkey2);
                                    iter3 = sub3.keySet().iterator();
                                    while (iter3.hasNext()) {
                                        subiterkey3 = iter3.next().toString();
                                        if (!subiterkey3.equals("ns")) {

//                                            Node3rd = new Element(subiterkey3);
                                            if (sub3.containsKey("ns")) {
                                                Namespace ns = root.getNamespace(sub3.get("ns").toString());
                                                Node3rd = new Element(subiterkey3, ns);
                                            } else {
                                                Node3rd = new Element(subiterkey3);
                                            }

                                            if (sub3.get(subiterkey3).toString().substring(0, 1).equals("{")) {
                                                sub4 = (HashMap) sub3.get(subiterkey3);
                                                iter4 = sub4.keySet().iterator();
                                                while (iter4.hasNext()) {
                                                    subiterkey4 = iter4.next().toString();
                                                    if (!subiterkey4.equals("ns")) {

//                                                        Node4th = new Element(subiterkey4);
                                                        if (sub4.containsKey("ns")) {
                                                            Namespace ns = root.getNamespace(sub4.get("ns").toString());
                                                            Node4th = new Element(subiterkey4, ns);
                                                        } else {
                                                            Node4th = new Element(subiterkey4);
                                                        }

                                                        if (sub4.get(subiterkey4).toString().substring(0, 1).equals("{")) {
                                                            sub5 = (HashMap) sub4.get(subiterkey4);
                                                            iter5 = sub5.keySet().iterator();
                                                            while (iter5.hasNext()) {
                                                                subiterkey5 = iter5.next().toString();
                                                                if (!subiterkey5.equals("ns")) {
//                                                                    Node5th = new Element(subiterkey5);
                                                                    if (sub5.containsKey("ns")) {
//                                                                        System.out.println("sub5:"+sub5);
                                                                        Namespace ns = root.getNamespace(sub5.get("ns").toString());
                                                                        Node5th = new Element(subiterkey5, ns);
                                                                    } else {
                                                                        Node5th = new Element(subiterkey5);
                                                                    }
                                                                    if (sub5.get(subiterkey5).toString().substring(0, 1).equals("{")) {
                                                                        sub6 = (HashMap) sub5.get(subiterkey5);
                                                                        iter6 = sub6.keySet().iterator();
                                                                        while (iter6.hasNext()) {
                                                                            subiterkey6 = iter6.next().toString();
                                                                            if (!subiterkey6.equals("ns")) {
                                                                                if (sub6.containsKey("ns")) {
                                                                                    Namespace ns = root.getNamespace(sub6.get("ns").toString());
                                                                                    Node6th = new Element(subiterkey6, ns);
                                                                                } else {
                                                                                    Node6th = new Element(subiterkey6);
                                                                                }
//                                                                                System.out.println("sub6 : "+sub6);
                                                                                if (sub6.get(subiterkey6).toString().substring(0, 1).equals("{")) {
                                                                                    sub7 = (HashMap) sub6.get(subiterkey6);
                                                                                    iter7 = sub7.keySet().iterator();
                                                                                    while (iter7.hasNext()) {
                                                                                        subiterkey7 = iter7.next().toString();
                                                                                        if (!subiterkey7.equals("ns")) {
                                                                                            if (sub7.containsKey("ns")) {
                                                                                                Namespace ns = root.getNamespace(sub7.get("ns").toString());
                                                                                                Node7th = new Element(subiterkey7, ns);
                                                                                            } else {
                                                                                                Node7th = new Element(subiterkey7);
                                                                                            }
                                                                                            if (sub7.get(subiterkey7).toString().substring(0, 1).equals("{")) {
                                                                                                sub8 = (HashMap) sub7.get(subiterkey7);
                                                                                                iter8 = sub8.keySet().iterator();
                                                                                                while (iter8.hasNext()) {
                                                                                                    subiterkey8 = iter8.next().toString();
                                                                                                    if (!subiterkey8.equals("ns")) {
                                                                                                        if (sub8.containsKey("ns")) {
                                                                                                            Namespace ns = root.getNamespace(sub8.get("ns").toString());
                                                                                                            Node8th = new Element(subiterkey8, ns);
                                                                                                        } else {
                                                                                                            Node8th = new Element(subiterkey8);
                                                                                                        }
                                                                                                        if (sub8.get(subiterkey8).toString().substring(0, 1).equals("{")) {
                                                                                                            sub9 = (HashMap) sub8.get(subiterkey8);
                                                                                                            iter9 = sub9.keySet().iterator();
                                                                                                            while (iter9.hasNext()) {
                                                                                                                subiterkey9 = iter9.next().toString();
                                                                                                                if (!subiterkey9.equals("ns")) {
                                                                                                                    if (sub9.containsKey("ns")) {
                                                                                                                        Namespace ns = root.getNamespace(sub9.get("ns").toString());
                                                                                                                        Node9th = new Element(subiterkey9, ns);
                                                                                                                    } else {
                                                                                                                        Node9th = new Element(subiterkey9);

                                                                                                                    }
                                                                                                                    if (sub9.get(subiterkey9).toString().substring(0, 1).equals("{")) {

                                                                                                                    } else {
                                                                                                                        Node9th.setText(sub9.get(subiterkey9).toString());
                                                                                                                        Node8th.addContent(Node9th);//
                                                                                                                    }

                                                                                                                }
                                                                                                            }
                                                                                                            Node7th.addContent(Node8th);//

                                                                                                        } else {
                                                                                                            Node8th.setText(sub8.get(subiterkey8).toString());
                                                                                                            Node7th.addContent(Node8th);//
                                                                                                        }
                                                                                                    }

                                                                                                }
                                                                                                Node6th.addContent(Node7th);//

                                                                                            } else {

                                                                                                Node7th.setText(sub7.get(subiterkey7).toString());
                                                                                                Node6th.addContent(Node7th);//
                                                                                            }
                                                                                        }

//                                                                                        
//
                                                                                    }
                                                                                    Node5th.addContent(Node6th);//

                                                                                } else {

//                                                                                    System.out.println("dasdasadsa : " + sub6.get(subiterkey6).toString());
                                                                                    Node6th.setText(sub6.get(subiterkey6).toString());
                                                                                    Node5th.addContent(Node6th);//
                                                                                }
                                                                            }

                                                                        }

//                                                                        Node5th.setText(sub5.get(subiterkey5).toString());
                                                                        Node4th.addContent(Node5th);//

                                                                        ////
                                                                    } else {
                                                                        Node5th.setText(sub5.get(subiterkey5).toString());
                                                                        Node4th.addContent(Node5th);//
                                                                    }
                                                                }
                                                            }
                                                            Node3rd.addContent(Node4th);//

                                                        } else {
                                                            Node4th.setText(sub4.get(subiterkey4).toString());
                                                            Node3rd.addContent(Node4th);//

                                                        }

                                                    }
                                                }
                                                Node2nd.addContent(Node3rd);

                                            } else {

                                                Node3rd.setText(sub3.get(subiterkey3).toString());
                                                Node2nd.addContent(Node3rd);
                                            }
                                        }
                                    }
                                    Node1st.addContent(Node2nd);

                                } else {
                                    Node2nd.setText(sub2.get(subiterkey2).toString());
                                    Node1st.addContent(Node2nd);
                                }
                            }
                        }
//                    node.setText(map.get(iterkey).toString());

                    } else {
                        Node1st.setText(map.get(subiterkey1).toString());
//                    node.setText(map.get(iterkey).toString());
                    }
                    doc.getRootElement().addContent(Node1st);
//                    doc.getRootElement().getChild("as").
//                doc.getRootElement().getChild("IntrBkSttlmAmt").setAttribute("Ccy", "USD");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            return xmlOutput.outputString(doc);

        }
    }

    public static String convertHashmapToXMLNS2(HashMap map, String rootelement, HashMap rootNS) {
        XMLOutputter xmlOutput = new XMLOutputter();
        xmlOutput.setFormat(Format.getCompactFormat());
        Element root = new Element(rootelement);
        String nsname = null;
        if (!rootNS.isEmpty()) {
            Iterator NS = rootNS.keySet().iterator();
            while (NS.hasNext()) {
//                Namespace.getNamespace(nsname)
                nsname = NS.next().toString();
                Namespace ns = Namespace.getNamespace(nsname, rootNS.get(nsname).toString());
                root.addNamespaceDeclaration(ns);
            }
        }

//        Namespace ns = Namespace.getNamespace("ns", "urn:iso");
//        Namespace ns1 = Namespace.getNamespace("ns1", "urn:iso:std:iso:20022:tech:xsd:head.001.001.01");
//        Namespace ns2 = Namespace.getNamespace("ns2", "urn:iso:std:iso:20022:tech:xsd:prxy.001.001.01");
//        root.addNamespaceDeclaration(ns);
//        root.addNamespaceDeclaration(ns1);
//        root.addNamespaceDeclaration(ns2);
//        root.addNamespaceDeclaration(new Namespace());
        Document doc = new Document(root);
        Element Node1st, Node2nd, Node3rd, Node4th, Node5th, Node6th, Node7th, Node8th, Node9th, Node10th = null;
        Iterator iter1, iter2, iter3, iter4, iter5, iter6, iter7, iter8, iter9, iter10 = null;
        HashMap sub2, sub3, sub4, sub5, sub6, sub7, sub8, sub9, sub10, sub11 = null;
        String subiterkey1, subiterkey2, subiterkey3, subiterkey4, subiterkey5, subiterkey6, subiterkey7, subiterkey8, subiterkey9, subiterkey10 = null;

        try {
            iter1 = map.keySet().iterator();
            while (iter1.hasNext()) {
                subiterkey1 = iter1.next().toString();
                if (!subiterkey1.equals("ns")) {
                    Namespace ns = root.getNamespace("ns");
                    Node1st = new Element(subiterkey1, ns);

                    if (map.get(subiterkey1).toString().substring(0, 1).equals("{")) {
                        sub2 = (HashMap) map.get(subiterkey1);
                        iter2 = sub2.keySet().iterator();
                        while (iter2.hasNext()) {
                            subiterkey2 = iter2.next().toString();
                            if (!subiterkey2.equals("ns")) {
//                                Node2nd = new Element(subiterkey2);
                                ns = root.getNamespace("ns");
                                Node2nd = new Element(subiterkey2, ns);

                                if (sub2.get(subiterkey2).toString().substring(0, 1).equals("{")) {
                                    sub3 = (HashMap) sub2.get(subiterkey2);
                                    iter3 = sub3.keySet().iterator();
                                    while (iter3.hasNext()) {
                                        subiterkey3 = iter3.next().toString();
                                        if (!subiterkey3.equals("ns")) {

//                                            Node3rd = new Element(subiterkey3);
                                            ns = root.getNamespace("ns2");
                                            Node3rd = new Element(subiterkey3, ns);

                                            if (sub3.get(subiterkey3).toString().substring(0, 1).equals("{")) {
                                                sub4 = (HashMap) sub3.get(subiterkey3);
                                                iter4 = sub4.keySet().iterator();
                                                while (iter4.hasNext()) {
                                                    subiterkey4 = iter4.next().toString();
                                                    if (!subiterkey4.equals("ns")) {

//                                                        Node4th = new Element(subiterkey4);
                                                        ns = root.getNamespace("ns2");
                                                        Node4th = new Element(subiterkey4, ns);

                                                        if (sub4.get(subiterkey4).toString().substring(0, 1).equals("{")) {
                                                            sub5 = (HashMap) sub4.get(subiterkey4);
                                                            iter5 = sub5.keySet().iterator();
                                                            while (iter5.hasNext()) {
                                                                subiterkey5 = iter5.next().toString();
                                                                if (!subiterkey5.equals("ns")) {
//                                                                    Node5th = new Element(subiterkey5);
//                                                                        System.out.println("sub5:"+sub5);
                                                                    ns = root.getNamespace("ns2");
                                                                    Node5th = new Element(subiterkey5, ns);
                                                                    if (sub5.get(subiterkey5).toString().substring(0, 1).equals("{")) {
                                                                        sub6 = (HashMap) sub5.get(subiterkey5);
                                                                        iter6 = sub6.keySet().iterator();
                                                                        while (iter6.hasNext()) {
                                                                            subiterkey6 = iter6.next().toString();
                                                                            if (!subiterkey6.equals("ns")) {
                                                                                ns = root.getNamespace("ns2");
                                                                                Node6th = new Element(subiterkey6, ns);
//                                                                                System.out.println("sub6 : "+sub6);
                                                                                if (sub6.get(subiterkey6).toString().substring(0, 1).equals("{")) {
                                                                                    sub7 = (HashMap) sub6.get(subiterkey6);
                                                                                    iter7 = sub7.keySet().iterator();
                                                                                    while (iter7.hasNext()) {
                                                                                        subiterkey7 = iter7.next().toString();
                                                                                        if (!subiterkey7.equals("ns")) {
                                                                                            ns = root.getNamespace("ns2");
                                                                                            Node7th = new Element(subiterkey7, ns);
                                                                                            if (sub7.get(subiterkey7).toString().substring(0, 1).equals("{")) {
                                                                                                sub8 = (HashMap) sub7.get(subiterkey7);
                                                                                                iter8 = sub8.keySet().iterator();
                                                                                                while (iter8.hasNext()) {
                                                                                                    subiterkey8 = iter8.next().toString();
                                                                                                    if (!subiterkey8.equals("ns")) {
                                                                                                        ns = root.getNamespace("ns2");
                                                                                                        Node8th = new Element(subiterkey8, ns);
                                                                                                        if (sub8.get(subiterkey8).toString().substring(0, 1).equals("{")) {
                                                                                                            sub9 = (HashMap) sub8.get(subiterkey8);
                                                                                                            iter9 = sub9.keySet().iterator();
                                                                                                            while (iter9.hasNext()) {
                                                                                                                subiterkey9 = iter9.next().toString();
                                                                                                                if (!subiterkey9.equals("ns")) {
                                                                                                                    ns = root.getNamespace("ns2");
                                                                                                                    Node9th = new Element(subiterkey9, ns);
                                                                                                                    if (sub9.get(subiterkey9).toString().substring(0, 1).equals("{")) {

                                                                                                                    } else {
                                                                                                                        Node9th.setText(sub9.get(subiterkey9).toString());
                                                                                                                        Node8th.addContent(Node9th);//
                                                                                                                    }

                                                                                                                }
                                                                                                            }
                                                                                                            Node7th.addContent(Node8th);//

                                                                                                        } else {
                                                                                                            Node8th.setText(sub8.get(subiterkey8).toString());
                                                                                                            Node7th.addContent(Node8th);//
                                                                                                        }
                                                                                                    }

                                                                                                }
                                                                                                Node6th.addContent(Node7th);//

                                                                                            } else {

                                                                                                Node7th.setText(sub7.get(subiterkey7).toString());
                                                                                                Node6th.addContent(Node7th);//
                                                                                            }
                                                                                        }

//                                                                                        
//
                                                                                    }
                                                                                    Node5th.addContent(Node6th);//

                                                                                } else {

//                                                                                    System.out.println("dasdasadsa : " + sub6.get(subiterkey6).toString());
                                                                                    Node6th.setText(sub6.get(subiterkey6).toString());
                                                                                    Node5th.addContent(Node6th);//
                                                                                }
                                                                            }

                                                                        }

//                                                                        Node5th.setText(sub5.get(subiterkey5).toString());
                                                                        Node4th.addContent(Node5th);//

                                                                        ////
                                                                    } else {
                                                                        Node5th.setText(sub5.get(subiterkey5).toString());
                                                                        Node4th.addContent(Node5th);//
                                                                    }
                                                                }
                                                            }
                                                            Node3rd.addContent(Node4th);//

                                                        } else {
                                                            Node4th.setText(sub4.get(subiterkey4).toString());
                                                            Node3rd.addContent(Node4th);//

                                                        }

                                                    }
                                                }
                                                Node2nd.addContent(Node3rd);

                                            } else {

                                                Node3rd.setText(sub3.get(subiterkey3).toString());
                                                Node2nd.addContent(Node3rd);
                                            }
                                        }
                                    }
                                    Node1st.addContent(Node2nd);

                                } else {
                                    Node2nd.setText(sub2.get(subiterkey2).toString());
                                    Node1st.addContent(Node2nd);
                                }
                            }
                        }
//                    node.setText(map.get(iterkey).toString());

                    } else {
                        Node1st.setText(map.get(subiterkey1).toString());
//                    node.setText(map.get(iterkey).toString());
                    }
                    doc.getRootElement().addContent(Node1st);
//                    doc.getRootElement().getChild("as").
//                doc.getRootElement().getChild("IntrBkSttlmAmt").setAttribute("Ccy", "USD");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            return xmlOutput.outputString(doc);

        }
    }
}
