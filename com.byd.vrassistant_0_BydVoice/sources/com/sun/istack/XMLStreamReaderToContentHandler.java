package com.sun.istack;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

/* JADX INFO: loaded from: classes3.dex */
public class XMLStreamReaderToContentHandler {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final boolean eagerQuit;
    private final boolean fragment;
    private final String[] inscopeNamespaces;
    private final ContentHandler saxHandler;
    private final XMLStreamReader staxStreamReader;

    private static String fixNull(String str) {
        return str == null ? "" : str;
    }

    private void handleAttribute() {
    }

    private void handleCDATA() {
    }

    private void handleComment() {
    }

    private void handleDTD() {
    }

    private void handleEntityDecl() {
    }

    private void handleEntityReference() {
    }

    private void handleNamespace() {
    }

    private void handleNotationDecl() {
    }

    private void handleSpace() {
    }

    public XMLStreamReaderToContentHandler(XMLStreamReader xMLStreamReader, ContentHandler contentHandler, boolean z, boolean z2) {
        this(xMLStreamReader, contentHandler, z, z2, new String[0]);
    }

    public XMLStreamReaderToContentHandler(XMLStreamReader xMLStreamReader, ContentHandler contentHandler, boolean z, boolean z2, String[] strArr) {
        this.staxStreamReader = xMLStreamReader;
        this.saxHandler = contentHandler;
        this.eagerQuit = z;
        this.fragment = z2;
        this.inscopeNamespaces = (String[]) strArr.clone();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0084 A[Catch: SAXException -> 0x00c2, LOOP:3: B:35:0x007f->B:37:0x0084, LOOP_END, TryCatch #0 {SAXException -> 0x00c2, blocks: (B:2:0x0000, B:4:0x0009, B:6:0x0011, B:9:0x001b, B:10:0x0020, B:12:0x0025, B:14:0x0034, B:15:0x0037, B:40:0x0092, B:41:0x00a8, B:16:0x003a, B:33:0x0077, B:35:0x007f, B:37:0x0084, B:38:0x008e, B:17:0x003e, B:18:0x0042, B:19:0x0046, B:20:0x004a, B:21:0x004e, B:22:0x0052, B:23:0x0056, B:24:0x005a, B:25:0x005e, B:26:0x0062, B:27:0x0066, B:29:0x006d, B:32:0x0072, B:42:0x00a9, B:43:0x00c1), top: B:47:0x0000 }] */
    /* JADX WARN: Removed duplicated region for block: B:54:0x008e A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void bridge() throws javax.xml.stream.XMLStreamException {
        /*
            Method dump skipped, instruction units count: 236
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sun.istack.XMLStreamReaderToContentHandler.bridge():void");
    }

    private void handleEndDocument() throws SAXException {
        if (this.fragment) {
            return;
        }
        this.saxHandler.endDocument();
    }

    private void handleStartDocument() throws SAXException {
        if (this.fragment) {
            return;
        }
        this.saxHandler.setDocumentLocator(new Locator() { // from class: com.sun.istack.XMLStreamReaderToContentHandler.1
            @Override // org.xml.sax.Locator
            public int getColumnNumber() {
                return XMLStreamReaderToContentHandler.this.staxStreamReader.getLocation().getColumnNumber();
            }

            @Override // org.xml.sax.Locator
            public int getLineNumber() {
                return XMLStreamReaderToContentHandler.this.staxStreamReader.getLocation().getLineNumber();
            }

            @Override // org.xml.sax.Locator
            public String getPublicId() {
                return XMLStreamReaderToContentHandler.this.staxStreamReader.getLocation().getPublicId();
            }

            @Override // org.xml.sax.Locator
            public String getSystemId() {
                return XMLStreamReaderToContentHandler.this.staxStreamReader.getLocation().getSystemId();
            }
        });
        this.saxHandler.startDocument();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    private void handlePI() throws XMLStreamException {
        try {
            this.saxHandler.processingInstruction(this.staxStreamReader.getPITarget(), this.staxStreamReader.getPIData());
        } catch (SAXException e) {
            throw new XMLStreamException2(e);
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    private void handleCharacters() throws XMLStreamException {
        try {
            this.saxHandler.characters(this.staxStreamReader.getTextCharacters(), this.staxStreamReader.getTextStart(), this.staxStreamReader.getTextLength());
        } catch (SAXException e) {
            throw new XMLStreamException2(e);
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    private void handleEndElement() throws XMLStreamException {
        String localPart;
        QName name = this.staxStreamReader.getName();
        try {
            String prefix = name.getPrefix();
            if (prefix == null || prefix.length() == 0) {
                localPart = name.getLocalPart();
            } else {
                localPart = prefix + ':' + name.getLocalPart();
            }
            this.saxHandler.endElement(name.getNamespaceURI(), name.getLocalPart(), localPart);
            for (int namespaceCount = this.staxStreamReader.getNamespaceCount() - 1; namespaceCount >= 0; namespaceCount--) {
                String namespacePrefix = this.staxStreamReader.getNamespacePrefix(namespaceCount);
                if (namespacePrefix == null) {
                    namespacePrefix = "";
                }
                this.saxHandler.endPrefixMapping(namespacePrefix);
            }
        } catch (SAXException e) {
            throw new XMLStreamException2(e);
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    private void handleStartElement() throws XMLStreamException {
        String localPart;
        try {
            int namespaceCount = this.staxStreamReader.getNamespaceCount();
            for (int i = 0; i < namespaceCount; i++) {
                this.saxHandler.startPrefixMapping(fixNull(this.staxStreamReader.getNamespacePrefix(i)), fixNull(this.staxStreamReader.getNamespaceURI(i)));
            }
            QName name = this.staxStreamReader.getName();
            String prefix = name.getPrefix();
            if (prefix == null || prefix.length() == 0) {
                localPart = name.getLocalPart();
            } else {
                localPart = prefix + ':' + name.getLocalPart();
            }
            this.saxHandler.startElement(name.getNamespaceURI(), name.getLocalPart(), localPart, getAttributes());
        } catch (SAXException e) {
            throw new XMLStreamException2(e);
        }
    }

    private Attributes getAttributes() {
        AttributesImpl attributesImpl = new AttributesImpl();
        int eventType = this.staxStreamReader.getEventType();
        if (eventType != 10 && eventType != 1) {
            throw new InternalError("getAttributes() attempting to process: " + eventType);
        }
        for (int i = 0; i < this.staxStreamReader.getAttributeCount(); i++) {
            String attributeNamespace = this.staxStreamReader.getAttributeNamespace(i);
            if (attributeNamespace == null) {
                attributeNamespace = "";
            }
            String str = attributeNamespace;
            String attributeLocalName = this.staxStreamReader.getAttributeLocalName(i);
            String attributePrefix = this.staxStreamReader.getAttributePrefix(i);
            attributesImpl.addAttribute(str, attributeLocalName, (attributePrefix == null || attributePrefix.length() == 0) ? attributeLocalName : attributePrefix + ':' + attributeLocalName, this.staxStreamReader.getAttributeType(i), this.staxStreamReader.getAttributeValue(i));
        }
        return attributesImpl;
    }
}
