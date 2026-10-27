package org.codehaus.stax2.util;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

/* JADX INFO: loaded from: classes7.dex */
public class StreamWriterDelegate implements XMLStreamWriter {
    protected XMLStreamWriter mDelegate;

    public StreamWriterDelegate(XMLStreamWriter xMLStreamWriter) {
        this.mDelegate = xMLStreamWriter;
    }

    public void close() throws XMLStreamException {
        this.mDelegate.close();
    }

    public void writeCData(String str) throws XMLStreamException {
        this.mDelegate.writeCData(str);
    }
}
