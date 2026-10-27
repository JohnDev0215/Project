package com.amazon.music.metrics.mts.event.types;

import com.amazon.music.metrics.mts.event.ConnectivityInfoProvider;
import com.amazon.music.metrics.mts.event.types.core.MetricValueType;
import com.google.android.gms.common.internal.ImagesContract;

/* JADX INFO: loaded from: classes4.dex */
public enum PlaybackSource implements MetricValueType {
    WIFI("wifi"),
    WIRED("wired"),
    OTA("carrierName"),
    LOCAL(ImagesContract.LOCAL),
    UNKNOWN("");

    private final String mMetricValue;

    PlaybackSource(String str) {
        this.mMetricValue = str;
    }

    public static PlaybackSource getSource(ConnectivityInfoProvider connectivityInfoProvider) {
        return getSource(connectivityInfoProvider, CacheHitStatus.UNKNOWN);
    }

    public static PlaybackSource getSource(ConnectivityInfoProvider connectivityInfoProvider, CacheHitStatus cacheHitStatus) {
        if (cacheHitStatus == CacheHitStatus.FULL) {
            return LOCAL;
        }
        if (connectivityInfoProvider.isEthernetConnected()) {
            return WIRED;
        }
        if (connectivityInfoProvider.isWifiOnlyDevice() || connectivityInfoProvider.isWifiConnected()) {
            return WIFI;
        }
        return OTA;
    }

    @Override // com.amazon.music.metrics.mts.event.types.core.MetricValueType
    public String getMetricValue() {
        return this.mMetricValue;
    }

    /* JADX INFO: renamed from: com.amazon.music.metrics.mts.event.types.PlaybackSource$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$music$metrics$mts$event$types$PlaybackSource;

        static {
            int[] iArr = new int[PlaybackSource.values().length];
            $SwitchMap$com$amazon$music$metrics$mts$event$types$PlaybackSource = iArr;
            try {
                iArr[PlaybackSource.OTA.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    public String getMetricValue(ConnectivityInfoProvider connectivityInfoProvider) {
        if (AnonymousClass1.$SwitchMap$com$amazon$music$metrics$mts$event$types$PlaybackSource[ordinal()] == 1) {
            return connectivityInfoProvider.getCarrierName();
        }
        return this.mMetricValue;
    }
}
