package com.android.settingslib.drawer;

import android.content.pm.ProviderInfo;
import android.os.Parcel;

/* JADX INFO: loaded from: classes.dex */
public class ProviderTile extends Tile {
    ProviderTile(Parcel parcel) {
        super(parcel);
        String str = ((ProviderInfo) this.mComponentInfo).authority;
        getMetaData().getString("com.android.settings.keyhint");
    }
}
