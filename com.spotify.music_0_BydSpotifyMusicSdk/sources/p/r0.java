package p;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.media.session.PlaybackStateCompat;
import android.text.TextUtils;
import com.google.common.base.Optional;
import com.spotify.music.R;
import com.spotify.player.model.ContextTrack;
import com.spotify.player.model.PlayerState;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class r0 {
    public final ju1 a;
    public final u6 b;
    public final m c;
    public final Optional d;
    public final if2 e;
    public final Resources f;
    public final boolean g;
    public final boolean h;
    public final boolean i;
    public final boolean j;
    public final boolean k;
    public final boolean l;
    public final boolean m;
    public final boolean n;
    public final boolean o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final boolean f175p;
    public final boolean q;
    public final boolean r;
    public final boolean s;
    public final long t;
    public final float u;
    public final int v;
    public final long w;
    public final Bundle x;

    public r0(Resources resources, ju1 ju1Var, u6 u6Var, m mVar, boolean z, boolean z2, int i) {
        this.a = ju1Var;
        this.b = u6Var;
        this.c = mVar;
        this.f = resources;
        ContextTrack contextTrackF = ju1Var.f();
        boolean z3 = false;
        this.h = contextTrackF != null ? Boolean.parseBoolean((String) contextTrackF.b().get("is_audiobook_chapter")) : false;
        this.g = z;
        this.s = z2;
        this.v = i;
        PlayerState playerState = ju1Var.a;
        this.u = (float) ((Number) playerState.s().g(Double.valueOf(0.0d))).doubleValue();
        this.i = playerState.k();
        this.j = playerState.j();
        Optional optionalB = Optional.b(vr2.g(playerState.e()));
        v03.i(optionalB, "fromNullable(getSpotifyU…ativeState.contextUri()))");
        this.k = optionalB.d();
        this.n = ju1Var.g().d();
        this.l = ju1Var.e();
        this.m = v03.b(playerState.c().get("format_list_type"), "car-mix");
        this.o = ju1Var.c();
        ContextTrack contextTrackF2 = ju1Var.f();
        this.d = contextTrackF2 != null ? Optional.e(Integer.valueOf(f60.F(contextTrackF2).hashCode())) : Optional.a();
        this.f175p = playerState.n().e();
        this.q = playerState.n().c();
        this.r = playerState.n().d();
        this.e = (if2) ju1Var.g().h();
        this.t = vr2.d(Optional.e(ju1Var));
        Object objG = playerState.t().g(0L);
        v03.i(objG, "nativeState.positionAsOfTimestamp().or(0)");
        this.w = ((Number) objG).longValue();
        Optional optionalE = Optional.e(ju1Var);
        Bundle bundle = new Bundle();
        ju1 ju1Var2 = (ju1) optionalE.c();
        bundle.putBoolean("com.spotify.music.extra.SET_REPEAT_ONE_AVAILABLE", ju1Var2.a.w().u().isEmpty() && !ju1Var2.e());
        bundle.putBoolean("com.spotify.music.extra.SET_REPEAT_ALL_AVAILABLE", ju1Var2.a.w().t().isEmpty() && !ju1Var2.e());
        if (!ju1Var2.a() && !ju1Var2.e()) {
            z3 = true;
        }
        bundle.putBoolean("com.spotify.music.extra.SHUFFLE_FORCE_ENABLED", z3);
        this.x = bundle;
    }

    public final void a(android.support.v4.media.session.f fVar) {
        String string = this.f.getString(R.string.seek_backward_action_name);
        if (TextUtils.isEmpty("seek-backward")) {
            throw new IllegalArgumentException("You must specify an action to build a CustomAction");
        }
        if (TextUtils.isEmpty(string)) {
            throw new IllegalArgumentException("You must specify a name to build a CustomAction");
        }
        fVar.a(new PlaybackStateCompat.CustomAction("seek-backward", string, R.drawable.ic_seek_back_15, null));
    }

    public final void b(android.support.v4.media.session.f fVar) {
        String string = this.f.getString(R.string.seek_forward_action_name);
        if (TextUtils.isEmpty("seek-forward")) {
            throw new IllegalArgumentException("You must specify an action to build a CustomAction");
        }
        if (TextUtils.isEmpty(string)) {
            throw new IllegalArgumentException("You must specify a name to build a CustomAction");
        }
        fVar.a(new PlaybackStateCompat.CustomAction("seek-forward", string, R.drawable.ic_seek_forward_15, null));
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof r0)) {
            return false;
        }
        r0 r0Var = (r0) obj;
        return this.g == r0Var.g && this.i == r0Var.i && this.j == r0Var.j && this.k == r0Var.k && this.l == r0Var.l && this.m == r0Var.m && this.n == r0Var.n && this.o == r0Var.o && this.f175p == r0Var.f175p && this.q == r0Var.q && this.r == r0Var.r && this.s == r0Var.s && this.t == r0Var.t && Float.compare(r0Var.u, this.u) == 0 && this.v == r0Var.v && this.d.equals(r0Var.d) && f60.p(this.e, r0Var.e) && this.w == r0Var.w;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Boolean.valueOf(this.g), Boolean.valueOf(this.i), Boolean.valueOf(this.j), Boolean.valueOf(this.k), Boolean.valueOf(this.l), Boolean.valueOf(this.m), Boolean.valueOf(this.n), Boolean.valueOf(this.o), Boolean.valueOf(this.f175p), Boolean.valueOf(this.q), Boolean.valueOf(this.r), Boolean.valueOf(this.s), Long.valueOf(this.t), Float.valueOf(this.u), Integer.valueOf(this.v), this.d, this.e, Long.valueOf(this.w)});
    }
}
