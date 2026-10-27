package com.android.car.developeroptions.nfc;

import android.content.Context;
import android.os.UserHandle;
import com.android.car.developeroptions.R;
import com.android.settingslib.RestrictedLockUtilsInternal;
import com.android.settingslib.RestrictedPreference;

/* JADX INFO: loaded from: classes.dex */
public class AndroidBeamEnabler extends BaseNfcEnabler {
    private final boolean mBeamDisallowedBySystem;
    private final RestrictedPreference mPreference;

    public AndroidBeamEnabler(Context context, RestrictedPreference restrictedPreference) {
        super(context);
        this.mPreference = restrictedPreference;
        this.mBeamDisallowedBySystem = RestrictedLockUtilsInternal.hasBaseUserRestriction(context, "no_outgoing_beam", UserHandle.myUserId());
        if (!isNfcAvailable()) {
            this.mPreference.setEnabled(false);
        } else if (this.mBeamDisallowedBySystem) {
            this.mPreference.setEnabled(false);
        }
    }

    @Override // com.android.car.developeroptions.nfc.BaseNfcEnabler
    protected void handleNfcStateChanged(int i) {
        if (i == 1) {
            this.mPreference.setEnabled(false);
            this.mPreference.setSummary(R.string.nfc_disabled_summary);
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
            return;
        }
        if (this.mBeamDisallowedBySystem) {
            this.mPreference.setDisabledByAdmin(null);
            this.mPreference.setEnabled(false);
        } else {
            this.mPreference.checkRestrictionAndSetDisabled("no_outgoing_beam");
        }
        if (this.mNfcAdapter.isNdefPushEnabled() && this.mPreference.isEnabled()) {
            this.mPreference.setSummary(R.string.android_beam_on_summary);
        } else {
            this.mPreference.setSummary(R.string.android_beam_off_summary);
        }
    }
}
