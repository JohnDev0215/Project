package com.blankj.utilcode.util;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import java.util.Locale;

/* JADX INFO: compiled from: LanguageUtils.java */
/* JADX INFO: loaded from: classes.dex */
public class m {
    static void a(Activity activity) {
        String strE = y.n().e("KEY_LOCALE");
        if (TextUtils.isEmpty(strE)) {
            return;
        }
        Locale localeB = "VALUE_FOLLOW_SYSTEM".equals(strE) ? b(Resources.getSystem().getConfiguration()) : e(strE);
        if (localeB == null) {
            return;
        }
        g(activity, localeB);
        g(w.a(), localeB);
    }

    private static Locale b(Configuration configuration) {
        return Build.VERSION.SDK_INT >= 24 ? configuration.getLocales().get(0) : configuration.locale;
    }

    private static boolean c(String str) {
        int i = 0;
        for (char c2 : str.toCharArray()) {
            if (c2 == '$') {
                if (i >= 1) {
                    return false;
                }
                i++;
            }
        }
        return i == 1;
    }

    private static void d(Configuration configuration, Locale locale) {
        if (Build.VERSION.SDK_INT >= 17) {
            configuration.setLocale(locale);
        } else {
            configuration.locale = locale;
        }
    }

    private static Locale e(String str) {
        Locale localeF = f(str);
        if (localeF == null) {
            Log.e("LanguageUtils", "The string of " + str + " is not in the correct format.");
            y.n().j("KEY_LOCALE");
        }
        return localeF;
    }

    private static Locale f(String str) {
        if (!c(str)) {
            return null;
        }
        try {
            int iIndexOf = str.indexOf("$");
            return new Locale(str.substring(0, iIndexOf), str.substring(iIndexOf + 1));
        } catch (Exception unused) {
            return null;
        }
    }

    private static void g(Context context, Locale locale) {
        Resources resources = context.getResources();
        Configuration configuration = resources.getConfiguration();
        d(configuration, locale);
        resources.updateConfiguration(configuration, resources.getDisplayMetrics());
    }
}
