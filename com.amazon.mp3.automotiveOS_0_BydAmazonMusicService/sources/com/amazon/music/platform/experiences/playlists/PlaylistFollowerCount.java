package com.amazon.music.platform.experiences.playlists;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PlaylistFireFlyService.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0006\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B\u001b\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\r¢\u0006\u0004\b\u0012\u0010\u0013J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0019\u0010\t\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0019\u0010\u000e\u001a\u0004\u0018\u00010\r8\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0014"}, d2 = {"Lcom/amazon/music/platform/experiences/playlists/PlaylistFollowerCount;", "", "", "toString", "", "hashCode", "other", "", "equals", "playlistId", "Ljava/lang/String;", "getPlaylistId", "()Ljava/lang/String;", "", "followerCount", "Ljava/lang/Double;", "getFollowerCount", "()Ljava/lang/Double;", "<init>", "(Ljava/lang/String;Ljava/lang/Double;)V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
public final /* data */ class PlaylistFollowerCount {
    private final Double followerCount;
    private final String playlistId;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PlaylistFollowerCount)) {
            return false;
        }
        PlaylistFollowerCount playlistFollowerCount = (PlaylistFollowerCount) other;
        return Intrinsics.areEqual(this.playlistId, playlistFollowerCount.playlistId) && Intrinsics.areEqual((Object) this.followerCount, (Object) playlistFollowerCount.followerCount);
    }

    public int hashCode() {
        String str = this.playlistId;
        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
        Double d = this.followerCount;
        return iHashCode + (d != null ? d.hashCode() : 0);
    }

    public String toString() {
        return "PlaylistFollowerCount(playlistId=" + this.playlistId + ", followerCount=" + this.followerCount + ')';
    }

    public PlaylistFollowerCount(String str, Double d) {
        this.playlistId = str;
        this.followerCount = d;
    }

    public final Double getFollowerCount() {
        return this.followerCount;
    }
}
