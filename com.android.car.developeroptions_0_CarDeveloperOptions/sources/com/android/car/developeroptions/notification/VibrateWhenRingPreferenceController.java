package com.android.car.developeroptions.notification;

import android.content.ContentResolver;
import android.content.Context;
import android.content.IntentFilter;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.provider.DeviceConfig;
import android.provider.Settings;
import android.text.TextUtils;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.car.developeroptions.Utils;
import com.android.car.developeroptions.core.TogglePreferenceController;
import com.android.car.developeroptions.slices.SliceBackgroundWorker;
import com.android.settingslib.core.AbstractPreferenceController;
import com.android.settingslib.core.lifecycle.LifecycleObserver;
import com.android.settingslib.core.lifecycle.events.OnPause;
import com.android.settingslib.core.lifecycle.events.OnResume;

/* JADX INFO: loaded from: classes.dex */
public class VibrateWhenRingPreferenceController extends TogglePreferenceController implements LifecycleObserver, OnResume, OnPause {
    private static final String KEY_VIBRATE_WHEN_RINGING = "vibrate_when_ringing";
    private static final String RAMPING_RINGER_ENABLED = "ramping_ringer_enabled";
    private final int DEFAULT_VALUE;
    private final int NOTIFICATION_VIBRATE_WHEN_RINGING;
    private SettingObserver mSettingObserver;

    @Override // com.android.car.developeroptions.core.TogglePreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ void copy() {
        super.copy();
    }

    @Override // com.android.car.developeroptions.core.TogglePreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ Class<? extends SliceBackgroundWorker> getBackgroundWorkerClass() {
        return super.getBackgroundWorkerClass();
    }

    @Override // com.android.car.developeroptions.core.TogglePreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ IntentFilter getIntentFilter() {
        return super.getIntentFilter();
    }

    @Override // com.android.car.developeroptions.core.TogglePreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean hasAsyncUpdate() {
        return super.hasAsyncUpdate();
    }

    @Override // com.android.car.developeroptions.core.TogglePreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean isCopyableSlice() {
        return super.isCopyableSlice();
    }

    @Override // com.android.car.developeroptions.core.TogglePreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean useDynamicSliceSummary() {
        return super.useDynamicSliceSummary();
    }

    public VibrateWhenRingPreferenceController(Context context, String str) {
        super(context, str);
        this.DEFAULT_VALUE = 0;
        this.NOTIFICATION_VIBRATE_WHEN_RINGING = 1;
    }

    @Override // com.android.car.developeroptions.core.TogglePreferenceController
    public boolean isChecked() {
        return Settings.System.getInt(this.mContext.getContentResolver(), KEY_VIBRATE_WHEN_RINGING, 0) != 0;
    }

    @Override // com.android.car.developeroptions.core.TogglePreferenceController
    public boolean setChecked(boolean z) {
        return Settings.System.putInt(this.mContext.getContentResolver(), KEY_VIBRATE_WHEN_RINGING, z ? 1 : 0);
    }

    @Override // com.android.car.developeroptions.core.BasePreferenceController
    public int getAvailabilityStatus() {
        return (!Utils.isVoiceCapable(this.mContext) || isRampingRingerEnabled()) ? 3 : 0;
    }

    @Override // com.android.car.developeroptions.core.TogglePreferenceController, com.android.car.developeroptions.slices.Sliceable
    public boolean isSliceable() {
        return TextUtils.equals(getPreferenceKey(), KEY_VIBRATE_WHEN_RINGING);
    }

    @Override // com.android.car.developeroptions.core.BasePreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        Preference preferenceFindPreference = preferenceScreen.findPreference(KEY_VIBRATE_WHEN_RINGING);
        if (preferenceFindPreference != null) {
            this.mSettingObserver = new SettingObserver(preferenceFindPreference);
            preferenceFindPreference.setPersistent(false);
        }
    }

    @Override // com.android.settingslib.core.lifecycle.events.OnResume
    public void onResume() {
        SettingObserver settingObserver = this.mSettingObserver;
        if (settingObserver != null) {
            settingObserver.register(true);
        }
    }

    @Override // com.android.settingslib.core.lifecycle.events.OnPause
    public void onPause() {
        SettingObserver settingObserver = this.mSettingObserver;
        if (settingObserver != null) {
            settingObserver.register(false);
        }
    }

    private final class SettingObserver extends ContentObserver {
        private final Uri VIBRATE_WHEN_RINGING_URI;
        private final Preference mPreference;

        public SettingObserver(Preference preference) {
            super(new Handler());
            this.VIBRATE_WHEN_RINGING_URI = Settings.System.getUriFor(VibrateWhenRingPreferenceController.KEY_VIBRATE_WHEN_RINGING);
            this.mPreference = preference;
        }

        public void register(boolean z) {
            ContentResolver contentResolver = ((AbstractPreferenceController) VibrateWhenRingPreferenceController.this).mContext.getContentResolver();
            if (z) {
                contentResolver.registerContentObserver(this.VIBRATE_WHEN_RINGING_URI, false, this);
            } else {
                contentResolver.unregisterContentObserver(this);
            }
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean z, Uri uri) {
            super.onChange(z, uri);
            if (this.VIBRATE_WHEN_RINGING_URI.equals(uri)) {
                VibrateWhenRingPreferenceController.this.updateState(this.mPreference);
            }
        }
    }

    private boolean isRampingRingerEnabled() {
        return DeviceConfig.getBoolean("telephony", RAMPING_RINGER_ENABLED, false);
    }
}
