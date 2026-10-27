package com.android.launcher3;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.CancellationSignal;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.ActivityChooserView;
import com.android.launcher3.LauncherAnimationRunner;
import com.android.launcher3.anim.Interpolators;
import com.android.launcher3.statehandlers.DepthController;
import com.android.launcher3.util.RemoteAnimationProvider;
import com.android.launcher3.views.FloatingIconView;
import com.android.quickstep.RemoteAnimationTargets;
import com.android.quickstep.util.MultiValueUpdateListener;
import com.android.quickstep.util.SurfaceTransactionApplier;
import com.android.systemui.SystemUIApplication;
import com.android.systemui.shared.system.ActivityCompat;
import com.android.systemui.shared.system.ActivityOptionsCompat;
import com.android.systemui.shared.system.RemoteAnimationAdapterCompat;
import com.android.systemui.shared.system.RemoteAnimationDefinitionCompat;
import com.android.systemui.shared.system.RemoteAnimationTargetCompat;
import com.android.systemui.shared.system.SyncRtSurfaceTransactionApplierCompat;
import com.android.systemui.statusbar.wm.Util;
import com.byd.card.bean.Constant;
import com.byd.card.drag.AppContentView;
import com.byd.card.drag.FolderView;
import com.byd.card.drag.SpringBoardPage;
import com.byd.card.view.activity.MoreAppActivity;

/* JADX INFO: loaded from: classes.dex */
public class AppTransitionManagerImpl {
    private static final long APP_LAUNCH_ALPHA_DOWN_DURATION = 40;
    private static final long APP_LAUNCH_ALPHA_DURATION = 50;
    private static final long APP_LAUNCH_ALPHA_START_DELAY = 35;
    private static final long APP_LAUNCH_CURVED_DURATION = 200;
    private static final long APP_LAUNCH_DOWN_CURVED_DURATION = 160;
    private static final long APP_LAUNCH_DOWN_DURATION = 280;
    private static final float APP_LAUNCH_DOWN_DUR_SCALE_FACTOR = 0.8f;
    private static final long APP_LAUNCH_DURATION = 350;
    private static final int CLOSING_TRANSITION_DURATION_MS = 250;
    private static final long CROP_DURATION = 300;
    public static final boolean DEBUG = false;
    private static final long MYCAR_LAUNCH_ALPHA_DURATION = 15;
    private static final long MYCAR_LAUNCH_ALPHA_START_DELAY = 10;
    private static final long RADIUS_DURATION = 300;
    public static final float SHAPE_PROGRESS_DURATION = 0.1f;
    public static final int STATUS_BAR_TRANSITION_DURATION = 120;
    public static final int STATUS_BAR_TRANSITION_PRE_DELAY = 96;
    private static final String TAG = "AppTransitionManagerImpl";
    private Activity mActivity;
    private View mImageView;
    private View mOrignView;
    private RemoteAnimationProvider mRemoteAnimationProvider;
    private ViewGroup mViewGroup;
    private WrappedAnimationRunnerImpl mWallpaperOpenRunner;
    private String startPackageName;
    private int mWindowingMode = 1;
    private NotCreatedAnimRunnable mNotCreatedAnimRunnable = new NotCreatedAnimRunnable();
    private Context mContext = SystemUIApplication.getInstance().getApplicationContext();
    Handler mHandler = new Handler(Looper.getMainLooper());

    public AppTransitionManagerImpl(Activity activity, ViewGroup viewGroup, View orignView, View imageView, String packageName) {
        this.mActivity = activity;
        this.mViewGroup = viewGroup;
        this.mOrignView = orignView;
        this.mImageView = imageView;
        this.startPackageName = packageName;
    }

    private Rect getWindowTargetBounds(RemoteAnimationTargetCompat[] appTargets) {
        int i;
        int i2 = 1920;
        if (Utilities.isScreenOriatationPortrait(this.mContext)) {
            i = 1920;
            i2 = 1080;
        } else {
            i = 1080;
        }
        if (this.mWindowingMode != 1) {
            Util.getNavigationBarHeight(this.mContext);
            int statusBarHeight = Util.getStatusBarHeight(this.mContext);
            int i3 = this.mWindowingMode;
            if (i3 == 3 || i3 == 4) {
                i = 1080 - statusBarHeight;
                i2 = 953;
            }
        }
        return new Rect(0, 0, i2, i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void composeIconLaunchAnimator(AnimatorSet anim, RemoteAnimationTargetCompat[] appTargets, RemoteAnimationTargetCompat[] wallpaperTargets, boolean launcherClosing, int width, int height) {
        Rect windowTargetBounds = getWindowTargetBounds(appTargets);
        boolean z = true;
        for (RemoteAnimationTargetCompat remoteAnimationTargetCompat : appTargets) {
            if (remoteAnimationTargetCompat.mode == 0) {
                z &= remoteAnimationTargetCompat.isTranslucent;
            }
            if (!z) {
                break;
            }
        }
        anim.play(getOpeningWindowAnimators(appTargets, wallpaperTargets, windowTargetBounds, !z, width, height));
        if (launcherClosing) {
            return;
        }
        anim.addListener(new AnimatorListenerAdapter() { // from class: com.android.launcher3.AppTransitionManagerImpl.1
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animation) {
                if (AppTransitionManagerImpl.this.mOrignView != null) {
                    if ((AppTransitionManagerImpl.this.mOrignView instanceof AppContentView) || (AppTransitionManagerImpl.this.mOrignView instanceof FolderView)) {
                        if (AppTransitionManagerImpl.this.mActivity != null && (AppTransitionManagerImpl.this.mActivity instanceof MoreAppActivity)) {
                            ((MoreAppActivity) AppTransitionManagerImpl.this.mActivity).getSelectedAppInfo().setSelected(true);
                        }
                        if (AppTransitionManagerImpl.this.mOrignView instanceof FolderView) {
                            ((SpringBoardPage) ((FolderView) AppTransitionManagerImpl.this.mOrignView).getContentView()).invalidate();
                            return;
                        } else {
                            AppTransitionManagerImpl.this.mOrignView.invalidate();
                            return;
                        }
                    }
                    AppTransitionManagerImpl.this.mOrignView.setAlpha(0.0f);
                }
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animation) {
                if (AppTransitionManagerImpl.this.mOrignView != null && !(AppTransitionManagerImpl.this.mOrignView instanceof AppContentView) && !(AppTransitionManagerImpl.this.mOrignView instanceof FolderView)) {
                    AppTransitionManagerImpl.this.mOrignView.setAlpha(1.0f);
                }
                if (AppTransitionManagerImpl.this.mViewGroup != null && AppTransitionManagerImpl.this.mActivity == null && AppTransitionManagerImpl.this.mViewGroup.getVisibility() == 0) {
                    AppTransitionManagerImpl.this.mViewGroup.setVisibility(4);
                }
                AppTransitionManagerImpl.this.mHandler.removeCallbacks(AppTransitionManagerImpl.this.mNotCreatedAnimRunnable);
                if (AppTransitionManagerImpl.this.mActivity != null) {
                    AppTransitionManagerImpl.this.mActivity.finish();
                }
                AppTransitionManagerImpl.this.mViewGroup = null;
                AppTransitionManagerImpl.this.mActivity = null;
                AppTransitionManagerImpl.this.mOrignView = null;
                AppTransitionManagerImpl.this.mImageView = null;
            }
        });
    }

    private Animator getOpeningWindowAnimators(RemoteAnimationTargetCompat[] appTargets, RemoteAnimationTargetCompat[] wallpaperTargets, Rect windowTargetBounds, boolean toggleVisibility, int width, int height) {
        float fHeight;
        float fHeight2;
        float fHeight3;
        int iWidth;
        RectF rectF = new RectF();
        FloatingIconView floatingIconView = FloatingIconView.getFloatingIconView(this.mContext, this.mActivity, this.mViewGroup, this.mImageView, toggleVisibility, rectF, true, width, height);
        Rect rect = new Rect();
        Matrix matrix = new Matrix();
        final RemoteAnimationTargets remoteAnimationTargets = new RemoteAnimationTargets(appTargets, wallpaperTargets, 0);
        SurfaceTransactionApplier surfaceTransactionApplier = new SurfaceTransactionApplier(floatingIconView);
        remoteAnimationTargets.addReleaseCheck(surfaceTransactionApplier);
        float fMin = Math.min(windowTargetBounds.height(), windowTargetBounds.width());
        float fMax = Math.max(fMin / rectF.width(), fMin / rectF.height());
        int[] iArr = {0, 0};
        float fCenterX = windowTargetBounds.centerX() - iArr[0];
        float fCenterY = windowTargetBounds.centerY() - iArr[1];
        float fCenterX2 = fCenterX - rectF.centerX();
        float fCenterY2 = fCenterY - rectF.centerY();
        long j = APP_LAUNCH_CURVED_DURATION;
        long j2 = (this.startPackageName.equals("com.byd.mycar") || this.startPackageName.equals(Constant.PackageName.MY_CAR_PKG_TS)) ? MYCAR_LAUNCH_ALPHA_DURATION : APP_LAUNCH_ALPHA_DURATION;
        Log.e(TAG, "xDuration = " + APP_LAUNCH_CURVED_DURATION + ", yDuration = " + APP_LAUNCH_DURATION);
        new RectF(windowTargetBounds);
        RectF rectF2 = new RectF();
        RectF rectF3 = new RectF();
        Point point = new Point();
        AnimatorSet animatorSet = new AnimatorSet();
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        valueAnimatorOfFloat.setDuration(APP_LAUNCH_DURATION);
        valueAnimatorOfFloat.setInterpolator(Interpolators.LINEAR);
        valueAnimatorOfFloat.addListener(floatingIconView);
        valueAnimatorOfFloat.addListener(new AnimatorListenerAdapter() { // from class: com.android.launcher3.AppTransitionManagerImpl.2
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animation) {
                remoteAnimationTargets.release();
            }
        });
        boolean zIsScreenOriatationPortrait = Utilities.isScreenOriatationPortrait(this.mContext);
        if (zIsScreenOriatationPortrait) {
            fHeight = windowTargetBounds.width();
            fHeight2 = windowTargetBounds.width();
            fHeight3 = windowTargetBounds.width();
            iWidth = windowTargetBounds.height();
        } else {
            fHeight = windowTargetBounds.height();
            fHeight2 = windowTargetBounds.height();
            fHeight3 = windowTargetBounds.height();
            iWidth = windowTargetBounds.width();
        }
        float f = iWidth;
        float f2 = fHeight;
        valueAnimatorOfFloat.addUpdateListener(new MultiValueUpdateListener(fCenterX2, j, fCenterY2, APP_LAUNCH_DURATION, 1.0f, fMax, j2, f2, fHeight2, zIsScreenOriatationPortrait ? f2 / 24.0f : f2 / 8.0f, f2 / (zIsScreenOriatationPortrait ? 24.0f : 16.0f), fHeight3, f, rectF, zIsScreenOriatationPortrait, rect, rectF3, rectF2, windowTargetBounds, appTargets, matrix, floatingIconView, point, surfaceTransactionApplier) { // from class: com.android.launcher3.AppTransitionManagerImpl.3
            MultiValueUpdateListener.FloatProp mCroppedSize;
            MultiValueUpdateListener.FloatProp mDx;
            MultiValueUpdateListener.FloatProp mDy;
            MultiValueUpdateListener.FloatProp mIconAlpha;
            MultiValueUpdateListener.FloatProp mScale;
            MultiValueUpdateListener.FloatProp mWindowRadius;
            MultiValueUpdateListener.FloatProp mWindowTargetBoundProp;
            final /* synthetic */ long val$alphaDuration;
            final /* synthetic */ RemoteAnimationTargetCompat[] val$appTargets;
            final /* synthetic */ RectF val$bounds;
            final /* synthetic */ Rect val$crop;
            final /* synthetic */ float val$dX;
            final /* synthetic */ float val$dY;
            final /* synthetic */ float val$endCrop;
            final /* synthetic */ float val$endWindowTargetCrop;
            final /* synthetic */ FloatingIconView val$floatingView;
            final /* synthetic */ RectF val$iconBounds;
            final /* synthetic */ float val$initialStartScale;
            final /* synthetic */ float val$initialWindowRadius;
            final /* synthetic */ boolean val$isScreenVertical;
            final /* synthetic */ Matrix val$matrix;
            final /* synthetic */ float val$scale;
            final /* synthetic */ float val$startCrop;
            final /* synthetic */ float val$startWindowTargetCrop;
            final /* synthetic */ SurfaceTransactionApplier val$surfaceApplier;
            final /* synthetic */ RectF val$temp;
            final /* synthetic */ Point val$tmpPos;
            final /* synthetic */ float val$windowRadius;
            final /* synthetic */ Rect val$windowTargetBounds;
            final /* synthetic */ long val$xDuration;
            final /* synthetic */ long val$yDuration;

            {
                this.val$dX = fCenterX2;
                this.val$xDuration = j;
                this.val$dY = fCenterY2;
                this.val$yDuration = val$yDuration;
                this.val$initialStartScale = val$initialStartScale;
                this.val$scale = fMax;
                this.val$alphaDuration = j2;
                this.val$startCrop = f2;
                this.val$endCrop = fHeight2;
                this.val$initialWindowRadius = val$initialWindowRadius;
                this.val$windowRadius = val$windowRadius;
                this.val$startWindowTargetCrop = fHeight3;
                this.val$endWindowTargetCrop = f;
                this.val$bounds = rectF;
                this.val$isScreenVertical = zIsScreenOriatationPortrait;
                this.val$crop = rect;
                this.val$temp = rectF3;
                this.val$iconBounds = rectF2;
                this.val$windowTargetBounds = windowTargetBounds;
                this.val$appTargets = appTargets;
                this.val$matrix = matrix;
                this.val$floatingView = floatingIconView;
                this.val$tmpPos = point;
                this.val$surfaceApplier = surfaceTransactionApplier;
                this.mDx = new MultiValueUpdateListener.FloatProp(0.0f, fCenterX2, 0.0f, j, Interpolators.AGGRESSIVE_EASE);
                this.mDy = new MultiValueUpdateListener.FloatProp(0.0f, fCenterY2, 0.0f, val$yDuration, Interpolators.AGGRESSIVE_EASE);
                this.mScale = new MultiValueUpdateListener.FloatProp(val$initialStartScale, fMax, 0.0f, 350.0f, Interpolators.EXAGGERATED_EASE);
                this.mIconAlpha = new MultiValueUpdateListener.FloatProp(1.0f, 0.0f, (AppTransitionManagerImpl.this.startPackageName.equals("com.byd.mycar") || AppTransitionManagerImpl.this.startPackageName.equals(Constant.PackageName.MY_CAR_PKG_TS)) ? 10.0f : 35.0f, j2, Interpolators.LINEAR);
                this.mCroppedSize = new MultiValueUpdateListener.FloatProp(f2, fHeight2, 0.0f, 300.0f, Interpolators.EXAGGERATED_EASE);
                this.mWindowRadius = new MultiValueUpdateListener.FloatProp(val$initialWindowRadius, val$windowRadius, 0.0f, 300.0f, Interpolators.EXAGGERATED_EASE);
                this.mWindowTargetBoundProp = new MultiValueUpdateListener.FloatProp(fHeight3, f, 0.0f, 300.0f, Interpolators.EXAGGERATED_EASE);
            }

            @Override // com.android.quickstep.util.MultiValueUpdateListener
            public void onUpdate(float percent) {
                int i;
                float f3;
                float fWidth = this.val$bounds.width() * this.mScale.value;
                float fHeight4 = this.val$bounds.height() * this.mScale.value;
                if (this.val$isScreenVertical) {
                    i = (int) this.mCroppedSize.value;
                    f3 = this.mWindowTargetBoundProp.value;
                } else {
                    i = (int) this.mWindowTargetBoundProp.value;
                    f3 = this.mCroppedSize.value;
                }
                int i2 = (int) f3;
                this.val$crop.set(0, 0, i, i2);
                float f4 = i;
                float f5 = i2;
                float fMin2 = Math.min(1.0f, Math.max(fWidth / f4, fHeight4 / f5));
                float f6 = ((f4 * fMin2) - fWidth) / 2.0f;
                float f7 = ((f5 * fMin2) - fHeight4) / 2.0f;
                this.val$temp.set(this.val$bounds);
                this.val$temp.offset(this.mDx.value, this.mDy.value);
                Utilities.scaleRectFAboutCenter(this.val$temp, this.mScale.value);
                float f8 = this.val$temp.left - f6;
                float f9 = this.val$temp.top - f7;
                this.val$iconBounds.set(this.val$bounds);
                this.val$iconBounds.offset(this.mDx.value, this.mDy.value);
                Utilities.scaleRectFAboutCenter(this.val$iconBounds, this.mScale.value * ((fWidth * 1.0f) / fHeight4));
                this.val$iconBounds.left -= f6;
                this.val$iconBounds.top -= f7;
                this.val$iconBounds.right += f6;
                this.val$iconBounds.bottom += f7;
                this.val$windowTargetBounds.height();
                this.val$crop.height();
                this.val$windowTargetBounds.width();
                this.val$crop.width();
                RemoteAnimationTargetCompat[] remoteAnimationTargetCompatArr = this.val$appTargets;
                SyncRtSurfaceTransactionApplierCompat.SurfaceParams[] surfaceParamsArr = new SyncRtSurfaceTransactionApplierCompat.SurfaceParams[remoteAnimationTargetCompatArr.length];
                for (int length = remoteAnimationTargetCompatArr.length - 1; length >= 0; length--) {
                    RemoteAnimationTargetCompat remoteAnimationTargetCompat = this.val$appTargets[length];
                    SyncRtSurfaceTransactionApplierCompat.SurfaceParams.Builder builder = new SyncRtSurfaceTransactionApplierCompat.SurfaceParams.Builder(remoteAnimationTargetCompat.leash);
                    if (remoteAnimationTargetCompat.mode == 0) {
                        this.val$matrix.setScale(fMin2, fMin2);
                        this.val$matrix.postTranslate(f8, f9);
                        this.val$floatingView.update(this.val$iconBounds, this.mIconAlpha.value, percent, 0.0f, this.mWindowRadius.value * fMin2, true, this.val$windowTargetBounds);
                        builder.withMatrix(this.val$matrix).withWindowCrop(this.val$crop).withAlpha(1.0f - this.mIconAlpha.value).withCornerRadius(this.mWindowRadius.value);
                    } else {
                        this.val$tmpPos.set(remoteAnimationTargetCompat.position.x, remoteAnimationTargetCompat.position.y);
                        if (remoteAnimationTargetCompat.localBounds != null) {
                            Rect rect2 = remoteAnimationTargetCompat.localBounds;
                            this.val$tmpPos.set(remoteAnimationTargetCompat.localBounds.left, remoteAnimationTargetCompat.localBounds.top);
                        }
                        this.val$matrix.setTranslate(this.val$tmpPos.x, this.val$tmpPos.y);
                        Rect rect3 = new Rect(remoteAnimationTargetCompat.screenSpaceBounds);
                        rect3.offsetTo(0, 0);
                        builder.withMatrix(this.val$matrix).withWindowCrop(rect3).withAlpha(1.0f);
                    }
                    surfaceParamsArr[length] = builder.build();
                }
                this.val$surfaceApplier.scheduleApply(surfaceParamsArr);
            }
        });
        final DepthController depthController = new DepthController();
        ObjectAnimator duration = ObjectAnimator.ofFloat(depthController, DepthController.DEPTH, 100.0f).setDuration(APP_LAUNCH_CURVED_DURATION);
        depthController.setSurfaceToApp(findLowestOpaqueLayerTarget(appTargets, 0));
        duration.addListener(new AnimatorListenerAdapter() { // from class: com.android.launcher3.AppTransitionManagerImpl.4
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animation) {
                depthController.setSurfaceToApp(null);
            }
        });
        animatorSet.playTogether(valueAnimatorOfFloat, duration);
        return animatorSet;
    }

    public static RemoteAnimationTargetCompat findLowestOpaqueLayerTarget(RemoteAnimationTargetCompat[] appTargets, int mode) {
        int i;
        int i2 = ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
        int i3 = -1;
        for (int length = appTargets.length - 1; length >= 0; length--) {
            RemoteAnimationTargetCompat remoteAnimationTargetCompat = appTargets[length];
            if (remoteAnimationTargetCompat.mode == mode && !remoteAnimationTargetCompat.isTranslucent && (i = remoteAnimationTargetCompat.prefixOrderIndex) < i2) {
                i3 = length;
                i2 = i;
            }
        }
        if (i3 != -1) {
            return appTargets[i3];
        }
        return null;
    }

    private class NotCreatedAnimRunnable implements Runnable {
        private NotCreatedAnimRunnable() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (AppTransitionManagerImpl.this.mViewGroup != null && AppTransitionManagerImpl.this.mActivity == null && AppTransitionManagerImpl.this.mViewGroup.getVisibility() == 0) {
                AppTransitionManagerImpl.this.mViewGroup.setVisibility(4);
            }
            if (AppTransitionManagerImpl.this.mActivity != null) {
                AppTransitionManagerImpl.this.mActivity.finish();
                AppTransitionManagerImpl.this.mActivity = null;
            }
            AppTransitionManagerImpl.this.mViewGroup = null;
        }
    }

    public ActivityOptions getActivityLaunchOptions(int width, int height, int windowingMode) {
        this.mHandler.postDelayed(this.mNotCreatedAnimRunnable, 1000L);
        WrappedLauncherAnimationRunner wrappedLauncherAnimationRunner = new WrappedLauncherAnimationRunner(new AppLaunchAnimationRunner(this.mHandler, width, height), true);
        this.mWindowingMode = windowingMode;
        return ActivityOptionsCompat.makeRemoteAnimation(new RemoteAnimationAdapterCompat(wrappedLauncherAnimationRunner, APP_LAUNCH_DURATION, 134L));
    }

    private class AppLaunchAnimationRunner implements WrappedAnimationRunnerImpl {
        private final Handler mHandler;
        private int mHeight;
        private int mWidth;

        AppLaunchAnimationRunner(Handler handler, int width, int height) {
            this.mHandler = handler;
            this.mWidth = width;
            this.mHeight = height;
        }

        @Override // com.android.launcher3.WrappedAnimationRunnerImpl
        public Handler getHandler() {
            return this.mHandler;
        }

        @Override // com.android.launcher3.WrappedAnimationRunnerImpl
        public void onCreateAnimation(RemoteAnimationTargetCompat[] appTargets, RemoteAnimationTargetCompat[] wallpaperTargets, LauncherAnimationRunner.AnimationResult result) {
            this.mHandler.removeCallbacks(AppTransitionManagerImpl.this.mNotCreatedAnimRunnable);
            AnimatorSet animatorSet = new AnimatorSet();
            AppTransitionManagerImpl.this.composeIconLaunchAnimator(animatorSet, appTargets, wallpaperTargets, false, this.mWidth, this.mHeight);
            result.setAnimation(animatorSet, AppTransitionManagerImpl.this.mContext);
        }
    }

    public void setRemoteAnimationProvider(final RemoteAnimationProvider animationProvider, CancellationSignal cancellationSignal) {
        this.mRemoteAnimationProvider = animationProvider;
        cancellationSignal.setOnCancelListener(new CancellationSignal.OnCancelListener() { // from class: com.android.launcher3.-$$Lambda$AppTransitionManagerImpl$KNQ76HlHldJmtduUvqifcyBS194
            @Override // android.os.CancellationSignal.OnCancelListener
            public final void onCancel() {
                this.f$0.lambda$setRemoteAnimationProvider$0$AppTransitionManagerImpl(animationProvider);
            }
        });
    }

    public /* synthetic */ void lambda$setRemoteAnimationProvider$0$AppTransitionManagerImpl(RemoteAnimationProvider remoteAnimationProvider) {
        if (remoteAnimationProvider == this.mRemoteAnimationProvider) {
            this.mRemoteAnimationProvider = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Animator getClosingWindowAnimators(Activity activity, RemoteAnimationTargetCompat[] appTargets, RemoteAnimationTargetCompat[] wallpaperTargets) {
        Log.e(TAG, "getClosingWindowAnimators");
        SurfaceTransactionApplier surfaceTransactionApplier = new SurfaceTransactionApplier(activity.getWindow().getDecorView());
        Matrix matrix = new Matrix();
        Point point = new Point();
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        valueAnimatorOfFloat.setDuration(250);
        valueAnimatorOfFloat.addUpdateListener(new MultiValueUpdateListener(250, appTargets, point, matrix, 200.0f, surfaceTransactionApplier) { // from class: com.android.launcher3.AppTransitionManagerImpl.5
            MultiValueUpdateListener.FloatProp mAlpha = new MultiValueUpdateListener.FloatProp(1.0f, 0.0f, 25.0f, 125.0f, Interpolators.LINEAR);
            MultiValueUpdateListener.FloatProp mDy;
            MultiValueUpdateListener.FloatProp mScale;
            final /* synthetic */ RemoteAnimationTargetCompat[] val$appTargets;
            final /* synthetic */ int val$duration;
            final /* synthetic */ Matrix val$matrix;
            final /* synthetic */ SurfaceTransactionApplier val$surfaceApplier;
            final /* synthetic */ Point val$tmpPos;
            final /* synthetic */ float val$windowCornerRadius;

            {
                this.val$duration = val$duration;
                this.val$appTargets = appTargets;
                this.val$tmpPos = point;
                this.val$matrix = matrix;
                this.val$windowCornerRadius = val$windowCornerRadius;
                this.val$surfaceApplier = surfaceTransactionApplier;
                this.mDy = new MultiValueUpdateListener.FloatProp(0.0f, 115.0f, 0.0f, val$duration, Interpolators.DEACCEL_1_7);
                this.mScale = new MultiValueUpdateListener.FloatProp(1.0f, 1.0f, 0.0f, val$duration, Interpolators.DEACCEL_1_7);
            }

            @Override // com.android.quickstep.util.MultiValueUpdateListener
            public void onUpdate(float percent) {
                Log.e(AppTransitionManagerImpl.TAG, "getClosingWindowAnimators onUpdate percent : " + percent);
                RemoteAnimationTargetCompat[] remoteAnimationTargetCompatArr = this.val$appTargets;
                SyncRtSurfaceTransactionApplierCompat.SurfaceParams[] surfaceParamsArr = new SyncRtSurfaceTransactionApplierCompat.SurfaceParams[remoteAnimationTargetCompatArr.length];
                for (int length = remoteAnimationTargetCompatArr.length - 1; length >= 0; length--) {
                    RemoteAnimationTargetCompat remoteAnimationTargetCompat = this.val$appTargets[length];
                    SyncRtSurfaceTransactionApplierCompat.SurfaceParams.Builder builder = new SyncRtSurfaceTransactionApplierCompat.SurfaceParams.Builder(remoteAnimationTargetCompat.leash);
                    this.val$tmpPos.set(remoteAnimationTargetCompat.position.x, remoteAnimationTargetCompat.position.y);
                    if (remoteAnimationTargetCompat.localBounds != null) {
                        this.val$tmpPos.set(remoteAnimationTargetCompat.localBounds.left, remoteAnimationTargetCompat.localBounds.top);
                    }
                    if (remoteAnimationTargetCompat.mode != 1) {
                        this.val$matrix.setScale(this.mScale.value, this.mScale.value, remoteAnimationTargetCompat.screenSpaceBounds.centerX(), remoteAnimationTargetCompat.screenSpaceBounds.centerY());
                        this.val$matrix.postTranslate(0.0f, this.mDy.value);
                        this.val$matrix.postTranslate(this.val$tmpPos.x, this.val$tmpPos.y);
                        builder.withMatrix(this.val$matrix).withAlpha(this.mAlpha.value).withCornerRadius(this.val$windowCornerRadius);
                    } else {
                        this.val$matrix.setTranslate(this.val$tmpPos.x, this.val$tmpPos.y);
                        builder.withMatrix(this.val$matrix).withAlpha(1.0f);
                    }
                    Rect rect = new Rect(remoteAnimationTargetCompat.screenSpaceBounds);
                    rect.offsetTo(0, 0);
                    Log.e(AppTransitionManagerImpl.TAG, "getClosingWindowAnimators onUpdate crop : " + rect.toString());
                    surfaceParamsArr[length] = builder.withWindowCrop(rect).build();
                }
                this.val$surfaceApplier.scheduleApply(surfaceParamsArr);
            }
        });
        return valueAnimatorOfFloat;
    }

    WrappedAnimationRunnerImpl createWallpaperOpenRunner(boolean fromUnlock) {
        Log.e(TAG, "createWallpaperOpenRunner");
        return new WallpaperOpenLauncherAnimationRunner(this.mHandler, fromUnlock);
    }

    public void registerRemoteAnimations(Activity activity) {
        Log.e(TAG, "registerRemoteAnimations");
        this.mWallpaperOpenRunner = createWallpaperOpenRunner(false);
        RemoteAnimationDefinitionCompat remoteAnimationDefinitionCompat = new RemoteAnimationDefinitionCompat();
        remoteAnimationDefinitionCompat.addRemoteAnimation(9, 1, new RemoteAnimationAdapterCompat(new WrappedLauncherAnimationRunner(this.mWallpaperOpenRunner, false), 250L, 0L));
        new ActivityCompat(activity).registerRemoteAnimations(remoteAnimationDefinitionCompat);
    }

    protected class WallpaperOpenLauncherAnimationRunner implements WrappedAnimationRunnerImpl {
        private final boolean mFromUnlock;
        private final Handler mHandler;

        public WallpaperOpenLauncherAnimationRunner(Handler handler, boolean fromUnlock) {
            this.mHandler = handler;
            this.mFromUnlock = fromUnlock;
        }

        @Override // com.android.launcher3.WrappedAnimationRunnerImpl
        public Handler getHandler() {
            return this.mHandler;
        }

        @Override // com.android.launcher3.WrappedAnimationRunnerImpl
        public void onCreateAnimation(RemoteAnimationTargetCompat[] appTargets, RemoteAnimationTargetCompat[] wallpaperTargets, LauncherAnimationRunner.AnimationResult result) {
            RemoteAnimationProvider remoteAnimationProvider = AppTransitionManagerImpl.this.mRemoteAnimationProvider;
            AnimatorSet animatorSetCreateWindowAnimation = remoteAnimationProvider != null ? remoteAnimationProvider.createWindowAnimation(appTargets, wallpaperTargets) : null;
            Log.e(AppTransitionManagerImpl.TAG, "WallpaperOpenLauncherAnimationRunner onCreateAnimation anim = " + animatorSetCreateWindowAnimation);
            if (animatorSetCreateWindowAnimation == null) {
                animatorSetCreateWindowAnimation = new AnimatorSet();
                AppTransitionManagerImpl appTransitionManagerImpl = AppTransitionManagerImpl.this;
                animatorSetCreateWindowAnimation.play(appTransitionManagerImpl.getClosingWindowAnimators(appTransitionManagerImpl.mActivity, appTargets, wallpaperTargets));
            }
            result.setAnimation(animatorSetCreateWindowAnimation, AppTransitionManagerImpl.this.mContext);
        }
    }
}
