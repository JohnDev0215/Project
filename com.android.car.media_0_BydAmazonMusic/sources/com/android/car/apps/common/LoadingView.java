package com.android.car.apps.common;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.android.car.ui.R$anim;
import com.android.car.ui.R$id;
import com.android.car.ui.R$layout;
import com.android.car.ui.R$styleable;

/* JADX INFO: loaded from: classes.dex */
public class LoadingView extends LinearLayout {
    public LoadingView(@NonNull Context context) {
        this(context, null);
    }

    public void setRotate(boolean z) {
        ImageView imageView = (ImageView) findViewById(R$id.iv_load);
        if (!z) {
            imageView.clearAnimation();
            setVisibility(8);
        } else {
            setVisibility(0);
            Animation animationLoadAnimation = AnimationUtils.loadAnimation(imageView.getContext(), R$anim.rotate_anim);
            animationLoadAnimation.setInterpolator(new LinearInterpolator());
            imageView.startAnimation(animationLoadAnimation);
        }
    }

    public LoadingView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public LoadingView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(R$layout.layout_loading, (ViewGroup) this, true);
        setVisibility(8);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.LoadingView);
        setRotate(typedArrayObtainStyledAttributes.getBoolean(R$styleable.LoadingView_initRotate, false));
        typedArrayObtainStyledAttributes.recycle();
    }
}
