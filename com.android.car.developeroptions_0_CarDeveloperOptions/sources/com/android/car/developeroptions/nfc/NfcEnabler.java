package com.android.car.developeroptions.nfc;

import android.content.Context;
import android.provider.Settings;
import androidx.preference.SwitchPreference;

/* JADX INFO: loaded from: classes.dex */
public class NfcEnabler extends BaseNfcEnabler {
    private final SwitchPreference mPreference;

    public NfcEnabler(Context context, SwitchPreference switchPreference) {
        super(context);
        this.mPreference = switchPreference;
    }

    @Override // com.android.car.developeroptions.nfc.BaseNfcEnabler
    protected void handleNfcStateChanged(int i) {
        if (i == 1) {
            this.mPreference.setChecked(false);
            this.mPreference.setEnabled(isToggleable());
            return;
        }
        if (i == 2) {
            this.mPreference.setChecked(true);
            this.mPreference.setEnabled(false);
        } else if (i == 3) {
            this.mPreference.setChecked(true);
            this.mPreference.setEnabled(true);
        } else {
            if (i != 4) {
                return;
            }
            this.mPreference.setChecked(false);
            this.mPreference.setEnabled(false);
        }
    }

    boolean isToggleable() {
        return (!NfcPreferenceController.isToggleableInAirplaneMode(this.mContext) && NfcPreferenceController.shouldTurnOffNFCInAirplaneMode(this.mContext) && Settings.Global.getInt(this.mContext.getContentResolver(), "airplane_mode_on", 0) == 1) ? false : true;
    }
}
