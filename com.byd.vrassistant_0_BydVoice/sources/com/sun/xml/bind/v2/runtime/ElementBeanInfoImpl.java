package com.sun.xml.bind.v2.runtime;

import com.byd.common.bean.WuwInfoBean;
import com.sun.xml.bind.api.AccessorException;
import com.sun.xml.bind.v2.model.core.PropertyKind;
import com.sun.xml.bind.v2.model.runtime.RuntimeElementInfo;
import com.sun.xml.bind.v2.model.runtime.RuntimePropertyInfo;
import com.sun.xml.bind.v2.runtime.property.Property;
import com.sun.xml.bind.v2.runtime.property.PropertyFactory;
import com.sun.xml.bind.v2.runtime.property.UnmarshallerChain;
import com.sun.xml.bind.v2.runtime.reflect.Accessor;
import com.sun.xml.bind.v2.runtime.unmarshaller.ChildLoader;
import com.sun.xml.bind.v2.runtime.unmarshaller.Discarder;
import com.sun.xml.bind.v2.runtime.unmarshaller.Intercepter;
import com.sun.xml.bind.v2.runtime.unmarshaller.Loader;
import com.sun.xml.bind.v2.runtime.unmarshaller.TagName;
import com.sun.xml.bind.v2.runtime.unmarshaller.UnmarshallingContext;
import com.sun.xml.bind.v2.util.QNameMap;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamException;
import org.xml.sax.SAXException;

/* JADX INFO: loaded from: classes3.dex */
public final class ElementBeanInfoImpl extends JaxBeanInfo<JAXBElement> {
    private final Constructor<? extends JAXBElement> constructor;
    public final Class expectedType;
    private Loader loader;
    private final Property property;
    private final Class scope;
    private final QName tagName;

    @Override // com.sun.xml.bind.v2.runtime.JaxBeanInfo
    public final Transducer<JAXBElement> getTransducer() {
        return null;
    }

    @Override // com.sun.xml.bind.v2.runtime.JaxBeanInfo
    public void serializeAttributes(JAXBElement jAXBElement, XMLSerializer xMLSerializer) {
    }

    @Override // com.sun.xml.bind.v2.runtime.JaxBeanInfo
    public void serializeURIs(JAXBElement jAXBElement, XMLSerializer xMLSerializer) {
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [com.sun.xml.bind.v2.model.runtime.RuntimeElementPropertyInfo, com.sun.xml.bind.v2.model.runtime.RuntimePropertyInfo] */
    ElementBeanInfoImpl(JAXBContextImpl jAXBContextImpl, RuntimeElementInfo runtimeElementInfo) {
        super(jAXBContextImpl, runtimeElementInfo, runtimeElementInfo.getType2(), true, false, true);
        this.property = PropertyFactory.create(jAXBContextImpl, runtimeElementInfo.getProperty());
        this.tagName = runtimeElementInfo.getElementName();
        Class<?> cls = (Class) Utils.REFLECTION_NAVIGATOR.erasure(runtimeElementInfo.getContentInMemoryType());
        this.expectedType = cls;
        this.scope = runtimeElementInfo.getScope() == null ? JAXBElement.GlobalScope.class : runtimeElementInfo.getScope().getClazz();
        Class cls2 = (Class) Utils.REFLECTION_NAVIGATOR.erasure(runtimeElementInfo.getType2());
        if (cls2 == JAXBElement.class) {
            this.constructor = null;
            return;
        }
        try {
            this.constructor = cls2.getConstructor(cls);
        } catch (NoSuchMethodException e) {
            NoSuchMethodError noSuchMethodError = new NoSuchMethodError("Failed to find the constructor for " + cls2 + " with " + this.expectedType);
            noSuchMethodError.initCause(e);
            throw noSuchMethodError;
        }
    }

    protected ElementBeanInfoImpl(final JAXBContextImpl jAXBContextImpl) {
        super(jAXBContextImpl, null, JAXBElement.class, true, false, true);
        this.tagName = null;
        this.expectedType = null;
        this.scope = null;
        this.constructor = null;
        this.property = new Property<JAXBElement>() { // from class: com.sun.xml.bind.v2.runtime.ElementBeanInfoImpl.1
            @Override // com.sun.xml.bind.v2.runtime.property.StructureLoaderBuilder
            public void buildChildElementUnmarshallers(UnmarshallerChain unmarshallerChain, QNameMap<ChildLoader> qNameMap) {
            }

            @Override // com.sun.xml.bind.v2.runtime.property.Property
            public String getFieldName() {
                return null;
            }

            @Override // com.sun.xml.bind.v2.runtime.property.Property
            public String getIdValue(JAXBElement jAXBElement) {
                return null;
            }

            @Override // com.sun.xml.bind.v2.runtime.property.Property
            public boolean hasSerializeURIAction() {
                return false;
            }

            @Override // com.sun.xml.bind.v2.runtime.property.Property
            public boolean isHiddenByOverride() {
                return false;
            }

            @Override // com.sun.xml.bind.v2.runtime.property.Property
            public void serializeURIs(JAXBElement jAXBElement, XMLSerializer xMLSerializer) {
            }

            @Override // com.sun.xml.bind.v2.runtime.property.Property
            public void wrapUp() {
            }

            @Override // com.sun.xml.bind.v2.runtime.property.Property
            public void reset(JAXBElement jAXBElement) {
                throw new UnsupportedOperationException();
            }

            /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
            @Override // com.sun.xml.bind.v2.runtime.property.Property
            public void serializeBody(JAXBElement jAXBElement, XMLSerializer xMLSerializer, Object obj) throws SAXException, XMLStreamException, IOException {
                Class scope = jAXBElement.getScope();
                if (jAXBElement.isGlobalScope()) {
                    scope = null;
                }
                QName name = jAXBElement.getName();
                ElementBeanInfoImpl element = jAXBContextImpl.getElement(scope, name);
                if (element != null) {
                    try {
                        element.property.serializeBody(jAXBElement, xMLSerializer, jAXBElement);
                        return;
                    } catch (AccessorException e) {
                        xMLSerializer.reportError(null, e);
                        return;
                    }
                }
                try {
                    JaxBeanInfo beanInfo = jAXBContextImpl.getBeanInfo(jAXBElement.getDeclaredType(), true);
                    Object value = jAXBElement.getValue();
                    xMLSerializer.startElement(name.getNamespaceURI(), name.getLocalPart(), name.getPrefix(), null);
                    if (value == null) {
                        xMLSerializer.writeXsiNilTrue();
                    } else {
                        xMLSerializer.childAsXsiType(value, WuwInfoBean.ValueKey, beanInfo, false);
                    }
                    xMLSerializer.endElement();
                } catch (JAXBException e2) {
                    xMLSerializer.reportError(null, e2);
                }
            }

            @Override // com.sun.xml.bind.v2.runtime.property.Property
            public PropertyKind getKind() {
                return PropertyKind.ELEMENT;
            }

            @Override // com.sun.xml.bind.v2.runtime.property.Property
            public Accessor getElementPropertyAccessor(String str, String str2) {
                throw new UnsupportedOperationException();
            }

            @Override // com.sun.xml.bind.v2.runtime.property.Property
            public RuntimePropertyInfo getInfo() {
                return ElementBeanInfoImpl.this.property.getInfo();
            }

            @Override // com.sun.xml.bind.v2.runtime.property.Property
            public void setHiddenByOverride(boolean z) {
                throw new UnsupportedOperationException("Not supported on jaxbelements.");
            }
        };
    }

    private final class IntercepterLoader extends Loader implements Intercepter {
        private final Loader core;

        public IntercepterLoader(Loader loader) {
            this.core = loader;
        }

        @Override // com.sun.xml.bind.v2.runtime.unmarshaller.Loader
        public final void startElement(UnmarshallingContext.State state, TagName tagName) throws SAXException {
            state.setLoader(this.core);
            state.setIntercepter(this);
            UnmarshallingContext context = state.getContext();
            Object outerPeer = context.getOuterPeer();
            if (outerPeer != null && ElementBeanInfoImpl.this.jaxbType != outerPeer.getClass()) {
                outerPeer = null;
            }
            if (outerPeer != null) {
                ElementBeanInfoImpl.this.reset((JAXBElement) outerPeer, context);
            }
            if (outerPeer == null) {
                outerPeer = context.createInstance(ElementBeanInfoImpl.this);
            }
            fireBeforeUnmarshal(ElementBeanInfoImpl.this, outerPeer, state);
            context.recordOuterPeer(outerPeer);
            UnmarshallingContext.State prev = state.getPrev();
            prev.setBackup(prev.getTarget());
            prev.setTarget(outerPeer);
            this.core.startElement(state, tagName);
        }

        @Override // com.sun.xml.bind.v2.runtime.unmarshaller.Intercepter
        public Object intercept(UnmarshallingContext.State state, Object obj) throws SAXException {
            JAXBElement jAXBElement = (JAXBElement) state.getTarget();
            state.setTarget(state.getBackup());
            state.setBackup(null);
            if (state.isNil()) {
                jAXBElement.setNil(true);
                state.setNil(false);
            }
            if (obj != null) {
                jAXBElement.setValue(obj);
            }
            fireAfterUnmarshal(ElementBeanInfoImpl.this, jAXBElement, state);
            return jAXBElement;
        }
    }

    @Override // com.sun.xml.bind.v2.runtime.JaxBeanInfo
    public String getElementNamespaceURI(JAXBElement jAXBElement) {
        return jAXBElement.getName().getNamespaceURI();
    }

    @Override // com.sun.xml.bind.v2.runtime.JaxBeanInfo
    public String getElementLocalName(JAXBElement jAXBElement) {
        return jAXBElement.getName().getLocalPart();
    }

    @Override // com.sun.xml.bind.v2.runtime.JaxBeanInfo
    public Loader getLoader(JAXBContextImpl jAXBContextImpl, boolean z) {
        if (this.loader == null) {
            UnmarshallerChain unmarshallerChain = new UnmarshallerChain(jAXBContextImpl);
            QNameMap<ChildLoader> qNameMap = new QNameMap<>();
            this.property.buildChildElementUnmarshallers(unmarshallerChain, qNameMap);
            if (qNameMap.size() == 1) {
                this.loader = new IntercepterLoader(qNameMap.getOne().getValue().loader);
            } else {
                this.loader = Discarder.INSTANCE;
            }
        }
        return this.loader;
    }

    @Override // com.sun.xml.bind.v2.runtime.JaxBeanInfo
    public final JAXBElement createInstance(UnmarshallingContext unmarshallingContext) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        return createInstanceFromValue(null);
    }

    public final JAXBElement createInstanceFromValue(Object obj) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        Constructor<? extends JAXBElement> constructor = this.constructor;
        if (constructor == null) {
            return new JAXBElement(this.tagName, this.expectedType, this.scope, obj);
        }
        return constructor.newInstance(obj);
    }

    @Override // com.sun.xml.bind.v2.runtime.JaxBeanInfo
    public boolean reset(JAXBElement jAXBElement, UnmarshallingContext unmarshallingContext) {
        jAXBElement.setValue(null);
        return true;
    }

    @Override // com.sun.xml.bind.v2.runtime.JaxBeanInfo
    public String getId(JAXBElement jAXBElement, XMLSerializer xMLSerializer) {
        Object value = jAXBElement.getValue();
        if (value instanceof String) {
            return (String) value;
        }
        return null;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // com.sun.xml.bind.v2.runtime.JaxBeanInfo
    public void serializeBody(JAXBElement jAXBElement, XMLSerializer xMLSerializer) throws SAXException, XMLStreamException, IOException {
        try {
            this.property.serializeBody(jAXBElement, xMLSerializer, null);
        } catch (AccessorException e) {
            xMLSerializer.reportError(null, e);
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // com.sun.xml.bind.v2.runtime.JaxBeanInfo
    public void serializeRoot(JAXBElement jAXBElement, XMLSerializer xMLSerializer) throws SAXException, XMLStreamException, IOException {
        serializeBody(jAXBElement, xMLSerializer);
    }

    @Override // com.sun.xml.bind.v2.runtime.JaxBeanInfo
    public void wrapUp() {
        super.wrapUp();
        this.property.wrapUp();
    }

    @Override // com.sun.xml.bind.v2.runtime.JaxBeanInfo
    public void link(JAXBContextImpl jAXBContextImpl) {
        super.link(jAXBContextImpl);
        getLoader(jAXBContextImpl, true);
    }
}
