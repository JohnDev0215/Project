package O;

import C0.V;
import C0.Y;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class F implements C0.K {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final F f4897a = new F();

    @Override // C0.K
    public final C0.L h(C0.M m10, List list, long j3) {
        ArrayList arrayList = new ArrayList(list.size());
        int size = list.size();
        Integer numValueOf = 0;
        for (int i7 = 0; i7 < size; i7++) {
            arrayList.add(((C0.J) list.get(i7)).b(j3));
        }
        int size2 = arrayList.size();
        Integer numValueOf2 = numValueOf;
        for (int i10 = 0; i10 < size2; i10++) {
            numValueOf2 = Integer.valueOf(Math.max(numValueOf2.intValue(), ((V) arrayList.get(i10)).l));
        }
        int iIntValue = numValueOf2.intValue();
        int size3 = arrayList.size();
        for (int i11 = 0; i11 < size3; i11++) {
            numValueOf = Integer.valueOf(Math.max(numValueOf.intValue(), ((V) arrayList.get(i11)).f871m));
        }
        return m10.m0(iIntValue, numValueOf.intValue(), n7.v.l, new Y(2, arrayList));
    }
}
