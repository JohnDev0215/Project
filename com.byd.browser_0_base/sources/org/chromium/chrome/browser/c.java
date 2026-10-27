package org.chromium.chrome.browser;

import android.app.Activity;
import android.content.Intent;
import android.os.SystemClock;
import defpackage.ku2;
import defpackage.oj2;
import defpackage.q11;
import defpackage.wt1;
import defpackage.xz;
import defpackage.zo3;
import org.chromium.chrome.browser.b;
import org.chromium.chrome.browser.searchwidget.SearchActivity;
import org.chromium.content_public.browser.LoadUrlParams;

/* JADX INFO: compiled from: LaunchIntentDispatcher.java */
/* JADX INFO: loaded from: classes.dex */
public final class c implements b.a {
    public final Activity a;
    public final Intent b;

    public c(Activity activity, Intent intent) {
        int intExtra;
        this.a = activity;
        Intent intentU = q11.u(intent);
        this.b = intentU;
        if (intentU != null && intentU.getLongExtra("org.chromium.chrome.browser.timestamp", -1L) == -1) {
            intentU.putExtra("org.chromium.chrome.browser.timestamp", SystemClock.elapsedRealtime());
        }
        if (intentU == null || (intExtra = intentU.getIntExtra("org.chromium.chrome.browser.metrics.org.chromium.components.browser_ui.media.MediaNotificationUma.EXTRA_CLICK_SOURCE", -1)) == -1 || intExtra >= 3) {
            return;
        }
        oj2.U(intExtra, 3, "Media.Notification.Click");
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0049  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0092  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0095  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static android.content.Intent e(android.content.Context r4, android.content.Intent r5) {
        /*
            Method dump skipped, instruction units count: 259
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.c.e(android.content.Context, android.content.Intent):android.content.Intent");
    }

    public static boolean g(Intent intent) {
        if (intent == null) {
            return false;
        }
        return ((intent.getBooleanExtra("android.support.customtabs.extra.user_opt_out", false) && (intent.getFlags() & 268435456) != 0) || !intent.hasExtra("android.support.customtabs.extra.SESSION") || b.o(intent) == null) ? false : true;
    }

    @Override // org.chromium.chrome.browser.b.a
    public final long a() {
        return 0L;
    }

    @Override // org.chromium.chrome.browser.b.a
    public final void c(String str) {
        Intent intent = new Intent("android.intent.action.WEB_SEARCH");
        intent.putExtra("query", str);
        ku2 ku2VarT = ku2.t();
        try {
            boolean zA = wt1.a(intent, 64);
            Activity activity = this.a;
            if (zA) {
                activity.startActivity(intent);
            } else {
                Intent intent2 = new Intent("android.intent.action.MAIN");
                intent2.setClass(xz.a, SearchActivity.class);
                intent2.putExtra("query", str);
                activity.startActivity(intent2);
            }
            ku2VarT.close();
        } catch (Throwable th) {
            try {
                ku2VarT.close();
            } catch (Throwable unused) {
            }
            throw th;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x0063, code lost:
    
        r0 = !defpackage.k5.b(r13, org.chromium.chrome.browser.ChromeTabbedActivity.T1).isEmpty();
     */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00bc  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x0134  */
    @android.annotation.SuppressLint({"InlinedApi"})
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final int f() {
        /*
            Method dump skipped, instruction units count: 395
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.c.f():int");
    }

    /* JADX WARN: Removed duplicated region for block: B:59:0x00ea  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean h(org.chromium.chrome.browser.b r9) {
        /*
            Method dump skipped, instruction units count: 382
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.c.h(org.chromium.chrome.browser.b):boolean");
    }

    public final void i() {
        String strO;
        Intent intent = this.b;
        if (intent == null || !"android.intent.action.VIEW".equals(intent.getAction()) || (strO = b.o(intent)) == null) {
            return;
        }
        zo3.a().d(strO);
    }

    @Override // org.chromium.chrome.browser.b.a
    public final void b(String str, String str2) {
    }

    @Override // org.chromium.chrome.browser.b.a
    public final void d(LoadUrlParams loadUrlParams, int i, String str, int i2, Intent intent) {
    }
}
