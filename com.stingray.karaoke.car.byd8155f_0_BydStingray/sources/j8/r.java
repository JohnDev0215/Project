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
public final class r extends AbstractC2139n {

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public static final r f17433t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public static final C1556a f17434u = new C1556a(6);
    public final AbstractC2129d l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f17435m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public EnumC1571p f17436n;
    public List o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public C1577w f17437p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public EnumC1572q f17438q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public byte f17439r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public int f17440s;

    static {
        r rVar = new r();
        f17433t = rVar;
        rVar.f17436n = EnumC1571p.RETURNS_CONSTANT;
        rVar.o = Collections.emptyList();
        rVar.f17437p = C1577w.f17458w;
        rVar.f17438q = EnumC1572q.AT_MOST_ONCE;
    }

    public r() {
        this.f17439r = (byte) -1;
        this.f17440s = -1;
        this.l = AbstractC2129d.l;
    }

    @Override // p8.u
    public final boolean b() {
        byte b10 = this.f17439r;
        if (b10 == 1) {
            return true;
        }
        if (b10 == 0) {
            return false;
        }
        for (int i7 = 0; i7 < this.o.size(); i7++) {
            if (!((C1577w) this.o.get(i7)).b()) {
                this.f17439r = (byte) 0;
                return false;
            }
        }
        if ((this.f17435m & 2) != 2 || this.f17437p.b()) {
            this.f17439r = (byte) 1;
            return true;
        }
        this.f17439r = (byte) 0;
        return false;
    }

    @Override // p8.AbstractC2126a
    public final int c() {
        int i7 = this.f17440s;
        if (i7 != -1) {
            return i7;
        }
        int iE = (this.f17435m & 1) == 1 ? A2.g.e(1, this.f17436n.l) : 0;
        for (int i10 = 0; i10 < this.o.size(); i10++) {
            iE += A2.g.h(2, (AbstractC2126a) this.o.get(i10));
        }
        if ((this.f17435m & 2) == 2) {
            iE += A2.g.h(3, this.f17437p);
        }
        if ((this.f17435m & 4) == 4) {
            iE += A2.g.e(4, this.f17438q.l);
        }
        int size = this.l.size() + iE;
        this.f17440s = size;
        return size;
    }

    @Override // p8.AbstractC2126a
    public final AbstractC2134i d() {
        return C1570o.g();
    }

    @Override // p8.AbstractC2126a
    public final AbstractC2134i e() {
        C1570o c1570oG = C1570o.g();
        c1570oG.h(this);
        return c1570oG;
    }

    @Override // p8.AbstractC2126a
    public final void f(A2.g gVar) throws IOException {
        c();
        if ((this.f17435m & 1) == 1) {
            gVar.B(1, this.f17436n.l);
        }
        for (int i7 = 0; i7 < this.o.size(); i7++) {
            gVar.E(2, (AbstractC2126a) this.o.get(i7));
        }
        if ((this.f17435m & 2) == 2) {
            gVar.E(3, this.f17437p);
        }
        if ((this.f17435m & 4) == 4) {
            gVar.B(4, this.f17438q.l);
        }
        gVar.H(this.l);
    }

    public r(C2130e c2130e, C2132g c2132g) {
        this.f17439r = (byte) -1;
        this.f17440s = -1;
        EnumC1571p enumC1571p = EnumC1571p.RETURNS_CONSTANT;
        this.f17436n = enumC1571p;
        this.o = Collections.emptyList();
        this.f17437p = C1577w.f17458w;
        EnumC1572q enumC1572q = EnumC1572q.AT_MOST_ONCE;
        this.f17438q = enumC1572q;
        C2128c c2128c = new C2128c();
        A2.g gVarT = A2.g.t(c2128c, 1);
        boolean z6 = false;
        char c = 0;
        while (!z6) {
            try {
                try {
                    try {
                        int iN = c2130e.n();
                        if (iN != 0) {
                            EnumC1572q enumC1572q2 = null;
                            EnumC1571p enumC1571p2 = null;
                            C1575u c1575uG = null;
                            if (iN == 8) {
                                int iK = c2130e.k();
                                if (iK == 0) {
                                    enumC1571p2 = enumC1571p;
                                } else if (iK == 1) {
                                    enumC1571p2 = EnumC1571p.CALLS;
                                } else if (iK == 2) {
                                    enumC1571p2 = EnumC1571p.RETURNS_NOT_NULL;
                                }
                                if (enumC1571p2 == null) {
                                    gVarT.L(iN);
                                    gVarT.L(iK);
                                } else {
                                    this.f17435m |= 1;
                                    this.f17436n = enumC1571p2;
                                }
                            } else if (iN == 18) {
                                int i7 = (c == true ? 1 : 0) & 2;
                                c = c;
                                if (i7 != 2) {
                                    this.o = new ArrayList();
                                    c = 2;
                                }
                                this.o.add(c2130e.g(C1577w.f17459x, c2132g));
                            } else if (iN == 26) {
                                if ((this.f17435m & 2) == 2) {
                                    C1577w c1577w = this.f17437p;
                                    c1577w.getClass();
                                    c1575uG = C1575u.g();
                                    c1575uG.h(c1577w);
                                }
                                C1577w c1577w2 = (C1577w) c2130e.g(C1577w.f17459x, c2132g);
                                this.f17437p = c1577w2;
                                if (c1575uG != null) {
                                    c1575uG.h(c1577w2);
                                    this.f17437p = c1575uG.f();
                                }
                                this.f17435m |= 2;
                            } else if (iN != 32) {
                                if (!c2130e.q(iN, gVarT)) {
                                }
                            } else {
                                int iK2 = c2130e.k();
                                if (iK2 == 0) {
                                    enumC1572q2 = enumC1572q;
                                } else if (iK2 == 1) {
                                    enumC1572q2 = EnumC1572q.EXACTLY_ONCE;
                                } else if (iK2 == 2) {
                                    enumC1572q2 = EnumC1572q.AT_LEAST_ONCE;
                                }
                                if (enumC1572q2 == null) {
                                    gVarT.L(iN);
                                    gVarT.L(iK2);
                                } else {
                                    this.f17435m |= 4;
                                    this.f17438q = enumC1572q2;
                                }
                            }
                        }
                        z6 = true;
                    } catch (C2142q e10) {
                        e10.l = this;
                        throw e10;
                    }
                } catch (IOException e11) {
                    C2142q c2142q = new C2142q(e11.getMessage());
                    c2142q.l = this;
                    throw c2142q;
                }
            } catch (Throwable th) {
                if (((c == true ? 1 : 0) & 2) == 2) {
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
        if (((c == true ? 1 : 0) & 2) == 2) {
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

    public r(C1570o c1570o) {
        this.f17439r = (byte) -1;
        this.f17440s = -1;
        this.l = c1570o.l;
    }
}
