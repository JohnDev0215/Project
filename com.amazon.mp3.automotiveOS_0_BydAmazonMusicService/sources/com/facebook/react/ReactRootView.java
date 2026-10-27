package com.facebook.react;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.DisplayCutout;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowInsets;
import android.view.WindowInsets$Type;
import android.view.WindowManager;
import android.widget.FrameLayout;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import com.facebook.common.logging.FLog;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.CatalystInstance;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactMarker;
import com.facebook.react.bridge.ReactMarkerConstants;
import com.facebook.react.bridge.ReactSoftExceptionLogger;
import com.facebook.react.bridge.UIManager;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.config.ReactFeatureFlags;
import com.facebook.react.modules.appregistry.AppRegistry;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.facebook.react.modules.deviceinfo.DeviceInfoModule;
import com.facebook.react.uimanager.DisplayMetricsHolder;
import com.facebook.react.uimanager.IllegalViewOperationException;
import com.facebook.react.uimanager.JSPointerDispatcher;
import com.facebook.react.uimanager.JSTouchDispatcher;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.ReactRoot;
import com.facebook.react.uimanager.ReactRootViewTagGenerator;
import com.facebook.react.uimanager.RootView;
import com.facebook.react.uimanager.RootViewUtil;
import com.facebook.react.uimanager.UIManagerHelper;
import com.facebook.react.uimanager.events.EventDispatcher;
import com.facebook.systrace.Systrace;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes6.dex */
public class ReactRootView extends FrameLayout implements RootView, ReactRoot {
    private final ReactAndroidHWInputDeviceHelper mAndroidHWInputDeviceHelper;

    @Nullable
    private Bundle mAppProperties;

    @Nullable
    private CustomGlobalLayoutListener mCustomGlobalLayoutListener;
    private int mHeightMeasureSpec;

    @Nullable
    private String mInitialUITemplate;
    private boolean mIsAttachedToInstance;

    @Nullable
    private String mJSModuleName;

    @Nullable
    private JSPointerDispatcher mJSPointerDispatcher;

    @Nullable
    private JSTouchDispatcher mJSTouchDispatcher;
    private int mLastHeight;
    private int mLastOffsetX;
    private int mLastOffsetY;
    private int mLastWidth;

    @Nullable
    private ReactInstanceManager mReactInstanceManager;

    @Nullable
    private ReactRootViewEventListener mRootViewEventListener;
    private int mRootViewTag;
    private boolean mShouldLogContentAppeared;
    private final AtomicInteger mState;
    private int mUIManagerType;
    private boolean mWasMeasured;
    private int mWidthMeasureSpec;

    public interface ReactRootViewEventListener {
        void onAttachedToReactInstance(ReactRootView reactRootView);
    }

    @Override // com.facebook.react.uimanager.ReactRoot
    public ViewGroup getRootViewGroup() {
        return this;
    }

    public boolean shouldDispatchJSTouchEvent(MotionEvent motionEvent) {
        return true;
    }

    public ReactRootView(Context context) {
        super(context);
        this.mRootViewTag = 0;
        this.mAndroidHWInputDeviceHelper = new ReactAndroidHWInputDeviceHelper(this);
        this.mWasMeasured = false;
        this.mWidthMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
        this.mHeightMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
        this.mLastWidth = 0;
        this.mLastHeight = 0;
        this.mLastOffsetX = Integer.MIN_VALUE;
        this.mLastOffsetY = Integer.MIN_VALUE;
        this.mUIManagerType = 1;
        this.mState = new AtomicInteger(0);
        init();
    }

    private void init() {
        setRootViewTag(ReactRootViewTagGenerator.getNextRootViewTag());
        setClipChildren(false);
    }

    @Override // android.widget.FrameLayout, android.view.View
    protected void onMeasure(int i, int i2) {
        int iMax;
        int iMax2;
        Systrace.beginSection(0L, "ReactRootView.onMeasure");
        ReactMarker.logMarker(ReactMarkerConstants.ROOT_VIEW_ON_MEASURE_START);
        try {
            boolean z = (i == this.mWidthMeasureSpec && i2 == this.mHeightMeasureSpec) ? false : true;
            this.mWidthMeasureSpec = i;
            this.mHeightMeasureSpec = i2;
            int mode = View.MeasureSpec.getMode(i);
            if (mode == Integer.MIN_VALUE || mode == 0) {
                iMax = 0;
                for (int i3 = 0; i3 < getChildCount(); i3++) {
                    View childAt = getChildAt(i3);
                    iMax = Math.max(iMax, childAt.getLeft() + childAt.getMeasuredWidth() + childAt.getPaddingLeft() + childAt.getPaddingRight());
                }
            } else {
                iMax = View.MeasureSpec.getSize(i);
            }
            int mode2 = View.MeasureSpec.getMode(i2);
            if (mode2 == Integer.MIN_VALUE || mode2 == 0) {
                iMax2 = 0;
                for (int i4 = 0; i4 < getChildCount(); i4++) {
                    View childAt2 = getChildAt(i4);
                    iMax2 = Math.max(iMax2, childAt2.getTop() + childAt2.getMeasuredHeight() + childAt2.getPaddingTop() + childAt2.getPaddingBottom());
                }
            } else {
                iMax2 = View.MeasureSpec.getSize(i2);
            }
            setMeasuredDimension(iMax, iMax2);
            this.mWasMeasured = true;
            if (this.mReactInstanceManager != null && !this.mIsAttachedToInstance) {
                attachToReactInstanceManager();
            } else if (z || this.mLastWidth != iMax || this.mLastHeight != iMax2) {
                updateRootLayoutSpecs(true, this.mWidthMeasureSpec, this.mHeightMeasureSpec);
            }
            this.mLastWidth = iMax;
            this.mLastHeight = iMax2;
        } finally {
            ReactMarker.logMarker(ReactMarkerConstants.ROOT_VIEW_ON_MEASURE_END);
            Systrace.endSection(0L);
        }
    }

    @Override // com.facebook.react.uimanager.RootView
    public void onChildStartedNativeGesture(MotionEvent motionEvent) {
        onChildStartedNativeGesture(null, motionEvent);
    }

    @Override // com.facebook.react.uimanager.RootView
    public void onChildStartedNativeGesture(View view, MotionEvent motionEvent) {
        UIManager uIManager;
        JSPointerDispatcher jSPointerDispatcher;
        if (isDispatcherReady() && (uIManager = UIManagerHelper.getUIManager(this.mReactInstanceManager.getCurrentReactContext(), getUIManagerType())) != null) {
            EventDispatcher eventDispatcher = (EventDispatcher) uIManager.getEventDispatcher();
            this.mJSTouchDispatcher.onChildStartedNativeGesture(motionEvent, eventDispatcher);
            if (view == null || (jSPointerDispatcher = this.mJSPointerDispatcher) == null) {
                return;
            }
            jSPointerDispatcher.onChildStartedNativeGesture(view, motionEvent, eventDispatcher);
        }
    }

    @Override // com.facebook.react.uimanager.RootView
    public void onChildEndedNativeGesture(View view, MotionEvent motionEvent) {
        UIManager uIManager;
        if (isDispatcherReady() && (uIManager = UIManagerHelper.getUIManager(this.mReactInstanceManager.getCurrentReactContext(), getUIManagerType())) != null) {
            this.mJSTouchDispatcher.onChildEndedNativeGesture(motionEvent, (EventDispatcher) uIManager.getEventDispatcher());
            JSPointerDispatcher jSPointerDispatcher = this.mJSPointerDispatcher;
            if (jSPointerDispatcher != null) {
                jSPointerDispatcher.onChildEndedNativeGesture();
            }
        }
    }

    private boolean isDispatcherReady() {
        ReactInstanceManager reactInstanceManager = this.mReactInstanceManager;
        if (reactInstanceManager == null || !this.mIsAttachedToInstance || reactInstanceManager.getCurrentReactContext() == null) {
            FLog.w("ReactRootView", "Unable to dispatch touch to JS as the catalyst instance has not been attached");
            return false;
        }
        if (this.mJSTouchDispatcher == null) {
            FLog.w("ReactRootView", "Unable to dispatch touch to JS before the dispatcher is available");
            return false;
        }
        if (!ReactFeatureFlags.dispatchPointerEvents || this.mJSPointerDispatcher != null) {
            return true;
        }
        FLog.w("ReactRootView", "Unable to dispatch pointer events to JS before the dispatcher is available");
        return false;
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (shouldDispatchJSTouchEvent(motionEvent)) {
            dispatchJSTouchEvent(motionEvent);
        }
        dispatchJSPointerEvent(motionEvent);
        return super.onInterceptTouchEvent(motionEvent);
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptHoverEvent(MotionEvent motionEvent) {
        dispatchJSPointerEvent(motionEvent);
        return super.onInterceptHoverEvent(motionEvent);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (shouldDispatchJSTouchEvent(motionEvent)) {
            dispatchJSTouchEvent(motionEvent);
        }
        dispatchJSPointerEvent(motionEvent);
        super.onTouchEvent(motionEvent);
        return true;
    }

    @Override // android.view.View
    public boolean onHoverEvent(MotionEvent motionEvent) {
        dispatchJSPointerEvent(motionEvent);
        return super.onHoverEvent(motionEvent);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchDraw(Canvas canvas) {
        try {
            super.dispatchDraw(canvas);
        } catch (StackOverflowError e) {
            handleException(e);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        ReactInstanceManager reactInstanceManager = this.mReactInstanceManager;
        if (reactInstanceManager == null || !this.mIsAttachedToInstance || reactInstanceManager.getCurrentReactContext() == null) {
            FLog.w("ReactRootView", "Unable to handle key event as the catalyst instance has not been attached");
            return super.dispatchKeyEvent(keyEvent);
        }
        this.mAndroidHWInputDeviceHelper.handleKeyEvent(keyEvent);
        return super.dispatchKeyEvent(keyEvent);
    }

    @Override // android.view.View
    protected void onFocusChanged(boolean z, int i, Rect rect) {
        ReactInstanceManager reactInstanceManager = this.mReactInstanceManager;
        if (reactInstanceManager == null || !this.mIsAttachedToInstance || reactInstanceManager.getCurrentReactContext() == null) {
            FLog.w("ReactRootView", "Unable to handle focus changed event as the catalyst instance has not been attached");
            super.onFocusChanged(z, i, rect);
        } else {
            this.mAndroidHWInputDeviceHelper.clearFocus();
            super.onFocusChanged(z, i, rect);
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void requestChildFocus(View view, View view2) {
        ReactInstanceManager reactInstanceManager = this.mReactInstanceManager;
        if (reactInstanceManager == null || !this.mIsAttachedToInstance || reactInstanceManager.getCurrentReactContext() == null) {
            FLog.w("ReactRootView", "Unable to handle child focus changed event as the catalyst instance has not been attached");
            super.requestChildFocus(view, view2);
        } else {
            this.mAndroidHWInputDeviceHelper.onFocusChanged(view2);
            super.requestChildFocus(view, view2);
        }
    }

    protected void dispatchJSPointerEvent(MotionEvent motionEvent) {
        ReactInstanceManager reactInstanceManager = this.mReactInstanceManager;
        if (reactInstanceManager == null || !this.mIsAttachedToInstance || reactInstanceManager.getCurrentReactContext() == null) {
            FLog.w("ReactRootView", "Unable to dispatch touch to JS as the catalyst instance has not been attached");
            return;
        }
        if (this.mJSPointerDispatcher == null) {
            if (ReactFeatureFlags.dispatchPointerEvents) {
                FLog.w("ReactRootView", "Unable to dispatch pointer events to JS before the dispatcher is available");
            }
        } else {
            UIManager uIManager = UIManagerHelper.getUIManager(this.mReactInstanceManager.getCurrentReactContext(), getUIManagerType());
            if (uIManager != null) {
                this.mJSPointerDispatcher.handleMotionEvent(motionEvent, (EventDispatcher) uIManager.getEventDispatcher());
            }
        }
    }

    protected void dispatchJSTouchEvent(MotionEvent motionEvent) {
        ReactInstanceManager reactInstanceManager = this.mReactInstanceManager;
        if (reactInstanceManager == null || !this.mIsAttachedToInstance || reactInstanceManager.getCurrentReactContext() == null) {
            FLog.w("ReactRootView", "Unable to dispatch touch to JS as the catalyst instance has not been attached");
            return;
        }
        if (this.mJSTouchDispatcher == null) {
            FLog.w("ReactRootView", "Unable to dispatch touch to JS before the dispatcher is available");
            return;
        }
        UIManager uIManager = UIManagerHelper.getUIManager(this.mReactInstanceManager.getCurrentReactContext(), getUIManagerType());
        if (uIManager != null) {
            this.mJSTouchDispatcher.handleTouchEvent(motionEvent, (EventDispatcher) uIManager.getEventDispatcher());
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void requestDisallowInterceptTouchEvent(boolean z) {
        if (getParent() != null) {
            getParent().requestDisallowInterceptTouchEvent(z);
        }
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        if (this.mWasMeasured && isFabric()) {
            updateRootLayoutSpecs(false, this.mWidthMeasureSpec, this.mHeightMeasureSpec);
        }
    }

    private boolean isFabric() {
        return getUIManagerType() == 2;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.mIsAttachedToInstance) {
            removeOnGlobalLayoutListener();
            getViewTreeObserver().addOnGlobalLayoutListener(getCustomGlobalLayoutListener());
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.mIsAttachedToInstance) {
            removeOnGlobalLayoutListener();
        }
    }

    private void removeOnGlobalLayoutListener() {
        getViewTreeObserver().removeOnGlobalLayoutListener(getCustomGlobalLayoutListener());
    }

    @Override // android.view.ViewGroup
    public void onViewAdded(View view) {
        super.onViewAdded(view);
        if (this.mShouldLogContentAppeared) {
            this.mShouldLogContentAppeared = false;
            String str = this.mJSModuleName;
            if (str != null) {
                ReactMarker.logMarker(ReactMarkerConstants.CONTENT_APPEARED, str, this.mRootViewTag);
            }
        }
    }

    public void startReactApplication(ReactInstanceManager reactInstanceManager, String str, @Nullable Bundle bundle) {
        startReactApplication(reactInstanceManager, str, bundle, null);
    }

    public void startReactApplication(ReactInstanceManager reactInstanceManager, String str, @Nullable Bundle bundle, @Nullable String str2) {
        Systrace.beginSection(0L, "startReactApplication");
        try {
            UiThreadUtil.assertOnUiThread();
            Assertions.assertCondition(this.mReactInstanceManager == null, "This root view has already been attached to a catalyst instance manager");
            this.mReactInstanceManager = reactInstanceManager;
            this.mJSModuleName = str;
            this.mAppProperties = bundle;
            this.mInitialUITemplate = str2;
            reactInstanceManager.createReactContextInBackground();
            if (ReactFeatureFlags.enableEagerRootViewAttachment) {
                if (!this.mWasMeasured) {
                    setSurfaceConstraintsToScreenSize();
                }
                attachToReactInstanceManager();
            }
        } finally {
            Systrace.endSection(0L);
        }
    }

    private void setSurfaceConstraintsToScreenSize() {
        DisplayMetrics displayMetrics = getContext().getResources().getDisplayMetrics();
        this.mWidthMeasureSpec = View.MeasureSpec.makeMeasureSpec(displayMetrics.widthPixels, Integer.MIN_VALUE);
        this.mHeightMeasureSpec = View.MeasureSpec.makeMeasureSpec(displayMetrics.heightPixels, Integer.MIN_VALUE);
    }

    @Override // com.facebook.react.uimanager.ReactRoot
    public int getWidthMeasureSpec() {
        return this.mWidthMeasureSpec;
    }

    @Override // com.facebook.react.uimanager.ReactRoot
    public int getHeightMeasureSpec() {
        return this.mHeightMeasureSpec;
    }

    @Override // com.facebook.react.uimanager.ReactRoot
    public void setShouldLogContentAppeared(boolean z) {
        this.mShouldLogContentAppeared = z;
    }

    @Override // com.facebook.react.uimanager.ReactRoot
    @Nullable
    public String getSurfaceID() {
        Bundle appProperties = getAppProperties();
        if (appProperties != null) {
            return appProperties.getString("surfaceID");
        }
        return null;
    }

    @Override // com.facebook.react.uimanager.ReactRoot
    public AtomicInteger getState() {
        return this.mState;
    }

    private void updateRootLayoutSpecs(boolean z, int i, int i2) {
        UIManager uIManager;
        int i3;
        ReactMarker.logMarker(ReactMarkerConstants.ROOT_VIEW_UPDATE_LAYOUT_SPECS_START);
        if (this.mReactInstanceManager == null) {
            ReactMarker.logMarker(ReactMarkerConstants.ROOT_VIEW_UPDATE_LAYOUT_SPECS_END);
            FLog.w("ReactRootView", "Unable to update root layout specs for uninitialized ReactInstanceManager");
            return;
        }
        boolean zIsFabric = isFabric();
        if (zIsFabric && !isRootViewTagSet()) {
            ReactMarker.logMarker(ReactMarkerConstants.ROOT_VIEW_UPDATE_LAYOUT_SPECS_END);
            FLog.e("ReactRootView", "Unable to update root layout specs for ReactRootView: no rootViewTag set yet");
            return;
        }
        ReactContext currentReactContext = this.mReactInstanceManager.getCurrentReactContext();
        if (currentReactContext != null && (uIManager = UIManagerHelper.getUIManager(currentReactContext, getUIManagerType())) != null) {
            int i4 = 0;
            if (zIsFabric) {
                Point viewportOffset = RootViewUtil.getViewportOffset(this);
                i4 = viewportOffset.x;
                i3 = viewportOffset.y;
            } else {
                i3 = 0;
            }
            if (z || i4 != this.mLastOffsetX || i3 != this.mLastOffsetY) {
                uIManager.updateRootLayoutSpecs(getRootViewTag(), i, i2, i4, i3);
            }
            this.mLastOffsetX = i4;
            this.mLastOffsetY = i3;
        }
        ReactMarker.logMarker(ReactMarkerConstants.ROOT_VIEW_UPDATE_LAYOUT_SPECS_END);
    }

    public void unmountReactApplication() {
        ReactContext currentReactContext;
        UIManager uIManager;
        UiThreadUtil.assertOnUiThread();
        ReactInstanceManager reactInstanceManager = this.mReactInstanceManager;
        if (reactInstanceManager != null && (currentReactContext = reactInstanceManager.getCurrentReactContext()) != null && isFabric() && (uIManager = UIManagerHelper.getUIManager(currentReactContext, getUIManagerType())) != null) {
            int id = getId();
            setId(-1);
            removeAllViews();
            if (id == -1) {
                ReactSoftExceptionLogger.logSoftException("ReactRootView", new RuntimeException("unmountReactApplication called on ReactRootView with invalid id"));
            } else {
                uIManager.stopSurface(id);
            }
        }
        ReactInstanceManager reactInstanceManager2 = this.mReactInstanceManager;
        if (reactInstanceManager2 != null && this.mIsAttachedToInstance) {
            reactInstanceManager2.detachRootView(this);
            this.mIsAttachedToInstance = false;
        }
        this.mReactInstanceManager = null;
        this.mShouldLogContentAppeared = false;
    }

    @Override // com.facebook.react.uimanager.ReactRoot
    public void onStage(int i) {
        if (i != 101) {
            return;
        }
        onAttachedToReactInstance();
    }

    public void onAttachedToReactInstance() {
        this.mJSTouchDispatcher = new JSTouchDispatcher(this);
        if (ReactFeatureFlags.dispatchPointerEvents) {
            this.mJSPointerDispatcher = new JSPointerDispatcher(this);
        }
        ReactRootViewEventListener reactRootViewEventListener = this.mRootViewEventListener;
        if (reactRootViewEventListener != null) {
            reactRootViewEventListener.onAttachedToReactInstance(this);
        }
    }

    public void setEventListener(@Nullable ReactRootViewEventListener reactRootViewEventListener) {
        this.mRootViewEventListener = reactRootViewEventListener;
    }

    @Override // com.facebook.react.uimanager.ReactRoot
    public String getJSModuleName() {
        return (String) Assertions.assertNotNull(this.mJSModuleName);
    }

    @Override // com.facebook.react.uimanager.ReactRoot
    @Nullable
    public Bundle getAppProperties() {
        return this.mAppProperties;
    }

    @Override // com.facebook.react.uimanager.ReactRoot
    @Nullable
    public String getInitialUITemplate() {
        return this.mInitialUITemplate;
    }

    public void setAppProperties(@Nullable Bundle bundle) {
        UiThreadUtil.assertOnUiThread();
        this.mAppProperties = bundle;
        if (isRootViewTagSet()) {
            runApplication();
        }
    }

    @Override // com.facebook.react.uimanager.ReactRoot
    public void runApplication() {
        Systrace.beginSection(0L, "ReactRootView.runApplication");
        try {
            ReactInstanceManager reactInstanceManager = this.mReactInstanceManager;
            if (reactInstanceManager != null && this.mIsAttachedToInstance) {
                ReactContext currentReactContext = reactInstanceManager.getCurrentReactContext();
                if (currentReactContext == null) {
                    return;
                }
                CatalystInstance catalystInstance = currentReactContext.getCatalystInstance();
                String jSModuleName = getJSModuleName();
                if (this.mWasMeasured) {
                    updateRootLayoutSpecs(true, this.mWidthMeasureSpec, this.mHeightMeasureSpec);
                }
                WritableNativeMap writableNativeMap = new WritableNativeMap();
                writableNativeMap.putDouble("rootTag", getRootViewTag());
                Bundle appProperties = getAppProperties();
                if (appProperties != null) {
                    writableNativeMap.putMap("initialProps", Arguments.fromBundle(appProperties));
                }
                this.mShouldLogContentAppeared = true;
                ((AppRegistry) catalystInstance.getJSModule(AppRegistry.class)).runApplication(jSModuleName, writableNativeMap);
            }
        } finally {
            Systrace.endSection(0L);
        }
    }

    private CustomGlobalLayoutListener getCustomGlobalLayoutListener() {
        if (this.mCustomGlobalLayoutListener == null) {
            this.mCustomGlobalLayoutListener = new CustomGlobalLayoutListener();
        }
        return this.mCustomGlobalLayoutListener;
    }

    private void attachToReactInstanceManager() {
        Systrace.beginSection(0L, "attachToReactInstanceManager");
        ReactMarker.logMarker(ReactMarkerConstants.ROOT_VIEW_ATTACH_TO_REACT_INSTANCE_MANAGER_START);
        if (getId() != -1) {
            ReactSoftExceptionLogger.logSoftException("ReactRootView", new IllegalViewOperationException("Trying to attach a ReactRootView with an explicit id already set to [" + getId() + "]. React Native uses the id field to track react tags and will overwrite this field. If that is fine, explicitly overwrite the id field to View.NO_ID."));
        }
        try {
            if (this.mIsAttachedToInstance) {
                return;
            }
            this.mIsAttachedToInstance = true;
            ((ReactInstanceManager) Assertions.assertNotNull(this.mReactInstanceManager)).attachRootView(this);
            getViewTreeObserver().addOnGlobalLayoutListener(getCustomGlobalLayoutListener());
        } finally {
            ReactMarker.logMarker(ReactMarkerConstants.ROOT_VIEW_ATTACH_TO_REACT_INSTANCE_MANAGER_END);
            Systrace.endSection(0L);
        }
    }

    protected void finalize() throws Throwable {
        super.finalize();
        Assertions.assertCondition(!this.mIsAttachedToInstance, "The application this ReactRootView was rendering was not unmounted before the ReactRootView was garbage collected. This usually means that your application is leaking large amounts of memory. To solve this, make sure to call ReactRootView#unmountReactApplication in the onDestroy() of your hosting Activity or in the onDestroyView() of your hosting Fragment.");
    }

    @Override // com.facebook.react.uimanager.ReactRoot
    public int getRootViewTag() {
        return this.mRootViewTag;
    }

    private boolean isRootViewTagSet() {
        int i = this.mRootViewTag;
        return (i == 0 || i == -1) ? false : true;
    }

    @Override // com.facebook.react.uimanager.ReactRoot
    public void setRootViewTag(int i) {
        this.mRootViewTag = i;
    }

    @Override // com.facebook.react.uimanager.RootView
    public void handleException(Throwable th) {
        ReactInstanceManager reactInstanceManager = this.mReactInstanceManager;
        if (reactInstanceManager == null || reactInstanceManager.getCurrentReactContext() == null) {
            throw new RuntimeException(th);
        }
        this.mReactInstanceManager.getCurrentReactContext().handleException(new IllegalViewOperationException(th.getMessage(), this, th));
    }

    public void setIsFabric(boolean z) {
        this.mUIManagerType = z ? 2 : 1;
    }

    @Override // com.facebook.react.uimanager.ReactRoot
    public int getUIManagerType() {
        return this.mUIManagerType;
    }

    @Nullable
    public ReactInstanceManager getReactInstanceManager() {
        return this.mReactInstanceManager;
    }

    void sendEvent(String str, @Nullable WritableMap writableMap) {
        ReactInstanceManager reactInstanceManager = this.mReactInstanceManager;
        if (reactInstanceManager != null) {
            ((DeviceEventManagerModule.RCTDeviceEventEmitter) reactInstanceManager.getCurrentReactContext().getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit(str, writableMap);
        }
    }

    private class CustomGlobalLayoutListener implements ViewTreeObserver.OnGlobalLayoutListener {
        private final int mMinKeyboardHeightDetected;
        private final Rect mVisibleViewArea;
        private boolean mKeyboardIsVisible = false;
        private int mKeyboardHeight = 0;
        private int mDeviceRotation = 0;

        CustomGlobalLayoutListener() {
            DisplayMetricsHolder.initDisplayMetricsIfNotInitialized(ReactRootView.this.getContext().getApplicationContext());
            this.mVisibleViewArea = new Rect();
            this.mMinKeyboardHeightDetected = (int) PixelUtil.toPixelFromDIP(60.0f);
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            if (ReactRootView.this.mReactInstanceManager == null || !ReactRootView.this.mIsAttachedToInstance || ReactRootView.this.mReactInstanceManager.getCurrentReactContext() == null) {
                return;
            }
            if (Build.VERSION.SDK_INT >= 30) {
                checkForKeyboardEvents();
            } else {
                checkForKeyboardEventsLegacy();
            }
            checkForDeviceOrientationChanges();
            checkForDeviceDimensionsChanges();
        }

        @RequiresApi(api = 30)
        private void checkForKeyboardEvents() {
            boolean zIsVisible;
            int i;
            ReactRootView.this.getRootView().getWindowVisibleDisplayFrame(this.mVisibleViewArea);
            WindowInsets rootWindowInsets = ReactRootView.this.getRootView().getRootWindowInsets();
            if (rootWindowInsets == null || (zIsVisible = rootWindowInsets.isVisible(WindowInsets$Type.ime())) == this.mKeyboardIsVisible) {
                return;
            }
            this.mKeyboardIsVisible = zIsVisible;
            if (zIsVisible) {
                int i2 = rootWindowInsets.getInsets(WindowInsets$Type.ime()).bottom - rootWindowInsets.getInsets(WindowInsets$Type.systemBars()).bottom;
                if (((Activity) ReactRootView.this.getContext()).getWindow().getAttributes().softInputMode == 48) {
                    i = this.mVisibleViewArea.bottom - i2;
                } else {
                    i = this.mVisibleViewArea.bottom;
                }
                ReactRootView.this.sendEvent("keyboardDidShow", createKeyboardEventPayload(PixelUtil.toDIPFromPixel(i), PixelUtil.toDIPFromPixel(this.mVisibleViewArea.left), PixelUtil.toDIPFromPixel(this.mVisibleViewArea.width()), PixelUtil.toDIPFromPixel(i2)));
                return;
            }
            ReactRootView.this.sendEvent("keyboardDidHide", createKeyboardEventPayload(PixelUtil.toDIPFromPixel(r0.mLastHeight), 0.0d, PixelUtil.toDIPFromPixel(this.mVisibleViewArea.width()), 0.0d));
        }

        private void checkForKeyboardEventsLegacy() {
            WindowInsets rootWindowInsets;
            DisplayCutout displayCutout;
            ReactRootView.this.getRootView().getWindowVisibleDisplayFrame(this.mVisibleViewArea);
            int safeInsetTop = (Build.VERSION.SDK_INT < 28 || (rootWindowInsets = ReactRootView.this.getRootView().getRootWindowInsets()) == null || (displayCutout = rootWindowInsets.getDisplayCutout()) == null) ? 0 : displayCutout.getSafeInsetTop();
            int i = (DisplayMetricsHolder.getWindowDisplayMetrics().heightPixels - this.mVisibleViewArea.bottom) + safeInsetTop;
            int i2 = this.mKeyboardHeight;
            if (i2 != i && i > this.mMinKeyboardHeightDetected) {
                this.mKeyboardHeight = i;
                this.mKeyboardIsVisible = true;
                ReactRootView.this.sendEvent("keyboardDidShow", createKeyboardEventPayload(PixelUtil.toDIPFromPixel(r3), PixelUtil.toDIPFromPixel(this.mVisibleViewArea.left), PixelUtil.toDIPFromPixel(this.mVisibleViewArea.width()), PixelUtil.toDIPFromPixel(this.mKeyboardHeight)));
            } else {
                if (i2 != 0 && i <= this.mMinKeyboardHeightDetected) {
                    this.mKeyboardHeight = 0;
                    this.mKeyboardIsVisible = false;
                    ReactRootView.this.sendEvent("keyboardDidHide", createKeyboardEventPayload(PixelUtil.toDIPFromPixel(r0.mLastHeight), 0.0d, PixelUtil.toDIPFromPixel(this.mVisibleViewArea.width()), 0.0d));
                }
            }
        }

        private void checkForDeviceOrientationChanges() {
            int rotation = ((WindowManager) ReactRootView.this.getContext().getSystemService("window")).getDefaultDisplay().getRotation();
            if (this.mDeviceRotation == rotation) {
                return;
            }
            this.mDeviceRotation = rotation;
            DisplayMetricsHolder.initDisplayMetrics(ReactRootView.this.getContext().getApplicationContext());
            emitOrientationChanged(rotation);
        }

        private void checkForDeviceDimensionsChanges() {
            emitUpdateDimensionsEvent();
        }

        private void emitOrientationChanged(int i) {
            double d;
            String str;
            double d2;
            boolean z = true;
            if (i != 0) {
                if (i == 1) {
                    d2 = -90.0d;
                    str = "landscape-primary";
                } else if (i == 2) {
                    d = 180.0d;
                    str = "portrait-secondary";
                } else {
                    if (i != 3) {
                        return;
                    }
                    d2 = 90.0d;
                    str = "landscape-secondary";
                }
                WritableMap writableMapCreateMap = Arguments.createMap();
                writableMapCreateMap.putString("name", str);
                writableMapCreateMap.putDouble("rotationDegrees", d2);
                writableMapCreateMap.putBoolean("isLandscape", z);
                ReactRootView.this.sendEvent("namedOrientationDidChange", writableMapCreateMap);
            }
            d = 0.0d;
            str = "portrait-primary";
            z = false;
            d2 = d;
            WritableMap writableMapCreateMap2 = Arguments.createMap();
            writableMapCreateMap2.putString("name", str);
            writableMapCreateMap2.putDouble("rotationDegrees", d2);
            writableMapCreateMap2.putBoolean("isLandscape", z);
            ReactRootView.this.sendEvent("namedOrientationDidChange", writableMapCreateMap2);
        }

        private void emitUpdateDimensionsEvent() {
            DeviceInfoModule deviceInfoModule = (DeviceInfoModule) ReactRootView.this.mReactInstanceManager.getCurrentReactContext().getNativeModule(DeviceInfoModule.class);
            if (deviceInfoModule != null) {
                deviceInfoModule.emitUpdateDimensionsEvent();
            }
        }

        private WritableMap createKeyboardEventPayload(double d, double d2, double d3, double d4) {
            WritableMap writableMapCreateMap = Arguments.createMap();
            WritableMap writableMapCreateMap2 = Arguments.createMap();
            writableMapCreateMap2.putDouble("height", d4);
            writableMapCreateMap2.putDouble("screenX", d2);
            writableMapCreateMap2.putDouble("width", d3);
            writableMapCreateMap2.putDouble("screenY", d);
            writableMapCreateMap.putMap("endCoordinates", writableMapCreateMap2);
            writableMapCreateMap.putString("easing", "keyboard");
            writableMapCreateMap.putDouble("duration", 0.0d);
            return writableMapCreateMap;
        }
    }
}
