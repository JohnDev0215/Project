package com.android.car.developeroptions.security;

import android.content.Context;
import android.security.KeyStore;
import androidx.preference.Preference;
import com.android.car.developeroptions.R;

/* JADX INFO: loaded from: classes.dex */
public class CredentialStoragePreferenceController extends RestrictedEncryptionPreferenceController {
    private final KeyStore mKeyStore;

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public String getPreferenceKey() {
        return "credential_storage_type";
    }

    public CredentialStoragePreferenceController(Context context) {
        super(context, "no_config_credentials");
        this.mKeyStore = KeyStore.getInstance();
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void updateState(Preference preference) {
        preference.setSummary(this.mKeyStore.isHardwareBacked() ? R.string.credential_storage_type_hardware : R.string.credential_storage_type_software);
    }
}
