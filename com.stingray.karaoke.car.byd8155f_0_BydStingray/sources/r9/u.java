package r9;

import java.io.Closeable;
import java.io.RandomAccessFile;
import java.util.concurrent.locks.ReentrantLock;

/* JADX INFO: loaded from: classes.dex */
public final class u implements Closeable {
    public final boolean l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public boolean f21946m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f21947n;
    public final ReentrantLock o = new ReentrantLock();

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final RandomAccessFile f21948p;

    public u(boolean z6, RandomAccessFile randomAccessFile) {
        this.l = z6;
        this.f21948p = randomAccessFile;
    }

    public static C2323m b(u uVar) {
        if (!uVar.l) {
            throw new IllegalStateException("file handle is read-only");
        }
        ReentrantLock reentrantLock = uVar.o;
        reentrantLock.lock();
        try {
            if (uVar.f21946m) {
                throw new IllegalStateException("closed");
            }
            uVar.f21947n++;
            reentrantLock.unlock();
            return new C2323m(uVar, 0L);
        } catch (Throwable th) {
            reentrantLock.unlock();
            throw th;
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        ReentrantLock reentrantLock = this.o;
        reentrantLock.lock();
        try {
            if (this.f21946m) {
                return;
            }
            this.f21946m = true;
            if (this.f21947n != 0) {
                return;
            }
            synchronized (this) {
                this.f21948p.close();
            }
        } finally {
            reentrantLock.unlock();
        }
    }

    public final void flush() {
        if (!this.l) {
            throw new IllegalStateException("file handle is read-only");
        }
        ReentrantLock reentrantLock = this.o;
        reentrantLock.lock();
        try {
            if (this.f21946m) {
                throw new IllegalStateException("closed");
            }
            synchronized (this) {
                this.f21948p.getFD().sync();
            }
        } finally {
            reentrantLock.unlock();
        }
    }

    public final long g() {
        long length;
        ReentrantLock reentrantLock = this.o;
        reentrantLock.lock();
        try {
            if (this.f21946m) {
                throw new IllegalStateException("closed");
            }
            synchronized (this) {
                length = this.f21948p.length();
            }
            return length;
        } finally {
            reentrantLock.unlock();
        }
    }

    public final n i(long j3) {
        ReentrantLock reentrantLock = this.o;
        reentrantLock.lock();
        try {
            if (this.f21946m) {
                throw new IllegalStateException("closed");
            }
            this.f21947n++;
            reentrantLock.unlock();
            return new n(this, j3);
        } catch (Throwable th) {
            reentrantLock.unlock();
            throw th;
        }
    }
}
