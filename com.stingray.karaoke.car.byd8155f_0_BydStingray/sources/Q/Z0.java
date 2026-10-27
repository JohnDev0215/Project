package Q;

import C0.InterfaceC0108o;
import java.util.List;
import java.util.NoSuchElementException;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class Z0 implements C0.K {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final boolean f5781a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final float f5782b;
    public final D.L c;

    public Z0(boolean z6, float f10, D.L l) {
        this.f5781a = z6;
        this.f5782b = f10;
        this.c = l;
    }

    public static int c(List list, int i7, InterfaceC2785n interfaceC2785n) {
        Object obj;
        Object obj2;
        Object obj3;
        Object obj4;
        Object obj5;
        Object obj6;
        int size = list.size();
        for (int i10 = 0; i10 < size; i10++) {
            Object obj7 = list.get(i10);
            if (A7.m.a(R.O.d((C0.J) obj7), "TextField")) {
                int iIntValue = ((Number) interfaceC2785n.invoke(obj7, Integer.valueOf(i7))).intValue();
                int size2 = list.size();
                int i11 = 0;
                while (true) {
                    obj = null;
                    if (i11 >= size2) {
                        obj2 = null;
                        break;
                    }
                    obj2 = list.get(i11);
                    if (A7.m.a(R.O.d((C0.J) obj2), "Label")) {
                        break;
                    }
                    i11++;
                }
                C0.J j3 = (C0.J) obj2;
                int iIntValue2 = j3 != null ? ((Number) interfaceC2785n.invoke(j3, Integer.valueOf(i7))).intValue() : 0;
                int size3 = list.size();
                int i12 = 0;
                while (true) {
                    if (i12 >= size3) {
                        obj3 = null;
                        break;
                    }
                    obj3 = list.get(i12);
                    if (A7.m.a(R.O.d((C0.J) obj3), "Trailing")) {
                        break;
                    }
                    i12++;
                }
                C0.J j10 = (C0.J) obj3;
                int iIntValue3 = j10 != null ? ((Number) interfaceC2785n.invoke(j10, Integer.valueOf(i7))).intValue() : 0;
                int size4 = list.size();
                int i13 = 0;
                while (true) {
                    if (i13 >= size4) {
                        obj4 = null;
                        break;
                    }
                    obj4 = list.get(i13);
                    if (A7.m.a(R.O.d((C0.J) obj4), "Prefix")) {
                        break;
                    }
                    i13++;
                }
                C0.J j11 = (C0.J) obj4;
                int iIntValue4 = j11 != null ? ((Number) interfaceC2785n.invoke(j11, Integer.valueOf(i7))).intValue() : 0;
                int size5 = list.size();
                int i14 = 0;
                while (true) {
                    if (i14 >= size5) {
                        obj5 = null;
                        break;
                    }
                    obj5 = list.get(i14);
                    if (A7.m.a(R.O.d((C0.J) obj5), "Suffix")) {
                        break;
                    }
                    i14++;
                }
                C0.J j12 = (C0.J) obj5;
                int iIntValue5 = j12 != null ? ((Number) interfaceC2785n.invoke(j12, Integer.valueOf(i7))).intValue() : 0;
                int size6 = list.size();
                int i15 = 0;
                while (true) {
                    if (i15 >= size6) {
                        obj6 = null;
                        break;
                    }
                    obj6 = list.get(i15);
                    if (A7.m.a(R.O.d((C0.J) obj6), "Leading")) {
                        break;
                    }
                    i15++;
                }
                C0.J j13 = (C0.J) obj6;
                int iIntValue6 = j13 != null ? ((Number) interfaceC2785n.invoke(j13, Integer.valueOf(i7))).intValue() : 0;
                int size7 = list.size();
                int i16 = 0;
                while (true) {
                    if (i16 >= size7) {
                        break;
                    }
                    Object obj8 = list.get(i16);
                    if (A7.m.a(R.O.d((C0.J) obj8), "Hint")) {
                        obj = obj8;
                        break;
                    }
                    i16++;
                }
                C0.J j14 = (C0.J) obj;
                int iIntValue7 = j14 != null ? ((Number) interfaceC2785n.invoke(j14, Integer.valueOf(i7))).intValue() : 0;
                long j15 = R.O.f7277a;
                float f10 = X0.f5757a;
                int i17 = iIntValue4 + iIntValue5;
                return Math.max(Math.max(iIntValue + i17, Math.max(iIntValue7 + i17, iIntValue2)) + iIntValue6 + iIntValue3, a1.a.j(j15));
            }
        }
        throw new NoSuchElementException("Collection contains no element matching the predicate.");
    }

    @Override // C0.K
    public final int a(InterfaceC0108o interfaceC0108o, List list, int i7) {
        return c(list, i7, C0538w0.f6066r);
    }

    public final int b(InterfaceC0108o interfaceC0108o, List list, int i7, InterfaceC2785n interfaceC2785n) {
        Object obj;
        int iV;
        int iIntValue;
        Object obj2;
        int iIntValue2;
        Object obj3;
        Object obj4;
        int i10;
        Object obj5;
        int i11;
        Object obj6;
        Object obj7;
        int size = list.size();
        int i12 = 0;
        while (true) {
            if (i12 >= size) {
                obj = null;
                break;
            }
            obj = list.get(i12);
            if (A7.m.a(R.O.d((C0.J) obj), "Leading")) {
                break;
            }
            i12++;
        }
        C0.J j3 = (C0.J) obj;
        if (j3 != null) {
            iV = i7 == Integer.MAX_VALUE ? i7 : i7 - j3.V(Integer.MAX_VALUE);
            iIntValue = ((Number) interfaceC2785n.invoke(j3, Integer.valueOf(i7))).intValue();
        } else {
            iV = i7;
            iIntValue = 0;
        }
        int size2 = list.size();
        int i13 = 0;
        while (true) {
            if (i13 >= size2) {
                obj2 = null;
                break;
            }
            obj2 = list.get(i13);
            if (A7.m.a(R.O.d((C0.J) obj2), "Trailing")) {
                break;
            }
            i13++;
        }
        C0.J j10 = (C0.J) obj2;
        if (j10 != null) {
            int iV2 = j10.V(Integer.MAX_VALUE);
            if (iV != Integer.MAX_VALUE) {
                iV -= iV2;
            }
            iIntValue2 = ((Number) interfaceC2785n.invoke(j10, Integer.valueOf(i7))).intValue();
        } else {
            iIntValue2 = 0;
        }
        int size3 = list.size();
        int i14 = 0;
        while (true) {
            if (i14 >= size3) {
                obj3 = null;
                break;
            }
            obj3 = list.get(i14);
            if (A7.m.a(R.O.d((C0.J) obj3), "Label")) {
                break;
            }
            i14++;
        }
        Object obj8 = (C0.J) obj3;
        int iIntValue3 = obj8 != null ? ((Number) interfaceC2785n.invoke(obj8, Integer.valueOf(iV))).intValue() : 0;
        int size4 = list.size();
        int i15 = 0;
        while (true) {
            if (i15 >= size4) {
                obj4 = null;
                break;
            }
            obj4 = list.get(i15);
            if (A7.m.a(R.O.d((C0.J) obj4), "Prefix")) {
                break;
            }
            i15++;
        }
        C0.J j11 = (C0.J) obj4;
        if (j11 != null) {
            int iIntValue4 = ((Number) interfaceC2785n.invoke(j11, Integer.valueOf(iV))).intValue();
            int iV3 = j11.V(Integer.MAX_VALUE);
            if (iV != Integer.MAX_VALUE) {
                iV -= iV3;
            }
            i10 = iIntValue4;
        } else {
            i10 = 0;
        }
        int size5 = list.size();
        int i16 = 0;
        while (true) {
            if (i16 >= size5) {
                obj5 = null;
                break;
            }
            obj5 = list.get(i16);
            if (A7.m.a(R.O.d((C0.J) obj5), "Suffix")) {
                break;
            }
            i16++;
        }
        C0.J j12 = (C0.J) obj5;
        if (j12 != null) {
            int iIntValue5 = ((Number) interfaceC2785n.invoke(j12, Integer.valueOf(iV))).intValue();
            int iV4 = j12.V(Integer.MAX_VALUE);
            if (iV != Integer.MAX_VALUE) {
                iV -= iV4;
            }
            i11 = iIntValue5;
        } else {
            i11 = 0;
        }
        int size6 = list.size();
        for (int i17 = 0; i17 < size6; i17++) {
            Object obj9 = list.get(i17);
            if (A7.m.a(R.O.d((C0.J) obj9), "TextField")) {
                int iIntValue6 = ((Number) interfaceC2785n.invoke(obj9, Integer.valueOf(iV))).intValue();
                int size7 = list.size();
                int i18 = 0;
                while (true) {
                    if (i18 >= size7) {
                        obj6 = null;
                        break;
                    }
                    obj6 = list.get(i18);
                    if (A7.m.a(R.O.d((C0.J) obj6), "Hint")) {
                        break;
                    }
                    i18++;
                }
                Object obj10 = (C0.J) obj6;
                int iIntValue7 = obj10 != null ? ((Number) interfaceC2785n.invoke(obj10, Integer.valueOf(iV))).intValue() : 0;
                int size8 = list.size();
                int i19 = 0;
                while (true) {
                    if (i19 >= size8) {
                        obj7 = null;
                        break;
                    }
                    Object obj11 = list.get(i19);
                    if (A7.m.a(R.O.d((C0.J) obj11), "Supporting")) {
                        obj7 = obj11;
                        break;
                    }
                    i19++;
                }
                Object obj12 = (C0.J) obj7;
                return X0.c(iIntValue6, iIntValue3, iIntValue, iIntValue2, i10, i11, iIntValue7, obj12 != null ? ((Number) interfaceC2785n.invoke(obj12, Integer.valueOf(i7))).intValue() : 0, this.f5782b, R.O.f7277a, interfaceC0108o.getDensity(), this.c);
            }
        }
        throw new NoSuchElementException("Collection contains no element matching the predicate.");
    }

    @Override // C0.K
    public final int d(InterfaceC0108o interfaceC0108o, List list, int i7) {
        return b(interfaceC0108o, list, i7, C0538w0.o);
    }

    @Override // C0.K
    public final C0.L h(C0.M m10, List list, long j3) {
        Object obj;
        Object obj2;
        Object obj3;
        Object obj4;
        Object obj5;
        Object obj6;
        Object obj7;
        List list2 = list;
        D.L l = this.c;
        int iO = m10.O(l.d());
        int iO2 = m10.O(l.a());
        long jA = a1.a.a(j3, 0, 0, 0, 0, 10);
        int size = list.size();
        int i7 = 0;
        while (true) {
            if (i7 >= size) {
                obj = null;
                break;
            }
            obj = list2.get(i7);
            if (A7.m.a(androidx.compose.ui.layout.a.a((C0.J) obj), "Leading")) {
                break;
            }
            i7++;
        }
        C0.J j10 = (C0.J) obj;
        C0.V vB = j10 != null ? j10.b(jA) : null;
        int iF = R.O.f(vB);
        int iMax = Math.max(0, R.O.e(vB));
        int size2 = list.size();
        int i10 = 0;
        while (true) {
            if (i10 >= size2) {
                obj2 = null;
                break;
            }
            obj2 = list2.get(i10);
            if (A7.m.a(androidx.compose.ui.layout.a.a((C0.J) obj2), "Trailing")) {
                break;
            }
            i10++;
        }
        C0.J j11 = (C0.J) obj2;
        C0.V vB2 = j11 != null ? j11.b(io.sentry.config.a.h0(-iF, 0, 2, jA)) : null;
        int iF2 = R.O.f(vB2) + iF;
        int iMax2 = Math.max(iMax, R.O.e(vB2));
        int size3 = list.size();
        int i11 = 0;
        while (true) {
            if (i11 >= size3) {
                obj3 = null;
                break;
            }
            obj3 = list2.get(i11);
            if (A7.m.a(androidx.compose.ui.layout.a.a((C0.J) obj3), "Prefix")) {
                break;
            }
            i11++;
        }
        C0.J j12 = (C0.J) obj3;
        C0.V vB3 = j12 != null ? j12.b(io.sentry.config.a.h0(-iF2, 0, 2, jA)) : null;
        int iF3 = R.O.f(vB3) + iF2;
        int iMax3 = Math.max(iMax2, R.O.e(vB3));
        int size4 = list.size();
        int i12 = 0;
        while (true) {
            if (i12 >= size4) {
                obj4 = null;
                break;
            }
            obj4 = list2.get(i12);
            if (A7.m.a(androidx.compose.ui.layout.a.a((C0.J) obj4), "Suffix")) {
                break;
            }
            i12++;
        }
        C0.J j13 = (C0.J) obj4;
        C0.V vB4 = j13 != null ? j13.b(io.sentry.config.a.h0(-iF3, 0, 2, jA)) : null;
        int iF4 = R.O.f(vB4) + iF3;
        int iMax4 = Math.max(iMax3, R.O.e(vB4));
        int i13 = -iF4;
        long jG0 = io.sentry.config.a.g0(i13, -iO2, jA);
        int size5 = list.size();
        int i14 = 0;
        while (true) {
            if (i14 >= size5) {
                obj5 = null;
                break;
            }
            Object obj8 = list2.get(i14);
            int i15 = size5;
            if (A7.m.a(androidx.compose.ui.layout.a.a((C0.J) obj8), "Label")) {
                obj5 = obj8;
                break;
            }
            i14++;
            size5 = i15;
        }
        C0.J j14 = (C0.J) obj5;
        C0.V vB5 = j14 != null ? j14.b(jG0) : null;
        int size6 = list.size();
        int i16 = 0;
        while (true) {
            if (i16 >= size6) {
                obj6 = null;
                break;
            }
            obj6 = list2.get(i16);
            int i17 = size6;
            if (A7.m.a(androidx.compose.ui.layout.a.a((C0.J) obj6), "Supporting")) {
                break;
            }
            i16++;
            size6 = i17;
        }
        C0.J j15 = (C0.J) obj6;
        int iA0 = j15 != null ? j15.a0(a1.a.j(j3)) : 0;
        int iE = R.O.e(vB5) + iO;
        long jG02 = io.sentry.config.a.g0(i13, ((-iE) - iO2) - iA0, a1.a.a(j3, 0, 0, 0, 0, 11));
        int size7 = list.size();
        int i18 = 0;
        while (i18 < size7) {
            int i19 = size7;
            C0.J j16 = (C0.J) list2.get(i18);
            int i20 = i18;
            if (A7.m.a(androidx.compose.ui.layout.a.a(j16), "TextField")) {
                C0.V vB6 = j16.b(jG02);
                long jA2 = a1.a.a(jG02, 0, 0, 0, 0, 14);
                int size8 = list.size();
                int i21 = 0;
                while (true) {
                    if (i21 >= size8) {
                        obj7 = null;
                        break;
                    }
                    obj7 = list2.get(i21);
                    int i22 = size8;
                    if (A7.m.a(androidx.compose.ui.layout.a.a((C0.J) obj7), "Hint")) {
                        break;
                    }
                    i21++;
                    list2 = list;
                    size8 = i22;
                }
                C0.J j17 = (C0.J) obj7;
                C0.V vB7 = j17 != null ? j17.b(jA2) : null;
                int iMax5 = Math.max(iMax4, Math.max(R.O.e(vB6), R.O.e(vB7)) + iE + iO2);
                int iF5 = R.O.f(vB);
                int iF6 = R.O.f(vB2);
                int iF7 = R.O.f(vB3) + R.O.f(vB4);
                int iMax6 = Math.max(Math.max(vB6.l + iF7, Math.max(R.O.f(vB7) + iF7, R.O.f(vB5))) + iF5 + iF6, a1.a.j(j3));
                C0.V vB8 = j15 != null ? j15.b(a1.a.a(io.sentry.config.a.h0(0, -iMax5, 1, jA), 0, iMax6, 0, 0, 9)) : null;
                int iE2 = R.O.e(vB8);
                int iC = X0.c(vB6.f871m, R.O.e(vB5), R.O.e(vB), R.O.e(vB2), R.O.e(vB3), R.O.e(vB4), R.O.e(vB7), R.O.e(vB8), this.f5782b, j3, m10.getDensity(), this.c);
                int i23 = iC - iE2;
                int size9 = list.size();
                int i24 = 0;
                while (i24 < size9) {
                    C0.J j18 = (C0.J) list.get(i24);
                    int i25 = size9;
                    if (A7.m.a(androidx.compose.ui.layout.a.a(j18), "Container")) {
                        return m10.m0(iMax6, iC, n7.v.l, new Y0(vB5, iMax6, iC, vB6, vB7, vB, vB2, vB3, vB4, j18.b(io.sentry.config.a.e(iMax6 != Integer.MAX_VALUE ? iMax6 : 0, iMax6, i23 != Integer.MAX_VALUE ? i23 : 0, i23)), vB8, this, iO, m10));
                    }
                    i24++;
                    size9 = i25;
                }
                throw new NoSuchElementException("Collection contains no element matching the predicate.");
            }
            list2 = list;
            i18 = i20 + 1;
            size7 = i19;
        }
        throw new NoSuchElementException("Collection contains no element matching the predicate.");
    }

    @Override // C0.K
    public final int i(InterfaceC0108o interfaceC0108o, List list, int i7) {
        return b(interfaceC0108o, list, i7, C0538w0.f6065q);
    }

    @Override // C0.K
    public final int j(InterfaceC0108o interfaceC0108o, List list, int i7) {
        return c(list, i7, C0538w0.f6064p);
    }
}
