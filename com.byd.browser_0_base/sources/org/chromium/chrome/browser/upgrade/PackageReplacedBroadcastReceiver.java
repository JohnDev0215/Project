package org.chromium.chrome.browser.upgrade;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import defpackage.d73;
import defpackage.f52;
import org.chromium.base.task.PostTask;

/* JADX INFO: loaded from: classes2.dex */
public final class PackageReplacedBroadcastReceiver extends BroadcastReceiver {
    public static final /* synthetic */ int a = 0;

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        if ("android.intent.action.MY_PACKAGE_REPLACED".equals(intent.getAction())) {
            PostTask.c(d73.g, new f52(22, goAsync()));
        }
    }
}
