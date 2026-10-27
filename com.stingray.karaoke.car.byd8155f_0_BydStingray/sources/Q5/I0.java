package Q5;

import E.C0147d;
import N0.C0411f;
import T.C0753m;
import T.C0761q;
import com.stingray.kca.model.KaraokeItem;
import java.util.List;
import java.util.ListIterator;
import n3.C1945j;
import w.C2576g;
import z7.InterfaceC2772a;
import z7.InterfaceC2782k;
import z7.InterfaceC2785n;
import z7.InterfaceC2787p;

/* JADX INFO: loaded from: classes.dex */
public final class I0 extends A7.o implements InterfaceC2787p {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f6387m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f6388n;
    public final /* synthetic */ Object o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ Object f6389p;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ I0(Object obj, Object obj2, Object obj3, int i7) {
        super(4);
        this.f6387m = i7;
        this.f6388n = obj;
        this.o = obj2;
        this.f6389p = obj3;
    }

    @Override // z7.InterfaceC2787p
    public final Object o(Object obj, Object obj2, Object obj3, Object obj4) {
        int i7;
        Object objPrevious;
        switch (this.f6387m) {
            case 0:
                C0147d c0147d = (C0147d) obj;
                int iIntValue = ((Number) obj2).intValue();
                C0761q c0761q = (C0761q) obj3;
                int iIntValue2 = ((Number) obj4).intValue();
                if ((iIntValue2 & 6) == 0) {
                    i7 = (c0761q.f(c0147d) ? 4 : 2) | iIntValue2;
                } else {
                    i7 = iIntValue2;
                }
                if ((iIntValue2 & 48) == 0) {
                    i7 |= c0761q.d(iIntValue) ? 32 : 16;
                }
                if ((i7 & 147) == 146 && c0761q.x()) {
                    c0761q.K();
                } else {
                    KaraokeItem karaokeItem = (KaraokeItem) ((List) this.f6388n).get(iIntValue);
                    c0761q.Q(-1589343142);
                    List list = karaokeItem.f13111g;
                    c0761q.Q(-1436741711);
                    if (list != null) {
                        boolean zIsEmpty = list.isEmpty();
                        T.S s6 = C0753m.f8270a;
                        Boolean bool = karaokeItem.f13119q;
                        Boolean bool2 = karaokeItem.f13118p;
                        String str = karaokeItem.f13108b;
                        g0.l lVar = g0.l.f14684a;
                        InterfaceC2782k interfaceC2782k = (InterfaceC2782k) this.o;
                        if (zIsEmpty) {
                            c0761q.Q(2057460341);
                            String str2 = karaokeItem.f13107a;
                            if (str2 != null) {
                                g0.o oVarD = androidx.compose.foundation.layout.c.d(lVar, 1.0f);
                                if (str == null) {
                                    str = "";
                                }
                                C0411f c0411f = new C0411f(str, null, 6);
                                c0761q.Q(1343473601);
                                boolean zF = c0761q.f(interfaceC2782k) | c0761q.h(karaokeItem);
                                Object objG = c0761q.G();
                                if (zF || objG == s6) {
                                    objG = new H0(interfaceC2782k, karaokeItem, 1);
                                    c0761q.a0(objG);
                                }
                                c0761q.p(false);
                                Boolean bool3 = Boolean.TRUE;
                                C7.a.a(oVarD, c0411f, 0.0f, str2, (InterfaceC2772a) objG, A7.m.a(bool2, bool3), A7.m.a(bool, bool3), c0761q, 6);
                            }
                            c0761q.p(false);
                        } else {
                            c0761q.Q(2056763523);
                            g0.o oVarD2 = androidx.compose.foundation.layout.c.d(lVar, 1.0f);
                            if (str == null) {
                                str = "";
                            }
                            C0411f c0411f2 = new C0411f(str, null, 6);
                            c0761q.Q(759094178);
                            boolean zF2 = c0761q.f(interfaceC2782k) | c0761q.h(karaokeItem);
                            Object objG2 = c0761q.G();
                            if (zF2 || objG2 == s6) {
                                objG2 = new H0(interfaceC2782k, karaokeItem, 0);
                                c0761q.a0(objG2);
                            }
                            InterfaceC2772a interfaceC2772a = (InterfaceC2772a) objG2;
                            c0761q.p(false);
                            c0761q.Q(759096576);
                            InterfaceC2785n interfaceC2785n = (InterfaceC2785n) this.f6389p;
                            boolean zF3 = c0761q.f(interfaceC2785n) | c0761q.h(karaokeItem);
                            Object objG3 = c0761q.G();
                            if (zF3 || objG3 == s6) {
                                objG3 = new C0554c0(interfaceC2785n, karaokeItem, 1);
                                c0761q.a0(objG3);
                            }
                            c0761q.p(false);
                            Boolean bool4 = Boolean.TRUE;
                            C7.a.d(oVarD2, c0411f2, 0.0f, karaokeItem.f13110f, list, interfaceC2772a, (InterfaceC2782k) objG3, A7.m.a(bool2, bool4), A7.m.a(bool, bool4), c0761q, 6);
                            c0761q.p(false);
                        }
                    }
                    c0761q.p(false);
                    c0761q.p(false);
                }
                break;
            default:
                C2576g c2576g = (C2576g) obj;
                C1945j c1945j = (C1945j) obj2;
                C0761q c0761q2 = (C0761q) obj3;
                ((Number) obj4).intValue();
                if (!((Boolean) ((T.X) this.o).getValue()).booleanValue()) {
                    List list2 = (List) ((T.Q0) this.f6389p).getValue();
                    ListIterator listIterator = list2.listIterator(list2.size());
                    while (true) {
                        if (listIterator.hasPrevious()) {
                            objPrevious = listIterator.previous();
                            if (A7.m.a(c1945j, (C1945j) objPrevious)) {
                            }
                        } else {
                            objPrevious = null;
                        }
                    }
                    c1945j = (C1945j) objPrevious;
                }
                if (c1945j != null) {
                    h6.j.e(c1945j, (d0.h) this.f6388n, b0.g.b(-1263531443, new A.L(c1945j, 17, c2576g), c0761q2), c0761q2, 384);
                }
                break;
        }
        return m7.q.f19267a;
    }
}
