package m3;

import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageView;
import androidx.media3.ui.PlayerView;
import androidx.media3.ui.SubtitleView;
import e2.U;
import e2.V;
import e2.W;
import e2.c0;
import e2.f0;
import e2.n0;
import e2.q0;
import g2.C1290c;
import h2.AbstractC1343A;
import l3.C1764u;

/* JADX INFO: loaded from: classes.dex */
public final class z implements U, View.OnClickListener, r, InterfaceC1809i {
    public final c0 l = new c0();

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public Object f19180m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ PlayerView f19181n;

    public z(PlayerView playerView) {
        this.f19181n = playerView;
    }

    @Override // e2.U
    public final void J(n0 n0Var) {
        PlayerView playerView = this.f19181n;
        W w10 = playerView.f12024D;
        w10.getClass();
        f0 f0VarT0 = w10.R0(17) ? w10.t0() : f0.f14097a;
        if (f0VarT0.p()) {
            this.f19180m = null;
        } else {
            boolean zR0 = w10.R0(30);
            c0 c0Var = this.l;
            if (!zR0 || w10.J().f14226a.isEmpty()) {
                Object obj = this.f19180m;
                if (obj != null) {
                    int iB = f0VarT0.b(obj);
                    if (iB != -1) {
                        if (w10.b0() == f0VarT0.f(iB, c0Var, false).c) {
                            return;
                        }
                    }
                    this.f19180m = null;
                }
            } else {
                this.f19180m = f0VarT0.f(w10.Q(), c0Var, true).f14060b;
            }
        }
        playerView.n(false);
    }

    @Override // e2.U
    public final void K(int i7, int i10) {
        if (AbstractC1343A.f15195a == 34) {
            PlayerView playerView = this.f19181n;
            if ((playerView.o instanceof SurfaceView) && playerView.f12037Q) {
                C1764u c1764u = playerView.f12041q;
                c1764u.getClass();
                playerView.f12050z.post(new T1.k(c1764u, (SurfaceView) playerView.o, new A2.l(playerView, 28), 13));
            }
        }
    }

    @Override // e2.U
    public final void M(q0 q0Var) {
        PlayerView playerView;
        W w10;
        if (q0Var.equals(q0.d) || (w10 = (playerView = this.f19181n).f12024D) == null || w10.e() == 1) {
            return;
        }
        playerView.j();
    }

    @Override // e2.U
    public final void R(C1290c c1290c) {
        SubtitleView subtitleView = this.f19181n.f12044t;
        if (subtitleView != null) {
            subtitleView.setCues(c1290c.f14801a);
        }
    }

    @Override // e2.U
    public final void l() {
        PlayerView playerView = this.f19181n;
        View view = playerView.f12039n;
        if (view != null) {
            view.setVisibility(4);
            if (!playerView.b()) {
                playerView.c();
                return;
            }
            ImageView imageView = playerView.f12042r;
            if (imageView != null) {
                imageView.setVisibility(4);
            }
        }
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        int i7 = PlayerView.f12021R;
        this.f19181n.i();
    }

    @Override // e2.U
    public final void q(int i7, boolean z6) {
        int i10 = PlayerView.f12021R;
        PlayerView playerView = this.f19181n;
        playerView.k();
        if (!playerView.d() || !playerView.f12035O) {
            playerView.e(false);
            return;
        }
        s sVar = playerView.f12047w;
        if (sVar != null) {
            sVar.f();
        }
    }

    @Override // e2.U
    public final void t(int i7, V v10, V v11) {
        s sVar;
        int i10 = PlayerView.f12021R;
        PlayerView playerView = this.f19181n;
        if (playerView.d() && playerView.f12035O && (sVar = playerView.f12047w) != null) {
            sVar.f();
        }
    }

    @Override // e2.U
    public final void x(int i7) {
        int i10 = PlayerView.f12021R;
        PlayerView playerView = this.f19181n;
        playerView.k();
        playerView.m();
        if (!playerView.d() || !playerView.f12035O) {
            playerView.e(false);
            return;
        }
        s sVar = playerView.f12047w;
        if (sVar != null) {
            sVar.f();
        }
    }
}
