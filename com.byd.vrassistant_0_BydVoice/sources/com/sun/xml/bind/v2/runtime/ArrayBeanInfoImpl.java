package com.sun.xml.bind.v2.runtime;

import com.sun.xml.bind.v2.model.runtime.RuntimeArrayInfo;
import com.sun.xml.bind.v2.model.runtime.RuntimeTypeInfo;
import com.sun.xml.bind.v2.runtime.unmarshaller.Loader;
import com.sun.xml.bind.v2.runtime.unmarshaller.Receiver;
import com.sun.xml.bind.v2.runtime.unmarshaller.TagName;
import com.sun.xml.bind.v2.runtime.unmarshaller.UnmarshallingContext;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import javax.xml.bind.helpers.ValidationEventImpl;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamException;
import org.xml.sax.SAXException;

/* JADX INFO: loaded from: classes3.dex */
final class ArrayBeanInfoImpl extends JaxBeanInfo {
    private final JaxBeanInfo itemBeanInfo;
    private final Class itemType;
    private Loader loader;

    @Override // com.sun.xml.bind.v2.runtime.JaxBeanInfo
    public final String getId(Object obj, XMLSerializer xMLSerializer) {
        return null;
    }

    @Override // com.sun.xml.bind.v2.runtime.JaxBeanInfo
    public final Transducer getTransducer() {
        return null;
    }

    @Override // com.sun.xml.bind.v2.runtime.JaxBeanInfo
    public final boolean reset(Object obj, UnmarshallingContext unmarshallingContext) {
        return false;
    }

    @Override // com.sun.xml.bind.v2.runtime.JaxBeanInfo
    public final void serializeAttributes(Object obj, XMLSerializer xMLSerializer) {
    }

    @Override // com.sun.xml.bind.v2.runtime.JaxBeanInfo
    public final void serializeURIs(Object obj, XMLSerializer xMLSerializer) {
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r10v1, types: [com.sun.xml.bind.v2.model.runtime.RuntimeNonElement, com.sun.xml.bind.v2.model.runtime.RuntimeTypeInfo] */
    public ArrayBeanInfoImpl(JAXBContextImpl jAXBContextImpl, RuntimeArrayInfo runtimeArrayInfo) {
        super(jAXBContextImpl, (RuntimeTypeInfo) runtimeArrayInfo, runtimeArrayInfo.getType2(), runtimeArrayInfo.getTypeName(), false, true, false);
        this.itemType = this.jaxbType.getComponentType();
        this.itemBeanInfo = jAXBContextImpl.getOrCreate((RuntimeTypeInfo) runtimeArrayInfo.getItemType());
    }

    @Override // com.sun.xml.bind.v2.runtime.JaxBeanInfo
    protected void link(JAXBContextImpl jAXBContextImpl) {
        getLoader(jAXBContextImpl, false);
        super.link(jAXBContextImpl);
    }

    private final class ArrayLoader extends Loader implements Receiver {
        private final Loader itemLoader;

        public ArrayLoader(JAXBContextImpl jAXBContextImpl) {
            super(false);
            this.itemLoader = ArrayBeanInfoImpl.this.itemBeanInfo.getLoader(jAXBContextImpl, true);
        }

        @Override // com.sun.xml.bind.v2.runtime.unmarshaller.Loader
        public void startElement(UnmarshallingContext.State state, TagName tagName) {
            state.setTarget(new ArrayList());
        }

        @Override // com.sun.xml.bind.v2.runtime.unmarshaller.Loader
        public void leaveElement(UnmarshallingContext.State state, TagName tagName) {
            state.setTarget(ArrayBeanInfoImpl.this.toArray((List) state.getTarget()));
        }

        @Override // com.sun.xml.bind.v2.runtime.unmarshaller.Loader
        public void childElement(UnmarshallingContext.State state, TagName tagName) throws SAXException {
            if (tagName.matches("", "item")) {
                state.setLoader(this.itemLoader);
                state.setReceiver(this);
            } else {
                super.childElement(state, tagName);
            }
        }

        @Override // com.sun.xml.bind.v2.runtime.unmarshaller.Loader
        public Collection<QName> getExpectedChildElements() {
            return Collections.singleton(new QName("", "item"));
        }

        @Override // com.sun.xml.bind.v2.runtime.unmarshaller.Receiver
        public void receive(UnmarshallingContext.State state, Object obj) {
            ((List) state.getTarget()).add(obj);
        }
    }

    protected Object toArray(List list) {
        int size = list.size();
        Object objNewInstance = Array.newInstance((Class<?>) this.itemType, size);
        for (int i = 0; i < size; i++) {
            Array.set(objNewInstance, i, list.get(i));
        }
        return objNewInstance;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // com.sun.xml.bind.v2.runtime.JaxBeanInfo
    public void serializeBody(Object obj, XMLSerializer xMLSerializer) throws XMLStreamException, SAXException, IOException {
        int length = Array.getLength(obj);
        for (int i = 0; i < length; i++) {
            Object obj2 = Array.get(obj, i);
            xMLSerializer.startElement("", "item", null, null);
            if (obj2 == null) {
                xMLSerializer.writeXsiNilTrue();
            } else {
                xMLSerializer.childAsXsiType(obj2, "arrayItem", this.itemBeanInfo, false);
            }
            xMLSerializer.endElement();
        }
    }

    @Override // com.sun.xml.bind.v2.runtime.JaxBeanInfo
    public final String getElementNamespaceURI(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // com.sun.xml.bind.v2.runtime.JaxBeanInfo
    public final String getElementLocalName(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // com.sun.xml.bind.v2.runtime.JaxBeanInfo
    public final Object createInstance(UnmarshallingContext unmarshallingContext) {
        return new ArrayList();
    }

    @Override // com.sun.xml.bind.v2.runtime.JaxBeanInfo
    public final void serializeRoot(Object obj, XMLSerializer xMLSerializer) throws SAXException, XMLStreamException, IOException {
        xMLSerializer.reportError(new ValidationEventImpl(1, Messages.UNABLE_TO_MARSHAL_NON_ELEMENT.format(obj.getClass().getName()), null, null));
    }

    @Override // com.sun.xml.bind.v2.runtime.JaxBeanInfo
    public final Loader getLoader(JAXBContextImpl jAXBContextImpl, boolean z) {
        if (this.loader == null) {
            this.loader = new ArrayLoader(jAXBContextImpl);
        }
        return this.loader;
    }
}
