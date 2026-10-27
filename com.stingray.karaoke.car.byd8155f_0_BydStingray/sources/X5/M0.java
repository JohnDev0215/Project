package X5;

import S8.AbstractC0727y;
import com.stingray.karaoke.car.player.PlayerStatus;
import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class M0 extends s7.i implements InterfaceC2785n {
    public final /* synthetic */ V0 l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public M0(V0 v02, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.l = v02;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new M0(this.l, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) throws Throwable {
        M0 m02 = (M0) create((PlayerStatus) obj, (InterfaceC2189c) obj2);
        m7.q qVar = m7.q.f19267a;
        m02.invokeSuspend(qVar);
        return qVar;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        AbstractC1386c.u(obj);
        V0 v02 = this.l;
        if (v02.f10210g.getValue() == null) {
            AbstractC0727y.w(v02.d, null, null, new U0(v02, null), 3);
        }
        return m7.q.f19267a;
    }
}
