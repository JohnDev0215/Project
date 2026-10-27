package com.amazon.avod.content.urlvending;

import amazon.android.config.ConfigurationValue;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.framework.config.MediaConfigBase;
import com.amazon.avod.media.framework.config.TimeConfigurationValue;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes2.dex */
class CdnSwitchConfig extends MediaConfigBase {
    public static final CdnSwitchConfig INSTANCE = new CdnSwitchConfig();
    private final TimeConfigurationValue mWindowToRememberCDNSwitches;
    private final TimeConfigurationValue mWindowToRememberErrors;
    private final ConfigurationValue<Integer> mMaxTriesPerFragment = newIntConfigValue("urlvending_maxTriesPerFragment", 5);
    private final ConfigurationValue<Integer> mMaxTriesPerManifest = newIntConfigValue("urlvending_maxTriesPerManifest", 2);
    private final ConfigurationValue<Integer> mMaxNumberOfCDNSwitchesPerWindow = newIntConfigValue("urlvending_maxNumberOfSwitchesPerWindow", 5);
    private final ConfigurationValue<Integer> mMaxNumberTriesForCdnError = newIntConfigValue("urlvending_maxTriesForCdnError", 3);
    private final ConfigurationValue<Integer> mMaxNumberTriesForNetworkError = newIntConfigValue("urlvending_maxTriesForNetworkError", 5);

    private CdnSwitchConfig() {
        TimeSpan timeSpanFromMinutes = TimeSpan.fromMinutes(5L);
        TimeUnit timeUnit = TimeUnit.MINUTES;
        this.mWindowToRememberErrors = newTimeConfigurationValue("urlvending_windowToRememberErrorsLengthMinutes", timeSpanFromMinutes, timeUnit);
        this.mWindowToRememberCDNSwitches = newTimeConfigurationValue("urlvending_windowToRememberCDNSwitches", TimeSpan.fromMinutes(5L), timeUnit);
    }

    public int getMaxNumberTriesPerFragment() {
        return this.mMaxTriesPerFragment.getValue().intValue();
    }

    public int getMaxNumberTriesPerManifest() {
        return this.mMaxTriesPerManifest.getValue().intValue();
    }

    public int getMaxNumberTriesForCdnError() {
        return this.mMaxNumberTriesForCdnError.getValue().intValue();
    }

    public int getMaxNumberTriesForNetworkError() {
        return this.mMaxNumberTriesForNetworkError.getValue().intValue();
    }

    public int getMaxNumberOfCDNSwitchesPerWindow() {
        return this.mMaxNumberOfCDNSwitchesPerWindow.getValue().intValue();
    }

    public TimeSpan getWindowToRememberErrors() {
        return this.mWindowToRememberErrors.getValue();
    }

    public TimeSpan getWindowToRememberCDNSwitches() {
        return this.mWindowToRememberCDNSwitches.getValue();
    }
}
