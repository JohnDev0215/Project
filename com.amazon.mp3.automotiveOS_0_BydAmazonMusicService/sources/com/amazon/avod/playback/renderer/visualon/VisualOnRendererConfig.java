package com.amazon.avod.playback.renderer.visualon;

import amazon.android.config.ConfigType;
import amazon.android.config.ConfigurationValue;
import com.amazon.avod.media.framework.config.MediaConfigBase;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import java.util.Iterator;
import java.util.Set;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class VisualOnRendererConfig extends MediaConfigBase {
    private static final int DEFAULT_DECODED_SAMPLE_BUFFER_SIZE = 13;
    private static final int DEFAULT_DECODE_THRESHOLD = 60;
    private static final int DEFAULT_JITTER_THRESHOLD = 100;
    private static final int DEFAULT_RENDER_THRESHOLD = 60;
    private static final int DEFAULT_REPORT_TIME_INTERVAL = 1;
    private static final int DEFAULT_SOURCE_THRESHOLD = 40;
    public static final VisualOnRendererConfig INSTANCE = new VisualOnRendererConfig();
    private final ConfigurationValue<Boolean> mIsVisualOnBackgroundAudioPlaybackSupported;
    private final ConfigurationValue<Boolean> mIsVisualOnVideoSurfaceHotSwapSupported;
    private final ConfigurationValue<Integer> mDecodedSampleBufferSize = newIntConfigValue("renderer_decodedSampleBufferSize", 13);
    private final ConfigurationValue<Integer> mReportTimeInSeconds = newIntConfigValue("renderer_visualOnReportTimeInSeconds", 1);
    private final ConfigurationValue<Integer> mSourceTimeThresholdInMillis = newIntConfigValue("renderer_visualOnSourceTimeThresholdInMillis", 40);
    private final ConfigurationValue<Integer> mDecodeTimeThresholdInMillis = newIntConfigValue("renderer_visualOnDecodeTimeThresholdInMillis", 60);
    private final ConfigurationValue<Integer> mRenderTimeThresholdInMillis = newIntConfigValue("renderer_visualOnRenderTimeThresholdInMillis", 60);
    private final ConfigurationValue<Integer> mJitterTimeThresholdInMillis = newIntConfigValue("renderer_visualOnJitterTimeThresholdInMillis", 100);
    private final ConfigurationValue<Boolean> mEnableCpuLoad = newBooleanConfigValue("renderer_visualOnEnableCpuLoad", true);
    private final ConfigurationValue<Boolean> mEnableMetricsLogging = newBooleanConfigValue("renderer_visualOnEnableMetricsLogging", false);
    private final ConfigurationValue<Boolean> mEnableRunningAtHigherPriority = newBooleanConfigValue("renderer_visualOnEnableRunningAtHigherPriority", true);
    private final ConfigurationValue<Boolean> mRenderingModeYuv = newBooleanConfigValue("renderer_visualOnRenderingModeEnableYuv", false);
    private final ConfigurationValue<Boolean> mRenderingModeYuvArm64AndroidL = newBooleanConfigValue("renderer_visualOnRenderingModeEnableYuvArm64AndroidL", true);
    private final ConfigurationValue<Set<String>> mYuvRenderingBlacklist = newSemicolonDelimitedStringSetConfigurationValue("renderer_visualOnYuvRenderingBlacklist", new String[]{"HUAWEI GRA-UL10"});
    private final ConfigurationValue<Integer> mDummyArraySizeInMb = newIntConfigValue("renderer_visualOnDummyArraySizeInMb", 20);
    private final ConfigurationValue<Integer> mDummyArraySkipSizeInKb = newIntConfigValue("renderer_visualOnDummyArraySkipSizeInKb", 4);
    private final ConfigurationValue<Integer> mAllocationBacktrackSizeInMb = newIntConfigValue("renderer_visualOnAllocationBacktrackSizeInMb", 2);

    private VisualOnRendererConfig() {
        ConfigType configType = ConfigType.SERVER;
        this.mIsVisualOnVideoSurfaceHotSwapSupported = newBooleanConfigValue("playback_isVisualOnVideoSurfaceHotSwapSupported", true, configType);
        this.mIsVisualOnBackgroundAudioPlaybackSupported = newBooleanConfigValue("playback_isVisualOnBackgroundAudioPlaybackSupported", true, configType);
    }

    public int getDecodedSampleBufferSize() {
        return this.mDecodedSampleBufferSize.getValue().intValue();
    }

    public int getReportTime() {
        return this.mReportTimeInSeconds.getValue().intValue();
    }

    public int getSourceTimeThreshold() {
        return this.mSourceTimeThresholdInMillis.getValue().intValue();
    }

    public int getDecodeTimeThreshold() {
        return this.mDecodeTimeThresholdInMillis.getValue().intValue();
    }

    public int getRenderTimeThreshold() {
        return this.mRenderTimeThresholdInMillis.getValue().intValue();
    }

    public int getJitterTimeThreshold() {
        return this.mJitterTimeThresholdInMillis.getValue().intValue();
    }

    public boolean getEnableCpuLoad() {
        return this.mEnableCpuLoad.getValue().booleanValue();
    }

    public boolean getEnableMetricsLogging() {
        return this.mEnableMetricsLogging.getValue().booleanValue();
    }

    public boolean getEnableRunningAtHigherPriority() {
        return this.mEnableRunningAtHigherPriority.getValue().booleanValue();
    }

    public int getDummyArraySizeInMb() {
        return this.mDummyArraySizeInMb.getValue().intValue();
    }

    public int getDummyArraySkipSizeInKb() {
        return this.mDummyArraySkipSizeInKb.getValue().intValue();
    }

    public int getAllocationBacktrackSizeInMb() {
        return this.mAllocationBacktrackSizeInMb.getValue().intValue();
    }

    public boolean getRenderingModeYuv() {
        return this.mRenderingModeYuv.getValue().booleanValue();
    }

    public boolean getRenderingModeYuvArm64AndroidL() {
        return this.mRenderingModeYuvArm64AndroidL.getValue().booleanValue();
    }

    public boolean isDeviceOnYuvRenderingBlacklist(@Nonnull String str) {
        return isDeviceBlacklisted(str, this.mYuvRenderingBlacklist.getValue());
    }

    private boolean isDeviceBlacklisted(@Nonnull String str, @Nonnull Set<String> set) {
        Preconditions.checkNotNull(str);
        Preconditions.checkNotNull(set);
        Iterator<String> it = set.iterator();
        while (it.hasNext()) {
            if (str.trim().equalsIgnoreCase(it.next().trim())) {
                DLog.logf("Device model: %s is blacklisted for YUV rendering, defaulting to RGB", str);
                return true;
            }
        }
        return false;
    }

    public boolean isVisualOnVideoSurfaceHotSwapSupported() {
        return this.mIsVisualOnVideoSurfaceHotSwapSupported.getValue().booleanValue();
    }

    public boolean isVisualOnBackgroundAudioPlaybackSupported() {
        return this.mIsVisualOnBackgroundAudioPlaybackSupported.getValue().booleanValue();
    }
}
