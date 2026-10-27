package com.android.systemui.statusbar.phone;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.SystemClock;
import android.util.Log;
import android.util.MathUtils;
import android.view.ContextThemeWrapper;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Interpolator;
import android.view.animation.PathInterpolator;
import androidx.core.graphics.ColorUtils;
import androidx.core.internal.view.SupportMenu;
import androidx.core.view.InputDeviceCompat;
import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.dynamicanimation.animation.FloatPropertyCompat;
import androidx.dynamicanimation.animation.SpringAnimation;
import androidx.dynamicanimation.animation.SpringForce;
import com.android.systemui.Interpolators;
import com.android.systemui.R;
import com.android.systemui.plugins.NavigationEdgeBackPlugin;
import com.android.systemui.statusbar.phone.RegionSamplingHelper;
import com.byd.card.utils.BitmapUtils;

/* JADX INFO: loaded from: classes.dex */
public class NavigationBarEdgePanel extends View implements NavigationEdgeBackPlugin {
    private static final int ARROW_ANGLE_ADDED_PER_1000_SPEED = 4;
    private static final int ARROW_ANGLE_WHEN_EXTENDED_DEGREES = 56;
    private static final int ARROW_LENGTH_DP = 54;
    private static final int ARROW_MAX_ANGLE_SPEED_OFFSET_DEGREES = 4;
    private static final float ARROW_THICKNESS_DP = 2.5f;
    private static final int BASE_TRANSLATION_DP = 72;
    private static final long COLOR_ANIMATION_DURATION_MS = 0;
    private static final long DISAPPEAR_ARROW_ANIMATION_DURATION_MS = 0;
    private static final long DISAPPEAR_FADE_ANIMATION_DURATION_MS = 0;
    private static final int GESTURE_DURATION_FOR_CLICK_MS = 0;
    private static final int PROTECTION_WIDTH_PX = 2;
    private static final int RUBBER_BAND_AMOUNT = 15;
    private static final int RUBBER_BAND_AMOUNT_APPEAR = 2;
    private static final String TAG = "NavigationBarEdgePanel";
    private Bitmap arrowBmp;
    private boolean isReverse;
    private final SpringAnimation mAngleAnimation;
    private final SpringForce mAngleAppearForce;
    private final SpringForce mAngleDisappearForce;
    private float mAngleOffset;
    private int mArrowColor;
    private final ValueAnimator mArrowColorAnimator;
    private int mArrowColorDark;
    private int mArrowColorLight;
    private final ValueAnimator mArrowDisappearAnimation;
    private final float mArrowLength;
    private int mArrowPaddingEnd;
    private final Path mArrowPath;
    private int mArrowStartColor;
    private final float mArrowThickness;
    private boolean mArrowsPointLeft;
    private NavigationEdgeBackPlugin.BackCallback mBackCallback;
    private final float mBaseTranslation;
    private float mCurrentAngle;
    private int mCurrentArrowColor;
    private float mCurrentTranslation;
    private final float mDensity;
    private float mDesiredAngle;
    private float mDesiredTranslation;
    private float mDesiredVerticalTranslation;
    private float mDisappearAmount;
    private final Point mDisplaySize;
    private boolean mDragSlopPassed;
    private int mFingerOffset;
    private boolean mIsDark;
    private boolean mIsLeftPanel;
    private WindowManager.LayoutParams mLayoutParams;
    private int mLeftInset;
    private float mMaxTranslation;
    private int mMinArrowPosition;
    private final int[] mMinColors;
    private final float mMinDeltaForSwitch;
    private final Paint mPaint;
    Path mPath;
    private float mPreviousTouchTranslation;
    private int mProtectionColor;
    private int mProtectionColorDark;
    private int mProtectionColorLight;
    private final Paint mProtectionPaint;
    private RegionSamplingHelper mRegionSamplingHelper;
    private final SpringForce mRegularTranslationSpring;
    private int mRightInset;
    private final Rect mSamplingRect;
    private int mScreenSize;
    private DynamicAnimation.OnAnimationEndListener mSetGoneEndListener;
    private boolean mShowProtection;
    Path mSidePath;
    private float mStartX;
    private float mStartY;
    private final float mSwipeThreshold;
    private float mTotalTouchDelta;
    private final SpringAnimation mTranslationAnimation;
    private boolean mTriggerBack;
    private final SpringForce mTriggerBackSpring;
    private VelocityTracker mVelocityTracker;
    private float mVerticalTranslation;
    private final SpringAnimation mVerticalTranslationAnimation;
    private long mVibrationTime;
    private final Paint mWavePaint;
    private final Paint mWaveSidePaint;
    private final WindowManager mWindowManager;
    int[] radialColors;
    private static final Interpolator RUBBER_BAND_INTERPOLATOR = new PathInterpolator(0.2f, 1.0f, 1.0f, 1.0f);
    private static final Interpolator RUBBER_BAND_INTERPOLATOR_APPEAR = new PathInterpolator(0.5f, 1.0f, 1.0f, 1.0f);
    private static final FloatPropertyCompat<NavigationBarEdgePanel> CURRENT_ANGLE = new FloatPropertyCompat<NavigationBarEdgePanel>("currentAngle") { // from class: com.android.systemui.statusbar.phone.NavigationBarEdgePanel.2
        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        public void setValue(NavigationBarEdgePanel object, float value) {
            object.setCurrentAngle(value);
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        public float getValue(NavigationBarEdgePanel object) {
            return object.getCurrentAngle();
        }
    };
    private static final FloatPropertyCompat<NavigationBarEdgePanel> CURRENT_TRANSLATION = new FloatPropertyCompat<NavigationBarEdgePanel>("currentTranslation") { // from class: com.android.systemui.statusbar.phone.NavigationBarEdgePanel.3
        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        public void setValue(NavigationBarEdgePanel object, float value) {
            object.setCurrentTranslation(value);
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        public float getValue(NavigationBarEdgePanel object) {
            return object.getCurrentTranslation();
        }
    };
    private static final FloatPropertyCompat<NavigationBarEdgePanel> CURRENT_VERTICAL_TRANSLATION = new FloatPropertyCompat<NavigationBarEdgePanel>("verticalTranslation") { // from class: com.android.systemui.statusbar.phone.NavigationBarEdgePanel.4
        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        public void setValue(NavigationBarEdgePanel object, float value) {
            object.setVerticalTranslation(value);
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        public float getValue(NavigationBarEdgePanel object) {
            return object.getVerticalTranslation();
        }
    };

    @Override // android.view.View
    public boolean hasOverlappingRendering() {
        return false;
    }

    public NavigationBarEdgePanel(Context context) {
        super(context);
        Paint paint = new Paint();
        this.mPaint = paint;
        this.mArrowPath = new Path();
        this.mDisplaySize = new Point();
        this.mIsDark = false;
        this.mShowProtection = false;
        this.mSamplingRect = new Rect();
        this.arrowBmp = null;
        this.isReverse = false;
        Paint paint2 = new Paint();
        this.mWavePaint = paint2;
        Paint paint3 = new Paint();
        this.mWaveSidePaint = paint3;
        this.mSetGoneEndListener = new DynamicAnimation.OnAnimationEndListener() { // from class: com.android.systemui.statusbar.phone.NavigationBarEdgePanel.1
            @Override // androidx.dynamicanimation.animation.DynamicAnimation.OnAnimationEndListener
            public void onAnimationEnd(DynamicAnimation animation, boolean canceled, float value, float velocity) {
                animation.removeEndListener(this);
                if (canceled) {
                    return;
                }
                NavigationBarEdgePanel.this.setVisibility(8);
            }
        };
        this.mPath = new Path();
        this.mSidePath = new Path();
        this.mMinColors = new int[]{Color.parseColor("#FFFFFFFF"), Color.parseColor("#00323232")};
        this.radialColors = new int[]{SupportMenu.CATEGORY_MASK, InputDeviceCompat.SOURCE_ANY, -16711936};
        Log.e(TAG, TAG);
        this.mWindowManager = (WindowManager) context.getSystemService(WindowManager.class);
        this.mDensity = context.getResources().getDisplayMetrics().density;
        this.mBaseTranslation = dp(72.0f);
        this.mArrowLength = dp(54.0f);
        float fDp = dp(ARROW_THICKNESS_DP);
        this.mArrowThickness = fDp;
        this.mMinDeltaForSwitch = dp(32.0f);
        paint.setStrokeWidth(fDp);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        this.mArrowColorAnimator = valueAnimatorOfFloat;
        valueAnimatorOfFloat.setDuration(0L);
        valueAnimatorOfFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.android.systemui.statusbar.phone.-$$Lambda$NavigationBarEdgePanel$N5ePht9FJY6_QlMLdNO6lFr5wI0
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                this.f$0.lambda$new$0$NavigationBarEdgePanel(valueAnimator);
            }
        });
        ValueAnimator valueAnimatorOfFloat2 = ValueAnimator.ofFloat(0.0f, 1.0f);
        this.mArrowDisappearAnimation = valueAnimatorOfFloat2;
        valueAnimatorOfFloat2.setDuration(0L);
        valueAnimatorOfFloat2.setInterpolator(Interpolators.FAST_OUT_SLOW_IN);
        valueAnimatorOfFloat2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.android.systemui.statusbar.phone.-$$Lambda$NavigationBarEdgePanel$pSnEaGMBRuNC755lR2nxjLcZkRU
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                this.f$0.lambda$new$1$NavigationBarEdgePanel(valueAnimator);
            }
        });
        SpringAnimation springAnimation = new SpringAnimation(this, CURRENT_ANGLE);
        this.mAngleAnimation = springAnimation;
        SpringForce dampingRatio = new SpringForce().setStiffness(500.0f).setDampingRatio(0.5f);
        this.mAngleAppearForce = dampingRatio;
        this.mAngleDisappearForce = new SpringForce().setStiffness(1500.0f).setDampingRatio(0.5f).setFinalPosition(90.0f);
        springAnimation.setSpring(dampingRatio).setMaxValue(90.0f);
        SpringAnimation springAnimation2 = new SpringAnimation(this, CURRENT_TRANSLATION);
        this.mTranslationAnimation = springAnimation2;
        SpringForce dampingRatio2 = new SpringForce().setStiffness(1500.0f).setDampingRatio(0.75f);
        this.mRegularTranslationSpring = dampingRatio2;
        this.mTriggerBackSpring = new SpringForce().setStiffness(450.0f).setDampingRatio(0.75f);
        springAnimation2.setSpring(dampingRatio2);
        SpringAnimation springAnimation3 = new SpringAnimation(this, CURRENT_VERTICAL_TRANSLATION);
        this.mVerticalTranslationAnimation = springAnimation3;
        springAnimation3.setSpring(new SpringForce().setStiffness(1500.0f).setDampingRatio(0.75f));
        Paint paint4 = new Paint(paint);
        this.mProtectionPaint = paint4;
        paint4.setStrokeWidth(fDp + 2.0f);
        loadDimens();
        loadColors(context);
        updateArrowDirection();
        paint2.setStrokeWidth(fDp);
        paint2.setStrokeCap(Paint.Cap.ROUND);
        paint2.setAntiAlias(true);
        paint2.setStyle(Paint.Style.FILL);
        paint2.setStrokeJoin(Paint.Join.ROUND);
        paint2.setColor(getResources().getColor(R.color.edge_back_background));
        paint3.setStrokeWidth(2.0f);
        paint3.setStrokeCap(Paint.Cap.ROUND);
        paint3.setStyle(Paint.Style.STROKE);
        paint3.setColor(-1);
        paint3.setAntiAlias(true);
        paint3.setStrokeJoin(Paint.Join.ROUND);
        this.mSwipeThreshold = context.getResources().getDimension(R.dimen.navigation_edge_action_drag_threshold);
        setVisibility(8);
        RegionSamplingHelper regionSamplingHelper = new RegionSamplingHelper(this, new RegionSamplingHelper.SamplingCallback() { // from class: com.android.systemui.statusbar.phone.NavigationBarEdgePanel.5
            @Override // com.android.systemui.statusbar.phone.RegionSamplingHelper.SamplingCallback
            public void onRegionDarknessChanged(boolean isRegionDark) {
                NavigationBarEdgePanel.this.setIsDark(!isRegionDark, true);
            }

            @Override // com.android.systemui.statusbar.phone.RegionSamplingHelper.SamplingCallback
            public Rect getSampledRegion(View sampledView) {
                return NavigationBarEdgePanel.this.mSamplingRect;
            }
        });
        this.mRegionSamplingHelper = regionSamplingHelper;
        regionSamplingHelper.setWindowVisible(true);
        this.arrowBmp = BitmapUtils.drawableToBitmap(context, R.mipmap.edge_back_arrow);
    }

    public /* synthetic */ void lambda$new$0$NavigationBarEdgePanel(ValueAnimator valueAnimator) {
        setCurrentArrowColor(ColorUtils.blendARGB(this.mArrowStartColor, this.mArrowColor, valueAnimator.getAnimatedFraction()));
    }

    public /* synthetic */ void lambda$new$1$NavigationBarEdgePanel(ValueAnimator valueAnimator) {
        this.mDisappearAmount = ((Float) valueAnimator.getAnimatedValue()).floatValue();
        invalidate();
    }

    @Override // com.android.systemui.plugins.Plugin
    public void onDestroy() {
        this.mWindowManager.removeView(this);
        this.mRegionSamplingHelper.stop();
        this.mRegionSamplingHelper = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setIsDark(boolean isDark, boolean animate) {
        this.mIsDark = isDark;
        updateIsDark(animate);
    }

    private void setShowProtection(boolean showProtection) {
        this.mShowProtection = showProtection;
        invalidate();
    }

    @Override // com.android.systemui.plugins.NavigationEdgeBackPlugin
    public void setIsLeftPanel(boolean isLeftPanel) {
        this.mIsLeftPanel = isLeftPanel;
        this.mLayoutParams.gravity = isLeftPanel ? 51 : 53;
    }

    @Override // com.android.systemui.plugins.NavigationEdgeBackPlugin
    public void setInsets(int leftInset, int rightInset) {
        this.mLeftInset = leftInset;
        this.mRightInset = rightInset;
    }

    @Override // com.android.systemui.plugins.NavigationEdgeBackPlugin
    public void setDisplaySize(Point displaySize) {
        this.mDisplaySize.set(displaySize.x, displaySize.y);
        this.mScreenSize = Math.min(this.mDisplaySize.x, this.mDisplaySize.y);
    }

    @Override // com.android.systemui.plugins.NavigationEdgeBackPlugin
    public void setBackCallback(NavigationEdgeBackPlugin.BackCallback callback) {
        this.mBackCallback = callback;
    }

    @Override // com.android.systemui.plugins.NavigationEdgeBackPlugin
    public void setLayoutParams(WindowManager.LayoutParams layoutParams) {
        this.mLayoutParams = layoutParams;
        this.mWindowManager.addView(this, layoutParams);
    }

    private void adjustSamplingRectToBoundingBox() {
        float staticArrowWidth = this.mDesiredTranslation;
        if (!this.mTriggerBack) {
            staticArrowWidth = this.mBaseTranslation;
            boolean z = this.mIsLeftPanel;
            if ((z && this.mArrowsPointLeft) || (!z && !this.mArrowsPointLeft)) {
                staticArrowWidth -= getStaticArrowWidth();
            }
        }
        float fWidth = staticArrowWidth - (this.mArrowThickness / 2.0f);
        if (!this.mIsLeftPanel) {
            fWidth = this.mSamplingRect.width() - fWidth;
        }
        float staticArrowWidth2 = getStaticArrowWidth();
        float fPolarToCartY = polarToCartY(56.0f) * this.mArrowLength * 2.0f;
        if (!this.mArrowsPointLeft) {
            fWidth -= staticArrowWidth2;
        }
        this.mSamplingRect.offset((int) fWidth, (int) (((getHeight() * 0.5f) + this.mDesiredVerticalTranslation) - (fPolarToCartY / 2.0f)));
        Rect rect = this.mSamplingRect;
        rect.set(rect.left, this.mSamplingRect.top, (int) (this.mSamplingRect.left + staticArrowWidth2), (int) (this.mSamplingRect.top + fPolarToCartY));
        this.mRegionSamplingHelper.updateSamplingRect();
    }

    @Override // com.android.systemui.plugins.NavigationEdgeBackPlugin
    public void onMotionEvent(MotionEvent event) {
        if (this.mVelocityTracker == null) {
            this.mVelocityTracker = VelocityTracker.obtain();
        }
        this.mVelocityTracker.addMovement(event);
        int actionMasked = event.getActionMasked();
        if (actionMasked == 0) {
            this.mDragSlopPassed = false;
            resetOnDown();
            this.mStartX = event.getX();
            this.mStartY = event.getY();
            setVisibility(0);
            updatePosition(event.getY());
            this.mRegionSamplingHelper.start(this.mSamplingRect);
            this.mWindowManager.updateViewLayout(this, this.mLayoutParams);
            return;
        }
        if (actionMasked == 1) {
            if (this.mTriggerBack) {
                triggerBack();
            } else {
                cancelBack();
            }
            this.mRegionSamplingHelper.stop();
            this.mVelocityTracker.recycle();
            this.mVelocityTracker = null;
            return;
        }
        if (actionMasked == 2) {
            handleMoveEvent(event);
        } else {
            if (actionMasked != 3) {
                return;
            }
            cancelBack();
            this.mRegionSamplingHelper.stop();
            this.mVelocityTracker.recycle();
            this.mVelocityTracker = null;
        }
    }

    @Override // android.view.View
    protected void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        updateArrowDirection();
        loadDimens();
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0026  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x006d  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x007c  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x007e  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x008b  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x00fb  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void onDraw(android.graphics.Canvas r20) {
        /*
            Method dump skipped, instruction units count: 269
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.phone.NavigationBarEdgePanel.onDraw(android.graphics.Canvas):void");
    }

    @Override // android.view.View
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        this.mMaxTranslation = getWidth() - this.mArrowPaddingEnd;
    }

    private void loadDimens() {
        Resources resources = getResources();
        this.mArrowPaddingEnd = resources.getDimensionPixelSize(R.dimen.navigation_edge_panel_padding);
        this.mMinArrowPosition = resources.getDimensionPixelSize(R.dimen.navigation_edge_arrow_min_y);
        this.mFingerOffset = resources.getDimensionPixelSize(R.dimen.navigation_edge_finger_offset);
    }

    private void updateArrowDirection() {
        this.mArrowsPointLeft = getLayoutDirection() == 0;
        invalidate();
    }

    public static int getThemeAttr(Context context, int attr) {
        return getThemeAttr(context, attr, 0);
    }

    public static int getThemeAttr(Context context, int attr, int defaultValue) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(new int[]{attr});
        int resourceId = typedArrayObtainStyledAttributes.getResourceId(0, defaultValue);
        typedArrayObtainStyledAttributes.recycle();
        return resourceId;
    }

    public static int getColorAttrDefaultColor(Context context, int attr) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(new int[]{attr});
        int color = typedArrayObtainStyledAttributes.getColor(0, 0);
        typedArrayObtainStyledAttributes.recycle();
        return color;
    }

    private void loadColors(Context context) {
        int themeAttr = getThemeAttr(context, R.attr.darkIconTheme);
        ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(context, getThemeAttr(context, R.attr.lightIconTheme));
        ContextThemeWrapper contextThemeWrapper2 = new ContextThemeWrapper(context, themeAttr);
        this.mArrowColorLight = getColorAttrDefaultColor(contextThemeWrapper, R.attr.singleToneColor);
        int colorAttrDefaultColor = getColorAttrDefaultColor(contextThemeWrapper2, R.attr.singleToneColor);
        this.mArrowColorDark = colorAttrDefaultColor;
        this.mProtectionColorDark = this.mArrowColorLight;
        this.mProtectionColorLight = colorAttrDefaultColor;
        updateIsDark(false);
        this.mWavePaint.setColor(R.color.edge_back_background);
        this.mPaint.setColor(-1);
    }

    private void updateIsDark(boolean animate) {
        int i = this.mIsDark ? this.mProtectionColorDark : this.mProtectionColorLight;
        this.mProtectionColor = i;
        this.mProtectionPaint.setColor(i);
        this.mArrowColor = this.mIsDark ? this.mArrowColorDark : this.mArrowColorLight;
        this.mArrowColorAnimator.cancel();
        if (!animate) {
            setCurrentArrowColor(this.mArrowColor);
        } else {
            this.mArrowStartColor = this.mCurrentArrowColor;
            this.mArrowColorAnimator.start();
        }
    }

    private void setCurrentArrowColor(int color) {
        this.mCurrentArrowColor = color;
    }

    private float getStaticArrowWidth() {
        return polarToCartX(56.0f) * this.mArrowLength;
    }

    private float polarToCartX(float angleInDegrees) {
        return (float) Math.cos(Math.toRadians(angleInDegrees));
    }

    private float polarToCartY(float angleInDegrees) {
        return (float) Math.sin(Math.toRadians(angleInDegrees));
    }

    private Path calculatePath(float x, float y) {
        if (!this.mArrowsPointLeft) {
            x = -x;
        }
        float fLerp = MathUtils.lerp(1.0f, 0.75f, this.mDisappearAmount);
        float f = x * fLerp;
        float f2 = y * fLerp;
        this.mArrowPath.reset();
        this.mArrowPath.moveTo(f, f2);
        this.mArrowPath.lineTo(0.0f, 0.0f);
        this.mArrowPath.lineTo(f, -f2);
        return this.mArrowPath;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public float getCurrentAngle() {
        return this.mCurrentAngle;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public float getCurrentTranslation() {
        return this.mCurrentTranslation;
    }

    private void triggerBack() {
        this.mBackCallback.triggerBack();
        if (this.mVelocityTracker == null) {
            this.mVelocityTracker = VelocityTracker.obtain();
        }
        this.mVelocityTracker.computeCurrentVelocity(1000);
        if (!(Math.abs(this.mVelocityTracker.getXVelocity()) < 500.0f)) {
            SystemClock.uptimeMillis();
        }
        float f = this.mAngleOffset;
        if (f > -4.0f) {
            this.mAngleOffset = Math.max(-8.0f, f - 8.0f);
            updateAngle(true);
        }
        final Runnable runnable = new Runnable() { // from class: com.android.systemui.statusbar.phone.-$$Lambda$NavigationBarEdgePanel$X9AnpuoSeR9LwoqMoGE7No5iScw
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$triggerBack$3$NavigationBarEdgePanel();
            }
        };
        if (this.mTranslationAnimation.isRunning()) {
            this.mTranslationAnimation.addEndListener(new DynamicAnimation.OnAnimationEndListener() { // from class: com.android.systemui.statusbar.phone.NavigationBarEdgePanel.6
                @Override // androidx.dynamicanimation.animation.DynamicAnimation.OnAnimationEndListener
                public void onAnimationEnd(DynamicAnimation animation, boolean canceled, float value, float velocity) {
                    animation.removeEndListener(this);
                    if (canceled) {
                        return;
                    }
                    runnable.run();
                }
            });
        } else {
            runnable.run();
        }
    }

    public /* synthetic */ void lambda$triggerBack$3$NavigationBarEdgePanel() {
        this.mAngleOffset = Math.max(0.0f, this.mAngleOffset + 8.0f);
        updateAngle(true);
        this.mTranslationAnimation.setSpring(this.mTriggerBackSpring);
        setDesiredTranslation(this.mDesiredTranslation - dp(32.0f), true);
        animate().alpha(0.0f).setDuration(0L).withEndAction(new Runnable() { // from class: com.android.systemui.statusbar.phone.-$$Lambda$NavigationBarEdgePanel$h47mMJRvKD9uumD2IK5i5SI7ECQ
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$triggerBack$2$NavigationBarEdgePanel();
            }
        });
        this.mArrowDisappearAnimation.start();
    }

    public /* synthetic */ void lambda$triggerBack$2$NavigationBarEdgePanel() {
        setVisibility(8);
    }

    private void cancelBack() {
        this.mBackCallback.cancelBack();
        if (this.mTranslationAnimation.isRunning()) {
            this.mTranslationAnimation.addEndListener(this.mSetGoneEndListener);
        } else {
            setVisibility(8);
        }
    }

    private void resetOnDown() {
        animate().cancel();
        this.mAngleAnimation.cancel();
        this.mTranslationAnimation.cancel();
        this.mVerticalTranslationAnimation.cancel();
        this.mArrowDisappearAnimation.cancel();
        this.mAngleOffset = 0.0f;
        this.mTranslationAnimation.setSpring(this.mRegularTranslationSpring);
        setTriggerBack(false, false);
        setDesiredTranslation(0.0f, false);
        setCurrentTranslation(0.0f);
        updateAngle(false);
        this.mPreviousTouchTranslation = 0.0f;
        this.mTotalTouchDelta = 0.0f;
        this.mVibrationTime = 0L;
        setDesiredVerticalTransition(0.0f, false);
    }

    private void handleMoveEvent(MotionEvent event) {
        float f;
        float staticArrowWidth;
        float x = event.getX();
        float y = event.getY();
        float fAbs = MathUtils.abs(x - this.mStartX);
        float f2 = y - this.mStartY;
        float f3 = fAbs - this.mPreviousTouchTranslation;
        if (Math.abs(f3) > 0.0f) {
            if (Math.signum(f3) == Math.signum(this.mTotalTouchDelta)) {
                this.mTotalTouchDelta += f3;
            } else {
                this.mTotalTouchDelta = f3;
            }
        }
        this.mPreviousTouchTranslation = fAbs;
        if (!this.mDragSlopPassed && fAbs > this.mSwipeThreshold) {
            this.mDragSlopPassed = true;
            this.mVibrationTime = SystemClock.uptimeMillis();
            this.mDisappearAmount = 0.0f;
            setAlpha(1.0f);
            setTriggerBack(true, true);
        }
        float f4 = this.mBaseTranslation;
        if (fAbs > f4) {
            float interpolation = RUBBER_BAND_INTERPOLATOR.getInterpolation(MathUtils.saturate((fAbs - f4) / (this.mScreenSize - f4)));
            float f5 = this.mMaxTranslation;
            float f6 = this.mBaseTranslation;
            f = f6 + (interpolation * (f5 - f6));
        } else {
            float interpolation2 = RUBBER_BAND_INTERPOLATOR_APPEAR.getInterpolation(MathUtils.saturate((f4 - fAbs) / f4));
            float f7 = this.mBaseTranslation;
            f = f7 - (interpolation2 * (f7 / 2.0f));
        }
        boolean z = this.mTriggerBack;
        if (Math.abs(this.mTotalTouchDelta) > this.mMinDeltaForSwitch) {
            z = this.mTotalTouchDelta > 0.0f;
        }
        this.mVelocityTracker.computeCurrentVelocity(1000);
        float xVelocity = this.mVelocityTracker.getXVelocity();
        float fMin = Math.min((MathUtils.mag(xVelocity, this.mVelocityTracker.getYVelocity()) / 1000.0f) * 4.0f, 4.0f) * Math.signum(xVelocity);
        this.mAngleOffset = fMin;
        boolean z2 = this.mIsLeftPanel;
        if ((z2 && this.mArrowsPointLeft) || (!z2 && !this.mArrowsPointLeft)) {
            this.mAngleOffset = fMin * (-1.0f);
        }
        setTriggerBack(Math.abs(f2) <= Math.abs(x - this.mStartX) * 2.0f ? z : false, true);
        if (!this.mTriggerBack) {
            if (this.mIsLeftPanel) {
                staticArrowWidth = getStaticArrowWidth();
                f -= staticArrowWidth;
            }
        } else {
            boolean z3 = this.mIsLeftPanel;
            if ((z3 && this.mArrowsPointLeft) || (!z3 && !this.mArrowsPointLeft)) {
                staticArrowWidth = getStaticArrowWidth();
                f -= staticArrowWidth;
            }
        }
        setDesiredTranslation(f, true);
        updateAngle(true);
        float height = (getHeight() / 2.0f) - this.mArrowLength;
        setDesiredVerticalTransition(RUBBER_BAND_INTERPOLATOR.getInterpolation(MathUtils.constrain(Math.abs(f2) / (15.0f * height), 0.0f, 1.0f)) * height * Math.signum(f2), true);
        updateSamplingRect();
    }

    private void updatePosition(float touchY) {
        this.mLayoutParams.y = (int) (Math.max(touchY - this.mFingerOffset, this.mMinArrowPosition) - (this.mLayoutParams.height / 2.0f));
        updateSamplingRect();
    }

    private void updateSamplingRect() {
        int i = this.mLayoutParams.y;
        int i2 = this.mIsLeftPanel ? this.mLeftInset : (this.mDisplaySize.x - this.mRightInset) - this.mLayoutParams.width;
        this.mSamplingRect.set(i2, i, this.mLayoutParams.width + i2, this.mLayoutParams.height + i);
        adjustSamplingRectToBoundingBox();
    }

    private void setDesiredVerticalTransition(float verticalTranslation, boolean animated) {
        if (this.mDesiredVerticalTranslation != verticalTranslation) {
            this.mDesiredVerticalTranslation = verticalTranslation;
            if (!animated) {
                setVerticalTranslation(verticalTranslation);
            } else {
                this.mVerticalTranslationAnimation.animateToFinalPosition(verticalTranslation);
            }
            invalidate();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setVerticalTranslation(float verticalTranslation) {
        this.mVerticalTranslation = verticalTranslation;
        invalidate();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public float getVerticalTranslation() {
        return this.mVerticalTranslation;
    }

    private void setDesiredTranslation(float desiredTranslation, boolean animated) {
        if (this.mDesiredTranslation != desiredTranslation) {
            this.mDesiredTranslation = desiredTranslation;
            if (!animated) {
                setCurrentTranslation(desiredTranslation);
            } else {
                this.mTranslationAnimation.animateToFinalPosition(desiredTranslation);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setCurrentTranslation(float currentTranslation) {
        this.mCurrentTranslation = currentTranslation;
        invalidate();
    }

    private void setTriggerBack(boolean triggerBack, boolean animated) {
        if (this.mTriggerBack != triggerBack) {
            this.mTriggerBack = triggerBack;
            this.mAngleAnimation.cancel();
            updateAngle(animated);
            this.mTranslationAnimation.cancel();
        }
    }

    private void updateAngle(boolean animated) {
        boolean z = this.mTriggerBack;
        float f = z ? this.mAngleOffset + 56.0f : 90.0f;
        if (f != this.mDesiredAngle) {
            if (!animated) {
                setCurrentAngle(f);
            } else {
                this.mAngleAnimation.setSpring(z ? this.mAngleAppearForce : this.mAngleDisappearForce);
                this.mAngleAnimation.animateToFinalPosition(f);
            }
            this.mDesiredAngle = f;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setCurrentAngle(float currentAngle) {
        this.mCurrentAngle = currentAngle;
        invalidate();
    }

    private float dp(float dp) {
        return this.mDensity * dp;
    }
}
