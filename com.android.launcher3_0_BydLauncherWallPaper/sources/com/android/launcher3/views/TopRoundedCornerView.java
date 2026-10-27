package com.android.launcher3.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import com.android.launcher3.R;
import com.android.launcher3.util.Themes;

/* JADX INFO: loaded from: classes4.dex */
public class TopRoundedCornerView extends SpringRelativeLayout {
    private final Path mClipPath;
    private int mNavBarScrimHeight;
    private final Paint mNavBarScrimPaint;
    private float[] mRadii;
    private final RectF mRect;

    public TopRoundedCornerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mRect = new RectF();
        this.mClipPath = new Path();
        this.mNavBarScrimHeight = 0;
        float dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.bg_round_rect_radius);
        this.mRadii = new float[]{dimensionPixelSize, dimensionPixelSize, dimensionPixelSize, dimensionPixelSize, 0.0f, 0.0f, 0.0f, 0.0f};
        Paint paint = new Paint();
        this.mNavBarScrimPaint = paint;
        paint.setColor(Themes.getAttrColor(context, R.attr.allAppsNavBarScrimColor));
    }

    public TopRoundedCornerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public void setNavBarScrimHeight(int height) {
        if (this.mNavBarScrimHeight != height) {
            this.mNavBarScrimHeight = height;
            invalidate();
        }
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        canvas.save();
        canvas.clipPath(this.mClipPath);
        super.draw(canvas);
        canvas.restore();
        if (this.mNavBarScrimHeight > 0) {
            canvas.drawRect(0.0f, getHeight() - this.mNavBarScrimHeight, getWidth(), getHeight(), this.mNavBarScrimPaint);
        }
    }

    @Override // android.widget.RelativeLayout, android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        this.mRect.set(0.0f, 0.0f, getMeasuredWidth(), getMeasuredHeight());
        this.mClipPath.reset();
        this.mClipPath.addRoundRect(this.mRect, this.mRadii, Path.Direction.CW);
    }
}
