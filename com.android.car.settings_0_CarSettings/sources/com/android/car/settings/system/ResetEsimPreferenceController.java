package com.android.car.settings.system;

import android.car.drivingstate.CarUxRestrictions;
import android.content.ContentResolver;
import android.content.Context;
import android.provider.Settings;
import android.telephony.euicc.EuiccManager;
import androidx.preference.TwoStatePreference;
import com.android.car.settings.common.FragmentController;
import com.android.car.settings.common.PreferenceController;

/* JADX INFO: loaded from: classes5.dex */
public class ResetEsimPreferenceController extends PreferenceController<TwoStatePreference> {
    public ResetEsimPreferenceController(Context context, String preferenceKey, FragmentController fragmentController, CarUxRestrictions uxRestrictions) {
        super(context, preferenceKey, fragmentController, uxRestrictions);
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected Class<TwoStatePreference> getPreferenceType() {
        return TwoStatePreference.class;
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected int getAvailabilityStatus() {
        return showEuiccSettings() ? 0 : 2;
    }

    private boolean showEuiccSettings() {
        EuiccManager euiccManager = (EuiccManager) getContext().getSystemService("euicc");
        if (!euiccManager.isEnabled()) {
            return false;
        }
        ContentResolver resolver = getContext().getContentResolver();
        return (Settings.Global.getInt(resolver, "euicc_provisioned", 0) == 0 && Settings.Global.getInt(resolver, "development_settings_enabled", 0) == 0) ? false : true;
    }
}
