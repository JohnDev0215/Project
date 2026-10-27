package com.byd.auto_photo;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

/* JADX INFO: loaded from: classes.dex */
public class ReceiverForVoice extends BroadcastReceiver {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Activity f1135a;

    public ReceiverForVoice() {
    }

    public ReceiverForVoice(Activity activity) {
        this.f1135a = activity;
    }

    public void a() {
        LocalBroadcastManager.getInstance(this.f1135a).registerReceiver(this, new IntentFilter("com.byd.ACTION_ALBUM_CLOSE"));
    }

    public void b() {
        LocalBroadcastManager.getInstance(this.f1135a).unregisterReceiver(this);
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if ("com.byd.ACTION_ALBUM_CLOSE".equals(intent.getAction())) {
            this.f1135a.finish();
        }
    }
}
