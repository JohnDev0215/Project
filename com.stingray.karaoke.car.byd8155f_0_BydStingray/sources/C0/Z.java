package C0;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class Z extends E0.D {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Z f880b = new Z("Undefined intrinsics block and it is required");

    @Override // C0.K
    public final L h(M m10, List list, long j3) {
        boolean zIsEmpty = list.isEmpty();
        n7.v vVar = n7.v.l;
        if (zIsEmpty) {
            return m10.m0(a1.a.j(j3), a1.a.i(j3), vVar, C0113u.f914p);
        }
        if (list.size() == 1) {
            V vB = ((J) list.get(0)).b(j3);
            return m10.m0(io.sentry.config.a.S(j3, vB.l), io.sentry.config.a.R(j3, vB.f871m), vVar, new X(vB, 0));
        }
        ArrayList arrayList = new ArrayList(list.size());
        int size = list.size();
        for (int i7 = 0; i7 < size; i7++) {
            arrayList.add(((J) list.get(i7)).b(j3));
        }
        int size2 = arrayList.size();
        int iMax = 0;
        int iMax2 = 0;
        for (int i10 = 0; i10 < size2; i10++) {
            V v10 = (V) arrayList.get(i10);
            iMax = Math.max(v10.l, iMax);
            iMax2 = Math.max(v10.f871m, iMax2);
        }
        return m10.m0(io.sentry.config.a.S(j3, iMax), io.sentry.config.a.R(j3, iMax2), vVar, new Y(0, arrayList));
    }
}
