package com.amazon.avod.ads.config;

import amazon.android.config.ConfigurationValue;
import amazon.android.config.ServerConfigBase;
import com.amazon.avod.ads.parser.vast.VastTrackingEventType;
import com.google.common.base.Joiner;
import java.util.Set;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class AdsConfig extends ServerConfigBase {
    private static AdsConfig INSTANCE;
    private final ConfigurationValue<Set<String>> mLiveSupportedAdTrackingEvents = newSemicolonDelimitedStringSetConfigurationValue("ads_liveSupportedTrackingEvents", new String[]{VastTrackingEventType.impression.name(), VastTrackingEventType.start.name(), VastTrackingEventType.firstQuartile.name(), VastTrackingEventType.midpoint.name(), VastTrackingEventType.thirdQuartile.name(), VastTrackingEventType.complete.name()});
    private final ConfigurationValue<Boolean> mShouldParseVastExtensionIntoDom = newBooleanConfigValue("ads_shouldParseVastExtensionIntoDom", true);

    public static synchronized AdsConfig getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new AdsConfig();
        }
        return INSTANCE;
    }

    private AdsConfig() {
    }

    @Nonnull
    public Set<String> getLiveSupportedAdTrackingEvents() {
        return this.mLiveSupportedAdTrackingEvents.getValue();
    }

    public boolean shouldParseVastExtensionIntoDom() {
        return this.mShouldParseVastExtensionIntoDom.getValue().booleanValue();
    }

    protected ConfigurationValue<Set<String>> newSemicolonDelimitedStringSetConfigurationValue(String str, String[] strArr) {
        return newStringSetConfigValue(str, Joiner.on(";").join(strArr), ";");
    }
}
