package com.amazon.music.platform.playback.capabilities;

import com.amazon.music.platform.Outcome;
import com.amazon.music.platform.data.eligibility.AppStateEligibility;
import com.amazon.music.platform.data.eligibility.ContentEligibility;
import com.amazon.music.platform.data.eligibility.ContentEligibilityQualifier;
import com.amazon.music.platform.data.eligibility.Eligibility;
import com.amazon.music.platform.data.entity.EntityItem;
import com.amazon.music.platform.data.subscription.SubscriptionType;
import com.amazon.music.platform.playback.OfflineStatus;
import com.amazon.music.platform.playback.capabilities.error.EligibilityThrowable;
import com.amazon.music.platform.playback.capabilities.reduce.Prioritizable;
import com.amazon.music.platform.playback.capabilities.reduce.ReduciblePrioritizable;
import com.amazon.music.platform.playback.data.PlayQueueInteractionPattern;
import com.amazon.music.platform.playback.data.ShuffleType;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: InteractionPatternCapability.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0016\u0018\u0000 \u00102\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003:\u0001\u0010B\u0005¢\u0006\u0002\u0010\u0004J,\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00020\n2\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\r0\f2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0014R\u0014\u0010\u0005\u001a\u00020\u0006X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u0011"}, d2 = {"Lcom/amazon/music/platform/playback/capabilities/InteractionPatternCapability;", "Lcom/amazon/music/platform/playback/capabilities/PlayableEntityCapability;", "Lcom/amazon/music/platform/playback/data/PlayQueueInteractionPattern;", "Lcom/amazon/music/platform/playback/capabilities/reduce/ReduciblePrioritizable;", "()V", "actionName", "", "getActionName", "()Ljava/lang/String;", "canPerformInternal", "Lcom/amazon/music/platform/Outcome;", "eligibilities", "", "Lcom/amazon/music/platform/data/eligibility/Eligibility;", "entityItem", "Lcom/amazon/music/platform/data/entity/EntityItem;", "Companion", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public class InteractionPatternCapability extends PlayableEntityCapability<PlayQueueInteractionPattern> implements ReduciblePrioritizable {
    private final String actionName = "INTERACTION_PATTERN";

    /* JADX INFO: compiled from: InteractionPatternCapability.kt */
    @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ShuffleType.values().length];
            iArr[ShuffleType.INLINE.ordinal()] = 1;
            iArr[ShuffleType.MIXED.ordinal()] = 2;
            iArr[ShuffleType.NONE.ordinal()] = 3;
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
    protected Outcome<PlayQueueInteractionPattern> canPerformInternal(Map<String, ? extends Eligibility> eligibilities, EntityItem entityItem) {
        Intrinsics.checkNotNullParameter(eligibilities, "eligibilities");
        Eligibility eligibility = eligibilities.get("APPLICATION_STATE");
        Intrinsics.checkNotNull(eligibility, "null cannot be cast to non-null type com.amazon.music.platform.data.eligibility.AppStateEligibility");
        AppStateEligibility appStateEligibility = (AppStateEligibility) eligibility;
        Eligibility eligibility2 = eligibilities.get("CONTENT");
        Intrinsics.checkNotNull(eligibility2, "null cannot be cast to non-null type com.amazon.music.platform.data.eligibility.ContentEligibility");
        ContentEligibility contentEligibility = (ContentEligibility) eligibility2;
        if (!appStateEligibility.getIsCasting() && contentEligibility.has(ContentEligibilityQualifier.IsOwned.INSTANCE) && !shouldSupportInterludes(eligibilities)) {
            return new Outcome.Success(PlayQueueInteractionPattern.LOCAL);
        }
        if (appStateEligibility.getActiveSubscription() == SubscriptionType.NONE) {
            return new Outcome.Failure(EligibilityThrowable.NoActiveSubscription.INSTANCE);
        }
        if (isContentUnavailable(eligibilities)) {
            return new Outcome.Failure(EligibilityThrowable.ContentUnavailable.INSTANCE);
        }
        if (appStateEligibility.getIsCasting()) {
            return new Outcome.Success(PlayQueueInteractionPattern.ONLINE);
        }
        if (appStateEligibility.getIsOffline()) {
            Outcome outcomeCanPerform$default = PlayableEntityCapability.canPerform$default(new OfflineCapability(), eligibilities, entityItem, false, 4, null);
            if (outcomeCanPerform$default instanceof Outcome.Success) {
                if (((OfflineStatus) ((Outcome.Success) outcomeCanPerform$default).getValue()).getIsPlayableOffline()) {
                    return new Outcome.Success(PlayQueueInteractionPattern.LOCAL);
                }
                Outcome outcomeCanPerform$default2 = PlayableEntityCapability.canPerform$default(new AdsCapability(), eligibilities, entityItem, false, 4, null);
                if ((outcomeCanPerform$default2 instanceof Outcome.Success) && ((Boolean) ((Outcome.Success) outcomeCanPerform$default2).getValue()).booleanValue()) {
                    return new Outcome.Failure(EligibilityThrowable.ContentUnavailable.INSTANCE);
                }
            } else if (outcomeCanPerform$default instanceof Outcome.Failure) {
                return new Outcome.Failure(((Outcome.Failure) outcomeCanPerform$default).getCause());
            }
        }
        Outcome outcomeCanPerform$default3 = PlayableEntityCapability.canPerform$default(new AdsCapability(), eligibilities, entityItem, false, 4, null);
        if (outcomeCanPerform$default3 instanceof Outcome.Success) {
            if (((Boolean) ((Outcome.Success) outcomeCanPerform$default3).getValue()).booleanValue()) {
                return new Outcome.Success(PlayQueueInteractionPattern.ONLINE);
            }
        } else if (outcomeCanPerform$default3 instanceof Outcome.Failure) {
            return new Outcome.Failure(((Outcome.Failure) outcomeCanPerform$default3).getCause());
        }
        if (shouldSupportInterludes(eligibilities)) {
            return new Outcome.Success(PlayQueueInteractionPattern.ONLINE);
        }
        Outcome outcomeCanPerform$default4 = PlayableEntityCapability.canPerform$default(new OnDemandCapability(), eligibilities, entityItem, false, 4, null);
        if (outcomeCanPerform$default4 instanceof Outcome.Success) {
            if (((Boolean) ((Outcome.Success) outcomeCanPerform$default4).getValue()).booleanValue()) {
                return new Outcome.Success(PlayQueueInteractionPattern.LOCAL);
            }
        } else if (outcomeCanPerform$default4 instanceof Outcome.Failure) {
            return new Outcome.Failure(((Outcome.Failure) outcomeCanPerform$default4).getCause());
        }
        Outcome outcomeCanPerform$default5 = PlayableEntityCapability.canPerform$default(new ShuffleCapability(), eligibilities, entityItem, false, 4, null);
        if (outcomeCanPerform$default5 instanceof Outcome.Success) {
            int i = WhenMappings.$EnumSwitchMapping$0[((ShuffleType) ((Outcome.Success) outcomeCanPerform$default5).getValue()).ordinal()];
            if (i == 1) {
                return new Outcome.Success(PlayQueueInteractionPattern.LOCAL);
            }
            if (i == 2) {
                return new Outcome.Success(PlayQueueInteractionPattern.ONLINE);
            }
        } else if (outcomeCanPerform$default5 instanceof Outcome.Failure) {
            return new Outcome.Failure(((Outcome.Failure) outcomeCanPerform$default5).getCause());
        }
        Outcome outcomeCanPerform$default6 = PlayableEntityCapability.canPerform$default(new StationFromAnythingCapability(), eligibilities, entityItem, false, 4, null);
        if (outcomeCanPerform$default6 instanceof Outcome.Success) {
            if (((Boolean) ((Outcome.Success) outcomeCanPerform$default6).getValue()).booleanValue()) {
                return new Outcome.Success(PlayQueueInteractionPattern.ONLINE);
            }
        } else if (outcomeCanPerform$default6 instanceof Outcome.Failure) {
            return new Outcome.Failure(((Outcome.Failure) outcomeCanPerform$default6).getCause());
        }
        return new Outcome.Failure(new EligibilityThrowable.UnexpectedFailure("Unhandled playback capability"));
    }
}
