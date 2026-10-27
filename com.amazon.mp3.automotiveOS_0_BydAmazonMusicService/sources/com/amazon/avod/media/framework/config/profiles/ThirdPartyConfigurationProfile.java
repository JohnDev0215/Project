package com.amazon.avod.media.framework.config.profiles;

import amazon.android.config.ConfigRegistry;
import amazon.android.config.ConfigType;
import amazon.android.config.internal.JacksonJsonConfigurationValue;
import android.os.Build;
import com.amazon.avod.media.framework.config.PlaybackConfigKeys;
import com.amazon.avod.media.framework.config.profiles.ThirdPartyProfile;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class ThirdPartyConfigurationProfile implements ConfigurationProfile {
    private static final ThirdPartyConfigurationProfile INSTANCE = new ThirdPartyConfigurationProfile();
    private final Map<String, String> mConfigCaptureMap = new HashMap();

    @Nullable
    private ThirdPartyProfileName mOverrideProfileName;

    @Nullable
    private String mProfileName;
    private JacksonJsonConfigurationValue<ThirdPartyProfile> mThirdPartyProfileConfigValue;

    private boolean isHdrSupportedForDeviceInHdrProfile(String str) {
        return true;
    }

    public static ThirdPartyConfigurationProfile getInstance() {
        return INSTANCE;
    }

    @Override // com.amazon.avod.media.framework.config.profiles.ConfigurationProfile
    @Nullable
    public String getDeviceProfileName() {
        ThirdPartyProfileName thirdPartyProfileName = this.mOverrideProfileName;
        if (thirdPartyProfileName != null) {
            return thirdPartyProfileName.name();
        }
        if (this.mThirdPartyProfileConfigValue == null) {
            this.mThirdPartyProfileConfigValue = new JacksonJsonConfigurationValue<>(PlaybackConfigKeys.PLAYBACK_3P_PROFILE_KEY, ThirdPartyProfile.getDefaultInstance(), ThirdPartyProfile.class, ConfigRegistry.getInstance().getConfigEditor(ConfigType.SERVER), new ThirdPartyProfile.Parser());
        }
        Map<ThirdPartyProfileName, String> profileMap = this.mThirdPartyProfileConfigValue.getValue().getProfileMap();
        String strTrim = (Build.MANUFACTURER + " " + Build.MODEL).trim();
        Iterator<Map.Entry<ThirdPartyProfileName, String>> it = profileMap.entrySet().iterator();
        loop0: while (true) {
            if (!it.hasNext()) {
                break;
            }
            Map.Entry<ThirdPartyProfileName, String> next = it.next();
            for (String str : next.getValue().split(";")) {
                if (str.trim().equalsIgnoreCase(strTrim)) {
                    this.mProfileName = next.getKey().getValue();
                    break loop0;
                }
            }
        }
        if ((ThirdPartyProfileName.HDR == ThirdPartyProfileName.fromProfileString(this.mProfileName) || ThirdPartyProfileName.UHD_HDR == ThirdPartyProfileName.fromProfileString(this.mProfileName)) && !isHdrSupportedForDeviceInHdrProfile(strTrim)) {
            this.mProfileName = ThirdPartyProfileName.SD.name();
        }
        return this.mProfileName;
    }

    public void overrideDeviceProfile(@Nonnull ThirdPartyProfileName thirdPartyProfileName) {
        DLog.warnf("ThirdPartyConfigurationProfile setting profile override: %s", thirdPartyProfileName);
        this.mOverrideProfileName = (ThirdPartyProfileName) Preconditions.checkNotNull(thirdPartyProfileName, "overrideProfile");
    }

    @Override // com.amazon.avod.media.framework.config.profiles.ConfigurationProfile
    public void record(@Nonnull String str, @Nonnull String str2) {
        Preconditions.checkNotNull(str, "configKey");
        Preconditions.checkNotNull(str2, "profileValue");
        this.mConfigCaptureMap.put(str, str2);
    }

    @Override // com.amazon.avod.media.framework.config.profiles.ConfigurationProfile
    public String getConfigValue(@Nonnull String str) {
        Preconditions.checkNotNull(str, "configKey");
        return this.mConfigCaptureMap.get(str);
    }

    @Override // com.amazon.avod.media.framework.config.profiles.ConfigurationProfile
    public boolean containsKey(@Nonnull String str) {
        Preconditions.checkNotNull(str, "configKey");
        return this.mConfigCaptureMap.containsKey(str);
    }
}
