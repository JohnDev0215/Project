package com.google.protobuf;

import p.j13;
import p.k72;
import p.p01;
import p.rl0;
import p.sl0;
import p.t02;
import p.u4;
import p.wj1;

/* JADX INFO: loaded from: classes.dex */
public final class l implements k72 {
    public final wj1 a;
    public final n b;
    public final sl0 c;

    public l(n nVar, sl0 sl0Var, wj1 wj1Var) {
        this.b = nVar;
        sl0Var.getClass();
        this.c = sl0Var;
        this.a = wj1Var;
    }

    @Override // p.k72
    public final void a(Object obj, Object obj2) {
        m.B(this.b, obj, obj2);
    }

    @Override // p.k72
    public final void b(Object obj, byte[] bArr, int i, int i2, j13 j13Var) {
        i iVar = (i) obj;
        if (iVar.unknownFields == o.f) {
            iVar.unknownFields = new o();
        }
        u4.z(obj);
        throw null;
    }

    @Override // p.k72
    public final void c(Object obj) {
        this.b.b(obj);
        this.c.getClass();
        u4.z(obj);
        throw null;
    }

    @Override // p.k72
    public final boolean d(Object obj) {
        this.c.getClass();
        u4.z(obj);
        throw null;
    }

    @Override // p.k72
    public final void e(Object obj, p01 p01Var) {
        this.c.getClass();
        u4.z(obj);
        throw null;
    }

    @Override // p.k72
    public final boolean f(Object obj, Object obj2) {
        n nVar = this.b;
        ((p) nVar).getClass();
        o oVar = ((i) obj).unknownFields;
        ((p) nVar).getClass();
        return oVar.equals(((i) obj2).unknownFields);
    }

    @Override // p.k72
    public final void g(Object obj, t02 t02Var, rl0 rl0Var) {
        this.b.a(obj);
        this.c.getClass();
        u4.z(obj);
        throw null;
    }

    @Override // p.k72
    public final int h(Object obj) {
        ((p) this.b).getClass();
        o oVar = ((i) obj).unknownFields;
        int i = oVar.d;
        if (i != -1) {
            return i;
        }
        int iV = 0;
        for (int i2 = 0; i2 < oVar.a; i2++) {
            int i3 = oVar.b[i2] >>> 3;
            iV += h.v(3, (ByteString) oVar.c[i2]) + h.M(2, i3) + (h.L(1) * 2);
        }
        oVar.d = iV;
        return iV;
    }

    @Override // p.k72
    public final Object i() {
        wj1 wj1Var = this.a;
        return wj1Var instanceof i ? ((i) wj1Var).newMutableInstance() : wj1Var.newBuilderForType().buildPartial();
    }

    @Override // p.k72
    public final int j(Object obj) {
        ((p) this.b).getClass();
        return ((i) obj).unknownFields.hashCode();
    }
}
