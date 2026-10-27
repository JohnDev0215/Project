package com.google.protobuf;

import com.google.protobuf.a0;
import com.google.protobuf.m;
import com.google.protobuf.n;
import com.google.protobuf.r;
import defpackage.dj3;
import defpackage.lk;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: ExtensionSchemaFull.java */
/* JADX INFO: loaded from: classes.dex */
public final class u extends t<n.f> {
    public static final long a;
    public static final /* synthetic */ int b = 0;

    static {
        a = dj3.q(a0.d.class.getDeclaredField("g"));
    }

    @Override // com.google.protobuf.t
    public final int a(Map.Entry<?, ?> entry) {
        return ((n.f) entry.getKey()).f.i;
    }

    @Override // com.google.protobuf.t
    public final Object b(s sVar, r0 r0Var, int i) {
        r rVar = (r) sVar;
        n.a aVarC = ((q0) r0Var).c();
        rVar.getClass();
        return rVar.d.get(new r.a(aVarC, i));
    }

    @Override // com.google.protobuf.t
    public final x<n.f> c(Object obj) {
        return (x) dj3.o(a, obj);
    }

    @Override // com.google.protobuf.t
    public final x<n.f> d(Object obj) {
        x<n.f> xVarC = c(obj);
        if (!xVarC.b) {
            return xVarC;
        }
        x xVarClone = xVarC.clone();
        dj3.z(a, obj, xVarClone);
        return xVarClone;
    }

    @Override // com.google.protobuf.t
    public final boolean e(r0 r0Var) {
        return r0Var instanceof a0.d;
    }

    @Override // com.google.protobuf.t
    public final void f(Object obj) {
        c(obj).t();
    }

    @Override // com.google.protobuf.t
    public final Object g(Object obj) {
        throw null;
    }

    @Override // com.google.protobuf.t
    public final void h(z0 z0Var, Object obj, s sVar, x<n.f> xVar) {
        s sVar2 = s.b;
        xVar.w(null, new e0(sVar, z0Var.D()));
        throw null;
    }

    @Override // com.google.protobuf.t
    public final void i(Object obj) {
        throw null;
    }

    @Override // com.google.protobuf.t
    public final void j(i iVar, Map.Entry entry) {
        n.f fVar = (n.f) entry.getKey();
        boolean zC = fVar.c();
        m.g gVar = fVar.f;
        if (!zC) {
            switch (fVar.d().ordinal()) {
                case 0:
                    iVar.c(gVar.i, ((Double) entry.getValue()).doubleValue());
                    break;
                case 1:
                    iVar.i(((Float) entry.getValue()).floatValue(), gVar.i);
                    break;
                case 2:
                    iVar.l(gVar.i, ((Long) entry.getValue()).longValue());
                    break;
                case 3:
                    iVar.u(gVar.i, ((Long) entry.getValue()).longValue());
                    break;
                case 4:
                    iVar.k(gVar.i, ((Integer) entry.getValue()).intValue());
                    break;
                case 5:
                    iVar.g(gVar.i, ((Long) entry.getValue()).longValue());
                    break;
                case 6:
                    iVar.e(gVar.i, ((Integer) entry.getValue()).intValue());
                    break;
                case 7:
                    iVar.a(gVar.i, ((Boolean) entry.getValue()).booleanValue());
                    break;
                case 8:
                    iVar.a.K0(gVar.i, (String) entry.getValue());
                    break;
                case 9:
                    iVar.a.y0(gVar.i, (r0) entry.getValue());
                    break;
                case 10:
                    iVar.a.D0(gVar.i, (r0) entry.getValue());
                    break;
                case 11:
                    iVar.b(gVar.i, (lk) entry.getValue());
                    break;
                case 12:
                    iVar.t(gVar.i, ((Integer) entry.getValue()).intValue());
                    break;
                case 13:
                    iVar.k(gVar.i, ((n.e) entry.getValue()).e.i);
                    break;
                case 14:
                    iVar.p(gVar.i, ((Integer) entry.getValue()).intValue());
                    break;
                case 15:
                    iVar.q(gVar.i, ((Long) entry.getValue()).longValue());
                    break;
                case 16:
                    iVar.r(gVar.i, ((Integer) entry.getValue()).intValue());
                    break;
                case 17:
                    iVar.s(gVar.i, ((Long) entry.getValue()).longValue());
                    break;
            }
        }
        int i = 0;
        switch (fVar.d().ordinal()) {
            case 0:
                c1.G(gVar.i, (List) entry.getValue(), iVar, fVar.G());
                break;
            case 1:
                c1.K(gVar.i, (List) entry.getValue(), iVar, fVar.G());
                break;
            case 2:
                c1.N(gVar.i, (List) entry.getValue(), iVar, fVar.G());
                break;
            case 3:
                c1.V(gVar.i, (List) entry.getValue(), iVar, fVar.G());
                break;
            case 4:
                c1.M(gVar.i, (List) entry.getValue(), iVar, fVar.G());
                break;
            case 5:
                c1.J(gVar.i, (List) entry.getValue(), iVar, fVar.G());
                break;
            case 6:
                c1.I(gVar.i, (List) entry.getValue(), iVar, fVar.G());
                break;
            case 7:
                c1.E(gVar.i, (List) entry.getValue(), iVar, fVar.G());
                break;
            case 8:
                c1.T(gVar.i, (List) entry.getValue(), iVar);
                break;
            case 9:
                int i2 = gVar.i;
                List list = (List) entry.getValue();
                Class<?> cls = c1.a;
                if (list != null && !list.isEmpty()) {
                    iVar.getClass();
                    while (i < list.size()) {
                        iVar.a.y0(i2, (r0) list.get(i));
                        i++;
                    }
                    break;
                }
                break;
            case 10:
                int i3 = gVar.i;
                List list2 = (List) entry.getValue();
                Class<?> cls2 = c1.a;
                if (list2 != null && !list2.isEmpty()) {
                    iVar.getClass();
                    while (i < list2.size()) {
                        iVar.a.D0(i3, (r0) list2.get(i));
                        i++;
                    }
                    break;
                }
                break;
            case 11:
                c1.F(gVar.i, (List) entry.getValue(), iVar);
                break;
            case 12:
                c1.U(gVar.i, (List) entry.getValue(), iVar, fVar.G());
                break;
            case 13:
                List list3 = (List) entry.getValue();
                ArrayList arrayList = new ArrayList();
                Iterator it = list3.iterator();
                while (it.hasNext()) {
                    arrayList.add(Integer.valueOf(((n.e) it.next()).e.i));
                }
                c1.M(gVar.i, arrayList, iVar, fVar.G());
                break;
            case 14:
                c1.P(gVar.i, (List) entry.getValue(), iVar, fVar.G());
                break;
            case 15:
                c1.Q(gVar.i, (List) entry.getValue(), iVar, fVar.G());
                break;
            case 16:
                c1.R(gVar.i, (List) entry.getValue(), iVar, fVar.G());
                break;
            case 17:
                c1.S(gVar.i, (List) entry.getValue(), iVar, fVar.G());
                break;
        }
    }
}
