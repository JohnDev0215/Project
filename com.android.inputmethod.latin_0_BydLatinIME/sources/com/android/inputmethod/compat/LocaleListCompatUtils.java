package com.android.inputmethod.compat;

import java.lang.reflect.Method;
import java.util.Locale;

/* JADX INFO: loaded from: classes2.dex */
public final class LocaleListCompatUtils {
    private static final Class CLASS_LocaleList;
    private static final Method METHOD_get;
    private static final Method METHOD_isEmpty;

    static {
        Class<?> cls = CompatUtils.getClass("android.os.LocaleList");
        CLASS_LocaleList = cls;
        METHOD_get = CompatUtils.getMethod(cls, "get", Integer.TYPE);
        METHOD_isEmpty = CompatUtils.getMethod(cls, "isEmpty", new Class[0]);
    }

    private LocaleListCompatUtils() {
    }

    public static boolean isEmpty(final Object localeList) {
        return ((Boolean) CompatUtils.invoke(localeList, Boolean.FALSE, METHOD_isEmpty, new Object[0])).booleanValue();
    }

    public static Locale get(final Object localeList, final int index) {
        return (Locale) CompatUtils.invoke(localeList, null, METHOD_get, Integer.valueOf(index));
    }
}
