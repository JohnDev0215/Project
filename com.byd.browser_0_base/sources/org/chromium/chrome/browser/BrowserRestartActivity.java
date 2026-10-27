package org.chromium.chrome.browser;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Process;
import defpackage.q11;
import defpackage.xz;

/* JADX INFO: loaded from: classes.dex */
public class BrowserRestartActivity extends Activity {
    public static final /* synthetic */ int e = 0;

    @Override // android.app.Activity
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Intent intent = getIntent();
        Process.killProcess(q11.l(intent, "org.chromium.chrome.browser.BrowserRestartActivity.main_pid", -1));
        if (q11.i(intent, "org.chromium.chrome.browser.BrowserRestartActivity.restart", false)) {
            Context context = xz.a;
            Intent intent2 = new Intent("android.intent.action.MAIN");
            intent2.setPackage(context.getPackageName());
            intent2.setFlags(268435456);
            context.startActivity(intent2);
        }
        finish();
        Process.killProcess(Process.myPid());
    }
}
