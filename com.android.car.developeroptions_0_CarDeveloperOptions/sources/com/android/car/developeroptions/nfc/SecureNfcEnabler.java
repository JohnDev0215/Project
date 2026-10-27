package com.android.car.developeroptions.nfc;

import android.content.Context;
import androidx.preference.SwitchPreference;
import com.android.car.developeroptions.R;

/* JADX INFO: loaded from: classes.dex */
public class SecureNfcEnabler extends BaseNfcEnabler {
    private final SwitchPreference mPreference;

    public SecureNfcEnabler(Context context, SwitchPreference switchPreference) {
        super(context);
        this.mPreference = switchPreference;
    }

    @Override // com.android.car.developeroptions.nfc.BaseNfcEnabler
    protected void handleNfcStateChanged(int i) {
        if (i == 1) {
            this.mPreference.setSummary(R.string.nfc_disabled_summary);
            this.mPreference.setEnabled(false);
            return;
        }
        if (i == 2) {
            this.mPreference.setEnabled(false);
            return;
        }
        if (i != 3) {
            if (i != 4) {
                return;
            }
            this.mPreference.setEnabled(false);
        } else {
            this.mPreference.setSummary(R.string.nfc_secure_toggle_summary);
            SwitchPreference switchPreference = this.mPreference;
            switchPreference.setChecked(switchPreference.isChecked());
            this.mPreference.setEnabled(true);
        }
    }
}
