package com.amazon.avod.media.download.internal;

import amazon.android.config.ConfigurationValue;
import com.amazon.avod.media.framework.config.MediaConfigBase;

/* JADX INFO: loaded from: classes2.dex */
public class PlaybackDownloadConfig extends MediaConfigBase {
    private final ConfigurationValue<Boolean> mDoDownloadDiskPersistenceSanityCheck = newBooleanConfigValue("playback_download_doDownloadDiskPersistenceSanityCheck", true);
    private final ConfigurationValue<Boolean> mDoDownloadDiskPersistenceSanityCheckFixes = newBooleanConfigValue("playback_download_doDownloadDiskPersistenceSanityCheckFixes", false);

    private static class SingletonHolder {
        private static final PlaybackDownloadConfig INSTANCE = new PlaybackDownloadConfig();

        private SingletonHolder() {
        }
    }

    public static PlaybackDownloadConfig getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public boolean doDownloadDiskPersistenceSanityCheck() {
        return this.mDoDownloadDiskPersistenceSanityCheck.getValue().booleanValue();
    }

    public boolean doDownloadDiskPersistenceSanityCheckFixes() {
        return this.mDoDownloadDiskPersistenceSanityCheckFixes.getValue().booleanValue();
    }
}
