package org.chromium.chrome.browser.partnercustomizations;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import defpackage.i9;
import defpackage.iv;
import defpackage.o50;
import defpackage.p50;
import defpackage.qm0;
import defpackage.rh3;
import defpackage.xz;
import java.util.ArrayList;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.task.PostTask;
import org.chromium.url.GURL;

/* JADX INFO: loaded from: classes2.dex */
public class PartnerBrowserCustomizations {
    public static volatile PartnerBrowserCustomizations g;
    public volatile GURL a;
    public volatile boolean b;
    public volatile boolean c;
    public boolean d;
    public final ArrayList e = new ArrayList();
    public a f;

    public interface a {
    }

    public static PartnerBrowserCustomizations b() {
        if (g == null) {
            g = new PartnerBrowserCustomizations();
        }
        return g;
    }

    @CalledByNative
    public static boolean isIncognitoDisabled() {
        return b().b;
    }

    public final GURL a() {
        iv ivVarE = iv.e();
        return ivVarE.g("partner-homepage-for-testing") ? new GURL(ivVarE.f("partner-homepage-for-testing")) : this.a;
    }

    public final void c(Context context) {
        this.d = false;
        org.chromium.chrome.browser.partnercustomizations.a aVar = new org.chromium.chrome.browser.partnercustomizations.a(context, this);
        aVar.c(i9.e);
        PostTask.b(rh3.a, new qm0(18, aVar), 10000L);
    }

    public final boolean d() {
        GURL gurlA = a();
        return (gurlA == null || gurlA.k()) ? false : true;
    }

    public final void e(o50 o50Var) {
        try {
            boolean z = false;
            if (p50.b()) {
                Cursor cursorQuery = xz.a.getContentResolver().query(p50.a("disablebookmarksediting"), null, null, null, null);
                if (cursorQuery != null && cursorQuery.moveToFirst() && cursorQuery.getColumnCount() == 1 && cursorQuery.getInt(0) == 1) {
                    z = true;
                }
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
            }
            this.c = z;
        } catch (Exception e) {
            Log.w("cr_PartnerCustomize", "Partner disable bookmarks editing read failed : ", e);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:39:0x0092  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0093  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00a1 A[Catch: Exception -> 0x00b6, TryCatch #0 {Exception -> 0x00b6, blocks: (B:3:0x0001, B:17:0x003a, B:19:0x0042, B:21:0x004a, B:41:0x0094, B:43:0x00a1, B:45:0x00a8, B:44:0x00a6, B:25:0x0057, B:27:0x005d, B:29:0x0069, B:32:0x0070, B:34:0x007c, B:36:0x0088, B:22:0x004f, B:6:0x000b, B:8:0x0021, B:10:0x0027, B:12:0x002d, B:15:0x0035), top: B:50:0x0001 }] */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00a6 A[Catch: Exception -> 0x00b6, TryCatch #0 {Exception -> 0x00b6, blocks: (B:3:0x0001, B:17:0x003a, B:19:0x0042, B:21:0x004a, B:41:0x0094, B:43:0x00a1, B:45:0x00a8, B:44:0x00a6, B:25:0x0057, B:27:0x005d, B:29:0x0069, B:32:0x0070, B:34:0x007c, B:36:0x0088, B:22:0x004f, B:6:0x000b, B:8:0x0021, B:10:0x0027, B:12:0x002d, B:15:0x0035), top: B:50:0x0001 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean f(defpackage.o50 r10) {
        /*
            r9 = this;
            r10 = 0
            boolean r0 = defpackage.p50.b()     // Catch: java.lang.Exception -> Lb6
            r1 = 1
            r2 = 0
            if (r0 != 0) goto Lb
            r3 = r2
            goto L38
        Lb:
            android.content.Context r0 = defpackage.xz.a     // Catch: java.lang.Exception -> Lb6
            android.content.ContentResolver r3 = r0.getContentResolver()     // Catch: java.lang.Exception -> Lb6
            java.lang.String r0 = "homepage"
            android.net.Uri r4 = defpackage.p50.a(r0)     // Catch: java.lang.Exception -> Lb6
            r5 = 0
            r6 = 0
            r7 = 0
            r8 = 0
            android.database.Cursor r0 = r3.query(r4, r5, r6, r7, r8)     // Catch: java.lang.Exception -> Lb6
            if (r0 == 0) goto L32
            boolean r3 = r0.moveToFirst()     // Catch: java.lang.Exception -> Lb6
            if (r3 == 0) goto L32
            int r3 = r0.getColumnCount()     // Catch: java.lang.Exception -> Lb6
            if (r3 != r1) goto L32
            java.lang.String r3 = r0.getString(r10)     // Catch: java.lang.Exception -> Lb6
            goto L33
        L32:
            r3 = r2
        L33:
            if (r0 == 0) goto L38
            r0.close()     // Catch: java.lang.Exception -> Lb6
        L38:
            if (r3 == 0) goto L4f
            java.lang.String r0 = "about:"
            boolean r0 = r3.startsWith(r0)     // Catch: java.lang.Exception -> Lb6
            if (r0 != 0) goto L4a
            java.lang.String r0 = "chrome:"
            boolean r0 = r3.startsWith(r0)     // Catch: java.lang.Exception -> Lb6
            if (r0 == 0) goto L4f
        L4a:
            org.chromium.url.GURL r0 = defpackage.oj2.h(r3)     // Catch: java.lang.Exception -> Lb6
            goto L54
        L4f:
            org.chromium.url.GURL r0 = new org.chromium.url.GURL     // Catch: java.lang.Exception -> Lb6
            r0.<init>(r3)     // Catch: java.lang.Exception -> Lb6
        L54:
            if (r0 != 0) goto L57
            goto L8f
        L57:
            boolean r3 = r0.b     // Catch: java.lang.Exception -> Lb6
            java.lang.String r4 = "PartnerCustomize"
            if (r3 == 0) goto L88
            java.util.regex.Pattern r3 = defpackage.bk3.a     // Catch: java.lang.Exception -> Lb6
            java.lang.String r3 = r0.i()     // Catch: java.lang.Exception -> Lb6
            boolean r3 = defpackage.bk3.m(r3)     // Catch: java.lang.Exception -> Lb6
            if (r3 != 0) goto L70
            boolean r3 = defpackage.bk3.l(r0)     // Catch: java.lang.Exception -> Lb6
            if (r3 != 0) goto L70
            goto L88
        L70:
            java.lang.String r3 = r0.j()     // Catch: java.lang.Exception -> Lb6
            int r3 = r3.length()     // Catch: java.lang.Exception -> Lb6
            r5 = 1000(0x3e8, float:1.401E-42)
            if (r3 <= r5) goto L86
            java.lang.String r3 = "The homepage URL \"%s\" is too long."
            java.lang.String r5 = r0.j()     // Catch: java.lang.Exception -> Lb6
            defpackage.fa1.k(r4, r3, r5)     // Catch: java.lang.Exception -> Lb6
            goto L8f
        L86:
            r3 = r1
            goto L90
        L88:
            java.lang.String r3 = r0.a     // Catch: java.lang.Exception -> Lb6
            java.lang.String r5 = "Partner homepage must be HTTP(S) or NewTabPage. Got invalid URL \"%s\""
            defpackage.fa1.k(r4, r5, r3)     // Catch: java.lang.Exception -> Lb6
        L8f:
            r3 = r10
        L90:
            if (r3 != 0) goto L93
            goto L94
        L93:
            r2 = r0
        L94:
            org.chromium.url.GURL r0 = r9.a     // Catch: java.lang.Exception -> Lb6
            boolean r10 = java.util.Objects.equals(r0, r2)     // Catch: java.lang.Exception -> Lb6
            r10 = r10 ^ r1
            r9.a = r2     // Catch: java.lang.Exception -> Lb6
            org.chromium.url.GURL r0 = r9.a     // Catch: java.lang.Exception -> Lb6
            if (r0 != 0) goto La6
            org.chromium.url.GURL r9 = org.chromium.url.GURL.emptyGURL()     // Catch: java.lang.Exception -> Lb6
            goto La8
        La6:
            org.chromium.url.GURL r9 = r9.a     // Catch: java.lang.Exception -> Lb6
        La8:
            java.lang.String r9 = r9.m()     // Catch: java.lang.Exception -> Lb6
            org.chromium.chrome.browser.preferences.SharedPreferencesManager r0 = org.chromium.chrome.browser.preferences.SharedPreferencesManager.getInstance()     // Catch: java.lang.Exception -> Lb6
            java.lang.String r1 = "Chrome.Homepage.PartnerCustomizedDefaultGurl"
            r0.o(r1, r9)     // Catch: java.lang.Exception -> Lb6
            goto Lbe
        Lb6:
            r9 = move-exception
            java.lang.String r0 = "cr_PartnerCustomize"
            java.lang.String r1 = "Partner homepage delegate URL read failed : "
            android.util.Log.w(r0, r1, r9)
        Lbe:
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.partnercustomizations.PartnerBrowserCustomizations.f(o50):boolean");
    }

    public final void g(o50 o50Var) {
        try {
            boolean z = false;
            if (p50.b()) {
                Cursor cursorQuery = xz.a.getContentResolver().query(p50.a("disableincognitomode"), null, null, null, null);
                if (cursorQuery != null && cursorQuery.moveToFirst() && cursorQuery.getColumnCount() == 1 && cursorQuery.getInt(0) == 1) {
                    z = true;
                }
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
            }
            this.b = z;
        } catch (Exception e) {
            Log.w("cr_PartnerCustomize", "Partner disable incognito mode read failed : ", e);
        }
    }

    public final void h(Runnable runnable) {
        if (this.d) {
            PostTask.c(rh3.a, runnable);
        } else {
            this.e.add(runnable);
        }
    }
}
