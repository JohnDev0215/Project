package com.sun.xml.bind.v2.runtime.property;

import com.sun.xml.bind.api.AccessorException;
import com.sun.xml.bind.v2.model.core.PropertyKind;
import com.sun.xml.bind.v2.model.runtime.RuntimeAttributePropertyInfo;
import com.sun.xml.bind.v2.model.runtime.RuntimePropertyInfo;
import com.sun.xml.bind.v2.runtime.JAXBContextImpl;
import com.sun.xml.bind.v2.runtime.Name;
import com.sun.xml.bind.v2.runtime.XMLSerializer;
import com.sun.xml.bind.v2.runtime.reflect.Accessor;
import com.sun.xml.bind.v2.runtime.reflect.TransducedAccessor;
import com.sun.xml.bind.v2.runtime.unmarshaller.ChildLoader;
import com.sun.xml.bind.v2.util.QNameMap;
import java.io.IOException;
import javax.xml.stream.XMLStreamException;
import org.xml.sax.SAXException;

/* JADX INFO: loaded from: classes3.dex */
public final class AttributeProperty<BeanT> extends PropertyImpl<BeanT> implements Comparable<AttributeProperty> {
    private final Accessor acc;
    public final Name attName;
    public final TransducedAccessor<BeanT> xacc;

    @Override // com.sun.xml.bind.v2.runtime.property.PropertyImpl, com.sun.xml.bind.v2.runtime.property.Property
    public /* bridge */ /* synthetic */ Accessor getElementPropertyAccessor(String str, String str2) {
        return super.getElementPropertyAccessor(str, str2);
    }

    @Override // com.sun.xml.bind.v2.runtime.property.PropertyImpl, com.sun.xml.bind.v2.runtime.property.Property
    public /* bridge */ /* synthetic */ String getFieldName() {
        return super.getFieldName();
    }

    @Override // com.sun.xml.bind.v2.runtime.property.PropertyImpl, com.sun.xml.bind.v2.runtime.property.Property
    public /* bridge */ /* synthetic */ RuntimePropertyInfo getInfo() {
        return super.getInfo();
    }

    @Override // com.sun.xml.bind.v2.runtime.property.PropertyImpl, com.sun.xml.bind.v2.runtime.property.Property
    public /* bridge */ /* synthetic */ boolean isHiddenByOverride() {
        return super.isHiddenByOverride();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.sun.xml.bind.v2.runtime.property.PropertyImpl, com.sun.xml.bind.v2.runtime.property.Property
    public /* bridge */ /* synthetic */ void serializeBody(Object obj, XMLSerializer xMLSerializer, Object obj2) throws SAXException, XMLStreamException, IOException, AccessorException {
        super.serializeBody(obj, xMLSerializer, obj2);
    }

    @Override // com.sun.xml.bind.v2.runtime.property.PropertyImpl, com.sun.xml.bind.v2.runtime.property.Property
    public /* bridge */ /* synthetic */ void setHiddenByOverride(boolean z) {
        super.setHiddenByOverride(z);
    }

    @Override // com.sun.xml.bind.v2.runtime.property.PropertyImpl, com.sun.xml.bind.v2.runtime.property.Property
    public /* bridge */ /* synthetic */ void wrapUp() {
        super.wrapUp();
    }

    public AttributeProperty(JAXBContextImpl jAXBContextImpl, RuntimeAttributePropertyInfo runtimeAttributePropertyInfo) {
        super(jAXBContextImpl, runtimeAttributePropertyInfo);
        this.attName = jAXBContextImpl.nameBuilder.createAttributeName(runtimeAttributePropertyInfo.getXmlName());
        this.xacc = TransducedAccessor.get(jAXBContextImpl, runtimeAttributePropertyInfo);
        this.acc = runtimeAttributePropertyInfo.getAccessor();
    }

    public void serializeAttributes(BeanT beant, XMLSerializer xMLSerializer) throws SAXException, XMLStreamException, IOException, AccessorException {
        CharSequence charSequencePrint = this.xacc.print(beant);
        if (charSequencePrint != null) {
            xMLSerializer.attribute(this.attName, charSequencePrint.toString());
        }
    }

    @Override // com.sun.xml.bind.v2.runtime.property.PropertyImpl, com.sun.xml.bind.v2.runtime.property.Property
    public void serializeURIs(BeanT beant, XMLSerializer xMLSerializer) throws SAXException, AccessorException {
        this.xacc.declareNamespace(beant, xMLSerializer);
    }

    @Override // com.sun.xml.bind.v2.runtime.property.PropertyImpl, com.sun.xml.bind.v2.runtime.property.Property
    public boolean hasSerializeURIAction() {
        return this.xacc.useNamespace();
    }

    @Override // com.sun.xml.bind.v2.runtime.property.StructureLoaderBuilder
    public void buildChildElementUnmarshallers(UnmarshallerChain unmarshallerChain, QNameMap<ChildLoader> qNameMap) {
        throw new IllegalStateException();
    }

    @Override // com.sun.xml.bind.v2.runtime.property.Property
    public PropertyKind getKind() {
        return PropertyKind.ATTRIBUTE;
    }

    @Override // com.sun.xml.bind.v2.runtime.property.Property
    public void reset(BeanT beant) throws AccessorException {
        this.acc.set(beant, null);
    }

    @Override // com.sun.xml.bind.v2.runtime.property.Property
    public String getIdValue(BeanT beant) throws SAXException, AccessorException {
        return this.xacc.print(beant).toString();
    }

    @Override // java.lang.Comparable
    public int compareTo(AttributeProperty attributeProperty) {
        return this.attName.compareTo(attributeProperty.attName);
    }
}
