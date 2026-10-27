package com.amazon.avod.playback.capability;

import com.amazon.avod.content.dash.quality.heuristic.HeuristicAlgorithm;
import com.amazon.avod.media.framework.config.profiles.ThirdPartyConfigurationProfile;
import com.amazon.avod.media.playback.ContentType;
import com.amazon.avod.util.DLog;
import com.amazon.avod.util.DataUnit;
import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.RateLimiter;
import java.util.Locale;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class DeviceResources {
    private ContentType mContentType;
    private int mDelayBehindLiveHeadInSeconds;
    private double mDesiredQualityScore;
    private HeuristicAlgorithm mHeuristicAlgorithm;
    private int mMinimumUpdatePeriodInSeconds;
    private int mNativeHeapAvailableSizeInBytes;
    private int mNativeHeapMaxSizeInBytes;
    private int mNativeHeapUsedSizeInBytes;
    private final ThirdPartyConfigurationProfile mProfile;
    private final RateLimiter mRateLimiter;
    private String mReviewProgressStrategy;
    private final Runtime mRuntime;
    private String mTimeoutStrategy;
    private String mTunneledPlaybackInfo;

    public ContentType getContentType() {
        return this.mContentType;
    }

    public void setContentType(ContentType contentType) {
        this.mContentType = contentType;
    }

    public int getMinimumUpdatePeriodInSeconds() {
        return this.mMinimumUpdatePeriodInSeconds;
    }

    public void setMinimumUpdatePeriodInSeconds(int i) {
        this.mMinimumUpdatePeriodInSeconds = i;
    }

    public int getDelayBehindLiveHeadInSeconds() {
        return this.mDelayBehindLiveHeadInSeconds;
    }

    public void setDelayBehindLiveHeadInSeconds(int i) {
        this.mDelayBehindLiveHeadInSeconds = i;
    }

    public int getNativeHeapMaxSizeInBytes() {
        return this.mNativeHeapMaxSizeInBytes;
    }

    public void setNativeHeapMaxSizeInBytes(int i) {
        this.mNativeHeapMaxSizeInBytes = i;
    }

    public int getNativeHeapUsedSizeInBytes() {
        return this.mNativeHeapUsedSizeInBytes;
    }

    public void setNativeHeapUsedSizeInBytes(int i) {
        this.mNativeHeapUsedSizeInBytes = i;
        if (this.mRateLimiter.tryAcquire()) {
            logPlayerMemoryStats();
        }
    }

    public int getNativeHeapAvailableSizeInBytes() {
        return this.mNativeHeapAvailableSizeInBytes;
    }

    public void setNativeHeapAvailableSizeInBytes(int i) {
        this.mNativeHeapAvailableSizeInBytes = i;
    }

    @Nullable
    public String getDeviceProfileName() {
        return this.mProfile.getDeviceProfileName();
    }

    private static class Holder {
        private static DeviceResources INSTANCE = new DeviceResources();

        private Holder() {
        }
    }

    public static DeviceResources getInstance() {
        return Holder.INSTANCE;
    }

    private DeviceResources() {
        this(Runtime.getRuntime(), RateLimiter.create(0.1d), ThirdPartyConfigurationProfile.getInstance());
    }

    DeviceResources(Runtime runtime, @Nonnull RateLimiter rateLimiter, @Nonnull ThirdPartyConfigurationProfile thirdPartyConfigurationProfile) {
        this.mContentType = ContentType.Feature;
        this.mTimeoutStrategy = "";
        this.mReviewProgressStrategy = "";
        this.mTunneledPlaybackInfo = "NA";
        this.mDesiredQualityScore = 0.0d;
        this.mRuntime = runtime;
        this.mDelayBehindLiveHeadInSeconds = 0;
        this.mMinimumUpdatePeriodInSeconds = 0;
        this.mRateLimiter = (RateLimiter) Preconditions.checkNotNull(rateLimiter, "rateLimiter");
        this.mProfile = (ThirdPartyConfigurationProfile) Preconditions.checkNotNull(thirdPartyConfigurationProfile, "thirdPartyConfigurationProfile");
    }

    private void logPlayerMemoryStats() {
        Locale locale = Locale.US;
        DataUnit dataUnit = DataUnit.BYTES;
        DLog.devf("PlayerMemoryStats: %s | %s", String.format(locale, "Java heap: %.2f MB / %.2f MB", Float.valueOf(dataUnit.toMegaBytes(this.mRuntime.totalMemory() - this.mRuntime.freeMemory())), Float.valueOf(dataUnit.toMegaBytes(this.mRuntime.maxMemory()))), String.format(locale, "Native heap: %.2f MB / %.2f MB", Float.valueOf(dataUnit.toMegaBytes(this.mNativeHeapUsedSizeInBytes)), Float.valueOf(dataUnit.toMegaBytes(this.mNativeHeapMaxSizeInBytes))));
    }

    public float getCurrentApplicationMemoryUsageInMB() {
        return DataUnit.BYTES.toMegaBytes(this.mRuntime.totalMemory() - this.mRuntime.freeMemory());
    }

    public float getApplicationMaxHeapSizeMaxHeapSizeInMB() {
        return DataUnit.BYTES.toMegaBytes(this.mRuntime.maxMemory());
    }

    @Nullable
    public String getMemoryUsageAsJsonString() {
        JSONObject jSONObject = new JSONObject();
        try {
            appendMemoryInformationToJsonObject(jSONObject);
            return jSONObject.toString();
        } catch (JSONException e) {
            DLog.warnf("Could not create create json format of the memory portion of device resources: %s", e);
            return null;
        }
    }

    public JSONObject appendMemoryInformationToJsonObject(@Nonnull JSONObject jSONObject) throws JSONException {
        Preconditions.checkNotNull(jSONObject, "jsonObj");
        Locale locale = Locale.US;
        jSONObject.put("javaHeap", String.format(locale, "%.2f(%.0f) MB", Float.valueOf(getCurrentApplicationMemoryUsageInMB()), Float.valueOf(getApplicationMaxHeapSizeMaxHeapSizeInMB())));
        DataUnit dataUnit = DataUnit.BYTES;
        jSONObject.put("nativeHeap", String.format(locale, "%.2f(%.0f) MB", Float.valueOf(dataUnit.toMegaBytes(getNativeHeapUsedSizeInBytes())), Float.valueOf(dataUnit.toMegaBytes(getNativeHeapMaxSizeInBytes()))));
        return jSONObject;
    }

    @Nullable
    public HeuristicAlgorithm getHeuristicAlgorithm() {
        return this.mHeuristicAlgorithm;
    }

    public void setHeuristicAlgorithm(@Nonnull HeuristicAlgorithm heuristicAlgorithm) {
        this.mHeuristicAlgorithm = heuristicAlgorithm;
    }

    @Nonnull
    public String getTimeoutStrategy() {
        return this.mTimeoutStrategy;
    }

    public void setTimeoutStrategy(@Nonnull String str) {
        Preconditions.checkNotNull(str, "timeoutStrategy");
        this.mTimeoutStrategy = str;
    }

    @Nonnull
    public String getReviewProgressStrategy() {
        return this.mReviewProgressStrategy;
    }

    public void setReviewProgressStrategy(@Nonnull String str) {
        Preconditions.checkNotNull(str, "reviewProgressStrategy");
        this.mReviewProgressStrategy = str;
    }

    @Nonnull
    public String getTunneledPlaybackInfo() {
        return this.mTunneledPlaybackInfo;
    }

    public void setTunneledPlaybackInfo(@Nonnull String str) {
        Preconditions.checkNotNull(str, "tunneledPlaybackInfo");
        this.mTunneledPlaybackInfo = str;
    }

    public double getDesiredQualityScore() {
        return this.mDesiredQualityScore;
    }

    public void setDesiredQualityScore(double d) {
        this.mDesiredQualityScore = d;
    }
}
