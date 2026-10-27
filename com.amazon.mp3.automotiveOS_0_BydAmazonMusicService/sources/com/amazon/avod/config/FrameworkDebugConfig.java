package com.amazon.avod.config;

import amazon.android.config.ConfigBase;
import amazon.android.config.ConfigType;
import amazon.android.config.ConfigurationValue;

/* JADX INFO: loaded from: classes2.dex */
public class FrameworkDebugConfig extends ConfigBase {
    private final ConfigurationValue<Boolean> mAdbCommandLogEnabledUser;
    private final ConfigurationValue<Boolean> mNetworkCallDebuggingEnabled;
    private final ConfigurationValue<Boolean> mNetworkCallDebuggingEnabledUser;

    private static final class SingletonHolder {
        public static final FrameworkDebugConfig INSTANCE = new FrameworkDebugConfig();

        private SingletonHolder() {
        }
    }

    public static FrameworkDebugConfig getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private FrameworkDebugConfig() {
        super("com.amazon.avod.config.annotate.PrefsEditorForDebugConfig");
        this.mNetworkCallDebuggingEnabled = newBooleanConfigValue("shouldShowNetworkOverlay", false, ConfigType.SERVER);
        ConfigType configType = ConfigType.INTERNAL;
        this.mNetworkCallDebuggingEnabledUser = newBooleanConfigValue("shouldShowNetworkOverlayUser", false, configType);
        this.mAdbCommandLogEnabledUser = newBooleanConfigValue("adbCommandLogEnabledUser", false, configType);
    }

    public boolean isNetworkCallDebuggingEnabled() {
        return this.mNetworkCallDebuggingEnabled.getValue().booleanValue() || this.mNetworkCallDebuggingEnabledUser.getValue().booleanValue();
    }
}
