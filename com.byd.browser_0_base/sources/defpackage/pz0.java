package defpackage;

import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.jvm.internal.SourceDebugExtension;

/* JADX INFO: compiled from: IndexBasedArrayIterator.kt */
/* JADX INFO: loaded from: classes.dex */
@SourceDebugExtension({"SMAP\nIndexBasedArrayIterator.kt\nKotlin\n*S Kotlin\n*F\n+ 1 IndexBasedArrayIterator.kt\nandroidx/collection/IndexBasedArrayIterator\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,47:1\n1#2:48\n*E\n"})
public abstract class pz0<T> implements Iterator<T> {
    public int e;
    public int f;
    public boolean g;

    public pz0(int i) {
        this.e = i;
    }

    public abstract T b(int i);

    public abstract void c(int i);

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.f < this.e;
    }

    @Override // java.util.Iterator
    public final T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        T tB = b(this.f);
        this.f++;
        this.g = true;
        return tB;
    }

    @Override // java.util.Iterator
    public final void remove() {
        if (!this.g) {
            throw new IllegalStateException("Call next() before removing an element.".toString());
        }
        int i = this.f - 1;
        this.f = i;
        c(i);
        this.e--;
        this.g = false;
    }
}
