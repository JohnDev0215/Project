package com.android.launcher3.allapps;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.Property;
import android.view.ContextThemeWrapper;
import com.android.launcher3.LauncherAnimUtils;
import com.android.launcher3.R;
import com.android.launcher3.util.Themes;

/* JADX INFO: loaded from: classes4.dex */
public class AllAppsBackgroundDrawable extends Drawable {
    private ObjectAnimator mBackgroundAnim;
    protected final TransformedImageDrawable mHand;
    private final int mHeight;
    protected final TransformedImageDrawable[] mIcons;
    private final int mWidth;

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
    }

    protected static class TransformedImageDrawable {
        private int mAlpha;
        private int mGravity;
        private Drawable mImage;
        private float mXPercent;
        private float mYPercent;

        public TransformedImageDrawable(Context context, int resourceId, float xPct, float yPct, int gravity) {
            this.mImage = context.getDrawable(resourceId);
            this.mXPercent = xPct;
            this.mYPercent = yPct;
            this.mGravity = gravity;
        }

        public void setAlpha(int alpha) {
            this.mImage.setAlpha(alpha);
            this.mAlpha = alpha;
        }

        public int getAlpha() {
            return this.mAlpha;
        }

        public void updateBounds(Rect bounds) {
            int intrinsicWidth = this.mImage.getIntrinsicWidth();
            int intrinsicHeight = this.mImage.getIntrinsicHeight();
            int iWidth = bounds.left + ((int) (this.mXPercent * bounds.width()));
            int iHeight = bounds.top + ((int) (this.mYPercent * bounds.height()));
            int i = this.mGravity;
            if ((i & 1) == 1) {
                iWidth -= intrinsicWidth / 2;
            }
            if ((i & 16) == 16) {
                iHeight -= intrinsicHeight / 2;
            }
            this.mImage.setBounds(iWidth, iHeight, intrinsicWidth + iWidth, intrinsicHeight + iHeight);
        }

        public void draw(Canvas canvas) {
            this.mImage.draw(canvas);
        }

        public Rect getBounds() {
            return this.mImage.getBounds();
        }
    }

    public AllAppsBackgroundDrawable(Context context) {
        Resources resources = context.getResources();
        this.mWidth = resources.getDimensionPixelSize(R.dimen.all_apps_background_canvas_width);
        this.mHeight = resources.getDimensionPixelSize(R.dimen.all_apps_background_canvas_height);
        ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(context, Themes.getAttrBoolean(context, R.attr.isMainColorDark) ? R.style.AllAppsEmptySearchBackground_Dark : R.style.AllAppsEmptySearchBackground);
        this.mHand = new TransformedImageDrawable(contextThemeWrapper, R.drawable.ic_all_apps_bg_hand, 0.575f, 0.0f, 1);
        this.mIcons = new TransformedImageDrawable[]{new TransformedImageDrawable(contextThemeWrapper, R.drawable.ic_all_apps_bg_icon_1, 0.375f, 0.0f, 1), new TransformedImageDrawable(contextThemeWrapper, R.drawable.ic_all_apps_bg_icon_2, 0.3125f, 0.2f, 1), new TransformedImageDrawable(contextThemeWrapper, R.drawable.ic_all_apps_bg_icon_3, 0.475f, 0.26f, 1), new TransformedImageDrawable(contextThemeWrapper, R.drawable.ic_all_apps_bg_icon_4, 0.7f, 0.125f, 1)};
    }

    public void animateBgAlpha(float finalAlpha, int duration) {
        int i = (int) (finalAlpha * 255.0f);
        if (getAlpha() != i) {
            this.mBackgroundAnim = cancelAnimator(this.mBackgroundAnim);
            ObjectAnimator objectAnimatorOfInt = ObjectAnimator.ofInt(this, (Property<AllAppsBackgroundDrawable, Integer>) LauncherAnimUtils.DRAWABLE_ALPHA, i);
            this.mBackgroundAnim = objectAnimatorOfInt;
            objectAnimatorOfInt.setDuration(duration);
            this.mBackgroundAnim.start();
        }
    }

    public void setBgAlpha(float finalAlpha) {
        int i = (int) (finalAlpha * 255.0f);
        if (getAlpha() != i) {
            this.mBackgroundAnim = cancelAnimator(this.mBackgroundAnim);
            setAlpha(i);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return this.mWidth;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return this.mHeight;
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        this.mHand.draw(canvas);
        int i = 0;
        while (true) {
            TransformedImageDrawable[] transformedImageDrawableArr = this.mIcons;
            if (i >= transformedImageDrawableArr.length) {
                return;
            }
            transformedImageDrawableArr[i].draw(canvas);
            i++;
        }
    }

    @Override // android.graphics.drawable.Drawable
    protected void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        this.mHand.updateBounds(bounds);
        int i = 0;
        while (true) {
            TransformedImageDrawable[] transformedImageDrawableArr = this.mIcons;
            if (i < transformedImageDrawableArr.length) {
                transformedImageDrawableArr[i].updateBounds(bounds);
                i++;
            } else {
                invalidateSelf();
                return;
            }
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int alpha) {
        this.mHand.setAlpha(alpha);
        int i = 0;
        while (true) {
            TransformedImageDrawable[] transformedImageDrawableArr = this.mIcons;
            if (i < transformedImageDrawableArr.length) {
                transformedImageDrawableArr[i].setAlpha(alpha);
                i++;
            } else {
                invalidateSelf();
                return;
            }
        }
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        return this.mHand.getAlpha();
    }

    private ObjectAnimator cancelAnimator(ObjectAnimator animator) {
        if (animator == null) {
            return null;
        }
        animator.cancel();
        return null;
    }
}
