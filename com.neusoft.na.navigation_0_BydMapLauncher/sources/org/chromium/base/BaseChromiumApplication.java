package org.chromium.base;

import android.app.Application;
import android.content.Context;
import org.chromium.base.multidex.ChromiumMultiDexInstaller;

/* JADX INFO: loaded from: classes3.dex */
public class BaseChromiumApplication extends Application {
    private static final String TAG = "cr.base";
    private final boolean mShouldInitializeApplicationStatusTracking;

    public void initCommandLine() {
    }

    public BaseChromiumApplication() {
        this(true);
    }

    protected BaseChromiumApplication(boolean z) {
        this.mShouldInitializeApplicationStatusTracking = z;
    }

    @Override // android.content.ContextWrapper
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
        ChromiumMultiDexInstaller.install(this);
    }

    @Override // android.app.Application
    public void onCreate() {
        super.onCreate();
        ApplicationStatusManager.init(this);
    }

    public static void initCommandLine(Context context) {
        ((BaseChromiumApplication) context.getApplicationContext()).initCommandLine();
    }
}
