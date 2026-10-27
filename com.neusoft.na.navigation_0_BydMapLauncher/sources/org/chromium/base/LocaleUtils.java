package org.chromium.base;

import java.util.Locale;

/* JADX INFO: loaded from: classes3.dex */
public class LocaleUtils {
    private LocaleUtils() {
    }

    public static String getLocale(Locale locale) {
        String language = locale.getLanguage();
        String country = locale.getCountry();
        if ("iw".equals(language)) {
            language = "he";
        } else if ("in".equals(language)) {
            language = "id";
        } else if ("tl".equals(language)) {
            language = "fil";
        }
        return country.isEmpty() ? language : language + "-" + country;
    }

    public static String getDefaultLocale() {
        return getLocale(Locale.getDefault());
    }

    private static String getDefaultCountryCode() {
        CommandLine commandLine = CommandLine.getInstance();
        return commandLine.hasSwitch(BaseSwitches.DEFAULT_COUNTRY_CODE_AT_INSTALL) ? commandLine.getSwitchValue(BaseSwitches.DEFAULT_COUNTRY_CODE_AT_INSTALL) : Locale.getDefault().getCountry();
    }
}
