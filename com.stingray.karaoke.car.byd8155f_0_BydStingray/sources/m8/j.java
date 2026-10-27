package m8;

import j8.C1556a;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import p8.AbstractC2126a;
import p8.AbstractC2129d;
import p8.AbstractC2134i;
import p8.AbstractC2139n;
import p8.C2128c;
import p8.C2130e;
import p8.C2132g;
import p8.C2142q;

/* JADX INFO: loaded from: classes.dex */
public final class j extends AbstractC2139n {

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public static final j f19321r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public static final C1556a f19322s = new C1556a(27);
    public final AbstractC2129d l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public List f19323m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public List f19324n;
    public int o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public byte f19325p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f19326q;

    static {
        j jVar = new j();
        f19321r = jVar;
        jVar.f19323m = Collections.emptyList();
        jVar.f19324n = Collections.emptyList();
    }

    public j() {
        this.o = -1;
        this.f19325p = (byte) -1;
        this.f19326q = -1;
        this.l = AbstractC2129d.l;
    }

    @Override // p8.u
    public final boolean b() {
        byte b10 = this.f19325p;
        if (b10 == 1) {
            return true;
        }
        if (b10 == 0) {
            return false;
        }
        this.f19325p = (byte) 1;
        return true;
    }

    @Override // p8.AbstractC2126a
    public final int c() {
        int i7 = this.f19326q;
        if (i7 != -1) {
            return i7;
        }
        int iH = 0;
        for (int i10 = 0; i10 < this.f19323m.size(); i10++) {
            iH += A2.g.h(1, (AbstractC2126a) this.f19323m.get(i10));
        }
        int iG = 0;
        for (int i11 = 0; i11 < this.f19324n.size(); i11++) {
            iG += A2.g.g(((Integer) this.f19324n.get(i11)).intValue());
        }
        int iG2 = iH + iG;
        if (!this.f19324n.isEmpty()) {
            iG2 = iG2 + 1 + A2.g.g(iG);
        }
        this.o = iG;
        int size = this.l.size() + iG2;
        this.f19326q = size;
        return size;
    }

    @Override // p8.AbstractC2126a
    public final AbstractC2134i d() {
        return f.g();
    }

    @Override // p8.AbstractC2126a
    public final AbstractC2134i e() {
        f fVarG = f.g();
        fVarG.h(this);
        return fVarG;
    }

    @Override // p8.AbstractC2126a
    public final void f(A2.g gVar) throws IOException {
        c();
        for (int i7 = 0; i7 < this.f19323m.size(); i7++) {
            gVar.E(1, (AbstractC2126a) this.f19323m.get(i7));
        }
        if (this.f19324n.size() > 0) {
            gVar.L(42);
            gVar.L(this.o);
        }
        for (int i10 = 0; i10 < this.f19324n.size(); i10++) {
            gVar.D(((Integer) this.f19324n.get(i10)).intValue());
        }
        gVar.H(this.l);
    }

    public j(C2130e c2130e, C2132g c2132g) {
        this.o = -1;
        this.f19325p = (byte) -1;
        this.f19326q = -1;
        this.f19323m = Collections.emptyList();
        this.f19324n = Collections.emptyList();
        C2128c c2128c = new C2128c();
        A2.g gVarT = A2.g.t(c2128c, 1);
        boolean z6 = false;
        int i7 = 0;
        while (!z6) {
            try {
                try {
                    int iN = c2130e.n();
                    if (iN != 0) {
                        if (iN == 10) {
                            if ((i7 & 1) != 1) {
                                this.f19323m = new ArrayList();
                                i7 |= 1;
                            }
                            this.f19323m.add(c2130e.g(i.f19310y, c2132g));
                        } else if (iN == 40) {
                            if ((i7 & 2) != 2) {
                                this.f19324n = new ArrayList();
                                i7 |= 2;
                            }
                            this.f19324n.add(Integer.valueOf(c2130e.k()));
                        } else if (iN != 42) {
                            if (!c2130e.q(iN, gVarT)) {
                            }
                        } else {
                            int iD = c2130e.d(c2130e.k());
                            if ((i7 & 2) != 2 && c2130e.b() > 0) {
                                this.f19324n = new ArrayList();
                                i7 |= 2;
                            }
                            while (c2130e.b() > 0) {
                                this.f19324n.add(Integer.valueOf(c2130e.k()));
                            }
                            c2130e.c(iD);
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
                if ((i7 & 1) == 1) {
                    this.f19323m = Collections.unmodifiableList(this.f19323m);
                }
                if ((i7 & 2) == 2) {
                    this.f19324n = Collections.unmodifiableList(this.f19324n);
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
        }
        if ((i7 & 1) == 1) {
            this.f19323m = Collections.unmodifiableList(this.f19323m);
        }
        if ((i7 & 2) == 2) {
            this.f19324n = Collections.unmodifiableList(this.f19324n);
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

    public j(f fVar) {
        this.o = -1;
        this.f19325p = (byte) -1;
        this.f19326q = -1;
        this.l = fVar.l;
    }
}
