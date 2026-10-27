package Q5;

import E.C0147d;
import N0.C0408c;
import N0.C0411f;
import T.C0753m;
import T.C0761q;
import com.stingray.kca.model.KaraokeItem;
import java.io.IOException;
import java.util.List;
import org.xmlpull.v1.XmlPullParserException;
import x3.AbstractC2673a;
import z7.InterfaceC2772a;
import z7.InterfaceC2782k;
import z7.InterfaceC2785n;
import z7.InterfaceC2787p;

/* JADX INFO: loaded from: classes.dex */
public final class F2 extends A7.o implements InterfaceC2787p {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ List f6351m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ String f6352n;
    public final /* synthetic */ int o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ InterfaceC2782k f6353p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ float f6354q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ InterfaceC2785n f6355r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final /* synthetic */ D.L f6356s;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public F2(List list, String str, int i7, InterfaceC2782k interfaceC2782k, float f10, InterfaceC2785n interfaceC2785n, D.L l) {
        super(4);
        this.f6351m = list;
        this.f6352n = str;
        this.o = i7;
        this.f6353p = interfaceC2782k;
        this.f6354q = f10;
        this.f6355r = interfaceC2785n;
        this.f6356s = l;
    }

    @Override // z7.InterfaceC2787p
    public final Object o(Object obj, Object obj2, Object obj3, Object obj4) throws XmlPullParserException, IOException {
        int i7;
        InterfaceC2772a interfaceC2772a;
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
            KaraokeItem karaokeItem = (KaraokeItem) this.f6351m.get(iIntValue);
            c0761q.Q(549780092);
            C0408c c0408c = new C0408c();
            String str = karaokeItem.f13108b;
            if (str != null) {
                String str2 = this.f6352n;
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
            c0761q.Q(1541776141);
            if (list != null) {
                c0761q.Q(1541777522);
                if (!list.isEmpty()) {
                    c0761q.Q(688703242);
                    if (list.size() > this.o) {
                        c0761q.Q(688705917);
                        InterfaceC2782k interfaceC2782k = this.f6353p;
                        boolean zF = c0761q.f(interfaceC2782k) | c0761q.h(karaokeItem);
                        Object objG = c0761q.G();
                        if (zF || objG == C0753m.f8270a) {
                            objG = new H0(interfaceC2782k, karaokeItem, 3);
                            c0761q.a0(objG);
                        }
                        c0761q.p(false);
                        interfaceC2772a = (InterfaceC2772a) objG;
                    } else {
                        interfaceC2772a = null;
                    }
                    c0761q.p(false);
                    g0.o oVarD = androidx.compose.foundation.layout.c.d(g0.l.f14684a, 1.0f);
                    D.L l = this.f6356s;
                    C7.a.s(oVarD, c0411fF, this.o, this.f6354q, karaokeItem, list, interfaceC2772a, this.f6355r, androidx.compose.foundation.layout.a.c(AbstractC2673a.J(l, c0761q), 0.0f, AbstractC2673a.I(l, c0761q), 0.0f, 10), c0761q, 6);
                }
                c0761q.p(false);
            }
            c0761q.p(false);
            c0761q.p(false);
        }
        return m7.q.f19267a;
    }
}
