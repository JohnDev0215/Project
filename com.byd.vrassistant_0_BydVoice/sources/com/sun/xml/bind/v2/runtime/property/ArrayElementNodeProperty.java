package com.sun.xml.bind.v2.runtime.property;

import com.sun.xml.bind.v2.model.runtime.RuntimeElementPropertyInfo;
import com.sun.xml.bind.v2.runtime.JAXBContextImpl;
import com.sun.xml.bind.v2.runtime.JaxBeanInfo;
import com.sun.xml.bind.v2.runtime.XMLSerializer;
import java.io.IOException;
import javax.xml.stream.XMLStreamException;
import org.xml.sax.SAXException;

/* JADX INFO: loaded from: classes3.dex */
final class ArrayElementNodeProperty<BeanT, ListT, ItemT> extends ArrayElementProperty<BeanT, ListT, ItemT> {
    public ArrayElementNodeProperty(JAXBContextImpl jAXBContextImpl, RuntimeElementPropertyInfo runtimeElementPropertyInfo) {
        super(jAXBContextImpl, runtimeElementPropertyInfo);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // com.sun.xml.bind.v2.runtime.property.ArrayElementProperty
    public void serializeItem(JaxBeanInfo jaxBeanInfo, ItemT itemt, XMLSerializer xMLSerializer) throws XMLStreamException, SAXException, IOException {
        if (itemt == null) {
            xMLSerializer.writeXsiNilTrue();
        } else {
            xMLSerializer.childAsXsiType(itemt, this.fieldName, jaxBeanInfo, false);
        }
    }
}
