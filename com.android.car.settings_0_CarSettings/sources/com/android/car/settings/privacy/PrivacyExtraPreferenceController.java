package com.android.car.settings.privacy;

import android.car.drivingstate.CarUxRestrictions;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import androidx.preference.Preference;
import com.android.car.settings.common.ExtraSettingsPreferenceController;
import com.android.car.settings.common.FragmentController;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public class PrivacyExtraPreferenceController extends ExtraSettingsPreferenceController {
    public PrivacyExtraPreferenceController(Context context, String preferenceKey, FragmentController fragmentController, CarUxRestrictions restrictionInfo) {
        super(context, preferenceKey, fragmentController, restrictionInfo);
    }

    @Override // com.android.car.settings.common.ExtraSettingsPreferenceController
    protected void addExtraSettings(Map<Preference, Bundle> preferenceBundleMap) {
        for (Preference setting : preferenceBundleMap.keySet()) {
            setting.setIcon((Drawable) null);
            getPreference().addPreference(setting);
        }
    }
}
