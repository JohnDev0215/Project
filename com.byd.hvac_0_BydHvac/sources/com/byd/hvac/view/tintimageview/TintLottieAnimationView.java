package com.byd.hvac.view.tintimageview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import com.airbnb.lottie.LottieAnimationView;
import com.byd.hvac.R;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class TintLottieAnimationView extends LottieAnimationView {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f1084a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f1085b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f1086c;
    public int d;
    public int e;
    public Drawable f;
    public Drawable g;
    public Context h;

    public TintLottieAnimationView(@NonNull Context context) {
        super(context);
        this.f1084a = 0;
        this.f1085b = 0;
        this.f1086c = 0;
        this.d = 0;
        this.e = 0;
        e(context);
    }

    public void a(int i) {
        Drawable background = getBackground();
        if (i <= 0) {
            setBackgroundDrawable(background);
        } else if (background != null) {
            Drawable drawableWrap = DrawableCompat.wrap(background.mutate());
            DrawableCompat.setTint(drawableWrap, ContextCompat.getColor(getContext(), i));
            setBackgroundDrawable(drawableWrap);
        }
    }

    public void b(int i) {
        if (i == 0) {
            setImageDrawable(this.f);
            setImageTintList(ContextCompat.getColorStateList(this.h, R.color.text_tem_white));
        } else {
            if (i != 1) {
                return;
            }
            setImageDrawable(this.g);
            setImageTintList(ContextCompat.getColorStateList(this.h, R.color.defrost_color_select));
        }
    }

    public void c(int i) {
        if (i == 0) {
            setImageDrawable(this.f);
            setImageTintList(ContextCompat.getColorStateList(this.h, R.color.text_tem_white));
        } else {
            if (i != 1) {
                return;
            }
            setImageDrawable(this.g);
            setImageTintList(ContextCompat.getColorStateList(this.h, R.color.text_tem_select));
        }
    }

    public final void d(Context context, AttributeSet attributeSet) {
        if (attributeSet == null) {
            return;
        }
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.tint_imageview);
        this.f1086c = typedArrayObtainStyledAttributes.getResourceId(2, this.f1086c);
        this.d = typedArrayObtainStyledAttributes.getResourceId(0, this.d);
        this.e = typedArrayObtainStyledAttributes.getResourceId(1, this.e);
        this.f1084a = typedArrayObtainStyledAttributes.getResourceId(5, this.f1084a);
        this.g = typedArrayObtainStyledAttributes.getDrawable(4);
        this.f = typedArrayObtainStyledAttributes.getDrawable(3);
        typedArrayObtainStyledAttributes.recycle();
    }

    public final void e(Context context) {
        this.h = context;
        a(this.d);
    }

    public int getCurrentState() {
        return this.f1085b;
    }

    public Drawable getTintButtonOff() {
        return this.f;
    }

    public Drawable getTintButtonOn() {
        return this.g;
    }

    public void setTintButton(Drawable drawable) {
        this.g = drawable;
        this.f = drawable;
    }

    public void setTintButtonOff(Drawable drawable) {
        this.f = drawable;
    }

    public void setTintButtonOn(Drawable drawable) {
        this.g = drawable;
    }

    public TintLottieAnimationView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f1084a = 0;
        this.f1085b = 0;
        this.f1086c = 0;
        this.d = 0;
        this.e = 0;
        d(context, attributeSet);
        e(context);
    }

    public TintLottieAnimationView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f1084a = 0;
        this.f1085b = 0;
        this.f1086c = 0;
        this.d = 0;
        this.e = 0;
        d(context, attributeSet);
        e(context);
    }
}
