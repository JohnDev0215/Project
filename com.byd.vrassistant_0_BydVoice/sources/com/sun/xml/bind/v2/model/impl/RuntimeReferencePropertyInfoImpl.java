package com.sun.xml.bind.v2.model.impl;

import com.sun.xml.bind.v2.model.core.Element;
import com.sun.xml.bind.v2.model.core.TypeInfo;
import com.sun.xml.bind.v2.model.impl.RuntimeClassInfoImpl;
import com.sun.xml.bind.v2.model.runtime.RuntimeReferencePropertyInfo;
import com.sun.xml.bind.v2.runtime.reflect.Accessor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Set;

/* JADX INFO: loaded from: classes3.dex */
class RuntimeReferencePropertyInfoImpl extends ReferencePropertyInfoImpl<Type, Class, Field, Method> implements RuntimeReferencePropertyInfo {
    private final Accessor acc;

    @Override // com.sun.xml.bind.v2.model.impl.PropertyInfoImpl, com.sun.xml.bind.v2.model.runtime.RuntimePropertyInfo
    public /* bridge */ /* synthetic */ Type getIndividualType() {
        return (Type) super.getIndividualType();
    }

    @Override // com.sun.xml.bind.v2.model.impl.PropertyInfoImpl, com.sun.xml.bind.v2.model.runtime.RuntimePropertyInfo
    public /* bridge */ /* synthetic */ Type getRawType() {
        return (Type) super.getRawType();
    }

    public RuntimeReferencePropertyInfoImpl(RuntimeClassInfoImpl runtimeClassInfoImpl, PropertySeed<Type, Class, Field, Method> propertySeed) {
        super(runtimeClassInfoImpl, propertySeed);
        Accessor accessor = ((RuntimeClassInfoImpl.RuntimePropertySeed) propertySeed).getAccessor();
        if (getAdapter() != null && !isCollection()) {
            accessor = accessor.adapt(getAdapter());
        }
        this.acc = accessor;
    }

    @Override // com.sun.xml.bind.v2.model.impl.ReferencePropertyInfoImpl, com.sun.xml.bind.v2.model.core.ReferencePropertyInfo
    public Set<? extends Element<Type, Class>> getElements() {
        return super.getElements();
    }

    @Override // com.sun.xml.bind.v2.model.impl.ReferencePropertyInfoImpl, com.sun.xml.bind.v2.model.core.PropertyInfo
    public Collection<? extends TypeInfo<Type, Class>> ref() {
        return super.ref();
    }

    @Override // com.sun.xml.bind.v2.model.runtime.RuntimePropertyInfo
    public Accessor getAccessor() {
        return this.acc;
    }

    @Override // com.sun.xml.bind.v2.model.runtime.RuntimePropertyInfo
    public boolean elementOnlyContent() {
        return !isMixed();
    }
}
