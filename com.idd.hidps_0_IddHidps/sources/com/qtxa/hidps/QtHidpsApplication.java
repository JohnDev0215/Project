package com.qtxa.hidps;

import android.app.Application;
import android.content.Context;
import android.os.SystemProperties;

/* JADX INFO: loaded from: classes.dex */
public final class QtHidpsApplication extends Application {
    @Override // android.content.ContextWrapper
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
    }

    @Override // android.app.Application
    public void onCreate() {
        super.onCreate();
        if (SystemProperties.get("sys.boot_completed", "0").equals("1")) {
            a.h(this);
        }
    }
}
