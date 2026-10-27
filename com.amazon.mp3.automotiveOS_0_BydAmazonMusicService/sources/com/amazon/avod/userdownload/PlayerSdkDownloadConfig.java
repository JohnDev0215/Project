package com.amazon.avod.userdownload;

import amazon.android.config.ConfigurationValue;
import com.amazon.avod.media.framework.config.MediaConfigBase;

/* JADX INFO: loaded from: classes2.dex */
public class PlayerSdkDownloadConfig extends MediaConfigBase {
    private final ConfigurationValue<Boolean> mEnablePlayerSdkDownloads = newBooleanConfigValue("playbackdownload_enablePlayerSdkDownload", true);

    public static PlayerSdkDownloadConfig getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static final PlayerSdkDownloadConfig INSTANCE = new PlayerSdkDownloadConfig();

        private SingletonHolder() {
        }
    }

    public boolean enablePlayerSdkDownloads() {
        return this.mEnablePlayerSdkDownloads.getValue().booleanValue();
    }
}
