package com.sun.xml.bind.v2.runtime.reflect;

import com.sun.xml.bind.api.AccessorException;
import com.sun.xml.bind.v2.runtime.Name;
import com.sun.xml.bind.v2.runtime.XMLSerializer;
import java.io.IOException;
import javax.xml.stream.XMLStreamException;
import org.xml.sax.SAXException;

/* JADX INFO: loaded from: classes3.dex */
public abstract class DefaultTransducedAccessor<T> extends TransducedAccessor<T> {
    @Override // com.sun.xml.bind.v2.runtime.reflect.TransducedAccessor
    public abstract String print(T t) throws SAXException, AccessorException;

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // com.sun.xml.bind.v2.runtime.reflect.TransducedAccessor
    public void writeLeafElement(XMLSerializer xMLSerializer, Name name, T t, String str) throws XMLStreamException, SAXException, IOException, AccessorException {
        xMLSerializer.leafElement(name, print((Object) t), str);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // com.sun.xml.bind.v2.runtime.reflect.TransducedAccessor
    public void writeText(XMLSerializer xMLSerializer, T t, String str) throws SAXException, XMLStreamException, IOException, AccessorException {
        xMLSerializer.text(print((Object) t), str);
    }
}
