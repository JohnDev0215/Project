package com.google.protobuf;

import com.google.protobuf.k0;
import com.google.protobuf.l0;
import defpackage.nu;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/* JADX INFO: compiled from: MapFieldSchemaFull.java */
/* JADX INFO: loaded from: classes.dex */
public class o0 implements n0 {
    /* JADX WARN: Type inference incomplete: some casts might be missing */
    @Override // com.google.protobuf.n0
    public final Object a(Object obj, Object obj2) {
        l0 l0Var = (l0) obj;
        l0 l0Var2 = (l0) obj2;
        if (!l0Var.a) {
            new l0(l0Var.e, m0.h(l0Var.c()));
        }
        if (l0Var.b != 1) {
            if (l0Var.b == 2) {
                l0Var.c = l0Var.a(l0Var.d);
            }
            l0Var.d = null;
            l0Var.b = 1;
        }
        l0Var.c.putAll(m0.h(l0Var2.c()));
        return l0Var;
    }

    @Override // com.google.protobuf.n0
    public final Object b(Object obj) {
        ((l0) obj).a = false;
        return obj;
    }

    @Override // com.google.protobuf.n0
    public final k0.a<?, ?> c(Object obj) {
        ((j0) obj).getClass();
        return null;
    }

    @Override // com.google.protobuf.n0
    public final Map<?, ?> d(Object obj) {
        l0 l0Var = (l0) obj;
        if (l0Var.b != 1) {
            if (l0Var.b == 2) {
                l0Var.c = l0Var.a(l0Var.d);
            }
            l0Var.d = null;
            l0Var.b = 1;
        }
        return l0Var.c;
    }

    @Override // com.google.protobuf.n0
    public final int e(Object obj, int i, Object obj2) {
        if (obj == null) {
            return 0;
        }
        Map mapC = ((l0) obj).c();
        j0 j0Var = (j0) obj2;
        if (mapC.isEmpty()) {
            return 0;
        }
        Iterator it = mapC.entrySet().iterator();
        if (!it.hasNext()) {
            return 0;
        }
        Map.Entry entry = (Map.Entry) it.next();
        nu.i0(i);
        j0Var.getClass();
        k0.a(null, entry.getKey(), entry.getValue());
        throw null;
    }

    @Override // com.google.protobuf.n0
    public final Object f(Object obj) {
        return new l0(new l0.b((j0) obj), new LinkedHashMap());
    }

    @Override // com.google.protobuf.n0
    public final boolean g(Object obj) {
        return !((l0) obj).a;
    }

    @Override // com.google.protobuf.n0
    public final Map<?, ?> h(Object obj) {
        return ((l0) obj).c();
    }
}
