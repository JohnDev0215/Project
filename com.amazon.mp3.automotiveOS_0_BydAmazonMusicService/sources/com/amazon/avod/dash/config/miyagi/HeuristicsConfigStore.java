package com.amazon.avod.dash.config.miyagi;

import java.util.Map;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public interface HeuristicsConfigStore {
    public static final String DELAY_TIME_MILLIS_KEY = "callback_delayMillis";
    public static final long INVALID_TIME_MILLIS = -1;
    public static final String SETTINGS_ID_KEY = "settingsId";
    public static final String STORED_TIME_KEY = "storedTime";
    public static final String TIME_TO_LIVE_MILLIS_KEY = "caching_timeToLiveMillis";

    @Nonnull
    HeuristicsConfig createMergedHeuristicsConfig(Map<String, String> map);

    @Nonnull
    HeuristicsConfig getDefaultHeuristicsConfig();

    long getTimeToLiveMillis();

    void reload(Map<String, String> map);
}
