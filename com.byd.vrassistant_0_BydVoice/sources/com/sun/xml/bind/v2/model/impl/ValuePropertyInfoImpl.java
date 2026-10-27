package com.sun.xml.bind.v2.model.impl;

import com.sun.xml.bind.v2.model.core.PropertyKind;
import com.sun.xml.bind.v2.model.core.ValuePropertyInfo;

/* JADX INFO: loaded from: classes3.dex */
class ValuePropertyInfoImpl<TypeT, ClassDeclT, FieldT, MethodT> extends SingleTypePropertyInfoImpl<TypeT, ClassDeclT, FieldT, MethodT> implements ValuePropertyInfo<TypeT, ClassDeclT> {
    ValuePropertyInfoImpl(ClassInfoImpl<TypeT, ClassDeclT, FieldT, MethodT> classInfoImpl, PropertySeed<TypeT, ClassDeclT, FieldT, MethodT> propertySeed) {
        super(classInfoImpl, propertySeed);
    }

    @Override // com.sun.xml.bind.v2.model.core.PropertyInfo
    public PropertyKind kind() {
        return PropertyKind.VALUE;
    }
}
