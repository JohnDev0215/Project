package com.amazon.avod.playback;

import amazon.android.config.ConfigurationValue;
import com.amazon.avod.media.framework.config.MediaConfigBase;

/* JADX INFO: loaded from: classes2.dex */
public class ProfilingConfig extends MediaConfigBase {
    private static final ProfilingConfig SINGLETON_HOLDER = new ProfilingConfig();
    private final ConfigurationValue<Boolean> mAreMicroTracesEnabled = newBooleanConfigValue("playback_LifecycleProfilingReportingEnabled", true);

    public static ProfilingConfig getInstance() {
        return SINGLETON_HOLDER;
    }

    private ProfilingConfig() {
    }

    public boolean areMicroTracesEnabled() {
        return this.mAreMicroTracesEnabled.getValue().booleanValue();
    }
}
