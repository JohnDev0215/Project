package i9;

import A7.m;
import android.hardware.bydauto.multimedia.BYDAutoMultimediaDevice;
import i8.C1417b;
import java.io.IOException;
import java.util.zip.Deflater;
import r9.AbstractC2312b;
import r9.C;
import r9.C2319i;
import r9.E;
import r9.F;
import r9.H;
import r9.L;
import r9.r;

/* JADX INFO: loaded from: classes.dex */
public final class e implements H {
    public final /* synthetic */ int l = 0;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public boolean f15808m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final Object f15809n;
    public final Object o;

    public e(C2319i c2319i, Deflater deflater) {
        this.f15809n = AbstractC2312b.b(c2319i);
        this.o = deflater;
    }

    public void b(boolean z6) throws IOException {
        E eT;
        int iDeflate;
        C c = (C) this.f15809n;
        C2319i c2319i = c.f21895m;
        while (true) {
            eT = c2319i.T(1);
            Deflater deflater = (Deflater) this.o;
            byte[] bArr = eT.f21899a;
            if (z6) {
                try {
                    int i7 = eT.c;
                    iDeflate = deflater.deflate(bArr, i7, 8192 - i7, 2);
                } catch (NullPointerException e10) {
                    throw new IOException("Deflater already closed", e10);
                }
            } else {
                int i10 = eT.c;
                iDeflate = deflater.deflate(bArr, i10, 8192 - i10);
            }
            if (iDeflate > 0) {
                eT.c += iDeflate;
                c2319i.f21932m += (long) iDeflate;
                c.b();
            } else if (deflater.needsInput()) {
                break;
            }
        }
        if (eT.f21900b == eT.c) {
            c2319i.l = eT.a();
            F.a(eT);
        }
    }

    @Override // r9.H, java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws Throwable {
        switch (this.l) {
            case 0:
                if (this.f15808m) {
                    return;
                }
                this.f15808m = true;
                C1417b c1417b = (C1417b) this.o;
                c1417b.getClass();
                r rVar = (r) this.f15809n;
                L l = rVar.f21940e;
                rVar.f21940e = L.d;
                l.a();
                l.b();
                c1417b.f15784b = 3;
                return;
            default:
                Deflater deflater = (Deflater) this.o;
                if (this.f15808m) {
                    return;
                }
                try {
                    deflater.finish();
                    b(false);
                    th = null;
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    deflater.end();
                    break;
                } catch (Throwable th2) {
                    if (th == null) {
                        th = th2;
                    }
                }
                try {
                    ((C) this.f15809n).close();
                    break;
                } catch (Throwable th3) {
                    if (th == null) {
                        th = th3;
                    }
                }
                this.f15808m = true;
                if (th != null) {
                    throw th;
                }
                return;
        }
    }

    @Override // r9.H
    public final L e() {
        switch (this.l) {
            case 0:
                return (r) this.f15809n;
            default:
                return ((C) this.f15809n).l.e();
        }
    }

    @Override // r9.H, java.io.Flushable
    public final void flush() throws IOException {
        switch (this.l) {
            case 0:
                if (!this.f15808m) {
                    ((C) ((C1417b) this.o).f15786f).flush();
                    break;
                }
                break;
            default:
                b(true);
                ((C) this.f15809n).flush();
                break;
        }
    }

    @Override // r9.H
    public final void t(C2319i c2319i, long j3) throws IOException {
        switch (this.l) {
            case 0:
                m.f(c2319i, BYDAutoMultimediaDevice.PARAM_SOURCE);
                if (this.f15808m) {
                    throw new IllegalStateException("closed");
                }
                d9.b.b(c2319i.f21932m, 0L, j3);
                ((C) ((C1417b) this.o).f15786f).t(c2319i, j3);
                return;
            default:
                m.f(c2319i, BYDAutoMultimediaDevice.PARAM_SOURCE);
                AbstractC2312b.e(c2319i.f21932m, 0L, j3);
                while (j3 > 0) {
                    E e10 = c2319i.l;
                    m.c(e10);
                    int iMin = (int) Math.min(j3, e10.c - e10.f21900b);
                    ((Deflater) this.o).setInput(e10.f21899a, e10.f21900b, iMin);
                    b(false);
                    long j10 = iMin;
                    c2319i.f21932m -= j10;
                    int i7 = e10.f21900b + iMin;
                    e10.f21900b = i7;
                    if (i7 == e10.c) {
                        c2319i.l = e10.a();
                        F.a(e10);
                    }
                    j3 -= j10;
                }
                return;
        }
    }

    public String toString() {
        switch (this.l) {
            case 1:
                return "DeflaterSink(" + ((C) this.f15809n) + ')';
            default:
                return super.toString();
        }
    }

    public e(C1417b c1417b) {
        this.o = c1417b;
        this.f15809n = new r(((C) c1417b.f15786f).l.e());
    }
}
