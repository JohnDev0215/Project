package com.amazon.avod.content.image;

import amazon.android.config.ConfigType;
import amazon.android.config.ConfigurationValue;
import android.content.Context;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.framework.config.MediaConfigBase;
import com.amazon.avod.media.framework.config.TimeConfigurationValue;
import com.amazon.avod.playbackclient.displaymode.ScreenSizeUtils;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class ImageDownloaderConfig extends MediaConfigBase {
    private final ConfigurationValue<Integer> mBackwardImageDownloadMaxRetryCount;
    private final ConfigurationValue<Integer> mCompactDeviceHorizontalImageResolution;
    private final TimeConfigurationValue mDurationThresholdForCurrentTierMultipass;
    private final ConfigurationValue<Integer> mForwardImageDownloadMaxRetryCount;
    private final ConfigurationValue<Boolean> mImageDownloadMultiPeriodSupported;
    private final TimeConfigurationValue mImageDownloadRetryDelay;
    private final ConfigurationValue<ImageDownloadStrategy> mImageDownloadStrategy;
    private final TimeConfigurationValue mImageDownloadTickInterval;
    private final TimeConfigurationValue mImageDownloadTimeout;
    private final ConfigurationValue<Float> mMaxImageCacheSizeMB;
    private final TimeConfigurationValue mMinDurationForMultipass;
    private final ConfigurationValue<Float> mMinPercentageRequiredToShowThumbnails;
    private final ConfigurationValue<Integer> mRegularDeviceHorizontalImageResolution;
    private final ConfigurationValue<List<String>> mSortedImageDownloadGranularitiesInSeconds;

    public static ImageDownloaderConfig getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static final class SingletonHolder {
        public static final ImageDownloaderConfig INSTANCE = new ImageDownloaderConfig();

        private SingletonHolder() {
        }
    }

    private ImageDownloaderConfig() {
        TimeSpan timeSpanFromSeconds = TimeSpan.fromSeconds(5.0d);
        TimeUnit timeUnit = TimeUnit.SECONDS;
        this.mImageDownloadTimeout = newTimeConfigurationValue("playback_imageDownloadTimeoutSeconds", timeSpanFromSeconds, timeUnit);
        this.mImageDownloadRetryDelay = newTimeConfigurationValue("playback_imageDownloadRetryDelaySeconds", TimeSpan.fromSeconds(1.0d), timeUnit);
        this.mForwardImageDownloadMaxRetryCount = newIntConfigValue("playback_imageDownloadMaxRetryCount_forwardPass", 2);
        this.mBackwardImageDownloadMaxRetryCount = newIntConfigValue("playback_imageDownloadMaxRetryCount_backwardPass", 1);
        this.mImageDownloadMultiPeriodSupported = newBooleanConfigValue("playback_imageDownloadMultiPeriodSupported", false);
        this.mSortedImageDownloadGranularitiesInSeconds = newSemicolonDelimitedStringListConfigurationValue("playback_sortedImageDownloadGranularitiesInSeconds", new String[]{"80", "40", "20", "10"});
        this.mMaxImageCacheSizeMB = newFloatConfigValue("playback_imageDownloadMaxCacheSizeMB", 50.0f);
        this.mMinDurationForMultipass = newTimeConfigurationValue("playback_imageDownloadMinDurationMultipassSeconds", TimeSpan.fromMinutes(10L), timeUnit);
        this.mImageDownloadStrategy = newEnumConfigValue("playback_dynamicImageDownloadStrategy", ImageDownloadStrategy.MULTIPASS, ImageDownloadStrategy.class, ConfigType.SERVER);
        this.mCompactDeviceHorizontalImageResolution = newIntConfigValue("playback_compactDeviceTrickplayHorizonalResolution", 240);
        this.mRegularDeviceHorizontalImageResolution = newIntConfigValue("playback_regularDeviceTrickplayHorizonalResolution", 320);
        this.mMinPercentageRequiredToShowThumbnails = newFloatConfigValue("playback_minPercentageRequiredToShowThumbnails", 0.9f);
        this.mImageDownloadTickInterval = newTimeConfigurationValue("playback_imageDownloadTickIntervalMillis", TimeSpan.fromMilliseconds(0L), TimeUnit.MILLISECONDS);
        this.mDurationThresholdForCurrentTierMultipass = newTimeConfigurationValue("playback_durationThresholdForCurrentTierMultipassSeconds", TimeSpan.fromMinutes(1L), timeUnit);
    }

    @Nonnull
    public TimeSpan getImageDownloadTimeout() {
        return this.mImageDownloadTimeout.getValue();
    }

    @Nonnull
    public TimeSpan getImageDownloadRetryDelay() {
        return this.mImageDownloadRetryDelay.getValue();
    }

    public int getForwardImageDownloadMaxRetryCount() {
        return this.mForwardImageDownloadMaxRetryCount.getValue().intValue();
    }

    public int getBackwardImageDownloadMaxRetryCount() {
        return this.mBackwardImageDownloadMaxRetryCount.getValue().intValue();
    }

    public boolean isMultiPeriodSupported() {
        return this.mImageDownloadMultiPeriodSupported.getValue().booleanValue();
    }

    @Nonnull
    public List<Integer> getSortedImageDownloadGranularitiesInSeconds() {
        ArrayList arrayList = new ArrayList();
        for (String str : this.mSortedImageDownloadGranularitiesInSeconds.getValue()) {
            try {
                arrayList.add(Integer.valueOf(Integer.parseInt(str)));
            } catch (NumberFormatException e) {
                DLog.warnf("Unable to parse image download granularity: %s, error: %s", str, e);
            }
        }
        return arrayList;
    }

    public float getMaxImageCacheSizeMB() {
        return this.mMaxImageCacheSizeMB.getValue().floatValue();
    }

    @Nonnull
    public ImageDownloadStrategy getImageDownloadStrategy(long j) {
        boolean z = this.mMinDurationForMultipass.getValue().getTotalNanoSeconds() < j;
        ImageDownloadStrategy value = this.mImageDownloadStrategy.getValue();
        if (!value.equals(ImageDownloadStrategy.MULTIPASS) || z) {
            return value;
        }
        DLog.logf("Falling back to serial because content duration is too short for multipass");
        return ImageDownloadStrategy.SERIAL;
    }

    public int getImageHorizontalResolution(@Nonnull Context context) {
        Preconditions.checkNotNull(context, "context");
        if (ScreenSizeUtils.isCompactDevice(context)) {
            return this.mCompactDeviceHorizontalImageResolution.getValue().intValue();
        }
        return this.mRegularDeviceHorizontalImageResolution.getValue().intValue();
    }

    public float getMinPercentageRequiredToShowThumbnails() {
        return this.mMinPercentageRequiredToShowThumbnails.getValue().floatValue();
    }

    @Nonnull
    public TimeSpan getImageDownloadTickInterval() {
        return this.mImageDownloadTickInterval.getValue();
    }

    @Nonnull
    public TimeSpan getDurationThresholdForCurrentTierMultipass() {
        return this.mDurationThresholdForCurrentTierMultipass.getValue();
    }
}
