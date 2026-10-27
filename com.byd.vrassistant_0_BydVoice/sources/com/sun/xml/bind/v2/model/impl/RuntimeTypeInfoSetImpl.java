package com.sun.xml.bind.v2.model.impl;

import com.sun.xml.bind.v2.model.annotation.AnnotationReader;
import com.sun.xml.bind.v2.model.core.ArrayInfo;
import com.sun.xml.bind.v2.model.core.BuiltinLeafInfo;
import com.sun.xml.bind.v2.model.core.ClassInfo;
import com.sun.xml.bind.v2.model.core.ElementInfo;
import com.sun.xml.bind.v2.model.core.EnumLeafInfo;
import com.sun.xml.bind.v2.model.core.NonElement;
import com.sun.xml.bind.v2.model.runtime.RuntimeNonElement;
import com.sun.xml.bind.v2.model.runtime.RuntimeTypeInfoSet;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Map;
import javax.xml.namespace.QName;

/* JADX INFO: loaded from: classes3.dex */
final class RuntimeTypeInfoSetImpl extends TypeInfoSetImpl<Type, Class, Field, Method> implements RuntimeTypeInfoSet {
    public RuntimeTypeInfoSetImpl(AnnotationReader<Type, Class, Field, Method> annotationReader) {
        super(Utils.REFLECTION_NAVIGATOR, annotationReader, RuntimeBuiltinLeafInfoImpl.LEAVES);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sun.xml.bind.v2.model.impl.TypeInfoSetImpl
    public NonElement<Type, Class> createAnyType() {
        return RuntimeAnyTypeImpl.theInstance;
    }

    @Override // com.sun.xml.bind.v2.model.impl.TypeInfoSetImpl, com.sun.xml.bind.v2.model.core.TypeInfoSet
    public RuntimeNonElement getTypeInfo(Type type) {
        return (RuntimeNonElement) super.getTypeInfo(type);
    }

    @Override // com.sun.xml.bind.v2.model.impl.TypeInfoSetImpl, com.sun.xml.bind.v2.model.core.TypeInfoSet
    public NonElement<Type, Class> getAnyTypeInfo() {
        return (RuntimeNonElement) super.getAnyTypeInfo();
    }

    @Override // com.sun.xml.bind.v2.model.impl.TypeInfoSetImpl, com.sun.xml.bind.v2.model.core.TypeInfoSet
    public RuntimeNonElement getClassInfo(Class cls) {
        return (RuntimeNonElement) super.getClassInfo(cls);
    }

    @Override // com.sun.xml.bind.v2.model.impl.TypeInfoSetImpl, com.sun.xml.bind.v2.model.core.TypeInfoSet, com.sun.xml.bind.v2.model.runtime.RuntimeTypeInfoSet
    public Map<Class, ? extends ClassInfo<Type, Class>> beans() {
        return super.beans();
    }

    @Override // com.sun.xml.bind.v2.model.impl.TypeInfoSetImpl, com.sun.xml.bind.v2.model.core.TypeInfoSet, com.sun.xml.bind.v2.model.runtime.RuntimeTypeInfoSet
    public Map<Type, ? extends BuiltinLeafInfo<Type, Class>> builtins() {
        return super.builtins();
    }

    @Override // com.sun.xml.bind.v2.model.impl.TypeInfoSetImpl, com.sun.xml.bind.v2.model.core.TypeInfoSet, com.sun.xml.bind.v2.model.runtime.RuntimeTypeInfoSet
    public Map<Class, ? extends EnumLeafInfo<Type, Class>> enums() {
        return super.enums();
    }

    @Override // com.sun.xml.bind.v2.model.impl.TypeInfoSetImpl, com.sun.xml.bind.v2.model.core.TypeInfoSet, com.sun.xml.bind.v2.model.runtime.RuntimeTypeInfoSet
    public Map<? extends Type, ? extends ArrayInfo<Type, Class>> arrays() {
        return super.arrays();
    }

    @Override // com.sun.xml.bind.v2.model.runtime.RuntimeTypeInfoSet
    public RuntimeElementInfoImpl getElementInfo(Class cls, QName qName) {
        return (RuntimeElementInfoImpl) super.getElementInfo(cls, qName);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.sun.xml.bind.v2.model.impl.TypeInfoSetImpl, com.sun.xml.bind.v2.model.core.TypeInfoSet
    public Map<QName, RuntimeElementInfoImpl> getElementMappings(Class cls) {
        return super.getElementMappings(cls);
    }

    @Override // com.sun.xml.bind.v2.model.impl.TypeInfoSetImpl, com.sun.xml.bind.v2.model.core.TypeInfoSet, com.sun.xml.bind.v2.model.runtime.RuntimeTypeInfoSet
    public Iterable<? extends ElementInfo<Type, Class>> getAllElements() {
        return super.getAllElements();
    }
}
