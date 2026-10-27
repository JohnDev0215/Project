package X5;

import S8.InterfaceC0725w;
import com.stingray.karaoke.car.player.LeadVocalState;
import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: X5.i, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0863i extends s7.i implements InterfaceC2785n {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ LeadVocalState f10266m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ t6.h f10267n;
    public final /* synthetic */ T.X o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ T.X f10268p;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0863i(int i7, LeadVocalState leadVocalState, t6.h hVar, T.X x8, T.X x9, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.l = i7;
        this.f10266m = leadVocalState;
        this.f10267n = hVar;
        this.o = x8;
        this.f10268p = x9;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new C0863i(this.l, this.f10266m, this.f10267n, this.o, this.f10268p, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) throws Throwable {
        C0863i c0863i = (C0863i) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2);
        m7.q qVar = m7.q.f19267a;
        c0863i.invokeSuspend(qVar);
        return qVar;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        AbstractC1386c.u(obj);
        l9.l.z(this.f10266m, this.f10267n, this.o, this.f10268p, this.l);
        return m7.q.f19267a;
    }
}
