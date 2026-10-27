package com.sun.xml.bind.v2.runtime.property;

import com.sun.xml.bind.api.AccessorException;
import com.sun.xml.bind.v2.model.core.PropertyKind;
import com.sun.xml.bind.v2.model.runtime.RuntimePropertyInfo;
import com.sun.xml.bind.v2.runtime.XMLSerializer;
import com.sun.xml.bind.v2.runtime.reflect.Accessor;
import java.io.IOException;
import javax.xml.stream.XMLStreamException;
import org.xml.sax.SAXException;

/* JADX INFO: loaded from: classes3.dex */
public interface Property<BeanT> extends StructureLoaderBuilder {
    Accessor getElementPropertyAccessor(String str, String str2);

    String getFieldName();

    String getIdValue(BeanT beant) throws SAXException, AccessorException;

    RuntimePropertyInfo getInfo();

    PropertyKind getKind();

    boolean hasSerializeURIAction();

    boolean isHiddenByOverride();

    void reset(BeanT beant) throws AccessorException;

    void serializeBody(BeanT beant, XMLSerializer xMLSerializer, Object obj) throws SAXException, XMLStreamException, IOException, AccessorException;

    void serializeURIs(BeanT beant, XMLSerializer xMLSerializer) throws SAXException, AccessorException;

    void setHiddenByOverride(boolean z);

    void wrapUp();
}
