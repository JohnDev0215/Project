package com.amazon.music.platform.playback.capabilities;

import com.amazon.music.platform.Outcome;
import com.amazon.music.platform.data.eligibility.AppStateEligibility;
import com.amazon.music.platform.data.eligibility.CapabilityExtensionsKt;
import com.amazon.music.platform.data.eligibility.ContentEligibility;
import com.amazon.music.platform.data.eligibility.ContentEligibilityQualifier;
import com.amazon.music.platform.data.eligibility.Eligibility;
import com.amazon.music.platform.data.eligibility.PlaymodeEligibility;
import com.amazon.music.platform.data.entity.EntityItem;
import com.amazon.music.platform.data.entity.EntityItemIdentifier;
import com.amazon.music.platform.data.identifiers.UserPlaylistId;
import com.amazon.music.platform.data.subscription.SubscriptionType;
import com.amazon.music.platform.featuregate.Feature;
import com.amazon.music.platform.playback.capabilities.error.EligibilityThrowable;
import com.amazon.music.platform.playback.capabilities.reduce.ReducibleBoolean;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* JADX INFO: compiled from: OnDemandCapability.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u00162\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003:\u0001\u0016B\u0005¢\u0006\u0002\u0010\u0004J,\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00020\n2\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\r0\f2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0014J<\u0010\u0010\u001a\u00020\u00022\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u00122\u0006\u0010\u0014\u001a\u00020\u00132\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\r0\f2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0002J\u001e\u0010\u0015\u001a\u00020\u00022\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u00122\u0006\u0010\u0014\u001a\u00020\u0013H\u0002R\u0014\u0010\u0005\u001a\u00020\u0006X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u0017"}, d2 = {"Lcom/amazon/music/platform/playback/capabilities/OnDemandCapability;", "Lcom/amazon/music/platform/playback/capabilities/PlayableEntityCapability;", "", "Lcom/amazon/music/platform/playback/capabilities/reduce/ReducibleBoolean;", "()V", "actionName", "", "getActionName", "()Ljava/lang/String;", "canPerformInternal", "Lcom/amazon/music/platform/Outcome;", "eligibilities", "", "Lcom/amazon/music/platform/data/eligibility/Eligibility;", "entityItem", "Lcom/amazon/music/platform/data/entity/EntityItem;", "checkOndemandStatus", "onDemandEntities", "", "Lcom/amazon/music/platform/data/entity/EntityItemIdentifier;", "contentEligibility", "containsOnDemandIdentifier", "Companion", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class OnDemandCapability extends PlayableEntityCapability<Boolean> implements ReducibleBoolean {
    private final String actionName = "ON_DEMAND";

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
        if (contentEligibility.has(ContentEligibilityQualifier.IsOwned.INSTANCE)) {
            return new Outcome.Success(Boolean.TRUE);
        }
        if (appStateEligibility.getActiveSubscription() == SubscriptionType.NONE) {
            return new Outcome.Failure(EligibilityThrowable.NoActiveSubscription.INSTANCE);
        }
        if (appStateEligibility.getActiveSubscription().isUnlimited$MusicPlatformCore_release() || appStateEligibility.getActiveSubscription() == SubscriptionType.PRIME) {
            return new Outcome.Success(Boolean.TRUE);
        }
        if (playmodeEligibility != null && !CapabilityExtensionsKt.shouldOverridePlaymodeEligibility(contentEligibility)) {
            return new Outcome.Success(Boolean.valueOf(playmodeEligibility.isOnDemand()));
        }
        if (!isContentUnavailable(eligibilities)) {
            Feature feature = appStateEligibility.getCapabilityFeatureOverrides().get(Reflection.getOrCreateKotlinClass(Boolean.class));
            if (feature != null) {
                zIsFeatureEnabled = CapabilityExtensionsKt.isFeatureEnabled(feature);
            } else {
                zIsFeatureEnabled = CapabilityExtensionsKt.isFeatureEnabled(appStateEligibility.getSupportsEligibilityFeature());
            }
            if (!zIsFeatureEnabled) {
                return new Outcome.Success(Boolean.FALSE);
            }
            boolean z = true;
            boolean z2 = appStateEligibility.getActiveSubscription() == SubscriptionType.SONIC_RUSH && contentEligibility.has(ContentEligibilityQualifier.IsSonicRushOnDemandPlayable.INSTANCE);
            boolean zCheckOndemandStatus = checkOndemandStatus(appStateEligibility.getOnDemandEntities(), contentEligibility.getContentEligibilityIdentifier(), eligibilities, entityItem);
            if (!z2 && !zCheckOndemandStatus) {
                z = false;
            }
            return new Outcome.Success(Boolean.valueOf(z));
        }
        return new Outcome.Failure(EligibilityThrowable.ContentUnavailable.INSTANCE);
    }

    private final boolean checkOndemandStatus(Set<EntityItemIdentifier> onDemandEntities, EntityItemIdentifier contentEligibility, Map<String, ? extends Eligibility> eligibilities, EntityItem entityItem) {
        if (contentEligibility.getId() instanceof UserPlaylistId) {
            return containsOnDemandIdentifier(onDemandEntities, contentEligibility) && Intrinsics.areEqual(PlayableEntityCapability.canPerform$default(new EligibleOnDemandUserPlaylistCapability(), eligibilities, entityItem, false, 4, null), new Outcome.Success(Boolean.TRUE));
        }
        return containsOnDemandIdentifier(onDemandEntities, contentEligibility);
    }

    private final boolean containsOnDemandIdentifier(Set<EntityItemIdentifier> onDemandEntities, EntityItemIdentifier contentEligibility) {
        Iterator<T> it = onDemandEntities.iterator();
        while (it.hasNext()) {
            if (Intrinsics.areEqual(((EntityItemIdentifier) it.next()).getId().getIdentifier(), contentEligibility.getId().getIdentifier())) {
                return true;
            }
        }
        return false;
    }
}
