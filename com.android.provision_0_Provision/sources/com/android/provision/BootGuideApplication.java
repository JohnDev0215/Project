package com.android.provision;

import android.app.Application;
import android.content.Context;
import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: loaded from: classes.dex */
public class BootGuideApplication extends Application {
    public static final String TAG = "BootGuideApplication";
    private static Context mContext;

    @Override // android.app.Application
    public void onCreate() {
        super.onCreate();
        BydLogHelper.logW(TAG, "onCreate");
        mContext = this;
        init();
    }

    private void init() {
        BootGuideManager.getInstance().init();
    }

    public static Context getAppContext() {
        return mContext;
    }
}
