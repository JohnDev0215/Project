package com.android.systemui.statusbar.phone;

import android.R;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Region;
import android.hardware.display.DisplayManager;
import android.hardware.input.InputManager;
import android.os.Handler;
import android.os.Looper;
import android.os.RemoteException;
import android.os.SystemClock;
import android.os.SystemProperties;
import android.provider.DeviceConfig;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.ISystemGestureExclusionListener;
import android.view.InputChannel;
import android.view.InputEvent;
import android.view.InputEventReceiver;
import android.view.InputMonitor;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.view.WindowManager;
import android.view.WindowManagerGlobal;
import com.android.internal.policy.GestureNavigationSettingsObserver;
import com.android.systemui.Dependency;
import com.android.systemui.SysUiServiceProvider;
import com.android.systemui.SystemUIFactory;
import com.android.systemui.plugins.NavigationEdgeBackPlugin;
import com.android.systemui.plugins.PluginListener;
import com.android.systemui.plugins.PluginManager;
import com.android.systemui.recents.OverviewProxyService;
import com.android.systemui.shared.system.ActivityManagerWrapper;
import com.android.systemui.shared.system.QuickStepContract;
import com.android.systemui.shared.system.TaskStackChangeListener;
import com.bydux.api.common.tools.util.ListUtils;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes.dex */
public class EdgeBackGestureHandler implements DisplayManager.DisplayListener, PluginListener<NavigationEdgeBackPlugin> {
    private static final int MAX_LOGGED_PREDICTIONS = 10;
    private static final int MAX_LONG_PRESS_TIMEOUT = SystemProperties.getInt("gestures.back_timeout", 250);
    private static final String TAG = "EdgeBackGestureHandler";
    private int edgeTouchPixels;
    private BackGestureTfClassifierProvider mBackGestureTfClassifierProvider;
    private float mBottomGestureHeight;
    private final Context mContext;
    private boolean mDisabledForQuickstep;
    private final int mDisplayId;
    private NavigationEdgeBackPlugin mEdgeBackPlugin;
    private int mEdgeWidthLeft;
    private int mEdgeWidthRight;
    private boolean mGestureBlockingActivityRunning;
    private final GestureNavigationSettingsObserver mGestureNavigationSettingsObserver;
    private InputEventReceiver mInputEventReceiver;
    private InputMonitor mInputMonitor;
    private boolean mIsAttached;
    private boolean mIsBackGestureAllowed;
    private boolean mIsEnabled;
    private boolean mIsGesturalModeEnabled;
    private boolean mIsNavBarShownTransiently;
    private boolean mIsOnLeftEdge;
    private int mLeftInset;
    private int mMLEnableWidth;
    private float mMLModelThreshold;
    private float mMLResults;
    private final Executor mMainExecutor;
    private final OverviewProxyService mOverviewProxyService;
    private String mPackageName;
    private final PluginManager mPluginManager;
    private int mRightInset;
    private final Runnable mStateChangeCallback;
    private final StatusBar mStatusBarComponent;
    private int mSysUiFlags;
    private float mTouchSlop;
    private boolean mUseMLModel;
    private Map<String, Integer> mVocab;
    private ISystemGestureExclusionListener mGestureExclusionListener = new AnonymousClass1();
    private TaskStackChangeListener mTaskStackListener = new TaskStackChangeListener() { // from class: com.android.systemui.statusbar.phone.EdgeBackGestureHandler.2
        @Override // com.android.systemui.shared.system.TaskStackChangeListener
        public void onTaskStackChanged() {
            EdgeBackGestureHandler edgeBackGestureHandler = EdgeBackGestureHandler.this;
            edgeBackGestureHandler.mGestureBlockingActivityRunning = edgeBackGestureHandler.isGestureBlockingActivityRunning();
        }

        @Override // com.android.systemui.shared.system.TaskStackChangeListener
        public void onTaskCreated(int taskId, ComponentName componentName) {
            if (componentName == null) {
                EdgeBackGestureHandler.this.mPackageName = "_UNKNOWN";
            } else {
                EdgeBackGestureHandler.this.mPackageName = componentName.getPackageName();
            }
        }
    };
    private DeviceConfig.OnPropertiesChangedListener mOnPropertiesChangedListener = new DeviceConfig.OnPropertiesChangedListener() { // from class: com.android.systemui.statusbar.phone.EdgeBackGestureHandler.3
        public void onPropertiesChanged(DeviceConfig.Properties properties) {
            if ("systemui".equals(properties.getNamespace())) {
                if (properties.getKeyset().contains("back_gesture_ml_model_threshold") || properties.getKeyset().contains("use_back_gesture_ml_model") || properties.getKeyset().contains("back_gesture_ml_model_name")) {
                    EdgeBackGestureHandler.this.updateMLModelState();
                }
            }
        }
    };
    private final List<ComponentName> mGestureBlockingActivities = new ArrayList();
    private final Point mDisplaySize = new Point();
    private final Region mExcludeRegion = new Region();
    private final Region mUnrestrictedExcludeRegion = new Region();
    private int mStartingQuickstepRotation = -1;
    private final PointF mDownPoint = new PointF();
    private final PointF mEndPoint = new PointF();
    private boolean mThresholdCrossed = false;
    private boolean mAllowGesture = false;
    private boolean mLogGesture = false;
    private boolean mInRejectedExclusion = false;
    private ArrayDeque<String> mPredictionLog = new ArrayDeque<>();
    private final NavigationEdgeBackPlugin.BackCallback mBackCallback = new NavigationEdgeBackPlugin.BackCallback() { // from class: com.android.systemui.statusbar.phone.EdgeBackGestureHandler.4
        @Override // com.android.systemui.plugins.NavigationEdgeBackPlugin.BackCallback
        public void cancelBack() {
        }

        @Override // com.android.systemui.plugins.NavigationEdgeBackPlugin.BackCallback
        public void triggerBack() {
            if (EdgeBackGestureHandler.this.mStatusBarComponent.isShow()) {
                EdgeBackGestureHandler.this.mStatusBarComponent.closePanelsIfNeed();
            } else {
                EdgeBackGestureHandler.this.sendEvent(0, 4);
                EdgeBackGestureHandler.this.sendEvent(1, 4);
            }
        }
    };
    private final int mLongPressTimeout = Math.min(MAX_LONG_PRESS_TIMEOUT, ViewConfiguration.getLongPressTimeout());

    @Override // android.hardware.display.DisplayManager.DisplayListener
    public void onDisplayAdded(int displayId) {
    }

    @Override // android.hardware.display.DisplayManager.DisplayListener
    public void onDisplayRemoved(int displayId) {
    }

    /* JADX INFO: renamed from: com.android.systemui.statusbar.phone.EdgeBackGestureHandler$1, reason: invalid class name */
    class AnonymousClass1 extends ISystemGestureExclusionListener.Stub {
        AnonymousClass1() {
        }

        public void onSystemGestureExclusionChanged(int displayId, final Region systemGestureExclusion, final Region unrestrictedOrNull) {
            if (displayId == EdgeBackGestureHandler.this.mDisplayId) {
                EdgeBackGestureHandler.this.mMainExecutor.execute(new Runnable() { // from class: com.android.systemui.statusbar.phone.-$$Lambda$EdgeBackGestureHandler$1$4Innhm_o_lpiiNcKaCKrqCkLIug
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$onSystemGestureExclusionChanged$0$EdgeBackGestureHandler$1(systemGestureExclusion, unrestrictedOrNull);
                    }
                });
            }
        }

        public /* synthetic */ void lambda$onSystemGestureExclusionChanged$0$EdgeBackGestureHandler$1(Region region, Region region2) {
            EdgeBackGestureHandler.this.mExcludeRegion.set(region);
            Region region3 = EdgeBackGestureHandler.this.mUnrestrictedExcludeRegion;
            if (region2 != null) {
                region = region2;
            }
            region3.set(region);
        }
    }

    public EdgeBackGestureHandler(Context context, OverviewProxyService overviewProxyService, PluginManager pluginManager, Runnable stateChangeCallback) {
        this.edgeTouchPixels = 24;
        this.mContext = context;
        this.mDisplayId = context.getDisplayId();
        this.mMainExecutor = context.getMainExecutor();
        this.mOverviewProxyService = overviewProxyService;
        this.mPluginManager = pluginManager;
        this.mStateChangeCallback = stateChangeCallback;
        this.mStatusBarComponent = (StatusBar) SysUiServiceProvider.getComponent(context, StatusBar.class);
        this.mGestureNavigationSettingsObserver = new GestureNavigationSettingsObserver(context.getMainThreadHandler(), context, new Runnable() { // from class: com.android.systemui.statusbar.phone.-$$Lambda$EdgeBackGestureHandler$t_KZSJyxz3HCsiaYQZ_l8soOVks
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.onNavigationSettingsChanged();
            }
        });
        updateCurrentUserResources();
        this.edgeTouchPixels = (int) (((double) (context.getResources().getDisplayMetrics().density * 16.0f)) + 0.5d);
    }

    public final void updateCurrentUserResources() {
        Resources resources = this.mContext.getResources();
        this.mEdgeWidthLeft = this.mGestureNavigationSettingsObserver.getLeftSensitivity(resources);
        this.mEdgeWidthRight = this.mGestureNavigationSettingsObserver.getRightSensitivity(resources);
        this.mIsBackGestureAllowed = !this.mGestureNavigationSettingsObserver.areNavigationButtonForcedVisible();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        this.mBottomGestureHeight = TypedValue.applyDimension(1, DeviceConfig.getFloat("systemui", "back_gesture_bottom_height", resources.getDimension(R.dimen.image_size) / displayMetrics.density), displayMetrics);
        int iApplyDimension = (int) TypedValue.applyDimension(1, 12.0f, displayMetrics);
        this.mMLEnableWidth = iApplyDimension;
        int i = this.mEdgeWidthRight;
        if (iApplyDimension > i) {
            this.mMLEnableWidth = i;
        }
        int i2 = this.mMLEnableWidth;
        int i3 = this.mEdgeWidthLeft;
        if (i2 > i3) {
            this.mMLEnableWidth = i3;
        }
        this.mTouchSlop = ViewConfiguration.get(this.mContext).getScaledTouchSlop() * DeviceConfig.getFloat("systemui", "back_gesture_slop_multiplier", 0.75f);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onNavigationSettingsChanged() {
        Runnable runnable;
        boolean zIsHandlingGestures = isHandlingGestures();
        updateCurrentUserResources();
        if (zIsHandlingGestures == isHandlingGestures() || (runnable = this.mStateChangeCallback) == null) {
            return;
        }
        runnable.run();
    }

    public void onNavBarAttached() {
        this.mIsAttached = true;
        updateIsEnabled();
    }

    public void onNavBarDetached() {
        this.mIsAttached = false;
        updateIsEnabled();
    }

    public void onNavigationModeChanged(int mode) {
        this.mIsGesturalModeEnabled = QuickStepContract.isGesturalMode(mode);
        updateIsEnabled();
    }

    public void onNavBarTransientStateChanged(boolean isTransient) {
        this.mIsNavBarShownTransiently = isTransient;
    }

    private void disposeInputChannel() {
        InputEventReceiver inputEventReceiver = this.mInputEventReceiver;
        if (inputEventReceiver != null) {
            inputEventReceiver.dispose();
            this.mInputEventReceiver = null;
        }
        InputMonitor inputMonitor = this.mInputMonitor;
        if (inputMonitor != null) {
            inputMonitor.dispose();
            this.mInputMonitor = null;
        }
    }

    private void updateIsEnabled() {
        boolean z = this.mIsAttached && this.mIsGesturalModeEnabled;
        Log.e(TAG, "updateIsEnabled isEnabled = " + z + ", mIsEnabled = " + this.mIsEnabled + ", mDisplayId = " + this.mDisplayId);
        if (z == this.mIsEnabled) {
            return;
        }
        this.mIsEnabled = z;
        disposeInputChannel();
        NavigationEdgeBackPlugin navigationEdgeBackPlugin = this.mEdgeBackPlugin;
        if (navigationEdgeBackPlugin != null) {
            navigationEdgeBackPlugin.onDestroy();
            this.mEdgeBackPlugin = null;
        }
        if (!this.mIsEnabled) {
            this.mGestureNavigationSettingsObserver.unregister();
            ((DisplayManager) this.mContext.getSystemService(DisplayManager.class)).unregisterDisplayListener(this);
            PluginManager pluginManager = this.mPluginManager;
            if (pluginManager != null) {
                pluginManager.removePluginListener(this);
            }
            ActivityManagerWrapper.getInstance().unregisterTaskStackListener(this.mTaskStackListener);
            DeviceConfig.removeOnPropertiesChangedListener(this.mOnPropertiesChangedListener);
            try {
                WindowManagerGlobal.getWindowManagerService().unregisterSystemGestureExclusionListener(this.mGestureExclusionListener, this.mDisplayId);
            } catch (RemoteException | IllegalArgumentException e) {
                Log.e(TAG, "Failed to unregister window manager callbacks", e);
            }
        } else {
            this.mGestureNavigationSettingsObserver.register();
            updateDisplaySize();
            ((DisplayManager) this.mContext.getSystemService(DisplayManager.class)).registerDisplayListener(this, this.mContext.getMainThreadHandler());
            ActivityManagerWrapper.getInstance().registerTaskStackListener(this.mTaskStackListener);
            DeviceConfig.addOnPropertiesChangedListener("systemui", new Executor() { // from class: com.android.systemui.statusbar.phone.-$$Lambda$EdgeBackGestureHandler$f2a_MhS2Sue0mJLFg-jOBhN_t14
                @Override // java.util.concurrent.Executor
                public final void execute(Runnable runnable) {
                    this.f$0.lambda$updateIsEnabled$0$EdgeBackGestureHandler(runnable);
                }
            }, this.mOnPropertiesChangedListener);
            try {
                WindowManagerGlobal.getWindowManagerService().registerSystemGestureExclusionListener(this.mGestureExclusionListener, this.mDisplayId);
            } catch (RemoteException | IllegalArgumentException e2) {
                Log.e(TAG, "Failed to register window manager callbacks", e2);
            }
            this.mInputMonitor = InputManager.getInstance().monitorGestureInput("edge-swipe", this.mDisplayId);
            this.mInputEventReceiver = new SysUiInputEventReceiver(this.mInputMonitor.getInputChannel(), Looper.getMainLooper());
            setEdgeBackPlugin(new NavigationBarEdgePanel(this.mContext));
            PluginManager pluginManager2 = this.mPluginManager;
            if (pluginManager2 != null) {
                pluginManager2.addPluginListener((PluginListener) this, NavigationEdgeBackPlugin.class, false);
            }
        }
        updateMLModelState();
    }

    public /* synthetic */ void lambda$updateIsEnabled$0$EdgeBackGestureHandler(Runnable runnable) {
        this.mContext.getMainThreadHandler().post(runnable);
    }

    @Override // com.android.systemui.plugins.PluginListener
    public void onPluginConnected(NavigationEdgeBackPlugin plugin, Context context) {
        Log.e(TAG, "onPluginConnected");
        setEdgeBackPlugin(plugin);
    }

    @Override // com.android.systemui.plugins.PluginListener
    public void onPluginDisconnected(NavigationEdgeBackPlugin plugin) {
        Log.e(TAG, "onPluginDisconnected");
        setEdgeBackPlugin(new NavigationBarEdgePanel(this.mContext));
    }

    private void setEdgeBackPlugin(NavigationEdgeBackPlugin edgeBackPlugin) {
        NavigationEdgeBackPlugin navigationEdgeBackPlugin = this.mEdgeBackPlugin;
        if (navigationEdgeBackPlugin != null) {
            navigationEdgeBackPlugin.onDestroy();
        }
        this.mEdgeBackPlugin = edgeBackPlugin;
        edgeBackPlugin.setBackCallback(this.mBackCallback);
        this.mEdgeBackPlugin.setLayoutParams(createLayoutParams());
        updateDisplaySize();
    }

    public boolean isHandlingGestures() {
        return this.mIsEnabled && this.mIsBackGestureAllowed;
    }

    private WindowManager.LayoutParams createLayoutParams() {
        Resources resources = this.mContext.getResources();
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(resources.getDimensionPixelSize(com.android.systemui.R.dimen.navigation_edge_panel_width), resources.getDimensionPixelSize(com.android.systemui.R.dimen.navigation_edge_panel_height), 2024, 8389160, -3);
        layoutParams.privateFlags |= 16;
        layoutParams.setTitle(TAG + this.mContext.getDisplayId());
        layoutParams.accessibilityTitle = this.mContext.getString(com.android.systemui.R.string.nav_bar_edge_panel);
        layoutParams.windowAnimations = 0;
        layoutParams.setFitInsetsTypes(0);
        return layoutParams;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onInputEvent(InputEvent ev) {
        if (ev instanceof MotionEvent) {
            onMotionEvent((MotionEvent) ev);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateMLModelState() {
        boolean z = this.mIsEnabled && DeviceConfig.getBoolean("systemui", "use_back_gesture_ml_model", false);
        if (z == this.mUseMLModel) {
            return;
        }
        if (z) {
            this.mBackGestureTfClassifierProvider = SystemUIFactory.getInstance().createBackGestureTfClassifierProvider(this.mContext.getAssets(), DeviceConfig.getString("systemui", "back_gesture_ml_model_name", "backgesture"));
            this.mMLModelThreshold = DeviceConfig.getFloat("systemui", "back_gesture_ml_model_threshold", 0.9f);
            if (this.mBackGestureTfClassifierProvider.isActive()) {
                this.mVocab = this.mBackGestureTfClassifierProvider.loadVocab(this.mContext.getAssets());
                this.mUseMLModel = true;
                return;
            }
        }
        this.mUseMLModel = false;
        BackGestureTfClassifierProvider backGestureTfClassifierProvider = this.mBackGestureTfClassifierProvider;
        if (backGestureTfClassifierProvider != null) {
            backGestureTfClassifierProvider.release();
            this.mBackGestureTfClassifierProvider = null;
        }
    }

    private int getBackGesturePredictionsCategory(int x, int y, int app) {
        int i;
        if (app == -1) {
            return -1;
        }
        if (x <= ((double) this.mDisplaySize.x) / 2.0d) {
            i = 1;
        } else {
            x = this.mDisplaySize.x - x;
            i = 2;
        }
        float fPredict = this.mBackGestureTfClassifierProvider.predict(new Object[]{new long[]{this.mDisplaySize.x}, new long[]{x}, new long[]{i}, new long[]{app}, new long[]{y}});
        this.mMLResults = fPredict;
        if (fPredict == -1.0f) {
            return -1;
        }
        return fPredict >= this.mMLModelThreshold ? 1 : 0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v10 */
    /* JADX WARN: Type inference failed for: r4v16 */
    /* JADX WARN: Type inference failed for: r4v6 */
    /* JADX WARN: Type inference failed for: r4v7 */
    /* JADX WARN: Type inference failed for: r4v8, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r4v9 */
    private boolean isWithinTouchRegion(int x, int y, boolean isOnLeftEdge) {
        int backGesturePredictionsCategory;
        int i = this.mContext.getResources().getConfiguration().orientation;
        if (i == 2 && Settings.System.getInt(this.mContext.getContentResolver(), "is_show_split_screen_bar", 0) == 1) {
            if (Settings.System.getInt(this.mContext.getContentResolver(), "show_split_screen_bar_orientation", 0) == 1) {
                if (y >= 462 && y <= 618 && isOnLeftEdge) {
                    return false;
                }
            } else if (Settings.System.getInt(this.mContext.getContentResolver(), "show_split_screen_bar_orientation", 0) == 2 && y >= 462 && y <= 618 && !isOnLeftEdge) {
                return false;
            }
        }
        Log.d(TAG, "isWithinTouchRegion orientation : " + i);
        Log.d(TAG, String.format(Locale.CHINESE, "isWithinTouchRegion x = %d, mEdgeWidthLeft = %d, mEdgeWidthRight = %d, mLeftInset = %d, mRightInset = %d, edgeTouchPixels = %d, mDisplaySize.x = %d, mDisplaySize.y = %d", Integer.valueOf(x), Integer.valueOf(this.mEdgeWidthLeft), Integer.valueOf(this.mEdgeWidthRight), Integer.valueOf(this.mLeftInset), Integer.valueOf(this.mRightInset), Integer.valueOf(this.edgeTouchPixels), Integer.valueOf(this.mDisplaySize.x), Integer.valueOf(this.mDisplaySize.y)));
        if (x > ((this.mEdgeWidthLeft + this.mLeftInset) * 2) + this.edgeTouchPixels && x < (this.mDisplaySize.x - ((this.mEdgeWidthRight + this.mRightInset) * 2)) - this.edgeTouchPixels) {
            return false;
        }
        Map<String, Integer> map = this.mVocab;
        int iIntValue = map != null ? map.getOrDefault(this.mPackageName, -1).intValue() : -1;
        ?? r4 = (x <= (this.mEdgeWidthLeft + this.mLeftInset) + this.edgeTouchPixels || x >= ((this.mDisplaySize.x - this.mEdgeWidthRight) - this.mRightInset) - this.edgeTouchPixels) ? 1 : 0;
        if (r4 != 0) {
            if (!(x < this.mMLEnableWidth + this.mLeftInset || x >= (this.mDisplaySize.x - this.mMLEnableWidth) - this.mRightInset) && this.mUseMLModel && (backGesturePredictionsCategory = getBackGesturePredictionsCategory(x, y, iIntValue)) != -1) {
                r4 = backGesturePredictionsCategory == 1 ? 1 : 0;
            }
        }
        if (this.mPredictionLog.size() >= 10) {
            this.mPredictionLog.removeFirst();
        }
        this.mPredictionLog.addLast(String.format("[%d,%d,%d,%f,%d]", Integer.valueOf(x), Integer.valueOf(y), Integer.valueOf(iIntValue), Float.valueOf(this.mMLResults), Integer.valueOf((int) r4)));
        if (this.mIsNavBarShownTransiently) {
            this.mLogGesture = true;
            return r4;
        }
        if (!this.mExcludeRegion.contains(x, y)) {
            this.mInRejectedExclusion = this.mUnrestrictedExcludeRegion.contains(x, y);
            this.mLogGesture = true;
            return r4;
        }
        if (r4 != 0) {
            this.mEndPoint.x = -1.0f;
            this.mEndPoint.y = -1.0f;
            this.mLogGesture = true;
        }
        return false;
    }

    private void cancelGesture(MotionEvent ev) {
        this.mAllowGesture = false;
        this.mLogGesture = false;
        this.mInRejectedExclusion = false;
        MotionEvent motionEventObtain = MotionEvent.obtain(ev);
        motionEventObtain.setAction(3);
        this.mEdgeBackPlugin.onMotionEvent(motionEventObtain);
        motionEventObtain.recycle();
    }

    private void logGesture(int backType) {
        if (this.mLogGesture) {
            this.mLogGesture = false;
            if (this.mUseMLModel && this.mVocab.containsKey(this.mPackageName)) {
                this.mVocab.get(this.mPackageName).intValue();
            }
        }
    }

    private void onMotionEvent(MotionEvent ev) {
        int actionMasked = ev.getActionMasked();
        if (actionMasked == 0) {
            this.mIsOnLeftEdge = ev.getX() <= ((float) ((this.mEdgeWidthLeft + this.mLeftInset) + this.edgeTouchPixels));
            this.mMLResults = 0.0f;
            this.mLogGesture = false;
            this.mInRejectedExclusion = false;
            boolean z = (this.mDisabledForQuickstep || !this.mIsBackGestureAllowed || this.mGestureBlockingActivityRunning || QuickStepContract.isBackGestureDisabled(this.mSysUiFlags) || !isWithinTouchRegion((int) ev.getX(), (int) ev.getY(), this.mIsOnLeftEdge)) ? false : true;
            this.mAllowGesture = z;
            if (z) {
                this.mEdgeBackPlugin.setIsLeftPanel(this.mIsOnLeftEdge);
                this.mEdgeBackPlugin.onMotionEvent(ev);
            }
            if (this.mLogGesture) {
                this.mDownPoint.set(ev.getX(), ev.getY());
                this.mEndPoint.set(-1.0f, -1.0f);
                this.mThresholdCrossed = false;
                return;
            }
            return;
        }
        if (this.mAllowGesture || this.mLogGesture) {
            if (!this.mThresholdCrossed) {
                this.mEndPoint.x = (int) ev.getX();
                this.mEndPoint.y = (int) ev.getY();
                if (actionMasked == 5) {
                    if (this.mAllowGesture) {
                        cancelGesture(ev);
                    }
                    this.mLogGesture = false;
                    return;
                } else if (actionMasked == 2) {
                    float fAbs = Math.abs(ev.getX() - this.mDownPoint.x);
                    float fAbs2 = Math.abs(ev.getY() - this.mDownPoint.y);
                    if (fAbs2 > fAbs && fAbs2 > this.mTouchSlop) {
                        if (this.mAllowGesture) {
                            cancelGesture(ev);
                        }
                        this.mLogGesture = false;
                        return;
                    } else if (fAbs > fAbs2 && fAbs > this.mTouchSlop && this.mAllowGesture) {
                        this.mThresholdCrossed = true;
                        this.mInputMonitor.pilferPointers();
                    }
                }
            }
            if (this.mAllowGesture) {
                this.mEdgeBackPlugin.onMotionEvent(ev);
            }
        }
    }

    private void updateDisabledForQuickstep() {
        int rotation = this.mContext.getResources().getConfiguration().windowConfiguration.getRotation();
        int i = this.mStartingQuickstepRotation;
        this.mDisabledForQuickstep = i > -1 && i != rotation;
    }

    @Override // android.hardware.display.DisplayManager.DisplayListener
    public void onDisplayChanged(int displayId) {
        if (this.mStartingQuickstepRotation > -1) {
            updateDisabledForQuickstep();
        }
        if (displayId == this.mDisplayId) {
            updateDisplaySize();
            int i = this.mContext.getResources().getConfiguration().orientation;
            if ((i == 2 && this.mDisplaySize.x < this.mDisplaySize.y) || (i == 1 && this.mDisplaySize.x > this.mDisplaySize.y)) {
                Log.w(TAG, "onDisplayChanged get wrong displaySize! orientation = " + i + " mDisplaySize = " + this.mDisplaySize);
                ((Handler) Dependency.get(Dependency.MAIN_HANDLER)).postDelayed(new Runnable() { // from class: com.android.systemui.statusbar.phone.-$$Lambda$EdgeBackGestureHandler$RljwPOtfNNsCMohiWuIGx5an5yc
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.updateDisplaySize();
                    }
                }, 1000L);
            }
        }
        Log.d(TAG, "onDisplayChanged displayId = " + displayId);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateDisplaySize() {
        this.mContext.getDisplay().getRealSize(this.mDisplaySize);
        Log.d(TAG, "updateDisplaySize mDisplaySize = " + this.mDisplaySize.x + ListUtils.DEFAULT_JOIN_SEPARATOR + this.mDisplaySize.y);
        NavigationEdgeBackPlugin navigationEdgeBackPlugin = this.mEdgeBackPlugin;
        if (navigationEdgeBackPlugin != null) {
            navigationEdgeBackPlugin.setDisplaySize(this.mDisplaySize);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendEvent(int action, int code) {
        long jUptimeMillis = SystemClock.uptimeMillis();
        InputManager.getInstance().injectInputEvent(new KeyEvent(jUptimeMillis, jUptimeMillis, action, code, 0, 0, -1, 0, 72, 257), 0);
    }

    public void setInsets(int leftInset, int rightInset) {
        this.mLeftInset = leftInset;
        this.mRightInset = rightInset;
        NavigationEdgeBackPlugin navigationEdgeBackPlugin = this.mEdgeBackPlugin;
        if (navigationEdgeBackPlugin != null) {
            navigationEdgeBackPlugin.setInsets(leftInset, rightInset);
        }
    }

    public void dump(PrintWriter pw) {
        pw.println("EdgeBackGestureHandler:");
        pw.println("  mIsEnabled=" + this.mIsEnabled);
        pw.println("  mIsBackGestureAllowed=" + this.mIsBackGestureAllowed);
        pw.println("  mAllowGesture=" + this.mAllowGesture);
        pw.println("  mDisabledForQuickstep=" + this.mDisabledForQuickstep);
        pw.println("  mStartingQuickstepRotation=" + this.mStartingQuickstepRotation);
        pw.println("  mInRejectedExclusion" + this.mInRejectedExclusion);
        pw.println("  mExcludeRegion=" + this.mExcludeRegion);
        pw.println("  mUnrestrictedExcludeRegion=" + this.mUnrestrictedExcludeRegion);
        pw.println("  mIsAttached=" + this.mIsAttached);
        pw.println("  mEdgeWidthLeft=" + this.mEdgeWidthLeft);
        pw.println("  mEdgeWidthRight=" + this.mEdgeWidthRight);
        pw.println("  mIsNavBarShownTransiently=" + this.mIsNavBarShownTransiently);
        pw.println("  mPredictionLog=" + String.join(";", this.mPredictionLog));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isGestureBlockingActivityRunning() {
        ActivityManager.RunningTaskInfo runningTask = ActivityManagerWrapper.getInstance().getRunningTask();
        ComponentName componentName = runningTask == null ? null : runningTask.topActivity;
        if (componentName != null) {
            this.mPackageName = componentName.getPackageName();
        } else {
            this.mPackageName = "_UNKNOWN";
        }
        return componentName != null && this.mGestureBlockingActivities.contains(componentName);
    }

    class SysUiInputEventReceiver extends InputEventReceiver {
        SysUiInputEventReceiver(InputChannel channel, Looper looper) {
            super(channel, looper);
        }

        public void onInputEvent(InputEvent event) {
            EdgeBackGestureHandler.this.onInputEvent(event);
            finishInputEvent(event, true);
        }
    }
}
