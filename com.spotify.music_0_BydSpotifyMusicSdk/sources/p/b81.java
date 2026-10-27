package p;

import java.io.Closeable;
import java.io.RandomAccessFile;
import java.util.concurrent.locks.ReentrantLock;

/* JADX INFO: loaded from: classes.dex */
public final class b81 implements Closeable {
    public boolean c;
    public int e;
    public final ReentrantLock k = new ReentrantLock();
    public final RandomAccessFile l;

    public b81(RandomAccessFile randomAccessFile) {
        this.l = randomAccessFile;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public final void close() {
        ReentrantLock reentrantLock = this.k;
        reentrantLock.lock();
        try {
            if (this.c) {
                return;
            }
            this.c = true;
            if (this.e != 0) {
                return;
            }
            synchronized (this) {
                this.l.close();
            }
        } finally {
            reentrantLock.unlock();
        }
    }

    public final long c() {
        long length;
        ReentrantLock reentrantLock = this.k;
        reentrantLock.lock();
        try {
            if (!(!this.c)) {
                throw new IllegalStateException("closed".toString());
            }
            synchronized (this) {
                length = this.l.length();
            }
            return length;
        } finally {
            reentrantLock.unlock();
        }
    }

    public final cu0 i(long j) {
        ReentrantLock reentrantLock = this.k;
        reentrantLock.lock();
        try {
            if (!(!this.c)) {
                throw new IllegalStateException("closed".toString());
            }
            this.e++;
            reentrantLock.unlock();
            return new cu0(this, j);
        } catch (Throwable th) {
            reentrantLock.unlock();
            throw th;
        }
    }
}
