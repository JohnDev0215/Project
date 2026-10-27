package com.sun.xml.bind.v2.runtime.output;

import com.sun.xml.bind.v2.runtime.Name;
import com.sun.xml.bind.v2.runtime.XMLSerializer;
import java.io.IOException;
import javax.xml.stream.XMLStreamException;
import org.xml.sax.SAXException;

/* JADX INFO: loaded from: classes3.dex */
public abstract class XmlOutputAbstractImpl implements XmlOutput {
    protected NamespaceContextImpl nsContext;
    protected int[] nsUriIndex2prefixIndex;
    protected XMLSerializer serializer;

    @Override // com.sun.xml.bind.v2.runtime.output.XmlOutput
    public abstract void attribute(int i, String str, String str2) throws XMLStreamException, IOException;

    @Override // com.sun.xml.bind.v2.runtime.output.XmlOutput
    public abstract void beginStartTag(int i, String str) throws XMLStreamException, IOException;

    @Override // com.sun.xml.bind.v2.runtime.output.XmlOutput
    public abstract void endStartTag() throws SAXException, IOException;

    @Override // com.sun.xml.bind.v2.runtime.output.XmlOutput
    public abstract void endTag(int i, String str) throws SAXException, XMLStreamException, IOException;

    @Override // com.sun.xml.bind.v2.runtime.output.XmlOutput
    public void startDocument(XMLSerializer xMLSerializer, boolean z, int[] iArr, NamespaceContextImpl namespaceContextImpl) throws SAXException, XMLStreamException, IOException {
        this.nsUriIndex2prefixIndex = iArr;
        this.nsContext = namespaceContextImpl;
        this.serializer = xMLSerializer;
    }

    @Override // com.sun.xml.bind.v2.runtime.output.XmlOutput
    public void endDocument(boolean z) throws SAXException, XMLStreamException, IOException {
        this.serializer = null;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // com.sun.xml.bind.v2.runtime.output.XmlOutput
    public void beginStartTag(Name name) throws XMLStreamException, IOException {
        beginStartTag(this.nsUriIndex2prefixIndex[name.nsUriIndex], name.localName);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // com.sun.xml.bind.v2.runtime.output.XmlOutput
    public void attribute(Name name, String str) throws XMLStreamException, IOException {
        short s = name.nsUriIndex;
        if (s == -1) {
            attribute(-1, name.localName, str);
        } else {
            attribute(this.nsUriIndex2prefixIndex[s], name.localName, str);
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // com.sun.xml.bind.v2.runtime.output.XmlOutput
    public void endTag(Name name) throws SAXException, XMLStreamException, IOException {
        endTag(this.nsUriIndex2prefixIndex[name.nsUriIndex], name.localName);
    }
}
