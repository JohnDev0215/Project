package com.android.car.developeroptions.enterprise;

import android.content.Context;
import androidx.preference.Preference;
import com.android.car.developeroptions.R;

/* JADX INFO: loaded from: classes.dex */
public class CaCertsCurrentUserPreferenceController extends CaCertsPreferenceControllerBase {
    static final String CA_CERTS_CURRENT_USER = "ca_certs_current_user";

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public String getPreferenceKey() {
        return CA_CERTS_CURRENT_USER;
    }

    public CaCertsCurrentUserPreferenceController(Context context) {
        super(context);
    }

    @Override // com.android.car.developeroptions.enterprise.CaCertsPreferenceControllerBase, com.android.settingslib.core.AbstractPreferenceController
    public void updateState(Preference preference) {
        super.updateState(preference);
        preference.setTitle(this.mFeatureProvider.isInCompMode() ? R.string.enterprise_privacy_ca_certs_personal : R.string.enterprise_privacy_ca_certs_device);
    }

    @Override // com.android.car.developeroptions.enterprise.CaCertsPreferenceControllerBase
    protected int getNumberOfCaCerts() {
        return this.mFeatureProvider.getNumberOfOwnerInstalledCaCertsForCurrentUser();
    }
}
