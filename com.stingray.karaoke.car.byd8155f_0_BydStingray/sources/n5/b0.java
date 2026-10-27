package N5;

import com.stingray.karaoke.car.player.PlayerActionType;
import com.stingray.karaoke.car.player.PlayerService;
import com.stingray.shared.logs.segment.ActionOwner;
import e2.AbstractC1197e;
import k3.A0;
import k3.C1672v;
import k3.C1679y0;
import n2.C1923x;

/* JADX INFO: loaded from: classes.dex */
public final class b0 extends AbstractC1197e {
    public final /* synthetic */ PlayerService c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public b0(PlayerService playerService, C1923x c1923x) {
        super(c1923x);
        this.c = playerService;
    }

    @Override // e2.AbstractC1197e, e2.W
    public final void C0() {
        A0 a02;
        C1679y0 c1679y0A;
        C1679y0 c1679y0A2;
        PlayerService playerService = this.c;
        A0 a03 = playerService.f13019t;
        if (A7.m.a((a03 == null || (c1679y0A2 = a03.a()) == null) ? null : c1679y0A2.b(), playerService.getApplicationContext().getPackageName()) && ((a02 = playerService.f13019t) == null || (c1679y0A = a02.a()) == null || c1679y0A.a().isEmpty())) {
            super.C0();
            return;
        }
        Q q4 = PlayerService.f13011x;
        if (q4 != null) {
            q4.l.E(true, ActionOwner.HOST);
        }
    }

    @Override // e2.AbstractC1197e, e2.W
    public final void M0() {
        A0 a02;
        C1679y0 c1679y0A;
        C1679y0 c1679y0A2;
        PlayerService playerService = this.c;
        A0 a03 = playerService.f13019t;
        if (A7.m.a((a03 == null || (c1679y0A2 = a03.a()) == null) ? null : c1679y0A2.b(), playerService.getApplicationContext().getPackageName()) && ((a02 = playerService.f13019t) == null || (c1679y0A = a02.a()) == null || c1679y0A.a().isEmpty())) {
            super.M0();
            return;
        }
        Q q4 = PlayerService.f13011x;
        if (q4 != null) {
            ActionOwner actionOwner = ActionOwner.HOST;
            Z z6 = q4.l;
            z6.getClass();
            A7.m.f(actionOwner, "actionOwner");
            z6.d(new C0451g(PlayerActionType.SkipPrevious, new C0463t(z6, actionOwner, null, false)));
        }
    }

    @Override // e2.AbstractC1197e, e2.W
    public final void b() {
        A0 a02;
        C1679y0 c1679y0A;
        C1679y0 c1679y0A2;
        PlayerService playerService = this.c;
        A0 a03 = playerService.f13019t;
        if (A7.m.a((a03 == null || (c1679y0A2 = a03.a()) == null) ? null : c1679y0A2.b(), playerService.getApplicationContext().getPackageName()) && ((a02 = playerService.f13019t) == null || (c1679y0A = a02.a()) == null || c1679y0A.a().isEmpty())) {
            super.b();
            return;
        }
        C1923x c1923x = PlayerService.f13008u;
        Q q4 = PlayerService.f13011x;
        if (q4 != null) {
            q4.l.s(ActionOwner.HOST);
        }
    }

    @Override // e2.AbstractC1197e, e2.W
    public final void g() {
        A0 a02;
        C1679y0 c1679y0A;
        C1679y0 c1679y0A2;
        PlayerService playerService = this.c;
        A0 a03 = playerService.f13019t;
        if (A7.m.a((a03 == null || (c1679y0A2 = a03.a()) == null) ? null : c1679y0A2.b(), playerService.getApplicationContext().getPackageName()) && ((a02 = playerService.f13019t) == null || (c1679y0A = a02.a()) == null || c1679y0A.a().isEmpty())) {
            super.g();
            return;
        }
        Q q4 = PlayerService.f13011x;
        if (q4 != null) {
            q4.l.t(ActionOwner.HOST);
        }
    }

    @Override // e2.AbstractC1197e, e2.W
    public final void h(int i7) {
        A0 a02;
        C1679y0 c1679y0A;
        C1679y0 c1679y0A2;
        PlayerService playerService = this.c;
        A0 a03 = playerService.f13019t;
        if (A7.m.a((a03 == null || (c1679y0A2 = a03.a()) == null) ? null : c1679y0A2.b(), playerService.getApplicationContext().getPackageName()) && ((a02 = playerService.f13019t) == null || (c1679y0A = a02.a()) == null || c1679y0A.a().isEmpty())) {
            super.h(i7);
            return;
        }
        Q q4 = PlayerService.f13011x;
        if (q4 != null) {
            q4.l.D(i7, ActionOwner.HOST);
        }
    }

    @Override // e2.AbstractC1197e, e2.W
    public final void k(long j3) {
        A0 a02;
        C1679y0 c1679y0A;
        C1679y0 c1679y0A2;
        PlayerService playerService = this.c;
        A0 a03 = playerService.f13019t;
        if (A7.m.a((a03 == null || (c1679y0A2 = a03.a()) == null) ? null : c1679y0A2.b(), playerService.getApplicationContext().getPackageName()) && ((a02 = playerService.f13019t) == null || (c1679y0A = a02.a()) == null || c1679y0A.a().isEmpty())) {
            super.k(j3);
            return;
        }
        Q q4 = PlayerService.f13011x;
        if (q4 != null) {
            q4.l.w(j3, ActionOwner.HOST);
        }
    }

    @Override // e2.AbstractC1197e, e2.W
    public final void stop() {
        A0 a02;
        C1679y0 c1679y0A;
        C1679y0 c1679y0A2;
        PlayerService playerService = this.c;
        A0 a03 = playerService.f13019t;
        if (A7.m.a((a03 == null || (c1679y0A2 = a03.a()) == null) ? null : c1679y0A2.b(), playerService.getApplicationContext().getPackageName()) && ((a02 = playerService.f13019t) == null || (c1679y0A = a02.a()) == null || c1679y0A.a().isEmpty())) {
            super.stop();
            return;
        }
        Q q4 = PlayerService.f13011x;
        if (q4 != null) {
            Z z6 = q4.l;
            z6.getClass();
            z6.d(new C0451g(PlayerActionType.Stop, new C0464u(z6, null)));
        }
    }

    @Override // e2.AbstractC1197e, e2.W
    public final void t(int i7) {
        A0 a02;
        C1679y0 c1679y0A;
        C1679y0 c1679y0A2;
        PlayerService playerService = this.c;
        A0 a03 = playerService.f13019t;
        if (A7.m.a((a03 == null || (c1679y0A2 = a03.a()) == null) ? null : c1679y0A2.b(), playerService.getApplicationContext().getPackageName()) && ((a02 = playerService.f13019t) == null || (c1679y0A = a02.a()) == null || c1679y0A.a().isEmpty())) {
            super.t(i7);
            return;
        }
        Q q4 = PlayerService.f13011x;
        if (q4 != null) {
            Z z6 = q4.l;
            q0 q0Var = z6.c;
            C1672v c1672v = z6.f4667p;
            if (c1672v == null) {
                A7.m.j("mediaController");
                throw null;
            }
            String str = c1672v.t0().m(i7, c1672v.f18135a, 0L).c.f13878a;
            A7.m.e(str, "mediaId");
            q0Var.l(str, true, ActionOwner.HOST);
        }
    }
}
