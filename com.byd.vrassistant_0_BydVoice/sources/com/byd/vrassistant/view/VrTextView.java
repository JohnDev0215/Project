package com.byd.vrassistant.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatTextView;
import com.byd.vrassistant.bean.ContentState;
import com.byd.vrassistant.bean.VrContent;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class VrTextView extends AppCompatTextView {
    private static final String TAG = "NEU_VrTextView";
    List<ContentState> changeColorOnly;
    Context context;
    Animation.AnimationListener hideListener;
    boolean isTxtAnim;
    VrContent lastContent;
    AlphaAnimation mHideAnimation;
    AlphaAnimation mShowAnimation;
    CharSequence newTxt;
    Animation.AnimationListener showListener;
    int skinType;

    @Override // android.view.View
    public int getFocusable() {
        return 1;
    }

    @Override // android.view.View
    public boolean isFocused() {
        return true;
    }

    public VrTextView(Context context) {
        this(context, null);
    }

    public VrTextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public VrTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.changeColorOnly = Arrays.asList(ContentState.INIT_MSG, ContentState.ISSUING, ContentState.VALID, ContentState.INVALID, ContentState.RESPONSE);
        this.hideListener = new Animation.AnimationListener() { // from class: com.byd.vrassistant.view.VrTextView.1
            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation) {
                VrTextView.this.isTxtAnim = true;
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
                if (TextUtils.isEmpty(VrTextView.this.newTxt)) {
                    VrTextView.this.isTxtAnim = false;
                    VrTextView.this.setAlpha(1.0f);
                    VrTextView vrTextView = VrTextView.this;
                    VrTextView.super.setText(vrTextView.newTxt, TextView.BufferType.NORMAL);
                    return;
                }
                VrTextView vrTextView2 = VrTextView.this;
                vrTextView2.startAnimation(vrTextView2.getmShowAnimation());
            }
        };
        this.showListener = new Animation.AnimationListener() { // from class: com.byd.vrassistant.view.VrTextView.2
            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation) {
                VrTextView.this.isTxtAnim = true;
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
                VrTextView.this.isTxtAnim = false;
                VrTextView vrTextView = VrTextView.this;
                VrTextView.super.setText(vrTextView.newTxt, TextView.BufferType.NORMAL);
            }
        };
        Log.i(TAG, "VrTextView: init");
    }

    @Override // android.widget.TextView
    public void setText(CharSequence charSequence, TextView.BufferType bufferType) {
        if (charSequence.equals(getText())) {
            return;
        }
        this.newTxt = charSequence;
        Log.i(TAG, "setText() called with: text = [" + ((Object) charSequence) + "], getText = [" + ((Object) getText()) + "]");
        if (this.isTxtAnim) {
            Log.i(TAG, "setText: isTxtAnim clearAnimation");
            clearAnimation();
        }
        if (TextUtils.isEmpty(charSequence) || this.changeColorOnly.contains(this.lastContent.getContentState())) {
            Log.i(TAG, "setText: text is empty or changeColorOnly");
            super.setText(charSequence, bufferType);
            return;
        }
        if (TextUtils.isEmpty(charSequence)) {
            startAnimation(getmHideAnimation());
        }
        if (TextUtils.isEmpty(getText())) {
            startAnimation(getmShowAnimation());
        } else {
            startAnimation(getmHideAnimation());
        }
    }

    public void setVrContent(VrContent vrContent) {
        this.lastContent = vrContent;
        setText(vrContent.getContentState().getText(this.context, vrContent));
        setTextColor(vrContent.getContentState().getTextColor(getContext(), this.skinType == 1));
        setSelected(true);
    }

    public void setContext(Context context) {
        this.context = context;
        VrContent vrContent = this.lastContent;
        if (vrContent == null) {
            return;
        }
        setText(vrContent.getContentState().getText(context, this.lastContent));
    }

    public void setSkin(int i) {
        this.skinType = i;
        VrContent vrContent = this.lastContent;
        if (vrContent == null) {
            return;
        }
        setTextColor(vrContent.getContentState().getTextColor(getContext(), this.skinType == 1));
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
