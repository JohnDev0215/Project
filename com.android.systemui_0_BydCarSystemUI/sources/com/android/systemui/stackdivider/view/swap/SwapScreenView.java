package com.android.systemui.stackdivider.view.swap;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.android.systemui.Constants;
import com.android.systemui.R;
import com.android.systemui.SysUiServiceProvider;
import com.android.systemui.stackdivider.Divider;
import com.android.systemui.stackdivider.DividerUtils;
import com.android.systemui.stackdivider.SwapSplitScreen;
import com.android.systemui.statusbar.wm.Util;
import com.byd.card.forbidden.ForbiddenSplitPrimaryActivity;
import com.byd.card.forbidden.ForbiddenSplitSecondaryActivity;
import com.byd.card.utils.AppUtils;
import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: loaded from: classes.dex */
public class SwapScreenView extends RelativeLayout {
    private static final String TAG = "SwapScreenView";
    private boolean isSwapping;
    private final Context mContext;
    private final Divider mDivider;
    private final DividerUtils mDividerUtils;
    private ImageView mLeftImageView;
    private ImageView mRightImageView;
    private final SwapSplitScreen mSwapSplitScreen;

    public SwapScreenView(Context context, Divider divider) {
        this(context, null, divider);
    }

    public SwapScreenView(Context context, AttributeSet attributeSet, Divider divider) {
        super(context, attributeSet);
        this.isSwapping = false;
        this.mDividerUtils = DividerUtils.getInstance();
        this.mSwapSplitScreen = SwapSplitScreen.getInstance();
        this.mContext = context;
        this.mDivider = divider;
        initView();
    }

    public final void initView() {
        View viewInflate = LayoutInflater.from(getContext()).inflate(R.layout.sys_custom_swap_view, (ViewGroup) null);
        addView(viewInflate);
        this.mLeftImageView = (ImageView) viewInflate.findViewById(R.id.leftImageView);
        this.mRightImageView = (ImageView) viewInflate.findViewById(R.id.rightImageView);
    }

    public void changeImageView(Bitmap primaryLayerTwoBitmap, Bitmap secondLayerTwoBitmap) {
        ViewGroup.LayoutParams layoutParams = this.mLeftImageView.getLayoutParams();
        ViewGroup.LayoutParams layoutParams2 = this.mRightImageView.getLayoutParams();
        if (this.mDividerUtils.isHorizontalDivision()) {
            layoutParams.width = (getScreenWidth(this.mContext) / 2) - (getMultiWindowTaskDividerSize(this.mContext) / 2);
            layoutParams.height = getScreenHeight(this.mContext);
            layoutParams2.width = (getScreenWidth(this.mContext) / 2) - (getMultiWindowTaskDividerSize(this.mContext) / 2);
            layoutParams2.height = getScreenHeight(this.mContext);
        } else {
            layoutParams.width = getScreenWidth(this.mContext);
            layoutParams.height = (getScreenHeight(this.mContext) / 2) - (getMultiWindowTaskDividerSize(this.mContext) / 2);
            layoutParams2.width = getScreenWidth(this.mContext);
            layoutParams2.height = (getScreenHeight(this.mContext) / 2) - (getMultiWindowTaskDividerSize(this.mContext) / 2);
        }
        this.mLeftImageView.setLayoutParams(layoutParams);
        this.mRightImageView.setLayoutParams(layoutParams2);
        this.mLeftImageView.setImageBitmap(primaryLayerTwoBitmap);
        this.mRightImageView.setImageBitmap(secondLayerTwoBitmap);
    }

    private int judgeDuration() {
        if (ForbiddenSplitPrimaryActivity.sIsTopShowing && ForbiddenSplitSecondaryActivity.sIsTopShowing) {
            Log.d(TAG, "judgeDuration long!");
            return 800;
        }
        Log.d(TAG, "judgeDuration short!");
        return 600;
    }

    public void addSwapAnimation() {
        TranslateAnimation translateAnimation;
        TranslateAnimation translateAnimation2;
        int navigationBarHeight = Util.getNavigationBarHeight(this.mContext);
        int statusBarHeight = Util.getStatusBarHeight(this.mContext);
        int multiWindowTaskDividerSize = getMultiWindowTaskDividerSize(this.mContext);
        int i = multiWindowTaskDividerSize / 2;
        int screenWidth = (getScreenWidth(this.mContext) / 2) + i;
        int screenHeight = ((getScreenHeight(this.mContext) / 2) - statusBarHeight) + i;
        boolean zIsRtl = AppUtils.isRtl(this.mContext);
        BydLogHelper.logD("addSwapAnimation", "isRtl : " + zIsRtl);
        BydLogHelper.logD("addSwapAnimation", "navigationBarHeight = " + navigationBarHeight + " statusBarHeight = " + statusBarHeight + " multiWindowTaskDividerSize = " + multiWindowTaskDividerSize + " screenWidth =" + screenWidth + " screenHeight = " + screenHeight);
        BydLogHelper.logD("addSwapAnimation", "mDividerUtils.isHorizontalDivision() : " + this.mDividerUtils.isHorizontalDivision());
        if (this.mDividerUtils.isHorizontalDivision()) {
            float f = screenWidth;
            translateAnimation = new TranslateAnimation(0.0f, f, 0.0f, 0.0f);
            translateAnimation2 = new TranslateAnimation(0.0f, -f, 0.0f, 0.0f);
        } else if (zIsRtl) {
            translateAnimation = new TranslateAnimation(0.0f, 0.0f, 0.0f, -screenHeight);
            translateAnimation2 = new TranslateAnimation(0.0f, 0.0f, 0.0f, screenHeight);
        } else {
            translateAnimation = new TranslateAnimation(0.0f, 0.0f, 0.0f, screenHeight);
            translateAnimation2 = new TranslateAnimation(0.0f, 0.0f, 0.0f, -screenHeight);
        }
        int iJudgeDuration = judgeDuration();
        translateAnimation.setFillAfter(true);
        long j = iJudgeDuration;
        translateAnimation.setDuration(j);
        translateAnimation2.setFillAfter(true);
        translateAnimation2.setDuration(j);
        if (zIsRtl) {
            this.mRightImageView.startAnimation(translateAnimation);
            this.mLeftImageView.startAnimation(translateAnimation2);
        } else {
            this.mLeftImageView.startAnimation(translateAnimation);
            this.mRightImageView.startAnimation(translateAnimation2);
        }
        translateAnimation2.setAnimationListener(new Animation.AnimationListener() { // from class: com.android.systemui.stackdivider.view.swap.SwapScreenView.1
            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation) {
                BydLogHelper.logD("addSwapAnimation", "onAnimationStart");
                SwapScreenView.this.mSwapSplitScreen.swapSplitTask(SwapScreenView.this.mContext);
                SwapScreenView.this.isSwapping = true;
                SwapScreenView.this.sendSplitScreenSwapAnimationBroadcast(true);
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
                BydLogHelper.logD("addSwapAnimation", "onAnimationEnd");
                SwapScreenView.this.mDivider.removeSwapView();
                SwapScreenView.this.isSwapping = false;
                SwapScreenView.this.sendSplitScreenSwapAnimationBroadcast(false);
                Divider divider = (Divider) SysUiServiceProvider.getComponent(SwapScreenView.this.mContext, Divider.class);
                if (divider != null) {
                    divider.setNewFocusStatus();
                }
            }
        });
    }

    public int getScreenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    public int getScreenHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    public int getMultiWindowTaskDividerSize(Context context) {
        return (int) context.getResources().getDimension(R.dimen.multi_window_task_divider_size);
    }

    @Override // android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        initView();
    }

    public boolean isSwapping() {
        return this.isSwapping;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendSplitScreenSwapAnimationBroadcast(boolean value) {
        BydLogHelper.logD("sendSplitScreenSwapAnimationBroadcast value : " + value);
        Intent intent = new Intent(Constants.SPLIT_SCREEN_SWAP_ANIMATION_BROADCAST);
        intent.putExtra(Constants.SPLIT_SCREEN_SWAP_ANIMATION_BROADCAST_EXTRA, value);
        this.mContext.sendBroadcast(intent);
    }
}
