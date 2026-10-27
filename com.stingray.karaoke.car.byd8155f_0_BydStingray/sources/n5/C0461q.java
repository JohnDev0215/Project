package N5;

import com.stingray.karaoke.car.player.PlayerStatus;
import com.stingray.shared.logs.segment.ActionOwner;
import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2782k;

/* JADX INFO: renamed from: N5.q, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0461q extends s7.i implements InterfaceC2782k {
    public final /* synthetic */ Z l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ ActionOwner f4734m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0461q(Z z6, ActionOwner actionOwner, InterfaceC2189c interfaceC2189c) {
        super(1, interfaceC2189c);
        this.l = z6;
        this.f4734m = actionOwner;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) throws Throwable {
        ((C0461q) create((InterfaceC2189c) obj)).invokeSuspend(m7.q.f19267a);
        return EnumC0445a.l;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(InterfaceC2189c interfaceC2189c) {
        return new C0461q(this.l, this.f4734m, interfaceC2189c);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        AbstractC1386c.u(obj);
        Z z6 = this.l;
        if (((Boolean) z6.f4638Q.getValue()).booleanValue()) {
            z6.q();
        }
        ActionOwner actionOwner = this.f4734m;
        z6.w(0L, actionOwner);
        if (((Boolean) z6.f4638Q.getValue()).booleanValue() || z6.f4634M.getValue() != PlayerStatus.PLAYING) {
            z6.t(actionOwner);
        }
        return EnumC0445a.l;
    }
}
