package com.sun.xml.bind.v2.runtime;

import com.sun.istack.FinalArrayList;
import com.sun.xml.bind.WhiteSpaceProcessor;
import com.sun.xml.bind.api.AccessorException;
import com.sun.xml.bind.v2.runtime.unmarshaller.Loader;
import com.sun.xml.bind.v2.runtime.unmarshaller.UnmarshallingContext;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.List;
import javax.xml.bind.JAXBException;
import javax.xml.bind.helpers.ValidationEventImpl;
import javax.xml.stream.XMLStreamException;
import org.xml.sax.SAXException;

/* JADX INFO: loaded from: classes3.dex */
final class ValueListBeanInfoImpl extends JaxBeanInfo {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final Class itemType;
    private final Loader loader;
    private final Transducer xducer;

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

    public ValueListBeanInfoImpl(JAXBContextImpl jAXBContextImpl, Class cls) throws JAXBException {
        super(jAXBContextImpl, null, cls, false, true, false);
        this.loader = new Loader(true) { // from class: com.sun.xml.bind.v2.runtime.ValueListBeanInfoImpl.1
            @Override // com.sun.xml.bind.v2.runtime.unmarshaller.Loader
            public void text(UnmarshallingContext.State state, CharSequence charSequence) throws SAXException {
                FinalArrayList finalArrayList = new FinalArrayList();
                int length = charSequence.length();
                int i = 0;
                while (true) {
                    int i2 = i;
                    while (i2 < length && !WhiteSpaceProcessor.isWhiteSpace(charSequence.charAt(i2))) {
                        i2++;
                    }
                    CharSequence charSequenceSubSequence = charSequence.subSequence(i, i2);
                    if (!charSequenceSubSequence.equals("")) {
                        try {
                            finalArrayList.add(ValueListBeanInfoImpl.this.xducer.parse(charSequenceSubSequence));
                        } catch (AccessorException e) {
                            handleGenericException(e, true);
                        }
                    }
                    if (i2 == length) {
                        break;
                    }
                    i = i2;
                    while (i < length && WhiteSpaceProcessor.isWhiteSpace(charSequence.charAt(i))) {
                        i++;
                    }
                    if (i == length) {
                        break;
                    }
                }
                state.setTarget(ValueListBeanInfoImpl.this.toArray(finalArrayList));
            }
        };
        this.itemType = this.jaxbType.getComponentType();
        this.xducer = jAXBContextImpl.getBeanInfo((Class) cls.getComponentType(), true).getTransducer();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Object toArray(List list) {
        int size = list.size();
        Object objNewInstance = Array.newInstance((Class<?>) this.itemType, size);
        for (int i = 0; i < size; i++) {
            Array.set(objNewInstance, i, list.get(i));
        }
        return objNewInstance;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // com.sun.xml.bind.v2.runtime.JaxBeanInfo
    public void serializeBody(Object obj, XMLSerializer xMLSerializer) throws SAXException, XMLStreamException, IOException {
        int length = Array.getLength(obj);
        for (int i = 0; i < length; i++) {
            try {
                this.xducer.writeText(xMLSerializer, Array.get(obj, i), "arrayItem");
            } catch (AccessorException e) {
                xMLSerializer.reportError("arrayItem", e);
            }
        }
    }

    @Override // com.sun.xml.bind.v2.runtime.JaxBeanInfo
    public final void serializeURIs(Object obj, XMLSerializer xMLSerializer) throws SAXException {
        if (this.xducer.useNamespace()) {
            int length = Array.getLength(obj);
            for (int i = 0; i < length; i++) {
                try {
                    this.xducer.declareNamespace(Array.get(obj, i), xMLSerializer);
                } catch (AccessorException e) {
                    xMLSerializer.reportError("arrayItem", e);
                }
            }
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
        throw new UnsupportedOperationException();
    }

    @Override // com.sun.xml.bind.v2.runtime.JaxBeanInfo
    public final void serializeRoot(Object obj, XMLSerializer xMLSerializer) throws SAXException {
        xMLSerializer.reportError(new ValidationEventImpl(1, Messages.UNABLE_TO_MARSHAL_NON_ELEMENT.format(obj.getClass().getName()), null, null));
    }

    @Override // com.sun.xml.bind.v2.runtime.JaxBeanInfo
    public final Loader getLoader(JAXBContextImpl jAXBContextImpl, boolean z) {
        return this.loader;
    }
}
