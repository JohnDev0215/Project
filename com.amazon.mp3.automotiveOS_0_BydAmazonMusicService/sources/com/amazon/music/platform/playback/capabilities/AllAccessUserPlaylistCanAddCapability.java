package com.amazon.music.platform.playback.capabilities;

import com.amazon.music.platform.Outcome;
import com.amazon.music.platform.data.eligibility.Eligibility;
import com.amazon.music.platform.data.eligibility.PlaylistLimitEligibility;
import com.amazon.music.platform.data.entity.EntityItem;
import com.amazon.music.platform.playback.capabilities.error.EligibilityThrowable;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AllAccessUserPlaylistCanAddCapability.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u000f2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u000fB\u0005¢\u0006\u0002\u0010\u0003J,\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00020\t2\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\f0\u000b2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0014R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0010"}, d2 = {"Lcom/amazon/music/platform/playback/capabilities/AllAccessUserPlaylistCanAddCapability;", "Lcom/amazon/music/platform/playback/capabilities/PlayableEntityCapability;", "", "()V", "actionName", "", "getActionName", "()Ljava/lang/String;", "canPerformInternal", "Lcom/amazon/music/platform/Outcome;", "eligibilities", "", "Lcom/amazon/music/platform/data/eligibility/Eligibility;", "entityItem", "Lcom/amazon/music/platform/data/entity/EntityItem;", "Companion", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class AllAccessUserPlaylistCanAddCapability extends PlayableEntityCapability<Boolean> {
    private final String actionName = "AAUP_CAN_ADD";

    @Override // com.amazon.music.platform.playback.capabilities.PlayableEntityCapability
    public String getActionName() {
        return this.actionName;
    }

    @Override // com.amazon.music.platform.playback.capabilities.PlayableEntityCapability
    protected Outcome<Boolean> canPerformInternal(Map<String, ? extends Eligibility> eligibilities, EntityItem entityItem) {
        Intrinsics.checkNotNullParameter(eligibilities, "eligibilities");
        Eligibility eligibility = eligibilities.get("PLAYLIST_LIMIT");
        Intrinsics.checkNotNull(eligibility, "null cannot be cast to non-null type com.amazon.music.platform.data.eligibility.PlaylistLimitEligibility");
        PlaylistLimitEligibility playlistLimitEligibility = (PlaylistLimitEligibility) eligibility;
        Integer numTracksToAdd = playlistLimitEligibility.getNumTracksToAdd();
        if (numTracksToAdd == null) {
            return new Outcome.Success(Boolean.TRUE);
        }
        int iIntValue = numTracksToAdd.intValue();
        if (playlistLimitEligibility.getNumTracksInPlaylist() + iIntValue > 50) {
            return new Outcome.Failure(EligibilityThrowable.AAUPMaxPlaylistSize.INSTANCE);
        }
        if (playlistLimitEligibility.getNumTracksInPlaylist() + iIntValue <= 15) {
            return new Outcome.Success(Boolean.TRUE);
        }
        if (playlistLimitEligibility.getNumTracksInPlaylist() + iIntValue <= 50 && !playlistLimitEligibility.getIsPlaylistRestricted()) {
            return new Outcome.Success(Boolean.TRUE);
        }
        if (playlistLimitEligibility.getNumChangesRemaining() < iIntValue) {
            return new Outcome.Failure(EligibilityThrowable.AAUPChangeLimitsReached.INSTANCE);
        }
        return new Outcome.Success(Boolean.TRUE);
    }
}
