package org.chromium.chrome.browser.webapps;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;

/* JADX INFO: loaded from: classes2.dex */
public class WebappLauncherActivity extends Activity {
    public static final /* synthetic */ int e = 0;

    public static class a {
        public final String a;
        public final String b;
        public final boolean c;
        public final String d;
        public final boolean e;

        public a(String str, String str2, String str3, boolean z) {
            this.a = str;
            this.b = str2;
            this.c = !TextUtils.isEmpty(str3);
            this.d = str3;
            this.e = z;
        }
    }

    public static Intent a(Intent intent, String str, String str2) {
        Intent intent2 = new Intent("android.intent.action.VIEW", Uri.parse(str2));
        intent2.setPackage(str);
        intent2.setFlags(268959744);
        Bundle extras = intent.getExtras();
        if (extras != null) {
            intent2.putExtras(extras);
        }
        return intent2;
    }

    /* JADX WARN: Removed duplicated region for block: B:63:0x0113  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x011d  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x0127  */
    @Override // android.app.Activity
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void onCreate(android.os.Bundle r14) {
        /*
            Method dump skipped, instruction units count: 577
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.webapps.WebappLauncherActivity.onCreate(android.os.Bundle):void");
    }
}
