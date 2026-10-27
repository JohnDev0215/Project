package com.amazon.avod.media.framework.config;

import amazon.android.config.ConfigurationValue;
import com.amazon.avod.media.framework.config.profiles.ThirdPartyConfigurationProfile;
import com.amazon.avod.media.framework.config.profiles.parsers.ProfileConfigParser;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class DashConfig extends MediaConfigBase {
    static final String KEY_DASH_MANIFEST_VALIDATE_PARSED_OBJECT_GRAPH = "isDashManifestValidateParsedObjectGraphEnabled";
    private final ConfigurationValue<Boolean> mIsDashManifestValidateParsedObjectGraphEnabled = newBooleanConfigValue(KEY_DASH_MANIFEST_VALIDATE_PARSED_OBJECT_GRAPH, false);
    private final WhitelistBlacklistAvailabilityConfig mIsWidevineEnabled = new WhitelistBlacklistAvailabilityProfileConfig(PlaybackConfigKeys.PLAYBACK_IS_WIDEVINE_ENABLED, ThirdPartyConfigurationProfile.getInstance(), "HD_HEVC:true;HD:true;HDR:true;UHD_HDR:true", Boolean.FALSE, ProfileConfigParser.BooleanProfileResultParser.getResultParser(), false);
    private final ConfigurationValue<Boolean> mEnableSubtitlesForMultiperiodManifest = newBooleanConfigValue("playback_enabledSubtitlesForMultiperiodManifest", false);
    private final ConfigurationValue<List<String>> mValidAtmosSupplementalProperties = newSemicolonDelimitedStringListConfigurationValue("playback_validAtmosSupplementalProperty", new String[]{"tag:dolby.com,2014:dash:DolbyDigitalPlusExtensionType:2014,ec+3", "tag:dolby.com,2018:dash:EC3_ExtensionType:2018,JOC"});

    private static final class SingletonHolder {
        private static final DashConfig INSTANCE = new DashConfig();

        private SingletonHolder() {
        }
    }

    public static DashConfig getInstance() {
        return SingletonHolder.INSTANCE;
    }

    DashConfig() {
    }

    public boolean getIsDashManifestValidateParsedObjectGraphEnabled() {
        return this.mIsDashManifestValidateParsedObjectGraphEnabled.getValue().booleanValue();
    }

    public boolean isWidevineEnabled() {
        return this.mIsWidevineEnabled.isAvailableForDevice();
    }

    public boolean isSubtitlesForMultiperiodManifestEnabled() {
        return this.mEnableSubtitlesForMultiperiodManifest.getValue().booleanValue();
    }

    public List<String> getValidAtmosSupplementalProperties() {
        return this.mValidAtmosSupplementalProperties.getValue();
    }
}
