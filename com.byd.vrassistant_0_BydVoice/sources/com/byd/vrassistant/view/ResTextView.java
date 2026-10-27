package com.byd.vrassistant.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatTextView;

/* JADX INFO: loaded from: classes2.dex */
public class ResTextView extends AppCompatTextView {
    private static final String TAG = "NEU_ResTextView";
    Animation.AnimationListener hideListener;
    boolean isTxtAnim;
    AlphaAnimation mHideAnimation;
    AlphaAnimation mShowAnimation;
    CharSequence newTxt;
    Animation.AnimationListener showListener;

    public ResTextView(Context context) {
        this(context, null);
    }

    public ResTextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ResTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.hideListener = new Animation.AnimationListener() { // from class: com.byd.vrassistant.view.ResTextView.1
            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation) {
                Log.i(ResTextView.TAG, "hide anim onAnimationStart: ");
                ResTextView.this.isTxtAnim = true;
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
                Log.i(ResTextView.TAG, "hide anim onAnimationEnd: ");
                ResTextView.this.isTxtAnim = false;
                ResTextView resTextView = ResTextView.this;
                ResTextView.super.setText(resTextView.newTxt, TextView.BufferType.NORMAL);
                ResTextView.this.setVisibility(8);
            }
        };
        this.showListener = new Animation.AnimationListener() { // from class: com.byd.vrassistant.view.ResTextView.2
            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation) {
                Log.i(ResTextView.TAG, "show anim onAnimationStart: ");
                ResTextView.this.isTxtAnim = true;
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
                Log.i(ResTextView.TAG, "show anim onAnimationEnd: ");
                ResTextView.this.isTxtAnim = false;
                ResTextView.this.setSelected(true);
                ResTextView resTextView = ResTextView.this;
                ResTextView.super.setText(resTextView.newTxt, TextView.BufferType.NORMAL);
            }
        };
        Log.i(TAG, "ResTextView: init");
    }

    @Override // android.widget.TextView
    public void setText(CharSequence charSequence, TextView.BufferType bufferType) {
        if (charSequence.equals(getText())) {
            return;
        }
        Log.i(TAG, "setText:origin= " + ((Object) getText()) + ",newText= " + ((Object) charSequence));
        this.newTxt = charSequence;
        if (this.isTxtAnim) {
            Log.i(TAG, "setText: isTxtAnim clearAnimation");
            clearAnimation();
        }
        if (TextUtils.isEmpty(getText()) && TextUtils.isEmpty(this.newTxt)) {
            super.setText(charSequence, bufferType);
            setVisibility(8);
        } else if (TextUtils.isEmpty(getText())) {
            startAnimation(getmShowAnimation());
            setVisibility(0);
        } else if (!TextUtils.isEmpty(getText()) && !TextUtils.isEmpty(this.newTxt)) {
            super.setText(this.newTxt, TextView.BufferType.NORMAL);
        } else {
            startAnimation(getmHideAnimation());
        }
    }

    public AlphaAnimation getmHideAnimation() {
        if (this.mHideAnimation == null) {
            AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
            this.mHideAnimation = alphaAnimation;
            alphaAnimation.setAnimationListener(this.hideListener);
            this.mHideAnimation.setDuration(200L);
            this.mHideAnimation.setFillAfter(true);
        }
        return this.mHideAnimation;
    }

    public AlphaAnimation getmShowAnimation() {
        if (this.mShowAnimation == null) {
            AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
            this.mShowAnimation = alphaAnimation;
            alphaAnimation.setAnimationListener(this.showListener);
            this.mShowAnimation.setDuration(100L);
            this.mShowAnimation.setFillAfter(true);
        }
        return this.mShowAnimation;
    }
}
