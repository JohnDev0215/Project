package com.byd.card.drag;

import android.app.UiStyleConfig;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import androidx.core.content.ContextCompat;
import com.android.systemui.R;
import java.lang.ref.SoftReference;

/* JADX INFO: loaded from: classes.dex */
public class ObjectPool {
    private SoftReference<Bitmap> bitmapFolder;
    private Context ctx;
    private LayoutCalculator mLayoutCalculator;
    private int mX;
    private int mY;
    private SoftReference<Paint> paintDarkener;
    private SoftReference<Paint> paintTextBlack;

    public ObjectPool(Context context, LayoutCalculator lc) {
        this.ctx = context;
        this.mLayoutCalculator = lc;
    }

    public Paint getPaintTextWhite() {
        SoftReference<Paint> softReference = this.paintTextBlack;
        Paint paint = (softReference == null || softReference.get() == null) ? null : this.paintTextBlack.get();
        if (paint != null) {
            return paint;
        }
        Paint paintCreatePaintTextWhite = createPaintTextWhite();
        this.paintTextBlack = new SoftReference<>(paintCreatePaintTextWhite);
        return paintCreatePaintTextWhite;
    }

    private Paint createPaintTextWhite() {
        Paint paint = new Paint();
        int uiStyle = UiStyleConfig.getInstance(this.ctx).getUiStyle();
        if (uiStyle == 1) {
            paint.setColor(this.ctx.getResources().getColor(R.color.denza_app_name));
        } else if (uiStyle != 2) {
            paint.setColor(this.ctx.getResources().getColor(R.color.app_center_tv));
        }
        paint.setTextSize(this.ctx.getResources().getDimension(R.dimen.sp_16));
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setAntiAlias(true);
        return paint;
    }

    public Paint getPaintDarkener() {
        SoftReference<Paint> softReference = this.paintDarkener;
        if (softReference != null && softReference.get() != null) {
            return this.paintDarkener.get();
        }
        Paint paint = new Paint();
        paint.setAlpha(255);
        this.paintDarkener = new SoftReference<>(paint);
        return paint;
    }

    public Animation createAnimationIconContract(int x, int y) {
        AnimationSet animationSet = new AnimationSet(true);
        animationSet.setFillEnabled(true);
        animationSet.setFillAfter(true);
        return animationSet;
    }

    public Animation createAnimationAboveFolder() {
        AnimationSet animationSet = new AnimationSet(true);
        animationSet.setFillEnabled(true);
        animationSet.setFillAfter(true);
        return animationSet;
    }

    public Animation createAnimationBisideFolder() {
        AnimationSet animationSet = new AnimationSet(true);
        animationSet.setFillEnabled(true);
        animationSet.setFillAfter(true);
        return animationSet;
    }

    public Animation createAnimationIconExpand() {
        AnimationSet animationSet = new AnimationSet(true);
        animationSet.setFillEnabled(true);
        animationSet.setFillAfter(true);
        return animationSet;
    }

    public Animation createAnimationIconIntoFolder(int x, int y, int left, int top) {
        AnimationSet animationSet = new AnimationSet(true);
        animationSet.setFillEnabled(true);
        animationSet.setFillAfter(true);
        return animationSet;
    }

    public Animation createAnimationOpenFolder(int x, int y, float screenWidth, float screenHeight) {
        AnimationSet animationSet = new AnimationSet(true);
        animationSet.setFillEnabled(true);
        animationSet.setFillAfter(true);
        this.mX = x - (this.mLayoutCalculator.iconWidth / 2);
        this.mY = y - this.mLayoutCalculator.iconHeight;
        ScaleAnimation scaleAnimation = new ScaleAnimation(this.mLayoutCalculator.iconWidth / screenWidth, 1.0f, this.mLayoutCalculator.iconHeight / screenHeight, 1.0f, this.mX, this.mY);
        scaleAnimation.setDuration(200L);
        animationSet.addAnimation(scaleAnimation);
        return animationSet;
    }

    public Animation createAnimationCloseFolder(float screenWidth, float screenHeight) {
        AnimationSet animationSet = new AnimationSet(true);
        animationSet.setFillEnabled(true);
        animationSet.setFillAfter(true);
        ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f, this.mLayoutCalculator.iconWidth / screenWidth, 1.0f, this.mLayoutCalculator.iconHeight / screenHeight, this.mX, this.mY);
        scaleAnimation.setDuration(200L);
        animationSet.addAnimation(scaleAnimation);
        return animationSet;
    }

    public Animation createAnimationPageShow(boolean show) {
        AlphaAnimation alphaAnimation;
        AnimationSet animationSet = new AnimationSet(true);
        animationSet.setFillEnabled(true);
        animationSet.setFillAfter(true);
        if (show) {
            alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        } else {
            alphaAnimation = new AlphaAnimation(1.0f, 0.9f);
        }
        alphaAnimation.setDuration(200L);
        animationSet.addAnimation(alphaAnimation);
        return animationSet;
    }

    public Bitmap getFolderIcon() {
        SoftReference<Bitmap> softReference = this.bitmapFolder;
        if (softReference != null && softReference.get() != null) {
            return this.bitmapFolder.get();
        }
        Bitmap bitmapStretch = stretch(BitmapManager.getBitmap(ContextCompat.getDrawable(this.ctx, R.drawable.launcher_more_folder)));
        this.bitmapFolder = new SoftReference<>(bitmapStretch);
        return bitmapStretch;
    }

    public Bitmap stretch(Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }
        return (bitmap.getWidth() == this.mLayoutCalculator.iconWidth && bitmap.getHeight() == this.mLayoutCalculator.iconHeight) ? bitmap : BitmapManager.createScaledBitmap(bitmap, this.mLayoutCalculator.iconWidth, this.mLayoutCalculator.iconHeight, true);
    }

    public Bitmap stretch(Bitmap bitmap, float scaled) {
        if (bitmap == null) {
            return null;
        }
        return (((float) bitmap.getWidth()) == ((float) this.mLayoutCalculator.iconWidth) * scaled && ((float) bitmap.getHeight()) == ((float) this.mLayoutCalculator.iconHeight) * scaled) ? bitmap : BitmapManager.createScaledBitmap(bitmap, (int) (this.mLayoutCalculator.iconWidth * scaled), (int) (this.mLayoutCalculator.iconHeight * scaled), true);
    }

    public Animation createAnimationClickIcon(int x, int y, float screenWidth, float screenHeight) {
        AnimationSet animationSet = new AnimationSet(true);
        animationSet.setFillEnabled(true);
        animationSet.setFillAfter(true);
        this.mX = x - (this.mLayoutCalculator.iconWidth / 2);
        this.mY = y - this.mLayoutCalculator.iconHeight;
        ScaleAnimation scaleAnimation = new ScaleAnimation(this.mLayoutCalculator.iconWidth / screenWidth, 1.0f, this.mLayoutCalculator.iconHeight / screenHeight, 1.0f, this.mX, this.mY);
        scaleAnimation.setDuration(200L);
        animationSet.addAnimation(scaleAnimation);
        return animationSet;
    }
}
