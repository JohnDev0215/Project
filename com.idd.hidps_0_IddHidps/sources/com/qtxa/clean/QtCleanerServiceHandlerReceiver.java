package com.qtxa.clean;

import android.content.Context;
import android.content.Intent;
import com.qtxa.clean.sysmonitor.SysMonitor;
import com.qtxa.qtxadefend.w;
import com.qtxa.receiver.HandlerModuleReceiver;

/* JADX INFO: loaded from: classes.dex */
public class QtCleanerServiceHandlerReceiver extends HandlerModuleReceiver {

    class a implements Runnable {
        a(QtCleanerServiceHandlerReceiver qtCleanerServiceHandlerReceiver) {
        }

        @Override // java.lang.Runnable
        public void run() {
            new SysMonitor().E("qtxa.systemerr.rule");
        }
    }

    @Override // com.qtxa.receiver.HandlerModuleReceiver
    public void d(Context context, Intent intent) {
        w.d(" get QtCleanerServiceHandlerReceiver run here every time");
        new Thread(new a(this)).start();
    }

    @Override // com.qtxa.receiver.HandlerModuleReceiver
    public void e(Context context, Intent intent) {
    }
}
