package com.sun.xml.bind.v2.runtime.property;

import com.sun.xml.bind.api.AccessorException;
import com.sun.xml.bind.v2.ClassFactory;
import com.sun.xml.bind.v2.model.core.Element;
import com.sun.xml.bind.v2.model.core.PropertyKind;
import com.sun.xml.bind.v2.model.core.WildcardMode;
import com.sun.xml.bind.v2.model.runtime.RuntimeElement;
import com.sun.xml.bind.v2.model.runtime.RuntimeReferencePropertyInfo;
import com.sun.xml.bind.v2.runtime.ElementBeanInfoImpl;
import com.sun.xml.bind.v2.runtime.JAXBContextImpl;
import com.sun.xml.bind.v2.runtime.JaxBeanInfo;
import com.sun.xml.bind.v2.runtime.XMLSerializer;
import com.sun.xml.bind.v2.runtime.reflect.Accessor;
import com.sun.xml.bind.v2.runtime.unmarshaller.ChildLoader;
import com.sun.xml.bind.v2.runtime.unmarshaller.WildcardLoader;
import com.sun.xml.bind.v2.util.QNameMap;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.util.Iterator;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.DomHandler;
import javax.xml.stream.XMLStreamException;
import org.xml.sax.SAXException;

/* JADX INFO: loaded from: classes3.dex */
final class SingleReferenceNodeProperty<BeanT, ValueT> extends PropertyImpl<BeanT> {
    private final Accessor<BeanT, ValueT> acc;
    private final DomHandler domHandler;
    private final QNameMap<JaxBeanInfo> expectedElements;
    private final WildcardMode wcMode;

    @Override // com.sun.xml.bind.v2.runtime.property.Property
    public String getIdValue(BeanT beant) {
        return null;
    }

    public SingleReferenceNodeProperty(JAXBContextImpl jAXBContextImpl, RuntimeReferencePropertyInfo runtimeReferencePropertyInfo) {
        super(jAXBContextImpl, runtimeReferencePropertyInfo);
        this.expectedElements = new QNameMap<>();
        this.acc = runtimeReferencePropertyInfo.getAccessor().optimize(jAXBContextImpl);
        Iterator<? extends Element<Type, Class>> it = runtimeReferencePropertyInfo.getElements().iterator();
        while (it.hasNext()) {
            RuntimeElement runtimeElement = (RuntimeElement) it.next();
            this.expectedElements.put(runtimeElement.getElementName(), jAXBContextImpl.getOrCreate(runtimeElement));
        }
        if (runtimeReferencePropertyInfo.getWildcard() != null) {
            this.domHandler = (DomHandler) ClassFactory.create(runtimeReferencePropertyInfo.getDOMHandler());
            this.wcMode = runtimeReferencePropertyInfo.getWildcard();
        } else {
            this.domHandler = null;
            this.wcMode = null;
        }
    }

    @Override // com.sun.xml.bind.v2.runtime.property.Property
    public void reset(BeanT beant) throws AccessorException {
        this.acc.set(beant, null);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // com.sun.xml.bind.v2.runtime.property.PropertyImpl, com.sun.xml.bind.v2.runtime.property.Property
    public void serializeBody(BeanT beant, XMLSerializer xMLSerializer, Object obj) throws SAXException, XMLStreamException, IOException, AccessorException {
        DomHandler domHandler;
        ValueT valuet = this.acc.get(beant);
        if (valuet != null) {
            try {
                JaxBeanInfo beanInfo = xMLSerializer.grammar.getBeanInfo((Object) valuet, true);
                if (beanInfo.jaxbType == Object.class && (domHandler = this.domHandler) != null) {
                    xMLSerializer.writeDom(valuet, domHandler, beant, this.fieldName);
                } else {
                    beanInfo.serializeRoot(valuet, xMLSerializer);
                }
            } catch (JAXBException e) {
                xMLSerializer.reportError(this.fieldName, e);
            }
        }
    }

    @Override // com.sun.xml.bind.v2.runtime.property.StructureLoaderBuilder
    public void buildChildElementUnmarshallers(UnmarshallerChain unmarshallerChain, QNameMap<ChildLoader> qNameMap) {
        for (QNameMap.Entry<JaxBeanInfo> entry : this.expectedElements.entrySet()) {
            qNameMap.put(entry.nsUri, entry.localName, new ChildLoader(entry.getValue().getLoader(unmarshallerChain.context, true), this.acc));
        }
        if (this.domHandler != null) {
            qNameMap.put(CATCH_ALL, new ChildLoader(new WildcardLoader(this.domHandler, this.wcMode), this.acc));
        }
    }

    @Override // com.sun.xml.bind.v2.runtime.property.Property
    public PropertyKind getKind() {
        return PropertyKind.REFERENCE;
    }

    @Override // com.sun.xml.bind.v2.runtime.property.PropertyImpl, com.sun.xml.bind.v2.runtime.property.Property
    public Accessor getElementPropertyAccessor(String str, String str2) {
        JaxBeanInfo jaxBeanInfo = this.expectedElements.get(str, str2);
        if (jaxBeanInfo == null) {
            return null;
        }
        if (jaxBeanInfo instanceof ElementBeanInfoImpl) {
            final ElementBeanInfoImpl elementBeanInfoImpl = (ElementBeanInfoImpl) jaxBeanInfo;
            return new Accessor<BeanT, Object>(elementBeanInfoImpl.expectedType) { // from class: com.sun.xml.bind.v2.runtime.property.SingleReferenceNodeProperty.1
                @Override // com.sun.xml.bind.v2.runtime.reflect.Accessor
                public Object get(BeanT beant) throws AccessorException {
                    Object obj = SingleReferenceNodeProperty.this.acc.get(beant);
                    return obj instanceof JAXBElement ? ((JAXBElement) obj).getValue() : obj;
                }

                /* JADX WARN: Multi-variable type inference failed */
                @Override // com.sun.xml.bind.v2.runtime.reflect.Accessor
                public void set(BeanT beant, Object obj) throws AccessorException {
                    if (obj != null) {
                        try {
                            obj = elementBeanInfoImpl.createInstanceFromValue(obj);
                        } catch (IllegalAccessException e) {
                            throw new AccessorException(e);
                        } catch (InstantiationException e2) {
                            throw new AccessorException(e2);
                        } catch (InvocationTargetException e3) {
                            throw new AccessorException(e3);
                        }
                    }
                    SingleReferenceNodeProperty.this.acc.set(beant, obj);
                }
            };
        }
        return this.acc;
    }
}
