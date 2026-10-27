package com.sun.xml.bind.v2.runtime.unmarshaller;

import com.sun.xml.bind.v2.runtime.unmarshaller.XmlVisitor;
import javax.xml.namespace.NamespaceContext;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

/* JADX INFO: loaded from: classes3.dex */
public final class InterningXmlVisitor implements XmlVisitor {
    private final AttributesImpl attributes = new AttributesImpl();
    private final XmlVisitor next;

    public InterningXmlVisitor(XmlVisitor xmlVisitor) {
        this.next = xmlVisitor;
    }

    @Override // com.sun.xml.bind.v2.runtime.unmarshaller.XmlVisitor
    public void startDocument(LocatorEx locatorEx, NamespaceContext namespaceContext) throws SAXException {
        this.next.startDocument(locatorEx, namespaceContext);
    }

    @Override // com.sun.xml.bind.v2.runtime.unmarshaller.XmlVisitor
    public void endDocument() throws SAXException {
        this.next.endDocument();
    }

    @Override // com.sun.xml.bind.v2.runtime.unmarshaller.XmlVisitor
    public void startElement(TagName tagName) throws SAXException {
        this.attributes.setAttributes(tagName.atts);
        tagName.atts = this.attributes;
        tagName.uri = intern(tagName.uri);
        tagName.local = intern(tagName.local);
        this.next.startElement(tagName);
    }

    @Override // com.sun.xml.bind.v2.runtime.unmarshaller.XmlVisitor
    public void endElement(TagName tagName) throws SAXException {
        tagName.uri = intern(tagName.uri);
        tagName.local = intern(tagName.local);
        this.next.endElement(tagName);
    }

    @Override // com.sun.xml.bind.v2.runtime.unmarshaller.XmlVisitor
    public void startPrefixMapping(String str, String str2) throws SAXException {
        this.next.startPrefixMapping(intern(str), intern(str2));
    }

    @Override // com.sun.xml.bind.v2.runtime.unmarshaller.XmlVisitor
    public void endPrefixMapping(String str) throws SAXException {
        this.next.endPrefixMapping(intern(str));
    }

    @Override // com.sun.xml.bind.v2.runtime.unmarshaller.XmlVisitor
    public void text(CharSequence charSequence) throws SAXException {
        this.next.text(charSequence);
    }

    @Override // com.sun.xml.bind.v2.runtime.unmarshaller.XmlVisitor
    public UnmarshallingContext getContext() {
        return this.next.getContext();
    }

    @Override // com.sun.xml.bind.v2.runtime.unmarshaller.XmlVisitor
    public XmlVisitor.TextPredictor getPredictor() {
        return this.next.getPredictor();
    }

    private static class AttributesImpl implements Attributes {
        private Attributes core;

        private AttributesImpl() {
        }

        void setAttributes(Attributes attributes) {
            this.core = attributes;
        }

        @Override // org.xml.sax.Attributes
        public int getIndex(String str) {
            return this.core.getIndex(str);
        }

        @Override // org.xml.sax.Attributes
        public int getIndex(String str, String str2) {
            return this.core.getIndex(str, str2);
        }

        @Override // org.xml.sax.Attributes
        public int getLength() {
            return this.core.getLength();
        }

        @Override // org.xml.sax.Attributes
        public String getLocalName(int i) {
            return InterningXmlVisitor.intern(this.core.getLocalName(i));
        }

        @Override // org.xml.sax.Attributes
        public String getQName(int i) {
            return InterningXmlVisitor.intern(this.core.getQName(i));
        }

        @Override // org.xml.sax.Attributes
        public String getType(int i) {
            return InterningXmlVisitor.intern(this.core.getType(i));
        }

        @Override // org.xml.sax.Attributes
        public String getType(String str) {
            return InterningXmlVisitor.intern(this.core.getType(str));
        }

        @Override // org.xml.sax.Attributes
        public String getType(String str, String str2) {
            return InterningXmlVisitor.intern(this.core.getType(str, str2));
        }

        @Override // org.xml.sax.Attributes
        public String getURI(int i) {
            return InterningXmlVisitor.intern(this.core.getURI(i));
        }

        @Override // org.xml.sax.Attributes
        public String getValue(int i) {
            return this.core.getValue(i);
        }

        @Override // org.xml.sax.Attributes
        public String getValue(String str) {
            return this.core.getValue(str);
        }

        @Override // org.xml.sax.Attributes
        public String getValue(String str, String str2) {
            return this.core.getValue(str, str2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String intern(String str) {
        if (str == null) {
            return null;
        }
        return str.intern();
    }
}
