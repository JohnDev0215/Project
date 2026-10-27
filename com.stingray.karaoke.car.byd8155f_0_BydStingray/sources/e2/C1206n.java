package e2;

import android.util.SparseBooleanArray;
import h2.AbstractC1343A;
import h2.AbstractC1346c;

/* JADX INFO: renamed from: e2.n, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1206n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final SparseBooleanArray f14224a;

    public C1206n(SparseBooleanArray sparseBooleanArray) {
        this.f14224a = sparseBooleanArray;
    }

    public final boolean a(int... iArr) {
        for (int i7 : iArr) {
            if (this.f14224a.get(i7)) {
                return true;
            }
        }
        return false;
    }

    public final int b(int i7) {
        SparseBooleanArray sparseBooleanArray = this.f14224a;
        AbstractC1346c.f(i7, sparseBooleanArray.size());
        return sparseBooleanArray.keyAt(i7);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C1206n)) {
            return false;
        }
        C1206n c1206n = (C1206n) obj;
        int i7 = AbstractC1343A.f15195a;
        SparseBooleanArray sparseBooleanArray = this.f14224a;
        if (i7 >= 24) {
            return sparseBooleanArray.equals(c1206n.f14224a);
        }
        if (sparseBooleanArray.size() != c1206n.f14224a.size()) {
            return false;
        }
        for (int i10 = 0; i10 < sparseBooleanArray.size(); i10++) {
            if (b(i10) != c1206n.b(i10)) {
                return false;
            }
        }
        return true;
    }

    public final int hashCode() {
        int i7 = AbstractC1343A.f15195a;
        SparseBooleanArray sparseBooleanArray = this.f14224a;
        if (i7 >= 24) {
            return sparseBooleanArray.hashCode();
        }
        int size = sparseBooleanArray.size();
        for (int i10 = 0; i10 < sparseBooleanArray.size(); i10++) {
            size = (size * 31) + b(i10);
        }
        return size;
    }
}
