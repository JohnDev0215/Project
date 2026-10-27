package com.android.systemui.widget;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import android.view.accessibility.AccessibilityEvent;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.customview.view.AbsSavedState;
import com.android.systemui.widget.ViewDragHelper;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class SysUiDrawerLayout extends ViewGroup {
    private static final boolean ALLOW_EDGE_LOCK = false;
    static final boolean CAN_HIDE_DESCENDANTS;
    private static final boolean CHILDREN_DISALLOW_INTERCEPT = true;
    private static final int DEFAULT_SCRIM_COLOR = -1728053248;
    private static final int DRAWER_ELEVATION = 10;
    public static final int LOCK_MODE_LOCKED_CLOSED = 1;
    public static final int LOCK_MODE_LOCKED_OPEN = 2;
    public static final int LOCK_MODE_UNDEFINED = 3;
    public static final int LOCK_MODE_UNLOCKED = 0;
    private static final int MIN_DRAWER_MARGIN = 0;
    private static final int MIN_FLING_VELOCITY = 400;
    private static final int MIN_FLING_VELOCITY2 = 1000;
    private static final int PEEK_DELAY = 1600;
    private static final boolean SET_DRAWER_SHADOW_FROM_ELEVATION;
    public static final int STATE_DRAGGING = 1;
    public static final int STATE_IDLE = 0;
    public static final int STATE_SETTLING = 2;
    private static final String TAG = "SysUiDrawerLayout";
    private static final float TOUCH_SLOP_SENSITIVITY = 1.0f;
    private static final float TOUCH_SLOP_SENSITIVITY2 = 2.0f;
    boolean ignoreDrawerOpenLockMode;
    boolean isTouch;
    private final ViewDragCallback mBottomCallback;
    private final ViewDragHelper mBottomDragger;
    private final ChildAccessibilityDelegate mChildAccessibilityDelegate;
    private Rect mChildHitRect;
    private Matrix mChildInvertedMatrix;
    private boolean mChildrenCanceledTouch;
    private boolean mDisallowInterceptRequested;
    private boolean mDrawStatusBarBackground;
    private float mDrawerElevation;
    private int mDrawerState;
    private boolean mFirstLayout;
    private boolean mInLayout;
    private float mInitialMotionX;
    private float mInitialMotionY;
    private Object mLastInsets;
    private DrawerListener mListener;
    private List<DrawerListener> mListeners;
    private int mLockModeBottom;
    private int mLockModeEnd;
    private int mLockModeStart;
    private int mLockModeTop;
    private int mMinDrawerMargin;
    private final ArrayList<View> mNonDrawerViews;
    private int mScrimColor;
    private float mScrimOpacity;
    private Paint mScrimPaint;
    private Drawable mShadowBottom;
    private Drawable mShadowBottomResolved;
    private Drawable mShadowEnd;
    private Drawable mShadowStart;
    private Drawable mShadowTop;
    private Drawable mShadowTopResolved;
    private Drawable mStatusBarBackground;
    private CharSequence mTitleBottom;
    private CharSequence mTitleTop;
    private final ViewDragCallback mTopCallback;
    private final ViewDragHelper mTopDragger;
    private static final int[] THEME_ATTRS = {R.attr.colorPrimaryDark};
    static final int[] LAYOUT_ATTRS = {R.attr.layout_gravity};

    public interface DrawerListener {
        void onDrawerClosed(View drawerView);

        void onDrawerOpened(View drawerView);

        void onDrawerSlide(View drawerView, float slideOffset);

        void onDrawerStateChanged(int newState, float onScreen);
    }

    @Retention(RetentionPolicy.SOURCE)
    private @interface EdgeGravity {
    }

    @Retention(RetentionPolicy.SOURCE)
    private @interface LockMode {
    }

    public static class SimpleDrawerListener implements DrawerListener {
        @Override // com.android.systemui.widget.SysUiDrawerLayout.DrawerListener
        public void onDrawerClosed(View drawerView) {
        }

        @Override // com.android.systemui.widget.SysUiDrawerLayout.DrawerListener
        public void onDrawerOpened(View drawerView) {
        }

        @Override // com.android.systemui.widget.SysUiDrawerLayout.DrawerListener
        public void onDrawerSlide(View drawerView, float slideOffset) {
        }

        @Override // com.android.systemui.widget.SysUiDrawerLayout.DrawerListener
        public void onDrawerStateChanged(int newState, float onScreen) {
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    private @interface State {
    }

    static {
        CAN_HIDE_DESCENDANTS = Build.VERSION.SDK_INT >= 19;
        SET_DRAWER_SHADOW_FROM_ELEVATION = Build.VERSION.SDK_INT >= 21;
    }

    public SysUiDrawerLayout(Context context) {
        this(context, null);
    }

    public SysUiDrawerLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SysUiDrawerLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mChildAccessibilityDelegate = new ChildAccessibilityDelegate();
        this.mScrimColor = 0;
        this.mScrimPaint = new Paint();
        this.mFirstLayout = true;
        this.mLockModeTop = 3;
        this.mLockModeBottom = 3;
        this.mLockModeStart = 3;
        this.mLockModeEnd = 3;
        this.mShadowStart = null;
        this.mShadowEnd = null;
        this.mShadowTop = null;
        this.mShadowBottom = null;
        this.ignoreDrawerOpenLockMode = false;
        this.isTouch = true;
        setDescendantFocusability(262144);
        float f = getResources().getDisplayMetrics().density;
        this.mMinDrawerMargin = (int) ((0.0f * f) + 0.5f);
        ViewDragCallback viewDragCallback = new ViewDragCallback(48);
        this.mTopCallback = viewDragCallback;
        ViewDragCallback viewDragCallback2 = new ViewDragCallback(80);
        this.mBottomCallback = viewDragCallback2;
        ViewDragHelper viewDragHelperCreate = ViewDragHelper.create(this, 1.0f, viewDragCallback);
        this.mTopDragger = viewDragHelperCreate;
        viewDragHelperCreate.setEdgeTrackingEnabled(4);
        viewDragHelperCreate.setMinVelocity(400.0f * f);
        viewDragCallback.setDragger(viewDragHelperCreate);
        ViewDragHelper viewDragHelperCreate2 = ViewDragHelper.create(this, TOUCH_SLOP_SENSITIVITY2, viewDragCallback2);
        this.mBottomDragger = viewDragHelperCreate2;
        viewDragHelperCreate2.setEdgeTrackingEnabled(8);
        viewDragHelperCreate2.setMinVelocity(1000.0f * f);
        viewDragCallback2.setDragger(viewDragHelperCreate2);
        setEdgeSize(getContext().getResources().getDimensionPixelSize(com.android.systemui.R.dimen.dp_56));
        setFocusableInTouchMode(true);
        ViewCompat.setImportantForAccessibility(this, 1);
        ViewCompat.setAccessibilityDelegate(this, new AccessibilityDelegate());
        setMotionEventSplittingEnabled(false);
        if (ViewCompat.getFitsSystemWindows(this)) {
            if (Build.VERSION.SDK_INT >= 21) {
                setOnApplyWindowInsetsListener(new View.OnApplyWindowInsetsListener() { // from class: com.android.systemui.widget.SysUiDrawerLayout.1
                    @Override // android.view.View.OnApplyWindowInsetsListener
                    public WindowInsets onApplyWindowInsets(View view, WindowInsets insets) {
                        ((SysUiDrawerLayout) view).setChildInsets(insets, insets.getSystemWindowInsetTop() > 0);
                        return insets.consumeSystemWindowInsets();
                    }
                });
                setSystemUiVisibility(1280);
                TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(THEME_ATTRS);
                try {
                    this.mStatusBarBackground = typedArrayObtainStyledAttributes.getDrawable(0);
                } finally {
                    typedArrayObtainStyledAttributes.recycle();
                }
            } else {
                this.mStatusBarBackground = null;
            }
        }
        this.mDrawerElevation = f * 10.0f;
        this.mNonDrawerViews = new ArrayList<>();
    }

    public void setDrawerElevation(float elevation) {
        this.mDrawerElevation = elevation;
        for (int i = 0; i < getChildCount(); i++) {
            View childAt = getChildAt(i);
            if (isDrawerView(childAt)) {
                ViewCompat.setElevation(childAt, this.mDrawerElevation);
            }
        }
    }

    public float getDrawerElevation() {
        if (SET_DRAWER_SHADOW_FROM_ELEVATION) {
            return this.mDrawerElevation;
        }
        return 0.0f;
    }

    public void setChildInsets(Object insets, boolean draw) {
        this.mLastInsets = insets;
        this.mDrawStatusBarBackground = draw;
        setWillNotDraw(!draw && getBackground() == null);
        requestLayout();
    }

    public void setDrawerShadow(Drawable shadowDrawable, int gravity) {
        if (SET_DRAWER_SHADOW_FROM_ELEVATION) {
            return;
        }
        if ((gravity & GravityCompat.START) == 8388611) {
            this.mShadowStart = shadowDrawable;
        } else if ((gravity & GravityCompat.END) == 8388613) {
            this.mShadowEnd = shadowDrawable;
        } else if ((gravity & 48) == 48) {
            this.mShadowTop = shadowDrawable;
        } else if ((gravity & 80) != 80) {
            return;
        } else {
            this.mShadowBottom = shadowDrawable;
        }
        resolveShadowDrawables();
        invalidate();
    }

    public void setDrawerShadow(int resId, int gravity) {
        setDrawerShadow(ContextCompat.getDrawable(getContext(), resId), gravity);
    }

    public void setScrimColor(int color) {
        this.mScrimColor = color;
        invalidate();
    }

    @Deprecated
    public void setDrawerListener(DrawerListener listener) {
        DrawerListener drawerListener = this.mListener;
        if (drawerListener != null) {
            removeDrawerListener(drawerListener);
        }
        if (listener != null) {
            addDrawerListener(listener);
        }
        this.mListener = listener;
    }

    public void addDrawerListener(DrawerListener listener) {
        if (listener == null) {
            return;
        }
        if (this.mListeners == null) {
            this.mListeners = new ArrayList();
        }
        this.mListeners.add(listener);
    }

    public void removeDrawerListener(DrawerListener listener) {
        List<DrawerListener> list;
        if (listener == null || (list = this.mListeners) == null) {
            return;
        }
        list.remove(listener);
    }

    public void setDrawerLockMode(int lockMode) {
        setDrawerLockMode(lockMode, 48);
        setDrawerLockMode(lockMode, 80);
    }

    public void setDrawerLockMode(int lockMode, int edgeGravity) {
        View viewFindDrawerWithGravity;
        int absoluteGravity = GravityCompat.getAbsoluteGravity(edgeGravity, ViewCompat.getLayoutDirection(this));
        if (edgeGravity == 48) {
            this.mLockModeTop = lockMode;
        } else if (edgeGravity == 80) {
            this.mLockModeBottom = lockMode;
        } else if (edgeGravity == 8388611) {
            this.mLockModeStart = lockMode;
        } else if (edgeGravity == 8388613) {
            this.mLockModeEnd = lockMode;
        }
        if (lockMode != 0) {
            (absoluteGravity == 48 ? this.mTopDragger : this.mBottomDragger).cancel();
        }
        if (lockMode != 1) {
            if (lockMode == 2 && (viewFindDrawerWithGravity = findDrawerWithGravity(absoluteGravity)) != null) {
                openDrawer(viewFindDrawerWithGravity);
                return;
            }
            return;
        }
        View viewFindDrawerWithGravity2 = findDrawerWithGravity(absoluteGravity);
        if (viewFindDrawerWithGravity2 != null) {
            closeDrawer(viewFindDrawerWithGravity2);
        }
    }

    public void setEdgeSize(int edgeSize) {
        setObjField(this.mTopDragger, "mEdgeSize", Integer.valueOf(edgeSize));
        setObjField(this.mBottomDragger, "mEdgeSize", Integer.valueOf(edgeSize));
    }

    public static void setObjField(Object object, String fieldName, Object value) {
        try {
            Field declaredField = object.getClass().getDeclaredField(fieldName);
            declaredField.setAccessible(true);
            declaredField.set(object, value);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    public void setDrawerLockMode(int lockMode, View drawerView) {
        if (!isDrawerView(drawerView)) {
            throw new IllegalArgumentException("View " + drawerView + " is not a drawer with appropriate layout_gravity");
        }
        setDrawerLockMode(lockMode, ((LayoutParams) drawerView.getLayoutParams()).gravity);
    }

    public int getDrawerLockMode(int edgeGravity) {
        int layoutDirection = ViewCompat.getLayoutDirection(this);
        if (edgeGravity == 48) {
            int i = this.mLockModeTop;
            if (i != 3) {
                return i;
            }
            int i2 = layoutDirection == 0 ? this.mLockModeStart : this.mLockModeEnd;
            if (i2 != 3) {
                return i2;
            }
            return 0;
        }
        if (edgeGravity == 80) {
            int i3 = this.mLockModeBottom;
            if (i3 != 3) {
                return i3;
            }
            int i4 = layoutDirection == 0 ? this.mLockModeEnd : this.mLockModeStart;
            if (i4 != 3) {
                return i4;
            }
            return 0;
        }
        if (edgeGravity == 8388611) {
            int i5 = this.mLockModeStart;
            if (i5 != 3) {
                return i5;
            }
            int i6 = layoutDirection == 0 ? this.mLockModeTop : this.mLockModeBottom;
            if (i6 != 3) {
                return i6;
            }
            return 0;
        }
        if (edgeGravity != 8388613) {
            return 0;
        }
        int i7 = this.mLockModeEnd;
        if (i7 != 3) {
            return i7;
        }
        int i8 = layoutDirection == 0 ? this.mLockModeBottom : this.mLockModeTop;
        if (i8 != 3) {
            return i8;
        }
        return 0;
    }

    public int getDrawerLockMode(View drawerView) {
        if (!isDrawerView(drawerView)) {
            throw new IllegalArgumentException("View " + drawerView + " is not a drawer");
        }
        return getDrawerLockMode(((LayoutParams) drawerView.getLayoutParams()).gravity);
    }

    public void setDrawerTitle(int edgeGravity, CharSequence title) {
        int absoluteGravity = GravityCompat.getAbsoluteGravity(edgeGravity, ViewCompat.getLayoutDirection(this));
        if (absoluteGravity == 48) {
            this.mTitleTop = title;
        } else if (absoluteGravity == 80) {
            this.mTitleBottom = title;
        }
    }

    public CharSequence getDrawerTitle(int edgeGravity) {
        int absoluteGravity = GravityCompat.getAbsoluteGravity(edgeGravity, ViewCompat.getLayoutDirection(this));
        if (absoluteGravity == 48) {
            return this.mTitleTop;
        }
        if (absoluteGravity == 80) {
            return this.mTitleBottom;
        }
        return null;
    }

    private boolean isInBoundsOfChild(float x, float y, View child) {
        if (this.mChildHitRect == null) {
            this.mChildHitRect = new Rect();
        }
        child.getHitRect(this.mChildHitRect);
        return this.mChildHitRect.contains((int) x, (int) y);
    }

    private boolean dispatchTransformedGenericPointerEvent(MotionEvent event, View child) {
        if (!child.getMatrix().isIdentity()) {
            MotionEvent transformedMotionEvent = getTransformedMotionEvent(event, child);
            boolean zDispatchGenericMotionEvent = child.dispatchGenericMotionEvent(transformedMotionEvent);
            transformedMotionEvent.recycle();
            return zDispatchGenericMotionEvent;
        }
        float scrollX = getScrollX() - child.getLeft();
        float scrollY = getScrollY() - child.getTop();
        event.offsetLocation(scrollX, scrollY);
        boolean zDispatchGenericMotionEvent2 = child.dispatchGenericMotionEvent(event);
        event.offsetLocation(-scrollX, -scrollY);
        return zDispatchGenericMotionEvent2;
    }

    private MotionEvent getTransformedMotionEvent(MotionEvent event, View child) {
        float scrollX = getScrollX() - child.getLeft();
        float scrollY = getScrollY() - child.getTop();
        MotionEvent motionEventObtain = MotionEvent.obtain(event);
        motionEventObtain.offsetLocation(scrollX, scrollY);
        Matrix matrix = child.getMatrix();
        if (!matrix.isIdentity()) {
            if (this.mChildInvertedMatrix == null) {
                this.mChildInvertedMatrix = new Matrix();
            }
            matrix.invert(this.mChildInvertedMatrix);
            motionEventObtain.transform(this.mChildInvertedMatrix);
        }
        return motionEventObtain;
    }

    void updateDrawerState(int forGravity, int activeState, View activeDrawer) {
        int viewDragState = this.mTopDragger.getViewDragState();
        int viewDragState2 = this.mBottomDragger.getViewDragState();
        int i = 2;
        if (viewDragState == 1 || viewDragState2 == 1) {
            i = 1;
        } else if (viewDragState != 2 && viewDragState2 != 2) {
            i = 0;
        }
        if (activeDrawer != null && activeState == 0) {
            LayoutParams layoutParams = (LayoutParams) activeDrawer.getLayoutParams();
            if (layoutParams.onScreen == 0.0f) {
                dispatchOnDrawerClosed(activeDrawer);
            } else if (layoutParams.onScreen == 1.0f) {
                dispatchOnDrawerOpened(activeDrawer);
            }
        }
        float f = activeDrawer != null ? ((LayoutParams) activeDrawer.getLayoutParams()).onScreen : 0.0f;
        if (i != this.mDrawerState) {
            this.mDrawerState = i;
            List<DrawerListener> list = this.mListeners;
            if (list != null) {
                for (int size = list.size() - 1; size >= 0; size--) {
                    this.mListeners.get(size).onDrawerStateChanged(i, f);
                }
            }
        }
    }

    public boolean isIgnoreDrawerOpenLockMode() {
        return this.ignoreDrawerOpenLockMode;
    }

    public void setIgnoreDrawerOpenLockMode(boolean ignoreDrawerOpenLockMode) {
        this.ignoreDrawerOpenLockMode = ignoreDrawerOpenLockMode;
    }

    void dispatchOnDrawerClosed(View drawerView) {
        View rootView;
        LayoutParams layoutParams = (LayoutParams) drawerView.getLayoutParams();
        if ((layoutParams.openState & 1) == 1) {
            layoutParams.openState = 0;
            if (!this.ignoreDrawerOpenLockMode) {
                setDrawerLockMode(3);
            }
            List<DrawerListener> list = this.mListeners;
            if (list != null) {
                for (int size = list.size() - 1; size >= 0; size--) {
                    this.mListeners.get(size).onDrawerClosed(drawerView);
                }
            }
            updateChildrenImportantForAccessibility(drawerView, false);
            if (!hasWindowFocus() || (rootView = getRootView()) == null) {
                return;
            }
            rootView.sendAccessibilityEvent(32);
        }
    }

    void dispatchOnDrawerOpened(View drawerView) {
        LayoutParams layoutParams = (LayoutParams) drawerView.getLayoutParams();
        if ((layoutParams.openState & 1) == 0) {
            layoutParams.openState = 1;
            if (!this.ignoreDrawerOpenLockMode) {
                if (isDrawerOpen(48)) {
                    setDrawerLockMode(1, 80);
                } else {
                    setDrawerLockMode(1, 48);
                }
            }
            List<DrawerListener> list = this.mListeners;
            if (list != null) {
                for (int size = list.size() - 1; size >= 0; size--) {
                    this.mListeners.get(size).onDrawerOpened(drawerView);
                }
            }
            updateChildrenImportantForAccessibility(drawerView, true);
            if (hasWindowFocus()) {
                sendAccessibilityEvent(32);
            }
            drawerView.requestFocus();
        }
    }

    private void updateChildrenImportantForAccessibility(View drawerView, boolean isDrawerOpen) {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if ((!isDrawerOpen && !isDrawerView(childAt)) || (isDrawerOpen && childAt == drawerView)) {
                ViewCompat.setImportantForAccessibility(childAt, 1);
            } else {
                ViewCompat.setImportantForAccessibility(childAt, 4);
            }
        }
    }

    void dispatchOnDrawerSlide(View drawerView, float slideOffset) {
        List<DrawerListener> list = this.mListeners;
        if (list != null) {
            for (int size = list.size() - 1; size >= 0; size--) {
                this.mListeners.get(size).onDrawerSlide(drawerView, slideOffset);
            }
        }
    }

    void setDrawerViewOffset(View drawerView, float slideOffset) {
        LayoutParams layoutParams = (LayoutParams) drawerView.getLayoutParams();
        if (slideOffset == layoutParams.onScreen) {
            return;
        }
        layoutParams.onScreen = slideOffset;
        dispatchOnDrawerSlide(drawerView, slideOffset);
    }

    float getDrawerViewOffset(View drawerView) {
        return ((LayoutParams) drawerView.getLayoutParams()).onScreen;
    }

    int getDrawerViewAbsoluteGravity(View drawerView) {
        return GravityCompat.getAbsoluteGravity(((LayoutParams) drawerView.getLayoutParams()).gravity, ViewCompat.getLayoutDirection(this));
    }

    boolean checkDrawerViewAbsoluteGravity(View drawerView, int checkFor) {
        return (getDrawerViewAbsoluteGravity(drawerView) & checkFor) == checkFor;
    }

    View findOpenDrawer() {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if ((((LayoutParams) childAt.getLayoutParams()).openState & 1) == 1) {
                return childAt;
            }
        }
        return null;
    }

    void moveDrawerToOffset(View drawerView, float slideOffset) {
        float drawerViewOffset = getDrawerViewOffset(drawerView);
        float width = drawerView.getWidth();
        int i = ((int) (width * slideOffset)) - ((int) (drawerViewOffset * width));
        if (!checkDrawerViewAbsoluteGravity(drawerView, 48)) {
            i = -i;
        }
        drawerView.offsetLeftAndRight(i);
        setDrawerViewOffset(drawerView, slideOffset);
    }

    View findDrawerWithGravity(int gravity) {
        int absoluteGravity = GravityCompat.getAbsoluteGravity(gravity, ViewCompat.getLayoutDirection(this)) & 112;
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if ((getDrawerViewAbsoluteGravity(childAt) & 112) == absoluteGravity) {
                return childAt;
            }
        }
        return null;
    }

    static String gravityToString(int gravity) {
        return (gravity & 48) == 48 ? "TOP" : (gravity & 80) == 80 ? "BOTTOM" : Integer.toHexString(gravity);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.mFirstLayout = true;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mFirstLayout = true;
    }

    @Override // android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int mode = View.MeasureSpec.getMode(widthMeasureSpec);
        int mode2 = View.MeasureSpec.getMode(heightMeasureSpec);
        int size = View.MeasureSpec.getSize(widthMeasureSpec);
        int size2 = View.MeasureSpec.getSize(heightMeasureSpec);
        if (mode != 1073741824 || mode2 != 1073741824) {
            if (!isInEditMode()) {
                throw new IllegalArgumentException("SysUiDrawerLayout must be measured with MeasureSpec.EXACTLY.");
            }
            if (mode != Integer.MIN_VALUE && mode == 0) {
                size = 300;
            }
            if (mode2 != Integer.MIN_VALUE && mode2 == 0) {
                size2 = 300;
            }
        }
        setMeasuredDimension(size, size2);
        int i = 0;
        boolean z = this.mLastInsets != null && ViewCompat.getFitsSystemWindows(this);
        int layoutDirection = ViewCompat.getLayoutDirection(this);
        int childCount = getChildCount();
        int i2 = 0;
        boolean z2 = false;
        boolean z3 = false;
        while (i2 < childCount) {
            View childAt = getChildAt(i2);
            if (childAt.getVisibility() != 8) {
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                if (z) {
                    int absoluteGravity = GravityCompat.getAbsoluteGravity(layoutParams.gravity, layoutDirection);
                    if (ViewCompat.getFitsSystemWindows(childAt)) {
                        if (Build.VERSION.SDK_INT >= 21) {
                            WindowInsets windowInsetsReplaceSystemWindowInsets = (WindowInsets) this.mLastInsets;
                            if (absoluteGravity == 48) {
                                windowInsetsReplaceSystemWindowInsets = windowInsetsReplaceSystemWindowInsets.replaceSystemWindowInsets(windowInsetsReplaceSystemWindowInsets.getSystemWindowInsetLeft(), windowInsetsReplaceSystemWindowInsets.getSystemWindowInsetTop(), windowInsetsReplaceSystemWindowInsets.getSystemWindowInsetRight(), i);
                            } else if (absoluteGravity == 80) {
                                windowInsetsReplaceSystemWindowInsets = windowInsetsReplaceSystemWindowInsets.replaceSystemWindowInsets(windowInsetsReplaceSystemWindowInsets.getSystemWindowInsetLeft(), i, windowInsetsReplaceSystemWindowInsets.getSystemWindowInsetRight(), windowInsetsReplaceSystemWindowInsets.getSystemWindowInsetBottom());
                            }
                            childAt.dispatchApplyWindowInsets(windowInsetsReplaceSystemWindowInsets);
                        }
                    } else if (Build.VERSION.SDK_INT >= 21) {
                        WindowInsets windowInsetsReplaceSystemWindowInsets2 = (WindowInsets) this.mLastInsets;
                        if (absoluteGravity == 48) {
                            windowInsetsReplaceSystemWindowInsets2 = windowInsetsReplaceSystemWindowInsets2.replaceSystemWindowInsets(windowInsetsReplaceSystemWindowInsets2.getSystemWindowInsetLeft(), windowInsetsReplaceSystemWindowInsets2.getSystemWindowInsetTop(), windowInsetsReplaceSystemWindowInsets2.getSystemWindowInsetRight(), i);
                        } else if (absoluteGravity == 80) {
                            windowInsetsReplaceSystemWindowInsets2 = windowInsetsReplaceSystemWindowInsets2.replaceSystemWindowInsets(windowInsetsReplaceSystemWindowInsets2.getSystemWindowInsetLeft(), i, windowInsetsReplaceSystemWindowInsets2.getSystemWindowInsetRight(), windowInsetsReplaceSystemWindowInsets2.getSystemWindowInsetBottom());
                        }
                        layoutParams.leftMargin = windowInsetsReplaceSystemWindowInsets2.getSystemWindowInsetLeft();
                        layoutParams.topMargin = windowInsetsReplaceSystemWindowInsets2.getSystemWindowInsetTop();
                        layoutParams.rightMargin = windowInsetsReplaceSystemWindowInsets2.getSystemWindowInsetRight();
                        layoutParams.bottomMargin = windowInsetsReplaceSystemWindowInsets2.getSystemWindowInsetBottom();
                    }
                }
                if (isContentView(childAt)) {
                    childAt.measure(View.MeasureSpec.makeMeasureSpec((size - layoutParams.leftMargin) - layoutParams.rightMargin, 1073741824), View.MeasureSpec.makeMeasureSpec((size2 - layoutParams.topMargin) - layoutParams.bottomMargin, 1073741824));
                } else if (isDrawerView(childAt)) {
                    if (SET_DRAWER_SHADOW_FROM_ELEVATION) {
                        float elevation = ViewCompat.getElevation(childAt);
                        float f = this.mDrawerElevation;
                        if (elevation != f) {
                            ViewCompat.setElevation(childAt, f);
                        }
                    }
                    int drawerViewAbsoluteGravity = getDrawerViewAbsoluteGravity(childAt) & 112;
                    int i3 = drawerViewAbsoluteGravity == 48 ? 1 : i;
                    if ((i3 != 0 && z2) || (i3 == 0 && z3)) {
                        throw new IllegalStateException("Child drawer has absolute gravity " + gravityToString(drawerViewAbsoluteGravity) + " but this " + TAG + " already has a drawer view along that edge");
                    }
                    if (i3 != 0) {
                        z2 = true;
                    } else {
                        z3 = true;
                    }
                    childAt.measure(getChildMeasureSpec(widthMeasureSpec, layoutParams.leftMargin + layoutParams.rightMargin, layoutParams.width), getChildMeasureSpec(heightMeasureSpec, this.mMinDrawerMargin + layoutParams.topMargin + layoutParams.bottomMargin, layoutParams.height));
                } else {
                    throw new IllegalStateException("Child " + childAt + " at index " + i2 + " does not have a valid layout_gravity - must be Gravity.TOP, Gravity.BOTTOM or Gravity.NO_GRAVITY");
                }
            }
            i2++;
            i = 0;
        }
    }

    private void resolveShadowDrawables() {
        if (SET_DRAWER_SHADOW_FROM_ELEVATION) {
            return;
        }
        this.mShadowTopResolved = resolveLeftShadow();
        this.mShadowBottomResolved = resolveRightShadow();
    }

    private Drawable resolveLeftShadow() {
        int layoutDirection = ViewCompat.getLayoutDirection(this);
        if (layoutDirection == 0) {
            Drawable drawable = this.mShadowStart;
            if (drawable != null) {
                mirror(drawable, layoutDirection);
                return this.mShadowStart;
            }
        } else {
            Drawable drawable2 = this.mShadowEnd;
            if (drawable2 != null) {
                mirror(drawable2, layoutDirection);
                return this.mShadowEnd;
            }
        }
        return this.mShadowTop;
    }

    private Drawable resolveRightShadow() {
        int layoutDirection = ViewCompat.getLayoutDirection(this);
        if (layoutDirection == 0) {
            Drawable drawable = this.mShadowEnd;
            if (drawable != null) {
                mirror(drawable, layoutDirection);
                return this.mShadowEnd;
            }
        } else {
            Drawable drawable2 = this.mShadowStart;
            if (drawable2 != null) {
                mirror(drawable2, layoutDirection);
                return this.mShadowStart;
            }
        }
        return this.mShadowBottom;
    }

    private boolean mirror(Drawable drawable, int layoutDirection) {
        if (drawable == null || !DrawableCompat.isAutoMirrored(drawable)) {
            return false;
        }
        DrawableCompat.setLayoutDirection(drawable, layoutDirection);
        return true;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        float f;
        int i;
        boolean z = true;
        this.mInLayout = true;
        int i2 = b - t;
        int childCount = getChildCount();
        int i3 = 0;
        while (i3 < childCount) {
            View childAt = getChildAt(i3);
            if (childAt.getVisibility() != 8) {
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                if (isContentView(childAt)) {
                    childAt.layout(layoutParams.leftMargin, layoutParams.topMargin, layoutParams.leftMargin + childAt.getMeasuredWidth(), layoutParams.topMargin + childAt.getMeasuredHeight());
                } else {
                    int measuredWidth = childAt.getMeasuredWidth();
                    int measuredHeight = childAt.getMeasuredHeight();
                    if (checkDrawerViewAbsoluteGravity(childAt, 48)) {
                        float f2 = measuredHeight;
                        i = (-measuredHeight) + ((int) (layoutParams.onScreen * f2));
                        f = (measuredHeight + i) / f2;
                    } else {
                        float f3 = measuredHeight;
                        f = (i2 - r11) / f3;
                        i = i2 - ((int) (layoutParams.onScreen * f3));
                    }
                    boolean z2 = f != layoutParams.onScreen ? z : false;
                    int i4 = layoutParams.gravity & 112;
                    if (i4 == 16) {
                        int i5 = r - l;
                        int i6 = (i5 - measuredWidth) / 2;
                        if (i6 < layoutParams.topMargin) {
                            i6 = layoutParams.topMargin;
                        } else if (i6 + measuredWidth > i5 - layoutParams.rightMargin) {
                            i6 = (i5 - layoutParams.rightMargin) - measuredWidth;
                        }
                        childAt.layout(i6, i, measuredWidth + i6, measuredHeight + i);
                    } else if (i4 != 80) {
                        childAt.layout(layoutParams.leftMargin, i, layoutParams.leftMargin + measuredWidth, measuredHeight + i);
                    } else {
                        int i7 = r - l;
                        childAt.layout((i7 - layoutParams.rightMargin) - childAt.getMeasuredWidth(), i, i7 - layoutParams.rightMargin, measuredHeight + i);
                    }
                    if (z2) {
                        setDrawerViewOffset(childAt, f);
                    }
                    int i8 = layoutParams.onScreen > 0.0f ? 0 : 4;
                    if (childAt.getVisibility() != i8) {
                        childAt.setVisibility(i8);
                    }
                }
            }
            i3++;
            z = true;
        }
        this.mInLayout = false;
        this.mFirstLayout = false;
    }

    @Override // android.view.View, android.view.ViewParent
    public void requestLayout() {
        if (this.mInLayout) {
            return;
        }
        super.requestLayout();
    }

    @Override // android.view.View
    public void computeScroll() {
        int childCount = getChildCount();
        float fMax = 0.0f;
        for (int i = 0; i < childCount; i++) {
            fMax = Math.max(fMax, ((LayoutParams) getChildAt(i).getLayoutParams()).onScreen);
        }
        this.mScrimOpacity = fMax;
        boolean zContinueSettling = this.mTopDragger.continueSettling(true);
        boolean zContinueSettling2 = this.mBottomDragger.continueSettling(true);
        if (zContinueSettling || zContinueSettling2) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    private static boolean hasOpaqueBackground(View v) {
        Drawable background = v.getBackground();
        return background != null && background.getOpacity() == -1;
    }

    public void setStatusBarBackground(Drawable bg) {
        this.mStatusBarBackground = bg;
        invalidate();
    }

    public Drawable getStatusBarBackgroundDrawable() {
        return this.mStatusBarBackground;
    }

    public void setStatusBarBackground(int resId) {
        this.mStatusBarBackground = resId != 0 ? ContextCompat.getDrawable(getContext(), resId) : null;
        invalidate();
    }

    public void setStatusBarBackgroundColor(int color) {
        this.mStatusBarBackground = new ColorDrawable(color);
        invalidate();
    }

    @Override // android.view.View
    public void onRtlPropertiesChanged(int layoutDirection) {
        resolveShadowDrawables();
    }

    @Override // android.view.View
    public void onDraw(Canvas c) {
        Object obj;
        super.onDraw(c);
        if (!this.mDrawStatusBarBackground || this.mStatusBarBackground == null) {
            return;
        }
        int systemWindowInsetTop = (Build.VERSION.SDK_INT < 21 || (obj = this.mLastInsets) == null) ? 0 : ((WindowInsets) obj).getSystemWindowInsetTop();
        if (systemWindowInsetTop > 0) {
            this.mStatusBarBackground.setBounds(0, 0, getWidth(), systemWindowInsetTop);
            this.mStatusBarBackground.draw(c);
        }
    }

    @Override // android.view.ViewGroup
    protected boolean drawChild(Canvas canvas, View child, long drawingTime) {
        int height = getHeight();
        boolean zIsContentView = isContentView(child);
        int width = getWidth();
        int iSave = canvas.save();
        int i = 0;
        if (zIsContentView) {
            int childCount = getChildCount();
            int i2 = 0;
            for (int i3 = 0; i3 < childCount; i3++) {
                View childAt = getChildAt(i3);
                if (childAt != child && childAt.getVisibility() == 0 && hasOpaqueBackground(childAt) && isDrawerView(childAt) && childAt.getHeight() >= height) {
                    if (checkDrawerViewAbsoluteGravity(childAt, 48)) {
                        int right = childAt.getRight();
                        if (right > i2) {
                            i2 = right;
                        }
                    } else {
                        int left = childAt.getLeft();
                        if (left < width) {
                            width = left;
                        }
                    }
                }
            }
            canvas.clipRect(i2, 0, width, getHeight());
            i = i2;
        }
        boolean zDrawChild = super.drawChild(canvas, child, drawingTime);
        canvas.restoreToCount(iSave);
        float f = this.mScrimOpacity;
        if (f > 0.0f && zIsContentView) {
            this.mScrimPaint.setColor((this.mScrimColor & ViewCompat.MEASURED_SIZE_MASK) | (((int) ((((-268435456) & r2) >>> 24) * f)) << 24));
            canvas.drawRect(i, 0.0f, width, getHeight(), this.mScrimPaint);
        } else if (this.mShadowTopResolved != null && checkDrawerViewAbsoluteGravity(child, 48)) {
            int intrinsicWidth = this.mShadowTopResolved.getIntrinsicWidth();
            int right2 = child.getRight();
            float fMax = Math.max(0.0f, Math.min(right2 / this.mTopDragger.getEdgeSize(), 1.0f));
            this.mShadowTopResolved.setBounds(right2, child.getTop(), intrinsicWidth + right2, child.getBottom());
            this.mShadowTopResolved.setAlpha((int) (fMax * 255.0f));
            this.mShadowTopResolved.draw(canvas);
        } else if (this.mShadowBottomResolved != null && checkDrawerViewAbsoluteGravity(child, 80)) {
            int intrinsicWidth2 = this.mShadowBottomResolved.getIntrinsicWidth();
            int left2 = child.getLeft();
            float fMax2 = Math.max(0.0f, Math.min((getWidth() - left2) / this.mBottomDragger.getEdgeSize(), 1.0f));
            this.mShadowBottomResolved.setBounds(left2 - intrinsicWidth2, child.getTop(), left2, child.getBottom());
            this.mShadowBottomResolved.setAlpha((int) (fMax2 * 255.0f));
            this.mShadowBottomResolved.draw(canvas);
        }
        return zDrawChild;
    }

    boolean isContentView(View child) {
        return ((LayoutParams) child.getLayoutParams()).gravity == 0;
    }

    boolean isDrawerView(View child) {
        int absoluteGravity = GravityCompat.getAbsoluteGravity(((LayoutParams) child.getLayoutParams()).gravity, ViewCompat.getLayoutDirection(child));
        return ((absoluteGravity & 48) == 0 && (absoluteGravity & 80) == 0) ? false : true;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0033  */
    @Override // android.view.ViewGroup
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean onInterceptTouchEvent(android.view.MotionEvent r7) {
        /*
            r6 = this;
            r0 = 1
            r6.isTouch = r0
            int r1 = r7.getActionMasked()
            com.android.systemui.widget.ViewDragHelper r2 = r6.mTopDragger
            boolean r2 = r2.shouldInterceptTouchEvent(r7)
            com.android.systemui.widget.ViewDragHelper r3 = r6.mBottomDragger
            boolean r3 = r3.shouldInterceptTouchEvent(r7)
            r2 = r2 | r3
            r3 = 0
            if (r1 == 0) goto L3c
            if (r1 == r0) goto L33
            r7 = 2
            r4 = 3
            if (r1 == r7) goto L20
            if (r1 == r4) goto L33
            goto L3a
        L20:
            com.android.systemui.widget.ViewDragHelper r7 = r6.mTopDragger
            boolean r7 = r7.checkTouchSlop(r4)
            if (r7 == 0) goto L3a
            com.android.systemui.widget.SysUiDrawerLayout$ViewDragCallback r7 = r6.mTopCallback
            r7.removeCallbacks()
            com.android.systemui.widget.SysUiDrawerLayout$ViewDragCallback r7 = r6.mBottomCallback
            r7.removeCallbacks()
            goto L3a
        L33:
            r6.closeDrawers(r0)
            r6.mDisallowInterceptRequested = r3
            r6.mChildrenCanceledTouch = r3
        L3a:
            r7 = r3
            goto L66
        L3c:
            float r1 = r7.getX()
            float r7 = r7.getY()
            r6.mInitialMotionX = r1
            r6.mInitialMotionY = r7
            float r4 = r6.mScrimOpacity
            r5 = 0
            int r4 = (r4 > r5 ? 1 : (r4 == r5 ? 0 : -1))
            if (r4 <= 0) goto L61
            com.android.systemui.widget.ViewDragHelper r4 = r6.mTopDragger
            int r1 = (int) r1
            int r7 = (int) r7
            android.view.View r7 = r4.findTopChildUnder(r1, r7)
            if (r7 == 0) goto L61
            boolean r7 = r6.isContentView(r7)
            if (r7 == 0) goto L61
            r7 = r0
            goto L62
        L61:
            r7 = r3
        L62:
            r6.mDisallowInterceptRequested = r3
            r6.mChildrenCanceledTouch = r3
        L66:
            if (r2 != 0) goto L76
            if (r7 != 0) goto L76
            boolean r7 = r6.hasPeekingDrawer()
            if (r7 != 0) goto L76
            boolean r7 = r6.mChildrenCanceledTouch
            if (r7 == 0) goto L75
            goto L76
        L75:
            r0 = r3
        L76:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.widget.SysUiDrawerLayout.onInterceptTouchEvent(android.view.MotionEvent):boolean");
    }

    @Override // android.view.View
    public boolean dispatchGenericMotionEvent(MotionEvent event) {
        if ((event.getSource() & 2) == 0 || event.getAction() == 10 || this.mScrimOpacity <= 0.0f) {
            return super.dispatchGenericMotionEvent(event);
        }
        int childCount = getChildCount();
        if (childCount == 0) {
            return false;
        }
        float x = event.getX();
        float y = event.getY();
        for (int i = childCount - 1; i >= 0; i--) {
            View childAt = getChildAt(i);
            if (isInBoundsOfChild(x, y, childAt) && !isContentView(childAt) && dispatchTransformedGenericPointerEvent(event, childAt)) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x005f  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean onTouchEvent(android.view.MotionEvent r7) {
        /*
            r6 = this;
            r0 = 1
            r1 = 0
            com.android.systemui.widget.ViewDragHelper r2 = r6.mTopDragger     // Catch: java.lang.Exception -> L79
            r2.processTouchEvent(r7)     // Catch: java.lang.Exception -> L79
            com.android.systemui.widget.ViewDragHelper r2 = r6.mBottomDragger     // Catch: java.lang.Exception -> L79
            r2.processTouchEvent(r7)     // Catch: java.lang.Exception -> L79
            int r2 = r7.getAction()     // Catch: java.lang.Exception -> L79
            r2 = r2 & 255(0xff, float:3.57E-43)
            if (r2 == 0) goto L66
            if (r2 == r0) goto L22
            r7 = 3
            if (r2 == r7) goto L1a
            goto L7e
        L1a:
            r6.closeDrawers(r0)     // Catch: java.lang.Exception -> L77
            r6.mDisallowInterceptRequested = r1     // Catch: java.lang.Exception -> L77
            r6.mChildrenCanceledTouch = r1     // Catch: java.lang.Exception -> L77
            goto L7e
        L22:
            float r2 = r7.getX()     // Catch: java.lang.Exception -> L77
            float r7 = r7.getY()     // Catch: java.lang.Exception -> L77
            com.android.systemui.widget.ViewDragHelper r3 = r6.mTopDragger     // Catch: java.lang.Exception -> L77
            int r4 = (int) r2     // Catch: java.lang.Exception -> L77
            int r5 = (int) r7     // Catch: java.lang.Exception -> L77
            android.view.View r3 = r3.findTopChildUnder(r4, r5)     // Catch: java.lang.Exception -> L77
            if (r3 == 0) goto L5f
            boolean r3 = r6.isContentView(r3)     // Catch: java.lang.Exception -> L77
            if (r3 == 0) goto L5f
            float r3 = r6.mInitialMotionX     // Catch: java.lang.Exception -> L77
            float r2 = r2 - r3
            float r3 = r6.mInitialMotionY     // Catch: java.lang.Exception -> L77
            float r7 = r7 - r3
            com.android.systemui.widget.ViewDragHelper r3 = r6.mTopDragger     // Catch: java.lang.Exception -> L77
            int r3 = r3.getTouchSlop()     // Catch: java.lang.Exception -> L77
            float r2 = r2 * r2
            float r7 = r7 * r7
            float r2 = r2 + r7
            int r3 = r3 * r3
            float r7 = (float) r3     // Catch: java.lang.Exception -> L77
            int r7 = (r2 > r7 ? 1 : (r2 == r7 ? 0 : -1))
            if (r7 >= 0) goto L5f
            android.view.View r7 = r6.findOpenDrawer()     // Catch: java.lang.Exception -> L77
            if (r7 == 0) goto L5f
            int r7 = r6.getDrawerLockMode(r7)     // Catch: java.lang.Exception -> L77
            r2 = 2
            if (r7 != r2) goto L5d
            goto L5f
        L5d:
            r7 = r1
            goto L60
        L5f:
            r7 = r0
        L60:
            r6.closeDrawers(r7)     // Catch: java.lang.Exception -> L77
            r6.mDisallowInterceptRequested = r1     // Catch: java.lang.Exception -> L77
            goto L7e
        L66:
            float r2 = r7.getX()     // Catch: java.lang.Exception -> L77
            float r7 = r7.getY()     // Catch: java.lang.Exception -> L77
            r6.mInitialMotionX = r2     // Catch: java.lang.Exception -> L77
            r6.mInitialMotionY = r7     // Catch: java.lang.Exception -> L77
            r6.mDisallowInterceptRequested = r1     // Catch: java.lang.Exception -> L77
            r6.mChildrenCanceledTouch = r1     // Catch: java.lang.Exception -> L77
            goto L7e
        L77:
            r7 = move-exception
            goto L7b
        L79:
            r7 = move-exception
            r0 = r1
        L7b:
            r7.printStackTrace()
        L7e:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.widget.SysUiDrawerLayout.onTouchEvent(android.view.MotionEvent):boolean");
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void requestDisallowInterceptTouchEvent(boolean disallowIntercept) {
        super.requestDisallowInterceptTouchEvent(disallowIntercept);
        this.mDisallowInterceptRequested = disallowIntercept;
        if (disallowIntercept) {
            closeDrawers(true);
        }
    }

    public void closeDrawers() {
        closeDrawers(false);
    }

    void closeDrawers(boolean peekingOnly) {
        boolean zSmoothSlideViewTo;
        int childCount = getChildCount();
        boolean z = false;
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
            if (isDrawerView(childAt) && (!peekingOnly || layoutParams.isPeeking)) {
                int height = childAt.getHeight();
                if (checkDrawerViewAbsoluteGravity(childAt, 48)) {
                    zSmoothSlideViewTo = this.mTopDragger.smoothSlideViewTo(childAt, childAt.getLeft(), -height);
                } else {
                    zSmoothSlideViewTo = this.mBottomDragger.smoothSlideViewTo(childAt, childAt.getLeft(), getHeight());
                }
                z |= zSmoothSlideViewTo;
                layoutParams.isPeeking = false;
            }
        }
        this.mTopCallback.removeCallbacks();
        this.mBottomCallback.removeCallbacks();
        if (z) {
            invalidate();
        }
    }

    public void closeDrawersForce() {
        ViewDragCallback viewDragCallback = this.mTopCallback;
        if (viewDragCallback != null) {
            viewDragCallback.removeCallbacks();
        }
        ViewDragCallback viewDragCallback2 = this.mBottomCallback;
        if (viewDragCallback2 != null) {
            viewDragCallback2.removeCallbacks();
        }
        invalidate();
    }

    public void openDrawer(View drawerView) {
        openDrawer(drawerView, true);
    }

    public void openDrawer(View drawerView, boolean animate) {
        if (!isDrawerView(drawerView)) {
            throw new IllegalArgumentException("View " + drawerView + " is not a sliding drawer");
        }
        LayoutParams layoutParams = (LayoutParams) drawerView.getLayoutParams();
        if (this.mFirstLayout) {
            layoutParams.onScreen = 1.0f;
            layoutParams.openState = 1;
            updateChildrenImportantForAccessibility(drawerView, true);
        } else if (animate) {
            layoutParams.openState |= 2;
            if (checkDrawerViewAbsoluteGravity(drawerView, 48)) {
                this.mTopDragger.smoothSlideViewTo(drawerView, drawerView.getLeft(), 0);
            } else {
                this.mBottomDragger.smoothSlideViewTo(drawerView, drawerView.getLeft(), getHeight() - drawerView.getHeight());
            }
        } else {
            moveDrawerToOffset(drawerView, 1.0f);
            updateDrawerState(layoutParams.gravity, 0, drawerView);
            drawerView.setVisibility(0);
        }
        invalidate();
    }

    public void openDrawer(int gravity) {
        openDrawer(gravity, true);
    }

    public void openDrawer(int gravity, boolean animate) {
        View viewFindDrawerWithGravity = findDrawerWithGravity(gravity);
        if (viewFindDrawerWithGravity == null) {
            throw new IllegalArgumentException("No drawer view found with gravity " + gravityToString(gravity));
        }
        openDrawer(viewFindDrawerWithGravity, animate);
    }

    public void closeDrawer(View drawerView) {
        closeDrawer(drawerView, true);
    }

    public void closeDrawer(View drawerView, boolean animate) {
        if (!isDrawerView(drawerView)) {
            throw new IllegalArgumentException("View " + drawerView + " is not a sliding drawer");
        }
        LayoutParams layoutParams = (LayoutParams) drawerView.getLayoutParams();
        if (this.mFirstLayout) {
            layoutParams.onScreen = 0.0f;
            layoutParams.openState = 0;
        } else if (animate) {
            layoutParams.openState |= 4;
            if (checkDrawerViewAbsoluteGravity(drawerView, 48)) {
                this.mTopDragger.smoothSlideViewTo(drawerView, drawerView.getLeft(), -drawerView.getHeight());
            } else {
                this.mBottomDragger.smoothSlideViewTo(drawerView, drawerView.getLeft(), getHeight());
            }
        } else {
            moveDrawerToOffset(drawerView, 0.0f);
            updateDrawerState(layoutParams.gravity, 0, drawerView);
            drawerView.setVisibility(4);
        }
        invalidate();
    }

    public void closeDrawer(int gravity) {
        closeDrawer(gravity, true);
    }

    public void closeDrawer(int gravity, boolean animate) {
        View viewFindDrawerWithGravity = findDrawerWithGravity(gravity);
        if (viewFindDrawerWithGravity == null) {
            throw new IllegalArgumentException("No drawer view found with gravity " + gravityToString(gravity));
        }
        closeDrawer(viewFindDrawerWithGravity, animate);
    }

    public boolean isDrawerOpen(View drawer) {
        if (isDrawerView(drawer)) {
            return (((LayoutParams) drawer.getLayoutParams()).openState & 1) == 1;
        }
        throw new IllegalArgumentException("View " + drawer + " is not a drawer");
    }

    public float getOnScreen(View drawer) {
        return ((LayoutParams) drawer.getLayoutParams()).onScreen;
    }

    public boolean isDrawerOpen(int drawerGravity) {
        View viewFindDrawerWithGravity = findDrawerWithGravity(drawerGravity);
        if (viewFindDrawerWithGravity != null) {
            return isDrawerOpen(viewFindDrawerWithGravity);
        }
        return false;
    }

    public boolean isDrawerVisible(View drawer) {
        if (isDrawerView(drawer)) {
            return ((LayoutParams) drawer.getLayoutParams()).onScreen > 0.0f;
        }
        throw new IllegalArgumentException("View " + drawer + " is not a drawer");
    }

    public boolean isDrawerVisible(int drawerGravity) {
        View viewFindDrawerWithGravity = findDrawerWithGravity(drawerGravity);
        if (viewFindDrawerWithGravity != null) {
            return isDrawerVisible(viewFindDrawerWithGravity);
        }
        return false;
    }

    private boolean hasPeekingDrawer() {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            if (((LayoutParams) getChildAt(i).getLayoutParams()).isPeeking) {
                return true;
            }
        }
        return false;
    }

    @Override // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-1, -1);
    }

    @Override // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams p) {
        if (p instanceof LayoutParams) {
            return new LayoutParams((LayoutParams) p);
        }
        if (p instanceof ViewGroup.MarginLayoutParams) {
            return new LayoutParams((ViewGroup.MarginLayoutParams) p);
        }
        return new LayoutParams(p);
    }

    @Override // android.view.ViewGroup
    protected boolean checkLayoutParams(ViewGroup.LayoutParams p) {
        return (p instanceof LayoutParams) && super.checkLayoutParams(p);
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new LayoutParams(getContext(), attrs);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void addFocusables(ArrayList<View> views, int direction, int focusableMode) {
        if (getDescendantFocusability() == 393216) {
            return;
        }
        int childCount = getChildCount();
        boolean z = false;
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (isDrawerView(childAt)) {
                if (isDrawerOpen(childAt)) {
                    childAt.addFocusables(views, direction, focusableMode);
                    z = true;
                }
            } else {
                this.mNonDrawerViews.add(childAt);
            }
        }
        if (!z) {
            int size = this.mNonDrawerViews.size();
            for (int i2 = 0; i2 < size; i2++) {
                View view = this.mNonDrawerViews.get(i2);
                if (view.getVisibility() == 0) {
                    view.addFocusables(views, direction, focusableMode);
                }
            }
        }
        this.mNonDrawerViews.clear();
    }

    private boolean hasVisibleDrawer() {
        return findVisibleDrawer() != null;
    }

    View findVisibleDrawer() {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (isDrawerView(childAt) && isDrawerVisible(childAt)) {
                return childAt;
            }
        }
        return null;
    }

    void cancelChildViewTouch() {
        if (this.mChildrenCanceledTouch) {
            return;
        }
        long jUptimeMillis = SystemClock.uptimeMillis();
        MotionEvent motionEventObtain = MotionEvent.obtain(jUptimeMillis, jUptimeMillis, 3, 0.0f, 0.0f, 0);
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            getChildAt(i).dispatchTouchEvent(motionEventObtain);
        }
        motionEventObtain.recycle();
        this.mChildrenCanceledTouch = true;
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == 4 && hasVisibleDrawer()) {
            event.startTracking();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == 4) {
            View viewFindVisibleDrawer = findVisibleDrawer();
            if (viewFindVisibleDrawer != null && getDrawerLockMode(viewFindVisibleDrawer) == 0) {
                closeDrawers();
            }
            return viewFindVisibleDrawer != null;
        }
        return super.onKeyUp(keyCode, event);
    }

    @Override // android.view.View
    protected void onRestoreInstanceState(Parcelable state) {
        View viewFindDrawerWithGravity;
        if (!(state instanceof SavedState)) {
            super.onRestoreInstanceState(state);
            return;
        }
        SavedState savedState = (SavedState) state;
        super.onRestoreInstanceState(savedState.getSuperState());
        if (savedState.openDrawerGravity != 0 && (viewFindDrawerWithGravity = findDrawerWithGravity(savedState.openDrawerGravity)) != null) {
            openDrawer(viewFindDrawerWithGravity);
        }
        if (savedState.lockModeTop != 3) {
            setDrawerLockMode(savedState.lockModeTop, 48);
        }
        if (savedState.lockModeBottom != 3) {
            setDrawerLockMode(savedState.lockModeBottom, 80);
        }
        if (savedState.lockModeStart != 3) {
            setDrawerLockMode(savedState.lockModeStart, GravityCompat.START);
        }
        if (savedState.lockModeEnd != 3) {
            setDrawerLockMode(savedState.lockModeEnd, GravityCompat.END);
        }
    }

    @Override // android.view.View
    protected Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            LayoutParams layoutParams = (LayoutParams) getChildAt(i).getLayoutParams();
            boolean z = layoutParams.openState == 1;
            boolean z2 = layoutParams.openState == 2;
            if (z || z2) {
                savedState.openDrawerGravity = layoutParams.gravity;
                break;
            }
        }
        savedState.lockModeTop = this.mLockModeTop;
        savedState.lockModeBottom = this.mLockModeBottom;
        savedState.lockModeStart = this.mLockModeStart;
        savedState.lockModeEnd = this.mLockModeEnd;
        return savedState;
    }

    @Override // android.view.ViewGroup
    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        super.addView(child, index, params);
        if (findOpenDrawer() != null || isDrawerView(child)) {
            ViewCompat.setImportantForAccessibility(child, 4);
        } else {
            ViewCompat.setImportantForAccessibility(child, 1);
        }
        if (CAN_HIDE_DESCENDANTS) {
            return;
        }
        ViewCompat.setAccessibilityDelegate(child, this.mChildAccessibilityDelegate);
    }

    static boolean includeChildForAccessibility(View child) {
        return (ViewCompat.getImportantForAccessibility(child) == 4 || ViewCompat.getImportantForAccessibility(child) == 2) ? false : true;
    }

    protected static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.ClassLoaderCreator<SavedState>() { // from class: com.android.systemui.widget.SysUiDrawerLayout.SavedState.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.ClassLoaderCreator
            public SavedState createFromParcel(Parcel in, ClassLoader loader) {
                return new SavedState(in, loader);
            }

            @Override // android.os.Parcelable.Creator
            public SavedState createFromParcel(Parcel in) {
                return new SavedState(in, null);
            }

            @Override // android.os.Parcelable.Creator
            public SavedState[] newArray(int size) {
                return new SavedState[size];
            }
        };
        int lockModeBottom;
        int lockModeEnd;
        int lockModeStart;
        int lockModeTop;
        int openDrawerGravity;

        public SavedState(Parcel in, ClassLoader loader) {
            super(in, loader);
            this.openDrawerGravity = 0;
            this.openDrawerGravity = in.readInt();
            this.lockModeTop = in.readInt();
            this.lockModeBottom = in.readInt();
            this.lockModeStart = in.readInt();
            this.lockModeEnd = in.readInt();
        }

        public SavedState(Parcelable superState) {
            super(superState);
            this.openDrawerGravity = 0;
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel dest, int flags) {
            super.writeToParcel(dest, flags);
            dest.writeInt(this.openDrawerGravity);
            dest.writeInt(this.lockModeTop);
            dest.writeInt(this.lockModeBottom);
            dest.writeInt(this.lockModeStart);
            dest.writeInt(this.lockModeEnd);
        }
    }

    private class ViewDragCallback extends ViewDragHelper.Callback {
        private final int mAbsGravity;
        private ViewDragHelper mDragger;
        private final Runnable mPeekRunnable = new Runnable() { // from class: com.android.systemui.widget.SysUiDrawerLayout.ViewDragCallback.1
            @Override // java.lang.Runnable
            public void run() {
            }
        };

        @Override // com.android.systemui.widget.ViewDragHelper.Callback
        public boolean onEdgeLock(int edgeFlags) {
            return false;
        }

        ViewDragCallback(int gravity) {
            this.mAbsGravity = gravity;
        }

        public void setDragger(ViewDragHelper dragger) {
            this.mDragger = dragger;
        }

        public void removeCallbacks() {
            SysUiDrawerLayout.this.removeCallbacks(this.mPeekRunnable);
        }

        @Override // com.android.systemui.widget.ViewDragHelper.Callback
        public boolean tryCaptureView(View child, int pointerId) {
            return SysUiDrawerLayout.this.isDrawerView(child) && SysUiDrawerLayout.this.checkDrawerViewAbsoluteGravity(child, this.mAbsGravity) && SysUiDrawerLayout.this.getDrawerLockMode(child) == 0;
        }

        @Override // com.android.systemui.widget.ViewDragHelper.Callback
        public void onViewDragStateChanged(int state) {
            SysUiDrawerLayout.this.updateDrawerState(this.mAbsGravity, state, this.mDragger.getCapturedView());
        }

        @Override // com.android.systemui.widget.ViewDragHelper.Callback
        public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
            float height = (SysUiDrawerLayout.this.checkDrawerViewAbsoluteGravity(changedView, 48) ? top + r2 : SysUiDrawerLayout.this.getHeight() - top) / changedView.getHeight();
            SysUiDrawerLayout.this.setDrawerViewOffset(changedView, height);
            changedView.setVisibility(height == 0.0f ? 4 : 0);
            SysUiDrawerLayout.this.invalidate();
        }

        @Override // com.android.systemui.widget.ViewDragHelper.Callback
        public void onViewCaptured(View capturedChild, int activePointerId) {
            ((LayoutParams) capturedChild.getLayoutParams()).isPeeking = false;
            closeOtherDrawer();
        }

        private void closeOtherDrawer() {
            View viewFindDrawerWithGravity = SysUiDrawerLayout.this.findDrawerWithGravity(this.mAbsGravity == 48 ? 80 : 48);
            if (viewFindDrawerWithGravity != null) {
                SysUiDrawerLayout.this.closeDrawer(viewFindDrawerWithGravity);
            }
        }

        @Override // com.android.systemui.widget.ViewDragHelper.Callback
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            int i;
            if (releasedChild == null) {
                return;
            }
            float drawerViewOffset = SysUiDrawerLayout.this.getDrawerViewOffset(releasedChild);
            int height = releasedChild.getHeight();
            if (SysUiDrawerLayout.this.checkDrawerViewAbsoluteGravity(releasedChild, 48)) {
                i = (yvel > 0.0f || (yvel == 0.0f && drawerViewOffset > 0.5f)) ? 0 : -height;
            } else {
                int height2 = SysUiDrawerLayout.this.getHeight();
                if (yvel < 0.0f || (yvel == 0.0f && drawerViewOffset > 0.5f)) {
                    height2 -= height;
                }
                i = height2;
            }
            this.mDragger.settleCapturedViewAt(releasedChild.getLeft(), i);
            SysUiDrawerLayout.this.invalidate();
        }

        @Override // com.android.systemui.widget.ViewDragHelper.Callback
        public void onEdgeTouched(int edgeFlags, int pointerId) {
            SysUiDrawerLayout.this.postDelayed(this.mPeekRunnable, 1600L);
        }

        void peekDrawer() {
            View viewFindDrawerWithGravity;
            int height;
            int edgeSize = this.mDragger.getEdgeSize();
            boolean z = this.mAbsGravity == 48;
            if (z) {
                viewFindDrawerWithGravity = SysUiDrawerLayout.this.findDrawerWithGravity(48);
                height = (viewFindDrawerWithGravity != null ? -viewFindDrawerWithGravity.getHeight() : 0) + edgeSize;
            } else {
                viewFindDrawerWithGravity = SysUiDrawerLayout.this.findDrawerWithGravity(80);
                height = SysUiDrawerLayout.this.getHeight() - edgeSize;
            }
            if (viewFindDrawerWithGravity != null) {
                if (((!z || viewFindDrawerWithGravity.getTop() >= height) && (z || viewFindDrawerWithGravity.getTop() <= height)) || SysUiDrawerLayout.this.getDrawerLockMode(viewFindDrawerWithGravity) != 0) {
                    return;
                }
                LayoutParams layoutParams = (LayoutParams) viewFindDrawerWithGravity.getLayoutParams();
                this.mDragger.smoothSlideViewTo(viewFindDrawerWithGravity, viewFindDrawerWithGravity.getLeft(), height);
                layoutParams.isPeeking = true;
                SysUiDrawerLayout.this.invalidate();
                closeOtherDrawer();
                SysUiDrawerLayout.this.cancelChildViewTouch();
            }
        }

        @Override // com.android.systemui.widget.ViewDragHelper.Callback
        public void onEdgeDragStarted(int edgeFlags, int pointerId) {
            View viewFindDrawerWithGravity;
            if ((edgeFlags & 4) == 4) {
                viewFindDrawerWithGravity = SysUiDrawerLayout.this.findDrawerWithGravity(48);
            } else {
                viewFindDrawerWithGravity = SysUiDrawerLayout.this.findDrawerWithGravity(80);
            }
            if (viewFindDrawerWithGravity == null || SysUiDrawerLayout.this.getDrawerLockMode(viewFindDrawerWithGravity) != 0) {
                return;
            }
            this.mDragger.captureChildView(viewFindDrawerWithGravity, pointerId);
        }

        @Override // com.android.systemui.widget.ViewDragHelper.Callback
        public int getViewHorizontalDragRange(View child) {
            return super.getViewHorizontalDragRange(child);
        }

        @Override // com.android.systemui.widget.ViewDragHelper.Callback
        public int getViewVerticalDragRange(View child) {
            if (SysUiDrawerLayout.this.isDrawerView(child)) {
                return child.getHeight();
            }
            return 0;
        }

        @Override // com.android.systemui.widget.ViewDragHelper.Callback
        public int clampViewPositionHorizontal(View child, int left, int dx) {
            return child.getLeft();
        }

        @Override // com.android.systemui.widget.ViewDragHelper.Callback
        public int clampViewPositionVertical(View child, int top, int dy) {
            if (SysUiDrawerLayout.this.checkDrawerViewAbsoluteGravity(child, 48)) {
                return Math.max(-child.getHeight(), Math.min(top, 0));
            }
            int height = SysUiDrawerLayout.this.getHeight();
            return Math.max(height - child.getHeight(), Math.min(top, height));
        }
    }

    public static class LayoutParams extends ViewGroup.MarginLayoutParams {
        private static final int FLAG_IS_CLOSING = 4;
        private static final int FLAG_IS_OPENED = 1;
        private static final int FLAG_IS_OPENING = 2;
        public int gravity;
        boolean isPeeking;
        float onScreen;
        int openState;

        public LayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
            this.gravity = 0;
            TypedArray typedArrayObtainStyledAttributes = c.obtainStyledAttributes(attrs, SysUiDrawerLayout.LAYOUT_ATTRS);
            this.gravity = typedArrayObtainStyledAttributes.getInt(0, 0);
            typedArrayObtainStyledAttributes.recycle();
        }

        public LayoutParams(int width, int height) {
            super(width, height);
            this.gravity = 0;
        }

        public LayoutParams(int width, int height, int gravity) {
            this(width, height);
            this.gravity = gravity;
        }

        public LayoutParams(LayoutParams source) {
            super((ViewGroup.MarginLayoutParams) source);
            this.gravity = 0;
            this.gravity = source.gravity;
        }

        public LayoutParams(ViewGroup.LayoutParams source) {
            super(source);
            this.gravity = 0;
        }

        public LayoutParams(ViewGroup.MarginLayoutParams source) {
            super(source);
            this.gravity = 0;
        }
    }

    class AccessibilityDelegate extends AccessibilityDelegateCompat {
        private final Rect mTmpRect = new Rect();

        AccessibilityDelegate() {
        }

        @Override // androidx.core.view.AccessibilityDelegateCompat
        public void onInitializeAccessibilityNodeInfo(View host, AccessibilityNodeInfoCompat info) {
            if (SysUiDrawerLayout.CAN_HIDE_DESCENDANTS) {
                super.onInitializeAccessibilityNodeInfo(host, info);
            } else {
                AccessibilityNodeInfoCompat accessibilityNodeInfoCompatObtain = AccessibilityNodeInfoCompat.obtain(info);
                super.onInitializeAccessibilityNodeInfo(host, accessibilityNodeInfoCompatObtain);
                info.setSource(host);
                Object parentForAccessibility = ViewCompat.getParentForAccessibility(host);
                if (parentForAccessibility instanceof View) {
                    info.setParent((View) parentForAccessibility);
                }
                copyNodeInfoNoChildren(info, accessibilityNodeInfoCompatObtain);
                accessibilityNodeInfoCompatObtain.recycle();
                addChildrenForAccessibility(info, (ViewGroup) host);
            }
            info.setClassName(SysUiDrawerLayout.class.getName());
            info.setFocusable(false);
            info.setFocused(false);
            info.removeAction(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_FOCUS);
            info.removeAction(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_CLEAR_FOCUS);
        }

        @Override // androidx.core.view.AccessibilityDelegateCompat
        public void onInitializeAccessibilityEvent(View host, AccessibilityEvent event) {
            super.onInitializeAccessibilityEvent(host, event);
            event.setClassName(SysUiDrawerLayout.class.getName());
        }

        @Override // androidx.core.view.AccessibilityDelegateCompat
        public boolean dispatchPopulateAccessibilityEvent(View host, AccessibilityEvent event) {
            if (event.getEventType() == 32) {
                List<CharSequence> text = event.getText();
                View viewFindVisibleDrawer = SysUiDrawerLayout.this.findVisibleDrawer();
                if (viewFindVisibleDrawer == null) {
                    return true;
                }
                CharSequence drawerTitle = SysUiDrawerLayout.this.getDrawerTitle(SysUiDrawerLayout.this.getDrawerViewAbsoluteGravity(viewFindVisibleDrawer));
                if (drawerTitle == null) {
                    return true;
                }
                text.add(drawerTitle);
                return true;
            }
            return super.dispatchPopulateAccessibilityEvent(host, event);
        }

        @Override // androidx.core.view.AccessibilityDelegateCompat
        public boolean onRequestSendAccessibilityEvent(ViewGroup host, View child, AccessibilityEvent event) {
            if (SysUiDrawerLayout.CAN_HIDE_DESCENDANTS || SysUiDrawerLayout.includeChildForAccessibility(child)) {
                return super.onRequestSendAccessibilityEvent(host, child, event);
            }
            return false;
        }

        private void addChildrenForAccessibility(AccessibilityNodeInfoCompat info, ViewGroup v) {
            int childCount = v.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = v.getChildAt(i);
                if (SysUiDrawerLayout.includeChildForAccessibility(childAt)) {
                    info.addChild(childAt);
                }
            }
        }

        private void copyNodeInfoNoChildren(AccessibilityNodeInfoCompat dest, AccessibilityNodeInfoCompat src) {
            Rect rect = this.mTmpRect;
            src.getBoundsInParent(rect);
            dest.setBoundsInParent(rect);
            src.getBoundsInScreen(rect);
            dest.setBoundsInScreen(rect);
            dest.setVisibleToUser(src.isVisibleToUser());
            dest.setPackageName(src.getPackageName());
            dest.setClassName(src.getClassName());
            dest.setContentDescription(src.getContentDescription());
            dest.setEnabled(src.isEnabled());
            dest.setClickable(src.isClickable());
            dest.setFocusable(src.isFocusable());
            dest.setFocused(src.isFocused());
            dest.setAccessibilityFocused(src.isAccessibilityFocused());
            dest.setSelected(src.isSelected());
            dest.setLongClickable(src.isLongClickable());
            dest.addAction(src.getActions());
        }
    }

    static final class ChildAccessibilityDelegate extends AccessibilityDelegateCompat {
        ChildAccessibilityDelegate() {
        }

        @Override // androidx.core.view.AccessibilityDelegateCompat
        public void onInitializeAccessibilityNodeInfo(View child, AccessibilityNodeInfoCompat info) {
            super.onInitializeAccessibilityNodeInfo(child, info);
            if (SysUiDrawerLayout.includeChildForAccessibility(child)) {
                return;
            }
            info.setParent(null);
        }
    }
}
