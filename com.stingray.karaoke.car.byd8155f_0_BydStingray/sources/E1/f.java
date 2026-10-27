package e1;

import C0.J;
import C0.K;
import C0.L;
import C0.M;
import C0.V;
import C0.X;
import C0.Y;
import java.util.ArrayList;
import java.util.List;
import n7.AbstractC1999n;

/* JADX INFO: loaded from: classes.dex */
public final class f implements K {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final f f13777b = new f(0);
    public static final f c = new f(1);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f13778a;

    public /* synthetic */ f(int i7) {
        this.f13778a = i7;
    }

    @Override // C0.K
    public final L h(M m10, List list, long j3) {
        Object obj;
        int iMax;
        switch (this.f13778a) {
            case 0:
                ArrayList arrayList = new ArrayList(list.size());
                int size = list.size();
                for (int i7 = 0; i7 < size; i7++) {
                    arrayList.add(((J) list.get(i7)).b(j3));
                }
                int i10 = 1;
                Object obj2 = null;
                if (arrayList.isEmpty()) {
                    obj = null;
                } else {
                    obj = arrayList.get(0);
                    int i11 = ((V) obj).l;
                    int iC = AbstractC1999n.C(arrayList);
                    if (1 <= iC) {
                        int i12 = 1;
                        while (true) {
                            Object obj3 = arrayList.get(i12);
                            int i13 = ((V) obj3).l;
                            if (i11 < i13) {
                                obj = obj3;
                                i11 = i13;
                            }
                            if (i12 != iC) {
                                i12++;
                            }
                        }
                    }
                }
                V v10 = (V) obj;
                int iJ = v10 != null ? v10.l : a1.a.j(j3);
                if (!arrayList.isEmpty()) {
                    Object obj4 = arrayList.get(0);
                    int i14 = ((V) obj4).f871m;
                    int iC2 = AbstractC1999n.C(arrayList);
                    if (1 <= iC2) {
                        while (true) {
                            Object obj5 = arrayList.get(i10);
                            int i15 = ((V) obj5).f871m;
                            if (i14 < i15) {
                                obj4 = obj5;
                                i14 = i15;
                            }
                            if (i10 != iC2) {
                                i10++;
                            }
                        }
                    }
                    obj2 = obj4;
                }
                V v11 = (V) obj2;
                return m10.m0(iJ, v11 != null ? v11.f871m : a1.a.i(j3), n7.v.l, new Y(3, arrayList));
            default:
                int size2 = list.size();
                n7.v vVar = n7.v.l;
                int i16 = 0;
                if (size2 == 0) {
                    return m10.m0(0, 0, vVar, C1190c.f13769r);
                }
                if (size2 == 1) {
                    V vB = ((J) list.get(0)).b(j3);
                    return m10.m0(vB.l, vB.f871m, vVar, new X(vB, 10));
                }
                ArrayList arrayList2 = new ArrayList(list.size());
                int size3 = list.size();
                for (int i17 = 0; i17 < size3; i17++) {
                    arrayList2.add(((J) list.get(i17)).b(j3));
                }
                int iC3 = AbstractC1999n.C(arrayList2);
                if (iC3 >= 0) {
                    int iMax2 = 0;
                    iMax = 0;
                    while (true) {
                        V v12 = (V) arrayList2.get(i16);
                        iMax2 = Math.max(iMax2, v12.l);
                        iMax = Math.max(iMax, v12.f871m);
                        if (i16 != iC3) {
                            i16++;
                        } else {
                            i16 = iMax2;
                        }
                    }
                } else {
                    iMax = 0;
                }
                return m10.m0(i16, iMax, vVar, new Y(4, arrayList2));
        }
    }
}
