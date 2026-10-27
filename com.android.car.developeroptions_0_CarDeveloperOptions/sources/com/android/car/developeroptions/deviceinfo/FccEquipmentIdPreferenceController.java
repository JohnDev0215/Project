package com.android.car.developeroptions.deviceinfo;

import android.content.Context;
import android.os.SystemProperties;
import android.text.TextUtils;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.core.PreferenceControllerMixin;
import com.android.settingslib.core.AbstractPreferenceController;

/* JADX INFO: loaded from: classes.dex */
public class FccEquipmentIdPreferenceController extends AbstractPreferenceController implements PreferenceControllerMixin {
    @Override // com.android.settingslib.core.AbstractPreferenceController
    public String getPreferenceKey() {
        return "fcc_equipment_id";
    }

    public FccEquipmentIdPreferenceController(Context context) {
        super(context);
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public boolean isAvailable() {
        return !TextUtils.isEmpty(SystemProperties.get("ro.ril.fccid"));
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        Preference preferenceFindPreference = preferenceScreen.findPreference("fcc_equipment_id");
        if (preferenceFindPreference != null) {
            preferenceFindPreference.setSummary(SystemProperties.get("ro.ril.fccid", this.mContext.getResources().getString(R.string.device_info_default)));
        }
    }
}
