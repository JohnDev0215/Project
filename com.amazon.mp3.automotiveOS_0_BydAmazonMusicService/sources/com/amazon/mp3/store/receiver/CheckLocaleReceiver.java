package com.amazon.mp3.store.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.service.ComponentCheckService;

/* JADX INFO: loaded from: classes4.dex */
public class CheckLocaleReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(final Context context, final Intent intent) {
        String action = intent.getAction();
        if (!"android.intent.action.LOCALE_CHANGED".equals(action) || AmazonApplication.localeHasChanged(context)) {
            ComponentCheckService.start(context, action);
        }
    }
}
