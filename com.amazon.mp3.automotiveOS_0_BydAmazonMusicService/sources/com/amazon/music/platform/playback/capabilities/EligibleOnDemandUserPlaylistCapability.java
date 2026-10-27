package com.amazon.music.platform.playback.capabilities;

import com.amazon.music.platform.Outcome;
import com.amazon.music.platform.data.eligibility.ContentEligibility;
import com.amazon.music.platform.data.eligibility.Eligibility;
import com.amazon.music.platform.data.entity.EntityItem;
import com.amazon.music.platform.data.entity.PlaylistEntityItem;
import com.amazon.music.platform.data.identifiers.UserPlaylistId;
import com.amazon.music.platform.playback.capabilities.reduce.ReducibleBoolean;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: EligibleOnDemandUserPlaylistCapability.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00132\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003:\u0001\u0013B\u0005¢\u0006\u0002\u0010\u0004J,\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00020\r2\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00100\u000f2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0014R\u0014\u0010\u0005\u001a\u00020\u0006X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\t\u001a\u00020\nX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\nX\u0082D¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/amazon/music/platform/playback/capabilities/EligibleOnDemandUserPlaylistCapability;", "Lcom/amazon/music/platform/playback/capabilities/PlayableEntityCapability;", "", "Lcom/amazon/music/platform/playback/capabilities/reduce/ReducibleBoolean;", "()V", "actionName", "", "getActionName", "()Ljava/lang/String;", "maximumAllAccessTrackCount", "", "minimumAllAccessTrackCount", "canPerformInternal", "Lcom/amazon/music/platform/Outcome;", "eligibilities", "", "Lcom/amazon/music/platform/data/eligibility/Eligibility;", "entityItem", "Lcom/amazon/music/platform/data/entity/EntityItem;", "Companion", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class EligibleOnDemandUserPlaylistCapability extends PlayableEntityCapability<Boolean> implements ReducibleBoolean {
    private final String actionName = "ON_DEMAND_ELIGIBLE";
    private final int minimumAllAccessTrackCount = 15;
    private final int maximumAllAccessTrackCount = 50;

    @Override // com.amazon.music.platform.playback.capabilities.reduce.Reducible
    public Outcome<Boolean> reduce(Outcome<? extends Boolean> outcome, Outcome<? extends Boolean> outcome2) {
        return ReducibleBoolean.DefaultImpls.reduce(this, outcome, outcome2);
    }

    @Override // com.amazon.music.platform.playback.capabilities.PlayableEntityCapability
    public String getActionName() {
        return this.actionName;
    }

    @Override // com.amazon.music.platform.playback.capabilities.PlayableEntityCapability
    protected Outcome<Boolean> canPerformInternal(Map<String, ? extends Eligibility> eligibilities, EntityItem entityItem) {
        Integer numberOfTracksInPlaylist;
        Intrinsics.checkNotNullParameter(eligibilities, "eligibilities");
        Eligibility eligibility = eligibilities.get("CONTENT");
        Intrinsics.checkNotNull(eligibility, "null cannot be cast to non-null type com.amazon.music.platform.data.eligibility.ContentEligibility");
        if (!(((ContentEligibility) eligibility).getContentEligibilityIdentifier().getId() instanceof UserPlaylistId)) {
            return new Outcome.Success(Boolean.FALSE);
        }
        PlaylistEntityItem playlistEntityItem = entityItem instanceof PlaylistEntityItem ? (PlaylistEntityItem) entityItem : null;
        if (playlistEntityItem != null && (numberOfTracksInPlaylist = playlistEntityItem.getNumberOfTracksInPlaylist()) != null) {
            int iIntValue = numberOfTracksInPlaylist.intValue();
            return new Outcome.Success(Boolean.valueOf(iIntValue >= this.minimumAllAccessTrackCount && iIntValue <= this.maximumAllAccessTrackCount));
        }
        return new Outcome.Success(Boolean.FALSE);
    }
}
