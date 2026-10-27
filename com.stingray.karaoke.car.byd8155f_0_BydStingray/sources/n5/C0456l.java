package N5;

import com.stingray.shared.logs.segment.ActionOwner;
import com.stingray.shared.logs.segment.player.PlaybackSetting;
import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: N5.l, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0456l extends s7.i implements InterfaceC2785n {
    public final /* synthetic */ A7.v l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Z f4718m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0456l(A7.v vVar, Z z6, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.l = vVar;
        this.f4718m = z6;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new C0456l(this.l, this.f4718m, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) throws Throwable {
        Boolean bool = (Boolean) obj;
        bool.booleanValue();
        C0456l c0456l = (C0456l) create(bool, (InterfaceC2189c) obj2);
        m7.q qVar = m7.q.f19267a;
        c0456l.invokeSuspend(qVar);
        return qVar;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        AbstractC1386c.u(obj);
        A7.v vVar = this.l;
        if (!vVar.l) {
            this.f4718m.p(PlaybackSetting.FULL_SCREEN, ActionOwner.HOST);
        }
        vVar.l = false;
        return m7.q.f19267a;
    }
}
