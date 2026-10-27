package N5;

import com.stingray.karaoke.car.player.PlayerStatus;
import q7.InterfaceC2189c;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: N5.w, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0466w extends s7.i implements InterfaceC2785n {
    public /* synthetic */ Object l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ PlayerStatus f4776m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0466w(PlayerStatus playerStatus, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f4776m = playerStatus;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        C0466w c0466w = new C0466w(this.f4776m, interfaceC2189c);
        c0466w.l = obj;
        return c0466w;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((C0466w) create((PlayerStatus) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x002a  */
    @Override // s7.AbstractC2408a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r2) throws java.lang.Throwable {
        /*
            r1 = this;
            r7.a r0 = r7.EnumC2303a.l
            h8.AbstractC1386c.u(r2)
            java.lang.Object r2 = r1.l
            com.stingray.karaoke.car.player.PlayerStatus r2 = (com.stingray.karaoke.car.player.PlayerStatus) r2
            com.stingray.karaoke.car.player.PlayerStatus r0 = com.stingray.karaoke.car.player.PlayerStatus.ERROR
            if (r2 == r0) goto L2a
            boolean r0 = r2.getIsActive()
            com.stingray.karaoke.car.player.PlayerStatus r1 = r1.f4776m
            if (r0 == 0) goto L1b
            boolean r0 = r1.getIsActive()
            if (r0 != 0) goto L2a
        L1b:
            boolean r2 = r2.getIsActive()
            if (r2 != 0) goto L28
            boolean r1 = r1.getIsActive()
            if (r1 != 0) goto L28
            goto L2a
        L28:
            r1 = 0
            goto L2b
        L2a:
            r1 = 1
        L2b:
            java.lang.Boolean r1 = java.lang.Boolean.valueOf(r1)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: N5.C0466w.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
