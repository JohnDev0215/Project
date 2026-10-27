package com.byd.auto_photo;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.byd.util.w;

/* JADX INFO: loaded from: classes.dex */
public class OTAReceiver extends BroadcastReceiver {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Activity f1125a;

    public OTAReceiver(Activity activity) {
        this.f1125a = activity;
    }

    public void a() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.byd.ota.broadcast.ENTER_OTA_MODEL");
        intentFilter.addAction("com.byd.ota.broadcast.EXIT_OTA_MODEL");
        this.f1125a.registerReceiver(this, intentFilter);
    }

    public void b() {
        this.f1125a.unregisterReceiver(this);
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        w.c("OTAReceiver onReceive : " + intent.getAction());
        Activity activity = this.f1125a;
        if (activity != null) {
            activity.finish();
        }
    }
}
