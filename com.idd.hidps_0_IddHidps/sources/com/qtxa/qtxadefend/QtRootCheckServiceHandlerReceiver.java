package com.qtxa.qtxadefend;

import android.content.Context;
import android.content.Intent;
import com.qtxa.receiver.HandlerModuleReceiver;

/* JADX INFO: loaded from: classes.dex */
public class QtRootCheckServiceHandlerReceiver extends HandlerModuleReceiver {
    @Override // com.qtxa.receiver.HandlerModuleReceiver
    public void d(Context context, Intent intent) throws Throwable {
        q.d().k("qtxa.rootcheck.rule");
    }

    @Override // com.qtxa.receiver.HandlerModuleReceiver
    public void e(Context context, Intent intent) {
    }
}
