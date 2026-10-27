package com.sun.xml.bind.v2.runtime;

import com.sun.xml.bind.api.AccessorException;
import java.io.IOException;
import javax.activation.MimeType;
import javax.xml.stream.XMLStreamException;
import org.xml.sax.SAXException;

/* JADX INFO: loaded from: classes3.dex */
public final class MimeTypedTransducer<V> extends FilterTransducer<V> {
    private final MimeType expectedMimeType;

    public MimeTypedTransducer(Transducer<V> transducer, MimeType mimeType) {
        super(transducer);
        this.expectedMimeType = mimeType;
    }

    /* JADX WARN: Type inference incomplete: some casts might be missing */
    @Override // com.sun.xml.bind.v2.runtime.FilterTransducer, com.sun.xml.bind.v2.runtime.Transducer
    public CharSequence print(V v) throws AccessorException {
        XMLSerializer xMLSerializer = XMLSerializer.getInstance();
        MimeType expectedMimeType = xMLSerializer.setExpectedMimeType(this.expectedMimeType);
        try {
            return this.core.print((T) v);
        } finally {
            xMLSerializer.setExpectedMimeType(expectedMimeType);
        }
    }

    /* JADX WARN: Type inference incomplete: some casts might be missing */
    @Override // com.sun.xml.bind.v2.runtime.FilterTransducer, com.sun.xml.bind.v2.runtime.Transducer
    public void writeText(XMLSerializer xMLSerializer, V v, String str) throws SAXException, XMLStreamException, IOException, AccessorException {
        MimeType expectedMimeType = xMLSerializer.setExpectedMimeType(this.expectedMimeType);
        try {
            this.core.writeText(xMLSerializer, (T) v, str);
        } finally {
            xMLSerializer.setExpectedMimeType(expectedMimeType);
        }
    }

    /* JADX WARN: Type inference incomplete: some casts might be missing */
    @Override // com.sun.xml.bind.v2.runtime.FilterTransducer, com.sun.xml.bind.v2.runtime.Transducer
    public void writeLeafElement(XMLSerializer xMLSerializer, Name name, V v, String str) throws SAXException, XMLStreamException, IOException, AccessorException {
        MimeType expectedMimeType = xMLSerializer.setExpectedMimeType(this.expectedMimeType);
        try {
            this.core.writeLeafElement(xMLSerializer, name, (T) v, str);
        } finally {
            xMLSerializer.setExpectedMimeType(expectedMimeType);
        }
    }
}
