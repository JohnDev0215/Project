package com.android.car.settings.language;

import androidx.preference.Preference;
import com.android.internal.app.LocaleStore;
import java.util.Locale;

/* JADX INFO: loaded from: classes5.dex */
public class LocaleUtil {
    public static final String LOCALE_BUNDLE_KEY = "locale_key";

    private LocaleUtil() {
    }

    public static void setLocaleArgument(Preference preference, LocaleStore.LocaleInfo localeInfo) {
        preference.getExtras().putSerializable(LOCALE_BUNDLE_KEY, localeInfo.getLocale());
    }

    public static LocaleStore.LocaleInfo getLocaleArgument(Preference preference) {
        Locale locale = (Locale) preference.getExtras().getSerializable(LOCALE_BUNDLE_KEY);
        if (locale == null) {
            return null;
        }
        return LocaleStore.getLocaleInfo(locale);
    }
}
