package n7;

import A7.C0054b;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/* JADX INFO: renamed from: n7.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1987b extends C0054b implements ListIterator {
    public final /* synthetic */ AbstractC1989d o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C1987b(AbstractC1989d abstractC1989d, int i7) {
        super(abstractC1989d, 2);
        this.o = abstractC1989d;
        int iD = abstractC1989d.d();
        if (i7 < 0 || i7 > iD) {
            throw new IndexOutOfBoundsException(A7.k.i("index: ", ", size: ", i7, iD));
        }
        this.f453m = i7;
    }

    @Override // java.util.ListIterator
    public final void add(Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.ListIterator
    public final boolean hasPrevious() {
        return this.f453m > 0;
    }

    @Override // java.util.ListIterator
    public final int nextIndex() {
        return this.f453m;
    }

    @Override // java.util.ListIterator
    public final Object previous() {
        if (!hasPrevious()) {
            throw new NoSuchElementException();
        }
        int i7 = this.f453m - 1;
        this.f453m = i7;
        return this.o.get(i7);
    }

    @Override // java.util.ListIterator
    public final int previousIndex() {
        return this.f453m - 1;
    }

    @Override // java.util.ListIterator
    public final void set(Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
