package com.byd.vrassistant.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import com.byd.vrassistant.base.FrameLayoutLifecycle;
import com.byd.vrassistant.bean.NetShowResult;
import com.byd.vrassistant.ui.R;
import com.byd.vrassistant.utils.RLog;
import com.byd.vrassistant.viewmodel.CommonViewModel;
import com.byd.vrassistant.viewmodel.ViewModelHelper;

/* JADX INFO: loaded from: classes2.dex */
public class ViewContainer extends FrameLayoutLifecycle {
    private static final String TAG = "NEU_ViewContainer";
    FrameLayout animParentView;
    Animation closeAnim;
    Animation.AnimationListener closeListener;
    CommonViewModel commonViewModel;
    Runnable dismissRunnable;
    ViewGroup factParentView;
    FrameLayout flNetErr;
    NetErrorHintView netErrorHintView;
    NetErrorView netErrorView;
    Animation.AnimationListener openListener;
    Animation startAnim;
    Runnable startRunnable;
    VoiceBar voiceBar;

    @Override // com.byd.vrassistant.base.FrameLayoutLifecycle
    protected void initViewModel() {
    }

    @Override // com.byd.vrassistant.base.FrameLayoutLifecycle
    public void unInit() {
    }

    public ViewContainer(Context context) {
        this(context, null);
    }

    public ViewContainer(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ViewContainer(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.commonViewModel = (CommonViewModel) ViewModelHelper.getAppViewModel(CommonViewModel.class);
        this.openListener = new Animation.AnimationListener() { // from class: com.byd.vrassistant.view.ViewContainer.1
            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation) {
                RLog.i(ViewContainer.TAG, "openListener onAnimationStart: ");
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
                RLog.i(ViewContainer.TAG, "openListener onAnimationEnd: ");
                ViewContainer.this.startRunnable.run();
            }
        };
        this.closeListener = new Animation.AnimationListener() { // from class: com.byd.vrassistant.view.ViewContainer.2
            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation) {
                RLog.i(ViewContainer.TAG, "closeListener onAnimationStart: ");
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
                RLog.i(ViewContainer.TAG, "closeListener onAnimationEnd: ");
                animation.setFillAfter(true);
                if (ViewManager.getViewStateManager().isInUiSession() && ViewStateManger.get().isVrStarted()) {
                    RLog.e(ViewContainer.TAG, "onAnimationEnd: in session,so won't close view");
                } else {
                    ViewContainer.this.dismissRunnable.run();
                }
            }
        };
    }

    @Override // com.byd.vrassistant.base.FrameLayoutLifecycle, android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        getCloseAnim().setStartTime(-1L);
        getStartAnim().setStartTime(-1L);
    }

    @Override // com.byd.vrassistant.base.FrameLayoutLifecycle
    protected void initView() {
        FrameLayout frameLayout = new FrameLayout(getContext());
        this.animParentView = frameLayout;
        frameLayout.setContentDescription("anim parent view container");
        this.netErrorHintView = new NetErrorHintView(getContext());
        this.netErrorView = new NetErrorView(getContext());
        addView(this.animParentView);
    }

    public void addVoiceBar(View view) {
        this.voiceBar = (VoiceBar) view;
        Log.i(TAG, "addChildView: name=" + view.getClass().getSimpleName());
        View childAt = this.animParentView.getChildAt(0);
        if (childAt != null && view.getContentDescription() == childAt.getContentDescription()) {
            Log.i(TAG, "addVoiceBar:voicebar already add ");
            return;
        }
        this.animParentView.addView(view);
        this.factParentView = (ViewGroup) view.findViewById(R.id.fl_container);
        this.flNetErr = (FrameLayout) findViewById(R.id.fl_net_err);
    }

    public void setNetHintVisibility(NetShowResult netShowResult) {
        if (netShowResult.isShow()) {
            this.flNetErr.removeAllViews();
            if (netShowResult.isStrongHint()) {
                this.flNetErr.addView(this.netErrorView);
            } else {
                this.flNetErr.addView(this.netErrorHintView);
            }
            this.flNetErr.setVisibility(0);
            return;
        }
        this.flNetErr.removeAllViews();
        this.flNetErr.setVisibility(8);
    }

    public void addChildView(View view) {
        if (this.animParentView.getChildAt(0) == null) {
            return;
        }
        this.factParentView.removeAllViews();
        view.setLayoutParams(new ViewGroup.LayoutParams(-1, -2));
        this.factParentView.addView(view);
        this.factParentView.setVisibility(0);
    }

    public void removeChildView(View view) {
        Log.i(TAG, "removeChildView: name=" + view.getClass().getSimpleName());
        this.factParentView.removeView(view);
        if (this.factParentView.getChildCount() == 0) {
            this.factParentView.setVisibility(8);
        }
    }

    public void removeExternalView() {
        this.factParentView.removeAllViews();
        this.factParentView.setVisibility(8);
    }

    public void cancelDismissAnim() {
        if (getCloseAnim().hasStarted()) {
            this.animParentView.clearAnimation();
            getCloseAnim().setStartTime(-1L);
        }
    }

    @Override // android.view.View
    public void startAnimation(Animation animation) {
        this.animParentView.startAnimation(animation);
    }

    public void startShowVoiceBar(Runnable runnable) {
        if (getStartAnim().hasStarted()) {
            RLog.i(TAG, "startShowVoiceBar: start anim has start");
        } else {
            this.startRunnable = runnable;
            this.animParentView.startAnimation(getStartAnim());
        }
    }

    public void startDismissVoiceBar(Runnable runnable) {
        if (ViewManager.getViewStateManager().isInUiSession() && ViewStateManger.get().isVrStarted() && getCloseAnim().hasEnded() && getCloseAnim().hasStarted()) {
            RLog.i(TAG, "restart close anim");
            getCloseAnim().setStartTime(-1L);
            return;
        }
        RLog.i(TAG, "startDismissVoiceBar: " + getCloseAnim().hasStarted());
        if (getCloseAnim().hasStarted()) {
            RLog.i(TAG, "dismissOnScreen: close anim has start");
        } else {
            this.dismissRunnable = runnable;
            this.animParentView.startAnimation(getCloseAnim());
        }
    }

    public Animation getStartAnim() {
        if (this.startAnim == null) {
            Animation animationLoadAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.view_voice_bar_open);
            this.startAnim = animationLoadAnimation;
            animationLoadAnimation.setAnimationListener(this.openListener);
        }
        return this.startAnim;
    }

    public Animation getCloseAnim() {
        if (this.closeAnim == null) {
            Animation animationLoadAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.view_voice_bar_close);
            this.closeAnim = animationLoadAnimation;
            animationLoadAnimation.setAnimationListener(this.closeListener);
        }
        return this.closeAnim;
    }
}
