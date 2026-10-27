package e2;

import android.os.Bundle;
import android.util.SparseBooleanArray;
import h2.AbstractC1343A;
import h2.AbstractC1346c;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class S {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final S f13999b;
    public static final String c;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final C1206n f14000a;

    static {
        SparseBooleanArray sparseBooleanArray = new SparseBooleanArray();
        AbstractC1346c.j(!false);
        f13999b = new S(new C1206n(sparseBooleanArray));
        int i7 = AbstractC1343A.f15195a;
        c = Integer.toString(0, 36);
    }

    public S(C1206n c1206n) {
        this.f14000a = c1206n;
    }

    public static S c(Bundle bundle) {
        ArrayList<Integer> integerArrayList = bundle.getIntegerArrayList(c);
        if (integerArrayList == null) {
            return f13999b;
        }
        SparseBooleanArray sparseBooleanArray = new SparseBooleanArray();
        for (int i7 = 0; i7 < integerArrayList.size(); i7++) {
            int iIntValue = integerArrayList.get(i7).intValue();
            AbstractC1346c.j(!false);
            sparseBooleanArray.append(iIntValue, true);
        }
        AbstractC1346c.j(!false);
        return new S(new C1206n(sparseBooleanArray));
    }

    public final boolean a(int i7) {
        return this.f14000a.f14224a.get(i7);
    }

    public final boolean b(int... iArr) {
        return this.f14000a.a(iArr);
    }

    public final Bundle d() {
        Bundle bundle = new Bundle();
        ArrayList<Integer> arrayList = new ArrayList<>();
        int i7 = 0;
        while (true) {
            C1206n c1206n = this.f14000a;
            if (i7 >= c1206n.f14224a.size()) {
                bundle.putIntegerArrayList(c, arrayList);
                return bundle;
            }
            arrayList.add(Integer.valueOf(c1206n.b(i7)));
            i7++;
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof S) {
            return this.f14000a.equals(((S) obj).f14000a);
        }
        return false;
    }

    public final int hashCode() {
        return this.f14000a.hashCode();
    }
}
