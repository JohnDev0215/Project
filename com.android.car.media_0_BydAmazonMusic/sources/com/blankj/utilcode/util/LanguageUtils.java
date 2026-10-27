package com.blankj.utilcode.util;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.blankj.utilcode.util.Utils;
import java.util.Iterator;
import java.util.Locale;

/* JADX INFO: loaded from: classes2.dex */
public class LanguageUtils {
    private static final String KEY_LOCALE = "KEY_LOCALE";
    private static final String VALUE_FOLLOW_SYSTEM = "VALUE_FOLLOW_SYSTEM";

    public static class a implements Utils.Consumer<Boolean> {
        public final /* synthetic */ boolean a;

        public a(boolean z) {
            this.a = z;
        }

        @Override // com.blankj.utilcode.util.Utils.Consumer
        public final void accept(Boolean bool) {
            if (bool.booleanValue()) {
                LanguageUtils.restart(this.a);
            } else {
                AppUtils.relaunchApp();
            }
        }
    }

    public static class b implements Runnable {
        public final /* synthetic */ Locale a;
        public final /* synthetic */ int b;
        public final /* synthetic */ Utils.Consumer c;

        public b(Locale locale, int i, Utils.Consumer consumer) {
            this.a = locale;
            this.b = i;
            this.c = consumer;
        }

        @Override // java.lang.Runnable
        public final void run() {
            LanguageUtils.pollCheckAppContextLocal(this.a, this.b + 1, this.c);
        }
    }

    private LanguageUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static void applyLanguage(@NonNull Locale locale) {
        applyLanguage(locale, false);
    }

    private static void applyLanguageReal(Locale locale, boolean z) {
        if (locale == null) {
            f.d().put(KEY_LOCALE, VALUE_FOLLOW_SYSTEM, true);
        } else {
            f.d().put(KEY_LOCALE, locale2String(locale), true);
        }
        if (locale == null) {
            locale = getLocal(Resources.getSystem().getConfiguration());
        }
        updateAppContextLanguage(locale, new a(z));
    }

    public static void applySystemLanguage() {
        applySystemLanguage(false);
    }

    public static Context attachBaseContext(Context context) {
        Locale localeString2Locale;
        String string = f.d().getString(KEY_LOCALE);
        if (TextUtils.isEmpty(string) || VALUE_FOLLOW_SYSTEM.equals(string) || (localeString2Locale = string2Locale(string)) == null) {
            return context;
        }
        Configuration configuration = context.getResources().getConfiguration();
        setLocal(configuration, localeString2Locale);
        return context.createConfigurationContext(configuration);
    }

    public static Locale getAppContextLanguage() {
        return getContextLanguage(Utils.getApp());
    }

    public static Locale getAppliedLanguage() {
        String string = f.d().getString(KEY_LOCALE);
        if (TextUtils.isEmpty(string) || VALUE_FOLLOW_SYSTEM.equals(string)) {
            return null;
        }
        return string2Locale(string);
    }

    public static Locale getContextLanguage(Context context) {
        return getLocal(context.getResources().getConfiguration());
    }

    private static Locale getLocal(Configuration configuration) {
        return configuration.getLocales().get(0);
    }

    public static Locale getSystemLanguage() {
        return getLocal(Resources.getSystem().getConfiguration());
    }

    public static boolean isAppliedLanguage() {
        return getAppliedLanguage() != null;
    }

    private static boolean isRightFormatLocalStr(String str) {
        int i = 0;
        for (char c : str.toCharArray()) {
            if (c == '$') {
                if (i >= 1) {
                    return false;
                }
                i++;
            }
        }
        return i == 1;
    }

    private static boolean isSameLocale(Locale locale, Locale locale2) {
        return StringUtils.equals(locale2.getLanguage(), locale.getLanguage()) && StringUtils.equals(locale2.getCountry(), locale.getCountry());
    }

    private static String locale2String(Locale locale) {
        return spi.c.g(locale.getLanguage(), "$", locale.getCountry());
    }

    public static void pollCheckAppContextLocal(Locale locale, int i, Utils.Consumer<Boolean> consumer) {
        Resources resources = Utils.getApp().getResources();
        Configuration configuration = resources.getConfiguration();
        Locale local = getLocal(configuration);
        setLocal(configuration, locale);
        Utils.getApp().getResources().updateConfiguration(configuration, resources.getDisplayMetrics());
        if (consumer == null) {
            return;
        }
        if (isSameLocale(local, locale)) {
            consumer.accept(Boolean.TRUE);
        } else if (i < 20) {
            ThreadUtils.runOnUiThreadDelayed(new b(locale, i, consumer), 16L);
        } else {
            Log.e("LanguageUtils", "appLocal didn't update.");
            consumer.accept(Boolean.FALSE);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void restart(boolean z) {
        if (z) {
            AppUtils.relaunchApp();
            return;
        }
        Iterator<Activity> it = f.c().iterator();
        while (it.hasNext()) {
            it.next().recreate();
        }
    }

    private static void setLocal(Configuration configuration, Locale locale) {
        configuration.setLocale(locale);
    }

    private static Locale string2Locale(String str) {
        Locale localeString2LocaleReal = string2LocaleReal(str);
        if (localeString2LocaleReal == null) {
            Log.e("LanguageUtils", "The string of " + str + " is not in the correct format.");
            f.d().remove(KEY_LOCALE);
        }
        return localeString2LocaleReal;
    }

    private static Locale string2LocaleReal(String str) {
        if (!isRightFormatLocalStr(str)) {
            return null;
        }
        try {
            int iIndexOf = str.indexOf("$");
            return new Locale(str.substring(0, iIndexOf), str.substring(iIndexOf + 1));
        } catch (Exception unused) {
            return null;
        }
    }

    public static void updateAppContextLanguage(@NonNull Locale locale, @Nullable Utils.Consumer<Boolean> consumer) {
        pollCheckAppContextLocal(locale, 0, consumer);
    }

    private static void updateConfiguration(Context context, Locale locale) {
        Resources resources = context.getResources();
        Configuration configuration = resources.getConfiguration();
        setLocal(configuration, locale);
        resources.updateConfiguration(configuration, resources.getDisplayMetrics());
    }

    public static void applyLanguage(@NonNull Locale locale, boolean z) {
        applyLanguageReal(locale, z);
    }

    public static void applySystemLanguage(boolean z) {
        applyLanguageReal(null, z);
    }

    public static boolean isAppliedLanguage(@NonNull Locale locale) {
        Locale appliedLanguage = getAppliedLanguage();
        if (appliedLanguage == null) {
            return false;
        }
        return isSameLocale(locale, appliedLanguage);
    }

    public static void applyLanguage(Activity activity) {
        Locale localeString2Locale;
        String string = f.d().getString(KEY_LOCALE);
        if (TextUtils.isEmpty(string)) {
            return;
        }
        if (VALUE_FOLLOW_SYSTEM.equals(string)) {
            localeString2Locale = getLocal(Resources.getSystem().getConfiguration());
        } else {
            localeString2Locale = string2Locale(string);
        }
        if (localeString2Locale == null) {
            return;
        }
        updateConfiguration(activity, localeString2Locale);
        updateConfiguration(Utils.getApp(), localeString2Locale);
    }
}
