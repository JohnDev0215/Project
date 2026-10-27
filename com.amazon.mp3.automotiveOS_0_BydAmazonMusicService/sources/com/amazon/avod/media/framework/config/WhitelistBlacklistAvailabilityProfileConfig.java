package com.amazon.avod.media.framework.config;

import amazon.android.config.ConfigRegistry;
import amazon.android.config.ConfigType;
import amazon.android.config.ConfigurationValue;
import com.amazon.avod.media.framework.config.profiles.ConfigurationProfile;
import com.amazon.avod.media.framework.config.profiles.parsers.ProfileResultParser;
import com.amazon.avod.util.Preconditions2;
import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class WhitelistBlacklistAvailabilityProfileConfig<T> extends WhitelistBlacklistAvailabilityConfig {

    @Nonnull
    private final ConfigurationProfile mConfigurationProfile;
    private final ConfigurationValue<String> mDefaultConfigValueString;

    @Nonnull
    private final T mDefaultValue;

    @Nullable
    private final ConfigurationValue<Set<String>> mProfileConfigSet;

    @Nullable
    private String mProfileName;

    @Nonnull
    private final ProfileResultParser<T> mProfileResultParser;

    @Nullable
    private String mProfileValue;

    public WhitelistBlacklistAvailabilityProfileConfig(@Nonnull String str, @Nonnull ConfigurationProfile configurationProfile, @Nullable String str2, @Nonnull T t, @Nonnull ProfileResultParser<T> profileResultParser, boolean z) {
        super(str, z);
        this.mConfigurationProfile = (ConfigurationProfile) Preconditions.checkNotNull(configurationProfile, "configurationProfile");
        T t2 = (T) Preconditions.checkNotNull(t, "defaultValue");
        this.mDefaultValue = t2;
        this.mDefaultConfigValueString = newStringConfigValue(this.mPrefix, t2.toString());
        this.mProfileResultParser = (ProfileResultParser) Preconditions.checkNotNull(profileResultParser, "profileResultParser");
        this.mProfileConfigSet = newSemicolonDelimitedStringSetConfigurationValue(this.mPrefix + "_profileConfig", str2 == null ? new String[0] : str2.split(";"));
    }

    @Override // com.amazon.avod.media.framework.config.WhitelistBlacklistAvailabilityConfig
    public boolean isAvailableForDevice() {
        if (deviceHasProfile()) {
            return ((Boolean) this.mProfileResultParser.parse2(this.mProfileValue)).booleanValue();
        }
        return super.isAvailableForDevice();
    }

    @Nonnull
    public ConfigurationValue<T> asConfigValue() {
        String str = this.mPrefix;
        T t = this.mDefaultValue;
        return new ConfigurationValue<T>(str, t, t.getClass(), ConfigRegistry.getInstance().getConfigEditor(ConfigType.SERVER)) { // from class: com.amazon.avod.media.framework.config.WhitelistBlacklistAvailabilityProfileConfig.1
            @Override // amazon.android.config.ConfigurationValue
            public T getValue() {
                return WhitelistBlacklistAvailabilityProfileConfig.this.deviceHasProfile() ? (T) WhitelistBlacklistAvailabilityProfileConfig.this.mProfileResultParser.parse2(WhitelistBlacklistAvailabilityProfileConfig.this.mProfileValue) : (T) WhitelistBlacklistAvailabilityProfileConfig.this.mProfileResultParser.parse2((String) WhitelistBlacklistAvailabilityProfileConfig.this.mDefaultConfigValueString.getValue());
            }

            @Override // amazon.android.config.ConfigurationValue
            public T getValue(T t2) {
                Preconditions2.failWeakly("Operation not supported.", new Object[0]);
                return null;
            }

            @Override // amazon.android.config.ConfigurationValue
            public void updateValue(T t2) {
                Preconditions2.failWeakly("Operation not supported.", new Object[0]);
            }

            @Override // amazon.android.config.ConfigurationValue
            public void updateValueString(String str2) {
                Preconditions2.failWeakly("Operation not supported.", new Object[0]);
            }
        };
    }

    private void updateProfileNameAndValue() {
        String deviceProfileName = this.mConfigurationProfile.getDeviceProfileName();
        if (deviceProfileName == null) {
            this.mProfileName = null;
            this.mProfileValue = null;
            return;
        }
        HashMap mapNewHashMap = Maps.newHashMap();
        ConfigurationValue<Set<String>> configurationValue = this.mProfileConfigSet;
        if (configurationValue != null) {
            Iterator<String> it = configurationValue.getValue().iterator();
            while (it.hasNext()) {
                String[] strArrSplit = it.next().split(":");
                mapNewHashMap.put(strArrSplit[0], strArrSplit[1]);
            }
        }
        String str = mapNewHashMap.containsKey(deviceProfileName) ? (String) mapNewHashMap.get(deviceProfileName) : null;
        this.mProfileName = deviceProfileName;
        this.mProfileValue = str;
        if (str != null) {
            this.mConfigurationProfile.record(this.mPrefix, str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean deviceHasProfile() {
        updateProfileNameAndValue();
        return (this.mProfileName == null || this.mProfileValue == null) ? false : true;
    }
}
