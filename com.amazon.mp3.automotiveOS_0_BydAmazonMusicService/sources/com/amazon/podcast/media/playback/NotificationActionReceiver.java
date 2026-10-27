package com.amazon.podcast.media.playback;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/* JADX INFO: loaded from: classes5.dex */
public final class NotificationActionReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        context.sendBroadcast(new Intent("notification_actions").putExtra("action_name", intent.getAction()));
    }
}
