package com.amazon.music.platform.playback.data;

import com.amazon.music.platform.PlatformProviderUtilKt;
import com.amazon.music.platform.playback.state.LoopMode;
import com.amazon.music.platform.playback.state.ShuffleMode;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PlaybackOptions.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0086\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\n\u001a\u00020\t\u0012\u0006\u0010\u000f\u001a\u00020\u000e\u0012\u0006\u0010\u0013\u001a\u00020\u0007¢\u0006\u0004\b\u0017\u0010\u0018J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\n\u001a\u00020\t8\u0006¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR\u0017\u0010\u000f\u001a\u00020\u000e8\u0006¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012R\u0017\u0010\u0013\u001a\u00020\u00078\u0006¢\u0006\f\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016¨\u0006\u0019"}, d2 = {"Lcom/amazon/music/platform/playback/data/PlaybackOptions;", "", "", "toString", "", "hashCode", "other", "", "equals", "Lcom/amazon/music/platform/playback/state/LoopMode;", "loopMode", "Lcom/amazon/music/platform/playback/state/LoopMode;", "getLoopMode", "()Lcom/amazon/music/platform/playback/state/LoopMode;", "Lcom/amazon/music/platform/playback/state/ShuffleMode;", "shuffleMode", "Lcom/amazon/music/platform/playback/state/ShuffleMode;", "getShuffleMode", "()Lcom/amazon/music/platform/playback/state/ShuffleMode;", "showNowPlaying", "Z", "getShowNowPlaying", "()Z", "<init>", "(Lcom/amazon/music/platform/playback/state/LoopMode;Lcom/amazon/music/platform/playback/state/ShuffleMode;Z)V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
public final /* data */ class PlaybackOptions {
    private final LoopMode loopMode;
    private final boolean showNowPlaying;
    private final ShuffleMode shuffleMode;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PlaybackOptions)) {
            return false;
        }
        PlaybackOptions playbackOptions = (PlaybackOptions) other;
        return this.loopMode == playbackOptions.loopMode && this.shuffleMode == playbackOptions.shuffleMode && this.showNowPlaying == playbackOptions.showNowPlaying;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v3, types: [int] */
    /* JADX WARN: Type inference failed for: r1v4 */
    /* JADX WARN: Type inference failed for: r1v5 */
    public int hashCode() {
        int iHashCode = ((this.loopMode.hashCode() * 31) + this.shuffleMode.hashCode()) * 31;
        boolean z = this.showNowPlaying;
        ?? r1 = z;
        if (z) {
            r1 = 1;
        }
        return iHashCode + r1;
    }

    public String toString() {
        return "PlaybackOptions(loopMode=" + this.loopMode + ", shuffleMode=" + this.shuffleMode + ", showNowPlaying=" + this.showNowPlaying + ')';
    }

    public PlaybackOptions(LoopMode loopMode, ShuffleMode shuffleMode, boolean z) {
        Intrinsics.checkNotNullParameter(loopMode, "loopMode");
        Intrinsics.checkNotNullParameter(shuffleMode, "shuffleMode");
        this.loopMode = loopMode;
        this.shuffleMode = shuffleMode;
        this.showNowPlaying = z;
        PlatformProviderUtilKt.freeze(this);
    }

    public final ShuffleMode getShuffleMode() {
        return this.shuffleMode;
    }

    public final boolean getShowNowPlaying() {
        return this.showNowPlaying;
    }
}
