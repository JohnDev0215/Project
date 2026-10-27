package com.android.launcher3.home.viewpage;

import android.graphics.Bitmap;

/* JADX INFO: loaded from: classes4.dex */
public class SysWallPage extends WallPage {
    Bitmap bitmap;

    @Override // com.android.launcher3.home.viewpage.WallPage
    public void onRecycle(Bitmap bitmap) {
    }

    public SysWallPage(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    @Override // com.android.launcher3.home.viewpage.WallPage
    public Bitmap getViewPageBitmap() {
        return this.bitmap;
    }
}
