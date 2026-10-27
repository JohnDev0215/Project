package okhttp3;

import java.util.Iterator;
import p.g81;

/* JADX INFO: loaded from: classes.dex */
public final class Cache$urls$1 implements Iterator<String>, g81 {
    public boolean c;

    @Override // java.util.Iterator
    public final boolean hasNext() {
        this.c = false;
        throw null;
    }

    @Override // java.util.Iterator
    public final String next() {
        this.c = false;
        throw null;
    }

    @Override // java.util.Iterator
    public final void remove() {
        if (!this.c) {
            throw new IllegalStateException("remove() before next()".toString());
        }
        throw null;
    }
}
