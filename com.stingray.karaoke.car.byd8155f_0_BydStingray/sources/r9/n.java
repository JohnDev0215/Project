package r9;

import java.util.concurrent.locks.ReentrantLock;

/* JADX INFO: loaded from: classes.dex */
public final class n implements J {
    public final u l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public long f21937m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public boolean f21938n;

    public n(u uVar, long j3) {
        A7.m.f(uVar, "fileHandle");
        this.l = uVar;
        this.f21937m = j3;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        if (this.f21938n) {
            return;
        }
        this.f21938n = true;
        u uVar = this.l;
        ReentrantLock reentrantLock = uVar.o;
        reentrantLock.lock();
        try {
            int i7 = uVar.f21947n - 1;
            uVar.f21947n = i7;
            if (i7 == 0) {
                if (uVar.f21946m) {
                    synchronized (uVar) {
                        uVar.f21948p.close();
                    }
                }
            }
        } finally {
            reentrantLock.unlock();
        }
    }

    @Override // r9.J
    public final L e() {
        return L.d;
    }

    @Override // r9.J
    public final long p(C2319i c2319i, long j3) {
        long j10;
        long j11;
        int i7;
        int i10;
        A7.m.f(c2319i, "sink");
        if (this.f21938n) {
            throw new IllegalStateException("closed");
        }
        u uVar = this.l;
        long j12 = this.f21937m;
        uVar.getClass();
        if (j3 < 0) {
            throw new IllegalArgumentException(M5.b.f("byteCount < 0: ", j3).toString());
        }
        long j13 = j3 + j12;
        long j14 = j12;
        while (true) {
            if (j14 >= j13) {
                break;
            }
            E eT = c2319i.T(1);
            byte[] bArr = eT.f21899a;
            int i11 = eT.c;
            int iMin = (int) Math.min(j13 - j14, 8192 - i11);
            synchronized (uVar) {
                A7.m.f(bArr, "array");
                uVar.f21948p.seek(j14);
                i7 = 0;
                while (true) {
                    if (i7 >= iMin) {
                        break;
                    }
                    int i12 = uVar.f21948p.read(bArr, i11, iMin - i7);
                    if (i12 != -1) {
                        i7 += i12;
                    } else if (i7 == 0) {
                        i10 = -1;
                        i7 = -1;
                    }
                }
                i10 = -1;
            }
            if (i7 == i10) {
                if (eT.f21900b == eT.c) {
                    c2319i.l = eT.a();
                    F.a(eT);
                }
                if (j12 == j14) {
                    j11 = -1;
                    j10 = -1;
                }
            } else {
                eT.c += i7;
                long j15 = i7;
                j14 += j15;
                c2319i.f21932m += j15;
            }
        }
        j10 = j14 - j12;
        j11 = -1;
        if (j10 != j11) {
            this.f21937m += j10;
        }
        return j10;
    }
}
