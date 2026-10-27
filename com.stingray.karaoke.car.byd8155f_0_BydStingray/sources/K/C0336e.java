package K;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: renamed from: K.e, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0336e implements C0.K {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final C0336e f3247b = new C0336e(0);
    public static final C0336e c = new C0336e(1);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f3248a;

    public /* synthetic */ C0336e(int i7) {
        this.f3248a = i7;
    }

    @Override // C0.K
    public final C0.L h(C0.M m10, List list, long j3) {
        switch (this.f3248a) {
            case 0:
                ArrayList arrayList = new ArrayList(list.size());
                int size = list.size();
                for (int i7 = 0; i7 < size; i7++) {
                    arrayList.add(((C0.J) list.get(i7)).b(j3));
                }
                return m10.m0(a1.a.h(j3), a1.a.g(j3), n7.v.l, new C0.Y(1, arrayList));
            default:
                return m10.m0(a1.a.h(j3), a1.a.g(j3), n7.v.l, C0340g.f3255p);
        }
    }
}
