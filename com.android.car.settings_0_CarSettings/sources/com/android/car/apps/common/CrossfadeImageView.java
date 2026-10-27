package com.android.car.apps.common;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.core.view.ViewCompat;
import java.util.Objects;

/* JADX INFO: loaded from: classes5.dex */
public class CrossfadeImageView extends FrameLayout {
    private CropAlignedImageView mActiveImageView;
    private final Animation.AnimationListener mAnimationListener;
    private Bitmap mCurrentBitmap;
    private Integer mCurrentColor;
    private Animation mImageInAnimation;
    private Animation mImageOutAnimation;
    private final CropAlignedImageView mImageView1;
    private final CropAlignedImageView mImageView2;
    private CropAlignedImageView mInactiveImageView;

    public CrossfadeImageView(Context context) {
        this(context, null);
    }

    public CrossfadeImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CrossfadeImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public CrossfadeImageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.mCurrentBitmap = null;
        this.mCurrentColor = null;
        this.mAnimationListener = new Animation.AnimationListener() { // from class: com.android.car.apps.common.CrossfadeImageView.1
            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
                if (CrossfadeImageView.this.mInactiveImageView != null) {
                    CrossfadeImageView.this.mInactiveImageView.setVisibility(8);
                }
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation) {
            }
        };
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(-1, -1);
        ImageView imageViewBackground = new ImageView(context, attrs, defStyleAttr, defStyleRes);
        imageViewBackground.setLayoutParams(lp);
        imageViewBackground.setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
        addView(imageViewBackground);
        CropAlignedImageView cropAlignedImageView = new CropAlignedImageView(context, attrs, defStyleAttr, defStyleRes);
        this.mImageView1 = cropAlignedImageView;
        cropAlignedImageView.setLayoutParams(lp);
        addView(this.mImageView1);
        CropAlignedImageView cropAlignedImageView2 = new CropAlignedImageView(context, attrs, defStyleAttr, defStyleRes);
        this.mImageView2 = cropAlignedImageView2;
        cropAlignedImageView2.setLayoutParams(lp);
        addView(this.mImageView2);
        this.mActiveImageView = this.mImageView1;
        this.mInactiveImageView = this.mImageView2;
        Animation animationLoadAnimation = AnimationUtils.loadAnimation(context, R.anim.image_in);
        this.mImageInAnimation = animationLoadAnimation;
        animationLoadAnimation.setInterpolator(new DecelerateInterpolator());
        this.mImageOutAnimation = AnimationUtils.loadAnimation(context, R.anim.image_out);
    }

    public void setImageBitmap(Bitmap bitmap, boolean showAnimation) {
        if (Objects.equals(this.mCurrentBitmap, bitmap)) {
            return;
        }
        this.mCurrentBitmap = bitmap;
        this.mCurrentColor = null;
        this.mInactiveImageView.setImageBitmap(bitmap);
        if (showAnimation) {
            animateViews();
        } else {
            this.mActiveImageView.setImageBitmap(bitmap);
        }
    }

    @Override // android.view.View
    public void setBackgroundColor(int color) {
        Integer num = this.mCurrentColor;
        if (num != null && num.intValue() == color) {
            return;
        }
        this.mInactiveImageView.setImageBitmap(null);
        this.mCurrentBitmap = null;
        this.mCurrentColor = Integer.valueOf(color);
        this.mInactiveImageView.setBackgroundColor(color);
        animateViews();
    }

    private void animateViews() {
        this.mInactiveImageView.setVisibility(0);
        this.mInactiveImageView.startAnimation(this.mImageInAnimation);
        this.mInactiveImageView.bringToFront();
        this.mActiveImageView.startAnimation(this.mImageOutAnimation);
        this.mImageOutAnimation.setAnimationListener(this.mAnimationListener);
        CropAlignedImageView cropAlignedImageView = this.mActiveImageView;
        CropAlignedImageView cropAlignedImageView2 = this.mImageView1;
        if (cropAlignedImageView == cropAlignedImageView2) {
            this.mActiveImageView = this.mImageView2;
            this.mInactiveImageView = cropAlignedImageView2;
        } else {
            this.mActiveImageView = cropAlignedImageView2;
            this.mInactiveImageView = this.mImageView2;
        }
    }

    public void setImageAdditionalScale(float scale) {
        this.mImageView2.setImageAdditionalScale(scale);
        this.mImageView1.setImageAdditionalScale(scale);
    }
}
