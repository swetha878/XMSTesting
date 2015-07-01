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
//import com.dialogic.xms.msml.IterateSendType;
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
//        Msml.Dialogstart dialogstart = objectFactory.createMsmlDialogstart();
//        dialogstart.setTarget("conn:1234");
//        dialogstart.setType(DialogLanguageDatatype.APPLICATION_MOML_XML);
//        dialogstart.setName("Collect");
//
//        Collect collect = objectFactory.createCollect();
//        collect.setFdt("10s");
//
//        collect.setIdt("2s");
//        collect.setStarttimer(BooleanDatatype.TRUE);
//        collect.setCleardb(BooleanDatatype.TRUE);
//        Collect.Pattern termDigPattern = objectFactory.createCollectPattern();
//        termDigPattern.setDigits("#");
//
//        Send sendDigit = objectFactory.createSend();
//        sendDigit.setTarget("source");
//        sendDigit.setEvent("termKey");
//        sendDigit.setNamelist("dtmf.digits dtmf.len dtmf.end");
//        termDigPattern.getSend().add(sendDigit);
//        collect.getPattern().add(termDigPattern);
//
//        Collect.Pattern digitsPattern = objectFactory.createCollectPattern();
//        digitsPattern.setDigits("xxxxx");
//        collect.getPattern().add(digitsPattern);
//
//        IterateSendType noinput = objectFactory.createIterateSendType();
//        Send sendNoInput = objectFactory.createSend();
//        sendNoInput.setTarget("source");
//        sendNoInput.setEvent("noinput");
//        sendNoInput.setNamelist("dtmf.digits dtmf.len dtmf.end");
//        noinput.getSend().add(sendNoInput);
//        collect.setNoinput(noinput);
//
//        IterateSendType nomatch = objectFactory.createIterateSendType();
//        Send sendNoMatch = objectFactory.createSend();
//        sendNoMatch.setTarget("source");
//        sendNoMatch.setEvent("nomatch");
//        sendNoMatch.setNamelist("dtmf.digits dtmf.len dtmf.end");
//        nomatch.getSend().add(sendNoInput);
//        collect.setNomatch(nomatch);
//
//        Collect.Dtmfexit dtmfexit = objectFactory.createCollectDtmfexit();
//        Send sendDtmf = objectFactory.createSend();
//        sendNoMatch.setTarget("source");
//        sendNoMatch.setEvent("dtmfexit");
//        sendNoMatch.setNamelist("dtmf.digits dtmf.len dtmf.end");
//        dtmfexit.getSend().add(sendDtmf);
//        collect.setDtmfexit(dtmfexit);
//
//        dialogstart.getMomlRequest().add(objectFactory.createCollect(collect));
//        msml.getMsmlRequest().add(dialogstart);
//        try {
//            JAXBContext jaxbContext = JAXBContext.newInstance(Msml.class);
//            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
//            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
//            jaxbMarshaller.marshal(msml, sw);
//
//        } catch (JAXBException ex) {
//            java.util.logging.Logger.getLogger(Call.class.getName())
//                    .log(Level.SEVERE, ex.getMessage(), ex);
//        }
//        System.out.println("MSML COLLECT DIGITS -> " + sw.toString());
//    }
//}
