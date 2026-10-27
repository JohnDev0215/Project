package w2;

import android.os.Bundle;
import android.os.Parcelable;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import e2.k0;
import e2.l0;
import h2.AbstractC1343A;
import h2.AbstractC1346c;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;
import u2.I;
import u2.n0;
import x3.AbstractC2673a;

/* JADX INFO: renamed from: w2.i, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2595i extends l0 {

    /* JADX INFO: renamed from: A0, reason: collision with root package name */
    public static final String f23209A0;
    public static final String B0;

    /* JADX INFO: renamed from: C0, reason: collision with root package name */
    public static final String f23210C0;

    /* JADX INFO: renamed from: D0, reason: collision with root package name */
    public static final String f23211D0;

    /* JADX INFO: renamed from: E0, reason: collision with root package name */
    public static final String f23212E0;

    /* JADX INFO: renamed from: F0, reason: collision with root package name */
    public static final String f23213F0;
    public static final String G0;

    /* JADX INFO: renamed from: H0, reason: collision with root package name */
    public static final String f23214H0;

    /* JADX INFO: renamed from: I0, reason: collision with root package name */
    public static final String f23215I0;

    /* JADX INFO: renamed from: J0, reason: collision with root package name */
    public static final String f23216J0;

    /* JADX INFO: renamed from: r0, reason: collision with root package name */
    public static final String f23217r0;

    /* JADX INFO: renamed from: s0, reason: collision with root package name */
    public static final String f23218s0;

    /* JADX INFO: renamed from: t0, reason: collision with root package name */
    public static final String f23219t0;

    /* JADX INFO: renamed from: u0, reason: collision with root package name */
    public static final String f23220u0;

    /* JADX INFO: renamed from: v0, reason: collision with root package name */
    public static final String f23221v0;

    /* JADX INFO: renamed from: w0, reason: collision with root package name */
    public static final String f23222w0;

    /* JADX INFO: renamed from: x0, reason: collision with root package name */
    public static final String f23223x0;

    /* JADX INFO: renamed from: y0, reason: collision with root package name */
    public static final String f23224y0;

    /* JADX INFO: renamed from: z0, reason: collision with root package name */
    public static final String f23225z0;

    /* JADX INFO: renamed from: i0, reason: collision with root package name */
    public final boolean f23226i0;

    /* JADX INFO: renamed from: j0, reason: collision with root package name */
    public final boolean f23227j0;

    /* JADX INFO: renamed from: k0, reason: collision with root package name */
    public final boolean f23228k0;

    /* JADX INFO: renamed from: l0, reason: collision with root package name */
    public final boolean f23229l0;

    /* JADX INFO: renamed from: m0, reason: collision with root package name */
    public final boolean f23230m0;

    /* JADX INFO: renamed from: n0, reason: collision with root package name */
    public final boolean f23231n0;

    /* JADX INFO: renamed from: o0, reason: collision with root package name */
    public final boolean f23232o0;

    /* JADX INFO: renamed from: p0, reason: collision with root package name */
    public final SparseArray f23233p0;

    /* JADX INFO: renamed from: q0, reason: collision with root package name */
    public final SparseBooleanArray f23234q0;

    static {
        new C2595i(new C2594h());
        int i7 = AbstractC1343A.f15195a;
        f23217r0 = Integer.toString(1000, 36);
        f23218s0 = Integer.toString(1001, 36);
        f23219t0 = Integer.toString(1002, 36);
        f23220u0 = Integer.toString(1003, 36);
        f23221v0 = Integer.toString(1004, 36);
        f23222w0 = Integer.toString(1005, 36);
        f23223x0 = Integer.toString(1006, 36);
        f23224y0 = Integer.toString(1007, 36);
        f23225z0 = Integer.toString(1008, 36);
        f23209A0 = Integer.toString(1009, 36);
        B0 = Integer.toString(1010, 36);
        f23210C0 = Integer.toString(1011, 36);
        f23211D0 = Integer.toString(1012, 36);
        f23212E0 = Integer.toString(1013, 36);
        f23213F0 = Integer.toString(1014, 36);
        G0 = Integer.toString(1015, 36);
        f23214H0 = Integer.toString(1016, 36);
        f23215I0 = Integer.toString(1017, 36);
        f23216J0 = Integer.toString(1018, 36);
    }

    public C2595i(C2594h c2594h) {
        super(c2594h);
        this.f23226i0 = c2594h.f23200C;
        this.f23227j0 = c2594h.f23201D;
        this.f23228k0 = c2594h.f23202E;
        this.f23229l0 = c2594h.f23203F;
        this.f23230m0 = c2594h.f23204G;
        this.f23231n0 = c2594h.f23205H;
        this.f23232o0 = c2594h.f23206I;
        this.f23233p0 = c2594h.f23207J;
        this.f23234q0 = c2594h.f23208K;
    }

    @Override // e2.l0
    public final k0 a() {
        return new C2594h(this);
    }

    @Override // e2.l0
    public final Bundle c() {
        Bundle bundleC = super.c();
        bundleC.putBoolean(f23217r0, this.f23226i0);
        bundleC.putBoolean(f23218s0, false);
        bundleC.putBoolean(f23219t0, this.f23227j0);
        bundleC.putBoolean(f23213F0, false);
        bundleC.putBoolean(f23220u0, this.f23228k0);
        bundleC.putBoolean(f23221v0, false);
        bundleC.putBoolean(f23222w0, false);
        bundleC.putBoolean(f23223x0, false);
        bundleC.putBoolean(G0, false);
        bundleC.putBoolean(f23216J0, this.f23229l0);
        bundleC.putBoolean(f23214H0, this.f23230m0);
        bundleC.putBoolean(f23224y0, this.f23231n0);
        bundleC.putBoolean(f23225z0, false);
        bundleC.putBoolean(f23209A0, this.f23232o0);
        bundleC.putBoolean(f23215I0, false);
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        SparseArray sparseArray = new SparseArray();
        int i7 = 0;
        while (true) {
            SparseArray sparseArray2 = this.f23233p0;
            if (i7 >= sparseArray2.size()) {
                SparseBooleanArray sparseBooleanArray = this.f23234q0;
                int[] iArr = new int[sparseBooleanArray.size()];
                for (int i10 = 0; i10 < sparseBooleanArray.size(); i10++) {
                    iArr[i10] = sparseBooleanArray.keyAt(i10);
                }
                bundleC.putIntArray(f23212E0, iArr);
                return bundleC;
            }
            int iKeyAt = sparseArray2.keyAt(i7);
            for (Map.Entry entry : ((Map) sparseArray2.valueAt(i7)).entrySet()) {
                if (entry.getValue() != null) {
                    throw new ClassCastException();
                }
                arrayList2.add((n0) entry.getKey());
                arrayList.add(Integer.valueOf(iKeyAt));
            }
            bundleC.putIntArray(B0, AbstractC2673a.Z(arrayList));
            bundleC.putParcelableArrayList(f23210C0, AbstractC1346c.y(arrayList2, new I(5)));
            SparseArray<? extends Parcelable> sparseArray3 = new SparseArray<>(sparseArray.size());
            if (sparseArray.size() > 0) {
                sparseArray.keyAt(0);
                A7.k.q(sparseArray.valueAt(0));
                throw null;
            }
            bundleC.putSparseParcelableArray(f23211D0, sparseArray3);
            i7++;
        }
    }

    @Override // e2.l0
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || C2595i.class != obj.getClass()) {
            return false;
        }
        C2595i c2595i = (C2595i) obj;
        if (super.equals(c2595i) && this.f23226i0 == c2595i.f23226i0 && this.f23227j0 == c2595i.f23227j0 && this.f23228k0 == c2595i.f23228k0 && this.f23229l0 == c2595i.f23229l0 && this.f23230m0 == c2595i.f23230m0 && this.f23231n0 == c2595i.f23231n0 && this.f23232o0 == c2595i.f23232o0) {
            SparseBooleanArray sparseBooleanArray = this.f23234q0;
            int size = sparseBooleanArray.size();
            SparseBooleanArray sparseBooleanArray2 = c2595i.f23234q0;
            if (sparseBooleanArray2.size() == size) {
                int i7 = 0;
                while (true) {
                    if (i7 >= size) {
                        SparseArray sparseArray = this.f23233p0;
                        int size2 = sparseArray.size();
                        SparseArray sparseArray2 = c2595i.f23233p0;
                        if (sparseArray2.size() == size2) {
                            for (int i10 = 0; i10 < size2; i10++) {
                                int iIndexOfKey = sparseArray2.indexOfKey(sparseArray.keyAt(i10));
                                if (iIndexOfKey >= 0) {
                                    Map map = (Map) sparseArray.valueAt(i10);
                                    Map map2 = (Map) sparseArray2.valueAt(iIndexOfKey);
                                    if (map2.size() == map.size()) {
                                        for (Map.Entry entry : map.entrySet()) {
                                            n0 n0Var = (n0) entry.getKey();
                                            if (map2.containsKey(n0Var)) {
                                                Object value = entry.getValue();
                                                Object obj2 = map2.get(n0Var);
                                                int i11 = AbstractC1343A.f15195a;
                                                if (!Objects.equals(value, obj2)) {
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            return true;
                        }
                    } else {
                        if (sparseBooleanArray2.indexOfKey(sparseBooleanArray.keyAt(i7)) < 0) {
                            break;
                        }
                        i7++;
                    }
                }
            }
        }
        return false;
    }

    @Override // e2.l0
    public final int hashCode() {
        return (((((((((((((((super.hashCode() + 31) * 31) + (this.f23226i0 ? 1 : 0)) * 961) + (this.f23227j0 ? 1 : 0)) * 961) + (this.f23228k0 ? 1 : 0)) * 28629151) + (this.f23229l0 ? 1 : 0)) * 31) + (this.f23230m0 ? 1 : 0)) * 31) + (this.f23231n0 ? 1 : 0)) * 961) + (this.f23232o0 ? 1 : 0)) * 31;
    }
}
