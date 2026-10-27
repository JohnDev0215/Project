package org.codehaus.stax2.typed;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

/* JADX INFO: loaded from: classes7.dex */
public interface TypedXMLStreamReader extends XMLStreamReader {
    double getElementAsDouble() throws XMLStreamException;

    float getElementAsFloat() throws XMLStreamException;

    int getElementAsInt() throws XMLStreamException;
}
