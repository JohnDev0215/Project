package N5;

import S8.AbstractC0727y;
import com.stingray.karaoke.car.player.PlayerActionType;
import com.stingray.karaoke.car.player.PlayerStatus;
import com.stingray.shared.logs.segment.ActionOwner;
import h8.AbstractC1386c;
import java.util.Set;
import k3.C1672v;
import n7.AbstractC1996k;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2782k;

/* JADX INFO: renamed from: N5.o, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0459o extends s7.i implements InterfaceC2782k {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Z f4726m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ ActionOwner f4727n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0459o(Z z6, ActionOwner actionOwner, InterfaceC2189c interfaceC2189c) {
        super(1, interfaceC2189c);
        this.f4726m = z6;
        this.f4727n = actionOwner;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        return ((C0459o) create((InterfaceC2189c) obj)).invokeSuspend(m7.q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(InterfaceC2189c interfaceC2189c) {
        return new C0459o(this.f4726m, this.f4727n, interfaceC2189c);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.l;
        if (i7 == 0) {
            AbstractC1386c.u(obj);
            Z z6 = this.f4726m;
            C1672v c1672v = z6.f4667p;
            if (c1672v == null) {
                A7.m.j("mediaController");
                throw null;
            }
            c1672v.b();
            Z8.e eVar = S8.E.f8084a;
            AbstractC0727y.w(AbstractC0727y.a(X8.m.f10573a), null, null, new I(z6, this.f4727n, null), 3);
            PlayerActionType playerActionType = PlayerActionType.Pause;
            Set setC0 = AbstractC1996k.c0(new PlayerStatus[]{PlayerStatus.PAUSED, PlayerStatus.READY});
            this.l = 1;
            obj = Z.H(this.f4726m, playerActionType, setC0, 0L, this, 12);
            if (obj == enumC2303a) {
                return enumC2303a;
            }
        } else {
            if (i7 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            AbstractC1386c.u(obj);
        }
        return obj;
    }
}
