package com.amazon.avod.util;

import amazon.android.config.ConfigBase;
import amazon.android.config.ConfigType;
import amazon.android.config.ConfigurationValue;
import com.google.common.util.concurrent.Uninterruptibles;
import java.util.concurrent.CountDownLatch;

/* JADX INFO: loaded from: classes2.dex */
public class AppStartConfig extends ConfigBase {
    private final CountDownLatch mAwaitSuccessfulConfig;
    private final ConfigurationValue<Boolean> mFirstAppStartWithUser;
    private final ConfigurationValue<Boolean> mFirstAppStartWithoutUser;
    private final ConfigurationValue<Boolean> mMadeSuccessfulServerConfigFetch;

    private static class SingletonHolder {
        private static final AppStartConfig INSTANCE = new AppStartConfig();

        private SingletonHolder() {
        }
    }

    public static AppStartConfig getInstance() {
        return SingletonHolder.INSTANCE;
    }

    protected AppStartConfig() {
        super("AppStartConfig");
        this.mAwaitSuccessfulConfig = new CountDownLatch(1);
        ConfigType configType = ConfigType.ACCOUNT;
        this.mFirstAppStartWithUser = newBooleanConfigValue("firstAppStartWithUser", true, configType);
        this.mFirstAppStartWithoutUser = newBooleanConfigValue("firstAppStartWithoutUser", true, configType);
        this.mMadeSuccessfulServerConfigFetch = newBooleanConfigValue("madeSuccessfulServerConfigFetch", false, ConfigType.SERVER);
    }

    public boolean isFirstAppStartForSignedStatus(boolean z) {
        return whichConfig(z).getValue().booleanValue();
    }

    public boolean isFirstStart() {
        return whichConfig(true).getValue().booleanValue() && whichConfig(false).getValue().booleanValue();
    }

    public void markFirstAppStartForSignedStatus(boolean z) {
        whichConfig(z).updateValue(Boolean.FALSE);
    }

    private ConfigurationValue<Boolean> whichConfig(boolean z) {
        return z ? this.mFirstAppStartWithUser : this.mFirstAppStartWithoutUser;
    }

    public void waitOnSuccessfulAppStartConfigCallUninterruptedly() {
        if (this.mMadeSuccessfulServerConfigFetch.getValue().booleanValue()) {
            return;
        }
        Uninterruptibles.awaitUninterruptibly(this.mAwaitSuccessfulConfig);
    }

    public void onSuccessfulAppStartConfigCall() {
        this.mAwaitSuccessfulConfig.countDown();
    }
}
