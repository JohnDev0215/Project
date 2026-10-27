package org.chromium.chrome.browser.customtabs;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import defpackage.n3;

/* JADX INFO: loaded from: classes.dex */
public final class CustomTabsShareBroadcastReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        n3.X0("MobileTopToolbarShareButton");
        String dataString = intent.getDataString();
        Intent intent2 = new Intent("android.intent.action.SEND");
        intent2.setType("text/plain");
        intent2.putExtra("android.intent.extra.TEXT", dataString);
        Intent intentCreateChooser = Intent.createChooser(intent2, null);
        intentCreateChooser.setFlags(268435456);
        context.startActivity(intentCreateChooser);
    }
}
