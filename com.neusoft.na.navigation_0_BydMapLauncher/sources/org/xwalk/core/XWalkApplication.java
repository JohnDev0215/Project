package org.xwalk.core;

import android.app.Application;
import android.content.res.Resources;

/* JADX INFO: loaded from: classes4.dex */
public class XWalkApplication extends Application {
    private static XWalkApplication gApp;
    private Resources mRes = null;

    @Override // android.app.Application
    public void onCreate() {
        super.onCreate();
        gApp = this;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Resources getResources() {
        Resources resources = this.mRes;
        return resources == null ? super.getResources() : resources;
    }

    void addResource(Resources resources) {
        if (this.mRes != null) {
            return;
        }
        this.mRes = new XWalkMixedResources(super.getResources(), resources);
    }

    static XWalkApplication getApplication() {
        return gApp;
    }
}
