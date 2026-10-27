package com.amazon.avod.playback;

import amazon.android.config.ConfigType;
import amazon.android.config.ConfigurationValue;
import amazon.android.config.ServerConfigBase;
import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import java.util.Map;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
@NotThreadSafe
public class LiveLanguageConfig extends ServerConfigBase {
    private final ConfigurationValue<Map<String, String>> mManifestCodeToDisplayNameMappingConfig;
    private final ConfigurationValue<Map<String, String>> mManifestCodeToLanguageCodeMappingConfig;
    private final ConfigurationValue<Boolean> mShouldFallbackToManifestBasedAudioLanguage;

    private static class SingletonHolder {
        private static final LiveLanguageConfig INSTANCE = new LiveLanguageConfig();

        private SingletonHolder() {
        }
    }

    public static final LiveLanguageConfig getInstance() {
        return SingletonHolder.INSTANCE;
    }

    LiveLanguageConfig() {
        ConfigType configType = ConfigType.SERVER;
        this.mManifestCodeToLanguageCodeMappingConfig = newStringMapConfigValue("language_live_codemapping", "eng:en-us,spa:es-es", ",", ":", configType);
        this.mManifestCodeToDisplayNameMappingConfig = newStringMapConfigValue("language_live_displaymapping", "eng:English,spa:Spanish", ",", ":", configType);
        this.mShouldFallbackToManifestBasedAudioLanguage = newBooleanConfigValue("language_shouldFallbackToManifestBasedAudioLanguage", true);
    }

    public BiMap<String, String> getLanguageCodeMapping() {
        try {
            return ImmutableBiMap.copyOf((Map) this.mManifestCodeToLanguageCodeMappingConfig.getValue());
        } catch (IllegalArgumentException | NullPointerException unused) {
            return ImmutableBiMap.of();
        }
    }

    public Map<String, String> getDisplayNameMapping() {
        return this.mManifestCodeToDisplayNameMappingConfig.getValue();
    }
}
