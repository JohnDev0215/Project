package com.amazon.music.platform.playback.capabilities;

import com.amazon.music.platform.Outcome;
import com.amazon.music.platform.data.eligibility.AppStateEligibility;
import com.amazon.music.platform.data.eligibility.CapabilityExtensionsKt;
import com.amazon.music.platform.data.eligibility.ContentEligibility;
import com.amazon.music.platform.data.eligibility.ContentEligibilityQualifier;
import com.amazon.music.platform.data.eligibility.Eligibility;
import com.amazon.music.platform.data.entity.EntityItem;
import com.amazon.music.platform.data.entity.EntityItemIdentifier;
import com.amazon.music.platform.data.entity.SingleType;
import com.amazon.music.platform.data.subscription.SubscriptionType;
import com.amazon.music.platform.featuregate.Feature;
import com.amazon.music.platform.playback.OfflineStatus;
import com.amazon.music.platform.playback.PlaybackExperience;
import com.amazon.music.platform.playback.capabilities.error.EligibilityThrowable;
import com.amazon.music.platform.playback.capabilities.reduce.Prioritizable;
import com.amazon.music.platform.playback.capabilities.reduce.ReduciblePrioritizable;
import com.amazon.music.platform.playback.data.ShuffleType;
import com.amazon.music.skyfire.ui.skyfire.Contexts;
import java.util.Map;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* JADX INFO: compiled from: PlaybackCapability.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00102\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003:\u0001\u0010B\u0005¢\u0006\u0002\u0010\u0004J,\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00020\n2\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\r0\f2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0014R\u0014\u0010\u0005\u001a\u00020\u0006X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u0011"}, d2 = {"Lcom/amazon/music/platform/playback/capabilities/PlaybackCapability;", "Lcom/amazon/music/platform/playback/capabilities/PlayableEntityCapability;", "Lcom/amazon/music/platform/playback/PlaybackExperience;", "Lcom/amazon/music/platform/playback/capabilities/reduce/ReduciblePrioritizable;", "()V", "actionName", "", "getActionName", "()Ljava/lang/String;", "canPerformInternal", "Lcom/amazon/music/platform/Outcome;", "eligibilities", "", "Lcom/amazon/music/platform/data/eligibility/Eligibility;", "entityItem", "Lcom/amazon/music/platform/data/entity/EntityItem;", "Companion", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class PlaybackCapability extends PlayableEntityCapability<PlaybackExperience> implements ReduciblePrioritizable {
    private final String actionName = Contexts.Upsell.playback;

    /* JADX INFO: compiled from: PlaybackCapability.kt */
    @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ShuffleType.values().length];
            iArr[ShuffleType.INLINE.ordinal()] = 1;
            iArr[ShuffleType.MIXED.ordinal()] = 2;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    @Override // com.amazon.music.platform.playback.capabilities.reduce.Reducible
    public Outcome<Prioritizable> reduce(Outcome<? extends Prioritizable> outcome, Outcome<? extends Prioritizable> outcome2) {
        return ReduciblePrioritizable.DefaultImpls.reduce(this, outcome, outcome2);
    }

    @Override // com.amazon.music.platform.playback.capabilities.PlayableEntityCapability
    public String getActionName() {
        return this.actionName;
    }

    @Override // com.amazon.music.platform.playback.capabilities.PlayableEntityCapability
    protected Outcome<PlaybackExperience> canPerformInternal(Map<String, ? extends Eligibility> eligibilities, EntityItem entityItem) {
        boolean zIsFeatureEnabled;
        Intrinsics.checkNotNullParameter(eligibilities, "eligibilities");
        Eligibility eligibility = eligibilities.get("APPLICATION_STATE");
        Intrinsics.checkNotNull(eligibility, "null cannot be cast to non-null type com.amazon.music.platform.data.eligibility.AppStateEligibility");
        AppStateEligibility appStateEligibility = (AppStateEligibility) eligibility;
        Eligibility eligibility2 = eligibilities.get("CONTENT");
        Intrinsics.checkNotNull(eligibility2, "null cannot be cast to non-null type com.amazon.music.platform.data.eligibility.ContentEligibility");
        if (((ContentEligibility) eligibility2).has(ContentEligibilityQualifier.IsOwned.INSTANCE)) {
            return new Outcome.Success(PlaybackExperience.ON_DEMAND);
        }
        if (appStateEligibility.getActiveSubscription() == SubscriptionType.NONE) {
            return new Outcome.Failure(EligibilityThrowable.NoActiveSubscription.INSTANCE);
        }
        if (!isContentUnavailable(eligibilities)) {
            Feature feature = appStateEligibility.getCapabilityFeatureOverrides().get(Reflection.getOrCreateKotlinClass(PlaybackExperience.class));
            if (feature != null) {
                zIsFeatureEnabled = CapabilityExtensionsKt.isFeatureEnabled(feature);
            } else {
                zIsFeatureEnabled = CapabilityExtensionsKt.isFeatureEnabled(appStateEligibility.getSupportsEligibilityFeature());
            }
            Map<String, Eligibility> mapEligibilitiesWithOverriddenFeatureFlag = CapabilityExtensionsKt.eligibilitiesWithOverriddenFeatureFlag(this, eligibilities, zIsFeatureEnabled);
            if (appStateEligibility.getIsOffline()) {
                Outcome outcomeCanPerform$default = PlayableEntityCapability.canPerform$default(new OfflineCapability(), mapEligibilitiesWithOverriddenFeatureFlag, entityItem, false, 4, null);
                if (outcomeCanPerform$default instanceof Outcome.Failure) {
                    return new Outcome.Failure(((Outcome.Failure) outcomeCanPerform$default).getCause());
                }
                if (outcomeCanPerform$default instanceof Outcome.Success) {
                    return ((OfflineStatus) ((Outcome.Success) outcomeCanPerform$default).getValue()).getIsPlayableOffline() ? new Outcome.Success(PlaybackExperience.ON_DEMAND) : new Outcome.Failure(EligibilityThrowable.NoNetwork.INSTANCE);
                }
                throw new NoWhenBranchMatchedException();
            }
            if (appStateEligibility.getActiveSubscription().isUnlimited$MusicPlatformCore_release() || appStateEligibility.getActiveSubscription() == SubscriptionType.PRIME) {
                return new Outcome.Success(PlaybackExperience.ON_DEMAND);
            }
            if (!zIsFeatureEnabled) {
                return new Outcome.Success(PlaybackExperience.STATION_FROM_ANYTHING);
            }
            Outcome outcomeCanPerform$default2 = PlayableEntityCapability.canPerform$default(new OnDemandCapability(), mapEligibilitiesWithOverriddenFeatureFlag, entityItem, false, 4, null);
            if (outcomeCanPerform$default2 instanceof Outcome.Failure) {
                return new Outcome.Failure(((Outcome.Failure) outcomeCanPerform$default2).getCause());
            }
            if ((outcomeCanPerform$default2 instanceof Outcome.Success) && ((Boolean) ((Outcome.Success) outcomeCanPerform$default2).getValue()).booleanValue()) {
                return new Outcome.Success(PlaybackExperience.ON_DEMAND);
            }
            Outcome outcomeCanPerform$default3 = PlayableEntityCapability.canPerform$default(new ShuffleCapability(), mapEligibilitiesWithOverriddenFeatureFlag, entityItem, false, 4, null);
            if (outcomeCanPerform$default3 instanceof Outcome.Failure) {
                return new Outcome.Failure(((Outcome.Failure) outcomeCanPerform$default3).getCause());
            }
            if (outcomeCanPerform$default3 instanceof Outcome.Success) {
                int i = WhenMappings.$EnumSwitchMapping$0[((ShuffleType) ((Outcome.Success) outcomeCanPerform$default3).getValue()).ordinal()];
                if (i == 1) {
                    return new Outcome.Success(PlaybackExperience.INLINE_SHUFFLE);
                }
                if (i == 2) {
                    EntityItemIdentifier entityItemIdentifier = entityItem != null ? entityItem.getEntityItemIdentifier() : null;
                    if (entityItemIdentifier != null && !Intrinsics.areEqual(entityItemIdentifier.getType(), SingleType.Track.INSTANCE)) {
                        return new Outcome.Success(PlaybackExperience.AUGMENTED_SHUFFLE);
                    }
                }
            }
            Outcome outcomeCanPerform$default4 = PlayableEntityCapability.canPerform$default(new StationFromAnythingCapability(), mapEligibilitiesWithOverriddenFeatureFlag, entityItem, false, 4, null);
            return outcomeCanPerform$default4 instanceof Outcome.Failure ? new Outcome.Failure(((Outcome.Failure) outcomeCanPerform$default4).getCause()) : ((outcomeCanPerform$default4 instanceof Outcome.Success) && ((Boolean) ((Outcome.Success) outcomeCanPerform$default4).getValue()).booleanValue()) ? new Outcome.Success(PlaybackExperience.STATION_FROM_ANYTHING) : new Outcome.Failure(new EligibilityThrowable.UnexpectedFailure("Unhandled playback capability"));
        }
        return new Outcome.Failure(EligibilityThrowable.ContentUnavailable.INSTANCE);
    }
}
