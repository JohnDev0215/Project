package X;

import java.util.NoSuchElementException;

/* JADX INFO: loaded from: classes.dex */
public final class d extends a {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ int f9950n = 1;
    public final Object o;

    public d(Object[] objArr, int i7, int i10) {
        super(i7, i10);
        this.o = objArr;
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public final Object next() {
        switch (this.f9950n) {
            case 0:
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                int i7 = this.l;
                this.l = i7 + 1;
                return ((Object[]) this.o)[i7];
            default:
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                this.l++;
                return this.o;
        }
    }

    @Override // java.util.ListIterator
    public final Object previous() {
        switch (this.f9950n) {
            case 0:
                if (!hasPrevious()) {
                    throw new NoSuchElementException();
                }
                int i7 = this.l - 1;
                this.l = i7;
                return ((Object[]) this.o)[i7];
            default:
                if (!hasPrevious()) {
                    throw new NoSuchElementException();
                }
                this.l--;
                return this.o;
        }
    }

    public d(Object obj, int i7) {
        super(i7, 1);
        this.o = obj;
    }
}
