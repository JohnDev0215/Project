package l3;

import android.util.SparseIntArray;

/* JADX INFO: renamed from: l3.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1746b {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final /* synthetic */ int f18567b = 0;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final C1745a f18568a;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sparseIntArray.put(5, 1);
        sparseIntArray.put(6, 2);
        sparseIntArray.put(7, 2);
        sparseIntArray.put(8, 1);
        sparseIntArray.put(9, 1);
        sparseIntArray.put(10, 1);
    }

    public C1746b(C1745a c1745a) {
        this.f18568a = c1745a;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof C1746b) {
            return this.f18568a.equals(((C1746b) obj).f18568a);
        }
        return false;
    }

    public final int hashCode() {
        return this.f18568a.hashCode();
    }

    public final String toString() {
        return this.f18568a.toString();
    }
}
