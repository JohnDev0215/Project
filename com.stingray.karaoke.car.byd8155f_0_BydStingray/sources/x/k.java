package X;

import A7.m;
import java.util.NoSuchElementException;

/* JADX INFO: loaded from: classes.dex */
public final class k extends a {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f9966n;
    public Object[] o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public boolean f9967p;

    /* JADX WARN: Type inference failed for: r5v1 */
    /* JADX WARN: Type inference failed for: r5v2, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r5v3 */
    public k(Object[] objArr, int i7, int i10, int i11) {
        super(i7, i10);
        this.f9966n = i11;
        Object[] objArr2 = new Object[i11];
        this.o = objArr2;
        ?? r52 = i7 == i10 ? 1 : 0;
        this.f9967p = r52;
        objArr2[0] = objArr;
        c(i7 - r52, 1);
    }

    public final Object b() {
        int i7 = this.l & 31;
        Object obj = this.o[this.f9966n - 1];
        m.d(obj, "null cannot be cast to non-null type kotlin.Array<E of androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableList.TrieIterator>");
        return ((Object[]) obj)[i7];
    }

    public final void c(int i7, int i10) {
        int i11 = (this.f9966n - i10) * 5;
        while (i10 < this.f9966n) {
            Object[] objArr = this.o;
            Object obj = objArr[i10 - 1];
            m.d(obj, "null cannot be cast to non-null type kotlin.Array<kotlin.Any?>");
            objArr[i10] = ((Object[]) obj)[io.sentry.config.a.c0(i7, i11)];
            i11 -= 5;
            i10++;
        }
    }

    public final void d(int i7) {
        int i10 = 0;
        while (io.sentry.config.a.c0(this.l, i10) == i7) {
            i10 += 5;
        }
        if (i10 > 0) {
            c(this.l, ((this.f9966n - 1) - (i10 / 5)) + 1);
        }
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public final Object next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        Object objB = b();
        int i7 = this.l + 1;
        this.l = i7;
        if (i7 == this.f9947m) {
            this.f9967p = true;
            return objB;
        }
        d(0);
        return objB;
    }

    @Override // java.util.ListIterator
    public final Object previous() {
        if (!hasPrevious()) {
            throw new NoSuchElementException();
        }
        this.l--;
        if (this.f9967p) {
            this.f9967p = false;
            return b();
        }
        d(31);
        return b();
    }
}
