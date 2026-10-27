package com.amazon.avod.content.smoothstream.manifest.acquisition;

import amazon.android.config.ConfigurationValue;
import android.os.Environment;
import com.amazon.avod.experiments.MobileWeblab;
import com.amazon.avod.experiments.PlaybackWeblabs;
import com.amazon.avod.experiments.WeblabTreatment;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.framework.config.MediaConfigBase;
import com.amazon.avod.media.framework.config.TimeConfigurationValue;
import com.amazon.avod.util.BetaConfigProvider;
import com.amazon.avod.util.DLog;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes2.dex */
public class ManifestCapturerConfig extends MediaConfigBase {
    public static final ManifestCapturerConfig INSTANCE = new ManifestCapturerConfig();
    private final ConfigurationValue<Boolean> mContinuousUploadEnabledForDAI;
    private final ConfigurationValue<Boolean> mContinuousUploadEnabledForLiveLinear;
    private final ConfigurationValue<Boolean> mContinuousUploadEnabledForMultiPeriod;
    private final ConfigurationValue<Integer> mErrorSeverityLevelToUploadManifest;
    private final ConfigurationValue<Boolean> mExceptionUploadEnabledForAd;
    private final ConfigurationValue<Boolean> mExceptionUploadEnabledForDAI;
    private final ConfigurationValue<Boolean> mExceptionUploadEnabledForLiveLinear;
    private final ConfigurationValue<Boolean> mExceptionUploadEnabledForMultiPeriod;
    private final ConfigurationValue<String> mExternalStorageDownloadPath;
    private final ConfigurationValue<String> mFirehoseLogsDatastreamName;
    private final ConfigurationValue<String> mFirehoseManifestsDatastreamName;
    private final ConfigurationValue<Boolean> mIsExternalStorageDownloadEnabled;
    private final ConfigurationValue<Boolean> mIsUploadManifestsWhenBufferEnabled;
    private final ConfigurationValue<Boolean> mIsUploadManifestsWhenFatalEnabled;
    private final ConfigurationValue<String> mKinesisAccessKey;
    private final ConfigurationValue<String> mKinesisAccessSecret;
    private final TimeConfigurationValue mKinesisPostTimeWindow;
    private final ConfigurationValue<Boolean> mKinesisServiceEnabled;
    private final TimeConfigurationValue mManifestCaptureTimeWindow;
    private final ConfigurationValue<Integer> mMaxBytesPerKinesisRecord;
    private final ConfigurationValue<String> mPostManifestInputFolder;
    private final ConfigurationValue<Boolean> mPostManifestReplayEnabled;
    private final ConfigurationValue<Boolean> mShouldCaptureCSAIManifests;
    private final ConfigurationValue<Boolean> mShouldCaptureSSAIManifests;
    private final ConfigurationValue<Boolean> mShouldCleanupExternalStorageDownloadPath;
    private final ConfigurationValue<Boolean> mShouldEnableAdManifestCapturer;
    private final ConfigurationValue<Boolean> mShouldEnableAdManifestCapturerInBeta;
    private final ConfigurationValue<Boolean> mShouldUploadAllAdManifests;
    private final ConfigurationValue<Boolean> mUploadAllManifests;
    private final ConfigurationValue<Integer> mMaxHistorialManifestsToUpload = newIntConfigValue("manifest_maxHistorialUploads", 2);
    private final ConfigurationValue<Boolean> mIsManifestCaptureEnabled = newBooleanConfigValue("manifest_isCaptureEnabled_3", true);
    private final ConfigurationValue<Boolean> mIsCrashboardsUploadEnabled = newBooleanConfigValue("manifest_isCrashboardsUploadEnabled_2", false);
    private final ConfigurationValue<Boolean> mIsKinesisUploadEnabled = newBooleanConfigValue("manifest_isKinesisUploadEnabled", true);

    private ManifestCapturerConfig() {
        TimeSpan timeSpanFromSeconds = TimeSpan.fromSeconds(270.0d);
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        this.mManifestCaptureTimeWindow = newTimeConfigurationValue("manifest_captureTimeWindowMillis", timeSpanFromSeconds, timeUnit);
        this.mIsExternalStorageDownloadEnabled = newBooleanConfigValue("manifest_isExternalStorageDownloadEnabled", false);
        this.mUploadAllManifests = newBooleanConfigValue("manifest_uploadAllManifests", true);
        this.mExternalStorageDownloadPath = newStringConfigValue("manifests_externalStorageDownloadPath", Environment.getExternalStorageDirectory().getAbsolutePath() + "/tmp/com.amazon.avod/saved_manifests/");
        this.mFirehoseManifestsDatastreamName = newStringConfigValue("manifest_firehoseManifestsDatastreamName", "postmanifest-manifests-west-2");
        this.mFirehoseLogsDatastreamName = newStringConfigValue("manifest_firehoseLogsDatastreamName", "postmanifest-logs");
        this.mPostManifestReplayEnabled = newBooleanConfigValue("manifest_manifestReplayEnabled", false);
        this.mPostManifestInputFolder = newStringConfigValue("manifest_postManifestInputFolder", Environment.getExternalStorageDirectory().getAbsolutePath() + "/tmp/com.amazon.avod/postmanifest");
        this.mKinesisServiceEnabled = newBooleanConfigValue("manifest_KinesisServiceEnabled", true);
        this.mKinesisAccessKey = newStringConfigValue("manifest_KinesisServiceAccessKey", "");
        this.mKinesisAccessSecret = newStringConfigValue("manifest_KinesisServiceAccessSecret", "");
        this.mMaxBytesPerKinesisRecord = newIntConfigValue("manifest_MaxBytesPerKinesisRecord", 512000);
        this.mContinuousUploadEnabledForDAI = newBooleanConfigValue("manifest_ContinuousUploadEnabledForDAI", true);
        this.mExceptionUploadEnabledForDAI = newBooleanConfigValue("manifest_ExceptionUploadEnabledForDAI", true);
        this.mContinuousUploadEnabledForMultiPeriod = newBooleanConfigValue("manifest_ContinuousUploadEnabledForMultiPeriod", false);
        this.mExceptionUploadEnabledForMultiPeriod = newBooleanConfigValue("manifest_ExceptionUploadEnabledForMultiPeriod", true);
        this.mContinuousUploadEnabledForLiveLinear = newBooleanConfigValue("manifest_ContinuousUploadEnabledForLiveLinear", false);
        this.mExceptionUploadEnabledForLiveLinear = newBooleanConfigValue("manifest_ExceptionUploadEnabledForLiveLinear", true);
        this.mKinesisPostTimeWindow = newTimeConfigurationValue("manifest_KinesisPostTimeWindowMillis", TimeSpan.fromSeconds(60.0d), timeUnit);
        this.mIsUploadManifestsWhenBufferEnabled = newBooleanConfigValue("manifest_isUploadManifestsWhenBufferEnabled", false);
        this.mErrorSeverityLevelToUploadManifest = newIntConfigValue("manifest_errorSeverityLevelToUploadManifest", 3);
        this.mIsUploadManifestsWhenFatalEnabled = newBooleanConfigValue("manifest_isUploadManifestsWhenFatalEnabled", false);
        this.mShouldEnableAdManifestCapturer = newBooleanConfigValue("manifest_shouldEnableAdManifestCapturer", false);
        this.mShouldEnableAdManifestCapturerInBeta = newBooleanConfigValue("manifest_shouldEnableAdManifestCapturerInBeta", true);
        this.mShouldCaptureSSAIManifests = newBooleanConfigValue("manifest_shouldCaptureSSAIManifests", true);
        this.mShouldCaptureCSAIManifests = newBooleanConfigValue("manifest_shouldCaptureCSAIManifests", false);
        this.mExceptionUploadEnabledForAd = newBooleanConfigValue("manifest_exceptionUploadEnabledForAd", true);
        this.mShouldUploadAllAdManifests = newBooleanConfigValue("manifest_shouldUploadAllAdManifests", false);
        this.mShouldCleanupExternalStorageDownloadPath = newBooleanConfigValue("manifest_shouldCleanupExternalStorageDownloadPath", true);
    }

    public int getMaxHistoricalManifestsToUpload() {
        return this.mMaxHistorialManifestsToUpload.getValue().intValue();
    }

    public boolean isManifestCaptureEnabled() {
        return this.mIsManifestCaptureEnabled.getValue().booleanValue();
    }

    public boolean isCrashboardsUploadEnabled() {
        return this.mIsCrashboardsUploadEnabled.getValue().booleanValue();
    }

    public TimeSpan getManifestCaptureTimeWindow() {
        return this.mManifestCaptureTimeWindow.getValue();
    }

    public boolean isKinesisUploadEnabled() {
        return this.mIsKinesisUploadEnabled.getValue().booleanValue();
    }

    public boolean isExternalStorageDownloadEnabled() {
        return this.mIsExternalStorageDownloadEnabled.getValue().booleanValue();
    }

    public boolean uploadAllManifests() {
        return this.mUploadAllManifests.getValue().booleanValue();
    }

    public String getExternalStorageDownloadPath() {
        return this.mExternalStorageDownloadPath.getValue();
    }

    public boolean getManifestReplayEnabled() {
        return this.mPostManifestReplayEnabled.getValue().booleanValue();
    }

    public String getPostManifestInputFolder() {
        return this.mPostManifestInputFolder.getValue();
    }

    public int getMaxBytesPerKinesisRecord() {
        return this.mMaxBytesPerKinesisRecord.getValue().intValue();
    }

    public boolean getContinuousUploadEnabledForDAI() {
        return this.mContinuousUploadEnabledForDAI.getValue().booleanValue();
    }

    public boolean isExceptionUploadEnabled(boolean z, boolean z2, boolean z3, boolean z4) {
        if (BetaConfigProvider.getInstance().provideBetaConfig().isBeta()) {
            return true;
        }
        return (getExceptionUploadEnabledForLiveLinear() && z) || (getExceptionUploadEnabledForMultiPeriod() && z2) || (getExceptionUploadEnabledForDAI() && z3) || (getExceptionUploadEnabledForAd() && z4);
    }

    public boolean isContinuousUploadEnabled(boolean z, boolean z2, boolean z3) {
        if (BetaConfigProvider.getInstance().provideBetaConfig().isBeta()) {
            return true;
        }
        return (getContinuousUploadEnabledForLiveLinear() && z) || (getContinuousUploadEnabledForMultiPeriod() && z2) || (getContinuousUploadEnabledForDAI() && z3);
    }

    public boolean getExceptionUploadEnabledForDAI() {
        return this.mExceptionUploadEnabledForDAI.getValue().booleanValue();
    }

    public boolean getContinuousUploadEnabledForMultiPeriod() {
        return this.mContinuousUploadEnabledForMultiPeriod.getValue().booleanValue();
    }

    public boolean getExceptionUploadEnabledForMultiPeriod() {
        return this.mExceptionUploadEnabledForMultiPeriod.getValue().booleanValue();
    }

    public boolean getContinuousUploadEnabledForLiveLinear() {
        return this.mContinuousUploadEnabledForLiveLinear.getValue().booleanValue();
    }

    public boolean getExceptionUploadEnabledForLiveLinear() {
        return this.mExceptionUploadEnabledForLiveLinear.getValue().booleanValue();
    }

    public boolean isUploadManifestsWhenBufferEnabledViaConfig() {
        return this.mIsUploadManifestsWhenBufferEnabled.getValue().booleanValue();
    }

    public boolean isUploadManifestsWhenBufferEnabled() {
        if (WeblabTreatment.T1.equals(PlaybackWeblabs.getPlaybackWeblabs().get("AIVPLAYERS_243004").getCurrentTreatment())) {
            DLog.logf("Buffering based PostManifest enabled by weblab %s", "AIVPLAYERS_243004");
            return true;
        }
        DLog.logf("Buffering based PostManifest %s by config", isUploadManifestsWhenBufferEnabledViaConfig() ? "enabled" : "disabled");
        return isUploadManifestsWhenBufferEnabledViaConfig();
    }

    public int getErrorSeverityLevelToUploadManifest() {
        return this.mErrorSeverityLevelToUploadManifest.getValue().intValue();
    }

    public boolean isUploadManifestsWhenFatalEnabledViaConfig() {
        return this.mIsUploadManifestsWhenFatalEnabled.getValue().booleanValue();
    }

    public boolean isUploadManifestsWhenFatalEnabled() {
        MobileWeblab mobileWeblab = PlaybackWeblabs.getPlaybackWeblabs().get("AIVPLAYERS_250630");
        if (mobileWeblab != null && WeblabTreatment.T1.equals(mobileWeblab.getCurrentTreatment())) {
            DLog.logf("Fatal based PostManifest enabled by weblab %s", "AIVPLAYERS_250630");
            return true;
        }
        DLog.logf("Fatal based PostManifest %s by config", isUploadManifestsWhenFatalEnabledViaConfig() ? "enabled" : "disabled");
        return isUploadManifestsWhenFatalEnabledViaConfig();
    }

    public boolean shouldEnableAdManifestCapturer() {
        MobileWeblab mobileWeblab = PlaybackWeblabs.getPlaybackWeblabs().get("SSAI_UPLOAD_AD_MANIFEST_TO_S3_343261");
        return (this.mShouldEnableAdManifestCapturer.getValue().booleanValue() || shouldEnableAdManifestCapturerInBeta()) && (mobileWeblab != null && mobileWeblab.getCurrentTreatment().equals(WeblabTreatment.T1));
    }

    public boolean shouldEnableAdManifestCapturerInBeta() {
        return this.mShouldEnableAdManifestCapturerInBeta.getValue().booleanValue() && BetaConfigProvider.getInstance().provideBetaConfig().isBeta();
    }

    public boolean shouldCaptureSSAIManifests() {
        return this.mShouldCaptureSSAIManifests.getValue().booleanValue();
    }

    public boolean shouldCaptureCSAIManifests() {
        return this.mShouldCaptureCSAIManifests.getValue().booleanValue();
    }

    public boolean getExceptionUploadEnabledForAd() {
        return this.mExceptionUploadEnabledForAd.getValue().booleanValue();
    }

    public boolean shouldUploadAllAdManifest() {
        return this.mShouldUploadAllAdManifests.getValue().booleanValue();
    }

    public boolean getShouldCleanupExternalStorageDownloadPath() {
        return this.mShouldCleanupExternalStorageDownloadPath.getValue().booleanValue();
    }
}
