package com.byd.music.view.cubeRotate;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.FrameLayout;
import com.byd.music.utils.LogUtil;

/* JADX INFO: loaded from: classes5.dex */
public class CubeRotateLayout extends FrameLayout {
    public static final int BITMAP_COMPRESS_SIZE = 2048;
    public static final int DIRECT_BOTTOM = 4;
    public static final int DIRECT_LEFT = 1;
    public static final int DIRECT_RIGHT = 2;
    public static final int DIRECT_TOP = 3;
    private static final String TAG = "CubeRotateLayout";
    private RotateAnimListener animListener;
    private int duration;
    private View mSwitchInBitmap;
    private View mSwitchOutBitmap;
    private boolean rotating;

    public interface RotateAnimListener {
        void onAnimationEnd();

        void onAnimationStart();
    }

    public CubeRotateLayout(Context context) {
        super(context);
        this.duration = 500;
    }

    public CubeRotateLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.duration = 500;
    }

    public CubeRotateLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.duration = 500;
    }

    public void setSwitchOutBitmap(View view) {
        this.mSwitchOutBitmap = view;
    }

    public void setSwitchInBitmap(View view) {
        this.mSwitchInBitmap = view;
    }

    public void setAnimDuration(int i) {
        this.duration = i;
    }

    public void setAnimListener(RotateAnimListener rotateAnimListener) {
        this.animListener = rotateAnimListener;
    }

    public void startRotateAnim(boolean z, boolean z2) {
        ViewGroup viewGroup;
        final ViewGroup viewGroup2;
        CubeInAnimation cubeInAnimation;
        CubeOutAnimation cubeOutAnimation;
        LogUtil.d(TAG, "startRotateAnim isLeftOrDownOut:" + z + " isLeftRightAnim:" + z2);
        if (this.rotating || this.mSwitchOutBitmap == null || this.mSwitchInBitmap == null) {
            return;
        }
        this.rotating = true;
        setVisibility(0);
        if (z) {
            viewGroup = (ViewGroup) getChildAt(0);
            viewGroup2 = (ViewGroup) getChildAt(1);
        } else {
            viewGroup = (ViewGroup) getChildAt(1);
            viewGroup2 = (ViewGroup) getChildAt(0);
        }
        viewGroup.getChildAt(0).setVisibility(0);
        if (z2) {
            cubeInAnimation = new CubeInAnimation(z ? 2 : 1);
            cubeOutAnimation = new CubeOutAnimation(z ? 1 : 2);
        } else {
            cubeInAnimation = new CubeInAnimation(z ? 3 : 4);
            cubeOutAnimation = new CubeOutAnimation(z ? 4 : 3);
        }
        cubeInAnimation.setDuration(this.duration);
        cubeInAnimation.setFillAfter(true);
        cubeOutAnimation.setDuration(this.duration);
        cubeOutAnimation.setFillAfter(true);
        viewGroup.startAnimation(cubeInAnimation);
        viewGroup2.startAnimation(cubeOutAnimation);
        cubeOutAnimation.setAnimationListener(new Animation.AnimationListener() { // from class: com.byd.music.view.cubeRotate.CubeRotateLayout.1
            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation) {
                CubeRotateLayout.this.onAnimStart();
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
                viewGroup2.getChildAt(0).setVisibility(8);
                CubeRotateLayout.this.onAnimEnd();
            }
        });
    }

    public static Bitmap rotateBitmap(Bitmap bitmap, float f) {
        if (bitmap == null) {
            return null;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Matrix matrix = new Matrix();
        matrix.setRotate(f);
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, false);
        if (bitmapCreateBitmap.equals(bitmap)) {
            return bitmapCreateBitmap;
        }
        bitmap.recycle();
        System.gc();
        return bitmapCreateBitmap;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onAnimStart() {
        RotateAnimListener rotateAnimListener = this.animListener;
        if (rotateAnimListener != null) {
            rotateAnimListener.onAnimationStart();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onAnimEnd() {
        if (this.mSwitchOutBitmap != null) {
            this.mSwitchOutBitmap = null;
        }
        if (this.mSwitchInBitmap != null) {
            this.mSwitchInBitmap = null;
        }
        System.gc();
        RotateAnimListener rotateAnimListener = this.animListener;
        if (rotateAnimListener != null) {
            rotateAnimListener.onAnimationEnd();
        }
        this.rotating = false;
    }
}
