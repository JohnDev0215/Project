package com.fasterxml.jackson.databind;

import java.io.Serializable;
import java.lang.reflect.Type;
import p.z32;

/* JADX INFO: loaded from: classes.dex */
public abstract class JavaType extends z32 implements Serializable, Type {
    private static final long serialVersionUID = 1;
    protected final boolean _asStatic;
    protected final Class<?> _class;
    protected final int _hash;
    protected final Object _typeHandler;
    protected final Object _valueHandler;

    public JavaType(Class cls, int i, Object obj, Object obj2, boolean z) {
        this._class = cls;
        this._hash = cls.getName().hashCode() + i;
        this._valueHandler = obj;
        this._typeHandler = obj2;
        this._asStatic = z;
    }

    public JavaType d() {
        return null;
    }

    public abstract StringBuilder e(StringBuilder sb);

    public abstract boolean equals(Object obj);

    public final Class f() {
        return this._class;
    }

    @Override // p.z32
    /* JADX INFO: renamed from: g, reason: merged with bridge method [inline-methods] */
    public JavaType a() {
        return null;
    }

    public abstract boolean h();

    public final int hashCode() {
        return this._hash;
    }

    public final boolean i() {
        return this._class.isPrimitive();
    }
}
