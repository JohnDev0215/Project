package androidx.leanback.graphics;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;
import androidx.leanback.R;

/* JADX INFO: loaded from: classes3.dex */
public final class ColorOverlayDimmer {
    private final float mActiveLevel;
    private int mAlpha;
    private float mAlphaFloat;
    private final float mDimmedLevel;
    private final Paint mPaint;

    public static ColorOverlayDimmer createDefault(Context context) {
        TypedArray a = context.obtainStyledAttributes(R.styleable.LeanbackTheme);
        int dimColor = a.getColor(R.styleable.LeanbackTheme_overlayDimMaskColor, context.getResources().getColor(R.color.lb_view_dim_mask_color));
        float activeLevel = a.getFraction(R.styleable.LeanbackTheme_overlayDimActiveLevel, 1, 1, context.getResources().getFraction(R.fraction.lb_view_active_level, 1, 0));
        float dimmedLevel = a.getFraction(R.styleable.LeanbackTheme_overlayDimDimmedLevel, 1, 1, context.getResources().getFraction(R.fraction.lb_view_dimmed_level, 1, 1));
        a.recycle();
        return new ColorOverlayDimmer(dimColor, activeLevel, dimmedLevel);
    }

    public static ColorOverlayDimmer createColorOverlayDimmer(int dimColor, float activeLevel, float dimmedLevel) {
        return new ColorOverlayDimmer(dimColor, activeLevel, dimmedLevel);
    }

    private ColorOverlayDimmer(int dimColor, float activeLevel, float dimmedLevel) {
        activeLevel = activeLevel > 1.0f ? 1.0f : activeLevel;
        activeLevel = activeLevel < 0.0f ? 0.0f : activeLevel;
        dimmedLevel = dimmedLevel > 1.0f ? 1.0f : dimmedLevel;
        dimmedLevel = dimmedLevel < 0.0f ? 0.0f : dimmedLevel;
        this.mPaint = new Paint();
        this.mPaint.setColor(Color.rgb(Color.red(dimColor), Color.green(dimColor), Color.blue(dimColor)));
        this.mActiveLevel = activeLevel;
        this.mDimmedLevel = dimmedLevel;
        setActiveLevel(1.0f);
    }

    public void setActiveLevel(float level) {
        float f = this.mDimmedLevel;
        float f2 = f + ((this.mActiveLevel - f) * level);
        this.mAlphaFloat = f2;
        int i = (int) (f2 * 255.0f);
        this.mAlpha = i;
        this.mPaint.setAlpha(i);
    }

    public boolean needsDraw() {
        return this.mAlpha != 0;
    }

    public int getAlpha() {
        return this.mAlpha;
    }

    public float getAlphaFloat() {
        return this.mAlphaFloat;
    }

    public Paint getPaint() {
        return this.mPaint;
    }

    public int applyToColor(int color) {
        float f = 1.0f - this.mAlphaFloat;
        return Color.argb(Color.alpha(color), (int) (Color.red(color) * f), (int) (Color.green(color) * f), (int) (Color.blue(color) * f));
    }

    public void drawColorOverlay(Canvas c, View v, boolean includePadding) {
        c.save();
        float dx = v.getLeft() + v.getTranslationX();
        float dy = v.getTop() + v.getTranslationY();
        c.translate(dx, dy);
        c.concat(v.getMatrix());
        c.translate(-dx, -dy);
        if (includePadding) {
            c.drawRect(v.getLeft(), v.getTop(), v.getRight(), v.getBottom(), this.mPaint);
        } else {
            c.drawRect(v.getLeft() + v.getPaddingLeft(), v.getTop() + v.getPaddingTop(), v.getRight() - v.getPaddingRight(), v.getBottom() - v.getPaddingBottom(), this.mPaint);
        }
        c.restore();
    }
}
