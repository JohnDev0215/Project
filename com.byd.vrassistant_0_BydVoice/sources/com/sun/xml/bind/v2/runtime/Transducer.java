package com.sun.xml.bind.v2.runtime;

import com.sun.xml.bind.api.AccessorException;
import java.io.IOException;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamException;
import org.xml.sax.SAXException;

/* JADX INFO: loaded from: classes3.dex */
public interface Transducer<ValueT> {
    void declareNamespace(ValueT valuet, XMLSerializer xMLSerializer) throws AccessorException;

    QName getTypeName(ValueT valuet);

    boolean isDefault();

    ValueT parse(CharSequence charSequence) throws SAXException, AccessorException;

    CharSequence print(ValueT valuet) throws AccessorException;

    boolean useNamespace();

    void writeLeafElement(XMLSerializer xMLSerializer, Name name, ValueT valuet, String str) throws SAXException, XMLStreamException, IOException, AccessorException;

    void writeText(XMLSerializer xMLSerializer, ValueT valuet, String str) throws SAXException, XMLStreamException, IOException, AccessorException;
}
