package p;

import com.google.common.base.Optional;
import com.spotify.common.uri.SpotifyUri$Kind;
import com.spotify.player.model.PlayOrigin;
import com.spotify.player.model.command.options.PlayerOptionOverrides;
import com.spotify.player.model.command.options.PreparePlayOptions;
import com.spotify.player.model.command.options.SkipToTrack;

/* JADX INFO: loaded from: classes.dex */
public abstract class z1 implements st1 {
    public final zh0 b;
    public final m c;
    public final vx1 d;
    public final PlayOrigin e;
    public final u6 f;
    public final io.reactivex.rxjava3.disposables.a g;

    public z1(zh0 zh0Var, m mVar, vx1 vx1Var, PlayOrigin playOrigin, u6 u6Var) {
        v03.j(zh0Var, "playerSubscriptions");
        v03.j(mVar, "properties");
        v03.j(vx1Var, "productStateController");
        v03.j(playOrigin, "defaultOrigin");
        v03.j(u6Var, "clock");
        this.b = zh0Var;
        this.c = mVar;
        this.d = vx1Var;
        this.e = playOrigin;
        this.f = u6Var;
        this.g = new io.reactivex.rxjava3.disposables.a();
    }

    public final void s(Integer num, String str, String str2, String str3) {
        PreparePlayOptions.Builder builderA;
        Optional optionalB = Optional.b(vr2.g(str2));
        if (optionalB.d() && (((if2) optionalB.c()).e == SpotifyUri$Kind.SHOW || ((if2) optionalB.c()).e == SpotifyUri$Kind.EPISODE)) {
            PreparePlayOptions.Builder builderA2 = PreparePlayOptions.c().a(true);
            PlayerOptionOverrides.Builder builderA3 = PlayerOptionOverrides.a();
            builderA3.d(Boolean.FALSE);
            builderA = builderA2.h(builderA3.a());
            v03.i(builderA, "{\n            PreparePla…false).build())\n        }");
        } else {
            builderA = PreparePlayOptions.c().a(true);
            v03.i(builderA, "{\n            PreparePla…Something(true)\n        }");
        }
        if (str2 != null) {
            SkipToTrack.Builder builderA4 = SkipToTrack.a();
            builderA4.f(str2);
            if (num != null) {
                builderA4.b(Long.valueOf((long) Math.floor(((double) num.intValue()) / ((double) 150))));
            }
            builderA.l(builderA4.a());
        }
        b(str, builderA.c(), str3);
    }

    public final void u(ju1 ju1Var, int i) {
        v03.j(ju1Var, "playerState");
        this.f.getClass();
        long jB = ju1Var.b(System.currentTimeMillis()) + ((long) i);
        Object objG = ju1Var.a.g().g(0L);
        v03.i(objG, "nativeState.duration().or(0)");
        o((int) Math.max(0L, Math.min(jB, ((Number) objG).longValue())));
    }
}
