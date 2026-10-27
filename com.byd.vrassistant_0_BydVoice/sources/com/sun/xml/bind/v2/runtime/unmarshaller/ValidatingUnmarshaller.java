package com.sun.xml.bind.v2.runtime.unmarshaller;

import com.sun.xml.bind.v2.runtime.unmarshaller.XmlVisitor;
import com.sun.xml.bind.v2.util.FatalAdapter;
import javax.xml.namespace.NamespaceContext;
import javax.xml.validation.Schema;
import javax.xml.validation.ValidatorHandler;
import org.xml.sax.SAXException;

/* JADX INFO: loaded from: classes3.dex */
final class ValidatingUnmarshaller implements XmlVisitor, XmlVisitor.TextPredictor {
    private final XmlVisitor next;
    private final XmlVisitor.TextPredictor predictor;
    private final ValidatorHandler validator;
    private NamespaceContext nsContext = null;
    private char[] buf = new char[256];

    @Override // com.sun.xml.bind.v2.runtime.unmarshaller.XmlVisitor.TextPredictor
    @Deprecated
    public boolean expectText() {
        return true;
    }

    @Override // com.sun.xml.bind.v2.runtime.unmarshaller.XmlVisitor
    public XmlVisitor.TextPredictor getPredictor() {
        return this;
    }

    public ValidatingUnmarshaller(Schema schema, XmlVisitor xmlVisitor) {
        ValidatorHandler validatorHandlerNewValidatorHandler = schema.newValidatorHandler();
        this.validator = validatorHandlerNewValidatorHandler;
        this.next = xmlVisitor;
        this.predictor = xmlVisitor.getPredictor();
        validatorHandlerNewValidatorHandler.setErrorHandler(new FatalAdapter(getContext()));
    }

    @Override // com.sun.xml.bind.v2.runtime.unmarshaller.XmlVisitor
    public void startDocument(LocatorEx locatorEx, NamespaceContext namespaceContext) throws SAXException {
        this.nsContext = namespaceContext;
        this.validator.setDocumentLocator(locatorEx);
        this.validator.startDocument();
        this.next.startDocument(locatorEx, namespaceContext);
    }

    @Override // com.sun.xml.bind.v2.runtime.unmarshaller.XmlVisitor
    public void endDocument() throws SAXException {
        this.nsContext = null;
        this.validator.endDocument();
        this.next.endDocument();
    }

    @Override // com.sun.xml.bind.v2.runtime.unmarshaller.XmlVisitor
    public void startElement(TagName tagName) throws SAXException {
        String strIntern;
        if (this.nsContext != null && (strIntern = tagName.getPrefix().intern()) != "") {
            this.validator.startPrefixMapping(strIntern, this.nsContext.getNamespaceURI(strIntern));
        }
        this.validator.startElement(tagName.uri, tagName.local, tagName.getQname(), tagName.atts);
        this.next.startElement(tagName);
    }

    @Override // com.sun.xml.bind.v2.runtime.unmarshaller.XmlVisitor
    public void endElement(TagName tagName) throws SAXException {
        this.validator.endElement(tagName.uri, tagName.local, tagName.getQname());
        this.next.endElement(tagName);
    }

    @Override // com.sun.xml.bind.v2.runtime.unmarshaller.XmlVisitor
    public void startPrefixMapping(String str, String str2) throws SAXException {
        this.validator.startPrefixMapping(str, str2);
        this.next.startPrefixMapping(str, str2);
    }

    @Override // com.sun.xml.bind.v2.runtime.unmarshaller.XmlVisitor
    public void endPrefixMapping(String str) throws SAXException {
        this.validator.endPrefixMapping(str);
        this.next.endPrefixMapping(str);
    }

    @Override // com.sun.xml.bind.v2.runtime.unmarshaller.XmlVisitor
    public void text(CharSequence charSequence) throws SAXException {
        int length = charSequence.length();
        if (this.buf.length < length) {
            this.buf = new char[length];
        }
        for (int i = 0; i < length; i++) {
            this.buf[i] = charSequence.charAt(i);
        }
        this.validator.characters(this.buf, 0, length);
        if (this.predictor.expectText()) {
            this.next.text(charSequence);
        }
    }

    @Override // com.sun.xml.bind.v2.runtime.unmarshaller.XmlVisitor
    public UnmarshallingContext getContext() {
        return this.next.getContext();
    }
}
