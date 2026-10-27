package com.sun.xml.bind.v2.model.runtime;

import com.sun.xml.bind.v2.model.core.ClassInfo;
import com.sun.xml.bind.v2.model.core.ElementInfo;
import com.sun.xml.bind.v2.model.core.ElementPropertyInfo;
import com.sun.xml.bind.v2.model.core.NonElement;
import java.lang.reflect.Type;

/* JADX INFO: loaded from: classes3.dex */
public interface RuntimeElementInfo extends ElementInfo<Type, Class>, RuntimeElement {
    @Override // com.sun.xml.bind.v2.model.core.ElementInfo
    NonElement<Type, Class> getContentType();

    @Override // com.sun.xml.bind.v2.model.core.ElementInfo
    ElementPropertyInfo<Type, Class> getProperty();

    @Override // com.sun.xml.bind.v2.model.core.Element
    ClassInfo<Type, Class> getScope();

    @Override // com.sun.xml.bind.v2.model.core.ElementInfo, com.sun.xml.bind.v2.model.core.TypeInfo
    Type getType();
}
