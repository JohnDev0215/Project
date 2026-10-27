package com.android.systemui.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.ImageView;
import com.android.systemui.R;

/* JADX INFO: loaded from: classes.dex */
public class RoundRectImageView extends ImageView {
    public static final int MODE_ALL = 1;
    public static final int MODE_BOTTOM = 5;
    public static final int MODE_LEFT = 2;
    public static final int MODE_NONE = 0;
    public static final int MODE_RIGHT = 4;
    public static final int MODE_TOP = 3;
    private int mHeight;
    private int mLastRadius;
    private Path mPath;
    private int mRadius;
    private int mRoundMode;
    private int mWidth;

    public RoundRectImageView(Context context) {
        super(context);
        this.mRoundMode = 1;
        init();
    }

    public RoundRectImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mRoundMode = 1;
        init();
    }

    private void init() {
        Path path = new Path();
        this.mPath = path;
        path.setFillType(Path.FillType.EVEN_ODD);
        setCornerRadius(getContext().getResources().getDimensionPixelSize(R.dimen.dp_12));
    }

    public void setRoundMode(int roundMode) {
        this.mRoundMode = roundMode;
    }

    public void setCornerRadius(int radius) {
        this.mRadius = radius;
    }

    private void checkPathChanged() {
        if (getWidth() == this.mWidth && getHeight() == this.mHeight && this.mLastRadius == this.mRadius) {
            return;
        }
        this.mWidth = getWidth();
        this.mHeight = getHeight();
        this.mLastRadius = this.mRadius;
        this.mPath.reset();
        int i = this.mRoundMode;
        if (i == 1) {
            Path path = this.mPath;
            RectF rectF = new RectF(0.0f, 0.0f, this.mWidth, this.mHeight);
            int i2 = this.mRadius;
            path.addRoundRect(rectF, i2, i2, Path.Direction.CW);
            return;
        }
        if (i == 2) {
            Path path2 = this.mPath;
            RectF rectF2 = new RectF(0.0f, 0.0f, this.mWidth, this.mHeight);
            int i3 = this.mRadius;
            path2.addRoundRect(rectF2, new float[]{i3, i3, 0.0f, 0.0f, 0.0f, 0.0f, i3, i3}, Path.Direction.CW);
            return;
        }
        if (i == 3) {
            Path path3 = this.mPath;
            RectF rectF3 = new RectF(0.0f, 0.0f, this.mWidth, this.mHeight);
            int i4 = this.mRadius;
            path3.addRoundRect(rectF3, new float[]{i4, i4, i4, i4, 0.0f, 0.0f, 0.0f, 0.0f}, Path.Direction.CW);
            return;
        }
        if (i == 4) {
            Path path4 = this.mPath;
            RectF rectF4 = new RectF(0.0f, 0.0f, this.mWidth, this.mHeight);
            int i5 = this.mRadius;
            path4.addRoundRect(rectF4, new float[]{0.0f, 0.0f, i5, i5, i5, i5, 0.0f, 0.0f}, Path.Direction.CW);
            return;
        }
        if (i != 5) {
            return;
        }
        Path path5 = this.mPath;
        RectF rectF5 = new RectF(0.0f, 0.0f, this.mWidth, this.mHeight);
        int i6 = this.mRadius;
        path5.addRoundRect(rectF5, new float[]{0.0f, 0.0f, 0.0f, 0.0f, i6, i6, i6, i6}, Path.Direction.CW);
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        if (this.mRoundMode != 0) {
            int iSave = canvas.save();
            checkPathChanged();
            canvas.clipPath(this.mPath);
            super.draw(canvas);
            canvas.restoreToCount(iSave);
            return;
        }
        super.draw(canvas);
    }
}
