package org.chromium.chrome.browser.notifications.channels;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import defpackage.d73;
import defpackage.y70;
import org.chromium.base.task.PostTask;

/* JADX INFO: loaded from: classes2.dex */
public class LocaleChangedBroadcastReceiver extends BroadcastReceiver {
    public static final /* synthetic */ int a = 0;

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        if ("android.intent.action.LOCALE_CHANGED".equals(intent.getAction())) {
            PostTask.c(d73.g, new y70(this, 8, goAsync()));
        }
    }
}
