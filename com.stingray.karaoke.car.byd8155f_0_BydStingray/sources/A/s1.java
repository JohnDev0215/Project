package A;

import B2.C0073m;
import android.hardware.bydauto.multimedia.BYDAutoMultimediaDevice;
import h2.AbstractC1346c;
import java.io.EOFException;
import java.io.InterruptedIOException;

/* JADX INFO: loaded from: classes.dex */
public final class s1 implements B2.s, B2.t {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public long f266m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public Object f267n;

    public /* synthetic */ s1(int i7, long j3, Object obj) {
        this.l = i7;
        this.f266m = j3;
        this.f267n = obj;
    }

    @Override // B2.s
    public void a(int i7, int i10, byte[] bArr) {
        ((C0073m) this.f267n).k(bArr, i7, i10, false);
    }

    @Override // B2.s
    public boolean c(byte[] bArr, int i7, int i10, boolean z6) {
        return ((C0073m) this.f267n).c(bArr, 0, i10, z6);
    }

    @Override // B2.s
    public long d() {
        return ((C0073m) this.f267n).f588n - this.f266m;
    }

    @Override // B2.t
    public void e() {
        ((u2.T) this.f267n).e();
    }

    @Override // B2.s
    public void h() {
        ((C0073m) this.f267n).f590q = 0;
    }

    @Override // B2.s
    public void i(int i7) {
        ((C0073m) this.f267n).i(i7);
    }

    @Override // B2.s
    public boolean k(byte[] bArr, int i7, int i10, boolean z6) {
        return ((C0073m) this.f267n).k(bArr, 0, i10, z6);
    }

    @Override // B2.s
    public long l() {
        return ((C0073m) this.f267n).l() - this.f266m;
    }

    @Override // B2.s
    public void m(int i7) throws EOFException, InterruptedIOException {
        ((C0073m) this.f267n).e(i7, false);
    }

    @Override // e2.InterfaceC1201i
    public int n(byte[] bArr, int i7, int i10) {
        return ((C0073m) this.f267n).n(bArr, i7, i10);
    }

    @Override // B2.s
    public long o() {
        return ((C0073m) this.f267n).o - this.f266m;
    }

    public void p(int i7) {
        if (i7 < 64) {
            this.f266m &= ~(1 << i7);
            return;
        }
        s1 s1Var = (s1) this.f267n;
        if (s1Var != null) {
            s1Var.p(i7 - 64);
        }
    }

    public int q(int i7) {
        s1 s1Var = (s1) this.f267n;
        if (s1Var == null) {
            if (i7 >= 64) {
                return Long.bitCount(this.f266m);
            }
            return Long.bitCount(((1 << i7) - 1) & this.f266m);
        }
        if (i7 < 64) {
            return Long.bitCount(((1 << i7) - 1) & this.f266m);
        }
        return Long.bitCount(this.f266m) + s1Var.q(i7 - 64);
    }

    public void r() {
        if (((s1) this.f267n) == null) {
            this.f267n = new s1();
        }
    }

    @Override // B2.s
    public void readFully(byte[] bArr, int i7, int i10) throws EOFException, InterruptedIOException {
        ((C0073m) this.f267n).c(bArr, i7, i10, false);
    }

    public boolean s(int i7) {
        if (i7 < 64) {
            return ((1 << i7) & this.f266m) != 0;
        }
        r();
        return ((s1) this.f267n).s(i7 - 64);
    }

    public void t(int i7, boolean z6) {
        if (i7 >= 64) {
            r();
            ((s1) this.f267n).t(i7 - 64, z6);
            return;
        }
        long j3 = this.f266m;
        boolean z10 = (Long.MIN_VALUE & j3) != 0;
        long j10 = (1 << i7) - 1;
        this.f266m = ((j3 & (~j10)) << 1) | (j3 & j10);
        if (z6) {
            y(i7);
        } else {
            p(i7);
        }
        if (z10 || ((s1) this.f267n) != null) {
            r();
            ((s1) this.f267n).t(0, z10);
        }
    }

    public String toString() {
        switch (this.l) {
            case 5:
                if (((s1) this.f267n) == null) {
                    return Long.toBinaryString(this.f266m);
                }
                return ((s1) this.f267n).toString() + "xx" + Long.toBinaryString(this.f266m);
            default:
                return super.toString();
        }
    }

    @Override // B2.t
    public B2.L u(int i7, int i10) {
        return ((u2.T) this.f267n).u(i7, i10);
    }

    public boolean v(int i7) {
        if (i7 >= 64) {
            r();
            return ((s1) this.f267n).v(i7 - 64);
        }
        long j3 = 1 << i7;
        long j10 = this.f266m;
        boolean z6 = (j10 & j3) != 0;
        long j11 = j10 & (~j3);
        this.f266m = j11;
        long j12 = j3 - 1;
        this.f266m = (j11 & j12) | Long.rotateRight((~j12) & j11, 1);
        s1 s1Var = (s1) this.f267n;
        if (s1Var != null) {
            if (s1Var.s(0)) {
                y(63);
            }
            ((s1) this.f267n).v(0);
        }
        return z6;
    }

    @Override // B2.t
    public void w(B2.F f10) {
        ((u2.T) this.f267n).w(new J2.d(this, f10, f10));
    }

    public void x() {
        this.f266m = 0L;
        s1 s1Var = (s1) this.f267n;
        if (s1Var != null) {
            s1Var.x();
        }
    }

    public void y(int i7) {
        if (i7 < 64) {
            this.f266m |= 1 << i7;
        } else {
            r();
            ((s1) this.f267n).y(i7 - 64);
        }
    }

    public s1(r9.D d) {
        this.l = 4;
        A7.m.f(d, BYDAutoMultimediaDevice.PARAM_SOURCE);
        this.f267n = d;
        this.f266m = 262144L;
    }

    public s1(C0073m c0073m, long j3) {
        this.l = 2;
        this.f267n = c0073m;
        AbstractC1346c.c(c0073m.o >= j3);
        this.f266m = j3;
    }

    public s1() {
        this.l = 5;
        this.f266m = 0L;
    }

    public s1(EnumC0017h0 enumC0017h0) {
        this.l = 0;
        this.f267n = enumC0017h0;
        this.f266m = 0L;
    }
}
