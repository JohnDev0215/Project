package j8;

import java.io.IOException;
import p8.AbstractC2129d;
import p8.AbstractC2134i;
import p8.AbstractC2139n;
import p8.C2128c;
import p8.C2130e;
import p8.C2142q;

/* JADX INFO: loaded from: classes.dex */
public final class J extends AbstractC2139n {

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public static final J f17136s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public static final C1556a f17137t = new C1556a(14);
    public final AbstractC2129d l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f17138m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f17139n;
    public int o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public I f17140p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public byte f17141q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public int f17142r;

    static {
        J j3 = new J();
        f17136s = j3;
        j3.f17139n = -1;
        j3.o = 0;
        j3.f17140p = I.PACKAGE;
    }

    public J() {
        this.f17141q = (byte) -1;
        this.f17142r = -1;
        this.l = AbstractC2129d.l;
    }

    @Override // p8.u
    public final boolean b() {
        byte b10 = this.f17141q;
        if (b10 == 1) {
            return true;
        }
        if (b10 == 0) {
            return false;
        }
        if ((this.f17138m & 2) == 2) {
            this.f17141q = (byte) 1;
            return true;
        }
        this.f17141q = (byte) 0;
        return false;
    }

    @Override // p8.AbstractC2126a
    public final int c() {
        int i7 = this.f17142r;
        if (i7 != -1) {
            return i7;
        }
        int iF = (this.f17138m & 1) == 1 ? A2.g.f(1, this.f17139n) : 0;
        if ((this.f17138m & 2) == 2) {
            iF += A2.g.f(2, this.o);
        }
        if ((this.f17138m & 4) == 4) {
            iF += A2.g.e(3, this.f17140p.l);
        }
        int size = this.l.size() + iF;
        this.f17142r = size;
        return size;
    }

    @Override // p8.AbstractC2126a
    public final AbstractC2134i d() {
        return H.g();
    }

    @Override // p8.AbstractC2126a
    public final AbstractC2134i e() {
        H hG = H.g();
        hG.h(this);
        return hG;
    }

    @Override // p8.AbstractC2126a
    public final void f(A2.g gVar) throws IOException {
        c();
        if ((this.f17138m & 1) == 1) {
            gVar.C(1, this.f17139n);
        }
        if ((this.f17138m & 2) == 2) {
            gVar.C(2, this.o);
        }
        if ((this.f17138m & 4) == 4) {
            gVar.B(3, this.f17140p.l);
        }
        gVar.H(this.l);
    }

    public J(C2130e c2130e) {
        I i7;
        this.f17141q = (byte) -1;
        this.f17142r = -1;
        this.f17139n = -1;
        boolean z6 = false;
        this.o = 0;
        I i10 = I.PACKAGE;
        this.f17140p = i10;
        C2128c c2128c = new C2128c();
        A2.g gVarT = A2.g.t(c2128c, 1);
        while (!z6) {
            try {
                try {
                    try {
                        int iN = c2130e.n();
                        if (iN != 0) {
                            if (iN == 8) {
                                this.f17138m |= 1;
                                this.f17139n = c2130e.k();
                            } else if (iN == 16) {
                                this.f17138m |= 2;
                                this.o = c2130e.k();
                            } else if (iN != 24) {
                                if (!c2130e.q(iN, gVarT)) {
                                }
                            } else {
                                int iK = c2130e.k();
                                if (iK == 0) {
                                    i7 = I.CLASS;
                                } else if (iK != 1) {
                                    i7 = iK != 2 ? null : I.LOCAL;
                                } else {
                                    i7 = i10;
                                }
                                if (i7 == null) {
                                    gVarT.L(iN);
                                    gVarT.L(iK);
                                } else {
                                    this.f17138m |= 4;
                                    this.f17140p = i7;
                                }
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

    public J(H h10) {
        this.f17141q = (byte) -1;
        this.f17142r = -1;
        this.l = h10.l;
    }
}
