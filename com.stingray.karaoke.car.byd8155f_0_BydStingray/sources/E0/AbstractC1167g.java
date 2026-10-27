package e0;

import l3.P;
import z7.InterfaceC2782k;

/* JADX INFO: renamed from: e0.g, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC1167g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public C1171k f13706a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f13707b;
    public boolean c;
    public int d;

    public AbstractC1167g(int i7, C1171k c1171k) {
        int iA;
        int iNumberOfTrailingZeros;
        this.f13706a = c1171k;
        this.f13707b = i7;
        if (i7 != 0) {
            C1171k c1171kE = e();
            P p10 = AbstractC1174n.f13720a;
            int[] iArr = c1171kE.o;
            if (iArr != null) {
                i7 = iArr[0];
            } else {
                long j3 = c1171kE.f13714m;
                int i10 = c1171kE.f13715n;
                if (j3 != 0) {
                    iNumberOfTrailingZeros = Long.numberOfTrailingZeros(j3);
                } else {
                    long j10 = c1171kE.l;
                    if (j10 != 0) {
                        i10 += 64;
                        iNumberOfTrailingZeros = Long.numberOfTrailingZeros(j10);
                    }
                }
                i7 = iNumberOfTrailingZeros + i10;
            }
            synchronized (AbstractC1174n.f13721b) {
                iA = AbstractC1174n.f13722e.a(i7);
            }
        } else {
            iA = -1;
        }
        this.d = iA;
    }

    public static void p(AbstractC1167g abstractC1167g) {
        AbstractC1174n.f13720a.D(abstractC1167g);
    }

    public final void a() {
        synchronized (AbstractC1174n.f13721b) {
            b();
            o();
        }
    }

    public void b() {
        AbstractC1174n.c = AbstractC1174n.c.l(d());
    }

    public abstract void c();

    public int d() {
        return this.f13707b;
    }

    public C1171k e() {
        return this.f13706a;
    }

    public abstract InterfaceC2782k f();

    public abstract boolean g();

    public int h() {
        return 0;
    }

    public abstract InterfaceC2782k i();

    public final AbstractC1167g j() {
        P p10 = AbstractC1174n.f13720a;
        AbstractC1167g abstractC1167g = (AbstractC1167g) p10.p();
        p10.D(this);
        return abstractC1167g;
    }

    public abstract void k();

    public abstract void l();

    public abstract void m();

    public abstract void n(InterfaceC1154A interfaceC1154A);

    public void o() {
        int i7 = this.d;
        if (i7 >= 0) {
            AbstractC1174n.u(i7);
            this.d = -1;
        }
    }

    public void q(int i7) {
        this.f13707b = i7;
    }

    public void r(C1171k c1171k) {
        this.f13706a = c1171k;
    }

    public void s(int i7) {
        throw new IllegalStateException("Updating write count is not supported for this snapshot");
    }

    public abstract AbstractC1167g t(InterfaceC2782k interfaceC2782k);
}
