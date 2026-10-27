package com.fasterxml.jackson.databind.type;

import com.fasterxml.jackson.databind.util.LRUMap;
import java.io.Serializable;
import p.m71;
import p.oo2;
import p.yd1;

/* JADX INFO: loaded from: classes.dex */
public class TypeFactory implements Serializable {
    public static final TypeFactory c = new TypeFactory();
    public static final SimpleType e;
    private static final long serialVersionUID = 1;
    protected final yd1 _typeCache = new LRUMap(16, 200);
    protected final TypeParser _parser = new TypeParser(this);
    protected final oo2[] _modifiers = null;
    protected final ClassLoader _classLoader = null;

    static {
        TypeBindings typeBindings = TypeBindings.k;
        Class cls = Boolean.TYPE;
        Class cls2 = Integer.TYPE;
        Class cls3 = Long.TYPE;
        new SimpleType(cls);
        new SimpleType(cls2);
        new SimpleType(cls3);
        new SimpleType(String.class);
        e = new SimpleType(Object.class);
        new SimpleType(Comparable.class);
        new SimpleType(Enum.class);
        new SimpleType(Class.class);
        new SimpleType(m71.class);
    }

    private TypeFactory() {
    }

    public static SimpleType a() {
        c.getClass();
        return e;
    }
}
