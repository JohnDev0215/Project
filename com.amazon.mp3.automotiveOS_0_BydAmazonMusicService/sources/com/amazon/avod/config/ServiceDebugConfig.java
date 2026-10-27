package com.amazon.avod.config;

import amazon.android.config.ConfigBase;
import amazon.android.config.ConfigType;
import amazon.android.config.ConfigurationValue;
import android.webkit.URLUtil;
import com.amazon.avod.core.Framework;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import java.util.Set;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class ServiceDebugConfig extends ConfigBase {
    private ImmutableMap<String, ConfigurationValue<String>> mServerApiSpecificOverrides;
    private final ConfigurationValue<Boolean> mShouldUseServerApiSpecificOverride;
    private final ConfigurationValue<Boolean> mShouldUseServerApiSpecificOverrideUser;
    private final ConfigurationValue<Boolean> mShouldWriteServerResponseToDisk;
    private final ConfigurationValue<Boolean> mShouldWriteServerResponseToDiskUser;
    private Set<String> mSupportedDebugAPIs;

    private static final class SingletonHolder {
        public static final ServiceDebugConfig INSTANCE = new ServiceDebugConfig();

        private SingletonHolder() {
        }
    }

    public static ServiceDebugConfig getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private ServiceDebugConfig() {
        super("ServiceDebugConfig");
        this.mSupportedDebugAPIs = ImmutableSet.of();
        this.mServerApiSpecificOverrides = ImmutableMap.of();
        ConfigType configType = ConfigType.SERVER;
        this.mShouldWriteServerResponseToDisk = newBooleanConfigValue("shouldWriteServerResponseToDisk", false, configType);
        this.mShouldUseServerApiSpecificOverride = newBooleanConfigValue("shouldUseServerApiSpecificOverride", false, configType);
        ConfigType configType2 = ConfigType.INTERNAL;
        this.mShouldWriteServerResponseToDiskUser = newBooleanConfigValue("shouldWriteServerResponseToDiskUser", false, configType2);
        this.mShouldUseServerApiSpecificOverrideUser = newBooleanConfigValue("shouldUseServerApiSpecificOverride", false, configType2);
    }

    public void initialize(@Nonnull Set<String> set) {
        this.mSupportedDebugAPIs = (Set) Preconditions.checkNotNull(set, "supportedDebugAPIs");
        ImmutableMap.Builder builder = ImmutableMap.builder();
        for (String str : this.mSupportedDebugAPIs) {
            builder.put(str, newStringConfigValue(str + "_Override", "", ConfigType.INTERNAL));
        }
        this.mServerApiSpecificOverrides = builder.build();
    }

    @Nonnull
    public Set<String> getSupportedDebugAPIs() {
        return this.mSupportedDebugAPIs;
    }

    public boolean shouldWriteServerResponseToDisk() {
        return this.mShouldWriteServerResponseToDisk.getValue().booleanValue() || this.mShouldWriteServerResponseToDiskUser.getValue().booleanValue();
    }

    public boolean shouldUseServerApiSpecificOverride() {
        return Framework.isDebugConfigurationEnabled() && (this.mShouldUseServerApiSpecificOverride.getValue().booleanValue() || this.mShouldUseServerApiSpecificOverrideUser.getValue().booleanValue());
    }

    public Optional<String> getServerApiSpecificOverride(@Nonnull String str) {
        if (!Framework.isDebugConfigurationEnabled()) {
            return Optional.absent();
        }
        if (!this.mServerApiSpecificOverrides.containsKey(str)) {
            return Optional.absent();
        }
        String value = this.mServerApiSpecificOverrides.get(str).getValue();
        if (!URLUtil.isNetworkUrl(value)) {
            return Optional.absent();
        }
        return Optional.of(value);
    }

    public void setUseServerApiSpecificOverride(boolean z) {
        this.mShouldUseServerApiSpecificOverrideUser.updateValue(Boolean.valueOf(z));
    }

    public void setServerApiSpecificOverride(@Nonnull String str, @Nullable String str2) {
        if (this.mServerApiSpecificOverrides.containsKey(str)) {
            if (str2 == null || URLUtil.isNetworkUrl(str2)) {
                this.mServerApiSpecificOverrides.get(str).updateValue(str2);
            }
        }
    }
}
