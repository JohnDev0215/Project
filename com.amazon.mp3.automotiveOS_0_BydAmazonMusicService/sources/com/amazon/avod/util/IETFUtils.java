package com.amazon.avod.util;

import com.amazon.avod.perf.Profiler;
import com.amazon.avod.perf.SimpleCounterMetric;
import com.amazon.music.inappmessaging.internal.model.Splash;
import com.amazonaws.mobileconnectors.remoteconfiguration.Attributes;
import com.google.common.base.CharMatcher;
import com.google.common.base.Preconditions;
import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.UnmodifiableIterator;
import java.util.List;
import java.util.Locale;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class IETFUtils {
    private static final ImmutableMap<String, String> LEGACY_TO_MODERN_LANGUAGE_CODE = ImmutableMap.of("iw", "he", "ji", "yi", "in", "id");

    @Nonnull
    public static String toIETFLanguageTag(@Nullable Locale locale) {
        StringBuilder sb = new StringBuilder();
        if (locale == null || Strings.isNullOrEmpty(getLanguage(locale))) {
            return sb.toString();
        }
        sb.append(getLanguage(locale));
        if (!Strings.isNullOrEmpty(locale.getCountry())) {
            sb.append("-");
            sb.append(locale.getCountry());
        }
        if (!Strings.isNullOrEmpty(locale.getVariant())) {
            sb.append("-");
            sb.append(locale.getVariant());
        }
        return sb.toString();
    }

    @Nullable
    public static Locale getLocaleFromLanguageTag(@Nullable String str) {
        reportIfUnrecognizedLanguageTag(str);
        return LanguageTagParser.parseLocaleFromLanguageTag(str);
    }

    public static void reportIfUnrecognizedLanguageTag(@Nullable String str) {
        if (isLanguageTagValidLocale(str)) {
            return;
        }
        Profiler.reportCounterMetric(new SimpleCounterMetric("UnrecognizedLanguageTag", (ImmutableList<String>) ImmutableList.of("Counter", String.format(Locale.US, "Language:%s", sanitize(str)))));
    }

    static boolean isLanguageTagValidLocale(@Nullable String str) {
        Locale localeFromLanguageTag = LanguageTagParser.parseLocaleFromLanguageTag(str);
        String languageFromLanguageTag = LanguageTagParser.parseLanguageFromLanguageTag(str);
        if (localeFromLanguageTag != null && languageFromLanguageTag != null) {
            String displayLanguage = localeFromLanguageTag.getDisplayLanguage();
            Locale locale = Locale.US;
            if (!displayLanguage.toLowerCase(locale).equals(languageFromLanguageTag.toLowerCase(locale))) {
                return true;
            }
        }
        return false;
    }

    private static class LanguageTagParser {
        private LanguageTagParser() {
        }

        @Nullable
        static Locale parseLocaleFromLanguageTag(@Nullable String str) {
            if (Strings.isNullOrEmpty(str)) {
                return null;
            }
            List<String> listSplitLanguageTag = splitLanguageTag(str.toLowerCase(Locale.US));
            String str2 = listSplitLanguageTag.get(0);
            if (listSplitLanguageTag.size() == 1) {
                return new Locale(str2);
            }
            String str3 = listSplitLanguageTag.get(1);
            if (listSplitLanguageTag.size() == 2) {
                return new Locale(str2, str3);
            }
            return new Locale(str2, str3, listSplitLanguageTag.get(2));
        }

        @Nullable
        static String parseLanguageFromLanguageTag(@Nullable String str) {
            if (Strings.isNullOrEmpty(str)) {
                return null;
            }
            return splitLanguageTag(str).get(0);
        }

        private static List<String> splitLanguageTag(String str) {
            return Splitter.on(CharMatcher.anyOf("_-")).limit(3).splitToList(str);
        }
    }

    public static String getLanguage(@Nonnull Locale locale) {
        Preconditions.checkNotNull(locale, Splash.PARAMS_LOCALE);
        String language = locale.getLanguage();
        ImmutableMap<String, String> immutableMap = LEGACY_TO_MODERN_LANGUAGE_CODE;
        return immutableMap.containsKey(language) ? immutableMap.get(language) : language;
    }

    @Nullable
    public static String sanitize(@Nullable String str) {
        if (str == null) {
            return null;
        }
        return str.replace('_', '-').toLowerCase(Locale.US);
    }

    @Nonnull
    public static String toAmazonLocaleStringCommaSeparated(@Nonnull ImmutableSet<Locale> immutableSet) {
        StringBuilder sb = new StringBuilder();
        UnmodifiableIterator<Locale> it = immutableSet.iterator();
        boolean z = true;
        while (it.hasNext()) {
            Locale next = it.next();
            if (!z) {
                sb.append(",");
            }
            sb.append(toAmazonLocaleString(next));
            z = false;
        }
        return sb.toString();
    }

    @Nonnull
    public static String toAmazonLocaleString(@Nonnull Locale locale) {
        Preconditions.checkNotNull(locale, Splash.PARAMS_LOCALE);
        String language = getLanguage(locale);
        String country = locale.getCountry();
        String variant = locale.getVariant();
        return (Strings.isNullOrEmpty(country) && Strings.isNullOrEmpty(variant)) ? language : Strings.isNullOrEmpty(variant) ? String.format(Locale.US, "%s_%s", language, country) : String.format(Locale.US, "%s_%s_%s", language, country, variant);
    }

    @Nonnull
    public static Locale fromAmazonLocaleString(@Nonnull String str) throws LocaleFormattingException {
        Preconditions.checkNotNull(str, "localeString");
        String[] strArrSplit = str.split(Attributes.PREDEFINED_ATTRIBUTE_PREFIX, 3);
        if (strArrSplit.length == 1 && !Strings.isNullOrEmpty(strArrSplit[0])) {
            return new Locale(strArrSplit[0]);
        }
        if (strArrSplit.length == 2 && !Strings.isNullOrEmpty(strArrSplit[0]) && !Strings.isNullOrEmpty(strArrSplit[1])) {
            return new Locale(strArrSplit[0], strArrSplit[1]);
        }
        if (strArrSplit.length == 3 && !Strings.isNullOrEmpty(strArrSplit[0]) && !Strings.isNullOrEmpty(strArrSplit[1]) && !Strings.isNullOrEmpty(strArrSplit[2])) {
            return new Locale(strArrSplit[0], strArrSplit[1], strArrSplit[2]);
        }
        DLog.warnf("String %s does not much Amazon Locale format.", str);
        throw new LocaleFormattingException(str);
    }
}
