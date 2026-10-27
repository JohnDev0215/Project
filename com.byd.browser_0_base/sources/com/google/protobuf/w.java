package com.google.protobuf;

import com.google.protobuf.c0;
import defpackage.gq2;
import defpackage.hm0;
import defpackage.ps1;
import java.lang.reflect.Field;
import java.nio.charset.Charset;

/* JADX INFO: compiled from: FieldInfo.java */
/* JADX INFO: loaded from: classes.dex */
public final class w implements Comparable<w> {
    public final Field e;
    public final hm0 f;
    public final Class<?> g;
    public final int h;
    public final Field i;
    public final int j;
    public final boolean k;
    public final boolean l;
    public final ps1 m;
    public final Field n;
    public final Class<?> o;
    public final Object p;
    public final c0.b q;

    public w(Field field, int i, hm0 hm0Var, Class<?> cls, Field field2, int i2, boolean z, boolean z2, ps1 ps1Var, Class<?> cls2, Object obj, c0.b bVar, Field field3) {
        this.e = field;
        this.f = hm0Var;
        this.g = cls;
        this.h = i;
        this.i = field2;
        this.j = i2;
        this.k = z;
        this.l = z2;
        this.m = ps1Var;
        this.o = cls2;
        this.p = obj;
        this.q = bVar;
        this.n = field3;
    }

    public static void a(int i) {
        if (i <= 0) {
            throw new IllegalArgumentException(gq2.e("fieldNumber must be positive: ", i));
        }
    }

    public static w e(Field field, int i, hm0 hm0Var, boolean z) {
        a(i);
        Charset charset = c0.a;
        if (field == null) {
            throw new NullPointerException("field");
        }
        if (hm0Var == null) {
            throw new NullPointerException("fieldType");
        }
        if (hm0Var == hm0.I || hm0Var == hm0.e0) {
            throw new IllegalStateException("Shouldn't be called for repeated message fields.");
        }
        return new w(field, i, hm0Var, null, null, 0, false, z, null, null, null, null, null);
    }

    public static w f(Field field, int i, Object obj, c0.b bVar) {
        Charset charset = c0.a;
        if (obj == null) {
            throw new NullPointerException("mapDefaultEntry");
        }
        a(i);
        if (field != null) {
            return new w(field, i, hm0.f0, null, null, 0, false, true, null, null, obj, bVar, null);
        }
        throw new NullPointerException("field");
    }

    public static w g(Field field, int i, hm0 hm0Var, Field field2) {
        a(i);
        Charset charset = c0.a;
        if (field == null) {
            throw new NullPointerException("field");
        }
        if (hm0Var == null) {
            throw new NullPointerException("fieldType");
        }
        if (hm0Var == hm0.I || hm0Var == hm0.e0) {
            throw new IllegalStateException("Shouldn't be called for repeated message fields.");
        }
        return new w(field, i, hm0Var, null, null, 0, false, false, null, null, null, null, field2);
    }

    public static w h(Field field, int i, hm0 hm0Var, Class<?> cls) {
        a(i);
        Charset charset = c0.a;
        if (field == null) {
            throw new NullPointerException("field");
        }
        if (hm0Var == null) {
            throw new NullPointerException("fieldType");
        }
        if (cls != null) {
            return new w(field, i, hm0Var, cls, null, 0, false, false, null, null, null, null, null);
        }
        throw new NullPointerException("messageClass");
    }

    @Override // java.lang.Comparable
    public final int compareTo(w wVar) {
        return this.h - wVar.h;
    }
}
