package q9;

import A7.m;
import h8.AbstractC1387d;
import java.io.Closeable;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Random;
import java.util.zip.Deflater;
import n7.AbstractC1985B;
import r9.AbstractC2312b;
import r9.C;
import r9.C2317g;
import r9.C2319i;
import r9.C2322l;
import r9.E;

/* JADX INFO: loaded from: classes.dex */
public final class j implements Closeable {
    public final C l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Random f21399m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final boolean f21400n;
    public final boolean o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final long f21401p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final C2319i f21402q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final C2319i f21403r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public boolean f21404s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public a f21405t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final byte[] f21406u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final C2317g f21407v;

    public j(C c, Random random, boolean z6, boolean z10, long j3) {
        m.f(c, "sink");
        this.l = c;
        this.f21399m = random;
        this.f21400n = z6;
        this.o = z10;
        this.f21401p = j3;
        this.f21402q = new C2319i();
        this.f21403r = c.f21895m;
        this.f21406u = new byte[4];
        this.f21407v = new C2317g();
    }

    public final void b(int i7, C2322l c2322l) throws IOException {
        if (this.f21404s) {
            throw new IOException("closed");
        }
        int iD = c2322l.d();
        if (iD > 125) {
            throw new IllegalArgumentException("Payload size must be less than or equal to 125");
        }
        C2319i c2319i = this.f21403r;
        c2319i.X(i7 | 128);
        c2319i.X(iD | 128);
        byte[] bArr = this.f21406u;
        m.c(bArr);
        this.f21399m.nextBytes(bArr);
        c2319i.V(bArr);
        if (iD > 0) {
            long j3 = c2319i.f21932m;
            c2319i.U(c2322l);
            C2317g c2317g = this.f21407v;
            m.c(c2317g);
            c2319i.y(c2317g);
            c2317g.g(j3);
            AbstractC1387d.x(c2317g, bArr);
            c2317g.close();
        }
        this.l.flush();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws Throwable {
        a aVar = this.f21405t;
        if (aVar != null) {
            aVar.close();
        }
    }

    public final void g(int i7, C2322l c2322l) throws IllegalAccessException, IOException, InvocationTargetException {
        if (this.f21404s) {
            throw new IOException("closed");
        }
        C2319i c2319i = this.f21402q;
        c2319i.U(c2322l);
        int i10 = i7 | 128;
        if (this.f21400n && c2322l.l.length >= this.f21401p) {
            a aVar = this.f21405t;
            if (aVar == null) {
                aVar = new a(0, this.o);
                this.f21405t = aVar;
            }
            C2319i c2319i2 = aVar.f21353n;
            if (c2319i2.f21932m != 0) {
                throw new IllegalArgumentException("Failed requirement.");
            }
            if (aVar.f21352m) {
                ((Deflater) aVar.o).reset();
            }
            long j3 = c2319i.f21932m;
            i9.e eVar = (i9.e) aVar.f21354p;
            eVar.t(c2319i, j3);
            eVar.flush();
            C2322l c2322l2 = b.f21355a;
            if (c2319i2.q(c2319i2.f21932m - ((long) c2322l2.l.length), c2322l2)) {
                long j10 = c2319i2.f21932m - ((long) 4);
                C2317g c2317gY = c2319i2.y(AbstractC2312b.f21914a);
                try {
                    c2317gY.b(j10);
                    AbstractC1985B.c(c2317gY, null);
                } catch (Throwable th) {
                    try {
                        throw th;
                    } catch (Throwable th2) {
                        AbstractC1985B.c(c2317gY, th);
                        throw th2;
                    }
                }
            } else {
                c2319i2.X(0);
            }
            c2319i.t(c2319i2, c2319i2.f21932m);
            i10 = i7 | 192;
        }
        long j11 = c2319i.f21932m;
        C2319i c2319i3 = this.f21403r;
        c2319i3.X(i10);
        if (j11 <= 125) {
            c2319i3.X(((int) j11) | 128);
        } else if (j11 <= 65535) {
            c2319i3.X(254);
            c2319i3.b0((int) j11);
        } else {
            c2319i3.X(255);
            E eT = c2319i3.T(8);
            int i11 = eT.c;
            byte[] bArr = eT.f21899a;
            bArr[i11] = (byte) ((j11 >>> 56) & 255);
            bArr[i11 + 1] = (byte) ((j11 >>> 48) & 255);
            bArr[i11 + 2] = (byte) ((j11 >>> 40) & 255);
            bArr[i11 + 3] = (byte) ((j11 >>> 32) & 255);
            bArr[i11 + 4] = (byte) ((j11 >>> 24) & 255);
            bArr[i11 + 5] = (byte) ((j11 >>> 16) & 255);
            bArr[i11 + 6] = (byte) ((j11 >>> 8) & 255);
            bArr[i11 + 7] = (byte) (j11 & 255);
            eT.c = i11 + 8;
            c2319i3.f21932m += 8;
        }
        byte[] bArr2 = this.f21406u;
        m.c(bArr2);
        this.f21399m.nextBytes(bArr2);
        c2319i3.V(bArr2);
        if (j11 > 0) {
            C2317g c2317g = this.f21407v;
            m.c(c2317g);
            c2319i.y(c2317g);
            c2317g.g(0L);
            AbstractC1387d.x(c2317g, bArr2);
            c2317g.close();
        }
        c2319i3.t(c2319i, j11);
        C c = this.l;
        if (c.f21896n) {
            throw new IllegalStateException("closed");
        }
        C2319i c2319i4 = c.f21895m;
        long j12 = c2319i4.f21932m;
        if (j12 > 0) {
            c.l.t(c2319i4, j12);
        }
    }
}
