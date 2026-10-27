package com.sun.xml.bind.v2.runtime.property;

import com.sun.xml.bind.api.AccessorException;
import com.sun.xml.bind.v2.model.core.PropertyKind;
import com.sun.xml.bind.v2.model.core.TypeRef;
import com.sun.xml.bind.v2.model.runtime.RuntimeElementPropertyInfo;
import com.sun.xml.bind.v2.model.runtime.RuntimeTypeInfo;
import com.sun.xml.bind.v2.model.runtime.RuntimeTypeRef;
import com.sun.xml.bind.v2.runtime.JAXBContextImpl;
import com.sun.xml.bind.v2.runtime.JaxBeanInfo;
import com.sun.xml.bind.v2.runtime.Name;
import com.sun.xml.bind.v2.runtime.XMLSerializer;
import com.sun.xml.bind.v2.runtime.reflect.Accessor;
import com.sun.xml.bind.v2.runtime.unmarshaller.ChildLoader;
import com.sun.xml.bind.v2.runtime.unmarshaller.DefaultValueLoaderDecorator;
import com.sun.xml.bind.v2.runtime.unmarshaller.Loader;
import com.sun.xml.bind.v2.runtime.unmarshaller.XsiNilLoader;
import com.sun.xml.bind.v2.util.QNameMap;
import java.io.IOException;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamException;
import org.xml.sax.SAXException;

/* JADX INFO: loaded from: classes3.dex */
final class SingleElementNodeProperty<BeanT, ValueT> extends PropertyImpl<BeanT> {
    private final Accessor<BeanT, ValueT> acc;
    private final QName[] acceptedElements;
    private final boolean nillable;
    private final Name nullTagName;
    private RuntimeElementPropertyInfo prop;
    private final Map<Class, TagAndType> typeNames;

    @Override // com.sun.xml.bind.v2.runtime.property.Property
    public String getIdValue(BeanT beant) {
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v1, types: [com.sun.xml.bind.v2.model.runtime.RuntimeNonElement, com.sun.xml.bind.v2.model.runtime.RuntimeTypeInfo] */
    public SingleElementNodeProperty(JAXBContextImpl jAXBContextImpl, RuntimeElementPropertyInfo runtimeElementPropertyInfo) {
        super(jAXBContextImpl, runtimeElementPropertyInfo);
        this.typeNames = new HashMap();
        this.acc = runtimeElementPropertyInfo.getAccessor().optimize(jAXBContextImpl);
        this.prop = runtimeElementPropertyInfo;
        this.acceptedElements = new QName[runtimeElementPropertyInfo.getTypes().size()];
        boolean zIsNillable = false;
        int i = 0;
        while (true) {
            QName[] qNameArr = this.acceptedElements;
            if (i >= qNameArr.length) {
                break;
            }
            qNameArr[i] = ((RuntimeTypeRef) runtimeElementPropertyInfo.getTypes().get(i)).getTagName();
            i++;
        }
        Iterator<? extends TypeRef<Type, Class>> it = runtimeElementPropertyInfo.getTypes().iterator();
        QName tagName = null;
        while (it.hasNext()) {
            RuntimeTypeRef runtimeTypeRef = (RuntimeTypeRef) it.next();
            JaxBeanInfo orCreate = jAXBContextImpl.getOrCreate((RuntimeTypeInfo) runtimeTypeRef.getTarget());
            if (tagName == null) {
                tagName = runtimeTypeRef.getTagName();
            }
            this.typeNames.put(orCreate.jaxbType, new TagAndType(jAXBContextImpl.nameBuilder.createElementName(runtimeTypeRef.getTagName()), orCreate));
            zIsNillable |= runtimeTypeRef.isNillable();
        }
        this.nullTagName = jAXBContextImpl.nameBuilder.createElementName(tagName);
        this.nillable = zIsNillable;
    }

    @Override // com.sun.xml.bind.v2.runtime.property.PropertyImpl, com.sun.xml.bind.v2.runtime.property.Property
    public void wrapUp() {
        super.wrapUp();
        this.prop = null;
    }

    @Override // com.sun.xml.bind.v2.runtime.property.Property
    public void reset(BeanT beant) throws AccessorException {
        this.acc.set(beant, null);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.sun.xml.bind.v2.runtime.property.PropertyImpl, com.sun.xml.bind.v2.runtime.property.Property
    public void serializeBody(BeanT beant, XMLSerializer xMLSerializer, Object obj) throws SAXException, XMLStreamException, IOException, AccessorException {
        ValueT valuet = this.acc.get(beant);
        if (valuet != null) {
            Class<?> cls = valuet.getClass();
            TagAndType value = this.typeNames.get(cls);
            if (value == null) {
                Iterator<Map.Entry<Class, TagAndType>> it = this.typeNames.entrySet().iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    Map.Entry<Class, TagAndType> next = it.next();
                    if (next.getKey().isAssignableFrom(cls)) {
                        value = next.getValue();
                        break;
                    }
                }
            }
            boolean z = (beant instanceof JAXBElement) && ((JAXBElement) beant).isNil();
            if (value == null) {
                xMLSerializer.startElement(this.typeNames.values().iterator().next().tagName, null);
                xMLSerializer.childAsXsiType(valuet, this.fieldName, xMLSerializer.grammar.getBeanInfo(Object.class), z && this.nillable);
            } else {
                xMLSerializer.startElement(value.tagName, null);
                xMLSerializer.childAsXsiType(valuet, this.fieldName, value.beanInfo, z && this.nillable);
            }
            xMLSerializer.endElement();
            return;
        }
        if (this.nillable) {
            xMLSerializer.startElement(this.nullTagName, null);
            xMLSerializer.writeXsiNilTrue();
            xMLSerializer.endElement();
        }
    }

    @Override // com.sun.xml.bind.v2.runtime.property.StructureLoaderBuilder
    public void buildChildElementUnmarshallers(UnmarshallerChain unmarshallerChain, QNameMap<ChildLoader> qNameMap) {
        JAXBContextImpl jAXBContextImpl = unmarshallerChain.context;
        for (TypeRef<Type, Class> typeRef : this.prop.getTypes()) {
            Loader loader = jAXBContextImpl.getOrCreate((RuntimeTypeInfo) typeRef.getTarget()).getLoader(jAXBContextImpl, !Modifier.isFinal(r3.jaxbType.getModifiers()));
            if (typeRef.getDefaultValue() != null) {
                loader = new DefaultValueLoaderDecorator(loader, typeRef.getDefaultValue());
            }
            if (this.nillable || unmarshallerChain.context.allNillable) {
                loader = new XsiNilLoader.Single(loader, this.acc);
            }
            qNameMap.put(typeRef.getTagName(), new ChildLoader(loader, this.acc));
        }
    }

    @Override // com.sun.xml.bind.v2.runtime.property.Property
    public PropertyKind getKind() {
        return PropertyKind.ELEMENT;
    }

    @Override // com.sun.xml.bind.v2.runtime.property.PropertyImpl, com.sun.xml.bind.v2.runtime.property.Property
    public Accessor getElementPropertyAccessor(String str, String str2) {
        for (QName qName : this.acceptedElements) {
            if (qName.getNamespaceURI().equals(str) && qName.getLocalPart().equals(str2)) {
                return this.acc;
            }
        }
        return null;
    }
}
