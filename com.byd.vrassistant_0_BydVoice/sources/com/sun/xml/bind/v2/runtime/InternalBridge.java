package com.sun.xml.bind.v2.runtime;

import com.sun.xml.bind.api.Bridge;
import java.io.IOException;
import javax.xml.stream.XMLStreamException;
import org.xml.sax.SAXException;

/* JADX INFO: loaded from: classes3.dex */
abstract class InternalBridge<T> extends Bridge<T> {
    abstract void marshal(T t, XMLSerializer xMLSerializer) throws SAXException, XMLStreamException, IOException;

    protected InternalBridge(JAXBContextImpl jAXBContextImpl) {
        super(jAXBContextImpl);
    }

    @Override // com.sun.xml.bind.api.Bridge
    public JAXBContextImpl getContext() {
        return this.context;
    }
}
