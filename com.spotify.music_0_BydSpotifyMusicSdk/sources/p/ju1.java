package p;

import com.google.common.base.Optional;
import com.spotify.common.uri.SpotifyUri$Kind;
import com.spotify.player.model.ContextTrack;
import com.spotify.player.model.PlayerState;

/* JADX INFO: loaded from: classes.dex */
public final class ju1 {
    public final PlayerState a;

    public ju1(PlayerState playerState) {
        v03.j(playerState, "nativeState");
        this.a = playerState;
    }

    public final boolean a() {
        return this.a.w().v().isEmpty() && !e();
    }

    public final long b(long j) {
        Optional optionalE;
        PlayerState playerState = this.a;
        if (playerState.t().d()) {
            optionalE = Optional.e(Long.valueOf(((Long) playerState.t().c()).longValue() + ((long) (((Double) playerState.s().g(Double.valueOf(0.0d))).doubleValue() * (j - playerState.A())))));
        } else {
            optionalE = Optional.a();
        }
        Object objG = optionalE.g(0L);
        v03.i(objG, "nativeState.position(now).or(0)");
        return ((Number) objG).longValue();
    }

    public final boolean c() {
        ContextTrack contextTrackF = f();
        if (contextTrackF != null) {
            return Boolean.parseBoolean((String) contextTrackF.b().get("is_advertisement"));
        }
        return false;
    }

    public final boolean d() {
        PlayerState playerState = this.a;
        return !playerState.B().d() && playerState.c().containsKey("loading.failed");
    }

    public final boolean e() {
        ContextTrack contextTrackF = f();
        if2 if2VarG = vr2.g(contextTrackF != null ? contextTrackF.f() : null);
        SpotifyUri$Kind spotifyUri$Kind = if2VarG != null ? if2VarG.e : null;
        if (spotifyUri$Kind == null) {
            spotifyUri$Kind = SpotifyUri$Kind.UNKNOWN;
        }
        return spotifyUri$Kind == SpotifyUri$Kind.EPISODE;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ju1) && v03.b(this.a, ((ju1) obj).a);
    }

    public final ContextTrack f() {
        return (ContextTrack) this.a.B().h();
    }

    public final Optional g() {
        ContextTrack contextTrackF = f();
        Optional optionalB = Optional.b(vr2.g(contextTrackF != null ? contextTrackF.f() : null));
        v03.i(optionalB, "fromNullable(getSpotifyUri(track()?.uri()))");
        return optionalB;
    }

    public final int hashCode() {
        return this.a.hashCode();
    }

    public final String toString() {
        return "PlayerState(nativeState=" + this.a + ')';
    }
}
