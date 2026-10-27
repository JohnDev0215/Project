package com.sun.xml.bind.v2.runtime;

import com.sun.xml.bind.api.AccessorException;
import java.io.IOException;
import javax.xml.stream.XMLStreamException;
import org.xml.sax.SAXException;

/* JADX INFO: loaded from: classes3.dex */
public class InlineBinaryTransducer<V> extends FilterTransducer<V> {
    public InlineBinaryTransducer(Transducer<V> transducer) {
        super(transducer);
    }

    /* JADX WARN: Type inference incomplete: some casts might be missing */
    @Override // com.sun.xml.bind.v2.runtime.FilterTransducer, com.sun.xml.bind.v2.runtime.Transducer
    public CharSequence print(V v) throws AccessorException {
        XMLSerializer xMLSerializer = XMLSerializer.getInstance();
        boolean inlineBinaryFlag = xMLSerializer.setInlineBinaryFlag(true);
        try {
            return this.core.print((T) v);
        } finally {
            xMLSerializer.setInlineBinaryFlag(inlineBinaryFlag);
        }
    }

    /* JADX WARN: Type inference incomplete: some casts might be missing */
    @Override // com.sun.xml.bind.v2.runtime.FilterTransducer, com.sun.xml.bind.v2.runtime.Transducer
    public void writeText(XMLSerializer xMLSerializer, V v, String str) throws SAXException, XMLStreamException, IOException, AccessorException {
        boolean inlineBinaryFlag = xMLSerializer.setInlineBinaryFlag(true);
        try {
            this.core.writeText(xMLSerializer, (T) v, str);
        } finally {
            xMLSerializer.setInlineBinaryFlag(inlineBinaryFlag);
        }
    }

    /* JADX WARN: Type inference incomplete: some casts might be missing */
    @Override // com.sun.xml.bind.v2.runtime.FilterTransducer, com.sun.xml.bind.v2.runtime.Transducer
    public void writeLeafElement(XMLSerializer xMLSerializer, Name name, V v, String str) throws SAXException, XMLStreamException, IOException, AccessorException {
        boolean inlineBinaryFlag = xMLSerializer.setInlineBinaryFlag(true);
        try {
            this.core.writeLeafElement(xMLSerializer, name, (T) v, str);
        } finally {
            xMLSerializer.setInlineBinaryFlag(inlineBinaryFlag);
        }
    }
}
