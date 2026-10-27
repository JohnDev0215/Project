package N5;

import com.stingray.karaoke.car.player.PlayerStatus;
import h8.AbstractC1386c;
import java.util.Set;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class X extends s7.i implements InterfaceC2785n {
    public /* synthetic */ Object l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Set f4620m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public X(Set set, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f4620m = set;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        X x8 = new X(this.f4620m, interfaceC2189c);
        x8.l = obj;
        return x8;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((X) create((PlayerStatus) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        AbstractC1386c.u(obj);
        PlayerStatus playerStatus = (PlayerStatus) this.l;
        return Boolean.valueOf(playerStatus == PlayerStatus.ERROR || this.f4620m.contains(playerStatus));
    }
}
