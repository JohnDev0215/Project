package com.byd.music.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.byd.music.R;

/* JADX INFO: loaded from: classes5.dex */
public class LoadingView extends ConstraintLayout {
    public LoadingView(Context context) {
        this(context, null);
    }

    public LoadingView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public LoadingView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.LoadingView);
        setRotate(typedArrayObtainStyledAttributes.getBoolean(R.styleable.LoadingView_initRotate, false));
        typedArrayObtainStyledAttributes.recycle();
    }

    private void init() {
        ((LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.layout_loading, (ViewGroup) this, true);
        setVisibility(8);
    }

    public void setRotate(boolean z) {
        ImageView imageView = (ImageView) findViewById(R.id.iv_load);
        if (z) {
            setVisibility(0);
            Animation animationLoadAnimation = AnimationUtils.loadAnimation(imageView.getContext(), R.anim.rotate_anim);
            animationLoadAnimation.setInterpolator(new LinearInterpolator());
            imageView.startAnimation(animationLoadAnimation);
            return;
        }
        imageView.clearAnimation();
        setVisibility(8);
    }
}
