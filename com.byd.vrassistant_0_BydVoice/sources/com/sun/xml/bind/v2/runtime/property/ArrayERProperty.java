package com.sun.xml.bind.v2.runtime.property;

import com.sun.xml.bind.api.AccessorException;
import com.sun.xml.bind.v2.model.runtime.RuntimePropertyInfo;
import com.sun.xml.bind.v2.runtime.JAXBContextImpl;
import com.sun.xml.bind.v2.runtime.Name;
import com.sun.xml.bind.v2.runtime.XMLSerializer;
import com.sun.xml.bind.v2.runtime.reflect.Accessor;
import com.sun.xml.bind.v2.runtime.reflect.Lister;
import com.sun.xml.bind.v2.runtime.unmarshaller.ChildLoader;
import com.sun.xml.bind.v2.runtime.unmarshaller.Loader;
import com.sun.xml.bind.v2.runtime.unmarshaller.Receiver;
import com.sun.xml.bind.v2.runtime.unmarshaller.TagName;
import com.sun.xml.bind.v2.runtime.unmarshaller.UnmarshallingContext;
import com.sun.xml.bind.v2.runtime.unmarshaller.XsiNilLoader;
import com.sun.xml.bind.v2.util.QNameMap;
import java.io.IOException;
import java.util.Collection;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamException;
import org.xml.sax.SAXException;

/* JADX INFO: loaded from: classes3.dex */
abstract class ArrayERProperty<BeanT, ListT, ItemT> extends ArrayProperty<BeanT, ListT, ItemT> {
    protected final boolean isWrapperNillable;
    protected final Name wrapperTagName;

    protected abstract void createBodyUnmarshaller(UnmarshallerChain unmarshallerChain, QNameMap<ChildLoader> qNameMap);

    protected abstract void serializeListBody(BeanT beant, XMLSerializer xMLSerializer, ListT listt) throws XMLStreamException, SAXException, IOException, AccessorException;

    protected ArrayERProperty(JAXBContextImpl jAXBContextImpl, RuntimePropertyInfo runtimePropertyInfo, QName qName, boolean z) {
        super(jAXBContextImpl, runtimePropertyInfo);
        if (qName == null) {
            this.wrapperTagName = null;
        } else {
            this.wrapperTagName = jAXBContextImpl.nameBuilder.createElementName(qName);
        }
        this.isWrapperNillable = z;
    }

    private static final class ItemsLoader extends Loader {
        private final Accessor acc;
        private final QNameMap<ChildLoader> children;
        private final Lister lister;

        public ItemsLoader(Accessor accessor, Lister lister, QNameMap<ChildLoader> qNameMap) {
            super(false);
            this.acc = accessor;
            this.lister = lister;
            this.children = qNameMap;
        }

        @Override // com.sun.xml.bind.v2.runtime.unmarshaller.Loader
        public void startElement(UnmarshallingContext.State state, TagName tagName) throws SAXException {
            UnmarshallingContext context = state.getContext();
            context.startScope(1);
            state.setTarget(state.getPrev().getTarget());
            context.getScope(0).start(this.acc, this.lister);
        }

        @Override // com.sun.xml.bind.v2.runtime.unmarshaller.Loader
        public void childElement(UnmarshallingContext.State state, TagName tagName) throws SAXException {
            ChildLoader childLoader = this.children.get(tagName.uri, tagName.local);
            if (childLoader == null) {
                childLoader = this.children.get(StructureLoaderBuilder.CATCH_ALL);
            }
            if (childLoader == null) {
                super.childElement(state, tagName);
            } else {
                state.setLoader(childLoader.loader);
                state.setReceiver(childLoader.receiver);
            }
        }

        @Override // com.sun.xml.bind.v2.runtime.unmarshaller.Loader
        public void leaveElement(UnmarshallingContext.State state, TagName tagName) throws SAXException {
            state.getContext().endScope(1);
        }

        @Override // com.sun.xml.bind.v2.runtime.unmarshaller.Loader
        public Collection<QName> getExpectedChildElements() {
            return this.children.keySet();
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // com.sun.xml.bind.v2.runtime.property.PropertyImpl, com.sun.xml.bind.v2.runtime.property.Property
    public final void serializeBody(BeanT beant, XMLSerializer xMLSerializer, Object obj) throws XMLStreamException, SAXException, IOException, AccessorException {
        ListT listt = this.acc.get(beant);
        if (listt != null) {
            Name name = this.wrapperTagName;
            if (name != null) {
                xMLSerializer.startElement(name, null);
                xMLSerializer.endNamespaceDecls(listt);
                xMLSerializer.endAttributes();
            }
            serializeListBody(beant, xMLSerializer, listt);
            if (this.wrapperTagName != null) {
                xMLSerializer.endElement();
                return;
            }
            return;
        }
        if (this.isWrapperNillable) {
            xMLSerializer.startElement(this.wrapperTagName, null);
            xMLSerializer.writeXsiNilTrue();
            xMLSerializer.endElement();
        }
    }

    @Override // com.sun.xml.bind.v2.runtime.property.StructureLoaderBuilder
    public final void buildChildElementUnmarshallers(UnmarshallerChain unmarshallerChain, QNameMap<ChildLoader> qNameMap) {
        if (this.wrapperTagName != null) {
            UnmarshallerChain unmarshallerChain2 = new UnmarshallerChain(unmarshallerChain.context);
            QNameMap<ChildLoader> qNameMap2 = new QNameMap<>();
            createBodyUnmarshaller(unmarshallerChain2, qNameMap2);
            Loader itemsLoader = new ItemsLoader(this.acc, this.lister, qNameMap2);
            if (this.isWrapperNillable || unmarshallerChain.context.allNillable) {
                itemsLoader = new XsiNilLoader(itemsLoader);
            }
            qNameMap.put(this.wrapperTagName, new ChildLoader(itemsLoader, null));
            return;
        }
        createBodyUnmarshaller(unmarshallerChain, qNameMap);
    }

    protected final class ReceiverImpl implements Receiver {
        private final int offset;

        protected ReceiverImpl(int i) {
            this.offset = i;
        }

        @Override // com.sun.xml.bind.v2.runtime.unmarshaller.Receiver
        public void receive(UnmarshallingContext.State state, Object obj) throws SAXException {
            state.getContext().getScope(this.offset).add(ArrayERProperty.this.acc, ArrayERProperty.this.lister, obj);
        }
    }
}
