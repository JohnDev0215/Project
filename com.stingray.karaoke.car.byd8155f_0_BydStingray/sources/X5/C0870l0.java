package X5;

import com.stingray.drivemodedetector.DriveMode;
import com.stingray.karaoke.car.player.PlayerStatus;
import h8.AbstractC1386c;
import java.io.Serializable;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2788q;

/* JADX INFO: renamed from: X5.l0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0870l0 extends s7.i implements InterfaceC2788q {
    public /* synthetic */ boolean l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public /* synthetic */ DriveMode f10286m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public /* synthetic */ boolean f10287n;
    public /* synthetic */ PlayerStatus o;

    @Override // z7.InterfaceC2788q
    public final Object e(Object obj, Object obj2, Object obj3, Object obj4, Serializable serializable) {
        boolean zBooleanValue = ((Boolean) obj).booleanValue();
        boolean zBooleanValue2 = ((Boolean) obj3).booleanValue();
        C0870l0 c0870l0 = new C0870l0(5, (InterfaceC2189c) serializable);
        c0870l0.l = zBooleanValue;
        c0870l0.f10286m = (DriveMode) obj2;
        c0870l0.f10287n = zBooleanValue2;
        c0870l0.o = (PlayerStatus) obj4;
        return c0870l0.invokeSuspend(m7.q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        AbstractC1386c.u(obj);
        boolean z6 = this.l;
        DriveMode driveMode = this.f10286m;
        boolean z10 = this.f10287n;
        PlayerStatus playerStatus = this.o;
        return Boolean.valueOf((z6 || driveMode == DriveMode.DRIVING || z10 || playerStatus.getForceShowControls()) && !playerStatus.getForceHideControls());
    }
}
