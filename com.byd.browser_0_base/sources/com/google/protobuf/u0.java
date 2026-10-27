package com.google.protobuf;

import com.google.protobuf.n;
import com.google.protobuf.n1;
import com.google.protobuf.q0;
import com.google.protobuf.r;
import defpackage.C0173a;
import defpackage.lk;
import defpackage.nu;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: MessageReflection.java */
/* JADX INFO: loaded from: classes.dex */
public final class u0 {

    /* JADX INFO: compiled from: MessageReflection.java */
    public static class a implements c {
        public final q0.a a;

        public a(q0.a aVar) {
            this.a = aVar;
        }

        @Override // com.google.protobuf.u0.c
        public final c a(n.f fVar, Object obj) {
            this.a.a(fVar, obj);
            return this;
        }

        @Override // com.google.protobuf.u0.c
        public final q0 b(g gVar, s sVar, n.f fVar) {
            q0 q0Var;
            q0.a aVar = this.a;
            q0.a aVarW = aVar.w(fVar);
            if (!fVar.c() && (q0Var = (q0) aVar.o(fVar)) != null) {
                aVarW.R(q0Var);
            }
            gVar.s(fVar.f.i, aVarW, sVar);
            return aVarW.d();
        }

        @Override // com.google.protobuf.u0.c
        public final q0 c(lk lkVar, s sVar) {
            this.a.w(null);
            throw null;
        }

        @Override // com.google.protobuf.u0.c
        public final int d() {
            return 1;
        }

        @Override // com.google.protobuf.u0.c
        public final q0 e(g gVar, s sVar, n.f fVar) {
            q0 q0Var;
            q0.a aVar = this.a;
            q0.a aVarW = aVar.w(fVar);
            if (!fVar.c() && (q0Var = (q0) aVar.o(fVar)) != null) {
                aVarW.R(q0Var);
            }
            gVar.w(aVarW, sVar);
            return aVarW.d();
        }

        @Override // com.google.protobuf.u0.c
        public final c f(n.f fVar, Object obj) {
            this.a.f(fVar, obj);
            return this;
        }

        @Override // com.google.protobuf.u0.c
        public final r.b g(r rVar, n.a aVar, int i) {
            rVar.getClass();
            return rVar.d.get(new r.a(aVar, i));
        }

        @Override // com.google.protobuf.u0.c
        public final boolean h() {
            return this.a.k(null);
        }

        @Override // com.google.protobuf.u0.c
        public final n1.c i(n.f fVar) {
            if (fVar.q()) {
                return n1.c.f;
            }
            fVar.c();
            return n1.c.e;
        }
    }

    /* JADX INFO: compiled from: MessageReflection.java */
    public static class b implements c {
        public final x<n.f> a;

        public b(x<n.f> xVar) {
            this.a = xVar;
        }

        @Override // com.google.protobuf.u0.c
        public final c a(n.f fVar, Object obj) {
            this.a.w(fVar, obj);
            return this;
        }

        @Override // com.google.protobuf.u0.c
        public final q0 b(g gVar, s sVar, n.f fVar) {
            throw null;
        }

        @Override // com.google.protobuf.u0.c
        public final q0 c(lk lkVar, s sVar) {
            throw null;
        }

        @Override // com.google.protobuf.u0.c
        public final int d() {
            return 2;
        }

        @Override // com.google.protobuf.u0.c
        public final q0 e(g gVar, s sVar, n.f fVar) {
            throw null;
        }

        @Override // com.google.protobuf.u0.c
        public final c f(n.f fVar, Object obj) {
            this.a.a(fVar, obj);
            return this;
        }

        @Override // com.google.protobuf.u0.c
        public final r.b g(r rVar, n.a aVar, int i) {
            rVar.getClass();
            return rVar.d.get(new r.a(aVar, i));
        }

        @Override // com.google.protobuf.u0.c
        public final boolean h() {
            this.a.n(null);
            throw null;
        }

        @Override // com.google.protobuf.u0.c
        public final n1.c i(n.f fVar) {
            return fVar.q() ? n1.c.f : n1.c.e;
        }
    }

    /* JADX INFO: compiled from: MessageReflection.java */
    public interface c {
        c a(n.f fVar, Object obj);

        q0 b(g gVar, s sVar, n.f fVar);

        q0 c(lk lkVar, s sVar);

        int d();

        q0 e(g gVar, s sVar, n.f fVar);

        c f(n.f fVar, Object obj);

        r.b g(r rVar, n.a aVar, int i);

        boolean h();

        n1.c i(n.f fVar);
    }

    public static void a(t0 t0Var, String str, ArrayList arrayList) {
        for (n.f fVar : t0Var.c().k()) {
            if (fVar.p() && !t0Var.k(fVar)) {
                StringBuilder sbJ = C0173a.j(str);
                sbJ.append(fVar.f());
                arrayList.add(sbJ.toString());
            }
        }
        for (Map.Entry<n.f, Object> entry : t0Var.s().entrySet()) {
            n.f key = entry.getKey();
            Object value = entry.getValue();
            if (key.k.e == n.f.b.n) {
                if (key.c()) {
                    Iterator it = ((List) value).iterator();
                    int i = 0;
                    while (it.hasNext()) {
                        a((t0) it.next(), d(str, key, i), arrayList);
                        i++;
                    }
                } else if (t0Var.k(key)) {
                    a((t0) value, d(str, key, -1), arrayList);
                }
            }
        }
    }

    public static int b(q0 q0Var, Map<n.f, Object> map) {
        boolean z = q0Var.c().n().i;
        int iZ = 0;
        for (Map.Entry<n.f, Object> entry : map.entrySet()) {
            n.f key = entry.getKey();
            Object value = entry.getValue();
            iZ = ((z && key.l() && key.k == n.f.c.h && !key.c()) ? nu.Z(key.f.i, (q0) value) : x.h(key, value)) + iZ;
        }
        j1 j1VarN = q0Var.n();
        return (z ? j1VarN.g() : j1VarN.m()) + iZ;
    }

    /* JADX WARN: Removed duplicated region for block: B:63:0x00cc  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean c(com.google.protobuf.g r7, com.google.protobuf.j1.a r8, com.google.protobuf.s r9, com.google.protobuf.n.a r10, com.google.protobuf.u0.c r11, int r12) {
        /*
            Method dump skipped, instruction units count: 405
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.u0.c(com.google.protobuf.g, com.google.protobuf.j1$a, com.google.protobuf.s, com.google.protobuf.n$a, com.google.protobuf.u0$c, int):boolean");
    }

    public static String d(String str, n.f fVar, int i) {
        StringBuilder sb = new StringBuilder(str);
        if (fVar.l()) {
            sb.append('(');
            sb.append(fVar.g);
            sb.append(')');
        } else {
            sb.append(fVar.f());
        }
        if (i != -1) {
            sb.append('[');
            sb.append(i);
            sb.append(']');
        }
        sb.append('.');
        return sb.toString();
    }

    public static void e(q0 q0Var, Map map, nu nuVar) {
        boolean z = q0Var.c().n().i;
        for (Map.Entry entry : map.entrySet()) {
            n.f fVar = (n.f) entry.getKey();
            Object value = entry.getValue();
            if (z && fVar.l() && fVar.k == n.f.c.h && !fVar.c()) {
                nuVar.I0(fVar.f.i, (q0) value);
            } else {
                x.A(fVar, value, nuVar);
            }
        }
        j1 j1VarN = q0Var.n();
        if (z) {
            j1VarN.i(nuVar);
        } else {
            j1VarN.p(nuVar);
        }
    }
}
