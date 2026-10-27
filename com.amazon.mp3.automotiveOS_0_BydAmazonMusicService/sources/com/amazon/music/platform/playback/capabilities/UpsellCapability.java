package com.amazon.music.platform.playback.capabilities;

import PlaybackInterface.v1_0.PlaybackEvent;
import com.amazon.music.platform.Outcome;
import com.amazon.music.platform.data.eligibility.AppStateEligibility;
import com.amazon.music.platform.data.eligibility.CapabilityExtensionsKt;
import com.amazon.music.platform.data.eligibility.Eligibility;
import com.amazon.music.platform.data.entity.EntityItem;
import com.amazon.music.platform.data.subscription.SubscriptionType;
import com.amazon.music.platform.featuregate.Feature;
import com.amazon.music.platform.playback.OfflineStatus;
import com.amazon.music.platform.playback.capabilities.reduce.Reducible;
import com.amazon.music.platform.playback.capabilities.reduce.ReducibleKt;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.SetsKt__SetsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* JADX INFO: compiled from: UpsellCapability.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 \u00132\b\u0012\u0004\u0012\u00020\u00020\u00012\b\u0012\u0004\u0012\u00020\u00020\u0003:\u0001\u0013B\u0005¢\u0006\u0002\u0010\u0004J,\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00020\n2\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\r0\f2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0014J*\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00020\n2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00020\n2\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00020\nH\u0016R\u0014\u0010\u0005\u001a\u00020\u0006X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u0014"}, d2 = {"Lcom/amazon/music/platform/playback/capabilities/UpsellCapability;", "Lcom/amazon/music/platform/playback/capabilities/PlayableEntityCapability;", "Lcom/amazon/music/platform/playback/capabilities/UpsellReason;", "Lcom/amazon/music/platform/playback/capabilities/reduce/Reducible;", "()V", "actionName", "", "getActionName", "()Ljava/lang/String;", "canPerformInternal", "Lcom/amazon/music/platform/Outcome;", "eligibilities", "", "Lcom/amazon/music/platform/data/eligibility/Eligibility;", "entityItem", "Lcom/amazon/music/platform/data/entity/EntityItem;", "reduce", "outcome1", "outcome2", "Companion", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class UpsellCapability extends PlayableEntityCapability<UpsellReason> implements Reducible<UpsellReason> {
    private static final Set<SubscriptionType> supportedTiers = SetsKt__SetsKt.setOf((Object[]) new SubscriptionType[]{SubscriptionType.FREE, SubscriptionType.SONIC_RUSH});
    private final String actionName = PlaybackEvent.UPSELL;

    @Override // com.amazon.music.platform.playback.capabilities.PlayableEntityCapability
    public String getActionName() {
        return this.actionName;
    }

    @Override // com.amazon.music.platform.playback.capabilities.PlayableEntityCapability
    protected Outcome<UpsellReason> canPerformInternal(Map<String, ? extends Eligibility> eligibilities, EntityItem entityItem) {
        boolean zIsFeatureEnabled;
        Intrinsics.checkNotNullParameter(eligibilities, "eligibilities");
        Eligibility eligibility = eligibilities.get("APPLICATION_STATE");
        Intrinsics.checkNotNull(eligibility, "null cannot be cast to non-null type com.amazon.music.platform.data.eligibility.AppStateEligibility");
        AppStateEligibility appStateEligibility = (AppStateEligibility) eligibility;
        Feature feature = appStateEligibility.getCapabilityFeatureOverrides().get(Reflection.getOrCreateKotlinClass(UpsellReason.class));
        if (feature != null) {
            zIsFeatureEnabled = CapabilityExtensionsKt.isFeatureEnabled(feature);
        } else {
            zIsFeatureEnabled = CapabilityExtensionsKt.isFeatureEnabled(appStateEligibility.getSupportsEligibilityFeature());
        }
        Map<String, Eligibility> mapEligibilitiesWithOverriddenFeatureFlag = CapabilityExtensionsKt.eligibilitiesWithOverriddenFeatureFlag(this, eligibilities, zIsFeatureEnabled);
        if (!supportedTiers.contains(appStateEligibility.getActiveSubscription())) {
            return new Outcome.Success(UpsellReason.None);
        }
        Outcome outcomeCanPerform$default = PlayableEntityCapability.canPerform$default(new OnDemandCapability(), mapEligibilitiesWithOverriddenFeatureFlag, entityItem, false, 4, null);
        if (Intrinsics.areEqual(outcomeCanPerform$default, new Outcome.Success(Boolean.FALSE))) {
            return new Outcome.Success(UpsellReason.NotOnDemandPlayable);
        }
        if (outcomeCanPerform$default instanceof Outcome.Failure) {
            return (Outcome.Failure) outcomeCanPerform$default;
        }
        Outcome outcomeCanPerform$default2 = PlayableEntityCapability.canPerform$default(new OfflineCapability(), mapEligibilitiesWithOverriddenFeatureFlag, entityItem, false, 4, null);
        if (zIsFeatureEnabled && (outcomeCanPerform$default2 instanceof Outcome.Success)) {
            Outcome.Success success = (Outcome.Success) outcomeCanPerform$default2;
            if (!((OfflineStatus) success.getValue()).getIsDownloadable() && !((OfflineStatus) success.getValue()).getIsDownloaded()) {
                return new Outcome.Success(UpsellReason.NotDownloadable);
            }
        }
        return new Outcome.Success(UpsellReason.None);
    }

    @Override // com.amazon.music.platform.playback.capabilities.reduce.Reducible
    public Outcome<UpsellReason> reduce(Outcome<? extends UpsellReason> outcome1, Outcome<? extends UpsellReason> outcome2) {
        Outcome.Success success;
        Intrinsics.checkNotNullParameter(outcome1, "outcome1");
        Intrinsics.checkNotNullParameter(outcome2, "outcome2");
        Outcome<UpsellReason> outcomeReduceFailures = ReducibleKt.reduceFailures(this, outcome1, outcome2);
        if (outcomeReduceFailures != null) {
            return outcomeReduceFailures;
        }
        Object value = ((Outcome.Success) outcome1).getValue();
        UpsellReason upsellReason = UpsellReason.None;
        if (value == upsellReason || ((Outcome.Success) outcome2).getValue() == upsellReason) {
            success = new Outcome.Success(upsellReason);
        } else {
            success = new Outcome.Success(UpsellReason.NotOnDemandPlayable);
        }
        return success;
    }
}
