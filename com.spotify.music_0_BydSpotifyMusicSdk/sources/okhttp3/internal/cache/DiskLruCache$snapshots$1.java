package okhttp3.internal.cache;

import java.util.Iterator;
import okhttp3.internal.cache.DiskLruCache;
import p.g81;

/* JADX INFO: loaded from: classes.dex */
public final class DiskLruCache$snapshots$1 implements Iterator<DiskLruCache.Snapshot>, g81 {
    public DiskLruCache.Snapshot c;

    @Override // java.util.Iterator
    public final boolean hasNext() {
        throw null;
    }

    @Override // java.util.Iterator
    public final DiskLruCache.Snapshot next() {
        throw null;
    }

    @Override // java.util.Iterator
    public final void remove() {
        if (this.c == null) {
            throw new IllegalStateException("remove() before next()".toString());
        }
        try {
            throw null;
        } catch (Throwable th) {
            this.c = null;
            throw th;
        }
    }
}
