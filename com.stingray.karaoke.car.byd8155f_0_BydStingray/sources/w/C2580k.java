package w;

import C0.InterfaceC0108o;
import C0.V;
import C0.Y;
import java.util.ArrayList;
import java.util.List;
import n7.AbstractC1999n;

/* JADX INFO: renamed from: w.k, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2580k implements C0.K {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final r f23107a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f23108b;

    public C2580k(r rVar) {
        this.f23107a = rVar;
    }

    @Override // C0.K
    public final int a(InterfaceC0108o interfaceC0108o, List list, int i7) {
        Integer numValueOf;
        if (list.isEmpty()) {
            numValueOf = null;
        } else {
            numValueOf = Integer.valueOf(((C0.J) list.get(0)).P(i7));
            int iC = AbstractC1999n.C(list);
            int i10 = 1;
            if (1 <= iC) {
                while (true) {
                    Integer numValueOf2 = Integer.valueOf(((C0.J) list.get(i10)).P(i7));
                    if (numValueOf2.compareTo(numValueOf) > 0) {
                        numValueOf = numValueOf2;
                    }
                    if (i10 == iC) {
                        break;
                    }
                    i10++;
                }
            }
        }
        if (numValueOf != null) {
            return numValueOf.intValue();
        }
        return 0;
    }

    @Override // C0.K
    public final int d(InterfaceC0108o interfaceC0108o, List list, int i7) {
        Integer numValueOf;
        if (list.isEmpty()) {
            numValueOf = null;
        } else {
            numValueOf = Integer.valueOf(((C0.J) list.get(0)).c(i7));
            int iC = AbstractC1999n.C(list);
            int i10 = 1;
            if (1 <= iC) {
                while (true) {
                    Integer numValueOf2 = Integer.valueOf(((C0.J) list.get(i10)).c(i7));
                    if (numValueOf2.compareTo(numValueOf) > 0) {
                        numValueOf = numValueOf2;
                    }
                    if (i10 == iC) {
                        break;
                    }
                    i10++;
                }
            }
        }
        if (numValueOf != null) {
            return numValueOf.intValue();
        }
        return 0;
    }

    @Override // C0.K
    public final C0.L h(C0.M m10, List list, long j3) {
        Object obj;
        ArrayList arrayList = new ArrayList(list.size());
        int size = list.size();
        for (int i7 = 0; i7 < size; i7++) {
            arrayList.add(((C0.J) list.get(i7)).b(j3));
        }
        Object obj2 = null;
        if (arrayList.isEmpty()) {
            obj = null;
        } else {
            obj = arrayList.get(0);
            int i10 = ((V) obj).l;
            int iC = AbstractC1999n.C(arrayList);
            if (1 <= iC) {
                int i11 = 1;
                while (true) {
                    Object obj3 = arrayList.get(i11);
                    int i12 = ((V) obj3).l;
                    if (i10 < i12) {
                        obj = obj3;
                        i10 = i12;
                    }
                    if (i11 == iC) {
                        break;
                    }
                    i11++;
                }
            }
        }
        V v10 = (V) obj;
        int i13 = v10 != null ? v10.l : 0;
        if (!arrayList.isEmpty()) {
            obj2 = arrayList.get(0);
            int i14 = ((V) obj2).f871m;
            int iC2 = AbstractC1999n.C(arrayList);
            if (1 <= iC2) {
                int i15 = 1;
                while (true) {
                    Object obj4 = arrayList.get(i15);
                    int i16 = ((V) obj4).f871m;
                    if (i14 < i16) {
                        obj2 = obj4;
                        i14 = i16;
                    }
                    if (i15 == iC2) {
                        break;
                    }
                    i15++;
                }
            }
        }
        V v11 = (V) obj2;
        int i17 = v11 != null ? v11.f871m : 0;
        boolean zV = m10.v();
        r rVar = this.f23107a;
        if (zV) {
            this.f23108b = true;
            rVar.f23137a.setValue(new a1.j(H7.H.c(i13, i17)));
        } else if (!this.f23108b) {
            rVar.f23137a.setValue(new a1.j(H7.H.c(i13, i17)));
        }
        return m10.m0(i13, i17, n7.v.l, new Y(5, arrayList));
    }

    @Override // C0.K
    public final int i(InterfaceC0108o interfaceC0108o, List list, int i7) {
        Integer numValueOf;
        if (list.isEmpty()) {
            numValueOf = null;
        } else {
            numValueOf = Integer.valueOf(((C0.J) list.get(0)).a0(i7));
            int iC = AbstractC1999n.C(list);
            int i10 = 1;
            if (1 <= iC) {
                while (true) {
                    Integer numValueOf2 = Integer.valueOf(((C0.J) list.get(i10)).a0(i7));
                    if (numValueOf2.compareTo(numValueOf) > 0) {
                        numValueOf = numValueOf2;
                    }
                    if (i10 == iC) {
                        break;
                    }
                    i10++;
                }
            }
        }
        if (numValueOf != null) {
            return numValueOf.intValue();
        }
        return 0;
    }

    @Override // C0.K
    public final int j(InterfaceC0108o interfaceC0108o, List list, int i7) {
        Integer numValueOf;
        if (list.isEmpty()) {
            numValueOf = null;
        } else {
            numValueOf = Integer.valueOf(((C0.J) list.get(0)).V(i7));
            int iC = AbstractC1999n.C(list);
            int i10 = 1;
            if (1 <= iC) {
                while (true) {
                    Integer numValueOf2 = Integer.valueOf(((C0.J) list.get(i10)).V(i7));
                    if (numValueOf2.compareTo(numValueOf) > 0) {
                        numValueOf = numValueOf2;
                    }
                    if (i10 == iC) {
                        break;
                    }
                    i10++;
                }
            }
        }
        if (numValueOf != null) {
            return numValueOf.intValue();
        }
        return 0;
    }
}
