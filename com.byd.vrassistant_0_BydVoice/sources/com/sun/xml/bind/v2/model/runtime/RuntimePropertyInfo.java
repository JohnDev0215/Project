package com.sun.xml.bind.v2.model.runtime;

import com.sun.xml.bind.v2.model.core.PropertyInfo;
import com.sun.xml.bind.v2.model.core.TypeInfo;
import com.sun.xml.bind.v2.runtime.reflect.Accessor;
import java.lang.reflect.Type;
import java.util.Collection;

/* JADX INFO: loaded from: classes3.dex */
public interface RuntimePropertyInfo extends PropertyInfo<Type, Class> {
    boolean elementOnlyContent();

    Accessor getAccessor();

    Type getIndividualType();

    Type getRawType();

    @Override // com.sun.xml.bind.v2.model.core.PropertyInfo
    Collection<? extends TypeInfo<Type, Class>> ref();
}
