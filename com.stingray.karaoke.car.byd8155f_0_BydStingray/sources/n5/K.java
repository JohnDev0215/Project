package N5;

import S8.InterfaceC0725w;
import com.stingray.shared.logs.segment.ActionOwner;
import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class K extends s7.i implements InterfaceC2785n {
    public final /* synthetic */ Z l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f4609m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ ActionOwner f4610n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public K(Z z6, int i7, ActionOwner actionOwner, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.l = z6;
        this.f4609m = i7;
        this.f4610n = actionOwner;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new K(this.l, this.f4609m, this.f4610n, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) throws Throwable {
        K k10 = (K) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2);
        m7.q qVar = m7.q.f19267a;
        k10.invokeSuspend(qVar);
        return qVar;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        AbstractC1386c.u(obj);
        Z z6 = this.l;
        D6.b bVarB = z6.b(false);
        D6.a aVarA = z6.a(null);
        D6.e eVar = z6.f4654e;
        eVar.getClass();
        ActionOwner actionOwner = this.f4610n;
        A7.m.f(actionOwner, "actionOwner");
        B5.z zVarA = D6.e.a(bVarB, aVarA, actionOwner);
        zVarA.k(Integer.valueOf(this.f4609m), "position_before");
        eVar.c("Karaoke Playback Seeked", zVarA, true);
        return m7.q.f19267a;
    }
}
