package com.sun.xml.bind.v2.runtime.property;

import com.sun.xml.bind.api.AccessorException;
import com.sun.xml.bind.v2.model.core.PropertyKind;
import com.sun.xml.bind.v2.model.runtime.RuntimePropertyInfo;
import com.sun.xml.bind.v2.model.runtime.RuntimeValuePropertyInfo;
import com.sun.xml.bind.v2.runtime.JAXBContextImpl;
import com.sun.xml.bind.v2.runtime.XMLSerializer;
import com.sun.xml.bind.v2.runtime.reflect.Accessor;
import com.sun.xml.bind.v2.runtime.reflect.TransducedAccessor;
import com.sun.xml.bind.v2.runtime.unmarshaller.ChildLoader;
import com.sun.xml.bind.v2.runtime.unmarshaller.ValuePropertyLoader;
import com.sun.xml.bind.v2.util.QNameMap;
import java.io.IOException;
import javax.xml.stream.XMLStreamException;
import org.xml.sax.SAXException;

/* JADX INFO: loaded from: classes3.dex */
public final class ValueProperty<BeanT> extends PropertyImpl<BeanT> {
    private final Accessor<BeanT, ?> acc;
    private final TransducedAccessor<BeanT> xacc;

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

    @Override // com.sun.xml.bind.v2.runtime.property.PropertyImpl, com.sun.xml.bind.v2.runtime.property.Property
    public /* bridge */ /* synthetic */ void setHiddenByOverride(boolean z) {
        super.setHiddenByOverride(z);
    }

    @Override // com.sun.xml.bind.v2.runtime.property.PropertyImpl, com.sun.xml.bind.v2.runtime.property.Property
    public /* bridge */ /* synthetic */ void wrapUp() {
        super.wrapUp();
    }

    public ValueProperty(JAXBContextImpl jAXBContextImpl, RuntimeValuePropertyInfo runtimeValuePropertyInfo) {
        super(jAXBContextImpl, runtimeValuePropertyInfo);
        this.xacc = TransducedAccessor.get(jAXBContextImpl, runtimeValuePropertyInfo);
        this.acc = runtimeValuePropertyInfo.getAccessor();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // com.sun.xml.bind.v2.runtime.property.PropertyImpl, com.sun.xml.bind.v2.runtime.property.Property
    public final void serializeBody(BeanT beant, XMLSerializer xMLSerializer, Object obj) throws SAXException, XMLStreamException, IOException, AccessorException {
        if (this.xacc.hasValue(beant)) {
            this.xacc.writeText(xMLSerializer, beant, this.fieldName);
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
        qNameMap.put(StructureLoaderBuilder.TEXT_HANDLER, new ChildLoader(new ValuePropertyLoader(this.xacc), null));
    }

    @Override // com.sun.xml.bind.v2.runtime.property.Property
    public PropertyKind getKind() {
        return PropertyKind.VALUE;
    }

    @Override // com.sun.xml.bind.v2.runtime.property.Property
    public void reset(BeanT beant) throws AccessorException {
        this.acc.set(beant, null);
    }

    @Override // com.sun.xml.bind.v2.runtime.property.Property
    public String getIdValue(BeanT beant) throws SAXException, AccessorException {
        return this.xacc.print(beant).toString();
    }
}
