package defpackage;

import java.util.Iterator;

/* JADX INFO: compiled from: SimpleList.java */
/* JADX INFO: loaded from: classes3.dex */
public interface xm2<T> extends Iterable<T> {

    /* JADX INFO: compiled from: SimpleList.java */
    public class a implements Iterator<T> {
        public int e;

        public a() {
        }

        @Override // java.util.Iterator
        public final boolean hasNext() {
            return this.e < xm2.this.size();
        }

        @Override // java.util.Iterator
        public final T next() {
            int i = this.e;
            this.e = i + 1;
            return (T) xm2.this.get(i);
        }
    }

    T get(int i);

    @Override // java.lang.Iterable
    default Iterator<T> iterator() {
        return new a();
    }

    int size();
}
