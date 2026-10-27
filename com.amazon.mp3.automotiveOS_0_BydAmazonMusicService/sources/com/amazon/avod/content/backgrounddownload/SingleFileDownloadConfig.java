package com.amazon.avod.content.backgrounddownload;

import amazon.android.config.ConfigurationValue;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.framework.config.MediaConfigBase;
import com.amazon.avod.media.framework.config.TimeConfigurationValue;
import com.amazon.avod.util.DataUnit;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes2.dex */
public class SingleFileDownloadConfig extends MediaConfigBase {
    private final ConfigurationValue<Long> mCheckPointStepBytes;
    private final TimeConfigurationValue mDonwloadRequestTimeout;
    private final ConfigurationValue<Boolean> mFasterSdCardDownload;
    private final ConfigurationValue<Long> mMaxChunkSizeBytes;
    private final ConfigurationValue<Integer> mMaxDownloadRetries;
    private final ConfigurationValue<Integer> mReservedSizeMegaBytes;
    private final ConfigurationValue<Integer> mAudioConcurrentRequests = newIntConfigValue("playback_SingleFileDownloadAudioConcurrentRequests", 1);
    private final ConfigurationValue<Integer> mVideoConcurrentRequests = newIntConfigValue("playback_SingleFileDownloadVideoConcurrentRequests", 2);

    private static class SingletonHolder {
        private static final SingleFileDownloadConfig INSTANCE = new SingleFileDownloadConfig();

        private SingletonHolder() {
        }
    }

    public static SingleFileDownloadConfig getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public SingleFileDownloadConfig() {
        DataUnit dataUnit = DataUnit.MEGABYTES;
        this.mMaxChunkSizeBytes = newLongConfigValue("playback_SingleFileDownloadMaxChunkSizeBytes", dataUnit.toBytes(20.0f));
        this.mCheckPointStepBytes = newLongConfigValue("playback_SingleFileDownloadCheckPointStepBytes", dataUnit.toBytes(5.0f));
        this.mDonwloadRequestTimeout = newTimeConfigurationValue("playback_SingleFileDownloadDownloadRequestTimeout", TimeSpan.fromSeconds(3600.0d), TimeUnit.SECONDS);
        this.mReservedSizeMegaBytes = newIntConfigValue("playback_SingleFileDownloadReseredSizeMegaBytes", 500);
        this.mFasterSdCardDownload = newBooleanConfigValue("playback_SingleFileDownloadFasterSdCardDownload", true);
        this.mMaxDownloadRetries = newIntConfigValue("playback_SingleFileDownloadMaxRetries", 5);
    }

    public int getAudioConcurrentRequests() {
        return this.mAudioConcurrentRequests.getValue().intValue();
    }

    public int getVideoConcurrentRequests() {
        return this.mVideoConcurrentRequests.getValue().intValue();
    }

    public long getMaxChunkSizeBytes() {
        return this.mMaxChunkSizeBytes.getValue().longValue();
    }

    public long getCheckPointStepBytes() {
        return this.mCheckPointStepBytes.getValue().longValue();
    }

    public TimeSpan getDownloadRequestTimeout() {
        return this.mDonwloadRequestTimeout.getValue();
    }

    public int getReservedSizeMegaBytes() {
        return this.mReservedSizeMegaBytes.getValue().intValue();
    }

    public boolean getFasterSdCardDownload() {
        return this.mFasterSdCardDownload.getValue().booleanValue();
    }

    public int getMaxDownloadRetries() {
        return this.mMaxDownloadRetries.getValue().intValue();
    }
}
