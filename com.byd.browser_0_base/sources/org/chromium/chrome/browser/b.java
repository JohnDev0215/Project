package org.chromium.chrome.browser;

import J.N;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import androidx.browser.customtabs.CustomTabsSessionToken;
import defpackage.C0175b;
import defpackage.bk3;
import defpackage.d40;
import defpackage.ft1;
import defpackage.n3;
import defpackage.oj2;
import defpackage.on2;
import defpackage.pp;
import defpackage.q11;
import defpackage.s50;
import defpackage.xz;
import defpackage.zi0;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Map;
import org.chromium.chrome.browser.browserservices.verification.ChromeOriginVerifier;
import org.chromium.chrome.browser.customtabs.CustomTabsConnection;
import org.chromium.chrome.browser.document.ChromeLauncherActivity;
import org.chromium.content_public.browser.LoadUrlParams;

/* JADX INFO: compiled from: IntentHandler.java */
/* JADX INFO: loaded from: classes.dex */
public final class b {
    public static Pair<Integer, String> c;
    public static int d;
    public static String e;
    public static boolean f;
    public static DelayedScreenLockIntentHandler g;
    public final a a;
    public final Activity b;

    /* JADX INFO: compiled from: IntentHandler.java */
    public interface a {
        long a();

        void b(String str, String str2);

        void c(String str);

        void d(LoadUrlParams loadUrlParams, int i, String str, int i2, Intent intent);
    }

    public b(Activity activity, a aVar) {
        this.a = aVar;
        this.b = activity;
    }

    public static void A(Context context, Intent intent, String str) {
        if (context == null) {
            context = xz.a;
        }
        Intent intent2 = new Intent(intent);
        if (str != null) {
            intent2.setComponent(new ComponentName(context.getPackageName(), str));
        }
        q11.a(intent2);
        context.startActivity(intent2);
    }

    public static Intent a(int i, int i2) {
        Context context = xz.a;
        Intent intent = new Intent(context, (Class<?>) ChromeLauncherActivity.class);
        intent.putExtra("com.android.browser.application_id", context.getPackageName());
        intent.putExtra("BRING_TAB_TO_FRONT", i);
        intent.putExtra("BRING_TAB_TO_FRONT_SOURCE", i2);
        q11.a(intent);
        return intent;
    }

    public static Intent b(Context context, boolean z) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        intent.setData(Uri.parse("chrome-native://newtab/"));
        intent.setClass(context, ChromeLauncherActivity.class);
        intent.putExtra("create_new_tab", true);
        intent.putExtra("com.android.browser.application_id", context.getPackageName());
        intent.putExtra("com.google.android.apps.chrome.EXTRA_OPEN_NEW_INCOGNITO_TAB", z);
        q11.a(intent);
        return intent;
    }

    public static int c(Intent intent) {
        if (q11.g(intent)) {
            return 5;
        }
        String strR = q11.r(intent, "com.android.browser.application_id");
        if (strR != null) {
            return t(strR);
        }
        String strO = o(intent);
        String strH = h(intent);
        if (strO != null && strO.startsWith("http://t.co/")) {
            return 4;
        }
        if (!"android-app://m.facebook.com".equals(strH)) {
            if (strO != null && strO.startsWith("http://news.google.com/news/url?")) {
                return 8;
            }
            if (strO != null && (strO.startsWith("https://www.youtube.com/redirect?") || strO.startsWith("http://www.youtube.com/redirect?"))) {
                return 15;
            }
            Bundle bundleJ = q11.j(intent, "com.android.browser.headers");
            if (bundleJ == null || !"http://m.facebook.com".equals(bundleJ.get("Referer"))) {
                return 0;
            }
        }
        return 2;
    }

    public static void d(String str, String str2, ArrayList arrayList) {
        int iIndexOf;
        int length;
        for (int i = 0; i < str.length() && (iIndexOf = str.indexOf(str2, i)) != -1; i = length) {
            length = str2.length() + iIndexOf;
            while (true) {
                if (length >= str.length()) {
                    break;
                }
                if (Character.isWhitespace(str.charAt(length))) {
                    arrayList.add(str.substring(iIndexOf, length));
                    break;
                }
                length++;
            }
            if (length >= str.length()) {
                arrayList.add(str.substring(iIndexOf));
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:35:0x0090  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00af  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String e(android.content.Intent r6) {
        /*
            Method dump skipped, instruction units count: 232
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.b.e(android.content.Intent):java.lang.String");
    }

    public static int f(Intent intent) {
        if (q11.g(intent)) {
            return q11.l(intent, "BRING_TAB_TO_FRONT", -1);
        }
        return -1;
    }

    public static String g(Intent intent) {
        boolean zH;
        ft1 ft1VarA;
        Bundle bundleJ = q11.j(intent, "com.android.browser.headers");
        if (bundleJ == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        boolean zG = q11.g(intent);
        CustomTabsConnection customTabsConnectionE = CustomTabsConnection.e();
        customTabsConnectionE.getClass();
        CustomTabsSessionToken customTabsSessionTokenB = CustomTabsSessionToken.b(intent);
        if (customTabsSessionTokenB == null || (ft1VarA = ft1.a(intent.getData())) == null) {
            zH = false;
        } else {
            org.chromium.chrome.browser.customtabs.a aVar = customTabsConnectionE.c;
            synchronized (aVar) {
                zH = ChromeOriginVerifier.h(aVar.c(customTabsSessionTokenB), ft1VarA);
            }
        }
        for (String str : bundleJ.keySet()) {
            String string = bundleJ.getString(str);
            if (N.MorcXgQd(str, string)) {
                Locale locale = Locale.US;
                if (!"x-chrome-intent-type".equals(str.toLowerCase(locale))) {
                    if (!zG) {
                        if (str.toLowerCase(locale).startsWith("x-chrome-")) {
                            Log.w("cr_IntentHandler", "Ignoring x-chrome header " + str + " in EXTRA_HEADERS.");
                        } else if (!zH && !N.MUs5WTJu(str, string)) {
                            Log.w("cr_IntentHandler", "Ignoring non-CORS-safelisted header " + str + " in EXTRA_HEADERS.");
                        }
                    }
                    if (sb.length() != 0) {
                        sb.append("\n");
                    }
                    sb.append(str);
                    sb.append(": ");
                    sb.append(string);
                }
            } else {
                Log.w("cr_IntentHandler", "Ignoring forbidden header " + str + " in EXTRA_HEADERS.");
            }
        }
        if (sb.length() == 0) {
            return null;
        }
        return sb.toString();
    }

    public static String h(Intent intent) {
        ft1 ft1VarA;
        on2 on2VarD;
        Uri uri = (Uri) q11.n(intent, "android.intent.extra.REFERRER");
        boolean z = false;
        if (uri != null) {
            int iL = q11.l(intent, "org.chromium.chrome.browser.referrer_id", 0);
            Pair<Integer, String> pair = c;
            String str = (pair == null || ((Integer) pair.first).intValue() != iL) ? null : (String) c.second;
            if (!TextUtils.isEmpty(str)) {
                uri = Uri.parse(str);
            }
        } else {
            String strR = q11.r(intent, "android.intent.extra.REFERRER_NAME");
            uri = strR != null ? Uri.parse(strR) : null;
        }
        CustomTabsSessionToken customTabsSessionTokenB = CustomTabsSessionToken.b(intent);
        if (uri == null && customTabsSessionTokenB != null && (on2VarD = CustomTabsConnection.e().c.d(customTabsSessionTokenB)) != null) {
            uri = Uri.parse((String) on2VarD.b);
        }
        if (uri == null) {
            return null;
        }
        if (s(uri)) {
            return uri.toString();
        }
        if (!v(intent)) {
            d40 d40VarA = ((s50) pp.d()).i().a(customTabsSessionTokenB);
            if (d40VarA != null) {
                String strD = d40VarA.i.d(d40VarA.e.z());
                if ((TextUtils.isEmpty(strD) || (ft1VarA = ft1.a(uri)) == null) ? false : ChromeOriginVerifier.h(strD, ft1VarA)) {
                    z = true;
                }
            }
            if (!z) {
                return null;
            }
        }
        return uri.toString();
    }

    public static String i(Intent intent) {
        String strH = h(intent);
        if (strH != null) {
            return strH;
        }
        Bundle bundleJ = q11.j(intent, "com.android.browser.headers");
        if (bundleJ == null) {
            return null;
        }
        for (String str : bundleJ.keySet()) {
            String string = bundleJ.getString(str);
            if (string != null && "referer".equals(str.toLowerCase(Locale.US))) {
                Uri uriNormalizeScheme = Uri.parse(string).normalizeScheme();
                if (s(uriNormalizeScheme)) {
                    return uriNormalizeScheme.toString();
                }
            }
        }
        return null;
    }

    public static String j(String str) {
        int iIndexOf;
        if (str == null || (iIndexOf = str.indexOf(":")) < 0) {
            return null;
        }
        boolean z = false;
        String strTrim = str.substring(0, iIndexOf).toLowerCase(Locale.US).trim();
        int i = 0;
        while (true) {
            if (i >= strTrim.length()) {
                break;
            }
            char cCharAt = strTrim.charAt(i);
            if (!Character.isLetterOrDigit(cCharAt) && cCharAt != '-' && cCharAt != '+' && cCharAt != '.') {
                z = true;
                break;
            }
            i++;
        }
        return z ? strTrim.replaceAll("[^a-z0-9.+-]", "") : strTrim;
    }

    public static int k(Intent intent) {
        if (q11.g(intent)) {
            return q11.l(intent, "com.android.chrome.tab_id", -1);
        }
        return -1;
    }

    public static long l(Intent intent) {
        return intent.getLongExtra("org.chromium.chrome.browser.timestamp", -1L);
    }

    public static int m(Intent intent, int i) {
        if (intent == null) {
            return i;
        }
        int iL = q11.l(intent, "com.google.chrome.transition_type", 0);
        return iL == 1 ? iL : (iL == 0 || !v(intent)) ? i : iL;
    }

    public static String n(String str) {
        if (!str.toLowerCase(Locale.US).startsWith("googlechrome://navigate?url=")) {
            return null;
        }
        String strSubstring = str.substring(28);
        if (!TextUtils.isEmpty(strSubstring) && j(strSubstring) == null) {
            strSubstring = C0175b.j("http://", strSubstring);
        }
        if (bk3.h(strSubstring)) {
            return strSubstring;
        }
        return null;
    }

    public static String o(Intent intent) {
        String scheme;
        String strE = e(intent);
        boolean z = false;
        if (strE != null && (scheme = Uri.parse(strE).getScheme()) != null && scheme.equals("googlechrome")) {
            z = true;
        }
        return z ? n(strE) : strE;
    }

    public static boolean q(Bundle bundle) {
        if (bundle == null) {
            return false;
        }
        return q11.h(bundle, "org.chromium.chrome.browser.incognito_mode", false) || q11.h(bundle, "com.google.android.apps.chrome.EXTRA_OPEN_NEW_INCOGNITO_TAB", false) || q11.h(bundle, "org.chromium.chrome.browser.incognito.invoked_from_launch_new_incognito_tab", false);
    }

    public static boolean r(Intent intent, String str, String str2) {
        if (str != null && (intent.hasCategory("android.intent.category.BROWSABLE") || intent.hasCategory("android.intent.category.DEFAULT") || intent.getCategories() == null)) {
            Locale locale = Locale.US;
            String lowerCase = str.toLowerCase(locale);
            if ("chrome".equals(lowerCase) || "chrome-native".equals(lowerCase) || "about".equals(lowerCase)) {
                String lowerCase2 = str2.toLowerCase(locale);
                if (!"about:blank".equals(lowerCase2) && !"about://blank".equals(lowerCase2) && !"chrome://dino".equals(lowerCase2)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean s(Uri uri) {
        if (uri == null) {
            return false;
        }
        Uri uriNormalizeScheme = uri.normalizeScheme();
        return TextUtils.equals(uriNormalizeScheme.getScheme(), "android-app") && !TextUtils.isEmpty(uriNormalizeScheme.getHost());
    }

    public static int t(String str) {
        if (str.equals("com.google.android.apps.plus")) {
            return 3;
        }
        if (str.equals("com.google.android.gm")) {
            return 1;
        }
        if (str.equals("com.google.android.talk")) {
            return 6;
        }
        if (str.equals("com.google.android.apps.messaging")) {
            return 7;
        }
        if (str.equals("jp.naver.line.android")) {
            return 9;
        }
        if (str.equals("com.whatsapp")) {
            return 10;
        }
        if (str.equals("com.google.android.googlequicksearchbox")) {
            return 11;
        }
        if (str.equals(xz.a.getPackageName())) {
            return 5;
        }
        if (str.startsWith("org.chromium.webapk")) {
            return 12;
        }
        if (str.equals("com.yahoo.mobile.client.android.mail")) {
            return 13;
        }
        return str.equals("com.viber.voip") ? 14 : 0;
    }

    public static String u(Intent intent, String str, String str2) {
        String type;
        if (intent == null || str == null || !TextUtils.equals(j(str), "content") || (type = intent.getType()) == null || type.isEmpty()) {
            return str2;
        }
        if (!(type.equals("multipart/related") || type.equals("message/rfc822"))) {
            return str2;
        }
        String strConcat = "X-Chrome-intent-type: ".concat(type);
        return str2 == null ? strConcat : C0175b.k(str2, "\n", strConcat);
    }

    @Deprecated
    public static boolean v(Intent intent) {
        if (intent == null) {
            return false;
        }
        if (q11.g(intent)) {
            return true;
        }
        PendingIntent pendingIntent = (PendingIntent) q11.n(intent, "trusted_application_code_extra");
        if (pendingIntent == null) {
            return false;
        }
        zi0 zi0Var = zi0.b;
        pendingIntent.getCreatorPackage();
        zi0Var.b();
        return false;
    }

    public static void y(Map<String, String> map, Intent intent) {
        if (map == null || map.isEmpty()) {
            intent.removeExtra("com.android.browser.headers");
            return;
        }
        Bundle bundle = new Bundle();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            bundle.putString(entry.getKey(), entry.getValue());
        }
        intent.putExtra("com.android.browser.headers", bundle);
    }

    public final boolean p(Intent intent) {
        if (intent == null) {
            return false;
        }
        String action = intent.getAction();
        String strR = ("android.intent.action.SEARCH".equals(action) || "android.intent.action.MEDIA_SEARCH".equals(action)) ? q11.r(intent, "query") : null;
        if (strR == null || TextUtils.isEmpty(strR)) {
            return false;
        }
        this.a.c(strR);
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:131:0x021a  */
    /* JADX WARN: Removed duplicated region for block: B:142:0x0241  */
    /* JADX WARN: Removed duplicated region for block: B:160:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean w(android.content.Intent r17) {
        /*
            Method dump skipped, instruction units count: 646
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.b.w(android.content.Intent):boolean");
    }

    public final void x(LoadUrlParams loadUrlParams, int i, String str, int i2, Intent intent) {
        this.a.d(loadUrlParams, i, str, i2, intent);
        int iC = c(intent);
        if (iC != 5) {
            oj2.U(iC, 16, "MobileIntent.PageLoadDueToExternalApp");
        }
        ArrayList<String> arrayListQ = q11.q(intent, "org.chromium.chrome.browser.eenp");
        if (arrayListQ == null || arrayListQ.size() <= 0) {
            return;
        }
        n3.X0("MobileExternalNavigationReceived");
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x004c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean z(android.content.Intent r7, boolean r8, boolean r9) {
        /*
            Method dump skipped, instruction units count: 290
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.b.z(android.content.Intent, boolean, boolean):boolean");
    }
}
