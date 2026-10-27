package com.sun.xml.bind.v2.runtime.property;

import com.sun.xml.bind.api.AccessorException;
import com.sun.xml.bind.v2.model.core.PropertyKind;
import com.sun.xml.bind.v2.model.runtime.RuntimeElementPropertyInfo;
import com.sun.xml.bind.v2.model.runtime.RuntimeTypeRef;
import com.sun.xml.bind.v2.runtime.JAXBContextImpl;
import com.sun.xml.bind.v2.runtime.Name;
import com.sun.xml.bind.v2.runtime.XMLSerializer;
import com.sun.xml.bind.v2.runtime.reflect.Accessor;
import com.sun.xml.bind.v2.runtime.reflect.ListTransducedAccessorImpl;
import com.sun.xml.bind.v2.runtime.reflect.TransducedAccessor;
import com.sun.xml.bind.v2.runtime.unmarshaller.ChildLoader;
import com.sun.xml.bind.v2.runtime.unmarshaller.DefaultValueLoaderDecorator;
import com.sun.xml.bind.v2.runtime.unmarshaller.LeafPropertyLoader;
import com.sun.xml.bind.v2.util.QNameMap;
import java.io.IOException;
import javax.xml.stream.XMLStreamException;
import org.xml.sax.SAXException;

/* JADX INFO: loaded from: classes3.dex */
final class ListElementProperty<BeanT, ListT, ItemT> extends ArrayProperty<BeanT, ListT, ItemT> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final String defaultValue;
    private final Name tagName;
    private final TransducedAccessor<BeanT> xacc;

    public ListElementProperty(JAXBContextImpl jAXBContextImpl, RuntimeElementPropertyInfo runtimeElementPropertyInfo) {
        super(jAXBContextImpl, runtimeElementPropertyInfo);
        RuntimeTypeRef runtimeTypeRef = (RuntimeTypeRef) runtimeElementPropertyInfo.getTypes().get(0);
        this.tagName = jAXBContextImpl.nameBuilder.createElementName(runtimeTypeRef.getTagName());
        this.defaultValue = runtimeTypeRef.getDefaultValue();
        this.xacc = new ListTransducedAccessorImpl(runtimeTypeRef.getTransducer(), this.acc, this.lister);
    }

    @Override // com.sun.xml.bind.v2.runtime.property.Property
    public PropertyKind getKind() {
        return PropertyKind.ELEMENT;
    }

    @Override // com.sun.xml.bind.v2.runtime.property.StructureLoaderBuilder
    public void buildChildElementUnmarshallers(UnmarshallerChain unmarshallerChain, QNameMap<ChildLoader> qNameMap) {
        qNameMap.put(this.tagName, new ChildLoader(new DefaultValueLoaderDecorator(new LeafPropertyLoader(this.xacc), this.defaultValue), null));
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // com.sun.xml.bind.v2.runtime.property.PropertyImpl, com.sun.xml.bind.v2.runtime.property.Property
    public void serializeBody(BeanT beant, XMLSerializer xMLSerializer, Object obj) throws SAXException, XMLStreamException, IOException, AccessorException {
        ListT listt = this.acc.get(beant);
        if (listt != null) {
            if (this.xacc.useNamespace()) {
                xMLSerializer.startElement(this.tagName, null);
                this.xacc.declareNamespace(beant, xMLSerializer);
                xMLSerializer.endNamespaceDecls(listt);
                xMLSerializer.endAttributes();
                this.xacc.writeText(xMLSerializer, beant, this.fieldName);
                xMLSerializer.endElement();
                return;
            }
            this.xacc.writeLeafElement(xMLSerializer, this.tagName, beant, this.fieldName);
        }
    }

    @Override // com.sun.xml.bind.v2.runtime.property.PropertyImpl, com.sun.xml.bind.v2.runtime.property.Property
    public Accessor getElementPropertyAccessor(String str, String str2) {
        Name name = this.tagName;
        if (name == null || !name.equals(str, str2)) {
            return null;
        }
        return this.acc;
    }
}
