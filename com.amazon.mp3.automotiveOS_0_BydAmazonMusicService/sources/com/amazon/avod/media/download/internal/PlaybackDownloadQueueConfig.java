package com.amazon.avod.media.download.internal;

import amazon.android.config.ConfigurationValue;
import com.amazon.avod.media.framework.config.MediaConfigBase;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class PlaybackDownloadQueueConfig extends MediaConfigBase {
    private final ConfigurationValue<Set<String>> mMarkErroredMustDeleteDownload = newSemicolonDelimitedStringSetConfigurationValue("playback_download_markErroredMustDeleteDownload", new String[0]);
    private final ConfigurationValue<Boolean> mDeleteOnDownloadNotOwned = newBooleanConfigValue("playback_download_deleteOnDownloadNotOwned", false);
    private final ConfigurationValue<Boolean> mShouldCheckInternalDownloadAvailability = newBooleanConfigValue("playback_download_shouldCheckInternalDownloadAvailability", true);
    private final ConfigurationValue<Set<String>> mQosReportingEventSubtypeBlocklist = newSemicolonDelimitedStringSetConfigurationValue("playback_download_qosReportingEventSubtypeBlocklist", new String[0]);
    private final ConfigurationValue<Long> mPluginDownloadTimeoutMillis = newLongConfigValue("playback_download_pluginTimeoutMillis", TimeUnit.MINUTES.toMillis(10));

    @Nonnull
    public Set<String> getMarkErroredMustDeleteDownload() {
        return this.mMarkErroredMustDeleteDownload.getValue();
    }

    public boolean shouldDeleteOnDownloadNotOwned() {
        return this.mDeleteOnDownloadNotOwned.getValue().booleanValue();
    }

    public boolean shouldCheckInternalDownloadAvailability() {
        return this.mShouldCheckInternalDownloadAvailability.getValue().booleanValue();
    }

    @Nonnull
    public Set<String> getQosReportingEventSubtypeBlocklist() {
        return this.mQosReportingEventSubtypeBlocklist.getValue();
    }

    public long getPluginDownloadTimeoutMillis() {
        return this.mPluginDownloadTimeoutMillis.getValue().longValue();
    }
}
