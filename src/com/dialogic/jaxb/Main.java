///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.dialogic.jaxb;
//
//import com.dialogic.clientLibrary.XMSMediaType;
//import com.dialogic.xms.msml.BooleanDatatype;
//import com.dialogic.xms.msml.Collect;
//import com.dialogic.xms.msml.DialogLanguageDatatype;
//import com.dialogic.xms.msml.ExitType;
//import com.dialogic.xms.msml.Group;
//import com.dialogic.xms.msml.Msml;
//import com.dialogic.xms.msml.ObjectFactory;
//import com.dialogic.xms.msml.Record;
//import com.dialogic.xms.msml.Send;
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
//        Msml.Dialogstart dialogstart = objectFactory.createMsmlDialogstart();
//        dialogstart.setTarget("conn:1234");
//        dialogstart.setType(DialogLanguageDatatype.APPLICATION_MOML_XML);
//        dialogstart.setName("Record");
//
//        Group group = objectFactory.createGroup();
//        group.setTopology("parallel");
//
//        Record record = objectFactory.createRecord();
//        record.setBeep(BooleanDatatype.TRUE);
//        record.setAudiodest("file://recorded/Test.wav");
//        record.setFormat("audio/wav");
//        record.setMaxtime("10s");
//
//        record.setVideodest("file://recorded/Test.wav");
//        record.setFormat("video/x-vid");
//
//        record.setAudiosamplerate(BigInteger.valueOf(16000));
//        record.setAudiosamplesize(BigInteger.valueOf(16));
//
//        Record.Recordexit recordExit = objectFactory.createRecordRecordexit();
//        ExitType exitType = new ExitType();
//        exitType.setNamelist("record.end record.len");
//        recordExit.setExit(exitType);
//        record.setRecordexit(recordExit);
//
//        group.getPrimitive().add(objectFactory.createRecord(record));
//
//        Collect collect = objectFactory.createCollect();
//        Collect.Pattern termDigPattern = objectFactory.createCollectPattern();
//        termDigPattern.setDigits("#");
//        Send sendDigit = objectFactory.createSend();
//        sendDigit.setTarget("source");
//        sendDigit.setEvent("TermkeyRecieved");
//        sendDigit.setNamelist("dtmf.digits dtmf.len dtmf.last");
//        termDigPattern.getSend().add(sendDigit);
//
//        Send recordTermSend = objectFactory.createSend();
//        recordTermSend.setTarget("record");
//        recordTermSend.setEvent("terminate");
//        termDigPattern.getSend().add(recordTermSend);
//
//        collect.getPattern().add(termDigPattern);
//        group.getPrimitive().add(objectFactory.createCollect(collect));
//
//        dialogstart.getMomlRequest().add(objectFactory.createGroup(group));
//        msml.getMsmlRequest().add(dialogstart);
//
//        try {
//            JAXBContext jaxbContext = JAXBContext.newInstance(Msml.class);
//            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
//            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
//            jaxbMarshaller.marshal(msml, sw);
//
//        } catch (JAXBException ex) {
//            System.out.println("Level.SEVERE, ex.getMessage(), ex");
//        }
//
//        System.out.println("MSML RECORD -> " + sw.toString());
//    }
//}
