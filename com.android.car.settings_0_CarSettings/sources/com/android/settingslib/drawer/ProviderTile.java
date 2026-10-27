package com.android.settingslib.drawer;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ComponentInfo;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.os.Parcel;
import android.util.Log;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes6.dex */
public class ProviderTile extends Tile {
    private static final boolean DEBUG_TIMING = false;
    private static final String TAG = "ProviderTile";
    private String mAuthority;
    private String mKey;

    public ProviderTile(ProviderInfo info, String category, Bundle metaData) {
        super(info, category);
        setMetaData(metaData);
        this.mAuthority = info.authority;
        this.mKey = metaData.getString(TileUtils.META_DATA_PREFERENCE_KEYHINT);
    }

    ProviderTile(Parcel in) {
        super(in);
        this.mAuthority = ((ProviderInfo) this.mComponentInfo).authority;
        this.mKey = getMetaData().getString(TileUtils.META_DATA_PREFERENCE_KEYHINT);
    }

    @Override // com.android.settingslib.drawer.Tile
    public int getId() {
        return Objects.hash(this.mAuthority, this.mKey);
    }

    @Override // com.android.settingslib.drawer.Tile
    public String getDescription() {
        return this.mAuthority + "/" + this.mKey;
    }

    @Override // com.android.settingslib.drawer.Tile
    protected ComponentInfo getComponentInfo(Context context) {
        if (this.mComponentInfo == null) {
            System.currentTimeMillis();
            PackageManager pm = context.getApplicationContext().getPackageManager();
            Intent intent = getIntent();
            List<ResolveInfo> infoList = pm.queryIntentContentProviders(intent, 0);
            if (infoList != null && !infoList.isEmpty()) {
                ProviderInfo providerInfo = infoList.get(0).providerInfo;
                this.mComponentInfo = providerInfo;
                setMetaData(TileUtils.getSwitchDataFromProvider(context, providerInfo.authority, this.mKey));
            } else {
                Log.e(TAG, "Cannot find package info for " + intent.getComponent().flattenToString());
            }
        }
        return this.mComponentInfo;
    }

    @Override // com.android.settingslib.drawer.Tile
    protected CharSequence getComponentLabel(Context context) {
        return null;
    }

    @Override // com.android.settingslib.drawer.Tile
    protected int getComponentIcon(ComponentInfo info) {
        return 0;
    }
}
