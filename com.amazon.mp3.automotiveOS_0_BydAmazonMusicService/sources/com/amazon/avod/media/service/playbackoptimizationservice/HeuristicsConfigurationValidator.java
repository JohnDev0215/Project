package com.amazon.avod.media.service.playbackoptimizationservice;

import com.amazon.avod.dash.config.miyagi.HeuristicsConfigStore;
import com.amazon.avod.media.TimeSpan;
import java.util.Locale;
import java.util.Map;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
class HeuristicsConfigurationValidator {
    private static final long MAX_DELAY_TIME_MILLIS = TimeSpan.fromHours(48).getTotalMilliseconds();
    private static final long MIN_DELAY_TIME_MILLIS = TimeSpan.fromMinutes(1).getTotalMilliseconds();

    HeuristicsConfigurationValidator() {
    }

    static void validateLongInRange(long j, long j2, long j3) throws InvalidConfigurationException {
        if (j > j3 || j < j2) {
            throw new InvalidConfigurationException(String.format(Locale.US, "%s is not in range %s-%s", Long.valueOf(j), Long.valueOf(j2), Long.valueOf(j3)));
        }
    }

    static long getLongValue(Map<String, String> map, String str) throws InvalidConfigurationException {
        String str2 = map.get(str);
        if (str2 == null) {
            throw new InvalidConfigurationException(String.format(Locale.US, "Key %s not found", str));
        }
        try {
            return Long.parseLong(str2);
        } catch (NumberFormatException unused) {
            throw new InvalidConfigurationException(String.format(Locale.US, "%s is not a long number", str2));
        }
    }

    public void validate(@Nonnull Map<String, String> map) throws InvalidConfigurationException {
        if (map == null) {
            throw new InvalidConfigurationException("configuration input is null!");
        }
        validateLongInRange(getLongValue(map, HeuristicsConfigStore.DELAY_TIME_MILLIS_KEY), MIN_DELAY_TIME_MILLIS, MAX_DELAY_TIME_MILLIS);
    }
}
