package com.sun.xml.bind.v2.runtime.property;

import com.sun.xml.bind.v2.ClassFactory;
import com.sun.xml.bind.v2.model.core.Element;
import com.sun.xml.bind.v2.model.core.PropertyKind;
import com.sun.xml.bind.v2.model.core.WildcardMode;
import com.sun.xml.bind.v2.model.runtime.RuntimeElement;
import com.sun.xml.bind.v2.model.runtime.RuntimeReferencePropertyInfo;
import com.sun.xml.bind.v2.runtime.JAXBContextImpl;
import com.sun.xml.bind.v2.runtime.JaxBeanInfo;
import com.sun.xml.bind.v2.runtime.XMLSerializer;
import com.sun.xml.bind.v2.runtime.property.ArrayERProperty;
import com.sun.xml.bind.v2.runtime.reflect.Accessor;
import com.sun.xml.bind.v2.runtime.reflect.ListIterator;
import com.sun.xml.bind.v2.runtime.unmarshaller.ChildLoader;
import com.sun.xml.bind.v2.runtime.unmarshaller.Loader;
import com.sun.xml.bind.v2.runtime.unmarshaller.Receiver;
import com.sun.xml.bind.v2.runtime.unmarshaller.UnmarshallingContext;
import com.sun.xml.bind.v2.runtime.unmarshaller.WildcardLoader;
import com.sun.xml.bind.v2.util.QNameMap;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Iterator;
import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.DomHandler;
import javax.xml.stream.XMLStreamException;
import org.xml.sax.SAXException;

/* JADX INFO: loaded from: classes3.dex */
class ArrayReferenceNodeProperty<BeanT, ListT, ItemT> extends ArrayERProperty<BeanT, ListT, ItemT> {
    private final DomHandler domHandler;
    private final QNameMap<JaxBeanInfo> expectedElements;
    private final boolean isMixed;
    private final WildcardMode wcMode;

    public ArrayReferenceNodeProperty(JAXBContextImpl jAXBContextImpl, RuntimeReferencePropertyInfo runtimeReferencePropertyInfo) {
        super(jAXBContextImpl, runtimeReferencePropertyInfo, runtimeReferencePropertyInfo.getXmlName(), runtimeReferencePropertyInfo.isCollectionNillable());
        this.expectedElements = new QNameMap<>();
        Iterator<? extends Element<Type, Class>> it = runtimeReferencePropertyInfo.getElements().iterator();
        while (it.hasNext()) {
            RuntimeElement runtimeElement = (RuntimeElement) it.next();
            this.expectedElements.put(runtimeElement.getElementName().getNamespaceURI(), runtimeElement.getElementName().getLocalPart(), jAXBContextImpl.getOrCreate(runtimeElement));
        }
        this.isMixed = runtimeReferencePropertyInfo.isMixed();
        if (runtimeReferencePropertyInfo.getWildcard() != null) {
            this.domHandler = (DomHandler) ClassFactory.create(runtimeReferencePropertyInfo.getDOMHandler());
            this.wcMode = runtimeReferencePropertyInfo.getWildcard();
        } else {
            this.domHandler = null;
            this.wcMode = null;
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.sun.xml.bind.v2.runtime.property.ArrayERProperty
    protected final void serializeListBody(BeanT beant, XMLSerializer xMLSerializer, ListT listt) throws SAXException, XMLStreamException, IOException {
        DomHandler domHandler;
        ListIterator<ItemT> it = this.lister.iterator(listt, xMLSerializer);
        while (it.hasNext()) {
            try {
                Object next = it.next();
                if (next != null) {
                    if (this.isMixed && next.getClass() == String.class) {
                        xMLSerializer.text((String) next, (String) null);
                    } else {
                        JaxBeanInfo beanInfo = xMLSerializer.grammar.getBeanInfo(next, true);
                        if (beanInfo.jaxbType == Object.class && (domHandler = this.domHandler) != null) {
                            xMLSerializer.writeDom(next, domHandler, beant, this.fieldName);
                        } else {
                            beanInfo.serializeRoot(next, xMLSerializer);
                        }
                    }
                }
            } catch (JAXBException e) {
                xMLSerializer.reportError(this.fieldName, e);
            }
        }
    }

    @Override // com.sun.xml.bind.v2.runtime.property.ArrayERProperty
    public void createBodyUnmarshaller(UnmarshallerChain unmarshallerChain, QNameMap<ChildLoader> qNameMap) {
        ArrayERProperty.ReceiverImpl receiverImpl = new ArrayERProperty.ReceiverImpl(unmarshallerChain.allocateOffset());
        for (QNameMap.Entry<JaxBeanInfo> entry : this.expectedElements.entrySet()) {
            qNameMap.put(entry.nsUri, entry.localName, new ChildLoader(entry.getValue().getLoader(unmarshallerChain.context, true), receiverImpl));
        }
        if (this.isMixed) {
            qNameMap.put(TEXT_HANDLER, new ChildLoader(new MixedTextLoader(receiverImpl), null));
        }
        if (this.domHandler != null) {
            qNameMap.put(CATCH_ALL, new ChildLoader(new WildcardLoader(this.domHandler, this.wcMode), receiverImpl));
        }
    }

    private static final class MixedTextLoader extends Loader {
        private final Receiver recv;

        public MixedTextLoader(Receiver receiver) {
            super(true);
            this.recv = receiver;
        }

        @Override // com.sun.xml.bind.v2.runtime.unmarshaller.Loader
        public void text(UnmarshallingContext.State state, CharSequence charSequence) throws SAXException {
            if (charSequence.length() != 0) {
                this.recv.receive(state, charSequence.toString());
            }
        }
    }

    @Override // com.sun.xml.bind.v2.runtime.property.Property
    public PropertyKind getKind() {
        return PropertyKind.REFERENCE;
    }

    @Override // com.sun.xml.bind.v2.runtime.property.PropertyImpl, com.sun.xml.bind.v2.runtime.property.Property
    public Accessor getElementPropertyAccessor(String str, String str2) {
        if (this.wrapperTagName != null) {
            if (this.wrapperTagName.equals(str, str2)) {
                return this.acc;
            }
            return null;
        }
        if (this.expectedElements.containsKey(str, str2)) {
            return this.acc;
        }
        return null;
    }
}
