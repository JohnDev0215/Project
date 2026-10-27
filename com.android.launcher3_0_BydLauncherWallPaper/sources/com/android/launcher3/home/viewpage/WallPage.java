package com.android.launcher3.home.viewpage;

import android.graphics.Bitmap;

/* JADX INFO: loaded from: classes4.dex */
public abstract class WallPage {
    public abstract Bitmap getViewPageBitmap();

    public abstract void onRecycle(Bitmap bitmap);
}
