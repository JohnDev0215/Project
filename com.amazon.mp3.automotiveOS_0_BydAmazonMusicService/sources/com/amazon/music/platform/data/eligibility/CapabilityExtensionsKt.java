package com.amazon.music.platform.data.eligibility;

import com.amazon.music.platform.AtomicMap;
import com.amazon.music.platform.Outcome;
import com.amazon.music.platform.data.eligibility.ContentEligibilityQualifier;
import com.amazon.music.platform.data.entity.EntityItem;
import com.amazon.music.platform.featuregate.Feature;
import com.amazon.music.platform.featuregate.HardcodedFeature;
import com.amazon.music.platform.featuregate.MpcFeatureKeys;
import com.amazon.music.platform.locator.Providers;
import com.amazon.music.platform.locator.ProvidersKt;
import com.amazon.music.platform.playback.capabilities.PlayableEntityCapability;
import com.amazon.music.platform.playback.capabilities.error.EligibilityThrowable;
import com.amazon.music.platform.providers.FeatureGateProvider;
import com.amazon.music.platform.providers.InterfaceProvider;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* JADX INFO: compiled from: CapabilityExtensions.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000J\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010%\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001aF\u0010\u000b\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\n\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u00012\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u00022\u0006\u0010\u0007\u001a\u00020\u00062\b\u0010\t\u001a\u0004\u0018\u00010\bH\u0000\u001a\u0018\u0010\u0010\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u000eH\u0000\u001a\u0010\u0010\u0013\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u0011H\u0000\u001a8\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u0015*\u0006\u0012\u0002\b\u00030\u00012\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u00022\u0006\u0010\u0014\u001a\u00020\u0006H\u0000\u001a\u0010\u0010\u0019\u001a\u00020\u00062\u0006\u0010\u0018\u001a\u00020\u0017H\u0000¨\u0006\u001a"}, d2 = {"T", "Lcom/amazon/music/platform/playback/capabilities/PlayableEntityCapability;", "", "", "Lcom/amazon/music/platform/data/eligibility/Eligibility;", "eligibilities", "", "shouldHandleError", "Lcom/amazon/music/platform/data/entity/EntityItem;", "entityItem", "Lcom/amazon/music/platform/Outcome$Failure;", "doValidation", "Lcom/amazon/music/platform/data/eligibility/AppStateEligibility;", "appStateEligibility", "Lcom/amazon/music/platform/featuregate/MpcFeatureKeys;", "mpcFeatureKey", "isMpcFeatureEnabled", "Lcom/amazon/music/platform/featuregate/Feature;", "feature", "isFeatureEnabled", "flagValue", "", "eligibilitiesWithOverriddenFeatureFlag", "Lcom/amazon/music/platform/data/eligibility/ContentEligibility;", "contentEligibility", "shouldOverridePlaymodeEligibility", "MusicPlatformCore_release"}, k = 2, mv = {1, 7, 1})
public final class CapabilityExtensionsKt {
    public static final <T> Outcome.Failure<T> doValidation(PlayableEntityCapability<? extends T> playableEntityCapability, Map<String, ? extends Eligibility> eligibilities, boolean z, EntityItem entityItem) {
        Intrinsics.checkNotNullParameter(playableEntityCapability, "<this>");
        Intrinsics.checkNotNullParameter(eligibilities, "eligibilities");
        if (!(eligibilities.get("APPLICATION_STATE") instanceof AppStateEligibility)) {
            return new Outcome.Failure<>(EligibilityThrowable.MissingAppStateEligibility.INSTANCE);
        }
        if (eligibilities.get("CONTENT") instanceof ContentEligibility) {
            return null;
        }
        return new Outcome.Failure<>(EligibilityThrowable.MissingContentEligibility.INSTANCE);
    }

    public static final boolean isMpcFeatureEnabled(AppStateEligibility appStateEligibility, MpcFeatureKeys mpcFeatureKey) {
        Intrinsics.checkNotNullParameter(appStateEligibility, "appStateEligibility");
        Intrinsics.checkNotNullParameter(mpcFeatureKey, "mpcFeatureKey");
        Feature feature = appStateEligibility.getMpcFeatures().get(mpcFeatureKey);
        if (feature != null) {
            return isFeatureEnabled(feature);
        }
        return false;
    }

    public static final boolean isFeatureEnabled(Feature feature) {
        Intrinsics.checkNotNullParameter(feature, "feature");
        AtomicMap<String, InterfaceProvider> providerInstances = Providers.INSTANCE.getProviderInstances();
        String originalClassName = ProvidersKt.getOriginalClassName(Reflection.getOrCreateKotlinClass(FeatureGateProvider.class));
        Intrinsics.checkNotNull(originalClassName);
        InterfaceProvider interfaceProvider = providerInstances.get(originalClassName);
        if (!(interfaceProvider instanceof FeatureGateProvider)) {
            interfaceProvider = null;
        }
        FeatureGateProvider featureGateProvider = (FeatureGateProvider) interfaceProvider;
        return featureGateProvider != null && featureGateProvider.isFeatureAvailable(feature);
    }

    public static final Map<String, Eligibility> eligibilitiesWithOverriddenFeatureFlag(PlayableEntityCapability<?> playableEntityCapability, Map<String, ? extends Eligibility> eligibilities, boolean z) {
        Intrinsics.checkNotNullParameter(playableEntityCapability, "<this>");
        Intrinsics.checkNotNullParameter(eligibilities, "eligibilities");
        Eligibility eligibility = eligibilities.get("CONTENT");
        Intrinsics.checkNotNull(eligibility, "null cannot be cast to non-null type com.amazon.music.platform.data.eligibility.ContentEligibility");
        ContentEligibility contentEligibility = (ContentEligibility) eligibility;
        Eligibility eligibility2 = eligibilities.get("PLAYMODES");
        PlaymodeEligibility playmodeEligibility = eligibility2 instanceof PlaymodeEligibility ? (PlaymodeEligibility) eligibility2 : null;
        Eligibility eligibility3 = eligibilities.get("APPLICATION_STATE");
        Intrinsics.checkNotNull(eligibility3, "null cannot be cast to non-null type com.amazon.music.platform.data.eligibility.AppStateEligibility");
        AppStateEligibility appStateEligibility = (AppStateEligibility) eligibility3;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("CONTENT", contentEligibility);
        if (playmodeEligibility != null) {
            linkedHashMap.put("PLAYMODES", playmodeEligibility);
        }
        linkedHashMap.put("APPLICATION_STATE", appStateEligibility.copy((159 & 1) != 0 ? appStateEligibility.isOffline : false, (159 & 2) != 0 ? appStateEligibility.isWakeWordEnabled : false, (159 & 4) != 0 ? appStateEligibility.isCasting : false, (159 & 8) != 0 ? appStateEligibility.activeSubscription : null, (159 & 16) != 0 ? appStateEligibility.onDemandEntities : null, (159 & 32) != 0 ? appStateEligibility.supportsEligibilityFeature : new HardcodedFeature(z), (159 & 64) != 0 ? appStateEligibility.capabilityFeatureOverrides : MapsKt__MapsKt.emptyMap(), (159 & 128) != 0 ? appStateEligibility.mpcFeatures : null));
        return linkedHashMap;
    }

    public static final boolean shouldOverridePlaymodeEligibility(ContentEligibility contentEligibility) {
        Intrinsics.checkNotNullParameter(contentEligibility, "contentEligibility");
        return contentEligibility.has(ContentEligibilityQualifier.IsOnDemandByService.INSTANCE);
    }
}
