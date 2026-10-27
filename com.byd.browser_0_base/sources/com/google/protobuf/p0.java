package com.google.protobuf;

import com.google.protobuf.k0;
import defpackage.nu;
import java.util.Map;

/* JADX INFO: compiled from: MapFieldSchemaLite.java */
/* JADX INFO: loaded from: classes.dex */
public final class p0 implements n0 {
    @Override // com.google.protobuf.n0
    public final Object a(Object obj, Object obj2) {
        m0 m0Var = (m0) obj;
        m0 m0Var2 = (m0) obj2;
        if (!m0Var2.isEmpty()) {
            if (!m0Var.e) {
                m0Var = m0Var.isEmpty() ? new m0() : new m0(m0Var);
            }
            m0Var.k();
            if (!m0Var2.isEmpty()) {
                m0Var.putAll(m0Var2);
            }
        }
        return m0Var;
    }

    @Override // com.google.protobuf.n0
    public final Object b(Object obj) {
        ((m0) obj).e = false;
        return obj;
    }

    @Override // com.google.protobuf.n0
    public final k0.a<?, ?> c(Object obj) {
        return ((k0) obj).a;
    }

    @Override // com.google.protobuf.n0
    public final Map<?, ?> d(Object obj) {
        return (m0) obj;
    }

    @Override // com.google.protobuf.n0
    public final int e(Object obj, int i, Object obj2) {
        m0 m0Var = (m0) obj;
        k0 k0Var = (k0) obj2;
        int iK0 = 0;
        if (!m0Var.isEmpty()) {
            for (Map.Entry entry : m0Var.entrySet()) {
                Object key = entry.getKey();
                Object value = entry.getValue();
                k0Var.getClass();
                int iI0 = nu.i0(i);
                int iA = k0.a(k0Var.a, key, value);
                iK0 += nu.k0(iA) + iA + iI0;
            }
        }
        return iK0;
    }

    @Override // com.google.protobuf.n0
    public final Object f(Object obj) {
        m0 m0Var = m0.f;
        return m0Var.isEmpty() ? new m0() : new m0(m0Var);
    }

    @Override // com.google.protobuf.n0
    public final boolean g(Object obj) {
        return !((m0) obj).e;
    }

    @Override // com.google.protobuf.n0
    public final Map<?, ?> h(Object obj) {
        return (m0) obj;
    }
}
