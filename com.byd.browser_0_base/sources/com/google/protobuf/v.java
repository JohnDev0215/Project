package com.google.protobuf;

import com.google.protobuf.s;
import com.google.protobuf.z;
import java.util.Map;

/* JADX INFO: compiled from: ExtensionSchemaLite.java */
/* JADX INFO: loaded from: classes.dex */
public final class v extends t<z.d> {
    @Override // com.google.protobuf.t
    public final int a(Map.Entry<?, ?> entry) {
        ((z.d) entry.getKey()).getClass();
        return 0;
    }

    @Override // com.google.protobuf.t
    public final Object b(s sVar, r0 r0Var, int i) {
        sVar.getClass();
        return sVar.a.get(new s.a(i, r0Var));
    }

    @Override // com.google.protobuf.t
    public final x<z.d> c(Object obj) {
        return ((z.c) obj).extensions;
    }

    @Override // com.google.protobuf.t
    public final x<z.d> d(Object obj) {
        z.c cVar = (z.c) obj;
        x<z.d> xVar = cVar.extensions;
        if (xVar.b) {
            cVar.extensions = xVar.clone();
        }
        return cVar.extensions;
    }

    @Override // com.google.protobuf.t
    public final boolean e(r0 r0Var) {
        return r0Var instanceof z.c;
    }

    @Override // com.google.protobuf.t
    public final void f(Object obj) {
        ((z.c) obj).extensions.t();
    }

    @Override // com.google.protobuf.t
    public final Object g(Object obj) {
        throw null;
    }

    @Override // com.google.protobuf.t
    public final void h(z0 z0Var, Object obj, s sVar, x<z.d> xVar) {
        throw null;
    }

    @Override // com.google.protobuf.t
    public final void i(Object obj) {
        throw null;
    }

    @Override // com.google.protobuf.t
    public final void j(i iVar, Map.Entry entry) {
        ((z.d) entry.getKey()).getClass();
        throw null;
    }
}
