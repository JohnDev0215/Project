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
import androidx.core.view.ViewCompat;
import com.bydux.api.R;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class BydLoading extends FrameLayout {
    private final String TAG;
    private int mAnimSpeed;
    private int mBackgroundId;
    private Context mContext;
    private TextView mLoading;
    private TextView mLoadingText;
    private int mMax;
    private int mProgress;
    private int mTextColor;
    private float mTextSize;
    private String mUnit;

    public BydLoading(Context context) {
        super(context);
        this.TAG = getClass().getSimpleName();
        this.mMax = 0;
        this.mProgress = 0;
        this.mUnit = "%";
        this.mBackgroundId = 0;
        this.mTextSize = 20.0f;
        this.mTextColor = ViewCompat.MEASURED_STATE_MASK;
        this.mAnimSpeed = 2000;
        initView(context);
    }

    private void initView(Context context) {
        this.mContext = context;
        this.mLoading = new TextView(this.mContext);
        this.mLoadingText = new TextView(this.mContext);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
        layoutParams.gravity = 17;
        this.mLoading.setLayoutParams(layoutParams);
        this.mLoadingText.setLayoutParams(layoutParams);
        addView(this.mLoading);
        addView(this.mLoadingText);
    }

    private void loadAttrs(AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = this.mContext.obtainStyledAttributes(attributeSet, R.styleable.BydLoading);
            this.mMax = typedArrayObtainStyledAttributes.getInteger(R.styleable.BydLoading_byd_loading_max, this.mMax);
            this.mProgress = typedArrayObtainStyledAttributes.getInteger(R.styleable.BydLoading_byd_loading_progress, this.mProgress);
            this.mUnit = typedArrayObtainStyledAttributes.getString(R.styleable.BydLoading_byd_loading_unit);
            this.mBackgroundId = typedArrayObtainStyledAttributes.getResourceId(R.styleable.BydLoading_byd_loading_background, this.mBackgroundId);
            this.mTextSize = typedArrayObtainStyledAttributes.getDimension(R.styleable.BydLoading_byd_loading_textSize, this.mTextSize);
            this.mTextColor = typedArrayObtainStyledAttributes.getColor(R.styleable.BydLoading_byd_loading_textColor, this.mTextColor);
            this.mAnimSpeed = typedArrayObtainStyledAttributes.getInteger(R.styleable.BydLoading_byd_loading_animSpeed, this.mAnimSpeed);
            if (TextUtils.isEmpty(this.mUnit)) {
                this.mUnit = "%";
            }
            typedArrayObtainStyledAttributes.recycle();
            setLoadingMax(this.mMax);
            setLoadingUnit(this.mUnit);
            setLoadingProgress(this.mProgress);
            setLoadingTextSize(this.mTextSize);
            setLoadingTextColor(this.mTextColor);
            int i = this.mBackgroundId;
            if (i > 0) {
                setLoadingBackground(this.mContext.getDrawable(i));
            }
        }
    }

    private void removeAnimation() {
        TextView textView = this.mLoading;
        if (textView != null) {
            textView.clearAnimation();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        setAnimSpeed(this.mAnimSpeed);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        removeAnimation();
    }

    public void setAnimSpeed(int i) {
        if (i <= 0) {
            return;
        }
        this.mAnimSpeed = i;
        if (this.mLoading != null) {
            RotateAnimation rotateAnimation = new RotateAnimation(0.0f, 360.0f, 1, 0.5f, 1, 0.5f);
            rotateAnimation.setFillAfter(true);
            rotateAnimation.setInterpolator(new LinearInterpolator());
            rotateAnimation.setDuration(this.mAnimSpeed);
            rotateAnimation.setRepeatCount(-1);
            rotateAnimation.setRepeatMode(1);
            this.mLoading.startAnimation(rotateAnimation);
        }
    }

    public void setLoadingBackground(Drawable drawable) {
        this.mLoading.setBackground(drawable);
    }

    public void setLoadingMax(int i) {
        this.mMax = i;
    }

    public void setLoadingProgress(int i) {
        int i2 = this.mMax;
        if (i2 == 0) {
            this.mProgress = 0;
            this.mLoadingText.setText("0" + this.mUnit);
            return;
        }
        if (i > i2) {
            this.mProgress = i2;
        } else {
            this.mProgress = i;
        }
        int i3 = (this.mProgress * 100) / i2;
        this.mLoadingText.setText(i3 + this.mUnit);
    }

    public void setLoadingTextColor(int i) {
        this.mLoadingText.setTextColor(i);
    }

    public void setLoadingTextSize(float f) {
        this.mLoadingText.setTextSize(f);
    }

    public void setLoadingUnit(String str) {
        this.mUnit = str;
    }

    public BydLoading(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.TAG = getClass().getSimpleName();
        this.mMax = 0;
        this.mProgress = 0;
        this.mUnit = "%";
        this.mBackgroundId = 0;
        this.mTextSize = 20.0f;
        this.mTextColor = ViewCompat.MEASURED_STATE_MASK;
        this.mAnimSpeed = 2000;
        initView(context);
        loadAttrs(attributeSet);
    }
}
