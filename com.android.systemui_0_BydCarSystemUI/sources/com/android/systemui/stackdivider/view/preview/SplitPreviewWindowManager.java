package com.android.systemui.stackdivider.view.preview;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.WindowManager;
import androidx.core.view.GravityCompat;
import com.android.systemui.R;
import com.android.systemui.SysUiServiceProvider;
import com.android.systemui.stackdivider.view.preview.SplitSurfaceView;
import com.android.systemui.statusbar.phone.StatusBar;
import com.byd.card.utils.BitmapUtils;
import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: loaded from: classes.dex */
public class SplitPreviewWindowManager {
    private static final String TAG = "SplitPreviewWindowManager";
    private static volatile SplitPreviewWindowManager mSplitPreviewWindowManager;
    private final Context mContext;
    private boolean mIsAnimating;
    private boolean mIsExitRight;
    private boolean mIsLandscape;
    private boolean mIsTransparentAnimating;
    private Drawable mLeftDrawable;
    private Drawable mRightDrawable;
    private SplitSurfaceView mSplitPreviewRootView;
    private WindowManager.LayoutParams mWindowLayoutParams;
    private final WindowManager mWindowManager;

    private SplitPreviewWindowManager(Context context) {
        this.mContext = context;
        this.mWindowManager = (WindowManager) context.getSystemService("window");
    }

    public static SplitPreviewWindowManager getInstance(Context context) {
        if (mSplitPreviewWindowManager == null) {
            synchronized (SplitPreviewWindowManager.class) {
                if (mSplitPreviewWindowManager == null) {
                    mSplitPreviewWindowManager = new SplitPreviewWindowManager(context);
                }
            }
        }
        return mSplitPreviewWindowManager;
    }

    public void showSplitPreview() {
        String str = TAG;
        BydLogHelper.logD(str, "showSplitPreview mSplitPreviewRootView=" + this.mSplitPreviewRootView);
        if (this.mSplitPreviewRootView == null) {
            this.mIsLandscape = this.mWindowManager.getDefaultDisplay().getRotation() == 0;
            BydLogHelper.logD(str, "showSplitPreview isLandscape =" + this.mIsLandscape);
            SplitSurfaceView splitSurfaceView = (SplitSurfaceView) LayoutInflater.from(this.mContext).inflate(R.layout.split_animation_layout, (ViewGroup) null);
            this.mSplitPreviewRootView = splitSurfaceView;
            splitSurfaceView.setOrientation(this.mIsLandscape ? 1 : 0);
            this.mSplitPreviewRootView.setDrawable(this.mLeftDrawable, this.mRightDrawable);
            WindowManager.LayoutParams layoutParams = getLayoutParams();
            this.mWindowLayoutParams = layoutParams;
            this.mWindowManager.addView(this.mSplitPreviewRootView, layoutParams);
        }
    }

    public void hideSplitPreview() {
        BydLogHelper.logD(TAG, "hideSplitPreview mSplitPreviewRootView =" + this.mSplitPreviewRootView);
        SplitSurfaceView splitSurfaceView = this.mSplitPreviewRootView;
        if (splitSurfaceView != null) {
            this.mWindowManager.removeViewImmediate(splitSurfaceView);
            this.mSplitPreviewRootView = null;
            this.mLeftDrawable = null;
            this.mRightDrawable = null;
            this.mIsTransparentAnimating = false;
            this.mIsAnimating = false;
        }
        mSplitPreviewWindowManager = null;
    }

    public void executeFullToHalfAnimation(String leftPkgName, String rightPkgName) {
        executeFullToHalfAnimation(leftPkgName, rightPkgName, false);
    }

    public void executeFullToHalfAnimation(String leftPkgName, String rightPkgName, boolean leftToRight) {
        BydLogHelper.logD(TAG, "executeFullToHalfAnimation isExitRight = " + this.mIsExitRight + ", leftPkgName =" + leftPkgName + ", rightPkgName =" + rightPkgName);
        this.mIsAnimating = true;
        updateLeftAndRightImg(leftPkgName, rightPkgName);
        showSplitPreview();
        if (leftToRight) {
            this.mSplitPreviewRootView.setDividerPosition(0.0f, 0.5f);
        } else {
            this.mSplitPreviewRootView.setDividerPosition(1.0f, 0.5f);
        }
        this.mSplitPreviewRootView.setAnimationListener(new SplitSurfaceView.AnimationListener() { // from class: com.android.systemui.stackdivider.view.preview.-$$Lambda$SplitPreviewWindowManager$uFHKB_Y-P6fGRgtdOe4lMWfyVEw
            @Override // com.android.systemui.stackdivider.view.preview.SplitSurfaceView.AnimationListener
            public final void onAnimationEnd() {
                this.f$0.lambda$executeFullToHalfAnimation$0$SplitPreviewWindowManager();
            }
        });
        this.mSplitPreviewRootView.startAnimation(200);
    }

    public /* synthetic */ void lambda$executeFullToHalfAnimation$0$SplitPreviewWindowManager() {
        transparentLayout();
        updateStatusBarColorBySplit();
    }

    public void executeHalfToFullAnimation(String leftPkgName, String rightPkgName, boolean exitRight) {
        BydLogHelper.logD(TAG, "executeHalfToFullAnimation isExitRight = " + this.mIsExitRight + ", leftPkgName =" + leftPkgName + ", rightPkgName =" + rightPkgName);
        this.mIsAnimating = true;
        this.mIsExitRight = exitRight;
        updateLeftAndRightImg(leftPkgName, rightPkgName);
        showSplitPreview();
        if (this.mIsExitRight) {
            this.mSplitPreviewRootView.setDividerPosition(0.5f, 1.0f);
        } else {
            this.mSplitPreviewRootView.setDividerPosition(0.5f, 0.0f);
        }
        this.mSplitPreviewRootView.setAnimationListener(new SplitSurfaceView.AnimationListener() { // from class: com.android.systemui.stackdivider.view.preview.-$$Lambda$pnml3xsPvJdX5OIJuzb-wCpaHIY
            @Override // com.android.systemui.stackdivider.view.preview.SplitSurfaceView.AnimationListener
            public final void onAnimationEnd() {
                this.f$0.transparentLayout();
            }
        });
        this.mSplitPreviewRootView.startAnimation(200);
    }

    private void updateLeftAndRightImg(String leftPkgName, String rightPkgName) {
        String str = TAG;
        BydLogHelper.logD(str, "leftPkgName = " + leftPkgName + ", rightPkgName =" + rightPkgName);
        try {
            if (!TextUtils.isEmpty(leftPkgName) && !leftPkgName.equals("com.android.systemui")) {
                Drawable drawableAdapterMultiTheme = BitmapUtils.adapterMultiTheme(leftPkgName, null);
                this.mLeftDrawable = drawableAdapterMultiTheme;
                if (drawableAdapterMultiTheme == null) {
                    this.mLeftDrawable = this.mContext.getPackageManager().getApplicationIcon(this.mContext.getPackageManager().getApplicationInfo(leftPkgName, 0));
                }
                BydLogHelper.logD(str, "mLeftDrawable = " + this.mLeftDrawable);
            }
            if (TextUtils.isEmpty(rightPkgName) || rightPkgName.equals("com.android.systemui")) {
                return;
            }
            Drawable drawableAdapterMultiTheme2 = BitmapUtils.adapterMultiTheme(rightPkgName, null);
            this.mRightDrawable = drawableAdapterMultiTheme2;
            if (drawableAdapterMultiTheme2 == null) {
                this.mRightDrawable = this.mContext.getPackageManager().getApplicationIcon(this.mContext.getPackageManager().getApplicationInfo(rightPkgName, 0));
            }
            BydLogHelper.logD(str, "mRightDrawable = " + this.mRightDrawable);
        } catch (Exception e) {
            BydLogHelper.logD(TAG, "updateLeftAndRightImg Exception = " + e);
            e.printStackTrace();
        }
    }

    public void transparentLayout() {
        if (this.mIsTransparentAnimating || this.mSplitPreviewRootView == null) {
            return;
        }
        this.mIsTransparentAnimating = true;
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(1.0f, 0.0f);
        valueAnimatorOfFloat.setDuration(300L);
        valueAnimatorOfFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.android.systemui.stackdivider.view.preview.-$$Lambda$SplitPreviewWindowManager$faiitT5CjLF1ummiuGcJRJcEVo0
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                this.f$0.lambda$transparentLayout$1$SplitPreviewWindowManager(valueAnimator);
            }
        });
        valueAnimatorOfFloat.addListener(new AnimatorListenerAdapter() { // from class: com.android.systemui.stackdivider.view.preview.SplitPreviewWindowManager.1
            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator, boolean isReverse) {
                super.onAnimationEnd(animator, isReverse);
                BydLogHelper.logD(SplitPreviewWindowManager.TAG, "transparentLayout onAnimationEnd");
                SplitPreviewWindowManager.this.hideSplitPreview();
            }
        });
        valueAnimatorOfFloat.start();
    }

    public /* synthetic */ void lambda$transparentLayout$1$SplitPreviewWindowManager(ValueAnimator valueAnimator) {
        float fFloatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
        BydLogHelper.logD(TAG, "transparentLayout onAnimationUpdate floatValue =" + fFloatValue);
        WindowManager.LayoutParams layoutParams = this.mWindowLayoutParams;
        layoutParams.alpha = fFloatValue;
        this.mWindowManager.updateViewLayout(this.mSplitPreviewRootView, layoutParams);
    }

    private WindowManager.LayoutParams getLayoutParams() {
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.type = 2010;
        layoutParams.width = -1;
        layoutParams.height = -1;
        layoutParams.setFitInsetsTypes(0);
        layoutParams.flags = 16778504;
        layoutParams.gravity = GravityCompat.START;
        return layoutParams;
    }

    public boolean isAnimating() {
        return this.mIsAnimating;
    }

    public void updateStatusBarColorBySplit() {
        ((StatusBar) SysUiServiceProvider.getComponent(this.mContext, StatusBar.class)).updateStatusBarColorBySplit();
    }
}
