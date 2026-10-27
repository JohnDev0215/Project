package com.android.car.developeroptions.security;

import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
public class InstallCredentialsPreferenceController extends RestrictedEncryptionPreferenceController {
    @Override // com.android.settingslib.core.AbstractPreferenceController
    public String getPreferenceKey() {
        return "credentials_install";
    }

    public InstallCredentialsPreferenceController(Context context) {
        super(context, "no_config_credentials");
    }
}
