package p;

import java.nio.ByteBuffer;
import okio.ByteString;

/* JADX INFO: loaded from: classes.dex */
public final class v02 implements io {
    public final pd2 c;
    public final fo e;
    public boolean k;

    public v02(pd2 pd2Var) {
        v03.j(pd2Var, "sink");
        this.c = pd2Var;
        this.e = new fo();
    }

    @Override // p.io
    public final io B() {
        if (!(!this.k)) {
            throw new IllegalStateException("closed".toString());
        }
        fo foVar = this.e;
        long j = foVar.e;
        if (j > 0) {
            this.c.Y(foVar, j);
        }
        return this;
    }

    @Override // p.io
    public final io C(int i) {
        if (!(!this.k)) {
            throw new IllegalStateException("closed".toString());
        }
        this.e.b1(i);
        c0();
        return this;
    }

    @Override // p.io
    public final io I(int i) {
        if (!(!this.k)) {
            throw new IllegalStateException("closed".toString());
        }
        this.e.Z0(i);
        c0();
        return this;
    }

    @Override // p.io
    public final long L(de2 de2Var) {
        long j = 0;
        while (true) {
            long jT = ((tc) de2Var).t(this.e, 8192L);
            if (jT == -1) {
                return j;
            }
            j += jT;
            c0();
        }
    }

    @Override // p.io
    public final io R(int i) {
        if (!(!this.k)) {
            throw new IllegalStateException("closed".toString());
        }
        this.e.W0(i);
        c0();
        return this;
    }

    @Override // p.io
    public final io V(byte[] bArr) {
        v03.j(bArr, "source");
        if (!(!this.k)) {
            throw new IllegalStateException("closed".toString());
        }
        this.e.U0(bArr);
        c0();
        return this;
    }

    @Override // p.pd2
    public final void Y(fo foVar, long j) {
        v03.j(foVar, "source");
        if (!(!this.k)) {
            throw new IllegalStateException("closed".toString());
        }
        this.e.Y(foVar, j);
        c0();
    }

    @Override // p.io
    public final io a0(ByteString byteString) {
        v03.j(byteString, "byteString");
        if (!(!this.k)) {
            throw new IllegalStateException("closed".toString());
        }
        this.e.T0(byteString);
        c0();
        return this;
    }

    @Override // p.io
    public final io c0() {
        if (!(!this.k)) {
            throw new IllegalStateException("closed".toString());
        }
        fo foVar = this.e;
        long jO0 = foVar.o0();
        if (jO0 > 0) {
            this.c.Y(foVar, jO0);
        }
        return this;
    }

    @Override // p.pd2, java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws Throwable {
        pd2 pd2Var = this.c;
        if (this.k) {
            return;
        }
        try {
            fo foVar = this.e;
            long j = foVar.e;
            if (j > 0) {
                pd2Var.Y(foVar, j);
            }
            th = null;
        } catch (Throwable th) {
            th = th;
        }
        try {
            pd2Var.close();
        } catch (Throwable th2) {
            if (th == null) {
                th = th2;
            }
        }
        this.k = true;
        if (th != null) {
            throw th;
        }
    }

    @Override // p.io
    public final fo d() {
        return this.e;
    }

    @Override // p.pd2
    public final tl2 e() {
        return this.c.e();
    }

    @Override // p.io, p.pd2, java.io.Flushable
    public final void flush() {
        if (!(!this.k)) {
            throw new IllegalStateException("closed".toString());
        }
        fo foVar = this.e;
        long j = foVar.e;
        pd2 pd2Var = this.c;
        if (j > 0) {
            pd2Var.Y(foVar, j);
        }
        pd2Var.flush();
    }

    @Override // java.nio.channels.Channel
    public final boolean isOpen() {
        return !this.k;
    }

    @Override // p.io
    public final io l(byte[] bArr, int i, int i2) {
        v03.j(bArr, "source");
        if (!(!this.k)) {
            throw new IllegalStateException("closed".toString());
        }
        this.e.V0(bArr, i, i2);
        c0();
        return this;
    }

    @Override // p.io
    public final io r(String str, int i, int i2) {
        v03.j(str, "string");
        if (!(!this.k)) {
            throw new IllegalStateException("closed".toString());
        }
        this.e.e1(str, i, i2);
        c0();
        return this;
    }

    @Override // p.io
    public final io s(long j) {
        if (!(!this.k)) {
            throw new IllegalStateException("closed".toString());
        }
        this.e.Y0(j);
        c0();
        return this;
    }

    @Override // p.io
    public final io s0(String str) {
        v03.j(str, "string");
        if (!(!this.k)) {
            throw new IllegalStateException("closed".toString());
        }
        this.e.d1(str);
        c0();
        return this;
    }

    @Override // p.io
    public final io t0(long j) {
        if (!(!this.k)) {
            throw new IllegalStateException("closed".toString());
        }
        this.e.X0(j);
        c0();
        return this;
    }

    public final String toString() {
        return "buffer(" + this.c + ')';
    }

    @Override // java.nio.channels.WritableByteChannel
    public final int write(ByteBuffer byteBuffer) {
        v03.j(byteBuffer, "source");
        if (!(!this.k)) {
            throw new IllegalStateException("closed".toString());
        }
        int iWrite = this.e.write(byteBuffer);
        c0();
        return iWrite;
    }
}
