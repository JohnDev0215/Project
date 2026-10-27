package com.fasterxml.jackson.databind.type;

import com.fasterxml.jackson.databind.JavaType;
import java.io.Serializable;
import p.u4;
import p.vs;

/* JADX INFO: loaded from: classes.dex */
public class TypeBindings implements Serializable {
    public static final String[] c;
    public static final JavaType[] e;
    public static final TypeBindings k;
    private static final long serialVersionUID = 1;
    private final int _hashCode;
    private final String[] _names;
    private final JavaType[] _types;
    private final String[] _unboundVariables;

    static {
        String[] strArr = new String[0];
        c = strArr;
        JavaType[] javaTypeArr = new JavaType[0];
        e = javaTypeArr;
        k = new TypeBindings(strArr, javaTypeArr, null);
    }

    public TypeBindings(String[] strArr, JavaType[] javaTypeArr, String[] strArr2) {
        strArr = strArr == null ? c : strArr;
        this._names = strArr;
        javaTypeArr = javaTypeArr == null ? e : javaTypeArr;
        this._types = javaTypeArr;
        if (strArr.length != javaTypeArr.length) {
            StringBuilder sb = new StringBuilder("Mismatching names (");
            sb.append(strArr.length);
            sb.append("), types (");
            throw new IllegalArgumentException(u4.l(sb, javaTypeArr.length, ")"));
        }
        int length = javaTypeArr.length;
        int iHashCode = 1;
        for (int i = 0; i < length; i++) {
            iHashCode += this._types[i].hashCode();
        }
        this._unboundVariables = strArr2;
        this._hashCode = iHashCode;
    }

    public final JavaType a(int i) {
        if (i < 0) {
            return null;
        }
        JavaType[] javaTypeArr = this._types;
        if (i >= javaTypeArr.length) {
            return null;
        }
        return javaTypeArr[i];
    }

    public final int b() {
        return this._types.length;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!vs.e(getClass(), obj)) {
            return false;
        }
        int length = this._types.length;
        JavaType[] javaTypeArr = ((TypeBindings) obj)._types;
        if (length != javaTypeArr.length) {
            return false;
        }
        for (int i = 0; i < length; i++) {
            if (!javaTypeArr[i].equals(this._types[i])) {
                return false;
            }
        }
        return true;
    }

    public final int hashCode() {
        return this._hashCode;
    }

    public Object readResolve() {
        String[] strArr = this._names;
        return (strArr == null || strArr.length == 0) ? k : this;
    }

    public final String toString() {
        if (this._types.length == 0) {
            return "<>";
        }
        StringBuilder sb = new StringBuilder("<");
        int length = this._types.length;
        for (int i = 0; i < length; i++) {
            if (i > 0) {
                sb.append(',');
            }
            JavaType javaType = this._types[i];
            StringBuilder sb2 = new StringBuilder(40);
            javaType.e(sb2);
            sb.append(sb2.toString());
        }
        sb.append('>');
        return sb.toString();
    }
}
