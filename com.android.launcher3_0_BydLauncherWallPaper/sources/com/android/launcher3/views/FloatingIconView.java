package com.android.launcher3.views;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Outline;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.AdaptiveIconDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.CancellationSignal;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.view.ViewTreeObserver;
import androidx.dynamicanimation.animation.FloatPropertyCompat;
import androidx.dynamicanimation.animation.SpringAnimation;
import androidx.dynamicanimation.animation.SpringForce;
import com.android.launcher3.BubbleTextView;
import com.android.launcher3.InsettableFrameLayout;
import com.android.launcher3.ItemInfo;
import com.android.launcher3.Launcher;
import com.android.launcher3.LauncherAnimUtils;
import com.android.launcher3.LauncherModel;
import com.android.launcher3.R;
import com.android.launcher3.Utilities;
import com.android.launcher3.anim.Interpolators;
import com.android.launcher3.dragndrop.DragLayer;
import com.android.launcher3.dragndrop.FolderAdaptiveIcon;
import com.android.launcher3.folder.FolderIcon;
import com.android.launcher3.graphics.IconShape;
import com.android.launcher3.graphics.ShiftedBitmapDrawable;
import com.android.launcher3.icons.LauncherIcons;
import java.util.Objects;

/* JADX INFO: loaded from: classes4.dex */
public class FloatingIconView extends View implements Animator.AnimatorListener, ClipPathView, ViewTreeObserver.OnGlobalLayoutListener {
    private static final int FADE_DURATION_MS = 200;
    private static final int FG_TRANS_X_FACTOR = 60;
    private static final int FG_TRANS_Y_FACTOR = 75;
    public static final float SHAPE_PROGRESS_DURATION = 0.1f;
    private static final String TAG = "FloatingIconView";
    private static IconLoadResult sIconLoadResult;
    private Drawable mBackground;
    private Drawable mBadge;
    private final int mBlurSizeOutline;
    private Path mClipPath;
    private final Rect mEndRevealRect;
    private Runnable mEndRunnable;
    private AnimatorSet mFadeAnimatorSet;
    private final SpringAnimation mFgSpringX;
    private final SpringAnimation mFgSpringY;
    private float mFgTransX;
    private float mFgTransY;
    private final Rect mFinalDrawableBounds;
    private Drawable mForeground;
    private IconLoadResult mIconLoadResult;
    private boolean mIsAdaptiveIcon;
    private boolean mIsOpening;
    private boolean mIsVerticalBarLayout;
    private final Launcher mLauncher;
    private ListenerView mListenerView;
    private CancellationSignal mLoadIconSignal;
    private Runnable mOnTargetChangeRunnable;
    private View mOriginalIcon;
    private final Rect mOutline;
    private RectF mPositionOut;
    private ValueAnimator mRevealAnimator;
    private float mRotation;
    private final Rect mStartRevealRect;
    private float mTaskCornerRadius;
    private static final Rect sTmpRect = new Rect();
    private static final RectF sTmpRectF = new RectF();
    private static final Object[] sTmpObjArray = new Object[1];
    private static final FloatPropertyCompat<FloatingIconView> mFgTransYProperty = new FloatPropertyCompat<FloatingIconView>("FloatingViewFgTransY") { // from class: com.android.launcher3.views.FloatingIconView.1
        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        public float getValue(FloatingIconView view) {
            return view.mFgTransY;
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        public void setValue(FloatingIconView view, float transY) {
            view.mFgTransY = transY;
            view.invalidate();
        }
    };
    private static final FloatPropertyCompat<FloatingIconView> mFgTransXProperty = new FloatPropertyCompat<FloatingIconView>("FloatingViewFgTransX") { // from class: com.android.launcher3.views.FloatingIconView.2
        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        public float getValue(FloatingIconView view) {
            return view.mFgTransX;
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        public void setValue(FloatingIconView view, float transX) {
            view.mFgTransX = transX;
            view.invalidate();
        }
    };

    @Override // android.animation.Animator.AnimatorListener
    public void onAnimationCancel(Animator animator) {
    }

    @Override // android.animation.Animator.AnimatorListener
    public void onAnimationRepeat(Animator animator) {
    }

    public FloatingIconView(Context context) {
        this(context, null);
    }

    public FloatingIconView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FloatingIconView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mIsVerticalBarLayout = false;
        this.mIsAdaptiveIcon = false;
        this.mStartRevealRect = new Rect();
        this.mEndRevealRect = new Rect();
        this.mOutline = new Rect();
        this.mFinalDrawableBounds = new Rect();
        this.mLauncher = Launcher.getLauncher(context);
        this.mBlurSizeOutline = getResources().getDimensionPixelSize(R.dimen.blur_size_medium_outline);
        this.mListenerView = new ListenerView(context, attrs);
        this.mFgSpringX = new SpringAnimation(this, mFgTransXProperty).setSpring(new SpringForce().setDampingRatio(0.75f).setStiffness(200.0f));
        this.mFgSpringY = new SpringAnimation(this, mFgTransYProperty).setSpring(new SpringForce().setDampingRatio(0.75f).setStiffness(200.0f));
    }

    @Override // android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.mIsOpening) {
            return;
        }
        getViewTreeObserver().addOnGlobalLayoutListener(this);
        this.mLauncher.getRotationHelper().setCurrentTransitionRequest(2);
    }

    @Override // android.view.View
    protected void onDetachedFromWindow() {
        getViewTreeObserver().removeOnGlobalLayoutListener(this);
        super.onDetachedFromWindow();
    }

    public void update(RectF rect, float alpha, float progress, float shapeProgressStart, float cornerRadius, boolean isOpening) {
        int i;
        setAlpha(alpha);
        InsettableFrameLayout.LayoutParams layoutParams = (InsettableFrameLayout.LayoutParams) getLayoutParams();
        float f = rect.left - layoutParams.leftMargin;
        float f2 = rect.top - layoutParams.topMargin;
        setTranslationX(f);
        setTranslationY(f2);
        float fMin = Math.min(layoutParams.width, layoutParams.height);
        float fMax = Math.max(1.0f, Math.min(rect.width() / fMin, rect.height() / fMin));
        setPivotX(0.0f);
        setPivotY(0.0f);
        setScaleX(fMax);
        setScaleY(fMax);
        float fBoundToRange = Utilities.boundToRange(Utilities.mapToRange(Math.max(shapeProgressStart, progress), shapeProgressStart, 1.0f, 0.0f, isOpening ? 10.0f : 1.0f, Interpolators.LINEAR), 0.0f, 1.0f);
        if (this.mIsVerticalBarLayout) {
            this.mOutline.right = (int) (rect.width() / fMax);
        } else {
            this.mOutline.bottom = (int) (rect.height() / fMax);
        }
        this.mTaskCornerRadius = cornerRadius / fMax;
        if (this.mIsAdaptiveIcon) {
            if (!isOpening && progress >= shapeProgressStart) {
                if (this.mRevealAnimator == null) {
                    ValueAnimator valueAnimator = (ValueAnimator) IconShape.getShape().createRevealAnimator(this, this.mStartRevealRect, this.mOutline, this.mTaskCornerRadius, !isOpening);
                    this.mRevealAnimator = valueAnimator;
                    valueAnimator.addListener(new AnimatorListenerAdapter() { // from class: com.android.launcher3.views.FloatingIconView.3
                        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                        public void onAnimationEnd(Animator animation) {
                            FloatingIconView.this.mRevealAnimator = null;
                        }
                    });
                    this.mRevealAnimator.start();
                    this.mRevealAnimator.pause();
                }
                this.mRevealAnimator.setCurrentFraction(fBoundToRange);
            }
            float fWidth = (this.mIsVerticalBarLayout ? this.mOutline.width() : this.mOutline.height()) / fMin;
            setBackgroundDrawableBounds(fWidth);
            if (isOpening) {
                int iHeight = this.mFinalDrawableBounds.height();
                int iWidth = this.mFinalDrawableBounds.width();
                boolean z = this.mIsVerticalBarLayout;
                int i2 = 0;
                if (z) {
                    i = 0;
                } else {
                    float f3 = iHeight;
                    i = (int) (((f3 * fWidth) - f3) / 2.0f);
                }
                if (z) {
                    float f4 = iWidth;
                    i2 = (int) (((fWidth * f4) - f4) / 2.0f);
                }
                Rect rect2 = sTmpRect;
                rect2.set(this.mFinalDrawableBounds);
                rect2.offset(i2, i);
                this.mForeground.setBounds(rect2);
            } else {
                this.mFgSpringX.animateToFinalPosition((int) ((f / this.mLauncher.getDeviceProfile().availableWidthPx) * 60.0f));
                this.mFgSpringY.animateToFinalPosition((int) ((f2 / this.mLauncher.getDeviceProfile().availableHeightPx) * 75.0f));
            }
        }
        invalidate();
        invalidateOutline();
    }

    @Override // android.animation.Animator.AnimatorListener
    public void onAnimationEnd(Animator animator) {
        CancellationSignal cancellationSignal = this.mLoadIconSignal;
        if (cancellationSignal != null) {
            cancellationSignal.cancel();
        }
        Runnable runnable = this.mEndRunnable;
        if (runnable != null) {
            runnable.run();
            return;
        }
        ValueAnimator valueAnimator = this.mRevealAnimator;
        if (valueAnimator != null) {
            valueAnimator.end();
        }
    }

    private void matchPositionOf(Launcher launcher, View v, boolean isOpening, RectF positionOut) {
        float locationBoundsForView = getLocationBoundsForView(launcher, v, isOpening, positionOut);
        InsettableFrameLayout.LayoutParams layoutParams = new InsettableFrameLayout.LayoutParams(Math.round(positionOut.width()), Math.round(positionOut.height()));
        updatePosition(locationBoundsForView, positionOut, layoutParams);
        setLayoutParams(layoutParams);
    }

    private void updatePosition(float rotation, RectF position, InsettableFrameLayout.LayoutParams lp) {
        this.mRotation = rotation;
        this.mPositionOut.set(position);
        lp.ignoreInsets = true;
        lp.leftMargin = Math.round(position.left);
        lp.topMargin = Math.round(position.top);
        layout(lp.leftMargin, lp.topMargin, lp.leftMargin + lp.width, lp.topMargin + lp.height);
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0028 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0029  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static float getLocationBoundsForView(com.android.launcher3.Launcher r11, android.view.View r12, boolean r13, android.graphics.RectF r14) {
        /*
            r0 = 1
            r13 = r13 ^ r0
            boolean r1 = r12 instanceof com.android.launcher3.shortcuts.DeepShortcutView
            r2 = 0
            if (r1 == 0) goto L10
            com.android.launcher3.shortcuts.DeepShortcutView r12 = (com.android.launcher3.shortcuts.DeepShortcutView) r12
            com.android.launcher3.BubbleTextView r12 = r12.getBubbleText()
        Ld:
            r3 = r12
            r7 = r2
            goto L25
        L10:
            android.view.ViewParent r1 = r12.getParent()
            boolean r1 = r1 instanceof com.android.launcher3.shortcuts.DeepShortcutView
            if (r1 == 0) goto L23
            android.view.ViewParent r12 = r12.getParent()
            com.android.launcher3.shortcuts.DeepShortcutView r12 = (com.android.launcher3.shortcuts.DeepShortcutView) r12
            android.view.View r12 = r12.getIconView()
            goto Ld
        L23:
            r3 = r12
            r7 = r13
        L25:
            r12 = 0
            if (r3 != 0) goto L29
            return r12
        L29:
            android.graphics.Rect r13 = new android.graphics.Rect
            r13.<init>()
            boolean r1 = r3 instanceof com.android.launcher3.BubbleTextView
            if (r1 == 0) goto L39
            r1 = r3
            com.android.launcher3.BubbleTextView r1 = (com.android.launcher3.BubbleTextView) r1
            r1.getIconBounds(r13)
            goto L4f
        L39:
            boolean r1 = r3 instanceof com.android.launcher3.folder.FolderIcon
            if (r1 == 0) goto L44
            r1 = r3
            com.android.launcher3.folder.FolderIcon r1 = (com.android.launcher3.folder.FolderIcon) r1
            r1.getPreviewBounds(r13)
            goto L4f
        L44:
            int r1 = r3.getWidth()
            int r4 = r3.getHeight()
            r13.set(r2, r2, r1, r4)
        L4f:
            r1 = 4
            float[] r1 = new float[r1]
            int r4 = r13.left
            float r4 = (float) r4
            r1[r2] = r4
            int r4 = r13.top
            float r4 = (float) r4
            r1[r0] = r4
            int r4 = r13.right
            float r4 = (float) r4
            r9 = 2
            r1[r9] = r4
            int r13 = r13.bottom
            float r13 = (float) r13
            r10 = 3
            r1[r10] = r13
            float[] r13 = new float[r0]
            r13[r2] = r12
            com.android.launcher3.dragndrop.DragLayer r4 = r11.getDragLayer()
            r6 = 0
            r5 = r1
            r8 = r13
            com.android.launcher3.Utilities.getDescendantCoordRelativeToAncestor(r3, r4, r5, r6, r7, r8)
            r11 = r1[r2]
            r12 = r1[r9]
            float r11 = java.lang.Math.min(r11, r12)
            r12 = r1[r0]
            r3 = r1[r10]
            float r12 = java.lang.Math.min(r12, r3)
            r3 = r1[r2]
            r4 = r1[r9]
            float r3 = java.lang.Math.max(r3, r4)
            r0 = r1[r0]
            r1 = r1[r10]
            float r0 = java.lang.Math.max(r0, r1)
            r14.set(r11, r12, r3, r0)
            r11 = r13[r2]
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.launcher3.views.FloatingIconView.getLocationBoundsForView(com.android.launcher3.Launcher, android.view.View, boolean, android.graphics.RectF):float");
    }

    /* JADX WARN: Removed duplicated region for block: B:38:0x0096  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x009a  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x00a9 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static void getIconResult(com.android.launcher3.Launcher r14, android.view.View r15, com.android.launcher3.ItemInfo r16, android.graphics.RectF r17, com.android.launcher3.views.FloatingIconView.IconLoadResult r18) {
        /*
            r0 = r14
            r1 = r15
            r7 = r16
            r8 = r18
            com.android.launcher3.config.BaseFlags$TogglableFlag r2 = com.android.launcher3.config.FeatureFlags.ADAPTIVE_ICON_WINDOW_ANIM
            boolean r2 = r2.get()
            r9 = 1
            r10 = 0
            if (r2 == 0) goto L18
            int r2 = android.os.Build.VERSION.SDK_INT
            r3 = 26
            if (r2 < r3) goto L18
            r2 = r9
            goto L19
        L18:
            r2 = r10
        L19:
            boolean r3 = r1 instanceof com.android.launcher3.BubbleTextView
            r11 = 0
            if (r3 == 0) goto L27
            r4 = r1
            com.android.launcher3.BubbleTextView r4 = (com.android.launcher3.BubbleTextView) r4
            android.graphics.drawable.Drawable r4 = r4.getIcon()
            r12 = r4
            goto L28
        L27:
            r12 = r11
        L28:
            boolean r4 = r7 instanceof com.android.launcher3.popup.SystemShortcut
            if (r4 == 0) goto L4d
            boolean r2 = r1 instanceof android.widget.ImageView
            if (r2 == 0) goto L39
            android.widget.ImageView r1 = (android.widget.ImageView) r1
            android.graphics.drawable.Drawable r12 = r1.getDrawable()
        L36:
            r2 = r11
            goto L94
        L39:
            boolean r2 = r1 instanceof com.android.launcher3.shortcuts.DeepShortcutView
            if (r2 == 0) goto L48
            com.android.launcher3.shortcuts.DeepShortcutView r1 = (com.android.launcher3.shortcuts.DeepShortcutView) r1
            android.view.View r1 = r1.getIconView()
            android.graphics.drawable.Drawable r12 = r1.getBackground()
            goto L36
        L48:
            android.graphics.drawable.Drawable r12 = r15.getBackground()
            goto L36
        L4d:
            boolean r4 = r1 instanceof com.android.launcher3.folder.FolderIcon
            if (r4 == 0) goto L56
            int r5 = r15.getWidth()
            goto L5b
        L56:
            float r5 = r17.width()
            int r5 = (int) r5
        L5b:
            if (r4 == 0) goto L62
            int r1 = r15.getHeight()
            goto L67
        L62:
            float r1 = r17.height()
            int r1 = (int) r1
        L67:
            r4 = r1
            if (r2 == 0) goto L83
            r6 = 0
            java.lang.Object[] r13 = com.android.launcher3.views.FloatingIconView.sTmpObjArray
            r1 = r14
            r2 = r16
            r3 = r5
            r5 = r6
            r6 = r13
            android.graphics.drawable.Drawable r1 = com.android.launcher3.Utilities.getFullDrawable(r1, r2, r3, r4, r5, r6)
            boolean r2 = r1 instanceof android.graphics.drawable.AdaptiveIconDrawable
            if (r2 == 0) goto L36
            r2 = r13[r10]
            android.graphics.drawable.Drawable r2 = com.android.launcher3.Utilities.getBadge(r14, r7, r2)
            r12 = r1
            goto L94
        L83:
            if (r3 == 0) goto L86
            goto L36
        L86:
            r6 = 0
            java.lang.Object[] r10 = com.android.launcher3.views.FloatingIconView.sTmpObjArray
            r1 = r14
            r2 = r16
            r3 = r5
            r5 = r6
            r6 = r10
            android.graphics.drawable.Drawable r12 = com.android.launcher3.Utilities.getFullDrawable(r1, r2, r3, r4, r5, r6)
            goto L36
        L94:
            if (r12 != 0) goto L9a
            r3 = r17
            r1 = r11
            goto La4
        L9a:
            android.graphics.drawable.Drawable$ConstantState r1 = r12.getConstantState()
            android.graphics.drawable.Drawable r1 = r1.newDrawable()
            r3 = r17
        La4:
            int r3 = getOffsetForIconBounds(r14, r1, r3)
            monitor-enter(r18)
            r8.drawable = r1     // Catch: java.lang.Throwable -> Lc2
            r8.badge = r2     // Catch: java.lang.Throwable -> Lc2
            r8.iconOffset = r3     // Catch: java.lang.Throwable -> Lc2
            java.lang.Runnable r1 = r8.onIconLoaded     // Catch: java.lang.Throwable -> Lc2
            if (r1 == 0) goto Lbe
            java.util.concurrent.Executor r0 = r14.getMainExecutor()     // Catch: java.lang.Throwable -> Lc2
            java.lang.Runnable r1 = r8.onIconLoaded     // Catch: java.lang.Throwable -> Lc2
            r0.execute(r1)     // Catch: java.lang.Throwable -> Lc2
            r8.onIconLoaded = r11     // Catch: java.lang.Throwable -> Lc2
        Lbe:
            r8.isIconLoaded = r9     // Catch: java.lang.Throwable -> Lc2
            monitor-exit(r18)     // Catch: java.lang.Throwable -> Lc2
            return
        Lc2:
            r0 = move-exception
            monitor-exit(r18)     // Catch: java.lang.Throwable -> Lc2
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.launcher3.views.FloatingIconView.getIconResult(com.android.launcher3.Launcher, android.view.View, com.android.launcher3.ItemInfo, android.graphics.RectF, com.android.launcher3.views.FloatingIconView$IconLoadResult):void");
    }

    private void setIcon(View originalView, Drawable drawable, Drawable badge, int iconOffset) {
        this.mBadge = badge;
        boolean z = drawable instanceof AdaptiveIconDrawable;
        this.mIsAdaptiveIcon = z;
        if (z) {
            boolean z2 = drawable instanceof FolderAdaptiveIcon;
            AdaptiveIconDrawable adaptiveIconDrawable = (AdaptiveIconDrawable) drawable;
            Drawable background = adaptiveIconDrawable.getBackground();
            if (background == null) {
                background = new ColorDrawable(0);
            }
            this.mBackground = background;
            Drawable foreground = adaptiveIconDrawable.getForeground();
            if (foreground == null) {
                foreground = new ColorDrawable(0);
            }
            this.mForeground = foreground;
            InsettableFrameLayout.LayoutParams layoutParams = (InsettableFrameLayout.LayoutParams) getLayoutParams();
            int i = layoutParams.height;
            int i2 = layoutParams.width;
            int i3 = this.mBlurSizeOutline / 2;
            this.mFinalDrawableBounds.set(0, 0, i2, i);
            if (!z2) {
                int i4 = iconOffset - i3;
                this.mFinalDrawableBounds.inset(i4, i4);
            }
            this.mForeground.setBounds(this.mFinalDrawableBounds);
            this.mBackground.setBounds(this.mFinalDrawableBounds);
            this.mStartRevealRect.set(0, 0, i2, i);
            Drawable drawable2 = this.mBadge;
            if (drawable2 != null) {
                drawable2.setBounds(this.mStartRevealRect);
                if (!this.mIsOpening && !z2) {
                    LauncherAnimUtils.DRAWABLE_ALPHA.set(this.mBadge, 0);
                }
            }
            if (z2) {
                FolderIcon folderIcon = (FolderIcon) originalView;
                folderIcon.getPreviewBounds(sTmpRect);
                float backgroundStrokeWidth = folderIcon.getBackgroundStrokeWidth();
                Drawable drawable3 = this.mForeground;
                if (drawable3 instanceof ShiftedBitmapDrawable) {
                    ShiftedBitmapDrawable shiftedBitmapDrawable = (ShiftedBitmapDrawable) drawable3;
                    shiftedBitmapDrawable.setShiftX((shiftedBitmapDrawable.getShiftX() - r8.left) - backgroundStrokeWidth);
                    shiftedBitmapDrawable.setShiftY((shiftedBitmapDrawable.getShiftY() - r8.top) - backgroundStrokeWidth);
                }
                Drawable drawable4 = this.mBadge;
                if (drawable4 instanceof ShiftedBitmapDrawable) {
                    ShiftedBitmapDrawable shiftedBitmapDrawable2 = (ShiftedBitmapDrawable) drawable4;
                    shiftedBitmapDrawable2.setShiftX((shiftedBitmapDrawable2.getShiftX() - r8.left) - backgroundStrokeWidth);
                    shiftedBitmapDrawable2.setShiftY((shiftedBitmapDrawable2.getShiftY() - r8.top) - backgroundStrokeWidth);
                }
            } else {
                Utilities.scaleRectAboutCenter(this.mStartRevealRect, IconShape.getNormalizationScale());
            }
            float f = this.mLauncher.getDeviceProfile().aspectRatio;
            if (this.mIsVerticalBarLayout) {
                layoutParams.width = (int) Math.max(layoutParams.width, layoutParams.height * f);
            } else {
                layoutParams.height = (int) Math.max(layoutParams.height, layoutParams.width * f);
            }
            layout(layoutParams.leftMargin, layoutParams.topMargin, layoutParams.leftMargin + layoutParams.width, layoutParams.topMargin + layoutParams.height);
            float fMax = Math.max(layoutParams.height / i, layoutParams.width / i2);
            if (this.mIsOpening) {
                fMax = 1.0f;
                this.mOutline.set(0, 0, i2, i);
            } else {
                this.mOutline.set(0, 0, layoutParams.width, layoutParams.height);
            }
            setBackgroundDrawableBounds(fMax);
            this.mEndRevealRect.set(0, 0, layoutParams.width, layoutParams.height);
            setOutlineProvider(new ViewOutlineProvider() { // from class: com.android.launcher3.views.FloatingIconView.4
                @Override // android.view.ViewOutlineProvider
                public void getOutline(View view, Outline outline) {
                    outline.setRoundRect(FloatingIconView.this.mOutline, FloatingIconView.this.mTaskCornerRadius);
                }
            });
            setClipToOutline(true);
        } else {
            setBackground(drawable);
            setClipToOutline(false);
        }
        invalidate();
        invalidateOutline();
    }

    private void checkIconResult(final View originalView, boolean isOpening) {
        final CancellationSignal cancellationSignal = new CancellationSignal();
        if (!isOpening) {
            originalView.setVisibility(4);
            cancellationSignal.setOnCancelListener(new CancellationSignal.OnCancelListener() { // from class: com.android.launcher3.views.-$$Lambda$FloatingIconView$MublqJw7MH4VeyGZw2Lg2wPuqoM
                @Override // android.os.CancellationSignal.OnCancelListener
                public final void onCancel() {
                    originalView.setVisibility(0);
                }
            });
        }
        IconLoadResult iconLoadResult = this.mIconLoadResult;
        if (iconLoadResult == null) {
            Log.w(TAG, "No icon load result found in checkIconResult");
            return;
        }
        synchronized (iconLoadResult) {
            if (this.mIconLoadResult.isIconLoaded) {
                setIcon(originalView, this.mIconLoadResult.drawable, this.mIconLoadResult.badge, this.mIconLoadResult.iconOffset);
                if (isOpening) {
                    originalView.setVisibility(4);
                }
            } else {
                this.mIconLoadResult.onIconLoaded = new Runnable() { // from class: com.android.launcher3.views.-$$Lambda$FloatingIconView$UFYWyoDaiNoiBTjwip2C48WhVP4
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$checkIconResult$1$FloatingIconView(cancellationSignal, originalView);
                    }
                };
                this.mLoadIconSignal = cancellationSignal;
            }
        }
    }

    public /* synthetic */ void lambda$checkIconResult$1$FloatingIconView(CancellationSignal cancellationSignal, View view) {
        if (cancellationSignal.isCanceled()) {
            return;
        }
        setIcon(view, this.mIconLoadResult.drawable, this.mIconLoadResult.badge, this.mIconLoadResult.iconOffset);
        setVisibility(0);
        view.setVisibility(4);
    }

    private void setBackgroundDrawableBounds(float scale) {
        Rect rect = sTmpRect;
        rect.set(this.mFinalDrawableBounds);
        Utilities.scaleRectAboutCenter(rect, scale);
        if (this.mIsVerticalBarLayout) {
            rect.offsetTo((int) (this.mFinalDrawableBounds.left * scale), rect.top);
        } else {
            rect.offsetTo(rect.left, (int) (this.mFinalDrawableBounds.top * scale));
        }
        this.mBackground.setBounds(rect);
    }

    private static int getOffsetForIconBounds(Launcher l, Drawable drawable, RectF position) {
        if (Build.VERSION.SDK_INT < 26 || !(drawable instanceof AdaptiveIconDrawable)) {
            return 0;
        }
        int dimensionPixelSize = l.getResources().getDimensionPixelSize(R.dimen.blur_size_medium_outline);
        Rect rect = new Rect(0, 0, ((int) position.width()) + dimensionPixelSize, ((int) position.height()) + dimensionPixelSize);
        int i = dimensionPixelSize / 2;
        rect.inset(i, i);
        LauncherIcons launcherIconsObtain = LauncherIcons.obtain(l);
        try {
            Utilities.scaleRectAboutCenter(rect, launcherIconsObtain.getNormalizer().getScale(drawable, null, null, null));
            if (launcherIconsObtain != null) {
                launcherIconsObtain.close();
            }
            rect.inset((int) ((-rect.width()) * AdaptiveIconDrawable.getExtraInsetFraction()), (int) ((-rect.height()) * AdaptiveIconDrawable.getExtraInsetFraction()));
            return rect.left;
        } catch (Throwable th) {
            if (launcherIconsObtain != null) {
                try {
                    launcherIconsObtain.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    @Override // com.android.launcher3.views.ClipPathView
    public void setClipPath(Path clipPath) {
        this.mClipPath = clipPath;
        invalidate();
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        int iSave = canvas.save();
        canvas.rotate(this.mRotation, this.mFinalDrawableBounds.exactCenterX(), this.mFinalDrawableBounds.exactCenterY());
        Path path = this.mClipPath;
        if (path != null) {
            canvas.clipPath(path);
        }
        super.draw(canvas);
        Drawable drawable = this.mBackground;
        if (drawable != null) {
            drawable.draw(canvas);
        }
        if (this.mForeground != null) {
            int iSave2 = canvas.save();
            canvas.translate(this.mFgTransX, this.mFgTransY);
            this.mForeground.draw(canvas);
            canvas.restoreToCount(iSave2);
        }
        Drawable drawable2 = this.mBadge;
        if (drawable2 != null) {
            drawable2.draw(canvas);
        }
        canvas.restoreToCount(iSave);
    }

    public void onListenerViewClosed() {
        Runnable runnable = this.mEndRunnable;
        if (runnable != null) {
            runnable.run();
            this.mEndRunnable = null;
        }
        AnimatorSet animatorSet = this.mFadeAnimatorSet;
        if (animatorSet != null) {
            animatorSet.end();
            this.mFadeAnimatorSet = null;
        }
    }

    @Override // android.animation.Animator.AnimatorListener
    public void onAnimationStart(Animator animator) {
        IconLoadResult iconLoadResult = this.mIconLoadResult;
        if (iconLoadResult == null || !iconLoadResult.isIconLoaded) {
            return;
        }
        setVisibility(0);
    }

    @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
    public void onGlobalLayout() {
        if (!this.mOriginalIcon.isAttachedToWindow() || this.mPositionOut == null) {
            return;
        }
        Launcher launcher = this.mLauncher;
        View view = this.mOriginalIcon;
        boolean z = this.mIsOpening;
        RectF rectF = sTmpRectF;
        float locationBoundsForView = getLocationBoundsForView(launcher, view, z, rectF);
        if (locationBoundsForView == this.mRotation && rectF.equals(this.mPositionOut)) {
            return;
        }
        updatePosition(locationBoundsForView, rectF, (InsettableFrameLayout.LayoutParams) getLayoutParams());
        Runnable runnable = this.mOnTargetChangeRunnable;
        if (runnable != null) {
            runnable.run();
        }
    }

    public void setOnTargetChangeListener(Runnable onTargetChangeListener) {
        this.mOnTargetChangeRunnable = onTargetChangeListener;
    }

    public static IconLoadResult fetchIcon(final Launcher l, final View v, final ItemInfo info, final boolean isOpening) {
        final IconLoadResult iconLoadResult = new IconLoadResult();
        new Handler(LauncherModel.getWorkerLooper()).postAtFrontOfQueue(new Runnable() { // from class: com.android.launcher3.views.-$$Lambda$FloatingIconView$8n4mwezx1p9wGVeuaVmTGOdwry4
            @Override // java.lang.Runnable
            public final void run() {
                FloatingIconView.lambda$fetchIcon$2(l, v, isOpening, info, iconLoadResult);
            }
        });
        sIconLoadResult = iconLoadResult;
        return iconLoadResult;
    }

    static /* synthetic */ void lambda$fetchIcon$2(Launcher launcher, View view, boolean z, ItemInfo itemInfo, IconLoadResult iconLoadResult) {
        RectF rectF = new RectF();
        getLocationBoundsForView(launcher, view, z, rectF);
        getIconResult(launcher, view, itemInfo, rectF, iconLoadResult);
    }

    public static FloatingIconView getFloatingIconView(Launcher launcher, final View originalView, final boolean hideOriginal, RectF positionOut, final boolean isOpening) {
        final DragLayer dragLayer = launcher.getDragLayer();
        ViewGroup viewGroup = (ViewGroup) dragLayer.getParent();
        final FloatingIconView floatingIconView = (FloatingIconView) launcher.getViewCache().getView(R.layout.floating_icon_view, launcher, viewGroup);
        floatingIconView.recycle();
        boolean z = (originalView.getTag() instanceof ItemInfo) && hideOriginal;
        IconLoadResult iconLoadResult = sIconLoadResult;
        floatingIconView.mIconLoadResult = iconLoadResult;
        if (z && iconLoadResult == null) {
            floatingIconView.mIconLoadResult = fetchIcon(launcher, originalView, (ItemInfo) originalView.getTag(), isOpening);
        }
        sIconLoadResult = null;
        floatingIconView.mIsVerticalBarLayout = launcher.getDeviceProfile().isVerticalBarLayout();
        floatingIconView.mIsOpening = isOpening;
        floatingIconView.mOriginalIcon = originalView;
        floatingIconView.mPositionOut = positionOut;
        floatingIconView.matchPositionOf(launcher, originalView, isOpening, positionOut);
        if (z) {
            floatingIconView.checkIconResult(originalView, isOpening);
        }
        floatingIconView.setVisibility(4);
        viewGroup.addView(floatingIconView);
        dragLayer.addView(floatingIconView.mListenerView);
        ListenerView listenerView = floatingIconView.mListenerView;
        Objects.requireNonNull(floatingIconView);
        listenerView.setListener(new Runnable() { // from class: com.android.launcher3.views.-$$Lambda$XmwoWftILL6fRO_BhUMVTj0539k
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.onListenerViewClosed();
            }
        });
        floatingIconView.mEndRunnable = new Runnable() { // from class: com.android.launcher3.views.-$$Lambda$FloatingIconView$oysEnXaTMgz9ConqRgcaLgoiiC8
            @Override // java.lang.Runnable
            public final void run() {
                FloatingIconView.lambda$getFloatingIconView$3(this.f$0, hideOriginal, isOpening, originalView, dragLayer);
            }
        };
        return floatingIconView;
    }

    static /* synthetic */ void lambda$getFloatingIconView$3(FloatingIconView floatingIconView, boolean z, boolean z2, View view, DragLayer dragLayer) {
        floatingIconView.mEndRunnable = null;
        if (!z) {
            floatingIconView.finish(dragLayer);
            return;
        }
        if (z2) {
            view.setVisibility(0);
            floatingIconView.finish(dragLayer);
        } else {
            AnimatorSet animatorSetCreateFadeAnimation = floatingIconView.createFadeAnimation(view, dragLayer);
            floatingIconView.mFadeAnimatorSet = animatorSetCreateFadeAnimation;
            animatorSetCreateFadeAnimation.start();
        }
    }

    private AnimatorSet createFadeAnimation(final View originalView, final DragLayer dragLayer) {
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(200L);
        animatorSet.addListener(new AnimatorListenerAdapter() { // from class: com.android.launcher3.views.FloatingIconView.5
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animation) {
                originalView.setVisibility(0);
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animation) {
                FloatingIconView.this.finish(dragLayer);
            }
        });
        Drawable drawable = this.mBadge;
        if (drawable != null && !(this.mOriginalIcon instanceof FolderIcon)) {
            ObjectAnimator objectAnimatorOfInt = ObjectAnimator.ofInt(drawable, LauncherAnimUtils.DRAWABLE_ALPHA, 255);
            objectAnimatorOfInt.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.android.launcher3.views.-$$Lambda$FloatingIconView$7OznQyBrkeNOWJ1J94OdLggFFMs
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                    this.f$0.lambda$createFadeAnimation$4$FloatingIconView(valueAnimator);
                }
            });
            animatorSet.play(objectAnimatorOfInt);
        }
        if (originalView instanceof BubbleTextView) {
            final BubbleTextView bubbleTextView = (BubbleTextView) originalView;
            bubbleTextView.forceHideDot(true);
            animatorSet.addListener(new AnimatorListenerAdapter() { // from class: com.android.launcher3.views.FloatingIconView.6
                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animation) {
                    bubbleTextView.forceHideDot(false);
                }
            });
        }
        if (originalView instanceof FolderIcon) {
            final FolderIcon folderIcon = (FolderIcon) originalView;
            folderIcon.setBackgroundVisible(false);
            folderIcon.getFolderName().setTextVisibility(false);
            animatorSet.play(folderIcon.getFolderName().createTextAlphaAnimator(true));
            animatorSet.addListener(new AnimatorListenerAdapter() { // from class: com.android.launcher3.views.FloatingIconView.7
                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animation) {
                    folderIcon.setBackgroundVisible(true);
                    if (folderIcon.hasDot()) {
                        folderIcon.animateDotScale(0.0f, 1.0f);
                    }
                }
            });
        } else {
            animatorSet.play(ObjectAnimator.ofFloat(originalView, ALPHA, 0.0f, 1.0f));
        }
        return animatorSet;
    }

    public /* synthetic */ void lambda$createFadeAnimation$4$FloatingIconView(ValueAnimator valueAnimator) {
        invalidate();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void finish(DragLayer dragLayer) {
        ((ViewGroup) dragLayer.getParent()).removeView(this);
        dragLayer.removeView(this.mListenerView);
        recycle();
        this.mLauncher.getViewCache().recycleView(R.layout.floating_icon_view, this);
    }

    private void recycle() {
        setTranslationX(0.0f);
        setTranslationY(0.0f);
        setScaleX(1.0f);
        setScaleY(1.0f);
        setAlpha(1.0f);
        setBackground(null);
        CancellationSignal cancellationSignal = this.mLoadIconSignal;
        if (cancellationSignal != null) {
            cancellationSignal.cancel();
        }
        this.mLoadIconSignal = null;
        this.mEndRunnable = null;
        this.mIsAdaptiveIcon = false;
        this.mForeground = null;
        this.mBackground = null;
        this.mClipPath = null;
        this.mFinalDrawableBounds.setEmpty();
        ValueAnimator valueAnimator = this.mRevealAnimator;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        this.mRevealAnimator = null;
        AnimatorSet animatorSet = this.mFadeAnimatorSet;
        if (animatorSet != null) {
            animatorSet.cancel();
        }
        this.mPositionOut = null;
        this.mFadeAnimatorSet = null;
        this.mListenerView.setListener(null);
        this.mOriginalIcon = null;
        this.mOnTargetChangeRunnable = null;
        this.mTaskCornerRadius = 0.0f;
        this.mOutline.setEmpty();
        this.mFgTransY = 0.0f;
        this.mFgSpringX.cancel();
        this.mFgTransX = 0.0f;
        this.mFgSpringY.cancel();
        this.mBadge = null;
        sTmpObjArray[0] = null;
        this.mIconLoadResult = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static class IconLoadResult {
        Drawable badge;
        Drawable drawable;
        int iconOffset;
        boolean isIconLoaded;
        Runnable onIconLoaded;

        private IconLoadResult() {
        }
    }
}
