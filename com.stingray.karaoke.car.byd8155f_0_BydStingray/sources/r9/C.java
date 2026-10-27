package r9;

import android.hardware.bydauto.multimedia.BYDAutoMultimediaDevice;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes.dex */
public final class C implements InterfaceC2320j {
    public final H l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final C2319i f21895m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public boolean f21896n;

    public C(H h10) {
        A7.m.f(h10, "sink");
        this.l = h10;
        this.f21895m = new C2319i();
    }

    @Override // r9.InterfaceC2320j
    public final InterfaceC2320j A(C2322l c2322l) {
        A7.m.f(c2322l, "byteString");
        if (this.f21896n) {
            throw new IllegalStateException("closed");
        }
        this.f21895m.U(c2322l);
        b();
        return this;
    }

    @Override // r9.InterfaceC2320j
    public final InterfaceC2320j B(int i7, int i10, byte[] bArr) {
        if (this.f21896n) {
            throw new IllegalStateException("closed");
        }
        this.f21895m.W(bArr, i7, i10);
        b();
        return this;
    }

    @Override // r9.InterfaceC2320j
    public final InterfaceC2320j D(String str) {
        A7.m.f(str, "string");
        if (this.f21896n) {
            throw new IllegalStateException("closed");
        }
        this.f21895m.c0(str);
        b();
        return this;
    }

    @Override // r9.InterfaceC2320j
    public final InterfaceC2320j E(long j3) {
        if (this.f21896n) {
            throw new IllegalStateException("closed");
        }
        this.f21895m.Y(j3);
        b();
        return this;
    }

    public final InterfaceC2320j b() {
        if (this.f21896n) {
            throw new IllegalStateException("closed");
        }
        C2319i c2319i = this.f21895m;
        long jG = c2319i.g();
        if (jG > 0) {
            this.l.t(c2319i, jG);
        }
        return this;
    }

    @Override // r9.InterfaceC2320j
    public final C2319i c() {
        return this.f21895m;
    }

    @Override // r9.H, java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws Throwable {
        H h10 = this.l;
        if (this.f21896n) {
            return;
        }
        try {
            C2319i c2319i = this.f21895m;
            long j3 = c2319i.f21932m;
            if (j3 > 0) {
                h10.t(c2319i, j3);
            }
            th = null;
        } catch (Throwable th) {
            th = th;
        }
        try {
            h10.close();
        } catch (Throwable th2) {
            if (th == null) {
                th = th2;
            }
        }
        this.f21896n = true;
        if (th != null) {
            throw th;
        }
    }

    @Override // r9.H
    public final L e() {
        return this.l.e();
    }

    @Override // r9.H, java.io.Flushable
    public final void flush() {
        if (this.f21896n) {
            throw new IllegalStateException("closed");
        }
        C2319i c2319i = this.f21895m;
        long j3 = c2319i.f21932m;
        H h10 = this.l;
        if (j3 > 0) {
            h10.t(c2319i, j3);
        }
        h10.flush();
    }

    public final InterfaceC2320j g(int i7) {
        if (this.f21896n) {
            throw new IllegalStateException("closed");
        }
        this.f21895m.a0(i7);
        b();
        return this;
    }

    @Override // java.nio.channels.Channel
    public final boolean isOpen() {
        return !this.f21896n;
    }

    @Override // r9.InterfaceC2320j
    public final long r(J j3) {
        long j10 = 0;
        while (true) {
            long jP = ((C2314d) j3).p(this.f21895m, 8192L);
            if (jP == -1) {
                return j10;
            }
            j10 += jP;
            b();
        }
    }

    @Override // r9.InterfaceC2320j
    public final InterfaceC2320j s(int i7) {
        if (this.f21896n) {
            throw new IllegalStateException("closed");
        }
        this.f21895m.X(i7);
        b();
        return this;
    }

    @Override // r9.H
    public final void t(C2319i c2319i, long j3) {
        A7.m.f(c2319i, BYDAutoMultimediaDevice.PARAM_SOURCE);
        if (this.f21896n) {
            throw new IllegalStateException("closed");
        }
        this.f21895m.t(c2319i, j3);
        b();
    }

    public final String toString() {
        return "buffer(" + this.l + ')';
    }

    @Override // r9.InterfaceC2320j
    public final InterfaceC2320j u(byte[] bArr) {
        A7.m.f(bArr, BYDAutoMultimediaDevice.PARAM_SOURCE);
        if (this.f21896n) {
            throw new IllegalStateException("closed");
        }
        this.f21895m.V(bArr);
        b();
        return this;
    }

    @Override // java.nio.channels.WritableByteChannel
    public final int write(ByteBuffer byteBuffer) {
        A7.m.f(byteBuffer, BYDAutoMultimediaDevice.PARAM_SOURCE);
        if (this.f21896n) {
            throw new IllegalStateException("closed");
        }
        int iWrite = this.f21895m.write(byteBuffer);
        b();
        return iWrite;
    }
}
