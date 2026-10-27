package N5;

import S8.AbstractC0727y;
import com.stingray.karaoke.car.player.PlayerStatus;
import com.stingray.shared.logs.segment.ActionOwner;
import com.stingray.shared.logs.segment.player.ContentCompletedReason;
import k3.C1672v;

/* JADX INFO: loaded from: classes.dex */
public final class S implements e2.U {
    public final /* synthetic */ Z l;

    public S(Z z6) {
        this.l = z6;
    }

    @Override // e2.U
    public final void L(int i7, e2.F f10) {
        Z z6 = this.l;
        z6.getClass();
        A7.m.f("onMediaItemTransition " + i7 + " " + (f10 != null ? f10.f13878a : null), "text");
        z6.f4674w = i7 == 1;
        String str = f10 != null ? f10.f13878a : null;
        if (str != null) {
            if (i7 != 2) {
                q0 q0Var = z6.c;
                if (i7 != 3) {
                    ActionOwner actionOwner = ActionOwner.HOST;
                    z6.o = actionOwner;
                    z6.m(z6.B, actionOwner);
                    q0Var.l(str, true, z6.o);
                } else {
                    if (z6.l()) {
                        z6.A(z6.f4676y, ((Number) ((V8.f0) q0Var.f4750t.l).getValue()).intValue());
                        return;
                    }
                    if (z6.f4634M.getValue() == PlayerStatus.STOPPED) {
                        z6.C(PlayerStatus.PAUSED);
                    }
                    if (!z6.f4673v) {
                        z6.A(0L, ((Number) ((V8.f0) q0Var.f4750t.l).getValue()).intValue());
                    }
                    z6.f4675x = true;
                }
            } else {
                z6.f4675x = true;
            }
        }
        z6.z();
        AbstractC0727y.w(z6.f4626E, null, null, new V(z6, null), 3);
    }

    @Override // e2.U
    public final void N(e2.f0 f0Var, int i7) {
        A7.m.f(f0Var, "timeline");
        Z z6 = this.l;
        z6.getClass();
        A7.m.f("onTimelineChanged " + i7, "text");
        if (z6.f4673v) {
            if (!z6.f4674w) {
                z6.B(((Number) ((V8.f0) z6.c.f4750t.l).getValue()).intValue());
            }
            z6.f4673v = false;
        }
        AbstractC0727y.w(z6.f4626E, null, null, new V(z6, null), 3);
    }

    @Override // e2.U
    public final void T(boolean z6) {
        Z z10 = this.l;
        z10.getClass();
        A7.m.f("onIsPlayingChanged " + z6, "text");
        C1672v c1672v = z10.f4667p;
        if (c1672v == null) {
            A7.m.j("mediaController");
            throw null;
        }
        if (c1672v.e() == 3) {
            if (z10.f4675x && z6 && z10.f4634M.getValue() == PlayerStatus.READY) {
                f0 f0Var = (f0) z10.f4636O.getValue();
                z10.n(f0Var != null ? f0Var.f4691a : null, z10.o);
            }
            z10.C(z6 ? PlayerStatus.PLAYING : PlayerStatus.PAUSED);
        }
        z10.z();
    }

    @Override // e2.U
    public final void f(e2.O o) {
        A7.m.f(o, "error");
        Z z6 = this.l;
        z6.getClass();
        if (o.l == 2004) {
            return;
        }
        z6.r(o);
    }

    @Override // e2.U
    public final void q(int i7, boolean z6) {
        Z z10 = this.l;
        z10.getClass();
        A7.m.f("onPlayWhenReadyChanged playWhenReady:" + z6 + " reason:" + i7, "text");
        if (z6 || i7 != 5) {
            return;
        }
        if (((Boolean) z10.f4638Q.getValue()).booleanValue()) {
            Boolean bool = Boolean.TRUE;
            V8.f0 f0Var = z10.f4646Y;
            f0Var.getClass();
            f0Var.m(null, bool);
        }
        z10.m(ContentCompletedReason.COMPLETED, z10.o);
    }

    @Override // e2.U
    public final void x(int i7) {
        Z z6 = this.l;
        z6.getClass();
        A7.m.f("onPlaybackStateChanged " + i7, "text");
        if (i7 == 2) {
            z6.C(PlayerStatus.BUFFERING);
        } else if (i7 == 3) {
            z6.C(PlayerStatus.READY);
        } else if (i7 == 4 && !z6.c.g() && !z6.l()) {
            z6.m(ContentCompletedReason.COMPLETED, z6.o);
            z6.o();
            z6.C(PlayerStatus.STOPPED);
        }
        z6.z();
    }
}
