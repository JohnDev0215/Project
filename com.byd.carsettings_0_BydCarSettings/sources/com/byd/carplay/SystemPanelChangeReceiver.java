package com.byd.carplay;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import b.b.a.f.h;
import b.b.f.c;

/* JADX INFO: loaded from: classes2.dex */
public class SystemPanelChangeReceiver extends BroadcastReceiver {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static int f2564a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Runnable f2565b = new a();

    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            SystemPanelChangeReceiver.f2564a = 0;
            b.b.m.c.a.a("SystemPanelChangeReceiver", "面板关闭后，5s恢复默认值");
        }
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction() != null && "com.byd.action.panel.state.change".equals(intent.getAction())) {
            boolean zS = c.w(context).s();
            boolean booleanExtra = intent.getBooleanExtra("expanded", false);
            b.b.m.c.a.a("SystemPanelChangeReceiver", "PanelChangeReceiver expanded:" + booleanExtra);
            if (!zS) {
                h.a().i(this.f2565b);
                h.a().g(this.f2565b, 2000L);
                b.b.m.c.a.a("SystemPanelChangeReceiver", "CP不在投屏页 return");
            } else if (booleanExtra) {
                f2564a = 1;
                h.a().i(this.f2565b);
            } else {
                f2564a = 2;
                h.a().g(this.f2565b, 5000L);
            }
        }
    }
}
