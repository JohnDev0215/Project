package com.byd.data.common;

import android.content.Context;
import com.bydux.api.common.views.application.BydApplication;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public class BaseApplicationContext extends BydApplication {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static Context f3286e;

    @Override // com.bydux.api.common.views.application.BydApplication, android.app.Application
    public void onCreate() {
        f3286e = getApplicationContext();
        super.onCreate();
    }
}
