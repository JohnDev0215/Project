package com.android.launcher3.home.viewpage;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/* JADX INFO: loaded from: classes4.dex */
public class DefaultViewPage extends WallPage {
    Bitmap bitmap;
    Context mContext;
    int resID;

    @Override // com.android.launcher3.home.viewpage.WallPage
    public void onRecycle(Bitmap bitmap) {
    }

    public DefaultViewPage(Context context, int id) {
        this.resID = id;
        this.mContext = context;
        this.bitmap = BitmapFactory.decodeResource(this.mContext.getResources(), this.resID, new BitmapFactory.Options());
    }

    @Override // com.android.launcher3.home.viewpage.WallPage
    public Bitmap getViewPageBitmap() {
        return this.bitmap;
    }
}
