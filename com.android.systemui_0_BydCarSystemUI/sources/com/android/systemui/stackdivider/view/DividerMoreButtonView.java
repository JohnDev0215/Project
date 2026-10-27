package com.android.systemui.stackdivider.view;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.android.systemui.Dependency;
import com.android.systemui.R;
import com.android.systemui.SysUiServiceProvider;
import com.android.systemui.stackdivider.Divider;
import com.android.systemui.stackdivider.DividerUtils;
import com.android.systemui.stackdivider.SwapSplitScreen;
import com.android.systemui.util.FastClickUtil;
import com.byd.card.utils.AppUtils;
import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: loaded from: classes.dex */
public class DividerMoreButtonView extends LinearLayout {
    private Context mContext;
    private Divider mDivider;
    private DividerUtils mDividerUtils;
    private ImageView mSplitExitView;
    private ImageView mSplitSwapView;
    private SwapSplitScreen mSwapSplitScreen;

    public DividerMoreButtonView(Context context) {
        super(context);
        init(context);
    }

    public DividerMoreButtonView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context);
    }

    public DividerMoreButtonView(Context context, AttributeSet attributeSet, int defStyleAttr) {
        super(context, attributeSet, defStyleAttr);
        init(context);
    }

    public DividerMoreButtonView(Context context, AttributeSet attributeSet, int defStyleAttr, int defStyleRes) {
        super(context, attributeSet, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        this.mContext = context;
        this.mDividerUtils = DividerUtils.getInstance();
        this.mSwapSplitScreen = SwapSplitScreen.getInstance();
        initData();
        initView();
    }

    private void initData() {
        this.mDivider = (Divider) SysUiServiceProvider.getComponent(this.mContext, Divider.class);
    }

    private void initView() {
        BydLogHelper.logD("DividerMoreButtonView", "initView");
        View viewInflate = LayoutInflater.from(getContext()).inflate(R.layout.split_screen_slicer_view_button, (ViewGroup) null);
        addView(viewInflate);
        ImageView imageView = (ImageView) viewInflate.findViewById(R.id.ivSwapSplit);
        this.mSplitSwapView = imageView;
        if (imageView != null) {
            imageView.setOnClickListener(new View.OnClickListener() { // from class: com.android.systemui.stackdivider.view.-$$Lambda$DividerMoreButtonView$omL5BGMSN2shi4067Ripmlg4xwY
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.lambda$initView$0$DividerMoreButtonView(view);
                }
            });
        }
        ImageView imageView2 = (ImageView) viewInflate.findViewById(R.id.ivExitSplit);
        this.mSplitExitView = imageView2;
        if (imageView2 != null) {
            imageView2.setOnClickListener(new View.OnClickListener() { // from class: com.android.systemui.stackdivider.view.-$$Lambda$DividerMoreButtonView$CNIk0fuZsDOMZGv-pF2TcwQPURs
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.lambda$initView$3$DividerMoreButtonView(view);
                }
            });
        }
    }

    public /* synthetic */ void lambda$initView$0$DividerMoreButtonView(View view) {
        BydLogHelper.logD("split_swap_button click");
        if (FastClickUtil.isFastDoubleClick(0, 1000L)) {
            BydLogHelper.logD("swap split screen is fast click");
            return;
        }
        BydLogHelper.logD("onClick SwapSplit");
        boolean zIsSwapping = this.mDivider.isSwapping();
        BydLogHelper.logD("onClick SwapSplit isSwapping : " + zIsSwapping);
        if (zIsSwapping) {
            return;
        }
        this.mSwapSplitScreen.executeSwapView(this.mContext, this.mDivider);
    }

    public /* synthetic */ void lambda$initView$3$DividerMoreButtonView(View view) {
        BydLogHelper.logD("onClick ExitSplit");
        if (FastClickUtil.isFastDoubleClick(0, 1000L)) {
            return;
        }
        int windowingMode = this.mDividerUtils.getWindowingMode();
        BydLogHelper.logD("onClick ExitSplit windowingMode : " + windowingMode);
        if (windowingMode == 3) {
            BydLogHelper.logD("onClick ExitSplit startSplitScreenListActivity true");
            this.mDividerUtils.startSplitScreenListActivity(this.mContext, true);
            ((Handler) Dependency.get(Dependency.MAIN_HANDLER)).postDelayed(new Runnable() { // from class: com.android.systemui.stackdivider.view.-$$Lambda$DividerMoreButtonView$LrUxkfnhDRHcFvwH4SiTlc4PQQg
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$initView$1$DividerMoreButtonView();
                }
            }, 120L);
        } else if (windowingMode == 4) {
            BydLogHelper.logD("onClick ExitSplit startSplitScreenListActivity false");
            this.mDividerUtils.startSplitScreenListActivity(this.mContext, false);
            ((Handler) Dependency.get(Dependency.MAIN_HANDLER)).postDelayed(new Runnable() { // from class: com.android.systemui.stackdivider.view.-$$Lambda$DividerMoreButtonView$LrLVAXctqRcWENGJPXPYyzsCS3Q
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$initView$2$DividerMoreButtonView();
                }
            }, 120L);
        }
    }

    public /* synthetic */ void lambda$initView$1$DividerMoreButtonView() {
        this.mDivider.resize(true);
    }

    public /* synthetic */ void lambda$initView$2$DividerMoreButtonView() {
        this.mDivider.resize(false);
    }

    public void updateSplitBtnShowHide() {
        boolean zIsSwapping = this.mDivider.isSwapping();
        BydLogHelper.logD("DividerMoreButtonView", "updateSplitBtnShowHide");
        BydLogHelper.logD("DividerMoreButtonView", "updateSplitBtnShowHide isSwapping : " + zIsSwapping);
        if (zIsSwapping) {
            return;
        }
        int dividerStatus = this.mDividerUtils.getDividerStatus();
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(500L);
        animatorSet.addListener(new Animator.AnimatorListener() { // from class: com.android.systemui.stackdivider.view.DividerMoreButtonView.1
            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animation) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationRepeat(Animator animation) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animation) {
                BydLogHelper.logD("DividerMoreButtonView", "onAnimationStart dividerStatus : " + DividerMoreButtonView.this.mDividerUtils.getDividerStatus());
                if (DividerMoreButtonView.this.mDividerUtils.getDividerStatus() == 2) {
                    DividerMoreButtonView.this.mSplitSwapView.setVisibility(0);
                    DividerMoreButtonView.this.mSplitSwapView.setClickable(true);
                    DividerMoreButtonView.this.mSplitExitView.setVisibility(0);
                    DividerMoreButtonView.this.mSplitExitView.setClickable(true);
                }
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animation) {
                BydLogHelper.logD("DividerMoreButtonView", "onAnimationEnd dividerStatus : " + DividerMoreButtonView.this.mDividerUtils.getDividerStatus());
                if (DividerMoreButtonView.this.mDividerUtils.getDividerStatus() != 2) {
                    DividerMoreButtonView.this.mSplitSwapView.setVisibility(8);
                    DividerMoreButtonView.this.mSplitExitView.setVisibility(8);
                }
            }
        });
        float dividerMoreButtonPadding = getDividerMoreButtonPadding(this.mContext) + getDividerMoreButtonWidth(this.mContext);
        boolean zIsHorizontalDivision = this.mDividerUtils.isHorizontalDivision();
        BydLogHelper.logD("DividerMoreButtonView", "updateSplitBtnShowHide isHorizontalDivision : " + zIsHorizontalDivision + " , dividerStatus : " + dividerStatus);
        if (zIsHorizontalDivision) {
            ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(this.mSplitSwapView, "translationY", 0.0f);
            if (dividerStatus == 2) {
                animatorSet.playTogether(ObjectAnimator.ofFloat(this.mSplitExitView, "translationY", -dividerMoreButtonPadding), objectAnimatorOfFloat);
            } else {
                animatorSet.playTogether(ObjectAnimator.ofFloat(this.mSplitExitView, "translationY", 0.0f), objectAnimatorOfFloat);
            }
        } else {
            ObjectAnimator objectAnimatorOfFloat2 = ObjectAnimator.ofFloat(this.mSplitSwapView, "translationX", 0.0f);
            if (dividerStatus == 2) {
                boolean zIsRtl = AppUtils.isRtl(this.mContext);
                BydLogHelper.logD("DividerMoreButtonView", "updateSplitBtnShowHide isRtl : " + zIsRtl);
                if (zIsRtl) {
                    animatorSet.playTogether(ObjectAnimator.ofFloat(this.mSplitExitView, "translationX", dividerMoreButtonPadding), objectAnimatorOfFloat2);
                } else {
                    animatorSet.playTogether(ObjectAnimator.ofFloat(this.mSplitExitView, "translationX", -dividerMoreButtonPadding), objectAnimatorOfFloat2);
                }
            } else {
                animatorSet.playTogether(ObjectAnimator.ofFloat(this.mSplitExitView, "translationX", 0.0f), objectAnimatorOfFloat2);
            }
        }
        animatorSet.start();
    }

    public float getDividerMoreButtonPadding(Context context) {
        return context.getResources().getDimension(R.dimen.divider_more_button_padding);
    }

    public float getDividerMoreButtonWidth(Context context) {
        return context.getResources().getDimension(R.dimen.divider_more_button_width);
    }

    public void updateExitOrientation() {
        int windowingMode = this.mDividerUtils.getWindowingMode();
        BydLogHelper.logD("updateExitOrientation", "orientation : " + windowingMode);
        if (windowingMode == 3) {
            if (this.mDividerUtils.isHorizontalDivision()) {
                this.mSplitExitView.setImageResource(R.mipmap.sys_custom_split_exit_left);
                return;
            } else {
                this.mSplitExitView.setImageResource(R.mipmap.sys_custom_split_exit_up);
                return;
            }
        }
        if (windowingMode == 4) {
            if (this.mDividerUtils.isHorizontalDivision()) {
                this.mSplitExitView.setImageResource(R.mipmap.sys_custom_split_exit_right);
            } else {
                this.mSplitExitView.setImageResource(R.mipmap.sys_custom_split_exit_down);
            }
        }
    }
}
