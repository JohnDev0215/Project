package org.chromium.base;

import android.os.Build;
import android.os.LocaleList;
import android.text.TextUtils;
import defpackage.Es;
import defpackage.Z7;
import java.util.ArrayList;
import java.util.Locale;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class LocaleUtils {
    /* JADX WARN: Removed duplicated region for block: B:23:0x0040  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String a(java.lang.String r5) {
        /*
            int r0 = r5.hashCode()
            r1 = 3365(0xd25, float:4.715E-42)
            r2 = 3
            r3 = 2
            r4 = 1
            if (r0 == r1) goto L36
            r1 = 3374(0xd2e, float:4.728E-42)
            if (r0 == r1) goto L2c
            r1 = 3391(0xd3f, float:4.752E-42)
            if (r0 == r1) goto L22
            r1 = 3704(0xe78, float:5.19E-42)
            if (r0 == r1) goto L18
            goto L40
        L18:
            java.lang.String r0 = "tl"
            boolean r0 = r5.equals(r0)
            if (r0 == 0) goto L40
            r0 = 3
            goto L41
        L22:
            java.lang.String r0 = "ji"
            boolean r0 = r5.equals(r0)
            if (r0 == 0) goto L40
            r0 = 1
            goto L41
        L2c:
            java.lang.String r0 = "iw"
            boolean r0 = r5.equals(r0)
            if (r0 == 0) goto L40
            r0 = 0
            goto L41
        L36:
            java.lang.String r0 = "in"
            boolean r0 = r5.equals(r0)
            if (r0 == 0) goto L40
            r0 = 2
            goto L41
        L40:
            r0 = -1
        L41:
            if (r0 == 0) goto L53
            if (r0 == r4) goto L50
            if (r0 == r3) goto L4d
            if (r0 == r2) goto L4a
            return r5
        L4a:
            java.lang.String r5 = "fil"
            return r5
        L4d:
            java.lang.String r5 = "id"
            return r5
        L50:
            java.lang.String r5 = "yi"
            return r5
        L53:
            java.lang.String r5 = "he"
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.base.LocaleUtils.a(java.lang.String):java.lang.String");
    }

    public static String b(Locale locale) {
        String strA = a(locale.getLanguage());
        String country = locale.getCountry();
        return (strA.equals("no") && country.equals("NO") && locale.getVariant().equals("NY")) ? "nn-NO" : country.isEmpty() ? strA : Z7.d(strA, "-", country);
    }

    public static String getDefaultCountryCode() {
        Es esE = Es.e();
        return esE.h("default-country-code") ? esE.f("default-country-code") : Locale.getDefault().getCountry();
    }

    public static String getDefaultLocaleListString() {
        if (Build.VERSION.SDK_INT < 24) {
            return getDefaultLocaleString();
        }
        LocaleList localeList = LocaleList.getDefault();
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < localeList.size(); i++) {
            Locale localeBuild = localeList.get(i);
            String language = localeBuild.getLanguage();
            String strA = a(language);
            if (!strA.equals(language)) {
                localeBuild = new Locale.Builder().setLocale(localeBuild).setLanguage(strA).build();
            }
            arrayList.add(b(localeBuild));
        }
        return TextUtils.join(",", arrayList);
    }

    public static String getDefaultLocaleString() {
        return b(Locale.getDefault());
    }
}
