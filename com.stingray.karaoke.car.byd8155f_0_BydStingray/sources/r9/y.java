package r9;

import java.util.RandomAccess;
import n7.AbstractC1989d;

/* JADX INFO: loaded from: classes.dex */
public final class y extends AbstractC1989d implements RandomAccess {
    public final C2322l[] l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final int[] f21950m;

    public y(C2322l[] c2322lArr, int[] iArr) {
        this.l = c2322lArr;
        this.f21950m = iArr;
    }

    @Override // n7.AbstractC1986a, java.util.Collection, java.util.List
    public final /* bridge */ boolean contains(Object obj) {
        if (obj instanceof C2322l) {
            return super.contains((C2322l) obj);
        }
        return false;
    }

    @Override // n7.AbstractC1986a
    public final int d() {
        return this.l.length;
    }

    @Override // java.util.List
    public final Object get(int i7) {
        return this.l[i7];
    }

    @Override // n7.AbstractC1989d, java.util.List
    public final /* bridge */ int indexOf(Object obj) {
        if (obj instanceof C2322l) {
            return super.indexOf((C2322l) obj);
        }
        return -1;
    }

    @Override // n7.AbstractC1989d, java.util.List
    public final /* bridge */ int lastIndexOf(Object obj) {
        if (obj instanceof C2322l) {
            return super.lastIndexOf((C2322l) obj);
        }
        return -1;
    }
}
