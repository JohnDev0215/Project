package com.sun.xml.bind.v2.runtime.property;

import com.byd.common.bean.WuwInfoBean;
import com.sun.xml.bind.api.AccessorException;
import com.sun.xml.bind.v2.ClassFactory;
import com.sun.xml.bind.v2.model.core.PropertyKind;
import com.sun.xml.bind.v2.model.runtime.RuntimeMapPropertyInfo;
import com.sun.xml.bind.v2.model.runtime.RuntimeTypeInfo;
import com.sun.xml.bind.v2.runtime.JAXBContextImpl;
import com.sun.xml.bind.v2.runtime.JaxBeanInfo;
import com.sun.xml.bind.v2.runtime.Name;
import com.sun.xml.bind.v2.runtime.XMLSerializer;
import com.sun.xml.bind.v2.runtime.reflect.Accessor;
import com.sun.xml.bind.v2.runtime.unmarshaller.ChildLoader;
import com.sun.xml.bind.v2.runtime.unmarshaller.Loader;
import com.sun.xml.bind.v2.runtime.unmarshaller.Receiver;
import com.sun.xml.bind.v2.runtime.unmarshaller.TagName;
import com.sun.xml.bind.v2.runtime.unmarshaller.UnmarshallingContext;
import com.sun.xml.bind.v2.util.QNameMap;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamException;
import org.xml.sax.SAXException;

/* JADX INFO: loaded from: classes3.dex */
final class SingleMapNodeProperty<BeanT, ValueT extends Map> extends PropertyImpl<BeanT> {
    private final Accessor<BeanT, ValueT> acc;
    private final Loader entryLoader;
    private final Name entryTag;
    private final Loader itemsLoader;
    private JaxBeanInfo keyBeanInfo;
    private Loader keyLoader;
    private final Name keyTag;
    private final Class<? extends ValueT> mapImplClass;
    private final boolean nillable;
    private final Name tagName;
    private JaxBeanInfo valueBeanInfo;
    private Loader valueLoader;
    private final Name valueTag;
    private static final Class[] knownImplClasses = {HashMap.class, TreeMap.class, LinkedHashMap.class};
    private static final Receiver keyReceiver = new ReceiverImpl(0);
    private static final Receiver valueReceiver = new ReceiverImpl(1);

    @Override // com.sun.xml.bind.v2.runtime.property.Property
    public String getIdValue(BeanT beant) {
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v13, types: [com.sun.xml.bind.v2.model.runtime.RuntimeNonElement, com.sun.xml.bind.v2.model.runtime.RuntimeTypeInfo] */
    /* JADX WARN: Type inference failed for: r0v15, types: [com.sun.xml.bind.v2.model.runtime.RuntimeNonElement, com.sun.xml.bind.v2.model.runtime.RuntimeTypeInfo] */
    public SingleMapNodeProperty(JAXBContextImpl jAXBContextImpl, RuntimeMapPropertyInfo runtimeMapPropertyInfo) {
        super(jAXBContextImpl, runtimeMapPropertyInfo);
        boolean z = false;
        this.itemsLoader = new Loader(z) { // from class: com.sun.xml.bind.v2.runtime.property.SingleMapNodeProperty.1
            private ThreadLocal<Stack<BeanT>> target = new ThreadLocal<>();
            private ThreadLocal<Stack<ValueT>> map = new ThreadLocal<>();

            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.sun.xml.bind.v2.runtime.unmarshaller.Loader
            public void startElement(UnmarshallingContext.State state, TagName tagName) throws SAXException {
                try {
                    Object target = state.getPrev().getTarget();
                    Map map = (Map) SingleMapNodeProperty.this.acc.get(target);
                    if (map == null) {
                        map = (Map) ClassFactory.create(SingleMapNodeProperty.this.mapImplClass);
                    } else {
                        map.clear();
                    }
                    Stack.push(this.target, target);
                    Stack.push(this.map, map);
                    state.setTarget(map);
                } catch (AccessorException e) {
                    handleGenericException(e, true);
                    state.setTarget(new HashMap());
                }
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.sun.xml.bind.v2.runtime.unmarshaller.Loader
            public void leaveElement(UnmarshallingContext.State state, TagName tagName) throws SAXException {
                super.leaveElement(state, tagName);
                try {
                    SingleMapNodeProperty.this.acc.set(Stack.pop(this.target), Stack.pop(this.map));
                } catch (AccessorException e) {
                    handleGenericException(e, true);
                }
            }

            @Override // com.sun.xml.bind.v2.runtime.unmarshaller.Loader
            public void childElement(UnmarshallingContext.State state, TagName tagName) throws SAXException {
                if (tagName.matches(SingleMapNodeProperty.this.entryTag)) {
                    state.setLoader(SingleMapNodeProperty.this.entryLoader);
                } else {
                    super.childElement(state, tagName);
                }
            }

            @Override // com.sun.xml.bind.v2.runtime.unmarshaller.Loader
            public Collection<QName> getExpectedChildElements() {
                return Collections.singleton(SingleMapNodeProperty.this.entryTag.toQName());
            }
        };
        this.entryLoader = new Loader(z) { // from class: com.sun.xml.bind.v2.runtime.property.SingleMapNodeProperty.2
            @Override // com.sun.xml.bind.v2.runtime.unmarshaller.Loader
            public void startElement(UnmarshallingContext.State state, TagName tagName) {
                state.setTarget(new Object[2]);
            }

            @Override // com.sun.xml.bind.v2.runtime.unmarshaller.Loader
            public void leaveElement(UnmarshallingContext.State state, TagName tagName) {
                Object[] objArr = (Object[]) state.getTarget();
                ((Map) state.getPrev().getTarget()).put(objArr[0], objArr[1]);
            }

            @Override // com.sun.xml.bind.v2.runtime.unmarshaller.Loader
            public void childElement(UnmarshallingContext.State state, TagName tagName) throws SAXException {
                if (tagName.matches(SingleMapNodeProperty.this.keyTag)) {
                    state.setLoader(SingleMapNodeProperty.this.keyLoader);
                    state.setReceiver(SingleMapNodeProperty.keyReceiver);
                } else if (tagName.matches(SingleMapNodeProperty.this.valueTag)) {
                    state.setLoader(SingleMapNodeProperty.this.valueLoader);
                    state.setReceiver(SingleMapNodeProperty.valueReceiver);
                } else {
                    super.childElement(state, tagName);
                }
            }

            @Override // com.sun.xml.bind.v2.runtime.unmarshaller.Loader
            public Collection<QName> getExpectedChildElements() {
                return Arrays.asList(SingleMapNodeProperty.this.keyTag.toQName(), SingleMapNodeProperty.this.valueTag.toQName());
            }
        };
        this.acc = runtimeMapPropertyInfo.getAccessor().optimize(jAXBContextImpl);
        this.tagName = jAXBContextImpl.nameBuilder.createElementName(runtimeMapPropertyInfo.getXmlName());
        this.entryTag = jAXBContextImpl.nameBuilder.createElementName("", "entry");
        this.keyTag = jAXBContextImpl.nameBuilder.createElementName("", "key");
        this.valueTag = jAXBContextImpl.nameBuilder.createElementName("", WuwInfoBean.ValueKey);
        this.nillable = runtimeMapPropertyInfo.isCollectionNillable();
        this.keyBeanInfo = jAXBContextImpl.getOrCreate((RuntimeTypeInfo) runtimeMapPropertyInfo.getKeyType());
        this.valueBeanInfo = jAXBContextImpl.getOrCreate((RuntimeTypeInfo) runtimeMapPropertyInfo.getValueType());
        this.mapImplClass = ClassFactory.inferImplClass((Class) Utils.REFLECTION_NAVIGATOR.erasure(runtimeMapPropertyInfo.getRawType()), knownImplClasses);
    }

    @Override // com.sun.xml.bind.v2.runtime.property.Property
    public void reset(BeanT beant) throws AccessorException {
        this.acc.set(beant, null);
    }

    @Override // com.sun.xml.bind.v2.runtime.property.Property
    public PropertyKind getKind() {
        return PropertyKind.MAP;
    }

    @Override // com.sun.xml.bind.v2.runtime.property.StructureLoaderBuilder
    public void buildChildElementUnmarshallers(UnmarshallerChain unmarshallerChain, QNameMap<ChildLoader> qNameMap) {
        this.keyLoader = this.keyBeanInfo.getLoader(unmarshallerChain.context, true);
        this.valueLoader = this.valueBeanInfo.getLoader(unmarshallerChain.context, true);
        qNameMap.put(this.tagName, new ChildLoader(this.itemsLoader, null));
    }

    private static final class ReceiverImpl implements Receiver {
        private final int index;

        public ReceiverImpl(int i) {
            this.index = i;
        }

        @Override // com.sun.xml.bind.v2.runtime.unmarshaller.Receiver
        public void receive(UnmarshallingContext.State state, Object obj) {
            ((Object[]) state.getTarget())[this.index] = obj;
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // com.sun.xml.bind.v2.runtime.property.PropertyImpl, com.sun.xml.bind.v2.runtime.property.Property
    public void serializeBody(BeanT beant, XMLSerializer xMLSerializer, Object obj) throws XMLStreamException, SAXException, IOException, AccessorException {
        ValueT valuet = this.acc.get(beant);
        if (valuet != null) {
            bareStartTag(xMLSerializer, this.tagName, valuet);
            for (Map.Entry entry : valuet.entrySet()) {
                bareStartTag(xMLSerializer, this.entryTag, null);
                Object key = entry.getKey();
                if (key != null) {
                    xMLSerializer.startElement(this.keyTag, key);
                    xMLSerializer.childAsXsiType(key, this.fieldName, this.keyBeanInfo, false);
                    xMLSerializer.endElement();
                }
                Object value = entry.getValue();
                if (value != null) {
                    xMLSerializer.startElement(this.valueTag, value);
                    xMLSerializer.childAsXsiType(value, this.fieldName, this.valueBeanInfo, false);
                    xMLSerializer.endElement();
                }
                xMLSerializer.endElement();
            }
            xMLSerializer.endElement();
            return;
        }
        if (this.nillable) {
            xMLSerializer.startElement(this.tagName, null);
            xMLSerializer.writeXsiNilTrue();
            xMLSerializer.endElement();
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    private void bareStartTag(XMLSerializer xMLSerializer, Name name, Object obj) throws XMLStreamException, SAXException, IOException {
        xMLSerializer.startElement(name, obj);
        xMLSerializer.endNamespaceDecls(obj);
        xMLSerializer.endAttributes();
    }

    @Override // com.sun.xml.bind.v2.runtime.property.PropertyImpl, com.sun.xml.bind.v2.runtime.property.Property
    public Accessor getElementPropertyAccessor(String str, String str2) {
        if (this.tagName.equals(str, str2)) {
            return this.acc;
        }
        return null;
    }

    private static final class Stack<T> {
        private Stack<T> parent;
        private T value;

        private Stack(Stack<T> stack, T t) {
            this.parent = stack;
            this.value = t;
        }

        private Stack(T t) {
            this.value = t;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static <T> void push(ThreadLocal<Stack<T>> threadLocal, T t) {
            Stack<T> stack = threadLocal.get();
            if (stack == null) {
                threadLocal.set(new Stack<>(t));
            } else {
                threadLocal.set(new Stack<>(stack, t));
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static <T> T pop(ThreadLocal<Stack<T>> threadLocal) {
            Stack<T> stack = threadLocal.get();
            Stack<T> stack2 = ((Stack) stack).parent;
            if (stack2 == null) {
                threadLocal.remove();
            } else {
                threadLocal.set(stack2);
            }
            return ((Stack) stack).value;
        }
    }
}
