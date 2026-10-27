package com.sun.xml.bind.v2.model.impl;

import com.sun.xml.bind.v2.model.core.EnumConstant;
import com.sun.xml.bind.v2.model.core.EnumLeafInfo;

/* JADX INFO: loaded from: classes3.dex */
class EnumConstantImpl<T, C, F, M> implements EnumConstant<T, C> {
    protected final String lexical;
    protected final String name;
    protected final EnumConstantImpl<T, C, F, M> next;
    protected final EnumLeafInfoImpl<T, C, F, M> owner;

    public EnumConstantImpl(EnumLeafInfoImpl<T, C, F, M> enumLeafInfoImpl, String str, String str2, EnumConstantImpl<T, C, F, M> enumConstantImpl) {
        this.lexical = str2;
        this.owner = enumLeafInfoImpl;
        this.name = str;
        this.next = enumConstantImpl;
    }

    @Override // com.sun.xml.bind.v2.model.core.EnumConstant
    public EnumLeafInfo<T, C> getEnclosingClass() {
        return this.owner;
    }

    @Override // com.sun.xml.bind.v2.model.core.EnumConstant
    public final String getLexicalValue() {
        return this.lexical;
    }

    @Override // com.sun.xml.bind.v2.model.core.EnumConstant
    public final String getName() {
        return this.name;
    }
}
