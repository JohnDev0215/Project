package p;

import java.util.NoSuchElementException;

/* JADX INFO: loaded from: classes.dex */
public final class i61 extends oq2 {
    public boolean c;
    public final /* synthetic */ Object e;

    public i61(Object obj) {
        this.e = obj;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return !this.c;
    }

    @Override // java.util.Iterator
    public final Object next() {
        if (this.c) {
            throw new NoSuchElementException();
        }
        this.c = true;
        return this.e;
    }
}
