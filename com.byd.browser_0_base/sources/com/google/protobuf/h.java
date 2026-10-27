package com.google.protobuf;

import com.google.protobuf.d0;
import com.google.protobuf.n1;
import defpackage.a72;
import defpackage.lk;
import defpackage.q61;
import java.nio.charset.Charset;
import java.util.List;

/* JADX INFO: compiled from: CodedInputStreamReader.java */
/* JADX INFO: loaded from: classes.dex */
public final class h implements z0 {
    public final g a;
    public int b;
    public int c;
    public int d = 0;

    public h(g gVar) {
        Charset charset = c0.a;
        if (gVar == null) {
            throw new NullPointerException("input");
        }
        this.a = gVar;
        gVar.c = this;
    }

    @Override // com.google.protobuf.z0
    public final <T> T A(b1<T> b1Var, s sVar) throws d0.a {
        T(2);
        return (T) Q(b1Var, sVar);
    }

    /* JADX WARN: Code restructure failed: missing block: B:24:0x0057, code lost:
    
        r10.put(r3, r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x005d, code lost:
    
        return;
     */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.protobuf.z0
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final <K, V> void B(java.util.Map<K, V> r10, com.google.protobuf.k0.a<K, V> r11, com.google.protobuf.s r12) throws com.google.protobuf.d0.a {
        /*
            r9 = this;
            r0 = 2
            r9.T(r0)
            com.google.protobuf.g r1 = r9.a
            int r2 = r1.G()
            int r2 = r1.k(r2)
            K r3 = r11.b
            V r4 = r11.d
            r5 = r4
        L13:
            int r6 = r9.y()     // Catch: java.lang.Throwable -> L5e
            r7 = 2147483647(0x7fffffff, float:NaN)
            if (r6 == r7) goto L57
            boolean r7 = r1.g()     // Catch: java.lang.Throwable -> L5e
            if (r7 == 0) goto L23
            goto L57
        L23:
            r7 = 1
            java.lang.String r8 = "Unable to parse map entry."
            if (r6 == r7) goto L42
            if (r6 == r0) goto L37
            boolean r6 = r9.H()     // Catch: com.google.protobuf.d0.a -> L4a java.lang.Throwable -> L5e
            if (r6 == 0) goto L31
            goto L13
        L31:
            com.google.protobuf.d0 r6 = new com.google.protobuf.d0     // Catch: com.google.protobuf.d0.a -> L4a java.lang.Throwable -> L5e
            r6.<init>(r8)     // Catch: com.google.protobuf.d0.a -> L4a java.lang.Throwable -> L5e
            throw r6     // Catch: com.google.protobuf.d0.a -> L4a java.lang.Throwable -> L5e
        L37:
            com.google.protobuf.n1$a r6 = r11.c     // Catch: com.google.protobuf.d0.a -> L4a java.lang.Throwable -> L5e
            java.lang.Class r7 = r4.getClass()     // Catch: com.google.protobuf.d0.a -> L4a java.lang.Throwable -> L5e
            java.lang.Object r5 = r9.O(r6, r7, r12)     // Catch: com.google.protobuf.d0.a -> L4a java.lang.Throwable -> L5e
            goto L13
        L42:
            com.google.protobuf.n1$a r6 = r11.a     // Catch: com.google.protobuf.d0.a -> L4a java.lang.Throwable -> L5e
            r7 = 0
            java.lang.Object r3 = r9.O(r6, r7, r7)     // Catch: com.google.protobuf.d0.a -> L4a java.lang.Throwable -> L5e
            goto L13
        L4a:
            boolean r6 = r9.H()     // Catch: java.lang.Throwable -> L5e
            if (r6 == 0) goto L51
            goto L13
        L51:
            com.google.protobuf.d0 r9 = new com.google.protobuf.d0     // Catch: java.lang.Throwable -> L5e
            r9.<init>(r8)     // Catch: java.lang.Throwable -> L5e
            throw r9     // Catch: java.lang.Throwable -> L5e
        L57:
            r10.put(r3, r5)     // Catch: java.lang.Throwable -> L5e
            r1.j(r2)
            return
        L5e:
            r9 = move-exception
            r1.j(r2)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.h.B(java.util.Map, com.google.protobuf.k0$a, com.google.protobuf.s):void");
    }

    @Override // com.google.protobuf.z0
    public final void C(List<String> list) throws d0.a {
        R(list, true);
    }

    @Override // com.google.protobuf.z0
    public final lk D() throws d0.a {
        T(2);
        return this.a.m();
    }

    @Override // com.google.protobuf.z0
    public final void E(List<Float> list) throws d0 {
        int iF;
        int iF2;
        boolean z = list instanceof y;
        g gVar = this.a;
        if (!z) {
            int i = this.b & 7;
            if (i == 2) {
                int iG = gVar.G();
                U(iG);
                int iF3 = gVar.f() + iG;
                do {
                    list.add(Float.valueOf(gVar.r()));
                } while (gVar.f() < iF3);
                return;
            }
            if (i != 5) {
                throw d0.d();
            }
            do {
                list.add(Float.valueOf(gVar.r()));
                if (gVar.g()) {
                    return;
                } else {
                    iF = gVar.F();
                }
            } while (iF == this.b);
            this.d = iF;
            return;
        }
        y yVar = (y) list;
        int i2 = this.b & 7;
        if (i2 == 2) {
            int iG2 = gVar.G();
            U(iG2);
            int iF4 = gVar.f() + iG2;
            do {
                yVar.b(gVar.r());
            } while (gVar.f() < iF4);
            return;
        }
        if (i2 != 5) {
            throw d0.d();
        }
        do {
            yVar.b(gVar.r());
            if (gVar.g()) {
                return;
            } else {
                iF2 = gVar.F();
            }
        } while (iF2 == this.b);
        this.d = iF2;
    }

    @Override // com.google.protobuf.z0
    public final int F() throws d0.a {
        T(0);
        return this.a.t();
    }

    @Override // com.google.protobuf.z0
    public final void G() {
        this.a.getClass();
    }

    @Override // com.google.protobuf.z0
    public final boolean H() {
        int i;
        g gVar = this.a;
        if (gVar.g() || (i = this.b) == this.c) {
            return false;
        }
        return gVar.I(i);
    }

    @Override // com.google.protobuf.z0
    public final int I() throws d0.a {
        T(5);
        return this.a.z();
    }

    @Override // com.google.protobuf.z0
    public final void J(List<lk> list) throws d0.a {
        int iF;
        if ((this.b & 7) != 2) {
            throw d0.d();
        }
        do {
            list.add(D());
            g gVar = this.a;
            if (gVar.g()) {
                return;
            } else {
                iF = gVar.F();
            }
        } while (iF == this.b);
        this.d = iF;
    }

    @Override // com.google.protobuf.z0
    public final void K(List<Double> list) throws d0 {
        int iF;
        int iF2;
        boolean z = list instanceof o;
        g gVar = this.a;
        if (!z) {
            int i = this.b & 7;
            if (i == 1) {
                do {
                    list.add(Double.valueOf(gVar.n()));
                    if (gVar.g()) {
                        return;
                    } else {
                        iF = gVar.F();
                    }
                } while (iF == this.b);
                this.d = iF;
                return;
            }
            if (i != 2) {
                throw d0.d();
            }
            int iG = gVar.G();
            V(iG);
            int iF3 = gVar.f() + iG;
            do {
                list.add(Double.valueOf(gVar.n()));
            } while (gVar.f() < iF3);
            return;
        }
        o oVar = (o) list;
        int i2 = this.b & 7;
        if (i2 == 1) {
            do {
                oVar.b(gVar.n());
                if (gVar.g()) {
                    return;
                } else {
                    iF2 = gVar.F();
                }
            } while (iF2 == this.b);
            this.d = iF2;
            return;
        }
        if (i2 != 2) {
            throw d0.d();
        }
        int iG2 = gVar.G();
        V(iG2);
        int iF4 = gVar.f() + iG2;
        do {
            oVar.b(gVar.n());
        } while (gVar.f() < iF4);
    }

    @Override // com.google.protobuf.z0
    public final long L() throws d0.a {
        T(0);
        return this.a.u();
    }

    @Override // com.google.protobuf.z0
    public final String M() throws d0.a {
        T(2);
        return this.a.E();
    }

    @Override // com.google.protobuf.z0
    public final void N(List<Long> list) throws d0 {
        int iF;
        int iF2;
        boolean z = list instanceof i0;
        g gVar = this.a;
        if (!z) {
            int i = this.b & 7;
            if (i == 1) {
                do {
                    list.add(Long.valueOf(gVar.q()));
                    if (gVar.g()) {
                        return;
                    } else {
                        iF = gVar.F();
                    }
                } while (iF == this.b);
                this.d = iF;
                return;
            }
            if (i != 2) {
                throw d0.d();
            }
            int iG = gVar.G();
            V(iG);
            int iF3 = gVar.f() + iG;
            do {
                list.add(Long.valueOf(gVar.q()));
            } while (gVar.f() < iF3);
            return;
        }
        i0 i0Var = (i0) list;
        int i2 = this.b & 7;
        if (i2 == 1) {
            do {
                i0Var.b(gVar.q());
                if (gVar.g()) {
                    return;
                } else {
                    iF2 = gVar.F();
                }
            } while (iF2 == this.b);
            this.d = iF2;
            return;
        }
        if (i2 != 2) {
            throw d0.d();
        }
        int iG2 = gVar.G();
        V(iG2);
        int iF4 = gVar.f() + iG2;
        do {
            i0Var.b(gVar.q());
        } while (gVar.f() < iF4);
    }

    public final Object O(n1.a aVar, Class<?> cls, s sVar) throws d0.a {
        switch (aVar.ordinal()) {
            case 0:
                return Double.valueOf(readDouble());
            case 1:
                return Float.valueOf(readFloat());
            case 2:
                return Long.valueOf(L());
            case 3:
                return Long.valueOf(b());
            case 4:
                return Integer.valueOf(F());
            case 5:
                return Long.valueOf(c());
            case 6:
                return Integer.valueOf(h());
            case 7:
                return Boolean.valueOf(i());
            case 8:
                return M();
            case 9:
            default:
                throw new RuntimeException("unsupported field type.");
            case 10:
                T(2);
                return Q(a72.c.a(cls), sVar);
            case 11:
                return D();
            case 12:
                return Integer.valueOf(n());
            case 13:
                return Integer.valueOf(s());
            case 14:
                return Integer.valueOf(I());
            case 15:
                return Long.valueOf(k());
            case 16:
                return Integer.valueOf(u());
            case 17:
                return Long.valueOf(v());
        }
    }

    public final <T> T P(b1<T> b1Var, s sVar) {
        int i = this.c;
        this.c = ((this.b >>> 3) << 3) | 4;
        try {
            T tI = b1Var.i();
            b1Var.e(tI, this, sVar);
            b1Var.c(tI);
            if (this.b == this.c) {
                return tI;
            }
            throw d0.h();
        } finally {
            this.c = i;
        }
    }

    public final <T> T Q(b1<T> b1Var, s sVar) throws d0 {
        g gVar = this.a;
        int iG = gVar.G();
        if (gVar.a >= gVar.b) {
            throw new d0("Protocol message had too many levels of nesting.  May be malicious.  Use CodedInputStream.setRecursionLimit() to increase the depth limit.");
        }
        int iK = gVar.k(iG);
        T tI = b1Var.i();
        gVar.a++;
        b1Var.e(tI, this, sVar);
        b1Var.c(tI);
        gVar.a(0);
        gVar.a--;
        gVar.j(iK);
        return tI;
    }

    public final void R(List<String> list, boolean z) throws d0.a {
        int iF;
        int iF2;
        if ((this.b & 7) != 2) {
            throw d0.d();
        }
        boolean z2 = list instanceof q61;
        g gVar = this.a;
        if (!z2 || z) {
            do {
                list.add(z ? M() : x());
                if (gVar.g()) {
                    return;
                } else {
                    iF = gVar.F();
                }
            } while (iF == this.b);
            this.d = iF;
            return;
        }
        q61 q61Var = (q61) list;
        do {
            q61Var.l(D());
            if (gVar.g()) {
                return;
            } else {
                iF2 = gVar.F();
            }
        } while (iF2 == this.b);
        this.d = iF2;
    }

    public final void S(int i) throws d0 {
        if (this.a.f() != i) {
            throw d0.k();
        }
    }

    public final void T(int i) throws d0.a {
        if ((this.b & 7) != i) {
            throw d0.d();
        }
    }

    public final void U(int i) throws d0 {
        if ((i & 3) != 0) {
            throw d0.h();
        }
    }

    public final void V(int i) throws d0 {
        if ((i & 7) != 0) {
            throw d0.h();
        }
    }

    @Override // com.google.protobuf.z0
    public final void a(List<Integer> list) throws d0 {
        int iF;
        int iF2;
        boolean z = list instanceof b0;
        g gVar = this.a;
        if (!z) {
            int i = this.b & 7;
            if (i == 0) {
                do {
                    list.add(Integer.valueOf(gVar.B()));
                    if (gVar.g()) {
                        return;
                    } else {
                        iF = gVar.F();
                    }
                } while (iF == this.b);
                this.d = iF;
                return;
            }
            if (i != 2) {
                throw d0.d();
            }
            int iF3 = gVar.f() + gVar.G();
            do {
                list.add(Integer.valueOf(gVar.B()));
            } while (gVar.f() < iF3);
            S(iF3);
            return;
        }
        b0 b0Var = (b0) list;
        int i2 = this.b & 7;
        if (i2 == 0) {
            do {
                b0Var.b(gVar.B());
                if (gVar.g()) {
                    return;
                } else {
                    iF2 = gVar.F();
                }
            } while (iF2 == this.b);
            this.d = iF2;
            return;
        }
        if (i2 != 2) {
            throw d0.d();
        }
        int iF4 = gVar.f() + gVar.G();
        do {
            b0Var.b(gVar.B());
        } while (gVar.f() < iF4);
        S(iF4);
    }

    @Override // com.google.protobuf.z0
    public final long b() throws d0.a {
        T(0);
        return this.a.H();
    }

    @Override // com.google.protobuf.z0
    public final long c() throws d0.a {
        T(1);
        return this.a.q();
    }

    @Override // com.google.protobuf.z0
    public final void d(List<Integer> list) throws d0 {
        int iF;
        int iF2;
        boolean z = list instanceof b0;
        g gVar = this.a;
        if (!z) {
            int i = this.b & 7;
            if (i == 2) {
                int iG = gVar.G();
                U(iG);
                int iF3 = gVar.f() + iG;
                do {
                    list.add(Integer.valueOf(gVar.z()));
                } while (gVar.f() < iF3);
                return;
            }
            if (i != 5) {
                throw d0.d();
            }
            do {
                list.add(Integer.valueOf(gVar.z()));
                if (gVar.g()) {
                    return;
                } else {
                    iF = gVar.F();
                }
            } while (iF == this.b);
            this.d = iF;
            return;
        }
        b0 b0Var = (b0) list;
        int i2 = this.b & 7;
        if (i2 == 2) {
            int iG2 = gVar.G();
            U(iG2);
            int iF4 = gVar.f() + iG2;
            do {
                b0Var.b(gVar.z());
            } while (gVar.f() < iF4);
            return;
        }
        if (i2 != 5) {
            throw d0.d();
        }
        do {
            b0Var.b(gVar.z());
            if (gVar.g()) {
                return;
            } else {
                iF2 = gVar.F();
            }
        } while (iF2 == this.b);
        this.d = iF2;
    }

    @Override // com.google.protobuf.z0
    public final void e(List<Long> list) throws d0 {
        int iF;
        int iF2;
        boolean z = list instanceof i0;
        g gVar = this.a;
        if (!z) {
            int i = this.b & 7;
            if (i == 0) {
                do {
                    list.add(Long.valueOf(gVar.C()));
                    if (gVar.g()) {
                        return;
                    } else {
                        iF = gVar.F();
                    }
                } while (iF == this.b);
                this.d = iF;
                return;
            }
            if (i != 2) {
                throw d0.d();
            }
            int iF3 = gVar.f() + gVar.G();
            do {
                list.add(Long.valueOf(gVar.C()));
            } while (gVar.f() < iF3);
            S(iF3);
            return;
        }
        i0 i0Var = (i0) list;
        int i2 = this.b & 7;
        if (i2 == 0) {
            do {
                i0Var.b(gVar.C());
                if (gVar.g()) {
                    return;
                } else {
                    iF2 = gVar.F();
                }
            } while (iF2 == this.b);
            this.d = iF2;
            return;
        }
        if (i2 != 2) {
            throw d0.d();
        }
        int iF4 = gVar.f() + gVar.G();
        do {
            i0Var.b(gVar.C());
        } while (gVar.f() < iF4);
        S(iF4);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.protobuf.z0
    public final <T> void f(List<T> list, b1<T> b1Var, s sVar) throws d0.a {
        int iF;
        int i = this.b;
        if ((i & 7) != 3) {
            int i2 = d0.f;
            throw new d0.a();
        }
        do {
            list.add(P(b1Var, sVar));
            g gVar = this.a;
            if (gVar.g() || this.d != 0) {
                return;
            } else {
                iF = gVar.F();
            }
        } while (iF == i);
        this.d = iF;
    }

    @Override // com.google.protobuf.z0
    public final void g(List<Integer> list) throws d0 {
        int iF;
        int iF2;
        boolean z = list instanceof b0;
        g gVar = this.a;
        if (!z) {
            int i = this.b & 7;
            if (i == 0) {
                do {
                    list.add(Integer.valueOf(gVar.G()));
                    if (gVar.g()) {
                        return;
                    } else {
                        iF = gVar.F();
                    }
                } while (iF == this.b);
                this.d = iF;
                return;
            }
            if (i != 2) {
                throw d0.d();
            }
            int iF3 = gVar.f() + gVar.G();
            do {
                list.add(Integer.valueOf(gVar.G()));
            } while (gVar.f() < iF3);
            S(iF3);
            return;
        }
        b0 b0Var = (b0) list;
        int i2 = this.b & 7;
        if (i2 == 0) {
            do {
                b0Var.b(gVar.G());
                if (gVar.g()) {
                    return;
                } else {
                    iF2 = gVar.F();
                }
            } while (iF2 == this.b);
            this.d = iF2;
            return;
        }
        if (i2 != 2) {
            throw d0.d();
        }
        int iF4 = gVar.f() + gVar.G();
        do {
            b0Var.b(gVar.G());
        } while (gVar.f() < iF4);
        S(iF4);
    }

    @Override // com.google.protobuf.z0
    public final int getTag() {
        return this.b;
    }

    @Override // com.google.protobuf.z0
    public final int h() throws d0.a {
        T(5);
        return this.a.p();
    }

    @Override // com.google.protobuf.z0
    public final boolean i() throws d0.a {
        T(0);
        return this.a.l();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.protobuf.z0
    public final <T> void j(List<T> list, b1<T> b1Var, s sVar) throws d0.a {
        int iF;
        int i = this.b;
        if ((i & 7) != 2) {
            int i2 = d0.f;
            throw new d0.a();
        }
        do {
            list.add(Q(b1Var, sVar));
            g gVar = this.a;
            if (gVar.g() || this.d != 0) {
                return;
            } else {
                iF = gVar.F();
            }
        } while (iF == i);
        this.d = iF;
    }

    @Override // com.google.protobuf.z0
    public final long k() throws d0.a {
        T(1);
        return this.a.A();
    }

    @Override // com.google.protobuf.z0
    public final void l(List<Long> list) throws d0 {
        int iF;
        int iF2;
        boolean z = list instanceof i0;
        g gVar = this.a;
        if (!z) {
            int i = this.b & 7;
            if (i == 0) {
                do {
                    list.add(Long.valueOf(gVar.H()));
                    if (gVar.g()) {
                        return;
                    } else {
                        iF = gVar.F();
                    }
                } while (iF == this.b);
                this.d = iF;
                return;
            }
            if (i != 2) {
                throw d0.d();
            }
            int iF3 = gVar.f() + gVar.G();
            do {
                list.add(Long.valueOf(gVar.H()));
            } while (gVar.f() < iF3);
            S(iF3);
            return;
        }
        i0 i0Var = (i0) list;
        int i2 = this.b & 7;
        if (i2 == 0) {
            do {
                i0Var.b(gVar.H());
                if (gVar.g()) {
                    return;
                } else {
                    iF2 = gVar.F();
                }
            } while (iF2 == this.b);
            this.d = iF2;
            return;
        }
        if (i2 != 2) {
            throw d0.d();
        }
        int iF4 = gVar.f() + gVar.G();
        do {
            i0Var.b(gVar.H());
        } while (gVar.f() < iF4);
        S(iF4);
    }

    @Override // com.google.protobuf.z0
    public final <T> T m(b1<T> b1Var, s sVar) throws d0.a {
        T(3);
        return (T) P(b1Var, sVar);
    }

    @Override // com.google.protobuf.z0
    public final int n() throws d0.a {
        T(0);
        return this.a.G();
    }

    @Override // com.google.protobuf.z0
    public final void o(List<Long> list) throws d0 {
        int iF;
        int iF2;
        boolean z = list instanceof i0;
        g gVar = this.a;
        if (!z) {
            int i = this.b & 7;
            if (i == 0) {
                do {
                    list.add(Long.valueOf(gVar.u()));
                    if (gVar.g()) {
                        return;
                    } else {
                        iF = gVar.F();
                    }
                } while (iF == this.b);
                this.d = iF;
                return;
            }
            if (i != 2) {
                throw d0.d();
            }
            int iF3 = gVar.f() + gVar.G();
            do {
                list.add(Long.valueOf(gVar.u()));
            } while (gVar.f() < iF3);
            S(iF3);
            return;
        }
        i0 i0Var = (i0) list;
        int i2 = this.b & 7;
        if (i2 == 0) {
            do {
                i0Var.b(gVar.u());
                if (gVar.g()) {
                    return;
                } else {
                    iF2 = gVar.F();
                }
            } while (iF2 == this.b);
            this.d = iF2;
            return;
        }
        if (i2 != 2) {
            throw d0.d();
        }
        int iF4 = gVar.f() + gVar.G();
        do {
            i0Var.b(gVar.u());
        } while (gVar.f() < iF4);
        S(iF4);
    }

    @Override // com.google.protobuf.z0
    public final void p(List<Long> list) throws d0 {
        int iF;
        int iF2;
        boolean z = list instanceof i0;
        g gVar = this.a;
        if (!z) {
            int i = this.b & 7;
            if (i == 1) {
                do {
                    list.add(Long.valueOf(gVar.A()));
                    if (gVar.g()) {
                        return;
                    } else {
                        iF = gVar.F();
                    }
                } while (iF == this.b);
                this.d = iF;
                return;
            }
            if (i != 2) {
                throw d0.d();
            }
            int iG = gVar.G();
            V(iG);
            int iF3 = gVar.f() + iG;
            do {
                list.add(Long.valueOf(gVar.A()));
            } while (gVar.f() < iF3);
            return;
        }
        i0 i0Var = (i0) list;
        int i2 = this.b & 7;
        if (i2 == 1) {
            do {
                i0Var.b(gVar.A());
                if (gVar.g()) {
                    return;
                } else {
                    iF2 = gVar.F();
                }
            } while (iF2 == this.b);
            this.d = iF2;
            return;
        }
        if (i2 != 2) {
            throw d0.d();
        }
        int iG2 = gVar.G();
        V(iG2);
        int iF4 = gVar.f() + iG2;
        do {
            i0Var.b(gVar.A());
        } while (gVar.f() < iF4);
    }

    @Override // com.google.protobuf.z0
    public final void q(List<Integer> list) throws d0 {
        int iF;
        int iF2;
        boolean z = list instanceof b0;
        g gVar = this.a;
        if (!z) {
            int i = this.b & 7;
            if (i == 0) {
                do {
                    list.add(Integer.valueOf(gVar.t()));
                    if (gVar.g()) {
                        return;
                    } else {
                        iF = gVar.F();
                    }
                } while (iF == this.b);
                this.d = iF;
                return;
            }
            if (i != 2) {
                throw d0.d();
            }
            int iF3 = gVar.f() + gVar.G();
            do {
                list.add(Integer.valueOf(gVar.t()));
            } while (gVar.f() < iF3);
            S(iF3);
            return;
        }
        b0 b0Var = (b0) list;
        int i2 = this.b & 7;
        if (i2 == 0) {
            do {
                b0Var.b(gVar.t());
                if (gVar.g()) {
                    return;
                } else {
                    iF2 = gVar.F();
                }
            } while (iF2 == this.b);
            this.d = iF2;
            return;
        }
        if (i2 != 2) {
            throw d0.d();
        }
        int iF4 = gVar.f() + gVar.G();
        do {
            b0Var.b(gVar.t());
        } while (gVar.f() < iF4);
        S(iF4);
    }

    @Override // com.google.protobuf.z0
    public final void r(List<Integer> list) throws d0 {
        int iF;
        int iF2;
        boolean z = list instanceof b0;
        g gVar = this.a;
        if (!z) {
            int i = this.b & 7;
            if (i == 0) {
                do {
                    list.add(Integer.valueOf(gVar.o()));
                    if (gVar.g()) {
                        return;
                    } else {
                        iF = gVar.F();
                    }
                } while (iF == this.b);
                this.d = iF;
                return;
            }
            if (i != 2) {
                throw d0.d();
            }
            int iF3 = gVar.f() + gVar.G();
            do {
                list.add(Integer.valueOf(gVar.o()));
            } while (gVar.f() < iF3);
            S(iF3);
            return;
        }
        b0 b0Var = (b0) list;
        int i2 = this.b & 7;
        if (i2 == 0) {
            do {
                b0Var.b(gVar.o());
                if (gVar.g()) {
                    return;
                } else {
                    iF2 = gVar.F();
                }
            } while (iF2 == this.b);
            this.d = iF2;
            return;
        }
        if (i2 != 2) {
            throw d0.d();
        }
        int iF4 = gVar.f() + gVar.G();
        do {
            b0Var.b(gVar.o());
        } while (gVar.f() < iF4);
        S(iF4);
    }

    @Override // com.google.protobuf.z0
    public final double readDouble() throws d0.a {
        T(1);
        return this.a.n();
    }

    @Override // com.google.protobuf.z0
    public final float readFloat() throws d0.a {
        T(5);
        return this.a.r();
    }

    @Override // com.google.protobuf.z0
    public final int s() throws d0.a {
        T(0);
        return this.a.o();
    }

    @Override // com.google.protobuf.z0
    public final void t(List<Integer> list) throws d0 {
        int iF;
        int iF2;
        boolean z = list instanceof b0;
        g gVar = this.a;
        if (!z) {
            int i = this.b & 7;
            if (i == 2) {
                int iG = gVar.G();
                U(iG);
                int iF3 = gVar.f() + iG;
                do {
                    list.add(Integer.valueOf(gVar.p()));
                } while (gVar.f() < iF3);
                return;
            }
            if (i != 5) {
                throw d0.d();
            }
            do {
                list.add(Integer.valueOf(gVar.p()));
                if (gVar.g()) {
                    return;
                } else {
                    iF = gVar.F();
                }
            } while (iF == this.b);
            this.d = iF;
            return;
        }
        b0 b0Var = (b0) list;
        int i2 = this.b & 7;
        if (i2 == 2) {
            int iG2 = gVar.G();
            U(iG2);
            int iF4 = gVar.f() + iG2;
            do {
                b0Var.b(gVar.p());
            } while (gVar.f() < iF4);
            return;
        }
        if (i2 != 5) {
            throw d0.d();
        }
        do {
            b0Var.b(gVar.p());
            if (gVar.g()) {
                return;
            } else {
                iF2 = gVar.F();
            }
        } while (iF2 == this.b);
        this.d = iF2;
    }

    @Override // com.google.protobuf.z0
    public final int u() throws d0.a {
        T(0);
        return this.a.B();
    }

    @Override // com.google.protobuf.z0
    public final long v() throws d0.a {
        T(0);
        return this.a.C();
    }

    @Override // com.google.protobuf.z0
    public final void w(List<Boolean> list) throws d0 {
        int iF;
        int iF2;
        boolean z = list instanceof f;
        g gVar = this.a;
        if (!z) {
            int i = this.b & 7;
            if (i == 0) {
                do {
                    list.add(Boolean.valueOf(gVar.l()));
                    if (gVar.g()) {
                        return;
                    } else {
                        iF = gVar.F();
                    }
                } while (iF == this.b);
                this.d = iF;
                return;
            }
            if (i != 2) {
                throw d0.d();
            }
            int iF3 = gVar.f() + gVar.G();
            do {
                list.add(Boolean.valueOf(gVar.l()));
            } while (gVar.f() < iF3);
            S(iF3);
            return;
        }
        f fVar = (f) list;
        int i2 = this.b & 7;
        if (i2 == 0) {
            do {
                fVar.b(gVar.l());
                if (gVar.g()) {
                    return;
                } else {
                    iF2 = gVar.F();
                }
            } while (iF2 == this.b);
            this.d = iF2;
            return;
        }
        if (i2 != 2) {
            throw d0.d();
        }
        int iF4 = gVar.f() + gVar.G();
        do {
            fVar.b(gVar.l());
        } while (gVar.f() < iF4);
        S(iF4);
    }

    @Override // com.google.protobuf.z0
    public final String x() throws d0.a {
        T(2);
        return this.a.D();
    }

    @Override // com.google.protobuf.z0
    public final int y() {
        int i = this.d;
        if (i != 0) {
            this.b = i;
            this.d = 0;
        } else {
            this.b = this.a.F();
        }
        int i2 = this.b;
        if (i2 == 0 || i2 == this.c) {
            return Integer.MAX_VALUE;
        }
        return i2 >>> 3;
    }

    @Override // com.google.protobuf.z0
    public final void z(List<String> list) throws d0.a {
        R(list, false);
    }
}
