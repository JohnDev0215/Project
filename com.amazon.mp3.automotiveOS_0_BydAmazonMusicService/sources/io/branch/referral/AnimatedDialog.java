package io.branch.referral;

import android.R;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.TranslateAnimation;

/* JADX INFO: loaded from: classes7.dex */
public class AnimatedDialog extends Dialog {
    private boolean isClosing_;
    private final boolean isFullWidthStyle_;

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (this.isFullWidthStyle_) {
            WindowManager.LayoutParams attributes = getWindow().getAttributes();
            attributes.width = -1;
            getWindow().setAttributes(attributes);
        }
    }

    @Override // android.app.Dialog
    public void show() {
        slideOpen();
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public void cancel() {
        slideClose();
    }

    @Override // android.app.Dialog
    public void setContentView(int i) {
        setDialogWindowAttributes();
        super.setContentView(i);
    }

    public void setDialogWindowAttributes() {
        requestWindowFeature(1);
        getWindow().setBackgroundDrawable(new ColorDrawable(0));
        getWindow().addFlags(2);
        getWindow().addFlags(1024);
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.copyFrom(getWindow().getAttributes());
        layoutParams.width = -1;
        layoutParams.height = -1;
        layoutParams.gravity = 80;
        layoutParams.dimAmount = 0.8f;
        getWindow().setAttributes(layoutParams);
        getWindow().setWindowAnimations(R.anim.slide_in_left);
        setCanceledOnTouchOutside(true);
    }

    private void slideOpen() {
        TranslateAnimation translateAnimation = new TranslateAnimation(1, 0.0f, 1, 0.0f, 1, 1.0f, 1, 0.0f);
        translateAnimation.setDuration(500L);
        translateAnimation.setInterpolator(new AccelerateInterpolator());
        ((ViewGroup) getWindow().getDecorView()).getChildAt(0).startAnimation(translateAnimation);
        super.show();
    }

    private void slideClose() {
        if (this.isClosing_) {
            return;
        }
        this.isClosing_ = true;
        TranslateAnimation translateAnimation = new TranslateAnimation(1, 0.0f, 1, 0.0f, 1, 0.0f, 1, 1.0f);
        translateAnimation.setDuration(500L);
        translateAnimation.setInterpolator(new DecelerateInterpolator());
        ((ViewGroup) getWindow().getDecorView()).getChildAt(0).startAnimation(translateAnimation);
        translateAnimation.setAnimationListener(new Animation.AnimationListener() { // from class: io.branch.referral.AnimatedDialog.2
            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
                AnimatedDialog.this.dismiss();
            }
        });
    }
}
