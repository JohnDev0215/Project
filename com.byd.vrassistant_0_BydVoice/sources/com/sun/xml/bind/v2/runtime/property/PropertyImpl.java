package com.sun.xml.bind.v2.runtime.property;

import com.sun.xml.bind.api.AccessorException;
import com.sun.xml.bind.v2.model.runtime.RuntimePropertyInfo;
import com.sun.xml.bind.v2.runtime.JAXBContextImpl;
import com.sun.xml.bind.v2.runtime.XMLSerializer;
import com.sun.xml.bind.v2.runtime.reflect.Accessor;
import java.io.IOException;
import javax.xml.stream.XMLStreamException;
import org.xml.sax.SAXException;

/* JADX INFO: loaded from: classes3.dex */
abstract class PropertyImpl<BeanT> implements Property<BeanT> {
    protected final String fieldName;
    private boolean hiddenByOverride = false;
    private RuntimePropertyInfo propertyInfo;

    @Override // com.sun.xml.bind.v2.runtime.property.Property
    public Accessor getElementPropertyAccessor(String str, String str2) {
        return null;
    }

    @Override // com.sun.xml.bind.v2.runtime.property.Property
    public boolean hasSerializeURIAction() {
        return false;
    }

    @Override // com.sun.xml.bind.v2.runtime.property.Property
    public void serializeBody(BeanT beant, XMLSerializer xMLSerializer, Object obj) throws SAXException, XMLStreamException, IOException, AccessorException {
    }

    @Override // com.sun.xml.bind.v2.runtime.property.Property
    public void serializeURIs(BeanT beant, XMLSerializer xMLSerializer) throws SAXException, AccessorException {
    }

    @Override // com.sun.xml.bind.v2.runtime.property.Property
    public void wrapUp() {
    }

    public PropertyImpl(JAXBContextImpl jAXBContextImpl, RuntimePropertyInfo runtimePropertyInfo) {
        this.propertyInfo = null;
        this.fieldName = runtimePropertyInfo.getName();
        if (jAXBContextImpl.retainPropertyInfo) {
            this.propertyInfo = runtimePropertyInfo;
        }
    }

    @Override // com.sun.xml.bind.v2.runtime.property.Property
    public RuntimePropertyInfo getInfo() {
        return this.propertyInfo;
    }

    @Override // com.sun.xml.bind.v2.runtime.property.Property
    public boolean isHiddenByOverride() {
        return this.hiddenByOverride;
    }

    @Override // com.sun.xml.bind.v2.runtime.property.Property
    public void setHiddenByOverride(boolean z) {
        this.hiddenByOverride = z;
    }

    @Override // com.sun.xml.bind.v2.runtime.property.Property
    public String getFieldName() {
        return this.fieldName;
    }
}
