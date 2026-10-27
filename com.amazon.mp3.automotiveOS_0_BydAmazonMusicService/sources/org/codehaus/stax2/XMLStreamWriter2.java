package org.codehaus.stax2;

import javax.xml.stream.XMLStreamException;
import org.codehaus.stax2.typed.TypedXMLStreamWriter;

/* JADX INFO: loaded from: classes7.dex */
public interface XMLStreamWriter2 extends TypedXMLStreamWriter {
    void closeCompletely() throws XMLStreamException;

    void writeCData(char[] cArr, int i, int i2) throws XMLStreamException;

    void writeRaw(String str) throws XMLStreamException;

    void writeRaw(String str, int i, int i2) throws XMLStreamException;

    void writeRaw(char[] cArr, int i, int i2) throws XMLStreamException;
}
