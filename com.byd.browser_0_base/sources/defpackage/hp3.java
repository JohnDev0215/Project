package defpackage;

import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.chromium.chrome.browser.ShortcutHelper;
import org.chromium.chrome.browser.b;

/* JADX INFO: compiled from: WebApkIntentDataProviderFactory.java */
/* JADX INFO: loaded from: classes2.dex */
public final class hp3 {
    public static ms3 a(Intent intent) {
        bk2 bk2Var;
        List<Uri> list;
        int i;
        Uri uri;
        String strR = q11.r(intent, "org.chromium.chrome.browser.webapk_package_name");
        if (TextUtils.isEmpty(strR)) {
            return null;
        }
        boolean zI = q11.i(intent, "org.chromium.chrome.browser.webapk_force_navigation", true);
        String strR2 = q11.r(intent, "org.chromium.webapk.selected_share_target_activity_class_name");
        if (TextUtils.isEmpty(strR2)) {
            bk2Var = null;
        } else {
            String strR3 = q11.r(intent, "android.intent.extra.SUBJECT");
            String strR4 = q11.r(intent, "android.intent.extra.TEXT");
            ArrayList arrayListC = q11.c(intent, "android.intent.extra.STREAM");
            if (arrayListC == null && (uri = (Uri) q11.n(intent, "android.intent.extra.STREAM")) != null) {
                arrayListC = new ArrayList();
                arrayListC.add(uri);
            }
            bk2Var = new bk2(strR3, strR4, arrayListC);
        }
        String strR5 = q11.r(intent, "org.chromium.chrome.browser.webapp_url");
        int iL = q11.l(intent, "org.chromium.chrome.browser.webapp_source", 0);
        if (iL >= 17) {
            i = 0;
        } else {
            if (iL == 9 && b.c(intent) == 5) {
                iL = 14;
            } else if (iL == 13 && bk2Var != null && (list = bk2Var.c) != null && list.size() > 0) {
                iL = 15;
            }
            i = iL;
        }
        return b(intent, strR, strR5, i, zI, q11.i(intent, "org.chromium.chrome.browser.webapk.splash_provided_by_webapk", false), bk2Var, strR2);
    }

    /* JADX WARN: Removed duplicated region for block: B:132:0x0261 A[PHI: r11 r14
  0x0261: PHI (r11v13 int) = (r11v2 int), (r11v9 int) binds: [B:131:0x025f, B:218:0x0261] A[DONT_GENERATE, DONT_INLINE]
  0x0261: PHI (r14v14 int) = (r14v0 int), (r14v7 int) binds: [B:131:0x025f, B:218:0x0261] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:153:0x02c5  */
    /* JADX WARN: Removed duplicated region for block: B:159:0x02e0  */
    /* JADX WARN: Removed duplicated region for block: B:177:0x0351  */
    /* JADX WARN: Removed duplicated region for block: B:222:0x0266 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0089  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x00ea  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static defpackage.ms3 b(android.content.Intent r53, java.lang.String r54, java.lang.String r55, int r56, boolean r57, boolean r58, defpackage.bk2 r59, java.lang.String r60) {
        /*
            Method dump skipped, instruction units count: 1098
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.hp3.b(android.content.Intent, java.lang.String, java.lang.String, int, boolean, boolean, bk2, java.lang.String):ms3");
    }

    public static ms3 c(Intent intent, String str, String str2, ks3 ks3Var, ks3 ks3Var2, String str3, String str4, int i, int i2, int i3, long j, long j2, int i4, boolean z, boolean z2, String str5, int i5, String str6, String str7, String str8, String str9, int i6, HashMap map, up3 up3Var, boolean z3, boolean z4, bk2 bk2Var, ArrayList arrayList, int i7) {
        if (str7 == null || str5 == null) {
            Log.e("cr_WebApkInfo", "Incomplete data provided: " + str7 + ", " + str5);
            return null;
        }
        String str10 = TextUtils.isEmpty(str) ? str7 : str;
        String strB = TextUtils.isEmpty(str2) ? ShortcutHelper.b(str7) : str2;
        String str11 = TextUtils.isEmpty(str9) ? str6 : str9;
        js3 js3Var = new js3(ns3.b(str5), str10, strB, ks3Var, str3, str4, i, i2, i3, (j2 > 2147483648L ? 1 : (j2 == 2147483648L ? 0 : -1)) != 0 ? Integer.valueOf((int) j2) : null, i4, false, z, z3);
        cp3 cp3Var = new cp3(str5, ks3Var2, z2, i5, str6, str7, str8, str11, i6, map, up3Var, z4, arrayList, i7);
        boolean z5 = j != 2147483648L;
        return new ms3(intent, z5 ? (int) j : -1, z5, bk2Var, js3Var, cp3Var);
    }
}
