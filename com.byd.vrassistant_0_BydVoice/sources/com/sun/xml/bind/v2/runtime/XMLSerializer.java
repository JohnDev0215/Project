package com.sun.xml.bind.v2.runtime;

import com.sun.istack.SAXException2;
import com.sun.xml.bind.CycleRecoverable;
import com.sun.xml.bind.marshaller.NamespacePrefixMapper;
import com.sun.xml.bind.util.ValidationEventLocatorExImpl;
import com.sun.xml.bind.v2.WellKnownNamespace;
import com.sun.xml.bind.v2.runtime.output.MTOMXmlOutput;
import com.sun.xml.bind.v2.runtime.output.NamespaceContextImpl;
import com.sun.xml.bind.v2.runtime.output.Pcdata;
import com.sun.xml.bind.v2.runtime.output.XmlOutput;
import com.sun.xml.bind.v2.runtime.property.Property;
import com.sun.xml.bind.v2.runtime.unmarshaller.Base64Data;
import com.sun.xml.bind.v2.runtime.unmarshaller.IntData;
import com.sun.xml.bind.v2.util.CollisionCheckStack;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import javax.activation.MimeType;
import javax.xml.bind.DatatypeConverter;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.ValidationEvent;
import javax.xml.bind.ValidationEventLocator;
import javax.xml.bind.annotation.DomHandler;
import javax.xml.bind.annotation.XmlNs;
import javax.xml.bind.attachment.AttachmentMarshaller;
import javax.xml.bind.helpers.NotIdentifiableEventImpl;
import javax.xml.bind.helpers.ValidationEventImpl;
import javax.xml.bind.helpers.ValidationEventLocatorImpl;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamException;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.sax.SAXResult;
import org.xml.sax.SAXException;

/* JADX INFO: loaded from: classes3.dex */
public final class XMLSerializer extends Coordinator {
    public AttachmentMarshaller attachmentMarshaller;
    private Base64Data base64Data;
    private ContentHandlerAdaptor contentHandlerAdapter;
    private MimeType expectedMimeType;
    private boolean fragment;
    public final JAXBContextImpl grammar;
    private Transformer identityTransformer;
    private boolean inlineBinaryFlag;
    public final int[] knownUri2prefixIndexMap;
    private final MarshallerImpl marshaller;
    public final NameList nameList;
    private String noNsSchemaLocation;
    private NamespaceContextImpl.Element nse;
    private XmlOutput out;
    private String schemaLocation;
    private QName schemaType;
    ThreadLocal<Property> currentProperty = new ThreadLocal<>();
    private boolean textHasAlreadyPrinted = false;
    private boolean seenRoot = false;
    private final Set<Object> idReferencedObjects = new HashSet();
    private final Set<Object> objectsWithId = new HashSet();
    private final CollisionCheckStack<Object> cycleDetectionStack = new CollisionCheckStack<>();
    private final IntData intData = new IntData();
    private final NamespaceContextImpl nsContext = new NamespaceContextImpl(this);

    XMLSerializer(MarshallerImpl marshallerImpl) {
        this.marshaller = marshallerImpl;
        this.grammar = marshallerImpl.context;
        NameList nameList = marshallerImpl.context.nameList;
        this.nameList = nameList;
        this.knownUri2prefixIndexMap = new int[nameList.namespaceURIs.length];
    }

    public Base64Data getCachedBase64DataInstance() {
        return new Base64Data();
    }

    private String getIdFromObject(Object obj) throws SAXException, JAXBException {
        return this.grammar.getBeanInfo(obj, true).getId(obj, this);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    private void handleMissingObjectError(String str) throws SAXException, XMLStreamException, IOException {
        reportMissingObjectError(str);
        endNamespaceDecls(null);
        endAttributes();
    }

    public void reportError(ValidationEvent validationEvent) throws SAXException {
        try {
            if (this.marshaller.getEventHandler().handleEvent(validationEvent)) {
                return;
            }
            if (validationEvent.getLinkedException() instanceof Exception) {
                throw new SAXException2((Exception) validationEvent.getLinkedException());
            }
            throw new SAXException2(validationEvent.getMessage());
        } catch (JAXBException e) {
            throw new SAXException2(e);
        }
    }

    public final void reportError(String str, Throwable th) throws SAXException {
        reportError(new ValidationEventImpl(1, th.getMessage(), getCurrentLocation(str), th));
    }

    public void startElement(Name name, Object obj) {
        startElement();
        this.nse.setTagName(name, obj);
    }

    public void startElement(String str, String str2, String str3, Object obj) {
        startElement();
        this.nse.setTagName(this.nsContext.declareNsUri(str, str3, false), str2, obj);
    }

    public void startElementForce(String str, String str2, String str3, Object obj) {
        startElement();
        this.nse.setTagName(this.nsContext.force(str, str3), str2, obj);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    public void endNamespaceDecls(Object obj) throws XMLStreamException, IOException {
        this.nsContext.collectionMode = false;
        this.nse.startElement(this.out, obj);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    public void endAttributes() throws XMLStreamException, SAXException, IOException {
        if (!this.seenRoot) {
            this.seenRoot = true;
            if (this.schemaLocation != null || this.noNsSchemaLocation != null) {
                int prefixIndex = this.nsContext.getPrefixIndex(WellKnownNamespace.XML_SCHEMA_INSTANCE);
                String str = this.schemaLocation;
                if (str != null) {
                    this.out.attribute(prefixIndex, "schemaLocation", str);
                }
                String str2 = this.noNsSchemaLocation;
                if (str2 != null) {
                    this.out.attribute(prefixIndex, "noNamespaceSchemaLocation", str2);
                }
            }
        }
        this.out.endStartTag();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    public void endElement() throws SAXException, XMLStreamException, IOException {
        this.nse.endElement(this.out);
        this.nse = this.nse.pop();
        this.textHasAlreadyPrinted = false;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    public void leafElement(Name name, String str, String str2) throws XMLStreamException, SAXException, IOException {
        if (this.seenRoot) {
            this.textHasAlreadyPrinted = false;
            this.nse = this.nse.push();
            this.out.beginStartTag(name);
            this.out.endStartTag();
            if (str != null) {
                try {
                    this.out.text(str, false);
                } catch (IllegalArgumentException e) {
                    throw new IllegalArgumentException(Messages.ILLEGAL_CONTENT.format(str2, e.getMessage()));
                }
            }
            this.out.endTag(name);
            this.nse = this.nse.pop();
            return;
        }
        startElement(name, null);
        endNamespaceDecls(null);
        endAttributes();
        try {
            this.out.text(str, false);
            endElement();
        } catch (IllegalArgumentException e2) {
            throw new IllegalArgumentException(Messages.ILLEGAL_CONTENT.format(str2, e2.getMessage()));
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    public void leafElement(Name name, Pcdata pcdata, String str) throws XMLStreamException, SAXException, IOException {
        if (this.seenRoot) {
            this.textHasAlreadyPrinted = false;
            this.nse = this.nse.push();
            this.out.beginStartTag(name);
            this.out.endStartTag();
            if (pcdata != null) {
                this.out.text(pcdata, false);
            }
            this.out.endTag(name);
            this.nse = this.nse.pop();
            return;
        }
        startElement(name, null);
        endNamespaceDecls(null);
        endAttributes();
        this.out.text(pcdata, false);
        endElement();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    public void leafElement(Name name, int i, String str) throws XMLStreamException, SAXException, IOException {
        this.intData.reset(i);
        leafElement(name, this.intData, str);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    public void text(String str, String str2) throws SAXException, XMLStreamException, IOException {
        if (str == null) {
            reportMissingObjectError(str2);
        } else {
            this.out.text(str, this.textHasAlreadyPrinted);
            this.textHasAlreadyPrinted = true;
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    public void text(Pcdata pcdata, String str) throws SAXException, XMLStreamException, IOException {
        if (pcdata == null) {
            reportMissingObjectError(str);
        } else {
            this.out.text(pcdata, this.textHasAlreadyPrinted);
            this.textHasAlreadyPrinted = true;
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    public void attribute(String str, String str2, String str3) throws SAXException {
        try {
            this.out.attribute(str.length() == 0 ? -1 : this.nsContext.getPrefixIndex(str), str2, str3);
        } catch (XMLStreamException e) {
            throw new SAXException2((Exception) e);
        } catch (IOException e2) {
            throw new SAXException2(e2);
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    public void attribute(Name name, CharSequence charSequence) throws XMLStreamException, IOException {
        this.out.attribute(name, charSequence.toString());
    }

    public NamespaceContext2 getNamespaceContext() {
        return this.nsContext;
    }

    public String onID(Object obj, String str) {
        this.objectsWithId.add(obj);
        return str;
    }

    public String onIDREF(Object obj) throws SAXException {
        try {
            String idFromObject = getIdFromObject(obj);
            this.idReferencedObjects.add(obj);
            if (idFromObject == null) {
                reportError(new NotIdentifiableEventImpl(1, Messages.NOT_IDENTIFIABLE.format(new Object[0]), new ValidationEventLocatorImpl(obj)));
            }
            return idFromObject;
        } catch (JAXBException e) {
            reportError(null, e);
            return null;
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    public void childAsRoot(Object obj) throws SAXException, XMLStreamException, IOException, JAXBException {
        JaxBeanInfo beanInfo = this.grammar.getBeanInfo(obj, true);
        this.cycleDetectionStack.pushNocheck(obj);
        boolean zLookForLifecycleMethods = beanInfo.lookForLifecycleMethods();
        if (zLookForLifecycleMethods) {
            fireBeforeMarshalEvents(beanInfo, obj);
        }
        beanInfo.serializeRoot(obj, this);
        if (zLookForLifecycleMethods) {
            fireAfterMarshalEvents(beanInfo, obj);
        }
        this.cycleDetectionStack.pop();
    }

    private Object pushObject(Object obj, String str) throws SAXException {
        if (!this.cycleDetectionStack.push(obj)) {
            return obj;
        }
        if (obj instanceof CycleRecoverable) {
            Object objOnCycleDetected = ((CycleRecoverable) obj).onCycleDetected(new CycleRecoverable.Context() { // from class: com.sun.xml.bind.v2.runtime.XMLSerializer.1
                @Override // com.sun.xml.bind.CycleRecoverable.Context
                public Marshaller getMarshaller() {
                    return XMLSerializer.this.marshaller;
                }
            });
            if (objOnCycleDetected == null) {
                return null;
            }
            this.cycleDetectionStack.pop();
            return pushObject(objOnCycleDetected, str);
        }
        reportError(new ValidationEventImpl(1, Messages.CYCLE_IN_MARSHALLER.format(this.cycleDetectionStack.getCycleString()), getCurrentLocation(str), null));
        return null;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    public final void childAsSoleContent(Object obj, String str) throws SAXException, XMLStreamException, IOException {
        if (obj == null) {
            handleMissingObjectError(str);
            return;
        }
        Object objPushObject = pushObject(obj, str);
        if (objPushObject == null) {
            endNamespaceDecls(null);
            endAttributes();
            this.cycleDetectionStack.pop();
        }
        try {
            JaxBeanInfo beanInfo = this.grammar.getBeanInfo(objPushObject, true);
            boolean zLookForLifecycleMethods = beanInfo.lookForLifecycleMethods();
            if (zLookForLifecycleMethods) {
                fireBeforeMarshalEvents(beanInfo, objPushObject);
            }
            beanInfo.serializeURIs(objPushObject, this);
            endNamespaceDecls(objPushObject);
            beanInfo.serializeAttributes(objPushObject, this);
            endAttributes();
            beanInfo.serializeBody(objPushObject, this);
            if (zLookForLifecycleMethods) {
                fireAfterMarshalEvents(beanInfo, objPushObject);
            }
            this.cycleDetectionStack.pop();
        } catch (JAXBException e) {
            reportError(str, e);
            endNamespaceDecls(null);
            endAttributes();
            this.cycleDetectionStack.pop();
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    public final void childAsXsiType(Object obj, String str, JaxBeanInfo jaxBeanInfo, boolean z) throws SAXException, XMLStreamException, IOException {
        if (obj == null) {
            handleMissingObjectError(str);
            return;
        }
        Object objPushObject = pushObject(obj, str);
        QName qName = null;
        if (objPushObject == null) {
            endNamespaceDecls(null);
            endAttributes();
            return;
        }
        boolean z2 = objPushObject.getClass() == jaxBeanInfo.jaxbType;
        if (z2 && jaxBeanInfo.lookForLifecycleMethods()) {
            fireBeforeMarshalEvents(jaxBeanInfo, objPushObject);
        }
        if (!z2) {
            try {
                JaxBeanInfo beanInfo = this.grammar.getBeanInfo(objPushObject, true);
                if (beanInfo.lookForLifecycleMethods()) {
                    fireBeforeMarshalEvents(beanInfo, objPushObject);
                }
                if (beanInfo == jaxBeanInfo) {
                    z2 = true;
                    jaxBeanInfo = beanInfo;
                } else {
                    QName typeName = beanInfo.getTypeName(objPushObject);
                    if (typeName == null) {
                        reportError(new ValidationEventImpl(1, Messages.SUBSTITUTED_BY_ANONYMOUS_TYPE.format(jaxBeanInfo.jaxbType.getName(), objPushObject.getClass().getName(), beanInfo.jaxbType.getName()), getCurrentLocation(str)));
                    } else {
                        getNamespaceContext().declareNamespace(WellKnownNamespace.XML_SCHEMA_INSTANCE, "xsi", true);
                        getNamespaceContext().declareNamespace(typeName.getNamespaceURI(), null, false);
                    }
                    jaxBeanInfo = beanInfo;
                    qName = typeName;
                }
            } catch (JAXBException e) {
                reportError(str, e);
                endNamespaceDecls(null);
                endAttributes();
                return;
            }
        }
        jaxBeanInfo.serializeURIs(objPushObject, this);
        if (z) {
            getNamespaceContext().declareNamespace(WellKnownNamespace.XML_SCHEMA_INSTANCE, "xsi", true);
        }
        endNamespaceDecls(objPushObject);
        if (!z2) {
            attribute(WellKnownNamespace.XML_SCHEMA_INSTANCE, "type", DatatypeConverter.printQName(qName, getNamespaceContext()));
        }
        jaxBeanInfo.serializeAttributes(objPushObject, this);
        boolean zIsNilIncluded = jaxBeanInfo.isNilIncluded();
        if (z && !zIsNilIncluded) {
            attribute(WellKnownNamespace.XML_SCHEMA_INSTANCE, "nil", "true");
        }
        endAttributes();
        jaxBeanInfo.serializeBody(objPushObject, this);
        if (jaxBeanInfo.lookForLifecycleMethods()) {
            fireAfterMarshalEvents(jaxBeanInfo, objPushObject);
        }
        this.cycleDetectionStack.pop();
    }

    private void fireAfterMarshalEvents(JaxBeanInfo jaxBeanInfo, Object obj) {
        if (jaxBeanInfo.hasAfterMarshalMethod()) {
            fireMarshalEvent(obj, jaxBeanInfo.getLifecycleMethods().afterMarshal);
        }
        Marshaller.Listener listener = this.marshaller.getListener();
        if (listener != null) {
            listener.afterMarshal(obj);
        }
    }

    private void fireBeforeMarshalEvents(JaxBeanInfo jaxBeanInfo, Object obj) {
        if (jaxBeanInfo.hasBeforeMarshalMethod()) {
            fireMarshalEvent(obj, jaxBeanInfo.getLifecycleMethods().beforeMarshal);
        }
        Marshaller.Listener listener = this.marshaller.getListener();
        if (listener != null) {
            listener.beforeMarshal(obj);
        }
    }

    private void fireMarshalEvent(Object obj, Method method) {
        try {
            method.invoke(obj, this.marshaller);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    public void attWildcardAsURIs(Map<QName, String> map, String str) {
        if (map == null) {
            return;
        }
        Iterator<Map.Entry<QName, String>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            QName key = it.next().getKey();
            String namespaceURI = key.getNamespaceURI();
            if (namespaceURI.length() > 0) {
                String prefix = key.getPrefix();
                if (prefix.length() == 0) {
                    prefix = null;
                }
                this.nsContext.declareNsUri(namespaceURI, prefix, true);
            }
        }
    }

    public void attWildcardAsAttributes(Map<QName, String> map, String str) throws SAXException {
        if (map == null) {
            return;
        }
        for (Map.Entry<QName, String> entry : map.entrySet()) {
            QName key = entry.getKey();
            attribute(key.getNamespaceURI(), key.getLocalPart(), entry.getValue());
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    public final void writeXsiNilTrue() throws XMLStreamException, SAXException, IOException {
        getNamespaceContext().declareNamespace(WellKnownNamespace.XML_SCHEMA_INSTANCE, "xsi", true);
        endNamespaceDecls(null);
        attribute(WellKnownNamespace.XML_SCHEMA_INSTANCE, "nil", "true");
        endAttributes();
    }

    public <E> void writeDom(E e, DomHandler<E, ?> domHandler, Object obj, String str) throws SAXException {
        Source sourceMarshal = domHandler.marshal(e, this);
        if (this.contentHandlerAdapter == null) {
            this.contentHandlerAdapter = new ContentHandlerAdaptor(this);
        }
        try {
            getIdentityTransformer().transform(sourceMarshal, new SAXResult(this.contentHandlerAdapter));
        } catch (TransformerException e2) {
            reportError(str, e2);
        }
    }

    public Transformer getIdentityTransformer() {
        if (this.identityTransformer == null) {
            this.identityTransformer = JAXBContextImpl.createTransformer(this.grammar.disableSecurityProcessing);
        }
        return this.identityTransformer;
    }

    public void setPrefixMapper(NamespacePrefixMapper namespacePrefixMapper) {
        this.nsContext.setPrefixMapper(namespacePrefixMapper);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    public void startDocument(XmlOutput xmlOutput, boolean z, String str, String str2) throws SAXException, XMLStreamException, IOException {
        pushCoordinator();
        this.nsContext.reset();
        this.nse = this.nsContext.getCurrent();
        AttachmentMarshaller attachmentMarshaller = this.attachmentMarshaller;
        if (attachmentMarshaller != null && attachmentMarshaller.isXOPPackage()) {
            xmlOutput = new MTOMXmlOutput(xmlOutput);
        }
        this.out = xmlOutput;
        this.objectsWithId.clear();
        this.idReferencedObjects.clear();
        this.textHasAlreadyPrinted = false;
        this.seenRoot = false;
        this.schemaLocation = str;
        this.noNsSchemaLocation = str2;
        this.fragment = z;
        this.inlineBinaryFlag = false;
        this.expectedMimeType = null;
        this.cycleDetectionStack.reset();
        xmlOutput.startDocument(this, z, this.knownUri2prefixIndexMap, this.nsContext);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    public void endDocument() throws SAXException, XMLStreamException, IOException {
        this.out.endDocument(this.fragment);
    }

    public void close() {
        this.out = null;
        clearCurrentProperty();
        popCoordinator();
    }

    public void addInscopeBinding(String str, String str2) {
        this.nsContext.put(str, str2);
    }

    public String getXMIMEContentType() {
        String xMIMEContentType = this.grammar.getXMIMEContentType(this.cycleDetectionStack.peek());
        if (xMIMEContentType != null) {
            return xMIMEContentType;
        }
        MimeType mimeType = this.expectedMimeType;
        if (mimeType != null) {
            return mimeType.toString();
        }
        return null;
    }

    private void startElement() {
        this.nse = this.nse.push();
        if (!this.seenRoot) {
            if (this.grammar.getXmlNsSet() != null) {
                for (XmlNs xmlNs : this.grammar.getXmlNsSet()) {
                    this.nsContext.declareNsUri(xmlNs.namespaceURI(), xmlNs.prefix() == null ? "" : xmlNs.prefix(), xmlNs.prefix() != null);
                }
            }
            String[] strArr = this.nameList.namespaceURIs;
            for (int i = 0; i < strArr.length; i++) {
                this.knownUri2prefixIndexMap[i] = this.nsContext.declareNsUri(strArr[i], null, this.nameList.nsUriCannotBeDefaulted[i]);
            }
            String[] preDeclaredNamespaceUris = this.nsContext.getPrefixMapper().getPreDeclaredNamespaceUris();
            if (preDeclaredNamespaceUris != null) {
                for (String str : preDeclaredNamespaceUris) {
                    if (str != null) {
                        this.nsContext.declareNsUri(str, null, false);
                    }
                }
            }
            String[] preDeclaredNamespaceUris2 = this.nsContext.getPrefixMapper().getPreDeclaredNamespaceUris2();
            if (preDeclaredNamespaceUris2 != null) {
                for (int i2 = 0; i2 < preDeclaredNamespaceUris2.length; i2 += 2) {
                    String str2 = preDeclaredNamespaceUris2[i2];
                    String str3 = preDeclaredNamespaceUris2[i2 + 1];
                    if (str2 != null && str3 != null) {
                        this.nsContext.put(str3, str2);
                    }
                }
            }
            if (this.schemaLocation != null || this.noNsSchemaLocation != null) {
                this.nsContext.declareNsUri(WellKnownNamespace.XML_SCHEMA_INSTANCE, "xsi", true);
            }
        }
        this.nsContext.collectionMode = true;
        this.textHasAlreadyPrinted = false;
    }

    public MimeType setExpectedMimeType(MimeType mimeType) {
        MimeType mimeType2 = this.expectedMimeType;
        this.expectedMimeType = mimeType;
        return mimeType2;
    }

    public boolean setInlineBinaryFlag(boolean z) {
        boolean z2 = this.inlineBinaryFlag;
        this.inlineBinaryFlag = z;
        return z2;
    }

    public boolean getInlineBinaryFlag() {
        return this.inlineBinaryFlag;
    }

    public QName setSchemaType(QName qName) {
        QName qName2 = this.schemaType;
        this.schemaType = qName;
        return qName2;
    }

    public QName getSchemaType() {
        return this.schemaType;
    }

    public void setObjectIdentityCycleDetection(boolean z) {
        this.cycleDetectionStack.setUseIdentity(z);
    }

    public boolean getObjectIdentityCycleDetection() {
        return this.cycleDetectionStack.getUseIdentity();
    }

    void reconcileID() throws SAXException {
        this.idReferencedObjects.removeAll(this.objectsWithId);
        for (Object obj : this.idReferencedObjects) {
            try {
                reportError(new NotIdentifiableEventImpl(1, Messages.DANGLING_IDREF.format(getIdFromObject(obj)), new ValidationEventLocatorImpl(obj)));
            } catch (JAXBException unused) {
            }
        }
        this.idReferencedObjects.clear();
        this.objectsWithId.clear();
    }

    public boolean handleError(Exception exc) {
        return handleError(exc, this.cycleDetectionStack.peek(), null);
    }

    public boolean handleError(Exception exc, Object obj, String str) {
        return handleEvent(new ValidationEventImpl(1, exc.getMessage(), new ValidationEventLocatorExImpl(obj, str), exc));
    }

    @Override // javax.xml.bind.ValidationEventHandler
    public boolean handleEvent(ValidationEvent validationEvent) {
        try {
            return this.marshaller.getEventHandler().handleEvent(validationEvent);
        } catch (JAXBException e) {
            throw new Error(e);
        }
    }

    private void reportMissingObjectError(String str) throws SAXException {
        reportError(new ValidationEventImpl(1, Messages.MISSING_OBJECT.format(str), getCurrentLocation(str), new NullPointerException()));
    }

    public void errorMissingId(Object obj) throws SAXException {
        reportError(new ValidationEventImpl(1, Messages.MISSING_ID.format(obj), new ValidationEventLocatorImpl(obj)));
    }

    public ValidationEventLocator getCurrentLocation(String str) {
        return new ValidationEventLocatorExImpl(this.cycleDetectionStack.peek(), str);
    }

    @Override // com.sun.xml.bind.v2.runtime.Coordinator
    protected ValidationEventLocator getLocation() {
        return getCurrentLocation(null);
    }

    public Property getCurrentProperty() {
        return this.currentProperty.get();
    }

    public void clearCurrentProperty() {
        ThreadLocal<Property> threadLocal = this.currentProperty;
        if (threadLocal != null) {
            threadLocal.remove();
        }
    }

    public static XMLSerializer getInstance() {
        return (XMLSerializer) Coordinator._getInstance();
    }
}
