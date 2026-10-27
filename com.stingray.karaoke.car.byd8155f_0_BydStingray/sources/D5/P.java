package d5;

import java.util.NoSuchElementException;

/* JADX INFO: loaded from: classes.dex */
public final class P extends s0 {
    public final Object l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public boolean f13395m;

    public P(Object obj) {
        this.l = obj;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return !this.f13395m;
    }

    @Override // java.util.Iterator
    public final Object next() {
        if (this.f13395m) {
            throw new NoSuchElementException();
        }
        this.f13395m = true;
        return this.l;
    }
}
