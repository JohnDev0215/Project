package com.sun.xml.bind.v2.model.runtime;

import com.sun.xml.bind.v2.model.core.NonElement;
import com.sun.xml.bind.v2.model.core.PropertyInfo;
import com.sun.xml.bind.v2.model.core.TypeRef;
import java.lang.reflect.Type;

/* JADX INFO: loaded from: classes3.dex */
public interface RuntimeTypeRef extends TypeRef<Type, Class>, RuntimeNonElementRef {
    @Override // com.sun.xml.bind.v2.model.core.NonElementRef
    PropertyInfo<Type, Class> getSource();

    @Override // com.sun.xml.bind.v2.model.core.NonElementRef
    NonElement<Type, Class> getTarget();
}
