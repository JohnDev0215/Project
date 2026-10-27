package com.sun.xml.bind.v2.runtime.property;

import com.sun.xml.bind.api.AccessorException;
import com.sun.xml.bind.v2.model.runtime.RuntimeElementPropertyInfo;
import com.sun.xml.bind.v2.model.runtime.RuntimeTypeRef;
import com.sun.xml.bind.v2.runtime.JAXBContextImpl;
import com.sun.xml.bind.v2.runtime.JaxBeanInfo;
import com.sun.xml.bind.v2.runtime.Transducer;
import com.sun.xml.bind.v2.runtime.XMLSerializer;
import java.io.IOException;
import javax.xml.stream.XMLStreamException;
import org.xml.sax.SAXException;

/* JADX INFO: loaded from: classes3.dex */
final class ArrayElementLeafProperty<BeanT, ListT, ItemT> extends ArrayElementProperty<BeanT, ListT, ItemT> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final Transducer<ItemT> xducer;

    public ArrayElementLeafProperty(JAXBContextImpl jAXBContextImpl, RuntimeElementPropertyInfo runtimeElementPropertyInfo) {
        super(jAXBContextImpl, runtimeElementPropertyInfo);
        this.xducer = ((RuntimeTypeRef) runtimeElementPropertyInfo.getTypes().get(0)).getTransducer();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // com.sun.xml.bind.v2.runtime.property.ArrayElementProperty
    public void serializeItem(JaxBeanInfo jaxBeanInfo, ItemT itemt, XMLSerializer xMLSerializer) throws XMLStreamException, SAXException, IOException, AccessorException {
        this.xducer.declareNamespace(itemt, xMLSerializer);
        xMLSerializer.endNamespaceDecls(itemt);
        xMLSerializer.endAttributes();
        this.xducer.writeText(xMLSerializer, itemt, this.fieldName);
    }
}
