package Q5;

import E.C0147d;
import N0.C0408c;
import N0.C0411f;
import T.C0753m;
import T.C0761q;
import com.stingray.kca.model.KaraokeItem;
import com.stingray.kca.model.KaraokeItemType;
import java.util.List;
import z7.InterfaceC2772a;
import z7.InterfaceC2782k;
import z7.InterfaceC2785n;
import z7.InterfaceC2787p;

/* JADX INFO: loaded from: classes.dex */
public final class D2 extends A7.o implements InterfaceC2787p {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ List f6316m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ String f6317n;
    public final /* synthetic */ InterfaceC2785n o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ InterfaceC2782k f6318p;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public D2(List list, String str, InterfaceC2785n interfaceC2785n, InterfaceC2782k interfaceC2782k) {
        super(4);
        this.f6316m = list;
        this.f6317n = str;
        this.o = interfaceC2785n;
        this.f6318p = interfaceC2782k;
    }

    @Override // z7.InterfaceC2787p
    public final Object o(Object obj, Object obj2, Object obj3, Object obj4) {
        int i7;
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
            KaraokeItem karaokeItem = (KaraokeItem) this.f6316m.get(iIntValue);
            c0761q.Q(-1201906537);
            C0408c c0408c = new C0408c();
            String str = karaokeItem.f13108b;
            if (str != null) {
                String str2 = this.f6317n;
                int iK0 = Q8.h.K0(str, str2, 0, false, 6);
                if (iK0 >= 0) {
                    String strSubstring = str.substring(0, iK0);
                    A7.m.e(strSubstring, "substring(...)");
                    c0408c.c(strSubstring);
                    int iE = c0408c.e(new N0.D(0L, 0L, S0.A.f7802u, null, null, null, null, 0L, null, null, null, 0L, null, null, 65531));
                    try {
                        c0408c.c(str2);
                        c0408c.d(iE);
                        String strSubstring2 = str.substring(str2.length() + iK0);
                        A7.m.e(strSubstring2, "substring(...)");
                        c0408c.c(strSubstring2);
                    } catch (Throwable th) {
                        c0408c.d(iE);
                        throw th;
                    }
                } else {
                    c0408c.c(str);
                }
            }
            C0411f c0411fF = c0408c.f();
            List list = karaokeItem.f13111g;
            c0761q.Q(-1424223854);
            if (list != null) {
                c0761q.Q(-1424222473);
                if (!list.isEmpty()) {
                    KaraokeItemType karaokeItemType = KaraokeItemType.HEROBANNER;
                    T.S s6 = C0753m.f8270a;
                    KaraokeItemType karaokeItemType2 = karaokeItem.f13109e;
                    g0.l lVar = g0.l.f14684a;
                    InterfaceC2785n interfaceC2785n = this.o;
                    if (karaokeItemType2 == karaokeItemType) {
                        c0761q.Q(-976836165);
                        g0.o oVarD = androidx.compose.foundation.layout.c.d(lVar, 1.0f);
                        KaraokeItem karaokeItem2 = (KaraokeItem) list.get(0);
                        c0761q.Q(-1001335863);
                        boolean zF = c0761q.f(interfaceC2785n) | c0761q.h(karaokeItem);
                        Object objG = c0761q.G();
                        if (zF || objG == s6) {
                            objG = new C0554c0(interfaceC2785n, karaokeItem, 2);
                            c0761q.a0(objG);
                        }
                        c0761q.p(false);
                        C7.a.c(oVarD, karaokeItem2, (InterfaceC2782k) objG, c0761q, 6);
                        c0761q.p(false);
                    } else {
                        c0761q.Q(-976472783);
                        g0.o oVarD2 = androidx.compose.foundation.layout.c.d(lVar, 1.0f);
                        c0761q.Q(-1001320344);
                        InterfaceC2782k interfaceC2782k = this.f6318p;
                        boolean zF2 = c0761q.f(interfaceC2782k) | c0761q.h(karaokeItem);
                        Object objG2 = c0761q.G();
                        if (zF2 || objG2 == s6) {
                            objG2 = new H0(interfaceC2782k, karaokeItem, 2);
                            c0761q.a0(objG2);
                        }
                        InterfaceC2772a interfaceC2772a = (InterfaceC2772a) objG2;
                        c0761q.p(false);
                        c0761q.Q(-1001317851);
                        boolean zF3 = c0761q.f(interfaceC2785n) | c0761q.h(karaokeItem);
                        Object objG3 = c0761q.G();
                        if (zF3 || objG3 == s6) {
                            objG3 = new C0554c0(interfaceC2785n, karaokeItem, 3);
                            c0761q.a0(objG3);
                        }
                        c0761q.p(false);
                        Boolean bool = Boolean.TRUE;
                        C7.a.d(oVarD2, c0411fF, 0.0f, karaokeItem.f13110f, list, interfaceC2772a, (InterfaceC2782k) objG3, A7.m.a(karaokeItem.f13118p, bool), A7.m.a(karaokeItem.f13119q, bool), c0761q, 6);
                        c0761q.p(false);
                    }
                }
                c0761q.p(false);
            }
            c0761q.p(false);
            c0761q.p(false);
        }
        return m7.q.f19267a;
    }
}
