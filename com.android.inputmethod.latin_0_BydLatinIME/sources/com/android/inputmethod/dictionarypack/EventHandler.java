package com.android.inputmethod.dictionarypack;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/* JADX INFO: loaded from: classes2.dex */
public final class EventHandler extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(final Context context, final Intent intent) {
        intent.setClass(context, DictionaryService.class);
        context.startService(intent);
    }
}
