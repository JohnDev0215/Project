package com.dee.app.contacts.common.dagger.modules;

import android.content.Context;
import android.content.SharedPreferences;
import com.dee.app.contacts.common.utils.ActivityLifecycleUtil;
import com.dee.app.contacts.common.utils.DeviceInfo;

/* JADX INFO: loaded from: classes5.dex */
public class AppModule {
    private ActivityLifecycleUtil mActivityLifecycleUtil;
    private Context mContext;

    public AppModule(Context context) {
        this.mContext = context;
        this.mActivityLifecycleUtil = new ActivityLifecycleUtil(context);
    }

    public SharedPreferences provideSharedPreferences(Context context) {
        return context.getSharedPreferences("SHARED_PREFS", 0);
    }

    Context provideContext() {
        return this.mContext;
    }

    public String provideDeviceId(Context context) {
        return new DeviceInfo().getUniqueDeviceId(context);
    }

    public String provideApplicationName(Context context) {
        return context.getApplicationInfo().loadLabel(context.getPackageManager()).toString();
    }

    public ActivityLifecycleUtil provideActivityLifecycleUtil() {
        return this.mActivityLifecycleUtil;
    }
}
