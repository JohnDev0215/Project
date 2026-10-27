package m8;

import j8.C1556a;
import java.io.IOException;
import p8.AbstractC2129d;
import p8.AbstractC2134i;
import p8.AbstractC2139n;
import p8.C2128c;
import p8.C2130e;
import p8.C2132g;
import p8.C2142q;

/* JADX INFO: loaded from: classes.dex */
public final class e extends AbstractC2139n {

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public static final e f19289u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public static final C1556a f19290v = new C1556a(26);
    public final AbstractC2129d l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f19291m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public b f19292n;
    public c o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public c f19293p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public c f19294q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public c f19295r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public byte f19296s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public int f19297t;

    static {
        e eVar = new e();
        f19289u = eVar;
        eVar.f19292n = b.f19272r;
        c cVar = c.f19278r;
        eVar.o = cVar;
        eVar.f19293p = cVar;
        eVar.f19294q = cVar;
        eVar.f19295r = cVar;
    }

    public e() {
        this.f19296s = (byte) -1;
        this.f19297t = -1;
        this.l = AbstractC2129d.l;
    }

    @Override // p8.u
    public final boolean b() {
        byte b10 = this.f19296s;
        if (b10 == 1) {
            return true;
        }
        if (b10 == 0) {
            return false;
        }
        this.f19296s = (byte) 1;
        return true;
    }

    @Override // p8.AbstractC2126a
    public final int c() {
        int i7 = this.f19297t;
        if (i7 != -1) {
            return i7;
        }
        int iH = (this.f19291m & 1) == 1 ? A2.g.h(1, this.f19292n) : 0;
        if ((this.f19291m & 2) == 2) {
            iH += A2.g.h(2, this.o);
        }
        if ((this.f19291m & 4) == 4) {
            iH += A2.g.h(3, this.f19293p);
        }
        if ((this.f19291m & 8) == 8) {
            iH += A2.g.h(4, this.f19294q);
        }
        if ((this.f19291m & 16) == 16) {
            iH += A2.g.h(5, this.f19295r);
        }
        int size = this.l.size() + iH;
        this.f19297t = size;
        return size;
    }

    @Override // p8.AbstractC2126a
    public final AbstractC2134i d() {
        return d.g();
    }

    @Override // p8.AbstractC2126a
    public final AbstractC2134i e() {
        d dVarG = d.g();
        dVarG.h(this);
        return dVarG;
    }

    @Override // p8.AbstractC2126a
    public final void f(A2.g gVar) throws IOException {
        c();
        if ((this.f19291m & 1) == 1) {
            gVar.E(1, this.f19292n);
        }
        if ((this.f19291m & 2) == 2) {
            gVar.E(2, this.o);
        }
        if ((this.f19291m & 4) == 4) {
            gVar.E(3, this.f19293p);
        }
        if ((this.f19291m & 8) == 8) {
            gVar.E(4, this.f19294q);
        }
        if ((this.f19291m & 16) == 16) {
            gVar.E(5, this.f19295r);
        }
        gVar.H(this.l);
    }

    public e(C2130e c2130e, C2132g c2132g) {
        this.f19296s = (byte) -1;
        this.f19297t = -1;
        this.f19292n = b.f19272r;
        c cVar = c.f19278r;
        this.o = cVar;
        this.f19293p = cVar;
        this.f19294q = cVar;
        this.f19295r = cVar;
        C2128c c2128c = new C2128c();
        A2.g gVarT = A2.g.t(c2128c, 1);
        boolean z6 = false;
        while (!z6) {
            try {
                try {
                    int iN = c2130e.n();
                    if (iN != 0) {
                        a aVarI = null;
                        if (iN == 10) {
                            if ((this.f19291m & 1) == 1) {
                                b bVar = this.f19292n;
                                bVar.getClass();
                                aVarI = new a(0);
                                aVarI.h(bVar);
                            }
                            b bVar2 = (b) c2130e.g(b.f19273s, c2132g);
                            this.f19292n = bVar2;
                            if (aVarI != null) {
                                aVarI.h(bVar2);
                                this.f19292n = aVarI.f();
                            }
                            this.f19291m |= 1;
                        } else if (iN == 18) {
                            if ((this.f19291m & 2) == 2) {
                                c cVar2 = this.o;
                                cVar2.getClass();
                                aVarI = c.i(cVar2);
                            }
                            c cVar3 = (c) c2130e.g(c.f19279s, c2132g);
                            this.o = cVar3;
                            if (aVarI != null) {
                                aVarI.i(cVar3);
                                this.o = aVarI.g();
                            }
                            this.f19291m |= 2;
                        } else if (iN == 26) {
                            if ((this.f19291m & 4) == 4) {
                                c cVar4 = this.f19293p;
                                cVar4.getClass();
                                aVarI = c.i(cVar4);
                            }
                            c cVar5 = (c) c2130e.g(c.f19279s, c2132g);
                            this.f19293p = cVar5;
                            if (aVarI != null) {
                                aVarI.i(cVar5);
                                this.f19293p = aVarI.g();
                            }
                            this.f19291m |= 4;
                        } else if (iN == 34) {
                            if ((this.f19291m & 8) == 8) {
                                c cVar6 = this.f19294q;
                                cVar6.getClass();
                                aVarI = c.i(cVar6);
                            }
                            c cVar7 = (c) c2130e.g(c.f19279s, c2132g);
                            this.f19294q = cVar7;
                            if (aVarI != null) {
                                aVarI.i(cVar7);
                                this.f19294q = aVarI.g();
                            }
                            this.f19291m |= 8;
                        } else if (iN != 42) {
                            if (!c2130e.q(iN, gVarT)) {
                            }
                        } else {
                            if ((this.f19291m & 16) == 16) {
                                c cVar8 = this.f19295r;
                                cVar8.getClass();
                                aVarI = c.i(cVar8);
                            }
                            c cVar9 = (c) c2130e.g(c.f19279s, c2132g);
                            this.f19295r = cVar9;
                            if (aVarI != null) {
                                aVarI.i(cVar9);
                                this.f19295r = aVarI.g();
                            }
                            this.f19291m |= 16;
                        }
                    }
                    z6 = true;
                } catch (C2142q e10) {
                    e10.l = this;
                    throw e10;
                } catch (IOException e11) {
                    C2142q c2142q = new C2142q(e11.getMessage());
                    c2142q.l = this;
                    throw c2142q;
                }
            } catch (Throwable th) {
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

    public e(d dVar) {
        this.f19296s = (byte) -1;
        this.f19297t = -1;
        this.l = dVar.l;
    }
}
