package N5;

import S8.InterfaceC0725w;
import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class G extends s7.i implements InterfaceC2785n {
    public final /* synthetic */ Z l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public G(Z z6, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.l = z6;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new G(this.l, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) throws Throwable {
        G g7 = (G) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2);
        m7.q qVar = m7.q.f19267a;
        g7.invokeSuspend(qVar);
        return qVar;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        AbstractC1386c.u(obj);
        Z z6 = this.l;
        D6.b bVarB = z6.b(false);
        D6.e eVar = z6.f4654e;
        eVar.getClass();
        eVar.c("Karaoke Playback Completed", D6.e.b(eVar, bVarB, null, null, 6), eVar.f1212e);
        eVar.f1212e = false;
        return m7.q.f19267a;
    }
}
