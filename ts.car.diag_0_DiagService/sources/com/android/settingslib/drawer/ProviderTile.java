package com.android.settingslib.drawer;

import android.content.pm.ProviderInfo;
import android.os.Bundle;
import android.os.Parcel;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes3.dex */
public class ProviderTile extends Tile {
    public ProviderTile(ProviderInfo providerInfo, String str, Bundle bundle) {
        super(providerInfo, str);
        f(bundle);
        String str2 = providerInfo.authority;
        bundle.getString("com.android.settings.keyhint");
    }

    ProviderTile(Parcel parcel) {
        super(parcel);
        String str = ((ProviderInfo) this.f).authority;
        a().getString("com.android.settings.keyhint");
    }
}
