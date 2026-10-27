package com.sun.xml.bind.v2.model.impl;

import com.sun.xml.bind.v2.model.annotation.Locatable;
import com.sun.xml.bind.v2.runtime.Location;
import java.lang.annotation.Annotation;

/* JADX INFO: loaded from: classes3.dex */
class FieldPropertySeed<TypeT, ClassDeclT, FieldT, MethodT> implements PropertySeed<TypeT, ClassDeclT, FieldT, MethodT> {
    protected final FieldT field;
    private ClassInfoImpl<TypeT, ClassDeclT, FieldT, MethodT> parent;

    FieldPropertySeed(ClassInfoImpl<TypeT, ClassDeclT, FieldT, MethodT> classInfoImpl, FieldT fieldt) {
        this.parent = classInfoImpl;
        this.field = fieldt;
    }

    @Override // com.sun.xml.bind.v2.model.annotation.AnnotationSource
    public <A extends Annotation> A readAnnotation(Class<A> cls) {
        return (A) this.parent.reader().getFieldAnnotation(cls, this.field, this);
    }

    @Override // com.sun.xml.bind.v2.model.annotation.AnnotationSource
    public boolean hasAnnotation(Class<? extends Annotation> cls) {
        return this.parent.reader().hasFieldAnnotation(cls, this.field);
    }

    @Override // com.sun.xml.bind.v2.model.impl.PropertySeed
    public String getName() {
        return this.parent.nav().getFieldName(this.field);
    }

    @Override // com.sun.xml.bind.v2.model.impl.PropertySeed
    public TypeT getRawType() {
        return this.parent.nav().getFieldType(this.field);
    }

    @Override // com.sun.xml.bind.v2.model.annotation.Locatable
    public Locatable getUpstream() {
        return this.parent;
    }

    @Override // com.sun.xml.bind.v2.model.annotation.Locatable
    public Location getLocation() {
        return this.parent.nav().getFieldLocation(this.field);
    }
}
