package org.chromium.chrome.browser.browserservices.ui.trustedwebactivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import defpackage.pj;
import defpackage.so1;
import defpackage.xz;
import org.chromium.chrome.browser.preferences.SharedPreferencesManager;
import org.chromium.components.browser_ui.notifications.NotificationManagerProxyImpl;

/* JADX INFO: loaded from: classes.dex */
public class DisclosureAcceptanceBroadcastReceiver extends BroadcastReceiver {
    public static final /* synthetic */ int c = 0;
    public final so1 a;
    public final pj b;

    public DisclosureAcceptanceBroadcastReceiver() {
        NotificationManagerProxyImpl notificationManagerProxyImpl = new NotificationManagerProxyImpl(xz.a);
        pj pjVar = new pj(SharedPreferencesManager.getInstance());
        this.a = notificationManagerProxyImpl;
        this.b = pjVar;
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        if (intent == null || !intent.hasExtra("TWADisclosureResp.tag_extra") || !intent.hasExtra("TWADisclosureResp.id_extra") || !intent.hasExtra("TWADisclosureResp.package_extra")) {
            Log.w("cr_TWADisclosureRec", "Started with null or incomplete Intent.");
            return;
        }
        String stringExtra = intent.getStringExtra("TWADisclosureResp.tag_extra");
        int intExtra = intent.getIntExtra("TWADisclosureResp.id_extra", -1);
        String stringExtra2 = intent.getStringExtra("TWADisclosureResp.package_extra");
        this.a.d(intExtra, stringExtra);
        this.b.a.b("trusted_web_activity_disclosure_accepted_packages", stringExtra2);
    }
}
