package com.android.car.ui.toolbar;

import android.os.IBinder;

/* JADX INFO: loaded from: classes6.dex */
public interface PrivateImeCommandCallback {
    void onItemClicked(String str);

    void onPostLoadSearchResults();

    void onSecondaryImageClicked(String str);

    void onSurfaceInfo(int i, IBinder iBinder, int i2, int i3);
}
