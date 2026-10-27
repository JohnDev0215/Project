package com.sun.xml.bind.v2.model.core;

import com.sun.xml.bind.v2.model.nav.Navigator;
import java.util.Map;
import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlNsForm;
import javax.xml.namespace.QName;
import javax.xml.transform.Result;

/* JADX INFO: loaded from: classes3.dex */
public interface TypeInfoSet<T, C, F, M> {
    Map<? extends T, ? extends ArrayInfo<T, C>> arrays();

    Map<C, ? extends ClassInfo<T, C>> beans();

    Map<T, ? extends BuiltinLeafInfo<T, C>> builtins();

    void dump(Result result) throws JAXBException;

    Map<C, ? extends EnumLeafInfo<T, C>> enums();

    Iterable<? extends ElementInfo<T, C>> getAllElements();

    NonElement<T, C> getAnyTypeInfo();

    XmlNsForm getAttributeFormDefault(String str);

    NonElement<T, C> getClassInfo(C c);

    XmlNsForm getElementFormDefault(String str);

    ElementInfo<T, C> getElementInfo(C c, QName qName);

    Map<QName, ? extends ElementInfo<T, C>> getElementMappings(C c);

    Navigator<T, C, F, M> getNavigator();

    Map<String, String> getSchemaLocations();

    NonElement<T, C> getTypeInfo(Ref<T, C> ref);

    NonElement<T, C> getTypeInfo(T t);

    Map<String, String> getXmlNs(String str);
}
