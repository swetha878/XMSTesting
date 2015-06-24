///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.dialogic.jaxb;
//
//import com.dialogic.clientLibrary.XMSMediaType;
//import com.dialogic.xms.msml.AudioMixType;
//import com.dialogic.xms.msml.BooleanDatatype;
//import com.dialogic.xms.msml.Collect;
//import com.dialogic.xms.msml.DialogLanguageDatatype;
//import com.dialogic.xms.msml.ExitType;
//import com.dialogic.xms.msml.Group;
//import com.dialogic.xms.msml.Msml;
//import com.dialogic.xms.msml.ObjectFactory;
//import com.dialogic.xms.msml.Record;
//import com.dialogic.xms.msml.RootType;
//import com.dialogic.xms.msml.Send;
//import com.dialogic.xms.msml.StreamType;
//import com.dialogic.xms.msml.VideoLayoutType;
//import com.dialogic.xmstesting.Call;
//import java.io.StringWriter;
//import java.math.BigInteger;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import javax.xml.bind.JAXBContext;
//import javax.xml.bind.JAXBException;
//import javax.xml.bind.Marshaller;
//
///**
// *
// * @author ssatyana
// */
//public class Main {
//
//    public static void main(String[] args) {
//        ObjectFactory objectFactory = new ObjectFactory();
//        java.io.StringWriter sw = new StringWriter();
//
//        Msml msml = objectFactory.createMsml();
//        msml.setVersion("1.1");
//
//        Msml.Join join = objectFactory.createMsmlJoin();
//        join.setId2("conf:conf1");
//        join.setId1("conn:1234");
//        join.setMark("2");
//
//        StreamType streamType1 = objectFactory.createStreamType();
//        streamType1.setMedia("audio");
//
//        StreamType streamType2 = objectFactory.createStreamType();
//        streamType2.setMedia("video");
//        streamType2.setDir("from-id1");
//        streamType2.setDisplay("1");
//
//        StreamType streamType3 = objectFactory.createStreamType();
//        streamType3.setMedia("video");
//        streamType3.setDir("to-id1");
//
//        join.getStream().add(streamType1);
//        join.getStream().add(streamType2);
//        join.getStream().add(streamType3);
//
//        msml.getMsmlRequest().add(join);
//
//        try {
//            JAXBContext jaxbContext = JAXBContext.newInstance(Msml.class);
//            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
//            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
//            jaxbMarshaller.marshal(msml, sw);
//
//        } catch (JAXBException ex) {
//            java.util.logging.Logger.getLogger(Call.class
//                    .getName()).log(Level.SEVERE, null, ex);
//        }
//
//        System.out.println("MSML JOIN -> " + sw.toString());
//    }
//}
