//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.06.26 at 03:30:37 PM EDT 
//


package com.dialogic.xms.msml;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.dialogic.xms.msml package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Play_QNAME = new QName("", "play");
    private final static QName _Primitive_QNAME = new QName("", "primitive");
    private final static QName _Dtmf_QNAME = new QName("", "dtmf");
    private final static QName _Var_QNAME = new QName("", "var");
    private final static QName _Control_QNAME = new QName("", "control");
    private final static QName _Faxdetect_QNAME = new QName("", "faxdetect");
    private final static QName _Gain_QNAME = new QName("", "gain");
    private final static QName _Transfer_QNAME = new QName("", "transfer");
    private final static QName _Fileop_QNAME = new QName("", "fileop");
    private final static QName _Dtmfgen_QNAME = new QName("", "dtmfgen");
    private final static QName _Record_QNAME = new QName("", "record");
    private final static QName _Agc_QNAME = new QName("", "agc");
    private final static QName _Smedia_QNAME = new QName("", "smedia");
    private final static QName _Collect_QNAME = new QName("", "collect");
    private final static QName _Group_QNAME = new QName("", "group");
    private final static QName _MomlExit_QNAME = new QName("", "exit");
    private final static QName _MomlDisconnect_QNAME = new QName("", "disconnect");
    private final static QName _PlayAudio_QNAME = new QName("", "audio");
    private final static QName _PlayVideo_QNAME = new QName("", "video");
    private final static QName _PlayMedia_QNAME = new QName("", "media");
    private final static QName _MsmlEventName_QNAME = new QName("", "name");
    private final static QName _MsmlEventValue_QNAME = new QName("", "value");
    private final static QName _MsmlResultConfid_QNAME = new QName("", "confid");
    private final static QName _MsmlResultDialogid_QNAME = new QName("", "dialogid");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.dialogic.xms.msml
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Msml }
     * 
     */
    public Msml createMsml() {
        return new Msml();
    }

    /**
     * Create an instance of {@link AudioMixType }
     * 
     */
    public AudioMixType createAudioMixType() {
        return new AudioMixType();
    }

    /**
     * Create an instance of {@link VideoLayoutType }
     * 
     */
    public VideoLayoutType createVideoLayoutType() {
        return new VideoLayoutType();
    }

    /**
     * Create an instance of {@link VideoLayoutType.Selector }
     * 
     */
    public VideoLayoutType.Selector createVideoLayoutTypeSelector() {
        return new VideoLayoutType.Selector();
    }

    /**
     * Create an instance of {@link StreamType }
     * 
     */
    public StreamType createStreamType() {
        return new StreamType();
    }

    /**
     * Create an instance of {@link Group }
     * 
     */
    public Group createGroup() {
        return new Group();
    }

    /**
     * Create an instance of {@link Collect }
     * 
     */
    public Collect createCollect() {
        return new Collect();
    }

    /**
     * Create an instance of {@link Record }
     * 
     */
    public Record createRecord() {
        return new Record();
    }

    /**
     * Create an instance of {@link Dtmfgen }
     * 
     */
    public Dtmfgen createDtmfgen() {
        return new Dtmfgen();
    }

    /**
     * Create an instance of {@link Transfer }
     * 
     */
    public Transfer createTransfer() {
        return new Transfer();
    }

    /**
     * Create an instance of {@link Transfer.Messageobj }
     * 
     */
    public Transfer.Messageobj createTransferMessageobj() {
        return new Transfer.Messageobj();
    }

    /**
     * Create an instance of {@link Faxdetect }
     * 
     */
    public Faxdetect createFaxdetect() {
        return new Faxdetect();
    }

    /**
     * Create an instance of {@link Dtmf }
     * 
     */
    public Dtmf createDtmf() {
        return new Dtmf();
    }

    /**
     * Create an instance of {@link Play }
     * 
     */
    public Play createPlay() {
        return new Play();
    }

    /**
     * Create an instance of {@link Play.Media }
     * 
     */
    public Play.Media createPlayMedia() {
        return new Play.Media();
    }

    /**
     * Create an instance of {@link PrimitiveType }
     * 
     */
    public PrimitiveType createPrimitiveType() {
        return new PrimitiveType();
    }

    /**
     * Create an instance of {@link Var }
     * 
     */
    public Var createVar() {
        return new Var();
    }

    /**
     * Create an instance of {@link SmediaType }
     * 
     */
    public SmediaType createSmediaType() {
        return new SmediaType();
    }

    /**
     * Create an instance of {@link com.dialogic.xms.msml.Gain }
     * 
     */
    public com.dialogic.xms.msml.Gain createGain() {
        return new com.dialogic.xms.msml.Gain();
    }

    /**
     * Create an instance of {@link Moml }
     * 
     */
    public Moml createMoml() {
        return new Moml();
    }

    /**
     * Create an instance of {@link com.dialogic.xms.msml.Send }
     * 
     */
    public com.dialogic.xms.msml.Send createSend() {
        return new com.dialogic.xms.msml.Send();
    }

    /**
     * Create an instance of {@link ExitType }
     * 
     */
    public ExitType createExitType() {
        return new ExitType();
    }

    /**
     * Create an instance of {@link com.dialogic.xms.msml.Event }
     * 
     */
    public com.dialogic.xms.msml.Event createEvent() {
        return new com.dialogic.xms.msml.Event();
    }

    /**
     * Create an instance of {@link Fileop }
     * 
     */
    public Fileop createFileop() {
        return new Fileop();
    }

    /**
     * Create an instance of {@link Msml.Createconference }
     * 
     */
    public Msml.Createconference createMsmlCreateconference() {
        return new Msml.Createconference();
    }

    /**
     * Create an instance of {@link Msml.Modifyconference }
     * 
     */
    public Msml.Modifyconference createMsmlModifyconference() {
        return new Msml.Modifyconference();
    }

    /**
     * Create an instance of {@link Msml.Destroyconference }
     * 
     */
    public Msml.Destroyconference createMsmlDestroyconference() {
        return new Msml.Destroyconference();
    }

    /**
     * Create an instance of {@link Msml.Dialogstart }
     * 
     */
    public Msml.Dialogstart createMsmlDialogstart() {
        return new Msml.Dialogstart();
    }

    /**
     * Create an instance of {@link Msml.Dialogend }
     * 
     */
    public Msml.Dialogend createMsmlDialogend() {
        return new Msml.Dialogend();
    }

    /**
     * Create an instance of {@link Msml.Join }
     * 
     */
    public Msml.Join createMsmlJoin() {
        return new Msml.Join();
    }

    /**
     * Create an instance of {@link Msml.Modifystream }
     * 
     */
    public Msml.Modifystream createMsmlModifystream() {
        return new Msml.Modifystream();
    }

    /**
     * Create an instance of {@link Msml.Unjoin }
     * 
     */
    public Msml.Unjoin createMsmlUnjoin() {
        return new Msml.Unjoin();
    }

    /**
     * Create an instance of {@link Msml.Send }
     * 
     */
    public Msml.Send createMsmlSend() {
        return new Msml.Send();
    }

    /**
     * Create an instance of {@link Msml.Audit }
     * 
     */
    public Msml.Audit createMsmlAudit() {
        return new Msml.Audit();
    }

    /**
     * Create an instance of {@link Msml.Event }
     * 
     */
    public Msml.Event createMsmlEvent() {
        return new Msml.Event();
    }

    /**
     * Create an instance of {@link Msml.Result }
     * 
     */
    public Msml.Result createMsmlResult() {
        return new Msml.Result();
    }

    /**
     * Create an instance of {@link Agc }
     * 
     */
    public Agc createAgc() {
        return new Agc();
    }

    /**
     * Create an instance of {@link IterateSendType }
     * 
     */
    public IterateSendType createIterateSendType() {
        return new IterateSendType();
    }

    /**
     * Create an instance of {@link RegionType }
     * 
     */
    public RegionType createRegionType() {
        return new RegionType();
    }

    /**
     * Create an instance of {@link BasicStreamType }
     * 
     */
    public BasicStreamType createBasicStreamType() {
        return new BasicStreamType();
    }

    /**
     * Create an instance of {@link RootType }
     * 
     */
    public RootType createRootType() {
        return new RootType();
    }

    /**
     * Create an instance of {@link BasicAudioMixType }
     * 
     */
    public BasicAudioMixType createBasicAudioMixType() {
        return new BasicAudioMixType();
    }

    /**
     * Create an instance of {@link BasicVideoLayoutType }
     * 
     */
    public BasicVideoLayoutType createBasicVideoLayoutType() {
        return new BasicVideoLayoutType();
    }

    /**
     * Create an instance of {@link SelectorType }
     * 
     */
    public SelectorType createSelectorType() {
        return new SelectorType();
    }

    /**
     * Create an instance of {@link AudioMixType.Asn }
     * 
     */
    public AudioMixType.Asn createAudioMixTypeAsn() {
        return new AudioMixType.Asn();
    }

    /**
     * Create an instance of {@link AudioMixType.NLoudest }
     * 
     */
    public AudioMixType.NLoudest createAudioMixTypeNLoudest() {
        return new AudioMixType.NLoudest();
    }

    /**
     * Create an instance of {@link VideoLayoutType.Region }
     * 
     */
    public VideoLayoutType.Region createVideoLayoutTypeRegion() {
        return new VideoLayoutType.Region();
    }

    /**
     * Create an instance of {@link VideoLayoutType.Selector.Region }
     * 
     */
    public VideoLayoutType.Selector.Region createVideoLayoutTypeSelectorRegion() {
        return new VideoLayoutType.Selector.Region();
    }

    /**
     * Create an instance of {@link StreamType.Gain }
     * 
     */
    public StreamType.Gain createStreamTypeGain() {
        return new StreamType.Gain();
    }

    /**
     * Create an instance of {@link StreamType.Clamp }
     * 
     */
    public StreamType.Clamp createStreamTypeClamp() {
        return new StreamType.Clamp();
    }

    /**
     * Create an instance of {@link Group.Groupexit }
     * 
     */
    public Group.Groupexit createGroupGroupexit() {
        return new Group.Groupexit();
    }

    /**
     * Create an instance of {@link Collect.Pattern }
     * 
     */
    public Collect.Pattern createCollectPattern() {
        return new Collect.Pattern();
    }

    /**
     * Create an instance of {@link Collect.Detect }
     * 
     */
    public Collect.Detect createCollectDetect() {
        return new Collect.Detect();
    }

    /**
     * Create an instance of {@link Collect.Dtmfexit }
     * 
     */
    public Collect.Dtmfexit createCollectDtmfexit() {
        return new Collect.Dtmfexit();
    }

    /**
     * Create an instance of {@link Collect.Send }
     * 
     */
    public Collect.Send createCollectSend() {
        return new Collect.Send();
    }

    /**
     * Create an instance of {@link Record.Recordexit }
     * 
     */
    public Record.Recordexit createRecordRecordexit() {
        return new Record.Recordexit();
    }

    /**
     * Create an instance of {@link Record.Send }
     * 
     */
    public Record.Send createRecordSend() {
        return new Record.Send();
    }

    /**
     * Create an instance of {@link Dtmfgen.Dtmfgenexit }
     * 
     */
    public Dtmfgen.Dtmfgenexit createDtmfgenDtmfgenexit() {
        return new Dtmfgen.Dtmfgenexit();
    }

    /**
     * Create an instance of {@link Dtmfgen.Send }
     * 
     */
    public Dtmfgen.Send createDtmfgenSend() {
        return new Dtmfgen.Send();
    }

    /**
     * Create an instance of {@link Transfer.Fileobj }
     * 
     */
    public Transfer.Fileobj createTransferFileobj() {
        return new Transfer.Fileobj();
    }

    /**
     * Create an instance of {@link Transfer.Transferobjdone }
     * 
     */
    public Transfer.Transferobjdone createTransferTransferobjdone() {
        return new Transfer.Transferobjdone();
    }

    /**
     * Create an instance of {@link Transfer.Transferexit }
     * 
     */
    public Transfer.Transferexit createTransferTransferexit() {
        return new Transfer.Transferexit();
    }

    /**
     * Create an instance of {@link Transfer.Messageobj.Content }
     * 
     */
    public Transfer.Messageobj.Content createTransferMessageobjContent() {
        return new Transfer.Messageobj.Content();
    }

    /**
     * Create an instance of {@link Faxdetect.Faxdetectexit }
     * 
     */
    public Faxdetect.Faxdetectexit createFaxdetectFaxdetectexit() {
        return new Faxdetect.Faxdetectexit();
    }

    /**
     * Create an instance of {@link Dtmf.Pattern }
     * 
     */
    public Dtmf.Pattern createDtmfPattern() {
        return new Dtmf.Pattern();
    }

    /**
     * Create an instance of {@link Dtmf.Detect }
     * 
     */
    public Dtmf.Detect createDtmfDetect() {
        return new Dtmf.Detect();
    }

    /**
     * Create an instance of {@link Dtmf.Dtmfexit }
     * 
     */
    public Dtmf.Dtmfexit createDtmfDtmfexit() {
        return new Dtmf.Dtmfexit();
    }

    /**
     * Create an instance of {@link Dtmf.Send }
     * 
     */
    public Dtmf.Send createDtmfSend() {
        return new Dtmf.Send();
    }

    /**
     * Create an instance of {@link Play.Audio }
     * 
     */
    public Play.Audio createPlayAudio() {
        return new Play.Audio();
    }

    /**
     * Create an instance of {@link Play.Video }
     * 
     */
    public Play.Video createPlayVideo() {
        return new Play.Video();
    }

    /**
     * Create an instance of {@link Play.Playexit }
     * 
     */
    public Play.Playexit createPlayPlayexit() {
        return new Play.Playexit();
    }

    /**
     * Create an instance of {@link Play.Send }
     * 
     */
    public Play.Send createPlaySend() {
        return new Play.Send();
    }

    /**
     * Create an instance of {@link Play.Media.Audio }
     * 
     */
    public Play.Media.Audio createPlayMediaAudio() {
        return new Play.Media.Audio();
    }

    /**
     * Create an instance of {@link Play.Media.Video }
     * 
     */
    public Play.Media.Video createPlayMediaVideo() {
        return new Play.Media.Video();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Play }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "play", substitutionHeadNamespace = "", substitutionHeadName = "primitive")
    public JAXBElement<Play> createPlay(Play value) {
        return new JAXBElement<Play>(_Play_QNAME, Play.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PrimitiveType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "primitive")
    public JAXBElement<PrimitiveType> createPrimitive(PrimitiveType value) {
        return new JAXBElement<PrimitiveType>(_Primitive_QNAME, PrimitiveType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Dtmf }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "dtmf", substitutionHeadNamespace = "", substitutionHeadName = "primitive")
    public JAXBElement<Dtmf> createDtmf(Dtmf value) {
        return new JAXBElement<Dtmf>(_Dtmf_QNAME, Dtmf.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Var }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "var", substitutionHeadNamespace = "", substitutionHeadName = "smedia")
    public JAXBElement<Var> createVar(Var value) {
        return new JAXBElement<Var>(_Var_QNAME, Var.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "control")
    public JAXBElement<Object> createControl(Object value) {
        return new JAXBElement<Object>(_Control_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Faxdetect }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "faxdetect", substitutionHeadNamespace = "", substitutionHeadName = "primitive")
    public JAXBElement<Faxdetect> createFaxdetect(Faxdetect value) {
        return new JAXBElement<Faxdetect>(_Faxdetect_QNAME, Faxdetect.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link com.dialogic.xms.msml.Gain }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "gain", substitutionHeadNamespace = "", substitutionHeadName = "primitive")
    public JAXBElement<com.dialogic.xms.msml.Gain> createGain(com.dialogic.xms.msml.Gain value) {
        return new JAXBElement<com.dialogic.xms.msml.Gain>(_Gain_QNAME, com.dialogic.xms.msml.Gain.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Transfer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "transfer", substitutionHeadNamespace = "", substitutionHeadName = "primitive")
    public JAXBElement<Transfer> createTransfer(Transfer value) {
        return new JAXBElement<Transfer>(_Transfer_QNAME, Transfer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Fileop }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "fileop", substitutionHeadNamespace = "", substitutionHeadName = "primitive")
    public JAXBElement<Fileop> createFileop(Fileop value) {
        return new JAXBElement<Fileop>(_Fileop_QNAME, Fileop.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Dtmfgen }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "dtmfgen", substitutionHeadNamespace = "", substitutionHeadName = "primitive")
    public JAXBElement<Dtmfgen> createDtmfgen(Dtmfgen value) {
        return new JAXBElement<Dtmfgen>(_Dtmfgen_QNAME, Dtmfgen.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Record }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "record", substitutionHeadNamespace = "", substitutionHeadName = "primitive")
    public JAXBElement<Record> createRecord(Record value) {
        return new JAXBElement<Record>(_Record_QNAME, Record.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Agc }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "agc", substitutionHeadNamespace = "", substitutionHeadName = "primitive")
    public JAXBElement<Agc> createAgc(Agc value) {
        return new JAXBElement<Agc>(_Agc_QNAME, Agc.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SmediaType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "smedia")
    public JAXBElement<SmediaType> createSmedia(SmediaType value) {
        return new JAXBElement<SmediaType>(_Smedia_QNAME, SmediaType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Collect }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "collect", substitutionHeadNamespace = "", substitutionHeadName = "primitive")
    public JAXBElement<Collect> createCollect(Collect value) {
        return new JAXBElement<Collect>(_Collect_QNAME, Collect.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Group }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "group", substitutionHeadNamespace = "", substitutionHeadName = "control")
    public JAXBElement<Group> createGroup(Group value) {
        return new JAXBElement<Group>(_Group_QNAME, Group.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ExitType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "exit", scope = Moml.class)
    public JAXBElement<ExitType> createMomlExit(ExitType value) {
        return new JAXBElement<ExitType>(_MomlExit_QNAME, ExitType.class, Moml.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ExitType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "disconnect", scope = Moml.class)
    public JAXBElement<ExitType> createMomlDisconnect(ExitType value) {
        return new JAXBElement<ExitType>(_MomlDisconnect_QNAME, ExitType.class, Moml.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Play.Audio }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "audio", scope = Play.class)
    public JAXBElement<Play.Audio> createPlayAudio(Play.Audio value) {
        return new JAXBElement<Play.Audio>(_PlayAudio_QNAME, Play.Audio.class, Play.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Play.Video }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "video", scope = Play.class)
    public JAXBElement<Play.Video> createPlayVideo(Play.Video value) {
        return new JAXBElement<Play.Video>(_PlayVideo_QNAME, Play.Video.class, Play.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Play.Media }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "media", scope = Play.class)
    public JAXBElement<Play.Media> createPlayMedia(Play.Media value) {
        return new JAXBElement<Play.Media>(_PlayMedia_QNAME, Play.Media.class, Play.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ExitType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "exit", scope = Msml.Dialogstart.class)
    public JAXBElement<ExitType> createMsmlDialogstartExit(ExitType value) {
        return new JAXBElement<ExitType>(_MomlExit_QNAME, ExitType.class, Msml.Dialogstart.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ExitType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "disconnect", scope = Msml.Dialogstart.class)
    public JAXBElement<ExitType> createMsmlDialogstartDisconnect(ExitType value) {
        return new JAXBElement<ExitType>(_MomlDisconnect_QNAME, ExitType.class, Msml.Dialogstart.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "name", scope = Msml.Event.class)
    public JAXBElement<String> createMsmlEventName(String value) {
        return new JAXBElement<String>(_MsmlEventName_QNAME, String.class, Msml.Event.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "value", scope = Msml.Event.class)
    public JAXBElement<String> createMsmlEventValue(String value) {
        return new JAXBElement<String>(_MsmlEventValue_QNAME, String.class, Msml.Event.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "name", scope = com.dialogic.xms.msml.Event.class)
    public JAXBElement<String> createEventName(String value) {
        return new JAXBElement<String>(_MsmlEventName_QNAME, String.class, com.dialogic.xms.msml.Event.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "value", scope = com.dialogic.xms.msml.Event.class)
    public JAXBElement<String> createEventValue(String value) {
        return new JAXBElement<String>(_MsmlEventValue_QNAME, String.class, com.dialogic.xms.msml.Event.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "confid", scope = Msml.Result.class)
    public JAXBElement<String> createMsmlResultConfid(String value) {
        return new JAXBElement<String>(_MsmlResultConfid_QNAME, String.class, Msml.Result.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "dialogid", scope = Msml.Result.class)
    public JAXBElement<String> createMsmlResultDialogid(String value) {
        return new JAXBElement<String>(_MsmlResultDialogid_QNAME, String.class, Msml.Result.class, value);
    }

}
