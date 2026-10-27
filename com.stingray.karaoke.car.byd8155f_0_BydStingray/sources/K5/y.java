package K5;

import S8.InterfaceC0725w;
import V8.f0;
import com.stingray.kca.model.KaraokeItem;
import h8.AbstractC1386c;
import java.util.List;
import n7.AbstractC1998m;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class y extends s7.i implements InterfaceC2785n {
    public final /* synthetic */ F l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public y(F f10, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.l = f10;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new y(this.l, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((y) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        KaraokeItem karaokeItem;
        EnumC2303a enumC2303a = EnumC2303a.l;
        AbstractC1386c.u(obj);
        F f10 = this.l;
        KaraokeItem karaokeItemC = r6.f.c(f10.f3617a.c(), AbstractC1998m.A0(J5.c.f3089i), Boolean.valueOf(!((Boolean) ((f0) f10.d.f18294e.l).getValue()).booleanValue()));
        f10.f3618b.a(karaokeItemC);
        List list = karaokeItemC.f13111g;
        List list2 = (list == null || (karaokeItem = (KaraokeItem) list.get(0)) == null) ? null : karaokeItem.f13111g;
        return list2 == null ? n7.u.l : list2;
    }
}
