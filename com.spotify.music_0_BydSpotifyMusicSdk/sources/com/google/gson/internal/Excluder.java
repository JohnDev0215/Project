package com.google.gson.internal;

import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import p.er2;
import p.lo2;
import p.md2;
import p.qo2;
import p.t71;
import p.u4;
import p.z71;

/* JADX INFO: loaded from: classes.dex */
public final class Excluder implements lo2, Cloneable {
    public static final Excluder n = new Excluder();
    public final double c = -1.0d;
    public final int e = 136;
    public final boolean k = true;
    public final List l = Collections.emptyList();
    public final List m = Collections.emptyList();

    public static boolean f(Class cls) {
        return !Enum.class.isAssignableFrom(cls) && (cls.getModifiers() & 8) == 0 && (cls.isAnonymousClass() || cls.isLocalClass());
    }

    @Override // p.lo2
    public final com.google.gson.b a(final com.google.gson.a aVar, final qo2 qo2Var) {
        final boolean z;
        final boolean z2;
        boolean zD = d(qo2Var.a);
        if (zD) {
            z = true;
        } else {
            e(true);
            z = false;
        }
        if (zD) {
            z2 = true;
        } else {
            e(false);
            z2 = false;
        }
        if (z || z2) {
            return new com.google.gson.b() { // from class: com.google.gson.internal.Excluder.1
                public com.google.gson.b a;

                @Override // com.google.gson.b
                public final Object b(t71 t71Var) throws IOException {
                    if (z2) {
                        t71Var.D();
                        return null;
                    }
                    com.google.gson.b bVarE = this.a;
                    if (bVarE == null) {
                        bVarE = aVar.e(Excluder.this, qo2Var);
                        this.a = bVarE;
                    }
                    return bVarE.b(t71Var);
                }

                @Override // com.google.gson.b
                public final void c(z71 z71Var, Object obj) throws IOException {
                    if (z) {
                        z71Var.E0();
                        return;
                    }
                    com.google.gson.b bVarE = this.a;
                    if (bVarE == null) {
                        bVarE = aVar.e(Excluder.this, qo2Var);
                        this.a = bVarE;
                    }
                    bVarE.c(z71Var, obj);
                }
            };
        }
        return null;
    }

    public final Object clone() {
        try {
            return (Excluder) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    public final boolean d(Class cls) {
        if (this.c != -1.0d) {
            md2 md2Var = (md2) cls.getAnnotation(md2.class);
            er2 er2Var = (er2) cls.getAnnotation(er2.class);
            double d = this.c;
            if ((md2Var != null && md2Var.value() > d) || (er2Var != null && er2Var.value() <= d)) {
                return true;
            }
        }
        return (!this.k && cls.isMemberClass() && (cls.getModifiers() & 8) == 0) || f(cls);
    }

    public final void e(boolean z) {
        Iterator it = (z ? this.l : this.m).iterator();
        if (it.hasNext()) {
            u4.z(it.next());
            throw null;
        }
    }
}
