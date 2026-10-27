package com.android.car.developeroptions.enterprise;

import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
public class FailedPasswordWipeManagedProfilePreferenceController extends FailedPasswordWipePreferenceControllerBase {
    @Override // com.android.settingslib.core.AbstractPreferenceController
    public String getPreferenceKey() {
        return "failed_password_wipe_managed_profile";
    }

    public FailedPasswordWipeManagedProfilePreferenceController(Context context) {
        super(context);
    }

    @Override // com.android.car.developeroptions.enterprise.FailedPasswordWipePreferenceControllerBase
    protected int getMaximumFailedPasswordsBeforeWipe() {
        return this.mFeatureProvider.getMaximumFailedPasswordsBeforeWipeInManagedProfile();
    }
}
