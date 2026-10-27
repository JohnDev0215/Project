package com.android.settingslib.widget;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.DrawableWrapper;
import android.os.RemoteException;
import android.util.PathParser;
import android.view.IWindowManager;
import android.view.WindowManagerGlobal;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes3.dex */
public class AdaptiveOutlineDrawable extends DrawableWrapper {
    private static final float ADVANCED_ICON_CENTER = 50.0f;
    private static final float ADVANCED_ICON_RADIUS = 48.0f;
    private Bitmap mBitmap;
    private int mInsetPx;
    Paint mOutlinePaint;
    private Path mPath;
    private int mStrokeWidth;
    private int mType;

    @Retention(RetentionPolicy.SOURCE)
    public @interface AdaptiveOutlineIconType {
        public static final int TYPE_ADVANCED = 1;
        public static final int TYPE_DEFAULT = 0;
    }

    public AdaptiveOutlineDrawable(Resources resources, Bitmap bitmap) {
        super(new AdaptiveIconShapeDrawable(resources));
        init(resources, bitmap, 0);
    }

    public AdaptiveOutlineDrawable(Resources resources, Bitmap bitmap, int type) {
        super(new AdaptiveIconShapeDrawable(resources));
        init(resources, bitmap, type);
    }

    private void init(Resources resources, Bitmap bitmap, int type) {
        this.mType = type;
        getDrawable().setTint(-1);
        this.mPath = new Path(PathParser.createPathFromPathData(resources.getString(android.R.string.config_customCountryDetector)));
        this.mStrokeWidth = resources.getDimensionPixelSize(R.dimen.adaptive_outline_stroke);
        Paint paint = new Paint();
        this.mOutlinePaint = paint;
        paint.setColor(getColor(resources, type));
        this.mOutlinePaint.setStyle(Paint.Style.STROKE);
        this.mOutlinePaint.setStrokeWidth(this.mStrokeWidth);
        this.mOutlinePaint.setAntiAlias(true);
        this.mInsetPx = getDimensionPixelSize(resources, type);
        this.mBitmap = bitmap;
    }

    private int getColor(Resources resources, int type) {
        int resId;
        if (type == 1) {
            resId = R.color.advanced_outline_color;
        } else {
            resId = R.color.bt_outline_color;
        }
        return resources.getColor(resId, null);
    }

    private int getDimensionPixelSize(Resources resources, int type) {
        int resId;
        if (type == 1) {
            resId = R.dimen.advanced_dashboard_tile_foreground_image_inset;
        } else {
            resId = R.dimen.dashboard_tile_foreground_image_inset;
        }
        return resources.getDimensionPixelSize(resId);
    }

    @Override // android.graphics.drawable.DrawableWrapper, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        super.draw(canvas);
        Rect bounds = getBounds();
        float scaleX = (bounds.right - bounds.left) / 100.0f;
        float scaleY = (bounds.bottom - bounds.top) / 100.0f;
        int count = canvas.save();
        canvas.scale(scaleX, scaleY);
        if (this.mType == 0) {
            canvas.drawPath(this.mPath, this.mOutlinePaint);
        } else {
            canvas.drawCircle(ADVANCED_ICON_CENTER, ADVANCED_ICON_CENTER, ADVANCED_ICON_RADIUS, this.mOutlinePaint);
        }
        canvas.restoreToCount(count);
        canvas.drawBitmap(this.mBitmap, bounds.left + this.mInsetPx, bounds.top + this.mInsetPx, (Paint) null);
    }

    private static int getDefaultDisplayDensity(int displayId) {
        try {
            IWindowManager wm = WindowManagerGlobal.getWindowManagerService();
            return wm.getInitialDisplayDensity(displayId);
        } catch (RemoteException e) {
            return -1;
        }
    }

    @Override // android.graphics.drawable.DrawableWrapper, android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return this.mBitmap.getHeight() + (this.mInsetPx * 2);
    }

    @Override // android.graphics.drawable.DrawableWrapper, android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return this.mBitmap.getWidth() + (this.mInsetPx * 2);
    }
}
