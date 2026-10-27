package org.chromium.components.policy;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import defpackage.p22;
import org.chromium.base.ThreadUtils;

/* JADX INFO: loaded from: classes3.dex */
public abstract class AbstractAppRestrictionsProvider extends p22 {
    public final Context c;
    public final a d = new a();

    public class a extends BroadcastReceiver {
        public a() {
        }

        @Override // android.content.BroadcastReceiver
        public final void onReceive(Context context, Intent intent) {
            AbstractAppRestrictionsProvider.this.b();
        }
    }

    public AbstractAppRestrictionsProvider(Context context) {
        this.c = context;
    }

    @Override // defpackage.p22
    public final void b() {
        StrictMode.ThreadPolicy threadPolicyAllowThreadDiskReads = StrictMode.allowThreadDiskReads();
        System.currentTimeMillis();
        Bundle bundleC = c(this.c.getPackageName());
        StrictMode.setThreadPolicy(threadPolicyAllowThreadDiskReads);
        a(bundleC);
    }

    public abstract Bundle c(String str);

    public abstract void d();

    public final void e() {
        d();
        this.c.registerReceiver(this.d, new IntentFilter("android.intent.action.APPLICATION_RESTRICTIONS_CHANGED"), null, new Handler(ThreadUtils.d()), 0);
    }
}
