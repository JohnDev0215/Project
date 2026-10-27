package com.sun.xml.bind.v2.model.runtime;

import com.sun.xml.bind.v2.model.core.ElementPropertyInfo;
import com.sun.xml.bind.v2.model.core.TypeInfo;
import com.sun.xml.bind.v2.model.core.TypeRef;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public interface RuntimeElementPropertyInfo extends ElementPropertyInfo<Type, Class>, RuntimePropertyInfo {
    @Override // com.sun.xml.bind.v2.model.core.ElementPropertyInfo
    List<? extends TypeRef<Type, Class>> getTypes();

    @Override // com.sun.xml.bind.v2.model.core.PropertyInfo
    Collection<? extends TypeInfo<Type, Class>> ref();
}
