package com.android.systemui.statusbar.phone;

import android.R;
import android.app.ActivityManager;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.WallpaperInfo;
import android.app.WallpaperManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.IPackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.ContentObserver;
import android.graphics.Point;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.os.SystemClock;
import android.os.UserHandle;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.IWindowManager;
import android.view.InsetsState;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.ThreadedRenderer;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.WindowManagerGlobal;
import android.widget.DateTimeView;
import android.widget.TextView;
import android.widget.Toast;
import com.android.internal.statusbar.IStatusBarService;
import com.android.internal.statusbar.RegisterStatusBarResult;
import com.android.internal.statusbar.StatusBarIcon;
import com.android.internal.view.AppearanceRegion;
import com.android.systemui.AssistantReceiver;
import com.android.systemui.BarReceiver;
import com.android.systemui.BtBatteryReceiver;
import com.android.systemui.Constants;
import com.android.systemui.CpaaReceiver;
import com.android.systemui.Dependency;
import com.android.systemui.LauncherReplacedDesktopReceiver;
import com.android.systemui.LauncherSelectReceiver;
import com.android.systemui.NaviStatusReceiver;
import com.android.systemui.PowerModeReceiver;
import com.android.systemui.QSPanelReceiver;
import com.android.systemui.SysUiServiceProvider;
import com.android.systemui.SystemUI;
import com.android.systemui.fragments.FragmentHostManager;
import com.android.systemui.qs.QSEditFragment;
import com.android.systemui.qs.SceneModeIntroduceFragment;
import com.android.systemui.recents.Recents;
import com.android.systemui.stackdivider.Divider;
import com.android.systemui.stackdivider.DividerUtils;
import com.android.systemui.stackdivider.SplitScreenController;
import com.android.systemui.stackdivider.view.help.SplitScreenHelpManager;
import com.android.systemui.stackdivider.view.preview.SplitPreviewWindowManager;
import com.android.systemui.statusbar.AutoHideUiElement;
import com.android.systemui.statusbar.CommandQueue;
import com.android.systemui.statusbar.policy.CarController;
import com.android.systemui.statusbar.wm.AirPanelController;
import com.android.systemui.util.InterestingConfigChanges;
import com.android.systemui.util.SharedPreferencesUtil;
import com.android.systemui.volume.VolumeComponent;
import com.android.systemui.widget.SysUiDrawerLayout;
import com.byd.card.utils.AppUtils;
import com.byd.mediacenter.api.media.MediaControl;
import com.byd.profile.StatusBarChangeEvent;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import org.greenrobot.eventbus.EventBus;

/* JADX INFO: loaded from: classes.dex */
public class StatusBar extends SystemUI implements CommandQueue.Callbacks {
    private static final long AUTO_HIDE_PANELS_TIMEOUT_MS = 8000;
    public static final boolean DEBUG = true;
    public static final boolean DEBUG_WINDOW_STATE = false;
    private static final int MSG_AUTO_CLOSE_PANEL = 9999;
    private static final int MSG_CLOSE_PANELS = 1001;
    private static final int MSG_DELAYED_CLOSE_DRAWERS = 10000;
    private static final int MSG_LAUNCH_TRANSITION_TIMEOUT = 1003;
    private static final int MSG_OPEN_NOTIFICATION_PANEL = 1000;
    private static final int MSG_OPEN_SETTINGS_PANEL = 1002;
    private static final boolean ONLY_CORE_APPS;
    public static final String SYSTEM_DIALOG_REASON_RECENT_APPS = "recentapps";
    public static final String SYSTEM_DIALOG_REASON_SCREENSHOT = "screenshot";
    public static final String TAG = "StatusBar";
    private static final String screenOrientation_uri = "screen_orientation";
    private AssistantReceiver assistantReceiver;
    private BarReceiver barReceiver;
    private long lastClickNightModeTime;
    private LauncherSelectReceiver launcherSelectReceiver;
    private AirPanelController mAirPanelController;
    private int mAppearance;
    private AutoHideController mAutoHideController;
    private IStatusBarService mBarService;
    private BlurViewController mBlurViewController;
    private CarController mCarController;
    private CommandQueue mCommandQueue;
    private Display mDisplay;
    private int mDisplayId;
    private boolean mExpandedVisible;
    private boolean mIsScreenShotWindowShowing;
    private NavigationBarFragment mNavigationBar;
    private NavigationBarFrame mNavigationBarView;
    protected boolean mPanelExpanded;
    protected Recents mRecents;
    private RotateSettingsObserver mRotateSettingsObserver;
    private ScreenOffObserver mScreenOffObserver;
    public float mSlideOffset;
    protected int mState;
    private int mStatusBarMode;
    protected PhoneStatusBarView mStatusBarView;
    protected StatusBarWindowView mStatusBarWindow;
    protected StatusBarWindowManager mStatusBarWindowManager;
    private SysUiDrawerLayout mSysUiDrawerLayout;
    private View mSysUiQsPanel;
    private Toast mToast;
    private boolean mTransientShown;
    private boolean mWallpaperSupported;
    private WindowManager mWindowManager;
    protected IWindowManager mWindowManagerService;
    private QSPanelReceiver qsPanelReceiver;
    private int mNaturalBarHeight = -1;
    private final Point mCurrentDisplaySize = new Point();
    private int mStatusBarWindowState = 0;
    private boolean isThreeFingers = false;
    private final Object mQueueLock = new Object();
    private final ArrayList<Runnable> mPostCollapseRunnables = new ArrayList<>();
    private int mDisabled1 = 0;
    private int mDisabled2 = 0;
    private final DisplayMetrics mDisplayMetrics = new DisplayMetrics();
    private final BroadcastReceiver mWallpaperChangedReceiver = new BroadcastReceiver() { // from class: com.android.systemui.statusbar.phone.StatusBar.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            BydLogHelper.logD("wallpaper change");
            SharedPreferencesUtil.putInt(StatusBar.this.mContext, Constants.STATE_WALLPAPER, 0);
            if (!StatusBar.this.mWallpaperSupported) {
                Log.wtf(StatusBar.TAG, "WallpaperManager not supported");
                return;
            }
            WallpaperInfo wallpaperInfo = ((WallpaperManager) context.getSystemService(WallpaperManager.class)).getWallpaperInfo(-2);
            if (!StatusBar.this.mContext.getResources().getBoolean(R.bool.config_bluetooth_sco_off_call) || wallpaperInfo == null) {
                return;
            }
            wallpaperInfo.supportsAmbientMode();
        }
    };
    private final InterestingConfigChanges mOrientationConfigChanges = new InterestingConfigChanges(128);
    private final Runnable closeRunnable = new Runnable() { // from class: com.android.systemui.statusbar.phone.StatusBar.4
        @Override // java.lang.Runnable
        public void run() {
            float onScreen = StatusBar.this.mSysUiDrawerLayout.getOnScreen(StatusBar.this.mSysUiQsPanel);
            if (onScreen <= 0.0f || onScreen >= 1.0f) {
                return;
            }
            BydLogHelper.logD("drawer unexpected onScreen:" + onScreen);
            if (onScreen < 0.5f) {
                StatusBar.this.closePanelsIfNeed();
            } else {
                StatusBar.this.openPanels();
            }
        }
    };
    protected final H mHandler = createHandler();
    private final Runnable mAutoHide = new Runnable() { // from class: com.android.systemui.statusbar.phone.-$$Lambda$StatusBar$GXg1zrT9qM7fJIXM2xBgTBtgXGc
        @Override // java.lang.Runnable
        public final void run() {
            this.f$0.lambda$new$2$StatusBar();
        }
    };
    private ContentObserver mContentObserver = new ContentObserver(new Handler()) { // from class: com.android.systemui.statusbar.phone.StatusBar.5
        @Override // android.database.ContentObserver
        public void onChange(boolean selfChange, Uri uri) {
            super.onChange(selfChange, uri);
            String host = uri.getHost();
            String lastPathSegment = uri.getLastPathSegment();
            Log.i(StatusBar.TAG, "selfChange:" + selfChange + " , host: " + uri.getHost() + " , option:" + lastPathSegment);
            if (!selfChange && TextUtils.equals(host, Constants.MSG_PROVIDER_URI_HOST) && TextUtils.equals(lastPathSegment, Constants.MSG_PROVIDER_URI_SEGMENT)) {
                ((CollapsedStatusBarFragment) FragmentHostManager.get(StatusBar.this.mStatusBarWindow).getFragmentManager().findFragmentByTag(CollapsedStatusBarFragment.TAG)).executeQueryMsg();
            }
        }
    };

    private static int barMode(boolean isTransient, int appearance) {
        if (isTransient) {
            return 1;
        }
        if ((appearance & 5) == 5) {
            return 3;
        }
        if ((appearance & 4) != 0) {
            return 6;
        }
        return (appearance & 1) != 0 ? 4 : 0;
    }

    void checkBarModes() {
    }

    @Override // com.android.systemui.SystemUI
    public void dump(FileDescriptor fd, PrintWriter pw, String[] args) {
    }

    public void executeRunnableDismissingKeyguard(final Runnable runnable, final Runnable cancelAction, final boolean dismissShade, final boolean afterKeyguardGone, final boolean deferred) {
    }

    static {
        boolean zIsOnlyCoreApps;
        try {
            zIsOnlyCoreApps = IPackageManager.Stub.asInterface(ServiceManager.getService(MediaControl.PARAM_PACKAGE)).isOnlyCoreApps();
        } catch (RemoteException unused) {
            zIsOnlyCoreApps = false;
        }
        ONLY_CORE_APPS = zIsOnlyCoreApps;
    }

    @Override // com.android.systemui.SystemUI
    protected void onConfigurationChanged(Configuration newConfig) {
        AirPanelController airPanelController;
        if (this.mOrientationConfigChanges.applyNewConfig(this.mContext.getResources())) {
            BydLogHelper.logD("onConfigurationChanged new orientation:" + this.mContext.getResources().getConfiguration().orientation);
            if (this.mSysUiDrawerLayout != null && this.mSysUiQsPanel != null && !isShow()) {
                closePanelsIfNeed(true);
            }
        }
        if (this.mNavigationBarView != null && (airPanelController = this.mAirPanelController) != null) {
            airPanelController.resetGesturePopup();
        }
        PhoneStatusBarView phoneStatusBarView = this.mStatusBarView;
        if (phoneStatusBarView != null) {
            FragmentHostManager.get(phoneStatusBarView).dispatchConfigurationChanged(newConfig);
        }
    }

    @Override // com.android.systemui.SystemUI
    public void start() {
        RegisterStatusBarResult registerStatusBarResultRegisterStatusBar;
        this.mOrientationConfigChanges.applyNewConfig(this.mContext.getResources());
        this.mWindowManager = (WindowManager) this.mContext.getSystemService("window");
        this.mWindowManagerService = WindowManagerGlobal.getWindowManagerService();
        Display defaultDisplay = this.mWindowManager.getDefaultDisplay();
        this.mDisplay = defaultDisplay;
        this.mDisplayId = defaultDisplay.getDisplayId();
        updateDisplaySize();
        DateTimeView.setReceiverHandler((Handler) Dependency.get(Dependency.TIME_TICK_HANDLER));
        putComponent(StatusBar.class, this);
        this.mBarService = IStatusBarService.Stub.asInterface(ServiceManager.getService("statusbar"));
        CommandQueue commandQueue = (CommandQueue) getComponent(CommandQueue.class);
        this.mCommandQueue = commandQueue;
        commandQueue.addCallback(this);
        try {
            registerStatusBarResultRegisterStatusBar = this.mBarService.registerStatusBar(this.mCommandQueue);
        } catch (RemoteException unused) {
            registerStatusBarResultRegisterStatusBar = null;
        }
        createAndAddWindows();
        this.mCommandQueue.disable(registerStatusBarResultRegisterStatusBar.mDisabledFlags1, registerStatusBarResultRegisterStatusBar.mDisabledFlags2, false);
        onSystemBarAppearanceChanged(this.mDisplayId, registerStatusBarResultRegisterStatusBar.mAppearance, registerStatusBarResultRegisterStatusBar.mAppearanceRegions, registerStatusBarResultRegisterStatusBar.mNavbarColorManagedByIme);
        setImeWindowStatus(registerStatusBarResultRegisterStatusBar.mImeToken, registerStatusBarResultRegisterStatusBar.mImeWindowVis, registerStatusBarResultRegisterStatusBar.mImeBackDisposition, registerStatusBarResultRegisterStatusBar.mShowImeSwitcher);
        int size = registerStatusBarResultRegisterStatusBar.mIcons.size();
        for (int i = 0; i < size; i++) {
            this.mCommandQueue.setIcon((String) registerStatusBarResultRegisterStatusBar.mIcons.keyAt(i), (StatusBarIcon) registerStatusBarResultRegisterStatusBar.mIcons.valueAt(i));
        }
        this.mRecents = (Recents) getComponent(Recents.class);
        this.mWallpaperSupported = ((WallpaperManager) this.mContext.getSystemService(WallpaperManager.class)).isWallpaperSupported();
        BydLogHelper.logV("mWallpaperSupported:" + this.mWallpaperSupported);
        if (this.mWallpaperSupported) {
            this.mContext.registerReceiverAsUser(this.mWallpaperChangedReceiver, UserHandle.ALL, new IntentFilter("android.intent.action.WALLPAPER_CHANGED"), null, null);
            this.mWallpaperChangedReceiver.onReceive(this.mContext, null);
        } else {
            Log.v(TAG, "start(): no wallpaper service ");
        }
        SharedPreferencesUtil.putBoolean(this.mContext, Constants.IS_START_ACTIVITY, false);
    }

    @Override // com.android.systemui.SystemUI
    protected void onBootCompleted() {
        Log.e(TAG, "onBootCompleted");
        BlurViewController blurViewController = (BlurViewController) Dependency.get(BlurViewController.class);
        this.mBlurViewController = blurViewController;
        blurViewController.setBar(this);
        this.mCarController = (CarController) Dependency.get(CarController.class);
        this.mAirPanelController = (AirPanelController) Dependency.get(AirPanelController.class);
        AutoHideController autoHideController = new AutoHideController(this.mContext, this.mHandler, this.mWindowManagerService);
        this.mAutoHideController = autoHideController;
        autoHideController.setStatusBar(new AutoHideUiElement() { // from class: com.android.systemui.statusbar.phone.StatusBar.2
            @Override // com.android.systemui.statusbar.AutoHideUiElement
            public boolean shouldHideOnTouch() {
                return true;
            }

            @Override // com.android.systemui.statusbar.AutoHideUiElement
            public void synchronizeState() {
                StatusBar.this.checkBarModes();
            }

            @Override // com.android.systemui.statusbar.AutoHideUiElement
            public boolean isVisible() {
                return StatusBar.this.isTransientShown();
            }

            @Override // com.android.systemui.statusbar.AutoHideUiElement
            public void hide() {
                StatusBar.this.clearTransient();
            }
        });
        CollapsedStatusBarFragment collapsedStatusBarFragment = (CollapsedStatusBarFragment) FragmentHostManager.get(this.mStatusBarWindow).getFragmentManager().findFragmentByTag(CollapsedStatusBarFragment.TAG);
        if (collapsedStatusBarFragment != null) {
            collapsedStatusBarFragment.onBootCompleted();
        }
        StatusBarWindowView statusBarWindowView = this.mStatusBarWindow;
        if (statusBarWindowView != null) {
            statusBarWindowView.onBootCompleted();
        }
        PhoneStatusBarView phoneStatusBarView = this.mStatusBarView;
        if (phoneStatusBarView != null) {
            phoneStatusBarView.onBootCompleted();
        }
        this.mNavigationBarView.onBootCompleted();
        this.mNavigationBar.onBootCompleted();
        registerCPAA();
        registerBarVisible();
        registerLauncherSelectDialogVisible();
        registerQSPanelAuto();
        registerRotateOrientation();
        registerScreenOffObserver();
        registerMsgUnreadChange();
        registerAssistantState();
        registerBtBatteryReceiver();
        registerNaviStatusReceiver();
        registerPowerModeReceiver();
        registerLauncherReplacedDesktopReceiver();
        this.mCarController.initAvm();
    }

    private void registerRotateOrientation() {
        try {
            if (this.mContext.getContentResolver() != null) {
                this.mRotateSettingsObserver = new RotateSettingsObserver(null);
                this.mContext.getContentResolver().registerContentObserver(Settings.System.getUriFor(screenOrientation_uri), false, this.mRotateSettingsObserver);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void registerScreenOffObserver() {
        try {
            if (this.mContext.getContentResolver() != null) {
                this.mScreenOffObserver = new ScreenOffObserver(null);
                this.mContext.getContentResolver().registerContentObserver(Settings.Global.getUriFor("navibar_mode_on_screensaver"), false, this.mScreenOffObserver);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void registerMsgUnreadChange() {
        if (this.mContext.getContentResolver() != null) {
            try {
                this.mContext.getContentResolver().registerContentObserver(Uri.parse(Constants.MSG_PROVIDER_URI), false, this.mContentObserver);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void registerCPAA() {
        Log.i(TAG, "registerCPAA Receiver");
        CarController carController = (CarController) Dependency.get(CarController.class);
        carController.initCarplay(true);
        carController.initAndroidAuto(true);
        CpaaReceiver cpaaReceiver = new CpaaReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(CpaaReceiver.ACTION_CARPLAY_START_FINISH);
        intentFilter.addAction(CpaaReceiver.ACTION_ANDROID_AUTO_START_FINISH);
        intentFilter.setPriority(10);
        this.mContext.registerReceiver(cpaaReceiver, intentFilter);
    }

    private void registerAssistantState() {
        this.assistantReceiver = new AssistantReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(AssistantReceiver.ACTION_DISMISS_VOLUME_DIALOG);
        intentFilter.setPriority(10);
        this.mContext.registerReceiver(this.assistantReceiver, intentFilter);
    }

    private void registerQSPanelAuto() {
        this.qsPanelReceiver = new QSPanelReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(QSPanelReceiver.ACTION);
        intentFilter.setPriority(10);
        this.mContext.registerReceiver(this.qsPanelReceiver, intentFilter);
    }

    private void registerBarVisible() {
        this.barReceiver = new BarReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.android.systemui.action.byd.BarReceiver");
        intentFilter.addAction(BarReceiver.ACTION_STATUS_BAR_UPDATE);
        intentFilter.setPriority(10);
        this.mContext.registerReceiver(this.barReceiver, intentFilter);
    }

    private void registerLauncherSelectDialogVisible() {
        this.launcherSelectReceiver = new LauncherSelectReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(LauncherSelectReceiver.ACTION);
        intentFilter.setPriority(10);
        this.mContext.registerReceiver(this.launcherSelectReceiver, intentFilter);
    }

    private void registerBtBatteryReceiver() {
        BtBatteryReceiver btBatteryReceiver = new BtBatteryReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(BtBatteryReceiver.ACTION);
        intentFilter.setPriority(10);
        this.mContext.registerReceiver(btBatteryReceiver, intentFilter);
    }

    private void registerNaviStatusReceiver() {
        NaviStatusReceiver naviStatusReceiver = new NaviStatusReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(NaviStatusReceiver.ACTION);
        intentFilter.setPriority(10);
        this.mContext.registerReceiver(naviStatusReceiver, intentFilter);
    }

    private void registerLauncherReplacedDesktopReceiver() {
        LauncherReplacedDesktopReceiver launcherReplacedDesktopReceiver = new LauncherReplacedDesktopReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(LauncherReplacedDesktopReceiver.ACTION);
        intentFilter.setPriority(10);
        this.mContext.registerReceiver(launcherReplacedDesktopReceiver, intentFilter);
    }

    private void registerPowerModeReceiver() {
        PowerModeReceiver powerModeReceiver = new PowerModeReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(PowerModeReceiver.ACTION);
        intentFilter.setPriority(10);
        this.mContext.registerReceiver(powerModeReceiver, intentFilter);
    }

    protected void makeStatusBarView() {
        Context context = this.mContext;
        updateDisplaySize();
        updateResources();
        inflateStatusBarWindow(context);
        this.mStatusBarWindow.setService(this);
        createNavigationBar();
        FragmentHostManager.get(this.mStatusBarWindow).addTagListener(CollapsedStatusBarFragment.TAG, new FragmentHostManager.FragmentListener() { // from class: com.android.systemui.statusbar.phone.-$$Lambda$StatusBar$MHr9TeMyf036YfAaGLSDYcywI5o
            @Override // com.android.systemui.fragments.FragmentHostManager.FragmentListener
            public final void onFragmentViewCreated(String str, Fragment fragment) {
                this.f$0.lambda$makeStatusBarView$0$StatusBar(str, fragment);
            }
        }).getFragmentManager().beginTransaction().replace(com.android.systemui.R.id.status_bar_container, new CollapsedStatusBarFragment(), CollapsedStatusBarFragment.TAG).commitNow();
        inflateQsPanel();
        ThreadedRenderer.overrideProperty("disableProfileBars", "true");
    }

    public /* synthetic */ void lambda$makeStatusBarView$0$StatusBar(String str, Fragment fragment) {
        Log.d(TAG, "addTagListener -->");
        PhoneStatusBarView phoneStatusBarView = (PhoneStatusBarView) fragment.getView();
        this.mStatusBarView = phoneStatusBarView;
        phoneStatusBarView.setBar(this);
        this.mStatusBarWindow.setStatusBarView(this.mStatusBarView);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isTransientShown() {
        return this.mTransientShown;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearTransient() {
        if (this.mTransientShown) {
            this.mTransientShown = false;
            handleTransientChanged();
        }
    }

    private void handleTransientChanged() {
        updateBarMode(barMode(this.mTransientShown, this.mAppearance));
    }

    private boolean updateBarMode(int barMode) {
        if (this.mStatusBarMode == barMode) {
            return false;
        }
        this.mStatusBarMode = barMode;
        checkBarModes();
        this.mAutoHideController.touchAutoHide();
        return true;
    }

    @Override // com.android.systemui.statusbar.CommandQueue.Callbacks
    public void onSystemBarAppearanceChanged(int displayId, int appearance, AppearanceRegion[] appearanceRegions, boolean navbarColorManagedByIme) {
        if (this.mAppearance != appearance) {
            this.mAppearance = appearance;
        }
    }

    @Override // com.android.systemui.statusbar.CommandQueue.Callbacks
    public void showTransient(int displayId, int[] types) {
        if (InsetsState.containsType(types, 0)) {
            showTransientUnchecked();
        }
    }

    private void showTransientUnchecked() {
        if (this.mTransientShown) {
            return;
        }
        this.mTransientShown = true;
        handleTransientChanged();
    }

    @Override // com.android.systemui.statusbar.CommandQueue.Callbacks
    public void abortTransient(int displayId, int[] types) {
        if (InsetsState.containsType(types, 0)) {
            clearTransient();
        }
    }

    public NavigationBarFragment getNavigationBarFragment() {
        return this.mNavigationBar;
    }

    private void createNavigationBar() {
        this.mNavigationBarView = (NavigationBarFrame) NavigationBarFragment.create(this.mContext, new FragmentHostManager.FragmentListener() { // from class: com.android.systemui.statusbar.phone.-$$Lambda$StatusBar$iy-zKrdJo06z9P43BAFPLc9X3m0
            @Override // com.android.systemui.fragments.FragmentHostManager.FragmentListener
            public final void onFragmentViewCreated(String str, Fragment fragment) {
                this.f$0.lambda$createNavigationBar$1$StatusBar(str, fragment);
            }
        });
    }

    public /* synthetic */ void lambda$createNavigationBar$1$StatusBar(String str, Fragment fragment) {
        NavigationBarFragment navigationBarFragment = (NavigationBarFragment) fragment;
        this.mNavigationBar = navigationBarFragment;
        navigationBarFragment.setAutoHideController(this.mAutoHideController);
        this.mNavigationBar.restoreAppearanceAndTransientState();
    }

    private void inflateStatusBarWindow(Context context) {
        StatusBarWindowView statusBarWindowView = (StatusBarWindowView) View.inflate(context, com.android.systemui.R.layout.super_status_bar, null);
        this.mStatusBarWindow = statusBarWindowView;
        this.mSysUiDrawerLayout = (SysUiDrawerLayout) statusBarWindowView.findViewById(com.android.systemui.R.id.status_bar_drawer_layout);
        this.mSysUiQsPanel = this.mStatusBarWindow.findViewById(com.android.systemui.R.id.qs_frame);
        this.mSysUiDrawerLayout.setScrimColor(0);
        this.mSysUiDrawerLayout.addDrawerListener(new SysUiDrawerLayout.DrawerListener() { // from class: com.android.systemui.statusbar.phone.StatusBar.3
            @Override // com.android.systemui.widget.SysUiDrawerLayout.DrawerListener
            public void onDrawerSlide(View drawerView, float slideOffset) {
                StatusBar.this.mSlideOffset = slideOffset;
                if (StatusBar.this.mSysUiDrawerLayout.isDrawerOpen(StatusBar.this.mSysUiQsPanel)) {
                    long jUptimeMillis = SystemClock.uptimeMillis();
                    MotionEvent motionEventObtain = MotionEvent.obtain(jUptimeMillis, jUptimeMillis, 3, 0.0f, 0.0f, 0);
                    motionEventObtain.setSource(4098);
                    StatusBar.this.mSysUiQsPanel.dispatchTouchEvent(motionEventObtain);
                    motionEventObtain.recycle();
                }
            }

            @Override // com.android.systemui.widget.SysUiDrawerLayout.DrawerListener
            public void onDrawerOpened(View drawerView) {
                Log.d(StatusBar.TAG, "onDrawerOpened : ");
                EventBus.getDefault().post(new StatusBarChangeEvent(true));
                StatusBar.this.scheduleAutoHide();
                if (StatusBar.this.mAutoHideController != null) {
                    StatusBar.this.mAutoHideController.suspendAutoHide();
                }
                StatusBar.this.sendBroadcastPanelState(true);
            }

            @Override // com.android.systemui.widget.SysUiDrawerLayout.DrawerListener
            public void onDrawerClosed(View drawerView) {
                Log.d(StatusBar.TAG, "onDrawerClosed : ");
                EventBus.getDefault().post(new StatusBarChangeEvent(false));
                StatusBar.this.showQSPanel();
                StatusBar.this.cancelAutoHide();
                if (StatusBar.this.mAutoHideController != null) {
                    StatusBar.this.mAutoHideController.resumeSuspendedAutoHide();
                }
                StatusBar.this.mStatusBarWindowManager.setPanelExpanded(false);
                StatusBar.this.mStatusBarWindowManager.setPanelVisible(false);
                StatusBar.this.goneMask();
                if (StatusBar.this.mBlurViewController != null) {
                    StatusBar.this.mBlurViewController.clearBlurBitmap();
                }
                StatusBar.this.sendBroadcastPanelState(false);
                SharedPreferencesUtil.putBoolean(StatusBar.this.mContext, Constants.IS_START_ACTIVITY, false);
            }

            @Override // com.android.systemui.widget.SysUiDrawerLayout.DrawerListener
            public void onDrawerStateChanged(final int newState, final float onScreen) {
                Log.d(StatusBar.TAG, "onDrawerStateChanged = " + newState + " ,onScreen: " + onScreen + " ,mSlideOffset: " + StatusBar.this.mSlideOffset);
                ((Handler) Dependency.get(Dependency.MAIN_HANDLER)).removeCallbacks(StatusBar.this.closeRunnable);
                if (newState == 0) {
                    if (StatusBar.this.mSlideOffset == 0.0f && !StatusBar.this.mSysUiDrawerLayout.isDrawerOpen(StatusBar.this.mSysUiQsPanel)) {
                        StatusBar.this.mStatusBarWindowManager.setPanelExpanded(false);
                        StatusBar.this.mStatusBarWindowManager.setPanelVisible(false);
                    }
                    if (0.5f <= onScreen && onScreen < 1.0f) {
                        BydLogHelper.logD("isDrawerOpen:" + StatusBar.this.mSysUiDrawerLayout.isDrawerOpen(StatusBar.this.mSysUiQsPanel));
                        StatusBar.this.openPanels();
                    }
                    ((Handler) Dependency.get(Dependency.MAIN_HANDLER)).postDelayed(StatusBar.this.closeRunnable, 500L);
                    return;
                }
                if (newState == 1) {
                    StatusBar.this.mStatusBarWindowManager.setPanelExpanded(true);
                    StatusBar.this.mStatusBarWindowManager.setPanelVisible(true);
                    if (onScreen == 0.0f) {
                        try {
                            StatusBar.this.mBlurViewController.setBlurBackground();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendBroadcastPanelState(boolean expanded) {
        Intent intent = new Intent(Constants.SYS_PANEL_STATE_CHANGE_ACTION);
        intent.putExtra("expanded", expanded);
        boolean z = SharedPreferencesUtil.getBoolean(this.mContext, Constants.IS_START_ACTIVITY, false);
        BydLogHelper.logD("sendBroadcastPanelState isStartActivity = " + z);
        intent.putExtra(Constants.IS_START_ACTIVITY, z);
        this.mContext.sendBroadcast(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void goneMask() {
        BydLogHelper.logD("11");
        QSFragment qSFragment = (QSFragment) FragmentHostManager.get(this.mStatusBarWindow).getFragmentManager().findFragmentByTag(QSFragment.TAG);
        if (qSFragment != null) {
            BydLogHelper.logD("22");
            qSFragment.goneMask();
        }
    }

    private boolean getMaskVisible() {
        QSFragment qSFragment = (QSFragment) FragmentHostManager.get(this.mStatusBarWindow).getFragmentManager().findFragmentByTag(QSFragment.TAG);
        if (qSFragment != null) {
            return qSFragment.maskVisible();
        }
        return false;
    }

    public QSFragment getQSFragment() {
        return (QSFragment) FragmentHostManager.get(this.mStatusBarWindow).getFragmentManager().findFragmentByTag(QSFragment.TAG);
    }

    private void inflateQsPanel() {
        FragmentHostManager.get(this.mStatusBarWindow).getFragmentManager().beginTransaction().replace(com.android.systemui.R.id.qs_frame, new QSFragment(), QSFragment.TAG).commit();
    }

    public void backStack(String tag) {
        try {
            BydLogHelper.logV("backStack", "tag:" + tag);
            FragmentHostManager.get(this.mStatusBarWindow).getFragmentManager().popBackStack(tag, 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showQSEdit(Bundle bundle) {
        try {
            FragmentManager fragmentManager = FragmentHostManager.get(this.mStatusBarWindow).getFragmentManager();
            Fragment fragmentFindFragmentByTag = fragmentManager.findFragmentByTag(QSFragment.TAG);
            Fragment fragmentFindFragmentByTag2 = fragmentManager.findFragmentByTag(QSEditFragment.TAG);
            Fragment fragmentFindFragmentByTag3 = fragmentManager.findFragmentByTag(SceneModeIntroduceFragment.TAG);
            FragmentTransaction fragmentTransactionBeginTransaction = fragmentManager.beginTransaction();
            if (fragmentFindFragmentByTag2 == null) {
                fragmentFindFragmentByTag2 = new QSEditFragment();
                BydLogHelper.logV("showFragment", "create:QSEditFragment");
                fragmentFindFragmentByTag2.setArguments(bundle);
            }
            BydLogHelper.logD("showQSEdit", "qsEditFragment.isAdded() = " + fragmentFindFragmentByTag2.isAdded());
            if (fragmentFindFragmentByTag2.isAdded()) {
                return;
            }
            fragmentTransactionBeginTransaction.add(com.android.systemui.R.id.qs_frame, fragmentFindFragmentByTag2, QSEditFragment.TAG);
            if (fragmentFindFragmentByTag != null && fragmentFindFragmentByTag.isVisible()) {
                fragmentTransactionBeginTransaction.hide(fragmentFindFragmentByTag);
            }
            if (fragmentFindFragmentByTag3 != null && fragmentFindFragmentByTag3.isVisible()) {
                fragmentTransactionBeginTransaction.hide(fragmentFindFragmentByTag3);
            }
            fragmentTransactionBeginTransaction.addToBackStack(QSEditFragment.TAG).commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showSceneModeIntroduce() {
        try {
            FragmentManager fragmentManager = FragmentHostManager.get(this.mStatusBarWindow).getFragmentManager();
            Fragment fragmentFindFragmentByTag = fragmentManager.findFragmentByTag(QSFragment.TAG);
            Fragment fragmentFindFragmentByTag2 = fragmentManager.findFragmentByTag(QSEditFragment.TAG);
            Fragment fragmentFindFragmentByTag3 = fragmentManager.findFragmentByTag(SceneModeIntroduceFragment.TAG);
            FragmentTransaction fragmentTransactionBeginTransaction = fragmentManager.beginTransaction();
            if (fragmentFindFragmentByTag3 == null) {
                BydLogHelper.logV("showFragment", "create:SceneModeIntroduceFragment");
                fragmentFindFragmentByTag3 = new SceneModeIntroduceFragment();
            }
            fragmentTransactionBeginTransaction.add(com.android.systemui.R.id.qs_frame, fragmentFindFragmentByTag3, SceneModeIntroduceFragment.TAG);
            if (fragmentFindFragmentByTag != null && fragmentFindFragmentByTag.isVisible()) {
                fragmentTransactionBeginTransaction.hide(fragmentFindFragmentByTag);
            }
            if (fragmentFindFragmentByTag2 != null && fragmentFindFragmentByTag2.isVisible()) {
                fragmentTransactionBeginTransaction.hide(fragmentFindFragmentByTag2);
            }
            fragmentTransactionBeginTransaction.addToBackStack(SceneModeIntroduceFragment.TAG).commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showQSPanel() {
        try {
            FragmentManager fragmentManager = FragmentHostManager.get(this.mStatusBarWindow).getFragmentManager();
            Fragment fragmentFindFragmentByTag = fragmentManager.findFragmentByTag(QSFragment.TAG);
            Fragment fragmentFindFragmentByTag2 = fragmentManager.findFragmentByTag(QSEditFragment.TAG);
            Fragment fragmentFindFragmentByTag3 = fragmentManager.findFragmentByTag(SceneModeIntroduceFragment.TAG);
            if (fragmentFindFragmentByTag == null) {
                BydLogHelper.logV("showFragment", "create:QSFragment");
                fragmentFindFragmentByTag = new QSFragment();
                FragmentTransaction fragmentTransactionBeginTransaction = fragmentManager.beginTransaction();
                fragmentTransactionBeginTransaction.replace(com.android.systemui.R.id.qs_frame, fragmentFindFragmentByTag, QSFragment.TAG);
                fragmentTransactionBeginTransaction.commit();
            }
            if (!fragmentFindFragmentByTag.isVisible()) {
                fragmentManager.beginTransaction().show(fragmentFindFragmentByTag).commit();
            }
            if (fragmentFindFragmentByTag2 != null) {
                backStack(QSEditFragment.TAG);
            }
            if (fragmentFindFragmentByTag3 != null) {
                backStack(SceneModeIntroduceFragment.TAG);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void closePanelsIfNeed() {
        this.mHandler.removeMessages(10000);
        this.mHandler.sendEmptyMessageDelayed(10000, 300L);
    }

    public void closePanelsIfNeedNoDelayed() {
        this.mHandler.removeMessages(10000);
        this.mHandler.sendEmptyMessage(10000);
    }

    public void closePanelsIfNeed(boolean isForce) {
        SysUiDrawerLayout sysUiDrawerLayout = this.mSysUiDrawerLayout;
        if (sysUiDrawerLayout != null) {
            if (isForce) {
                sysUiDrawerLayout.closeDrawersForce();
            } else {
                sysUiDrawerLayout.closeDrawers();
            }
        }
    }

    public void openPanels() {
        if (this.mSysUiDrawerLayout == null || this.mSysUiQsPanel == null) {
            return;
        }
        this.mStatusBarWindowManager.setPanelExpanded(true);
        this.mStatusBarWindowManager.setPanelVisible(true);
        this.mSysUiDrawerLayout.openDrawer(this.mSysUiQsPanel);
    }

    public boolean isShow() {
        View view;
        SysUiDrawerLayout sysUiDrawerLayout = this.mSysUiDrawerLayout;
        if (sysUiDrawerLayout == null || (view = this.mSysUiQsPanel) == null) {
            return false;
        }
        return sysUiDrawerLayout.isDrawerOpen(view);
    }

    public void setQsPanelEnable(boolean enable) {
        if (enable) {
            this.mSysUiDrawerLayout.setDrawerLockMode(0);
        } else {
            this.mSysUiDrawerLayout.setDrawerLockMode(1);
        }
    }

    public StatusBarWindowView getStatusBarWindow() {
        return this.mStatusBarWindow;
    }

    public PhoneStatusBarView getPhoneStatusBarView() {
        return this.mStatusBarView;
    }

    public int getStatusBarHeight() {
        if (this.mNaturalBarHeight < 0) {
            this.mNaturalBarHeight = this.mContext.getResources().getDimensionPixelSize(R.dimen.media_notification_expanded_image_margin_bottom);
        }
        return this.mNaturalBarHeight;
    }

    @Override // com.android.systemui.statusbar.CommandQueue.Callbacks
    public void disable(int state1, int state2, boolean animate) {
        int i = this.mStatusBarWindowState;
        this.mDisabled1 = state1;
        this.mDisabled2 = state2;
    }

    public void recomputeDisableFlags(boolean animate) {
        this.mCommandQueue.recomputeDisableFlags(animate);
    }

    private void runPostCollapseRunnables() {
        ArrayList arrayList = new ArrayList(this.mPostCollapseRunnables);
        this.mPostCollapseRunnables.clear();
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            ((Runnable) arrayList.get(i)).run();
        }
    }

    @Override // com.android.systemui.statusbar.CommandQueue.Callbacks
    public void setWindowState(int window, int state) {
        StatusBarWindowView statusBarWindowView;
        Log.d(TAG, "setWindowState window = " + window + " , state = " + state);
        if (this.mStatusBarWindow != null && window == 1 && this.mStatusBarWindowState != state) {
            this.mStatusBarWindowState = state;
        }
        boolean zIsShowHelpActivity = SplitScreenHelpManager.getInstance(this.mContext).isShowHelpActivity();
        Log.d(TAG, "isThreeFingers = " + this.isThreeFingers + ", isShowingSplitHelpView = " + zIsShowHelpActivity);
        if ((this.isThreeFingers || zIsShowHelpActivity) && (statusBarWindowView = this.mStatusBarWindow) != null) {
            if (state == 0) {
                statusBarWindowView.setAlpha(0.0f);
            } else if (state == 2) {
                statusBarWindowView.setAlpha(1.0f);
            }
        }
    }

    public void setThreeFingers(boolean threeFingers) {
        StatusBarWindowView statusBarWindowView;
        this.isThreeFingers = threeFingers;
        if (threeFingers || (statusBarWindowView = this.mStatusBarWindow) == null) {
            return;
        }
        statusBarWindowView.setAlpha(1.0f);
    }

    public void createAndAddWindows() {
        addStatusBarWindow();
    }

    private void addStatusBarWindow() {
        makeStatusBarView();
        StatusBarWindowManager statusBarWindowManager = (StatusBarWindowManager) Dependency.get(StatusBarWindowManager.class);
        this.mStatusBarWindowManager = statusBarWindowManager;
        statusBarWindowManager.add(this.mStatusBarWindow, getStatusBarHeight());
    }

    private void updateDisplaySize() {
        this.mDisplay.getMetrics(this.mDisplayMetrics);
        this.mDisplay.getSize(this.mCurrentDisplaySize);
    }

    private void updateResources() {
        loadDimens();
        PhoneStatusBarView phoneStatusBarView = this.mStatusBarView;
        if (phoneStatusBarView != null) {
            phoneStatusBarView.updateResources();
        }
    }

    private void loadDimens() {
        this.mContext.getResources();
        int statusBarHeight = getStatusBarHeight();
        this.mNaturalBarHeight = statusBarHeight;
        StatusBarWindowManager statusBarWindowManager = this.mStatusBarWindowManager;
        if (statusBarWindowManager != null) {
            statusBarWindowManager.setBarHeight(statusBarHeight);
        }
    }

    public void destroy() {
        StatusBarWindowView statusBarWindowView = this.mStatusBarWindow;
        if (statusBarWindowView != null) {
            this.mWindowManager.removeViewImmediate(statusBarWindowView);
            this.mStatusBarWindow = null;
        }
        NavigationBarFrame navigationBarFrame = this.mNavigationBarView;
        if (navigationBarFrame != null) {
            this.mWindowManager.removeViewImmediate(navigationBarFrame);
            this.mNavigationBarView = null;
        }
    }

    public int getBarState() {
        return this.mState;
    }

    public void setBarState(int state) {
        this.mState = state;
        this.mStatusBarWindowManager.setStatusBarState(state);
    }

    public void onTrackingStarted() {
        runPostCollapseRunnables();
    }

    public NavigationBarView getNavigationBarView() {
        NavigationBarFragment navigationBarFragment = this.mNavigationBar;
        if (navigationBarFragment != null) {
            return (NavigationBarView) navigationBarFragment.getView();
        }
        return null;
    }

    @Override // com.android.systemui.statusbar.CommandQueue.Callbacks
    public void setTopAppHidesStatusBar(boolean topAppHidesStatusBar) {
        recomputeDisableFlags(true);
    }

    public void animateCollapsePanels() {
        animateCollapsePanels(0);
    }

    public void setPanelExpanded(boolean isExpanded) {
        this.mPanelExpanded = isExpanded;
        this.mStatusBarWindowManager.setPanelExpanded(isExpanded);
    }

    public void setStatusBarFocusable(boolean statusBarFocusable) {
        this.mStatusBarWindowManager.setStatusBarFocusable(statusBarFocusable);
    }

    public void setPanelVisible(boolean panelVisible) {
        this.mStatusBarWindowManager.setPanelVisible(panelVisible);
    }

    public void setForceVisible(boolean panelVisible) {
        if (panelVisible) {
            this.mStatusBarWindowManager.setBarHeight(getStatusBarHeight());
        } else {
            this.mStatusBarWindowManager.setBarHeight(0);
        }
        this.mNavigationBar.updateNaviBar(panelVisible, this.mNavigationBarView);
    }

    boolean panelsEnabled() {
        return (this.mDisabled1 & 65536) == 0 && !ONLY_CORE_APPS;
    }

    private void suspendAutohide() {
        this.mHandler.removeCallbacks(this.mAutoHide);
    }

    public void scheduleAutoHide() {
        cancelAutoHide();
        this.mHandler.sendEmptyMessageDelayed(MSG_AUTO_CLOSE_PANEL, AUTO_HIDE_PANELS_TIMEOUT_MS);
    }

    public void cancelAutoHide() {
        this.mHandler.removeMessages(MSG_AUTO_CLOSE_PANEL);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doHide() {
        this.mHandler.post(this.mAutoHide);
    }

    private H createHandler() {
        return new H();
    }

    public /* synthetic */ void lambda$new$2$StatusBar() {
        Log.d(TAG, "auto hide");
        this.mSysUiDrawerLayout.closeDrawers();
    }

    protected class H extends Handler {
        protected H() {
        }

        @Override // android.os.Handler
        public void handleMessage(Message m) {
            int i = m.what;
            if (i == StatusBar.MSG_AUTO_CLOSE_PANEL) {
                StatusBar.this.doHide();
                return;
            }
            if (i != 10000) {
                switch (i) {
                    case 1000:
                        StatusBar.this.animateExpandNotificationsPanel();
                        break;
                    case 1001:
                        StatusBar.this.animateCollapsePanels();
                        break;
                    case 1002:
                        StatusBar.this.animateExpandSettingsPanel((String) m.obj);
                        break;
                }
                return;
            }
            StatusBar.this.closePanelsIfNeed(false);
        }
    }

    @Override // com.android.systemui.statusbar.CommandQueue.Callbacks
    public void handleSystemKey(int key) {
        Log.d(TAG, "handleSystemKey: " + key);
    }

    public void toggleSplitScreenMode() {
        Divider divider = (Divider) SysUiServiceProvider.getComponent(this.mContext, Divider.class);
        SplitScreenController splitScreenController = (SplitScreenController) Dependency.get(SplitScreenController.class);
        if (divider == null || !divider.isDividerVisible()) {
            BydLogHelper.logD("splitPrimaryTask");
            splitScreenController.splitPrimaryTask();
        } else {
            exitSplit();
        }
    }

    public void exitSplit() {
        Divider divider = (Divider) SysUiServiceProvider.getComponent(this.mContext, Divider.class);
        if (divider.isMinimized() && !divider.isHomeStackResizable()) {
            BydLogHelper.logD("Undocking from the minimized state is not supported");
            divider.dismissDockedStack();
            return;
        }
        boolean zIsShowListActivity = DividerUtils.getInstance().isShowListActivity(true);
        boolean zIsShowListActivity2 = DividerUtils.getInstance().isShowListActivity(false);
        BydLogHelper.logD("isPrimaryViewShowing : " + zIsShowListActivity + " , isSecondaryViewShowing : " + zIsShowListActivity2);
        if (zIsShowListActivity && zIsShowListActivity2) {
            BydLogHelper.logD("starOnePixelActivity");
            SplitPreviewWindowManager.getInstance(this.mContext).executeHalfToFullAnimation("", "", true);
            divider.doHomeClick();
        } else if (zIsShowListActivity) {
            BydLogHelper.logD("list on the left, app on the right");
            divider.onUndockingTask(2);
        } else if (zIsShowListActivity2) {
            BydLogHelper.logD("app on the left, list on the right");
            divider.onUndockingTask(1);
        } else if (DividerUtils.getInstance().getWindowingMode() == 3) {
            BydLogHelper.logD("both app,but focus on the left");
            divider.onUndockingTask(1);
        } else {
            BydLogHelper.logD("both app,but focus on the right");
            divider.onUndockingTask(2);
        }
    }

    @Override // com.android.systemui.statusbar.CommandQueue.Callbacks
    public void appTransitionCancelled() {
        ((Divider) SysUiServiceProvider.getComponent(this.mContext, Divider.class)).onAppTransitionFinished();
    }

    /* JADX INFO: Access modifiers changed from: private */
    class RotateSettingsObserver extends ContentObserver {
        public RotateSettingsObserver(Handler handler) {
            super(handler);
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean selfChange, Uri uri) {
            String lastPathSegment = uri.getLastPathSegment();
            BydLogHelper.logD("onChange selfChange : " + selfChange + " , option : " + lastPathSegment);
            if (TextUtils.equals(lastPathSegment, StatusBar.screenOrientation_uri)) {
                try {
                    int i = Settings.System.getInt(StatusBar.this.mContext.getContentResolver(), lastPathSegment);
                    BydLogHelper.logD("screenValue:" + i);
                    CarController carController = (CarController) Dependency.get(CarController.class);
                    int i2 = Settings.Global.getInt(StatusBar.this.mContext.getContentResolver(), Constants.SYSUI_ROTATION_AUTO, 0);
                    BydLogHelper.logD("autoRotateValue:" + i2);
                    boolean z = i2 == 0;
                    boolean zIsAndroidAutoFocus = carController.isAndroidAutoFocus();
                    boolean zIsCarPlayFocus = carController.isCarPlayFocus();
                    BydLogHelper.logD("android auto aaFocus:" + zIsAndroidAutoFocus);
                    BydLogHelper.logD("carplay cpFocus:" + zIsCarPlayFocus);
                    if (!z) {
                        BydLogHelper.logD("auto rotate is close");
                        if ((zIsAndroidAutoFocus || zIsCarPlayFocus) && i == 0) {
                            StatusBar statusBar = StatusBar.this;
                            if (!statusBar.isLand(statusBar.mContext)) {
                                carController.setPadRotation(1);
                                ((Handler) Dependency.get(Dependency.MAIN_HANDLER)).postDelayed(new Runnable() { // from class: com.android.systemui.statusbar.phone.-$$Lambda$StatusBar$RotateSettingsObserver$YIUXsTJHVTc45GpOQSgGqlNpcvI
                                    @Override // java.lang.Runnable
                                    public final void run() {
                                        this.f$0.lambda$onChange$1$StatusBar$RotateSettingsObserver();
                                    }
                                }, 200L);
                            }
                        }
                    } else if (i == 1) {
                        StatusBar statusBar2 = StatusBar.this;
                        if (statusBar2.isLand(statusBar2.mContext)) {
                            carController.setPadRotation(2);
                        }
                    } else if (i == 0) {
                        StatusBar statusBar3 = StatusBar.this;
                        if (!statusBar3.isLand(statusBar3.mContext)) {
                            carController.setPadRotation(1);
                            if (zIsAndroidAutoFocus || zIsCarPlayFocus) {
                                ((Handler) Dependency.get(Dependency.MAIN_HANDLER)).postDelayed(new Runnable() { // from class: com.android.systemui.statusbar.phone.-$$Lambda$StatusBar$RotateSettingsObserver$aeacmq1306IQRxr1_PWSTzLkUQs
                                    @Override // java.lang.Runnable
                                    public final void run() {
                                        this.f$0.lambda$onChange$0$StatusBar$RotateSettingsObserver();
                                    }
                                }, 200L);
                            }
                        }
                    }
                } catch (Settings.SettingNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }

        public /* synthetic */ void lambda$onChange$0$StatusBar$RotateSettingsObserver() {
            StatusBar statusBar = StatusBar.this;
            statusBar.showToast(statusBar.mContext.getString(com.android.systemui.R.string.toast_android_auto_for_rotate));
        }

        public /* synthetic */ void lambda$onChange$1$StatusBar$RotateSettingsObserver() {
            StatusBar statusBar = StatusBar.this;
            statusBar.showToast(statusBar.mContext.getString(com.android.systemui.R.string.toast_android_auto_for_rotate));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    class ScreenOffObserver extends ContentObserver {
        public ScreenOffObserver(Handler handler) {
            super(handler);
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean selfChange, Uri uri) {
            String lastPathSegment = uri.getLastPathSegment();
            BydLogHelper.logD("onChange selfChange : " + selfChange + " , option : " + lastPathSegment);
            if (lastPathSegment.equals("navibar_mode_on_screensaver")) {
                final int i = Settings.Global.getInt(StatusBar.this.mContext.getContentResolver(), "navibar_mode_on_screensaver", 0);
                Settings.Global.putInt(StatusBar.this.mContext.getContentResolver(), Constants.IS_IN_SCREEN_SAVE_MODE, i != 1 ? 1 : 0);
                BydLogHelper.logD("onChange screenOffType : " + i);
                ((Handler) Dependency.get(Dependency.MAIN_HANDLER)).post(new Runnable() { // from class: com.android.systemui.statusbar.phone.-$$Lambda$StatusBar$ScreenOffObserver$GJ4uxw5yIUHFsIOddxJK_9_i6Cs
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$onChange$0$StatusBar$ScreenOffObserver(i);
                    }
                });
            }
        }

        public /* synthetic */ void lambda$onChange$0$StatusBar$ScreenOffObserver(int i) {
            StatusBar.this.updateNaviBarStatus(i);
            ((CollapsedStatusBarFragment) FragmentHostManager.get(StatusBar.this.mStatusBarWindow).getFragmentManager().findFragmentByTag(CollapsedStatusBarFragment.TAG)).updateNaviBarStatus(i);
            if (i == 2) {
                StatusBar.this.bindBydCarSettingsService();
            }
        }
    }

    public boolean isLand(Context context) {
        int i = context.getResources().getConfiguration().orientation;
        Log.d(TAG, "orientation " + i);
        return i != 1;
    }

    protected void showToast(String text) {
        int dimensionPixelSize;
        Toast toast = this.mToast;
        if (toast != null) {
            toast.cancel();
        }
        this.mToast = new Toast(this.mContext);
        Resources resources = this.mContext.getResources();
        if (isLand(this.mContext)) {
            dimensionPixelSize = resources.getDimensionPixelSize(com.android.systemui.R.dimen.dp_72);
        } else {
            dimensionPixelSize = resources.getDimensionPixelSize(com.android.systemui.R.dimen.dp_190);
        }
        View viewInflate = LayoutInflater.from(this.mContext).inflate(com.android.systemui.R.layout.view_custom_toast, (ViewGroup) null);
        TextView textView = (TextView) viewInflate.findViewById(com.android.systemui.R.id.tv_content);
        this.mToast.setView(viewInflate);
        this.mToast.setGravity(48, 0, dimensionPixelSize);
        textView.setText(text);
        this.mToast.setDuration(0);
        this.mToast.show();
    }

    public void dismissVolumeDialog() {
        VolumeComponent volumeComponent = (VolumeComponent) SysUiServiceProvider.getComponent(this.mContext, VolumeComponent.class);
        if (volumeComponent != null) {
            BydLogHelper.logD();
            volumeComponent.dismiss();
        }
    }

    public void updateNaviBarBg(boolean splitScreenStatus) {
        this.mNavigationBar.updateNaviBarBg(splitScreenStatus);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateNaviBarStatus(int screenOffType) {
        this.mNavigationBar.updateNaviBarStatus(screenOffType);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bindBydCarSettingsService() {
        this.mNavigationBar.bindBydCarSettingsService();
    }

    public void updateStatusBarIcon() {
        CollapsedStatusBarFragment collapsedStatusBarFragment = (CollapsedStatusBarFragment) FragmentHostManager.get(this.mStatusBarWindow).getFragmentManager().findFragmentByTag(CollapsedStatusBarFragment.TAG);
        if (collapsedStatusBarFragment != null) {
            collapsedStatusBarFragment.changeIcon();
        }
    }

    public long getLastClickNightModeTime() {
        return this.lastClickNightModeTime;
    }

    public void setLastClickNightModeTime(long lastClickNightModeTime) {
        this.lastClickNightModeTime = lastClickNightModeTime;
    }

    public void removeChangeIconMsg() {
        this.lastClickNightModeTime = System.currentTimeMillis();
        CollapsedStatusBarFragment collapsedStatusBarFragment = (CollapsedStatusBarFragment) FragmentHostManager.get(this.mStatusBarWindow).getFragmentManager().findFragmentByTag(CollapsedStatusBarFragment.TAG);
        if (collapsedStatusBarFragment != null) {
            collapsedStatusBarFragment.removeMsg();
        }
    }

    public void updateStatusBarBgOnSplit(boolean isSplit) {
        CollapsedStatusBarFragment collapsedStatusBarFragment = (CollapsedStatusBarFragment) FragmentHostManager.get(this.mStatusBarWindow).getFragmentManager().findFragmentByTag(CollapsedStatusBarFragment.TAG);
        if (collapsedStatusBarFragment != null) {
            collapsedStatusBarFragment.updateStatusBarView(isSplit);
        }
    }

    public void updateStatusBarColorBySplit() {
        CollapsedStatusBarFragment collapsedStatusBarFragment = (CollapsedStatusBarFragment) FragmentHostManager.get(this.mStatusBarWindow).getFragmentManager().findFragmentByTag(CollapsedStatusBarFragment.TAG);
        if (collapsedStatusBarFragment != null) {
            collapsedStatusBarFragment.updateStatusBarColorBySplit();
        }
    }

    public void updateStatusBarCPAA(boolean isShow) {
        CollapsedStatusBarFragment collapsedStatusBarFragment = (CollapsedStatusBarFragment) FragmentHostManager.get(this.mStatusBarWindow).getFragmentManager().findFragmentByTag(CollapsedStatusBarFragment.TAG);
        if (collapsedStatusBarFragment != null) {
            collapsedStatusBarFragment.updateStatusColor(isShow);
        }
    }

    public void updateStatusBarIconSplitHelpView() {
        CollapsedStatusBarFragment collapsedStatusBarFragment = (CollapsedStatusBarFragment) FragmentHostManager.get(this.mStatusBarWindow).getFragmentManager().findFragmentByTag(CollapsedStatusBarFragment.TAG);
        if (collapsedStatusBarFragment != null) {
            collapsedStatusBarFragment.showDefaultStatusBarView();
        }
    }

    public void updateStatusBarIconSplitHelpViewExit() {
        new QueryTopActivityTask().execute(new Void[0]);
    }

    public class QueryTopActivityTask extends AsyncTask<Void, Void, ActivityManager.RunningTaskInfo> {
        public QueryTopActivityTask() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public ActivityManager.RunningTaskInfo doInBackground(Void... voids) {
            return AppUtils.getRunningTask(StatusBar.this.mContext);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(ActivityManager.RunningTaskInfo taskInfo) {
            ComponentName componentName;
            int i;
            super.onPostExecute(taskInfo);
            if (taskInfo != null) {
                componentName = taskInfo.topActivity;
                i = taskInfo.displayId;
            } else {
                componentName = null;
                i = 0;
            }
            CollapsedStatusBarFragment collapsedStatusBarFragment = (CollapsedStatusBarFragment) FragmentHostManager.get(StatusBar.this.mStatusBarWindow).getFragmentManager().findFragmentByTag(CollapsedStatusBarFragment.TAG);
            if (collapsedStatusBarFragment != null) {
                collapsedStatusBarFragment.onTopActivityChanged(componentName, i);
            }
        }
    }

    public boolean isScreenShotWindowShowing() {
        return this.mIsScreenShotWindowShowing;
    }

    public void setIsScreenShotWindowShowing(boolean screenShotWindowShowing) {
        Log.i(TAG, "setIsScreenShotWindowShowing " + screenShotWindowShowing);
        this.mIsScreenShotWindowShowing = screenShotWindowShowing;
    }

    public void onNavigationBarClicked(boolean isLongClick) {
        CollapsedStatusBarFragment collapsedStatusBarFragment = (CollapsedStatusBarFragment) FragmentHostManager.get(this.mStatusBarWindow).getFragmentManager().findFragmentByTag(CollapsedStatusBarFragment.TAG);
        if (collapsedStatusBarFragment != null) {
            collapsedStatusBarFragment.onNavigationBarClicked(isLongClick);
        }
    }
}
