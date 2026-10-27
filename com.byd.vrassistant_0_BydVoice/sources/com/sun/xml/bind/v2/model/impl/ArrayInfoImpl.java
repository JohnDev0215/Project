package com.sun.xml.bind.v2.model.impl;

import com.sun.xml.bind.v2.model.annotation.Locatable;
import com.sun.xml.bind.v2.model.core.ArrayInfo;
import com.sun.xml.bind.v2.model.core.NonElement;
import com.sun.xml.bind.v2.model.util.ArrayInfoUtil;
import com.sun.xml.bind.v2.runtime.IllegalAnnotationException;
import com.sun.xml.bind.v2.runtime.Location;
import javax.xml.namespace.QName;

/* JADX INFO: loaded from: classes3.dex */
public class ArrayInfoImpl<TypeT, ClassDeclT, FieldT, MethodT> extends TypeInfoImpl<TypeT, ClassDeclT, FieldT, MethodT> implements ArrayInfo<TypeT, ClassDeclT>, Location {
    private final TypeT arrayType;
    private final NonElement<TypeT, ClassDeclT> itemType;
    private final QName typeName;

    @Override // com.sun.xml.bind.v2.model.core.TypeInfo
    public final boolean canBeReferencedByIDREF() {
        return false;
    }

    @Override // com.sun.xml.bind.v2.model.annotation.Locatable
    public Location getLocation() {
        return this;
    }

    @Override // com.sun.xml.bind.v2.model.core.NonElement
    public boolean isSimpleType() {
        return false;
    }

    @Override // com.sun.xml.bind.v2.model.impl.TypeInfoImpl, com.sun.xml.bind.v2.model.annotation.Locatable
    public /* bridge */ /* synthetic */ Locatable getUpstream() {
        return super.getUpstream();
    }

    public ArrayInfoImpl(ModelBuilder<TypeT, ClassDeclT, FieldT, MethodT> modelBuilder, Locatable locatable, TypeT typet) {
        super(modelBuilder, locatable);
        this.arrayType = typet;
        TypeT componentType = nav().getComponentType(typet);
        NonElement<TypeT, ClassDeclT> typeInfo = modelBuilder.getTypeInfo(componentType, this);
        this.itemType = typeInfo;
        QName typeName = typeInfo.getTypeName();
        if (typeName == null) {
            modelBuilder.reportError(new IllegalAnnotationException(Messages.ANONYMOUS_ARRAY_ITEM.format(nav().getTypeName(componentType)), this));
            typeName = new QName("#dummy");
        }
        this.typeName = ArrayInfoUtil.calcArrayTypeName(typeName);
    }

    @Override // com.sun.xml.bind.v2.model.core.ArrayInfo
    public NonElement<TypeT, ClassDeclT> getItemType() {
        return this.itemType;
    }

    @Override // com.sun.xml.bind.v2.model.core.NonElement
    public QName getTypeName() {
        return this.typeName;
    }

    @Override // com.sun.xml.bind.v2.model.core.TypeInfo
    public TypeT getType() {
        return this.arrayType;
    }

    @Override // com.sun.xml.bind.v2.runtime.Location
    public String toString() {
        return nav().getTypeName(this.arrayType);
    }
}
