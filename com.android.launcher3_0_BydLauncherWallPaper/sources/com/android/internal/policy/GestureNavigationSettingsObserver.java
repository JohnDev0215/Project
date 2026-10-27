package com.android.internal.policy;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Resources;
import android.database.ContentObserver;
import android.os.Handler;
import android.provider.DeviceConfig;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import com.android.internal.R;
import com.android.internal.config.sysui.SystemUiDeviceConfigFlags;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes4.dex */
public class GestureNavigationSettingsObserver extends ContentObserver {
    private Context mContext;
    private Handler mMainHandler;
    private Runnable mOnChangeRunnable;
    private final DeviceConfig.OnPropertiesChangedListener mOnPropertiesChangedListener;

    public GestureNavigationSettingsObserver(Handler handler, Context context, Runnable runnable) {
        super(handler);
        this.mOnPropertiesChangedListener = new DeviceConfig.OnPropertiesChangedListener() { // from class: com.android.internal.policy.GestureNavigationSettingsObserver.1
            @Override // android.provider.DeviceConfig.OnPropertiesChangedListener
            public void onPropertiesChanged(DeviceConfig.Properties properties) {
                if (!DeviceConfig.NAMESPACE_SYSTEMUI.equals(properties.getNamespace()) || GestureNavigationSettingsObserver.this.mOnChangeRunnable == null) {
                    return;
                }
                GestureNavigationSettingsObserver.this.mOnChangeRunnable.run();
            }
        };
        this.mMainHandler = handler;
        this.mContext = context;
        this.mOnChangeRunnable = runnable;
    }

    public void register() {
        ContentResolver contentResolver = this.mContext.getContentResolver();
        contentResolver.registerContentObserver(Settings.Secure.getUriFor(Settings.Secure.BACK_GESTURE_INSET_SCALE_LEFT), false, this, -1);
        contentResolver.registerContentObserver(Settings.Secure.getUriFor(Settings.Secure.BACK_GESTURE_INSET_SCALE_RIGHT), false, this, -1);
        contentResolver.registerContentObserver(Settings.Secure.getUriFor(Settings.Secure.USER_SETUP_COMPLETE), false, this, -1);
        DeviceConfig.addOnPropertiesChangedListener(DeviceConfig.NAMESPACE_SYSTEMUI, new Executor() { // from class: com.android.internal.policy.-$$Lambda$GestureNavigationSettingsObserver$kNHOlqZAyTWmcdPCwEj0mZNfAog
            @Override // java.util.concurrent.Executor
            public final void execute(Runnable runnable) {
                this.f$0.lambda$register$0$GestureNavigationSettingsObserver(runnable);
            }
        }, this.mOnPropertiesChangedListener);
    }

    public /* synthetic */ void lambda$register$0$GestureNavigationSettingsObserver(Runnable runnable) {
        this.mMainHandler.post(runnable);
    }

    public void unregister() {
        this.mContext.getContentResolver().unregisterContentObserver(this);
        DeviceConfig.removeOnPropertiesChangedListener(this.mOnPropertiesChangedListener);
    }

    @Override // android.database.ContentObserver
    public void onChange(boolean z) {
        super.onChange(z);
        Runnable runnable = this.mOnChangeRunnable;
        if (runnable != null) {
            runnable.run();
        }
    }

    public int getLeftSensitivity(Resources resources) {
        return getSensitivity(resources, Settings.Secure.BACK_GESTURE_INSET_SCALE_LEFT);
    }

    public int getRightSensitivity(Resources resources) {
        return getSensitivity(resources, Settings.Secure.BACK_GESTURE_INSET_SCALE_RIGHT);
    }

    public boolean areNavigationButtonForcedVisible() {
        return Settings.Secure.getIntForUser(this.mContext.getContentResolver(), Settings.Secure.USER_SETUP_COMPLETE, 0, -2) == 0;
    }

    private int getSensitivity(Resources resources, String str) {
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        float dimension = resources.getDimension(R.dimen.config_backGestureInset) / displayMetrics.density;
        if (dimension > 0.0f) {
            dimension = DeviceConfig.getFloat(DeviceConfig.NAMESPACE_SYSTEMUI, SystemUiDeviceConfigFlags.BACK_GESTURE_EDGE_WIDTH, dimension);
        }
        return (int) (TypedValue.applyDimension(1, dimension, displayMetrics) * Settings.Secure.getFloatForUser(this.mContext.getContentResolver(), str, 1.0f, -2));
    }
}
