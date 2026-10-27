package com.amazon.avod.userdownload;

import amazon.android.config.ConfigBase;
import amazon.android.config.ConfigType;
import amazon.android.config.ConfigurationValue;
import com.amazon.avod.listeners.SetListenerProxy;

/* JADX INFO: loaded from: classes2.dex */
public class DownloadWanConfig extends ConfigBase {
    private final ConfigurationValue<Boolean> mAllowRetryForCompleteDownloadRegardlessNetworkType;
    private final ConfigurationValue<Boolean> mIsWANDownloadingDisabledByUser;
    private final PreferenceChangeListenerProxy mPreferenceChangeListenerProxy;
    private final ConfigurationValue<Boolean> mSupportsWANDownloading;

    private static class Holder {
        private static DownloadWanConfig sInstance = new DownloadWanConfig();

        private Holder() {
        }
    }

    public static final DownloadWanConfig getInstance() {
        return Holder.sInstance;
    }

    DownloadWanConfig() {
        super("com.amazon.avod.userdownload.DownloadWanConfig");
        this.mPreferenceChangeListenerProxy = new PreferenceChangeListenerProxy();
        ConfigType configType = ConfigType.SERVER;
        this.mSupportsWANDownloading = newBooleanConfigValue("isWANDownloadingSupported", true, configType);
        this.mIsWANDownloadingDisabledByUser = newBooleanConfigValue("only_download_on_wifi", true, ConfigType.ACCOUNT);
        this.mAllowRetryForCompleteDownloadRegardlessNetworkType = newBooleanConfigValue("dwld_mAllowRetryForCompleteDownloadRegardlessNetworkType", true, configType);
    }

    public boolean isWANDownloadingEnabledByUser() {
        return !this.mIsWANDownloadingDisabledByUser.getValue().booleanValue();
    }

    private static class PreferenceChangeListenerProxy extends SetListenerProxy<Object> {
        private PreferenceChangeListenerProxy() {
        }
    }
}
