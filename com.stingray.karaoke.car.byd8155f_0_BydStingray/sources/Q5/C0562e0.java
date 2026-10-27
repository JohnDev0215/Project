package Q5;

import E.C0147d;
import T.C0753m;
import T.C0761q;
import com.stingray.kca.model.KaraokeItem;
import com.stingray.kca.model.KaraokeItemChildType;
import java.util.ArrayList;
import java.util.List;
import x3.AbstractC2673a;
import z7.InterfaceC2782k;
import z7.InterfaceC2785n;
import z7.InterfaceC2787p;

/* JADX INFO: renamed from: Q5.e0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0562e0 extends A7.o implements InterfaceC2787p {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ List f6765m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ InterfaceC2785n f6766n;
    public final /* synthetic */ float o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ D.L f6767p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ InterfaceC2785n f6768q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ b6.g f6769r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final /* synthetic */ Integer f6770s;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0562e0(List list, InterfaceC2785n interfaceC2785n, float f10, D.L l, InterfaceC2785n interfaceC2785n2, b6.g gVar, Integer num) {
        super(4);
        this.f6765m = list;
        this.f6766n = interfaceC2785n;
        this.o = f10;
        this.f6767p = l;
        this.f6768q = interfaceC2785n2;
        this.f6769r = gVar;
        this.f6770s = num;
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
            KaraokeItem karaokeItem = (KaraokeItem) this.f6765m.get(iIntValue);
            c0761q.Q(484429346);
            List list = karaokeItem.f13111g;
            A7.m.c(list);
            KaraokeItemChildType karaokeItemChildType = KaraokeItemChildType.GROUP;
            c0761q.Q(15632523);
            InterfaceC2785n interfaceC2785n = this.f6766n;
            boolean zF = c0761q.f(interfaceC2785n) | c0761q.h(karaokeItem);
            Object objG = c0761q.G();
            if (zF || objG == C0753m.f8270a) {
                objG = new C0554c0(interfaceC2785n, karaokeItem, 0);
                c0761q.a0(objG);
            }
            c0761q.p(false);
            ArrayList arrayListV = com.bumptech.glide.c.V(list, karaokeItemChildType, false, true, (InterfaceC2782k) objG, iIntValue, c0761q, 0);
            g0.o oVarO = androidx.compose.foundation.layout.c.o(g0.l.f14684a, this.o);
            D.L l = this.f6767p;
            com.bumptech.glide.c.A(oVarO, null, arrayListV, null, androidx.compose.foundation.layout.a.c(AbstractC2673a.J(l, c0761q), 0.0f, AbstractC2673a.I(l, c0761q), AbstractC2673a.H(l, c0761q), 2), this.f6768q, this.f6769r, false, this.f6770s, c0761q, 0, 138);
            c0761q.p(false);
        }
        return m7.q.f19267a;
    }
}
