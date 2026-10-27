package com.android.systemui.recents;

import android.app.ActivityManager;
import android.content.Context;
import android.content.res.Resources;
import android.os.SystemProperties;
import com.android.systemui.R;
import com.android.systemui.recents.misc.SystemServicesProxy;

/* JADX INFO: loaded from: classes.dex */
public class RecentsConfiguration {
    private static final int LARGE_SCREEN_MIN_DP = 600;
    private static final int XLARGE_SCREEN_MIN_DP = 720;
    public boolean dragToSplitEnabled;
    public boolean fakeShadows;
    public boolean isGridEnabled;
    public final boolean isLargeScreen;
    public boolean isLowRamDevice;
    public final boolean isXLargeScreen;
    private final Context mAppContext;
    public RecentsActivityLaunchState mLaunchState = new RecentsActivityLaunchState();
    public final int smallestWidth;
    public int svelteLevel;

    public RecentsConfiguration(Context context) {
        SystemServicesProxy systemServices = LegacyRecentsImpl.getSystemServices();
        Context applicationContext = context.getApplicationContext();
        this.mAppContext = applicationContext;
        Resources resources = applicationContext.getResources();
        this.fakeShadows = resources.getBoolean(R.bool.config_recents_fake_shadows);
        this.svelteLevel = resources.getInteger(R.integer.recents_svelte_level);
        this.isGridEnabled = SystemProperties.getBoolean("ro.recents.grid", false);
        boolean zIsLowRamDeviceStatic = ActivityManager.isLowRamDeviceStatic();
        this.isLowRamDevice = zIsLowRamDeviceStatic;
        this.dragToSplitEnabled = !zIsLowRamDeviceStatic;
        float f = context.getResources().getDisplayMetrics().density;
        int deviceSmallestWidth = systemServices.getDeviceSmallestWidth();
        this.smallestWidth = deviceSmallestWidth;
        this.isLargeScreen = deviceSmallestWidth >= ((int) (600.0f * f));
        this.isXLargeScreen = deviceSmallestWidth >= ((int) (f * 720.0f));
    }

    public RecentsActivityLaunchState getLaunchState() {
        return this.mLaunchState;
    }
}
