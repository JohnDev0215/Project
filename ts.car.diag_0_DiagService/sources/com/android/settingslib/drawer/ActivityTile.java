package com.android.settingslib.drawer;

import android.content.pm.ActivityInfo;
import android.os.Parcel;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes3.dex */
public class ActivityTile extends Tile {
    public ActivityTile(ActivityInfo activityInfo, String str) {
        super(activityInfo, str);
        f(activityInfo.metaData);
    }

    ActivityTile(Parcel parcel) {
        super(parcel);
    }
}
