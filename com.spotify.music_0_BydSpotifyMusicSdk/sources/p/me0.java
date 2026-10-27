package p;

import com.spotify.automotive.pathfinder.type.EpisodePlaybackState;

/* JADX INFO: loaded from: classes.dex */
public final class me0 {
    public final int a;
    public final EpisodePlaybackState b;

    public me0(int i, EpisodePlaybackState episodePlaybackState) {
        this.a = i;
        this.b = episodePlaybackState;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof me0)) {
            return false;
        }
        me0 me0Var = (me0) obj;
        return this.a == me0Var.a && this.b == me0Var.b;
    }

    public final int hashCode() {
        return this.b.hashCode() + (Integer.hashCode(this.a) * 31);
    }

    public final String toString() {
        return "PlayedState(playPositionMilliseconds=" + this.a + ", state=" + this.b + ')';
    }
}
