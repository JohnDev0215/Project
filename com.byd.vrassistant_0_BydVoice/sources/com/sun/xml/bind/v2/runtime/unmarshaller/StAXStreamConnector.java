package com.sun.xml.bind.v2.runtime.unmarshaller;

import com.sun.xml.bind.WhiteSpaceProcessor;
import java.lang.reflect.Constructor;
import javax.xml.stream.Location;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

/* JADX INFO: loaded from: classes3.dex */
class StAXStreamConnector extends StAXConnector {
    private final Attributes attributes;
    protected final StringBuilder buffer;
    private final XMLStreamReader staxStreamReader;
    protected boolean textReported;
    private static final Class FI_STAX_READER_CLASS = initFIStAXReaderClass();
    private static final Constructor<? extends StAXConnector> FI_CONNECTOR_CTOR = initFastInfosetConnectorClass();
    private static final Class STAX_EX_READER_CLASS = initStAXExReader();
    private static final Constructor<? extends StAXConnector> STAX_EX_CONNECTOR_CTOR = initStAXExConnector();

    public static StAXConnector create(XMLStreamReader xMLStreamReader, XmlVisitor xmlVisitor) {
        Constructor<? extends StAXConnector> constructor;
        Class<?> cls = xMLStreamReader.getClass();
        Class cls2 = FI_STAX_READER_CLASS;
        if (cls2 != null && cls2.isAssignableFrom(cls) && (constructor = FI_CONNECTOR_CTOR) != null) {
            try {
                return constructor.newInstance(xMLStreamReader, xmlVisitor);
            } catch (Exception unused) {
            }
        }
        boolean zEquals = cls.getName().equals("com.sun.xml.stream.XMLReaderImpl");
        if ((!getBoolProp(xMLStreamReader, "org.codehaus.stax2.internNames") || !getBoolProp(xMLStreamReader, "org.codehaus.stax2.internNsUris")) && !zEquals && !checkImplementaionNameOfSjsxp(xMLStreamReader)) {
            xmlVisitor = new InterningXmlVisitor(xmlVisitor);
        }
        Class cls3 = STAX_EX_READER_CLASS;
        if (cls3 != null && cls3.isAssignableFrom(cls)) {
            try {
                return STAX_EX_CONNECTOR_CTOR.newInstance(xMLStreamReader, xmlVisitor);
            } catch (Exception unused2) {
            }
        }
        return new StAXStreamConnector(xMLStreamReader, xmlVisitor);
    }

    private static boolean checkImplementaionNameOfSjsxp(XMLStreamReader xMLStreamReader) {
        try {
            Object property = xMLStreamReader.getProperty("http://java.sun.com/xml/stream/properties/implementation-name");
            if (property != null) {
                return property.equals("sjsxp");
            }
            return false;
        } catch (Exception unused) {
            return false;
        }
    }

    private static boolean getBoolProp(XMLStreamReader xMLStreamReader, String str) {
        try {
            Object property = xMLStreamReader.getProperty(str);
            if (property instanceof Boolean) {
                return ((Boolean) property).booleanValue();
            }
        } catch (Exception unused) {
        }
        return false;
    }

    protected StAXStreamConnector(XMLStreamReader xMLStreamReader, XmlVisitor xmlVisitor) {
        super(xmlVisitor);
        this.buffer = new StringBuilder();
        this.textReported = false;
        this.attributes = new Attributes() { // from class: com.sun.xml.bind.v2.runtime.unmarshaller.StAXStreamConnector.1
            @Override // org.xml.sax.Attributes
            public int getLength() {
                return StAXStreamConnector.this.staxStreamReader.getAttributeCount();
            }

            @Override // org.xml.sax.Attributes
            public String getURI(int i) {
                String attributeNamespace = StAXStreamConnector.this.staxStreamReader.getAttributeNamespace(i);
                return attributeNamespace == null ? "" : attributeNamespace;
            }

            @Override // org.xml.sax.Attributes
            public String getLocalName(int i) {
                return StAXStreamConnector.this.staxStreamReader.getAttributeLocalName(i);
            }

            @Override // org.xml.sax.Attributes
            public String getQName(int i) {
                String attributePrefix = StAXStreamConnector.this.staxStreamReader.getAttributePrefix(i);
                if (attributePrefix == null || attributePrefix.length() == 0) {
                    return getLocalName(i);
                }
                return attributePrefix + ':' + getLocalName(i);
            }

            @Override // org.xml.sax.Attributes
            public String getType(int i) {
                return StAXStreamConnector.this.staxStreamReader.getAttributeType(i);
            }

            @Override // org.xml.sax.Attributes
            public String getValue(int i) {
                return StAXStreamConnector.this.staxStreamReader.getAttributeValue(i);
            }

            @Override // org.xml.sax.Attributes
            public int getIndex(String str, String str2) {
                for (int length = getLength() - 1; length >= 0; length--) {
                    if (str2.equals(getLocalName(length)) && str.equals(getURI(length))) {
                        return length;
                    }
                }
                return -1;
            }

            @Override // org.xml.sax.Attributes
            public int getIndex(String str) {
                for (int length = getLength() - 1; length >= 0; length--) {
                    if (str.equals(getQName(length))) {
                        return length;
                    }
                }
                return -1;
            }

            @Override // org.xml.sax.Attributes
            public String getType(String str, String str2) {
                int index = getIndex(str, str2);
                if (index < 0) {
                    return null;
                }
                return getType(index);
            }

            @Override // org.xml.sax.Attributes
            public String getType(String str) {
                int index = getIndex(str);
                if (index < 0) {
                    return null;
                }
                return getType(index);
            }

            @Override // org.xml.sax.Attributes
            public String getValue(String str, String str2) {
                int index = getIndex(str, str2);
                if (index < 0) {
                    return null;
                }
                return getValue(index);
            }

            @Override // org.xml.sax.Attributes
            public String getValue(String str) {
                int index = getIndex(str);
                if (index < 0) {
                    return null;
                }
                return getValue(index);
            }
        };
        this.staxStreamReader = xMLStreamReader;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // com.sun.xml.bind.v2.runtime.unmarshaller.StAXConnector
    public void bridge() throws XMLStreamException {
        try {
            int eventType = this.staxStreamReader.getEventType();
            if (eventType == 7) {
                while (!this.staxStreamReader.isStartElement()) {
                    eventType = this.staxStreamReader.next();
                }
            }
            if (eventType != 1) {
                throw new IllegalStateException("The current event is not START_ELEMENT\n but " + eventType);
            }
            handleStartDocument(this.staxStreamReader.getNamespaceContext());
            int i = 0;
            while (true) {
                if (eventType == 1) {
                    handleStartElement();
                    i++;
                } else if (eventType == 2) {
                    i--;
                    handleEndElement();
                    if (i == 0) {
                        this.staxStreamReader.next();
                        handleEndDocument();
                        return;
                    }
                } else if (eventType == 4 || eventType == 6 || eventType == 12) {
                    handleCharacters();
                }
                eventType = this.staxStreamReader.next();
            }
        } catch (SAXException e) {
            throw new XMLStreamException(e);
        }
    }

    @Override // com.sun.xml.bind.v2.runtime.unmarshaller.StAXConnector
    protected Location getCurrentLocation() {
        return this.staxStreamReader.getLocation();
    }

    @Override // com.sun.xml.bind.v2.runtime.unmarshaller.StAXConnector
    protected String getCurrentQName() {
        return getQName(this.staxStreamReader.getPrefix(), this.staxStreamReader.getLocalName());
    }

    private void handleEndElement() throws SAXException {
        processText(false);
        this.tagName.uri = fixNull(this.staxStreamReader.getNamespaceURI());
        this.tagName.local = this.staxStreamReader.getLocalName();
        this.visitor.endElement(this.tagName);
        for (int namespaceCount = this.staxStreamReader.getNamespaceCount() - 1; namespaceCount >= 0; namespaceCount--) {
            this.visitor.endPrefixMapping(fixNull(this.staxStreamReader.getNamespacePrefix(namespaceCount)));
        }
    }

    private void handleStartElement() throws SAXException {
        processText(true);
        int namespaceCount = this.staxStreamReader.getNamespaceCount();
        for (int i = 0; i < namespaceCount; i++) {
            this.visitor.startPrefixMapping(fixNull(this.staxStreamReader.getNamespacePrefix(i)), fixNull(this.staxStreamReader.getNamespaceURI(i)));
        }
        this.tagName.uri = fixNull(this.staxStreamReader.getNamespaceURI());
        this.tagName.local = this.staxStreamReader.getLocalName();
        this.tagName.atts = this.attributes;
        this.visitor.startElement(this.tagName);
    }

    protected void handleCharacters() throws XMLStreamException, SAXException {
        if (this.predictor.expectText()) {
            this.buffer.append(this.staxStreamReader.getTextCharacters(), this.staxStreamReader.getTextStart(), this.staxStreamReader.getTextLength());
        }
    }

    private void processText(boolean z) throws SAXException {
        if (this.predictor.expectText() && (!z || !WhiteSpaceProcessor.isWhiteSpace(this.buffer) || this.context.getCurrentState().isMixed())) {
            if (this.textReported) {
                this.textReported = false;
            } else {
                this.visitor.text(this.buffer);
            }
        }
        this.buffer.setLength(0);
    }

    private static Class initFIStAXReaderClass() {
        Class<?> cls;
        Class<?> cls2;
        try {
            cls = Class.forName("org.jvnet.fastinfoset.stax.FastInfosetStreamReader");
            cls2 = Class.forName("com.sun.xml.fastinfoset.stax.StAXDocumentParser");
        } catch (Throwable unused) {
        }
        if (cls.isAssignableFrom(cls2)) {
            return cls2;
        }
        return null;
    }

    private static Constructor<? extends StAXConnector> initFastInfosetConnectorClass() {
        try {
            Class<?> cls = FI_STAX_READER_CLASS;
            if (cls == null) {
                return null;
            }
            return Class.forName("com.sun.xml.bind.v2.runtime.unmarshaller.FastInfosetConnector").getConstructor(cls, XmlVisitor.class);
        } catch (Throwable unused) {
            return null;
        }
    }

    private static Class initStAXExReader() {
        try {
            return Class.forName("org.jvnet.staxex.XMLStreamReaderEx");
        } catch (Throwable unused) {
            return null;
        }
    }

    private static Constructor<? extends StAXConnector> initStAXExConnector() {
        try {
            return Class.forName("com.sun.xml.bind.v2.runtime.unmarshaller.StAXExConnector").getConstructor(STAX_EX_READER_CLASS, XmlVisitor.class);
        } catch (Throwable unused) {
            return null;
        }
    }
}
