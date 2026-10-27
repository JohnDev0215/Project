package com.sun.xml.bind.v2.runtime;

import com.sun.xml.bind.api.CompositeStructure;
import com.sun.xml.bind.v2.runtime.unmarshaller.Loader;
import com.sun.xml.bind.v2.runtime.unmarshaller.UnmarshallingContext;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import javax.xml.bind.helpers.ValidationEventImpl;
import javax.xml.stream.XMLStreamException;
import org.xml.sax.SAXException;

/* JADX INFO: loaded from: classes3.dex */
public class CompositeStructureBeanInfo extends JaxBeanInfo<CompositeStructure> {
    @Override // com.sun.xml.bind.v2.runtime.JaxBeanInfo
    public String getId(CompositeStructure compositeStructure, XMLSerializer xMLSerializer) throws SAXException {
        return null;
    }

    @Override // com.sun.xml.bind.v2.runtime.JaxBeanInfo
    public Transducer<CompositeStructure> getTransducer() {
        return null;
    }

    @Override // com.sun.xml.bind.v2.runtime.JaxBeanInfo
    public void serializeAttributes(CompositeStructure compositeStructure, XMLSerializer xMLSerializer) throws SAXException, XMLStreamException, IOException {
    }

    @Override // com.sun.xml.bind.v2.runtime.JaxBeanInfo
    public void serializeURIs(CompositeStructure compositeStructure, XMLSerializer xMLSerializer) throws SAXException {
    }

    public CompositeStructureBeanInfo(JAXBContextImpl jAXBContextImpl) {
        super(jAXBContextImpl, null, CompositeStructure.class, false, true, false);
    }

    @Override // com.sun.xml.bind.v2.runtime.JaxBeanInfo
    public String getElementNamespaceURI(CompositeStructure compositeStructure) {
        throw new UnsupportedOperationException();
    }

    @Override // com.sun.xml.bind.v2.runtime.JaxBeanInfo
    public String getElementLocalName(CompositeStructure compositeStructure) {
        throw new UnsupportedOperationException();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.sun.xml.bind.v2.runtime.JaxBeanInfo
    public CompositeStructure createInstance(UnmarshallingContext unmarshallingContext) throws IllegalAccessException, SAXException, InstantiationException, InvocationTargetException {
        throw new UnsupportedOperationException();
    }

    @Override // com.sun.xml.bind.v2.runtime.JaxBeanInfo
    public boolean reset(CompositeStructure compositeStructure, UnmarshallingContext unmarshallingContext) throws SAXException {
        throw new UnsupportedOperationException();
    }

    @Override // com.sun.xml.bind.v2.runtime.JaxBeanInfo
    public Loader getLoader(JAXBContextImpl jAXBContextImpl, boolean z) {
        throw new UnsupportedOperationException();
    }

    @Override // com.sun.xml.bind.v2.runtime.JaxBeanInfo
    public void serializeRoot(CompositeStructure compositeStructure, XMLSerializer xMLSerializer) throws SAXException, XMLStreamException, IOException {
        xMLSerializer.reportError(new ValidationEventImpl(1, Messages.UNABLE_TO_MARSHAL_NON_ELEMENT.format(compositeStructure.getClass().getName()), null, null));
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // com.sun.xml.bind.v2.runtime.JaxBeanInfo
    public void serializeBody(CompositeStructure compositeStructure, XMLSerializer xMLSerializer) throws SAXException, XMLStreamException, IOException {
        int length = compositeStructure.bridges.length;
        for (int i = 0; i < length; i++) {
            ((InternalBridge) compositeStructure.bridges[i]).marshal(compositeStructure.values[i], xMLSerializer);
        }
    }
}
