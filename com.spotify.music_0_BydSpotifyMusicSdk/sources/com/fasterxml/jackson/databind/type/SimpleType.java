package com.fasterxml.jackson.databind.type;

import com.fasterxml.jackson.databind.JavaType;

/* JADX INFO: loaded from: classes.dex */
public class SimpleType extends TypeBase {
    private static final long serialVersionUID = 1;

    public SimpleType(Class cls, TypeBindings typeBindings, JavaType javaType, JavaType[] javaTypeArr) {
        super(cls, typeBindings, javaType, javaTypeArr, 0, null, null, false);
    }

    @Override // com.fasterxml.jackson.databind.JavaType
    public StringBuilder e(StringBuilder sb) {
        TypeBase.j(this._class, sb, false);
        int iB = this._bindings.b();
        if (iB > 0) {
            sb.append('<');
            for (int i = 0; i < iB; i++) {
                sb = this._bindings.a(i).e(sb);
            }
            sb.append('>');
        }
        sb.append(';');
        return sb;
    }

    @Override // com.fasterxml.jackson.databind.JavaType
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        SimpleType simpleType = (SimpleType) obj;
        if (simpleType._class != this._class) {
            return false;
        }
        return this._bindings.equals(simpleType._bindings);
    }

    @Override // com.fasterxml.jackson.databind.JavaType
    public final boolean h() {
        return false;
    }

    @Override // com.fasterxml.jackson.databind.type.TypeBase
    public String l() {
        StringBuilder sb = new StringBuilder();
        sb.append(this._class.getName());
        int iB = this._bindings.b();
        if (iB > 0 && k(iB)) {
            sb.append('<');
            for (int i = 0; i < iB; i++) {
                JavaType javaTypeA = this._bindings.a(i);
                if (i > 0) {
                    sb.append(',');
                }
                sb.append(javaTypeA.c());
            }
            sb.append('>');
        }
        return sb.toString();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(40);
        sb.append("[simple type, class ");
        sb.append(l());
        sb.append(']');
        return sb.toString();
    }

    public SimpleType(Class cls) {
        this(cls, TypeBindings.k, null, null);
    }
}
