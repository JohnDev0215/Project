package com.amazon.music.platform.playback.capabilities;

import com.amazon.music.platform.Outcome;
import com.amazon.music.platform.data.eligibility.AppStateEligibility;
import com.amazon.music.platform.data.eligibility.CapabilityExtensionsKt;
import com.amazon.music.platform.data.eligibility.Eligibility;
import com.amazon.music.platform.data.entity.CollectionType;
import com.amazon.music.platform.data.entity.EntityItem;
import com.amazon.music.platform.data.entity.EntityItemIdentifier;
import com.amazon.music.platform.data.entity.EntityItemType;
import com.amazon.music.platform.data.entity.SingleType;
import com.amazon.music.platform.featuregate.Feature;
import com.amazon.music.platform.playback.PlaybackExperience;
import com.amazon.music.platform.playback.capabilities.reduce.Prioritizable;
import com.amazon.music.platform.playback.capabilities.reduce.ReduciblePrioritizable;
import com.amazon.music.platform.playback.data.NonBlockingUpsellReason;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* JADX INFO: compiled from: NonBlockingUpsellCapability.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\u0018\u0000 \u00192\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003:\u0001\u0019B\u0015\u0012\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\r0\u0001¢\u0006\u0004\b\u0017\u0010\u0018J\u0012\u0010\u0006\u001a\u00020\u00022\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0002J,\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00020\u000b2\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u00072\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0014R\u001d\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\r0\u00018\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0012\u001a\u00020\b8\u0016X\u0096D¢\u0006\f\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015¨\u0006\u001a"}, d2 = {"Lcom/amazon/music/platform/playback/capabilities/NonBlockingUpsellCapability;", "Lcom/amazon/music/platform/playback/capabilities/PlayableEntityCapability;", "Lcom/amazon/music/platform/playback/data/NonBlockingUpsellReason;", "Lcom/amazon/music/platform/playback/capabilities/reduce/ReduciblePrioritizable;", "Lcom/amazon/music/platform/data/entity/EntityItem;", "entityItem", "getEntityUpsellReason", "", "", "Lcom/amazon/music/platform/data/eligibility/Eligibility;", "eligibilities", "Lcom/amazon/music/platform/Outcome;", "canPerformInternal", "Lcom/amazon/music/platform/playback/PlaybackExperience;", "playbackExperience", "Lcom/amazon/music/platform/playback/capabilities/PlayableEntityCapability;", "getPlaybackExperience", "()Lcom/amazon/music/platform/playback/capabilities/PlayableEntityCapability;", "actionName", "Ljava/lang/String;", "getActionName", "()Ljava/lang/String;", "playbackExperienceCapability", "<init>", "(Lcom/amazon/music/platform/playback/capabilities/PlayableEntityCapability;)V", "Companion", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
public final class NonBlockingUpsellCapability extends PlayableEntityCapability<NonBlockingUpsellReason> implements ReduciblePrioritizable {
    private final String actionName;
    private final PlayableEntityCapability<PlaybackExperience> playbackExperience;

    @Override // com.amazon.music.platform.playback.capabilities.reduce.Reducible
    public Outcome<Prioritizable> reduce(Outcome<? extends Prioritizable> outcome, Outcome<? extends Prioritizable> outcome2) {
        return ReduciblePrioritizable.DefaultImpls.reduce(this, outcome, outcome2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public NonBlockingUpsellCapability(PlayableEntityCapability<? extends PlaybackExperience> playbackExperienceCapability) {
        Intrinsics.checkNotNullParameter(playbackExperienceCapability, "playbackExperienceCapability");
        this.playbackExperience = playbackExperienceCapability;
        this.actionName = "NON_BLOCKING_UPSELL";
    }

    @Override // com.amazon.music.platform.playback.capabilities.PlayableEntityCapability
    public String getActionName() {
        return this.actionName;
    }

    @Override // com.amazon.music.platform.playback.capabilities.PlayableEntityCapability
    protected Outcome<NonBlockingUpsellReason> canPerformInternal(Map<String, ? extends Eligibility> eligibilities, EntityItem entityItem) {
        boolean zIsFeatureEnabled;
        PlaybackExperience playbackExperience;
        Intrinsics.checkNotNullParameter(eligibilities, "eligibilities");
        Eligibility eligibility = eligibilities.get("APPLICATION_STATE");
        Intrinsics.checkNotNull(eligibility, "null cannot be cast to non-null type com.amazon.music.platform.data.eligibility.AppStateEligibility");
        AppStateEligibility appStateEligibility = (AppStateEligibility) eligibility;
        Feature feature = appStateEligibility.getCapabilityFeatureOverrides().get(Reflection.getOrCreateKotlinClass(NonBlockingUpsellReason.class));
        if (feature != null) {
            zIsFeatureEnabled = CapabilityExtensionsKt.isFeatureEnabled(feature);
        } else {
            zIsFeatureEnabled = CapabilityExtensionsKt.isFeatureEnabled(appStateEligibility.getSupportsEligibilityFeature());
        }
        Map<String, Eligibility> mapEligibilitiesWithOverriddenFeatureFlag = CapabilityExtensionsKt.eligibilitiesWithOverriddenFeatureFlag(this, eligibilities, zIsFeatureEnabled);
        if (!zIsFeatureEnabled) {
            return new Outcome.Success(NonBlockingUpsellReason.None);
        }
        Outcome outcomeCanPerform$default = PlayableEntityCapability.canPerform$default(this.playbackExperience, mapEligibilitiesWithOverriddenFeatureFlag, entityItem, false, 4, null);
        if (outcomeCanPerform$default instanceof Outcome.Failure) {
            return new Outcome.Failure(((Outcome.Failure) outcomeCanPerform$default).getCause());
        }
        if ((outcomeCanPerform$default instanceof Outcome.Success) && ((playbackExperience = (PlaybackExperience) ((Outcome.Success) outcomeCanPerform$default).getValue()) == PlaybackExperience.AUGMENTED_SHUFFLE || playbackExperience == PlaybackExperience.STATION_FROM_ANYTHING)) {
            return new Outcome.Success(getEntityUpsellReason(entityItem));
        }
        return new Outcome.Success(NonBlockingUpsellReason.None);
    }

    private final NonBlockingUpsellReason getEntityUpsellReason(EntityItem entityItem) {
        EntityItemIdentifier entityItemIdentifier;
        EntityItemType type = (entityItem == null || (entityItemIdentifier = entityItem.getEntityItemIdentifier()) == null) ? null : entityItemIdentifier.getType();
        return type instanceof CollectionType.Album ? NonBlockingUpsellReason.NotOnDemandAlbum : type instanceof CollectionType.Artist ? NonBlockingUpsellReason.NotOnDemandArtist : type instanceof CollectionType.Playlist ? NonBlockingUpsellReason.NotOnDemandPlaylist : type instanceof SingleType.Track ? NonBlockingUpsellReason.NotOnDemandTrack : NonBlockingUpsellReason.None;
    }
}
