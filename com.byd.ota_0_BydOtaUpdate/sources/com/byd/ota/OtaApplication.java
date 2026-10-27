package com.byd.ota;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import c.a.b.d.e;
import c.a.b.d.k;
import c.a.b.d.n;
import c.a.d.b;
import com.byd.ota.view.OtaUiService;

/* JADX INFO: loaded from: classes.dex */
public class OtaApplication extends Application {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final String f3043c = OtaApplication.class.getSimpleName();

    public final void a(Context context) {
        Intent intent = new Intent(context, (Class<?>) OtaUiService.class);
        intent.setAction("com.intent.action.OTA_UPGRADE_SDK");
        context.startService(intent);
    }

    @Override // android.app.Application
    public void onCreate() {
        super.onCreate();
        String str = f3043c;
        e.c(str, n.b("yyyy-MM-dd HH:mm:ss"));
        e.c(str, "onCreate");
        e.c(str, "versionName = 1.19.9-202504271425");
        k.f(this);
        b.d().f(this);
        a(this);
    }
}
