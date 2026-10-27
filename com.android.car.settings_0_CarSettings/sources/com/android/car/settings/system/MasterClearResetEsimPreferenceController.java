package com.android.car.settings.system;

import android.car.drivingstate.CarUxRestrictions;
import android.content.Context;
import android.os.SystemProperties;
import com.android.car.settings.common.FragmentController;

/* JADX INFO: loaded from: classes5.dex */
public class MasterClearResetEsimPreferenceController extends ResetEsimPreferenceController {
    static final String KEY_SHOW_ESIM_RESET_CHECKBOX = "masterclear.allow_retain_esim_profiles_after_fdr";

    public MasterClearResetEsimPreferenceController(Context context, String preferenceKey, FragmentController fragmentController, CarUxRestrictions uxRestrictions) {
        super(context, preferenceKey, fragmentController, uxRestrictions);
    }

    @Override // com.android.car.settings.system.ResetEsimPreferenceController, com.android.car.settings.common.PreferenceController
    protected int getAvailabilityStatus() {
        int status = super.getAvailabilityStatus();
        if (status == 0) {
            return SystemProperties.get(KEY_SHOW_ESIM_RESET_CHECKBOX, Boolean.FALSE.toString()).equals(Boolean.TRUE.toString()) ? 0 : 2;
        }
        return status;
    }
}
