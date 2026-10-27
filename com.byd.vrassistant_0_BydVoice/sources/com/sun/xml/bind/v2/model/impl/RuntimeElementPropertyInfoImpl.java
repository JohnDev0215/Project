package com.sun.xml.bind.v2.model.impl;

import com.sun.xml.bind.v2.model.core.TypeInfo;
import com.sun.xml.bind.v2.model.core.TypeRef;
import com.sun.xml.bind.v2.model.impl.RuntimeClassInfoImpl;
import com.sun.xml.bind.v2.model.runtime.RuntimeElementPropertyInfo;
import com.sun.xml.bind.v2.runtime.reflect.Accessor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;
import javax.xml.namespace.QName;

/* JADX INFO: loaded from: classes3.dex */
class RuntimeElementPropertyInfoImpl extends ElementPropertyInfoImpl<Type, Class, Field, Method> implements RuntimeElementPropertyInfo {
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

    RuntimeElementPropertyInfoImpl(RuntimeClassInfoImpl runtimeClassInfoImpl, PropertySeed<Type, Class, Field, Method> propertySeed) {
        super(runtimeClassInfoImpl, propertySeed);
        Accessor accessor = ((RuntimeClassInfoImpl.RuntimePropertySeed) propertySeed).getAccessor();
        if (getAdapter() != null && !isCollection()) {
            accessor = accessor.adapt(getAdapter());
        }
        this.acc = accessor;
    }

    @Override // com.sun.xml.bind.v2.model.runtime.RuntimePropertyInfo
    public Accessor getAccessor() {
        return this.acc;
    }

    @Override // com.sun.xml.bind.v2.model.impl.ElementPropertyInfoImpl, com.sun.xml.bind.v2.model.core.PropertyInfo
    public Collection<? extends TypeInfo<Type, Class>> ref() {
        return super.ref();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sun.xml.bind.v2.model.impl.ElementPropertyInfoImpl
    public RuntimeTypeRefImpl createTypeRef(QName qName, Type type, boolean z, String str) {
        return new RuntimeTypeRefImpl(this, qName, type, z, str);
    }

    @Override // com.sun.xml.bind.v2.model.impl.ElementPropertyInfoImpl, com.sun.xml.bind.v2.model.core.ElementPropertyInfo
    public List<? extends TypeRef<Type, Class>> getTypes() {
        return super.getTypes();
    }
}
