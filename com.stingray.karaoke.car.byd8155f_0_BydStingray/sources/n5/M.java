package N5;

import S8.InterfaceC0725w;
import com.stingray.shared.logs.segment.ActionOwner;
import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class M extends s7.i implements InterfaceC2785n {
    public final /* synthetic */ Z l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public M(Z z6, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.l = z6;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new M(this.l, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) throws Throwable {
        M m10 = (M) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2);
        m7.q qVar = m7.q.f19267a;
        m10.invokeSuspend(qVar);
        return qVar;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        AbstractC1386c.u(obj);
        Z z6 = this.l;
        D6.b bVarB = z6.b(false);
        ActionOwner actionOwner = z6.o;
        D6.e eVar = z6.f4654e;
        eVar.getClass();
        A7.m.f(actionOwner, "actionOwner");
        if (!eVar.f1212e) {
            eVar.c("Karaoke Playback Started", D6.e.b(eVar, bVarB, null, actionOwner, 2), true);
            eVar.f1212e = true;
            eVar.f1213f = false;
        }
        return m7.q.f19267a;
    }
}
