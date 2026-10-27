package L8;

import F8.C0273h;
import java.util.Arrays;
import java.util.Iterator;
import n7.AbstractC1996k;

/* JADX INFO: loaded from: classes.dex */
public final class c extends a {
    public Object[] l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f4071m;

    @Override // L8.a
    public final int d() {
        return this.f4071m;
    }

    @Override // L8.a
    public final Object get(int i7) {
        return AbstractC1996k.S(i7, this.l);
    }

    @Override // L8.a, java.lang.Iterable
    public final Iterator iterator() {
        return new b(this);
    }

    @Override // L8.a
    public final void l(int i7, C0273h c0273h) {
        Object[] objArr = this.l;
        if (objArr.length <= i7) {
            Object[] objArrCopyOf = Arrays.copyOf(objArr, objArr.length * 2);
            A7.m.e(objArrCopyOf, "copyOf(this, newSize)");
            this.l = objArrCopyOf;
        }
        Object[] objArr2 = this.l;
        if (objArr2[i7] == null) {
            this.f4071m++;
        }
        objArr2[i7] = c0273h;
    }
}
