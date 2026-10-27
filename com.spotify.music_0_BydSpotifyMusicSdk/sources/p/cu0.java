package p;

import java.util.concurrent.locks.ReentrantLock;

/* JADX INFO: loaded from: classes.dex */
public final class cu0 implements de2 {
    public final b81 c;
    public long e;
    public boolean k;

    public cu0(b81 b81Var, long j) {
        v03.j(b81Var, "fileHandle");
        this.c = b81Var;
        this.e = j;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        if (this.k) {
            return;
        }
        this.k = true;
        b81 b81Var = this.c;
        ReentrantLock reentrantLock = b81Var.k;
        reentrantLock.lock();
        try {
            int i = b81Var.e - 1;
            b81Var.e = i;
            if (i == 0) {
                if (b81Var.c) {
                    synchronized (b81Var) {
                        b81Var.l.close();
                    }
                }
            }
        } finally {
            reentrantLock.unlock();
        }
    }

    @Override // p.de2
    public final tl2 e() {
        return tl2.d;
    }

    @Override // p.de2
    public final long t(fo foVar, long j) {
        long j2;
        long j3;
        long j4;
        int i;
        v03.j(foVar, "sink");
        int i2 = 1;
        if (!(!this.k)) {
            throw new IllegalStateException("closed".toString());
        }
        b81 b81Var = this.c;
        long j5 = this.e;
        b81Var.getClass();
        if (j < 0) {
            throw new IllegalArgumentException(u4.g("byteCount < 0: ", j).toString());
        }
        long j6 = j + j5;
        long j7 = j5;
        while (true) {
            if (j7 >= j6) {
                j2 = j5;
                break;
            }
            bb2 bb2VarS0 = foVar.S0(i2);
            byte[] bArr = bb2VarS0.a;
            int i3 = bb2VarS0.c;
            j2 = j5;
            int iMin = (int) Math.min(j6 - j7, 8192 - i3);
            synchronized (b81Var) {
                v03.j(bArr, "array");
                b81Var.l.seek(j7);
                i = 0;
                while (true) {
                    if (i >= iMin) {
                        break;
                    }
                    int i4 = b81Var.l.read(bArr, i3, iMin - i);
                    if (i4 != -1) {
                        i += i4;
                    } else if (i == 0) {
                        i = -1;
                    }
                }
            }
            if (i == -1) {
                if (bb2VarS0.b == bb2VarS0.c) {
                    foVar.c = bb2VarS0.a();
                    db2.a(bb2VarS0);
                }
                if (j2 == j7) {
                    j4 = -1;
                    j3 = -1;
                }
            } else {
                bb2VarS0.c += i;
                long j8 = i;
                j7 += j8;
                foVar.e += j8;
                j5 = j2;
                i2 = 1;
            }
        }
        j3 = j7 - j2;
        j4 = -1;
        if (j3 != j4) {
            this.e += j3;
        }
        return j3;
    }
}
