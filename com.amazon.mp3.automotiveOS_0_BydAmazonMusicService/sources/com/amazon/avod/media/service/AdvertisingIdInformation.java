package com.amazon.avod.media.service;

import android.os.Build;
import com.amazon.avod.media.ads.AdvertisingIdCollector;
import com.amazon.avod.playbackresource.PlaybackResourceConfig;
import com.amazon.avod.prs.AdditionalParams;
import com.amazon.avod.util.DLog;
import com.amazon.music.inappmessaging.external.DeviceInformation;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableMap;
import java.util.Map;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class AdvertisingIdInformation implements AdditionalParams {
    private final AdvertisingIdCollector mAdvertisingIdCollector;
    private final long mAdvertisingIdCollectorTimeoutInMillis;
    private final boolean mIsVideoFrameRateOverrideSupported;
    private final String mPlaybackToken;
    private final boolean mShouldAdvertisingIdCollectorUseStaleData;
    private final boolean mShouldAppendChipsetInfo;
    private final boolean mShouldAppendManufacturerInfo;
    private final boolean mShouldAppendModelInfo;
    private final boolean mShouldAppendOSVersionInfo;
    private final boolean mShouldIncludeBuildParams;

    @Override // com.amazon.avod.prs.AdditionalParams
    public boolean hasQueryStringParams() {
        return true;
    }

    public AdvertisingIdInformation(@Nullable AdvertisingIdCollector advertisingIdCollector, boolean z, long j, boolean z2, @Nullable String str) {
        this(advertisingIdCollector, z, j, true, PlaybackResourceConfig.getInstance(), z2, str);
    }

    public AdvertisingIdInformation(@Nullable AdvertisingIdCollector advertisingIdCollector, boolean z, long j, boolean z2, boolean z3, @Nullable String str) {
        this(advertisingIdCollector, z, j, z2, PlaybackResourceConfig.getInstance(), z3, str);
    }

    AdvertisingIdInformation(@Nullable AdvertisingIdCollector advertisingIdCollector, boolean z, long j, boolean z2, @Nonnull PlaybackResourceConfig playbackResourceConfig, boolean z3, @Nullable String str) {
        this.mAdvertisingIdCollector = advertisingIdCollector;
        this.mShouldAdvertisingIdCollectorUseStaleData = z;
        this.mAdvertisingIdCollectorTimeoutInMillis = j;
        Preconditions.checkNotNull(playbackResourceConfig, "playbackResourceConfig");
        this.mShouldAppendManufacturerInfo = playbackResourceConfig.shouldAppendManufacturerInfo();
        this.mShouldAppendModelInfo = playbackResourceConfig.shouldAppendModelInfo();
        this.mShouldAppendChipsetInfo = playbackResourceConfig.shouldAppendChipsetInfo();
        this.mShouldAppendOSVersionInfo = playbackResourceConfig.shouldAppendOSVersionInfo();
        this.mShouldIncludeBuildParams = z2;
        this.mIsVideoFrameRateOverrideSupported = z3;
        this.mPlaybackToken = str;
    }

    @Override // com.amazon.avod.prs.AdditionalParams
    public boolean hasPostBodyParams() {
        return !Strings.isNullOrEmpty(this.mPlaybackToken);
    }

    @Override // com.amazon.avod.prs.AdditionalParams
    @Nonnull
    public Map<String, String> getPostBodyParams() {
        ImmutableMap.Builder builder = ImmutableMap.builder();
        if (!Strings.isNullOrEmpty(this.mPlaybackToken)) {
            builder.put("playbackToken", this.mPlaybackToken);
        }
        return builder.build();
    }

    @Override // com.amazon.avod.prs.AdditionalParams
    @Nonnull
    public Map<String, String> getQueryStringParams() {
        ImmutableMap.Builder builder = ImmutableMap.builder();
        AdvertisingIdCollector advertisingIdCollector = this.mAdvertisingIdCollector;
        if (advertisingIdCollector != null) {
            AdvertisingIdCollector.AdvertisingInfo advertisingInfo = advertisingIdCollector.get(this.mAdvertisingIdCollectorTimeoutInMillis, this.mShouldAdvertisingIdCollectorUseStaleData);
            builder.put("advertisingID", Strings.nullToEmpty(advertisingInfo.getAdId())).put("optOutAdTracking", String.valueOf(advertisingInfo.isOptOut()));
        }
        if (!this.mShouldIncludeBuildParams) {
            return builder.build();
        }
        if (this.mShouldAppendManufacturerInfo) {
            builder.put("manufacturer", Build.MANUFACTURER);
        }
        if (this.mShouldAppendModelInfo) {
            builder.put(DeviceInformation.PARAMS_DEVICE_MODEL, Build.MODEL);
        }
        if (this.mShouldAppendChipsetInfo) {
            builder.put("deviceChipset", Build.BOARD);
        }
        if (this.mShouldAppendOSVersionInfo) {
            builder.put("operatingSystemVersion", Build.FINGERPRINT);
        }
        if (this.mIsVideoFrameRateOverrideSupported) {
            builder.put("deviceFrameRateOverride", PlaybackResourceServiceConstants$VideoFrameRate.High.toString());
        }
        DLog.devf("AdvertisingIdInformation: Adding https DEVICE_PROTOCOL_OVERRIDE");
        builder.put("deviceProtocolOverride", "Https");
        return builder.build();
    }
}
