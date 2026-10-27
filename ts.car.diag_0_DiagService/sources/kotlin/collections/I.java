package kotlin.collections;

import java.util.Arrays;
import java.util.Iterator;
import java.util.RandomAccess;
import kotlin.Metadata;
import kotlin.TypeCastException;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes3.dex */
final class I<T> extends AbstractC0112c<T> implements RandomAccess {
    private final int d;
    private int e;
    private int f;
    private final Object[] g;

    /* JADX INFO: compiled from: caradapter-bug */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\b\n\u0018\u00002\u00020\u0001B\u0011\b\u0010\u0012\u0006\u0010\n\u001a\u00020\t¢\u0006\u0004\b\u000b\u0010\fJ\u000f\u0010\u0003\u001a\u00020\u0002H\u0014¢\u0006\u0004\b\u0003\u0010\u0004R\u0016\u0010\u0006\u001a\u00020\u00058\u0000@\u0000X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0006\u0010\u0007R\u0016\u0010\b\u001a\u00020\u00058\u0000@\u0000X\u0082\u000e¢\u0006\u0006\n\u0004\b\b\u0010\u0007¨\u0006\r"}, d2 = {"Lkotlin/collections/I$a;", "Lkotlin/collections/b;", "Lkotlin/Unit;", "a", "()V", "Lkotlin/Int;", "e", "I", "f", "Lkotlin/collections/I;", "p0", "<init>", "(Lkotlin/collections/RingBuffer;)V", "kotlin-stdlib"}, k = 1, mv = {1, 1, 15}, pn = "", xi = 0, xs = "")
    public static final class a extends AbstractC0111b<T> {
        private int e;
        private int f;

        a() {
            this.e = I.this.size();
            this.f = I.this.e;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // kotlin.collections.AbstractC0111b
        protected void a() {
            if (this.e == 0) {
                b();
                return;
            }
            c(I.this.g[this.f]);
            this.f = (this.f + 1) % I.this.d;
            this.e--;
        }
    }

    public I(@NotNull Object[] objArr, int i) {
        kotlin.jvm.internal.q.c(objArr, "buffer");
        this.g = objArr;
        if (!(i >= 0)) {
            throw new IllegalArgumentException(("ring buffer filled size should not be negative but it is " + i).toString());
        }
        if (i <= this.g.length) {
            this.d = this.g.length;
            this.f = i;
            return;
        }
        throw new IllegalArgumentException(("ring buffer filled size: " + i + " cannot be larger than the buffer size: " + this.g.length).toString());
    }

    @Override // kotlin.collections.AbstractCollection
    /* JADX INFO: renamed from: a */
    public int getD() {
        return this.f;
    }

    public final void e(T t) {
        if (g()) {
            throw new IllegalStateException("ring buffer is full");
        }
        this.g[(this.e + size()) % this.d] = t;
        this.f = size() + 1;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @NotNull
    public final I<T> f(int i) {
        Object[] array;
        int i2 = this.d;
        int iB = kotlin.t.g.b(i2 + (i2 >> 1) + 1, i);
        if (this.e == 0) {
            array = Arrays.copyOf(this.g, iB);
            kotlin.jvm.internal.q.b(array, "java.util.Arrays.copyOf(this, newSize)");
        } else {
            array = toArray(new Object[iB]);
        }
        return new I<>(array, size());
    }

    public final boolean g() {
        return size() == this.d;
    }

    @Override // kotlin.collections.AbstractC0112c, java.util.List
    public T get(int i) {
        AbstractC0112c.f1486c.a(i, size());
        return (T) this.g[(this.e + i) % this.d];
    }

    public final void h(int i) {
        if (!(i >= 0)) {
            throw new IllegalArgumentException(("n shouldn't be negative but it is " + i).toString());
        }
        if (!(i <= size())) {
            throw new IllegalArgumentException(("n shouldn't be greater than the buffer size: n = " + i + ", size = " + size()).toString());
        }
        if (i > 0) {
            int i2 = this.e;
            int i3 = (i2 + i) % this.d;
            if (i2 > i3) {
                C0117h.a(this.g, null, i2, this.d);
                C0117h.a(this.g, null, 0, i3);
            } else {
                C0117h.a(this.g, null, i2, i3);
            }
            this.e = i3;
            this.f = size() - i;
        }
    }

    @Override // kotlin.collections.AbstractC0112c, java.util.Collection, java.lang.Iterable, java.util.List
    @NotNull
    public Iterator<T> iterator() {
        return new a();
    }

    @Override // kotlin.collections.AbstractCollection, java.util.Collection
    @NotNull
    public <T> T[] toArray(@NotNull T[] tArr) {
        kotlin.jvm.internal.q.c(tArr, "array");
        if (tArr.length < size()) {
            tArr = (T[]) Arrays.copyOf(tArr, size());
            kotlin.jvm.internal.q.b(tArr, "java.util.Arrays.copyOf(this, newSize)");
        }
        int size = size();
        int i = 0;
        int i2 = 0;
        for (int i3 = this.e; i2 < size && i3 < this.d; i3++) {
            tArr[i2] = this.g[i3];
            i2++;
        }
        while (i2 < size) {
            tArr[i2] = this.g[i];
            i2++;
            i++;
        }
        if (tArr.length > size()) {
            tArr[size()] = null;
        }
        if (tArr != null) {
            return tArr;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
    }

    public I(int i) {
        this(new Object[i], 0);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlin.collections.AbstractCollection, java.util.Collection
    @NotNull
    public Object[] toArray() {
        return toArray(new Object[size()]);
    }
}
