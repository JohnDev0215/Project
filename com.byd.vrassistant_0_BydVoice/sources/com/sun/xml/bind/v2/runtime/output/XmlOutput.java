package com.sun.xml.bind.v2.runtime.output;

import com.sun.xml.bind.v2.runtime.Name;
import com.sun.xml.bind.v2.runtime.XMLSerializer;
import java.io.IOException;
import javax.xml.stream.XMLStreamException;
import org.xml.sax.SAXException;

/* JADX INFO: loaded from: classes3.dex */
public interface XmlOutput {
    void attribute(int i, String str, String str2) throws XMLStreamException, IOException;

    void attribute(Name name, String str) throws XMLStreamException, IOException;

    void beginStartTag(int i, String str) throws XMLStreamException, IOException;

    void beginStartTag(Name name) throws XMLStreamException, IOException;

    void endDocument(boolean z) throws SAXException, XMLStreamException, IOException;

    void endStartTag() throws SAXException, IOException;

    void endTag(int i, String str) throws SAXException, XMLStreamException, IOException;

    void endTag(Name name) throws SAXException, XMLStreamException, IOException;

    void startDocument(XMLSerializer xMLSerializer, boolean z, int[] iArr, NamespaceContextImpl namespaceContextImpl) throws SAXException, XMLStreamException, IOException;

    void text(Pcdata pcdata, boolean z) throws SAXException, XMLStreamException, IOException;

    void text(String str, boolean z) throws SAXException, XMLStreamException, IOException;
}
