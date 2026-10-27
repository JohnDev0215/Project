package com.amazon.mp3.util;

import android.content.Context;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import com.amazon.mp3.automotiveOS.R;
import java.text.Normalizer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes4.dex */
public class StringUtil {
    private static final String TAG = "StringUtil";
    public static final Pattern SPLIT_CSV = Pattern.compile("\\s*,\\s*");
    private static final Pattern ACCENT_TRIM_PATTERN = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
    private static final Pattern NUMBER_EXTRACT_PATTERN = Pattern.compile("\\d+");
    private static final String[] COMMON_PREFIX = {"THE ", "A "};
    private static final String[] COMMON_WORDS = {"  ", " THE ", " A "};
    private static final Map<String, String> sNormalizeCache = new LruHashMap(1000);
    private static final HashMap<Long, String> sFormattedTimeCache = new LruHashMap(500);

    public static void clearFormattedTimeCache() {
        sFormattedTimeCache.clear();
    }

    public static void clearNormalizeCache() {
        sNormalizeCache.clear();
    }

    public static String getCachedFormattedTime(long milliseconds) {
        if (milliseconds <= 0) {
            return "0:00";
        }
        long j = milliseconds / 1000;
        HashMap<Long, String> map = sFormattedTimeCache;
        String str = map.get(Long.valueOf(j));
        if (str != null) {
            return str;
        }
        String str2 = String.format(Locale.US, "%d:%02d", Long.valueOf(milliseconds / 60000), Long.valueOf((milliseconds % 60000) / 1000));
        map.put(Long.valueOf(j), str2);
        return str2;
    }

    public static boolean isNullOrWhiteSpaces(@Nullable String string) {
        return string == null || TextUtils.getTrimmedLength(string) <= 0;
    }

    public static String unAccent(String string) {
        return ACCENT_TRIM_PATTERN.matcher(Normalizer.normalize(string, Normalizer.Form.NFD)).replaceAll("");
    }

    public static String normalize(String source, String regexForReplaceAll, String replacement, String[] prefixesToRemove, String[] wordsToRemove) {
        if (source == null) {
            Log.warning(TAG, "Failing to normalize string as it is null.");
            return null;
        }
        String str = sNormalizeCache.get(source);
        if (str != null) {
            return str;
        }
        String upperCase = source.trim().toUpperCase(Locale.US);
        if (regexForReplaceAll != null) {
            upperCase = upperCase.replaceAll(regexForReplaceAll, replacement);
        }
        StringBuilder sb = new StringBuilder(upperCase);
        if (prefixesToRemove != null) {
            for (String str2 : prefixesToRemove) {
                if (sb.indexOf(str2) == 0) {
                    sb.replace(0, str2.length(), "");
                }
            }
        }
        if (wordsToRemove != null) {
            for (String str3 : wordsToRemove) {
                while (true) {
                    int iIndexOf = sb.indexOf(str3);
                    if (iIndexOf == -1) {
                        break;
                    }
                    sb.replace(iIndexOf, str3.length() + iIndexOf, " ");
                }
            }
        }
        String strUnAccent = unAccent(sb.toString().trim());
        sNormalizeCache.put(source, strUnAccent);
        return strUnAccent;
    }

    public static String toLowercaseNoun(String noun, Locale locale) {
        return (Locale.GERMAN.getLanguage().equals(locale.getLanguage()) || noun == null) ? noun : noun.toLowerCase(locale);
    }

    public static String normalizeArtistName(String name) {
        return normalize(name, "\\p{Punct}+", " ", COMMON_PREFIX, COMMON_WORDS);
    }

    public static String normalizeTrackName(String name) {
        return normalize(name, "\\p{Punct}+", " ", COMMON_PREFIX, COMMON_WORDS);
    }

    public static String normalizeAlbumName(String name) {
        return normalize(name, "\\p{Punct}+", " ", COMMON_PREFIX, COMMON_WORDS);
    }

    public static boolean csvContains(final String csv, final String needle) {
        return contains(SPLIT_CSV.split(csv), needle);
    }

    public static boolean contains(final String[] haystack, final String needle) {
        for (String str : haystack) {
            if (str.equals(needle)) {
                return true;
            }
        }
        return false;
    }

    private static final class LruHashMap<K1, V1> extends LinkedHashMap<K1, V1> {
        private int mMaxCacheSize;

        public LruHashMap(int maxCacheSize) {
            super(256, 0.75f, true);
            this.mMaxCacheSize = maxCacheSize;
        }

        @Override // java.util.LinkedHashMap
        protected boolean removeEldestEntry(Map.Entry<K1, V1> eldest) {
            return size() >= this.mMaxCacheSize;
        }
    }

    public static void setDefaultTextStyle(MenuItem item, @NonNull Context context) {
        if (item != null) {
            SpannableString spannableString = new SpannableString(item.getTitle().toString());
            spannableString.setSpan(new StyleSpan(0), 0, spannableString.length(), 0);
            spannableString.setSpan(new ForegroundColorSpan(ContextCompat.getColor(context, R.color.white)), 0, spannableString.length(), 0);
            item.setTitle(spannableString);
        }
    }

    public static void boldTextColor(MenuItem item) {
        if (item != null) {
            SpannableString spannableString = new SpannableString(item.getTitle().toString());
            spannableString.setSpan(new StyleSpan(1), 0, spannableString.length(), 0);
            item.setTitle(spannableString);
        }
    }

    public static void dimTextColor(MenuItem item, @NonNull Context context) {
        if (item != null) {
            SpannableString spannableString = new SpannableString(item.getTitle());
            spannableString.setSpan(new ForegroundColorSpan(ContextCompat.getColor(context, R.color.setting_disabled_preference_text_color)), 0, spannableString.length(), 0);
            item.setTitle(spannableString);
        }
    }

    public static void tryResetMenuTitleColorForNightwing(MenuItem item, @NonNull Context context) {
        if (!UserSubscriptionUtil.isNightwingOnly() || item == null) {
            return;
        }
        dimTextColor(item, context);
    }

    public static String join(final String separator, final List<String> stringList) {
        if (separator == null || stringList == null || stringList.size() == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        Iterator<String> it = stringList.iterator();
        while (it.hasNext()) {
            sb.append(it.next());
            sb.append(separator);
        }
        if (sb.length() > 0) {
            sb.setLength(sb.length() - separator.length());
        }
        return sb.toString();
    }

    public static String removeExplicitLabelFromAlbumOrTrackTitle(String entityTitle) {
        if (isNullOrWhiteSpaces(entityTitle)) {
            return entityTitle;
        }
        if (entityTitle.contains("[Explicit]")) {
            entityTitle = entityTitle.replace("[Explicit]", "");
        }
        return entityTitle.trim();
    }

    public static String cleanFormatString(String stringToClean) {
        return stringToClean.replaceAll("\\ %[a-zA-Z]", "");
    }
}
