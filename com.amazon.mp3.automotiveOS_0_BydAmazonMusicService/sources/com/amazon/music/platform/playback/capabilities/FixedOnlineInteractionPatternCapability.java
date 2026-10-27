package com.amazon.music.platform.playback.capabilities;

import com.amazon.music.platform.Outcome;
import com.amazon.music.platform.data.eligibility.Eligibility;
import com.amazon.music.platform.data.entity.EntityItem;
import com.amazon.music.platform.playback.data.PlayQueueInteractionPattern;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: InteractionPatternCapability.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J,\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\b2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0014¨\u0006\r"}, d2 = {"Lcom/amazon/music/platform/playback/capabilities/FixedOnlineInteractionPatternCapability;", "Lcom/amazon/music/platform/playback/capabilities/InteractionPatternCapability;", "Lcom/amazon/music/platform/playback/capabilities/reduce/ReduciblePrioritizable;", "()V", "canPerformInternal", "Lcom/amazon/music/platform/Outcome;", "Lcom/amazon/music/platform/playback/data/PlayQueueInteractionPattern;", "eligibilities", "", "", "Lcom/amazon/music/platform/data/eligibility/Eligibility;", "entityItem", "Lcom/amazon/music/platform/data/entity/EntityItem;", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class FixedOnlineInteractionPatternCapability extends InteractionPatternCapability {
    @Override // com.amazon.music.platform.playback.capabilities.InteractionPatternCapability, com.amazon.music.platform.playback.capabilities.PlayableEntityCapability
    protected Outcome<PlayQueueInteractionPattern> canPerformInternal(Map<String, ? extends Eligibility> eligibilities, EntityItem entityItem) {
        Intrinsics.checkNotNullParameter(eligibilities, "eligibilities");
        return new Outcome.Success(PlayQueueInteractionPattern.ONLINE);
    }
}
