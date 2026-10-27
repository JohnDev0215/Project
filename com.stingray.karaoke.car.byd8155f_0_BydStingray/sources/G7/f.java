package G7;

import java.util.Iterator;
import java.util.NoSuchElementException;

/* JADX INFO: loaded from: classes.dex */
public final class f implements Iterator, B7.a {
    public final long l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final long f2718m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public boolean f2719n;
    public long o;

    public f(long j3, long j10, long j11) {
        this.l = j11;
        this.f2718m = j10;
        boolean z6 = false;
        if (j11 <= 0 ? j3 >= j10 : j3 <= j10) {
            z6 = true;
        }
        this.f2719n = z6;
        this.o = z6 ? j3 : j10;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.f2719n;
    }

    @Override // java.util.Iterator
    public final Object next() {
        long j3 = this.o;
        if (j3 != this.f2718m) {
            this.o = this.l + j3;
        } else {
            if (!this.f2719n) {
                throw new NoSuchElementException();
            }
            this.f2719n = false;
        }
        return Long.valueOf(j3);
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
