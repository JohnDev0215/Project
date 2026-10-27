package com.sun.xml.bind.v2.runtime.property;

import com.sun.xml.bind.api.AccessorException;
import com.sun.xml.bind.v2.model.core.ID;
import com.sun.xml.bind.v2.model.core.PropertyKind;
import com.sun.xml.bind.v2.model.runtime.RuntimeElementPropertyInfo;
import com.sun.xml.bind.v2.model.runtime.RuntimeTypeRef;
import com.sun.xml.bind.v2.runtime.JAXBContextImpl;
import com.sun.xml.bind.v2.runtime.Name;
import com.sun.xml.bind.v2.runtime.XMLSerializer;
import com.sun.xml.bind.v2.runtime.reflect.Accessor;
import com.sun.xml.bind.v2.runtime.reflect.TransducedAccessor;
import com.sun.xml.bind.v2.runtime.unmarshaller.ChildLoader;
import com.sun.xml.bind.v2.runtime.unmarshaller.DefaultValueLoaderDecorator;
import com.sun.xml.bind.v2.runtime.unmarshaller.LeafPropertyLoader;
import com.sun.xml.bind.v2.runtime.unmarshaller.LeafPropertyXsiLoader;
import com.sun.xml.bind.v2.runtime.unmarshaller.Loader;
import com.sun.xml.bind.v2.runtime.unmarshaller.XsiNilLoader;
import com.sun.xml.bind.v2.util.QNameMap;
import java.io.IOException;
import java.lang.reflect.Modifier;
import javax.xml.bind.JAXBElement;
import javax.xml.stream.XMLStreamException;
import org.xml.sax.SAXException;

/* JADX INFO: loaded from: classes3.dex */
final class SingleElementLeafProperty<BeanT> extends PropertyImpl<BeanT> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final Accessor acc;
    private final String defaultValue;
    private final boolean idRef;
    private final boolean improvedXsiTypeHandling;
    private final boolean nillable;
    private final Name tagName;
    private final TransducedAccessor<BeanT> xacc;

    public SingleElementLeafProperty(JAXBContextImpl jAXBContextImpl, RuntimeElementPropertyInfo runtimeElementPropertyInfo) {
        super(jAXBContextImpl, runtimeElementPropertyInfo);
        RuntimeTypeRef runtimeTypeRef = (RuntimeTypeRef) runtimeElementPropertyInfo.getTypes().get(0);
        this.tagName = jAXBContextImpl.nameBuilder.createElementName(runtimeTypeRef.getTagName());
        this.nillable = runtimeTypeRef.isNillable();
        this.defaultValue = runtimeTypeRef.getDefaultValue();
        this.acc = runtimeElementPropertyInfo.getAccessor().optimize(jAXBContextImpl);
        this.xacc = TransducedAccessor.get(jAXBContextImpl, runtimeTypeRef);
        this.improvedXsiTypeHandling = jAXBContextImpl.improvedXsiTypeHandling;
        this.idRef = runtimeTypeRef.getSource().id() == ID.IDREF;
    }

    @Override // com.sun.xml.bind.v2.runtime.property.Property
    public void reset(BeanT beant) throws AccessorException {
        this.acc.set(beant, null);
    }

    @Override // com.sun.xml.bind.v2.runtime.property.Property
    public String getIdValue(BeanT beant) throws SAXException, AccessorException {
        return this.xacc.print(beant).toString();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // com.sun.xml.bind.v2.runtime.property.PropertyImpl, com.sun.xml.bind.v2.runtime.property.Property
    public void serializeBody(BeanT beant, XMLSerializer xMLSerializer, Object obj) throws SAXException, XMLStreamException, IOException, AccessorException {
        Object unadapted;
        boolean zHasValue = this.xacc.hasValue(beant);
        try {
            unadapted = this.acc.getUnadapted(beant);
        } catch (AccessorException unused) {
            unadapted = null;
        }
        Class valueType = this.acc.getValueType();
        if (xsiTypeNeeded(beant, xMLSerializer, unadapted, valueType)) {
            xMLSerializer.startElement(this.tagName, obj);
            xMLSerializer.childAsXsiType(unadapted, this.fieldName, xMLSerializer.grammar.getBeanInfo(valueType), false);
            xMLSerializer.endElement();
        } else if (zHasValue) {
            this.xacc.writeLeafElement(xMLSerializer, this.tagName, beant, this.fieldName);
        } else if (this.nillable) {
            xMLSerializer.startElement(this.tagName, null);
            xMLSerializer.writeXsiNilTrue();
            xMLSerializer.endElement();
        }
    }

    private boolean xsiTypeNeeded(BeanT beant, XMLSerializer xMLSerializer, Object obj, Class cls) {
        if (!this.improvedXsiTypeHandling || this.acc.isAdapted() || obj == null || obj.getClass().equals(cls) || this.idRef || cls.isPrimitive()) {
            return false;
        }
        return this.acc.isValueTypeAbstractable() || isNillableAbstract(beant, xMLSerializer.grammar, obj, cls);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private boolean isNillableAbstract(BeanT beant, JAXBContextImpl jAXBContextImpl, Object obj, Class cls) {
        if (!this.nillable || cls != Object.class || beant.getClass() != JAXBElement.class) {
            return false;
        }
        Class<?> cls2 = obj.getClass();
        Class declaredType = ((JAXBElement) beant).getDeclaredType();
        if (!declaredType.equals(cls2) && declaredType.isAssignableFrom(cls2) && Modifier.isAbstract(declaredType.getModifiers())) {
            return this.acc.isAbstractable(declaredType);
        }
        return false;
    }

    @Override // com.sun.xml.bind.v2.runtime.property.StructureLoaderBuilder
    public void buildChildElementUnmarshallers(UnmarshallerChain unmarshallerChain, QNameMap<ChildLoader> qNameMap) {
        Loader leafPropertyLoader = new LeafPropertyLoader(this.xacc);
        if (this.defaultValue != null) {
            leafPropertyLoader = new DefaultValueLoaderDecorator(leafPropertyLoader, this.defaultValue);
        }
        if (this.nillable || unmarshallerChain.context.allNillable) {
            leafPropertyLoader = new XsiNilLoader.Single(leafPropertyLoader, this.acc);
        }
        if (this.improvedXsiTypeHandling) {
            leafPropertyLoader = new LeafPropertyXsiLoader(leafPropertyLoader, this.xacc, this.acc);
        }
        qNameMap.put(this.tagName, new ChildLoader(leafPropertyLoader, null));
    }

    @Override // com.sun.xml.bind.v2.runtime.property.Property
    public PropertyKind getKind() {
        return PropertyKind.ELEMENT;
    }

    @Override // com.sun.xml.bind.v2.runtime.property.PropertyImpl, com.sun.xml.bind.v2.runtime.property.Property
    public Accessor getElementPropertyAccessor(String str, String str2) {
        if (this.tagName.equals(str, str2)) {
            return this.acc;
        }
        return null;
    }
}
