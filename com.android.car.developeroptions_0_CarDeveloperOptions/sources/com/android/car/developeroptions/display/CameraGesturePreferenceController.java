package com.android.car.developeroptions.display;

import android.R;
import android.content.Context;
import android.os.SystemProperties;
import android.provider.Settings;
import androidx.preference.Preference;
import androidx.preference.SwitchPreference;
import com.android.car.developeroptions.core.PreferenceControllerMixin;
import com.android.settingslib.core.AbstractPreferenceController;

/* JADX INFO: loaded from: classes.dex */
public class CameraGesturePreferenceController extends AbstractPreferenceController implements PreferenceControllerMixin, Preference.OnPreferenceChangeListener {
    @Override // com.android.settingslib.core.AbstractPreferenceController
    public String getPreferenceKey() {
        return "camera_gesture";
    }

    public CameraGesturePreferenceController(Context context) {
        super(context);
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void updateState(Preference preference) {
        ((SwitchPreference) preference).setChecked(Settings.Secure.getInt(this.mContext.getContentResolver(), "camera_gesture_disabled", 0) == 0);
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public boolean isAvailable() {
        return (this.mContext.getResources().getInteger(R.integer.config_autoPowerModeAnyMotionSensor) != -1) && !SystemProperties.getBoolean("gesture.disable_camera_launch", false);
    }

    @Override // androidx.preference.Preference.OnPreferenceChangeListener
    public boolean onPreferenceChange(Preference preference, Object obj) {
        Settings.Secure.putInt(this.mContext.getContentResolver(), "camera_gesture_disabled", !((Boolean) obj).booleanValue() ? 1 : 0);
        return true;
    }
}
