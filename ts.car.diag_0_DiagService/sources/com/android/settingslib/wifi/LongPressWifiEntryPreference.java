package com.android.settingslib.wifi;

import androidx.fragment.app.Fragment;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes3.dex */
public class LongPressWifiEntryPreference extends WifiEntryPreference {
    private final Fragment Z;

    @Override // com.android.settingslib.wifi.WifiEntryPreference, androidx.preference.Preference
    public void M(androidx.preference.f fVar) {
        super.M(fVar);
        Fragment fragment = this.Z;
        if (fragment != null) {
            fVar.f1092a.setOnCreateContextMenuListener(fragment);
            fVar.f1092a.setTag(this);
            fVar.f1092a.setLongClickable(true);
        }
    }
}
