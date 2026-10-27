package org.chromium.base;

import android.os.LocaleList;
import android.text.TextUtils;
import defpackage.C0175b;
import defpackage.iv;
import defpackage.xz;
import java.util.ArrayList;
import java.util.Locale;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes.dex */
public class LocaleUtils {
    public static String a(String str) {
        str.getClass();
        switch (str) {
            case "in":
                return "id";
            case "iw":
                return "he";
            case "ji":
                return "yi";
            case "jw":
                return "jv";
            case "tl":
                return "fil";
            default:
                return str;
        }
    }

    public static boolean b() {
        String language = xz.a.getResources().getConfiguration().locale.getLanguage();
        return language.endsWith("iw") || language.endsWith("ar");
    }

    public static String c(String str) {
        int iIndexOf = str.indexOf(45);
        return iIndexOf < 0 ? str : str.substring(0, iIndexOf);
    }

    public static String d(Locale locale) {
        String strA = a(locale.getLanguage());
        String country = locale.getCountry();
        return (strA.equals("no") && country.equals("NO") && locale.getVariant().equals("NY")) ? "nn-NO" : country.isEmpty() ? strA : C0175b.k(strA, "-", country);
    }

    @CalledByNative
    public static String getDefaultCountryCode() {
        iv ivVarE = iv.e();
        return ivVarE.g("default-country-code") ? ivVarE.f("default-country-code") : Locale.getDefault().getCountry();
    }

    @CalledByNative
    public static String getDefaultLocaleListString() {
        LocaleList localeList = LocaleList.getDefault();
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < localeList.size(); i++) {
            Locale localeBuild = localeList.get(i);
            String language = localeBuild.getLanguage();
            String strA = a(language);
            if (!strA.equals(language)) {
                localeBuild = new Locale.Builder().setLocale(localeBuild).setLanguage(strA).build();
            }
            arrayList.add(d(localeBuild));
        }
        return TextUtils.join(",", arrayList);
    }

    @CalledByNative
    public static String getDefaultLocaleString() {
        return d(Locale.getDefault());
    }
}
