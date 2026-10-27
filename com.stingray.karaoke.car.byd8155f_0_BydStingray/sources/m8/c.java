package m8;

import j8.C1556a;
import java.io.IOException;
import p8.AbstractC2129d;
import p8.AbstractC2134i;
import p8.AbstractC2139n;
import p8.C2128c;
import p8.C2130e;
import p8.C2142q;

/* JADX INFO: loaded from: classes.dex */
public final class c extends AbstractC2139n {

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public static final c f19278r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public static final C1556a f19279s = new C1556a(25);
    public final AbstractC2129d l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f19280m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f19281n;
    public int o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public byte f19282p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f19283q;

    static {
        c cVar = new c();
        f19278r = cVar;
        cVar.f19281n = 0;
        cVar.o = 0;
    }

    public c() {
        this.f19282p = (byte) -1;
        this.f19283q = -1;
        this.l = AbstractC2129d.l;
    }

    public static a i(c cVar) {
        a aVar = new a(1);
        aVar.i(cVar);
        return aVar;
    }

    @Override // p8.u
    public final boolean b() {
        byte b10 = this.f19282p;
        if (b10 == 1) {
            return true;
        }
        if (b10 == 0) {
            return false;
        }
        this.f19282p = (byte) 1;
        return true;
    }

    @Override // p8.AbstractC2126a
    public final int c() {
        int i7 = this.f19283q;
        if (i7 != -1) {
            return i7;
        }
        int iF = (this.f19280m & 1) == 1 ? A2.g.f(1, this.f19281n) : 0;
        if ((this.f19280m & 2) == 2) {
            iF += A2.g.f(2, this.o);
        }
        int size = this.l.size() + iF;
        this.f19283q = size;
        return size;
    }

    @Override // p8.AbstractC2126a
    public final AbstractC2134i d() {
        return new a(1);
    }

    @Override // p8.AbstractC2126a
    public final AbstractC2134i e() {
        return i(this);
    }

    @Override // p8.AbstractC2126a
    public final void f(A2.g gVar) throws IOException {
        c();
        if ((this.f19280m & 1) == 1) {
            gVar.C(1, this.f19281n);
        }
        if ((this.f19280m & 2) == 2) {
            gVar.C(2, this.o);
        }
        gVar.H(this.l);
    }

    public c(C2130e c2130e) {
        this.f19282p = (byte) -1;
        this.f19283q = -1;
        boolean z6 = false;
        this.f19281n = 0;
        this.o = 0;
        C2128c c2128c = new C2128c();
        A2.g gVarT = A2.g.t(c2128c, 1);
        while (!z6) {
            try {
                try {
                    int iN = c2130e.n();
                    if (iN != 0) {
                        if (iN == 8) {
                            this.f19280m |= 1;
                            this.f19281n = c2130e.k();
                        } else if (iN != 16) {
                            if (!c2130e.q(iN, gVarT)) {
                            }
                        } else {
                            this.f19280m |= 2;
                            this.o = c2130e.k();
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

    public c(a aVar) {
        this.f19282p = (byte) -1;
        this.f19283q = -1;
        this.l = aVar.l;
    }
}
