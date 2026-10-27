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

/* JADX INFO: loaded from: classes.dex */
public final class X extends AbstractC2139n {

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public static final X f17240r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public static final C1556a f17241s = new C1556a(20);
    public final AbstractC2129d l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f17242m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public List f17243n;
    public int o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public byte f17244p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f17245q;

    static {
        X x8 = new X();
        f17240r = x8;
        x8.f17243n = Collections.emptyList();
        x8.o = -1;
    }

    public X() {
        this.f17244p = (byte) -1;
        this.f17245q = -1;
        this.l = AbstractC2129d.l;
    }

    public static C1561f i(X x8) {
        C1561f c1561fI = C1561f.i();
        c1561fI.l(x8);
        return c1561fI;
    }

    @Override // p8.u
    public final boolean b() {
        byte b10 = this.f17244p;
        if (b10 == 1) {
            return true;
        }
        if (b10 == 0) {
            return false;
        }
        for (int i7 = 0; i7 < this.f17243n.size(); i7++) {
            if (!((Q) this.f17243n.get(i7)).b()) {
                this.f17244p = (byte) 0;
                return false;
            }
        }
        this.f17244p = (byte) 1;
        return true;
    }

    @Override // p8.AbstractC2126a
    public final int c() {
        int i7 = this.f17245q;
        if (i7 != -1) {
            return i7;
        }
        int iF = 0;
        for (int i10 = 0; i10 < this.f17243n.size(); i10++) {
            iF += A2.g.h(1, (AbstractC2126a) this.f17243n.get(i10));
        }
        if ((this.f17242m & 1) == 1) {
            iF += A2.g.f(2, this.o);
        }
        int size = this.l.size() + iF;
        this.f17245q = size;
        return size;
    }

    @Override // p8.AbstractC2126a
    public final AbstractC2134i d() {
        return C1561f.i();
    }

    @Override // p8.AbstractC2126a
    public final AbstractC2134i e() {
        return i(this);
    }

    @Override // p8.AbstractC2126a
    public final void f(A2.g gVar) throws IOException {
        c();
        for (int i7 = 0; i7 < this.f17243n.size(); i7++) {
            gVar.E(1, (AbstractC2126a) this.f17243n.get(i7));
        }
        if ((this.f17242m & 1) == 1) {
            gVar.C(2, this.o);
        }
        gVar.H(this.l);
    }

    public final C1561f j() {
        return i(this);
    }

    public X(C2130e c2130e, C2132g c2132g) {
        this.f17244p = (byte) -1;
        this.f17245q = -1;
        this.f17243n = Collections.emptyList();
        this.o = -1;
        C2128c c2128c = new C2128c();
        A2.g gVarT = A2.g.t(c2128c, 1);
        boolean z6 = false;
        boolean z10 = false;
        while (!z6) {
            try {
                try {
                    try {
                        int iN = c2130e.n();
                        if (iN != 0) {
                            if (iN == 10) {
                                if (!z10) {
                                    this.f17243n = new ArrayList();
                                    z10 = true;
                                }
                                this.f17243n.add(c2130e.g(Q.f17179F, c2132g));
                            } else if (iN != 16) {
                                if (!c2130e.q(iN, gVarT)) {
                                }
                            } else {
                                this.f17242m |= 1;
                                this.o = c2130e.k();
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
                if (z10) {
                    this.f17243n = Collections.unmodifiableList(this.f17243n);
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
        if (z10) {
            this.f17243n = Collections.unmodifiableList(this.f17243n);
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

    public X(C1561f c1561f) {
        this.f17244p = (byte) -1;
        this.f17245q = -1;
        this.l = c1561f.l;
    }
}
