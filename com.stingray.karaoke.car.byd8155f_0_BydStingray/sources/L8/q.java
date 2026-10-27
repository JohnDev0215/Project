package L8;

import java.util.Iterator;
import java.util.NoSuchElementException;

/* JADX INFO: loaded from: classes.dex */
public final class q implements Iterator, B7.a {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public boolean f4092m = true;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final Object f4093n;

    public /* synthetic */ q(Object obj, int i7) {
        this.l = i7;
        this.f4093n = obj;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        switch (this.l) {
        }
        return this.f4092m;
    }

    @Override // java.util.Iterator
    public final Object next() {
        switch (this.l) {
            case 0:
                if (!this.f4092m) {
                    throw new NoSuchElementException();
                }
                this.f4092m = false;
                return ((r) this.f4093n).l;
            default:
                if (!this.f4092m) {
                    throw new NoSuchElementException();
                }
                this.f4092m = false;
                return this.f4093n;
        }
    }

    @Override // java.util.Iterator
    public final void remove() {
        switch (this.l) {
            case 0:
                throw new UnsupportedOperationException("Operation is not supported for read-only collection");
            default:
                throw new UnsupportedOperationException();
        }
    }
}
