package com.android.car.developeroptions.display;

import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.PowerManager;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.provider.Settings;
import android.service.vr.IVrManager;
import android.util.Log;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.car.developeroptions.core.PreferenceControllerMixin;
import com.android.settingslib.core.AbstractPreferenceController;
import com.android.settingslib.core.lifecycle.Lifecycle;
import com.android.settingslib.core.lifecycle.LifecycleObserver;
import com.android.settingslib.core.lifecycle.events.OnStart;
import com.android.settingslib.core.lifecycle.events.OnStop;
import com.android.settingslib.display.BrightnessUtils;
import java.text.NumberFormat;

/* JADX INFO: loaded from: classes.dex */
public class BrightnessLevelPreferenceController extends AbstractPreferenceController implements PreferenceControllerMixin, LifecycleObserver, OnStart, OnStop {
    private ContentObserver mBrightnessObserver;
    private final ContentResolver mContentResolver;
    private final int mMaxBrightness;
    private final int mMaxVrBrightness;
    private final int mMinBrightness;
    private final int mMinVrBrightness;
    private Preference mPreference;
    private static final Uri BRIGHTNESS_URI = Settings.System.getUriFor("screen_brightness");
    private static final Uri BRIGHTNESS_FOR_VR_URI = Settings.System.getUriFor("screen_brightness_for_vr");
    private static final Uri BRIGHTNESS_ADJ_URI = Settings.System.getUriFor("screen_auto_brightness_adj");

    private double getPercentage(double d, int i, int i2) {
        if (d > i2) {
            return 1.0d;
        }
        double d2 = i;
        if (d < d2) {
            return 0.0d;
        }
        return (d - d2) / ((double) (i2 - i));
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public String getPreferenceKey() {
        return "brightness";
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public boolean isAvailable() {
        return true;
    }

    public BrightnessLevelPreferenceController(Context context, Lifecycle lifecycle) {
        super(context);
        this.mBrightnessObserver = new ContentObserver(new Handler(Looper.getMainLooper())) { // from class: com.android.car.developeroptions.display.BrightnessLevelPreferenceController.1
            @Override // android.database.ContentObserver
            public void onChange(boolean z) {
                BrightnessLevelPreferenceController brightnessLevelPreferenceController = BrightnessLevelPreferenceController.this;
                brightnessLevelPreferenceController.updatedSummary(brightnessLevelPreferenceController.mPreference);
            }
        };
        if (lifecycle != null) {
            lifecycle.addObserver(this);
        }
        PowerManager powerManager = (PowerManager) context.getSystemService("power");
        this.mMinBrightness = powerManager.getMinimumScreenBrightnessSetting();
        this.mMaxBrightness = powerManager.getMaximumScreenBrightnessSetting();
        this.mMinVrBrightness = powerManager.getMinimumScreenBrightnessForVrSetting();
        this.mMaxVrBrightness = powerManager.getMaximumScreenBrightnessForVrSetting();
        this.mContentResolver = this.mContext.getContentResolver();
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        this.mPreference = preferenceScreen.findPreference("brightness");
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void updateState(Preference preference) {
        updatedSummary(preference);
    }

    @Override // com.android.settingslib.core.lifecycle.events.OnStart
    public void onStart() {
        this.mContentResolver.registerContentObserver(BRIGHTNESS_URI, false, this.mBrightnessObserver);
        this.mContentResolver.registerContentObserver(BRIGHTNESS_FOR_VR_URI, false, this.mBrightnessObserver);
        this.mContentResolver.registerContentObserver(BRIGHTNESS_ADJ_URI, false, this.mBrightnessObserver);
    }

    @Override // com.android.settingslib.core.lifecycle.events.OnStop
    public void onStop() {
        this.mContentResolver.unregisterContentObserver(this.mBrightnessObserver);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updatedSummary(Preference preference) {
        if (preference != null) {
            preference.setSummary(NumberFormat.getPercentInstance().format(getCurrentBrightness()));
        }
    }

    private double getCurrentBrightness() {
        int iConvertLinearToGamma;
        if (isInVrMode()) {
            iConvertLinearToGamma = BrightnessUtils.convertLinearToGamma(Settings.System.getInt(this.mContentResolver, "screen_brightness_for_vr", this.mMaxBrightness), this.mMinVrBrightness, this.mMaxVrBrightness);
        } else {
            iConvertLinearToGamma = BrightnessUtils.convertLinearToGamma(Settings.System.getInt(this.mContentResolver, "screen_brightness", this.mMinBrightness), this.mMinBrightness, this.mMaxBrightness);
        }
        return getPercentage(iConvertLinearToGamma, 0, 65535);
    }

    IVrManager safeGetVrManager() {
        return IVrManager.Stub.asInterface(ServiceManager.getService("vrmanager"));
    }

    boolean isInVrMode() {
        IVrManager iVrManagerSafeGetVrManager = safeGetVrManager();
        if (iVrManagerSafeGetVrManager == null) {
            return false;
        }
        try {
            return iVrManagerSafeGetVrManager.getVrModeState();
        } catch (RemoteException e) {
            Log.e("BrightnessPrefCtrl", "Failed to check vr mode!", e);
            return false;
        }
    }
}
