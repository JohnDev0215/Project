package io.sentry;

import java.util.Iterator;
import java.util.NoSuchElementException;

/* JADX INFO: renamed from: io.sentry.e, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1460e implements Iterator {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f16524m = -1;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public boolean f16525n;
    public final /* synthetic */ C1463f o;

    public C1460e(C1463f c1463f) {
        this.o = c1463f;
        this.l = c1463f.f16531m;
        this.f16525n = c1463f.o;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.f16525n || this.l != this.o.f16532n;
    }

    @Override // java.util.Iterator
    public final Object next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        this.f16525n = false;
        int i7 = this.l;
        this.f16524m = i7;
        int i10 = i7 + 1;
        C1463f c1463f = this.o;
        this.l = i10 < c1463f.f16533p ? i10 : 0;
        return c1463f.l[i7];
    }

    @Override // java.util.Iterator
    public final void remove() {
        int i7;
        int i10 = this.f16524m;
        if (i10 == -1) {
            throw new IllegalStateException();
        }
        C1463f c1463f = this.o;
        int i11 = c1463f.f16531m;
        if (i10 == i11) {
            c1463f.remove();
            this.f16524m = -1;
            return;
        }
        int i12 = i10 + 1;
        int i13 = c1463f.f16533p;
        if (i11 >= i10 || i12 >= (i7 = c1463f.f16532n)) {
            while (i12 != c1463f.f16532n) {
                if (i12 >= i13) {
                    Object[] objArr = c1463f.l;
                    objArr[i12 - 1] = objArr[0];
                } else {
                    Object[] objArr2 = c1463f.l;
                    int i14 = i12 - 1;
                    if (i14 < 0) {
                        i14 = i13 - 1;
                    }
                    objArr2[i14] = objArr2[i12];
                    i12++;
                    if (i12 >= i13) {
                    }
                }
                i12 = 0;
            }
        } else {
            Object[] objArr3 = c1463f.l;
            System.arraycopy(objArr3, i12, objArr3, i10, i7 - i12);
        }
        this.f16524m = -1;
        int i15 = c1463f.f16532n - 1;
        if (i15 < 0) {
            i15 = i13 - 1;
        }
        c1463f.f16532n = i15;
        c1463f.l[i15] = null;
        c1463f.o = false;
        int i16 = this.l - 1;
        if (i16 < 0) {
            i16 = i13 - 1;
        }
        this.l = i16;
    }
}
