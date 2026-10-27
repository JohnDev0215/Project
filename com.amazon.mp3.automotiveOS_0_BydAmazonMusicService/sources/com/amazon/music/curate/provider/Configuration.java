package com.amazon.music.curate.provider;

import androidx.core.app.NotificationCompat;
import com.amazon.music.curate.data.PlaylistService;
import com.amazon.music.curate.data.RecentsService;
import com.amazon.music.curate.data.database.PlaylistDB;
import com.amazon.music.curate.data.database.RecsCache;
import com.amazon.music.platform.providers.DeviceInfoProvider;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Configuration.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001Be\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0011¢\u0006\u0002\u0010\u0012J\u000b\u0010#\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010$\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010%\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u000b\u0010&\u001a\u0004\u0018\u00010\tHÆ\u0003J\u000b\u0010'\u001a\u0004\u0018\u00010\u000bHÆ\u0003J\u000b\u0010(\u001a\u0004\u0018\u00010\rHÆ\u0003J\u000b\u0010)\u001a\u0004\u0018\u00010\u000fHÆ\u0003J\u000b\u0010*\u001a\u0004\u0018\u00010\u0011HÆ\u0003Ji\u0010+\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0011HÆ\u0001J\u0013\u0010,\u001a\u00020-2\b\u0010.\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010/\u001a\u000200HÖ\u0001J\t\u00101\u001a\u000202HÖ\u0001R\u0013\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0013\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0013\u0010\u0010\u001a\u0004\u0018\u00010\u0011¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0013\u0010\f\u001a\u0004\u0018\u00010\r¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0013\u0010\u000e\u001a\u0004\u0018\u00010\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"¨\u00063"}, d2 = {"Lcom/amazon/music/curate/provider/Configuration;", "", "overflowMenuProvider", "Lcom/amazon/music/curate/provider/OverflowMenuProvider;", "featureFlagProvider", "Lcom/amazon/music/curate/provider/FeatureFlagProvider;", "subscriptionProvider", "Lcom/amazon/music/curate/provider/SubscriptionProvider;", "deviceInfoProvider", "Lcom/amazon/music/platform/providers/DeviceInfoProvider;", "cache", "Lcom/amazon/music/curate/data/database/PlaylistDB;", "recsCache", "Lcom/amazon/music/curate/data/database/RecsCache;", NotificationCompat.CATEGORY_SERVICE, "Lcom/amazon/music/curate/data/PlaylistService;", "recentsService", "Lcom/amazon/music/curate/data/RecentsService;", "(Lcom/amazon/music/curate/provider/OverflowMenuProvider;Lcom/amazon/music/curate/provider/FeatureFlagProvider;Lcom/amazon/music/curate/provider/SubscriptionProvider;Lcom/amazon/music/platform/providers/DeviceInfoProvider;Lcom/amazon/music/curate/data/database/PlaylistDB;Lcom/amazon/music/curate/data/database/RecsCache;Lcom/amazon/music/curate/data/PlaylistService;Lcom/amazon/music/curate/data/RecentsService;)V", "getCache", "()Lcom/amazon/music/curate/data/database/PlaylistDB;", "getDeviceInfoProvider", "()Lcom/amazon/music/platform/providers/DeviceInfoProvider;", "getFeatureFlagProvider", "()Lcom/amazon/music/curate/provider/FeatureFlagProvider;", "getOverflowMenuProvider", "()Lcom/amazon/music/curate/provider/OverflowMenuProvider;", "getRecentsService", "()Lcom/amazon/music/curate/data/RecentsService;", "getRecsCache", "()Lcom/amazon/music/curate/data/database/RecsCache;", "getService", "()Lcom/amazon/music/curate/data/PlaylistService;", "getSubscriptionProvider", "()Lcom/amazon/music/curate/provider/SubscriptionProvider;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", "", "other", "hashCode", "", "toString", "", "DMMCurateExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final /* data */ class Configuration {
    private final PlaylistDB cache;
    private final DeviceInfoProvider deviceInfoProvider;
    private final FeatureFlagProvider featureFlagProvider;
    private final OverflowMenuProvider overflowMenuProvider;
    private final RecentsService recentsService;
    private final RecsCache recsCache;
    private final PlaylistService service;
    private final SubscriptionProvider subscriptionProvider;

    public Configuration() {
        this(null, null, null, null, null, null, null, null, 255, null);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final OverflowMenuProvider getOverflowMenuProvider() {
        return this.overflowMenuProvider;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final FeatureFlagProvider getFeatureFlagProvider() {
        return this.featureFlagProvider;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final SubscriptionProvider getSubscriptionProvider() {
        return this.subscriptionProvider;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final DeviceInfoProvider getDeviceInfoProvider() {
        return this.deviceInfoProvider;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final PlaylistDB getCache() {
        return this.cache;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final RecsCache getRecsCache() {
        return this.recsCache;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final PlaylistService getService() {
        return this.service;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final RecentsService getRecentsService() {
        return this.recentsService;
    }

    public final Configuration copy(OverflowMenuProvider overflowMenuProvider, FeatureFlagProvider featureFlagProvider, SubscriptionProvider subscriptionProvider, DeviceInfoProvider deviceInfoProvider, PlaylistDB cache, RecsCache recsCache, PlaylistService service, RecentsService recentsService) {
        return new Configuration(overflowMenuProvider, featureFlagProvider, subscriptionProvider, deviceInfoProvider, cache, recsCache, service, recentsService);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Configuration)) {
            return false;
        }
        Configuration configuration = (Configuration) other;
        return Intrinsics.areEqual(this.overflowMenuProvider, configuration.overflowMenuProvider) && Intrinsics.areEqual(this.featureFlagProvider, configuration.featureFlagProvider) && Intrinsics.areEqual(this.subscriptionProvider, configuration.subscriptionProvider) && Intrinsics.areEqual(this.deviceInfoProvider, configuration.deviceInfoProvider) && Intrinsics.areEqual(this.cache, configuration.cache) && Intrinsics.areEqual(this.recsCache, configuration.recsCache) && Intrinsics.areEqual(this.service, configuration.service) && Intrinsics.areEqual(this.recentsService, configuration.recentsService);
    }

    public int hashCode() {
        OverflowMenuProvider overflowMenuProvider = this.overflowMenuProvider;
        int iHashCode = (overflowMenuProvider == null ? 0 : overflowMenuProvider.hashCode()) * 31;
        FeatureFlagProvider featureFlagProvider = this.featureFlagProvider;
        int iHashCode2 = (iHashCode + (featureFlagProvider == null ? 0 : featureFlagProvider.hashCode())) * 31;
        SubscriptionProvider subscriptionProvider = this.subscriptionProvider;
        int iHashCode3 = (iHashCode2 + (subscriptionProvider == null ? 0 : subscriptionProvider.hashCode())) * 31;
        DeviceInfoProvider deviceInfoProvider = this.deviceInfoProvider;
        int iHashCode4 = (iHashCode3 + (deviceInfoProvider == null ? 0 : deviceInfoProvider.hashCode())) * 31;
        PlaylistDB playlistDB = this.cache;
        int iHashCode5 = (iHashCode4 + (playlistDB == null ? 0 : playlistDB.hashCode())) * 31;
        RecsCache recsCache = this.recsCache;
        int iHashCode6 = (iHashCode5 + (recsCache == null ? 0 : recsCache.hashCode())) * 31;
        PlaylistService playlistService = this.service;
        int iHashCode7 = (iHashCode6 + (playlistService == null ? 0 : playlistService.hashCode())) * 31;
        RecentsService recentsService = this.recentsService;
        return iHashCode7 + (recentsService != null ? recentsService.hashCode() : 0);
    }

    public String toString() {
        return "Configuration(overflowMenuProvider=" + this.overflowMenuProvider + ", featureFlagProvider=" + this.featureFlagProvider + ", subscriptionProvider=" + this.subscriptionProvider + ", deviceInfoProvider=" + this.deviceInfoProvider + ", cache=" + this.cache + ", recsCache=" + this.recsCache + ", service=" + this.service + ", recentsService=" + this.recentsService + ')';
    }

    public Configuration(OverflowMenuProvider overflowMenuProvider, FeatureFlagProvider featureFlagProvider, SubscriptionProvider subscriptionProvider, DeviceInfoProvider deviceInfoProvider, PlaylistDB playlistDB, RecsCache recsCache, PlaylistService playlistService, RecentsService recentsService) {
        this.overflowMenuProvider = overflowMenuProvider;
        this.featureFlagProvider = featureFlagProvider;
        this.subscriptionProvider = subscriptionProvider;
        this.deviceInfoProvider = deviceInfoProvider;
        this.cache = playlistDB;
        this.recsCache = recsCache;
        this.service = playlistService;
        this.recentsService = recentsService;
    }

    public /* synthetic */ Configuration(OverflowMenuProvider overflowMenuProvider, FeatureFlagProvider featureFlagProvider, SubscriptionProvider subscriptionProvider, DeviceInfoProvider deviceInfoProvider, PlaylistDB playlistDB, RecsCache recsCache, PlaylistService playlistService, RecentsService recentsService, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : overflowMenuProvider, (i & 2) != 0 ? null : featureFlagProvider, (i & 4) != 0 ? null : subscriptionProvider, (i & 8) != 0 ? null : deviceInfoProvider, (i & 16) != 0 ? null : playlistDB, (i & 32) != 0 ? null : recsCache, (i & 64) != 0 ? null : playlistService, (i & 128) == 0 ? recentsService : null);
    }

    public final OverflowMenuProvider getOverflowMenuProvider() {
        return this.overflowMenuProvider;
    }

    public final FeatureFlagProvider getFeatureFlagProvider() {
        return this.featureFlagProvider;
    }

    public final SubscriptionProvider getSubscriptionProvider() {
        return this.subscriptionProvider;
    }

    public final DeviceInfoProvider getDeviceInfoProvider() {
        return this.deviceInfoProvider;
    }

    public final PlaylistDB getCache() {
        return this.cache;
    }

    public final RecsCache getRecsCache() {
        return this.recsCache;
    }

    public final PlaylistService getService() {
        return this.service;
    }

    public final RecentsService getRecentsService() {
        return this.recentsService;
    }
}
