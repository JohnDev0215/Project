package com.sun.xml.bind.v2.model.impl;

import com.sun.xml.bind.v2.model.annotation.Locatable;
import com.sun.xml.bind.v2.model.core.NonElement;
import com.sun.xml.bind.v2.model.nav.Navigator;
import com.sun.xml.bind.v2.runtime.Location;
import javax.xml.namespace.QName;

/* JADX INFO: loaded from: classes3.dex */
class AnyTypeImpl<T, C> implements NonElement<T, C> {
    private final Navigator<T, C, ?, ?> nav;
    private final T type;

    @Override // com.sun.xml.bind.v2.model.core.TypeInfo
    public final boolean canBeReferencedByIDREF() {
        return true;
    }

    @Override // com.sun.xml.bind.v2.model.annotation.Locatable
    public Locatable getUpstream() {
        return null;
    }

    @Override // com.sun.xml.bind.v2.model.core.NonElement
    public boolean isSimpleType() {
        return false;
    }

    public AnyTypeImpl(Navigator<T, C, ?, ?> navigator) {
        this.type = navigator.ref(Object.class);
        this.nav = navigator;
    }

    @Override // com.sun.xml.bind.v2.model.core.NonElement
    public QName getTypeName() {
        return ANYTYPE_NAME;
    }

    @Override // com.sun.xml.bind.v2.model.core.TypeInfo
    public T getType() {
        return this.type;
    }

    @Override // com.sun.xml.bind.v2.model.annotation.Locatable
    public Location getLocation() {
        Navigator<T, C, ?, ?> navigator = this.nav;
        return navigator.getClassLocation(navigator.asDecl(Object.class));
    }
}
