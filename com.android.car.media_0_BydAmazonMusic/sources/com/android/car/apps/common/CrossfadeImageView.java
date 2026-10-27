package com.android.car.apps.common;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.core.view.ViewCompat;
import com.android.car.ui.R$anim;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class CrossfadeImageView extends FrameLayout {
    public final CropAlignedImageView a;
    public final CropAlignedImageView b;
    public CropAlignedImageView c;
    public CropAlignedImageView d;
    public Bitmap e;
    public Integer f;
    public Animation g;
    public Animation h;
    public final a i;

    public class a implements Animation.AnimationListener {
        public a() {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public final void onAnimationEnd(Animation animation) {
            CropAlignedImageView cropAlignedImageView = CrossfadeImageView.this.d;
            if (cropAlignedImageView != null) {
                cropAlignedImageView.setVisibility(8);
            }
        }

        @Override // android.view.animation.Animation.AnimationListener
        public final void onAnimationRepeat(Animation animation) {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public final void onAnimationStart(Animation animation) {
        }
    }

    public CrossfadeImageView(Context context) {
        this(context, null);
    }

    public final void a() {
        this.d.setVisibility(0);
        this.d.startAnimation(this.g);
        this.d.bringToFront();
        this.c.startAnimation(this.h);
        this.h.setAnimationListener(this.i);
        CropAlignedImageView cropAlignedImageView = this.c;
        CropAlignedImageView cropAlignedImageView2 = this.a;
        if (cropAlignedImageView == cropAlignedImageView2) {
            this.c = this.b;
            this.d = cropAlignedImageView2;
        } else {
            this.c = cropAlignedImageView2;
            this.d = this.b;
        }
    }

    public final void b(Bitmap bitmap, boolean z) {
        if (Objects.equals(this.e, bitmap)) {
            return;
        }
        this.e = bitmap;
        this.f = null;
        this.d.setImageBitmap(bitmap);
        if (z) {
            a();
        } else {
            this.c.setImageBitmap(bitmap);
        }
    }

    @Override // android.view.View
    public void setBackgroundColor(int i) {
        Integer num = this.f;
        if (num == null || num.intValue() != i) {
            this.d.setImageBitmap(null);
            this.e = null;
            this.f = Integer.valueOf(i);
            this.d.setBackgroundColor(i);
            a();
        }
    }

    public void setImageAdditionalScale(float f) {
        this.b.setImageAdditionalScale(f);
        this.a.setImageAdditionalScale(f);
    }

    public CrossfadeImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CrossfadeImageView(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public CrossfadeImageView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.e = null;
        this.f = null;
        this.i = new a();
        ViewGroup.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        View imageView = new ImageView(context, attributeSet, i, i2);
        imageView.setLayoutParams(layoutParams);
        imageView.setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
        addView(imageView);
        CropAlignedImageView cropAlignedImageView = new CropAlignedImageView(context, attributeSet, i, i2);
        this.a = cropAlignedImageView;
        cropAlignedImageView.setLayoutParams(layoutParams);
        addView(cropAlignedImageView);
        CropAlignedImageView cropAlignedImageView2 = new CropAlignedImageView(context, attributeSet, i, i2);
        this.b = cropAlignedImageView2;
        cropAlignedImageView2.setLayoutParams(layoutParams);
        addView(cropAlignedImageView2);
        this.c = cropAlignedImageView;
        this.d = cropAlignedImageView2;
        Animation animationLoadAnimation = AnimationUtils.loadAnimation(context, R$anim.image_in);
        this.g = animationLoadAnimation;
        animationLoadAnimation.setInterpolator(new DecelerateInterpolator());
        this.h = AnimationUtils.loadAnimation(context, R$anim.image_out);
    }
}
