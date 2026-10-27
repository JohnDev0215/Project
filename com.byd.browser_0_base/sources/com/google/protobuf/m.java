package com.google.protobuf;

import com.google.protobuf.a;
import com.google.protobuf.a0;
import com.google.protobuf.c0;
import com.google.protobuf.j1;
import com.google.protobuf.n;
import com.google.protobuf.q0;
import com.google.protobuf.r0;
import defpackage.C0175b;
import defpackage.lk;
import defpackage.nu;
import defpackage.q61;
import defpackage.wv1;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: compiled from: DescriptorProtos.java */
/* JADX INFO: loaded from: classes.dex */
public final class m {
    public static final n.a A;
    public static final a0.e B;
    public static final n.a C;
    public static final a0.e D;
    public static final n.a E;
    public static final a0.e F;
    public static final n.a G;
    public static final a0.e H;
    public static final n.a I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public static final a0.e f38J;
    public static final n.a K;
    public static final a0.e L;
    public static final n.a M;
    public static final a0.e N;
    public static final n.a O;
    public static final a0.e P;
    public static final n.a Q;
    public static final a0.e R;
    public static final n.a S;
    public static final a0.e T;
    public static final n.a U;
    public static final a0.e V;
    public static final n.a a;
    public static final a0.e b;
    public static final n.a c;
    public static final a0.e d;
    public static final n.a e;
    public static final a0.e f;
    public static final n.a g;
    public static final a0.e h;
    public static final n.a i;
    public static final a0.e j;
    public static final n.a k;
    public static final a0.e l;
    public static final n.a m;
    public static final a0.e n;
    public static final n.a o;
    public static final a0.e p;
    public static final n.a q;
    public static final a0.e r;
    public static final n.a s;
    public static final a0.e t;
    public static final n.a u;
    public static final a0.e v;
    public static final n.a w;
    public static final a0.e x;
    public static final n.a y;
    public static final a0.e z;

    /* JADX INFO: compiled from: DescriptorProtos.java */
    public static final class a extends a0 implements t0 {
        private static final long serialVersionUID = 0;
        public int g;
        public volatile Object h;
        public List<g> i;
        public List<g> j;
        public List<a> k;
        public List<b> l;
        public List<c> m;
        public List<n> n;
        public k o;
        public List<d> p;
        public q61 q;
        public byte r;
        public static final a s = new a();

        @Deprecated
        public static final C0047a t = new C0047a();

        /* JADX INFO: renamed from: com.google.protobuf.m$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: DescriptorProtos.java */
        public static class C0047a extends com.google.protobuf.c<a> {
            @Override // defpackage.wv1
            public final Object b(com.google.protobuf.g gVar, com.google.protobuf.s sVar) {
                a aVar = new a();
                sVar.getClass();
                j1 j1Var = j1.f;
                j1.a aVar2 = new j1.a();
                boolean z = false;
                int i = 0;
                while (!z) {
                    try {
                        try {
                            int iF = gVar.F();
                            switch (iF) {
                                case 0:
                                    z = true;
                                    break;
                                case 10:
                                    lk.f fVarM = gVar.m();
                                    aVar.g = 1 | aVar.g;
                                    aVar.h = fVarM;
                                    break;
                                case 18:
                                    if ((i & 2) == 0) {
                                        aVar.i = new ArrayList();
                                        i |= 2;
                                    }
                                    aVar.i.add((g) gVar.v(g.u, sVar));
                                    break;
                                case 26:
                                    if ((i & 8) == 0) {
                                        aVar.k = new ArrayList();
                                        i |= 8;
                                    }
                                    aVar.k.add((a) gVar.v(a.t, sVar));
                                    break;
                                case 34:
                                    if ((i & 16) == 0) {
                                        aVar.l = new ArrayList();
                                        i |= 16;
                                    }
                                    aVar.l.add((b) gVar.v(b.o, sVar));
                                    break;
                                case 42:
                                    if ((i & 32) == 0) {
                                        aVar.m = new ArrayList();
                                        i |= 32;
                                    }
                                    aVar.m.add((c) gVar.v(c.m, sVar));
                                    break;
                                case 50:
                                    if ((i & 4) == 0) {
                                        aVar.j = new ArrayList();
                                        i |= 4;
                                    }
                                    aVar.j.add((g) gVar.v(g.u, sVar));
                                    break;
                                case 58:
                                    k.b bVarH = (aVar.g & 2) != 0 ? aVar.o.h() : null;
                                    k kVar = (k) gVar.v(k.p, sVar);
                                    aVar.o = kVar;
                                    if (bVarH != null) {
                                        bVarH.Q(kVar);
                                        aVar.o = bVarH.d();
                                    }
                                    aVar.g |= 2;
                                    break;
                                case 66:
                                    if ((i & 64) == 0) {
                                        aVar.n = new ArrayList();
                                        i |= 64;
                                    }
                                    aVar.n.add((n) gVar.v(n.l, sVar));
                                    break;
                                case 74:
                                    if ((i & 256) == 0) {
                                        aVar.p = new ArrayList();
                                        i |= 256;
                                    }
                                    aVar.p.add((d) gVar.v(d.l, sVar));
                                    break;
                                case 82:
                                    lk.f fVarM2 = gVar.m();
                                    if ((i & 512) == 0) {
                                        aVar.q = new g0();
                                        i |= 512;
                                    }
                                    aVar.q.l(fVarM2);
                                    break;
                                default:
                                    if (!aVar2.r(iF, gVar)) {
                                        z = true;
                                    }
                                    break;
                            }
                        } catch (d0 e) {
                            e.e = aVar;
                            throw e;
                        } catch (IOException e2) {
                            d0 d0Var = new d0(e2);
                            d0Var.e = aVar;
                            throw d0Var;
                        }
                    } catch (Throwable th) {
                        if ((i & 2) != 0) {
                            aVar.i = Collections.unmodifiableList(aVar.i);
                        }
                        if ((i & 8) != 0) {
                            aVar.k = Collections.unmodifiableList(aVar.k);
                        }
                        if ((i & 16) != 0) {
                            aVar.l = Collections.unmodifiableList(aVar.l);
                        }
                        if ((i & 32) != 0) {
                            aVar.m = Collections.unmodifiableList(aVar.m);
                        }
                        if ((i & 4) != 0) {
                            aVar.j = Collections.unmodifiableList(aVar.j);
                        }
                        if ((i & 64) != 0) {
                            aVar.n = Collections.unmodifiableList(aVar.n);
                        }
                        if ((i & 256) != 0) {
                            aVar.p = Collections.unmodifiableList(aVar.p);
                        }
                        if ((i & 512) != 0) {
                            aVar.q = aVar.q.m();
                        }
                        aVar.f = aVar2.build();
                        throw th;
                    }
                }
                if ((i & 2) != 0) {
                    aVar.i = Collections.unmodifiableList(aVar.i);
                }
                if ((i & 8) != 0) {
                    aVar.k = Collections.unmodifiableList(aVar.k);
                }
                if ((i & 16) != 0) {
                    aVar.l = Collections.unmodifiableList(aVar.l);
                }
                if ((i & 32) != 0) {
                    aVar.m = Collections.unmodifiableList(aVar.m);
                }
                if ((i & 4) != 0) {
                    aVar.j = Collections.unmodifiableList(aVar.j);
                }
                if ((i & 64) != 0) {
                    aVar.n = Collections.unmodifiableList(aVar.n);
                }
                if ((i & 256) != 0) {
                    aVar.p = Collections.unmodifiableList(aVar.p);
                }
                if ((i & 512) != 0) {
                    aVar.q = aVar.q.m();
                }
                aVar.f = aVar2.build();
                return aVar;
            }
        }

        /* JADX INFO: compiled from: DescriptorProtos.java */
        public static final class b extends a0.a<b> implements t0 {
            public int g;
            public k o;
            public Object h = "";
            public List<g> i = Collections.emptyList();
            public List<g> j = Collections.emptyList();
            public List<a> k = Collections.emptyList();
            public List<b> l = Collections.emptyList();
            public List<c> m = Collections.emptyList();
            public List<n> n = Collections.emptyList();
            public List<d> p = Collections.emptyList();
            public q61 q = g0.g;

            @Override // com.google.protobuf.a0.a
            /* JADX INFO: renamed from: A */
            public final a0.a f(n.f fVar, Object obj) {
                super.f(fVar, obj);
                return this;
            }

            @Override // com.google.protobuf.a0.a
            /* JADX INFO: renamed from: B */
            public final a0.a clone() {
                return (b) super.clone();
            }

            @Override // com.google.protobuf.a0.a
            public final a0.e D() {
                a0.e eVar = m.d;
                eVar.c(a.class, b.class);
                return eVar;
            }

            @Override // com.google.protobuf.a0.a
            /* JADX INFO: renamed from: E */
            public final a0.a y(j1 j1Var) {
                return (b) super.y(j1Var);
            }

            @Override // com.google.protobuf.a0.a
            /* JADX INFO: renamed from: I */
            public final a0.a a(n.f fVar, Object obj) {
                super.a(fVar, obj);
                return this;
            }

            @Override // com.google.protobuf.a0.a
            /* JADX INFO: renamed from: J */
            public final a0.a j0(j1 j1Var) {
                this.f = j1Var;
                H();
                return this;
            }

            @Override // com.google.protobuf.r0.a, com.google.protobuf.q0.a
            /* JADX INFO: renamed from: K, reason: merged with bridge method [inline-methods] */
            public final a d() {
                a aVar = new a(this);
                int i = this.g;
                int i2 = (i & 1) != 0 ? 1 : 0;
                aVar.h = this.h;
                if ((this.g & 2) != 0) {
                    this.i = Collections.unmodifiableList(this.i);
                    this.g &= -3;
                }
                aVar.i = this.i;
                if ((this.g & 4) != 0) {
                    this.j = Collections.unmodifiableList(this.j);
                    this.g &= -5;
                }
                aVar.j = this.j;
                if ((this.g & 8) != 0) {
                    this.k = Collections.unmodifiableList(this.k);
                    this.g &= -9;
                }
                aVar.k = this.k;
                if ((this.g & 16) != 0) {
                    this.l = Collections.unmodifiableList(this.l);
                    this.g &= -17;
                }
                aVar.l = this.l;
                if ((this.g & 32) != 0) {
                    this.m = Collections.unmodifiableList(this.m);
                    this.g &= -33;
                }
                aVar.m = this.m;
                if ((this.g & 64) != 0) {
                    this.n = Collections.unmodifiableList(this.n);
                    this.g &= -65;
                }
                aVar.n = this.n;
                if ((i & 128) != 0) {
                    aVar.o = this.o;
                    i2 |= 2;
                }
                if ((this.g & 256) != 0) {
                    this.p = Collections.unmodifiableList(this.p);
                    this.g &= -257;
                }
                aVar.p = this.p;
                if ((this.g & 512) != 0) {
                    this.q = this.q.m();
                    this.g &= -513;
                }
                aVar.q = this.q;
                aVar.g = i2;
                F();
                return aVar;
            }

            @Override // com.google.protobuf.a.AbstractC0042a, com.google.protobuf.r0.a
            public final /* bridge */ /* synthetic */ r0.a L(com.google.protobuf.g gVar, com.google.protobuf.s sVar) throws Throwable {
                M(gVar, sVar);
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:15:0x001d  */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public final com.google.protobuf.m.a.b M(com.google.protobuf.g r2, com.google.protobuf.s r3) throws java.lang.Throwable {
                /*
                    r1 = this;
                    com.google.protobuf.m$a$a r0 = com.google.protobuf.m.a.t     // Catch: java.lang.Throwable -> Lc com.google.protobuf.d0 -> Le
                    java.lang.Object r2 = r0.b(r2, r3)     // Catch: java.lang.Throwable -> Lc com.google.protobuf.d0 -> Le
                    com.google.protobuf.m$a r2 = (com.google.protobuf.m.a) r2     // Catch: java.lang.Throwable -> Lc com.google.protobuf.d0 -> Le
                    r1.N(r2)
                    return r1
                Lc:
                    r2 = move-exception
                    goto L1a
                Le:
                    r2 = move-exception
                    com.google.protobuf.r0 r3 = r2.e     // Catch: java.lang.Throwable -> Lc
                    com.google.protobuf.m$a r3 = (com.google.protobuf.m.a) r3     // Catch: java.lang.Throwable -> Lc
                    java.io.IOException r2 = r2.l()     // Catch: java.lang.Throwable -> L18
                    throw r2     // Catch: java.lang.Throwable -> L18
                L18:
                    r2 = move-exception
                    goto L1b
                L1a:
                    r3 = 0
                L1b:
                    if (r3 == 0) goto L20
                    r1.N(r3)
                L20:
                    throw r2
                */
                throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.m.a.b.M(com.google.protobuf.g, com.google.protobuf.s):com.google.protobuf.m$a$b");
            }

            public final void N(a aVar) {
                k kVar;
                k kVar2;
                if (aVar == a.s) {
                    return;
                }
                if ((aVar.g & 1) != 0) {
                    this.g |= 1;
                    this.h = aVar.h;
                    H();
                }
                if (!aVar.i.isEmpty()) {
                    if (this.i.isEmpty()) {
                        this.i = aVar.i;
                        this.g &= -3;
                    } else {
                        if ((this.g & 2) == 0) {
                            this.i = new ArrayList(this.i);
                            this.g |= 2;
                        }
                        this.i.addAll(aVar.i);
                    }
                    H();
                }
                if (!aVar.j.isEmpty()) {
                    if (this.j.isEmpty()) {
                        this.j = aVar.j;
                        this.g &= -5;
                    } else {
                        if ((this.g & 4) == 0) {
                            this.j = new ArrayList(this.j);
                            this.g |= 4;
                        }
                        this.j.addAll(aVar.j);
                    }
                    H();
                }
                if (!aVar.k.isEmpty()) {
                    if (this.k.isEmpty()) {
                        this.k = aVar.k;
                        this.g &= -9;
                    } else {
                        if ((this.g & 8) == 0) {
                            this.k = new ArrayList(this.k);
                            this.g |= 8;
                        }
                        this.k.addAll(aVar.k);
                    }
                    H();
                }
                if (!aVar.l.isEmpty()) {
                    if (this.l.isEmpty()) {
                        this.l = aVar.l;
                        this.g &= -17;
                    } else {
                        if ((this.g & 16) == 0) {
                            this.l = new ArrayList(this.l);
                            this.g |= 16;
                        }
                        this.l.addAll(aVar.l);
                    }
                    H();
                }
                if (!aVar.m.isEmpty()) {
                    if (this.m.isEmpty()) {
                        this.m = aVar.m;
                        this.g &= -33;
                    } else {
                        if ((this.g & 32) == 0) {
                            this.m = new ArrayList(this.m);
                            this.g |= 32;
                        }
                        this.m.addAll(aVar.m);
                    }
                    H();
                }
                if (!aVar.n.isEmpty()) {
                    if (this.n.isEmpty()) {
                        this.n = aVar.n;
                        this.g &= -65;
                    } else {
                        if ((this.g & 64) == 0) {
                            this.n = new ArrayList(this.n);
                            this.g |= 64;
                        }
                        this.n.addAll(aVar.n);
                    }
                    H();
                }
                if (aVar.V()) {
                    k kVarU = aVar.U();
                    if ((this.g & 128) == 0 || (kVar = this.o) == null || kVar == (kVar2 = k.o)) {
                        this.o = kVarU;
                    } else {
                        k.b bVarH = kVar2.h();
                        bVarH.Q(kVar);
                        bVarH.Q(kVarU);
                        this.o = bVarH.d();
                    }
                    H();
                    this.g |= 128;
                }
                if (!aVar.p.isEmpty()) {
                    if (this.p.isEmpty()) {
                        this.p = aVar.p;
                        this.g &= -257;
                    } else {
                        if ((this.g & 256) == 0) {
                            this.p = new ArrayList(this.p);
                            this.g |= 256;
                        }
                        this.p.addAll(aVar.p);
                    }
                    H();
                }
                if (!aVar.q.isEmpty()) {
                    if (this.q.isEmpty()) {
                        this.q = aVar.q;
                        this.g &= -513;
                    } else {
                        if ((this.g & 512) == 0) {
                            this.q = new g0(this.q);
                            this.g |= 512;
                        }
                        this.q.addAll(aVar.q);
                    }
                    H();
                }
                H();
            }

            @Override // com.google.protobuf.a.AbstractC0042a, com.google.protobuf.q0.a
            public final q0.a R(q0 q0Var) {
                if (q0Var instanceof a) {
                    N((a) q0Var);
                } else {
                    super.R(q0Var);
                }
                return this;
            }

            @Override // com.google.protobuf.a0.a, com.google.protobuf.q0.a
            public final q0.a a(n.f fVar, Object obj) {
                super.a(fVar, obj);
                return this;
            }

            @Override // defpackage.bh1, com.google.protobuf.t0
            public final q0 b() {
                return a.s;
            }

            @Override // com.google.protobuf.r0.a, com.google.protobuf.q0.a
            public final q0 build() {
                a aVarD = d();
                if (aVarD.isInitialized()) {
                    return aVarD;
                }
                throw a.AbstractC0042a.z(aVarD);
            }

            @Override // com.google.protobuf.a0.a, com.google.protobuf.q0.a, com.google.protobuf.t0
            public final n.a c() {
                return m.c;
            }

            @Override // com.google.protobuf.a0.a
            public final Object clone() {
                return (b) super.clone();
            }

            @Override // com.google.protobuf.a0.a, com.google.protobuf.q0.a
            public final q0.a f(n.f fVar, Object obj) {
                super.f(fVar, obj);
                return this;
            }

            @Override // com.google.protobuf.a0.a, com.google.protobuf.q0.a
            public final q0.a j0(j1 j1Var) {
                this.f = j1Var;
                H();
                return this;
            }

            @Override // com.google.protobuf.a.AbstractC0042a
            /* JADX INFO: renamed from: v */
            public final /* bridge */ /* synthetic */ a.AbstractC0042a L(com.google.protobuf.g gVar, com.google.protobuf.s sVar) throws Throwable {
                M(gVar, sVar);
                return this;
            }

            @Override // com.google.protobuf.a.AbstractC0042a
            /* JADX INFO: renamed from: x */
            public final a.AbstractC0042a R(q0 q0Var) {
                if (q0Var instanceof a) {
                    N((a) q0Var);
                } else {
                    super.R(q0Var);
                }
                return this;
            }

            @Override // com.google.protobuf.a0.a, com.google.protobuf.a.AbstractC0042a
            public final void y(j1 j1Var) {
            }

            @Override // defpackage.bh1, com.google.protobuf.t0
            public final r0 b() {
                return a.s;
            }

            @Override // com.google.protobuf.r0.a, com.google.protobuf.q0.a
            public final r0 build() {
                a aVarD = d();
                if (aVarD.isInitialized()) {
                    return aVarD;
                }
                throw a.AbstractC0042a.z(aVarD);
            }
        }

        /* JADX INFO: compiled from: DescriptorProtos.java */
        public static final class c extends a0 implements t0 {
            public static final c l = new c();

            @Deprecated
            public static final C0048a m = new C0048a();
            private static final long serialVersionUID = 0;
            public int g;
            public int h;
            public int i;
            public f j;
            public byte k;

            /* JADX INFO: renamed from: com.google.protobuf.m$a$c$a, reason: collision with other inner class name */
            /* JADX INFO: compiled from: DescriptorProtos.java */
            public static class C0048a extends com.google.protobuf.c<c> {
                @Override // defpackage.wv1
                public final Object b(com.google.protobuf.g gVar, com.google.protobuf.s sVar) {
                    c cVar = new c();
                    sVar.getClass();
                    j1 j1Var = j1.f;
                    j1.a aVar = new j1.a();
                    boolean z = false;
                    while (!z) {
                        try {
                            try {
                                int iF = gVar.F();
                                if (iF != 0) {
                                    if (iF == 8) {
                                        cVar.g |= 1;
                                        cVar.h = gVar.t();
                                    } else if (iF == 16) {
                                        cVar.g |= 2;
                                        cVar.i = gVar.t();
                                    } else if (iF == 26) {
                                        f.b bVarH = (cVar.g & 4) != 0 ? cVar.j.h() : null;
                                        f fVar = (f) gVar.v(f.k, sVar);
                                        cVar.j = fVar;
                                        if (bVarH != null) {
                                            bVarH.Q(fVar);
                                            cVar.j = bVarH.d();
                                        }
                                        cVar.g |= 4;
                                    } else if (!aVar.r(iF, gVar)) {
                                    }
                                }
                                z = true;
                            } catch (d0 e) {
                                e.e = cVar;
                                throw e;
                            } catch (IOException e2) {
                                d0 d0Var = new d0(e2);
                                d0Var.e = cVar;
                                throw d0Var;
                            }
                        } catch (Throwable th) {
                            cVar.f = aVar.build();
                            throw th;
                        }
                    }
                    cVar.f = aVar.build();
                    return cVar;
                }
            }

            /* JADX INFO: compiled from: DescriptorProtos.java */
            public static final class b extends a0.a<b> implements t0 {
                public int g;
                public int h;
                public int i;
                public f j;

                @Override // com.google.protobuf.a0.a
                /* JADX INFO: renamed from: A */
                public final a0.a f(n.f fVar, Object obj) {
                    super.f(fVar, obj);
                    return this;
                }

                @Override // com.google.protobuf.a0.a
                /* JADX INFO: renamed from: B */
                public final a0.a clone() {
                    return (b) super.clone();
                }

                @Override // com.google.protobuf.a0.a
                public final a0.e D() {
                    a0.e eVar = m.f;
                    eVar.c(c.class, b.class);
                    return eVar;
                }

                @Override // com.google.protobuf.a0.a
                /* JADX INFO: renamed from: E */
                public final a0.a y(j1 j1Var) {
                    return (b) super.y(j1Var);
                }

                @Override // com.google.protobuf.a0.a
                /* JADX INFO: renamed from: I */
                public final a0.a a(n.f fVar, Object obj) {
                    super.a(fVar, obj);
                    return this;
                }

                @Override // com.google.protobuf.a0.a
                /* JADX INFO: renamed from: J */
                public final a0.a j0(j1 j1Var) {
                    this.f = j1Var;
                    H();
                    return this;
                }

                @Override // com.google.protobuf.r0.a, com.google.protobuf.q0.a
                /* JADX INFO: renamed from: K, reason: merged with bridge method [inline-methods] */
                public final c d() {
                    int i;
                    c cVar = new c(this);
                    int i2 = this.g;
                    if ((i2 & 1) != 0) {
                        cVar.h = this.h;
                        i = 1;
                    } else {
                        i = 0;
                    }
                    if ((i2 & 2) != 0) {
                        cVar.i = this.i;
                        i |= 2;
                    }
                    if ((i2 & 4) != 0) {
                        cVar.j = this.j;
                        i |= 4;
                    }
                    cVar.g = i;
                    F();
                    return cVar;
                }

                @Override // com.google.protobuf.a.AbstractC0042a, com.google.protobuf.r0.a
                public final /* bridge */ /* synthetic */ r0.a L(com.google.protobuf.g gVar, com.google.protobuf.s sVar) throws Throwable {
                    L(gVar, sVar);
                    return this;
                }

                /* JADX WARN: Removed duplicated region for block: B:15:0x001d  */
                @Override // com.google.protobuf.a.AbstractC0042a
                /* JADX INFO: renamed from: M, reason: merged with bridge method [inline-methods] */
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct code enable 'Show inconsistent code' option in preferences
                */
                public final com.google.protobuf.m.a.c.b L(com.google.protobuf.g r2, com.google.protobuf.s r3) throws java.lang.Throwable {
                    /*
                        r1 = this;
                        com.google.protobuf.m$a$c$a r0 = com.google.protobuf.m.a.c.m     // Catch: java.lang.Throwable -> Lc com.google.protobuf.d0 -> Le
                        java.lang.Object r2 = r0.b(r2, r3)     // Catch: java.lang.Throwable -> Lc com.google.protobuf.d0 -> Le
                        com.google.protobuf.m$a$c r2 = (com.google.protobuf.m.a.c) r2     // Catch: java.lang.Throwable -> Lc com.google.protobuf.d0 -> Le
                        r1.N(r2)
                        return r1
                    Lc:
                        r2 = move-exception
                        goto L1a
                    Le:
                        r2 = move-exception
                        com.google.protobuf.r0 r3 = r2.e     // Catch: java.lang.Throwable -> Lc
                        com.google.protobuf.m$a$c r3 = (com.google.protobuf.m.a.c) r3     // Catch: java.lang.Throwable -> Lc
                        java.io.IOException r2 = r2.l()     // Catch: java.lang.Throwable -> L18
                        throw r2     // Catch: java.lang.Throwable -> L18
                    L18:
                        r2 = move-exception
                        goto L1b
                    L1a:
                        r3 = 0
                    L1b:
                        if (r3 == 0) goto L20
                        r1.N(r3)
                    L20:
                        throw r2
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.m.a.c.b.L(com.google.protobuf.g, com.google.protobuf.s):com.google.protobuf.m$a$c$b");
                }

                public final b N(c cVar) {
                    f fVar;
                    f fVar2;
                    if (cVar == c.l) {
                        return this;
                    }
                    if ((cVar.g & 1) != 0) {
                        int i = cVar.h;
                        this.g |= 1;
                        this.h = i;
                        H();
                    }
                    if ((cVar.g & 2) != 0) {
                        int i2 = cVar.i;
                        this.g |= 2;
                        this.i = i2;
                        H();
                    }
                    if (cVar.S()) {
                        f fVarQ = cVar.Q();
                        if ((this.g & 4) == 0 || (fVar = this.j) == null || fVar == (fVar2 = f.j)) {
                            this.j = fVarQ;
                        } else {
                            f.b bVarH = fVar2.h();
                            bVarH.Q(fVar);
                            bVarH.Q(fVarQ);
                            this.j = bVarH.d();
                        }
                        H();
                        this.g |= 4;
                    }
                    H();
                    return this;
                }

                @Override // com.google.protobuf.a.AbstractC0042a, com.google.protobuf.q0.a
                public final q0.a R(q0 q0Var) {
                    if (q0Var instanceof c) {
                        N((c) q0Var);
                    } else {
                        super.R(q0Var);
                    }
                    return this;
                }

                @Override // com.google.protobuf.a0.a, com.google.protobuf.q0.a
                public final q0.a a(n.f fVar, Object obj) {
                    super.a(fVar, obj);
                    return this;
                }

                @Override // defpackage.bh1, com.google.protobuf.t0
                public final q0 b() {
                    return c.l;
                }

                @Override // com.google.protobuf.r0.a, com.google.protobuf.q0.a
                public final q0 build() {
                    c cVarD = d();
                    if (cVarD.isInitialized()) {
                        return cVarD;
                    }
                    throw a.AbstractC0042a.z(cVarD);
                }

                @Override // com.google.protobuf.a0.a, com.google.protobuf.q0.a, com.google.protobuf.t0
                public final n.a c() {
                    return m.e;
                }

                @Override // com.google.protobuf.a0.a
                public final Object clone() {
                    return (b) super.clone();
                }

                @Override // com.google.protobuf.a0.a, com.google.protobuf.q0.a
                public final q0.a f(n.f fVar, Object obj) {
                    super.f(fVar, obj);
                    return this;
                }

                @Override // com.google.protobuf.a0.a, com.google.protobuf.q0.a
                public final q0.a j0(j1 j1Var) {
                    this.f = j1Var;
                    H();
                    return this;
                }

                @Override // com.google.protobuf.a.AbstractC0042a
                /* JADX INFO: renamed from: x */
                public final a.AbstractC0042a R(q0 q0Var) {
                    if (q0Var instanceof c) {
                        N((c) q0Var);
                    } else {
                        super.R(q0Var);
                    }
                    return this;
                }

                @Override // com.google.protobuf.a0.a, com.google.protobuf.a.AbstractC0042a
                public final void y(j1 j1Var) {
                }

                @Override // defpackage.bh1, com.google.protobuf.t0
                public final r0 b() {
                    return c.l;
                }

                @Override // com.google.protobuf.r0.a, com.google.protobuf.q0.a
                public final r0 build() {
                    c cVarD = d();
                    if (cVarD.isInitialized()) {
                        return cVarD;
                    }
                    throw a.AbstractC0042a.z(cVarD);
                }
            }

            public c(a0.a aVar) {
                super(aVar);
                this.k = (byte) -1;
            }

            @Override // com.google.protobuf.a0
            public final a0.e M() {
                a0.e eVar = m.f;
                eVar.c(c.class, b.class);
                return eVar;
            }

            @Override // com.google.protobuf.a0
            public final Object O() {
                return new c();
            }

            public final f Q() {
                f fVar = this.j;
                return fVar == null ? f.j : fVar;
            }

            public final boolean S() {
                return (this.g & 4) != 0;
            }

            @Override // com.google.protobuf.r0
            /* JADX INFO: renamed from: T, reason: merged with bridge method [inline-methods] */
            public final b h() {
                if (this == l) {
                    return new b();
                }
                b bVar = new b();
                bVar.N(this);
                return bVar;
            }

            @Override // defpackage.bh1, com.google.protobuf.t0
            public final q0 b() {
                return l;
            }

            @Override // com.google.protobuf.r0, com.google.protobuf.q0
            public final q0.a e() {
                return l.h();
            }

            @Override // com.google.protobuf.a
            public final boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof c)) {
                    return super.equals(obj);
                }
                c cVar = (c) obj;
                int i = this.g;
                boolean z = (i & 1) != 0;
                int i2 = cVar.g;
                if (z != ((i2 & 1) != 0)) {
                    return false;
                }
                if (((i & 1) != 0) && this.h != cVar.h) {
                    return false;
                }
                if (((i & 2) != 0) != ((i2 & 2) != 0)) {
                    return false;
                }
                if ((!((i & 2) != 0) || this.i == cVar.i) && S() == cVar.S()) {
                    return (!S() || Q().equals(cVar.Q())) && this.f.equals(cVar.f);
                }
                return false;
            }

            @Override // com.google.protobuf.a
            public final int hashCode() {
                int i = this.memoizedHashCode;
                if (i != 0) {
                    return i;
                }
                int iHashCode = m.e.hashCode() + 779;
                int i2 = this.g;
                if ((i2 & 1) != 0) {
                    iHashCode = C0175b.e(iHashCode, 37, 1, 53) + this.h;
                }
                if ((i2 & 2) != 0) {
                    iHashCode = C0175b.e(iHashCode, 37, 2, 53) + this.i;
                }
                if (S()) {
                    iHashCode = C0175b.e(iHashCode, 37, 3, 53) + Q().hashCode();
                }
                int iHashCode2 = this.f.hashCode() + (iHashCode * 29);
                this.memoizedHashCode = iHashCode2;
                return iHashCode2;
            }

            @Override // com.google.protobuf.a0, defpackage.bh1
            public final boolean isInitialized() {
                byte b2 = this.k;
                if (b2 == 1) {
                    return true;
                }
                if (b2 == 0) {
                    return false;
                }
                if (!S() || Q().isInitialized()) {
                    this.k = (byte) 1;
                    return true;
                }
                this.k = (byte) 0;
                return false;
            }

            @Override // com.google.protobuf.a0, com.google.protobuf.r0
            public final int m() {
                int i = this.e;
                if (i != -1) {
                    return i;
                }
                int iS = (this.g & 1) != 0 ? 0 + nu.S(1, this.h) : 0;
                if ((this.g & 2) != 0) {
                    iS += nu.S(2, this.i);
                }
                if ((this.g & 4) != 0) {
                    iS += nu.a0(3, Q());
                }
                int iM = this.f.m() + iS;
                this.e = iM;
                return iM;
            }

            @Override // com.google.protobuf.a0, com.google.protobuf.t0
            public final j1 n() {
                return this.f;
            }

            @Override // com.google.protobuf.a0, com.google.protobuf.r0
            public final void p(nu nuVar) {
                if ((this.g & 1) != 0) {
                    nuVar.z0(1, this.h);
                }
                if ((this.g & 2) != 0) {
                    nuVar.z0(2, this.i);
                }
                if ((this.g & 4) != 0) {
                    nuVar.D0(3, Q());
                }
                this.f.p(nuVar);
            }

            @Override // com.google.protobuf.a0, com.google.protobuf.r0
            public final wv1<c> u() {
                return m;
            }

            @Override // defpackage.bh1, com.google.protobuf.t0
            public final r0 b() {
                return l;
            }

            @Override // com.google.protobuf.r0, com.google.protobuf.q0
            public final r0.a e() {
                return l.h();
            }

            public c() {
                this.k = (byte) -1;
            }
        }

        /* JADX INFO: compiled from: DescriptorProtos.java */
        public static final class d extends a0 implements t0 {
            public static final d k = new d();

            @Deprecated
            public static final C0049a l = new C0049a();
            private static final long serialVersionUID = 0;
            public int g;
            public int h;
            public int i;
            public byte j;

            /* JADX INFO: renamed from: com.google.protobuf.m$a$d$a, reason: collision with other inner class name */
            /* JADX INFO: compiled from: DescriptorProtos.java */
            public static class C0049a extends com.google.protobuf.c<d> {
                @Override // defpackage.wv1
                public final Object b(com.google.protobuf.g gVar, com.google.protobuf.s sVar) {
                    d dVar = new d();
                    sVar.getClass();
                    j1 j1Var = j1.f;
                    j1.a aVar = new j1.a();
                    boolean z = false;
                    while (!z) {
                        try {
                            try {
                                int iF = gVar.F();
                                if (iF != 0) {
                                    if (iF == 8) {
                                        dVar.g |= 1;
                                        dVar.h = gVar.t();
                                    } else if (iF == 16) {
                                        dVar.g |= 2;
                                        dVar.i = gVar.t();
                                    } else if (!aVar.r(iF, gVar)) {
                                    }
                                }
                                z = true;
                            } catch (d0 e) {
                                e.e = dVar;
                                throw e;
                            } catch (IOException e2) {
                                d0 d0Var = new d0(e2);
                                d0Var.e = dVar;
                                throw d0Var;
                            }
                        } finally {
                            dVar.f = aVar.build();
                        }
                    }
                    return dVar;
                }
            }

            /* JADX INFO: compiled from: DescriptorProtos.java */
            public static final class b extends a0.a<b> implements t0 {
                public int g;
                public int h;
                public int i;

                @Override // com.google.protobuf.a0.a
                /* JADX INFO: renamed from: A */
                public final a0.a f(n.f fVar, Object obj) {
                    super.f(fVar, obj);
                    return this;
                }

                @Override // com.google.protobuf.a0.a
                /* JADX INFO: renamed from: B */
                public final a0.a clone() {
                    return (b) super.clone();
                }

                @Override // com.google.protobuf.a0.a
                public final a0.e D() {
                    a0.e eVar = m.h;
                    eVar.c(d.class, b.class);
                    return eVar;
                }

                @Override // com.google.protobuf.a0.a
                /* JADX INFO: renamed from: E */
                public final a0.a y(j1 j1Var) {
                    return (b) super.y(j1Var);
                }

                @Override // com.google.protobuf.a0.a
                /* JADX INFO: renamed from: I */
                public final a0.a a(n.f fVar, Object obj) {
                    super.a(fVar, obj);
                    return this;
                }

                @Override // com.google.protobuf.a0.a
                /* JADX INFO: renamed from: J */
                public final a0.a j0(j1 j1Var) {
                    this.f = j1Var;
                    H();
                    return this;
                }

                @Override // com.google.protobuf.r0.a, com.google.protobuf.q0.a
                /* JADX INFO: renamed from: K, reason: merged with bridge method [inline-methods] */
                public final d d() {
                    int i;
                    d dVar = new d(this);
                    int i2 = this.g;
                    if ((i2 & 1) != 0) {
                        dVar.h = this.h;
                        i = 1;
                    } else {
                        i = 0;
                    }
                    if ((i2 & 2) != 0) {
                        dVar.i = this.i;
                        i |= 2;
                    }
                    dVar.g = i;
                    F();
                    return dVar;
                }

                @Override // com.google.protobuf.a.AbstractC0042a, com.google.protobuf.r0.a
                public final /* bridge */ /* synthetic */ r0.a L(com.google.protobuf.g gVar, com.google.protobuf.s sVar) throws Throwable {
                    M(gVar, sVar);
                    return this;
                }

                /* JADX WARN: Removed duplicated region for block: B:15:0x001d  */
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct code enable 'Show inconsistent code' option in preferences
                */
                public final void M(com.google.protobuf.g r2, com.google.protobuf.s r3) throws java.lang.Throwable {
                    /*
                        r1 = this;
                        com.google.protobuf.m$a$d$a r0 = com.google.protobuf.m.a.d.l     // Catch: java.lang.Throwable -> Lc com.google.protobuf.d0 -> Le
                        java.lang.Object r2 = r0.b(r2, r3)     // Catch: java.lang.Throwable -> Lc com.google.protobuf.d0 -> Le
                        com.google.protobuf.m$a$d r2 = (com.google.protobuf.m.a.d) r2     // Catch: java.lang.Throwable -> Lc com.google.protobuf.d0 -> Le
                        r1.N(r2)
                        return
                    Lc:
                        r2 = move-exception
                        goto L1a
                    Le:
                        r2 = move-exception
                        com.google.protobuf.r0 r3 = r2.e     // Catch: java.lang.Throwable -> Lc
                        com.google.protobuf.m$a$d r3 = (com.google.protobuf.m.a.d) r3     // Catch: java.lang.Throwable -> Lc
                        java.io.IOException r2 = r2.l()     // Catch: java.lang.Throwable -> L18
                        throw r2     // Catch: java.lang.Throwable -> L18
                    L18:
                        r2 = move-exception
                        goto L1b
                    L1a:
                        r3 = 0
                    L1b:
                        if (r3 == 0) goto L20
                        r1.N(r3)
                    L20:
                        throw r2
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.m.a.d.b.M(com.google.protobuf.g, com.google.protobuf.s):void");
                }

                public final void N(d dVar) {
                    if (dVar == d.k) {
                        return;
                    }
                    if ((dVar.g & 1) != 0) {
                        int i = dVar.h;
                        this.g |= 1;
                        this.h = i;
                        H();
                    }
                    if ((dVar.g & 2) != 0) {
                        int i2 = dVar.i;
                        this.g |= 2;
                        this.i = i2;
                        H();
                    }
                    H();
                }

                @Override // com.google.protobuf.a.AbstractC0042a, com.google.protobuf.q0.a
                public final q0.a R(q0 q0Var) {
                    if (q0Var instanceof d) {
                        N((d) q0Var);
                    } else {
                        super.R(q0Var);
                    }
                    return this;
                }

                @Override // com.google.protobuf.a0.a, com.google.protobuf.q0.a
                public final q0.a a(n.f fVar, Object obj) {
                    super.a(fVar, obj);
                    return this;
                }

                @Override // defpackage.bh1, com.google.protobuf.t0
                public final q0 b() {
                    return d.k;
                }

                @Override // com.google.protobuf.r0.a, com.google.protobuf.q0.a
                public final q0 build() {
                    d dVarD = d();
                    if (dVarD.isInitialized()) {
                        return dVarD;
                    }
                    throw a.AbstractC0042a.z(dVarD);
                }

                @Override // com.google.protobuf.a0.a, com.google.protobuf.q0.a, com.google.protobuf.t0
                public final n.a c() {
                    return m.g;
                }

                @Override // com.google.protobuf.a0.a
                public final Object clone() {
                    return (b) super.clone();
                }

                @Override // com.google.protobuf.a0.a, com.google.protobuf.q0.a
                public final q0.a f(n.f fVar, Object obj) {
                    super.f(fVar, obj);
                    return this;
                }

                @Override // com.google.protobuf.a0.a, com.google.protobuf.q0.a
                public final q0.a j0(j1 j1Var) {
                    this.f = j1Var;
                    H();
                    return this;
                }

                @Override // com.google.protobuf.a.AbstractC0042a
                /* JADX INFO: renamed from: v */
                public final /* bridge */ /* synthetic */ a.AbstractC0042a L(com.google.protobuf.g gVar, com.google.protobuf.s sVar) throws Throwable {
                    M(gVar, sVar);
                    return this;
                }

                @Override // com.google.protobuf.a.AbstractC0042a
                /* JADX INFO: renamed from: x */
                public final a.AbstractC0042a R(q0 q0Var) {
                    if (q0Var instanceof d) {
                        N((d) q0Var);
                    } else {
                        super.R(q0Var);
                    }
                    return this;
                }

                @Override // com.google.protobuf.a0.a, com.google.protobuf.a.AbstractC0042a
                public final void y(j1 j1Var) {
                }

                @Override // defpackage.bh1, com.google.protobuf.t0
                public final r0 b() {
                    return d.k;
                }

                @Override // com.google.protobuf.r0.a, com.google.protobuf.q0.a
                public final r0 build() {
                    d dVarD = d();
                    if (dVarD.isInitialized()) {
                        return dVarD;
                    }
                    throw a.AbstractC0042a.z(dVarD);
                }
            }

            public d(a0.a aVar) {
                super(aVar);
                this.j = (byte) -1;
            }

            @Override // com.google.protobuf.a0
            public final a0.e M() {
                a0.e eVar = m.h;
                eVar.c(d.class, b.class);
                return eVar;
            }

            @Override // com.google.protobuf.a0
            public final Object O() {
                return new d();
            }

            @Override // com.google.protobuf.r0
            /* JADX INFO: renamed from: Q, reason: merged with bridge method [inline-methods] */
            public final b h() {
                if (this == k) {
                    return new b();
                }
                b bVar = new b();
                bVar.N(this);
                return bVar;
            }

            @Override // defpackage.bh1, com.google.protobuf.t0
            public final q0 b() {
                return k;
            }

            @Override // com.google.protobuf.r0, com.google.protobuf.q0
            public final q0.a e() {
                return k.h();
            }

            @Override // com.google.protobuf.a
            public final boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof d)) {
                    return super.equals(obj);
                }
                d dVar = (d) obj;
                int i = this.g;
                boolean z = (i & 1) != 0;
                int i2 = dVar.g;
                if (z != ((i2 & 1) != 0)) {
                    return false;
                }
                if (((i & 1) != 0) && this.h != dVar.h) {
                    return false;
                }
                if (((i & 2) != 0) != ((i2 & 2) != 0)) {
                    return false;
                }
                return (!((i & 2) != 0) || this.i == dVar.i) && this.f.equals(dVar.f);
            }

            @Override // com.google.protobuf.a
            public final int hashCode() {
                int i = this.memoizedHashCode;
                if (i != 0) {
                    return i;
                }
                int iHashCode = m.g.hashCode() + 779;
                int i2 = this.g;
                if ((i2 & 1) != 0) {
                    iHashCode = C0175b.e(iHashCode, 37, 1, 53) + this.h;
                }
                if ((i2 & 2) != 0) {
                    iHashCode = C0175b.e(iHashCode, 37, 2, 53) + this.i;
                }
                int iHashCode2 = this.f.hashCode() + (iHashCode * 29);
                this.memoizedHashCode = iHashCode2;
                return iHashCode2;
            }

            @Override // com.google.protobuf.a0, defpackage.bh1
            public final boolean isInitialized() {
                byte b2 = this.j;
                if (b2 == 1) {
                    return true;
                }
                if (b2 == 0) {
                    return false;
                }
                this.j = (byte) 1;
                return true;
            }

            @Override // com.google.protobuf.a0, com.google.protobuf.r0
            public final int m() {
                int i = this.e;
                if (i != -1) {
                    return i;
                }
                int iS = (this.g & 1) != 0 ? 0 + nu.S(1, this.h) : 0;
                if ((this.g & 2) != 0) {
                    iS += nu.S(2, this.i);
                }
                int iM = this.f.m() + iS;
                this.e = iM;
                return iM;
            }

            @Override // com.google.protobuf.a0, com.google.protobuf.t0
            public final j1 n() {
                return this.f;
            }

            @Override // com.google.protobuf.a0, com.google.protobuf.r0
            public final void p(nu nuVar) {
                if ((this.g & 1) != 0) {
                    nuVar.z0(1, this.h);
                }
                if ((this.g & 2) != 0) {
                    nuVar.z0(2, this.i);
                }
                this.f.p(nuVar);
            }

            @Override // com.google.protobuf.a0, com.google.protobuf.r0
            public final wv1<d> u() {
                return l;
            }

            @Override // defpackage.bh1, com.google.protobuf.t0
            public final r0 b() {
                return k;
            }

            @Override // com.google.protobuf.r0, com.google.protobuf.q0
            public final r0.a e() {
                return k.h();
            }

            public d() {
                this.j = (byte) -1;
            }
        }

        public a(a0.a aVar) {
            super(aVar);
            this.r = (byte) -1;
        }

        @Override // com.google.protobuf.a0
        public final a0.e M() {
            a0.e eVar = m.d;
            eVar.c(a.class, b.class);
            return eVar;
        }

        @Override // com.google.protobuf.a0
        public final Object O() {
            return new a();
        }

        public final int Q() {
            return this.i.size();
        }

        public final String S() {
            Object obj = this.h;
            if (obj instanceof String) {
                return (String) obj;
            }
            lk lkVar = (lk) obj;
            String strZ = lkVar.z();
            if (lkVar.q()) {
                this.h = strZ;
            }
            return strZ;
        }

        public final int T() {
            return this.n.size();
        }

        public final k U() {
            k kVar = this.o;
            return kVar == null ? k.o : kVar;
        }

        public final boolean V() {
            return (this.g & 2) != 0;
        }

        @Override // com.google.protobuf.r0
        /* JADX INFO: renamed from: W, reason: merged with bridge method [inline-methods] */
        public final b h() {
            if (this == s) {
                return new b();
            }
            b bVar = new b();
            bVar.N(this);
            return bVar;
        }

        @Override // defpackage.bh1, com.google.protobuf.t0
        public final q0 b() {
            return s;
        }

        @Override // com.google.protobuf.r0, com.google.protobuf.q0
        public final q0.a e() {
            return s.h();
        }

        @Override // com.google.protobuf.a
        public final boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof a)) {
                return super.equals(obj);
            }
            a aVar = (a) obj;
            int i = this.g;
            if (((i & 1) != 0) != ((aVar.g & 1) != 0)) {
                return false;
            }
            if ((!((i & 1) != 0) || S().equals(aVar.S())) && this.i.equals(aVar.i) && this.j.equals(aVar.j) && this.k.equals(aVar.k) && this.l.equals(aVar.l) && this.m.equals(aVar.m) && this.n.equals(aVar.n) && V() == aVar.V()) {
                return (!V() || U().equals(aVar.U())) && this.p.equals(aVar.p) && this.q.equals(aVar.q) && this.f.equals(aVar.f);
            }
            return false;
        }

        @Override // com.google.protobuf.a
        public final int hashCode() {
            int i = this.memoizedHashCode;
            if (i != 0) {
                return i;
            }
            int iHashCode = m.c.hashCode() + 779;
            if ((this.g & 1) != 0) {
                iHashCode = C0175b.e(iHashCode, 37, 1, 53) + S().hashCode();
            }
            if (Q() > 0) {
                iHashCode = C0175b.e(iHashCode, 37, 2, 53) + this.i.hashCode();
            }
            if (this.j.size() > 0) {
                iHashCode = C0175b.e(iHashCode, 37, 6, 53) + this.j.hashCode();
            }
            if (this.k.size() > 0) {
                iHashCode = C0175b.e(iHashCode, 37, 3, 53) + this.k.hashCode();
            }
            if (this.l.size() > 0) {
                iHashCode = C0175b.e(iHashCode, 37, 4, 53) + this.l.hashCode();
            }
            if (this.m.size() > 0) {
                iHashCode = C0175b.e(iHashCode, 37, 5, 53) + this.m.hashCode();
            }
            if (T() > 0) {
                iHashCode = C0175b.e(iHashCode, 37, 8, 53) + this.n.hashCode();
            }
            if (V()) {
                iHashCode = C0175b.e(iHashCode, 37, 7, 53) + U().hashCode();
            }
            if (this.p.size() > 0) {
                iHashCode = C0175b.e(iHashCode, 37, 9, 53) + this.p.hashCode();
            }
            if (this.q.size() > 0) {
                iHashCode = C0175b.e(iHashCode, 37, 10, 53) + this.q.hashCode();
            }
            int iHashCode2 = this.f.hashCode() + (iHashCode * 29);
            this.memoizedHashCode = iHashCode2;
            return iHashCode2;
        }

        @Override // com.google.protobuf.a0, defpackage.bh1
        public final boolean isInitialized() {
            byte b2 = this.r;
            if (b2 == 1) {
                return true;
            }
            if (b2 == 0) {
                return false;
            }
            for (int i = 0; i < Q(); i++) {
                if (!this.i.get(i).isInitialized()) {
                    this.r = (byte) 0;
                    return false;
                }
            }
            for (int i2 = 0; i2 < this.j.size(); i2++) {
                if (!this.j.get(i2).isInitialized()) {
                    this.r = (byte) 0;
                    return false;
                }
            }
            for (int i3 = 0; i3 < this.k.size(); i3++) {
                if (!this.k.get(i3).isInitialized()) {
                    this.r = (byte) 0;
                    return false;
                }
            }
            for (int i4 = 0; i4 < this.l.size(); i4++) {
                if (!this.l.get(i4).isInitialized()) {
                    this.r = (byte) 0;
                    return false;
                }
            }
            for (int i5 = 0; i5 < this.m.size(); i5++) {
                if (!this.m.get(i5).isInitialized()) {
                    this.r = (byte) 0;
                    return false;
                }
            }
            for (int i6 = 0; i6 < T(); i6++) {
                if (!this.n.get(i6).isInitialized()) {
                    this.r = (byte) 0;
                    return false;
                }
            }
            if (!V() || U().isInitialized()) {
                this.r = (byte) 1;
                return true;
            }
            this.r = (byte) 0;
            return false;
        }

        @Override // com.google.protobuf.a0, com.google.protobuf.r0
        public final int m() {
            int i = this.e;
            if (i != -1) {
                return i;
            }
            int iH = (this.g & 1) != 0 ? a0.H(1, this.h) + 0 : 0;
            for (int i2 = 0; i2 < this.i.size(); i2++) {
                iH += nu.a0(2, this.i.get(i2));
            }
            for (int i3 = 0; i3 < this.k.size(); i3++) {
                iH += nu.a0(3, this.k.get(i3));
            }
            for (int i4 = 0; i4 < this.l.size(); i4++) {
                iH += nu.a0(4, this.l.get(i4));
            }
            for (int i5 = 0; i5 < this.m.size(); i5++) {
                iH += nu.a0(5, this.m.get(i5));
            }
            for (int i6 = 0; i6 < this.j.size(); i6++) {
                iH += nu.a0(6, this.j.get(i6));
            }
            if ((this.g & 2) != 0) {
                iH += nu.a0(7, U());
            }
            for (int i7 = 0; i7 < this.n.size(); i7++) {
                iH += nu.a0(8, this.n.get(i7));
            }
            for (int i8 = 0; i8 < this.p.size(); i8++) {
                iH += nu.a0(9, this.p.get(i8));
            }
            int I = 0;
            for (int i9 = 0; i9 < this.q.size(); i9++) {
                I += a0.I(this.q.n(i9));
            }
            int iM = this.f.m() + (this.q.size() * 1) + iH + I;
            this.e = iM;
            return iM;
        }

        @Override // com.google.protobuf.a0, com.google.protobuf.t0
        public final j1 n() {
            return this.f;
        }

        @Override // com.google.protobuf.a0, com.google.protobuf.r0
        public final void p(nu nuVar) {
            if ((this.g & 1) != 0) {
                a0.P(nuVar, 1, this.h);
            }
            for (int i = 0; i < this.i.size(); i++) {
                nuVar.D0(2, this.i.get(i));
            }
            for (int i2 = 0; i2 < this.k.size(); i2++) {
                nuVar.D0(3, this.k.get(i2));
            }
            for (int i3 = 0; i3 < this.l.size(); i3++) {
                nuVar.D0(4, this.l.get(i3));
            }
            for (int i4 = 0; i4 < this.m.size(); i4++) {
                nuVar.D0(5, this.m.get(i4));
            }
            for (int i5 = 0; i5 < this.j.size(); i5++) {
                nuVar.D0(6, this.j.get(i5));
            }
            if ((this.g & 2) != 0) {
                nuVar.D0(7, U());
            }
            for (int i6 = 0; i6 < this.n.size(); i6++) {
                nuVar.D0(8, this.n.get(i6));
            }
            for (int i7 = 0; i7 < this.p.size(); i7++) {
                nuVar.D0(9, this.p.get(i7));
            }
            for (int i8 = 0; i8 < this.q.size(); i8++) {
                a0.P(nuVar, 10, this.q.n(i8));
            }
            this.f.p(nuVar);
        }

        @Override // com.google.protobuf.a0, com.google.protobuf.r0
        public final wv1<a> u() {
            return t;
        }

        @Override // defpackage.bh1, com.google.protobuf.t0
        public final r0 b() {
            return s;
        }

        @Override // com.google.protobuf.r0, com.google.protobuf.q0
        public final r0.a e() {
            return s.h();
        }

        public a() {
            this.r = (byte) -1;
            this.h = "";
            this.i = Collections.emptyList();
            this.j = Collections.emptyList();
            this.k = Collections.emptyList();
            this.l = Collections.emptyList();
            this.m = Collections.emptyList();
            this.n = Collections.emptyList();
            this.p = Collections.emptyList();
            this.q = g0.g;
        }
    }

    /* JADX INFO: compiled from: DescriptorProtos.java */
    public static final class b extends a0 implements t0 {
        public static final b n = new b();

        @Deprecated
        public static final a o = new a();
        private static final long serialVersionUID = 0;
        public int g;
        public volatile Object h;
        public List<d> i;
        public c j;
        public List<c> k;
        public q61 l;
        public byte m;

        /* JADX INFO: compiled from: DescriptorProtos.java */
        public static class a extends com.google.protobuf.c<b> {
            @Override // defpackage.wv1
            public final Object b(com.google.protobuf.g gVar, com.google.protobuf.s sVar) {
                b bVar = new b();
                sVar.getClass();
                j1 j1Var = j1.f;
                j1.a aVar = new j1.a();
                boolean z = false;
                int i = 0;
                while (!z) {
                    try {
                        try {
                            int iF = gVar.F();
                            if (iF != 0) {
                                if (iF == 10) {
                                    lk.f fVarM = gVar.m();
                                    bVar.g = 1 | bVar.g;
                                    bVar.h = fVarM;
                                } else if (iF == 18) {
                                    if ((i & 2) == 0) {
                                        bVar.i = new ArrayList();
                                        i |= 2;
                                    }
                                    bVar.i.add((d) gVar.v(d.m, sVar));
                                } else if (iF == 26) {
                                    c.b bVarH = (bVar.g & 2) != 0 ? bVar.j.h() : null;
                                    c cVar = (c) gVar.v(c.n, sVar);
                                    bVar.j = cVar;
                                    if (bVarH != null) {
                                        bVarH.Q(cVar);
                                        bVar.j = bVarH.d();
                                    }
                                    bVar.g |= 2;
                                } else if (iF == 34) {
                                    if ((i & 8) == 0) {
                                        bVar.k = new ArrayList();
                                        i |= 8;
                                    }
                                    bVar.k.add((c) gVar.v(c.l, sVar));
                                } else if (iF == 42) {
                                    lk.f fVarM2 = gVar.m();
                                    if ((i & 16) == 0) {
                                        bVar.l = new g0();
                                        i |= 16;
                                    }
                                    bVar.l.l(fVarM2);
                                } else if (!aVar.r(iF, gVar)) {
                                }
                            }
                            z = true;
                        } catch (d0 e) {
                            e.e = bVar;
                            throw e;
                        } catch (IOException e2) {
                            d0 d0Var = new d0(e2);
                            d0Var.e = bVar;
                            throw d0Var;
                        }
                    } catch (Throwable th) {
                        if ((i & 2) != 0) {
                            bVar.i = Collections.unmodifiableList(bVar.i);
                        }
                        if ((i & 8) != 0) {
                            bVar.k = Collections.unmodifiableList(bVar.k);
                        }
                        if ((i & 16) != 0) {
                            bVar.l = bVar.l.m();
                        }
                        bVar.f = aVar.build();
                        throw th;
                    }
                }
                if ((i & 2) != 0) {
                    bVar.i = Collections.unmodifiableList(bVar.i);
                }
                if ((i & 8) != 0) {
                    bVar.k = Collections.unmodifiableList(bVar.k);
                }
                if ((i & 16) != 0) {
                    bVar.l = bVar.l.m();
                }
                bVar.f = aVar.build();
                return bVar;
            }
        }

        /* JADX INFO: renamed from: com.google.protobuf.m$b$b, reason: collision with other inner class name */
        /* JADX INFO: compiled from: DescriptorProtos.java */
        public static final class C0050b extends a0.a<C0050b> implements t0 {
            public int g;
            public c j;
            public Object h = "";
            public List<d> i = Collections.emptyList();
            public List<c> k = Collections.emptyList();
            public q61 l = g0.g;

            @Override // com.google.protobuf.a0.a
            /* JADX INFO: renamed from: A */
            public final a0.a f(n.f fVar, Object obj) {
                super.f(fVar, obj);
                return this;
            }

            @Override // com.google.protobuf.a0.a
            /* JADX INFO: renamed from: B */
            public final a0.a clone() {
                return (C0050b) super.clone();
            }

            @Override // com.google.protobuf.a0.a
            public final a0.e D() {
                a0.e eVar = m.p;
                eVar.c(b.class, C0050b.class);
                return eVar;
            }

            @Override // com.google.protobuf.a0.a
            /* JADX INFO: renamed from: E */
            public final a0.a y(j1 j1Var) {
                return (C0050b) super.y(j1Var);
            }

            @Override // com.google.protobuf.a0.a
            /* JADX INFO: renamed from: I */
            public final a0.a a(n.f fVar, Object obj) {
                super.a(fVar, obj);
                return this;
            }

            @Override // com.google.protobuf.a0.a
            /* JADX INFO: renamed from: J */
            public final a0.a j0(j1 j1Var) {
                this.f = j1Var;
                H();
                return this;
            }

            @Override // com.google.protobuf.r0.a, com.google.protobuf.q0.a
            /* JADX INFO: renamed from: K, reason: merged with bridge method [inline-methods] */
            public final b d() {
                b bVar = new b(this);
                int i = this.g;
                int i2 = (i & 1) != 0 ? 1 : 0;
                bVar.h = this.h;
                if ((this.g & 2) != 0) {
                    this.i = Collections.unmodifiableList(this.i);
                    this.g &= -3;
                }
                bVar.i = this.i;
                if ((i & 4) != 0) {
                    bVar.j = this.j;
                    i2 |= 2;
                }
                if ((this.g & 8) != 0) {
                    this.k = Collections.unmodifiableList(this.k);
                    this.g &= -9;
                }
                bVar.k = this.k;
                if ((this.g & 16) != 0) {
                    this.l = this.l.m();
                    this.g &= -17;
                }
                bVar.l = this.l;
                bVar.g = i2;
                F();
                return bVar;
            }

            @Override // com.google.protobuf.a.AbstractC0042a, com.google.protobuf.r0.a
            public final /* bridge */ /* synthetic */ r0.a L(com.google.protobuf.g gVar, com.google.protobuf.s sVar) throws Throwable {
                N(gVar, sVar);
                return this;
            }

            public final void M(b bVar) {
                c cVar;
                c cVar2;
                if (bVar == b.n) {
                    return;
                }
                if ((bVar.g & 1) != 0) {
                    this.g |= 1;
                    this.h = bVar.h;
                    H();
                }
                if (!bVar.i.isEmpty()) {
                    if (this.i.isEmpty()) {
                        this.i = bVar.i;
                        this.g &= -3;
                    } else {
                        if ((this.g & 2) == 0) {
                            this.i = new ArrayList(this.i);
                            this.g |= 2;
                        }
                        this.i.addAll(bVar.i);
                    }
                    H();
                }
                if (bVar.U()) {
                    c cVarS = bVar.S();
                    if ((this.g & 4) == 0 || (cVar = this.j) == null || cVar == (cVar2 = c.m)) {
                        this.j = cVarS;
                    } else {
                        c.b bVarH = cVar2.h();
                        bVarH.Q(cVar);
                        bVarH.Q(cVarS);
                        this.j = bVarH.d();
                    }
                    H();
                    this.g |= 4;
                }
                if (!bVar.k.isEmpty()) {
                    if (this.k.isEmpty()) {
                        this.k = bVar.k;
                        this.g &= -9;
                    } else {
                        if ((this.g & 8) == 0) {
                            this.k = new ArrayList(this.k);
                            this.g |= 8;
                        }
                        this.k.addAll(bVar.k);
                    }
                    H();
                }
                if (!bVar.l.isEmpty()) {
                    if (this.l.isEmpty()) {
                        this.l = bVar.l;
                        this.g &= -17;
                    } else {
                        if ((this.g & 16) == 0) {
                            this.l = new g0(this.l);
                            this.g |= 16;
                        }
                        this.l.addAll(bVar.l);
                    }
                    H();
                }
                H();
            }

            /* JADX WARN: Removed duplicated region for block: B:15:0x001d  */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public final void N(com.google.protobuf.g r2, com.google.protobuf.s r3) throws java.lang.Throwable {
                /*
                    r1 = this;
                    com.google.protobuf.m$b$a r0 = com.google.protobuf.m.b.o     // Catch: java.lang.Throwable -> Lc com.google.protobuf.d0 -> Le
                    java.lang.Object r2 = r0.b(r2, r3)     // Catch: java.lang.Throwable -> Lc com.google.protobuf.d0 -> Le
                    com.google.protobuf.m$b r2 = (com.google.protobuf.m.b) r2     // Catch: java.lang.Throwable -> Lc com.google.protobuf.d0 -> Le
                    r1.M(r2)
                    return
                Lc:
                    r2 = move-exception
                    goto L1a
                Le:
                    r2 = move-exception
                    com.google.protobuf.r0 r3 = r2.e     // Catch: java.lang.Throwable -> Lc
                    com.google.protobuf.m$b r3 = (com.google.protobuf.m.b) r3     // Catch: java.lang.Throwable -> Lc
                    java.io.IOException r2 = r2.l()     // Catch: java.lang.Throwable -> L18
                    throw r2     // Catch: java.lang.Throwable -> L18
                L18:
                    r2 = move-exception
                    goto L1b
                L1a:
                    r3 = 0
                L1b:
                    if (r3 == 0) goto L20
                    r1.M(r3)
                L20:
                    throw r2
                */
                throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.m.b.C0050b.N(com.google.protobuf.g, com.google.protobuf.s):void");
            }

            @Override // com.google.protobuf.a.AbstractC0042a, com.google.protobuf.q0.a
            public final q0.a R(q0 q0Var) {
                if (q0Var instanceof b) {
                    M((b) q0Var);
                } else {
                    super.R(q0Var);
                }
                return this;
            }

            @Override // com.google.protobuf.a0.a, com.google.protobuf.q0.a
            public final q0.a a(n.f fVar, Object obj) {
                super.a(fVar, obj);
                return this;
            }

            @Override // defpackage.bh1, com.google.protobuf.t0
            public final q0 b() {
                return b.n;
            }

            @Override // com.google.protobuf.r0.a, com.google.protobuf.q0.a
            public final q0 build() {
                b bVarD = d();
                if (bVarD.isInitialized()) {
                    return bVarD;
                }
                throw a.AbstractC0042a.z(bVarD);
            }

            @Override // com.google.protobuf.a0.a, com.google.protobuf.q0.a, com.google.protobuf.t0
            public final n.a c() {
                return m.o;
            }

            @Override // com.google.protobuf.a0.a
            public final Object clone() {
                return (C0050b) super.clone();
            }

            @Override // com.google.protobuf.a0.a, com.google.protobuf.q0.a
            public final q0.a f(n.f fVar, Object obj) {
                super.f(fVar, obj);
                return this;
            }

            @Override // com.google.protobuf.a0.a, com.google.protobuf.q0.a
            public final q0.a j0(j1 j1Var) {
                this.f = j1Var;
                H();
                return this;
            }

            @Override // com.google.protobuf.a.AbstractC0042a
            /* JADX INFO: renamed from: v */
            public final /* bridge */ /* synthetic */ a.AbstractC0042a L(com.google.protobuf.g gVar, com.google.protobuf.s sVar) throws Throwable {
                N(gVar, sVar);
                return this;
            }

            @Override // com.google.protobuf.a.AbstractC0042a
            /* JADX INFO: renamed from: x */
            public final a.AbstractC0042a R(q0 q0Var) {
                if (q0Var instanceof b) {
                    M((b) q0Var);
                } else {
                    super.R(q0Var);
                }
                return this;
            }

            @Override // com.google.protobuf.a0.a, com.google.protobuf.a.AbstractC0042a
            public final void y(j1 j1Var) {
            }

            @Override // defpackage.bh1, com.google.protobuf.t0
            public final r0 b() {
                return b.n;
            }

            @Override // com.google.protobuf.r0.a, com.google.protobuf.q0.a
            public final r0 build() {
                b bVarD = d();
                if (bVarD.isInitialized()) {
                    return bVarD;
                }
                throw a.AbstractC0042a.z(bVarD);
            }
        }

        /* JADX INFO: compiled from: DescriptorProtos.java */
        public static final class c extends a0 implements t0 {
            public static final c k = new c();

            @Deprecated
            public static final a l = new a();
            private static final long serialVersionUID = 0;
            public int g;
            public int h;
            public int i;
            public byte j;

            /* JADX INFO: compiled from: DescriptorProtos.java */
            public static class a extends com.google.protobuf.c<c> {
                @Override // defpackage.wv1
                public final Object b(com.google.protobuf.g gVar, com.google.protobuf.s sVar) {
                    c cVar = new c();
                    sVar.getClass();
                    j1 j1Var = j1.f;
                    j1.a aVar = new j1.a();
                    boolean z = false;
                    while (!z) {
                        try {
                            try {
                                int iF = gVar.F();
                                if (iF != 0) {
                                    if (iF == 8) {
                                        cVar.g |= 1;
                                        cVar.h = gVar.t();
                                    } else if (iF == 16) {
                                        cVar.g |= 2;
                                        cVar.i = gVar.t();
                                    } else if (!aVar.r(iF, gVar)) {
                                    }
                                }
                                z = true;
                            } catch (d0 e) {
                                e.e = cVar;
                                throw e;
                            } catch (IOException e2) {
                                d0 d0Var = new d0(e2);
                                d0Var.e = cVar;
                                throw d0Var;
                            }
                        } finally {
                            cVar.f = aVar.build();
                        }
                    }
                    return cVar;
                }
            }

            /* JADX INFO: renamed from: com.google.protobuf.m$b$c$b, reason: collision with other inner class name */
            /* JADX INFO: compiled from: DescriptorProtos.java */
            public static final class C0051b extends a0.a<C0051b> implements t0 {
                public int g;
                public int h;
                public int i;

                @Override // com.google.protobuf.a0.a
                /* JADX INFO: renamed from: A */
                public final a0.a f(n.f fVar, Object obj) {
                    super.f(fVar, obj);
                    return this;
                }

                @Override // com.google.protobuf.a0.a
                /* JADX INFO: renamed from: B */
                public final a0.a clone() {
                    return (C0051b) super.clone();
                }

                @Override // com.google.protobuf.a0.a
                public final a0.e D() {
                    a0.e eVar = m.r;
                    eVar.c(c.class, C0051b.class);
                    return eVar;
                }

                @Override // com.google.protobuf.a0.a
                /* JADX INFO: renamed from: E */
                public final a0.a y(j1 j1Var) {
                    return (C0051b) super.y(j1Var);
                }

                @Override // com.google.protobuf.a0.a
                /* JADX INFO: renamed from: I */
                public final a0.a a(n.f fVar, Object obj) {
                    super.a(fVar, obj);
                    return this;
                }

                @Override // com.google.protobuf.a0.a
                /* JADX INFO: renamed from: J */
                public final a0.a j0(j1 j1Var) {
                    this.f = j1Var;
                    H();
                    return this;
                }

                @Override // com.google.protobuf.r0.a, com.google.protobuf.q0.a
                /* JADX INFO: renamed from: K, reason: merged with bridge method [inline-methods] */
                public final c d() {
                    int i;
                    c cVar = new c(this);
                    int i2 = this.g;
                    if ((i2 & 1) != 0) {
                        cVar.h = this.h;
                        i = 1;
                    } else {
                        i = 0;
                    }
                    if ((i2 & 2) != 0) {
                        cVar.i = this.i;
                        i |= 2;
                    }
                    cVar.g = i;
                    F();
                    return cVar;
                }

                @Override // com.google.protobuf.a.AbstractC0042a, com.google.protobuf.r0.a
                public final /* bridge */ /* synthetic */ r0.a L(com.google.protobuf.g gVar, com.google.protobuf.s sVar) throws Throwable {
                    N(gVar, sVar);
                    return this;
                }

                public final void M(c cVar) {
                    if (cVar == c.k) {
                        return;
                    }
                    if ((cVar.g & 1) != 0) {
                        int i = cVar.h;
                        this.g |= 1;
                        this.h = i;
                        H();
                    }
                    if ((cVar.g & 2) != 0) {
                        int i2 = cVar.i;
                        this.g |= 2;
                        this.i = i2;
                        H();
                    }
                    H();
                }

                /* JADX WARN: Removed duplicated region for block: B:15:0x001d  */
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct code enable 'Show inconsistent code' option in preferences
                */
                public final void N(com.google.protobuf.g r2, com.google.protobuf.s r3) throws java.lang.Throwable {
                    /*
                        r1 = this;
                        com.google.protobuf.m$b$c$a r0 = com.google.protobuf.m.b.c.l     // Catch: java.lang.Throwable -> Lc com.google.protobuf.d0 -> Le
                        java.lang.Object r2 = r0.b(r2, r3)     // Catch: java.lang.Throwable -> Lc com.google.protobuf.d0 -> Le
                        com.google.protobuf.m$b$c r2 = (com.google.protobuf.m.b.c) r2     // Catch: java.lang.Throwable -> Lc com.google.protobuf.d0 -> Le
                        r1.M(r2)
                        return
                    Lc:
                        r2 = move-exception
                        goto L1a
                    Le:
                        r2 = move-exception
                        com.google.protobuf.r0 r3 = r2.e     // Catch: java.lang.Throwable -> Lc
                        com.google.protobuf.m$b$c r3 = (com.google.protobuf.m.b.c) r3     // Catch: java.lang.Throwable -> Lc
                        java.io.IOException r2 = r2.l()     // Catch: java.lang.Throwable -> L18
                        throw r2     // Catch: java.lang.Throwable -> L18
                    L18:
                        r2 = move-exception
                        goto L1b
                    L1a:
                        r3 = 0
                    L1b:
                        if (r3 == 0) goto L20
                        r1.M(r3)
                    L20:
                        throw r2
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.m.b.c.C0051b.N(com.google.protobuf.g, com.google.protobuf.s):void");
                }

                @Override // com.google.protobuf.a.AbstractC0042a, com.google.protobuf.q0.a
                public final q0.a R(q0 q0Var) {
                    if (q0Var instanceof c) {
                        M((c) q0Var);
                    } else {
                        super.R(q0Var);
                    }
                    return this;
                }

                @Override // com.google.protobuf.a0.a, com.google.protobuf.q0.a
                public final q0.a a(n.f fVar, Object obj) {
                    super.a(fVar, obj);
                    return this;
                }

                @Override // defpackage.bh1, com.google.protobuf.t0
                public final q0 b() {
                    return c.k;
                }

                @Override // com.google.protobuf.r0.a, com.google.protobuf.q0.a
                public final q0 build() {
                    c cVarD = d();
                    if (cVarD.isInitialized()) {
                        return cVarD;
                    }
                    throw a.AbstractC0042a.z(cVarD);
                }

                @Override // com.google.protobuf.a0.a, com.google.protobuf.q0.a, com.google.protobuf.t0
                public final n.a c() {
                    return m.q;
                }

                @Override // com.google.protobuf.a0.a
                public final Object clone() {
                    return (C0051b) super.clone();
                }

                @Override // com.google.protobuf.a0.a, com.google.protobuf.q0.a
                public final q0.a f(n.f fVar, Object obj) {
                    super.f(fVar, obj);
                    return this;
                }

                @Override // com.google.protobuf.a0.a, com.google.protobuf.q0.a
                public final q0.a j0(j1 j1Var) {
                    this.f = j1Var;
                    H();
                    return this;
                }

                @Override // com.google.protobuf.a.AbstractC0042a
                /* JADX INFO: renamed from: v */
                public final /* bridge */ /* synthetic */ a.AbstractC0042a L(com.google.protobuf.g gVar, com.google.protobuf.s sVar) throws Throwable {
                    N(gVar, sVar);
                    return this;
                }

                @Override // com.google.protobuf.a.AbstractC0042a
                /* JADX INFO: renamed from: x */
                public final a.AbstractC0042a R(q0 q0Var) {
                    if (q0Var instanceof c) {
                        M((c) q0Var);
                    } else {
                        super.R(q0Var);
                    }
                    return this;
                }

                @Override // com.google.protobuf.a0.a, com.google.protobuf.a.AbstractC0042a
                public final void y(j1 j1Var) {
                }

                @Override // defpackage.bh1, com.google.protobuf.t0
                public final r0 b() {
                    return c.k;
                }

                @Override // com.google.protobuf.r0.a, com.google.protobuf.q0.a
                public final r0 build() {
                    c cVarD = d();
                    if (cVarD.isInitialized()) {
                        return cVarD;
                    }
                    throw a.AbstractC0042a.z(cVarD);
                }
            }

            public c(a0.a aVar) {
                super(aVar);
                this.j = (byte) -1;
            }

            @Override // com.google.protobuf.a0
            public final a0.e M() {
                a0.e eVar = m.r;
                eVar.c(c.class, C0051b.class);
                return eVar;
            }

            @Override // com.google.protobuf.a0
            public final Object O() {
                return new c();
            }

            @Override // com.google.protobuf.r0
            /* JADX INFO: renamed from: Q, reason: merged with bridge method [inline-methods] */
            public final C0051b h() {
                if (this == k) {
                    return new C0051b();
                }
                C0051b c0051b = new C0051b();
                c0051b.M(this);
                return c0051b;
            }

            @Override // defpackage.bh1, com.google.protobuf.t0
            public final q0 b() {
                return k;
            }

            @Override // com.google.protobuf.r0, com.google.protobuf.q0
            public final q0.a e() {
                return k.h();
            }

            @Override // com.google.protobuf.a
            public final boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof c)) {
                    return super.equals(obj);
                }
                c cVar = (c) obj;
                int i = this.g;
                boolean z = (i & 1) != 0;
                int i2 = cVar.g;
                if (z != ((i2 & 1) != 0)) {
                    return false;
                }
                if (((i & 1) != 0) && this.h != cVar.h) {
                    return false;
                }
                if (((i & 2) != 0) != ((i2 & 2) != 0)) {
                    return false;
                }
                return (!((i & 2) != 0) || this.i == cVar.i) && this.f.equals(cVar.f);
            }

            @Override // com.google.protobuf.a
            public final int hashCode() {
                int i = this.memoizedHashCode;
                if (i != 0) {
                    return i;
                }
                int iHashCode = m.q.hashCode() + 779;
                int i2 = this.g;
                if ((i2 & 1) != 0) {
                    iHashCode = C0175b.e(iHashCode, 37, 1, 53) + this.h;
                }
                if ((i2 & 2) != 0) {
                    iHashCode = C0175b.e(iHashCode, 37, 2, 53) + this.i;
                }
                int iHashCode2 = this.f.hashCode() + (iHashCode * 29);
                this.memoizedHashCode = iHashCode2;
                return iHashCode2;
            }

            @Override // com.google.protobuf.a0, defpackage.bh1
            public final boolean isInitialized() {
                byte b = this.j;
                if (b == 1) {
                    return true;
                }
                if (b == 0) {
                    return false;
                }
                this.j = (byte) 1;
                return true;
            }

            @Override // com.google.protobuf.a0, com.google.protobuf.r0
            public final int m() {
                int i = this.e;
                if (i != -1) {
                    return i;
                }
                int iS = (this.g & 1) != 0 ? 0 + nu.S(1, this.h) : 0;
                if ((this.g & 2) != 0) {
                    iS += nu.S(2, this.i);
                }
                int iM = this.f.m() + iS;
                this.e = iM;
                return iM;
            }

            @Override // com.google.protobuf.a0, com.google.protobuf.t0
            public final j1 n() {
                return this.f;
            }

            @Override // com.google.protobuf.a0, com.google.protobuf.r0
            public final void p(nu nuVar) {
                if ((this.g & 1) != 0) {
                    nuVar.z0(1, this.h);
                }
                if ((this.g & 2) != 0) {
                    nuVar.z0(2, this.i);
                }
                this.f.p(nuVar);
            }

            @Override // com.google.protobuf.a0, com.google.protobuf.r0
            public final wv1<c> u() {
                return l;
            }

            @Override // defpackage.bh1, com.google.protobuf.t0
            public final r0 b() {
                return k;
            }

            @Override // com.google.protobuf.r0, com.google.protobuf.q0
            public final r0.a e() {
                return k.h();
            }

            public c() {
                this.j = (byte) -1;
            }
        }

        public b(a0.a aVar) {
            super(aVar);
            this.m = (byte) -1;
        }

        @Override // com.google.protobuf.a0
        public final a0.e M() {
            a0.e eVar = m.p;
            eVar.c(b.class, C0050b.class);
            return eVar;
        }

        @Override // com.google.protobuf.a0
        public final Object O() {
            return new b();
        }

        public final String Q() {
            Object obj = this.h;
            if (obj instanceof String) {
                return (String) obj;
            }
            lk lkVar = (lk) obj;
            String strZ = lkVar.z();
            if (lkVar.q()) {
                this.h = strZ;
            }
            return strZ;
        }

        public final c S() {
            c cVar = this.j;
            return cVar == null ? c.m : cVar;
        }

        public final int T() {
            return this.i.size();
        }

        public final boolean U() {
            return (this.g & 2) != 0;
        }

        @Override // com.google.protobuf.r0
        /* JADX INFO: renamed from: V, reason: merged with bridge method [inline-methods] */
        public final C0050b h() {
            if (this == n) {
                return new C0050b();
            }
            C0050b c0050b = new C0050b();
            c0050b.M(this);
            return c0050b;
        }

        @Override // defpackage.bh1, com.google.protobuf.t0
        public final q0 b() {
            return n;
        }

        @Override // com.google.protobuf.r0, com.google.protobuf.q0
        public final q0.a e() {
            return n.h();
        }

        @Override // com.google.protobuf.a
        public final boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof b)) {
                return super.equals(obj);
            }
            b bVar = (b) obj;
            int i = this.g;
            if (((i & 1) != 0) != ((bVar.g & 1) != 0)) {
                return false;
            }
            if ((!((i & 1) != 0) || Q().equals(bVar.Q())) && this.i.equals(bVar.i) && U() == bVar.U()) {
                return (!U() || S().equals(bVar.S())) && this.k.equals(bVar.k) && this.l.equals(bVar.l) && this.f.equals(bVar.f);
            }
            return false;
        }

        @Override // com.google.protobuf.a
        public final int hashCode() {
            int i = this.memoizedHashCode;
            if (i != 0) {
                return i;
            }
            int iHashCode = m.o.hashCode() + 779;
            if ((this.g & 1) != 0) {
                iHashCode = C0175b.e(iHashCode, 37, 1, 53) + Q().hashCode();
            }
            if (T() > 0) {
                iHashCode = C0175b.e(iHashCode, 37, 2, 53) + this.i.hashCode();
            }
            if (U()) {
                iHashCode = C0175b.e(iHashCode, 37, 3, 53) + S().hashCode();
            }
            if (this.k.size() > 0) {
                iHashCode = C0175b.e(iHashCode, 37, 4, 53) + this.k.hashCode();
            }
            if (this.l.size() > 0) {
                iHashCode = C0175b.e(iHashCode, 37, 5, 53) + this.l.hashCode();
            }
            int iHashCode2 = this.f.hashCode() + (iHashCode * 29);
            this.memoizedHashCode = iHashCode2;
            return iHashCode2;
        }

        @Override // com.google.protobuf.a0, defpackage.bh1
        public final boolean isInitialized() {
            byte b = this.m;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
                return false;
            }
            for (int i = 0; i < T(); i++) {
                if (!this.i.get(i).isInitialized()) {
                    this.m = (byte) 0;
                    return false;
                }
            }
            if (!U() || S().isInitialized()) {
                this.m = (byte) 1;
                return true;
            }
            this.m = (byte) 0;
            return false;
        }

        @Override // com.google.protobuf.a0, com.google.protobuf.r0
        public final int m() {
            int i = this.e;
            if (i != -1) {
                return i;
            }
            int iH = (this.g & 1) != 0 ? a0.H(1, this.h) + 0 : 0;
            for (int i2 = 0; i2 < this.i.size(); i2++) {
                iH += nu.a0(2, this.i.get(i2));
            }
            if ((this.g & 2) != 0) {
                iH += nu.a0(3, S());
            }
            for (int i3 = 0; i3 < this.k.size(); i3++) {
                iH += nu.a0(4, this.k.get(i3));
            }
            int I = 0;
            for (int i4 = 0; i4 < this.l.size(); i4++) {
                I += a0.I(this.l.n(i4));
            }
            int iM = this.f.m() + (this.l.size() * 1) + iH + I;
            this.e = iM;
            return iM;
        }

        @Override // com.google.protobuf.a0, com.google.protobuf.t0
        public final j1 n() {
            return this.f;
        }

        @Override // com.google.protobuf.a0, com.google.protobuf.r0
        public final void p(nu nuVar) {
            if ((this.g & 1) != 0) {
                a0.P(nuVar, 1, this.h);
            }
            for (int i = 0; i < this.i.size(); i++) {
                nuVar.D0(2, this.i.get(i));
            }
            if ((this.g & 2) != 0) {
                nuVar.D0(3, S());
            }
            for (int i2 = 0; i2 < this.k.size(); i2++) {
                nuVar.D0(4, this.k.get(i2));
            }
            for (int i3 = 0; i3 < this.l.size(); i3++) {
                a0.P(nuVar, 5, this.l.n(i3));
            }
            this.f.p(nuVar);
        }

        @Override // com.google.protobuf.a0, com.google.protobuf.r0
        public final wv1<b> u() {
            return o;
        }

        @Override // defpackage.bh1, com.google.protobuf.t0
        public final r0 b() {
            return n;
        }

        @Override // com.google.protobuf.r0, com.google.protobuf.q0
        public final r0.a e() {
            return n.h();
        }

        public b() {
            this.m = (byte) -1;
            this.h = "";
            this.i = Collections.emptyList();
            this.k = Collections.emptyList();
            this.l = g0.g;
        }
    }

    /* JADX INFO: compiled from: DescriptorProtos.java */
    public static final class c extends a0.d<c> {
        public static final c m = new c();

        @Deprecated
        public static final a n = new a();
        private static final long serialVersionUID = 0;
        public int h;
        public boolean i;
        public boolean j;
        public List<s> k;
        public byte l;

        /* JADX INFO: compiled from: DescriptorProtos.java */
        public static class a extends com.google.protobuf.c<c> {
            @Override // defpackage.wv1
            public final Object b(com.google.protobuf.g gVar, com.google.protobuf.s sVar) {
                c cVar = new c();
                sVar.getClass();
                j1 j1Var = j1.f;
                j1.a aVar = new j1.a();
                boolean z = false;
                int i = 0;
                while (!z) {
                    try {
                        try {
                            int iF = gVar.F();
                            if (iF != 0) {
                                if (iF == 16) {
                                    cVar.h |= 1;
                                    cVar.i = gVar.l();
                                } else if (iF == 24) {
                                    cVar.h |= 2;
                                    cVar.j = gVar.l();
                                } else if (iF == 7994) {
                                    if ((i & 4) == 0) {
                                        cVar.k = new ArrayList();
                                        i |= 4;
                                    }
                                    cVar.k.add((s) gVar.v(s.q, sVar));
                                } else if (!cVar.V(gVar, aVar, sVar, iF)) {
                                }
                            }
                            z = true;
                        } catch (d0 e) {
                            e.e = cVar;
                            throw e;
                        } catch (IOException e2) {
                            d0 d0Var = new d0(e2);
                            d0Var.e = cVar;
                            throw d0Var;
                        }
                    } catch (Throwable th) {
                        if ((i & 4) != 0) {
                            cVar.k = Collections.unmodifiableList(cVar.k);
                        }
                        cVar.f = aVar.build();
                        cVar.U();
                        throw th;
                    }
                }
                if ((i & 4) != 0) {
                    cVar.k = Collections.unmodifiableList(cVar.k);
                }
                cVar.f = aVar.build();
                cVar.U();
                return cVar;
            }
        }

        /* JADX INFO: compiled from: DescriptorProtos.java */
        public static final class b extends a0.c<c, b> {
            public int h;
            public boolean i;
            public boolean j;
            public List<s> k = Collections.emptyList();

            @Override // com.google.protobuf.a0.a
            /* JADX INFO: renamed from: A */
            public final a0.a f(n.f fVar, Object obj) {
                super.K(fVar, obj);
                return this;
            }

            @Override // com.google.protobuf.a0.a
            /* JADX INFO: renamed from: B */
            public final a0.a clone() {
                return (b) super.clone();
            }

            @Override // com.google.protobuf.a0.a
            public final a0.e D() {
                a0.e eVar = m.H;
                eVar.c(c.class, b.class);
                return eVar;
            }

            @Override // com.google.protobuf.a0.a
            /* JADX INFO: renamed from: E */
            public final a0.a y(j1 j1Var) {
                return (b) super.y(j1Var);
            }

            @Override // com.google.protobuf.a0.a
            /* JADX INFO: renamed from: I */
            public final a0.a a(n.f fVar, Object obj) {
                return (b) super.N(fVar, obj);
            }

            @Override // com.google.protobuf.a0.a
            /* JADX INFO: renamed from: J */
            public final a0.a j0(j1 j1Var) {
                this.f = j1Var;
                H();
                return this;
            }

            @Override // com.google.protobuf.a.AbstractC0042a, com.google.protobuf.r0.a
            public final /* bridge */ /* synthetic */ r0.a L(com.google.protobuf.g gVar, com.google.protobuf.s sVar) throws Throwable {
                S(gVar, sVar);
                return this;
            }

            @Override // com.google.protobuf.r0.a, com.google.protobuf.q0.a
            /* JADX INFO: renamed from: P, reason: merged with bridge method [inline-methods] */
            public final c d() {
                int i;
                c cVar = new c(this);
                int i2 = this.h;
                if ((i2 & 1) != 0) {
                    cVar.i = this.i;
                    i = 1;
                } else {
                    i = 0;
                }
                if ((i2 & 2) != 0) {
                    cVar.j = this.j;
                    i |= 2;
                }
                if ((i2 & 4) != 0) {
                    this.k = Collections.unmodifiableList(this.k);
                    this.h &= -5;
                }
                cVar.k = this.k;
                cVar.h = i;
                F();
                return cVar;
            }

            public final void Q(c cVar) {
                if (cVar == c.m) {
                    return;
                }
                if ((cVar.h & 1) != 0) {
                    boolean z = cVar.i;
                    this.h |= 1;
                    this.i = z;
                    H();
                }
                if ((cVar.h & 2) != 0) {
                    boolean z2 = cVar.j;
                    this.h |= 2;
                    this.j = z2;
                    H();
                }
                if (!cVar.k.isEmpty()) {
                    if (this.k.isEmpty()) {
                        this.k = cVar.k;
                        this.h &= -5;
                    } else {
                        if ((this.h & 4) == 0) {
                            this.k = new ArrayList(this.k);
                            this.h |= 4;
                        }
                        this.k.addAll(cVar.k);
                    }
                    H();
                }
                M(cVar);
                H();
            }

            @Override // com.google.protobuf.a.AbstractC0042a, com.google.protobuf.q0.a
            public final q0.a R(q0 q0Var) {
                if (q0Var instanceof c) {
                    Q((c) q0Var);
                } else {
                    super.R(q0Var);
                }
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:15:0x001d  */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public final void S(com.google.protobuf.g r2, com.google.protobuf.s r3) throws java.lang.Throwable {
                /*
                    r1 = this;
                    com.google.protobuf.m$c$a r0 = com.google.protobuf.m.c.n     // Catch: java.lang.Throwable -> Lc com.google.protobuf.d0 -> Le
                    java.lang.Object r2 = r0.b(r2, r3)     // Catch: java.lang.Throwable -> Lc com.google.protobuf.d0 -> Le
                    com.google.protobuf.m$c r2 = (com.google.protobuf.m.c) r2     // Catch: java.lang.Throwable -> Lc com.google.protobuf.d0 -> Le
                    r1.Q(r2)
                    return
                Lc:
                    r2 = move-exception
                    goto L1a
                Le:
                    r2 = move-exception
                    com.google.protobuf.r0 r3 = r2.e     // Catch: java.lang.Throwable -> Lc
                    com.google.protobuf.m$c r3 = (com.google.protobuf.m.c) r3     // Catch: java.lang.Throwable -> Lc
                    java.io.IOException r2 = r2.l()     // Catch: java.lang.Throwable -> L18
                    throw r2     // Catch: java.lang.Throwable -> L18
                L18:
                    r2 = move-exception
                    goto L1b
                L1a:
                    r3 = 0
                L1b:
                    if (r3 == 0) goto L20
                    r1.Q(r3)
                L20:
                    throw r2
                */
                throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.m.c.b.S(com.google.protobuf.g, com.google.protobuf.s):void");
            }

            @Override // com.google.protobuf.a0.a, com.google.protobuf.q0.a
            public final q0.a a(n.f fVar, Object obj) {
                super.N(fVar, obj);
                return this;
            }

            @Override // defpackage.bh1, com.google.protobuf.t0
            public final q0 b() {
                return c.m;
            }

            @Override // com.google.protobuf.r0.a, com.google.protobuf.q0.a
            public final q0 build() {
                c cVarD = d();
                if (cVarD.isInitialized()) {
                    return cVarD;
                }
                throw a.AbstractC0042a.z(cVarD);
            }

            @Override // com.google.protobuf.a0.a, com.google.protobuf.q0.a, com.google.protobuf.t0
            public final n.a c() {
                return m.G;
            }

            @Override // com.google.protobuf.a0.a
            public final Object clone() {
                return (b) super.clone();
            }

            @Override // com.google.protobuf.a0.a, com.google.protobuf.q0.a
            public final q0.a f(n.f fVar, Object obj) {
                super.K(fVar, obj);
                return this;
            }

            @Override // com.google.protobuf.a0.a, com.google.protobuf.q0.a
            public final q0.a j0(j1 j1Var) {
                this.f = j1Var;
                H();
                return this;
            }

            @Override // com.google.protobuf.a.AbstractC0042a
            /* JADX INFO: renamed from: v */
            public final /* bridge */ /* synthetic */ a.AbstractC0042a L(com.google.protobuf.g gVar, com.google.protobuf.s sVar) throws Throwable {
                S(gVar, sVar);
                return this;
            }

            @Override // com.google.protobuf.a.AbstractC0042a
            /* JADX INFO: renamed from: x */
            public final a.AbstractC0042a R(q0 q0Var) {
                if (q0Var instanceof c) {
                    Q((c) q0Var);
                } else {
                    super.R(q0Var);
                }
                return this;
            }

            @Override // com.google.protobuf.a0.a, com.google.protobuf.a.AbstractC0042a
            public final void y(j1 j1Var) {
            }

            @Override // defpackage.bh1, com.google.protobuf.t0
            public final r0 b() {
                return c.m;
            }

            @Override // com.google.protobuf.r0.a, com.google.protobuf.q0.a
            public final r0 build() {
                c cVarD = d();
                if (cVarD.isInitialized()) {
                    return cVarD;
                }
                throw a.AbstractC0042a.z(cVarD);
            }
        }

        public c(a0.c cVar) {
            super(cVar);
            this.l = (byte) -1;
        }

        @Override // com.google.protobuf.a0
        public final a0.e M() {
            a0.e eVar = m.H;
            eVar.c(c.class, b.class);
            return eVar;
        }

        @Override // com.google.protobuf.a0
        public final Object O() {
            return new c();
        }

        @Override // com.google.protobuf.r0
        /* JADX INFO: renamed from: W, reason: merged with bridge method [inline-methods] */
        public final b h() {
            if (this == m) {
                return new b();
            }
            b bVar = new b();
            bVar.Q(this);
            return bVar;
        }

        @Override // defpackage.bh1, com.google.protobuf.t0
        public final q0 b() {
            return m;
        }

        @Override // com.google.protobuf.r0, com.google.protobuf.q0
        public final q0.a e() {
            return m.h();
        }

        @Override // com.google.protobuf.a
        public final boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof c)) {
                return super.equals(obj);
            }
            c cVar = (c) obj;
            int i = this.h;
            boolean z = (i & 1) != 0;
            int i2 = cVar.h;
            if (z != ((i2 & 1) != 0)) {
                return false;
            }
            if (((i & 1) != 0) && this.i != cVar.i) {
                return false;
            }
            if (((i & 2) != 0) != ((i2 & 2) != 0)) {
                return false;
            }
            return (!((i & 2) != 0) || this.j == cVar.j) && this.k.equals(cVar.k) && this.f.equals(cVar.f) && T().equals(cVar.T());
        }

        @Override // com.google.protobuf.a
        public final int hashCode() {
            int i = this.memoizedHashCode;
            if (i != 0) {
                return i;
            }
            int iHashCode = m.G.hashCode() + 779;
            if ((this.h & 1) != 0) {
                iHashCode = C0175b.e(iHashCode, 37, 2, 53) + c0.a(this.i);
            }
            if ((this.h & 2) != 0) {
                iHashCode = C0175b.e(iHashCode, 37, 3, 53) + c0.a(this.j);
            }
            if (this.k.size() > 0) {
                iHashCode = C0175b.e(iHashCode, 37, 999, 53) + this.k.hashCode();
            }
            int iHashCode2 = this.f.hashCode() + (com.google.protobuf.a.D(iHashCode, T()) * 29);
            this.memoizedHashCode = iHashCode2;
            return iHashCode2;
        }

        @Override // com.google.protobuf.a0.d, com.google.protobuf.a0, defpackage.bh1
        public final boolean isInitialized() {
            byte b2 = this.l;
            if (b2 == 1) {
                return true;
            }
            if (b2 == 0) {
                return false;
            }
            for (int i = 0; i < this.k.size(); i++) {
                if (!this.k.get(i).isInitialized()) {
                    this.l = (byte) 0;
                    return false;
                }
            }
            if (Q()) {
                this.l = (byte) 1;
                return true;
            }
            this.l = (byte) 0;
            return false;
        }

        @Override // com.google.protobuf.a0, com.google.protobuf.r0
        public final int m() {
            int i = this.e;
            if (i != -1) {
                return i;
            }
            int iJ = (this.h & 1) != 0 ? nu.J(2) + 0 : 0;
            if ((2 & this.h) != 0) {
                iJ += nu.J(3);
            }
            for (int i2 = 0; i2 < this.k.size(); i2++) {
                iJ += nu.a0(999, this.k.get(i2));
            }
            int iM = this.f.m() + S() + iJ;
            this.e = iM;
            return iM;
        }

        @Override // com.google.protobuf.a0, com.google.protobuf.t0
        public final j1 n() {
            return this.f;
        }

        @Override // com.google.protobuf.a0, com.google.protobuf.r0
        public final void p(nu nuVar) {
            a0.d.a aVar = new a0.d.a(this);
            if ((this.h & 1) != 0) {
                nuVar.p0(2, this.i);
            }
            if ((this.h & 2) != 0) {
                nuVar.p0(3, this.j);
            }
            for (int i = 0; i < this.k.size(); i++) {
                nuVar.D0(999, this.k.get(i));
            }
            aVar.a(nuVar);
            this.f.p(nuVar);
        }

        @Override // com.google.protobuf.a0, com.google.protobuf.r0
        public final wv1<c> u() {
            return n;
        }

        @Override // defpackage.bh1, com.google.protobuf.t0
        public final r0 b() {
            return m;
        }

        @Override // com.google.protobuf.r0, com.google.protobuf.q0
        public final r0.a e() {
            return m.h();
        }

        public c() {
            this.l = (byte) -1;
            this.k = Collections.emptyList();
        }
    }

    /* JADX INFO: compiled from: DescriptorProtos.java */
    public static final class d extends a0 implements t0 {
        public static final d l = new d();

        @Deprecated
        public static final a m = new a();
        private static final long serialVersionUID = 0;
        public int g;
        public volatile Object h;
        public int i;
        public e j;
        public byte k;

        /* JADX INFO: compiled from: DescriptorProtos.java */
        public static class a extends com.google.protobuf.c<d> {
            @Override // defpackage.wv1
            public final Object b(com.google.protobuf.g gVar, com.google.protobuf.s sVar) {
                d dVar = new d();
                sVar.getClass();
                j1 j1Var = j1.f;
                j1.a aVar = new j1.a();
                boolean z = false;
                while (!z) {
                    try {
                        try {
                            int iF = gVar.F();
                            if (iF != 0) {
                                if (iF == 10) {
                                    lk.f fVarM = gVar.m();
                                    dVar.g = 1 | dVar.g;
                                    dVar.h = fVarM;
                                } else if (iF == 16) {
                                    dVar.g |= 2;
                                    dVar.i = gVar.t();
                                } else if (iF == 26) {
                                    e.b bVarH = (dVar.g & 4) != 0 ? dVar.j.h() : null;
                                    e eVar = (e) gVar.v(e.m, sVar);
                                    dVar.j = eVar;
                                    if (bVarH != null) {
                                        bVarH.Q(eVar);
                                        dVar.j = bVarH.d();
                                    }
                                    dVar.g |= 4;
                                } else if (!aVar.r(iF, gVar)) {
                                }
                            }
                            z = true;
                        } catch (d0 e) {
                            e.e = dVar;
                            throw e;
                        } catch (IOException e2) {
                            d0 d0Var = new d0(e2);
                            d0Var.e = dVar;
                            throw d0Var;
                        }
                    } catch (Throwable th) {
                        dVar.f = aVar.build();
                        throw th;
                    }
                }
                dVar.f = aVar.build();
                return dVar;
            }
        }

        /* JADX INFO: compiled from: DescriptorProtos.java */
        public static final class b extends a0.a<b> implements t0 {
            public int g;
            public Object h = "";
            public int i;
            public e j;

            @Override // com.google.protobuf.a0.a
            /* JADX INFO: renamed from: A */
            public final a0.a f(n.f fVar, Object obj) {
                super.f(fVar, obj);
                return this;
            }

            @Override // com.google.protobuf.a0.a
            /* JADX INFO: renamed from: B */
            public final a0.a clone() {
                return (b) super.clone();
            }

            @Override // com.google.protobuf.a0.a
            public final a0.e D() {
                a0.e eVar = m.t;
                eVar.c(d.class, b.class);
                return eVar;
            }

            @Override // com.google.protobuf.a0.a
            /* JADX INFO: renamed from: E */
            public final a0.a y(j1 j1Var) {
                return (b) super.y(j1Var);
            }

            @Override // com.google.protobuf.a0.a
            /* JADX INFO: renamed from: I */
            public final a0.a a(n.f fVar, Object obj) {
                super.a(fVar, obj);
                return this;
            }

            @Override // com.google.protobuf.a0.a
            /* JADX INFO: renamed from: J */
            public final a0.a j0(j1 j1Var) {
                this.f = j1Var;
                H();
                return this;
            }

            @Override // com.google.protobuf.r0.a, com.google.protobuf.q0.a
            /* JADX INFO: renamed from: K, reason: merged with bridge method [inline-methods] */
            public final d build() {
                d dVarD = d();
                if (dVarD.isInitialized()) {
                    return dVarD;
                }
                throw a.AbstractC0042a.z(dVarD);
            }

            @Override // com.google.protobuf.a.AbstractC0042a, com.google.protobuf.r0.a
            public final /* bridge */ /* synthetic */ r0.a L(com.google.protobuf.g gVar, com.google.protobuf.s sVar) throws Throwable {
                O(gVar, sVar);
                return this;
            }

            @Override // com.google.protobuf.r0.a, com.google.protobuf.q0.a
            /* JADX INFO: renamed from: M, reason: merged with bridge method [inline-methods] */
            public final d d() {
                d dVar = new d(this);
                int i = this.g;
                int i2 = (i & 1) != 0 ? 1 : 0;
                dVar.h = this.h;
                if ((i & 2) != 0) {
                    dVar.i = this.i;
                    i2 |= 2;
                }
                if ((i & 4) != 0) {
                    dVar.j = this.j;
                    i2 |= 4;
                }
                dVar.g = i2;
                F();
                return dVar;
            }

            public final b N(d dVar) {
                e eVar;
                e eVar2;
                if (dVar == d.l) {
                    return this;
                }
                if ((dVar.g & 1) != 0) {
                    this.g |= 1;
                    this.h = dVar.h;
                    H();
                }
                if ((dVar.g & 2) != 0) {
                    int i = dVar.i;
                    this.g |= 2;
                    this.i = i;
                    H();
                }
                if (dVar.T()) {
                    e eVarS = dVar.S();
                    if ((this.g & 4) == 0 || (eVar = this.j) == null || eVar == (eVar2 = e.l)) {
                        this.j = eVarS;
                    } else {
                        e.b bVarH = eVar2.h();
                        bVarH.Q(eVar);
                        bVarH.Q(eVarS);
                        this.j = bVarH.d();
                    }
                    H();
                    this.g |= 4;
                }
                H();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:15:0x001d  */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public final void O(com.google.protobuf.g r2, com.google.protobuf.s r3) throws java.lang.Throwable {
                /*
                    r1 = this;
                    com.google.protobuf.m$d$a r0 = com.google.protobuf.m.d.m     // Catch: java.lang.Throwable -> Lc com.google.protobuf.d0 -> Le
                    java.lang.Object r2 = r0.b(r2, r3)     // Catch: java.lang.Throwable -> Lc com.google.protobuf.d0 -> Le
                    com.google.protobuf.m$d r2 = (com.google.protobuf.m.d) r2     // Catch: java.lang.Throwable -> Lc com.google.protobuf.d0 -> Le
                    r1.N(r2)
                    return
                Lc:
                    r2 = move-exception
                    goto L1a
                Le:
                    r2 = move-exception
                    com.google.protobuf.r0 r3 = r2.e     // Catch: java.lang.Throwable -> Lc
                    com.google.protobuf.m$d r3 = (com.google.protobuf.m.d) r3     // Catch: java.lang.Throwable -> Lc
                    java.io.IOException r2 = r2.l()     // Catch: java.lang.Throwable -> L18
                    throw r2     // Catch: java.lang.Throwable -> L18
                L18:
                    r2 = move-exception
                    goto L1b
                L1a:
                    r3 = 0
                L1b:
                    if (r3 == 0) goto L20
                    r1.N(r3)
                L20:
                    throw r2
                */
                throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.m.d.b.O(com.google.protobuf.g, com.google.protobuf.s):void");
            }

            @Override // com.google.protobuf.a.AbstractC0042a, com.google.protobuf.q0.a
            public final q0.a R(q0 q0Var) {
                if (q0Var instanceof d) {
                    N((d) q0Var);
                } else {
                    super.R(q0Var);
                }
                return this;
            }

            @Override // com.google.protobuf.a0.a, com.google.protobuf.q0.a
            public final q0.a a(n.f fVar, Object obj) {
                super.a(fVar, obj);
                return this;
            }

            @Override // defpackage.bh1, com.google.protobuf.t0
            public final q0 b() {
                return d.l;
            }

            @Override // com.google.protobuf.a0.a, com.google.protobuf.q0.a, com.google.protobuf.t0
            public final n.a c() {
                return m.s;
            }

            @Override // com.google.protobuf.a0.a
            public final Object clone() {
                return (b) super.clone();
            }

            @Override // com.google.protobuf.a0.a, com.google.protobuf.q0.a
            public final q0.a f(n.f fVar, Object obj) {
                super.f(fVar, obj);
                return this;
            }

            @Override // com.google.protobuf.a0.a, com.google.protobuf.q0.a
            public final q0.a j0(j1 j1Var) {
                this.f = j1Var;
                H();
                return this;
            }

            @Override // com.google.protobuf.a.AbstractC0042a
            /* JADX INFO: renamed from: v */
            public final /* bridge */ /* synthetic */ a.AbstractC0042a L(com.google.protobuf.g gVar, com.google.protobuf.s sVar) throws Throwable {
                O(gVar, sVar);
                return this;
            }

            @Override // com.google.protobuf.a.AbstractC0042a
            /* JADX INFO: renamed from: x */
            public final a.AbstractC0042a R(q0 q0Var) {
                if (q0Var instanceof d) {
                    N((d) q0Var);
                } else {
                    super.R(q0Var);
                }
                return this;
            }

            @Override // com.google.protobuf.a0.a, com.google.protobuf.a.AbstractC0042a
            public final void y(j1 j1Var) {
            }

            @Override // defpackage.bh1, com.google.protobuf.t0
            public final r0 b() {
                return d.l;
            }
        }

        public d(a0.a aVar) {
            super(aVar);
            this.k = (byte) -1;
        }

        public static b U() {
            return l.h();
        }

        @Override // com.google.protobuf.a0
        public final a0.e M() {
            a0.e eVar = m.t;
            eVar.c(d.class, b.class);
            return eVar;
        }

        @Override // com.google.protobuf.a0
        public final Object O() {
            return new d();
        }

        public final String Q() {
            Object obj = this.h;
            if (obj instanceof String) {
                return (String) obj;
            }
            lk lkVar = (lk) obj;
            String strZ = lkVar.z();
            if (lkVar.q()) {
                this.h = strZ;
            }
            return strZ;
        }

        public final e S() {
            e eVar = this.j;
            return eVar == null ? e.l : eVar;
        }

        public final boolean T() {
            return (this.g & 4) != 0;
        }

        @Override // com.google.protobuf.r0
        /* JADX INFO: renamed from: V, reason: merged with bridge method [inline-methods] */
        public final b h() {
            if (this == l) {
                return new b();
            }
            b bVar = new b();
            bVar.N(this);
            return bVar;
        }

        @Override // defpackage.bh1, com.google.protobuf.t0
        public final q0 b() {
            return l;
        }

        @Override // com.google.protobuf.r0, com.google.protobuf.q0
        public final q0.a e() {
            return U();
        }

        @Override // com.google.protobuf.a
        public final boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof d)) {
                return super.equals(obj);
            }
            d dVar = (d) obj;
            int i = this.g;
            if (((i & 1) != 0) != ((dVar.g & 1) != 0)) {
                return false;
            }
            if (((i & 1) != 0) && !Q().equals(dVar.Q())) {
                return false;
            }
            int i2 = this.g;
            if (((i2 & 2) != 0) != ((dVar.g & 2) != 0)) {
                return false;
            }
            if ((!((i2 & 2) != 0) || this.i == dVar.i) && T() == dVar.T()) {
                return (!T() || S().equals(dVar.S())) && this.f.equals(dVar.f);
            }
            return false;
        }

        @Override // com.google.protobuf.a
        public final int hashCode() {
            int i = this.memoizedHashCode;
            if (i != 0) {
                return i;
            }
            int iHashCode = m.s.hashCode() + 779;
            if ((this.g & 1) != 0) {
                iHashCode = C0175b.e(iHashCode, 37, 1, 53) + Q().hashCode();
            }
            if ((this.g & 2) != 0) {
                iHashCode = C0175b.e(iHashCode, 37, 2, 53) + this.i;
            }
            if (T()) {
                iHashCode = C0175b.e(iHashCode, 37, 3, 53) + S().hashCode();
            }
            int iHashCode2 = this.f.hashCode() + (iHashCode * 29);
            this.memoizedHashCode = iHashCode2;
            return iHashCode2;
        }

        @Override // com.google.protobuf.a0, defpackage.bh1
        public final boolean isInitialized() {
            byte b2 = this.k;
            if (b2 == 1) {
                return true;
            }
            if (b2 == 0) {
                return false;
            }
            if (!T() || S().isInitialized()) {
                this.k = (byte) 1;
                return true;
            }
            this.k = (byte) 0;
            return false;
        }

        @Override // com.google.protobuf.a0, com.google.protobuf.r0
        public final int m() {
            int i = this.e;
            if (i != -1) {
                return i;
            }
            int iH = (this.g & 1) != 0 ? 0 + a0.H(1, this.h) : 0;
            if ((this.g & 2) != 0) {
                iH += nu.S(2, this.i);
            }
            if ((this.g & 4) != 0) {
                iH += nu.a0(3, S());
            }
            int iM = this.f.m() + iH;
            this.e = iM;
            return iM;
        }

        @Override // com.google.protobuf.a0, com.google.protobuf.t0
        public final j1 n() {
            return this.f;
        }

        @Override // com.google.protobuf.a0, com.google.protobuf.r0
        public final void p(nu nuVar) {
            if ((this.g & 1) != 0) {
                a0.P(nuVar, 1, this.h);
            }
            if ((this.g & 2) != 0) {
                nuVar.z0(2, this.i);
            }
            if ((this.g & 4) != 0) {
                nuVar.D0(3, S());
            }
            this.f.p(nuVar);
        }

        @Override // com.google.protobuf.a0, com.google.protobuf.r0
        public final wv1<d> u() {
            return m;
        }

        @Override // defpackage.bh1, com.google.protobuf.t0
        public final r0 b() {
            return l;
        }

        @Override // com.google.protobuf.r0, com.google.protobuf.q0
        public final r0.a e() {
            return U();
        }

        public d() {
            this.k = (byte) -1;
            this.h = "";
        }
    }

    /* JADX INFO: compiled from: DescriptorProtos.java */
    public static final class e extends a0.d<e> {
        public static final e l = new e();

        @Deprecated
        public static final a m = new a();
        private static final long serialVersionUID = 0;
        public int h;
        public boolean i;
        public List<s> j;
        public byte k;

        /* JADX INFO: compiled from: DescriptorProtos.java */
        public static class a extends com.google.protobuf.c<e> {
            @Override // defpackage.wv1
            public final Object b(com.google.protobuf.g gVar, com.google.protobuf.s sVar) {
                e eVar = new e();
                sVar.getClass();
                j1 j1Var = j1.f;
                j1.a aVar = new j1.a();
                boolean z = false;
                int i = 0;
                while (!z) {
                    try {
                        try {
                            try {
                                int iF = gVar.F();
                                if (iF != 0) {
                                    if (iF == 8) {
                                        eVar.h |= 1;
                                        eVar.i = gVar.l();
                                    } else if (iF == 7994) {
                                        if ((i & 2) == 0) {
                                            eVar.j = new ArrayList();
                                            i |= 2;
                                        }
                                        eVar.j.add((s) gVar.v(s.q, sVar));
                                    } else if (!eVar.V(gVar, aVar, sVar, iF)) {
                                    }
                                }
                                z = true;
                            } catch (IOException e) {
                                d0 d0Var = new d0(e);
                                d0Var.e = eVar;
                                throw d0Var;
                            }
                        } catch (d0 e2) {
                            e2.e = eVar;
                            throw e2;
                        }
                    } catch (Throwable th) {
                        if ((i & 2) != 0) {
                            eVar.j = Collections.unmodifiableList(eVar.j);
                        }
                        eVar.f = aVar.build();
                        eVar.U();
                        throw th;
                    }
                }
                if ((i & 2) != 0) {
                    eVar.j = Collections.unmodifiableList(eVar.j);
                }
                eVar.f = aVar.build();
                eVar.U();
                return eVar;
            }
        }

        /* JADX INFO: compiled from: DescriptorProtos.java */
        public static final class b extends a0.c<e, b> {
            public int h;
            public boolean i;
            public List<s> j = Collections.emptyList();

            @Override // com.google.protobuf.a0.a
            /* JADX INFO: renamed from: A */
            public final a0.a f(n.f fVar, Object obj) {
                super.K(fVar, obj);
                return this;
            }

            @Override // com.google.protobuf.a0.a
            /* JADX INFO: renamed from: B */
            public final a0.a clone() {
                return (b) super.clone();
            }

            @Override // com.google.protobuf.a0.a
            public final a0.e D() {
                a0.e eVar = m.f38J;
                eVar.c(e.class, b.class);
                return eVar;
            }

            @Override // com.google.protobuf.a0.a
            /* JADX INFO: renamed from: E */
            public final a0.a y(j1 j1Var) {
                return (b) super.y(j1Var);
            }

            @Override // com.google.protobuf.a0.a
            /* JADX INFO: renamed from: I */
            public final a0.a a(n.f fVar, Object obj) {
                return (b) super.N(fVar, obj);
            }

            @Override // com.google.protobuf.a0.a
            /* JADX INFO: renamed from: J */
            public final a0.a j0(j1 j1Var) {
                this.f = j1Var;
                H();
                return this;
            }

            @Override // com.google.protobuf.a.AbstractC0042a, com.google.protobuf.r0.a
            public final /* bridge */ /* synthetic */ r0.a L(com.google.protobuf.g gVar, com.google.protobuf.s sVar) throws Throwable {
                S(gVar, sVar);
                return this;
            }

            @Override // com.google.protobuf.r0.a, com.google.protobuf.q0.a
            /* JADX INFO: renamed from: P, reason: merged with bridge method [inline-methods] */
            public final e d() {
                int i;
                e eVar = new e(this);
                int i2 = this.h;
                if ((i2 & 1) != 0) {
                    eVar.i = this.i;
                    i = 1;
                } else {
                    i = 0;
                }
                if ((i2 & 2) != 0) {
                    this.j = Collections.unmodifiableList(this.j);
                    this.h &= -3;
                }
                eVar.j = this.j;
                eVar.h = i;
                F();
                return eVar;
            }

            public final b Q(e eVar) {
                if (eVar == e.l) {
                    return this;
                }
                if ((eVar.h & 1) != 0) {
                    boolean z = eVar.i;
                    this.h = 1 | this.h;
                    this.i = z;
                    H();
                }
                if (!eVar.j.isEmpty()) {
                    if (this.j.isEmpty()) {
                        this.j = eVar.j;
                        this.h &= -3;
                    } else {
                        if ((this.h & 2) == 0) {
                            this.j = new ArrayList(this.j);
                            this.h |= 2;
                        }
                        this.j.addAll(eVar.j);
                    }
                    H();
                }
                M(eVar);
                H();
                return this;
            }

            @Override // com.google.protobuf.a.AbstractC0042a, com.google.protobuf.q0.a
            public final q0.a R(q0 q0Var) {
                if (q0Var instanceof e) {
                    Q((e) q0Var);
                } else {
                    super.R(q0Var);
                }
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:15:0x001d  */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public final void S(com.google.protobuf.g r2, com.google.protobuf.s r3) throws java.lang.Throwable {
                /*
                    r1 = this;
                    com.google.protobuf.m$e$a r0 = com.google.protobuf.m.e.m     // Catch: java.lang.Throwable -> Lc com.google.protobuf.d0 -> Le
                    java.lang.Object r2 = r0.b(r2, r3)     // Catch: java.lang.Throwable -> Lc com.google.protobuf.d0 -> Le
                    com.google.protobuf.m$e r2 = (com.google.protobuf.m.e) r2     // Catch: java.lang.Throwable -> Lc com.google.protobuf.d0 -> Le
                    r1.Q(r2)
                    return
                Lc:
                    r2 = move-exception
                    goto L1a
                Le:
                    r2 = move-exception
                    com.google.protobuf.r0 r3 = r2.e     // Catch: java.lang.Throwable -> Lc
                    com.google.protobuf.m$e r3 = (com.google.protobuf.m.e) r3     // Catch: java.lang.Throwable -> Lc
                    java.io.IOException r2 = r2.l()     // Catch: java.lang.Throwable -> L18
                    throw r2     // Catch: java.lang.Throwable -> L18
                L18:
                    r2 = move-exception
                    goto L1b
                L1a:
                    r3 = 0
                L1b:
                    if (r3 == 0) goto L20
                    r1.Q(r3)
                L20:
                    throw r2
                */
                throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.m.e.b.S(com.google.protobuf.g, com.google.protobuf.s):void");
            }

            @Override // com.google.protobuf.a0.a, com.google.protobuf.q0.a
            public final q0.a a(n.f fVar, Object obj) {
                super.N(fVar, obj);
                return this;
            }

            @Override // defpackage.bh1, com.google.protobuf.t0
            public final q0 b() {
                return e.l;
            }

            @Override // com.google.protobuf.r0.a, com.google.protobuf.q0.a
            public final q0 build() {
                e eVarD = d();
                if (eVarD.isInitialized()) {
                    return eVarD;
                }
                throw a.AbstractC0042a.z(eVarD);
            }

            @Override // com.google.protobuf.a0.a, com.google.protobuf.q0.a, com.google.protobuf.t0
            public final n.a c() {
                return m.I;
            }

            @Override // com.google.protobuf.a0.a
            public final Object clone() {
                return (b) super.clone();
            }

            @Override // com.google.protobuf.a0.a, com.google.protobuf.q0.a
            public final q0.a f(n.f fVar, Object obj) {
                super.K(fVar, obj);
                return this;
            }

            @Override // com.google.protobuf.a0.a, com.google.protobuf.q0.a
            public final q0.a j0(j1 j1Var) {
                this.f = j1Var;
                H();
                return this;
            }

            @Override // com.google.protobuf.a.AbstractC0042a
            /* JADX INFO: renamed from: v */
            public final /* bridge */ /* synthetic */ a.AbstractC0042a L(com.google.protobuf.g gVar, com.google.protobuf.s sVar) throws Throwable {
                S(gVar, sVar);
                return this;
            }

            @Override // com.google.protobuf.a.AbstractC0042a
            /* JADX INFO: renamed from: x */
            public final a.AbstractC0042a R(q0 q0Var) {
                if (q0Var instanceof e) {
                    Q((e) q0Var);
                } else {
                    super.R(q0Var);
                }
                return this;
            }

            @Override // com.google.protobuf.a0.a, com.google.protobuf.a.AbstractC0042a
            public final void y(j1 j1Var) {
            }

            @Override // defpackage.bh1, com.google.protobuf.t0
            public final r0 b() {
                return e.l;
            }

            @Override // com.google.protobuf.r0.a, com.google.protobuf.q0.a
            public final r0 build() {
                e eVarD = d();
                if (eVarD.isInitialized()) {
                    return eVarD;
                }
                throw a.AbstractC0042a.z(eVarD);
            }
        }

        public e(a0.c cVar) {
            super(cVar);
            this.k = (byte) -1;
        }

        @Override // com.google.protobuf.a0
        public final a0.e M() {
            a0.e eVar = m.f38J;
            eVar.c(e.class, b.class);
            return eVar;
        }

        @Override // com.google.protobuf.a0
        public final Object O() {
            return new e();
        }

        @Override // com.google.protobuf.r0
        /* JADX INFO: renamed from: W, reason: merged with bridge method [inline-methods] */
        public final b h() {
            return this == l ? new b() : new b().Q(this);
        }

        @Override // defpackage.bh1, com.google.protobuf.t0
        public final q0 b() {
            return l;
        }

        @Override // com.google.protobuf.r0, com.google.protobuf.q0
        public final q0.a e() {
            return l.h();
        }

        @Override // com.google.protobuf.a
        public final boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof e)) {
                return super.equals(obj);
            }
            e eVar = (e) obj;
            int i = this.h;
            if (((i & 1) != 0) != ((eVar.h & 1) != 0)) {
                return false;
            }
            return (!((i & 1) != 0) || this.i == eVar.i) && this.j.equals(eVar.j) && this.f.equals(eVar.f) && T().equals(eVar.T());
        }

        @Override // com.google.protobuf.a
        public final int hashCode() {
            int i = this.memoizedHashCode;
            if (i != 0) {
                return i;
            }
            int iHashCode = m.I.hashCode() + 779;
            if ((this.h & 1) != 0) {
                iHashCode = C0175b.e(iHashCode, 37, 1, 53) + c0.a(this.i);
            }
            if (this.j.size() > 0) {
                iHashCode = C0175b.e(iHashCode, 37, 999, 53) + this.j.hashCode();
            }
            int iHashCode2 = this.f.hashCode() + (com.google.protobuf.a.D(iHashCode, T()) * 29);
            this.memoizedHashCode = iHashCode2;
            return iHashCode2;
        }

        @Override // com.google.protobuf.a0.d, com.google.protobuf.a0, defpackage.bh1
        public final boolean isInitialized() {
            byte b2 = this.k;
            if (b2 == 1) {
                return true;
            }
            if (b2 == 0) {
                return false;
            }
            for (int i = 0; i < this.j.size(); i++) {
                if (!this.j.get(i).isInitialized()) {
                    this.k = (byte) 0;
                    return false;
                }
            }
            if (Q()) {
                this.k = (byte) 1;
                return true;
            }
            this.k = (byte) 0;
            return false;
        }

        @Override // com.google.protobuf.a0, com.google.protobuf.r0
        public final int m() {
            int i = this.e;
            if (i != -1) {
                return i;
            }
            int iJ = (this.h & 1) != 0 ? nu.J(1) + 0 : 0;
            for (int i2 = 0; i2 < this.j.size(); i2++) {
                iJ += nu.a0(999, this.j.get(i2));
            }
            int iM = this.f.m() + S() + iJ;
            this.e = iM;
            return iM;
        }

        @Override // com.google.protobuf.a0, com.google.protobuf.t0
        public final j1 n() {
            return this.f;
        }

        @Override // com.google.protobuf.a0, com.google.protobuf.r0
        public final void p(nu nuVar) {
            a0.d.a aVar = new a0.d.a(this);
            if ((this.h & 1) != 0) {
                nuVar.p0(1, this.i);
            }
            for (int i = 0; i < this.j.size(); i++) {
                nuVar.D0(999, this.j.get(i));
            }
            aVar.a(nuVar);
            this.f.p(nuVar);
        }

        @Override // com.google.protobuf.a0, com.google.protobuf.r0
        public final wv1<e> u() {
            return m;
        }

        @Override // defpackage.bh1, com.google.protobuf.t0
        public final r0 b() {
            return l;
        }

        @Override // com.google.protobuf.r0, com.google.protobuf.q0
        public final r0.a e() {
            return l.h();
        }

        public e() {
            this.k = (byte) -1;
            this.j = Collections.emptyList();
        }
    }

    /* JADX INFO: compiled from: DescriptorProtos.java */
    public static final class f extends a0.d<f> {
        public static final f j = new f();

        @Deprecated
        public static final a k = new a();
        private static final long serialVersionUID = 0;
        public List<s> h;
        public byte i;

        /* JADX INFO: compiled from: DescriptorProtos.java */
        public static class a extends com.google.protobuf.c<f> {
            @Override // defpackage.wv1
            public final Object b(com.google.protobuf.g gVar, com.google.protobuf.s sVar) {
                f fVar = new f();
                sVar.getClass();
                j1 j1Var = j1.f;
                j1.a aVar = new j1.a();
                boolean z = false;
                boolean z2 = false;
                while (!z) {
                    try {
                        try {
                            int iF = gVar.F();
                            if (iF != 0) {
                                if (iF == 7994) {
                                    if (!(z2 & true)) {
                                        fVar.h = new ArrayList();
                                        z2 |= true;
                                    }
                                    fVar.h.add((s) gVar.v(s.q, sVar));
                                } else if (!fVar.V(gVar, aVar, sVar, iF)) {
                                }
                            }
                            z = true;
                        } catch (d0 e) {
                            e.e = fVar;
                            throw e;
                        } catch (IOException e2) {
                            d0 d0Var = new d0(e2);
                            d0Var.e = fVar;
                            throw d0Var;
                        }
                    } finally {
                        if (z2 & true) {
                            fVar.h = Collections.unmodifiableList(fVar.h);
                        }
                        fVar.f = aVar.build();
                        fVar.U();
                    }
                }
                return fVar;
            }
        }

        /* JADX INFO: compiled from: DescriptorProtos.java */
        public static final class b extends a0.c<f, b> {
            public int h;
            public List<s> i = Collections.emptyList();

            @Override // com.google.protobuf.a0.a
            /* JADX INFO: renamed from: A */
            public final a0.a f(n.f fVar, Object obj) {
                super.K(fVar, obj);
                return this;
            }

            @Override // com.google.protobuf.a0.a
            /* JADX INFO: renamed from: B */
            public final a0.a clone() {
                return (b) super.clone();
            }

            @Override // com.google.protobuf.a0.a
            public final a0.e D() {
                a0.e eVar = m.j;
                eVar.c(f.class, b.class);
                return eVar;
            }

            @Override // com.google.protobuf.a0.a
            /* JADX INFO: renamed from: E */
            public final a0.a y(j1 j1Var) {
                return (b) super.y(j1Var);
            }

            @Override // com.google.protobuf.a0.a
            /* JADX INFO: renamed from: I */
            public final a0.a a(n.f fVar, Object obj) {
                return (b) super.N(fVar, obj);
            }

            @Override // com.google.protobuf.a0.a
            /* JADX INFO: renamed from: J */
            public final a0.a j0(j1 j1Var) {
                this.f = j1Var;
                H();
                return this;
            }

            @Override // com.google.protobuf.a.AbstractC0042a, com.google.protobuf.r0.a
            public final /* bridge */ /* synthetic */ r0.a L(com.google.protobuf.g gVar, com.google.protobuf.s sVar) throws Throwable {
                S(gVar, sVar);
                return this;
            }

            @Override // com.google.protobuf.r0.a, com.google.protobuf.q0.a
            /* JADX INFO: renamed from: P, reason: merged with bridge method [inline-methods] */
            public final f d() {
                f fVar = new f(this);
                if ((this.h & 1) != 0) {
                    this.i = Collections.unmodifiableList(this.i);
                    this.h &= -2;
                }
                fVar.h = this.i;
                F();
                return fVar;
            }

            public final void Q(f fVar) {
                if (fVar == f.j) {
                    return;
                }
                if (!fVar.h.isEmpty()) {
                    if (this.i.isEmpty()) {
                        this.i = fVar.h;
                        this.h &= -2;
                    } else {
                        if ((this.h & 1) == 0) {
                            this.i = new ArrayList(this.i);
                            this.h |= 1;
                        }
                        this.i.addAll(fVar.h);
                    }
                    H();
                }
                M(fVar);
                H();
            }

            @Override // com.google.protobuf.a.AbstractC0042a, com.google.protobuf.q0.a
            public final q0.a R(q0 q0Var) {
                if (q0Var instanceof f) {
                    Q((f) q0Var);
                } else {
                    super.R(q0Var);
                }
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:15:0x001d  */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public final void S(com.google.protobuf.g r2, com.google.protobuf.s r3) throws java.lang.Throwable {
                /*
                    r1 = this;
                    com.google.protobuf.m$f$a r0 = com.google.protobuf.m.f.k     // Catch: java.lang.Throwable -> Lc com.google.protobuf.d0 -> Le
                    java.lang.Object r2 = r0.b(r2, r3)     // Catch: java.lang.Throwable -> Lc com.google.protobuf.d0 -> Le
                    com.google.protobuf.m$f r2 = (com.google.protobuf.m.f) r2     // Catch: java.lang.Throwable -> Lc com.google.protobuf.d0 -> Le
                    r1.Q(r2)
                    return
                Lc:
                    r2 = move-exception
                    goto L1a
                Le:
                    r2 = move-exception
                    com.google.protobuf.r0 r3 = r2.e     // Catch: java.lang.Throwable -> Lc
                    com.google.protobuf.m$f r3 = (com.google.protobuf.m.f) r3     // Catch: java.lang.Throwable -> Lc
                    java.io.IOException r2 = r2.l()     // Catch: java.lang.Throwable -> L18
                    throw r2     // Catch: java.lang.Throwable -> L18
                L18:
                    r2 = move-exception
                    goto L1b
                L1a:
                    r3 = 0
                L1b:
                    if (r3 == 0) goto L20
                    r1.Q(r3)
                L20:
                    throw r2
                */
                throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.m.f.b.S(com.google.protobuf.g, com.google.protobuf.s):void");
            }

            @Override // com.google.protobuf.a0.a, com.google.protobuf.q0.a
            public final q0.a a(n.f fVar, Object obj) {
                super.N(fVar, obj);
                return this;
            }

            @Override // defpackage.bh1, com.google.protobuf.t0
            public final q0 b() {
                return f.j;
            }

            @Override // com.google.protobuf.r0.a, com.google.protobuf.q0.a
            public final q0 build() {
                f fVarD = d();
                if (fVarD.isInitialized()) {
                    return fVarD;
                }
                throw a.AbstractC0042a.z(fVarD);
            }

            @Override // com.google.protobuf.a0.a, com.google.protobuf.q0.a, com.google.protobuf.t0
            public final n.a c() {
                return m.i;
            }

            @Override // com.google.protobuf.a0.a
            public final Object clone() {
                return (b) super.clone();
            }

            @Override // com.google.protobuf.a0.a, com.google.protobuf.q0.a
            public final q0.a f(n.f fVar, Object obj) {
                super.K(fVar, obj);
                return this;
            }

            @Override // com.google.protobuf.a0.a, com.google.protobuf.q0.a
            public final q0.a j0(j1 j1Var) {
                this.f = j1Var;
                H();
                return this;
            }

            @Override // com.google.protobuf.a.AbstractC0042a
            /* JADX INFO: renamed from: v */
            public final /* bridge */ /* synthetic */ a.AbstractC0042a L(com.google.protobuf.g gVar, com.google.protobuf.s sVar) throws Throwable {
                S(gVar, sVar);
                return this;
            }

            @Override // com.google.protobuf.a.AbstractC0042a
            /* JADX INFO: renamed from: x */
            public final a.AbstractC0042a R(q0 q0Var) {
                if (q0Var instanceof f) {
                    Q((f) q0Var);
                } else {
                    super.R(q0Var);
                }
                return this;
            }

            @Override // com.google.protobuf.a0.a, com.google.protobuf.a.AbstractC0042a
            public final void y(j1 j1Var) {
            }

            @Override // defpackage.bh1, com.google.protobuf.t0
            public final r0 b() {
                return f.j;
            }

            @Override // com.google.protobuf.r0.a, com.google.protobuf.q0.a
            public final r0 build() {
                f fVarD = d();
                if (fVarD.isInitialized()) {
                    return fVarD;
                }
                throw a.AbstractC0042a.z(fVarD);
            }
        }

        public f(a0.c cVar) {
            super(cVar);
            this.i = (byte) -1;
        }

        @Override // com.google.protobuf.a0
        public final a0.e M() {
            a0.e eVar = m.j;
            eVar.c(f.class, b.class);
            return eVar;
        }

        @Override // com.google.protobuf.a0
        public final Object O() {
            return new f();
        }

        @Override // com.google.protobuf.r0
        /* JADX INFO: renamed from: W, reason: merged with bridge method [inline-methods] */
        public final b h() {
            if (this == j) {
                return new b();
            }
            b bVar = new b();
            bVar.Q(this);
            return bVar;
        }

        @Override // defpackage.bh1, com.google.protobuf.t0
        public final q0 b() {
            return j;
        }

        @Override // com.google.protobuf.r0, com.google.protobuf.q0
        public final q0.a e() {
            return j.h();
        }

        @Override // com.google.protobuf.a
        public final boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof f)) {
                return super.equals(obj);
            }
            f fVar = (f) obj;
            return this.h.equals(fVar.h) && this.f.equals(fVar.f) && T().equals(fVar.T());
        }

        @Override // com.google.protobuf.a
        public final int hashCode() {
            int i = this.memoizedHashCode;
            if (i != 0) {
                return i;
            }
            int iHashCode = m.i.hashCode() + 779;
            if (this.h.size() > 0) {
                iHashCode = C0175b.e(iHashCode, 37, 999, 53) + this.h.hashCode();
            }
            int iHashCode2 = this.f.hashCode() + (com.google.protobuf.a.D(iHashCode, T()) * 29);
            this.memoizedHashCode = iHashCode2;
            return iHashCode2;
        }

        @Override // com.google.protobuf.a0.d, com.google.protobuf.a0, defpackage.bh1
        public final boolean isInitialized() {
            byte b2 = this.i;
            if (b2 == 1) {
                return true;
            }
            if (b2 == 0) {
                return false;
            }
            for (int i = 0; i < this.h.size(); i++) {
                if (!this.h.get(i).isInitialized()) {
                    this.i = (byte) 0;
                    return false;
                }
            }
            if (Q()) {
                this.i = (byte) 1;
                return true;
            }
            this.i = (byte) 0;
            return false;
        }

        @Override // com.google.protobuf.a0, com.google.protobuf.r0
        public final int m() {
            int i = this.e;
            if (i != -1) {
                return i;
            }
            int iA0 = 0;
            for (int i2 = 0; i2 < this.h.size(); i2++) {
                iA0 += nu.a0(999, this.h.get(i2));
            }
            int iM = this.f.m() + S() + iA0;
            this.e = iM;
            return iM;
        }

        @Override // com.google.protobuf.a0, com.google.protobuf.t0
        public final j1 n() {
            return this.f;
        }

        @Override // com.google.protobuf.a0, com.google.protobuf.r0
        public final void p(nu nuVar) {
            a0.d.a aVar = new a0.d.a(this);
            for (int i = 0; i < this.h.size(); i++) {
                nuVar.D0(999, this.h.get(i));
            }
            aVar.a(nuVar);
            this.f.p(nuVar);
        }

        @Override // com.google.protobuf.a0, com.google.protobuf.r0
        public final wv1<f> u() {
            return k;
        }

        @Override // defpackage.bh1, com.google.protobuf.t0
        public final r0 b() {
            return j;
        }

        @Override // com.google.protobuf.r0, com.google.protobuf.q0
        public final r0.a e() {
            return j.h();
        }

        public f() {
            this.i = (byte) -1;
            this.h = Collections.emptyList();
        }
    }

    /* JADX INFO: compiled from: DescriptorProtos.java */
    public static final class g extends a0 implements t0 {
        private static final long serialVersionUID = 0;
        public static final g t = new g();

        @Deprecated
        public static final a u = new a();
        public int g;
        public volatile Object h;
        public int i;
        public int j;
        public int k;
        public volatile Object l;
        public volatile Object m;
        public volatile Object n;
        public int o;
        public volatile Object p;
        public h q;
        public boolean r;
        public byte s;

        /* JADX INFO: compiled from: DescriptorProtos.java */
        public static class a extends com.google.protobuf.c<g> {
            @Override // defpackage.wv1
            public final Object b(com.google.protobuf.g gVar, com.google.protobuf.s sVar) {
                g gVar2 = new g();
                sVar.getClass();
                j1 j1Var = j1.f;
                j1.a aVar = new j1.a();
                boolean z = false;
                while (!z) {
                    try {
                        try {
                            int iF = gVar.F();
                            switch (iF) {
                                case 0:
                                    z = true;
                                    break;
                                case 10:
                                    lk.f fVarM = gVar.m();
                                    gVar2.g = 1 | gVar2.g;
                                    gVar2.h = fVarM;
                                    break;
                                case 18:
                                    lk.f fVarM2 = gVar.m();
                                    gVar2.g |= 32;
                                    gVar2.m = fVarM2;
                                    break;
                                case 24:
                                    gVar2.g |= 2;
                                    gVar2.i = gVar.t();
                                    break;
                                case 32:
                                    int iO = gVar.o();
                                    if (c.a(iO) == null) {
                                        aVar.y(4, iO);
                                    } else {
                                        gVar2.g |= 4;
                                        gVar2.j = iO;
                                    }
                                    break;
                                case 40:
                                    int iO2 = gVar.o();
                                    if (d.a(iO2) == null) {
                                        aVar.y(5, iO2);
                                    } else {
                                        gVar2.g |= 8;
                                        gVar2.k = iO2;
                                    }
                                    break;
                                case 50:
                                    lk.f fVarM3 = gVar.m();
                                    gVar2.g |= 16;
                                    gVar2.l = fVarM3;
                                    break;
                                case 58:
                                    lk.f fVarM4 = gVar.m();
                                    gVar2.g |= 64;
                                    gVar2.n = fVarM4;
                                    break;
                                case 66:
                                    h.b bVarH = (gVar2.g & 512) != 0 ? gVar2.q.h() : null;
                                    h hVar = (h) gVar.v(h.r, sVar);
                                    gVar2.q = hVar;
                                    if (bVarH != null) {
                                        bVarH.Q(hVar);
                                        gVar2.q = bVarH.d();
                                    }
                                    gVar2.g |= 512;
                                    break;
                                case 72:
                                    gVar2.g |= 128;
                                    gVar2.o = gVar.t();
                                    break;
                                case 82:
                                    lk.f fVarM5 = gVar.m();
                                    gVar2.g |= 256;
                                    gVar2.p = fVarM5;
                                    break;
                                case 136:
                                    gVar2.g |= 1024;
                                    gVar2.r = gVar.l();
                                    break;
                                default:
                                    if (!aVar.r(iF, gVar)) {
                                        z = true;
                                    }
                                    break;
                            }
                        } catch (d0 e) {
                            e.e = gVar2;
                            throw e;
                        } catch (IOException e2) {
                            d0 d0Var = new d0(e2);
                            d0Var.e = gVar2;
                            throw d0Var;
                        }
                    } catch (Throwable th) {
                        gVar2.f = aVar.build();
                        throw th;
                    }
                }
                gVar2.f = aVar.build();
                return gVar2;
            }
        }

        /* JADX INFO: compiled from: DescriptorProtos.java */
        public static final class b extends a0.a<b> implements t0 {
            public int g;
            public int i;
            public int o;
            public h q;
            public boolean r;
            public Object h = "";
            public int j = 1;
            public int k = 1;
            public Object l = "";
            public Object m = "";
            public Object n = "";
            public Object p = "";

            @Override // com.google.protobuf.a0.a
            /* JADX INFO: renamed from: A */
            public final a0.a f(n.f fVar, Object obj) {
                super.f(fVar, obj);
                return this;
            }

            @Override // com.google.protobuf.a0.a
            /* JADX INFO: renamed from: B */
            public final a0.a clone() {
                return (b) super.clone();
            }

            @Override // com.google.protobuf.a0.a
            public final a0.e D() {
                a0.e eVar = m.l;
                eVar.c(g.class, b.class);
                return eVar;
            }

            @Override // com.google.protobuf.a0.a
            /* JADX INFO: renamed from: E */
            public final a0.a y(j1 j1Var) {
                return (b) super.y(j1Var);
            }

            @Override // com.google.protobuf.a0.a
            /* JADX INFO: renamed from: I */
            public final a0.a a(n.f fVar, Object obj) {
                super.a(fVar, obj);
                return this;
            }

            @Override // com.google.protobuf.a0.a
            /* JADX INFO: renamed from: J */
            public final a0.a j0(j1 j1Var) {
                this.f = j1Var;
                H();
                return this;
            }

            @Override // com.google.protobuf.r0.a, com.google.protobuf.q0.a
            /* JADX INFO: renamed from: K, reason: merged with bridge method [inline-methods] */
            public final g d() {
                g gVar = new g(this);
                int i = this.g;
                int i2 = (i & 1) != 0 ? 1 : 0;
                gVar.h = this.h;
                if ((i & 2) != 0) {
                    gVar.i = this.i;
                    i2 |= 2;
                }
                if ((i & 4) != 0) {
                    i2 |= 4;
                }
                gVar.j = this.j;
                if ((i & 8) != 0) {
                    i2 |= 8;
                }
                gVar.k = this.k;
                if ((i & 16) != 0) {
                    i2 |= 16;
                }
                gVar.l = this.l;
                if ((i & 32) != 0) {
                    i2 |= 32;
                }
                gVar.m = this.m;
                if ((i & 64) != 0) {
                    i2 |= 64;
                }
                gVar.n = this.n;
                if ((i & 128) != 0) {
                    gVar.o = this.o;
                    i2 |= 128;
                }
                if ((i & 256) != 0) {
                    i2 |= 256;
                }
                gVar.p = this.p;
                if ((i & 512) != 0) {
                    gVar.q = this.q;
                    i2 |= 512;
                }
                if ((i & 1024) != 0) {
                    gVar.r = this.r;
                    i2 |= 1024;
                }
                gVar.g = i2;
                F();
                return gVar;
            }

            @Override // com.google.protobuf.a.AbstractC0042a, com.google.protobuf.r0.a
            public final /* bridge */ /* synthetic */ r0.a L(com.google.protobuf.g gVar, com.google.protobuf.s sVar) throws Throwable {
                N(gVar, sVar);
                return this;
            }

            public final void M(g gVar) {
                h hVar;
                h hVar2;
                if (gVar == g.t) {
                    return;
                }
                if ((gVar.g & 1) != 0) {
                    this.g |= 1;
                    this.h = gVar.h;
                    H();
                }
                if ((gVar.g & 2) != 0) {
                    int i = gVar.i;
                    this.g |= 2;
                    this.i = i;
                    H();
                }
                if ((gVar.g & 4) != 0) {
                    c cVarA = c.a(gVar.j);
                    if (cVarA == null) {
                        cVarA = c.LABEL_OPTIONAL;
                    }
                    this.g |= 4;
                    this.j = cVarA.e;
                    H();
                }
                if (gVar.b0()) {
                    d dVarA = d.a(gVar.k);
                    if (dVarA == null) {
                        dVarA = d.TYPE_DOUBLE;
                    }
                    this.g |= 8;
                    this.k = dVarA.e;
                    H();
                }
                if (gVar.c0()) {
                    this.g |= 16;
                    this.l = gVar.l;
                    H();
                }
                if (gVar.Y()) {
                    this.g |= 32;
                    this.m = gVar.m;
                    H();
                }
                if (gVar.X()) {
                    this.g |= 64;
                    this.n = gVar.n;
                    H();
                }
                if (gVar.Z()) {
                    int i2 = gVar.o;
                    this.g |= 128;
                    this.o = i2;
                    H();
                }
                if ((gVar.g & 256) != 0) {
                    this.g |= 256;
                    this.p = gVar.p;
                    H();
                }
                if (gVar.a0()) {
                    h hVarV = gVar.V();
                    if ((this.g & 512) == 0 || (hVar = this.q) == null || hVar == (hVar2 = h.q)) {
                        this.q = hVarV;
                    } else {
                        h.b bVarH = hVar2.h();
                        bVarH.Q(hVar);
                        bVarH.Q(hVarV);
                        this.q = bVarH.d();
                    }
                    H();
                    this.g |= 512;
                }
                if ((gVar.g & 1024) != 0) {
                    boolean z = gVar.r;
                    this.g |= 1024;
                    this.r = z;
                    H();
                }
                H();
            }

            /* JADX WARN: Removed duplicated region for block: B:15:0x001d  */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public final void N(com.google.protobuf.g r2, com.google.protobuf.s r3) throws java.lang.Throwable {
                /*
                    r1 = this;
                    com.google.protobuf.m$g$a r0 = com.google.protobuf.m.g.u     // Catch: java.lang.Throwable -> Lc com.google.protobuf.d0 -> Le
                    java.lang.Object r2 = r0.b(r2, r3)     // Catch: java.lang.Throwable -> Lc com.google.protobuf.d0 -> Le
                    com.google.protobuf.m$g r2 = (com.google.protobuf.m.g) r2     // Catch: java.lang.Throwable -> Lc com.google.protobuf.d0 -> Le
                    r1.M(r2)
                    return
                Lc:
                    r2 = move-exception
                    goto L1a
                Le:
                    r2 = move-exception
                    com.google.protobuf.r0 r3 = r2.e     // Catch: java.lang.Throwable -> Lc
                    com.google.protobuf.m$g r3 = (com.google.protobuf.m.g) r3     // Catch: java.lang.Throwable -> Lc
                    java.io.IOException r2 = r2.l()     // Catch: java.lang.Throwable -> L18
                    throw r2     // Catch: java.lang.Throwable -> L18
                L18:
                    r2 = move-exception
                    goto L1b
                L1a:
                    r3 = 0
                L1b:
                    if (r3 == 0) goto L20
                    r1.M(r3)
                L20:
                    throw r2
                */
                throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.m.g.b.N(com.google.protobuf.g, com.google.protobuf.s):void");
            }

            @Override // com.google.protobuf.a.AbstractC0042a, com.google.protobuf.q0.a
            public final q0.a R(q0 q0Var) {
                if (q0Var instanceof g) {
                    M((g) q0Var);
                } else {
                    super.R(q0Var);
                }
                return this;
            }

            @Override // com.google.protobuf.a0.a, com.google.protobuf.q0.a
            public final q0.a a(n.f fVar, Object obj) {
                super.a(fVar, obj);
                return this;
            }

            @Override // defpackage.bh1, com.google.protobuf.t0
            public final q0 b() {
                return g.t;
            }

            @Override // com.google.protobuf.r0.a, com.google.protobuf.q0.a
            public final q0 build() {
                g gVarD = d();
                if (gVarD.isInitialized()) {
                    return gVarD;
                }
                throw a.AbstractC0042a.z(gVarD);
            }

            @Override // com.google.protobuf.a0.a, com.google.protobuf.q0.a, com.google.protobuf.t0
            public final n.a c() {
                return m.k;
            }

            @Override // com.google.protobuf.a0.a
            public final Object clone() {
                return (b) super.clone();
            }

            @Override // com.google.protobuf.a0.a, com.google.protobuf.q0.a
            public final q0.a f(n.f fVar, Object obj) {
                super.f(fVar, obj);
                return this;
            }

            @Override // com.google.protobuf.a0.a, com.google.protobuf.q0.a
            public final q0.a j0(j1 j1Var) {
                this.f = j1Var;
                H();
                return this;
            }

            @Override // com.google.protobuf.a.AbstractC0042a
            /* JADX INFO: renamed from: v */
            public final /* bridge */ /* synthetic */ a.AbstractC0042a L(com.google.protobuf.g gVar, com.google.protobuf.s sVar) throws Throwable {
                N(gVar, sVar);
                return this;
            }

            @Override // com.google.protobuf.a.AbstractC0042a
            /* JADX INFO: renamed from: x */
            public final a.AbstractC0042a R(q0 q0Var) {
                if (q0Var instanceof g) {
                    M((g) q0Var);
                } else {
                    super.R(q0Var);
                }
                return this;
            }

            @Override // com.google.protobuf.a0.a, com.google.protobuf.a.AbstractC0042a
            public final void y(j1 j1Var) {
            }

            @Override // defpackage.bh1, com.google.protobuf.t0
            public final r0 b() {
                return g.t;
            }

            @Override // com.google.protobuf.r0.a, com.google.protobuf.q0.a
            public final r0 build() {
                g gVarD = d();
                if (gVarD.isInitialized()) {
                    return gVarD;
                }
                throw a.AbstractC0042a.z(gVarD);
            }
        }

        /* JADX INFO: compiled from: DescriptorProtos.java */
        public enum c implements c0.a {
            LABEL_OPTIONAL(1),
            LABEL_REQUIRED(2),
            LABEL_REPEATED(3);

            public final int e;

            static {
                values();
            }

            c(int i2) {
                this.e = i2;
            }

            public static c a(int i2) {
                if (i2 == 1) {
                    return LABEL_OPTIONAL;
                }
                if (i2 == 2) {
                    return LABEL_REQUIRED;
                }
                if (i2 != 3) {
                    return null;
                }
                return LABEL_REPEATED;
            }

            @Override // com.google.protobuf.c0.a
            public final int b() {
                return this.e;
            }
        }

        /* JADX INFO: compiled from: DescriptorProtos.java */
        public enum d implements c0.a {
            TYPE_DOUBLE(1),
            TYPE_FLOAT(2),
            TYPE_INT64(3),
            TYPE_UINT64(4),
            TYPE_INT32(5),
            TYPE_FIXED64(6),
            TYPE_FIXED32(7),
            TYPE_BOOL(8),
            TYPE_STRING(9),
            TYPE_GROUP(10),
            TYPE_MESSAGE(11),
            TYPE_BYTES(12),
            TYPE_UINT32(13),
            TYPE_ENUM(14),
            TYPE_SFIXED32(15),
            TYPE_SFIXED64(16),
            TYPE_SINT32(17),
            TYPE_SINT64(18);

            public final int e;

            static {
                values();
            }

            d(int i) {
                this.e = i;
            }

            public static d a(int i) {
                switch (i) {
                    case 1:
                        return TYPE_DOUBLE;
                    case 2:
                        return TYPE_FLOAT;
                    case 3:
                        return TYPE_INT64;
                    case 4:
                        return TYPE_UINT64;
                    case 5:
                        return TYPE_INT32;
                    case 6:
                        return TYPE_FIXED64;
                    case 7:
                        return TYPE_FIXED32;
                    case 8:
                        return TYPE_BOOL;
                    case 9:
                        return TYPE_STRING;
                    case 10:
                        return TYPE_GROUP;
                    case 11:
                        return TYPE_MESSAGE;
                    case 12:
                        return TYPE_BYTES;
                    case 13:
                        return TYPE_UINT32;
                    case 14:
                        return TYPE_ENUM;
                    case 15:
                        return TYPE_SFIXED32;
                    case 16:
                        return TYPE_SFIXED64;
                    case 17:
                        return TYPE_SINT32;
                    case 18:
                        return TYPE_SINT64;
                    default:
                        return null;
                }
            }

            @Override // com.google.protobuf.c0.a
            public final int b() {
                return this.e;
            }
        }

        public g(a0.a aVar) {
            super(aVar);
            this.s = (byte) -1;
        }

        @Override // com.google.protobuf.a0
        public final a0.e M() {
            a0.e eVar = m.l;
            eVar.c(g.class, b.class);
            return eVar;
        }

        @Override // com.google.protobuf.a0
        public final Object O() {
            return new g();
        }

        public final String Q() {
            Object obj = this.n;
            if (obj instanceof String) {
                return (String) obj;
            }
            lk lkVar = (lk) obj;
            String strZ = lkVar.z();
            if (lkVar.q()) {
                this.n = strZ;
            }
            return strZ;
        }

        public final String S() {
            Object obj = this.m;
            if (obj instanceof String) {
                return (String) obj;
            }
            lk lkVar = (lk) obj;
            String strZ = lkVar.z();
            if (lkVar.q()) {
                this.m = strZ;
            }
            return strZ;
        }

        public final String T() {
            Object obj = this.p;
            if (obj instanceof String) {
                return (String) obj;
            }
            lk lkVar = (lk) obj;
            String strZ = lkVar.z();
            if (lkVar.q()) {
                this.p = strZ;
            }
            return strZ;
        }

        public final String U() {
            Object obj = this.h;
            if (obj instanceof String) {
                return (String) obj;
            }
            lk lkVar = (lk) obj;
            String strZ = lkVar.z();
            if (lkVar.q()) {
                this.h = strZ;
            }
            return strZ;
        }

        public final h V() {
            h hVar = this.q;
            return hVar == null ? h.q : hVar;
        }

        public final String W() {
            Object obj = this.l;
            if (obj instanceof String) {
                return (String) obj;
            }
            lk lkVar = (lk) obj;
            String strZ = lkVar.z();
            if (lkVar.q()) {
                this.l = strZ;
            }
            return strZ;
        }

        public final boolean X() {
            return (this.g & 64) != 0;
        }

        public final boolean Y() {
            return (this.g & 32) != 0;
        }

        public final boolean Z() {
            return (this.g & 128) != 0;
        }

        public final boolean a0() {
            return (this.g & 512) != 0;
        }

        @Override // defpackage.bh1, com.google.protobuf.t0
        public final q0 b() {
            return t;
        }

        public final boolean b0() {
            return (this.g & 8) != 0;
        }

        public final boolean c0() {
            return (this.g & 16) != 0;
        }

        @Override // com.google.protobuf.r0
        /* JADX INFO: renamed from: d0, reason: merged with bridge method [inline-methods] */
        public final b h() {
            if (this == t) {
                return new b();
            }
            b bVar = new b();
            bVar.M(this);
            return bVar;
        }

        @Override // com.google.protobuf.r0, com.google.protobuf.q0
        public final q0.a e() {
            return t.h();
        }

        @Override // com.google.protobuf.a
        public final boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof g)) {
                return super.equals(obj);
            }
            g gVar = (g) obj;
            int i = this.g;
            if (((i & 1) != 0) != ((gVar.g & 1) != 0)) {
                return false;
            }
            if (((i & 1) != 0) && !U().equals(gVar.U())) {
                return false;
            }
            int i2 = this.g;
            boolean z = (i2 & 2) != 0;
            int i3 = gVar.g;
            if (z != ((i3 & 2) != 0)) {
                return false;
            }
            if (((i2 & 2) != 0) && this.i != gVar.i) {
                return false;
            }
            if (((i2 & 4) != 0) != ((i3 & 4) != 0)) {
                return false;
            }
            if ((((i2 & 4) != 0) && this.j != gVar.j) || b0() != gVar.b0()) {
                return false;
            }
            if ((b0() && this.k != gVar.k) || c0() != gVar.c0()) {
                return false;
            }
            if ((c0() && !W().equals(gVar.W())) || Y() != gVar.Y()) {
                return false;
            }
            if ((Y() && !S().equals(gVar.S())) || X() != gVar.X()) {
                return false;
            }
            if ((X() && !Q().equals(gVar.Q())) || Z() != gVar.Z()) {
                return false;
            }
            if (Z() && this.o != gVar.o) {
                return false;
            }
            int i4 = this.g;
            if (((i4 & 256) != 0) != ((gVar.g & 256) != 0)) {
                return false;
            }
            if ((((i4 & 256) != 0) && !T().equals(gVar.T())) || a0() != gVar.a0()) {
                return false;
            }
            if (a0() && !V().equals(gVar.V())) {
                return false;
            }
            int i5 = this.g;
            if (((i5 & 1024) != 0) != ((gVar.g & 1024) != 0)) {
                return false;
            }
            return (!((i5 & 1024) != 0) || this.r == gVar.r) && this.f.equals(gVar.f);
        }

        @Override // com.google.protobuf.a
        public final int hashCode() {
            int i = this.memoizedHashCode;
            if (i != 0) {
                return i;
            }
            int iHashCode = m.k.hashCode() + 779;
            if ((this.g & 1) != 0) {
                iHashCode = C0175b.e(iHashCode, 37, 1, 53) + U().hashCode();
            }
            int i2 = this.g;
            if ((i2 & 2) != 0) {
                iHashCode = C0175b.e(iHashCode, 37, 3, 53) + this.i;
            }
            if ((i2 & 4) != 0) {
                iHashCode = C0175b.e(iHashCode, 37, 4, 53) + this.j;
            }
            if (b0()) {
                iHashCode = C0175b.e(iHashCode, 37, 5, 53) + this.k;
            }
            if (c0()) {
                iHashCode = C0175b.e(iHashCode, 37, 6, 53) + W().hashCode();
            }
            if (Y()) {
                iHashCode = C0175b.e(iHashCode, 37, 2, 53) + S().hashCode();
            }
            if (X()) {
                iHashCode = C0175b.e(iHashCode, 37, 7, 53) + Q().hashCode();
            }
            if (Z()) {
                iHashCode = C0175b.e(iHashCode, 37, 9, 53) + this.o;
            }
            if ((this.g & 256) != 0) {
                iHashCode = C0175b.e(iHashCode, 37, 10, 53) + T().hashCode();
            }
            if (a0()) {
                iHashCode = C0175b.e(iHashCode, 37, 8, 53) + V().hashCode();
            }
            if ((this.g & 1024) != 0) {
                iHashCode = C0175b.e(iHashCode, 37, 17, 53) + c0.a(this.r);
            }
            int iHashCode2 = this.f.hashCode() + (iHashCode * 29);
            this.memoizedHashCode = iHashCode2;
            return iHashCode2;
        }

        @Override // com.google.protobuf.a0, defpackage.bh1
        public final boolean isInitialized() {
            byte b2 = this.s;
            if (b2 == 1) {
                return true;
            }
            if (b2 == 0) {
                return false;
            }
            if (!a0() || V().isInitialized()) {
                this.s = (byte) 1;
                return true;
            }
            this.s = (byte) 0;
            return false;
        }

        @Override // com.google.protobuf.a0, com.google.protobuf.r0
        public final int m() {
            int i = this.e;
            if (i != -1) {
                return i;
            }
            int iH = (this.g & 1) != 0 ? 0 + a0.H(1, this.h) : 0;
            if ((this.g & 32) != 0) {
                iH += a0.H(2, this.m);
            }
            if ((this.g & 2) != 0) {
                iH += nu.S(3, this.i);
            }
            if ((this.g & 4) != 0) {
                iH += nu.N(4, this.j);
            }
            if ((this.g & 8) != 0) {
                iH += nu.N(5, this.k);
            }
            if ((this.g & 16) != 0) {
                iH += a0.H(6, this.l);
            }
            if ((this.g & 64) != 0) {
                iH += a0.H(7, this.n);
            }
            if ((this.g & 512) != 0) {
                iH += nu.a0(8, V());
            }
            if ((this.g & 128) != 0) {
                iH += nu.S(9, this.o);
            }
            if ((this.g & 256) != 0) {
                iH += a0.H(10, this.p);
            }
            if ((this.g & 1024) != 0) {
                iH += nu.J(17);
            }
            int iM = this.f.m() + iH;
            this.e = iM;
            return iM;
        }

        @Override // com.google.protobuf.a0, com.google.protobuf.t0
        public final j1 n() {
            return this.f;
        }

        @Override // com.google.protobuf.a0, com.google.protobuf.r0
        public final void p(nu nuVar) {
            if ((this.g & 1) != 0) {
                a0.P(nuVar, 1, this.h);
            }
            if ((this.g & 32) != 0) {
                a0.P(nuVar, 2, this.m);
            }
            if ((this.g & 2) != 0) {
                nuVar.z0(3, this.i);
            }
            if ((this.g & 4) != 0) {
                nuVar.z0(4, this.j);
            }
            if ((this.g & 8) != 0) {
                nuVar.z0(5, this.k);
            }
            if ((this.g & 16) != 0) {
                a0.P(nuVar, 6, this.l);
            }
            if ((this.g & 64) != 0) {
                a0.P(nuVar, 7, this.n);
            }
            if ((this.g & 512) != 0) {
                nuVar.D0(8, V());
            }
            if ((this.g & 128) != 0) {
                nuVar.z0(9, this.o);
            }
            if ((this.g & 256) != 0) {
                a0.P(nuVar, 10, this.p);
            }
            if ((this.g & 1024) != 0) {
                nuVar.p0(17, this.r);
            }
            this.f.p(nuVar);
        }

        @Override // com.google.protobuf.a0, com.google.protobuf.r0
        public final wv1<g> u() {
            return u;
        }

        @Override // defpackage.bh1, com.google.protobuf.t0
        public final r0 b() {
            return t;
        }

        @Override // com.google.protobuf.r0, com.google.protobuf.q0
        public final r0.a e() {
            return t.h();
        }

        public g() {
            this.s = (byte) -1;
            this.h = "";
            this.j = 1;
            this.k = 1;
            this.l = "";
            this.m = "";
            this.n = "";
            this.p = "";
        }
    }

    /* JADX INFO: compiled from: DescriptorProtos.java */
    public static final class h extends a0.d<h> {
        public static final h q = new h();

        @Deprecated
        public static final a r = new a();
        private static final long serialVersionUID = 0;
        public int h;
        public int i;
        public boolean j;
        public int k;
        public boolean l;
        public boolean m;
        public boolean n;
        public List<s> o;
        public byte p;

        /* JADX INFO: compiled from: DescriptorProtos.java */
        public static class a extends com.google.protobuf.c<h> {
            @Override // defpackage.wv1
            public final Object b(com.google.protobuf.g gVar, com.google.protobuf.s sVar) {
                h hVar = new h();
                sVar.getClass();
                j1 j1Var = j1.f;
                j1.a aVar = new j1.a();
                boolean z = false;
                int i = 0;
                while (!z) {
                    try {
                        try {
                            int iF = gVar.F();
                            if (iF != 0) {
                                if (iF == 8) {
                                    int iO = gVar.o();
                                    if (c.a(iO) == null) {
                                        aVar.y(1, iO);
                                    } else {
                                        hVar.h = 1 | hVar.h;
                                        hVar.i = iO;
                                    }
                                } else if (iF == 16) {
                                    hVar.h |= 2;
                                    hVar.j = gVar.l();
                                } else if (iF == 24) {
                                    hVar.h |= 16;
                                    hVar.m = gVar.l();
                                } else if (iF == 40) {
                                    hVar.h |= 8;
                                    hVar.l = gVar.l();
                                } else if (iF == 48) {
                                    int iO2 = gVar.o();
                                    if (d.a(iO2) == null) {
                                        aVar.y(6, iO2);
                                    } else {
                                        hVar.h |= 4;
                                        hVar.k = iO2;
                                    }
                                } else if (iF == 80) {
                                    hVar.h |= 32;
                                    hVar.n = gVar.l();
                                } else if (iF == 7994) {
                                    if ((i & 64) == 0) {
                                        hVar.o = new ArrayList();
                                        i |= 64;
                                    }
                                    hVar.o.add((s) gVar.v(s.q, sVar));
                                } else if (!hVar.V(gVar, aVar, sVar, iF)) {
                                }
                            }
                            z = true;
                        } catch (d0 e) {
                            e.e = hVar;
                            throw e;
                        } catch (IOException e2) {
                            d0 d0Var = new d0(e2);
                            d0Var.e = hVar;
                            throw d0Var;
                        }
                    } catch (Throwable th) {
                        if ((i & 64) != 0) {
                            hVar.o = Collections.unmodifiableList(hVar.o);
                        }
                        hVar.f = aVar.build();
                        hVar.U();
                        throw th;
                    }
                }
                if ((i & 64) != 0) {
                    hVar.o = Collections.unmodifiableList(hVar.o);
                }
                hVar.f = aVar.build();
                hVar.U();
                return hVar;
            }
        }

        /* JADX INFO: compiled from: DescriptorProtos.java */
        public static final class b extends a0.c<h, b> {
            public int h;
            public boolean j;
            public boolean l;
            public boolean m;
            public boolean n;
            public int i = 0;
            public int k = 0;
            public List<s> o = Collections.emptyList();

            @Override // com.google.protobuf.a0.a
            /* JADX INFO: renamed from: A */
            public final a0.a f(n.f fVar, Object obj) {
                super.K(fVar, obj);
                return this;
            }

            @Override // com.google.protobuf.a0.a
            /* JADX INFO: renamed from: B */
            public final a0.a clone() {
                return (b) super.clone();
            }

            @Override // com.google.protobuf.a0.a
            public final a0.e D() {
                a0.e eVar = m.D;
                eVar.c(h.class, b.class);
                return eVar;
            }

            @Override // com.google.protobuf.a0.a
            /* JADX INFO: renamed from: E */
            public final a0.a y(j1 j1Var) {
                return (b) super.y(j1Var);
            }

            @Override // com.google.protobuf.a0.a
            /* JADX INFO: renamed from: I */
            public final a0.a a(n.f fVar, Object obj) {
                return (b) super.N(fVar, obj);
            }

            @Override // com.google.protobuf.a0.a
            /* JADX INFO: renamed from: J */
            public final a0.a j0(j1 j1Var) {
                this.f = j1Var;
                H();
                return this;
            }

            @Override // com.google.protobuf.a.AbstractC0042a, com.google.protobuf.r0.a
            public final /* bridge */ /* synthetic */ r0.a L(com.google.protobuf.g gVar, com.google.protobuf.s sVar) throws Throwable {
                S(gVar, sVar);
                return this;
            }

            @Override // com.google.protobuf.r0.a, com.google.protobuf.q0.a
            /* JADX INFO: renamed from: P, reason: merged with bridge method [inline-methods] */
            public final h d() {
                h hVar = new h(this);
                int i = this.h;
                int i2 = (i & 1) != 0 ? 1 : 0;
                hVar.i = this.i;
                if ((i & 2) != 0) {
                    hVar.j = this.j;
                    i2 |= 2;
                }
                if ((i & 4) != 0) {
                    i2 |= 4;
                }
                hVar.k = this.k;
                if ((i & 8) != 0) {
                    hVar.l = this.l;
                    i2 |= 8;
                }
                if ((i & 16) != 0) {
                    hVar.m = this.m;
                    i2 |= 16;
                }
                if ((i & 32) != 0) {
                    hVar.n = this.n;
                    i2 |= 32;
                }
                if ((i & 64) != 0) {
                    this.o = Collections.unmodifiableList(this.o);
                    this.h &= -65;
                }
                hVar.o = this.o;
                hVar.h = i2;
                F();
                return hVar;
            }

            public final void Q(h hVar) {
                if (hVar == h.q) {
                    return;
                }
                if ((hVar.h & 1) != 0) {
                    c cVarA = c.a(hVar.i);
                    if (cVarA == null) {
                        cVarA = c.STRING;
                    }
                    this.h |= 1;
                    this.i = cVarA.e;
                    H();
                }
                if (hVar.W()) {
                    boolean z = hVar.j;
                    this.h |= 2;
                    this.j = z;
                    H();
                }
                if ((hVar.h & 4) != 0) {
                    d dVarA = d.a(hVar.k);
                    if (dVarA == null) {
                        dVarA = d.JS_NORMAL;
                    }
                    this.h |= 4;
                    this.k = dVarA.e;
                    H();
                }
                if ((hVar.h & 8) != 0) {
                    boolean z2 = hVar.l;
                    this.h |= 8;
                    this.l = z2;
                    H();
                }
                if ((hVar.h & 16) != 0) {
                    boolean z3 = hVar.m;
                    this.h |= 16;
                    this.m = z3;
                    H();
                }
                if ((hVar.h & 32) != 0) {
                    boolean z4 = hVar.n;
                    this.h |= 32;
                    this.n = z4;
                    H();
                }
                if (!hVar.o.isEmpty()) {
                    if (this.o.isEmpty()) {
                        this.o = hVar.o;
                        this.h &= -65;
                    } else {
                        if ((this.h & 64) == 0) {
                            this.o = new ArrayList(this.o);
                            this.h |= 64;
                        }
                        this.o.addAll(hVar.o);
                    }
                    H();
                }
                M(hVar);
                H();
            }

            @Override // com.google.protobuf.a.AbstractC0042a, com.google.protobuf.q0.a
            public final q0.a R(q0 q0Var) {
                if (q0Var instanceof h) {
                    Q((h) q0Var);
                } else {
                    super.R(q0Var);
                }
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:15:0x001d  */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public final void S(com.google.protobuf.g r2, com.google.protobuf.s r3) throws java.lang.Throwable {
                /*
                    r1 = this;
                    com.google.protobuf.m$h$a r0 = com.google.protobuf.m.h.r     // Catch: java.lang.Throwable -> Lc com.google.protobuf.d0 -> Le
                    java.lang.Object r2 = r0.b(r2, r3)     // Catch: java.lang.Throwable -> Lc com.google.protobuf.d0 -> Le
                    com.google.protobuf.m$h r2 = (com.google.protobuf.m.h) r2     // Catch: java.lang.Throwable -> Lc com.google.protobuf.d0 -> Le
                    r1.Q(r2)
                    return
                Lc:
                    r2 = move-exception
                    goto L1a
                Le:
                    r2 = move-exception
                    com.google.protobuf.r0 r3 = r2.e     // Catch: java.lang.Throwable -> Lc
                    com.google.protobuf.m$h r3 = (com.google.protobuf.m.h) r3     // Catch: java.lang.Throwable -> Lc
                    java.io.IOException r2 = r2.l()     // Catch: java.lang.Throwable -> L18
                    throw r2     // Catch: java.lang.Throwable -> L18
                L18:
                    r2 = move-exception
                    goto L1b
                L1a:
                    r3 = 0
                L1b:
                    if (r3 == 0) goto L20
                    r1.Q(r3)
                L20:
                    throw r2
                */
                throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.m.h.b.S(com.google.protobuf.g, com.google.protobuf.s):void");
            }

            @Override // com.google.protobuf.a0.a, com.google.protobuf.q0.a
            public final q0.a a(n.f fVar, Object obj) {
                super.N(fVar, obj);
                return this;
            }

            @Override // defpackage.bh1, com.google.protobuf.t0
            public final q0 b() {
                return h.q;
            }

            @Override // com.google.protobuf.r0.a, com.google.protobuf.q0.a
            public final q0 build() {
                h hVarD = d();
                if (hVarD.isInitialized()) {
                    return hVarD;
                }
                throw a.AbstractC0042a.z(hVarD);
            }

            @Override // com.google.protobuf.a0.a, com.google.protobuf.q0.a, com.google.protobuf.t0
            public final n.a c() {
                return m.C;
            }

            @Override // com.google.protobuf.a0.a
            public final Object clone() {
                return (b) super.clone();
            }

            @Override // com.google.protobuf.a0.a, com.google.protobuf.q0.a
            public final q0.a f(n.f fVar, Object obj) {
                super.K(fVar, obj);
                return this;
            }

            @Override // com.google.protobuf.a0.a, com.google.protobuf.q0.a
            public final q0.a j0(j1 j1Var) {
                this.f = j1Var;
                H();
                return this;
            }

            @Override // com.google.protobuf.a.AbstractC0042a
            /* JADX INFO: renamed from: v */
            public final /* bridge */ /* synthetic */ a.AbstractC0042a L(com.google.protobuf.g gVar, com.google.protobuf.s sVar) throws Throwable {
                S(gVar, sVar);
                return this;
            }

            @Override // com.google.protobuf.a.AbstractC0042a
            /* JADX INFO: renamed from: x */
            public final a.AbstractC0042a R(q0 q0Var) {
                if (q0Var instanceof h) {
                    Q((h) q0Var);
                } else {
                    super.R(q0Var);
                }
                return this;
            }

            @Override // com.google.protobuf.a0.a, com.google.protobuf.a.AbstractC0042a
            public final void y(j1 j1Var) {
            }

            @Override // defpackage.bh1, com.google.protobuf.t0
            public final r0 b() {
                return h.q;
            }

            @Override // com.google.protobuf.r0.a, com.google.protobuf.q0.a
            public final r0 build() {
                h hVarD = d();
                if (hVarD.isInitialized()) {
                    return hVarD;
                }
                throw a.AbstractC0042a.z(hVarD);
            }
        }

        /* JADX INFO: compiled from: DescriptorProtos.java */
        public enum c implements c0.a {
            STRING(0),
            CORD(1),
            STRING_PIECE(2);

            public final int e;

            static {
                values();
            }

            c(int i2) {
                this.e = i2;
            }

            public static c a(int i2) {
                if (i2 == 0) {
                    return STRING;
                }
                if (i2 == 1) {
                    return CORD;
                }
                if (i2 != 2) {
                    return null;
                }
                return STRING_PIECE;
            }

            @Override // com.google.protobuf.c0.a
            public final int b() {
                return this.e;
            }
        }

        /* JADX INFO: compiled from: DescriptorProtos.java */
        public enum d implements c0.a {
            JS_NORMAL(0),
            JS_STRING(1),
            JS_NUMBER(2);

            public final int e;

            static {
                values();
            }

            d(int i2) {
                this.e = i2;
            }

            public static d a(int i2) {
                if (i2 == 0) {
                    return JS_NORMAL;
                }
                if (i2 == 1) {
                    return JS_STRING;
                }
                if (i2 != 2) {
                    return null;
                }
                return JS_NUMBER;
            }

            @Override // com.google.protobuf.c0.a
            public final int b() {
                return this.e;
            }
        }

        public h(a0.c cVar) {
            super(cVar);
            this.p = (byte) -1;
        }

        @Override // com.google.protobuf.a0
        public final a0.e M() {
            a0.e eVar = m.D;
            eVar.c(h.class, b.class);
            return eVar;
        }

        @Override // com.google.protobuf.a0
        public final Object O() {
            return new h();
        }

        public final boolean W() {
            return (this.h & 2) != 0;
        }

        @Override // com.google.protobuf.r0
        /* JADX INFO: renamed from: X, reason: merged with bridge method [inline-methods] */
        public final b h() {
            if (this == q) {
                return new b();
            }
            b bVar = new b();
            bVar.Q(this);
            return bVar;
        }

        @Override // defpackage.bh1, com.google.protobuf.t0
        public final q0 b() {
            return q;
        }

        @Override // com.google.protobuf.r0, com.google.protobuf.q0
        public final q0.a e() {
            return q.h();
        }

        @Override // com.google.protobuf.a
        public final boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof h)) {
                return super.equals(obj);
            }
            h hVar = (h) obj;
            int i = this.h;
            if (((i & 1) != 0) != ((hVar.h & 1) != 0)) {
                return false;
            }
            if ((((i & 1) != 0) && this.i != hVar.i) || W() != hVar.W()) {
                return false;
            }
            if (W() && this.j != hVar.j) {
                return false;
            }
            int i2 = this.h;
            boolean z = (i2 & 4) != 0;
            int i3 = hVar.h;
            if (z != ((i3 & 4) != 0)) {
                return false;
            }
            if (((i2 & 4) != 0) && this.k != hVar.k) {
                return false;
            }
            if (((i2 & 8) != 0) != ((i3 & 8) != 0)) {
                return false;
            }
            if (((i2 & 8) != 0) && this.l != hVar.l) {
                return false;
            }
            if (((i2 & 16) != 0) != ((i3 & 16) != 0)) {
                return false;
            }
            if (((i2 & 16) != 0) && this.m != hVar.m) {
                return false;
            }
            if (((i2 & 32) != 0) != ((i3 & 32) != 0)) {
                return false;
            }
            return (!((i2 & 32) != 0) || this.n == hVar.n) && this.o.equals(hVar.o) && this.f.equals(hVar.f) && T().equals(hVar.T());
        }

        @Override // com.google.protobuf.a
        public final int hashCode() {
            int i = this.memoizedHashCode;
            if (i != 0) {
                return i;
            }
            int iHashCode = m.C.hashCode() + 779;
            if ((this.h & 1) != 0) {
                iHashCode = C0175b.e(iHashCode, 37, 1, 53) + this.i;
            }
            if (W()) {
                iHashCode = C0175b.e(iHashCode, 37, 2, 53) + c0.a(this.j);
            }
            int i2 = this.h;
            if ((i2 & 4) != 0) {
                iHashCode = C0175b.e(iHashCode, 37, 6, 53) + this.k;
            }
            if ((i2 & 8) != 0) {
                iHashCode = C0175b.e(iHashCode, 37, 5, 53) + c0.a(this.l);
            }
            if ((this.h & 16) != 0) {
                iHashCode = C0175b.e(iHashCode, 37, 3, 53) + c0.a(this.m);
            }
            if ((this.h & 32) != 0) {
                iHashCode = C0175b.e(iHashCode, 37, 10, 53) + c0.a(this.n);
            }
            if (this.o.size() > 0) {
                iHashCode = C0175b.e(iHashCode, 37, 999, 53) + this.o.hashCode();
            }
            int iHashCode2 = this.f.hashCode() + (com.google.protobuf.a.D(iHashCode, T()) * 29);
            this.memoizedHashCode = iHashCode2;
            return iHashCode2;
        }

        @Override // com.google.protobuf.a0.d, com.google.protobuf.a0, defpackage.bh1
        public final boolean isInitialized() {
            byte b2 = this.p;
            if (b2 == 1) {
                return true;
            }
            if (b2 == 0) {
                return false;
            }
            for (int i = 0; i < this.o.size(); i++) {
                if (!this.o.get(i).isInitialized()) {
                    this.p = (byte) 0;
                    return false;
                }
            }
            if (Q()) {
                this.p = (byte) 1;
                return true;
            }
            this.p = (byte) 0;
            return false;
        }

        @Override // com.google.protobuf.a0, com.google.protobuf.r0
        public final int m() {
            int i = this.e;
            if (i != -1) {
                return i;
            }
            int iN = (this.h & 1) != 0 ? nu.N(1, this.i) + 0 : 0;
            if ((this.h & 2) != 0) {
                iN += nu.J(2);
            }
            if ((this.h & 16) != 0) {
                iN += nu.J(3);
            }
            if ((this.h & 8) != 0) {
                iN += nu.J(5);
            }
            if ((this.h & 4) != 0) {
                iN += nu.N(6, this.k);
            }
            if ((this.h & 32) != 0) {
                iN += nu.J(10);
            }
            for (int i2 = 0; i2 < this.o.size(); i2++) {
                iN += nu.a0(999, this.o.get(i2));
            }
            int iM = this.f.m() + S() + iN;
            this.e = iM;
            return iM;
        }

        @Override // com.google.protobuf.a0, com.google.protobuf.t0
        public final j1 n() {
            return this.f;
        }

        @Override // com.google.protobuf.a0, com.google.protobuf.r0
        public final void p(nu nuVar) {
            a0.d.a aVar = new a0.d.a(this);
            if ((this.h & 1) != 0) {
                nuVar.z0(1, this.i);
            }
            if ((this.h & 2) != 0) {
                nuVar.p0(2, this.j);
            }
            if ((this.h & 16) != 0) {
                nuVar.p0(3, this.m);
            }
            if ((this.h & 8) != 0) {
                nuVar.p0(5, this.l);
            }
            if ((this.h & 4) != 0) {
                nuVar.z0(6, this.k);
            }
            if ((this.h & 32) != 0) {
                nuVar.p0(10, this.n);
            }
            for (int i = 0; i < this.o.size(); i++) {
                nuVar.D0(999, this.o.get(i));
            }
            aVar.a(nuVar);
            this.f.p(nuVar);
        }

        @Override // com.google.protobuf.a0, com.google.protobuf.r0
        public final wv1<h> u() {
            return r;
        }

        @Override // defpackage.bh1, com.google.protobuf.t0
        public final r0 b() {
            return q;
        }

        @Override // com.google.protobuf.r0, com.google.protobuf.q0
        public final r0.a e() {
            return q.h();
        }

        public h() {
            this.p = (byte) -1;
            this.i = 0;
            this.k = 0;
            this.o = Collections.emptyList();
        }
    }

    /* JADX INFO: compiled from: DescriptorProtos.java */
    public static final class i extends a0 implements t0 {
        private static final long serialVersionUID = 0;
        public static final i u = new i();

        @Deprecated
        public static final a v = new a();
        public int g;
        public volatile Object h;
        public volatile Object i;
        public q61 j;
        public c0.c k;
        public c0.c l;
        public List<a> m;
        public List<b> n;
        public List<p> o;
        public List<g> p;
        public j q;
        public r r;
        public volatile Object s;
        public byte t;

        /* JADX INFO: compiled from: DescriptorProtos.java */
        public static class a extends com.google.protobuf.c<i> {
            /* JADX WARN: Type inference incomplete: some casts might be missing */
            @Override // defpackage.wv1
            public final Object b(com.google.protobuf.g gVar, com.google.protobuf.s sVar) {
                i iVar = new i();
                sVar.getClass();
                j1 j1Var = j1.f;
                j1.a aVar = new j1.a();
                boolean z = false;
                int i = 0;
                while (!z) {
                    try {
                        try {
                            int iF = gVar.F();
                            switch (iF) {
                                case 0:
                                    z = true;
                                    break;
                                case 10:
                                    lk.f fVarM = gVar.m();
                                    iVar.g |= 1;
                                    iVar.h = fVarM;
                                    break;
                                case 18:
                                    lk.f fVarM2 = gVar.m();
                                    iVar.g |= 2;
                                    iVar.i = fVarM2;
                                    break;
                                case 26:
                                    lk.f fVarM3 = gVar.m();
                                    int i2 = (i == true ? 1 : 0) & 4;
                                    i = i;
                                    if (i2 == 0) {
                                        iVar.j = new g0();
                                        i = (i == true ? 1 : 0) | 4;
                                    }
                                    iVar.j.l(fVarM3);
                                    break;
                                case 34:
                                    int i3 = (i == true ? 1 : 0) & 32;
                                    i = i;
                                    if (i3 == 0) {
                                        iVar.m = new ArrayList();
                                        i = (i == true ? 1 : 0) | 32;
                                    }
                                    iVar.m.add((a) gVar.v(a.t, sVar));
                                    break;
                                case 42:
                                    int i4 = (i == true ? 1 : 0) & 64;
                                    i = i;
                                    if (i4 == 0) {
                                        iVar.n = new ArrayList();
                                        i = (i == true ? 1 : 0) | 64;
                                    }
                                    iVar.n.add((b) gVar.v(b.o, sVar));
                                    break;
                                case 50:
                                    int i5 = (i == true ? 1 : 0) & 128;
                                    i = i;
                                    if (i5 == 0) {
                                        iVar.o = new ArrayList();
                                        i = (i == true ? 1 : 0) | 128;
                                    }
                                    iVar.o.add((p) gVar.v(p.m, sVar));
                                    break;
                                case 58:
                                    int i6 = (i == true ? 1 : 0) & 256;
                                    i = i;
                                    if (i6 == 0) {
                                        iVar.p = new ArrayList();
                                        i = (i == true ? 1 : 0) | 256;
                                    }
                                    iVar.p.add((g) gVar.v(g.u, sVar));
                                    break;
                                case 66:
                                    j.b bVarH = (iVar.g & 4) != 0 ? iVar.q.h() : null;
                                    j jVar = (j) gVar.v(j.F, sVar);
                                    iVar.q = jVar;
                                    if (bVarH != null) {
                                        bVarH.Q(jVar);
                                        iVar.q = bVarH.d();
                                    }
                                    iVar.g |= 4;
                                    break;
                                case 74:
                                    r.b bVarH2 = (iVar.g & 8) != 0 ? iVar.r.h() : null;
                                    r rVar = (r) gVar.v(r.j, sVar);
                                    iVar.r = rVar;
                                    if (bVarH2 != null) {
                                        bVarH2.M(rVar);
                                        iVar.r = bVarH2.d();
                                    }
                                    iVar.g |= 8;
                                    break;
                                case 80:
                                    int i7 = (i == true ? 1 : 0) & 8;
                                    i = i;
                                    if (i7 == 0) {
                                        iVar.k = new b0();
                                        i = (i == true ? 1 : 0) | 8;
                                    }
                                    ((b0) iVar.k).b(gVar.t());
                                    break;
                                case 82:
                                    int iK = gVar.k(gVar.y());
                                    int i8 = (i == true ? 1 : 0) & 8;
                                    i = i;
                                    if (i8 == 0) {
                                        i = i;
                                        if (gVar.e() > 0) {
                                            iVar.k = new b0();
                                            i = (i == true ? 1 : 0) | 8;
                                        }
                                    }
                                    while (gVar.e() > 0) {
                                        ((b0) iVar.k).b(gVar.t());
                                    }
                                    gVar.j(iK);
                                    break;
                                case 88:
                                    int i9 = (i == true ? 1 : 0) & 16;
                                    i = i;
                                    if (i9 == 0) {
                                        iVar.l = new b0();
                                        i = (i == true ? 1 : 0) | 16;
                                    }
                                    ((b0) iVar.l).b(gVar.t());
                                    break;
                                case 90:
                                    int iK2 = gVar.k(gVar.y());
                                    int i10 = (i == true ? 1 : 0) & 16;
                                    i = i;
                                    if (i10 == 0) {
                                        i = i;
                                        if (gVar.e() > 0) {
                                            iVar.l = new b0();
                                            i = (i == true ? 1 : 0) | 16;
                                        }
                                    }
                                    while (gVar.e() > 0) {
                                        ((b0) iVar.l).b(gVar.t());
                                    }
                                    gVar.j(iK2);
                                    break;
                                case 98:
                                    lk.f fVarM4 = gVar.m();
                                    iVar.g |= 16;
                                    iVar.s = fVarM4;
                                    break;
                                default:
                                    if (!aVar.r(iF, gVar)) {
                                        z = true;
                                    }
                                    break;
                            }
                        } catch (d0 e) {
                            e.e = iVar;
                            throw e;
                        } catch (IOException e2) {
                            d0 d0Var = new d0(e2);
                            d0Var.e = iVar;
                            throw d0Var;
                        }
                    } catch (Throwable th) {
                        if (((i == true ? 1 : 0) & 4) != 0) {
                            iVar.j = iVar.j.m();
                        }
                        if (((i == true ? 1 : 0) & 32) != 0) {
                            iVar.m = Collections.unmodifiableList(iVar.m);
                        }
                        if (((i == true ? 1 : 0) & 64) != 0) {
                            iVar.n = Collections.unmodifiableList(iVar.n);
                        }
                        if (((i == true ? 1 : 0) & 128) != 0) {
                            iVar.o = Collections.unmodifiableList(iVar.o);
                        }
                        if (((i == true ? 1 : 0) & 256) != 0) {
                            iVar.p = Collections.unmodifiableList(iVar.p);
                        }
                        if (((i == true ? 1 : 0) & 8) != 0) {
                            ((com.google.protobuf.d) iVar.k).e = false;
                        }
                        if (((i == true ? 1 : 0) & 16) != 0) {
                            ((com.google.protobuf.d) iVar.l).e = false;
                        }
                        iVar.f = aVar.build();
                        throw th;
                    }
                }
                if (((i == true ? 1 : 0) & 4) != 0) {
                    iVar.j = iVar.j.m();
                }
                if (((i == true ? 1 : 0) & 32) != 0) {
                    iVar.m = Collections.unmodifiableList(iVar.m);
                }
                if (((i == true ? 1 : 0) & 64) != 0) {
                    iVar.n = Collections.unmodifiableList(iVar.n);
                }
                if (((i == true ? 1 : 0) & 128) != 0) {
                    iVar.o = Collections.unmodifiableList(iVar.o);
                }
                if (((i == true ? 1 : 0) & 256) != 0) {
                    iVar.p = Collections.unmodifiableList(iVar.p);
                }
                if (((i == true ? 1 : 0) & 8) != 0) {
                    ((com.google.protobuf.d) iVar.k).e = false;
                }
                if (((i == true ? 1 : 0) & 16) != 0) {
                    ((com.google.protobuf.d) iVar.l).e = false;
                }
                iVar.f = aVar.build();
                return iVar;
            }
        }

        /* JADX INFO: compiled from: DescriptorProtos.java */
        public static final class b extends a0.a<b> implements t0 {
            public int g;
            public Object h = "";
            public Object i = "";
            public q61 j = g0.g;
            public c0.c k;
            public c0.c l;
            public List<a> m;
            public List<b> n;
            public List<p> o;
            public List<g> p;
            public j q;
            public r r;
            public Object s;

            public b() {
                b0 b0Var = b0.h;
                this.k = b0Var;
                this.l = b0Var;
                this.m = Collections.emptyList();
                this.n = Collections.emptyList();
                this.o = Collections.emptyList();
                this.p = Collections.emptyList();
                this.s = "";
            }

            @Override // com.google.protobuf.a0.a
            /* JADX INFO: renamed from: A */
            public final a0.a f(n.f fVar, Object obj) {
                super.f(fVar, obj);
                return this;
            }

            @Override // com.google.protobuf.a0.a
            /* JADX INFO: renamed from: B */
            public final a0.a clone() {
                return (b) super.clone();
            }

            @Override // com.google.protobuf.a0.a
            public final a0.e D() {
                a0.e eVar = m.b;
                eVar.c(i.class, b.class);
                return eVar;
            }

            @Override // com.google.protobuf.a0.a
            /* JADX INFO: renamed from: E */
            public final a0.a y(j1 j1Var) {
                return (b) super.y(j1Var);
            }

            @Override // com.google.protobuf.a0.a
            /* JADX INFO: renamed from: I */
            public final a0.a a(n.f fVar, Object obj) {
                super.a(fVar, obj);
                return this;
            }

            @Override // com.google.protobuf.a0.a
            /* JADX INFO: renamed from: J */
            public final a0.a j0(j1 j1Var) {
                this.f = j1Var;
                H();
                return this;
            }

            @Override // com.google.protobuf.r0.a, com.google.protobuf.q0.a
            /* JADX INFO: renamed from: K, reason: merged with bridge method [inline-methods] */
            public final i d() {
                i iVar = new i(this);
                int i = this.g;
                int i2 = (i & 1) != 0 ? 1 : 0;
                iVar.h = this.h;
                if ((i & 2) != 0) {
                    i2 |= 2;
                }
                iVar.i = this.i;
                if ((this.g & 4) != 0) {
                    this.j = this.j.m();
                    this.g &= -5;
                }
                iVar.j = this.j;
                int i3 = this.g;
                if ((i3 & 8) != 0) {
                    ((com.google.protobuf.d) this.k).e = false;
                    this.g = i3 & (-9);
                }
                iVar.k = this.k;
                int i4 = this.g;
                if ((i4 & 16) != 0) {
                    ((com.google.protobuf.d) this.l).e = false;
                    this.g = i4 & (-17);
                }
                iVar.l = this.l;
                if ((this.g & 32) != 0) {
                    this.m = Collections.unmodifiableList(this.m);
                    this.g &= -33;
                }
                iVar.m = this.m;
                if ((this.g & 64) != 0) {
                    this.n = Collections.unmodifiableList(this.n);
                    this.g &= -65;
                }
                iVar.n = this.n;
                if ((this.g & 128) != 0) {
                    this.o = Collections.unmodifiableList(this.o);
                    this.g &= -129;
                }
                iVar.o = this.o;
                if ((this.g & 256) != 0) {
                    this.p = Collections.unmodifiableList(this.p);
                    this.g &= -257;
                }
                iVar.p = this.p;
                if ((i & 512) != 0) {
                    iVar.q = this.q;
                    i2 |= 4;
                }
                if ((i & 1024) != 0) {
                    iVar.r = this.r;
                    i2 |= 8;
                }
                if ((i & 2048) != 0) {
                    i2 |= 16;
                }
                iVar.s = this.s;
                iVar.g = i2;
                F();
                return iVar;
            }

            @Override // com.google.protobuf.a.AbstractC0042a, com.google.protobuf.r0.a
            public final /* bridge */ /* synthetic */ r0.a L(com.google.protobuf.g gVar, com.google.protobuf.s sVar) throws Throwable {
                N(gVar, sVar);
                return this;
            }

            public final void M(i iVar) {
                r rVar;
                r rVar2;
                j jVar;
                j jVar2;
                if (iVar == i.u) {
                    return;
                }
                if ((iVar.g & 1) != 0) {
                    this.g |= 1;
                    this.h = iVar.h;
                    H();
                }
                if ((iVar.g & 2) != 0) {
                    this.g |= 2;
                    this.i = iVar.i;
                    H();
                }
                if (!iVar.j.isEmpty()) {
                    if (this.j.isEmpty()) {
                        this.j = iVar.j;
                        this.g &= -5;
                    } else {
                        if ((this.g & 4) == 0) {
                            this.j = new g0(this.j);
                            this.g |= 4;
                        }
                        this.j.addAll(iVar.j);
                    }
                    H();
                }
                if (!iVar.k.isEmpty()) {
                    if (this.k.isEmpty()) {
                        this.k = iVar.k;
                        this.g &= -9;
                    } else {
                        if ((this.g & 8) == 0) {
                            this.k = a0.N(this.k);
                            this.g |= 8;
                        }
                        ((b0) this.k).addAll(iVar.k);
                    }
                    H();
                }
                if (!iVar.l.isEmpty()) {
                    if (this.l.isEmpty()) {
                        this.l = iVar.l;
                        this.g &= -17;
                    } else {
                        if ((this.g & 16) == 0) {
                            this.l = a0.N(this.l);
                            this.g |= 16;
                        }
                        ((b0) this.l).addAll(iVar.l);
                    }
                    H();
                }
                if (!iVar.m.isEmpty()) {
                    if (this.m.isEmpty()) {
                        this.m = iVar.m;
                        this.g &= -33;
                    } else {
                        if ((this.g & 32) == 0) {
                            this.m = new ArrayList(this.m);
                            this.g |= 32;
                        }
                        this.m.addAll(iVar.m);
                    }
                    H();
                }
                if (!iVar.n.isEmpty()) {
                    if (this.n.isEmpty()) {
                        this.n = iVar.n;
                        this.g &= -65;
                    } else {
                        if ((this.g & 64) == 0) {
                            this.n = new ArrayList(this.n);
                            this.g |= 64;
                        }
                        this.n.addAll(iVar.n);
                    }
                    H();
                }
                if (!iVar.o.isEmpty()) {
                    if (this.o.isEmpty()) {
                        this.o = iVar.o;
                        this.g &= -129;
                    } else {
                        if ((this.g & 128) == 0) {
                            this.o = new ArrayList(this.o);
                            this.g |= 128;
                        }
                        this.o.addAll(iVar.o);
                    }
                    H();
                }
                if (!iVar.p.isEmpty()) {
                    if (this.p.isEmpty()) {
                        this.p = iVar.p;
                        this.g &= -257;
                    } else {
                        if ((this.g & 256) == 0) {
                            this.p = new ArrayList(this.p);
                            this.g |= 256;
                        }
                        this.p.addAll(iVar.p);
                    }
                    H();
                }
                if (iVar.W()) {
                    j jVarS = iVar.S();
                    if ((this.g & 512) == 0 || (jVar = this.q) == null || jVar == (jVar2 = j.E)) {
                        this.q = jVarS;
                    } else {
                        j.b bVarH = jVar2.h();
                        bVarH.Q(jVar);
                        bVarH.Q(jVarS);
                        this.q = bVarH.d();
                    }
                    H();
                    this.g |= 512;
                }
                if ((iVar.g & 8) != 0) {
                    r rVarU = iVar.U();
                    if ((this.g & 1024) == 0 || (rVar = this.r) == null || rVar == (rVar2 = r.i)) {
                        this.r = rVarU;
                    } else {
                        r.b bVarH2 = rVar2.h();
                        bVarH2.M(rVar);
                        bVarH2.M(rVarU);
                        this.r = bVarH2.d();
                    }
                    H();
                    this.g |= 1024;
                }
                if ((iVar.g & 16) != 0) {
                    this.g |= 2048;
                    this.s = iVar.s;
                    H();
                }
                H();
            }

            /* JADX WARN: Removed duplicated region for block: B:15:0x001d  */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public final void N(com.google.protobuf.g r2, com.google.protobuf.s r3) throws java.lang.Throwable {
                /*
                    r1 = this;
                    com.google.protobuf.m$i$a r0 = com.google.protobuf.m.i.v     // Catch: java.lang.Throwable -> Lc com.google.protobuf.d0 -> Le
                    java.lang.Object r2 = r0.b(r2, r3)     // Catch: java.lang.Throwable -> Lc com.google.protobuf.d0 -> Le
                    com.google.protobuf.m$i r2 = (com.google.protobuf.m.i) r2     // Catch: java.lang.Throwable -> Lc com.google.protobuf.d0 -> Le
                    r1.M(r2)
                    return
                Lc:
                    r2 = move-exception
                    goto L1a
                Le:
                    r2 = move-exception
                    com.google.protobuf.r0 r3 = r2.e     // Catch: java.lang.Throwable -> Lc
                    com.google.protobuf.m$i r3 = (com.google.protobuf.m.i) r3     // Catch: java.lang.Throwable -> Lc
                    java.io.IOException r2 = r2.l()     // Catch: java.lang.Throwable -> L18
                    throw r2     // Catch: java.lang.Throwable -> L18
                L18:
                    r2 = move-exception
                    goto L1b
                L1a:
                    r3 = 0
                L1b:
                    if (r3 == 0) goto L20
                    r1.M(r3)
                L20:
                    throw r2
                */
                throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.m.i.b.N(com.google.protobuf.g, com.google.protobuf.s):void");
            }

            @Override // com.google.protobuf.a.AbstractC0042a, com.google.protobuf.q0.a
            public final q0.a R(q0 q0Var) {
                if (q0Var instanceof i) {
                    M((i) q0Var);
                } else {
                    super.R(q0Var);
                }
                return this;
            }

            @Override // com.google.protobuf.a0.a, com.google.protobuf.q0.a
            public final q0.a a(n.f fVar, Object obj) {
                super.a(fVar, obj);
                return this;
            }

            @Override // defpackage.bh1, com.google.protobuf.t0
            public final q0 b() {
                return i.u;
            }

            @Override // com.google.protobuf.r0.a, com.google.protobuf.q0.a
            public final q0 build() {
                i iVarD = d();
                if (iVarD.isInitialized()) {
                    return iVarD;
                }
                throw a.AbstractC0042a.z(iVarD);
            }

            @Override // com.google.protobuf.a0.a, com.google.protobuf.q0.a, com.google.protobuf.t0
            public final n.a c() {
                return m.a;
            }

            @Override // com.google.protobuf.a0.a
            public final Object clone() {
                return (b) super.clone();
            }

            @Override // com.google.protobuf.a0.a, com.google.protobuf.q0.a
            public final q0.a f(n.f fVar, Object obj) {
                super.f(fVar, obj);
                return this;
            }

            @Override // com.google.protobuf.a0.a, com.google.protobuf.q0.a
            public final q0.a j0(j1 j1Var) {
                this.f = j1Var;
                H();
                return this;
            }

            @Override // com.google.protobuf.a.AbstractC0042a
            /* JADX INFO: renamed from: v */
            public final /* bridge */ /* synthetic */ a.AbstractC0042a L(com.google.protobuf.g gVar, com.google.protobuf.s sVar) throws Throwable {
                N(gVar, sVar);
                return this;
            }

            @Override // com.google.protobuf.a.AbstractC0042a
            /* JADX INFO: renamed from: x */
            public final a.AbstractC0042a R(q0 q0Var) {
                if (q0Var instanceof i) {
                    M((i) q0Var);
                } else {
                    super.R(q0Var);
                }
                return this;
            }

            @Override // com.google.protobuf.a0.a, com.google.protobuf.a.AbstractC0042a
            public final void y(j1 j1Var) {
            }

            @Override // defpackage.bh1, com.google.protobuf.t0
            public final r0 b() {
                return i.u;
            }

            @Override // com.google.protobuf.r0.a, com.google.protobuf.q0.a
            public final r0 build() {
                i iVarD = d();
                if (iVarD.isInitialized()) {
                    return iVarD;
                }
                throw a.AbstractC0042a.z(iVarD);
            }
        }

        public i(a0.a aVar) {
            super(aVar);
            this.t = (byte) -1;
        }

        @Override // com.google.protobuf.a0
        public final a0.e M() {
            a0.e eVar = m.b;
            eVar.c(i.class, b.class);
            return eVar;
        }

        @Override // com.google.protobuf.a0
        public final Object O() {
            return new i();
        }

        public final String Q() {
            Object obj = this.h;
            if (obj instanceof String) {
                return (String) obj;
            }
            lk lkVar = (lk) obj;
            String strZ = lkVar.z();
            if (lkVar.q()) {
                this.h = strZ;
            }
            return strZ;
        }

        public final j S() {
            j jVar = this.q;
            return jVar == null ? j.E : jVar;
        }

        public final String T() {
            Object obj = this.i;
            if (obj instanceof String) {
                return (String) obj;
            }
            lk lkVar = (lk) obj;
            String strZ = lkVar.z();
            if (lkVar.q()) {
                this.i = strZ;
            }
            return strZ;
        }

        public final r U() {
            r rVar = this.r;
            return rVar == null ? r.i : rVar;
        }

        public final String V() {
            Object obj = this.s;
            if (obj instanceof String) {
                return (String) obj;
            }
            lk lkVar = (lk) obj;
            String strZ = lkVar.z();
            if (lkVar.q()) {
                this.s = strZ;
            }
            return strZ;
        }

        public final boolean W() {
            return (this.g & 4) != 0;
        }

        @Override // com.google.protobuf.r0
        /* JADX INFO: renamed from: X, reason: merged with bridge method [inline-methods] */
        public final b h() {
            if (this == u) {
                return new b();
            }
            b bVar = new b();
            bVar.M(this);
            return bVar;
        }

        @Override // defpackage.bh1, com.google.protobuf.t0
        public final q0 b() {
            return u;
        }

        @Override // com.google.protobuf.r0, com.google.protobuf.q0
        public final q0.a e() {
            return u.h();
        }

        @Override // com.google.protobuf.a
        public final boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof i)) {
                return super.equals(obj);
            }
            i iVar = (i) obj;
            int i = this.g;
            if (((i & 1) != 0) != ((iVar.g & 1) != 0)) {
                return false;
            }
            if (((i & 1) != 0) && !Q().equals(iVar.Q())) {
                return false;
            }
            int i2 = this.g;
            if (((i2 & 2) != 0) != ((iVar.g & 2) != 0)) {
                return false;
            }
            if ((((i2 & 2) != 0) && !T().equals(iVar.T())) || !this.j.equals(iVar.j)) {
                return false;
            }
            if (!((b0) this.k).equals(iVar.k)) {
                return false;
            }
            if (!((b0) this.l).equals(iVar.l) || !this.m.equals(iVar.m) || !this.n.equals(iVar.n) || !this.o.equals(iVar.o) || !this.p.equals(iVar.p) || W() != iVar.W()) {
                return false;
            }
            if (W() && !S().equals(iVar.S())) {
                return false;
            }
            int i3 = this.g;
            if (((i3 & 8) != 0) != ((iVar.g & 8) != 0)) {
                return false;
            }
            if (((i3 & 8) != 0) && !U().equals(iVar.U())) {
                return false;
            }
            int i4 = this.g;
            if (((i4 & 16) != 0) != ((iVar.g & 16) != 0)) {
                return false;
            }
            return (!((i4 & 16) != 0) || V().equals(iVar.V())) && this.f.equals(iVar.f);
        }

        @Override // com.google.protobuf.a
        public final int hashCode() {
            int i = this.memoizedHashCode;
            if (i != 0) {
                return i;
            }
            int iHashCode = m.a.hashCode() + 779;
            if ((this.g & 1) != 0) {
                iHashCode = C0175b.e(iHashCode, 37, 1, 53) + Q().hashCode();
            }
            if ((this.g & 2) != 0) {
                iHashCode = C0175b.e(iHashCode, 37, 2, 53) + T().hashCode();
            }
            if (this.j.size() > 0) {
                iHashCode = C0175b.e(iHashCode, 37, 3, 53) + this.j.hashCode();
            }
            c0.c cVar = this.k;
            if (((b0) cVar).g > 0) {
                iHashCode = C0175b.e(iHashCode, 37, 10, 53) + ((b0) cVar).hashCode();
            }
            c0.c cVar2 = this.l;
            if (((b0) cVar2).g > 0) {
                iHashCode = C0175b.e(iHashCode, 37, 11, 53) + ((b0) cVar2).hashCode();
            }
            if (this.m.size() > 0) {
                iHashCode = C0175b.e(iHashCode, 37, 4, 53) + this.m.hashCode();
            }
            if (this.n.size() > 0) {
                iHashCode = C0175b.e(iHashCode, 37, 5, 53) + this.n.hashCode();
            }
            if (this.o.size() > 0) {
                iHashCode = C0175b.e(iHashCode, 37, 6, 53) + this.o.hashCode();
            }
            if (this.p.size() > 0) {
                iHashCode = C0175b.e(iHashCode, 37, 7, 53) + this.p.hashCode();
            }
            if (W()) {
                iHashCode = C0175b.e(iHashCode, 37, 8, 53) + S().hashCode();
            }
            if ((this.g & 8) != 0) {
                iHashCode = C0175b.e(iHashCode, 37, 9, 53) + U().hashCode();
            }
            if ((this.g & 16) != 0) {
                iHashCode = C0175b.e(iHashCode, 37, 12, 53) + V().hashCode();
            }
            int iHashCode2 = this.f.hashCode() + (iHashCode * 29);
            this.memoizedHashCode = iHashCode2;
            return iHashCode2;
        }

        @Override // com.google.protobuf.a0, defpackage.bh1
        public final boolean isInitialized() {
            byte b2 = this.t;
            if (b2 == 1) {
                return true;
            }
            if (b2 == 0) {
                return false;
            }
            for (int i = 0; i < this.m.size(); i++) {
                if (!this.m.get(i).isInitialized()) {
                    this.t = (byte) 0;
                    return false;
                }
            }
            for (int i2 = 0; i2 < this.n.size(); i2++) {
                if (!this.n.get(i2).isInitialized()) {
                    this.t = (byte) 0;
                    return false;
                }
            }
            for (int i3 = 0; i3 < this.o.size(); i3++) {
                if (!this.o.get(i3).isInitialized()) {
                    this.t = (byte) 0;
                    return false;
                }
            }
            for (int i4 = 0; i4 < this.p.size(); i4++) {
                if (!this.p.get(i4).isInitialized()) {
                    this.t = (byte) 0;
                    return false;
                }
            }
            if (!W() || S().isInitialized()) {
                this.t = (byte) 1;
                return true;
            }
            this.t = (byte) 0;
            return false;
        }

        @Override // com.google.protobuf.a0, com.google.protobuf.r0
        public final int m() {
            int i;
            int i2;
            int i3 = this.e;
            if (i3 != -1) {
                return i3;
            }
            int i4 = 0;
            int iH = (this.g & 1) != 0 ? a0.H(1, this.h) + 0 : 0;
            if ((this.g & 2) != 0) {
                iH += a0.H(2, this.i);
            }
            int I = 0;
            for (int i5 = 0; i5 < this.j.size(); i5++) {
                I += a0.I(this.j.n(i5));
            }
            int size = (this.j.size() * 1) + iH + I;
            for (int i6 = 0; i6 < this.m.size(); i6++) {
                size += nu.a0(4, this.m.get(i6));
            }
            for (int i7 = 0; i7 < this.n.size(); i7++) {
                size += nu.a0(5, this.n.get(i7));
            }
            for (int i8 = 0; i8 < this.o.size(); i8++) {
                size += nu.a0(6, this.o.get(i8));
            }
            for (int i9 = 0; i9 < this.p.size(); i9++) {
                size += nu.a0(7, this.p.get(i9));
            }
            if ((this.g & 4) != 0) {
                size += nu.a0(8, S());
            }
            if ((this.g & 8) != 0) {
                size += nu.a0(9, U());
            }
            int i10 = 0;
            int iT = 0;
            while (true) {
                b0 b0Var = (b0) this.k;
                i = b0Var.g;
                if (i10 >= i) {
                    break;
                }
                iT += nu.T(b0Var.d(i10));
                i10++;
            }
            int i11 = (i * 1) + size + iT;
            int iT2 = 0;
            while (true) {
                b0 b0Var2 = (b0) this.l;
                i2 = b0Var2.g;
                if (i4 >= i2) {
                    break;
                }
                iT2 += nu.T(b0Var2.d(i4));
                i4++;
            }
            int iH2 = (i2 * 1) + i11 + iT2;
            if ((this.g & 16) != 0) {
                iH2 += a0.H(12, this.s);
            }
            int iM = this.f.m() + iH2;
            this.e = iM;
            return iM;
        }

        @Override // com.google.protobuf.a0, com.google.protobuf.t0
        public final j1 n() {
            return this.f;
        }

        @Override // com.google.protobuf.a0, com.google.protobuf.r0
        public final void p(nu nuVar) {
            if ((this.g & 1) != 0) {
                a0.P(nuVar, 1, this.h);
            }
            if ((this.g & 2) != 0) {
                a0.P(nuVar, 2, this.i);
            }
            int i = 0;
            for (int i2 = 0; i2 < this.j.size(); i2++) {
                a0.P(nuVar, 3, this.j.n(i2));
            }
            for (int i3 = 0; i3 < this.m.size(); i3++) {
                nuVar.D0(4, this.m.get(i3));
            }
            for (int i4 = 0; i4 < this.n.size(); i4++) {
                nuVar.D0(5, this.n.get(i4));
            }
            for (int i5 = 0; i5 < this.o.size(); i5++) {
                nuVar.D0(6, this.o.get(i5));
            }
            for (int i6 = 0; i6 < this.p.size(); i6++) {
                nuVar.D0(7, this.p.get(i6));
            }
            if ((this.g & 4) != 0) {
                nuVar.D0(8, S());
            }
            if ((this.g & 8) != 0) {
                nuVar.D0(9, U());
            }
            int i7 = 0;
            while (true) {
                c0.c cVar = this.k;
                if (i7 >= ((b0) cVar).g) {
                    break;
                }
                nuVar.z0(10, ((b0) cVar).d(i7));
                i7++;
            }
            while (true) {
                c0.c cVar2 = this.l;
                if (i >= ((b0) cVar2).g) {
                    break;
                }
                nuVar.z0(11, ((b0) cVar2).d(i));
                i++;
            }
            if ((this.g & 16) != 0) {
                a0.P(nuVar, 12, this.s);
            }
            this.f.p(nuVar);
        }

        @Override // com.google.protobuf.a0, com.google.protobuf.r0
        public final wv1<i> u() {
            return v;
        }

        @Override // defpackage.bh1, com.google.protobuf.t0
        public final r0 b() {
            return u;
        }

        @Override // com.google.protobuf.r0, com.google.protobuf.q0
        public final r0.a e() {
            return u.h();
        }

        public i() {
            this.t = (byte) -1;
            this.h = "";
            this.i = "";
            this.j = g0.g;
            b0 b0Var = b0.h;
            this.k = b0Var;
            this.l = b0Var;
            this.m = Collections.emptyList();
            this.n = Collections.emptyList();
            this.o = Collections.emptyList();
            this.p = Collections.emptyList();
            this.s = "";
        }
    }

    /* JADX INFO: compiled from: DescriptorProtos.java */
    public static final class j extends a0.d<j> {
        public static final j E = new j();

        @Deprecated
        public static final a F = new a();
        private static final long serialVersionUID = 0;
        public volatile Object A;
        public volatile Object B;
        public List<s> C;
        public byte D;
        public int h;
        public volatile Object i;
        public volatile Object j;
        public boolean k;
        public boolean l;
        public boolean m;
        public int n;
        public volatile Object o;
        public boolean p;
        public boolean q;
        public boolean r;
        public boolean s;
        public boolean t;
        public boolean u;
        public volatile Object v;
        public volatile Object w;
        public volatile Object x;
        public volatile Object y;
        public volatile Object z;

        /* JADX INFO: compiled from: DescriptorProtos.java */
        public static class a extends com.google.protobuf.c<j> {
            /* JADX WARN: Multi-variable type inference failed */
            /* JADX WARN: Type inference failed for: r3v0 */
            /* JADX WARN: Type inference failed for: r3v1 */
            /* JADX WARN: Type inference failed for: r3v2, types: [boolean] */
            @Override // defpackage.wv1
            public final Object b(com.google.protobuf.g gVar, com.google.protobuf.s sVar) {
                j jVar = new j();
                sVar.getClass();
                j1 j1Var = j1.f;
                j1.a aVar = new j1.a();
                boolean z = false;
                int i = 0;
                while (true) {
                    ?? V = 1048576;
                    if (z) {
                        if ((i & 1048576) != 0) {
                            jVar.C = Collections.unmodifiableList(jVar.C);
                        }
                        jVar.f = aVar.build();
                        jVar.U();
                        return jVar;
                    }
                    try {
                        try {
                            int iF = gVar.F();
                            switch (iF) {
                                case 0:
                                    z = true;
                                    break;
                                case 10:
                                    lk.f fVarM = gVar.m();
                                    jVar.h = 1 | jVar.h;
                                    jVar.i = fVarM;
                                    break;
                                case 66:
                                    lk.f fVarM2 = gVar.m();
                                    jVar.h |= 2;
                                    jVar.j = fVarM2;
                                    break;
                                case 72:
                                    int iO = gVar.o();
                                    if (c.a(iO) == null) {
                                        aVar.y(9, iO);
                                    } else {
                                        jVar.h |= 32;
                                        jVar.n = iO;
                                    }
                                    break;
                                case 80:
                                    jVar.h |= 4;
                                    jVar.k = gVar.l();
                                    break;
                                case 90:
                                    lk.f fVarM3 = gVar.m();
                                    jVar.h |= 64;
                                    jVar.o = fVarM3;
                                    break;
                                case 128:
                                    jVar.h |= 128;
                                    jVar.p = gVar.l();
                                    break;
                                case 136:
                                    jVar.h |= 256;
                                    jVar.q = gVar.l();
                                    break;
                                case 144:
                                    jVar.h |= 512;
                                    jVar.r = gVar.l();
                                    break;
                                case 160:
                                    jVar.h |= 8;
                                    jVar.l = gVar.l();
                                    break;
                                case 184:
                                    jVar.h |= 2048;
                                    jVar.t = gVar.l();
                                    break;
                                case 216:
                                    jVar.h |= 16;
                                    jVar.m = gVar.l();
                                    break;
                                case 248:
                                    jVar.h |= 4096;
                                    jVar.u = gVar.l();
                                    break;
                                case 290:
                                    lk.f fVarM4 = gVar.m();
                                    jVar.h |= 8192;
                                    jVar.v = fVarM4;
                                    break;
                                case 298:
                                    lk.f fVarM5 = gVar.m();
                                    jVar.h |= 16384;
                                    jVar.w = fVarM5;
                                    break;
                                case 314:
                                    lk.f fVarM6 = gVar.m();
                                    jVar.h |= 32768;
                                    jVar.x = fVarM6;
                                    break;
                                case 322:
                                    lk.f fVarM7 = gVar.m();
                                    jVar.h |= 65536;
                                    jVar.y = fVarM7;
                                    break;
                                case 330:
                                    lk.f fVarM8 = gVar.m();
                                    jVar.h |= 131072;
                                    jVar.z = fVarM8;
                                    break;
                                case 336:
                                    jVar.h |= 1024;
                                    jVar.s = gVar.l();
                                    break;
                                case 354:
                                    lk.f fVarM9 = gVar.m();
                                    jVar.h |= 262144;
                                    jVar.A = fVarM9;
                                    break;
                                case 362:
                                    lk.f fVarM10 = gVar.m();
                                    jVar.h |= 524288;
                                    jVar.B = fVarM10;
                                    break;
                                case 7994:
                                    if ((i & 1048576) == 0) {
                                        jVar.C = new ArrayList();
                                        i |= 1048576;
                                    }
                                    jVar.C.add((s) gVar.v(s.q, sVar));
                                    break;
                                default:
                                    V = jVar.V(gVar, aVar, sVar, iF);
                                    if (V == 0) {
                                        z = true;
                                    }
                                    break;
                            }
                        } catch (d0 e) {
                            e.e = jVar;
                            throw e;
                        } catch (IOException e2) {
                            d0 d0Var = new d0(e2);
                            d0Var.e = jVar;
                            throw d0Var;
                        }
                    } catch (Throwable th) {
                        if ((i & V) != 0) {
                            jVar.C = Collections.unmodifiableList(jVar.C);
                        }
                        jVar.f = aVar.build();
                        jVar.U();
                        throw th;
                    }
                }
            }
        }

        /* JADX INFO: compiled from: DescriptorProtos.java */
        public static final class b extends a0.c<j, b> {
            public int h;
            public boolean k;
            public boolean l;
            public boolean m;
            public boolean p;
            public boolean q;
            public boolean r;
            public boolean s;
            public boolean t;
            public Object i = "";
            public Object j = "";
            public int n = 1;
            public Object o = "";
            public boolean u = true;
            public Object v = "";
            public Object w = "";
            public Object x = "";
            public Object y = "";
            public Object z = "";
            public Object A = "";
            public Object B = "";
            public List<s> C = Collections.emptyList();

            @Override // com.google.protobuf.a0.a
            /* JADX INFO: renamed from: A */
            public final a0.a f(n.f fVar, Object obj) {
                super.K(fVar, obj);
                return this;
            }

            @Override // com.google.protobuf.a0.a
            /* JADX INFO: renamed from: B */
            public final a0.a clone() {
                return (b) super.clone();
            }

            @Override // com.google.protobuf.a0.a
            public final a0.e D() {
                a0.e eVar = m.z;
                eVar.c(j.class, b.class);
                return eVar;
            }

            @Override // com.google.protobuf.a0.a
            /* JADX INFO: renamed from: E */
            public final a0.a y(j1 j1Var) {
                return (b) super.y(j1Var);
            }

            @Override // com.google.protobuf.a0.a
            /* JADX INFO: renamed from: I */
            public final a0.a a(n.f fVar, Object obj) {
                return (b) super.N(fVar, obj);
            }

            @Override // com.google.protobuf.a0.a
            /* JADX INFO: renamed from: J */
            public final a0.a j0(j1 j1Var) {
                this.f = j1Var;
                H();
                return this;
            }

            @Override // com.google.protobuf.a.AbstractC0042a, com.google.protobuf.r0.a
            public final /* bridge */ /* synthetic */ r0.a L(com.google.protobuf.g gVar, com.google.protobuf.s sVar) throws Throwable {
                S(gVar, sVar);
                return this;
            }

            @Override // com.google.protobuf.r0.a, com.google.protobuf.q0.a
            /* JADX INFO: renamed from: P, reason: merged with bridge method [inline-methods] */
            public final j d() {
                j jVar = new j(this);
                int i = this.h;
                int i2 = (i & 1) != 0 ? 1 : 0;
                jVar.i = this.i;
                if ((i & 2) != 0) {
                    i2 |= 2;
                }
                jVar.j = this.j;
                if ((i & 4) != 0) {
                    jVar.k = this.k;
                    i2 |= 4;
                }
                if ((i & 8) != 0) {
                    jVar.l = this.l;
                    i2 |= 8;
                }
                if ((i & 16) != 0) {
                    jVar.m = this.m;
                    i2 |= 16;
                }
                if ((i & 32) != 0) {
                    i2 |= 32;
                }
                jVar.n = this.n;
                if ((i & 64) != 0) {
                    i2 |= 64;
                }
                jVar.o = this.o;
                if ((i & 128) != 0) {
                    jVar.p = this.p;
                    i2 |= 128;
                }
                if ((i & 256) != 0) {
                    jVar.q = this.q;
                    i2 |= 256;
                }
                if ((i & 512) != 0) {
                    jVar.r = this.r;
                    i2 |= 512;
                }
                if ((i & 1024) != 0) {
                    jVar.s = this.s;
                    i2 |= 1024;
                }
                if ((i & 2048) != 0) {
                    jVar.t = this.t;
                    i2 |= 2048;
                }
                if ((i & 4096) != 0) {
                    i2 |= 4096;
                }
                jVar.u = this.u;
                if ((i & 8192) != 0) {
                    i2 |= 8192;
                }
                jVar.v = this.v;
                if ((i & 16384) != 0) {
                    i2 |= 16384;
                }
                jVar.w = this.w;
                if ((i & 32768) != 0) {
                    i2 |= 32768;
                }
                jVar.x = this.x;
                if ((i & 65536) != 0) {
                    i2 |= 65536;
                }
                jVar.y = this.y;
                if ((i & 131072) != 0) {
                    i2 |= 131072;
                }
                jVar.z = this.z;
                if ((i & 262144) != 0) {
                    i2 |= 262144;
                }
                jVar.A = this.A;
                if ((i & 524288) != 0) {
                    i2 |= 524288;
                }
                jVar.B = this.B;
                if ((this.h & 1048576) != 0) {
                    this.C = Collections.unmodifiableList(this.C);
                    this.h &= -1048577;
                }
                jVar.C = this.C;
                jVar.h = i2;
                F();
                return jVar;
            }

            public final void Q(j jVar) {
                if (jVar == j.E) {
                    return;
                }
                if ((jVar.h & 1) != 0) {
                    this.h |= 1;
                    this.i = jVar.i;
                    H();
                }
                if ((jVar.h & 2) != 0) {
                    this.h |= 2;
                    this.j = jVar.j;
                    H();
                }
                if ((jVar.h & 4) != 0) {
                    boolean z = jVar.k;
                    this.h |= 4;
                    this.k = z;
                    H();
                }
                if ((jVar.h & 8) != 0) {
                    boolean z2 = jVar.l;
                    this.h |= 8;
                    this.l = z2;
                    H();
                }
                if ((jVar.h & 16) != 0) {
                    boolean z3 = jVar.m;
                    this.h |= 16;
                    this.m = z3;
                    H();
                }
                if ((jVar.h & 32) != 0) {
                    c cVarA = c.a(jVar.n);
                    if (cVarA == null) {
                        cVarA = c.SPEED;
                    }
                    this.h |= 32;
                    this.n = cVarA.e;
                    H();
                }
                if ((jVar.h & 64) != 0) {
                    this.h |= 64;
                    this.o = jVar.o;
                    H();
                }
                if ((jVar.h & 128) != 0) {
                    boolean z4 = jVar.p;
                    this.h |= 128;
                    this.p = z4;
                    H();
                }
                if ((jVar.h & 256) != 0) {
                    boolean z5 = jVar.q;
                    this.h |= 256;
                    this.q = z5;
                    H();
                }
                if ((jVar.h & 512) != 0) {
                    boolean z6 = jVar.r;
                    this.h |= 512;
                    this.r = z6;
                    H();
                }
                if ((jVar.h & 1024) != 0) {
                    boolean z7 = jVar.s;
                    this.h |= 1024;
                    this.s = z7;
                    H();
                }
                if ((jVar.h & 2048) != 0) {
                    boolean z8 = jVar.t;
                    this.h |= 2048;
                    this.t = z8;
                    H();
                }
                if ((jVar.h & 4096) != 0) {
                    boolean z9 = jVar.u;
                    this.h |= 4096;
                    this.u = z9;
                    H();
                }
                if ((jVar.h & 8192) != 0) {
                    this.h |= 8192;
                    this.v = jVar.v;
                    H();
                }
                if ((jVar.h & 16384) != 0) {
                    this.h |= 16384;
                    this.w = jVar.w;
                    H();
                }
                if (jVar.l0()) {
                    this.h |= 32768;
                    this.x = jVar.x;
                    H();
                }
                if (jVar.g0()) {
                    this.h |= 65536;
                    this.y = jVar.y;
                    H();
                }
                if (jVar.i0()) {
                    this.h |= 131072;
                    this.z = jVar.z;
                    H();
                }
                if (jVar.h0()) {
                    this.h |= 262144;
                    this.A = jVar.A;
                    H();
                }
                if (jVar.k0()) {
                    this.h |= 524288;
                    this.B = jVar.B;
                    H();
                }
                if (!jVar.C.isEmpty()) {
                    if (this.C.isEmpty()) {
                        this.C = jVar.C;
                        this.h = (-1048577) & this.h;
                    } else {
                        if ((this.h & 1048576) == 0) {
                            this.C = new ArrayList(this.C);
                            this.h |= 1048576;
                        }
                        this.C.addAll(jVar.C);
                    }
                    H();
                }
                M(jVar);
                H();
            }

            @Override // com.google.protobuf.a.AbstractC0042a, com.google.protobuf.q0.a
            public final q0.a R(q0 q0Var) {
                if (q0Var instanceof j) {
                    Q((j) q0Var);
                } else {
                    super.R(q0Var);
                }
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:15:0x001d  */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public final void S(com.google.protobuf.g r2, com.google.protobuf.s r3) throws java.lang.Throwable {
                /*
                    r1 = this;
                    com.google.protobuf.m$j$a r0 = com.google.protobuf.m.j.F     // Catch: java.lang.Throwable -> Lc com.google.protobuf.d0 -> Le
                    java.lang.Object r2 = r0.b(r2, r3)     // Catch: java.lang.Throwable -> Lc com.google.protobuf.d0 -> Le
                    com.google.protobuf.m$j r2 = (com.google.protobuf.m.j) r2     // Catch: java.lang.Throwable -> Lc com.google.protobuf.d0 -> Le
                    r1.Q(r2)
                    return
                Lc:
                    r2 = move-exception
                    goto L1a
                Le:
                    r2 = move-exception
                    com.google.protobuf.r0 r3 = r2.e     // Catch: java.lang.Throwable -> Lc
                    com.google.protobuf.m$j r3 = (com.google.protobuf.m.j) r3     // Catch: java.lang.Throwable -> Lc
                    java.io.IOException r2 = r2.l()     // Catch: java.lang.Throwable -> L18
                    throw r2     // Catch: java.lang.Throwable -> L18
                L18:
                    r2 = move-exception
                    goto L1b
                L1a:
                    r3 = 0
                L1b:
                    if (r3 == 0) goto L20
                    r1.Q(r3)
                L20:
                    throw r2
                */
                throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.m.j.b.S(com.google.protobuf.g, com.google.protobuf.s):void");
            }

            @Override // com.google.protobuf.a0.a, com.google.protobuf.q0.a
            public final q0.a a(n.f fVar, Object obj) {
                super.N(fVar, obj);
                return this;
            }

            @Override // defpackage.bh1, com.google.protobuf.t0
            public final q0 b() {
                return j.E;
            }

            @Override // com.google.protobuf.r0.a, com.google.protobuf.q0.a
            public final q0 build() {
                j jVarD = d();
                if (jVarD.isInitialized()) {
                    return jVarD;
                }
                throw a.AbstractC0042a.z(jVarD);
            }

            @Override // com.google.protobuf.a0.a, com.google.protobuf.q0.a, com.google.protobuf.t0
            public final n.a c() {
                return m.y;
            }

            @Override // com.google.protobuf.a0.a
            public final Object clone() {
                return (b) super.clone();
            }

            @Override // com.google.protobuf.a0.a, com.google.protobuf.q0.a
            public final q0.a f(n.f fVar, Object obj) {
                super.K(fVar, obj);
                return this;
            }

            @Override // com.google.protobuf.a0.a, com.google.protobuf.q0.a
            public final q0.a j0(j1 j1Var) {
                this.f = j1Var;
                H();
                return this;
            }

            @Override // com.google.protobuf.a.AbstractC0042a
            /* JADX INFO: renamed from: v */
            public final /* bridge */ /* synthetic */ a.AbstractC0042a L(com.google.protobuf.g gVar, com.google.protobuf.s sVar) throws Throwable {
                S(gVar, sVar);
                return this;
            }

            @Override // com.google.protobuf.a.AbstractC0042a
            /* JADX INFO: renamed from: x */
            public final a.AbstractC0042a R(q0 q0Var) {
                if (q0Var instanceof j) {
                    Q((j) q0Var);
                } else {
                    super.R(q0Var);
                }
                return this;
            }

            @Override // com.google.protobuf.a0.a, com.google.protobuf.a.AbstractC0042a
            public final void y(j1 j1Var) {
            }

            @Override // defpackage.bh1, com.google.protobuf.t0
            public final r0 b() {
                return j.E;
            }

            @Override // com.google.protobuf.r0.a, com.google.protobuf.q0.a
            public final r0 build() {
                j jVarD = d();
                if (jVarD.isInitialized()) {
                    return jVarD;
                }
                throw a.AbstractC0042a.z(jVarD);
            }
        }

        /* JADX INFO: compiled from: DescriptorProtos.java */
        public enum c implements c0.a {
            SPEED(1),
            CODE_SIZE(2),
            LITE_RUNTIME(3);

            public final int e;

            static {
                values();
            }

            c(int i2) {
                this.e = i2;
            }

            public static c a(int i2) {
                if (i2 == 1) {
                    return SPEED;
                }
                if (i2 == 2) {
                    return CODE_SIZE;
                }
                if (i2 != 3) {
                    return null;
                }
                return LITE_RUNTIME;
            }

            @Override // com.google.protobuf.c0.a
            public final int b() {
                return this.e;
            }
        }

        public j(a0.c cVar) {
            super(cVar);
            this.D = (byte) -1;
        }

        @Override // com.google.protobuf.a0
        public final a0.e M() {
            a0.e eVar = m.z;
            eVar.c(j.class, b.class);
            return eVar;
        }

        @Override // com.google.protobuf.a0
        public final Object O() {
            return new j();
        }

        public final String W() {
            Object obj = this.w;
            if (obj instanceof String) {
                return (String) obj;
            }
            lk lkVar = (lk) obj;
            String strZ = lkVar.z();
            if (lkVar.q()) {
                this.w = strZ;
            }
            return strZ;
        }

        public final String X() {
            Object obj = this.o;
            if (obj instanceof String) {
                return (String) obj;
            }
            lk lkVar = (lk) obj;
            String strZ = lkVar.z();
            if (lkVar.q()) {
                this.o = strZ;
            }
            return strZ;
        }

        public final String Y() {
            Object obj = this.j;
            if (obj instanceof String) {
                return (String) obj;
            }
            lk lkVar = (lk) obj;
            String strZ = lkVar.z();
            if (lkVar.q()) {
                this.j = strZ;
            }
            return strZ;
        }

        public final String Z() {
            Object obj = this.i;
            if (obj instanceof String) {
                return (String) obj;
            }
            lk lkVar = (lk) obj;
            String strZ = lkVar.z();
            if (lkVar.q()) {
                this.i = strZ;
            }
            return strZ;
        }

        public final String a0() {
            Object obj = this.v;
            if (obj instanceof String) {
                return (String) obj;
            }
            lk lkVar = (lk) obj;
            String strZ = lkVar.z();
            if (lkVar.q()) {
                this.v = strZ;
            }
            return strZ;
        }

        @Override // defpackage.bh1, com.google.protobuf.t0
        public final q0 b() {
            return E;
        }

        public final String b0() {
            Object obj = this.y;
            if (obj instanceof String) {
                return (String) obj;
            }
            lk lkVar = (lk) obj;
            String strZ = lkVar.z();
            if (lkVar.q()) {
                this.y = strZ;
            }
            return strZ;
        }

        public final String c0() {
            Object obj = this.A;
            if (obj instanceof String) {
                return (String) obj;
            }
            lk lkVar = (lk) obj;
            String strZ = lkVar.z();
            if (lkVar.q()) {
                this.A = strZ;
            }
            return strZ;
        }

        public final String d0() {
            Object obj = this.z;
            if (obj instanceof String) {
                return (String) obj;
            }
            lk lkVar = (lk) obj;
            String strZ = lkVar.z();
            if (lkVar.q()) {
                this.z = strZ;
            }
            return strZ;
        }

        @Override // com.google.protobuf.r0, com.google.protobuf.q0
        public final q0.a e() {
            return E.h();
        }

        public final String e0() {
            Object obj = this.B;
            if (obj instanceof String) {
                return (String) obj;
            }
            lk lkVar = (lk) obj;
            String strZ = lkVar.z();
            if (lkVar.q()) {
                this.B = strZ;
            }
            return strZ;
        }

        @Override // com.google.protobuf.a
        public final boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof j)) {
                return super.equals(obj);
            }
            j jVar = (j) obj;
            int i = this.h;
            if (((i & 1) != 0) != ((jVar.h & 1) != 0)) {
                return false;
            }
            if (((i & 1) != 0) && !Z().equals(jVar.Z())) {
                return false;
            }
            int i2 = this.h;
            if (((i2 & 2) != 0) != ((jVar.h & 2) != 0)) {
                return false;
            }
            if (((i2 & 2) != 0) && !Y().equals(jVar.Y())) {
                return false;
            }
            int i3 = this.h;
            boolean z = (i3 & 4) != 0;
            int i4 = jVar.h;
            if (z != ((i4 & 4) != 0)) {
                return false;
            }
            if (((i3 & 4) != 0) && this.k != jVar.k) {
                return false;
            }
            if (((i3 & 8) != 0) != ((i4 & 8) != 0)) {
                return false;
            }
            if (((i3 & 8) != 0) && this.l != jVar.l) {
                return false;
            }
            if (((i3 & 16) != 0) != ((i4 & 16) != 0)) {
                return false;
            }
            if (((i3 & 16) != 0) && this.m != jVar.m) {
                return false;
            }
            if (((i3 & 32) != 0) != ((i4 & 32) != 0)) {
                return false;
            }
            if (((i3 & 32) != 0) && this.n != jVar.n) {
                return false;
            }
            if (((i3 & 64) != 0) != ((i4 & 64) != 0)) {
                return false;
            }
            if (((i3 & 64) != 0) && !X().equals(jVar.X())) {
                return false;
            }
            int i5 = this.h;
            boolean z2 = (i5 & 128) != 0;
            int i6 = jVar.h;
            if (z2 != ((i6 & 128) != 0)) {
                return false;
            }
            if (((i5 & 128) != 0) && this.p != jVar.p) {
                return false;
            }
            if (((i5 & 256) != 0) != ((i6 & 256) != 0)) {
                return false;
            }
            if (((i5 & 256) != 0) && this.q != jVar.q) {
                return false;
            }
            if (((i5 & 512) != 0) != ((i6 & 512) != 0)) {
                return false;
            }
            if (((i5 & 512) != 0) && this.r != jVar.r) {
                return false;
            }
            if (((i5 & 1024) != 0) != ((i6 & 1024) != 0)) {
                return false;
            }
            if (((i5 & 1024) != 0) && this.s != jVar.s) {
                return false;
            }
            if (((i5 & 2048) != 0) != ((i6 & 2048) != 0)) {
                return false;
            }
            if (((i5 & 2048) != 0) && this.t != jVar.t) {
                return false;
            }
            if (((i5 & 4096) != 0) != ((i6 & 4096) != 0)) {
                return false;
            }
            if (((i5 & 4096) != 0) && this.u != jVar.u) {
                return false;
            }
            if (((i5 & 8192) != 0) != ((i6 & 8192) != 0)) {
                return false;
            }
            if (((i5 & 8192) != 0) && !a0().equals(jVar.a0())) {
                return false;
            }
            int i7 = this.h;
            if (((i7 & 16384) != 0) != ((jVar.h & 16384) != 0)) {
                return false;
            }
            if ((((i7 & 16384) != 0) && !W().equals(jVar.W())) || l0() != jVar.l0()) {
                return false;
            }
            if ((l0() && !f0().equals(jVar.f0())) || g0() != jVar.g0()) {
                return false;
            }
            if ((g0() && !b0().equals(jVar.b0())) || i0() != jVar.i0()) {
                return false;
            }
            if ((i0() && !d0().equals(jVar.d0())) || h0() != jVar.h0()) {
                return false;
            }
            if ((!h0() || c0().equals(jVar.c0())) && k0() == jVar.k0()) {
                return (!k0() || e0().equals(jVar.e0())) && this.C.equals(jVar.C) && this.f.equals(jVar.f) && T().equals(jVar.T());
            }
            return false;
        }

        public final String f0() {
            Object obj = this.x;
            if (obj instanceof String) {
                return (String) obj;
            }
            lk lkVar = (lk) obj;
            String strZ = lkVar.z();
            if (lkVar.q()) {
                this.x = strZ;
            }
            return strZ;
        }

        public final boolean g0() {
            return (this.h & 65536) != 0;
        }

        public final boolean h0() {
            return (this.h & 262144) != 0;
        }

        @Override // com.google.protobuf.a
        public final int hashCode() {
            int i = this.memoizedHashCode;
            if (i != 0) {
                return i;
            }
            int iHashCode = m.y.hashCode() + 779;
            if ((this.h & 1) != 0) {
                iHashCode = C0175b.e(iHashCode, 37, 1, 53) + Z().hashCode();
            }
            if ((this.h & 2) != 0) {
                iHashCode = C0175b.e(iHashCode, 37, 8, 53) + Y().hashCode();
            }
            if ((this.h & 4) != 0) {
                iHashCode = C0175b.e(iHashCode, 37, 10, 53) + c0.a(this.k);
            }
            if ((this.h & 8) != 0) {
                iHashCode = C0175b.e(iHashCode, 37, 20, 53) + c0.a(this.l);
            }
            if ((this.h & 16) != 0) {
                iHashCode = C0175b.e(iHashCode, 37, 27, 53) + c0.a(this.m);
            }
            int i2 = this.h;
            if ((i2 & 32) != 0) {
                iHashCode = C0175b.e(iHashCode, 37, 9, 53) + this.n;
            }
            if ((i2 & 64) != 0) {
                iHashCode = C0175b.e(iHashCode, 37, 11, 53) + X().hashCode();
            }
            if ((this.h & 128) != 0) {
                iHashCode = C0175b.e(iHashCode, 37, 16, 53) + c0.a(this.p);
            }
            if ((this.h & 256) != 0) {
                iHashCode = C0175b.e(iHashCode, 37, 17, 53) + c0.a(this.q);
            }
            if ((this.h & 512) != 0) {
                iHashCode = C0175b.e(iHashCode, 37, 18, 53) + c0.a(this.r);
            }
            if ((this.h & 1024) != 0) {
                iHashCode = C0175b.e(iHashCode, 37, 42, 53) + c0.a(this.s);
            }
            if ((this.h & 2048) != 0) {
                iHashCode = C0175b.e(iHashCode, 37, 23, 53) + c0.a(this.t);
            }
            if ((this.h & 4096) != 0) {
                iHashCode = C0175b.e(iHashCode, 37, 31, 53) + c0.a(this.u);
            }
            if ((this.h & 8192) != 0) {
                iHashCode = C0175b.e(iHashCode, 37, 36, 53) + a0().hashCode();
            }
            if ((this.h & 16384) != 0) {
                iHashCode = C0175b.e(iHashCode, 37, 37, 53) + W().hashCode();
            }
            if (l0()) {
                iHashCode = C0175b.e(iHashCode, 37, 39, 53) + f0().hashCode();
            }
            if (g0()) {
                iHashCode = C0175b.e(iHashCode, 37, 40, 53) + b0().hashCode();
            }
            if (i0()) {
                iHashCode = C0175b.e(iHashCode, 37, 41, 53) + d0().hashCode();
            }
            if (h0()) {
                iHashCode = C0175b.e(iHashCode, 37, 44, 53) + c0().hashCode();
            }
            if (k0()) {
                iHashCode = C0175b.e(iHashCode, 37, 45, 53) + e0().hashCode();
            }
            if (this.C.size() > 0) {
                iHashCode = C0175b.e(iHashCode, 37, 999, 53) + this.C.hashCode();
            }
            int iHashCode2 = this.f.hashCode() + (com.google.protobuf.a.D(iHashCode, T()) * 29);
            this.memoizedHashCode = iHashCode2;
            return iHashCode2;
        }

        public final boolean i0() {
            return (this.h & 131072) != 0;
        }

        @Override // com.google.protobuf.a0.d, com.google.protobuf.a0, defpackage.bh1
        public final boolean isInitialized() {
            byte b2 = this.D;
            if (b2 == 1) {
                return true;
            }
            if (b2 == 0) {
                return false;
            }
            for (int i = 0; i < this.C.size(); i++) {
                if (!this.C.get(i).isInitialized()) {
                    this.D = (byte) 0;
                    return false;
                }
            }
            if (Q()) {
                this.D = (byte) 1;
                return true;
            }
            this.D = (byte) 0;
            return false;
        }

        public final boolean k0() {
            return (this.h & 524288) != 0;
        }

        public final boolean l0() {
            return (this.h & 32768) != 0;
        }

        @Override // com.google.protobuf.a0, com.google.protobuf.r0
        public final int m() {
            int i = this.e;
            if (i != -1) {
                return i;
            }
            int iH = (this.h & 1) != 0 ? a0.H(1, this.i) + 0 : 0;
            if ((this.h & 2) != 0) {
                iH += a0.H(8, this.j);
            }
            if ((this.h & 32) != 0) {
                iH += nu.N(9, this.n);
            }
            if ((this.h & 4) != 0) {
                iH += nu.J(10);
            }
            if ((this.h & 64) != 0) {
                iH += a0.H(11, this.o);
            }
            if ((this.h & 128) != 0) {
                iH += nu.J(16);
            }
            if ((this.h & 256) != 0) {
                iH += nu.J(17);
            }
            if ((this.h & 512) != 0) {
                iH += nu.J(18);
            }
            if ((this.h & 8) != 0) {
                iH += nu.J(20);
            }
            if ((this.h & 2048) != 0) {
                iH += nu.J(23);
            }
            if ((this.h & 16) != 0) {
                iH += nu.J(27);
            }
            if ((this.h & 4096) != 0) {
                iH += nu.J(31);
            }
            if ((this.h & 8192) != 0) {
                iH += a0.H(36, this.v);
            }
            if ((this.h & 16384) != 0) {
                iH += a0.H(37, this.w);
            }
            if ((this.h & 32768) != 0) {
                iH += a0.H(39, this.x);
            }
            if ((this.h & 65536) != 0) {
                iH += a0.H(40, this.y);
            }
            if ((this.h & 131072) != 0) {
                iH += a0.H(41, this.z);
            }
            if ((this.h & 1024) != 0) {
                iH += nu.J(42);
            }
            if ((this.h & 262144) != 0) {
                iH += a0.H(44, this.A);
            }
            if ((this.h & 524288) != 0) {
                iH += a0.H(45, this.B);
            }
            for (int i2 = 0; i2 < this.C.size(); i2++) {
                iH += nu.a0(999, this.C.get(i2));
            }
            int iM = this.f.m() + S() + iH;
            this.e = iM;
            return iM;
        }

        @Override // com.google.protobuf.r0
        /* JADX INFO: renamed from: m0, reason: merged with bridge method [inline-methods] */
        public final b h() {
            if (this == E) {
                return new b();
            }
            b bVar = new b();
            bVar.Q(this);
            return bVar;
        }

        @Override // com.google.protobuf.a0, com.google.protobuf.t0
        public final j1 n() {
            return this.f;
        }

        @Override // com.google.protobuf.a0, com.google.protobuf.r0
        public final void p(nu nuVar) {
            a0.d.a aVar = new a0.d.a(this);
            if ((this.h & 1) != 0) {
                a0.P(nuVar, 1, this.i);
            }
            if ((this.h & 2) != 0) {
                a0.P(nuVar, 8, this.j);
            }
            if ((this.h & 32) != 0) {
                nuVar.z0(9, this.n);
            }
            if ((this.h & 4) != 0) {
                nuVar.p0(10, this.k);
            }
            if ((this.h & 64) != 0) {
                a0.P(nuVar, 11, this.o);
            }
            if ((this.h & 128) != 0) {
                nuVar.p0(16, this.p);
            }
            if ((this.h & 256) != 0) {
                nuVar.p0(17, this.q);
            }
            if ((this.h & 512) != 0) {
                nuVar.p0(18, this.r);
            }
            if ((this.h & 8) != 0) {
                nuVar.p0(20, this.l);
            }
            if ((this.h & 2048) != 0) {
                nuVar.p0(23, this.t);
            }
            if ((this.h & 16) != 0) {
                nuVar.p0(27, this.m);
            }
            if ((this.h & 4096) != 0) {
                nuVar.p0(31, this.u);
            }
            if ((this.h & 8192) != 0) {
                a0.P(nuVar, 36, this.v);
            }
            if ((this.h & 16384) != 0) {
                a0.P(nuVar, 37, this.w);
            }
            if ((this.h & 32768) != 0) {
                a0.P(nuVar, 39, this.x);
            }
            if ((this.h & 65536) != 0) {
                a0.P(nuVar, 40, this.y);
            }
            if ((this.h & 131072) != 0) {
                a0.P(nuVar, 41, this.z);
            }
            if ((this.h & 1024) != 0) {
                nuVar.p0(42, this.s);
            }
            if ((this.h & 262144) != 0) {
                a0.P(nuVar, 44, this.A);
            }
            if ((this.h & 524288) != 0) {
                a0.P(nuVar, 45, this.B);
            }
            for (int i = 0; i < this.C.size(); i++) {
                nuVar.D0(999, this.C.get(i));
            }
            aVar.a(nuVar);
            this.f.p(nuVar);
        }

        @Override // com.google.protobuf.a0, com.google.protobuf.r0
        public final wv1<j> u() {
            return F;
        }

        @Override // defpackage.bh1, com.google.protobuf.t0
        public final r0 b() {
            return E;
        }

        @Override // com.google.protobuf.r0, com.google.protobuf.q0
        public final r0.a e() {
            return E.h();
        }

        public j() {
            this.D = (byte) -1;
            this.i = "";
            this.j = "";
            this.n = 1;
            this.o = "";
            this.u = true;
            this.v = "";
            this.w = "";
            this.x = "";
            this.y = "";
            this.z = "";
            this.A = "";
            this.B = "";
            this.C = Collections.emptyList();
        }
    }

    /* JADX INFO: compiled from: DescriptorProtos.java */
    public static final class k extends a0.d<k> {
        public static final k o = new k();

        @Deprecated
        public static final a p = new a();
        private static final long serialVersionUID = 0;
        public int h;
        public boolean i;
        public boolean j;
        public boolean k;
        public boolean l;
        public List<s> m;
        public byte n;

        /* JADX INFO: compiled from: DescriptorProtos.java */
        public static class a extends com.google.protobuf.c<k> {
            @Override // defpackage.wv1
            public final Object b(com.google.protobuf.g gVar, com.google.protobuf.s sVar) {
                k kVar = new k();
                sVar.getClass();
                j1 j1Var = j1.f;
                j1.a aVar = new j1.a();
                boolean z = false;
                int i = 0;
                while (!z) {
                    try {
                        try {
                            int iF = gVar.F();
                            if (iF != 0) {
                                if (iF == 8) {
                                    kVar.h |= 1;
                                    kVar.i = gVar.l();
                                } else if (iF == 16) {
                                    kVar.h |= 2;
                                    kVar.j = gVar.l();
                                } else if (iF == 24) {
                                    kVar.h |= 4;
                                    kVar.k = gVar.l();
                                } else if (iF == 56) {
                                    kVar.h |= 8;
                                    kVar.l = gVar.l();
                                } else if (iF == 7994) {
                                    if ((i & 16) == 0) {
                                        kVar.m = new ArrayList();
                                        i |= 16;
                                    }
                                    kVar.m.add((s) gVar.v(s.q, sVar));
                                } else if (!kVar.V(gVar, aVar, sVar, iF)) {
                                }
                            }
                            z = true;
                        } catch (d0 e) {
                            e.e = kVar;
                            throw e;
                        } catch (IOException e2) {
                            d0 d0Var = new d0(e2);
                            d0Var.e = kVar;
                            throw d0Var;
                        }
                    } catch (Throwable th) {
                        if ((i & 16) != 0) {
                            kVar.m = Collections.unmodifiableList(kVar.m);
                        }
                        kVar.f = aVar.build();
                        kVar.U();
                        throw th;
                    }
                }
                if ((i & 16) != 0) {
                    kVar.m = Collections.unmodifiableList(kVar.m);
                }
                kVar.f = aVar.build();
                kVar.U();
                return kVar;
            }
        }

        /* JADX INFO: compiled from: DescriptorProtos.java */
        public static final class b extends a0.c<k, b> {
            public int h;
            public boolean i;
            public boolean j;
            public boolean k;
            public boolean l;
            public List<s> m = Collections.emptyList();

            @Override // com.google.protobuf.a0.a
            /* JADX INFO: renamed from: A */
            public final a0.a f(n.f fVar, Object obj) {
                super.K(fVar, obj);
                return this;
            }

            @Override // com.google.protobuf.a0.a
            /* JADX INFO: renamed from: B */
            public final a0.a clone() {
                return (b) super.clone();
            }

            @Override // com.google.protobuf.a0.a
            public final a0.e D() {
                a0.e eVar = m.B;
                eVar.c(k.class, b.class);
                return eVar;
            }

            @Override // com.google.protobuf.a0.a
            /* JADX INFO: renamed from: E */
            public final a0.a y(j1 j1Var) {
                return (b) super.y(j1Var);
            }

            @Override // com.google.protobuf.a0.a
            /* JADX INFO: renamed from: I */
            public final a0.a a(n.f fVar, Object obj) {
                return (b) super.N(fVar, obj);
            }

            @Override // com.google.protobuf.a0.a
            /* JADX INFO: renamed from: J */
            public final a0.a j0(j1 j1Var) {
                this.f = j1Var;
                H();
                return this;
            }

            @Override // com.google.protobuf.a.AbstractC0042a, com.google.protobuf.r0.a
            public final /* bridge */ /* synthetic */ r0.a L(com.google.protobuf.g gVar, com.google.protobuf.s sVar) throws Throwable {
                S(gVar, sVar);
                return this;
            }

            @Override // com.google.protobuf.r0.a, com.google.protobuf.q0.a
            /* JADX INFO: renamed from: P, reason: merged with bridge method [inline-methods] */
            public final k d() {
                int i;
                k kVar = new k(this);
                int i2 = this.h;
                if ((i2 & 1) != 0) {
                    kVar.i = this.i;
                    i = 1;
                } else {
                    i = 0;
                }
                if ((i2 & 2) != 0) {
                    kVar.j = this.j;
                    i |= 2;
                }
                if ((i2 & 4) != 0) {
                    kVar.k = this.k;
                    i |= 4;
                }
                if ((i2 & 8) != 0) {
                    kVar.l = this.l;
                    i |= 8;
                }
                if ((i2 & 16) != 0) {
                    this.m = Collections.unmodifiableList(this.m);
                    this.h &= -17;
                }
                kVar.m = this.m;
                kVar.h = i;
                F();
                return kVar;
            }

            public final void Q(k kVar) {
                if (kVar == k.o) {
                    return;
                }
                if ((kVar.h & 1) != 0) {
                    boolean z = kVar.i;
                    this.h |= 1;
                    this.i = z;
                    H();
                }
                if ((kVar.h & 2) != 0) {
                    boolean z2 = kVar.j;
                    this.h |= 2;
                    this.j = z2;
                    H();
                }
                if ((kVar.h & 4) != 0) {
                    boolean z3 = kVar.k;
                    this.h |= 4;
                    this.k = z3;
                    H();
                }
                if ((kVar.h & 8) != 0) {
                    boolean z4 = kVar.l;
                    this.h |= 8;
                    this.l = z4;
                    H();
                }
                if (!kVar.m.isEmpty()) {
                    if (this.m.isEmpty()) {
                        this.m = kVar.m;
                        this.h &= -17;
                    } else {
                        if ((this.h & 16) == 0) {
                            this.m = new ArrayList(this.m);
                            this.h |= 16;
                        }
                        this.m.addAll(kVar.m);
                    }
                    H();
                }
                M(kVar);
                H();
            }

            @Override // com.google.protobuf.a.AbstractC0042a, com.google.protobuf.q0.a
            public final q0.a R(q0 q0Var) {
                if (q0Var instanceof k) {
                    Q((k) q0Var);
                } else {
                    super.R(q0Var);
                }
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:15:0x001d  */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public final void S(com.google.protobuf.g r2, com.google.protobuf.s r3) throws java.lang.Throwable {
                /*
                    r1 = this;
                    com.google.protobuf.m$k$a r0 = com.google.protobuf.m.k.p     // Catch: java.lang.Throwable -> Lc com.google.protobuf.d0 -> Le
                    java.lang.Object r2 = r0.b(r2, r3)     // Catch: java.lang.Throwable -> Lc com.google.protobuf.d0 -> Le
                    com.google.protobuf.m$k r2 = (com.google.protobuf.m.k) r2     // Catch: java.lang.Throwable -> Lc com.google.protobuf.d0 -> Le
                    r1.Q(r2)
                    return
                Lc:
                    r2 = move-exception
                    goto L1a
                Le:
                    r2 = move-exception
                    com.google.protobuf.r0 r3 = r2.e     // Catch: java.lang.Throwable -> Lc
                    com.google.protobuf.m$k r3 = (com.google.protobuf.m.k) r3     // Catch: java.lang.Throwable -> Lc
                    java.io.IOException r2 = r2.l()     // Catch: java.lang.Throwable -> L18
                    throw r2     // Catch: java.lang.Throwable -> L18
                L18:
                    r2 = move-exception
                    goto L1b
                L1a:
                    r3 = 0
                L1b:
                    if (r3 == 0) goto L20
                    r1.Q(r3)
                L20:
                    throw r2
                */
                throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.m.k.b.S(com.google.protobuf.g, com.google.protobuf.s):void");
            }

            @Override // com.google.protobuf.a0.a, com.google.protobuf.q0.a
            public final q0.a a(n.f fVar, Object obj) {
                super.N(fVar, obj);
                return this;
            }

            @Override // defpackage.bh1, com.google.protobuf.t0
            public final q0 b() {
                return k.o;
            }

            @Override // com.google.protobuf.r0.a, com.google.protobuf.q0.a
            public final q0 build() {
                k kVarD = d();
                if (kVarD.isInitialized()) {
                    return kVarD;
                }
                throw a.AbstractC0042a.z(kVarD);
            }

            @Override // com.google.protobuf.a0.a, com.google.protobuf.q0.a, com.google.protobuf.t0
            public final n.a c() {
                return m.A;
            }

            @Override // com.google.protobuf.a0.a
            public final Object clone() {
                return (b) super.clone();
            }

            @Override // com.google.protobuf.a0.a, com.google.protobuf.q0.a
            public final q0.a f(n.f fVar, Object obj) {
                super.K(fVar, obj);
                return this;
            }

            @Override // com.google.protobuf.a0.a, com.google.protobuf.q0.a
            public final q0.a j0(j1 j1Var) {
                this.f = j1Var;
                H();
                return this;
            }

            @Override // com.google.protobuf.a.AbstractC0042a
            /* JADX INFO: renamed from: v */
            public final /* bridge */ /* synthetic */ a.AbstractC0042a L(com.google.protobuf.g gVar, com.google.protobuf.s sVar) throws Throwable {
                S(gVar, sVar);
                return this;
            }

            @Override // com.google.protobuf.a.AbstractC0042a
            /* JADX INFO: renamed from: x */
            public final a.AbstractC0042a R(q0 q0Var) {
                if (q0Var instanceof k) {
                    Q((k) q0Var);
                } else {
                    super.R(q0Var);
                }
                return this;
            }

            @Override // com.google.protobuf.a0.a, com.google.protobuf.a.AbstractC0042a
            public final void y(j1 j1Var) {
            }

            @Override // defpackage.bh1, com.google.protobuf.t0
            public final r0 b() {
                return k.o;
            }

            @Override // com.google.protobuf.r0.a, com.google.protobuf.q0.a
            public final r0 build() {
                k kVarD = d();
                if (kVarD.isInitialized()) {
                    return kVarD;
                }
                throw a.AbstractC0042a.z(kVarD);
            }
        }

        public k(a0.c cVar) {
            super(cVar);
            this.n = (byte) -1;
        }

        @Override // com.google.protobuf.a0
        public final a0.e M() {
            a0.e eVar = m.B;
            eVar.c(k.class, b.class);
            return eVar;
        }

        @Override // com.google.protobuf.a0
        public final Object O() {
            return new k();
        }

        @Override // com.google.protobuf.r0
        /* JADX INFO: renamed from: W, reason: merged with bridge method [inline-methods] */
        public final b h() {
            if (this == o) {
                return new b();
            }
            b bVar = new b();
            bVar.Q(this);
            return bVar;
        }

        @Override // defpackage.bh1, com.google.protobuf.t0
        public final q0 b() {
            return o;
        }

        @Override // com.google.protobuf.r0, com.google.protobuf.q0
        public final q0.a e() {
            return o.h();
        }

        @Override // com.google.protobuf.a
        public final boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof k)) {
                return super.equals(obj);
            }
            k kVar = (k) obj;
            int i = this.h;
            boolean z = (i & 1) != 0;
            int i2 = kVar.h;
            if (z != ((i2 & 1) != 0)) {
                return false;
            }
            if (((i & 1) != 0) && this.i != kVar.i) {
                return false;
            }
            if (((i & 2) != 0) != ((i2 & 2) != 0)) {
                return false;
            }
            if (((i & 2) != 0) && this.j != kVar.j) {
                return false;
            }
            if (((i & 4) != 0) != ((i2 & 4) != 0)) {
                return false;
            }
            if (((i & 4) != 0) && this.k != kVar.k) {
                return false;
            }
            if (((i & 8) != 0) != ((i2 & 8) != 0)) {
                return false;
            }
            return (!((i & 8) != 0) || this.l == kVar.l) && this.m.equals(kVar.m) && this.f.equals(kVar.f) && T().equals(kVar.T());
        }

        @Override // com.google.protobuf.a
        public final int hashCode() {
            int i = this.memoizedHashCode;
            if (i != 0) {
                return i;
            }
            int iHashCode = m.A.hashCode() + 779;
            if ((this.h & 1) != 0) {
                iHashCode = C0175b.e(iHashCode, 37, 1, 53) + c0.a(this.i);
            }
            if ((this.h & 2) != 0) {
                iHashCode = C0175b.e(iHashCode, 37, 2, 53) + c0.a(this.j);
            }
            if ((this.h & 4) != 0) {
                iHashCode = C0175b.e(iHashCode, 37, 3, 53) + c0.a(this.k);
            }
            if ((this.h & 8) != 0) {
                iHashCode = C0175b.e(iHashCode, 37, 7, 53) + c0.a(this.l);
            }
            if (this.m.size() > 0) {
                iHashCode = C0175b.e(iHashCode, 37, 999, 53) + this.m.hashCode();
            }
            int iHashCode2 = this.f.hashCode() + (com.google.protobuf.a.D(iHashCode, T()) * 29);
            this.memoizedHashCode = iHashCode2;
            return iHashCode2;
        }

        @Override // com.google.protobuf.a0.d, com.google.protobuf.a0, defpackage.bh1
        public final boolean isInitialized() {
            byte b2 = this.n;
            if (b2 == 1) {
                return true;
            }
            if (b2 == 0) {
                return false;
            }
            for (int i = 0; i < this.m.size(); i++) {
                if (!this.m.get(i).isInitialized()) {
                    this.n = (byte) 0;
                    return false;
                }
            }
            if (Q()) {
                this.n = (byte) 1;
                return true;
            }
            this.n = (byte) 0;
            return false;
        }

        @Override // com.google.protobuf.a0, com.google.protobuf.r0
        public final int m() {
            int i = this.e;
            if (i != -1) {
                return i;
            }
            int iJ = (this.h & 1) != 0 ? nu.J(1) + 0 : 0;
            if ((this.h & 2) != 0) {
                iJ += nu.J(2);
            }
            if ((this.h & 4) != 0) {
                iJ += nu.J(3);
            }
            if ((this.h & 8) != 0) {
                iJ += nu.J(7);
            }
            for (int i2 = 0; i2 < this.m.size(); i2++) {
                iJ += nu.a0(999, this.m.get(i2));
            }
            int iM = this.f.m() + S() + iJ;
            this.e = iM;
            return iM;
        }

        @Override // com.google.protobuf.a0, com.google.protobuf.t0
        public final j1 n() {
            return this.f;
        }

        @Override // com.google.protobuf.a0, com.google.protobuf.r0
        public final void p(nu nuVar) {
            a0.d.a aVar = new a0.d.a(this);
            if ((this.h & 1) != 0) {
                nuVar.p0(1, this.i);
            }
            if ((this.h & 2) != 0) {
                nuVar.p0(2, this.j);
            }
            if ((this.h & 4) != 0) {
                nuVar.p0(3, this.k);
            }
            if ((this.h & 8) != 0) {
                nuVar.p0(7, this.l);
            }
            for (int i = 0; i < this.m.size(); i++) {
                nuVar.D0(999, this.m.get(i));
            }
            aVar.a(nuVar);
            this.f.p(nuVar);
        }

        @Override // com.google.protobuf.a0, com.google.protobuf.r0
        public final wv1<k> u() {
            return p;
        }

        @Override // defpackage.bh1, com.google.protobuf.t0
        public final r0 b() {
            return o;
        }

        @Override // com.google.protobuf.r0, com.google.protobuf.q0
        public final r0.a e() {
            return o.h();
        }

        public k() {
            this.n = (byte) -1;
            this.m = Collections.emptyList();
        }
    }

    /* JADX INFO: compiled from: DescriptorProtos.java */
    public static final class l extends a0 implements t0 {
        public static final l o = new l();

        @Deprecated
        public static final a p = new a();
        private static final long serialVersionUID = 0;
        public int g;
        public volatile Object h;
        public volatile Object i;
        public volatile Object j;
        public C0052m k;
        public boolean l;
        public boolean m;
        public byte n;

        /* JADX INFO: compiled from: DescriptorProtos.java */
        public static class a extends com.google.protobuf.c<l> {
            @Override // defpackage.wv1
            public final Object b(com.google.protobuf.g gVar, com.google.protobuf.s sVar) {
                l lVar = new l();
                sVar.getClass();
                j1 j1Var = j1.f;
                j1.a aVar = new j1.a();
                boolean z = false;
                while (!z) {
                    try {
                        try {
                            try {
                                int iF = gVar.F();
                                if (iF != 0) {
                                    if (iF == 10) {
                                        lk.f fVarM = gVar.m();
                                        lVar.g = 1 | lVar.g;
                                        lVar.h = fVarM;
                                    } else if (iF == 18) {
                                        lk.f fVarM2 = gVar.m();
                                        lVar.g |= 2;
                                        lVar.i = fVarM2;
                                    } else if (iF == 26) {
                                        lk.f fVarM3 = gVar.m();
                                        lVar.g |= 4;
                                        lVar.j = fVarM3;
                                    } else if (iF == 34) {
                                        C0052m.b bVarH = (lVar.g & 8) != 0 ? lVar.k.h() : null;
                                        C0052m c0052m = (C0052m) gVar.v(C0052m.n, sVar);
                                        lVar.k = c0052m;
                                        if (bVarH != null) {
                                            bVarH.Q(c0052m);
                                            lVar.k = bVarH.d();
                                        }
                                        lVar.g |= 8;
                                    } else if (iF == 40) {
                                        lVar.g |= 16;
                                        lVar.l = gVar.l();
                                    } else if (iF == 48) {
                                        lVar.g |= 32;
                                        lVar.m = gVar.l();
                                    } else if (!aVar.r(iF, gVar)) {
                                    }
                                }
                                z = true;
                            } catch (d0 e) {
                                e.e = lVar;
                                throw e;
                            }
                        } catch (IOException e2) {
                            d0 d0Var = new d0(e2);
                            d0Var.e = lVar;
                            throw d0Var;
                        }
                    } catch (Throwable th) {
                        lVar.f = aVar.build();
                        throw th;
                    }
                }
                lVar.f = aVar.build();
                return lVar;
            }
        }

        /* JADX INFO: compiled from: DescriptorProtos.java */
        public static final class b extends a0.a<b> implements t0 {
            public int g;
            public Object h = "";
            public Object i = "";
            public Object j = "";
            public C0052m k;
            public boolean l;
            public boolean m;

            @Override // com.google.protobuf.a0.a
            /* JADX INFO: renamed from: A */
            public final a0.a f(n.f fVar, Object obj) {
                super.f(fVar, obj);
                return this;
            }

            @Override // com.google.protobuf.a0.a
            /* JADX INFO: renamed from: B */
            public final a0.a clone() {
                return (b) super.clone();
            }

            @Override // com.google.protobuf.a0.a
            public final a0.e D() {
                a0.e eVar = m.x;
                eVar.c(l.class, b.class);
                return eVar;
            }

            @Override // com.google.protobuf.a0.a
            /* JADX INFO: renamed from: E */
            public final a0.a y(j1 j1Var) {
                return (b) super.y(j1Var);
            }

            @Override // com.google.protobuf.a0.a
            /* JADX INFO: renamed from: I */
            public final a0.a a(n.f fVar, Object obj) {
                super.a(fVar, obj);
                return this;
            }

            @Override // com.google.protobuf.a0.a
            /* JADX INFO: renamed from: J */
            public final a0.a j0(j1 j1Var) {
                this.f = j1Var;
                H();
                return this;
            }

            @Override // com.google.protobuf.r0.a, com.google.protobuf.q0.a
            /* JADX INFO: renamed from: K, reason: merged with bridge method [inline-methods] */
            public final l d() {
                l lVar = new l(this);
                int i = this.g;
                int i2 = (i & 1) != 0 ? 1 : 0;
                lVar.h = this.h;
                if ((i & 2) != 0) {
                    i2 |= 2;
                }
                lVar.i = this.i;
                if ((i & 4) != 0) {
                    i2 |= 4;
                }
                lVar.j = this.j;
                if ((i & 8) != 0) {
                    lVar.k = this.k;
                    i2 |= 8;
                }
                if ((i & 16) != 0) {
                    lVar.l = this.l;
                    i2 |= 16;
                }
                if ((i & 32) != 0) {
                    lVar.m = this.m;
                    i2 |= 32;
                }
                lVar.g = i2;
                F();
                return lVar;
            }

            @Override // com.google.protobuf.a.AbstractC0042a, com.google.protobuf.r0.a
            public final /* bridge */ /* synthetic */ r0.a L(com.google.protobuf.g gVar, com.google.protobuf.s sVar) throws Throwable {
                N(gVar, sVar);
                return this;
            }

            public final void M(l lVar) {
                C0052m c0052m;
                C0052m c0052m2;
                if (lVar == l.o) {
                    return;
                }
                if ((lVar.g & 1) != 0) {
                    this.g |= 1;
                    this.h = lVar.h;
                    H();
                }
                if ((lVar.g & 2) != 0) {
                    this.g |= 2;
                    this.i = lVar.i;
                    H();
                }
                if ((lVar.g & 4) != 0) {
                    this.g |= 4;
                    this.j = lVar.j;
                    H();
                }
                if (lVar.V()) {
                    C0052m c0052mT = lVar.T();
                    if ((this.g & 8) == 0 || (c0052m = this.k) == null || c0052m == (c0052m2 = C0052m.m)) {
                        this.k = c0052mT;
                    } else {
                        C0052m.b bVarH = c0052m2.h();
                        bVarH.Q(c0052m);
                        bVarH.Q(c0052mT);
                        this.k = bVarH.d();
                    }
                    H();
                    this.g |= 8;
                }
                if ((lVar.g & 16) != 0) {
                    boolean z = lVar.l;
                    this.g |= 16;
                    this.l = z;
                    H();
                }
                if ((lVar.g & 32) != 0) {
                    boolean z2 = lVar.m;
                    this.g |= 32;
                    this.m = z2;
                    H();
                }
                H();
            }

            /* JADX WARN: Removed duplicated region for block: B:15:0x001d  */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public final void N(com.google.protobuf.g r2, com.google.protobuf.s r3) throws java.lang.Throwable {
                /*
                    r1 = this;
                    com.google.protobuf.m$l$a r0 = com.google.protobuf.m.l.p     // Catch: java.lang.Throwable -> Lc com.google.protobuf.d0 -> Le
                    java.lang.Object r2 = r0.b(r2, r3)     // Catch: java.lang.Throwable -> Lc com.google.protobuf.d0 -> Le
                    com.google.protobuf.m$l r2 = (com.google.protobuf.m.l) r2     // Catch: java.lang.Throwable -> Lc com.google.protobuf.d0 -> Le
                    r1.M(r2)
                    return
                Lc:
                    r2 = move-exception
                    goto L1a
                Le:
                    r2 = move-exception
                    com.google.protobuf.r0 r3 = r2.e     // Catch: java.lang.Throwable -> Lc
                    com.google.protobuf.m$l r3 = (com.google.protobuf.m.l) r3     // Catch: java.lang.Throwable -> Lc
                    java.io.IOException r2 = r2.l()     // Catch: java.lang.Throwable -> L18
                    throw r2     // Catch: java.lang.Throwable -> L18
                L18:
                    r2 = move-exception
                    goto L1b
                L1a:
                    r3 = 0
                L1b:
                    if (r3 == 0) goto L20
                    r1.M(r3)
                L20:
                    throw r2
                */
                throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.m.l.b.N(com.google.protobuf.g, com.google.protobuf.s):void");
            }

            @Override // com.google.protobuf.a.AbstractC0042a, com.google.protobuf.q0.a
            public final q0.a R(q0 q0Var) {
                if (q0Var instanceof l) {
                    M((l) q0Var);
                } else {
                    super.R(q0Var);
                }
                return this;
            }

            @Override // com.google.protobuf.a0.a, com.google.protobuf.q0.a
            public final q0.a a(n.f fVar, Object obj) {
                super.a(fVar, obj);
                return this;
            }

            @Override // defpackage.bh1, com.google.protobuf.t0
            public final q0 b() {
                return l.o;
            }

            @Override // com.google.protobuf.r0.a, com.google.protobuf.q0.a
            public final q0 build() {
                l lVarD = d();
                if (lVarD.isInitialized()) {
                    return lVarD;
                }
                throw a.AbstractC0042a.z(lVarD);
            }

            @Override // com.google.protobuf.a0.a, com.google.protobuf.q0.a, com.google.protobuf.t0
            public final n.a c() {
                return m.w;
            }

            @Override // com.google.protobuf.a0.a
            public final Object clone() {
                return (b) super.clone();
            }

            @Override // com.google.protobuf.a0.a, com.google.protobuf.q0.a
            public final q0.a f(n.f fVar, Object obj) {
                super.f(fVar, obj);
                return this;
            }

            @Override // com.google.protobuf.a0.a, com.google.protobuf.q0.a
            public final q0.a j0(j1 j1Var) {
                this.f = j1Var;
                H();
                return this;
            }

            @Override // com.google.protobuf.a.AbstractC0042a
            /* JADX INFO: renamed from: v */
            public final /* bridge */ /* synthetic */ a.AbstractC0042a L(com.google.protobuf.g gVar, com.google.protobuf.s sVar) throws Throwable {
                N(gVar, sVar);
                return this;
            }

            @Override // com.google.protobuf.a.AbstractC0042a
            /* JADX INFO: renamed from: x */
            public final a.AbstractC0042a R(q0 q0Var) {
                if (q0Var instanceof l) {
                    M((l) q0Var);
                } else {
                    super.R(q0Var);
                }
                return this;
            }

            @Override // com.google.protobuf.a0.a, com.google.protobuf.a.AbstractC0042a
            public final void y(j1 j1Var) {
            }

            @Override // defpackage.bh1, com.google.protobuf.t0
            public final r0 b() {
                return l.o;
            }

            @Override // com.google.protobuf.r0.a, com.google.protobuf.q0.a
            public final r0 build() {
                l lVarD = d();
                if (lVarD.isInitialized()) {
                    return lVarD;
                }
                throw a.AbstractC0042a.z(lVarD);
            }
        }

        public l(a0.a aVar) {
            super(aVar);
            this.n = (byte) -1;
        }

        @Override // com.google.protobuf.a0
        public final a0.e M() {
            a0.e eVar = m.x;
            eVar.c(l.class, b.class);
            return eVar;
        }

        @Override // com.google.protobuf.a0
        public final Object O() {
            return new l();
        }

        public final String Q() {
            Object obj = this.i;
            if (obj instanceof String) {
                return (String) obj;
            }
            lk lkVar = (lk) obj;
            String strZ = lkVar.z();
            if (lkVar.q()) {
                this.i = strZ;
            }
            return strZ;
        }

        public final String S() {
            Object obj = this.h;
            if (obj instanceof String) {
                return (String) obj;
            }
            lk lkVar = (lk) obj;
            String strZ = lkVar.z();
            if (lkVar.q()) {
                this.h = strZ;
            }
            return strZ;
        }

        public final C0052m T() {
            C0052m c0052m = this.k;
            return c0052m == null ? C0052m.m : c0052m;
        }

        public final String U() {
            Object obj = this.j;
            if (obj instanceof String) {
                return (String) obj;
            }
            lk lkVar = (lk) obj;
            String strZ = lkVar.z();
            if (lkVar.q()) {
                this.j = strZ;
            }
            return strZ;
        }

        public final boolean V() {
            return (this.g & 8) != 0;
        }

        @Override // com.google.protobuf.r0
        /* JADX INFO: renamed from: W, reason: merged with bridge method [inline-methods] */
        public final b h() {
            if (this == o) {
                return new b();
            }
            b bVar = new b();
            bVar.M(this);
            return bVar;
        }

        @Override // defpackage.bh1, com.google.protobuf.t0
        public final q0 b() {
            return o;
        }

        @Override // com.google.protobuf.r0, com.google.protobuf.q0
        public final q0.a e() {
            return o.h();
        }

        @Override // com.google.protobuf.a
        public final boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof l)) {
                return super.equals(obj);
            }
            l lVar = (l) obj;
            int i = this.g;
            if (((i & 1) != 0) != ((lVar.g & 1) != 0)) {
                return false;
            }
            if (((i & 1) != 0) && !S().equals(lVar.S())) {
                return false;
            }
            int i2 = this.g;
            if (((i2 & 2) != 0) != ((lVar.g & 2) != 0)) {
                return false;
            }
            if (((i2 & 2) != 0) && !Q().equals(lVar.Q())) {
                return false;
            }
            int i3 = this.g;
            if (((i3 & 4) != 0) != ((lVar.g & 4) != 0)) {
                return false;
            }
            if ((((i3 & 4) != 0) && !U().equals(lVar.U())) || V() != lVar.V()) {
                return false;
            }
            if (V() && !T().equals(lVar.T())) {
                return false;
            }
            int i4 = this.g;
            boolean z = (i4 & 16) != 0;
            int i5 = lVar.g;
            if (z != ((i5 & 16) != 0)) {
                return false;
            }
            if (((i4 & 16) != 0) && this.l != lVar.l) {
                return false;
            }
            if (((i4 & 32) != 0) != ((i5 & 32) != 0)) {
                return false;
            }
            return (!((i4 & 32) != 0) || this.m == lVar.m) && this.f.equals(lVar.f);
        }

        @Override // com.google.protobuf.a
        public final int hashCode() {
            int i = this.memoizedHashCode;
            if (i != 0) {
                return i;
            }
            int iHashCode = m.w.hashCode() + 779;
            if ((this.g & 1) != 0) {
                iHashCode = C0175b.e(iHashCode, 37, 1, 53) + S().hashCode();
            }
            if ((this.g & 2) != 0) {
                iHashCode = C0175b.e(iHashCode, 37, 2, 53) + Q().hashCode();
            }
            if ((this.g & 4) != 0) {
                iHashCode = C0175b.e(iHashCode, 37, 3, 53) + U().hashCode();
            }
            if (V()) {
                iHashCode = C0175b.e(iHashCode, 37, 4, 53) + T().hashCode();
            }
            if ((this.g & 16) != 0) {
                iHashCode = C0175b.e(iHashCode, 37, 5, 53) + c0.a(this.l);
            }
            if ((this.g & 32) != 0) {
                iHashCode = C0175b.e(iHashCode, 37, 6, 53) + c0.a(this.m);
            }
            int iHashCode2 = this.f.hashCode() + (iHashCode * 29);
            this.memoizedHashCode = iHashCode2;
            return iHashCode2;
        }

        @Override // com.google.protobuf.a0, defpackage.bh1
        public final boolean isInitialized() {
            byte b2 = this.n;
            if (b2 == 1) {
                return true;
            }
            if (b2 == 0) {
                return false;
            }
            if (!V() || T().isInitialized()) {
                this.n = (byte) 1;
                return true;
            }
            this.n = (byte) 0;
            return false;
        }

        @Override // com.google.protobuf.a0, com.google.protobuf.r0
        public final int m() {
            int i = this.e;
            if (i != -1) {
                return i;
            }
            int iH = (this.g & 1) != 0 ? 0 + a0.H(1, this.h) : 0;
            if ((this.g & 2) != 0) {
                iH += a0.H(2, this.i);
            }
            if ((this.g & 4) != 0) {
                iH += a0.H(3, this.j);
            }
            if ((this.g & 8) != 0) {
                iH += nu.a0(4, T());
            }
            if ((this.g & 16) != 0) {
                iH += nu.J(5);
            }
            if ((this.g & 32) != 0) {
                iH += nu.J(6);
            }
            int iM = this.f.m() + iH;
            this.e = iM;
            return iM;
        }

        @Override // com.google.protobuf.a0, com.google.protobuf.t0
        public final j1 n() {
            return this.f;
        }

        @Override // com.google.protobuf.a0, com.google.protobuf.r0
        public final void p(nu nuVar) {
            if ((this.g & 1) != 0) {
                a0.P(nuVar, 1, this.h);
            }
            if ((this.g & 2) != 0) {
                a0.P(nuVar, 2, this.i);
            }
            if ((this.g & 4) != 0) {
                a0.P(nuVar, 3, this.j);
            }
            if ((this.g & 8) != 0) {
                nuVar.D0(4, T());
            }
            if ((this.g & 16) != 0) {
                nuVar.p0(5, this.l);
            }
            if ((this.g & 32) != 0) {
                nuVar.p0(6, this.m);
            }
            this.f.p(nuVar);
        }

        @Override // com.google.protobuf.a0, com.google.protobuf.r0
        public final wv1<l> u() {
            return p;
        }

        @Override // defpackage.bh1, com.google.protobuf.t0
        public final r0 b() {
            return o;
        }

        @Override // com.google.protobuf.r0, com.google.protobuf.q0
        public final r0.a e() {
            return o.h();
        }

        public l() {
            this.n = (byte) -1;
            this.h = "";
            this.i = "";
            this.j = "";
        }
    }

    /* JADX INFO: renamed from: com.google.protobuf.m$m, reason: collision with other inner class name */
    /* JADX INFO: compiled from: DescriptorProtos.java */
    public static final class C0052m extends a0.d<C0052m> {
        public static final C0052m m = new C0052m();

        @Deprecated
        public static final a n = new a();
        private static final long serialVersionUID = 0;
        public int h;
        public boolean i;
        public int j;
        public List<s> k;
        public byte l;

        /* JADX INFO: renamed from: com.google.protobuf.m$m$a */
        /* JADX INFO: compiled from: DescriptorProtos.java */
        public static class a extends com.google.protobuf.c<C0052m> {
            @Override // defpackage.wv1
            public final Object b(com.google.protobuf.g gVar, com.google.protobuf.s sVar) {
                C0052m c0052m = new C0052m();
                sVar.getClass();
                j1 j1Var = j1.f;
                j1.a aVar = new j1.a();
                boolean z = false;
                int i = 0;
                while (!z) {
                    try {
                        try {
                            int iF = gVar.F();
                            if (iF != 0) {
                                if (iF == 264) {
                                    c0052m.h |= 1;
                                    c0052m.i = gVar.l();
                                } else if (iF == 272) {
                                    int iO = gVar.o();
                                    if (c.a(iO) == null) {
                                        aVar.y(34, iO);
                                    } else {
                                        c0052m.h |= 2;
                                        c0052m.j = iO;
                                    }
                                } else if (iF == 7994) {
                                    if ((i & 4) == 0) {
                                        c0052m.k = new ArrayList();
                                        i |= 4;
                                    }
                                    c0052m.k.add((s) gVar.v(s.q, sVar));
                                } else if (!c0052m.V(gVar, aVar, sVar, iF)) {
                                }
                            }
                            z = true;
                        } catch (d0 e) {
                            e.e = c0052m;
                            throw e;
                        } catch (IOException e2) {
                            d0 d0Var = new d0(e2);
                            d0Var.e = c0052m;
                            throw d0Var;
                        }
                    } catch (Throwable th) {
                        if ((i & 4) != 0) {
                            c0052m.k = Collections.unmodifiableList(c0052m.k);
                        }
                        c0052m.f = aVar.build();
                        c0052m.U();
                        throw th;
                    }
                }
                if ((i & 4) != 0) {
                    c0052m.k = Collections.unmodifiableList(c0052m.k);
                }
                c0052m.f = aVar.build();
                c0052m.U();
                return c0052m;
            }
        }

        /* JADX INFO: renamed from: com.google.protobuf.m$m$b */
        /* JADX INFO: compiled from: DescriptorProtos.java */
        public static final class b extends a0.c<C0052m, b> {
            public int h;
            public boolean i;
            public int j = 0;
            public List<s> k = Collections.emptyList();

            @Override // com.google.protobuf.a0.a
            /* JADX INFO: renamed from: A */
            public final a0.a f(n.f fVar, Object obj) {
                super.K(fVar, obj);
                return this;
            }

            @Override // com.google.protobuf.a0.a
            /* JADX INFO: renamed from: B */
            public final a0.a clone() {
                return (b) super.clone();
            }

            @Override // com.google.protobuf.a0.a
            public final a0.e D() {
                a0.e eVar = m.N;
                eVar.c(C0052m.class, b.class);
                return eVar;
            }

            @Override // com.google.protobuf.a0.a
            /* JADX INFO: renamed from: E */
            public final a0.a y(j1 j1Var) {
                return (b) super.y(j1Var);
            }

            @Override // com.google.protobuf.a0.a
            /* JADX INFO: renamed from: I */
            public final a0.a a(n.f fVar, Object obj) {
                return (b) super.N(fVar, obj);
            }

            @Override // com.google.protobuf.a0.a
            /* JADX INFO: renamed from: J */
            public final a0.a j0(j1 j1Var) {
                this.f = j1Var;
                H();
                return this;
            }

            @Override // com.google.protobuf.a.AbstractC0042a, com.google.protobuf.r0.a
            public final /* bridge */ /* synthetic */ r0.a L(com.google.protobuf.g gVar, com.google.protobuf.s sVar) throws Throwable {
                S(gVar, sVar);
                return this;
            }

            @Override // com.google.protobuf.r0.a, com.google.protobuf.q0.a
            /* JADX INFO: renamed from: P, reason: merged with bridge method [inline-methods] */
            public final C0052m d() {
                int i;
                C0052m c0052m = new C0052m(this);
                int i2 = this.h;
                if ((i2 & 1) != 0) {
                    c0052m.i = this.i;
                    i = 1;
                } else {
                    i = 0;
                }
                if ((i2 & 2) != 0) {
                    i |= 2;
                }
                c0052m.j = this.j;
                if ((i2 & 4) != 0) {
                    this.k = Collections.unmodifiableList(this.k);
                    this.h &= -5;
                }
                c0052m.k = this.k;
                c0052m.h = i;
                F();
                return c0052m;
            }

            public final void Q(C0052m c0052m) {
                if (c0052m == C0052m.m) {
                    return;
                }
                if ((c0052m.h & 1) != 0) {
                    boolean z = c0052m.i;
                    this.h |= 1;
                    this.i = z;
                    H();
                }
                if ((c0052m.h & 2) != 0) {
                    c cVarA = c.a(c0052m.j);
                    if (cVarA == null) {
                        cVarA = c.IDEMPOTENCY_UNKNOWN;
                    }
                    this.h |= 2;
                    this.j = cVarA.e;
                    H();
                }
                if (!c0052m.k.isEmpty()) {
                    if (this.k.isEmpty()) {
                        this.k = c0052m.k;
                        this.h &= -5;
                    } else {
                        if ((this.h & 4) == 0) {
                            this.k = new ArrayList(this.k);
                            this.h |= 4;
                        }
                        this.k.addAll(c0052m.k);
                    }
                    H();
                }
                M(c0052m);
                H();
            }

            @Override // com.google.protobuf.a.AbstractC0042a, com.google.protobuf.q0.a
            public final q0.a R(q0 q0Var) {
                if (q0Var instanceof C0052m) {
                    Q((C0052m) q0Var);
                } else {
                    super.R(q0Var);
                }
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:15:0x001d  */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public final void S(com.google.protobuf.g r2, com.google.protobuf.s r3) throws java.lang.Throwable {
                /*
                    r1 = this;
                    com.google.protobuf.m$m$a r0 = com.google.protobuf.m.C0052m.n     // Catch: java.lang.Throwable -> Lc com.google.protobuf.d0 -> Le
                    java.lang.Object r2 = r0.b(r2, r3)     // Catch: java.lang.Throwable -> Lc com.google.protobuf.d0 -> Le
                    com.google.protobuf.m$m r2 = (com.google.protobuf.m.C0052m) r2     // Catch: java.lang.Throwable -> Lc com.google.protobuf.d0 -> Le
                    r1.Q(r2)
                    return
                Lc:
                    r2 = move-exception
                    goto L1a
                Le:
                    r2 = move-exception
                    com.google.protobuf.r0 r3 = r2.e     // Catch: java.lang.Throwable -> Lc
                    com.google.protobuf.m$m r3 = (com.google.protobuf.m.C0052m) r3     // Catch: java.lang.Throwable -> Lc
                    java.io.IOException r2 = r2.l()     // Catch: java.lang.Throwable -> L18
                    throw r2     // Catch: java.lang.Throwable -> L18
                L18:
                    r2 = move-exception
                    goto L1b
                L1a:
                    r3 = 0
                L1b:
                    if (r3 == 0) goto L20
                    r1.Q(r3)
                L20:
                    throw r2
                */
                throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.m.C0052m.b.S(com.google.protobuf.g, com.google.protobuf.s):void");
            }

            @Override // com.google.protobuf.a0.a, com.google.protobuf.q0.a
            public final q0.a a(n.f fVar, Object obj) {
                super.N(fVar, obj);
                return this;
            }

            @Override // defpackage.bh1, com.google.protobuf.t0
            public final q0 b() {
                return C0052m.m;
            }

            @Override // com.google.protobuf.r0.a, com.google.protobuf.q0.a
            public final q0 build() {
                C0052m c0052mD = d();
                if (c0052mD.isInitialized()) {
                    return c0052mD;
                }
                throw a.AbstractC0042a.z(c0052mD);
            }

            @Override // com.google.protobuf.a0.a, com.google.protobuf.q0.a, com.google.protobuf.t0
            public final n.a c() {
                return m.M;
            }

            @Override // com.google.protobuf.a0.a
            public final Object clone() {
                return (b) super.clone();
            }

            @Override // com.google.protobuf.a0.a, com.google.protobuf.q0.a
            public final q0.a f(n.f fVar, Object obj) {
                super.K(fVar, obj);
                return this;
            }

            @Override // com.google.protobuf.a0.a, com.google.protobuf.q0.a
            public final q0.a j0(j1 j1Var) {
                this.f = j1Var;
                H();
                return this;
            }

            @Override // com.google.protobuf.a.AbstractC0042a
            /* JADX INFO: renamed from: v */
            public final /* bridge */ /* synthetic */ a.AbstractC0042a L(com.google.protobuf.g gVar, com.google.protobuf.s sVar) throws Throwable {
                S(gVar, sVar);
                return this;
            }

            @Override // com.google.protobuf.a.AbstractC0042a
            /* JADX INFO: renamed from: x */
            public final a.AbstractC0042a R(q0 q0Var) {
                if (q0Var instanceof C0052m) {
                    Q((C0052m) q0Var);
                } else {
                    super.R(q0Var);
                }
                return this;
            }

            @Override // com.google.protobuf.a0.a, com.google.protobuf.a.AbstractC0042a
            public final void y(j1 j1Var) {
            }

            @Override // defpackage.bh1, com.google.protobuf.t0
            public final r0 b() {
                return C0052m.m;
            }

            @Override // com.google.protobuf.r0.a, com.google.protobuf.q0.a
            public final r0 build() {
                C0052m c0052mD = d();
                if (c0052mD.isInitialized()) {
                    return c0052mD;
                }
                throw a.AbstractC0042a.z(c0052mD);
            }
        }

        /* JADX INFO: renamed from: com.google.protobuf.m$m$c */
        /* JADX INFO: compiled from: DescriptorProtos.java */
        public enum c implements c0.a {
            IDEMPOTENCY_UNKNOWN(0),
            NO_SIDE_EFFECTS(1),
            IDEMPOTENT(2);

            public final int e;

            static {
                values();
            }

            c(int i2) {
                this.e = i2;
            }

            public static c a(int i2) {
                if (i2 == 0) {
                    return IDEMPOTENCY_UNKNOWN;
                }
                if (i2 == 1) {
                    return NO_SIDE_EFFECTS;
                }
                if (i2 != 2) {
                    return null;
                }
                return IDEMPOTENT;
            }

            @Override // com.google.protobuf.c0.a
            public final int b() {
                return this.e;
            }
        }

        public C0052m(a0.c cVar) {
            super(cVar);
            this.l = (byte) -1;
        }

        @Override // com.google.protobuf.a0
        public final a0.e M() {
            a0.e eVar = m.N;
            eVar.c(C0052m.class, b.class);
            return eVar;
        }

        @Override // com.google.protobuf.a0
        public final Object O() {
            return new C0052m();
        }

        @Override // com.google.protobuf.r0
        /* JADX INFO: renamed from: W, reason: merged with bridge method [inline-methods] */
        public final b h() {
            if (this == m) {
                return new b();
            }
            b bVar = new b();
            bVar.Q(this);
            return bVar;
        }

        @Override // defpackage.bh1, com.google.protobuf.t0
        public final q0 b() {
            return m;
        }

        @Override // com.google.protobuf.r0, com.google.protobuf.q0
        public final q0.a e() {
            return m.h();
        }

        @Override // com.google.protobuf.a
        public final boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof C0052m)) {
                return super.equals(obj);
            }
            C0052m c0052m = (C0052m) obj;
            int i = this.h;
            boolean z = (i & 1) != 0;
            int i2 = c0052m.h;
            if (z != ((i2 & 1) != 0)) {
                return false;
            }
            if (((i & 1) != 0) && this.i != c0052m.i) {
                return false;
            }
            if (((i & 2) != 0) != ((i2 & 2) != 0)) {
                return false;
            }
            return (!((i & 2) != 0) || this.j == c0052m.j) && this.k.equals(c0052m.k) && this.f.equals(c0052m.f) && T().equals(c0052m.T());
        }

        @Override // com.google.protobuf.a
        public final int hashCode() {
            int i = this.memoizedHashCode;
            if (i != 0) {
                return i;
            }
            int iHashCode = m.M.hashCode() + 779;
            if ((this.h & 1) != 0) {
                iHashCode = C0175b.e(iHashCode, 37, 33, 53) + c0.a(this.i);
            }
            if ((this.h & 2) != 0) {
                iHashCode = C0175b.e(iHashCode, 37, 34, 53) + this.j;
            }
            if (this.k.size() > 0) {
                iHashCode = C0175b.e(iHashCode, 37, 999, 53) + this.k.hashCode();
            }
            int iHashCode2 = this.f.hashCode() + (com.google.protobuf.a.D(iHashCode, T()) * 29);
            this.memoizedHashCode = iHashCode2;
            return iHashCode2;
        }

        @Override // com.google.protobuf.a0.d, com.google.protobuf.a0, defpackage.bh1
        public final boolean isInitialized() {
            byte b2 = this.l;
            if (b2 == 1) {
                return true;
            }
            if (b2 == 0) {
                return false;
            }
            for (int i = 0; i < this.k.size(); i++) {
                if (!this.k.get(i).isInitialized()) {
                    this.l = (byte) 0;
                    return false;
                }
            }
            if (Q()) {
                this.l = (byte) 1;
                return true;
            }
            this.l = (byte) 0;
            return false;
        }

        @Override // com.google.protobuf.a0, com.google.protobuf.r0
        public final int m() {
            int i = this.e;
            if (i != -1) {
                return i;
            }
            int iJ = (this.h & 1) != 0 ? nu.J(33) + 0 : 0;
            if ((this.h & 2) != 0) {
                iJ += nu.N(34, this.j);
            }
            for (int i2 = 0; i2 < this.k.size(); i2++) {
                iJ += nu.a0(999, this.k.get(i2));
            }
            int iM = this.f.m() + S() + iJ;
            this.e = iM;
            return iM;
        }

        @Override // com.google.protobuf.a0, com.google.protobuf.t0
        public final j1 n() {
            return this.f;
        }

        @Override // com.google.protobuf.a0, com.google.protobuf.r0
        public final void p(nu nuVar) {
            a0.d.a aVar = new a0.d.a(this);
            if ((this.h & 1) != 0) {
                nuVar.p0(33, this.i);
            }
            if ((this.h & 2) != 0) {
                nuVar.z0(34, this.j);
            }
            for (int i = 0; i < this.k.size(); i++) {
                nuVar.D0(999, this.k.get(i));
            }
            aVar.a(nuVar);
            this.f.p(nuVar);
        }

        @Override // com.google.protobuf.a0, com.google.protobuf.r0
        public final wv1<C0052m> u() {
            return n;
        }

        @Override // defpackage.bh1, com.google.protobuf.t0
        public final r0 b() {
            return m;
        }

        @Override // com.google.protobuf.r0, com.google.protobuf.q0
        public final r0.a e() {
            return m.h();
        }

        public C0052m() {
            this.l = (byte) -1;
            this.j = 0;
            this.k = Collections.emptyList();
        }
    }

    /* JADX INFO: compiled from: DescriptorProtos.java */
    public static final class n extends a0 implements t0 {
        public static final n k = new n();

        @Deprecated
        public static final a l = new a();
        private static final long serialVersionUID = 0;
        public int g;
        public volatile Object h;
        public o i;
        public byte j;

        /* JADX INFO: compiled from: DescriptorProtos.java */
        public static class a extends com.google.protobuf.c<n> {
            @Override // defpackage.wv1
            public final Object b(com.google.protobuf.g gVar, com.google.protobuf.s sVar) {
                n nVar = new n();
                sVar.getClass();
                j1 j1Var = j1.f;
                j1.a aVar = new j1.a();
                boolean z = false;
                while (!z) {
                    try {
                        try {
                            int iF = gVar.F();
                            if (iF != 0) {
                                if (iF == 10) {
                                    lk.f fVarM = gVar.m();
                                    nVar.g = 1 | nVar.g;
                                    nVar.h = fVarM;
                                } else if (iF == 18) {
                                    o.b bVarH = (nVar.g & 2) != 0 ? nVar.i.h() : null;
                                    o oVar = (o) gVar.v(o.k, sVar);
                                    nVar.i = oVar;
                                    if (bVarH != null) {
                                        bVarH.Q(oVar);
                                        nVar.i = bVarH.d();
                                    }
                                    nVar.g |= 2;
                                } else if (!aVar.r(iF, gVar)) {
                                }
                            }
                            z = true;
                        } catch (d0 e) {
                            e.e = nVar;
                            throw e;
                        } catch (IOException e2) {
                            d0 d0Var = new d0(e2);
                            d0Var.e = nVar;
                            throw d0Var;
                        }
                    } catch (Throwable th) {
                        nVar.f = aVar.build();
                        throw th;
                    }
                }
                nVar.f = aVar.build();
                return nVar;
            }
        }

        /* JADX INFO: compiled from: DescriptorProtos.java */
        public static final class b extends a0.a<b> implements t0 {
            public int g;
            public Object h = "";
            public o i;

            @Override // com.google.protobuf.a0.a
            /* JADX INFO: renamed from: A */
            public final a0.a f(n.f fVar, Object obj) {
                super.f(fVar, obj);
                return this;
            }

            @Override // com.google.protobuf.a0.a
            /* JADX INFO: renamed from: B */
            public final a0.a clone() {
                return (b) super.clone();
            }

            @Override // com.google.protobuf.a0.a
            public final a0.e D() {
                a0.e eVar = m.n;
                eVar.c(n.class, b.class);
                return eVar;
            }

            @Override // com.google.protobuf.a0.a
            /* JADX INFO: renamed from: E */
            public final a0.a y(j1 j1Var) {
                return (b) super.y(j1Var);
            }

            @Override // com.google.protobuf.a0.a
            /* JADX INFO: renamed from: I */
            public final a0.a a(n.f fVar, Object obj) {
                super.a(fVar, obj);
                return this;
            }

            @Override // com.google.protobuf.a0.a
            /* JADX INFO: renamed from: J */
            public final a0.a j0(j1 j1Var) {
                this.f = j1Var;
                H();
                return this;
            }

            @Override // com.google.protobuf.r0.a, com.google.protobuf.q0.a
            /* JADX INFO: renamed from: K, reason: merged with bridge method [inline-methods] */
            public final n d() {
                n nVar = new n(this);
                int i = this.g;
                int i2 = (i & 1) != 0 ? 1 : 0;
                nVar.h = this.h;
                if ((i & 2) != 0) {
                    nVar.i = this.i;
                    i2 |= 2;
                }
                nVar.g = i2;
                F();
                return nVar;
            }

            @Override // com.google.protobuf.a.AbstractC0042a, com.google.protobuf.r0.a
            public final /* bridge */ /* synthetic */ r0.a L(com.google.protobuf.g gVar, com.google.protobuf.s sVar) throws Throwable {
                N(gVar, sVar);
                return this;
            }

            public final void M(n nVar) {
                o oVar;
                o oVar2;
                if (nVar == n.k) {
                    return;
                }
                if ((nVar.g & 1) != 0) {
                    this.g |= 1;
                    this.h = nVar.h;
                    H();
                }
                if (nVar.T()) {
                    o oVarS = nVar.S();
                    if ((this.g & 2) == 0 || (oVar = this.i) == null || oVar == (oVar2 = o.j)) {
                        this.i = oVarS;
                    } else {
                        o.b bVarH = oVar2.h();
                        bVarH.Q(oVar);
                        bVarH.Q(oVarS);
                        this.i = bVarH.d();
                    }
                    H();
                    this.g |= 2;
                }
                H();
            }

            /* JADX WARN: Removed duplicated region for block: B:15:0x001d  */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public final void N(com.google.protobuf.g r2, com.google.protobuf.s r3) throws java.lang.Throwable {
                /*
                    r1 = this;
                    com.google.protobuf.m$n$a r0 = com.google.protobuf.m.n.l     // Catch: java.lang.Throwable -> Lc com.google.protobuf.d0 -> Le
                    java.lang.Object r2 = r0.b(r2, r3)     // Catch: java.lang.Throwable -> Lc com.google.protobuf.d0 -> Le
                    com.google.protobuf.m$n r2 = (com.google.protobuf.m.n) r2     // Catch: java.lang.Throwable -> Lc com.google.protobuf.d0 -> Le
                    r1.M(r2)
                    return
                Lc:
                    r2 = move-exception
                    goto L1a
                Le:
                    r2 = move-exception
                    com.google.protobuf.r0 r3 = r2.e     // Catch: java.lang.Throwable -> Lc
                    com.google.protobuf.m$n r3 = (com.google.protobuf.m.n) r3     // Catch: java.lang.Throwable -> Lc
                    java.io.IOException r2 = r2.l()     // Catch: java.lang.Throwable -> L18
                    throw r2     // Catch: java.lang.Throwable -> L18
                L18:
                    r2 = move-exception
                    goto L1b
                L1a:
                    r3 = 0
                L1b:
                    if (r3 == 0) goto L20
                    r1.M(r3)
                L20:
                    throw r2
                */
                throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.m.n.b.N(com.google.protobuf.g, com.google.protobuf.s):void");
            }

            @Override // com.google.protobuf.a.AbstractC0042a, com.google.protobuf.q0.a
            public final q0.a R(q0 q0Var) {
                if (q0Var instanceof n) {
                    M((n) q0Var);
                } else {
                    super.R(q0Var);
                }
                return this;
            }

            @Override // com.google.protobuf.a0.a, com.google.protobuf.q0.a
            public final q0.a a(n.f fVar, Object obj) {
                super.a(fVar, obj);
                return this;
            }

            @Override // defpackage.bh1, com.google.protobuf.t0
            public final q0 b() {
                return n.k;
            }

            @Override // com.google.protobuf.r0.a, com.google.protobuf.q0.a
            public final q0 build() {
                n nVarD = d();
                if (nVarD.isInitialized()) {
                    return nVarD;
                }
                throw a.AbstractC0042a.z(nVarD);
            }

            @Override // com.google.protobuf.a0.a, com.google.protobuf.q0.a, com.google.protobuf.t0
            public final n.a c() {
                return m.m;
            }

            @Override // com.google.protobuf.a0.a
            public final Object clone() {
                return (b) super.clone();
            }

            @Override // com.google.protobuf.a0.a, com.google.protobuf.q0.a
            public final q0.a f(n.f fVar, Object obj) {
                super.f(fVar, obj);
                return this;
            }

            @Override // com.google.protobuf.a0.a, com.google.protobuf.q0.a
            public final q0.a j0(j1 j1Var) {
                this.f = j1Var;
                H();
                return this;
            }

            @Override // com.google.protobuf.a.AbstractC0042a
            /* JADX INFO: renamed from: v */
            public final /* bridge */ /* synthetic */ a.AbstractC0042a L(com.google.protobuf.g gVar, com.google.protobuf.s sVar) throws Throwable {
                N(gVar, sVar);
                return this;
            }

            @Override // com.google.protobuf.a.AbstractC0042a
            /* JADX INFO: renamed from: x */
            public final a.AbstractC0042a R(q0 q0Var) {
                if (q0Var instanceof n) {
                    M((n) q0Var);
                } else {
                    super.R(q0Var);
                }
                return this;
            }

            @Override // com.google.protobuf.a0.a, com.google.protobuf.a.AbstractC0042a
            public final void y(j1 j1Var) {
            }

            @Override // defpackage.bh1, com.google.protobuf.t0
            public final r0 b() {
                return n.k;
            }

            @Override // com.google.protobuf.r0.a, com.google.protobuf.q0.a
            public final r0 build() {
                n nVarD = d();
                if (nVarD.isInitialized()) {
                    return nVarD;
                }
                throw a.AbstractC0042a.z(nVarD);
            }
        }

        public n(a0.a aVar) {
            super(aVar);
            this.j = (byte) -1;
        }

        @Override // com.google.protobuf.a0
        public final a0.e M() {
            a0.e eVar = m.n;
            eVar.c(n.class, b.class);
            return eVar;
        }

        @Override // com.google.protobuf.a0
        public final Object O() {
            return new n();
        }

        public final String Q() {
            Object obj = this.h;
            if (obj instanceof String) {
                return (String) obj;
            }
            lk lkVar = (lk) obj;
            String strZ = lkVar.z();
            if (lkVar.q()) {
                this.h = strZ;
            }
            return strZ;
        }

        public final o S() {
            o oVar = this.i;
            return oVar == null ? o.j : oVar;
        }

        public final boolean T() {
            return (this.g & 2) != 0;
        }

        @Override // com.google.protobuf.r0
        /* JADX INFO: renamed from: U, reason: merged with bridge method [inline-methods] */
        public final b h() {
            if (this == k) {
                return new b();
            }
            b bVar = new b();
            bVar.M(this);
            return bVar;
        }

        @Override // defpackage.bh1, com.google.protobuf.t0
        public final q0 b() {
            return k;
        }

        @Override // com.google.protobuf.r0, com.google.protobuf.q0
        public final q0.a e() {
            return k.h();
        }

        @Override // com.google.protobuf.a
        public final boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof n)) {
                return super.equals(obj);
            }
            n nVar = (n) obj;
            int i = this.g;
            if (((i & 1) != 0) != ((nVar.g & 1) != 0)) {
                return false;
            }
            if ((!((i & 1) != 0) || Q().equals(nVar.Q())) && T() == nVar.T()) {
                return (!T() || S().equals(nVar.S())) && this.f.equals(nVar.f);
            }
            return false;
        }

        @Override // com.google.protobuf.a
        public final int hashCode() {
            int i = this.memoizedHashCode;
            if (i != 0) {
                return i;
            }
            int iHashCode = m.m.hashCode() + 779;
            if ((this.g & 1) != 0) {
                iHashCode = C0175b.e(iHashCode, 37, 1, 53) + Q().hashCode();
            }
            if (T()) {
                iHashCode = C0175b.e(iHashCode, 37, 2, 53) + S().hashCode();
            }
            int iHashCode2 = this.f.hashCode() + (iHashCode * 29);
            this.memoizedHashCode = iHashCode2;
            return iHashCode2;
        }

        @Override // com.google.protobuf.a0, defpackage.bh1
        public final boolean isInitialized() {
            byte b2 = this.j;
            if (b2 == 1) {
                return true;
            }
            if (b2 == 0) {
                return false;
            }
            if (!T() || S().isInitialized()) {
                this.j = (byte) 1;
                return true;
            }
            this.j = (byte) 0;
            return false;
        }

        @Override // com.google.protobuf.a0, com.google.protobuf.r0
        public final int m() {
            int i = this.e;
            if (i != -1) {
                return i;
            }
            int iH = (this.g & 1) != 0 ? 0 + a0.H(1, this.h) : 0;
            if ((this.g & 2) != 0) {
                iH += nu.a0(2, S());
            }
            int iM = this.f.m() + iH;
            this.e = iM;
            return iM;
        }

        @Override // com.google.protobuf.a0, com.google.protobuf.t0
        public final j1 n() {
            return this.f;
        }

        @Override // com.google.protobuf.a0, com.google.protobuf.r0
        public final void p(nu nuVar) {
            if ((this.g & 1) != 0) {
                a0.P(nuVar, 1, this.h);
            }
            if ((this.g & 2) != 0) {
                nuVar.D0(2, S());
            }
            this.f.p(nuVar);
        }

        @Override // com.google.protobuf.a0, com.google.protobuf.r0
        public final wv1<n> u() {
            return l;
        }

        @Override // defpackage.bh1, com.google.protobuf.t0
        public final r0 b() {
            return k;
        }

        @Override // com.google.protobuf.r0, com.google.protobuf.q0
        public final r0.a e() {
            return k.h();
        }

        public n() {
            this.j = (byte) -1;
            this.h = "";
        }
    }

    /* JADX INFO: compiled from: DescriptorProtos.java */
    public static final class o extends a0.d<o> {
        public static final o j = new o();

        @Deprecated
        public static final a k = new a();
        private static final long serialVersionUID = 0;
        public List<s> h;
        public byte i;

        /* JADX INFO: compiled from: DescriptorProtos.java */
        public static class a extends com.google.protobuf.c<o> {
            @Override // defpackage.wv1
            public final Object b(com.google.protobuf.g gVar, com.google.protobuf.s sVar) {
                o oVar = new o();
                sVar.getClass();
                j1 j1Var = j1.f;
                j1.a aVar = new j1.a();
                boolean z = false;
                boolean z2 = false;
                while (!z) {
                    try {
                        try {
                            int iF = gVar.F();
                            if (iF != 0) {
                                if (iF == 7994) {
                                    if (!(z2 & true)) {
                                        oVar.h = new ArrayList();
                                        z2 |= true;
                                    }
                                    oVar.h.add((s) gVar.v(s.q, sVar));
                                } else if (!oVar.V(gVar, aVar, sVar, iF)) {
                                }
                            }
                            z = true;
                        } catch (d0 e) {
                            e.e = oVar;
                            throw e;
                        } catch (IOException e2) {
                            d0 d0Var = new d0(e2);
                            d0Var.e = oVar;
                            throw d0Var;
                        }
                    } finally {
                        if (z2 & true) {
                            oVar.h = Collections.unmodifiableList(oVar.h);
                        }
                        oVar.f = aVar.build();
                        oVar.U();
                    }
                }
                return oVar;
            }
        }

        /* JADX INFO: compiled from: DescriptorProtos.java */
        public static final class b extends a0.c<o, b> {
            public int h;
            public List<s> i = Collections.emptyList();

            @Override // com.google.protobuf.a0.a
            /* JADX INFO: renamed from: A */
            public final a0.a f(n.f fVar, Object obj) {
                super.K(fVar, obj);
                return this;
            }

            @Override // com.google.protobuf.a0.a
            /* JADX INFO: renamed from: B */
            public final a0.a clone() {
                return (b) super.clone();
            }

            @Override // com.google.protobuf.a0.a
            public final a0.e D() {
                a0.e eVar = m.F;
                eVar.c(o.class, b.class);
                return eVar;
            }

            @Override // com.google.protobuf.a0.a
            /* JADX INFO: renamed from: E */
            public final a0.a y(j1 j1Var) {
                return (b) super.y(j1Var);
            }

            @Override // com.google.protobuf.a0.a
            /* JADX INFO: renamed from: I */
            public final a0.a a(n.f fVar, Object obj) {
                return (b) super.N(fVar, obj);
            }

            @Override // com.google.protobuf.a0.a
            /* JADX INFO: renamed from: J */
            public final a0.a j0(j1 j1Var) {
                this.f = j1Var;
                H();
                return this;
            }

            @Override // com.google.protobuf.a.AbstractC0042a, com.google.protobuf.r0.a
            public final /* bridge */ /* synthetic */ r0.a L(com.google.protobuf.g gVar, com.google.protobuf.s sVar) throws Throwable {
                S(gVar, sVar);
                return this;
            }

            @Override // com.google.protobuf.r0.a, com.google.protobuf.q0.a
            /* JADX INFO: renamed from: P, reason: merged with bridge method [inline-methods] */
            public final o d() {
                o oVar = new o(this);
                if ((this.h & 1) != 0) {
                    this.i = Collections.unmodifiableList(this.i);
                    this.h &= -2;
                }
                oVar.h = this.i;
                F();
                return oVar;
            }

            public final void Q(o oVar) {
                if (oVar == o.j) {
                    return;
                }
                if (!oVar.h.isEmpty()) {
                    if (this.i.isEmpty()) {
                        this.i = oVar.h;
                        this.h &= -2;
                    } else {
                        if ((this.h & 1) == 0) {
                            this.i = new ArrayList(this.i);
                            this.h |= 1;
                        }
                        this.i.addAll(oVar.h);
                    }
                    H();
                }
                M(oVar);
                H();
            }

            @Override // com.google.protobuf.a.AbstractC0042a, com.google.protobuf.q0.a
            public final q0.a R(q0 q0Var) {
                if (q0Var instanceof o) {
                    Q((o) q0Var);
                } else {
                    super.R(q0Var);
                }
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:15:0x001d  */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public final void S(com.google.protobuf.g r2, com.google.protobuf.s r3) throws java.lang.Throwable {
                /*
                    r1 = this;
                    com.google.protobuf.m$o$a r0 = com.google.protobuf.m.o.k     // Catch: java.lang.Throwable -> Lc com.google.protobuf.d0 -> Le
                    java.lang.Object r2 = r0.b(r2, r3)     // Catch: java.lang.Throwable -> Lc com.google.protobuf.d0 -> Le
                    com.google.protobuf.m$o r2 = (com.google.protobuf.m.o) r2     // Catch: java.lang.Throwable -> Lc com.google.protobuf.d0 -> Le
                    r1.Q(r2)
                    return
                Lc:
                    r2 = move-exception
                    goto L1a
                Le:
                    r2 = move-exception
                    com.google.protobuf.r0 r3 = r2.e     // Catch: java.lang.Throwable -> Lc
                    com.google.protobuf.m$o r3 = (com.google.protobuf.m.o) r3     // Catch: java.lang.Throwable -> Lc
                    java.io.IOException r2 = r2.l()     // Catch: java.lang.Throwable -> L18
                    throw r2     // Catch: java.lang.Throwable -> L18
                L18:
                    r2 = move-exception
                    goto L1b
                L1a:
                    r3 = 0
                L1b:
                    if (r3 == 0) goto L20
                    r1.Q(r3)
                L20:
                    throw r2
                */
                throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.m.o.b.S(com.google.protobuf.g, com.google.protobuf.s):void");
            }

            @Override // com.google.protobuf.a0.a, com.google.protobuf.q0.a
            public final q0.a a(n.f fVar, Object obj) {
                super.N(fVar, obj);
                return this;
            }

            @Override // defpackage.bh1, com.google.protobuf.t0
            public final q0 b() {
                return o.j;
            }

            @Override // com.google.protobuf.r0.a, com.google.protobuf.q0.a
            public final q0 build() {
                o oVarD = d();
                if (oVarD.isInitialized()) {
                    return oVarD;
                }
                throw a.AbstractC0042a.z(oVarD);
            }

            @Override // com.google.protobuf.a0.a, com.google.protobuf.q0.a, com.google.protobuf.t0
            public final n.a c() {
                return m.E;
            }

            @Override // com.google.protobuf.a0.a
            public final Object clone() {
                return (b) super.clone();
            }

            @Override // com.google.protobuf.a0.a, com.google.protobuf.q0.a
            public final q0.a f(n.f fVar, Object obj) {
                super.K(fVar, obj);
                return this;
            }

            @Override // com.google.protobuf.a0.a, com.google.protobuf.q0.a
            public final q0.a j0(j1 j1Var) {
                this.f = j1Var;
                H();
                return this;
            }

            @Override // com.google.protobuf.a.AbstractC0042a
            /* JADX INFO: renamed from: v */
            public final /* bridge */ /* synthetic */ a.AbstractC0042a L(com.google.protobuf.g gVar, com.google.protobuf.s sVar) throws Throwable {
                S(gVar, sVar);
                return this;
            }

            @Override // com.google.protobuf.a.AbstractC0042a
            /* JADX INFO: renamed from: x */
            public final a.AbstractC0042a R(q0 q0Var) {
                if (q0Var instanceof o) {
                    Q((o) q0Var);
                } else {
                    super.R(q0Var);
                }
                return this;
            }

            @Override // com.google.protobuf.a0.a, com.google.protobuf.a.AbstractC0042a
            public final void y(j1 j1Var) {
            }

            @Override // defpackage.bh1, com.google.protobuf.t0
            public final r0 b() {
                return o.j;
            }

            @Override // com.google.protobuf.r0.a, com.google.protobuf.q0.a
            public final r0 build() {
                o oVarD = d();
                if (oVarD.isInitialized()) {
                    return oVarD;
                }
                throw a.AbstractC0042a.z(oVarD);
            }
        }

        public o(a0.c cVar) {
            super(cVar);
            this.i = (byte) -1;
        }

        @Override // com.google.protobuf.a0
        public final a0.e M() {
            a0.e eVar = m.F;
            eVar.c(o.class, b.class);
            return eVar;
        }

        @Override // com.google.protobuf.a0
        public final Object O() {
            return new o();
        }

        @Override // com.google.protobuf.r0
        /* JADX INFO: renamed from: W, reason: merged with bridge method [inline-methods] */
        public final b h() {
            if (this == j) {
                return new b();
            }
            b bVar = new b();
            bVar.Q(this);
            return bVar;
        }

        @Override // defpackage.bh1, com.google.protobuf.t0
        public final q0 b() {
            return j;
        }

        @Override // com.google.protobuf.r0, com.google.protobuf.q0
        public final q0.a e() {
            return j.h();
        }

        @Override // com.google.protobuf.a
        public final boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof o)) {
                return super.equals(obj);
            }
            o oVar = (o) obj;
            return this.h.equals(oVar.h) && this.f.equals(oVar.f) && T().equals(oVar.T());
        }

        @Override // com.google.protobuf.a
        public final int hashCode() {
            int i = this.memoizedHashCode;
            if (i != 0) {
                return i;
            }
            int iHashCode = m.E.hashCode() + 779;
            if (this.h.size() > 0) {
                iHashCode = C0175b.e(iHashCode, 37, 999, 53) + this.h.hashCode();
            }
            int iHashCode2 = this.f.hashCode() + (com.google.protobuf.a.D(iHashCode, T()) * 29);
            this.memoizedHashCode = iHashCode2;
            return iHashCode2;
        }

        @Override // com.google.protobuf.a0.d, com.google.protobuf.a0, defpackage.bh1
        public final boolean isInitialized() {
            byte b2 = this.i;
            if (b2 == 1) {
                return true;
            }
            if (b2 == 0) {
                return false;
            }
            for (int i = 0; i < this.h.size(); i++) {
                if (!this.h.get(i).isInitialized()) {
                    this.i = (byte) 0;
                    return false;
                }
            }
            if (Q()) {
                this.i = (byte) 1;
                return true;
            }
            this.i = (byte) 0;
            return false;
        }

        @Override // com.google.protobuf.a0, com.google.protobuf.r0
        public final int m() {
            int i = this.e;
            if (i != -1) {
                return i;
            }
            int iA0 = 0;
            for (int i2 = 0; i2 < this.h.size(); i2++) {
                iA0 += nu.a0(999, this.h.get(i2));
            }
            int iM = this.f.m() + S() + iA0;
            this.e = iM;
            return iM;
        }

        @Override // com.google.protobuf.a0, com.google.protobuf.t0
        public final j1 n() {
            return this.f;
        }

        @Override // com.google.protobuf.a0, com.google.protobuf.r0
        public final void p(nu nuVar) {
            a0.d.a aVar = new a0.d.a(this);
            for (int i = 0; i < this.h.size(); i++) {
                nuVar.D0(999, this.h.get(i));
            }
            aVar.a(nuVar);
            this.f.p(nuVar);
        }

        @Override // com.google.protobuf.a0, com.google.protobuf.r0
        public final wv1<o> u() {
            return k;
        }

        @Override // defpackage.bh1, com.google.protobuf.t0
        public final r0 b() {
            return j;
        }

        @Override // com.google.protobuf.r0, com.google.protobuf.q0
        public final r0.a e() {
            return j.h();
        }

        public o() {
            this.i = (byte) -1;
            this.h = Collections.emptyList();
        }
    }

    /* JADX INFO: compiled from: DescriptorProtos.java */
    public static final class p extends a0 implements t0 {
        public static final p l = new p();

        @Deprecated
        public static final a m = new a();
        private static final long serialVersionUID = 0;
        public int g;
        public volatile Object h;
        public List<l> i;
        public q j;
        public byte k;

        /* JADX INFO: compiled from: DescriptorProtos.java */
        public static class a extends com.google.protobuf.c<p> {
            @Override // defpackage.wv1
            public final Object b(com.google.protobuf.g gVar, com.google.protobuf.s sVar) {
                p pVar = new p();
                sVar.getClass();
                j1 j1Var = j1.f;
                j1.a aVar = new j1.a();
                boolean z = false;
                int i = 0;
                while (!z) {
                    try {
                        try {
                            int iF = gVar.F();
                            if (iF != 0) {
                                if (iF == 10) {
                                    lk.f fVarM = gVar.m();
                                    pVar.g = 1 | pVar.g;
                                    pVar.h = fVarM;
                                } else if (iF == 18) {
                                    if ((i & 2) == 0) {
                                        pVar.i = new ArrayList();
                                        i |= 2;
                                    }
                                    pVar.i.add((l) gVar.v(l.p, sVar));
                                } else if (iF == 26) {
                                    q.b bVarH = (pVar.g & 2) != 0 ? pVar.j.h() : null;
                                    q qVar = (q) gVar.v(q.m, sVar);
                                    pVar.j = qVar;
                                    if (bVarH != null) {
                                        bVarH.Q(qVar);
                                        pVar.j = bVarH.d();
                                    }
                                    pVar.g |= 2;
                                } else if (!aVar.r(iF, gVar)) {
                                }
                            }
                            z = true;
                        } catch (d0 e) {
                            e.e = pVar;
                            throw e;
                        } catch (IOException e2) {
                            d0 d0Var = new d0(e2);
                            d0Var.e = pVar;
                            throw d0Var;
                        }
                    } catch (Throwable th) {
                        if ((i & 2) != 0) {
                            pVar.i = Collections.unmodifiableList(pVar.i);
                        }
                        pVar.f = aVar.build();
                        throw th;
                    }
                }
                if ((i & 2) != 0) {
                    pVar.i = Collections.unmodifiableList(pVar.i);
                }
                pVar.f = aVar.build();
                return pVar;
            }
        }

        /* JADX INFO: compiled from: DescriptorProtos.java */
        public static final class b extends a0.a<b> implements t0 {
            public int g;
            public Object h = "";
            public List<l> i = Collections.emptyList();
            public q j;

            @Override // com.google.protobuf.a0.a
            /* JADX INFO: renamed from: A */
            public final a0.a f(n.f fVar, Object obj) {
                super.f(fVar, obj);
                return this;
            }

            @Override // com.google.protobuf.a0.a
            /* JADX INFO: renamed from: B */
            public final a0.a clone() {
                return (b) super.clone();
            }

            @Override // com.google.protobuf.a0.a
            public final a0.e D() {
                a0.e eVar = m.v;
                eVar.c(p.class, b.class);
                return eVar;
            }

            @Override // com.google.protobuf.a0.a
            /* JADX INFO: renamed from: E */
            public final a0.a y(j1 j1Var) {
                return (b) super.y(j1Var);
            }

            @Override // com.google.protobuf.a0.a
            /* JADX INFO: renamed from: I */
            public final a0.a a(n.f fVar, Object obj) {
                super.a(fVar, obj);
                return this;
            }

            @Override // com.google.protobuf.a0.a
            /* JADX INFO: renamed from: J */
            public final a0.a j0(j1 j1Var) {
                this.f = j1Var;
                H();
                return this;
            }

            @Override // com.google.protobuf.r0.a, com.google.protobuf.q0.a
            /* JADX INFO: renamed from: K, reason: merged with bridge method [inline-methods] */
            public final p d() {
                p pVar = new p(this);
                int i = this.g;
                int i2 = (i & 1) != 0 ? 1 : 0;
                pVar.h = this.h;
                if ((this.g & 2) != 0) {
                    this.i = Collections.unmodifiableList(this.i);
                    this.g &= -3;
                }
                pVar.i = this.i;
                if ((i & 4) != 0) {
                    pVar.j = this.j;
                    i2 |= 2;
                }
                pVar.g = i2;
                F();
                return pVar;
            }

            @Override // com.google.protobuf.a.AbstractC0042a, com.google.protobuf.r0.a
            public final /* bridge */ /* synthetic */ r0.a L(com.google.protobuf.g gVar, com.google.protobuf.s sVar) throws Throwable {
                N(gVar, sVar);
                return this;
            }

            public final void M(p pVar) {
                q qVar;
                q qVar2;
                if (pVar == p.l) {
                    return;
                }
                if ((pVar.g & 1) != 0) {
                    this.g |= 1;
                    this.h = pVar.h;
                    H();
                }
                if (!pVar.i.isEmpty()) {
                    if (this.i.isEmpty()) {
                        this.i = pVar.i;
                        this.g &= -3;
                    } else {
                        if ((this.g & 2) == 0) {
                            this.i = new ArrayList(this.i);
                            this.g |= 2;
                        }
                        this.i.addAll(pVar.i);
                    }
                    H();
                }
                if (pVar.T()) {
                    q qVarS = pVar.S();
                    if ((this.g & 4) == 0 || (qVar = this.j) == null || qVar == (qVar2 = q.l)) {
                        this.j = qVarS;
                    } else {
                        q.b bVarH = qVar2.h();
                        bVarH.Q(qVar);
                        bVarH.Q(qVarS);
                        this.j = bVarH.d();
                    }
                    H();
                    this.g |= 4;
                }
                H();
            }

            /* JADX WARN: Removed duplicated region for block: B:15:0x001d  */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public final void N(com.google.protobuf.g r2, com.google.protobuf.s r3) throws java.lang.Throwable {
                /*
                    r1 = this;
                    com.google.protobuf.m$p$a r0 = com.google.protobuf.m.p.m     // Catch: java.lang.Throwable -> Lc com.google.protobuf.d0 -> Le
                    java.lang.Object r2 = r0.b(r2, r3)     // Catch: java.lang.Throwable -> Lc com.google.protobuf.d0 -> Le
                    com.google.protobuf.m$p r2 = (com.google.protobuf.m.p) r2     // Catch: java.lang.Throwable -> Lc com.google.protobuf.d0 -> Le
                    r1.M(r2)
                    return
                Lc:
                    r2 = move-exception
                    goto L1a
                Le:
                    r2 = move-exception
                    com.google.protobuf.r0 r3 = r2.e     // Catch: java.lang.Throwable -> Lc
                    com.google.protobuf.m$p r3 = (com.google.protobuf.m.p) r3     // Catch: java.lang.Throwable -> Lc
                    java.io.IOException r2 = r2.l()     // Catch: java.lang.Throwable -> L18
                    throw r2     // Catch: java.lang.Throwable -> L18
                L18:
                    r2 = move-exception
                    goto L1b
                L1a:
                    r3 = 0
                L1b:
                    if (r3 == 0) goto L20
                    r1.M(r3)
                L20:
                    throw r2
                */
                throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.m.p.b.N(com.google.protobuf.g, com.google.protobuf.s):void");
            }

            @Override // com.google.protobuf.a.AbstractC0042a, com.google.protobuf.q0.a
            public final q0.a R(q0 q0Var) {
                if (q0Var instanceof p) {
                    M((p) q0Var);
                } else {
                    super.R(q0Var);
                }
                return this;
            }

            @Override // com.google.protobuf.a0.a, com.google.protobuf.q0.a
            public final q0.a a(n.f fVar, Object obj) {
                super.a(fVar, obj);
                return this;
            }

            @Override // defpackage.bh1, com.google.protobuf.t0
            public final q0 b() {
                return p.l;
            }

            @Override // com.google.protobuf.r0.a, com.google.protobuf.q0.a
            public final q0 build() {
                p pVarD = d();
                if (pVarD.isInitialized()) {
                    return pVarD;
                }
                throw a.AbstractC0042a.z(pVarD);
            }

            @Override // com.google.protobuf.a0.a, com.google.protobuf.q0.a, com.google.protobuf.t0
            public final n.a c() {
                return m.u;
            }

            @Override // com.google.protobuf.a0.a
            public final Object clone() {
                return (b) super.clone();
            }

            @Override // com.google.protobuf.a0.a, com.google.protobuf.q0.a
            public final q0.a f(n.f fVar, Object obj) {
                super.f(fVar, obj);
                return this;
            }

            @Override // com.google.protobuf.a0.a, com.google.protobuf.q0.a
            public final q0.a j0(j1 j1Var) {
                this.f = j1Var;
                H();
                return this;
            }

            @Override // com.google.protobuf.a.AbstractC0042a
            /* JADX INFO: renamed from: v */
            public final /* bridge */ /* synthetic */ a.AbstractC0042a L(com.google.protobuf.g gVar, com.google.protobuf.s sVar) throws Throwable {
                N(gVar, sVar);
                return this;
            }

            @Override // com.google.protobuf.a.AbstractC0042a
            /* JADX INFO: renamed from: x */
            public final a.AbstractC0042a R(q0 q0Var) {
                if (q0Var instanceof p) {
                    M((p) q0Var);
                } else {
                    super.R(q0Var);
                }
                return this;
            }

            @Override // com.google.protobuf.a0.a, com.google.protobuf.a.AbstractC0042a
            public final void y(j1 j1Var) {
            }

            @Override // defpackage.bh1, com.google.protobuf.t0
            public final r0 b() {
                return p.l;
            }

            @Override // com.google.protobuf.r0.a, com.google.protobuf.q0.a
            public final r0 build() {
                p pVarD = d();
                if (pVarD.isInitialized()) {
                    return pVarD;
                }
                throw a.AbstractC0042a.z(pVarD);
            }
        }

        public p(a0.a aVar) {
            super(aVar);
            this.k = (byte) -1;
        }

        @Override // com.google.protobuf.a0
        public final a0.e M() {
            a0.e eVar = m.v;
            eVar.c(p.class, b.class);
            return eVar;
        }

        @Override // com.google.protobuf.a0
        public final Object O() {
            return new p();
        }

        public final String Q() {
            Object obj = this.h;
            if (obj instanceof String) {
                return (String) obj;
            }
            lk lkVar = (lk) obj;
            String strZ = lkVar.z();
            if (lkVar.q()) {
                this.h = strZ;
            }
            return strZ;
        }

        public final q S() {
            q qVar = this.j;
            return qVar == null ? q.l : qVar;
        }

        public final boolean T() {
            return (this.g & 2) != 0;
        }

        @Override // com.google.protobuf.r0
        /* JADX INFO: renamed from: U, reason: merged with bridge method [inline-methods] */
        public final b h() {
            if (this == l) {
                return new b();
            }
            b bVar = new b();
            bVar.M(this);
            return bVar;
        }

        @Override // defpackage.bh1, com.google.protobuf.t0
        public final q0 b() {
            return l;
        }

        @Override // com.google.protobuf.r0, com.google.protobuf.q0
        public final q0.a e() {
            return l.h();
        }

        @Override // com.google.protobuf.a
        public final boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof p)) {
                return super.equals(obj);
            }
            p pVar = (p) obj;
            int i = this.g;
            if (((i & 1) != 0) != ((pVar.g & 1) != 0)) {
                return false;
            }
            if ((!((i & 1) != 0) || Q().equals(pVar.Q())) && this.i.equals(pVar.i) && T() == pVar.T()) {
                return (!T() || S().equals(pVar.S())) && this.f.equals(pVar.f);
            }
            return false;
        }

        @Override // com.google.protobuf.a
        public final int hashCode() {
            int i = this.memoizedHashCode;
            if (i != 0) {
                return i;
            }
            int iHashCode = m.u.hashCode() + 779;
            if ((this.g & 1) != 0) {
                iHashCode = C0175b.e(iHashCode, 37, 1, 53) + Q().hashCode();
            }
            if (this.i.size() > 0) {
                iHashCode = C0175b.e(iHashCode, 37, 2, 53) + this.i.hashCode();
            }
            if (T()) {
                iHashCode = C0175b.e(iHashCode, 37, 3, 53) + S().hashCode();
            }
            int iHashCode2 = this.f.hashCode() + (iHashCode * 29);
            this.memoizedHashCode = iHashCode2;
            return iHashCode2;
        }

        @Override // com.google.protobuf.a0, defpackage.bh1
        public final boolean isInitialized() {
            byte b2 = this.k;
            if (b2 == 1) {
                return true;
            }
            if (b2 == 0) {
                return false;
            }
            for (int i = 0; i < this.i.size(); i++) {
                if (!this.i.get(i).isInitialized()) {
                    this.k = (byte) 0;
                    return false;
                }
            }
            if (!T() || S().isInitialized()) {
                this.k = (byte) 1;
                return true;
            }
            this.k = (byte) 0;
            return false;
        }

        @Override // com.google.protobuf.a0, com.google.protobuf.r0
        public final int m() {
            int i = this.e;
            if (i != -1) {
                return i;
            }
            int iH = (this.g & 1) != 0 ? a0.H(1, this.h) + 0 : 0;
            for (int i2 = 0; i2 < this.i.size(); i2++) {
                iH += nu.a0(2, this.i.get(i2));
            }
            if ((this.g & 2) != 0) {
                iH += nu.a0(3, S());
            }
            int iM = this.f.m() + iH;
            this.e = iM;
            return iM;
        }

        @Override // com.google.protobuf.a0, com.google.protobuf.t0
        public final j1 n() {
            return this.f;
        }

        @Override // com.google.protobuf.a0, com.google.protobuf.r0
        public final void p(nu nuVar) {
            if ((this.g & 1) != 0) {
                a0.P(nuVar, 1, this.h);
            }
            for (int i = 0; i < this.i.size(); i++) {
                nuVar.D0(2, this.i.get(i));
            }
            if ((this.g & 2) != 0) {
                nuVar.D0(3, S());
            }
            this.f.p(nuVar);
        }

        @Override // com.google.protobuf.a0, com.google.protobuf.r0
        public final wv1<p> u() {
            return m;
        }

        @Override // defpackage.bh1, com.google.protobuf.t0
        public final r0 b() {
            return l;
        }

        @Override // com.google.protobuf.r0, com.google.protobuf.q0
        public final r0.a e() {
            return l.h();
        }

        public p() {
            this.k = (byte) -1;
            this.h = "";
            this.i = Collections.emptyList();
        }
    }

    /* JADX INFO: compiled from: DescriptorProtos.java */
    public static final class q extends a0.d<q> {
        public static final q l = new q();

        @Deprecated
        public static final a m = new a();
        private static final long serialVersionUID = 0;
        public int h;
        public boolean i;
        public List<s> j;
        public byte k;

        /* JADX INFO: compiled from: DescriptorProtos.java */
        public static class a extends com.google.protobuf.c<q> {
            @Override // defpackage.wv1
            public final Object b(com.google.protobuf.g gVar, com.google.protobuf.s sVar) {
                q qVar = new q();
                sVar.getClass();
                j1 j1Var = j1.f;
                j1.a aVar = new j1.a();
                boolean z = false;
                int i = 0;
                while (!z) {
                    try {
                        try {
                            try {
                                int iF = gVar.F();
                                if (iF != 0) {
                                    if (iF == 264) {
                                        qVar.h |= 1;
                                        qVar.i = gVar.l();
                                    } else if (iF == 7994) {
                                        if ((i & 2) == 0) {
                                            qVar.j = new ArrayList();
                                            i |= 2;
                                        }
                                        qVar.j.add((s) gVar.v(s.q, sVar));
                                    } else if (!qVar.V(gVar, aVar, sVar, iF)) {
                                    }
                                }
                                z = true;
                            } catch (IOException e) {
                                d0 d0Var = new d0(e);
                                d0Var.e = qVar;
                                throw d0Var;
                            }
                        } catch (d0 e2) {
                            e2.e = qVar;
                            throw e2;
                        }
                    } catch (Throwable th) {
                        if ((i & 2) != 0) {
                            qVar.j = Collections.unmodifiableList(qVar.j);
                        }
                        qVar.f = aVar.build();
                        qVar.U();
                        throw th;
                    }
                }
                if ((i & 2) != 0) {
                    qVar.j = Collections.unmodifiableList(qVar.j);
                }
                qVar.f = aVar.build();
                qVar.U();
                return qVar;
            }
        }

        /* JADX INFO: compiled from: DescriptorProtos.java */
        public static final class b extends a0.c<q, b> {
            public int h;
            public boolean i;
            public List<s> j = Collections.emptyList();

            @Override // com.google.protobuf.a0.a
            /* JADX INFO: renamed from: A */
            public final a0.a f(n.f fVar, Object obj) {
                super.K(fVar, obj);
                return this;
            }

            @Override // com.google.protobuf.a0.a
            /* JADX INFO: renamed from: B */
            public final a0.a clone() {
                return (b) super.clone();
            }

            @Override // com.google.protobuf.a0.a
            public final a0.e D() {
                a0.e eVar = m.L;
                eVar.c(q.class, b.class);
                return eVar;
            }

            @Override // com.google.protobuf.a0.a
            /* JADX INFO: renamed from: E */
            public final a0.a y(j1 j1Var) {
                return (b) super.y(j1Var);
            }

            @Override // com.google.protobuf.a0.a
            /* JADX INFO: renamed from: I */
            public final a0.a a(n.f fVar, Object obj) {
                return (b) super.N(fVar, obj);
            }

            @Override // com.google.protobuf.a0.a
            /* JADX INFO: renamed from: J */
            public final a0.a j0(j1 j1Var) {
                this.f = j1Var;
                H();
                return this;
            }

            @Override // com.google.protobuf.a.AbstractC0042a, com.google.protobuf.r0.a
            public final /* bridge */ /* synthetic */ r0.a L(com.google.protobuf.g gVar, com.google.protobuf.s sVar) throws Throwable {
                S(gVar, sVar);
                return this;
            }

            @Override // com.google.protobuf.r0.a, com.google.protobuf.q0.a
            /* JADX INFO: renamed from: P, reason: merged with bridge method [inline-methods] */
            public final q d() {
                int i;
                q qVar = new q(this);
                int i2 = this.h;
                if ((i2 & 1) != 0) {
                    qVar.i = this.i;
                    i = 1;
                } else {
                    i = 0;
                }
                if ((i2 & 2) != 0) {
                    this.j = Collections.unmodifiableList(this.j);
                    this.h &= -3;
                }
                qVar.j = this.j;
                qVar.h = i;
                F();
                return qVar;
            }

            public final void Q(q qVar) {
                if (qVar == q.l) {
                    return;
                }
                if ((qVar.h & 1) != 0) {
                    boolean z = qVar.i;
                    this.h = 1 | this.h;
                    this.i = z;
                    H();
                }
                if (!qVar.j.isEmpty()) {
                    if (this.j.isEmpty()) {
                        this.j = qVar.j;
                        this.h &= -3;
                    } else {
                        if ((this.h & 2) == 0) {
                            this.j = new ArrayList(this.j);
                            this.h |= 2;
                        }
                        this.j.addAll(qVar.j);
                    }
                    H();
                }
                M(qVar);
                H();
            }

            @Override // com.google.protobuf.a.AbstractC0042a, com.google.protobuf.q0.a
            public final q0.a R(q0 q0Var) {
                if (q0Var instanceof q) {
                    Q((q) q0Var);
                } else {
                    super.R(q0Var);
                }
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:15:0x001d  */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public final void S(com.google.protobuf.g r2, com.google.protobuf.s r3) throws java.lang.Throwable {
                /*
                    r1 = this;
                    com.google.protobuf.m$q$a r0 = com.google.protobuf.m.q.m     // Catch: java.lang.Throwable -> Lc com.google.protobuf.d0 -> Le
                    java.lang.Object r2 = r0.b(r2, r3)     // Catch: java.lang.Throwable -> Lc com.google.protobuf.d0 -> Le
                    com.google.protobuf.m$q r2 = (com.google.protobuf.m.q) r2     // Catch: java.lang.Throwable -> Lc com.google.protobuf.d0 -> Le
                    r1.Q(r2)
                    return
                Lc:
                    r2 = move-exception
                    goto L1a
                Le:
                    r2 = move-exception
                    com.google.protobuf.r0 r3 = r2.e     // Catch: java.lang.Throwable -> Lc
                    com.google.protobuf.m$q r3 = (com.google.protobuf.m.q) r3     // Catch: java.lang.Throwable -> Lc
                    java.io.IOException r2 = r2.l()     // Catch: java.lang.Throwable -> L18
                    throw r2     // Catch: java.lang.Throwable -> L18
                L18:
                    r2 = move-exception
                    goto L1b
                L1a:
                    r3 = 0
                L1b:
                    if (r3 == 0) goto L20
                    r1.Q(r3)
                L20:
                    throw r2
                */
                throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.m.q.b.S(com.google.protobuf.g, com.google.protobuf.s):void");
            }

            @Override // com.google.protobuf.a0.a, com.google.protobuf.q0.a
            public final q0.a a(n.f fVar, Object obj) {
                super.N(fVar, obj);
                return this;
            }

            @Override // defpackage.bh1, com.google.protobuf.t0
            public final q0 b() {
                return q.l;
            }

            @Override // com.google.protobuf.r0.a, com.google.protobuf.q0.a
            public final q0 build() {
                q qVarD = d();
                if (qVarD.isInitialized()) {
                    return qVarD;
                }
                throw a.AbstractC0042a.z(qVarD);
            }

            @Override // com.google.protobuf.a0.a, com.google.protobuf.q0.a, com.google.protobuf.t0
            public final n.a c() {
                return m.K;
            }

            @Override // com.google.protobuf.a0.a
            public final Object clone() {
                return (b) super.clone();
            }

            @Override // com.google.protobuf.a0.a, com.google.protobuf.q0.a
            public final q0.a f(n.f fVar, Object obj) {
                super.K(fVar, obj);
                return this;
            }

            @Override // com.google.protobuf.a0.a, com.google.protobuf.q0.a
            public final q0.a j0(j1 j1Var) {
                this.f = j1Var;
                H();
                return this;
            }

            @Override // com.google.protobuf.a.AbstractC0042a
            /* JADX INFO: renamed from: v */
            public final /* bridge */ /* synthetic */ a.AbstractC0042a L(com.google.protobuf.g gVar, com.google.protobuf.s sVar) throws Throwable {
                S(gVar, sVar);
                return this;
            }

            @Override // com.google.protobuf.a.AbstractC0042a
            /* JADX INFO: renamed from: x */
            public final a.AbstractC0042a R(q0 q0Var) {
                if (q0Var instanceof q) {
                    Q((q) q0Var);
                } else {
                    super.R(q0Var);
                }
                return this;
            }

            @Override // com.google.protobuf.a0.a, com.google.protobuf.a.AbstractC0042a
            public final void y(j1 j1Var) {
            }

            @Override // defpackage.bh1, com.google.protobuf.t0
            public final r0 b() {
                return q.l;
            }

            @Override // com.google.protobuf.r0.a, com.google.protobuf.q0.a
            public final r0 build() {
                q qVarD = d();
                if (qVarD.isInitialized()) {
                    return qVarD;
                }
                throw a.AbstractC0042a.z(qVarD);
            }
        }

        public q(a0.c cVar) {
            super(cVar);
            this.k = (byte) -1;
        }

        @Override // com.google.protobuf.a0
        public final a0.e M() {
            a0.e eVar = m.L;
            eVar.c(q.class, b.class);
            return eVar;
        }

        @Override // com.google.protobuf.a0
        public final Object O() {
            return new q();
        }

        @Override // com.google.protobuf.r0
        /* JADX INFO: renamed from: W, reason: merged with bridge method [inline-methods] */
        public final b h() {
            if (this == l) {
                return new b();
            }
            b bVar = new b();
            bVar.Q(this);
            return bVar;
        }

        @Override // defpackage.bh1, com.google.protobuf.t0
        public final q0 b() {
            return l;
        }

        @Override // com.google.protobuf.r0, com.google.protobuf.q0
        public final q0.a e() {
            return l.h();
        }

        @Override // com.google.protobuf.a
        public final boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof q)) {
                return super.equals(obj);
            }
            q qVar = (q) obj;
            int i = this.h;
            if (((i & 1) != 0) != ((qVar.h & 1) != 0)) {
                return false;
            }
            return (!((i & 1) != 0) || this.i == qVar.i) && this.j.equals(qVar.j) && this.f.equals(qVar.f) && T().equals(qVar.T());
        }

        @Override // com.google.protobuf.a
        public final int hashCode() {
            int i = this.memoizedHashCode;
            if (i != 0) {
                return i;
            }
            int iHashCode = m.K.hashCode() + 779;
            if ((this.h & 1) != 0) {
                iHashCode = C0175b.e(iHashCode, 37, 33, 53) + c0.a(this.i);
            }
            if (this.j.size() > 0) {
                iHashCode = C0175b.e(iHashCode, 37, 999, 53) + this.j.hashCode();
            }
            int iHashCode2 = this.f.hashCode() + (com.google.protobuf.a.D(iHashCode, T()) * 29);
            this.memoizedHashCode = iHashCode2;
            return iHashCode2;
        }

        @Override // com.google.protobuf.a0.d, com.google.protobuf.a0, defpackage.bh1
        public final boolean isInitialized() {
            byte b2 = this.k;
            if (b2 == 1) {
                return true;
            }
            if (b2 == 0) {
                return false;
            }
            for (int i = 0; i < this.j.size(); i++) {
                if (!this.j.get(i).isInitialized()) {
                    this.k = (byte) 0;
                    return false;
                }
            }
            if (Q()) {
                this.k = (byte) 1;
                return true;
            }
            this.k = (byte) 0;
            return false;
        }

        @Override // com.google.protobuf.a0, com.google.protobuf.r0
        public final int m() {
            int i = this.e;
            if (i != -1) {
                return i;
            }
            int iJ = (this.h & 1) != 0 ? nu.J(33) + 0 : 0;
            for (int i2 = 0; i2 < this.j.size(); i2++) {
                iJ += nu.a0(999, this.j.get(i2));
            }
            int iM = this.f.m() + S() + iJ;
            this.e = iM;
            return iM;
        }

        @Override // com.google.protobuf.a0, com.google.protobuf.t0
        public final j1 n() {
            return this.f;
        }

        @Override // com.google.protobuf.a0, com.google.protobuf.r0
        public final void p(nu nuVar) {
            a0.d.a aVar = new a0.d.a(this);
            if ((this.h & 1) != 0) {
                nuVar.p0(33, this.i);
            }
            for (int i = 0; i < this.j.size(); i++) {
                nuVar.D0(999, this.j.get(i));
            }
            aVar.a(nuVar);
            this.f.p(nuVar);
        }

        @Override // com.google.protobuf.a0, com.google.protobuf.r0
        public final wv1<q> u() {
            return m;
        }

        @Override // defpackage.bh1, com.google.protobuf.t0
        public final r0 b() {
            return l;
        }

        @Override // com.google.protobuf.r0, com.google.protobuf.q0
        public final r0.a e() {
            return l.h();
        }

        public q() {
            this.k = (byte) -1;
            this.j = Collections.emptyList();
        }
    }

    /* JADX INFO: compiled from: DescriptorProtos.java */
    public static final class r extends a0 implements t0 {
        public static final r i = new r();

        @Deprecated
        public static final a j = new a();
        private static final long serialVersionUID = 0;
        public List<c> g;
        public byte h;

        /* JADX INFO: compiled from: DescriptorProtos.java */
        public static class a extends com.google.protobuf.c<r> {
            @Override // defpackage.wv1
            public final Object b(com.google.protobuf.g gVar, com.google.protobuf.s sVar) {
                r rVar = new r();
                sVar.getClass();
                j1 j1Var = j1.f;
                j1.a aVar = new j1.a();
                boolean z = false;
                boolean z2 = false;
                while (!z) {
                    try {
                        try {
                            int iF = gVar.F();
                            if (iF != 0) {
                                if (iF == 10) {
                                    if (!(z2 & true)) {
                                        rVar.g = new ArrayList();
                                        z2 |= true;
                                    }
                                    rVar.g.add((c) gVar.v(c.q, sVar));
                                } else if (!aVar.r(iF, gVar)) {
                                }
                            }
                            z = true;
                        } catch (d0 e) {
                            e.e = rVar;
                            throw e;
                        } catch (IOException e2) {
                            d0 d0Var = new d0(e2);
                            d0Var.e = rVar;
                            throw d0Var;
                        }
                    } catch (Throwable th) {
                        if (z2 & true) {
                            rVar.g = Collections.unmodifiableList(rVar.g);
                        }
                        rVar.f = aVar.build();
                        throw th;
                    }
                }
                if (z2 & true) {
                    rVar.g = Collections.unmodifiableList(rVar.g);
                }
                rVar.f = aVar.build();
                return rVar;
            }
        }

        /* JADX INFO: compiled from: DescriptorProtos.java */
        public static final class b extends a0.a<b> implements t0 {
            public int g;
            public List<c> h = Collections.emptyList();

            @Override // com.google.protobuf.a0.a
            /* JADX INFO: renamed from: A */
            public final a0.a f(n.f fVar, Object obj) {
                super.f(fVar, obj);
                return this;
            }

            @Override // com.google.protobuf.a0.a
            /* JADX INFO: renamed from: B */
            public final a0.a clone() {
                return (b) super.clone();
            }

            @Override // com.google.protobuf.a0.a
            public final a0.e D() {
                a0.e eVar = m.T;
                eVar.c(r.class, b.class);
                return eVar;
            }

            @Override // com.google.protobuf.a0.a
            /* JADX INFO: renamed from: E */
            public final a0.a y(j1 j1Var) {
                return (b) super.y(j1Var);
            }

            @Override // com.google.protobuf.a0.a
            /* JADX INFO: renamed from: I */
            public final a0.a a(n.f fVar, Object obj) {
                super.a(fVar, obj);
                return this;
            }

            @Override // com.google.protobuf.a0.a
            /* JADX INFO: renamed from: J */
            public final a0.a j0(j1 j1Var) {
                this.f = j1Var;
                H();
                return this;
            }

            @Override // com.google.protobuf.r0.a, com.google.protobuf.q0.a
            /* JADX INFO: renamed from: K, reason: merged with bridge method [inline-methods] */
            public final r d() {
                r rVar = new r(this);
                if ((this.g & 1) != 0) {
                    this.h = Collections.unmodifiableList(this.h);
                    this.g &= -2;
                }
                rVar.g = this.h;
                F();
                return rVar;
            }

            @Override // com.google.protobuf.a.AbstractC0042a, com.google.protobuf.r0.a
            public final /* bridge */ /* synthetic */ r0.a L(com.google.protobuf.g gVar, com.google.protobuf.s sVar) throws Throwable {
                N(gVar, sVar);
                return this;
            }

            public final void M(r rVar) {
                if (rVar == r.i) {
                    return;
                }
                if (!rVar.g.isEmpty()) {
                    if (this.h.isEmpty()) {
                        this.h = rVar.g;
                        this.g &= -2;
                    } else {
                        if ((this.g & 1) == 0) {
                            this.h = new ArrayList(this.h);
                            this.g |= 1;
                        }
                        this.h.addAll(rVar.g);
                    }
                    H();
                }
                H();
            }

            /* JADX WARN: Removed duplicated region for block: B:15:0x001d  */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public final void N(com.google.protobuf.g r2, com.google.protobuf.s r3) throws java.lang.Throwable {
                /*
                    r1 = this;
                    com.google.protobuf.m$r$a r0 = com.google.protobuf.m.r.j     // Catch: java.lang.Throwable -> Lc com.google.protobuf.d0 -> Le
                    java.lang.Object r2 = r0.b(r2, r3)     // Catch: java.lang.Throwable -> Lc com.google.protobuf.d0 -> Le
                    com.google.protobuf.m$r r2 = (com.google.protobuf.m.r) r2     // Catch: java.lang.Throwable -> Lc com.google.protobuf.d0 -> Le
                    r1.M(r2)
                    return
                Lc:
                    r2 = move-exception
                    goto L1a
                Le:
                    r2 = move-exception
                    com.google.protobuf.r0 r3 = r2.e     // Catch: java.lang.Throwable -> Lc
                    com.google.protobuf.m$r r3 = (com.google.protobuf.m.r) r3     // Catch: java.lang.Throwable -> Lc
                    java.io.IOException r2 = r2.l()     // Catch: java.lang.Throwable -> L18
                    throw r2     // Catch: java.lang.Throwable -> L18
                L18:
                    r2 = move-exception
                    goto L1b
                L1a:
                    r3 = 0
                L1b:
                    if (r3 == 0) goto L20
                    r1.M(r3)
                L20:
                    throw r2
                */
                throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.m.r.b.N(com.google.protobuf.g, com.google.protobuf.s):void");
            }

            @Override // com.google.protobuf.a.AbstractC0042a, com.google.protobuf.q0.a
            public final q0.a R(q0 q0Var) {
                if (q0Var instanceof r) {
                    M((r) q0Var);
                } else {
                    super.R(q0Var);
                }
                return this;
            }

            @Override // com.google.protobuf.a0.a, com.google.protobuf.q0.a
            public final q0.a a(n.f fVar, Object obj) {
                super.a(fVar, obj);
                return this;
            }

            @Override // defpackage.bh1, com.google.protobuf.t0
            public final q0 b() {
                return r.i;
            }

            @Override // com.google.protobuf.r0.a, com.google.protobuf.q0.a
            public final q0 build() {
                r rVarD = d();
                if (rVarD.isInitialized()) {
                    return rVarD;
                }
                throw a.AbstractC0042a.z(rVarD);
            }

            @Override // com.google.protobuf.a0.a, com.google.protobuf.q0.a, com.google.protobuf.t0
            public final n.a c() {
                return m.S;
            }

            @Override // com.google.protobuf.a0.a
            public final Object clone() {
                return (b) super.clone();
            }

            @Override // com.google.protobuf.a0.a, com.google.protobuf.q0.a
            public final q0.a f(n.f fVar, Object obj) {
                super.f(fVar, obj);
                return this;
            }

            @Override // com.google.protobuf.a0.a, com.google.protobuf.q0.a
            public final q0.a j0(j1 j1Var) {
                this.f = j1Var;
                H();
                return this;
            }

            @Override // com.google.protobuf.a.AbstractC0042a
            /* JADX INFO: renamed from: v */
            public final /* bridge */ /* synthetic */ a.AbstractC0042a L(com.google.protobuf.g gVar, com.google.protobuf.s sVar) throws Throwable {
                N(gVar, sVar);
                return this;
            }

            @Override // com.google.protobuf.a.AbstractC0042a
            /* JADX INFO: renamed from: x */
            public final a.AbstractC0042a R(q0 q0Var) {
                if (q0Var instanceof r) {
                    M((r) q0Var);
                } else {
                    super.R(q0Var);
                }
                return this;
            }

            @Override // com.google.protobuf.a0.a, com.google.protobuf.a.AbstractC0042a
            public final void y(j1 j1Var) {
            }

            @Override // defpackage.bh1, com.google.protobuf.t0
            public final r0 b() {
                return r.i;
            }

            @Override // com.google.protobuf.r0.a, com.google.protobuf.q0.a
            public final r0 build() {
                r rVarD = d();
                if (rVarD.isInitialized()) {
                    return rVarD;
                }
                throw a.AbstractC0042a.z(rVarD);
            }
        }

        /* JADX INFO: compiled from: DescriptorProtos.java */
        public static final class c extends a0 implements t0 {
            public static final c p = new c();

            @Deprecated
            public static final a q = new a();
            private static final long serialVersionUID = 0;
            public int g;
            public c0.c h;
            public int i;
            public c0.c j;
            public int k;
            public volatile Object l;
            public volatile Object m;
            public q61 n;
            public byte o;

            /* JADX INFO: compiled from: DescriptorProtos.java */
            public static class a extends com.google.protobuf.c<c> {
                @Override // defpackage.wv1
                public final Object b(com.google.protobuf.g gVar, com.google.protobuf.s sVar) {
                    c cVar = new c();
                    sVar.getClass();
                    j1 j1Var = j1.f;
                    j1.a aVar = new j1.a();
                    boolean z = false;
                    int i = 0;
                    while (!z) {
                        try {
                            try {
                                int iF = gVar.F();
                                if (iF != 0) {
                                    if (iF == 8) {
                                        if ((i & 1) == 0) {
                                            cVar.h = new b0();
                                            i |= 1;
                                        }
                                        ((b0) cVar.h).b(gVar.t());
                                    } else if (iF == 10) {
                                        int iK = gVar.k(gVar.y());
                                        if ((i & 1) == 0 && gVar.e() > 0) {
                                            cVar.h = new b0();
                                            i |= 1;
                                        }
                                        while (gVar.e() > 0) {
                                            ((b0) cVar.h).b(gVar.t());
                                        }
                                        gVar.j(iK);
                                    } else if (iF == 16) {
                                        if ((i & 2) == 0) {
                                            cVar.j = new b0();
                                            i |= 2;
                                        }
                                        ((b0) cVar.j).b(gVar.t());
                                    } else if (iF == 18) {
                                        int iK2 = gVar.k(gVar.y());
                                        if ((i & 2) == 0 && gVar.e() > 0) {
                                            cVar.j = new b0();
                                            i |= 2;
                                        }
                                        while (gVar.e() > 0) {
                                            ((b0) cVar.j).b(gVar.t());
                                        }
                                        gVar.j(iK2);
                                    } else if (iF == 26) {
                                        lk.f fVarM = gVar.m();
                                        cVar.g = 1 | cVar.g;
                                        cVar.l = fVarM;
                                    } else if (iF == 34) {
                                        lk.f fVarM2 = gVar.m();
                                        cVar.g |= 2;
                                        cVar.m = fVarM2;
                                    } else if (iF == 50) {
                                        lk.f fVarM3 = gVar.m();
                                        if ((i & 16) == 0) {
                                            cVar.n = new g0();
                                            i |= 16;
                                        }
                                        cVar.n.l(fVarM3);
                                    } else if (!aVar.r(iF, gVar)) {
                                    }
                                }
                                z = true;
                            } catch (d0 e) {
                                e.e = cVar;
                                throw e;
                            } catch (IOException e2) {
                                d0 d0Var = new d0(e2);
                                d0Var.e = cVar;
                                throw d0Var;
                            }
                        } catch (Throwable th) {
                            if ((i & 1) != 0) {
                                ((com.google.protobuf.d) cVar.h).e = false;
                            }
                            if ((i & 2) != 0) {
                                ((com.google.protobuf.d) cVar.j).e = false;
                            }
                            if ((i & 16) != 0) {
                                cVar.n = cVar.n.m();
                            }
                            cVar.f = aVar.build();
                            throw th;
                        }
                    }
                    if ((i & 1) != 0) {
                        ((com.google.protobuf.d) cVar.h).e = false;
                    }
                    if ((i & 2) != 0) {
                        ((com.google.protobuf.d) cVar.j).e = false;
                    }
                    if ((i & 16) != 0) {
                        cVar.n = cVar.n.m();
                    }
                    cVar.f = aVar.build();
                    return cVar;
                }
            }

            /* JADX INFO: compiled from: DescriptorProtos.java */
            public static final class b extends a0.a<b> implements t0 {
                public int g;
                public c0.c h;
                public c0.c i;
                public Object j;
                public Object k;
                public q61 l;

                public b() {
                    b0 b0Var = b0.h;
                    this.h = b0Var;
                    this.i = b0Var;
                    this.j = "";
                    this.k = "";
                    this.l = g0.g;
                }

                @Override // com.google.protobuf.a0.a
                /* JADX INFO: renamed from: A */
                public final a0.a f(n.f fVar, Object obj) {
                    super.f(fVar, obj);
                    return this;
                }

                @Override // com.google.protobuf.a0.a
                /* JADX INFO: renamed from: B */
                public final a0.a clone() {
                    return (b) super.clone();
                }

                @Override // com.google.protobuf.a0.a
                public final a0.e D() {
                    a0.e eVar = m.V;
                    eVar.c(c.class, b.class);
                    return eVar;
                }

                @Override // com.google.protobuf.a0.a
                /* JADX INFO: renamed from: E */
                public final a0.a y(j1 j1Var) {
                    return (b) super.y(j1Var);
                }

                @Override // com.google.protobuf.a0.a
                /* JADX INFO: renamed from: I */
                public final a0.a a(n.f fVar, Object obj) {
                    super.a(fVar, obj);
                    return this;
                }

                @Override // com.google.protobuf.a0.a
                /* JADX INFO: renamed from: J */
                public final a0.a j0(j1 j1Var) {
                    this.f = j1Var;
                    H();
                    return this;
                }

                @Override // com.google.protobuf.r0.a, com.google.protobuf.q0.a
                /* JADX INFO: renamed from: K, reason: merged with bridge method [inline-methods] */
                public final c d() {
                    c cVar = new c(this);
                    int i = this.g;
                    if ((i & 1) != 0) {
                        ((com.google.protobuf.d) this.h).e = false;
                        this.g = i & (-2);
                    }
                    cVar.h = this.h;
                    int i2 = this.g;
                    if ((i2 & 2) != 0) {
                        ((com.google.protobuf.d) this.i).e = false;
                        this.g = i2 & (-3);
                    }
                    cVar.j = this.i;
                    int i3 = (i & 4) != 0 ? 1 : 0;
                    cVar.l = this.j;
                    if ((i & 8) != 0) {
                        i3 |= 2;
                    }
                    cVar.m = this.k;
                    if ((this.g & 16) != 0) {
                        this.l = this.l.m();
                        this.g &= -17;
                    }
                    cVar.n = this.l;
                    cVar.g = i3;
                    F();
                    return cVar;
                }

                @Override // com.google.protobuf.a.AbstractC0042a, com.google.protobuf.r0.a
                public final /* bridge */ /* synthetic */ r0.a L(com.google.protobuf.g gVar, com.google.protobuf.s sVar) throws Throwable {
                    N(gVar, sVar);
                    return this;
                }

                public final void M(c cVar) {
                    if (cVar == c.p) {
                        return;
                    }
                    if (!cVar.h.isEmpty()) {
                        if (this.h.isEmpty()) {
                            this.h = cVar.h;
                            this.g &= -2;
                        } else {
                            if ((this.g & 1) == 0) {
                                this.h = a0.N(this.h);
                                this.g |= 1;
                            }
                            ((b0) this.h).addAll(cVar.h);
                        }
                        H();
                    }
                    if (!cVar.j.isEmpty()) {
                        if (this.i.isEmpty()) {
                            this.i = cVar.j;
                            this.g &= -3;
                        } else {
                            if ((this.g & 2) == 0) {
                                this.i = a0.N(this.i);
                                this.g |= 2;
                            }
                            ((b0) this.i).addAll(cVar.j);
                        }
                        H();
                    }
                    if ((cVar.g & 1) != 0) {
                        this.g |= 4;
                        this.j = cVar.l;
                        H();
                    }
                    if ((cVar.g & 2) != 0) {
                        this.g |= 8;
                        this.k = cVar.m;
                        H();
                    }
                    if (!cVar.n.isEmpty()) {
                        if (this.l.isEmpty()) {
                            this.l = cVar.n;
                            this.g &= -17;
                        } else {
                            if ((this.g & 16) == 0) {
                                this.l = new g0(this.l);
                                this.g |= 16;
                            }
                            this.l.addAll(cVar.n);
                        }
                        H();
                    }
                    H();
                }

                /* JADX WARN: Removed duplicated region for block: B:15:0x001d  */
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct code enable 'Show inconsistent code' option in preferences
                */
                public final void N(com.google.protobuf.g r2, com.google.protobuf.s r3) throws java.lang.Throwable {
                    /*
                        r1 = this;
                        com.google.protobuf.m$r$c$a r0 = com.google.protobuf.m.r.c.q     // Catch: java.lang.Throwable -> Lc com.google.protobuf.d0 -> Le
                        java.lang.Object r2 = r0.b(r2, r3)     // Catch: java.lang.Throwable -> Lc com.google.protobuf.d0 -> Le
                        com.google.protobuf.m$r$c r2 = (com.google.protobuf.m.r.c) r2     // Catch: java.lang.Throwable -> Lc com.google.protobuf.d0 -> Le
                        r1.M(r2)
                        return
                    Lc:
                        r2 = move-exception
                        goto L1a
                    Le:
                        r2 = move-exception
                        com.google.protobuf.r0 r3 = r2.e     // Catch: java.lang.Throwable -> Lc
                        com.google.protobuf.m$r$c r3 = (com.google.protobuf.m.r.c) r3     // Catch: java.lang.Throwable -> Lc
                        java.io.IOException r2 = r2.l()     // Catch: java.lang.Throwable -> L18
                        throw r2     // Catch: java.lang.Throwable -> L18
                    L18:
                        r2 = move-exception
                        goto L1b
                    L1a:
                        r3 = 0
                    L1b:
                        if (r3 == 0) goto L20
                        r1.M(r3)
                    L20:
                        throw r2
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.m.r.c.b.N(com.google.protobuf.g, com.google.protobuf.s):void");
                }

                @Override // com.google.protobuf.a.AbstractC0042a, com.google.protobuf.q0.a
                public final q0.a R(q0 q0Var) {
                    if (q0Var instanceof c) {
                        M((c) q0Var);
                    } else {
                        super.R(q0Var);
                    }
                    return this;
                }

                @Override // com.google.protobuf.a0.a, com.google.protobuf.q0.a
                public final q0.a a(n.f fVar, Object obj) {
                    super.a(fVar, obj);
                    return this;
                }

                @Override // defpackage.bh1, com.google.protobuf.t0
                public final q0 b() {
                    return c.p;
                }

                @Override // com.google.protobuf.r0.a, com.google.protobuf.q0.a
                public final q0 build() {
                    c cVarD = d();
                    if (cVarD.isInitialized()) {
                        return cVarD;
                    }
                    throw a.AbstractC0042a.z(cVarD);
                }

                @Override // com.google.protobuf.a0.a, com.google.protobuf.q0.a, com.google.protobuf.t0
                public final n.a c() {
                    return m.U;
                }

                @Override // com.google.protobuf.a0.a
                public final Object clone() {
                    return (b) super.clone();
                }

                @Override // com.google.protobuf.a0.a, com.google.protobuf.q0.a
                public final q0.a f(n.f fVar, Object obj) {
                    super.f(fVar, obj);
                    return this;
                }

                @Override // com.google.protobuf.a0.a, com.google.protobuf.q0.a
                public final q0.a j0(j1 j1Var) {
                    this.f = j1Var;
                    H();
                    return this;
                }

                @Override // com.google.protobuf.a.AbstractC0042a
                /* JADX INFO: renamed from: v */
                public final /* bridge */ /* synthetic */ a.AbstractC0042a L(com.google.protobuf.g gVar, com.google.protobuf.s sVar) throws Throwable {
                    N(gVar, sVar);
                    return this;
                }

                @Override // com.google.protobuf.a.AbstractC0042a
                /* JADX INFO: renamed from: x */
                public final a.AbstractC0042a R(q0 q0Var) {
                    if (q0Var instanceof c) {
                        M((c) q0Var);
                    } else {
                        super.R(q0Var);
                    }
                    return this;
                }

                @Override // com.google.protobuf.a0.a, com.google.protobuf.a.AbstractC0042a
                public final void y(j1 j1Var) {
                }

                @Override // defpackage.bh1, com.google.protobuf.t0
                public final r0 b() {
                    return c.p;
                }

                @Override // com.google.protobuf.r0.a, com.google.protobuf.q0.a
                public final r0 build() {
                    c cVarD = d();
                    if (cVarD.isInitialized()) {
                        return cVarD;
                    }
                    throw a.AbstractC0042a.z(cVarD);
                }
            }

            public c(a0.a aVar) {
                super(aVar);
                this.i = -1;
                this.k = -1;
                this.o = (byte) -1;
            }

            @Override // com.google.protobuf.a0
            public final a0.e M() {
                a0.e eVar = m.V;
                eVar.c(c.class, b.class);
                return eVar;
            }

            @Override // com.google.protobuf.a0
            public final Object O() {
                return new c();
            }

            public final String Q() {
                Object obj = this.l;
                if (obj instanceof String) {
                    return (String) obj;
                }
                lk lkVar = (lk) obj;
                String strZ = lkVar.z();
                if (lkVar.q()) {
                    this.l = strZ;
                }
                return strZ;
            }

            public final String S() {
                Object obj = this.m;
                if (obj instanceof String) {
                    return (String) obj;
                }
                lk lkVar = (lk) obj;
                String strZ = lkVar.z();
                if (lkVar.q()) {
                    this.m = strZ;
                }
                return strZ;
            }

            @Override // com.google.protobuf.r0
            /* JADX INFO: renamed from: T, reason: merged with bridge method [inline-methods] */
            public final b h() {
                if (this == p) {
                    return new b();
                }
                b bVar = new b();
                bVar.M(this);
                return bVar;
            }

            @Override // defpackage.bh1, com.google.protobuf.t0
            public final q0 b() {
                return p;
            }

            @Override // com.google.protobuf.r0, com.google.protobuf.q0
            public final q0.a e() {
                return p.h();
            }

            @Override // com.google.protobuf.a
            public final boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof c)) {
                    return super.equals(obj);
                }
                c cVar = (c) obj;
                if (!((b0) this.h).equals(cVar.h)) {
                    return false;
                }
                if (!((b0) this.j).equals(cVar.j)) {
                    return false;
                }
                int i = this.g;
                if (((i & 1) != 0) != ((cVar.g & 1) != 0)) {
                    return false;
                }
                if (((i & 1) != 0) && !Q().equals(cVar.Q())) {
                    return false;
                }
                int i2 = this.g;
                if (((i2 & 2) != 0) != ((cVar.g & 2) != 0)) {
                    return false;
                }
                return (!((i2 & 2) != 0) || S().equals(cVar.S())) && this.n.equals(cVar.n) && this.f.equals(cVar.f);
            }

            @Override // com.google.protobuf.a
            public final int hashCode() {
                int i = this.memoizedHashCode;
                if (i != 0) {
                    return i;
                }
                int iHashCode = m.U.hashCode() + 779;
                c0.c cVar = this.h;
                if (((b0) cVar).g > 0) {
                    iHashCode = C0175b.e(iHashCode, 37, 1, 53) + ((b0) cVar).hashCode();
                }
                c0.c cVar2 = this.j;
                if (((b0) cVar2).g > 0) {
                    iHashCode = C0175b.e(iHashCode, 37, 2, 53) + ((b0) cVar2).hashCode();
                }
                if ((this.g & 1) != 0) {
                    iHashCode = C0175b.e(iHashCode, 37, 3, 53) + Q().hashCode();
                }
                if ((this.g & 2) != 0) {
                    iHashCode = C0175b.e(iHashCode, 37, 4, 53) + S().hashCode();
                }
                if (this.n.size() > 0) {
                    iHashCode = C0175b.e(iHashCode, 37, 6, 53) + this.n.hashCode();
                }
                int iHashCode2 = this.f.hashCode() + (iHashCode * 29);
                this.memoizedHashCode = iHashCode2;
                return iHashCode2;
            }

            @Override // com.google.protobuf.a0, defpackage.bh1
            public final boolean isInitialized() {
                byte b2 = this.o;
                if (b2 == 1) {
                    return true;
                }
                if (b2 == 0) {
                    return false;
                }
                this.o = (byte) 1;
                return true;
            }

            @Override // com.google.protobuf.a0, com.google.protobuf.r0
            public final int m() {
                c0.c cVar;
                c0.c cVar2;
                int i = this.e;
                if (i != -1) {
                    return i;
                }
                int i2 = 0;
                int iT = 0;
                while (true) {
                    cVar = this.h;
                    if (i2 >= ((b0) cVar).g) {
                        break;
                    }
                    iT += nu.T(((b0) cVar).d(i2));
                    i2++;
                }
                int iT2 = 0 + iT;
                if (!cVar.isEmpty()) {
                    iT2 = iT2 + 1 + nu.T(iT);
                }
                this.i = iT;
                int i3 = 0;
                int iT3 = 0;
                while (true) {
                    cVar2 = this.j;
                    if (i3 >= ((b0) cVar2).g) {
                        break;
                    }
                    iT3 += nu.T(((b0) cVar2).d(i3));
                    i3++;
                }
                int iH = iT2 + iT3;
                if (!cVar2.isEmpty()) {
                    iH = iH + 1 + nu.T(iT3);
                }
                this.k = iT3;
                if ((this.g & 1) != 0) {
                    iH += a0.H(3, this.l);
                }
                if ((this.g & 2) != 0) {
                    iH += a0.H(4, this.m);
                }
                int I = 0;
                for (int i4 = 0; i4 < this.n.size(); i4++) {
                    I += a0.I(this.n.n(i4));
                }
                int iM = this.f.m() + (this.n.size() * 1) + iH + I;
                this.e = iM;
                return iM;
            }

            @Override // com.google.protobuf.a0, com.google.protobuf.t0
            public final j1 n() {
                return this.f;
            }

            @Override // com.google.protobuf.a0, com.google.protobuf.r0
            public final void p(nu nuVar) {
                m();
                if (((b0) this.h).g > 0) {
                    nuVar.O0(10);
                    nuVar.O0(this.i);
                }
                int i = 0;
                while (true) {
                    c0.c cVar = this.h;
                    if (i >= ((b0) cVar).g) {
                        break;
                    }
                    nuVar.C0(((b0) cVar).d(i));
                    i++;
                }
                if (((b0) this.j).g > 0) {
                    nuVar.O0(18);
                    nuVar.O0(this.k);
                }
                int i2 = 0;
                while (true) {
                    c0.c cVar2 = this.j;
                    if (i2 >= ((b0) cVar2).g) {
                        break;
                    }
                    nuVar.C0(((b0) cVar2).d(i2));
                    i2++;
                }
                if ((this.g & 1) != 0) {
                    a0.P(nuVar, 3, this.l);
                }
                if ((this.g & 2) != 0) {
                    a0.P(nuVar, 4, this.m);
                }
                for (int i3 = 0; i3 < this.n.size(); i3++) {
                    a0.P(nuVar, 6, this.n.n(i3));
                }
                this.f.p(nuVar);
            }

            @Override // com.google.protobuf.a0, com.google.protobuf.r0
            public final wv1<c> u() {
                return q;
            }

            @Override // defpackage.bh1, com.google.protobuf.t0
            public final r0 b() {
                return p;
            }

            @Override // com.google.protobuf.r0, com.google.protobuf.q0
            public final r0.a e() {
                return p.h();
            }

            public c() {
                this.i = -1;
                this.k = -1;
                this.o = (byte) -1;
                b0 b0Var = b0.h;
                this.h = b0Var;
                this.j = b0Var;
                this.l = "";
                this.m = "";
                this.n = g0.g;
            }
        }

        public r(a0.a aVar) {
            super(aVar);
            this.h = (byte) -1;
        }

        @Override // com.google.protobuf.a0
        public final a0.e M() {
            a0.e eVar = m.T;
            eVar.c(r.class, b.class);
            return eVar;
        }

        @Override // com.google.protobuf.a0
        public final Object O() {
            return new r();
        }

        @Override // com.google.protobuf.r0
        /* JADX INFO: renamed from: Q, reason: merged with bridge method [inline-methods] */
        public final b h() {
            if (this == i) {
                return new b();
            }
            b bVar = new b();
            bVar.M(this);
            return bVar;
        }

        @Override // defpackage.bh1, com.google.protobuf.t0
        public final q0 b() {
            return i;
        }

        @Override // com.google.protobuf.r0, com.google.protobuf.q0
        public final q0.a e() {
            return i.h();
        }

        @Override // com.google.protobuf.a
        public final boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof r)) {
                return super.equals(obj);
            }
            r rVar = (r) obj;
            return this.g.equals(rVar.g) && this.f.equals(rVar.f);
        }

        @Override // com.google.protobuf.a
        public final int hashCode() {
            int i2 = this.memoizedHashCode;
            if (i2 != 0) {
                return i2;
            }
            int iHashCode = m.S.hashCode() + 779;
            if (this.g.size() > 0) {
                iHashCode = C0175b.e(iHashCode, 37, 1, 53) + this.g.hashCode();
            }
            int iHashCode2 = this.f.hashCode() + (iHashCode * 29);
            this.memoizedHashCode = iHashCode2;
            return iHashCode2;
        }

        @Override // com.google.protobuf.a0, defpackage.bh1
        public final boolean isInitialized() {
            byte b2 = this.h;
            if (b2 == 1) {
                return true;
            }
            if (b2 == 0) {
                return false;
            }
            this.h = (byte) 1;
            return true;
        }

        @Override // com.google.protobuf.a0, com.google.protobuf.r0
        public final int m() {
            int i2 = this.e;
            if (i2 != -1) {
                return i2;
            }
            int iA0 = 0;
            for (int i3 = 0; i3 < this.g.size(); i3++) {
                iA0 += nu.a0(1, this.g.get(i3));
            }
            int iM = this.f.m() + iA0;
            this.e = iM;
            return iM;
        }

        @Override // com.google.protobuf.a0, com.google.protobuf.t0
        public final j1 n() {
            return this.f;
        }

        @Override // com.google.protobuf.a0, com.google.protobuf.r0
        public final void p(nu nuVar) {
            for (int i2 = 0; i2 < this.g.size(); i2++) {
                nuVar.D0(1, this.g.get(i2));
            }
            this.f.p(nuVar);
        }

        @Override // com.google.protobuf.a0, com.google.protobuf.r0
        public final wv1<r> u() {
            return j;
        }

        @Override // defpackage.bh1, com.google.protobuf.t0
        public final r0 b() {
            return i;
        }

        @Override // com.google.protobuf.r0, com.google.protobuf.q0
        public final r0.a e() {
            return i.h();
        }

        public r() {
            this.h = (byte) -1;
            this.g = Collections.emptyList();
        }
    }

    /* JADX INFO: compiled from: DescriptorProtos.java */
    public static final class s extends a0 implements t0 {
        public static final s p = new s();

        @Deprecated
        public static final a q = new a();
        private static final long serialVersionUID = 0;
        public int g;
        public List<c> h;
        public volatile Object i;
        public long j;
        public long k;
        public double l;
        public lk m;
        public volatile Object n;
        public byte o;

        /* JADX INFO: compiled from: DescriptorProtos.java */
        public static class a extends com.google.protobuf.c<s> {
            @Override // defpackage.wv1
            public final Object b(com.google.protobuf.g gVar, com.google.protobuf.s sVar) {
                s sVar2 = new s();
                sVar.getClass();
                j1 j1Var = j1.f;
                j1.a aVar = new j1.a();
                boolean z = false;
                boolean z2 = false;
                while (!z) {
                    try {
                        try {
                            int iF = gVar.F();
                            if (iF != 0) {
                                if (iF == 18) {
                                    if (!(z2 & true)) {
                                        sVar2.h = new ArrayList();
                                        z2 |= true;
                                    }
                                    sVar2.h.add((c) gVar.v(c.l, sVar));
                                } else if (iF == 26) {
                                    lk.f fVarM = gVar.m();
                                    sVar2.g |= 1;
                                    sVar2.i = fVarM;
                                } else if (iF == 32) {
                                    sVar2.g |= 2;
                                    sVar2.j = gVar.H();
                                } else if (iF == 40) {
                                    sVar2.g |= 4;
                                    sVar2.k = gVar.u();
                                } else if (iF == 49) {
                                    sVar2.g |= 8;
                                    sVar2.l = gVar.n();
                                } else if (iF == 58) {
                                    sVar2.g |= 16;
                                    sVar2.m = gVar.m();
                                } else if (iF == 66) {
                                    lk.f fVarM2 = gVar.m();
                                    sVar2.g = 32 | sVar2.g;
                                    sVar2.n = fVarM2;
                                } else if (!aVar.r(iF, gVar)) {
                                }
                            }
                            z = true;
                        } catch (d0 e) {
                            e.e = sVar2;
                            throw e;
                        } catch (IOException e2) {
                            d0 d0Var = new d0(e2);
                            d0Var.e = sVar2;
                            throw d0Var;
                        }
                    } catch (Throwable th) {
                        if (z2 & true) {
                            sVar2.h = Collections.unmodifiableList(sVar2.h);
                        }
                        sVar2.f = aVar.build();
                        throw th;
                    }
                }
                if (z2 & true) {
                    sVar2.h = Collections.unmodifiableList(sVar2.h);
                }
                sVar2.f = aVar.build();
                return sVar2;
            }
        }

        /* JADX INFO: compiled from: DescriptorProtos.java */
        public static final class b extends a0.a<b> implements t0 {
            public int g;
            public long j;
            public long k;
            public double l;
            public List<c> h = Collections.emptyList();
            public Object i = "";
            public lk m = lk.f;
            public Object n = "";

            @Override // com.google.protobuf.a0.a
            /* JADX INFO: renamed from: A */
            public final a0.a f(n.f fVar, Object obj) {
                super.f(fVar, obj);
                return this;
            }

            @Override // com.google.protobuf.a0.a
            /* JADX INFO: renamed from: B */
            public final a0.a clone() {
                return (b) super.clone();
            }

            @Override // com.google.protobuf.a0.a
            public final a0.e D() {
                a0.e eVar = m.P;
                eVar.c(s.class, b.class);
                return eVar;
            }

            @Override // com.google.protobuf.a0.a
            /* JADX INFO: renamed from: E */
            public final a0.a y(j1 j1Var) {
                return (b) super.y(j1Var);
            }

            @Override // com.google.protobuf.a0.a
            /* JADX INFO: renamed from: I */
            public final a0.a a(n.f fVar, Object obj) {
                super.a(fVar, obj);
                return this;
            }

            @Override // com.google.protobuf.a0.a
            /* JADX INFO: renamed from: J */
            public final a0.a j0(j1 j1Var) {
                this.f = j1Var;
                H();
                return this;
            }

            @Override // com.google.protobuf.r0.a, com.google.protobuf.q0.a
            /* JADX INFO: renamed from: K, reason: merged with bridge method [inline-methods] */
            public final s d() {
                s sVar = new s(this);
                int i = this.g;
                if ((i & 1) != 0) {
                    this.h = Collections.unmodifiableList(this.h);
                    this.g &= -2;
                }
                sVar.h = this.h;
                int i2 = (i & 2) != 0 ? 1 : 0;
                sVar.i = this.i;
                if ((i & 4) != 0) {
                    sVar.j = this.j;
                    i2 |= 2;
                }
                if ((i & 8) != 0) {
                    sVar.k = this.k;
                    i2 |= 4;
                }
                if ((i & 16) != 0) {
                    sVar.l = this.l;
                    i2 |= 8;
                }
                if ((i & 32) != 0) {
                    i2 |= 16;
                }
                sVar.m = this.m;
                if ((i & 64) != 0) {
                    i2 |= 32;
                }
                sVar.n = this.n;
                sVar.g = i2;
                F();
                return sVar;
            }

            @Override // com.google.protobuf.a.AbstractC0042a, com.google.protobuf.r0.a
            public final /* bridge */ /* synthetic */ r0.a L(com.google.protobuf.g gVar, com.google.protobuf.s sVar) throws Throwable {
                N(gVar, sVar);
                return this;
            }

            public final void M(s sVar) {
                if (sVar == s.p) {
                    return;
                }
                if (!sVar.h.isEmpty()) {
                    if (this.h.isEmpty()) {
                        this.h = sVar.h;
                        this.g &= -2;
                    } else {
                        if ((this.g & 1) == 0) {
                            this.h = new ArrayList(this.h);
                            this.g |= 1;
                        }
                        this.h.addAll(sVar.h);
                    }
                    H();
                }
                if ((sVar.g & 1) != 0) {
                    this.g |= 2;
                    this.i = sVar.i;
                    H();
                }
                if ((sVar.g & 2) != 0) {
                    long j = sVar.j;
                    this.g |= 4;
                    this.j = j;
                    H();
                }
                if ((sVar.g & 4) != 0) {
                    long j2 = sVar.k;
                    this.g |= 8;
                    this.k = j2;
                    H();
                }
                if ((sVar.g & 8) != 0) {
                    double d = sVar.l;
                    this.g |= 16;
                    this.l = d;
                    H();
                }
                if ((sVar.g & 16) != 0) {
                    lk lkVar = sVar.m;
                    lkVar.getClass();
                    this.g |= 32;
                    this.m = lkVar;
                    H();
                }
                if ((sVar.g & 32) != 0) {
                    this.g |= 64;
                    this.n = sVar.n;
                    H();
                }
                H();
            }

            /* JADX WARN: Removed duplicated region for block: B:15:0x001d  */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public final void N(com.google.protobuf.g r2, com.google.protobuf.s r3) throws java.lang.Throwable {
                /*
                    r1 = this;
                    com.google.protobuf.m$s$a r0 = com.google.protobuf.m.s.q     // Catch: java.lang.Throwable -> Lc com.google.protobuf.d0 -> Le
                    java.lang.Object r2 = r0.b(r2, r3)     // Catch: java.lang.Throwable -> Lc com.google.protobuf.d0 -> Le
                    com.google.protobuf.m$s r2 = (com.google.protobuf.m.s) r2     // Catch: java.lang.Throwable -> Lc com.google.protobuf.d0 -> Le
                    r1.M(r2)
                    return
                Lc:
                    r2 = move-exception
                    goto L1a
                Le:
                    r2 = move-exception
                    com.google.protobuf.r0 r3 = r2.e     // Catch: java.lang.Throwable -> Lc
                    com.google.protobuf.m$s r3 = (com.google.protobuf.m.s) r3     // Catch: java.lang.Throwable -> Lc
                    java.io.IOException r2 = r2.l()     // Catch: java.lang.Throwable -> L18
                    throw r2     // Catch: java.lang.Throwable -> L18
                L18:
                    r2 = move-exception
                    goto L1b
                L1a:
                    r3 = 0
                L1b:
                    if (r3 == 0) goto L20
                    r1.M(r3)
                L20:
                    throw r2
                */
                throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.m.s.b.N(com.google.protobuf.g, com.google.protobuf.s):void");
            }

            @Override // com.google.protobuf.a.AbstractC0042a, com.google.protobuf.q0.a
            public final q0.a R(q0 q0Var) {
                if (q0Var instanceof s) {
                    M((s) q0Var);
                } else {
                    super.R(q0Var);
                }
                return this;
            }

            @Override // com.google.protobuf.a0.a, com.google.protobuf.q0.a
            public final q0.a a(n.f fVar, Object obj) {
                super.a(fVar, obj);
                return this;
            }

            @Override // defpackage.bh1, com.google.protobuf.t0
            public final q0 b() {
                return s.p;
            }

            @Override // com.google.protobuf.r0.a, com.google.protobuf.q0.a
            public final q0 build() {
                s sVarD = d();
                if (sVarD.isInitialized()) {
                    return sVarD;
                }
                throw a.AbstractC0042a.z(sVarD);
            }

            @Override // com.google.protobuf.a0.a, com.google.protobuf.q0.a, com.google.protobuf.t0
            public final n.a c() {
                return m.O;
            }

            @Override // com.google.protobuf.a0.a
            public final Object clone() {
                return (b) super.clone();
            }

            @Override // com.google.protobuf.a0.a, com.google.protobuf.q0.a
            public final q0.a f(n.f fVar, Object obj) {
                super.f(fVar, obj);
                return this;
            }

            @Override // com.google.protobuf.a0.a, com.google.protobuf.q0.a
            public final q0.a j0(j1 j1Var) {
                this.f = j1Var;
                H();
                return this;
            }

            @Override // com.google.protobuf.a.AbstractC0042a
            /* JADX INFO: renamed from: v */
            public final /* bridge */ /* synthetic */ a.AbstractC0042a L(com.google.protobuf.g gVar, com.google.protobuf.s sVar) throws Throwable {
                N(gVar, sVar);
                return this;
            }

            @Override // com.google.protobuf.a.AbstractC0042a
            /* JADX INFO: renamed from: x */
            public final a.AbstractC0042a R(q0 q0Var) {
                if (q0Var instanceof s) {
                    M((s) q0Var);
                } else {
                    super.R(q0Var);
                }
                return this;
            }

            @Override // com.google.protobuf.a0.a, com.google.protobuf.a.AbstractC0042a
            public final void y(j1 j1Var) {
            }

            @Override // defpackage.bh1, com.google.protobuf.t0
            public final r0 b() {
                return s.p;
            }

            @Override // com.google.protobuf.r0.a, com.google.protobuf.q0.a
            public final r0 build() {
                s sVarD = d();
                if (sVarD.isInitialized()) {
                    return sVarD;
                }
                throw a.AbstractC0042a.z(sVarD);
            }
        }

        /* JADX INFO: compiled from: DescriptorProtos.java */
        public static final class c extends a0 implements t0 {
            public static final c k = new c();

            @Deprecated
            public static final a l = new a();
            private static final long serialVersionUID = 0;
            public int g;
            public volatile Object h;
            public boolean i;
            public byte j;

            /* JADX INFO: compiled from: DescriptorProtos.java */
            public static class a extends com.google.protobuf.c<c> {
                @Override // defpackage.wv1
                public final Object b(com.google.protobuf.g gVar, com.google.protobuf.s sVar) {
                    c cVar = new c();
                    sVar.getClass();
                    j1 j1Var = j1.f;
                    j1.a aVar = new j1.a();
                    boolean z = false;
                    while (!z) {
                        try {
                            try {
                                int iF = gVar.F();
                                if (iF != 0) {
                                    if (iF == 10) {
                                        lk.f fVarM = gVar.m();
                                        cVar.g = 1 | cVar.g;
                                        cVar.h = fVarM;
                                    } else if (iF == 16) {
                                        cVar.g |= 2;
                                        cVar.i = gVar.l();
                                    } else if (!aVar.r(iF, gVar)) {
                                    }
                                }
                                z = true;
                            } catch (d0 e) {
                                e.e = cVar;
                                throw e;
                            } catch (IOException e2) {
                                d0 d0Var = new d0(e2);
                                d0Var.e = cVar;
                                throw d0Var;
                            }
                        } catch (Throwable th) {
                            cVar.f = aVar.build();
                            throw th;
                        }
                    }
                    cVar.f = aVar.build();
                    return cVar;
                }
            }

            /* JADX INFO: compiled from: DescriptorProtos.java */
            public static final class b extends a0.a<b> implements t0 {
                public int g;
                public Object h = "";
                public boolean i;

                @Override // com.google.protobuf.a0.a
                /* JADX INFO: renamed from: A */
                public final a0.a f(n.f fVar, Object obj) {
                    super.f(fVar, obj);
                    return this;
                }

                @Override // com.google.protobuf.a0.a
                /* JADX INFO: renamed from: B */
                public final a0.a clone() {
                    return (b) super.clone();
                }

                @Override // com.google.protobuf.a0.a
                public final a0.e D() {
                    a0.e eVar = m.R;
                    eVar.c(c.class, b.class);
                    return eVar;
                }

                @Override // com.google.protobuf.a0.a
                /* JADX INFO: renamed from: E */
                public final a0.a y(j1 j1Var) {
                    return (b) super.y(j1Var);
                }

                @Override // com.google.protobuf.a0.a
                /* JADX INFO: renamed from: I */
                public final a0.a a(n.f fVar, Object obj) {
                    super.a(fVar, obj);
                    return this;
                }

                @Override // com.google.protobuf.a0.a
                /* JADX INFO: renamed from: J */
                public final a0.a j0(j1 j1Var) {
                    this.f = j1Var;
                    H();
                    return this;
                }

                @Override // com.google.protobuf.r0.a, com.google.protobuf.q0.a
                /* JADX INFO: renamed from: K, reason: merged with bridge method [inline-methods] */
                public final c d() {
                    c cVar = new c(this);
                    int i = this.g;
                    int i2 = (i & 1) != 0 ? 1 : 0;
                    cVar.h = this.h;
                    if ((i & 2) != 0) {
                        cVar.i = this.i;
                        i2 |= 2;
                    }
                    cVar.g = i2;
                    F();
                    return cVar;
                }

                @Override // com.google.protobuf.a.AbstractC0042a, com.google.protobuf.r0.a
                public final /* bridge */ /* synthetic */ r0.a L(com.google.protobuf.g gVar, com.google.protobuf.s sVar) throws Throwable {
                    N(gVar, sVar);
                    return this;
                }

                public final void M(c cVar) {
                    if (cVar == c.k) {
                        return;
                    }
                    if (cVar.T()) {
                        this.g |= 1;
                        this.h = cVar.h;
                        H();
                    }
                    if (cVar.S()) {
                        boolean z = cVar.i;
                        this.g |= 2;
                        this.i = z;
                        H();
                    }
                    H();
                }

                /* JADX WARN: Removed duplicated region for block: B:15:0x001d  */
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct code enable 'Show inconsistent code' option in preferences
                */
                public final void N(com.google.protobuf.g r2, com.google.protobuf.s r3) throws java.lang.Throwable {
                    /*
                        r1 = this;
                        com.google.protobuf.m$s$c$a r0 = com.google.protobuf.m.s.c.l     // Catch: java.lang.Throwable -> Lc com.google.protobuf.d0 -> Le
                        java.lang.Object r2 = r0.b(r2, r3)     // Catch: java.lang.Throwable -> Lc com.google.protobuf.d0 -> Le
                        com.google.protobuf.m$s$c r2 = (com.google.protobuf.m.s.c) r2     // Catch: java.lang.Throwable -> Lc com.google.protobuf.d0 -> Le
                        r1.M(r2)
                        return
                    Lc:
                        r2 = move-exception
                        goto L1a
                    Le:
                        r2 = move-exception
                        com.google.protobuf.r0 r3 = r2.e     // Catch: java.lang.Throwable -> Lc
                        com.google.protobuf.m$s$c r3 = (com.google.protobuf.m.s.c) r3     // Catch: java.lang.Throwable -> Lc
                        java.io.IOException r2 = r2.l()     // Catch: java.lang.Throwable -> L18
                        throw r2     // Catch: java.lang.Throwable -> L18
                    L18:
                        r2 = move-exception
                        goto L1b
                    L1a:
                        r3 = 0
                    L1b:
                        if (r3 == 0) goto L20
                        r1.M(r3)
                    L20:
                        throw r2
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.m.s.c.b.N(com.google.protobuf.g, com.google.protobuf.s):void");
                }

                @Override // com.google.protobuf.a.AbstractC0042a, com.google.protobuf.q0.a
                public final q0.a R(q0 q0Var) {
                    if (q0Var instanceof c) {
                        M((c) q0Var);
                    } else {
                        super.R(q0Var);
                    }
                    return this;
                }

                @Override // com.google.protobuf.a0.a, com.google.protobuf.q0.a
                public final q0.a a(n.f fVar, Object obj) {
                    super.a(fVar, obj);
                    return this;
                }

                @Override // defpackage.bh1, com.google.protobuf.t0
                public final q0 b() {
                    return c.k;
                }

                @Override // com.google.protobuf.r0.a, com.google.protobuf.q0.a
                public final q0 build() {
                    c cVarD = d();
                    if (cVarD.isInitialized()) {
                        return cVarD;
                    }
                    throw a.AbstractC0042a.z(cVarD);
                }

                @Override // com.google.protobuf.a0.a, com.google.protobuf.q0.a, com.google.protobuf.t0
                public final n.a c() {
                    return m.Q;
                }

                @Override // com.google.protobuf.a0.a
                public final Object clone() {
                    return (b) super.clone();
                }

                @Override // com.google.protobuf.a0.a, com.google.protobuf.q0.a
                public final q0.a f(n.f fVar, Object obj) {
                    super.f(fVar, obj);
                    return this;
                }

                @Override // com.google.protobuf.a0.a, com.google.protobuf.q0.a
                public final q0.a j0(j1 j1Var) {
                    this.f = j1Var;
                    H();
                    return this;
                }

                @Override // com.google.protobuf.a.AbstractC0042a
                /* JADX INFO: renamed from: v */
                public final /* bridge */ /* synthetic */ a.AbstractC0042a L(com.google.protobuf.g gVar, com.google.protobuf.s sVar) throws Throwable {
                    N(gVar, sVar);
                    return this;
                }

                @Override // com.google.protobuf.a.AbstractC0042a
                /* JADX INFO: renamed from: x */
                public final a.AbstractC0042a R(q0 q0Var) {
                    if (q0Var instanceof c) {
                        M((c) q0Var);
                    } else {
                        super.R(q0Var);
                    }
                    return this;
                }

                @Override // com.google.protobuf.a0.a, com.google.protobuf.a.AbstractC0042a
                public final void y(j1 j1Var) {
                }

                @Override // defpackage.bh1, com.google.protobuf.t0
                public final r0 b() {
                    return c.k;
                }

                @Override // com.google.protobuf.r0.a, com.google.protobuf.q0.a
                public final r0 build() {
                    c cVarD = d();
                    if (cVarD.isInitialized()) {
                        return cVarD;
                    }
                    throw a.AbstractC0042a.z(cVarD);
                }
            }

            public c(a0.a aVar) {
                super(aVar);
                this.j = (byte) -1;
            }

            @Override // com.google.protobuf.a0
            public final a0.e M() {
                a0.e eVar = m.R;
                eVar.c(c.class, b.class);
                return eVar;
            }

            @Override // com.google.protobuf.a0
            public final Object O() {
                return new c();
            }

            public final String Q() {
                Object obj = this.h;
                if (obj instanceof String) {
                    return (String) obj;
                }
                lk lkVar = (lk) obj;
                String strZ = lkVar.z();
                if (lkVar.q()) {
                    this.h = strZ;
                }
                return strZ;
            }

            public final boolean S() {
                return (this.g & 2) != 0;
            }

            public final boolean T() {
                return (this.g & 1) != 0;
            }

            @Override // com.google.protobuf.r0
            /* JADX INFO: renamed from: U, reason: merged with bridge method [inline-methods] */
            public final b h() {
                if (this == k) {
                    return new b();
                }
                b bVar = new b();
                bVar.M(this);
                return bVar;
            }

            @Override // defpackage.bh1, com.google.protobuf.t0
            public final q0 b() {
                return k;
            }

            @Override // com.google.protobuf.r0, com.google.protobuf.q0
            public final q0.a e() {
                return k.h();
            }

            @Override // com.google.protobuf.a
            public final boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof c)) {
                    return super.equals(obj);
                }
                c cVar = (c) obj;
                if (T() != cVar.T()) {
                    return false;
                }
                if ((!T() || Q().equals(cVar.Q())) && S() == cVar.S()) {
                    return (!S() || this.i == cVar.i) && this.f.equals(cVar.f);
                }
                return false;
            }

            @Override // com.google.protobuf.a
            public final int hashCode() {
                int i = this.memoizedHashCode;
                if (i != 0) {
                    return i;
                }
                int iHashCode = m.Q.hashCode() + 779;
                if (T()) {
                    iHashCode = C0175b.e(iHashCode, 37, 1, 53) + Q().hashCode();
                }
                if (S()) {
                    iHashCode = C0175b.e(iHashCode, 37, 2, 53) + c0.a(this.i);
                }
                int iHashCode2 = this.f.hashCode() + (iHashCode * 29);
                this.memoizedHashCode = iHashCode2;
                return iHashCode2;
            }

            @Override // com.google.protobuf.a0, defpackage.bh1
            public final boolean isInitialized() {
                byte b2 = this.j;
                if (b2 == 1) {
                    return true;
                }
                if (b2 == 0) {
                    return false;
                }
                if (!T()) {
                    this.j = (byte) 0;
                    return false;
                }
                if (S()) {
                    this.j = (byte) 1;
                    return true;
                }
                this.j = (byte) 0;
                return false;
            }

            @Override // com.google.protobuf.a0, com.google.protobuf.r0
            public final int m() {
                int i = this.e;
                if (i != -1) {
                    return i;
                }
                int iH = (this.g & 1) != 0 ? 0 + a0.H(1, this.h) : 0;
                if ((this.g & 2) != 0) {
                    iH += nu.J(2);
                }
                int iM = this.f.m() + iH;
                this.e = iM;
                return iM;
            }

            @Override // com.google.protobuf.a0, com.google.protobuf.t0
            public final j1 n() {
                return this.f;
            }

            @Override // com.google.protobuf.a0, com.google.protobuf.r0
            public final void p(nu nuVar) {
                if ((this.g & 1) != 0) {
                    a0.P(nuVar, 1, this.h);
                }
                if ((this.g & 2) != 0) {
                    nuVar.p0(2, this.i);
                }
                this.f.p(nuVar);
            }

            @Override // com.google.protobuf.a0, com.google.protobuf.r0
            public final wv1<c> u() {
                return l;
            }

            @Override // defpackage.bh1, com.google.protobuf.t0
            public final r0 b() {
                return k;
            }

            @Override // com.google.protobuf.r0, com.google.protobuf.q0
            public final r0.a e() {
                return k.h();
            }

            public c() {
                this.j = (byte) -1;
                this.h = "";
            }
        }

        public s(a0.a aVar) {
            super(aVar);
            this.o = (byte) -1;
        }

        @Override // com.google.protobuf.a0
        public final a0.e M() {
            a0.e eVar = m.P;
            eVar.c(s.class, b.class);
            return eVar;
        }

        @Override // com.google.protobuf.a0
        public final Object O() {
            return new s();
        }

        public final String Q() {
            Object obj = this.n;
            if (obj instanceof String) {
                return (String) obj;
            }
            lk lkVar = (lk) obj;
            String strZ = lkVar.z();
            if (lkVar.q()) {
                this.n = strZ;
            }
            return strZ;
        }

        public final String S() {
            Object obj = this.i;
            if (obj instanceof String) {
                return (String) obj;
            }
            lk lkVar = (lk) obj;
            String strZ = lkVar.z();
            if (lkVar.q()) {
                this.i = strZ;
            }
            return strZ;
        }

        @Override // com.google.protobuf.r0
        /* JADX INFO: renamed from: T, reason: merged with bridge method [inline-methods] */
        public final b h() {
            if (this == p) {
                return new b();
            }
            b bVar = new b();
            bVar.M(this);
            return bVar;
        }

        @Override // defpackage.bh1, com.google.protobuf.t0
        public final q0 b() {
            return p;
        }

        @Override // com.google.protobuf.r0, com.google.protobuf.q0
        public final q0.a e() {
            return p.h();
        }

        @Override // com.google.protobuf.a
        public final boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof s)) {
                return super.equals(obj);
            }
            s sVar = (s) obj;
            if (!this.h.equals(sVar.h)) {
                return false;
            }
            int i = this.g;
            if (((i & 1) != 0) != ((sVar.g & 1) != 0)) {
                return false;
            }
            if (((i & 1) != 0) && !S().equals(sVar.S())) {
                return false;
            }
            int i2 = this.g;
            boolean z = (i2 & 2) != 0;
            int i3 = sVar.g;
            if (z != ((i3 & 2) != 0)) {
                return false;
            }
            if (((i2 & 2) != 0) && this.j != sVar.j) {
                return false;
            }
            if (((i2 & 4) != 0) != ((i3 & 4) != 0)) {
                return false;
            }
            if (((i2 & 4) != 0) && this.k != sVar.k) {
                return false;
            }
            if (((i2 & 8) != 0) != ((i3 & 8) != 0)) {
                return false;
            }
            if (((i2 & 8) != 0) && Double.doubleToLongBits(this.l) != Double.doubleToLongBits(sVar.l)) {
                return false;
            }
            int i4 = this.g;
            if (((i4 & 16) != 0) != ((sVar.g & 16) != 0)) {
                return false;
            }
            if (((i4 & 16) != 0) && !this.m.equals(sVar.m)) {
                return false;
            }
            int i5 = this.g;
            if (((i5 & 32) != 0) != ((sVar.g & 32) != 0)) {
                return false;
            }
            return (!((i5 & 32) != 0) || Q().equals(sVar.Q())) && this.f.equals(sVar.f);
        }

        @Override // com.google.protobuf.a
        public final int hashCode() {
            int i = this.memoizedHashCode;
            if (i != 0) {
                return i;
            }
            int iHashCode = m.O.hashCode() + 779;
            if (this.h.size() > 0) {
                iHashCode = C0175b.e(iHashCode, 37, 2, 53) + this.h.hashCode();
            }
            if ((this.g & 1) != 0) {
                iHashCode = C0175b.e(iHashCode, 37, 3, 53) + S().hashCode();
            }
            if ((this.g & 2) != 0) {
                iHashCode = C0175b.e(iHashCode, 37, 4, 53) + c0.b(this.j);
            }
            if ((this.g & 4) != 0) {
                iHashCode = C0175b.e(iHashCode, 37, 5, 53) + c0.b(this.k);
            }
            if ((this.g & 8) != 0) {
                iHashCode = C0175b.e(iHashCode, 37, 6, 53) + c0.b(Double.doubleToLongBits(this.l));
            }
            if ((this.g & 16) != 0) {
                iHashCode = C0175b.e(iHashCode, 37, 7, 53) + this.m.hashCode();
            }
            if ((this.g & 32) != 0) {
                iHashCode = C0175b.e(iHashCode, 37, 8, 53) + Q().hashCode();
            }
            int iHashCode2 = this.f.hashCode() + (iHashCode * 29);
            this.memoizedHashCode = iHashCode2;
            return iHashCode2;
        }

        @Override // com.google.protobuf.a0, defpackage.bh1
        public final boolean isInitialized() {
            byte b2 = this.o;
            if (b2 == 1) {
                return true;
            }
            if (b2 == 0) {
                return false;
            }
            for (int i = 0; i < this.h.size(); i++) {
                if (!this.h.get(i).isInitialized()) {
                    this.o = (byte) 0;
                    return false;
                }
            }
            this.o = (byte) 1;
            return true;
        }

        @Override // com.google.protobuf.a0, com.google.protobuf.r0
        public final int m() {
            int i = this.e;
            if (i != -1) {
                return i;
            }
            int iH = 0;
            for (int i2 = 0; i2 < this.h.size(); i2++) {
                iH += nu.a0(2, this.h.get(i2));
            }
            if ((this.g & 1) != 0) {
                iH += a0.H(3, this.i);
            }
            if ((this.g & 2) != 0) {
                iH += nu.m0(4, this.j);
            }
            if ((this.g & 4) != 0) {
                iH += nu.U(5, this.k);
            }
            if ((this.g & 8) != 0) {
                iH += nu.M(6);
            }
            if ((this.g & 16) != 0) {
                iH += nu.K(7, this.m);
            }
            if ((this.g & 32) != 0) {
                iH += a0.H(8, this.n);
            }
            int iM = this.f.m() + iH;
            this.e = iM;
            return iM;
        }

        @Override // com.google.protobuf.a0, com.google.protobuf.t0
        public final j1 n() {
            return this.f;
        }

        @Override // com.google.protobuf.a0, com.google.protobuf.r0
        public final void p(nu nuVar) {
            for (int i = 0; i < this.h.size(); i++) {
                nuVar.D0(2, this.h.get(i));
            }
            if ((this.g & 1) != 0) {
                a0.P(nuVar, 3, this.i);
            }
            if ((this.g & 2) != 0) {
                nuVar.P0(4, this.j);
            }
            if ((this.g & 4) != 0) {
                nuVar.P0(5, this.k);
            }
            if ((this.g & 8) != 0) {
                double d = this.l;
                nuVar.getClass();
                nuVar.w0(6, Double.doubleToRawLongBits(d));
            }
            if ((this.g & 16) != 0) {
                nuVar.r0(7, this.m);
            }
            if ((this.g & 32) != 0) {
                a0.P(nuVar, 8, this.n);
            }
            this.f.p(nuVar);
        }

        @Override // com.google.protobuf.a0, com.google.protobuf.r0
        public final wv1<s> u() {
            return q;
        }

        @Override // defpackage.bh1, com.google.protobuf.t0
        public final r0 b() {
            return p;
        }

        @Override // com.google.protobuf.r0, com.google.protobuf.q0
        public final r0.a e() {
            return p.h();
        }

        public s() {
            this.o = (byte) -1;
            this.h = Collections.emptyList();
            this.i = "";
            this.m = lk.f;
            this.n = "";
        }
    }

    static {
        n.g[] gVarArr = new n.g[0];
        byte[] bytes = new String[]{"\n google/protobuf/descriptor.proto\u0012\u000fgoogle.protobuf\"G\n\u0011FileDescriptorSet\u00122\n\u0004file\u0018\u0001 \u0003(\u000b2$.google.protobuf.FileDescriptorProto\"Û\u0003\n\u0013FileDescriptorProto\u0012\f\n\u0004name\u0018\u0001 \u0001(\t\u0012\u000f\n\u0007package\u0018\u0002 \u0001(\t\u0012\u0012\n\ndependency\u0018\u0003 \u0003(\t\u0012\u0019\n\u0011public_dependency\u0018\n \u0003(\u0005\u0012\u0017\n\u000fweak_dependency\u0018\u000b \u0003(\u0005\u00126\n\fmessage_type\u0018\u0004 \u0003(\u000b2 .google.protobuf.DescriptorProto\u00127\n\tenum_type\u0018\u0005 \u0003(\u000b2$.google.protobuf.EnumDescriptorProto\u00128\n\u0007service\u0018\u0006 \u0003(\u000b2'.google.protobuf.ServiceDescriptorProto\u00128\n\textension\u0018\u0007 \u0003(\u000b2%.google.protobuf.FieldDescriptorProto\u0012-\n\u0007options\u0018\b \u0001(\u000b2\u001c.google.protobuf.FileOptions\u00129\n\u0010source_code_info\u0018\t \u0001(\u000b2\u001f.google.protobuf.SourceCodeInfo\u0012\u000e\n\u0006syntax\u0018\f \u0001(\t\"©\u0005\n\u000fDescriptorProto\u0012\f\n\u0004name\u0018\u0001 \u0001(\t\u00124\n\u0005field\u0018\u0002 \u0003(\u000b2%.google.protobuf.FieldDescriptorProto\u00128\n\textension\u0018\u0006 \u0003(\u000b2%.google.protobuf.FieldDescriptorProto\u00125\n\u000bnested_type\u0018\u0003 \u0003(\u000b2 .google.protobuf.DescriptorProto\u00127\n\tenum_type\u0018\u0004 \u0003(\u000b2$.google.protobuf.EnumDescriptorProto\u0012H\n\u000fextension_range\u0018\u0005 \u0003(\u000b2/.google.protobuf.DescriptorProto.ExtensionRange\u00129\n\noneof_decl\u0018\b \u0003(\u000b2%.google.protobuf.OneofDescriptorProto\u00120\n\u0007options\u0018\u0007 \u0001(\u000b2\u001f.google.protobuf.MessageOptions\u0012F\n\u000ereserved_range\u0018\t \u0003(\u000b2..google.protobuf.DescriptorProto.ReservedRange\u0012\u0015\n\rreserved_name\u0018\n \u0003(\t\u001ae\n\u000eExtensionRange\u0012\r\n\u0005start\u0018\u0001 \u0001(\u0005\u0012\u000b\n\u0003end\u0018\u0002 \u0001(\u0005\u00127\n\u0007options\u0018\u0003 \u0001(\u000b2&.google.protobuf.ExtensionRangeOptions\u001a+\n\rReservedRange\u0012\r\n\u0005start\u0018\u0001 \u0001(\u0005\u0012\u000b\n\u0003end\u0018\u0002 \u0001(\u0005\"g\n\u0015ExtensionRangeOptions\u0012C\n\u0014uninterpreted_option\u0018ç\u0007 \u0003(\u000b2$.google.protobuf.UninterpretedOption*\t\bè\u0007\u0010\u0080\u0080\u0080\u0080\u0002\"Õ\u0005\n\u0014FieldDescriptorProto\u0012\f\n\u0004name\u0018\u0001 \u0001(\t\u0012\u000e\n\u0006number\u0018\u0003 \u0001(\u0005\u0012:\n\u0005label\u0018\u0004 \u0001(\u000e2+.google.protobuf.FieldDescriptorProto.Label\u00128\n\u0004type\u0018\u0005 \u0001(\u000e2*.google.protobuf.FieldDescriptorProto.Type\u0012\u0011\n\ttype_name\u0018\u0006 \u0001(\t\u0012\u0010\n\bextendee\u0018\u0002 \u0001(\t\u0012\u0015\n\rdefault_value\u0018\u0007 \u0001(\t\u0012\u0013\n\u000boneof_index\u0018\t \u0001(\u0005\u0012\u0011\n\tjson_name\u0018\n \u0001(\t\u0012.\n\u0007options\u0018\b \u0001(\u000b2\u001d.google.protobuf.FieldOptions\u0012\u0017\n\u000fproto3_optional\u0018\u0011 \u0001(\b\"¶\u0002\n\u0004Type\u0012\u000f\n\u000bTYPE_DOUBLE\u0010\u0001\u0012\u000e\n\nTYPE_FLOAT\u0010\u0002\u0012\u000e\n\nTYPE_INT64\u0010\u0003\u0012\u000f\n\u000bTYPE_UINT64\u0010\u0004\u0012\u000e\n\nTYPE_INT32\u0010\u0005\u0012\u0010\n\fTYPE_FIXED64\u0010\u0006\u0012\u0010\n\fTYPE_FIXED32\u0010\u0007\u0012\r\n\tTYPE_BOOL\u0010\b\u0012\u000f\n\u000bTYPE_STRING\u0010\t\u0012\u000e\n\nTYPE_GROUP\u0010\n\u0012\u0010\n\fTYPE_MESSAGE\u0010\u000b\u0012\u000e\n\nTYPE_BYTES\u0010\f\u0012\u000f\n\u000bTYPE_UINT32\u0010\r\u0012\r\n\tTYPE_ENUM\u0010\u000e\u0012\u0011\n\rTYPE_SFIXED32\u0010\u000f\u0012\u0011\n\rTYPE_SFIXED64\u0010\u0010\u0012\u000f\n\u000bTYPE_SINT32\u0010\u0011\u0012\u000f\n\u000bTYPE_SINT64\u0010\u0012\"C\n\u0005Label\u0012\u0012\n\u000eLABEL_OPTIONAL\u0010\u0001\u0012\u0012\n\u000eLABEL_REQUIRED\u0010\u0002\u0012\u0012\n\u000eLABEL_REPEATED\u0010\u0003\"T\n\u0014OneofDescriptorProto\u0012\f\n\u0004name\u0018\u0001 \u0001(\t\u0012.\n\u0007options\u0018\u0002 \u0001(\u000b2\u001d.google.protobuf.OneofOptions\"¤\u0002\n\u0013EnumDescriptorProto\u0012\f\n\u0004name\u0018\u0001 \u0001(\t\u00128\n\u0005value\u0018\u0002 \u0003(\u000b2).google.protobuf.EnumValueDescriptorProto\u0012-\n\u0007options\u0018\u0003 \u0001(\u000b2\u001c.google.protobuf.EnumOptions\u0012N\n\u000ereserved_range\u0018\u0004 \u0003(\u000b26.google.protobuf.EnumDescriptorProto.EnumReservedRange\u0012\u0015\n\rreserved_name\u0018\u0005 \u0003(\t\u001a/\n\u0011EnumReservedRange\u0012\r\n\u0005start\u0018\u0001 \u0001(\u0005\u0012\u000b\n\u0003end\u0018\u0002 \u0001(\u0005\"l\n\u0018EnumValueDescriptorProto\u0012\f\n\u0004name\u0018\u0001 \u0001(\t\u0012\u000e\n\u0006number\u0018\u0002 \u0001(\u0005\u00122\n\u0007options\u0018\u0003 \u0001(\u000b2!.google.protobuf.EnumValueOptions\"\u0090\u0001\n\u0016ServiceDescriptorProto\u0012\f\n\u0004name\u0018\u0001 \u0001(\t\u00126\n\u0006method\u0018\u0002 \u0003(\u000b2&.google.protobuf.MethodDescriptorProto\u00120\n\u0007options\u0018\u0003 \u0001(\u000b2\u001f.google.protobuf.ServiceOptions\"Á\u0001\n\u0015MethodDescriptorProto\u0012\f\n\u0004name\u0018\u0001 \u0001(\t\u0012\u0012\n\ninput_type\u0018\u0002 \u0001(\t\u0012\u0013\n\u000boutput_type\u0018\u0003 \u0001(\t\u0012/\n\u0007options\u0018\u0004 \u0001(\u000b2\u001e.google.protobuf.MethodOptions\u0012\u001f\n\u0010client_streaming\u0018\u0005 \u0001(\b:\u0005false\u0012\u001f\n\u0010server_streaming\u0018\u0006 \u0001(\b:\u0005false\"¥\u0006\n\u000bFileOptions\u0012\u0014\n\fjava_package\u0018\u0001 \u0001(\t\u0012\u001c\n\u0014java_outer_classname\u0018\b \u0001(\t\u0012\"\n\u0013java_multiple_files\u0018\n \u0001(\b:\u0005false\u0012)\n\u001djava_generate_equals_and_hash\u0018\u0014 \u0001(\bB\u0002\u0018\u0001\u0012%\n\u0016java_string_check_utf8\u0018\u001b \u0001(\b:\u0005false\u0012F\n\foptimize_for\u0018\t \u0001(\u000e2).google.protobuf.FileOptions.OptimizeMode:\u0005SPEED\u0012\u0012\n\ngo_package\u0018\u000b \u0001(\t\u0012\"\n\u0013cc_generic_services\u0018\u0010 \u0001(\b:\u0005false\u0012$\n\u0015java_generic_services\u0018\u0011 \u0001(\b:\u0005false\u0012\"\n\u0013py_generic_services\u0018\u0012 \u0001(\b:\u0005false\u0012#\n\u0014php_generic_services\u0018* \u0001(\b:\u0005false\u0012\u0019\n\ndeprecated\u0018\u0017 \u0001(\b:\u0005false\u0012\u001e\n\u0010cc_enable_arenas\u0018\u001f \u0001(\b:\u0004true\u0012\u0019\n\u0011objc_class_prefix\u0018$ \u0001(\t\u0012\u0018\n\u0010csharp_namespace\u0018% \u0001(\t\u0012\u0014\n\fswift_prefix\u0018' \u0001(\t\u0012\u0018\n\u0010php_class_prefix\u0018( \u0001(\t\u0012\u0015\n\rphp_namespace\u0018) \u0001(\t\u0012\u001e\n\u0016php_metadata_namespace\u0018, \u0001(\t\u0012\u0014\n\fruby_package\u0018- \u0001(\t\u0012C\n\u0014uninterpreted_option\u0018ç\u0007 \u0003(\u000b2$.google.protobuf.UninterpretedOption\":\n\fOptimizeMode\u0012\t\n\u0005SPEED\u0010\u0001\u0012\r\n\tCODE_SIZE\u0010\u0002\u0012\u0010\n\fLITE_RUNTIME\u0010\u0003*\t\bè\u0007\u0010\u0080\u0080\u0080\u0080\u0002J\u0004\b&\u0010'\"\u0084\u0002\n\u000eMessageOptions\u0012&\n\u0017message_set_wire_format\u0018\u0001 \u0001(\b:\u0005false\u0012.\n\u001fno_standard_descriptor_accessor\u0018\u0002 \u0001(\b:\u0005false\u0012\u0019\n\ndeprecated\u0018\u0003 \u0001(\b:\u0005false\u0012\u0011\n\tmap_entry\u0018\u0007 \u0001(\b\u0012C\n\u0014uninterpreted_option\u0018ç\u0007 \u0003(\u000b2$.google.protobuf.UninterpretedOption*\t\bè\u0007\u0010\u0080\u0080\u0080\u0080\u0002J\u0004\b\u0004\u0010\u0005J\u0004\b\u0005\u0010\u0006J\u0004\b\u0006\u0010\u0007J\u0004\b\b\u0010\tJ\u0004\b\t\u0010\n\"\u009e\u0003\n\fFieldOptions\u0012:\n\u0005ctype\u0018\u0001 \u0001(\u000e2#.google.protobuf.FieldOptions.CType:\u0006STRING\u0012\u000e\n\u0006packed\u0018\u0002 \u0001(\b\u0012?\n\u0006jstype\u0018\u0006 \u0001(\u000e2$.google.protobuf.FieldOptions.JSType:\tJS_NORMAL\u0012\u0013\n\u0004lazy\u0018\u0005 \u0001(\b:\u0005false\u0012\u0019\n\ndeprecated\u0018\u0003 \u0001(\b:\u0005false\u0012\u0013\n\u0004weak\u0018\n \u0001(\b:\u0005false\u0012C\n\u0014uninterpreted_option\u0018ç\u0007 \u0003(\u000b2$.google.protobuf.UninterpretedOption\"/\n\u0005CType\u0012\n\n\u0006STRING\u0010\u0000\u0012\b\n\u0004CORD\u0010\u0001\u0012\u0010\n\fSTRING_PIECE\u0010\u0002\"5\n\u0006JSType\u0012\r\n\tJS_NORMAL\u0010\u0000\u0012\r\n\tJS_STRING\u0010\u0001\u0012\r\n\tJS_NUMBER\u0010\u0002*\t\bè\u0007\u0010\u0080\u0080\u0080\u0080\u0002J\u0004\b\u0004\u0010\u0005\"^\n\fOneofOptions\u0012C\n\u0014uninterpreted_option\u0018ç\u0007 \u0003(\u000b2$.google.protobuf.UninterpretedOption*\t\bè\u0007\u0010\u0080\u0080\u0080\u0080\u0002\"\u0093\u0001\n\u000bEnumOptions\u0012\u0013\n\u000ballow_alias\u0018\u0002 \u0001(\b\u0012\u0019\n\ndeprecated\u0018\u0003 \u0001(\b:\u0005false\u0012C\n\u0014uninterpreted_option\u0018ç\u0007 \u0003(\u000b2$.google.protobuf.UninterpretedOption*\t\bè\u0007\u0010\u0080\u0080\u0080\u0080\u0002J\u0004\b\u0005\u0010\u0006\"}\n\u0010EnumValueOptions\u0012\u0019\n\ndeprecated\u0018\u0001 \u0001(\b:\u0005false\u0012C\n\u0014uninterpreted_option\u0018ç\u0007 \u0003(\u000b2$.google.protobuf.UninterpretedOption*\t\bè\u0007\u0010\u0080\u0080\u0080\u0080\u0002\"{\n\u000eServiceOptions\u0012\u0019\n\ndeprecated\u0018! \u0001(\b:\u0005false\u0012C\n\u0014uninterpreted_option\u0018ç\u0007 \u0003(\u000b2$.google.protobuf.UninterpretedOption*\t\bè\u0007\u0010\u0080\u0080\u0080\u0080\u0002\"\u00ad\u0002\n\rMethodOptions\u0012\u0019\n\ndeprecated\u0018! \u0001(\b:\u0005false\u0012_\n\u0011idempotency_level\u0018\" \u0001(\u000e2/.google.protobuf.MethodOptions.IdempotencyLevel:\u0013IDEMPOTENCY_UNKNOWN\u0012C\n\u0014uninterpreted_option\u0018ç\u0007 \u0003(\u000b2$.google.protobuf.UninterpretedOption\"P\n\u0010IdempotencyLevel\u0012\u0017\n\u0013IDEMPOTENCY_UNKNOWN\u0010\u0000\u0012\u0013\n\u000fNO_SIDE_EFFECTS\u0010\u0001\u0012\u000e\n\nIDEMPOTENT\u0010\u0002*\t\bè\u0007\u0010\u0080\u0080\u0080\u0080\u0002\"\u009e\u0002\n\u0013UninterpretedOption\u0012;\n\u0004name\u0018\u0002 \u0003(\u000b2-.google.protobuf.UninterpretedOption.NamePart\u0012\u0018\n\u0010identifier_value\u0018\u0003 \u0001(\t\u0012\u001a\n\u0012positive_int_value\u0018\u0004 \u0001(\u0004\u0012\u001a\n\u0012negative_int_value\u0018\u0005 \u0001(\u0003\u0012\u0014\n\fdouble_value\u0018\u0006 \u0001(\u0001\u0012\u0014\n\fstring_value\u0018\u0007 \u0001(\f\u0012\u0017\n\u000faggregate_value\u0018\b \u0001(\t\u001a3\n\bNamePart\u0012\u0011\n\tname_part\u0018\u0001 \u0002(\t\u0012\u0014\n\fis_extension\u0018\u0002 \u0002(\b\"Õ\u0001\n\u000eSourceCodeInfo\u0012:\n\blocation\u0018\u0001 \u0003(\u000b2(.google.protobuf.SourceCodeInfo.Location\u001a\u0086\u0001\n\bLocation\u0012\u0010\n\u0004path\u0018\u0001 \u0003(\u0005B\u0002\u0010\u0001\u0012\u0010\n\u0004span\u0018\u0002 \u0003(\u0005B\u0002\u0010\u0001\u0012\u0018\n\u0010leading_comments\u0018\u0003 \u0001(\t\u0012\u0019\n\u0011trailing_comments\u0018\u0004 \u0001(\t\u0012!\n\u0019leading_detached_comments\u0018\u0006 \u0003(\t\"§\u0001\n\u0011GeneratedCodeInfo\u0012A\n\nannotation\u0018\u0001 \u0003(\u000b2-.google.protobuf.GeneratedCodeInfo.Annotation\u001aO\n\nAnnotation\u0012\u0010\n\u0004path\u0018\u0001 \u0003(\u0005B\u0002\u0010\u0001\u0012\u0013\n\u000bsource_file\u0018\u0002 \u0001(\t\u0012\r\n\u0005begin\u0018\u0003 \u0001(\u0005\u0012\u000b\n\u0003end\u0018\u0004 \u0001(\u0005B~\n\u0013com.google.protobufB\u0010DescriptorProtosH\u0001Z-google.golang.org/protobuf/types/descriptorpbø\u0001\u0001¢\u0002\u0003GPBª\u0002\u001aGoogle.Protobuf.Reflection"}[0].getBytes(c0.b);
        try {
            i.a aVar = i.v;
            aVar.getClass();
            r0 r0VarD = aVar.d(bytes, bytes.length, com.google.protobuf.c.a);
            com.google.protobuf.c.c(r0VarD);
            i iVar = (i) r0VarD;
            try {
                n.g gVarH = n.g.h(iVar, gVarArr);
                n.a aVar2 = gVarH.i().get(0);
                a0.e.a[] aVarArr = new a0.e.a[aVar2.k().size()];
                a0.e.c[] cVarArr = new a0.e.c[aVar2.m().size()];
                n.a aVar3 = gVarH.i().get(1);
                a = aVar3;
                b = new a0.e(aVar3, new String[]{"Name", "Package", "Dependency", "PublicDependency", "WeakDependency", "MessageType", "EnumType", "Service", "Extension", "Options", "SourceCodeInfo", "Syntax"});
                n.a aVar4 = gVarH.i().get(2);
                c = aVar4;
                d = new a0.e(aVar4, new String[]{"Name", "Field", "Extension", "NestedType", "EnumType", "ExtensionRange", "OneofDecl", "Options", "ReservedRange", "ReservedName"});
                n.a aVar5 = aVar4.l().get(0);
                e = aVar5;
                f = new a0.e(aVar5, new String[]{"Start", "End", "Options"});
                n.a aVar6 = aVar4.l().get(1);
                g = aVar6;
                h = new a0.e(aVar6, new String[]{"Start", "End"});
                n.a aVar7 = gVarH.i().get(3);
                i = aVar7;
                j = new a0.e(aVar7, new String[]{"UninterpretedOption"});
                n.a aVar8 = gVarH.i().get(4);
                k = aVar8;
                l = new a0.e(aVar8, new String[]{"Name", "Number", "Label", "Type", "TypeName", "Extendee", "DefaultValue", "OneofIndex", "JsonName", "Options", "Proto3Optional"});
                n.a aVar9 = gVarH.i().get(5);
                m = aVar9;
                n = new a0.e(aVar9, new String[]{"Name", "Options"});
                n.a aVar10 = gVarH.i().get(6);
                o = aVar10;
                p = new a0.e(aVar10, new String[]{"Name", "Value", "Options", "ReservedRange", "ReservedName"});
                n.a aVar11 = aVar10.l().get(0);
                q = aVar11;
                r = new a0.e(aVar11, new String[]{"Start", "End"});
                n.a aVar12 = gVarH.i().get(7);
                s = aVar12;
                t = new a0.e(aVar12, new String[]{"Name", "Number", "Options"});
                n.a aVar13 = gVarH.i().get(8);
                u = aVar13;
                v = new a0.e(aVar13, new String[]{"Name", "Method", "Options"});
                n.a aVar14 = gVarH.i().get(9);
                w = aVar14;
                x = new a0.e(aVar14, new String[]{"Name", "InputType", "OutputType", "Options", "ClientStreaming", "ServerStreaming"});
                n.a aVar15 = gVarH.i().get(10);
                y = aVar15;
                z = new a0.e(aVar15, new String[]{"JavaPackage", "JavaOuterClassname", "JavaMultipleFiles", "JavaGenerateEqualsAndHash", "JavaStringCheckUtf8", "OptimizeFor", "GoPackage", "CcGenericServices", "JavaGenericServices", "PyGenericServices", "PhpGenericServices", "Deprecated", "CcEnableArenas", "ObjcClassPrefix", "CsharpNamespace", "SwiftPrefix", "PhpClassPrefix", "PhpNamespace", "PhpMetadataNamespace", "RubyPackage", "UninterpretedOption"});
                n.a aVar16 = gVarH.i().get(11);
                A = aVar16;
                B = new a0.e(aVar16, new String[]{"MessageSetWireFormat", "NoStandardDescriptorAccessor", "Deprecated", "MapEntry", "UninterpretedOption"});
                n.a aVar17 = gVarH.i().get(12);
                C = aVar17;
                D = new a0.e(aVar17, new String[]{"Ctype", "Packed", "Jstype", "Lazy", "Deprecated", "Weak", "UninterpretedOption"});
                n.a aVar18 = gVarH.i().get(13);
                E = aVar18;
                F = new a0.e(aVar18, new String[]{"UninterpretedOption"});
                n.a aVar19 = gVarH.i().get(14);
                G = aVar19;
                H = new a0.e(aVar19, new String[]{"AllowAlias", "Deprecated", "UninterpretedOption"});
                n.a aVar20 = gVarH.i().get(15);
                I = aVar20;
                f38J = new a0.e(aVar20, new String[]{"Deprecated", "UninterpretedOption"});
                n.a aVar21 = gVarH.i().get(16);
                K = aVar21;
                L = new a0.e(aVar21, new String[]{"Deprecated", "UninterpretedOption"});
                n.a aVar22 = gVarH.i().get(17);
                M = aVar22;
                N = new a0.e(aVar22, new String[]{"Deprecated", "IdempotencyLevel", "UninterpretedOption"});
                n.a aVar23 = gVarH.i().get(18);
                O = aVar23;
                P = new a0.e(aVar23, new String[]{"Name", "IdentifierValue", "PositiveIntValue", "NegativeIntValue", "DoubleValue", "StringValue", "AggregateValue"});
                n.a aVar24 = aVar23.l().get(0);
                Q = aVar24;
                R = new a0.e(aVar24, new String[]{"NamePart", "IsExtension"});
                n.a aVar25 = gVarH.i().get(19);
                S = aVar25;
                T = new a0.e(aVar25, new String[]{"Location"});
                n.a aVar26 = aVar25.l().get(0);
                U = aVar26;
                V = new a0.e(aVar26, new String[]{"Path", "Span", "LeadingComments", "TrailingComments", "LeadingDetachedComments"});
                n.a aVar27 = gVarH.i().get(20);
                a0.e.a[] aVarArr2 = new a0.e.a[aVar27.k().size()];
                a0.e.c[] cVarArr2 = new a0.e.c[aVar27.m().size()];
                n.a aVar28 = aVar27.l().get(0);
                a0.e.a[] aVarArr3 = new a0.e.a[aVar28.k().size()];
                a0.e.c[] cVarArr3 = new a0.e.c[aVar28.m().size()];
            } catch (n.c e2) {
                throw new IllegalArgumentException("Invalid embedded descriptor for \"" + iVar.Q() + "\".", e2);
            }
        } catch (d0 e3) {
            throw new IllegalArgumentException("Failed to parse protocol buffer descriptor for generated code.", e3);
        }
    }
}
