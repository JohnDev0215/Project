package org.chromium.chrome.browser;

import J.N;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import defpackage.wq0;
import defpackage.xz;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes.dex */
public class DelayedScreenLockIntentHandler extends BroadcastReceiver {
    public static final /* synthetic */ int f = 0;
    public final Handler a = new Handler();
    public final wq0 b = new wq0(10, this);
    public final WeakReference<Activity> c;
    public Intent d;
    public boolean e;

    public DelayedScreenLockIntentHandler(Activity activity) {
        this.c = new WeakReference<>(activity);
    }

    public final void a(Intent intent) {
        Handler handler = this.a;
        wq0 wq0Var = this.b;
        handler.removeCallbacks(wq0Var);
        handler.postDelayed(wq0Var, 10000L);
        this.d = intent;
        boolean z = intent != null;
        if (z == this.e) {
            return;
        }
        this.e = z;
        Context context = xz.a;
        if (z) {
            xz.g(context, this, new IntentFilter("android.intent.action.USER_PRESENT"));
        } else {
            context.unregisterReceiver(this);
        }
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        if (!"android.intent.action.USER_PRESENT".equals(intent.getAction()) || this.d == null) {
            return;
        }
        Activity activity = this.c.get();
        if (activity != null) {
            try {
                activity.startActivity(this.d);
            } catch (ActivityNotFoundException e) {
                N.MLlibBXh(false, e);
            }
        }
        a(null);
    }
}
