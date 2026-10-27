package com.amazon.avod.media.framework.config;

import amazon.android.config.ConfigType;
import amazon.android.config.ConfigurationValue;
import amazon.android.config.ServerConfigBase;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.util.DLog;
import com.google.common.base.Joiner;
import com.google.common.collect.Sets;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public abstract class MediaConfigBase extends ServerConfigBase {
    protected TimeConfigurationValue newTimeConfigurationValue(String str, TimeSpan timeSpan, TimeUnit timeUnit) {
        return newTimeConfigurationValue(str, timeSpan, timeUnit, ConfigType.SERVER);
    }

    protected TimeConfigurationValue newTimeConfigurationValue(String str, TimeSpan timeSpan, TimeUnit timeUnit, ConfigType configType) {
        return new TimeConfigurationValue(newLongConfigValue(str, timeUnit.convert(timeSpan.getTotalNanoSeconds(), TimeUnit.NANOSECONDS), configType), timeUnit);
    }

    protected ConfigurationValue<Set<String>> newSemicolonDelimitedStringSetConfigurationValue(String str, String[] strArr) {
        return newStringSetConfigValue(str, Joiner.on(";").join(strArr), ";");
    }

    protected ConfigurationValue<List<String>> newSemicolonDelimitedStringListConfigurationValue(String str, String[] strArr) {
        return newStringListConfigValue(str, Joiner.on(";").join(strArr), ";");
    }

    protected Set<Integer> createIntegerSetFromConfigurationValueStringSet(ConfigurationValue<Set<String>> configurationValue) {
        HashSet hashSetNewHashSet = Sets.newHashSet();
        for (String str : configurationValue.getValue()) {
            try {
                hashSetNewHashSet.add(Integer.valueOf((int) Long.decode(str).longValue()));
            } catch (NumberFormatException unused) {
                DLog.warnf("Unable to parse the error code in server config %s, due to NumberFormatException", str);
            }
        }
        return hashSetNewHashSet;
    }

    @Nonnull
    protected static double[] convertToDoubleArray(@Nonnull List<String> list) {
        double[] dArr = new double[list.size()];
        int i = 0;
        for (String str : list) {
            double d = 0.0d;
            try {
                d = Double.parseDouble(str);
            } catch (NumberFormatException e) {
                DLog.exceptionf(e, "Invalid format for parsing double of %s", str);
            }
            dArr[i] = d;
            i++;
        }
        return dArr;
    }

    @Nonnull
    protected static String[] convertToStringArray(@Nonnull List<String> list) {
        String[] strArr = new String[list.size()];
        list.toArray(strArr);
        return strArr;
    }
}
