package com.sun.xml.bind.v2.model.impl;

import com.sun.xml.bind.v2.model.annotation.Locatable;
import com.sun.xml.bind.v2.model.core.LeafInfo;
import com.sun.xml.bind.v2.runtime.Location;
import javax.xml.namespace.QName;

/* JADX INFO: loaded from: classes3.dex */
abstract class LeafInfoImpl<TypeT, ClassDeclT> implements LeafInfo<TypeT, ClassDeclT>, Location {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final TypeT type;
    private final QName typeName;

    @Override // com.sun.xml.bind.v2.model.core.TypeInfo
    public final boolean canBeReferencedByIDREF() {
        return false;
    }

    public Location getLocation() {
        return this;
    }

    public Locatable getUpstream() {
        return null;
    }

    public boolean isSimpleType() {
        return true;
    }

    protected LeafInfoImpl(TypeT typet, QName qName) {
        this.type = typet;
        this.typeName = qName;
    }

    public TypeT getType() {
        return this.type;
    }

    public QName getTypeName() {
        return this.typeName;
    }

    public String toString() {
        return this.type.toString();
    }
}
