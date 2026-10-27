package com.sun.xml.bind.v2.model.impl;

import com.sun.xml.bind.v2.model.core.NonElement;
import com.sun.xml.bind.v2.model.core.PropertyInfo;
import com.sun.xml.bind.v2.model.core.TypeRef;
import javax.xml.namespace.QName;

/* JADX INFO: loaded from: classes3.dex */
class TypeRefImpl<TypeT, ClassDeclT> implements TypeRef<TypeT, ClassDeclT> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private String defaultValue;
    private final QName elementName;
    private final boolean isNillable;
    protected final ElementPropertyInfoImpl<TypeT, ClassDeclT, ?, ?> owner;
    private NonElement<TypeT, ClassDeclT> ref;
    private final TypeT type;

    public TypeRefImpl(ElementPropertyInfoImpl<TypeT, ClassDeclT, ?, ?> elementPropertyInfoImpl, QName qName, TypeT typet, boolean z, String str) {
        this.owner = elementPropertyInfoImpl;
        this.elementName = qName;
        this.type = typet;
        this.isNillable = z;
        this.defaultValue = str;
    }

    public NonElement<TypeT, ClassDeclT> getTarget() {
        if (this.ref == null) {
            calcRef();
        }
        return this.ref;
    }

    @Override // com.sun.xml.bind.v2.model.core.TypeRef
    public QName getTagName() {
        return this.elementName;
    }

    @Override // com.sun.xml.bind.v2.model.core.TypeRef
    public boolean isNillable() {
        return this.isNillable;
    }

    @Override // com.sun.xml.bind.v2.model.core.TypeRef
    public String getDefaultValue() {
        return this.defaultValue;
    }

    protected void link() {
        calcRef();
    }

    private void calcRef() {
        this.ref = this.owner.parent.builder.getTypeInfo(this.type, this.owner);
    }

    public PropertyInfo<TypeT, ClassDeclT> getSource() {
        return this.owner;
    }
}
