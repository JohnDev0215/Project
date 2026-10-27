package com.sun.xml.bind.v2.runtime.output;

import com.sun.xml.bind.v2.WellKnownNamespace;
import com.sun.xml.bind.v2.runtime.Name;
import com.sun.xml.bind.v2.runtime.XMLSerializer;
import com.sun.xml.bind.v2.runtime.unmarshaller.Base64Data;
import java.io.IOException;
import javax.xml.stream.XMLStreamException;
import org.xml.sax.SAXException;

/* JADX INFO: loaded from: classes3.dex */
public final class MTOMXmlOutput extends XmlOutputAbstractImpl {
    private String localName;
    private final XmlOutput next;
    private String nsUri;

    public MTOMXmlOutput(XmlOutput xmlOutput) {
        this.next = xmlOutput;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // com.sun.xml.bind.v2.runtime.output.XmlOutputAbstractImpl, com.sun.xml.bind.v2.runtime.output.XmlOutput
    public void startDocument(XMLSerializer xMLSerializer, boolean z, int[] iArr, NamespaceContextImpl namespaceContextImpl) throws SAXException, XMLStreamException, IOException {
        super.startDocument(xMLSerializer, z, iArr, namespaceContextImpl);
        this.next.startDocument(xMLSerializer, z, iArr, namespaceContextImpl);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // com.sun.xml.bind.v2.runtime.output.XmlOutputAbstractImpl, com.sun.xml.bind.v2.runtime.output.XmlOutput
    public void endDocument(boolean z) throws SAXException, XMLStreamException, IOException {
        this.next.endDocument(z);
        super.endDocument(z);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // com.sun.xml.bind.v2.runtime.output.XmlOutputAbstractImpl, com.sun.xml.bind.v2.runtime.output.XmlOutput
    public void beginStartTag(Name name) throws XMLStreamException, IOException {
        this.next.beginStartTag(name);
        this.nsUri = name.nsUri;
        this.localName = name.localName;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // com.sun.xml.bind.v2.runtime.output.XmlOutputAbstractImpl, com.sun.xml.bind.v2.runtime.output.XmlOutput
    public void beginStartTag(int i, String str) throws XMLStreamException, IOException {
        this.next.beginStartTag(i, str);
        this.nsUri = this.nsContext.getNamespaceURI(i);
        this.localName = str;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // com.sun.xml.bind.v2.runtime.output.XmlOutputAbstractImpl, com.sun.xml.bind.v2.runtime.output.XmlOutput
    public void attribute(Name name, String str) throws XMLStreamException, IOException {
        this.next.attribute(name, str);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // com.sun.xml.bind.v2.runtime.output.XmlOutputAbstractImpl, com.sun.xml.bind.v2.runtime.output.XmlOutput
    public void attribute(int i, String str, String str2) throws XMLStreamException, IOException {
        this.next.attribute(i, str, str2);
    }

    @Override // com.sun.xml.bind.v2.runtime.output.XmlOutputAbstractImpl, com.sun.xml.bind.v2.runtime.output.XmlOutput
    public void endStartTag() throws SAXException, IOException {
        this.next.endStartTag();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // com.sun.xml.bind.v2.runtime.output.XmlOutputAbstractImpl, com.sun.xml.bind.v2.runtime.output.XmlOutput
    public void endTag(Name name) throws SAXException, XMLStreamException, IOException {
        this.next.endTag(name);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // com.sun.xml.bind.v2.runtime.output.XmlOutputAbstractImpl, com.sun.xml.bind.v2.runtime.output.XmlOutput
    public void endTag(int i, String str) throws SAXException, XMLStreamException, IOException {
        this.next.endTag(i, str);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // com.sun.xml.bind.v2.runtime.output.XmlOutput
    public void text(String str, boolean z) throws SAXException, XMLStreamException, IOException {
        this.next.text(str, z);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // com.sun.xml.bind.v2.runtime.output.XmlOutput
    public void text(Pcdata pcdata, boolean z) throws XMLStreamException, SAXException, IOException {
        String strAddMtomAttachment;
        if ((pcdata instanceof Base64Data) && !this.serializer.getInlineBinaryFlag()) {
            Base64Data base64Data = (Base64Data) pcdata;
            if (base64Data.hasData()) {
                strAddMtomAttachment = this.serializer.attachmentMarshaller.addMtomAttachment(base64Data.get(), 0, base64Data.getDataLen(), base64Data.getMimeType(), this.nsUri, this.localName);
            } else {
                strAddMtomAttachment = this.serializer.attachmentMarshaller.addMtomAttachment(base64Data.getDataHandler(), this.nsUri, this.localName);
            }
            if (strAddMtomAttachment != null) {
                this.nsContext.getCurrent().push();
                int iDeclareNsUri = this.nsContext.declareNsUri(WellKnownNamespace.XOP, "xop", false);
                beginStartTag(iDeclareNsUri, "Include");
                attribute(-1, "href", strAddMtomAttachment);
                endStartTag();
                endTag(iDeclareNsUri, "Include");
                this.nsContext.getCurrent().pop();
                return;
            }
        }
        this.next.text(pcdata, z);
    }
}
