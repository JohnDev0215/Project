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

/* JADX INFO: renamed from: j8.d, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1559d extends AbstractC2139n {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public static final C1559d f17300A;
    public static final C1556a B = new C1556a(2);
    public final AbstractC2129d l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f17301m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public EnumC1558c f17302n;
    public long o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public float f17303p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public double f17304q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public int f17305r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public int f17306s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public int f17307t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public C1562g f17308u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public List f17309v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public int f17310w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public int f17311x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public byte f17312y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public int f17313z;

    static {
        C1559d c1559d = new C1559d();
        f17300A = c1559d;
        c1559d.i();
    }

    public C1559d() {
        this.f17312y = (byte) -1;
        this.f17313z = -1;
        this.l = AbstractC2129d.l;
    }

    @Override // p8.u
    public final boolean b() {
        byte b10 = this.f17312y;
        if (b10 == 1) {
            return true;
        }
        if (b10 == 0) {
            return false;
        }
        if ((this.f17301m & 128) == 128 && !this.f17308u.b()) {
            this.f17312y = (byte) 0;
            return false;
        }
        for (int i7 = 0; i7 < this.f17309v.size(); i7++) {
            if (!((C1559d) this.f17309v.get(i7)).b()) {
                this.f17312y = (byte) 0;
                return false;
            }
        }
        this.f17312y = (byte) 1;
        return true;
    }

    @Override // p8.AbstractC2126a
    public final int c() {
        int i7 = this.f17313z;
        if (i7 != -1) {
            return i7;
        }
        int iE = (this.f17301m & 1) == 1 ? A2.g.e(1, this.f17302n.l) : 0;
        if ((this.f17301m & 2) == 2) {
            long j3 = this.o;
            iE += A2.g.k((j3 >> 63) ^ (j3 << 1)) + A2.g.l(2);
        }
        if ((this.f17301m & 4) == 4) {
            iE += A2.g.l(3) + 4;
        }
        if ((this.f17301m & 8) == 8) {
            iE += A2.g.l(4) + 8;
        }
        if ((this.f17301m & 16) == 16) {
            iE += A2.g.f(5, this.f17305r);
        }
        if ((this.f17301m & 32) == 32) {
            iE += A2.g.f(6, this.f17306s);
        }
        if ((this.f17301m & 64) == 64) {
            iE += A2.g.f(7, this.f17307t);
        }
        if ((this.f17301m & 128) == 128) {
            iE += A2.g.h(8, this.f17308u);
        }
        for (int i10 = 0; i10 < this.f17309v.size(); i10++) {
            iE += A2.g.h(9, (AbstractC2126a) this.f17309v.get(i10));
        }
        if ((this.f17301m & 512) == 512) {
            iE += A2.g.f(10, this.f17311x);
        }
        if ((this.f17301m & 256) == 256) {
            iE += A2.g.f(11, this.f17310w);
        }
        int size = this.l.size() + iE;
        this.f17313z = size;
        return size;
    }

    @Override // p8.AbstractC2126a
    public final AbstractC2134i d() {
        return C1557b.g();
    }

    @Override // p8.AbstractC2126a
    public final AbstractC2134i e() {
        C1557b c1557bG = C1557b.g();
        c1557bG.h(this);
        return c1557bG;
    }

    @Override // p8.AbstractC2126a
    public final void f(A2.g gVar) throws IOException {
        c();
        if ((this.f17301m & 1) == 1) {
            gVar.B(1, this.f17302n.l);
        }
        if ((this.f17301m & 2) == 2) {
            long j3 = this.o;
            gVar.N(2, 0);
            gVar.M((j3 >> 63) ^ (j3 << 1));
        }
        if ((this.f17301m & 4) == 4) {
            float f10 = this.f17303p;
            gVar.N(3, 5);
            gVar.J(Float.floatToRawIntBits(f10));
        }
        if ((this.f17301m & 8) == 8) {
            double d = this.f17304q;
            gVar.N(4, 1);
            gVar.K(Double.doubleToRawLongBits(d));
        }
        if ((this.f17301m & 16) == 16) {
            gVar.C(5, this.f17305r);
        }
        if ((this.f17301m & 32) == 32) {
            gVar.C(6, this.f17306s);
        }
        if ((this.f17301m & 64) == 64) {
            gVar.C(7, this.f17307t);
        }
        if ((this.f17301m & 128) == 128) {
            gVar.E(8, this.f17308u);
        }
        for (int i7 = 0; i7 < this.f17309v.size(); i7++) {
            gVar.E(9, (AbstractC2126a) this.f17309v.get(i7));
        }
        if ((this.f17301m & 512) == 512) {
            gVar.C(10, this.f17311x);
        }
        if ((this.f17301m & 256) == 256) {
            gVar.C(11, this.f17310w);
        }
        gVar.H(this.l);
    }

    public final void i() {
        this.f17302n = EnumC1558c.BYTE;
        this.o = 0L;
        this.f17303p = 0.0f;
        this.f17304q = 0.0d;
        this.f17305r = 0;
        this.f17306s = 0;
        this.f17307t = 0;
        this.f17308u = C1562g.f17338r;
        this.f17309v = Collections.emptyList();
        this.f17310w = 0;
        this.f17311x = 0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v0 */
    /* JADX WARN: Type inference failed for: r5v1 */
    /* JADX WARN: Type inference failed for: r5v2, types: [boolean] */
    public C1559d(C2130e c2130e, C2132g c2132g) {
        C1561f c1561f;
        this.f17312y = (byte) -1;
        this.f17313z = -1;
        i();
        C2128c c2128c = new C2128c();
        A2.g gVarT = A2.g.t(c2128c, 1);
        boolean z6 = false;
        char c = 0;
        while (true) {
            ?? Q9 = 256;
            if (!z6) {
                try {
                    try {
                        int iN = c2130e.n();
                        switch (iN) {
                            case 0:
                                z6 = true;
                                break;
                            case 8:
                                int iK = c2130e.k();
                                EnumC1558c enumC1558cB = EnumC1558c.b(iK);
                                if (enumC1558cB == null) {
                                    gVarT.L(iN);
                                    gVarT.L(iK);
                                } else {
                                    this.f17301m |= 1;
                                    this.f17302n = enumC1558cB;
                                }
                                break;
                            case 16:
                                this.f17301m |= 2;
                                long jL = c2130e.l();
                                this.o = (-(jL & 1)) ^ (jL >>> 1);
                                break;
                            case 29:
                                this.f17301m |= 4;
                                this.f17303p = Float.intBitsToFloat(c2130e.i());
                                break;
                            case 33:
                                this.f17301m |= 8;
                                this.f17304q = Double.longBitsToDouble(c2130e.j());
                                break;
                            case 40:
                                this.f17301m |= 16;
                                this.f17305r = c2130e.k();
                                break;
                            case 48:
                                this.f17301m |= 32;
                                this.f17306s = c2130e.k();
                                break;
                            case 56:
                                this.f17301m |= 64;
                                this.f17307t = c2130e.k();
                                break;
                            case 66:
                                if ((this.f17301m & 128) == 128) {
                                    C1562g c1562g = this.f17308u;
                                    c1562g.getClass();
                                    c1561f = new C1561f(0);
                                    c1561f.o = Collections.emptyList();
                                    c1561f.k(c1562g);
                                } else {
                                    c1561f = null;
                                }
                                C1562g c1562g2 = (C1562g) c2130e.g(C1562g.f17339s, c2132g);
                                this.f17308u = c1562g2;
                                if (c1561f != null) {
                                    c1561f.k(c1562g2);
                                    this.f17308u = c1561f.g();
                                }
                                this.f17301m |= 128;
                                break;
                            case 74:
                                if ((c & 256) != 256) {
                                    this.f17309v = new ArrayList();
                                    c = 256;
                                }
                                this.f17309v.add(c2130e.g(B, c2132g));
                                break;
                            case 80:
                                this.f17301m |= 512;
                                this.f17311x = c2130e.k();
                                break;
                            case 88:
                                this.f17301m |= 256;
                                this.f17310w = c2130e.k();
                                break;
                            default:
                                Q9 = c2130e.q(iN, gVarT);
                                if (Q9 == 0) {
                                    z6 = true;
                                }
                                break;
                        }
                    } catch (Throwable th) {
                        if ((c & 256) == Q9) {
                            this.f17309v = Collections.unmodifiableList(this.f17309v);
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
            } else {
                if ((c & 256) == 256) {
                    this.f17309v = Collections.unmodifiableList(this.f17309v);
                }
                try {
                    gVarT.n();
                } catch (IOException unused2) {
                } catch (Throwable th3) {
                    this.l = c2128c.i();
                    throw th3;
                }
                this.l = c2128c.i();
                return;
            }
        }
    }

    public C1559d(C1557b c1557b) {
        this.f17312y = (byte) -1;
        this.f17313z = -1;
        this.l = c1557b.l;
    }
}
