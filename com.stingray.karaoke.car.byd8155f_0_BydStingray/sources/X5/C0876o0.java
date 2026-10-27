package X5;

import com.stingray.karaoke.car.player.PlayerStatus;
import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2786o;

/* JADX INFO: renamed from: X5.o0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0876o0 extends s7.i implements InterfaceC2786o {
    public /* synthetic */ PlayerStatus l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public /* synthetic */ boolean f10369m;

    @Override // z7.InterfaceC2786o
    public final Object g(Object obj, Object obj2, Object obj3) {
        boolean zBooleanValue = ((Boolean) obj2).booleanValue();
        C0876o0 c0876o0 = new C0876o0(3, (InterfaceC2189c) obj3);
        c0876o0.l = (PlayerStatus) obj;
        c0876o0.f10369m = zBooleanValue;
        return c0876o0.invokeSuspend(m7.q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        AbstractC1386c.u(obj);
        return Boolean.valueOf(this.f10369m && this.l != PlayerStatus.STOPPED);
    }
}
