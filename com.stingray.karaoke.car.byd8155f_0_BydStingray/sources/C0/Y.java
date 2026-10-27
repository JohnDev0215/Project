package C0;

import java.util.ArrayList;
import n7.AbstractC1999n;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class Y extends A7.o implements InterfaceC2782k {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f878m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ ArrayList f879n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ Y(int i7, ArrayList arrayList) {
        super(1);
        this.f878m = i7;
        this.f879n = arrayList;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        switch (this.f878m) {
            case 0:
                U u4 = (U) obj;
                ArrayList arrayList = this.f879n;
                int size = arrayList.size();
                for (int i7 = 0; i7 < size; i7++) {
                    U.g(u4, (V) arrayList.get(i7), 0, 0);
                }
                break;
            case 1:
                U u10 = (U) obj;
                ArrayList arrayList2 = this.f879n;
                int size2 = arrayList2.size();
                for (int i10 = 0; i10 < size2; i10++) {
                    U.f(u10, (V) arrayList2.get(i10), 0, 0);
                }
                break;
            case 2:
                U u11 = (U) obj;
                ArrayList arrayList3 = this.f879n;
                int size3 = arrayList3.size();
                for (int i11 = 0; i11 < size3; i11++) {
                    U.d(u11, (V) arrayList3.get(i11), 0, 0);
                }
                break;
            case 3:
                U u12 = (U) obj;
                ArrayList arrayList4 = this.f879n;
                int size4 = arrayList4.size();
                for (int i12 = 0; i12 < size4; i12++) {
                    U.f(u12, (V) arrayList4.get(i12), 0, 0);
                }
                break;
            case 4:
                U u13 = (U) obj;
                ArrayList arrayList5 = this.f879n;
                int iC = AbstractC1999n.C(arrayList5);
                if (iC >= 0) {
                    int i13 = 0;
                    while (true) {
                        U.f(u13, (V) arrayList5.get(i13), 0, 0);
                        if (i13 != iC) {
                            i13++;
                        }
                    }
                }
                break;
            default:
                U u14 = (U) obj;
                ArrayList arrayList6 = this.f879n;
                int size5 = arrayList6.size();
                for (int i14 = 0; i14 < size5; i14++) {
                    U.d(u14, (V) arrayList6.get(i14), 0, 0);
                }
                break;
        }
        return m7.q.f19267a;
    }
}
