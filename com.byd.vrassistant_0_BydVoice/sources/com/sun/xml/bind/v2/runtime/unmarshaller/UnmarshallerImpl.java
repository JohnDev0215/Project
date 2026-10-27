package com.sun.xml.bind.v2.runtime.unmarshaller;

import com.sun.xml.bind.IDResolver;
import com.sun.xml.bind.api.ClassResolver;
import com.sun.xml.bind.unmarshaller.DOMScanner;
import com.sun.xml.bind.unmarshaller.InfosetScanner;
import com.sun.xml.bind.v2.ClassFactory;
import com.sun.xml.bind.v2.runtime.AssociationMap;
import com.sun.xml.bind.v2.runtime.JAXBContextImpl;
import com.sun.xml.bind.v2.runtime.JaxBeanInfo;
import com.sun.xml.bind.v2.util.XmlFactory;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.PropertyException;
import javax.xml.bind.UnmarshalException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.UnmarshallerHandler;
import javax.xml.bind.ValidationEvent;
import javax.xml.bind.ValidationEventHandler;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.bind.attachment.AttachmentUnmarshaller;
import javax.xml.bind.helpers.AbstractUnmarshallerImpl;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;
import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

/* JADX INFO: loaded from: classes3.dex */
public final class UnmarshallerImpl extends AbstractUnmarshallerImpl implements ValidationEventHandler, Closeable {
    public static final String FACTORY = "com.sun.xml.bind.ObjectFactory";
    private static final DefaultHandler dummyHandler = new DefaultHandler();
    private AttachmentUnmarshaller attachmentUnmarshaller;
    protected final JAXBContextImpl context;
    public final UnmarshallingContext coordinator;
    private Unmarshaller.Listener externalListener;
    private IDResolver idResolver = new DefaultIDResolver();
    private XMLReader reader = null;
    private Schema schema;

    public UnmarshallerImpl(JAXBContextImpl jAXBContextImpl, AssociationMap associationMap) {
        this.context = jAXBContextImpl;
        this.coordinator = new UnmarshallingContext(this, associationMap);
        try {
            setEventHandler(this);
        } catch (JAXBException e) {
            throw new AssertionError(e);
        }
    }

    @Override // javax.xml.bind.Unmarshaller
    public UnmarshallerHandler getUnmarshallerHandler() {
        return getUnmarshallerHandler(true, null);
    }

    @Override // javax.xml.bind.helpers.AbstractUnmarshallerImpl
    protected XMLReader getXMLReader() throws JAXBException {
        if (this.reader == null) {
            try {
                SAXParserFactory sAXParserFactoryCreateParserFactory = XmlFactory.createParserFactory(this.context.disableSecurityProcessing);
                sAXParserFactoryCreateParserFactory.setValidating(false);
                this.reader = sAXParserFactoryCreateParserFactory.newSAXParser().getXMLReader();
            } catch (ParserConfigurationException e) {
                throw new JAXBException(e);
            } catch (SAXException e2) {
                throw new JAXBException(e2);
            }
        }
        return this.reader;
    }

    private SAXConnector getUnmarshallerHandler(boolean z, JaxBeanInfo jaxBeanInfo) {
        XmlVisitor xmlVisitorCreateUnmarshallerHandler = createUnmarshallerHandler(null, false, jaxBeanInfo);
        if (z) {
            xmlVisitorCreateUnmarshallerHandler = new InterningXmlVisitor(xmlVisitorCreateUnmarshallerHandler);
        }
        return new SAXConnector(xmlVisitorCreateUnmarshallerHandler, null);
    }

    public final XmlVisitor createUnmarshallerHandler(InfosetScanner infosetScanner, boolean z, JaxBeanInfo jaxBeanInfo) {
        this.coordinator.reset(infosetScanner, z, jaxBeanInfo, this.idResolver);
        XmlVisitor validatingUnmarshaller = this.coordinator;
        if (this.schema != null) {
            validatingUnmarshaller = new ValidatingUnmarshaller(this.schema, validatingUnmarshaller);
        }
        AttachmentUnmarshaller attachmentUnmarshaller = this.attachmentUnmarshaller;
        return (attachmentUnmarshaller == null || !attachmentUnmarshaller.isXOPPackage()) ? validatingUnmarshaller : new MTOMDecorator(this, validatingUnmarshaller, this.attachmentUnmarshaller);
    }

    public static boolean needsInterning(XMLReader xMLReader) {
        try {
            xMLReader.setFeature("http://xml.org/sax/features/string-interning", true);
        } catch (SAXException unused) {
        }
        return !xMLReader.getFeature("http://xml.org/sax/features/string-interning");
    }

    @Override // javax.xml.bind.helpers.AbstractUnmarshallerImpl
    protected Object unmarshal(XMLReader xMLReader, InputSource inputSource) throws JAXBException {
        return unmarshal0(xMLReader, inputSource, null);
    }

    protected <T> JAXBElement<T> unmarshal(XMLReader xMLReader, InputSource inputSource, Class<T> cls) throws JAXBException {
        if (cls == null) {
            throw new IllegalArgumentException();
        }
        return (JAXBElement) unmarshal0(xMLReader, inputSource, getBeanInfo(cls));
    }

    private Object unmarshal0(XMLReader xMLReader, InputSource inputSource, JaxBeanInfo jaxBeanInfo) throws JAXBException {
        SAXConnector unmarshallerHandler = getUnmarshallerHandler(needsInterning(xMLReader), jaxBeanInfo);
        xMLReader.setContentHandler(unmarshallerHandler);
        xMLReader.setErrorHandler(this.coordinator);
        try {
            xMLReader.parse(inputSource);
            Object result = unmarshallerHandler.getResult();
            DefaultHandler defaultHandler = dummyHandler;
            xMLReader.setContentHandler(defaultHandler);
            xMLReader.setErrorHandler(defaultHandler);
            return result;
        } catch (IOException e) {
            this.coordinator.clearStates();
            throw new UnmarshalException(e);
        } catch (SAXException e2) {
            this.coordinator.clearStates();
            throw createUnmarshalException(e2);
        }
    }

    @Override // javax.xml.bind.helpers.AbstractUnmarshallerImpl, javax.xml.bind.Unmarshaller
    public <T> JAXBElement<T> unmarshal(Source source, Class<T> cls) throws JAXBException {
        if (source instanceof SAXSource) {
            SAXSource sAXSource = (SAXSource) source;
            XMLReader xMLReader = sAXSource.getXMLReader();
            if (xMLReader == null) {
                xMLReader = getXMLReader();
            }
            return unmarshal(xMLReader, sAXSource.getInputSource(), cls);
        }
        if (source instanceof StreamSource) {
            return unmarshal(getXMLReader(), streamSourceToInputSource((StreamSource) source), cls);
        }
        if (source instanceof DOMSource) {
            return unmarshal(((DOMSource) source).getNode(), cls);
        }
        throw new IllegalArgumentException();
    }

    public Object unmarshal0(Source source, JaxBeanInfo jaxBeanInfo) throws JAXBException {
        if (source instanceof SAXSource) {
            SAXSource sAXSource = (SAXSource) source;
            XMLReader xMLReader = sAXSource.getXMLReader();
            if (xMLReader == null) {
                xMLReader = getXMLReader();
            }
            return unmarshal0(xMLReader, sAXSource.getInputSource(), jaxBeanInfo);
        }
        if (source instanceof StreamSource) {
            return unmarshal0(getXMLReader(), streamSourceToInputSource((StreamSource) source), jaxBeanInfo);
        }
        if (source instanceof DOMSource) {
            return unmarshal0(((DOMSource) source).getNode(), jaxBeanInfo);
        }
        throw new IllegalArgumentException();
    }

    @Override // javax.xml.bind.helpers.AbstractUnmarshallerImpl, javax.xml.bind.Unmarshaller
    public final ValidationEventHandler getEventHandler() {
        try {
            return super.getEventHandler();
        } catch (JAXBException unused) {
            throw new AssertionError();
        }
    }

    public final boolean hasEventHandler() {
        return getEventHandler() != this;
    }

    @Override // javax.xml.bind.helpers.AbstractUnmarshallerImpl, javax.xml.bind.Unmarshaller
    public <T> JAXBElement<T> unmarshal(Node node, Class<T> cls) throws JAXBException {
        if (cls == null) {
            throw new IllegalArgumentException();
        }
        return (JAXBElement) unmarshal0(node, getBeanInfo(cls));
    }

    @Override // javax.xml.bind.Unmarshaller
    public final Object unmarshal(Node node) throws JAXBException {
        return unmarshal0(node, (JaxBeanInfo) null);
    }

    @Deprecated
    public final Object unmarshal(SAXSource sAXSource) throws JAXBException {
        return super.unmarshal((Source) sAXSource);
    }

    public final Object unmarshal0(Node node, JaxBeanInfo jaxBeanInfo) throws JAXBException {
        try {
            DOMScanner dOMScanner = new DOMScanner();
            InterningXmlVisitor interningXmlVisitor = new InterningXmlVisitor(createUnmarshallerHandler(null, false, jaxBeanInfo));
            dOMScanner.setContentHandler(new SAXConnector(interningXmlVisitor, dOMScanner));
            if (node.getNodeType() == 1) {
                dOMScanner.scan((Element) node);
            } else if (node.getNodeType() == 9) {
                dOMScanner.scan((Document) node);
            } else {
                throw new IllegalArgumentException("Unexpected node type: " + node);
            }
            Object result = interningXmlVisitor.getContext().getResult();
            interningXmlVisitor.getContext().clearResult();
            return result;
        } catch (SAXException e) {
            throw createUnmarshalException(e);
        }
    }

    @Override // javax.xml.bind.helpers.AbstractUnmarshallerImpl, javax.xml.bind.Unmarshaller
    public Object unmarshal(XMLStreamReader xMLStreamReader) throws JAXBException {
        return unmarshal0(xMLStreamReader, (JaxBeanInfo) null);
    }

    @Override // javax.xml.bind.helpers.AbstractUnmarshallerImpl, javax.xml.bind.Unmarshaller
    public <T> JAXBElement<T> unmarshal(XMLStreamReader xMLStreamReader, Class<T> cls) throws JAXBException {
        if (cls == null) {
            throw new IllegalArgumentException();
        }
        return (JAXBElement) unmarshal0(xMLStreamReader, getBeanInfo(cls));
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    public Object unmarshal0(XMLStreamReader xMLStreamReader, JaxBeanInfo jaxBeanInfo) throws JAXBException {
        if (xMLStreamReader == null) {
            throw new IllegalArgumentException(com.sun.xml.bind.unmarshaller.Messages.format(com.sun.xml.bind.unmarshaller.Messages.NULL_READER));
        }
        int eventType = xMLStreamReader.getEventType();
        if (eventType != 1 && eventType != 7) {
            throw new IllegalStateException(com.sun.xml.bind.unmarshaller.Messages.format(com.sun.xml.bind.unmarshaller.Messages.ILLEGAL_READER_STATE, Integer.valueOf(eventType)));
        }
        XmlVisitor xmlVisitorCreateUnmarshallerHandler = createUnmarshallerHandler(null, false, jaxBeanInfo);
        try {
            StAXStreamConnector.create(xMLStreamReader, xmlVisitorCreateUnmarshallerHandler).bridge();
            Object result = xmlVisitorCreateUnmarshallerHandler.getContext().getResult();
            xmlVisitorCreateUnmarshallerHandler.getContext().clearResult();
            return result;
        } catch (XMLStreamException e) {
            throw handleStreamException(e);
        }
    }

    @Override // javax.xml.bind.helpers.AbstractUnmarshallerImpl, javax.xml.bind.Unmarshaller
    public <T> JAXBElement<T> unmarshal(XMLEventReader xMLEventReader, Class<T> cls) throws JAXBException {
        if (cls == null) {
            throw new IllegalArgumentException();
        }
        return (JAXBElement) unmarshal0(xMLEventReader, getBeanInfo(cls));
    }

    @Override // javax.xml.bind.helpers.AbstractUnmarshallerImpl, javax.xml.bind.Unmarshaller
    public Object unmarshal(XMLEventReader xMLEventReader) throws JAXBException {
        return unmarshal0(xMLEventReader, (JaxBeanInfo) null);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    private Object unmarshal0(XMLEventReader xMLEventReader, JaxBeanInfo jaxBeanInfo) throws JAXBException {
        if (xMLEventReader == null) {
            throw new IllegalArgumentException(com.sun.xml.bind.unmarshaller.Messages.format(com.sun.xml.bind.unmarshaller.Messages.NULL_READER));
        }
        try {
            XMLEvent xMLEventPeek = xMLEventReader.peek();
            if (!xMLEventPeek.isStartElement() && !xMLEventPeek.isStartDocument()) {
                throw new IllegalStateException(com.sun.xml.bind.unmarshaller.Messages.format(com.sun.xml.bind.unmarshaller.Messages.ILLEGAL_READER_STATE, Integer.valueOf(xMLEventPeek.getEventType())));
            }
            boolean zEquals = xMLEventReader.getClass().getName().equals("com.sun.xml.stream.XMLReaderImpl");
            XmlVisitor xmlVisitorCreateUnmarshallerHandler = createUnmarshallerHandler(null, false, jaxBeanInfo);
            if (!zEquals) {
                xmlVisitorCreateUnmarshallerHandler = new InterningXmlVisitor(xmlVisitorCreateUnmarshallerHandler);
            }
            new StAXEventConnector(xMLEventReader, xmlVisitorCreateUnmarshallerHandler).bridge();
            return xmlVisitorCreateUnmarshallerHandler.getContext().getResult();
        } catch (XMLStreamException e) {
            throw handleStreamException(e);
        }
    }

    public Object unmarshal0(InputStream inputStream, JaxBeanInfo jaxBeanInfo) throws JAXBException {
        return unmarshal0(getXMLReader(), new InputSource(inputStream), jaxBeanInfo);
    }

    private static JAXBException handleStreamException(XMLStreamException xMLStreamException) {
        Throwable nestedException = xMLStreamException.getNestedException();
        if (nestedException instanceof JAXBException) {
            return (JAXBException) nestedException;
        }
        if (nestedException instanceof SAXException) {
            return new UnmarshalException(nestedException);
        }
        return new UnmarshalException((Throwable) xMLStreamException);
    }

    @Override // javax.xml.bind.helpers.AbstractUnmarshallerImpl, javax.xml.bind.Unmarshaller
    public Object getProperty(String str) throws PropertyException {
        if (str.equals(IDResolver.class.getName())) {
            return this.idResolver;
        }
        return super.getProperty(str);
    }

    @Override // javax.xml.bind.helpers.AbstractUnmarshallerImpl, javax.xml.bind.Unmarshaller
    public void setProperty(String str, Object obj) throws PropertyException {
        if (str.equals(FACTORY)) {
            this.coordinator.setFactories(obj);
            return;
        }
        if (str.equals(IDResolver.class.getName())) {
            this.idResolver = (IDResolver) obj;
            return;
        }
        if (str.equals(ClassResolver.class.getName())) {
            this.coordinator.classResolver = (ClassResolver) obj;
        } else if (str.equals(ClassLoader.class.getName())) {
            this.coordinator.classLoader = (ClassLoader) obj;
        } else {
            super.setProperty(str, obj);
        }
    }

    @Override // javax.xml.bind.helpers.AbstractUnmarshallerImpl, javax.xml.bind.Unmarshaller
    public void setSchema(Schema schema) {
        this.schema = schema;
    }

    @Override // javax.xml.bind.helpers.AbstractUnmarshallerImpl, javax.xml.bind.Unmarshaller
    public Schema getSchema() {
        return this.schema;
    }

    @Override // javax.xml.bind.helpers.AbstractUnmarshallerImpl, javax.xml.bind.Unmarshaller
    public AttachmentUnmarshaller getAttachmentUnmarshaller() {
        return this.attachmentUnmarshaller;
    }

    @Override // javax.xml.bind.helpers.AbstractUnmarshallerImpl, javax.xml.bind.Unmarshaller
    public void setAttachmentUnmarshaller(AttachmentUnmarshaller attachmentUnmarshaller) {
        this.attachmentUnmarshaller = attachmentUnmarshaller;
    }

    @Override // javax.xml.bind.helpers.AbstractUnmarshallerImpl, javax.xml.bind.Unmarshaller
    public boolean isValidating() {
        throw new UnsupportedOperationException();
    }

    @Override // javax.xml.bind.helpers.AbstractUnmarshallerImpl, javax.xml.bind.Unmarshaller
    public void setValidating(boolean z) {
        throw new UnsupportedOperationException();
    }

    @Override // javax.xml.bind.helpers.AbstractUnmarshallerImpl, javax.xml.bind.Unmarshaller
    public <A extends XmlAdapter> void setAdapter(Class<A> cls, A a) {
        if (cls == null) {
            throw new IllegalArgumentException();
        }
        this.coordinator.putAdapter(cls, a);
    }

    @Override // javax.xml.bind.helpers.AbstractUnmarshallerImpl, javax.xml.bind.Unmarshaller
    public <A extends XmlAdapter> A getAdapter(Class<A> cls) {
        if (cls == null) {
            throw new IllegalArgumentException();
        }
        if (this.coordinator.containsAdapter(cls)) {
            return (A) this.coordinator.getAdapter(cls);
        }
        return null;
    }

    @Override // javax.xml.bind.helpers.AbstractUnmarshallerImpl
    public UnmarshalException createUnmarshalException(SAXException sAXException) {
        return super.createUnmarshalException(sAXException);
    }

    @Override // javax.xml.bind.ValidationEventHandler
    public boolean handleEvent(ValidationEvent validationEvent) {
        return validationEvent.getSeverity() != 2;
    }

    private static InputSource streamSourceToInputSource(StreamSource streamSource) {
        InputSource inputSource = new InputSource();
        inputSource.setSystemId(streamSource.getSystemId());
        inputSource.setByteStream(streamSource.getInputStream());
        inputSource.setCharacterStream(streamSource.getReader());
        return inputSource;
    }

    public <T> JaxBeanInfo<T> getBeanInfo(Class<T> cls) throws JAXBException {
        return this.context.getBeanInfo((Class) cls, true);
    }

    @Override // javax.xml.bind.helpers.AbstractUnmarshallerImpl, javax.xml.bind.Unmarshaller
    public Unmarshaller.Listener getListener() {
        return this.externalListener;
    }

    @Override // javax.xml.bind.helpers.AbstractUnmarshallerImpl, javax.xml.bind.Unmarshaller
    public void setListener(Unmarshaller.Listener listener) {
        this.externalListener = listener;
    }

    public UnmarshallingContext getContext() {
        return this.coordinator;
    }

    protected void finalize() throws Throwable {
        try {
            ClassFactory.cleanCache();
        } finally {
            super.finalize();
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        ClassFactory.cleanCache();
    }
}
