package com.amazon.music.platform.data.eligibility;

import com.amazon.music.platform.data.entity.EntityItemIdentifier;
import com.amazon.music.platform.data.subscription.SubscriptionType;
import com.amazon.music.platform.featuregate.Feature;
import com.amazon.music.platform.featuregate.MpcFeatureKeys;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;

/* JADX INFO: compiled from: AppStateEligibility.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0018\b\u0086\b\u0018\u0000 .2\u00020\u0001:\u0001.Bo\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\b\u0012\u0006\u0010\f\u001a\u00020\u000b\u0012\u0018\b\u0002\u0010\u000f\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u000e\u0012\u0004\u0012\u00020\u000b0\r\u0012\u0014\b\u0002\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u000b0\r¢\u0006\u0004\b,\u0010-J{\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00022\b\b\u0002\u0010\u0007\u001a\u00020\u00062\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\b2\b\b\u0002\u0010\f\u001a\u00020\u000b2\u0018\b\u0002\u0010\u000f\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u000e\u0012\u0004\u0012\u00020\u000b0\r2\u0014\b\u0002\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u000b0\rHÆ\u0001J\t\u0010\u0014\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0015HÖ\u0001J\u0013\u0010\u0019\u001a\u00020\u00022\b\u0010\u0018\u001a\u0004\u0018\u00010\u0017HÖ\u0003R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u001a\u001a\u0004\b\u0003\u0010\u001bR\u0017\u0010\u0004\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u001a\u001a\u0004\b\u0004\u0010\u001bR\u0017\u0010\u0005\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u001a\u001a\u0004\b\u0005\u0010\u001bR\u0017\u0010\u0007\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\b\u0007\u0010\u001c\u001a\u0004\b\u001d\u0010\u001eR\u001d\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\b8\u0006¢\u0006\f\n\u0004\b\n\u0010\u001f\u001a\u0004\b \u0010!R\u0017\u0010\f\u001a\u00020\u000b8\u0006¢\u0006\f\n\u0004\b\f\u0010\"\u001a\u0004\b#\u0010$R'\u0010\u000f\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u000e\u0012\u0004\u0012\u00020\u000b0\r8\u0006¢\u0006\f\n\u0004\b\u000f\u0010%\u001a\u0004\b&\u0010'R#\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u000b0\r8\u0006¢\u0006\f\n\u0004\b\u0011\u0010%\u001a\u0004\b(\u0010'R\u0014\u0010+\u001a\u00020\u00138VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b)\u0010*¨\u0006/"}, d2 = {"Lcom/amazon/music/platform/data/eligibility/AppStateEligibility;", "Lcom/amazon/music/platform/data/eligibility/Eligibility;", "", "isOffline", "isWakeWordEnabled", "isCasting", "Lcom/amazon/music/platform/data/subscription/SubscriptionType;", "activeSubscription", "", "Lcom/amazon/music/platform/data/entity/EntityItemIdentifier;", "onDemandEntities", "Lcom/amazon/music/platform/featuregate/Feature;", "supportsEligibilityFeature", "", "Lkotlin/reflect/KClass;", "capabilityFeatureOverrides", "Lcom/amazon/music/platform/featuregate/MpcFeatureKeys;", "mpcFeatures", "copy", "", "toString", "", "hashCode", "", "other", "equals", "Z", "()Z", "Lcom/amazon/music/platform/data/subscription/SubscriptionType;", "getActiveSubscription", "()Lcom/amazon/music/platform/data/subscription/SubscriptionType;", "Ljava/util/Set;", "getOnDemandEntities", "()Ljava/util/Set;", "Lcom/amazon/music/platform/featuregate/Feature;", "getSupportsEligibilityFeature", "()Lcom/amazon/music/platform/featuregate/Feature;", "Ljava/util/Map;", "getCapabilityFeatureOverrides", "()Ljava/util/Map;", "getMpcFeatures", "getTYPE", "()Ljava/lang/String;", "TYPE", "<init>", "(ZZZLcom/amazon/music/platform/data/subscription/SubscriptionType;Ljava/util/Set;Lcom/amazon/music/platform/featuregate/Feature;Ljava/util/Map;Ljava/util/Map;)V", "Companion", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
public final /* data */ class AppStateEligibility implements Eligibility {
    private final SubscriptionType activeSubscription;
    private final Map<KClass<?>, Feature> capabilityFeatureOverrides;
    private final boolean isCasting;
    private final boolean isOffline;
    private final boolean isWakeWordEnabled;
    private final Map<MpcFeatureKeys, Feature> mpcFeatures;
    private final Set<EntityItemIdentifier> onDemandEntities;
    private final Feature supportsEligibilityFeature;

    public final AppStateEligibility copy(boolean isOffline, boolean isWakeWordEnabled, boolean isCasting, SubscriptionType activeSubscription, Set<EntityItemIdentifier> onDemandEntities, Feature supportsEligibilityFeature, Map<KClass<?>, ? extends Feature> capabilityFeatureOverrides, Map<MpcFeatureKeys, ? extends Feature> mpcFeatures) {
        Intrinsics.checkNotNullParameter(activeSubscription, "activeSubscription");
        Intrinsics.checkNotNullParameter(onDemandEntities, "onDemandEntities");
        Intrinsics.checkNotNullParameter(supportsEligibilityFeature, "supportsEligibilityFeature");
        Intrinsics.checkNotNullParameter(capabilityFeatureOverrides, "capabilityFeatureOverrides");
        Intrinsics.checkNotNullParameter(mpcFeatures, "mpcFeatures");
        return new AppStateEligibility(isOffline, isWakeWordEnabled, isCasting, activeSubscription, onDemandEntities, supportsEligibilityFeature, capabilityFeatureOverrides, mpcFeatures);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AppStateEligibility)) {
            return false;
        }
        AppStateEligibility appStateEligibility = (AppStateEligibility) other;
        return this.isOffline == appStateEligibility.isOffline && this.isWakeWordEnabled == appStateEligibility.isWakeWordEnabled && this.isCasting == appStateEligibility.isCasting && this.activeSubscription == appStateEligibility.activeSubscription && Intrinsics.areEqual(this.onDemandEntities, appStateEligibility.onDemandEntities) && Intrinsics.areEqual(this.supportsEligibilityFeature, appStateEligibility.supportsEligibilityFeature) && Intrinsics.areEqual(this.capabilityFeatureOverrides, appStateEligibility.capabilityFeatureOverrides) && Intrinsics.areEqual(this.mpcFeatures, appStateEligibility.mpcFeatures);
    }

    public String getTYPE() {
        return "APPLICATION_STATE";
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [int] */
    /* JADX WARN: Type inference failed for: r0v16 */
    /* JADX WARN: Type inference failed for: r0v17 */
    /* JADX WARN: Type inference failed for: r1v0 */
    /* JADX WARN: Type inference failed for: r1v1, types: [int] */
    /* JADX WARN: Type inference failed for: r1v12 */
    /* JADX WARN: Type inference failed for: r2v1, types: [int] */
    /* JADX WARN: Type inference failed for: r2v3 */
    /* JADX WARN: Type inference failed for: r2v4 */
    public int hashCode() {
        boolean z = this.isOffline;
        ?? r0 = z;
        if (z) {
            r0 = 1;
        }
        int i = r0 * 31;
        boolean z2 = this.isWakeWordEnabled;
        ?? r2 = z2;
        if (z2) {
            r2 = 1;
        }
        int i2 = (i + r2) * 31;
        boolean z3 = this.isCasting;
        return ((((((((((i2 + (z3 ? 1 : z3)) * 31) + this.activeSubscription.hashCode()) * 31) + this.onDemandEntities.hashCode()) * 31) + this.supportsEligibilityFeature.hashCode()) * 31) + this.capabilityFeatureOverrides.hashCode()) * 31) + this.mpcFeatures.hashCode();
    }

    public String toString() {
        return "AppStateEligibility(isOffline=" + this.isOffline + ", isWakeWordEnabled=" + this.isWakeWordEnabled + ", isCasting=" + this.isCasting + ", activeSubscription=" + this.activeSubscription + ", onDemandEntities=" + this.onDemandEntities + ", supportsEligibilityFeature=" + this.supportsEligibilityFeature + ", capabilityFeatureOverrides=" + this.capabilityFeatureOverrides + ", mpcFeatures=" + this.mpcFeatures + ')';
    }

    /* JADX WARN: Multi-variable type inference failed */
    public AppStateEligibility(boolean z, boolean z2, boolean z3, SubscriptionType activeSubscription, Set<EntityItemIdentifier> onDemandEntities, Feature supportsEligibilityFeature, Map<KClass<?>, ? extends Feature> capabilityFeatureOverrides, Map<MpcFeatureKeys, ? extends Feature> mpcFeatures) {
        Intrinsics.checkNotNullParameter(activeSubscription, "activeSubscription");
        Intrinsics.checkNotNullParameter(onDemandEntities, "onDemandEntities");
        Intrinsics.checkNotNullParameter(supportsEligibilityFeature, "supportsEligibilityFeature");
        Intrinsics.checkNotNullParameter(capabilityFeatureOverrides, "capabilityFeatureOverrides");
        Intrinsics.checkNotNullParameter(mpcFeatures, "mpcFeatures");
        this.isOffline = z;
        this.isWakeWordEnabled = z2;
        this.isCasting = z3;
        this.activeSubscription = activeSubscription;
        this.onDemandEntities = onDemandEntities;
        this.supportsEligibilityFeature = supportsEligibilityFeature;
        this.capabilityFeatureOverrides = capabilityFeatureOverrides;
        this.mpcFeatures = mpcFeatures;
    }

    /* JADX INFO: renamed from: isOffline, reason: from getter */
    public final boolean getIsOffline() {
        return this.isOffline;
    }

    /* JADX INFO: renamed from: isWakeWordEnabled, reason: from getter */
    public final boolean getIsWakeWordEnabled() {
        return this.isWakeWordEnabled;
    }

    /* JADX INFO: renamed from: isCasting, reason: from getter */
    public final boolean getIsCasting() {
        return this.isCasting;
    }

    public final SubscriptionType getActiveSubscription() {
        return this.activeSubscription;
    }

    public final Set<EntityItemIdentifier> getOnDemandEntities() {
        return this.onDemandEntities;
    }

    public final Feature getSupportsEligibilityFeature() {
        return this.supportsEligibilityFeature;
    }

    public final Map<KClass<?>, Feature> getCapabilityFeatureOverrides() {
        return this.capabilityFeatureOverrides;
    }

    public final Map<MpcFeatureKeys, Feature> getMpcFeatures() {
        return this.mpcFeatures;
    }
}
