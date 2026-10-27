package com.sun.xml.bind.v2.runtime;

import com.sun.xml.bind.api.AccessorException;
import java.io.IOException;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamException;
import org.xml.sax.SAXException;

/* JADX INFO: loaded from: classes3.dex */
public abstract class FilterTransducer<T> implements Transducer<T> {
    protected final Transducer<T> core;

    @Override // com.sun.xml.bind.v2.runtime.Transducer
    public QName getTypeName(T t) {
        return null;
    }

    @Override // com.sun.xml.bind.v2.runtime.Transducer
    public final boolean isDefault() {
        return false;
    }

    protected FilterTransducer(Transducer<T> transducer) {
        this.core = transducer;
    }

    @Override // com.sun.xml.bind.v2.runtime.Transducer
    public boolean useNamespace() {
        return this.core.useNamespace();
    }

    @Override // com.sun.xml.bind.v2.runtime.Transducer
    public void declareNamespace(T t, XMLSerializer xMLSerializer) throws AccessorException {
        this.core.declareNamespace(t, xMLSerializer);
    }

    @Override // com.sun.xml.bind.v2.runtime.Transducer
    public CharSequence print(T t) throws AccessorException {
        return this.core.print(t);
    }

    @Override // com.sun.xml.bind.v2.runtime.Transducer
    public T parse(CharSequence charSequence) throws SAXException, AccessorException {
        return this.core.parse(charSequence);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // com.sun.xml.bind.v2.runtime.Transducer
    public void writeText(XMLSerializer xMLSerializer, T t, String str) throws SAXException, XMLStreamException, IOException, AccessorException {
        this.core.writeText(xMLSerializer, t, str);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // com.sun.xml.bind.v2.runtime.Transducer
    public void writeLeafElement(XMLSerializer xMLSerializer, Name name, T t, String str) throws SAXException, XMLStreamException, IOException, AccessorException {
        this.core.writeLeafElement(xMLSerializer, name, t, str);
    }
}
