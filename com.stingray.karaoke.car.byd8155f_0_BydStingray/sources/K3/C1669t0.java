package k3;

import com.stingray.karaoke.car.player.PlayerService;

/* JADX INFO: renamed from: k3.t0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1669t0 implements InterfaceC1668t, e2.U {
    public final PlayerService l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final A0 f18117m;

    public C1669t0(PlayerService playerService, A0 a02) {
        this.l = playerService;
        this.f18117m = a02;
    }

    @Override // e2.U
    public final void E(e2.T t10) {
        if (t10.f14001a.a(4, 5, 14, 0)) {
            this.l.j(this.f18117m, false);
        }
    }

    @Override // k3.InterfaceC1668t
    public final void a() {
        PlayerService playerService = this.l;
        A0 a02 = this.f18117m;
        if (playerService.d(a02)) {
            playerService.k(a02);
        }
        playerService.j(a02, false);
    }

    @Override // k3.InterfaceC1668t
    public final void n() {
        this.l.j(this.f18117m, false);
    }

    @Override // k3.InterfaceC1668t
    public final void s() {
        this.l.j(this.f18117m, false);
    }
}
