package m8;

import j8.C1556a;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import p8.AbstractC2129d;
import p8.AbstractC2134i;
import p8.AbstractC2139n;
import p8.C2128c;
import p8.C2130e;
import p8.C2142q;
import p8.t;

/* JADX INFO: loaded from: classes.dex */
public final class i extends AbstractC2139n {

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public static final i f19309x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public static final C1556a f19310y = new C1556a(28);
    public final AbstractC2129d l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f19311m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f19312n;
    public int o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public Object f19313p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public h f19314q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public List f19315r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public int f19316s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public List f19317t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public int f19318u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public byte f19319v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public int f19320w;

    static {
        i iVar = new i();
        f19309x = iVar;
        iVar.f19312n = 1;
        iVar.o = 0;
        iVar.f19313p = "";
        iVar.f19314q = h.NONE;
        iVar.f19315r = Collections.emptyList();
        iVar.f19317t = Collections.emptyList();
    }

    public i() {
        this.f19316s = -1;
        this.f19318u = -1;
        this.f19319v = (byte) -1;
        this.f19320w = -1;
        this.l = AbstractC2129d.l;
    }

    @Override // p8.u
    public final boolean b() {
        byte b10 = this.f19319v;
        if (b10 == 1) {
            return true;
        }
        if (b10 == 0) {
            return false;
        }
        this.f19319v = (byte) 1;
        return true;
    }

    @Override // p8.AbstractC2126a
    public final int c() {
        AbstractC2129d tVar;
        int i7 = this.f19320w;
        if (i7 != -1) {
            return i7;
        }
        int iF = (this.f19311m & 1) == 1 ? A2.g.f(1, this.f19312n) : 0;
        if ((this.f19311m & 2) == 2) {
            iF += A2.g.f(2, this.o);
        }
        if ((this.f19311m & 8) == 8) {
            iF += A2.g.e(3, this.f19314q.l);
        }
        int iG = 0;
        for (int i10 = 0; i10 < this.f19315r.size(); i10++) {
            iG += A2.g.g(((Integer) this.f19315r.get(i10)).intValue());
        }
        int iG2 = iF + iG;
        if (!this.f19315r.isEmpty()) {
            iG2 = iG2 + 1 + A2.g.g(iG);
        }
        this.f19316s = iG;
        int iG3 = 0;
        for (int i11 = 0; i11 < this.f19317t.size(); i11++) {
            iG3 += A2.g.g(((Integer) this.f19317t.get(i11)).intValue());
        }
        int size = iG2 + iG3;
        if (!this.f19317t.isEmpty()) {
            size = size + 1 + A2.g.g(iG3);
        }
        this.f19318u = iG3;
        if ((this.f19311m & 4) == 4) {
            Object obj = this.f19313p;
            if (obj instanceof String) {
                try {
                    tVar = new t(((String) obj).getBytes("UTF-8"));
                    this.f19313p = tVar;
                } catch (UnsupportedEncodingException e10) {
                    throw new RuntimeException("UTF-8 not supported?", e10);
                }
            } else {
                tVar = (AbstractC2129d) obj;
            }
            size += tVar.size() + A2.g.j(tVar.size()) + A2.g.l(6);
        }
        int size2 = this.l.size() + size;
        this.f19320w = size2;
        return size2;
    }

    @Override // p8.AbstractC2126a
    public final AbstractC2134i d() {
        return g.g();
    }

    @Override // p8.AbstractC2126a
    public final AbstractC2134i e() {
        g gVarG = g.g();
        gVarG.h(this);
        return gVarG;
    }

    @Override // p8.AbstractC2126a
    public final void f(A2.g gVar) throws IOException {
        AbstractC2129d tVar;
        c();
        if ((this.f19311m & 1) == 1) {
            gVar.C(1, this.f19312n);
        }
        if ((this.f19311m & 2) == 2) {
            gVar.C(2, this.o);
        }
        if ((this.f19311m & 8) == 8) {
            gVar.B(3, this.f19314q.l);
        }
        if (this.f19315r.size() > 0) {
            gVar.L(34);
            gVar.L(this.f19316s);
        }
        for (int i7 = 0; i7 < this.f19315r.size(); i7++) {
            gVar.D(((Integer) this.f19315r.get(i7)).intValue());
        }
        if (this.f19317t.size() > 0) {
            gVar.L(42);
            gVar.L(this.f19318u);
        }
        for (int i10 = 0; i10 < this.f19317t.size(); i10++) {
            gVar.D(((Integer) this.f19317t.get(i10)).intValue());
        }
        if ((this.f19311m & 4) == 4) {
            Object obj = this.f19313p;
            if (obj instanceof String) {
                try {
                    tVar = new t(((String) obj).getBytes("UTF-8"));
                    this.f19313p = tVar;
                } catch (UnsupportedEncodingException e10) {
                    throw new RuntimeException("UTF-8 not supported?", e10);
                }
            } else {
                tVar = (AbstractC2129d) obj;
            }
            gVar.N(6, 2);
            gVar.L(tVar.size());
            gVar.H(tVar);
        }
        gVar.H(this.l);
    }

    public i(C2130e c2130e) {
        h hVar;
        this.f19316s = -1;
        this.f19318u = -1;
        this.f19319v = (byte) -1;
        this.f19320w = -1;
        this.f19312n = 1;
        boolean z6 = false;
        this.o = 0;
        this.f19313p = "";
        h hVar2 = h.NONE;
        this.f19314q = hVar2;
        this.f19315r = Collections.emptyList();
        this.f19317t = Collections.emptyList();
        C2128c c2128c = new C2128c();
        A2.g gVarT = A2.g.t(c2128c, 1);
        int i7 = 0;
        while (!z6) {
            try {
                try {
                    int iN = c2130e.n();
                    if (iN != 0) {
                        if (iN == 8) {
                            this.f19311m |= 1;
                            this.f19312n = c2130e.k();
                        } else if (iN == 16) {
                            this.f19311m |= 2;
                            this.o = c2130e.k();
                        } else if (iN == 24) {
                            int iK = c2130e.k();
                            if (iK == 0) {
                                hVar = hVar2;
                            } else if (iK != 1) {
                                hVar = iK != 2 ? null : h.DESC_TO_CLASS_ID;
                            } else {
                                hVar = h.INTERNAL_TO_CLASS_ID;
                            }
                            if (hVar == null) {
                                gVarT.L(iN);
                                gVarT.L(iK);
                            } else {
                                this.f19311m |= 8;
                                this.f19314q = hVar;
                            }
                        } else if (iN == 32) {
                            if ((i7 & 16) != 16) {
                                this.f19315r = new ArrayList();
                                i7 |= 16;
                            }
                            this.f19315r.add(Integer.valueOf(c2130e.k()));
                        } else if (iN == 34) {
                            int iD = c2130e.d(c2130e.k());
                            if ((i7 & 16) != 16 && c2130e.b() > 0) {
                                this.f19315r = new ArrayList();
                                i7 |= 16;
                            }
                            while (c2130e.b() > 0) {
                                this.f19315r.add(Integer.valueOf(c2130e.k()));
                            }
                            c2130e.c(iD);
                        } else if (iN == 40) {
                            if ((i7 & 32) != 32) {
                                this.f19317t = new ArrayList();
                                i7 |= 32;
                            }
                            this.f19317t.add(Integer.valueOf(c2130e.k()));
                        } else if (iN == 42) {
                            int iD2 = c2130e.d(c2130e.k());
                            if ((i7 & 32) != 32 && c2130e.b() > 0) {
                                this.f19317t = new ArrayList();
                                i7 |= 32;
                            }
                            while (c2130e.b() > 0) {
                                this.f19317t.add(Integer.valueOf(c2130e.k()));
                            }
                            c2130e.c(iD2);
                        } else if (iN != 50) {
                            if (!c2130e.q(iN, gVarT)) {
                            }
                        } else {
                            t tVarE = c2130e.e();
                            this.f19311m |= 4;
                            this.f19313p = tVarE;
                        }
                    }
                    z6 = true;
                } catch (Throwable th) {
                    if ((i7 & 16) == 16) {
                        this.f19315r = Collections.unmodifiableList(this.f19315r);
                    }
                    if ((i7 & 32) == 32) {
                        this.f19317t = Collections.unmodifiableList(this.f19317t);
                    }
                    try {
                        gVarT.n();
                    } catch (IOException unused) {
                    } catch (Throwable th2) {
                        this.l = c2128c.i();
                        throw th2;
                    }
                    this.l = c2128c.i();
                    throw th;
                }
            } catch (C2142q e10) {
                e10.l = this;
                throw e10;
            } catch (IOException e11) {
                C2142q c2142q = new C2142q(e11.getMessage());
                c2142q.l = this;
                throw c2142q;
            }
        }
        if ((i7 & 16) == 16) {
            this.f19315r = Collections.unmodifiableList(this.f19315r);
        }
        if ((i7 & 32) == 32) {
            this.f19317t = Collections.unmodifiableList(this.f19317t);
        }
        try {
            gVarT.n();
        } catch (IOException unused2) {
        } catch (Throwable th3) {
            this.l = c2128c.i();
            throw th3;
        }
        this.l = c2128c.i();
    }

    public i(g gVar) {
        this.f19316s = -1;
        this.f19318u = -1;
        this.f19319v = (byte) -1;
        this.f19320w = -1;
        this.l = gVar.l;
    }
}
