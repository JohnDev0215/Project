package com.sun.xml.bind.v2.runtime.output;

import com.sun.xml.bind.v2.runtime.XMLSerializer;
import com.sun.xml.bind.v2.runtime.output.NamespaceContextImpl;
import java.io.IOException;
import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.Characters;
import org.xml.sax.SAXException;

/* JADX INFO: loaded from: classes3.dex */
public class XMLEventWriterOutput extends XmlOutputAbstractImpl {
    private final XMLEventFactory ef;
    private final XMLEventWriter out;
    private final Characters sp;

    @Override // com.sun.xml.bind.v2.runtime.output.XmlOutputAbstractImpl, com.sun.xml.bind.v2.runtime.output.XmlOutput
    public void endStartTag() throws SAXException, IOException {
    }

    public XMLEventWriterOutput(XMLEventWriter xMLEventWriter) {
        this.out = xMLEventWriter;
        XMLEventFactory xMLEventFactoryNewInstance = XMLEventFactory.newInstance();
        this.ef = xMLEventFactoryNewInstance;
        this.sp = xMLEventFactoryNewInstance.createCharacters(" ");
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // com.sun.xml.bind.v2.runtime.output.XmlOutputAbstractImpl, com.sun.xml.bind.v2.runtime.output.XmlOutput
    public void startDocument(XMLSerializer xMLSerializer, boolean z, int[] iArr, NamespaceContextImpl namespaceContextImpl) throws SAXException, XMLStreamException, IOException {
        super.startDocument(xMLSerializer, z, iArr, namespaceContextImpl);
        if (z) {
            return;
        }
        this.out.add(this.ef.createStartDocument());
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // com.sun.xml.bind.v2.runtime.output.XmlOutputAbstractImpl, com.sun.xml.bind.v2.runtime.output.XmlOutput
    public void endDocument(boolean z) throws SAXException, XMLStreamException, IOException {
        if (!z) {
            this.out.add(this.ef.createEndDocument());
            this.out.flush();
        }
        super.endDocument(z);
    }

    @Override // com.sun.xml.bind.v2.runtime.output.XmlOutputAbstractImpl, com.sun.xml.bind.v2.runtime.output.XmlOutput
    public void beginStartTag(int i, String str) throws XMLStreamException, IOException {
        this.out.add(this.ef.createStartElement(this.nsContext.getPrefix(i), this.nsContext.getNamespaceURI(i), str));
        NamespaceContextImpl.Element current = this.nsContext.getCurrent();
        if (current.count() > 0) {
            for (int iCount = current.count() - 1; iCount >= 0; iCount--) {
                String nsUri = current.getNsUri(iCount);
                if (nsUri.length() != 0 || current.getBase() != 1) {
                    this.out.add(this.ef.createNamespace(current.getPrefix(iCount), nsUri));
                }
            }
        }
    }

    @Override // com.sun.xml.bind.v2.runtime.output.XmlOutputAbstractImpl, com.sun.xml.bind.v2.runtime.output.XmlOutput
    public void attribute(int i, String str, String str2) throws XMLStreamException, IOException {
        Attribute attributeCreateAttribute;
        if (i == -1) {
            attributeCreateAttribute = this.ef.createAttribute(str, str2);
        } else {
            attributeCreateAttribute = this.ef.createAttribute(this.nsContext.getPrefix(i), this.nsContext.getNamespaceURI(i), str, str2);
        }
        this.out.add(attributeCreateAttribute);
    }

    @Override // com.sun.xml.bind.v2.runtime.output.XmlOutputAbstractImpl, com.sun.xml.bind.v2.runtime.output.XmlOutput
    public void endTag(int i, String str) throws SAXException, XMLStreamException, IOException {
        this.out.add(this.ef.createEndElement(this.nsContext.getPrefix(i), this.nsContext.getNamespaceURI(i), str));
    }

    @Override // com.sun.xml.bind.v2.runtime.output.XmlOutput
    public void text(String str, boolean z) throws SAXException, XMLStreamException, IOException {
        if (z) {
            this.out.add(this.sp);
        }
        this.out.add(this.ef.createCharacters(str));
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // com.sun.xml.bind.v2.runtime.output.XmlOutput
    public void text(Pcdata pcdata, boolean z) throws SAXException, XMLStreamException, IOException {
        text(pcdata.toString(), z);
    }
}
