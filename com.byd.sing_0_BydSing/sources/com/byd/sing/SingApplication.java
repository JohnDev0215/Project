package com.byd.sing;

import android.app.Application;
import com.byd.sing.service.MicConnectService;
import com.byd.sing.service.MicKeyService;

/* JADX INFO: loaded from: classes.dex */
public class SingApplication extends Application {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static SingApplication f3238b;

    public static SingApplication a() {
        return f3238b;
    }

    @Override // android.app.Application
    public void onCreate() {
        super.onCreate();
        a.a("onCreate version : Sing-ukepamr-20250428.1630");
        f3238b = this;
        MicKeyService.g(this);
        MicConnectService.t(this);
    }
}
