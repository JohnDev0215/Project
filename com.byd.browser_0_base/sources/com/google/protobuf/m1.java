package com.google.protobuf;

import com.google.protobuf.j1;
import defpackage.lk;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: compiled from: UnknownFieldSetSchema.java */
/* JADX INFO: loaded from: classes.dex */
public class m1 extends i1<j1, j1.a> {
    @Override // com.google.protobuf.i1
    public final void a(int i, int i2, Object obj) {
        j1.a aVar = (j1.a) obj;
        int i3 = j1.b.f;
        j1.b.a aVar2 = new j1.b.a();
        j1.b bVar = aVar2.a;
        if (bVar.b == null) {
            bVar.b = new ArrayList();
        }
        aVar2.a.b.add(Integer.valueOf(i2));
        aVar.j(i, aVar2.c());
    }

    @Override // com.google.protobuf.i1
    public final void b(long j, int i, Object obj) {
        j1.a aVar = (j1.a) obj;
        int i2 = j1.b.f;
        j1.b.a aVar2 = new j1.b.a();
        j1.b bVar = aVar2.a;
        if (bVar.c == null) {
            bVar.c = new ArrayList();
        }
        aVar2.a.c.add(Long.valueOf(j));
        aVar.j(i, aVar2.c());
    }

    @Override // com.google.protobuf.i1
    public final void c(j1.a aVar, int i, j1 j1Var) {
        j1.a aVar2 = aVar;
        j1 j1Var2 = j1Var;
        int i2 = j1.b.f;
        j1.b.a aVar3 = new j1.b.a();
        j1.b bVar = aVar3.a;
        if (bVar.e == null) {
            bVar.e = new ArrayList();
        }
        aVar3.a.e.add(j1Var2);
        aVar2.j(i, aVar3.c());
    }

    @Override // com.google.protobuf.i1
    public final void d(j1.a aVar, int i, lk lkVar) {
        int i2 = j1.b.f;
        j1.b.a aVar2 = new j1.b.a();
        aVar2.a(lkVar);
        aVar.j(i, aVar2.c());
    }

    @Override // com.google.protobuf.i1
    public final void e(long j, int i, Object obj) {
        int i2 = j1.b.f;
        j1.b.a aVar = new j1.b.a();
        aVar.b(j);
        ((j1.a) obj).j(i, aVar.c());
    }

    @Override // com.google.protobuf.i1
    public final j1.a f(Object obj) {
        j1 j1Var = ((a0) obj).f;
        j1Var.getClass();
        j1.a aVar = new j1.a();
        aVar.v(j1Var);
        return aVar;
    }

    @Override // com.google.protobuf.i1
    public final j1 g(Object obj) {
        return ((a0) obj).f;
    }

    @Override // com.google.protobuf.i1
    public final int h(j1 j1Var) {
        return j1Var.m();
    }

    @Override // com.google.protobuf.i1
    public final int i(j1 j1Var) {
        return j1Var.g();
    }

    @Override // com.google.protobuf.i1
    public final j1 k(j1 j1Var, j1 j1Var2) {
        j1 j1Var3 = j1Var;
        j1Var3.getClass();
        j1.a aVar = new j1.a();
        aVar.v(j1Var3);
        aVar.v(j1Var2);
        return aVar.build();
    }

    @Override // com.google.protobuf.i1
    public final j1.a m() {
        j1 j1Var = j1.f;
        return new j1.a();
    }

    @Override // com.google.protobuf.i1
    public final void n(Object obj, j1.a aVar) {
        ((a0) obj).f = aVar.build();
    }

    @Override // com.google.protobuf.i1
    public final void o(Object obj, j1 j1Var) {
        ((a0) obj).f = j1Var;
    }

    @Override // com.google.protobuf.i1
    public final boolean p(z0 z0Var) {
        z0Var.G();
        return false;
    }

    @Override // com.google.protobuf.i1
    public final j1 q(j1.a aVar) {
        return aVar.build();
    }

    @Override // com.google.protobuf.i1
    public final void r(Object obj, i iVar) {
        j1 j1Var = (j1) obj;
        j1Var.getClass();
        iVar.getClass();
        for (Map.Entry<Integer, j1.b> entry : j1Var.e.entrySet()) {
            j1.b value = entry.getValue();
            int iIntValue = entry.getKey().intValue();
            int i = j1.b.f;
            value.getClass();
            Iterator<lk> it = value.d.iterator();
            while (it.hasNext()) {
                iVar.o(iIntValue, it.next());
            }
        }
    }

    @Override // com.google.protobuf.i1
    public final void s(Object obj, i iVar) {
        ((j1) obj).j(iVar);
    }

    @Override // com.google.protobuf.i1
    public final void j(Object obj) {
    }
}
