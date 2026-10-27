package i9;

import A.s1;
import A7.m;
import Q8.h;
import Q8.o;
import c9.C;
import c9.u;
import c9.w;
import g1.AbstractC1274a;
import i8.C1417b;
import java.io.IOException;
import java.net.ProtocolException;
import java.util.concurrent.TimeUnit;
import r9.C2319i;
import r9.D;

/* JADX INFO: loaded from: classes.dex */
public final class c extends a {
    public final w o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public long f15804p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public boolean f15805q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ C1417b f15806r;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public c(C1417b c1417b, w wVar) {
        super(c1417b);
        m.f(wVar, "url");
        this.f15806r = c1417b;
        this.o = wVar;
        this.f15804p = -1L;
        this.f15805q = true;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        if (this.f15800m) {
            return;
        }
        if (this.f15805q && !d9.b.h(this, TimeUnit.MILLISECONDS)) {
            ((g9.m) this.f15806r.d).k();
            b();
        }
        this.f15800m = true;
    }

    @Override // i9.a, r9.J
    public final long p(C2319i c2319i, long j3) throws IOException {
        C2319i c2319i2;
        long j10;
        byte bK;
        m.f(c2319i, "sink");
        if (j3 < 0) {
            throw new IllegalArgumentException(M5.b.f("byteCount < 0: ", j3).toString());
        }
        if (this.f15800m) {
            throw new IllegalStateException("closed");
        }
        if (!this.f15805q) {
            return -1L;
        }
        long j11 = this.f15804p;
        C1417b c1417b = this.f15806r;
        if (j11 == 0 || j11 == -1) {
            if (j11 != -1) {
                ((D) c1417b.f15785e).G(Long.MAX_VALUE);
            }
            try {
                D d = (D) c1417b.f15785e;
                d.I(1L);
                int i7 = 0;
                while (true) {
                    int i10 = i7 + 1;
                    boolean zL = d.l(i10);
                    c2319i2 = d.f21897m;
                    if (!zL) {
                        break;
                    }
                    bK = c2319i2.k(i7);
                    if ((bK < 48 || bK > 57) && ((bK < 97 || bK > 102) && (bK < 65 || bK > 70))) {
                        break;
                    }
                    i7 = i10;
                }
                if (i7 == 0) {
                    AbstractC1274a.y(16);
                    String string = Integer.toString(bK, 16);
                    m.e(string, "toString(...)");
                    throw new NumberFormatException("Expected leading [0-9a-fA-F] character but was 0x".concat(string));
                }
                this.f15804p = c2319i2.K();
                String string2 = h.g1(((D) c1417b.f15785e).G(Long.MAX_VALUE)).toString();
                if (this.f15804p < 0 || (string2.length() > 0 && !o.z0(string2, ";", false))) {
                    throw new ProtocolException("expected chunk size and optional extensions but was \"" + this.f15804p + string2 + '\"');
                }
                if (this.f15804p == 0) {
                    this.f15805q = false;
                    s1 s1Var = (s1) c1417b.f15787g;
                    s1Var.getClass();
                    A7.D d10 = new A7.D(5, false);
                    while (true) {
                        String strG = ((D) s1Var.f267n).G(s1Var.f266m);
                        s1Var.f266m -= (long) strG.length();
                        if (strG.length() == 0) {
                            break;
                        }
                        d10.h(strG);
                    }
                    c1417b.f15788h = d10.m();
                    C c = (C) c1417b.c;
                    m.c(c);
                    u uVar = (u) c1417b.f15788h;
                    m.c(uVar);
                    h9.d.b(c.f12486u, this.o, uVar);
                    b();
                }
                if (!this.f15805q) {
                    return -1L;
                }
                j10 = -1;
            } catch (NumberFormatException e10) {
                throw new ProtocolException(e10.getMessage());
            }
        } else {
            j10 = -1;
        }
        long jP = super.p(c2319i, Math.min(j3, this.f15804p));
        if (jP != j10) {
            this.f15804p -= jP;
            return jP;
        }
        ((g9.m) c1417b.d).k();
        ProtocolException protocolException = new ProtocolException("unexpected end of stream");
        b();
        throw protocolException;
    }
}
