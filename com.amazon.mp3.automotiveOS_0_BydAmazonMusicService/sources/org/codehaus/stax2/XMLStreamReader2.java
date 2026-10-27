package org.codehaus.stax2;

import javax.xml.stream.XMLStreamException;
import org.codehaus.stax2.typed.TypedXMLStreamReader;

/* JADX INFO: loaded from: classes7.dex */
public interface XMLStreamReader2 extends TypedXMLStreamReader {
    void closeCompletely() throws XMLStreamException;

    int getDepth();

    @Override // org.codehaus.stax2.typed.TypedXMLStreamReader
    /* synthetic */ double getElementAsDouble() throws XMLStreamException;

    @Override // org.codehaus.stax2.typed.TypedXMLStreamReader
    /* synthetic */ float getElementAsFloat() throws XMLStreamException;

    @Override // org.codehaus.stax2.typed.TypedXMLStreamReader
    /* synthetic */ int getElementAsInt() throws XMLStreamException;

    LocationInfo getLocationInfo();

    boolean isEmptyElement() throws XMLStreamException;
}
