package com.amazon.music.platform.data.eligibility;

import com.amazon.music.platform.PlatformProviderUtilKt;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PlaylistLimitEligibility.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0017\b\u0086\b\u0018\u0000 \u001e2\u00020\u0001:\u0001\u001eB=\u0012\b\b\u0002\u0010\n\u001a\u00020\b\u0012\b\b\u0002\u0010\r\u001a\u00020\u0004\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u0004\u0012\b\b\u0002\u0010\u0017\u001a\u00020\u0004¢\u0006\u0004\b\u001c\u0010\u001dJ\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\t\u001a\u00020\b2\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006HÖ\u0003R\u0017\u0010\n\u001a\u00020\b8\u0006¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\n\u0010\fR\u0017\u0010\r\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010R\u0019\u0010\u0011\u001a\u0004\u0018\u00010\u00048\u0006¢\u0006\f\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014R\u0019\u0010\u0015\u001a\u0004\u0018\u00010\u00048\u0006¢\u0006\f\n\u0004\b\u0015\u0010\u0012\u001a\u0004\b\u0016\u0010\u0014R\u0017\u0010\u0017\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0017\u0010\u000e\u001a\u0004\b\u0018\u0010\u0010R\u0014\u0010\u001b\u001a\u00020\u00028VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001a¨\u0006\u001f"}, d2 = {"Lcom/amazon/music/platform/data/eligibility/PlaylistLimitEligibility;", "Lcom/amazon/music/platform/data/eligibility/Eligibility;", "", "toString", "", "hashCode", "", "other", "", "equals", "isPlaylistRestricted", "Z", "()Z", "numTracksInPlaylist", "I", "getNumTracksInPlaylist", "()I", "numTracksToAdd", "Ljava/lang/Integer;", "getNumTracksToAdd", "()Ljava/lang/Integer;", "numTracksToRemove", "getNumTracksToRemove", "numChangesRemaining", "getNumChangesRemaining", "getTYPE", "()Ljava/lang/String;", "TYPE", "<init>", "(ZILjava/lang/Integer;Ljava/lang/Integer;I)V", "Companion", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
public final /* data */ class PlaylistLimitEligibility implements Eligibility {
    private final boolean isPlaylistRestricted;
    private final int numChangesRemaining;
    private final int numTracksInPlaylist;
    private final Integer numTracksToAdd;
    private final Integer numTracksToRemove;

    public PlaylistLimitEligibility() {
        this(false, 0, null, null, 0, 31, null);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PlaylistLimitEligibility)) {
            return false;
        }
        PlaylistLimitEligibility playlistLimitEligibility = (PlaylistLimitEligibility) other;
        return this.isPlaylistRestricted == playlistLimitEligibility.isPlaylistRestricted && this.numTracksInPlaylist == playlistLimitEligibility.numTracksInPlaylist && Intrinsics.areEqual(this.numTracksToAdd, playlistLimitEligibility.numTracksToAdd) && Intrinsics.areEqual(this.numTracksToRemove, playlistLimitEligibility.numTracksToRemove) && this.numChangesRemaining == playlistLimitEligibility.numChangesRemaining;
    }

    public String getTYPE() {
        return "PLAYLIST_LIMIT";
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [int] */
    /* JADX WARN: Type inference failed for: r0v10 */
    /* JADX WARN: Type inference failed for: r0v11 */
    public int hashCode() {
        boolean z = this.isPlaylistRestricted;
        ?? r0 = z;
        if (z) {
            r0 = 1;
        }
        int iHashCode = ((r0 * 31) + Integer.hashCode(this.numTracksInPlaylist)) * 31;
        Integer num = this.numTracksToAdd;
        int iHashCode2 = (iHashCode + (num == null ? 0 : num.hashCode())) * 31;
        Integer num2 = this.numTracksToRemove;
        return ((iHashCode2 + (num2 != null ? num2.hashCode() : 0)) * 31) + Integer.hashCode(this.numChangesRemaining);
    }

    public String toString() {
        return "PlaylistLimitEligibility(isPlaylistRestricted=" + this.isPlaylistRestricted + ", numTracksInPlaylist=" + this.numTracksInPlaylist + ", numTracksToAdd=" + this.numTracksToAdd + ", numTracksToRemove=" + this.numTracksToRemove + ", numChangesRemaining=" + this.numChangesRemaining + ')';
    }

    public PlaylistLimitEligibility(boolean z, int i, Integer num, Integer num2, int i2) {
        this.isPlaylistRestricted = z;
        this.numTracksInPlaylist = i;
        this.numTracksToAdd = num;
        this.numTracksToRemove = num2;
        this.numChangesRemaining = i2;
        PlatformProviderUtilKt.freeze(this);
    }

    public /* synthetic */ PlaylistLimitEligibility(boolean z, int i, Integer num, Integer num2, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? false : z, (i3 & 2) != 0 ? 0 : i, (i3 & 4) != 0 ? null : num, (i3 & 8) != 0 ? null : num2, (i3 & 16) != 0 ? 0 : i2);
    }

    /* JADX INFO: renamed from: isPlaylistRestricted, reason: from getter */
    public final boolean getIsPlaylistRestricted() {
        return this.isPlaylistRestricted;
    }

    public final int getNumTracksInPlaylist() {
        return this.numTracksInPlaylist;
    }

    public final Integer getNumTracksToAdd() {
        return this.numTracksToAdd;
    }

    public final Integer getNumTracksToRemove() {
        return this.numTracksToRemove;
    }

    public final int getNumChangesRemaining() {
        return this.numChangesRemaining;
    }
}
