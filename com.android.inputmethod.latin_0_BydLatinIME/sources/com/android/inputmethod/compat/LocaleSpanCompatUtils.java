package com.android.inputmethod.compat;

import android.text.Spannable;
import android.util.Log;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Locale;

/* JADX INFO: loaded from: classes2.dex */
public final class LocaleSpanCompatUtils {
    private static final Constructor<?> LOCALE_SPAN_CONSTRUCTOR;
    private static final Method LOCALE_SPAN_GET_LOCALE;
    private static final Class<?> LOCALE_SPAN_TYPE;
    private static final String TAG = "LocaleSpanCompatUtils";

    private static int getSpanPointMarkFlag(final boolean isStartExclusive, final boolean isEndExclusive) {
        return isStartExclusive ? isEndExclusive ? 33 : 34 : isEndExclusive ? 17 : 18;
    }

    private static Class<?> getLocaleSpanClass() {
        try {
            return Class.forName("android.text.style.LocaleSpan");
        } catch (ClassNotFoundException unused) {
            return null;
        }
    }

    static {
        Class<?> localeSpanClass = getLocaleSpanClass();
        LOCALE_SPAN_TYPE = localeSpanClass;
        LOCALE_SPAN_CONSTRUCTOR = CompatUtils.getConstructor(localeSpanClass, Locale.class);
        LOCALE_SPAN_GET_LOCALE = CompatUtils.getMethod(localeSpanClass, "getLocale", new Class[0]);
    }

    public static boolean isLocaleSpanAvailable() {
        return (LOCALE_SPAN_CONSTRUCTOR == null || LOCALE_SPAN_GET_LOCALE == null) ? false : true;
    }

    public static Object newLocaleSpan(final Locale locale) {
        return CompatUtils.newInstance(LOCALE_SPAN_CONSTRUCTOR, locale);
    }

    public static Locale getLocaleFromLocaleSpan(final Object localeSpan) {
        return (Locale) CompatUtils.invoke(localeSpan, null, LOCALE_SPAN_GET_LOCALE, new Object[0]);
    }

    public static void updateLocaleSpan(final Spannable spannable, final int start, final int end, final Locale locale) {
        Object objNewLocaleSpan;
        int spanFlags;
        Object[] objArr;
        if (end < start) {
            Log.e(TAG, "Invalid range: start=" + start + " end=" + end);
            return;
        }
        if (isLocaleSpanAvailable()) {
            Object[] spans = spannable.getSpans(Math.max(start - 1, 0), Math.min(end + 1, spannable.length()), LOCALE_SPAN_TYPE);
            ArrayList arrayList = new ArrayList();
            int length = spans.length;
            int i = start;
            int i2 = end;
            int i3 = 0;
            boolean z = true;
            boolean z2 = true;
            while (i3 < length) {
                Object obj = spans[i3];
                if (!locale.equals(getLocaleFromLocaleSpan(obj))) {
                    removeLocaleSpanFromRange(obj, spannable, start, end);
                    objArr = spans;
                } else {
                    int spanStart = spannable.getSpanStart(obj);
                    int spanEnd = spannable.getSpanEnd(obj);
                    if (spanEnd < spanStart) {
                        objArr = spans;
                        Log.e(TAG, "Invalid span: spanStart=" + spanStart + " spanEnd=" + spanEnd);
                    } else {
                        objArr = spans;
                        if (spanEnd >= start && end >= spanStart) {
                            int spanFlags2 = spannable.getSpanFlags(obj);
                            if (spanStart < i) {
                                z = (spanFlags2 & 33) == 33;
                                i = spanStart;
                            }
                            if (i2 < spanEnd) {
                                z2 = (spanFlags2 & 33) == 33;
                                i2 = spanEnd;
                            }
                            arrayList.add(obj);
                        }
                    }
                }
                i3++;
                spans = objArr;
            }
            if (arrayList.isEmpty()) {
                objNewLocaleSpan = newLocaleSpan(locale);
                spanFlags = 0;
            } else {
                objNewLocaleSpan = arrayList.get(0);
                spanFlags = spannable.getSpanFlags(objNewLocaleSpan);
                for (int i4 = 1; i4 < arrayList.size(); i4++) {
                    spannable.removeSpan(arrayList.get(i4));
                }
            }
            spannable.setSpan(objNewLocaleSpan, i, i2, getSpanFlag(spanFlags, z, z2));
        }
    }

    private static void removeLocaleSpanFromRange(final Object localeSpan, final Spannable spannable, final int removeStart, final int removeEnd) {
        if (isLocaleSpanAvailable()) {
            int spanStart = spannable.getSpanStart(localeSpan);
            int spanEnd = spannable.getSpanEnd(localeSpan);
            if (spanStart > spanEnd) {
                Log.e(TAG, "Invalid span: spanStart=" + spanStart + " spanEnd=" + spanEnd);
                return;
            }
            if (spanEnd >= removeStart && removeEnd >= spanStart) {
                int spanFlags = spannable.getSpanFlags(localeSpan);
                if (spanStart >= removeStart) {
                    if (removeEnd < spanEnd) {
                        spannable.setSpan(localeSpan, removeEnd, spanEnd, spanFlags);
                        return;
                    } else {
                        spannable.removeSpan(localeSpan);
                        return;
                    }
                }
                if (removeEnd < spanEnd) {
                    Locale localeFromLocaleSpan = getLocaleFromLocaleSpan(localeSpan);
                    spannable.setSpan(localeSpan, spanStart, removeStart, spanFlags);
                    spannable.setSpan(newLocaleSpan(localeFromLocaleSpan), removeEnd, spanEnd, spanFlags);
                    return;
                }
                spannable.setSpan(localeSpan, spanStart, removeStart, spanFlags);
            }
        }
    }

    private static int getSpanFlag(final int originalFlag, final boolean isStartExclusive, final boolean isEndExclusive) {
        return (originalFlag & (-52)) | getSpanPointMarkFlag(isStartExclusive, isEndExclusive);
    }
}
