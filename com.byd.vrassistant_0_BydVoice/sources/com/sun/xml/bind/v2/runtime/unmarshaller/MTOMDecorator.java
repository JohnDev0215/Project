package com.sun.xml.bind.v2.runtime.unmarshaller;

import com.sun.xml.bind.v2.WellKnownNamespace;
import com.sun.xml.bind.v2.runtime.unmarshaller.XmlVisitor;
import javax.activation.DataHandler;
import javax.xml.bind.attachment.AttachmentUnmarshaller;
import javax.xml.namespace.NamespaceContext;
import org.xml.sax.SAXException;

/* JADX INFO: loaded from: classes3.dex */
final class MTOMDecorator implements XmlVisitor {
    private final AttachmentUnmarshaller au;
    private final Base64Data base64data = new Base64Data();
    private boolean followXop;
    private boolean inXopInclude;
    private final XmlVisitor next;
    private UnmarshallerImpl parent;

    public MTOMDecorator(UnmarshallerImpl unmarshallerImpl, XmlVisitor xmlVisitor, AttachmentUnmarshaller attachmentUnmarshaller) {
        this.parent = unmarshallerImpl;
        this.next = xmlVisitor;
        this.au = attachmentUnmarshaller;
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
        if (tagName.local.equals("Include") && tagName.uri.equals(WellKnownNamespace.XOP)) {
            DataHandler attachmentAsDataHandler = this.au.getAttachmentAsDataHandler(tagName.atts.getValue("href"));
            if (attachmentAsDataHandler == null) {
                this.parent.getEventHandler().handleEvent(null);
            }
            this.base64data.set(attachmentAsDataHandler);
            this.next.text(this.base64data);
            this.inXopInclude = true;
            this.followXop = true;
            return;
        }
        this.next.startElement(tagName);
    }

    @Override // com.sun.xml.bind.v2.runtime.unmarshaller.XmlVisitor
    public void endElement(TagName tagName) throws SAXException {
        if (this.inXopInclude) {
            this.inXopInclude = false;
            this.followXop = true;
        } else {
            this.next.endElement(tagName);
        }
    }

    @Override // com.sun.xml.bind.v2.runtime.unmarshaller.XmlVisitor
    public void startPrefixMapping(String str, String str2) throws SAXException {
        this.next.startPrefixMapping(str, str2);
    }

    @Override // com.sun.xml.bind.v2.runtime.unmarshaller.XmlVisitor
    public void endPrefixMapping(String str) throws SAXException {
        this.next.endPrefixMapping(str);
    }

    @Override // com.sun.xml.bind.v2.runtime.unmarshaller.XmlVisitor
    public void text(CharSequence charSequence) throws SAXException {
        if (!this.followXop) {
            this.next.text(charSequence);
        } else {
            this.followXop = false;
        }
    }

    @Override // com.sun.xml.bind.v2.runtime.unmarshaller.XmlVisitor
    public UnmarshallingContext getContext() {
        return this.next.getContext();
    }

    @Override // com.sun.xml.bind.v2.runtime.unmarshaller.XmlVisitor
    public XmlVisitor.TextPredictor getPredictor() {
        return this.next.getPredictor();
    }
}
