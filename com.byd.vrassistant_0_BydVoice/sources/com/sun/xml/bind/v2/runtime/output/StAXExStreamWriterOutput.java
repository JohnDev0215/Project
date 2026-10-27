package com.sun.xml.bind.v2.runtime.output;

import com.sun.xml.bind.marshaller.NoEscapeHandler;
import com.sun.xml.bind.v2.runtime.unmarshaller.Base64Data;
import javax.xml.stream.XMLStreamException;
import org.jvnet.staxex.XMLStreamWriterEx;

/* JADX INFO: loaded from: classes3.dex */
public final class StAXExStreamWriterOutput extends XMLStreamWriterOutput {
    private final XMLStreamWriterEx out;

    public StAXExStreamWriterOutput(XMLStreamWriterEx xMLStreamWriterEx) {
        super(xMLStreamWriterEx, NoEscapeHandler.theInstance);
        this.out = xMLStreamWriterEx;
    }

    @Override // com.sun.xml.bind.v2.runtime.output.XMLStreamWriterOutput, com.sun.xml.bind.v2.runtime.output.XmlOutput
    public void text(Pcdata pcdata, boolean z) throws XMLStreamException {
        if (z) {
            this.out.writeCharacters(" ");
        }
        if (!(pcdata instanceof Base64Data)) {
            this.out.writeCharacters(pcdata.toString());
        } else {
            this.out.writeBinary(((Base64Data) pcdata).getDataHandler());
        }
    }
}
