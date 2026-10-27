package com.amazon.avod.userdownload;

import amazon.android.config.ConfigurationValue;
import androidx.annotation.VisibleForTesting;
import com.amazon.avod.media.framework.config.MediaConfigBase;

/* JADX INFO: loaded from: classes2.dex */
public class GeneralDownloadConfig extends MediaConfigBase {
    private final ConfigurationValue<Boolean> mSingleFileDownloadEnabled = newBooleanConfigValue("download_singleFileDownloadEnabled_2", true);
    private final ConfigurationValue<Boolean> mRecoverSessionContextIfMissing = newBooleanConfigValue("download_recoverSessionContextIfMissing", true);
    private final ConfigurationValue<Boolean> mExternalOrphanedDownloadDeletion = newBooleanConfigValue("download_externalOrphanedDownloadDeletion", true);
    private final ConfigurationValue<Boolean> mInternalOrphanedDownloadDeletion = newBooleanConfigValue("download_internalOrphanedDownloadDeletion", true);

    public static GeneralDownloadConfig getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static final GeneralDownloadConfig INSTANCE = new GeneralDownloadConfig();

        private SingletonHolder() {
        }
    }

    @VisibleForTesting
    public GeneralDownloadConfig() {
    }

    public boolean isSingleFileDownloadEnabled() {
        return this.mSingleFileDownloadEnabled.getValue().booleanValue();
    }
}
