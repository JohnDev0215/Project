package org.chromium.chrome.browser.webapps;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import defpackage.hp3;
import defpackage.n3;
import defpackage.oj;
import defpackage.p53;
import defpackage.q11;
import defpackage.qq0;
import defpackage.s51;
import org.chromium.chrome.R$id;
import org.chromium.chrome.browser.customtabs.BaseCustomTabActivity;

/* JADX INFO: loaded from: classes2.dex */
public class WebappActivity extends BaseCustomTabActivity {
    @Override // org.chromium.chrome.browser.customtabs.BaseCustomTabActivity
    public final oj d3(Intent intent, int i) {
        if (intent == null) {
            return null;
        }
        return TextUtils.isEmpty(q11.r(intent, "org.chromium.chrome.browser.webapk_package_name")) ? n3.A(intent) : hp3.a(intent);
    }

    @Override // org.chromium.chrome.browser.customtabs.BaseCustomTabActivity, org.chromium.chrome.browser.init.AsyncInitializationActivity
    public final boolean e2(Intent intent) {
        String strR = q11.r(intent, "org.chromium.chrome.browser.webapk_package_name");
        return (strR == null || strR.startsWith("org.chromium.webapk")) ? false : true;
    }

    @Override // org.chromium.chrome.browser.customtabs.BaseCustomTabActivity, org.chromium.chrome.browser.app.ChromeActivity, defpackage.jf1
    public final boolean h1(int i, boolean z) {
        if (i == R$id.bookmark_this_page_id) {
            return true;
        }
        if (i != R$id.open_in_browser_id) {
            return super.h1(i, z);
        }
        this.e1.c();
        if (z) {
            n3.X0("WebappMenuOpenInChrome");
        } else {
            n3.X0("Webapp.NotificationOpenInChrome");
        }
        return true;
    }

    @Override // org.chromium.chrome.browser.app.ChromeActivity, org.chromium.chrome.browser.init.AsyncInitializationActivity, defpackage.jp
    public final void l() {
        super.l();
        ((qq0) E2()).i();
    }

    @Override // org.chromium.chrome.browser.app.ChromeActivity
    public final s51 l2() {
        c cVar = this.k1;
        return new p53(this, cVar == null ? null : cVar.f);
    }

    @Override // org.chromium.chrome.browser.app.ChromeActivity
    public final Drawable y2() {
        return null;
    }
}
