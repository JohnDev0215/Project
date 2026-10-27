package w;

import C0.InterfaceC0108o;
import C0.V;
import java.util.List;
import n7.AbstractC1999n;

/* JADX INFO: renamed from: w.f, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2575f implements C0.K {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final C2579j f23101a;

    public C2575f(C2579j c2579j) {
        this.f23101a = c2579j;
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
        V v10;
        V v11;
        int i7;
        int size = list.size();
        V[] vArr = new V[size];
        int size2 = list.size();
        long j10 = 0;
        int i10 = 0;
        int i11 = 0;
        while (true) {
            v10 = null;
            if (i11 >= size2) {
                break;
            }
            C0.J j11 = (C0.J) list.get(i11);
            Object objI = j11.i();
            C2577h c2577h = objI instanceof C2577h ? (C2577h) objI : null;
            if (c2577h != null && ((Boolean) c2577h.f23102a.getValue()).booleanValue()) {
                V vB = j11.b(j3);
                long jC = H7.H.c(vB.l, vB.f871m);
                vArr[i11] = vB;
                j10 = jC;
            }
            i11++;
        }
        int size3 = list.size();
        for (int i12 = 0; i12 < size3; i12++) {
            C0.J j12 = (C0.J) list.get(i12);
            if (vArr[i12] == null) {
                vArr[i12] = j12.b(j3);
            }
        }
        if (m10.v()) {
            i7 = (int) (j10 >> 32);
        } else {
            if (size == 0) {
                v11 = null;
            } else {
                v11 = vArr[0];
                int i13 = size - 1;
                if (i13 != 0) {
                    int i14 = v11 != null ? v11.l : 0;
                    G7.c it = new G7.d(1, i13, 1).iterator();
                    while (it.f2715n) {
                        V v12 = vArr[it.b()];
                        int i15 = v12 != null ? v12.l : 0;
                        if (i14 < i15) {
                            v11 = v12;
                            i14 = i15;
                        }
                    }
                }
            }
            i7 = v11 != null ? v11.l : 0;
        }
        if (m10.v()) {
            i10 = (int) (4294967295L & j10);
        } else {
            if (size != 0) {
                v10 = vArr[0];
                int i16 = size - 1;
                if (i16 != 0) {
                    int i17 = v10 != null ? v10.f871m : 0;
                    G7.c it2 = new G7.d(1, i16, 1).iterator();
                    while (it2.f2715n) {
                        V v13 = vArr[it2.b()];
                        int i18 = v13 != null ? v13.f871m : 0;
                        if (i17 < i18) {
                            v10 = v13;
                            i17 = i18;
                        }
                    }
                }
            }
            if (v10 != null) {
                i10 = v10.f871m;
            }
        }
        if (!m10.v()) {
            this.f23101a.c.setValue(new a1.j(H7.H.c(i7, i10)));
        }
        return m10.m0(i7, i10, n7.v.l, new C2574e(vArr, this, i7, i10));
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
