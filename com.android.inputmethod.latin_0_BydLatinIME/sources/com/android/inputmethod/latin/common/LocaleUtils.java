package com.android.inputmethod.latin.common;

import com.byd.ime.subtype.model.SubtypeConfigData;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public final class LocaleUtils {
    public static final int LOCALE_ANY_MATCH = 10;
    public static final int LOCALE_FULL_MATCH = 30;
    public static final int LOCALE_LANGUAGE_AND_COUNTRY_MATCH = 20;
    public static final int LOCALE_LANGUAGE_AND_COUNTRY_MATCH_VARIANT_DIFFER = 6;
    public static final int LOCALE_LANGUAGE_MATCH = 15;
    public static final int LOCALE_LANGUAGE_MATCH_COUNTRY_DIFFER = 3;
    private static final int LOCALE_MATCH = 10;
    public static final int LOCALE_NO_MATCH = 0;
    private static final int MATCH_LEVEL_MAX = 30;
    private static final HashMap<String, Locale> sLocaleCache = new HashMap<>();
    private static final HashSet<String> sRtlLanguageCodes;

    public static boolean isMatch(final int level) {
        return 10 <= level;
    }

    private LocaleUtils() {
    }

    public static int getMatchLevel(@Nullable final String referenceLocale, @Nullable final String testedLocale) {
        if (StringUtils.isEmpty(referenceLocale)) {
            return StringUtils.isEmpty(testedLocale) ? 30 : 10;
        }
        if (testedLocale == null) {
            return 0;
        }
        String[] strArrSplit = referenceLocale.split("_", 3);
        String[] strArrSplit2 = testedLocale.split("_", 3);
        if (!strArrSplit[0].equals(strArrSplit2[0])) {
            return 0;
        }
        int length = strArrSplit.length;
        if (length == 1) {
            return 1 == strArrSplit2.length ? 30 : 15;
        }
        if (length == 2) {
            if (1 != strArrSplit2.length && strArrSplit[1].equals(strArrSplit2[1])) {
                return 3 == strArrSplit2.length ? 20 : 30;
            }
            return 3;
        }
        if (length != 3) {
            return 0;
        }
        if (1 != strArrSplit2.length && strArrSplit[1].equals(strArrSplit2[1])) {
            return (2 != strArrSplit2.length && strArrSplit[2].equals(strArrSplit2[2])) ? 30 : 6;
        }
        return 3;
    }

    public static String getMatchLevelSortedString(final int matchLevel) {
        return String.format(Locale.ROOT, "%02d", Integer.valueOf(30 - matchLevel));
    }

    static {
        HashSet<String> hashSet = new HashSet<>();
        sRtlLanguageCodes = hashSet;
        hashSet.add(SubtypeConfigData.SUBTYPE_LOCAL_LANGUAGE_Arabic);
        hashSet.add("fa");
        hashSet.add(SubtypeConfigData.SUBTYPE_LOCAL_LANGUAGE_Hebrew);
        hashSet.add("ku");
        hashSet.add("ps");
        hashSet.add("sd");
        hashSet.add("ug");
        hashSet.add("ur");
        hashSet.add("yi");
    }

    @Nonnull
    public static Locale constructLocaleFromString(@Nonnull final String localeString) {
        Locale locale;
        HashMap<String, Locale> map = sLocaleCache;
        synchronized (map) {
            if (map.containsKey(localeString)) {
                return map.get(localeString);
            }
            String[] strArrSplit = localeString.split("_", 3);
            if (strArrSplit.length == 1) {
                locale = new Locale(strArrSplit[0]);
            } else if (strArrSplit.length == 2) {
                locale = new Locale(strArrSplit[0], strArrSplit[1]);
            } else {
                locale = new Locale(strArrSplit[0], strArrSplit[1], strArrSplit[2]);
            }
            map.put(localeString, locale);
            return locale;
        }
    }

    public static boolean isRtlLanguage(@Nonnull final Locale locale) {
        return sRtlLanguageCodes.contains(locale.getLanguage());
    }
}
