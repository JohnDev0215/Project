package com.android.car.developeroptions.enterprise;

import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
public class FailedPasswordWipeCurrentUserPreferenceController extends FailedPasswordWipePreferenceControllerBase {
    @Override // com.android.settingslib.core.AbstractPreferenceController
    public String getPreferenceKey() {
        return "failed_password_wipe_current_user";
    }

    public FailedPasswordWipeCurrentUserPreferenceController(Context context) {
        super(context);
    }

    @Override // com.android.car.developeroptions.enterprise.FailedPasswordWipePreferenceControllerBase
    protected int getMaximumFailedPasswordsBeforeWipe() {
        return this.mFeatureProvider.getMaximumFailedPasswordsBeforeWipeInCurrentUser();
    }
}
