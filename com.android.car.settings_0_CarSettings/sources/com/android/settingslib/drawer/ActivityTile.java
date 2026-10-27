package com.android.settingslib.drawer;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ComponentInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Parcel;
import android.util.Log;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes6.dex */
public class ActivityTile extends Tile {
    private static final String TAG = "ActivityTile";

    public ActivityTile(ActivityInfo info, String category) {
        super(info, category);
        setMetaData(info.metaData);
    }

    ActivityTile(Parcel in) {
        super(in);
    }

    @Override // com.android.settingslib.drawer.Tile
    public int getId() {
        return Objects.hash(getPackageName(), getComponentName());
    }

    @Override // com.android.settingslib.drawer.Tile
    public String getDescription() {
        return getPackageName() + "/" + getComponentName();
    }

    @Override // com.android.settingslib.drawer.Tile
    protected ComponentInfo getComponentInfo(Context context) {
        if (this.mComponentInfo == null) {
            PackageManager pm = context.getApplicationContext().getPackageManager();
            Intent intent = getIntent();
            List<ResolveInfo> infoList = pm.queryIntentActivities(intent, 128);
            if (infoList != null && !infoList.isEmpty()) {
                this.mComponentInfo = infoList.get(0).activityInfo;
                setMetaData(this.mComponentInfo.metaData);
            } else {
                Log.e(TAG, "Cannot find package info for " + intent.getComponent().flattenToString());
            }
        }
        return this.mComponentInfo;
    }

    @Override // com.android.settingslib.drawer.Tile
    protected CharSequence getComponentLabel(Context context) {
        PackageManager pm = context.getPackageManager();
        ComponentInfo info = getComponentInfo(context);
        if (info == null) {
            return null;
        }
        return info.loadLabel(pm);
    }

    @Override // com.android.settingslib.drawer.Tile
    protected int getComponentIcon(ComponentInfo componentInfo) {
        return componentInfo.icon;
    }
}
