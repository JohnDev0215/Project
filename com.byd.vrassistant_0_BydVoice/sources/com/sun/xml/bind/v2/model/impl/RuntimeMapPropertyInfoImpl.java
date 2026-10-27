package com.sun.xml.bind.v2.model.impl;

import com.sun.xml.bind.v2.model.core.NonElement;
import com.sun.xml.bind.v2.model.core.TypeInfo;
import com.sun.xml.bind.v2.model.impl.RuntimeClassInfoImpl;
import com.sun.xml.bind.v2.model.runtime.RuntimeMapPropertyInfo;
import com.sun.xml.bind.v2.model.runtime.RuntimeNonElement;
import com.sun.xml.bind.v2.runtime.reflect.Accessor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
class RuntimeMapPropertyInfoImpl extends MapPropertyInfoImpl<Type, Class, Field, Method> implements RuntimeMapPropertyInfo {
    private final Accessor acc;

    @Override // com.sun.xml.bind.v2.model.runtime.RuntimePropertyInfo
    public boolean elementOnlyContent() {
        return true;
    }

    @Override // com.sun.xml.bind.v2.model.impl.PropertyInfoImpl, com.sun.xml.bind.v2.model.runtime.RuntimePropertyInfo
    public /* bridge */ /* synthetic */ Type getIndividualType() {
        return (Type) super.getIndividualType();
    }

    @Override // com.sun.xml.bind.v2.model.impl.PropertyInfoImpl, com.sun.xml.bind.v2.model.runtime.RuntimePropertyInfo
    public /* bridge */ /* synthetic */ Type getRawType() {
        return (Type) super.getRawType();
    }

    RuntimeMapPropertyInfoImpl(RuntimeClassInfoImpl runtimeClassInfoImpl, PropertySeed<Type, Class, Field, Method> propertySeed) {
        super(runtimeClassInfoImpl, propertySeed);
        this.acc = ((RuntimeClassInfoImpl.RuntimePropertySeed) propertySeed).getAccessor();
    }

    @Override // com.sun.xml.bind.v2.model.runtime.RuntimePropertyInfo
    public Accessor getAccessor() {
        return this.acc;
    }

    @Override // com.sun.xml.bind.v2.model.impl.MapPropertyInfoImpl, com.sun.xml.bind.v2.model.core.MapPropertyInfo
    public NonElement<Type, Class> getKeyType() {
        return (RuntimeNonElement) super.getKeyType();
    }

    @Override // com.sun.xml.bind.v2.model.impl.MapPropertyInfoImpl, com.sun.xml.bind.v2.model.core.MapPropertyInfo
    public NonElement<Type, Class> getValueType() {
        return (RuntimeNonElement) super.getValueType();
    }

    @Override // com.sun.xml.bind.v2.model.impl.MapPropertyInfoImpl, com.sun.xml.bind.v2.model.core.PropertyInfo
    public Collection<? extends TypeInfo<Type, Class>> ref() {
        return (List) super.ref();
    }
}
