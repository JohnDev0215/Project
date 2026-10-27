package L8;

import java.util.Iterator;
import java.util.NoSuchElementException;

/* JADX INFO: loaded from: classes.dex */
public final class b implements Iterator, B7.a {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public Object f4069m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f4070n = -1;
    public final /* synthetic */ c o;

    public b(c cVar) {
        this.o = cVar;
    }

    public final boolean b() {
        int i7;
        Object[] objArr;
        this.l = 3;
        do {
            i7 = this.f4070n + 1;
            this.f4070n = i7;
            objArr = this.o.l;
            if (i7 >= objArr.length) {
                break;
            }
        } while (objArr[i7] == null);
        if (i7 >= objArr.length) {
            this.l = 2;
        } else {
            Object obj = objArr[i7];
            A7.m.d(obj, "null cannot be cast to non-null type T of org.jetbrains.kotlin.util.ArrayMapImpl");
            this.f4069m = obj;
            this.l = 1;
        }
        return this.l == 1;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        int i7 = this.l;
        if (i7 == 0) {
            return b();
        }
        if (i7 == 1) {
            return true;
        }
        if (i7 == 2) {
            return false;
        }
        throw new IllegalArgumentException("hasNext called when the iterator is in the FAILED state.");
    }

    @Override // java.util.Iterator
    public final Object next() {
        int i7 = this.l;
        if (i7 == 1) {
            this.l = 0;
            return this.f4069m;
        }
        if (i7 == 2 || !b()) {
            throw new NoSuchElementException();
        }
        this.l = 0;
        return this.f4069m;
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
