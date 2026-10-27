package com.sun.xml.bind.v2.model.impl;

import com.sun.xml.bind.v2.model.annotation.Locatable;
import com.sun.xml.bind.v2.runtime.Location;
import java.beans.Introspector;
import java.lang.annotation.Annotation;

/* JADX INFO: loaded from: classes3.dex */
class GetterSetterPropertySeed<TypeT, ClassDeclT, FieldT, MethodT> implements PropertySeed<TypeT, ClassDeclT, FieldT, MethodT> {
    protected final MethodT getter;
    private ClassInfoImpl<TypeT, ClassDeclT, FieldT, MethodT> parent;
    protected final MethodT setter;

    GetterSetterPropertySeed(ClassInfoImpl<TypeT, ClassDeclT, FieldT, MethodT> classInfoImpl, MethodT methodt, MethodT methodt2) {
        this.parent = classInfoImpl;
        this.getter = methodt;
        this.setter = methodt2;
        if (methodt == null && methodt2 == null) {
            throw new IllegalArgumentException();
        }
    }

    @Override // com.sun.xml.bind.v2.model.impl.PropertySeed
    public TypeT getRawType() {
        if (this.getter != null) {
            return this.parent.nav().getReturnType(this.getter);
        }
        return this.parent.nav().getMethodParameters(this.setter)[0];
    }

    @Override // com.sun.xml.bind.v2.model.annotation.AnnotationSource
    public <A extends Annotation> A readAnnotation(Class<A> cls) {
        return (A) this.parent.reader().getMethodAnnotation(cls, this.getter, this.setter, this);
    }

    @Override // com.sun.xml.bind.v2.model.annotation.AnnotationSource
    public boolean hasAnnotation(Class<? extends Annotation> cls) {
        return this.parent.reader().hasMethodAnnotation(cls, getName(), this.getter, this.setter, this);
    }

    @Override // com.sun.xml.bind.v2.model.impl.PropertySeed
    public String getName() {
        MethodT methodt = this.getter;
        if (methodt != null) {
            return getName(methodt);
        }
        return getName(this.setter);
    }

    private String getName(MethodT methodt) {
        String methodName = this.parent.nav().getMethodName(methodt);
        String lowerCase = methodName.toLowerCase();
        if (lowerCase.startsWith("get") || lowerCase.startsWith("set")) {
            return camelize(methodName.substring(3));
        }
        return lowerCase.startsWith("is") ? camelize(methodName.substring(2)) : methodName;
    }

    private static String camelize(String str) {
        return Introspector.decapitalize(str);
    }

    @Override // com.sun.xml.bind.v2.model.annotation.Locatable
    public Locatable getUpstream() {
        return this.parent;
    }

    @Override // com.sun.xml.bind.v2.model.annotation.Locatable
    public Location getLocation() {
        if (this.getter != null) {
            return this.parent.nav().getMethodLocation(this.getter);
        }
        return this.parent.nav().getMethodLocation(this.setter);
    }
}
