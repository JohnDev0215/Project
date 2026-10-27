package j9;

import android.hardware.bydauto.multimedia.BYDAutoMultimediaDevice;
import r9.C2319i;
import r9.H;
import r9.L;

/* JADX INFO: loaded from: classes.dex */
public final class r implements H {
    public final boolean l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final C2319i f17573m = new C2319i();

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public boolean f17574n;
    public final /* synthetic */ u o;

    public r(u uVar, boolean z6) {
        this.o = uVar;
        this.l = z6;
    }

    public final void b(boolean z6) {
        long jMin;
        boolean z10;
        u uVar = this.o;
        synchronized (uVar) {
            uVar.l.i();
            while (uVar.f17582e >= uVar.f17583f && !this.l && !this.f17574n) {
                try {
                    synchronized (uVar) {
                        int i7 = uVar.f17589m;
                        if (i7 != 0) {
                            break;
                        } else {
                            uVar.k();
                        }
                    }
                } finally {
                    uVar.l.l();
                }
            }
            uVar.l.l();
            uVar.b();
            jMin = Math.min(uVar.f17583f - uVar.f17582e, this.f17573m.f21932m);
            uVar.f17582e += jMin;
            z10 = z6 && jMin == this.f17573m.f21932m;
        }
        this.o.l.i();
        try {
            u uVar2 = this.o;
            uVar2.f17581b.m(uVar2.f17580a, z10, this.f17573m, jMin);
        } finally {
            uVar = this.o;
        }
    }

    @Override // r9.H, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        boolean z6;
        u uVar = this.o;
        byte[] bArr = d9.b.f13645a;
        synchronized (uVar) {
            if (this.f17574n) {
                return;
            }
            synchronized (uVar) {
                z6 = uVar.f17589m == 0;
            }
            u uVar2 = this.o;
            if (!uVar2.f17587j.l) {
                if (this.f17573m.f21932m > 0) {
                    while (this.f17573m.f21932m > 0) {
                        b(true);
                    }
                } else if (z6) {
                    uVar2.f17581b.m(uVar2.f17580a, true, null, 0L);
                }
            }
            synchronized (this.o) {
                this.f17574n = true;
            }
            this.o.f17581b.flush();
            this.o.a();
        }
    }

    @Override // r9.H
    public final L e() {
        return this.o.l;
    }

    @Override // r9.H, java.io.Flushable
    public final void flush() {
        u uVar = this.o;
        byte[] bArr = d9.b.f13645a;
        synchronized (uVar) {
            uVar.b();
        }
        while (this.f17573m.f21932m > 0) {
            b(false);
            this.o.f17581b.flush();
        }
    }

    @Override // r9.H
    public final void t(C2319i c2319i, long j3) {
        A7.m.f(c2319i, BYDAutoMultimediaDevice.PARAM_SOURCE);
        byte[] bArr = d9.b.f13645a;
        C2319i c2319i2 = this.f17573m;
        c2319i2.t(c2319i, j3);
        while (c2319i2.f21932m >= 16384) {
            b(false);
        }
    }
}
