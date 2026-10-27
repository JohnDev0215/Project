package com.android.launcher3.graphics;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Rect;
import androidx.core.graphics.ColorUtils;
import com.android.launcher3.FastBitmapDrawable;
import com.android.launcher3.ItemInfoWithIcon;
import com.android.launcher3.R;
import com.android.launcher3.icons.BitmapInfo;
import com.android.launcher3.util.Themes;

/* JADX INFO: loaded from: classes4.dex */
public class PlaceHolderIconDrawable extends FastBitmapDrawable {
    private final Path mProgressPath;

    public PlaceHolderIconDrawable(BitmapInfo info, Path progressPath, Context context) {
        this(info.icon, info.color, progressPath, context);
    }

    public PlaceHolderIconDrawable(ItemInfoWithIcon info, Path progressPath, Context context) {
        this(info.iconBitmap, info.iconColor, progressPath, context);
    }

    protected PlaceHolderIconDrawable(Bitmap b, int iconColor, Path progressPath, Context context) {
        super(b, iconColor);
        this.mProgressPath = progressPath;
        this.mPaint.setColor(ColorUtils.compositeColors(Themes.getAttrColor(context, R.attr.loadingIconColor), iconColor));
    }

    @Override // com.android.launcher3.FastBitmapDrawable
    protected void drawInternal(Canvas canvas, Rect bounds) {
        int iSave = canvas.save();
        canvas.translate(bounds.left, bounds.top);
        canvas.scale(bounds.width() / 100.0f, bounds.height() / 100.0f);
        canvas.drawPath(this.mProgressPath, this.mPaint);
        canvas.restoreToCount(iSave);
    }
}
