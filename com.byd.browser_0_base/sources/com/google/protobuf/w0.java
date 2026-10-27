package com.google.protobuf;

import com.google.protobuf.e0;
import com.google.protobuf.n1;
import com.google.protobuf.x;
import defpackage.lk;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: compiled from: MessageSetSchema.java */
/* JADX INFO: loaded from: classes.dex */
public final class w0<T> implements b1<T> {
    public final r0 a;
    public final i1<?, ?> b;
    public final boolean c;
    public final t<?> d;

    public w0(i1<?, ?> i1Var, t<?> tVar, r0 r0Var) {
        this.b = i1Var;
        this.c = tVar.e(r0Var);
        this.d = tVar;
        this.a = r0Var;
    }

    @Override // com.google.protobuf.b1
    public final void a(T t, T t2) {
        Class<?> cls = c1.a;
        i1<?, ?> i1Var = this.b;
        i1Var.o(t, i1Var.k(i1Var.g(t), i1Var.g(t2)));
        if (this.c) {
            t<?> tVar = this.d;
            x<T> xVarC = tVar.c(t2);
            if (xVarC.o()) {
                return;
            }
            tVar.d(t).u(xVarC);
        }
    }

    @Override // com.google.protobuf.b1
    public final void b(Object obj, i iVar) {
        Iterator itS = this.d.c(obj).s();
        while (itS.hasNext()) {
            Map.Entry entry = (Map.Entry) itS.next();
            x.b bVar = (x.b) entry.getKey();
            if (bVar.w() != n1.b.n || bVar.c() || bVar.G()) {
                throw new IllegalStateException("Found invalid MessageSet item.");
            }
            if (entry instanceof e0.a) {
                iVar.o(bVar.b(), ((e0.a) entry).e.getValue().b());
            } else {
                iVar.o(bVar.b(), entry.getValue());
            }
        }
        i1<?, ?> i1Var = this.b;
        i1Var.r(i1Var.g(obj), iVar);
    }

    @Override // com.google.protobuf.b1
    public final void c(T t) {
        this.b.j(t);
        this.d.f(t);
    }

    @Override // com.google.protobuf.b1
    public final boolean d(T t) {
        return this.d.c(t).p();
    }

    /* JADX WARN: Type inference incomplete: some casts might be missing */
    @Override // com.google.protobuf.b1
    public final void e(T t, z0 z0Var, s sVar) {
        i1<?, ?> i1Var = this.b;
        Object objF = i1Var.f(t);
        t<?> tVar = this.d;
        x xVarD = tVar.d(t);
        while (z0Var.y() != Integer.MAX_VALUE && k(z0Var, sVar, tVar, xVarD, i1Var, objF)) {
            try {
            } finally {
                i1Var.n(t, (Object) objF);
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:43:0x00b2, code lost:
    
        if (r5 == null) goto L45;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x00b4, code lost:
    
        r10.b((r1 << 3) | 2, r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x00bb, code lost:
    
        r1 = r3;
        r11 = r4;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00aa  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x00b2 A[EDGE_INSN: B:61:0x00b2->B:43:0x00b2 BREAK  A[LOOP:1: B:21:0x0067->B:41:0x00ae], SYNTHETIC] */
    @Override // com.google.protobuf.b1
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void f(T r18, byte[] r19, int r20, int r21, com.google.protobuf.e.a r22) throws java.lang.Throwable {
        /*
            r17 = this;
            r0 = r17
            r7 = r19
            r8 = r21
            r9 = r22
            r1 = r18
            com.google.protobuf.z r1 = (com.google.protobuf.z) r1
            com.google.protobuf.k1 r2 = r1.unknownFields
            com.google.protobuf.k1 r3 = com.google.protobuf.k1.f
            if (r2 != r3) goto L19
            com.google.protobuf.k1 r2 = new com.google.protobuf.k1
            r2.<init>()
            r1.unknownFields = r2
        L19:
            r10 = r2
            r1 = r18
            com.google.protobuf.z$c r1 = (com.google.protobuf.z.c) r1
            com.google.protobuf.x<com.google.protobuf.z$d> r2 = r1.extensions
            boolean r3 = r2.b
            if (r3 == 0) goto L2a
            com.google.protobuf.x r2 = r2.clone()
            r1.extensions = r2
        L2a:
            r11 = 0
            r1 = r20
            r2 = r11
        L2e:
            if (r1 >= r8) goto Lbf
            int r3 = com.google.protobuf.e.H(r7, r1, r9)
            int r1 = r9.a
            r4 = 2
            r5 = 11
            com.google.protobuf.r0 r6 = r0.a
            com.google.protobuf.t<?> r12 = r0.d
            com.google.protobuf.s r13 = r9.d
            if (r1 == r5) goto L65
            r5 = r1 & 7
            if (r5 != r4) goto L60
            int r2 = r1 >>> 3
            java.lang.Object r2 = r12.b(r13, r6, r2)
            r12 = r2
            com.google.protobuf.z$e r12 = (com.google.protobuf.z.e) r12
            if (r12 != 0) goto L5d
            r2 = r19
            r4 = r21
            r5 = r10
            r6 = r22
            int r1 = com.google.protobuf.e.F(r1, r2, r3, r4, r5, r6)
            r2 = r12
            goto L2e
        L5d:
            a72 r0 = defpackage.a72.c
            throw r11
        L60:
            int r1 = com.google.protobuf.e.L(r1, r7, r3, r8, r9)
            goto L2e
        L65:
            r1 = 0
            r5 = r11
        L67:
            if (r3 >= r8) goto Lb1
            int r3 = com.google.protobuf.e.H(r7, r3, r9)
            int r14 = r9.a
            int r15 = r14 >>> 3
            r11 = r14 & 7
            if (r15 == r4) goto L8f
            r4 = 3
            if (r15 == r4) goto L79
            goto L89
        L79:
            if (r2 != 0) goto L8b
            r4 = 2
            if (r11 != r4) goto L89
            int r3 = com.google.protobuf.e.b(r7, r3, r9)
            java.lang.Object r4 = r9.c
            r5 = r4
            lk r5 = (defpackage.lk) r5
            r4 = 0
            goto Lae
        L89:
            r4 = 0
            goto La5
        L8b:
            a72 r0 = defpackage.a72.c
            r4 = 0
            throw r4
        L8f:
            r4 = 0
            if (r11 != 0) goto La5
            int r1 = com.google.protobuf.e.H(r7, r3, r9)
            int r2 = r9.a
            java.lang.Object r3 = r12.b(r13, r6, r2)
            com.google.protobuf.z$e r3 = (com.google.protobuf.z.e) r3
            r16 = r3
            r3 = r1
            r1 = r2
            r2 = r16
            goto Lae
        La5:
            r11 = 12
            if (r14 != r11) goto Laa
            goto Lb2
        Laa:
            int r3 = com.google.protobuf.e.L(r14, r7, r3, r8, r9)
        Lae:
            r11 = r4
            r4 = 2
            goto L67
        Lb1:
            r4 = r11
        Lb2:
            if (r5 == 0) goto Lbb
            int r1 = r1 << 3
            r6 = 2
            r1 = r1 | r6
            r10.b(r1, r5)
        Lbb:
            r1 = r3
            r11 = r4
            goto L2e
        Lbf:
            if (r1 != r8) goto Lc2
            return
        Lc2:
            com.google.protobuf.d0 r0 = com.google.protobuf.d0.h()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.w0.f(java.lang.Object, byte[], int, int, com.google.protobuf.e$a):void");
    }

    @Override // com.google.protobuf.b1
    public final boolean g(T t, T t2) {
        i1<?, ?> i1Var = this.b;
        if (!i1Var.g(t).equals(i1Var.g(t2))) {
            return false;
        }
        if (!this.c) {
            return true;
        }
        t<?> tVar = this.d;
        return tVar.c(t).equals(tVar.c(t2));
    }

    @Override // com.google.protobuf.b1
    public final int h(T t) {
        i1<?, ?> i1Var = this.b;
        int i = i1Var.i(i1Var.g(t)) + 0;
        return this.c ? i + this.d.c(t).k() : i;
    }

    @Override // com.google.protobuf.b1
    public final T i() {
        return (T) this.a.e().d();
    }

    @Override // com.google.protobuf.b1
    public final int j(T t) {
        int iHashCode = this.b.g(t).hashCode();
        return this.c ? (iHashCode * 53) + this.d.c(t).hashCode() : iHashCode;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final <UT, UB, ET extends x.b<ET>> boolean k(z0 z0Var, s sVar, t<ET> tVar, x<ET> xVar, i1<UT, UB> i1Var, UB ub) throws d0 {
        int tag = z0Var.getTag();
        r0 r0Var = this.a;
        if (tag != 11) {
            if ((tag & 7) != 2) {
                return z0Var.H();
            }
            Object objB = tVar.b(sVar, r0Var, tag >>> 3);
            if (objB == null) {
                return i1Var.l(ub, z0Var);
            }
            tVar.h(z0Var, objB, sVar, xVar);
            return true;
        }
        Object objB2 = null;
        int iN = 0;
        lk lkVarD = null;
        while (z0Var.y() != Integer.MAX_VALUE) {
            int tag2 = z0Var.getTag();
            if (tag2 == 16) {
                iN = z0Var.n();
                objB2 = tVar.b(sVar, r0Var, iN);
            } else if (tag2 == 26) {
                if (objB2 != null) {
                    tVar.h(z0Var, objB2, sVar, xVar);
                } else {
                    lkVarD = z0Var.D();
                }
            } else if (!z0Var.H()) {
                break;
            }
        }
        if (z0Var.getTag() != 12) {
            throw d0.a();
        }
        if (lkVarD != null) {
            if (objB2 != null) {
                tVar.i(objB2);
            } else {
                i1Var.d(ub, iN, lkVarD);
            }
        }
        return true;
    }
}
