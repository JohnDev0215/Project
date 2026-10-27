package com.amazon.music.platform.playback.capabilities;

import com.amazon.music.platform.Outcome;
import com.amazon.music.platform.data.eligibility.AppStateEligibility;
import com.amazon.music.platform.data.eligibility.CapabilityExtensionsKt;
import com.amazon.music.platform.data.eligibility.ContentEligibility;
import com.amazon.music.platform.data.eligibility.ContentEligibilityQualifier;
import com.amazon.music.platform.data.eligibility.Eligibility;
import com.amazon.music.platform.data.eligibility.PlaymodeEligibility;
import com.amazon.music.platform.data.entity.EntityItem;
import com.amazon.music.platform.featuregate.Feature;
import com.amazon.music.platform.featuregate.MpcFeatureKeys;
import com.amazon.music.platform.playback.capabilities.reduce.Prioritizable;
import com.amazon.music.platform.playback.capabilities.reduce.ReduciblePrioritizable;
import com.amazon.music.platform.playback.data.ShuffleType;
import java.util.Map;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* JADX INFO: compiled from: ShuffleCapability.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00102\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003:\u0001\u0010B\u0005¢\u0006\u0002\u0010\u0004J,\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00020\n2\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\r0\f2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0014R\u0014\u0010\u0005\u001a\u00020\u0006X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u0011"}, d2 = {"Lcom/amazon/music/platform/playback/capabilities/ShuffleCapability;", "Lcom/amazon/music/platform/playback/capabilities/PlayableEntityCapability;", "Lcom/amazon/music/platform/playback/data/ShuffleType;", "Lcom/amazon/music/platform/playback/capabilities/reduce/ReduciblePrioritizable;", "()V", "actionName", "", "getActionName", "()Ljava/lang/String;", "canPerformInternal", "Lcom/amazon/music/platform/Outcome;", "eligibilities", "", "Lcom/amazon/music/platform/data/eligibility/Eligibility;", "entityItem", "Lcom/amazon/music/platform/data/entity/EntityItem;", "Companion", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class ShuffleCapability extends PlayableEntityCapability<ShuffleType> implements ReduciblePrioritizable {
    private final String actionName = "SHUFFLE";

    @Override // com.amazon.music.platform.playback.capabilities.reduce.Reducible
    public Outcome<Prioritizable> reduce(Outcome<? extends Prioritizable> outcome, Outcome<? extends Prioritizable> outcome2) {
        return ReduciblePrioritizable.DefaultImpls.reduce(this, outcome, outcome2);
    }

    @Override // com.amazon.music.platform.playback.capabilities.PlayableEntityCapability
    public String getActionName() {
        return this.actionName;
    }

    @Override // com.amazon.music.platform.playback.capabilities.PlayableEntityCapability
    protected Outcome<ShuffleType> canPerformInternal(Map<String, ? extends Eligibility> eligibilities, EntityItem entityItem) {
        boolean zIsFeatureEnabled;
        Intrinsics.checkNotNullParameter(eligibilities, "eligibilities");
        Eligibility eligibility = eligibilities.get("APPLICATION_STATE");
        Intrinsics.checkNotNull(eligibility, "null cannot be cast to non-null type com.amazon.music.platform.data.eligibility.AppStateEligibility");
        AppStateEligibility appStateEligibility = (AppStateEligibility) eligibility;
        Eligibility eligibility2 = eligibilities.get("CONTENT");
        Intrinsics.checkNotNull(eligibility2, "null cannot be cast to non-null type com.amazon.music.platform.data.eligibility.ContentEligibility");
        ContentEligibility contentEligibility = (ContentEligibility) eligibility2;
        Eligibility eligibility3 = eligibilities.get("PLAYMODES");
        PlaymodeEligibility playmodeEligibility = eligibility3 instanceof PlaymodeEligibility ? (PlaymodeEligibility) eligibility3 : null;
        Feature feature = appStateEligibility.getCapabilityFeatureOverrides().get(Reflection.getOrCreateKotlinClass(ShuffleType.class));
        if (feature != null) {
            zIsFeatureEnabled = CapabilityExtensionsKt.isFeatureEnabled(feature);
        } else {
            zIsFeatureEnabled = CapabilityExtensionsKt.isFeatureEnabled(appStateEligibility.getSupportsEligibilityFeature());
        }
        if (zIsFeatureEnabled && playmodeEligibility != null && !CapabilityExtensionsKt.shouldOverridePlaymodeEligibility(contentEligibility)) {
            if (!contentEligibility.has(ContentEligibilityQualifier.IsFreeOnDemandPlayable.INSTANCE) && playmodeEligibility.shuffleType() == ShuffleType.MIXED && CapabilityExtensionsKt.isMpcFeatureEnabled(appStateEligibility, MpcFeatureKeys.should_override_augmented_shuffle_with_sfa)) {
                return new Outcome.Success(ShuffleType.NONE);
            }
            return new Outcome.Success(playmodeEligibility.shuffleType());
        }
        Outcome outcomeCanPerform$default = PlayableEntityCapability.canPerform$default(new OnDemandCapability(), CapabilityExtensionsKt.eligibilitiesWithOverriddenFeatureFlag(this, eligibilities, zIsFeatureEnabled), entityItem, false, 4, null);
        if (outcomeCanPerform$default instanceof Outcome.Failure) {
            return new Outcome.Failure(((Outcome.Failure) outcomeCanPerform$default).getCause());
        }
        if (outcomeCanPerform$default instanceof Outcome.Success) {
            if (((Boolean) ((Outcome.Success) outcomeCanPerform$default).getValue()).booleanValue()) {
                return new Outcome.Success(ShuffleType.INLINE);
            }
            if (!zIsFeatureEnabled || CapabilityExtensionsKt.isMpcFeatureEnabled(appStateEligibility, MpcFeatureKeys.should_override_augmented_shuffle_with_sfa)) {
                return new Outcome.Success(ShuffleType.NONE);
            }
            return new Outcome.Success(ShuffleType.MIXED);
        }
        throw new NoWhenBranchMatchedException();
    }
}
