package com.android.car.developeroptions.enterprise;

import android.content.Context;
import androidx.preference.Preference;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.core.PreferenceControllerMixin;
import com.android.car.developeroptions.overlay.FeatureFactory;
import com.android.settingslib.core.AbstractPreferenceController;

/* JADX INFO: loaded from: classes.dex */
public abstract class FailedPasswordWipePreferenceControllerBase extends AbstractPreferenceController implements PreferenceControllerMixin {
    protected final EnterprisePrivacyFeatureProvider mFeatureProvider;

    protected abstract int getMaximumFailedPasswordsBeforeWipe();

    public FailedPasswordWipePreferenceControllerBase(Context context) {
        super(context);
        this.mFeatureProvider = FeatureFactory.getFactory(context).getEnterprisePrivacyFeatureProvider(context);
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void updateState(Preference preference) {
        int maximumFailedPasswordsBeforeWipe = getMaximumFailedPasswordsBeforeWipe();
        preference.setSummary(this.mContext.getResources().getQuantityString(R.plurals.enterprise_privacy_number_failed_password_wipe, maximumFailedPasswordsBeforeWipe, Integer.valueOf(maximumFailedPasswordsBeforeWipe)));
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public boolean isAvailable() {
        return getMaximumFailedPasswordsBeforeWipe() > 0;
    }
}
