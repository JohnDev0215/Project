package j8;

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

/* JADX INFO: renamed from: j8.g, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1562g extends AbstractC2139n {

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public static final C1562g f17338r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public static final C1556a f17339s = new C1556a(0);
    public final AbstractC2129d l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f17340m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f17341n;
    public List o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public byte f17342p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f17343q;

    static {
        C1562g c1562g = new C1562g();
        f17338r = c1562g;
        c1562g.f17341n = 0;
        c1562g.o = Collections.emptyList();
    }

    public C1562g() {
        this.f17342p = (byte) -1;
        this.f17343q = -1;
        this.l = AbstractC2129d.l;
    }

    @Override // p8.u
    public final boolean b() {
        byte b10 = this.f17342p;
        if (b10 == 1) {
            return true;
        }
        if (b10 == 0) {
            return false;
        }
        if ((this.f17340m & 1) != 1) {
            this.f17342p = (byte) 0;
            return false;
        }
        for (int i7 = 0; i7 < this.o.size(); i7++) {
            if (!((C1560e) this.o.get(i7)).b()) {
                this.f17342p = (byte) 0;
                return false;
            }
        }
        this.f17342p = (byte) 1;
        return true;
    }

    @Override // p8.AbstractC2126a
    public final int c() {
        int i7 = this.f17343q;
        if (i7 != -1) {
            return i7;
        }
        int iF = (this.f17340m & 1) == 1 ? A2.g.f(1, this.f17341n) : 0;
        for (int i10 = 0; i10 < this.o.size(); i10++) {
            iF += A2.g.h(2, (AbstractC2126a) this.o.get(i10));
        }
        int size = this.l.size() + iF;
        this.f17343q = size;
        return size;
    }

    @Override // p8.AbstractC2126a
    public final AbstractC2134i d() {
        C1561f c1561f = new C1561f(0);
        c1561f.o = Collections.emptyList();
        return c1561f;
    }

    @Override // p8.AbstractC2126a
    public final AbstractC2134i e() {
        C1561f c1561f = new C1561f(0);
        c1561f.o = Collections.emptyList();
        c1561f.k(this);
        return c1561f;
    }

    @Override // p8.AbstractC2126a
    public final void f(A2.g gVar) throws IOException {
        c();
        if ((this.f17340m & 1) == 1) {
            gVar.C(1, this.f17341n);
        }
        for (int i7 = 0; i7 < this.o.size(); i7++) {
            gVar.E(2, (AbstractC2126a) this.o.get(i7));
        }
        gVar.H(this.l);
    }

    public C1562g(C2130e c2130e, C2132g c2132g) {
        this.f17342p = (byte) -1;
        this.f17343q = -1;
        boolean z6 = false;
        this.f17341n = 0;
        this.o = Collections.emptyList();
        C2128c c2128c = new C2128c();
        A2.g gVarT = A2.g.t(c2128c, 1);
        char c = 0;
        while (!z6) {
            try {
                try {
                    try {
                        int iN = c2130e.n();
                        if (iN != 0) {
                            if (iN == 8) {
                                this.f17340m |= 1;
                                this.f17341n = c2130e.k();
                            } else if (iN != 18) {
                                if (!c2130e.q(iN, gVarT)) {
                                }
                            } else {
                                if ((c & 2) != 2) {
                                    this.o = new ArrayList();
                                    c = 2;
                                }
                                this.o.add(c2130e.g(C1560e.f17325s, c2132g));
                            }
                        }
                        z6 = true;
                    } catch (IOException e10) {
                        C2142q c2142q = new C2142q(e10.getMessage());
                        c2142q.l = this;
                        throw c2142q;
                    }
                } catch (C2142q e11) {
                    e11.l = this;
                    throw e11;
                }
            } catch (Throwable th) {
                if ((c & 2) == 2) {
                    this.o = Collections.unmodifiableList(this.o);
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
        if ((c & 2) == 2) {
            this.o = Collections.unmodifiableList(this.o);
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

    public C1562g(C1561f c1561f) {
        this.f17342p = (byte) -1;
        this.f17343q = -1;
        this.l = c1561f.l;
    }
}
