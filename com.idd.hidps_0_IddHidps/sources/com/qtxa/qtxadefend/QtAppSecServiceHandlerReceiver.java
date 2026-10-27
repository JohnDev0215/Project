package com.qtxa.qtxadefend;

import android.content.Context;
import android.content.Intent;
import com.qtxa.receiver.HandlerModuleReceiver;

/* JADX INFO: loaded from: classes.dex */
public class QtAppSecServiceHandlerReceiver extends HandlerModuleReceiver {
    @Override // com.qtxa.receiver.HandlerModuleReceiver
    public void d(Context context, Intent intent) {
        context.startService(new Intent(context, (Class<?>) SecurityService.class));
    }

    @Override // com.qtxa.receiver.HandlerModuleReceiver
    public void e(Context context, Intent intent) {
    }
}
