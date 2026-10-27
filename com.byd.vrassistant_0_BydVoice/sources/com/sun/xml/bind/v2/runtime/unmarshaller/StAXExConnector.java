package com.sun.xml.bind.v2.runtime.unmarshaller;

import javax.xml.stream.XMLStreamException;
import org.jvnet.staxex.XMLStreamReaderEx;
import org.xml.sax.SAXException;

/* JADX INFO: loaded from: classes3.dex */
final class StAXExConnector extends StAXStreamConnector {
    private final XMLStreamReaderEx in;

    public StAXExConnector(XMLStreamReaderEx xMLStreamReaderEx, XmlVisitor xmlVisitor) {
        super(xMLStreamReaderEx, xmlVisitor);
        this.in = xMLStreamReaderEx;
    }

    @Override // com.sun.xml.bind.v2.runtime.unmarshaller.StAXStreamConnector
    protected void handleCharacters() throws SAXException, XMLStreamException {
        if (this.predictor.expectText()) {
            org.jvnet.staxex.Base64Data pcdata = this.in.getPCDATA();
            if (pcdata instanceof org.jvnet.staxex.Base64Data) {
                org.jvnet.staxex.Base64Data base64Data = pcdata;
                Base64Data base64Data2 = new Base64Data();
                if (!base64Data.hasData()) {
                    base64Data2.set(base64Data.getDataHandler());
                } else {
                    base64Data2.set(base64Data.get(), base64Data.getDataLen(), base64Data.getMimeType());
                }
                this.visitor.text(base64Data2);
                this.textReported = true;
                return;
            }
            this.buffer.append((CharSequence) pcdata);
        }
    }
}
