package com.qtxa.qtxadefend;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.SystemProperties;

/* JADX INFO: loaded from: classes.dex */
public class PackageEventReceiver extends BroadcastReceiver {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final u f102a;

    private PackageEventReceiver(u uVar) {
        this.f102a = uVar;
    }

    static void a(u uVar) {
        PackageEventReceiver packageEventReceiver = new PackageEventReceiver(uVar);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.PACKAGE_ADDED");
        intentFilter.addAction("android.intent.action.PACKAGE_FULLY_REMOVED");
        intentFilter.addDataScheme("package");
        uVar.c().registerReceiver(packageEventReceiver, intentFilter);
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        String schemeSpecificPart = intent.getData().getSchemeSpecificPart();
        String str = SystemProperties.get("qtcarsec.pm.install", "1");
        if (!(str != null && "0".equals(str) && b.f110a) && b.a()) {
            if (!action.equals("android.intent.action.PACKAGE_ADDED")) {
                if (action.equals("android.intent.action.PACKAGE_FULLY_REMOVED")) {
                    h.h(schemeSpecificPart, false);
                    this.f102a.f().f(schemeSpecificPart);
                    return;
                }
                return;
            }
            try {
                h.c(schemeSpecificPart);
                h.h(schemeSpecificPart, true);
                if (this.f102a.e().d != null) {
                    this.f102a.e().d.add(new m(this.f102a.c().getPackageManager().getApplicationInfo(schemeSpecificPart, 0)));
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
            w.d("PackageEventReceiver：ACTION_PACKAGE_ADDED1");
            if (this.f102a.e().d(schemeSpecificPart)) {
                this.f102a.g().b(schemeSpecificPart);
                return;
            }
            w.d("PackageEventReceiver：ACTION_PACKAGE_ADDED2");
            this.f102a.f().d(schemeSpecificPart, intent.getExtras().getBoolean("android.intent.extra.REPLACING"));
        }
    }
}
