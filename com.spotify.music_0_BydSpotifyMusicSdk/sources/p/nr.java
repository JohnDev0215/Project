package p;

import com.spotify.automotive.pathfinder.type.EpisodePlaybackState;

/* JADX INFO: loaded from: classes.dex */
public final class nr {
    public final int a;
    public final EpisodePlaybackState b;

    public nr(int i, EpisodePlaybackState episodePlaybackState) {
        this.a = i;
        this.b = episodePlaybackState;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof nr)) {
            return false;
        }
        nr nrVar = (nr) obj;
        return this.a == nrVar.a && this.b == nrVar.b;
    }

    public final int hashCode() {
        return this.b.hashCode() + (Integer.hashCode(this.a) * 31);
    }

    public final String toString() {
        return "PlayedState(playPositionMilliseconds=" + this.a + ", state=" + this.b + ')';
    }
}
