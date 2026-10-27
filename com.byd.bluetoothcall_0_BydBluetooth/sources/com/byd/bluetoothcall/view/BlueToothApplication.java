package com.byd.bluetoothcall.view;

import android.content.Intent;
import c.a.b.b;
import com.byd.bluetoothcall.service.BYD_BlueToothService;
import com.byd.bluetoothcall.widget.WidgetProvide;
import com.bydux.api.common.views.application.BydApplication;

/* JADX INFO: loaded from: classes.dex */
public class BlueToothApplication extends BydApplication {

    /* JADX INFO: renamed from: b */
    private static BlueToothApplication f3285b;

    /* JADX INFO: renamed from: c */
    private boolean f3286c = false;

    public static BlueToothApplication a() {
        return f3285b;
    }

    @Override // com.bydux.api.common.views.application.BydApplication, com.bydux.api.common.views.skin.listener.ILoaderListener
    public void onChangeSkinSuccess() {
        if (!b.f0().W() || !this.f3286c) {
            WidgetProvide.j();
        }
        this.f3286c = false;
    }

    @Override // com.bydux.api.common.views.application.BydApplication, android.app.Application
    public void onCreate() {
        super.onCreate();
        f3285b = this;
        this.f3286c = true;
        startService(new Intent(this, (Class<?>) BYD_BlueToothService.class));
    }

    @Override // com.bydux.api.common.views.application.BydApplication
    protected boolean onSkinEnable() {
        return false;
    }
}
