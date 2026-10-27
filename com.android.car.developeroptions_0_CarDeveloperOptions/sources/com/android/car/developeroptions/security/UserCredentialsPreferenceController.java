package com.android.car.developeroptions.security;

import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
public class UserCredentialsPreferenceController extends RestrictedEncryptionPreferenceController {
    @Override // com.android.settingslib.core.AbstractPreferenceController
    public String getPreferenceKey() {
        return "user_credentials";
    }

    public UserCredentialsPreferenceController(Context context) {
        super(context, "no_config_credentials");
    }
}
