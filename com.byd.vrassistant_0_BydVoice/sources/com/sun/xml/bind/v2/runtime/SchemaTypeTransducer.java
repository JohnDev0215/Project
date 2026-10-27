package com.sun.xml.bind.v2.runtime;

import com.sun.xml.bind.api.AccessorException;
import java.io.IOException;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamException;
import org.xml.sax.SAXException;

/* JADX INFO: loaded from: classes3.dex */
public class SchemaTypeTransducer<V> extends FilterTransducer<V> {
    private final QName schemaType;

    public SchemaTypeTransducer(Transducer<V> transducer, QName qName) {
        super(transducer);
        this.schemaType = qName;
    }

    /* JADX WARN: Type inference incomplete: some casts might be missing */
    @Override // com.sun.xml.bind.v2.runtime.FilterTransducer, com.sun.xml.bind.v2.runtime.Transducer
    public CharSequence print(V v) throws AccessorException {
        XMLSerializer xMLSerializer = XMLSerializer.getInstance();
        QName schemaType = xMLSerializer.setSchemaType(this.schemaType);
        try {
            return this.core.print((T) v);
        } finally {
            xMLSerializer.setSchemaType(schemaType);
        }
    }

    /* JADX WARN: Type inference incomplete: some casts might be missing */
    @Override // com.sun.xml.bind.v2.runtime.FilterTransducer, com.sun.xml.bind.v2.runtime.Transducer
    public void writeText(XMLSerializer xMLSerializer, V v, String str) throws SAXException, XMLStreamException, IOException, AccessorException {
        QName schemaType = xMLSerializer.setSchemaType(this.schemaType);
        try {
            this.core.writeText(xMLSerializer, (T) v, str);
        } finally {
            xMLSerializer.setSchemaType(schemaType);
        }
    }

    /* JADX WARN: Type inference incomplete: some casts might be missing */
    @Override // com.sun.xml.bind.v2.runtime.FilterTransducer, com.sun.xml.bind.v2.runtime.Transducer
    public void writeLeafElement(XMLSerializer xMLSerializer, Name name, V v, String str) throws SAXException, XMLStreamException, IOException, AccessorException {
        QName schemaType = xMLSerializer.setSchemaType(this.schemaType);
        try {
            this.core.writeLeafElement(xMLSerializer, name, (T) v, str);
        } finally {
            xMLSerializer.setSchemaType(schemaType);
        }
    }
}
