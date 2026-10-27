package com.android.systemui.shared.recents.model;

import android.app.ActivityManager;
import android.graphics.Bitmap;
import android.graphics.GraphicBuffer;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.Log;
import androidx.core.view.ViewCompat;
import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: loaded from: classes.dex */
public class ThumbnailData {
    public Rect insets;
    public boolean isRealSnapshot;
    public boolean isTranslucent;
    public int orientation;
    public boolean reducedResolution;
    public int rotation;
    public float scale;
    public long snapshotId;
    public int systemUiVisibility;
    public final Bitmap thumbnail;
    public int windowingMode;

    public ThumbnailData() {
        this.thumbnail = null;
        this.orientation = 0;
        this.rotation = -1;
        this.insets = new Rect();
        this.reducedResolution = false;
        this.scale = 1.0f;
        this.isRealSnapshot = true;
        this.isTranslucent = false;
        this.windowingMode = 0;
        this.systemUiVisibility = 0;
        this.snapshotId = 0L;
    }

    public ThumbnailData(ActivityManager.TaskSnapshot snapshot) {
        GraphicBuffer snapshot2 = snapshot.getSnapshot();
        BydLogHelper.logD("width:" + snapshot2.getWidth() + ",height:" + snapshot2.getHeight());
        if (snapshot2 == null || (((long) snapshot2.getUsage()) & 256) == 0) {
            Log.e("ThumbnailData", "Unexpected snapshot without USAGE_GPU_SAMPLED_IMAGE: " + snapshot2);
            Point taskSize = snapshot.getTaskSize();
            Bitmap bitmapCreateBitmap = Bitmap.createBitmap(taskSize.x, taskSize.y, Bitmap.Config.ARGB_8888);
            this.thumbnail = bitmapCreateBitmap;
            bitmapCreateBitmap.eraseColor(ViewCompat.MEASURED_STATE_MASK);
        } else {
            this.thumbnail = Bitmap.wrapHardwareBuffer(snapshot2, snapshot.getColorSpace());
        }
        this.insets = new Rect(snapshot.getContentInsets());
        this.orientation = snapshot.getOrientation();
        this.rotation = snapshot.getRotation();
        this.reducedResolution = snapshot.isLowResolution();
        this.scale = this.thumbnail.getWidth() / snapshot.getTaskSize().x;
        this.isRealSnapshot = snapshot.isRealSnapshot();
        this.isTranslucent = snapshot.isTranslucent();
        this.windowingMode = snapshot.getWindowingMode();
        this.systemUiVisibility = snapshot.getSystemUiVisibility();
        this.snapshotId = snapshot.getId();
        BydLogHelper.logD("reducedResolution:" + this.reducedResolution + ",orientation:" + this.orientation + " ,rotation:" + this.rotation + ",isRealSnapshot:" + this.isRealSnapshot);
    }
}
