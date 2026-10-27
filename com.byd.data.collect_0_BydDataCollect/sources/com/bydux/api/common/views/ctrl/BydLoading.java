package com.bydux.api.common.views.ctrl;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.FrameLayout;
import android.widget.TextView;
import d.a.a.e;

/* JADX INFO: loaded from: classes.dex */
public class BydLoading extends FrameLayout {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final String f3581b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Context f3582c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private TextView f3583d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private TextView f3584e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private int f3585f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private int f3586g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private String f3587h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private int f3588i;
    private float j;
    private int k;
    private int l;

    public BydLoading(Context context) {
        super(context);
        this.f3581b = getClass().getSimpleName();
        this.f3585f = 0;
        this.f3586g = 0;
        this.f3587h = "%";
        this.f3588i = 0;
        this.j = 20.0f;
        this.k = -16777216;
        this.l = 2000;
        a(context);
    }

    private void a(Context context) {
        this.f3582c = context;
        this.f3583d = new TextView(context);
        this.f3584e = new TextView(this.f3582c);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
        layoutParams.gravity = 17;
        this.f3583d.setLayoutParams(layoutParams);
        this.f3584e.setLayoutParams(layoutParams);
        addView(this.f3583d);
        addView(this.f3584e);
    }

    private void b(AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = this.f3582c.obtainStyledAttributes(attributeSet, e.BydLoading);
            this.f3585f = typedArrayObtainStyledAttributes.getInteger(e.BydLoading_byd_loading_max, this.f3585f);
            this.f3586g = typedArrayObtainStyledAttributes.getInteger(e.BydLoading_byd_loading_progress, this.f3586g);
            this.f3587h = typedArrayObtainStyledAttributes.getString(e.BydLoading_byd_loading_unit);
            this.f3588i = typedArrayObtainStyledAttributes.getResourceId(e.BydLoading_byd_loading_background, this.f3588i);
            this.j = typedArrayObtainStyledAttributes.getDimension(e.BydLoading_byd_loading_textSize, this.j);
            this.k = typedArrayObtainStyledAttributes.getColor(e.BydLoading_byd_loading_textColor, this.k);
            this.l = typedArrayObtainStyledAttributes.getInteger(e.BydLoading_byd_loading_animSpeed, this.l);
            if (TextUtils.isEmpty(this.f3587h)) {
                this.f3587h = "%";
            }
            typedArrayObtainStyledAttributes.recycle();
            setLoadingMax(this.f3585f);
            setLoadingUnit(this.f3587h);
            setLoadingProgress(this.f3586g);
            setLoadingTextSize(this.j);
            setLoadingTextColor(this.k);
            int i2 = this.f3588i;
            if (i2 > 0) {
                setLoadingBackground(this.f3582c.getDrawable(i2));
            }
        }
    }

    private void c() {
        TextView textView = this.f3583d;
        if (textView != null) {
            textView.clearAnimation();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        setAnimSpeed(this.l);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        c();
    }

    public void setAnimSpeed(int i2) {
        if (i2 <= 0) {
            return;
        }
        this.l = i2;
        if (this.f3583d != null) {
            RotateAnimation rotateAnimation = new RotateAnimation(0.0f, 360.0f, 1, 0.5f, 1, 0.5f);
            rotateAnimation.setFillAfter(true);
            rotateAnimation.setInterpolator(new LinearInterpolator());
            rotateAnimation.setDuration(this.l);
            rotateAnimation.setRepeatCount(-1);
            rotateAnimation.setRepeatMode(1);
            this.f3583d.startAnimation(rotateAnimation);
        }
    }

    public void setLoadingBackground(Drawable drawable) {
        this.f3583d.setBackground(drawable);
    }

    public void setLoadingMax(int i2) {
        this.f3585f = i2;
    }

    public void setLoadingProgress(int i2) {
        int i3 = this.f3585f;
        if (i3 == 0) {
            this.f3586g = 0;
            this.f3584e.setText("0" + this.f3587h);
            return;
        }
        if (i2 > i3) {
            this.f3586g = i3;
        } else {
            this.f3586g = i2;
        }
        int i4 = (this.f3586g * 100) / i3;
        this.f3584e.setText(i4 + this.f3587h);
    }

    public void setLoadingTextColor(int i2) {
        this.f3584e.setTextColor(i2);
    }

    public void setLoadingTextSize(float f2) {
        this.f3584e.setTextSize(f2);
    }

    public void setLoadingUnit(String str) {
        this.f3587h = str;
    }

    public BydLoading(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f3581b = getClass().getSimpleName();
        this.f3585f = 0;
        this.f3586g = 0;
        this.f3587h = "%";
        this.f3588i = 0;
        this.j = 20.0f;
        this.k = -16777216;
        this.l = 2000;
        a(context);
        b(attributeSet);
    }
}
