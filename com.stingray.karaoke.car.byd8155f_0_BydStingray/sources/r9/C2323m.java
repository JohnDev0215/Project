package r9;

import android.hardware.bydauto.multimedia.BYDAutoMultimediaDevice;
import java.util.concurrent.locks.ReentrantLock;

/* JADX INFO: renamed from: r9.m, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2323m implements H {
    public final u l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public long f21935m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public boolean f21936n;

    public C2323m(u uVar, long j3) {
        A7.m.f(uVar, "fileHandle");
        this.l = uVar;
        this.f21935m = j3;
    }

    @Override // r9.H, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        if (this.f21936n) {
            return;
        }
        this.f21936n = true;
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

    @Override // r9.H
    public final L e() {
        return L.d;
    }

    @Override // r9.H, java.io.Flushable
    public final void flush() {
        if (this.f21936n) {
            throw new IllegalStateException("closed");
        }
        u uVar = this.l;
        synchronized (uVar) {
            uVar.f21948p.getFD().sync();
        }
    }

    @Override // r9.H
    public final void t(C2319i c2319i, long j3) {
        A7.m.f(c2319i, BYDAutoMultimediaDevice.PARAM_SOURCE);
        if (this.f21936n) {
            throw new IllegalStateException("closed");
        }
        u uVar = this.l;
        long j10 = this.f21935m;
        uVar.getClass();
        AbstractC2312b.e(c2319i.f21932m, 0L, j3);
        long j11 = j10 + j3;
        while (j10 < j11) {
            E e10 = c2319i.l;
            A7.m.c(e10);
            int iMin = (int) Math.min(j11 - j10, e10.c - e10.f21900b);
            byte[] bArr = e10.f21899a;
            int i7 = e10.f21900b;
            synchronized (uVar) {
                A7.m.f(bArr, "array");
                uVar.f21948p.seek(j10);
                uVar.f21948p.write(bArr, i7, iMin);
            }
            int i10 = e10.f21900b + iMin;
            e10.f21900b = i10;
            long j12 = iMin;
            j10 += j12;
            c2319i.f21932m -= j12;
            if (i10 == e10.c) {
                c2319i.l = e10.a();
                F.a(e10);
            }
        }
        this.f21935m += j3;
    }
}
