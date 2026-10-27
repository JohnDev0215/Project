package com.android.launcher3;

import android.Manifest;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.ActivityOptions;
import android.app.WallpaperManager;
import android.appwidget.AppWidgetHostView;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.os.Process;
import android.text.TextUtils;
import android.text.method.TextKeyListener;
import android.util.Log;
import android.util.SparseArray;
import android.util.TimedRemoteCaller;
import android.view.Display;
import android.view.KeyEvent;
import android.view.KeyboardShortcutGroup;
import android.view.KeyboardShortcutInfo;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.animation.OvershootInterpolator;
import android.widget.Toast;
import com.android.launcher3.DropTarget;
import com.android.launcher3.InvariantDeviceProfile;
import com.android.launcher3.LauncherModel;
import com.android.launcher3.LauncherStateManager;
import com.android.launcher3.accessibility.LauncherAccessibilityDelegate;
import com.android.launcher3.allapps.AllAppsContainerView;
import com.android.launcher3.allapps.AllAppsTransitionController;
import com.android.launcher3.allapps.DiscoveryBounce;
import com.android.launcher3.anim.PropertyListBuilder;
import com.android.launcher3.compat.AppWidgetManagerCompat;
import com.android.launcher3.compat.LauncherAppsCompatVO;
import com.android.launcher3.config.FeatureFlags;
import com.android.launcher3.dot.DotInfo;
import com.android.launcher3.dragndrop.DragController;
import com.android.launcher3.dragndrop.DragLayer;
import com.android.launcher3.dragndrop.DragView;
import com.android.launcher3.folder.Folder;
import com.android.launcher3.folder.FolderIcon;
import com.android.launcher3.folder.FolderIconPreviewVerifier;
import com.android.launcher3.folder.SelectedFolderApp;
import com.android.launcher3.graphics.RotationMode;
import com.android.launcher3.icons.IconCache;
import com.android.launcher3.keyboard.CustomActionsPopup;
import com.android.launcher3.keyboard.ViewGroupFocusHelper;
import com.android.launcher3.logging.FileLog;
import com.android.launcher3.logging.LoggerUtils;
import com.android.launcher3.logging.UserEventDispatcher;
import com.android.launcher3.model.AppLaunchTracker;
import com.android.launcher3.model.ModelWriter;
import com.android.launcher3.notification.NotificationListener;
import com.android.launcher3.popup.PopupContainerWithArrow;
import com.android.launcher3.popup.PopupDataProvider;
import com.android.launcher3.shortcuts.DeepShortcutManager;
import com.android.launcher3.states.InternalStateHandler;
import com.android.launcher3.states.RotationHelper;
import com.android.launcher3.testing.TestProtocol;
import com.android.launcher3.touch.ItemClickHandler;
import com.android.launcher3.uioverrides.UiFactory;
import com.android.launcher3.userevent.nano.LauncherLogProto;
import com.android.launcher3.util.ActivityResultInfo;
import com.android.launcher3.util.ComponentKey;
import com.android.launcher3.util.IntArray;
import com.android.launcher3.util.ItemInfoMatcher;
import com.android.launcher3.util.MultiValueAlpha;
import com.android.launcher3.util.PackageManagerHelper;
import com.android.launcher3.util.PackageUserKey;
import com.android.launcher3.util.PendingRequestArgs;
import com.android.launcher3.util.RaceConditionTracker;
import com.android.launcher3.util.Themes;
import com.android.launcher3.util.TraceHelper;
import com.android.launcher3.util.UiThreadHelper;
import com.android.launcher3.util.ViewOnDrawExecutor;
import com.android.launcher3.views.ActivityContext;
import com.android.launcher3.views.OptionsPopupView;
import com.android.launcher3.views.ScrimView;
import com.android.launcher3.widget.LauncherAppWidgetHostView;
import com.android.launcher3.widget.PendingAddShortcutInfo;
import com.android.launcher3.widget.PendingAddWidgetInfo;
import com.android.launcher3.widget.PendingAppWidgetHostView;
import com.android.launcher3.widget.WidgetAddFlowHandler;
import com.android.launcher3.widget.WidgetHostViewLoader;
import com.android.launcher3.widget.WidgetListRowEntry;
import com.android.launcher3.widget.WidgetsFullSheet;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

/* JADX INFO: loaded from: classes4.dex */
public class Launcher extends BaseDraggingActivity implements LauncherExterns, LauncherModel.Callbacks, LauncherProviderChangeListener, UserEventDispatcher.UserEventDelegate, InvariantDeviceProfile.OnIDPChangeListener {
    private static final int APPS_VIEW_ALPHA_CHANNEL_INDEX = 1;
    private static final float BOUNCE_ANIMATION_TENSION = 1.3f;
    static final boolean DEBUG_STRICT_MODE = false;
    static final boolean LOGD = false;
    static final int NEW_APPS_ANIMATION_DELAY = 500;
    private static final int NEW_APPS_ANIMATION_INACTIVE_TIMEOUT_SECONDS = 5;
    private static final int NEW_APPS_PAGE_MOVE_DELAY = 500;
    private static final int ON_ACTIVITY_RESULT_ANIMATION_DELAY = 500;
    public static final String ON_CREATE_EVT = "Launcher.onCreate";
    private static final String ON_RESUME_EVT = "Launcher.onResume";
    private static final String ON_START_EVT = "Launcher.onStart";
    private static final int REQUEST_BIND_APPWIDGET = 11;
    public static final int REQUEST_BIND_PENDING_APPWIDGET = 12;
    private static final int REQUEST_CREATE_APPWIDGET = 5;
    private static final int REQUEST_CREATE_SHORTCUT = 1;
    protected static final int REQUEST_LAST = 100;
    private static final int REQUEST_PERMISSION_CALL_PHONE = 14;
    private static final int REQUEST_PICK_APPWIDGET = 9;
    public static final int REQUEST_RECONFIGURE_APPWIDGET = 13;
    private static final String RUNTIME_STATE = "launcher.state";
    private static final String RUNTIME_STATE_CURRENT_SCREEN = "launcher.current_screen";
    private static final String RUNTIME_STATE_PENDING_ACTIVITY_RESULT = "launcher.activity_result";
    private static final String RUNTIME_STATE_PENDING_REQUEST_ARGS = "launcher.request_args";
    private static final String RUNTIME_STATE_PENDING_REQUEST_CODE = "launcher.request_code";
    private static final String RUNTIME_STATE_WIDGET_PANEL = "launcher.widget_panel";
    private static final int SCRIM_VIEW_ALPHA_CHANNEL_INDEX = 0;
    public static final String TAG = "Launcher";
    private LauncherAccessibilityDelegate mAccessibilityDelegate;
    AllAppsTransitionController mAllAppsController;
    private LauncherAppTransitionManager mAppTransitionManager;
    private LauncherAppWidgetHost mAppWidgetHost;
    private AppWidgetManagerCompat mAppWidgetManager;
    AllAppsContainerView mAppsView;
    private Runnable mCancelTouchController;
    private boolean mDeferredResumePending;
    private DragController mDragController;
    DragLayer mDragLayer;
    private DropTargetBar mDropTargetBar;
    public ViewGroupFocusHelper mFocusHandler;
    Hotseat mHotseat;
    private IconCache mIconCache;
    private LauncherCallbacks mLauncherCallbacks;
    private View mLauncherView;
    private LauncherModel mModel;
    private ModelWriter mModelWriter;
    private Configuration mOldConfig;
    private View mOverviewPanel;
    private ActivityResultInfo mPendingActivityResult;
    private ViewOnDrawExecutor mPendingExecutor;
    private PendingRequestArgs mPendingRequestArgs;
    private PopupDataProvider mPopupDataProvider;
    private RotationHelper mRotationHelper;
    ScrimView mScrimView;
    private SharedPreferences mSharedPrefs;
    private DeviceProfile mStableDeviceProfile;
    private LauncherStateManager mStateManager;
    Workspace mWorkspace;
    private SelectedFolderApp selectedFolderApp;
    private WallpaperManager wallpaperManager;
    private final int[] mTmpAddItemCellCoordinates = new int[2];
    boolean mWorkspaceLoading = true;
    private ArrayList<OnResumeCallback> mOnResumeCallbacks = new ArrayList<>();
    private int mSynchronouslyBoundPage = -1;
    private int mPendingActivityRequestCode = -1;
    final Handler mHandler = new Handler();
    private final Runnable mHandleDeferredResume = new Runnable() { // from class: com.android.launcher3.-$$Lambda$Launcher$5A2khOOqQOozgkrbYUfN9v7nSE4
        @Override // java.lang.Runnable
        public final void run() {
            this.f$0.handleDeferredResume();
        }
    };
    private float mCurrentAssistantVisibility = 0.0f;
    private RotationMode mRotationMode = RotationMode.NORMAL;
    private final BroadcastReceiver mScreenOffReceiver = new BroadcastReceiver() { // from class: com.android.launcher3.Launcher.6
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (Launcher.this.mPendingRequestArgs == null) {
                Launcher.this.mStateManager.goToState(LauncherState.NORMAL);
            }
        }
    };

    public interface LauncherOverlay {
        void onScrollChange(float progress, boolean rtl);

        void onScrollInteractionBegin();

        void onScrollInteractionEnd();

        void setOverlayCallbacks(LauncherOverlayCallbacks callbacks);
    }

    public interface LauncherOverlayCallbacks {
        void onScrollChanged(float progress);
    }

    public interface OnResumeCallback {
        void onLauncherResume();
    }

    @Override // com.android.launcher3.BaseDraggingActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        RaceConditionTracker.onEvent(ON_CREATE_EVT, true);
        TraceHelper.beginSection("Launcher-onCreate");
        super.onCreate(savedInstanceState);
        TraceHelper.partitionSection("Launcher-onCreate", "super call");
        LauncherAppState launcherAppState = LauncherAppState.getInstance(this);
        this.mOldConfig = new Configuration(getResources().getConfiguration());
        this.mModel = launcherAppState.setLauncher(this);
        this.mRotationHelper = new RotationHelper(this);
        InvariantDeviceProfile invariantDeviceProfile = launcherAppState.getInvariantDeviceProfile();
        initDeviceProfile(invariantDeviceProfile);
        invariantDeviceProfile.addOnChangeListener(this);
        this.mSharedPrefs = Utilities.getPrefs(this);
        this.mIconCache = launcherAppState.getIconCache();
        this.mAccessibilityDelegate = new LauncherAccessibilityDelegate(this);
        this.mDragController = new DragController(this);
        this.mAllAppsController = new AllAppsTransitionController(this);
        this.mStateManager = new LauncherStateManager(this);
        UiFactory.onCreate(this);
        this.mAppWidgetManager = AppWidgetManagerCompat.getInstance(this);
        LauncherAppWidgetHost launcherAppWidgetHost = new LauncherAppWidgetHost(this);
        this.mAppWidgetHost = launcherAppWidgetHost;
        launcherAppWidgetHost.startListening();
        this.mLauncherView = LayoutInflater.from(this).inflate(R.layout.launcher, (ViewGroup) null);
        setupViews();
        this.mPopupDataProvider = new PopupDataProvider(this);
        this.mAppTransitionManager = LauncherAppTransitionManager.newInstance(this);
        boolean zHandleCreate = InternalStateHandler.handleCreate(this, getIntent());
        if (zHandleCreate && savedInstanceState != null) {
            savedInstanceState.remove(RUNTIME_STATE);
        }
        restoreState(savedInstanceState);
        this.mStateManager.reapplyState();
        int i = savedInstanceState != null ? savedInstanceState.getInt(RUNTIME_STATE_CURRENT_SCREEN, -1001) : -1001;
        if (this.mModel.startLoader(i)) {
            this.mWorkspace.setCurrentPage(i);
            setWorkspaceLoading(true);
        } else if (!zHandleCreate) {
            this.mDragLayer.getAlphaProperty(1).setValue(0.0f);
        }
        setDefaultKeyMode(3);
        setContentView(this.mLauncherView);
        getRootView().dispatchInsets();
        registerReceiver(this.mScreenOffReceiver, new IntentFilter(Intent.ACTION_SCREEN_OFF));
        getSystemUiController().updateUiState(0, Themes.getAttrBoolean(this, R.attr.isWorkspaceDarkText));
        LauncherCallbacks launcherCallbacks = this.mLauncherCallbacks;
        if (launcherCallbacks != null) {
            launcherCallbacks.onCreate(savedInstanceState);
        }
        this.mRotationHelper.initialize();
        TraceHelper.endSection("Launcher-onCreate");
        RaceConditionTracker.onEvent(ON_CREATE_EVT, false);
        this.mStateManager.addStateListener(new LauncherStateManager.StateListener() { // from class: com.android.launcher3.Launcher.1
            @Override // com.android.launcher3.LauncherStateManager.StateListener
            public void onStateTransitionStart(LauncherState toState) {
            }

            @Override // com.android.launcher3.LauncherStateManager.StateListener
            public void onStateTransitionComplete(LauncherState finalState) {
                float f = 1.0f - Launcher.this.mCurrentAssistantVisibility;
                if (finalState == LauncherState.NORMAL) {
                    Launcher.this.mAppsView.getAlphaProperty(1).setValue(f);
                    return;
                }
                if (finalState == LauncherState.OVERVIEW || finalState == LauncherState.OVERVIEW_PEEK) {
                    Launcher.this.mAppsView.getAlphaProperty(1).setValue(f);
                    Launcher.this.mScrimView.getAlphaProperty(0).setValue(f);
                } else {
                    Launcher.this.mAppsView.getAlphaProperty(1).setValue(1.0f);
                    Launcher.this.mScrimView.getAlphaProperty(0).setValue(1.0f);
                }
            }
        });
    }

    @Override // android.app.Activity
    public void onEnterAnimationComplete() {
        super.onEnterAnimationComplete();
        UiFactory.onEnterAnimationComplete(this);
        this.mAllAppsController.highlightWorkTabIfNecessary();
        this.mRotationHelper.setCurrentTransitionRequest(0);
    }

    @Override // com.android.launcher3.BaseDraggingActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration newConfig) {
        int iDiff = newConfig.diff(this.mOldConfig);
        if ((iDiff & 4) != 0) {
            Folder.setLocaleDependentFields(getResources(), true);
        }
        if ((iDiff & 1152) != 0) {
            onIdpChanged(this.mDeviceProfile.inv);
        }
        this.mOldConfig.setTo(newConfig);
        UiFactory.onLauncherStateOrResumeChanged(this);
        super.onConfigurationChanged(newConfig);
    }

    public void reload() {
        onIdpChanged(this.mDeviceProfile.inv);
    }

    private boolean supportsFakeLandscapeUI() {
        return FeatureFlags.FAKE_LANDSCAPE_UI.get() && !this.mRotationHelper.homeScreenCanRotate();
    }

    @Override // com.android.launcher3.BaseDraggingActivity
    public void reapplyUi() {
        if (supportsFakeLandscapeUI()) {
            this.mRotationMode = this.mStableDeviceProfile == null ? RotationMode.NORMAL : UiFactory.getRotationMode(this.mDeviceProfile);
        }
        getRootView().dispatchInsets();
        getStateManager().reapplyState(true);
    }

    @Override // com.android.launcher3.LauncherModel.Callbacks
    public void rebindModel() {
        int nextPage = this.mWorkspace.getNextPage();
        if (this.mModel.startLoader(nextPage)) {
            this.mWorkspace.setCurrentPage(nextPage);
            setWorkspaceLoading(true);
        }
    }

    @Override // com.android.launcher3.InvariantDeviceProfile.OnIDPChangeListener
    public void onIdpChanged(int changeFlags, InvariantDeviceProfile idp) {
        onIdpChanged(idp);
    }

    private void onIdpChanged(InvariantDeviceProfile idp) {
        this.mUserEventDispatcher = null;
        DeviceProfile wallpaperDeviceProfile = getWallpaperDeviceProfile();
        initDeviceProfile(idp);
        dispatchDeviceProfileChanged();
        reapplyUi();
        this.mDragLayer.recreateControllers();
        onSaveInstanceState(new Bundle());
        if (wallpaperDeviceProfile != getWallpaperDeviceProfile()) {
            rebindModel();
        }
    }

    public void onAssistantVisibilityChanged(float visibility) {
        this.mCurrentAssistantVisibility = visibility;
        float f = 1.0f - visibility;
        LauncherState state = this.mStateManager.getState();
        if (state == LauncherState.NORMAL) {
            this.mAppsView.getAlphaProperty(1).setValue(f);
        } else if (state == LauncherState.OVERVIEW || state == LauncherState.OVERVIEW_PEEK) {
            this.mAppsView.getAlphaProperty(1).setValue(f);
            this.mScrimView.getAlphaProperty(0).setValue(f);
        }
    }

    private void initDeviceProfile(InvariantDeviceProfile idp) {
        this.mDeviceProfile = idp.getDeviceProfile(this);
        if (isInMultiWindowMode()) {
            Display defaultDisplay = getWindowManager().getDefaultDisplay();
            Point point = new Point();
            defaultDisplay.getSize(point);
            this.mDeviceProfile = this.mDeviceProfile.getMultiWindowProfile(this, point);
        }
        if (supportsFakeLandscapeUI() && this.mDeviceProfile.isVerticalBarLayout()) {
            this.mStableDeviceProfile = this.mDeviceProfile.inv.portraitProfile;
            this.mRotationMode = UiFactory.getRotationMode(this.mDeviceProfile);
        } else {
            this.mStableDeviceProfile = null;
            this.mRotationMode = RotationMode.NORMAL;
        }
        this.mRotationHelper.updateRotationAnimation();
        onDeviceProfileInitiated();
        this.mModelWriter = this.mModel.getWriter(getWallpaperDeviceProfile().isVerticalBarLayout(), true);
    }

    public void updateInsets(Rect insets) {
        this.mDeviceProfile.updateInsets(insets);
        DeviceProfile deviceProfile = this.mStableDeviceProfile;
        if (deviceProfile != null) {
            Rect insets2 = deviceProfile.getInsets();
            this.mRotationMode.mapInsets(this, insets, insets2);
            this.mStableDeviceProfile.updateInsets(insets2);
        }
    }

    @Override // com.android.launcher3.views.ActivityContext
    public RotationMode getRotationMode() {
        return this.mRotationMode;
    }

    @Override // com.android.launcher3.views.ActivityContext
    public DeviceProfile getWallpaperDeviceProfile() {
        DeviceProfile deviceProfile = this.mStableDeviceProfile;
        return deviceProfile == null ? this.mDeviceProfile : deviceProfile;
    }

    public RotationHelper getRotationHelper() {
        return this.mRotationHelper;
    }

    public LauncherStateManager getStateManager() {
        return this.mStateManager;
    }

    @Override // android.app.Activity
    public <T extends View> T findViewById(int i) {
        return (T) this.mLauncherView.findViewById(i);
    }

    @Override // com.android.launcher3.LauncherProviderChangeListener
    public void onAppWidgetHostReset() {
        LauncherAppWidgetHost launcherAppWidgetHost = this.mAppWidgetHost;
        if (launcherAppWidgetHost != null) {
            launcherAppWidgetHost.startListening();
        }
    }

    @Override // com.android.launcher3.LauncherExterns
    public void setLauncherOverlay(LauncherOverlay overlay) {
        if (overlay != null) {
            overlay.setOverlayCallbacks(new LauncherOverlayCallbacksImpl());
        }
        this.mWorkspace.setLauncherOverlay(overlay);
    }

    @Override // com.android.launcher3.LauncherExterns
    public boolean setLauncherCallbacks(LauncherCallbacks callbacks) {
        this.mLauncherCallbacks = callbacks;
        return true;
    }

    @Override // com.android.launcher3.LauncherProviderChangeListener
    public void onLauncherProviderChanged() {
        LauncherCallbacks launcherCallbacks = this.mLauncherCallbacks;
        if (launcherCallbacks != null) {
            launcherCallbacks.onLauncherProviderChange();
        }
    }

    public boolean isDraggingEnabled() {
        return !isWorkspaceLoading();
    }

    public PopupDataProvider getPopupDataProvider() {
        return this.mPopupDataProvider;
    }

    @Override // com.android.launcher3.views.ActivityContext
    public DotInfo getDotInfoForItem(ItemInfo info) {
        return this.mPopupDataProvider.getDotInfoForItem(info);
    }

    @Override // com.android.launcher3.views.ActivityContext
    public void invalidateParent(ItemInfo info) {
        if (info.container >= 0) {
            View homescreenIconByItemId = getWorkspace().getHomescreenIconByItemId(info.container);
            if ((homescreenIconByItemId instanceof FolderIcon) && (homescreenIconByItemId.getTag() instanceof FolderInfo)) {
                FolderIconPreviewVerifier folderIconPreviewVerifier = new FolderIconPreviewVerifier(getDeviceProfile().inv);
                folderIconPreviewVerifier.setFolderInfo((FolderInfo) homescreenIconByItemId.getTag());
                if (folderIconPreviewVerifier.isItemInPreview(info.rank)) {
                    homescreenIconByItemId.invalidate();
                }
            }
        }
    }

    private int completeAdd(int requestCode, Intent intent, int appWidgetId, PendingRequestArgs info) {
        LauncherAppWidgetProviderInfo launcherAppWidgetInfo;
        int iEnsurePendingDropLayoutExists = info.screenId;
        if (info.container == -100) {
            iEnsurePendingDropLayoutExists = ensurePendingDropLayoutExists(info.screenId);
        }
        if (requestCode == 1) {
            completeAddShortcut(intent, info.container, iEnsurePendingDropLayoutExists, info.cellX, info.cellY, info);
        } else if (requestCode == 5) {
            completeAddAppWidget(appWidgetId, info, null, null);
        } else if (requestCode == 12) {
            LauncherAppWidgetInfo launcherAppWidgetInfoCompleteRestoreAppWidget = completeRestoreAppWidget(appWidgetId, 4);
            if (launcherAppWidgetInfoCompleteRestoreAppWidget != null && (launcherAppWidgetInfo = this.mAppWidgetManager.getLauncherAppWidgetInfo(appWidgetId)) != null) {
                new WidgetAddFlowHandler(launcherAppWidgetInfo).startConfigActivity(this, launcherAppWidgetInfoCompleteRestoreAppWidget, 13);
            }
        } else if (requestCode == 13) {
            completeRestoreAppWidget(appWidgetId, 0);
        }
        return iEnsurePendingDropLayoutExists;
    }

    private void handleActivityResult(final int requestCode, final int resultCode, final Intent data) {
        if (isWorkspaceLoading()) {
            this.mPendingActivityResult = new ActivityResultInfo(requestCode, resultCode, data);
            return;
        }
        this.mPendingActivityResult = null;
        final PendingRequestArgs pendingRequestArgs = this.mPendingRequestArgs;
        setWaitingForResult(null);
        if (pendingRequestArgs == null) {
            return;
        }
        int widgetId = pendingRequestArgs.getWidgetId();
        Runnable runnable = new Runnable() { // from class: com.android.launcher3.Launcher.2
            @Override // java.lang.Runnable
            public void run() {
                Launcher.this.mStateManager.goToState(LauncherState.NORMAL, 500L);
            }
        };
        if (requestCode == 11) {
            int intExtra = data != null ? data.getIntExtra("appWidgetId", -1) : -1;
            if (resultCode == 0) {
                completeTwoStageWidgetDrop(0, intExtra, pendingRequestArgs);
                this.mWorkspace.removeExtraEmptyScreenDelayed(true, runnable, 500, false);
                return;
            } else {
                if (resultCode == -1) {
                    addAppWidgetImpl(intExtra, pendingRequestArgs, null, pendingRequestArgs.getWidgetHandler(), 500);
                    return;
                }
                return;
            }
        }
        if (requestCode == 9 || requestCode == 5) {
            final int intExtra2 = data != null ? data.getIntExtra("appWidgetId", -1) : -1;
            if (intExtra2 < 0) {
                intExtra2 = widgetId;
            }
            if (intExtra2 < 0 || resultCode == 0) {
                Log.e(TAG, "Error: appWidgetId (EXTRA_APPWIDGET_ID) was not returned from the widget configuration activity.");
                completeTwoStageWidgetDrop(0, intExtra2, pendingRequestArgs);
                this.mWorkspace.removeExtraEmptyScreenDelayed(true, new Runnable() { // from class: com.android.launcher3.Launcher.3
                    @Override // java.lang.Runnable
                    public void run() {
                        Launcher.this.getStateManager().goToState(LauncherState.NORMAL);
                    }
                }, 500, false);
                return;
            } else {
                if (pendingRequestArgs.container == -100) {
                    pendingRequestArgs.screenId = ensurePendingDropLayoutExists(pendingRequestArgs.screenId);
                }
                final CellLayout screenWithId = this.mWorkspace.getScreenWithId(pendingRequestArgs.screenId);
                screenWithId.setDropPending(true);
                this.mWorkspace.removeExtraEmptyScreenDelayed(true, new Runnable() { // from class: com.android.launcher3.Launcher.4
                    @Override // java.lang.Runnable
                    public void run() {
                        Launcher.this.completeTwoStageWidgetDrop(resultCode, intExtra2, pendingRequestArgs);
                        screenWithId.setDropPending(false);
                    }
                }, 500, false);
                return;
            }
        }
        if (requestCode == 13 || requestCode == 12) {
            if (resultCode == -1) {
                completeAdd(requestCode, data, widgetId, pendingRequestArgs);
                return;
            }
            return;
        }
        if (requestCode == 1) {
            if (resultCode == -1 && pendingRequestArgs.container != -1) {
                completeAdd(requestCode, data, -1, pendingRequestArgs);
                this.mWorkspace.removeExtraEmptyScreenDelayed(true, runnable, 500, false);
            } else if (resultCode == 0) {
                this.mWorkspace.removeExtraEmptyScreenDelayed(true, runnable, 500, false);
            }
        }
        this.mDragLayer.clearAnimatedView();
    }

    @Override // com.android.launcher3.BaseActivity, android.app.Activity
    public void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        this.mPendingActivityRequestCode = -1;
        handleActivityResult(requestCode, resultCode, data);
        LauncherCallbacks launcherCallbacks = this.mLauncherCallbacks;
        if (launcherCallbacks != null) {
            launcherCallbacks.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override // android.app.Activity, androidx.core.app.ActivityCompat.OnRequestPermissionsResultCallback
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        PendingRequestArgs pendingRequestArgs = this.mPendingRequestArgs;
        if (requestCode == 14 && pendingRequestArgs != null && pendingRequestArgs.getRequestCode() == 14) {
            setWaitingForResult(null);
            CellLayout cellLayout = getCellLayout(pendingRequestArgs.container, pendingRequestArgs.screenId);
            View childAt = cellLayout != null ? cellLayout.getChildAt(pendingRequestArgs.cellX, pendingRequestArgs.cellY) : null;
            Intent pendingIntent = pendingRequestArgs.getPendingIntent();
            if (grantResults.length > 0 && grantResults[0] == 0) {
                lambda$startActivitySafely$0$Launcher(childAt, pendingIntent, null, null);
            } else {
                Toast.makeText(this, getString(R.string.msg_no_phone_permission, getString(R.string.derived_app_name)), 0).show();
            }
        }
        LauncherCallbacks launcherCallbacks = this.mLauncherCallbacks;
        if (launcherCallbacks != null) {
            launcherCallbacks.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    private int ensurePendingDropLayoutExists(int screenId) {
        if (this.mWorkspace.getScreenWithId(screenId) != null) {
            return screenId;
        }
        this.mWorkspace.addExtraEmptyScreen();
        return this.mWorkspace.commitExtraEmptyScreen();
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    void completeTwoStageWidgetDrop(final int resultCode, final int appWidgetId, final PendingRequestArgs requestArgs) {
        int i;
        int i2;
        Runnable runnable;
        AppWidgetHostView appWidgetHostView;
        CellLayout screenWithId = this.mWorkspace.getScreenWithId(requestArgs.screenId);
        if (resultCode == -1) {
            final AppWidgetHostView appWidgetHostViewCreateView = this.mAppWidgetHost.createView((Context) this, appWidgetId, requestArgs.getWidgetHandler().getProviderInfo(this));
            i2 = 3;
            appWidgetHostView = appWidgetHostViewCreateView;
            runnable = new Runnable() { // from class: com.android.launcher3.Launcher.5
                @Override // java.lang.Runnable
                public void run() {
                    Launcher.this.completeAddAppWidget(appWidgetId, requestArgs, appWidgetHostViewCreateView, null);
                    Launcher.this.mStateManager.goToState(LauncherState.NORMAL, 500L);
                }
            };
        } else {
            if (resultCode == 0) {
                this.mAppWidgetHost.deleteAppWidgetId(appWidgetId);
                i = 4;
            } else {
                i = 0;
            }
            i2 = i;
            runnable = null;
            appWidgetHostView = null;
        }
        if (this.mDragLayer.getAnimatedView() != null) {
            this.mWorkspace.animateWidgetDrop(requestArgs, screenWithId, (DragView) this.mDragLayer.getAnimatedView(), runnable, i2, appWidgetHostView, true);
        } else if (runnable != null) {
            runnable.run();
        }
    }

    @Override // com.android.launcher3.BaseActivity, android.app.Activity
    protected void onStop() {
        super.onStop();
        LauncherCallbacks launcherCallbacks = this.mLauncherCallbacks;
        if (launcherCallbacks != null) {
            launcherCallbacks.onStop();
        }
        getUserEventDispatcher().logActionCommand(5, this.mStateManager.getState().containerType, -1);
        this.mAppWidgetHost.setListenIfResumed(false);
        NotificationListener.removeNotificationsChangedListener();
        getStateManager().moveToRestState();
        UiFactory.onLauncherStateOrResumeChanged(this);
        onTrimMemory(20);
    }

    @Override // com.android.launcher3.BaseDraggingActivity, com.android.launcher3.BaseActivity, android.app.Activity
    protected void onStart() {
        if (TestProtocol.sDebugTracing) {
            Log.d(TestProtocol.NO_OVERVIEW_EVENT_TAG, ON_START_EVT);
        }
        RaceConditionTracker.onEvent(ON_START_EVT, true);
        super.onStart();
        LauncherCallbacks launcherCallbacks = this.mLauncherCallbacks;
        if (launcherCallbacks != null) {
            launcherCallbacks.onStart();
        }
        this.mAppWidgetHost.setListenIfResumed(true);
        RaceConditionTracker.onEvent(ON_START_EVT, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleDeferredResume() {
        if (hasBeenResumed() && !this.mStateManager.getState().disableInteraction) {
            getUserEventDispatcher().logActionCommand(7, this.mStateManager.getState().containerType, -1);
            getUserEventDispatcher().startSession();
            UiFactory.onLauncherStateOrResumeChanged(this);
            AppLaunchTracker.INSTANCE.lambda$get$0$MainThreadInitializedObject(this).onReturnedToHome();
            InstallShortcutReceiver.disableAndFlushInstallQueue(1, this);
            this.mModel.refreshShortcutsIfRequired();
            NotificationListener.setNotificationsChangedListener(this.mPopupDataProvider);
            DiscoveryBounce.showForHomeIfNeeded(this);
            if (this.mPendingActivityRequestCode != -1 && isInState(LauncherState.NORMAL)) {
                UiFactory.resetPendingActivityResults(this, this.mPendingActivityRequestCode);
            }
            this.mDeferredResumePending = false;
            return;
        }
        this.mDeferredResumePending = true;
    }

    protected void onStateSet(LauncherState state) {
        getAppWidgetHost().setResumed(state == LauncherState.NORMAL);
        if (this.mDeferredResumePending) {
            handleDeferredResume();
        }
        LauncherCallbacks launcherCallbacks = this.mLauncherCallbacks;
        if (launcherCallbacks != null) {
            launcherCallbacks.onStateChanged();
        }
    }

    @Override // com.android.launcher3.BaseActivity, android.app.Activity
    protected void onResume() {
        RaceConditionTracker.onEvent(ON_RESUME_EVT, true);
        TraceHelper.beginSection("ON_RESUME");
        super.onResume();
        TraceHelper.partitionSection("ON_RESUME", "superCall");
        this.mHandler.removeCallbacks(this.mHandleDeferredResume);
        Utilities.postAsyncCallback(this.mHandler, this.mHandleDeferredResume);
        if (!this.mOnResumeCallbacks.isEmpty()) {
            ArrayList arrayList = new ArrayList(this.mOnResumeCallbacks);
            this.mOnResumeCallbacks.clear();
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                ((OnResumeCallback) arrayList.get(size)).onLauncherResume();
            }
            arrayList.clear();
        }
        LauncherCallbacks launcherCallbacks = this.mLauncherCallbacks;
        if (launcherCallbacks != null) {
            launcherCallbacks.onResume();
        }
        TraceHelper.endSection("ON_RESUME");
        RaceConditionTracker.onEvent(ON_RESUME_EVT, false);
    }

    @Override // com.android.launcher3.BaseActivity, android.app.Activity
    protected void onPause() {
        InstallShortcutReceiver.enableInstallQueue(1);
        super.onPause();
        this.mDragController.cancelDrag();
        this.mDragController.resetLastGestureUpTime();
        this.mDropTargetBar.animateToVisibility(false);
        LauncherCallbacks launcherCallbacks = this.mLauncherCallbacks;
        if (launcherCallbacks != null) {
            launcherCallbacks.onPause();
        }
    }

    @Override // com.android.launcher3.BaseActivity, android.app.Activity
    protected void onUserLeaveHint() {
        super.onUserLeaveHint();
        UiFactory.onLauncherStateOrResumeChanged(this);
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        this.mStateManager.onWindowFocusChanged();
    }

    class LauncherOverlayCallbacksImpl implements LauncherOverlayCallbacks {
        LauncherOverlayCallbacksImpl() {
        }

        @Override // com.android.launcher3.Launcher.LauncherOverlayCallbacks
        public void onScrollChanged(float progress) {
            if (Launcher.this.mWorkspace != null) {
                Launcher.this.mWorkspace.onOverlayScrollChanged(progress);
            }
        }
    }

    public boolean isInState(LauncherState state) {
        return this.mStateManager.getState() == state;
    }

    private void restoreState(Bundle savedState) {
        if (savedState == null) {
            return;
        }
        LauncherState launcherState = LauncherState.values()[savedState.getInt(RUNTIME_STATE, LauncherState.NORMAL.ordinal)];
        if (!launcherState.disableRestore) {
            this.mStateManager.goToState(launcherState, false);
        }
        PendingRequestArgs pendingRequestArgs = (PendingRequestArgs) savedState.getParcelable(RUNTIME_STATE_PENDING_REQUEST_ARGS);
        if (pendingRequestArgs != null) {
            setWaitingForResult(pendingRequestArgs);
        }
        this.mPendingActivityRequestCode = savedState.getInt(RUNTIME_STATE_PENDING_REQUEST_CODE);
        this.mPendingActivityResult = (ActivityResultInfo) savedState.getParcelable(RUNTIME_STATE_PENDING_ACTIVITY_RESULT);
        SparseArray<Parcelable> sparseParcelableArray = savedState.getSparseParcelableArray(RUNTIME_STATE_WIDGET_PANEL);
        if (sparseParcelableArray != null) {
            WidgetsFullSheet.show(this, false).restoreHierarchyState(sparseParcelableArray);
        }
    }

    private void setupViews() {
        DragLayer dragLayer = (DragLayer) findViewById(R.id.drag_layer);
        this.mDragLayer = dragLayer;
        this.mFocusHandler = dragLayer.getFocusIndicatorHelper();
        Workspace workspace = (Workspace) this.mDragLayer.findViewById(R.id.workspace);
        this.mWorkspace = workspace;
        workspace.initParentViews(this.mDragLayer);
        this.mOverviewPanel = findViewById(R.id.overview_panel);
        this.mHotseat = (Hotseat) findViewById(R.id.hotseat);
        this.mLauncherView.setSystemUiVisibility(1792);
        this.mDragLayer.setup(this.mDragController, this.mWorkspace);
        this.mCancelTouchController = UiFactory.enableLiveUIChanges(this);
        this.mWorkspace.setup(this.mDragController);
        this.mWorkspace.lockWallpaperToDefaultPage();
        this.mWorkspace.bindAndInitFirstWorkspaceScreen(null);
        this.mDragController.addDragListener(this.mWorkspace);
        this.mDropTargetBar = (DropTargetBar) this.mDragLayer.findViewById(R.id.drop_target_bar);
        this.mAppsView = (AllAppsContainerView) findViewById(R.id.apps_view);
        this.mScrimView = (ScrimView) findViewById(R.id.scrim_view);
        this.mDragController.setMoveTarget(this.mWorkspace);
        this.mDropTargetBar.setup(this.mDragController);
        this.mAllAppsController.setupViews(this.mAppsView);
        this.selectedFolderApp = new SelectedFolderApp(this);
    }

    View createShortcut(WorkspaceItemInfo info) {
        Workspace workspace = this.mWorkspace;
        return createShortcut((ViewGroup) workspace.getChildAt(workspace.getCurrentPage()), info);
    }

    public View createShortcut(ViewGroup parent, WorkspaceItemInfo info) {
        BubbleTextView bubbleTextView = (BubbleTextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.app_icon, parent, false);
        bubbleTextView.applyFromWorkspaceItem(info);
        bubbleTextView.setOnClickListener(ItemClickHandler.INSTANCE);
        bubbleTextView.setOnFocusChangeListener(this.mFocusHandler);
        return bubbleTextView;
    }

    private void completeAddShortcut(Intent data, int container, int screenId, int cellX, int cellY, PendingRequestArgs args) {
        WorkspaceItemInfo workspaceItemInfo;
        View view;
        WorkspaceItemInfo workspaceItemInfo2;
        char c;
        CellLayout cellLayout;
        int[] iArr;
        boolean zFindCellForSpan;
        if (args.getRequestCode() != 1 || args.getPendingIntent().getComponent() == null) {
            return;
        }
        int[] iArr2 = this.mTmpAddItemCellCoordinates;
        CellLayout cellLayout2 = getCellLayout(container, screenId);
        WorkspaceItemInfo workspaceItemInfoCreateWorkspaceItemFromPinItemRequest = Utilities.ATLEAST_OREO ? LauncherAppsCompatVO.createWorkspaceItemFromPinItemRequest(this, LauncherAppsCompatVO.getPinItemRequest(data), 0L) : null;
        if (workspaceItemInfoCreateWorkspaceItemFromPinItemRequest == null) {
            WorkspaceItemInfo workspaceItemInfoFromShortcutIntent = Process.myUserHandle().equals(args.user) ? InstallShortcutReceiver.fromShortcutIntent(this, data) : null;
            if (workspaceItemInfoFromShortcutIntent == null) {
                Log.e(TAG, "Unable to parse a valid custom shortcut result");
                return;
            } else {
                if (!new PackageManagerHelper(this).hasPermissionForActivity(workspaceItemInfoFromShortcutIntent.intent, args.getPendingIntent().getComponent().getPackageName())) {
                    Log.e(TAG, "Ignoring malicious intent " + workspaceItemInfoFromShortcutIntent.intent.toUri(0));
                    return;
                }
                workspaceItemInfo = workspaceItemInfoFromShortcutIntent;
            }
        } else {
            workspaceItemInfo = workspaceItemInfoCreateWorkspaceItemFromPinItemRequest;
        }
        if (container < 0) {
            View viewCreateShortcut = createShortcut(workspaceItemInfo);
            if (cellX >= 0 && cellY >= 0) {
                iArr2[0] = cellX;
                iArr2[1] = cellY;
                view = viewCreateShortcut;
                if (this.mWorkspace.createUserFolderIfNecessary(viewCreateShortcut, container, cellLayout2, iArr2, 0.0f, true, null)) {
                    return;
                }
                DropTarget.DragObject dragObject = new DropTarget.DragObject();
                dragObject.dragInfo = workspaceItemInfo;
                workspaceItemInfo2 = workspaceItemInfo;
                c = 0;
                cellLayout = cellLayout2;
                iArr = iArr2;
                if (this.mWorkspace.addToExistingFolderIfNecessary(view, cellLayout2, iArr2, 0.0f, dragObject, true)) {
                    return;
                } else {
                    zFindCellForSpan = true;
                }
            } else {
                view = viewCreateShortcut;
                workspaceItemInfo2 = workspaceItemInfo;
                c = 0;
                cellLayout = cellLayout2;
                iArr = iArr2;
                zFindCellForSpan = cellLayout.findCellForSpan(iArr, 1, 1);
            }
            if (!zFindCellForSpan) {
                this.mWorkspace.onNoCellFound(cellLayout);
                return;
            } else {
                getModelWriter().addItemToDatabase(workspaceItemInfo2, container, screenId, iArr[c], iArr[1]);
                this.mWorkspace.addInScreen(view, workspaceItemInfo2);
                return;
            }
        }
        WorkspaceItemInfo workspaceItemInfo3 = workspaceItemInfo;
        FolderIcon folderIconFindFolderIcon = findFolderIcon(container);
        if (folderIconFindFolderIcon != null) {
            ((FolderInfo) folderIconFindFolderIcon.getTag()).add(workspaceItemInfo3, args.rank, false);
        } else {
            Log.e(TAG, "Could not find folder with id " + container + " to add shortcut.");
        }
    }

    public FolderIcon findFolderIcon(final int folderIconId) {
        return (FolderIcon) this.mWorkspace.getHomescreenIconByItemId(folderIconId);
    }

    void completeAddAppWidget(int appWidgetId, ItemInfo itemInfo, AppWidgetHostView hostView, LauncherAppWidgetProviderInfo appWidgetInfo) {
        if (appWidgetInfo == null) {
            appWidgetInfo = this.mAppWidgetManager.getLauncherAppWidgetInfo(appWidgetId);
        }
        LauncherAppWidgetInfo launcherAppWidgetInfo = new LauncherAppWidgetInfo(appWidgetId, appWidgetInfo.provider);
        launcherAppWidgetInfo.spanX = itemInfo.spanX;
        launcherAppWidgetInfo.spanY = itemInfo.spanY;
        launcherAppWidgetInfo.minSpanX = itemInfo.minSpanX;
        launcherAppWidgetInfo.minSpanY = itemInfo.minSpanY;
        launcherAppWidgetInfo.user = appWidgetInfo.getProfile();
        getModelWriter().addItemToDatabase(launcherAppWidgetInfo, itemInfo.container, itemInfo.screenId, itemInfo.cellX, itemInfo.cellY);
        if (hostView == null) {
            hostView = this.mAppWidgetHost.createView((Context) this, appWidgetId, appWidgetInfo);
        }
        hostView.setVisibility(0);
        prepareAppWidget(hostView, launcherAppWidgetInfo);
        this.mWorkspace.addInScreen(hostView, launcherAppWidgetInfo);
    }

    private void prepareAppWidget(AppWidgetHostView hostView, LauncherAppWidgetInfo item) {
        hostView.setTag(item);
        item.onBindAppWidget(this, hostView);
        hostView.setFocusable(true);
        hostView.setOnFocusChangeListener(this.mFocusHandler);
    }

    public void updateNotificationDots(Predicate<PackageUserKey> updatedDots) {
        this.mWorkspace.updateNotificationDots(updatedDots);
        this.mAppsView.getAppsStore().updateNotificationDots(updatedDots);
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        LauncherCallbacks launcherCallbacks = this.mLauncherCallbacks;
        if (launcherCallbacks != null) {
            launcherCallbacks.onAttachedToWindow();
        }
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        LauncherCallbacks launcherCallbacks = this.mLauncherCallbacks;
        if (launcherCallbacks != null) {
            launcherCallbacks.onDetachedFromWindow();
        }
    }

    public AllAppsTransitionController getAllAppsController() {
        return this.mAllAppsController;
    }

    @Override // com.android.launcher3.BaseDraggingActivity
    public LauncherRootView getRootView() {
        return (LauncherRootView) this.mLauncherView;
    }

    @Override // com.android.launcher3.views.ActivityContext
    public DragLayer getDragLayer() {
        return this.mDragLayer;
    }

    public AllAppsContainerView getAppsView() {
        return this.mAppsView;
    }

    public Workspace getWorkspace() {
        return this.mWorkspace;
    }

    public Hotseat getHotseat() {
        return this.mHotseat;
    }

    @Override // com.android.launcher3.BaseDraggingActivity
    public <T extends View> T getOverviewPanel() {
        return (T) this.mOverviewPanel;
    }

    public DropTargetBar getDropTargetBar() {
        return this.mDropTargetBar;
    }

    public LauncherAppWidgetHost getAppWidgetHost() {
        return this.mAppWidgetHost;
    }

    public LauncherModel getModel() {
        return this.mModel;
    }

    public ModelWriter getModelWriter() {
        return this.mModelWriter;
    }

    @Override // com.android.launcher3.LauncherExterns
    public SharedPreferences getSharedPrefs() {
        return this.mSharedPrefs;
    }

    public int getOrientation() {
        return this.mOldConfig.orientation;
    }

    @Override // android.app.Activity
    protected void onNewIntent(Intent intent) {
        TraceHelper.beginSection("NEW_INTENT");
        super.onNewIntent(intent);
        boolean z = hasWindowFocus() && (intent.getFlags() & 4194304) != 4194304;
        boolean z2 = z && isInState(LauncherState.NORMAL) && AbstractFloatingView.getTopOpenView(this) == null;
        boolean zEquals = Intent.ACTION_MAIN.equals(intent.getAction());
        boolean zHandleNewIntent = InternalStateHandler.handleNewIntent(this, intent, isStarted());
        if (zEquals) {
            if (!zHandleNewIntent) {
                AbstractFloatingView.closeAllOpenViews(this, isStarted());
                if (!isInState(LauncherState.NORMAL)) {
                    this.mStateManager.goToState(LauncherState.NORMAL);
                }
                if (!z) {
                    this.mAppsView.reset(isStarted());
                }
                if (z2 && !this.mWorkspace.isHandlingTouch()) {
                    final Workspace workspace = this.mWorkspace;
                    Objects.requireNonNull(workspace);
                    workspace.post(new Runnable() { // from class: com.android.launcher3.-$$Lambda$g_PWBejC70ihpe8wyanMHZpVuUc
                        @Override // java.lang.Runnable
                        public final void run() {
                            workspace.moveToDefaultScreen();
                        }
                    });
                }
            }
            UserEventDispatcher userEventDispatcher = getUserEventDispatcher();
            LauncherLogProto.Target targetNewContainerTarget = LoggerUtils.newContainerTarget(this.mStateManager.getState().containerType);
            targetNewContainerTarget.pageIndex = this.mWorkspace.getCurrentPage();
            userEventDispatcher.logActionCommand(0, targetNewContainerTarget, LoggerUtils.newContainerTarget(1));
            View viewPeekDecorView = getWindow().peekDecorView();
            if (viewPeekDecorView != null && viewPeekDecorView.getWindowToken() != null) {
                UiThreadHelper.hideKeyboardAsync(this, viewPeekDecorView.getWindowToken());
            }
            LauncherCallbacks launcherCallbacks = this.mLauncherCallbacks;
            if (launcherCallbacks != null) {
                launcherCallbacks.onHomeIntent(zHandleNewIntent);
            }
        }
        TraceHelper.endSection("NEW_INTENT");
    }

    @Override // android.app.Activity
    public void onRestoreInstanceState(Bundle state) {
        super.onRestoreInstanceState(state);
        this.mWorkspace.restoreInstanceStateForChild(this.mSynchronouslyBoundPage);
    }

    @Override // android.app.Activity
    protected void onSaveInstanceState(Bundle outState) {
        if (this.mWorkspace.getChildCount() > 0) {
            outState.putInt(RUNTIME_STATE_CURRENT_SCREEN, this.mWorkspace.getNextPage());
        }
        outState.putInt(RUNTIME_STATE, this.mStateManager.getState().ordinal);
        AbstractFloatingView openView = AbstractFloatingView.getOpenView(this, 16);
        if (openView != null) {
            SparseArray<Parcelable> sparseArray = new SparseArray<>();
            openView.saveHierarchyState(sparseArray);
            outState.putSparseParcelableArray(RUNTIME_STATE_WIDGET_PANEL, sparseArray);
        } else {
            outState.remove(RUNTIME_STATE_WIDGET_PANEL);
        }
        AbstractFloatingView.closeAllOpenViews(this, false);
        PendingRequestArgs pendingRequestArgs = this.mPendingRequestArgs;
        if (pendingRequestArgs != null) {
            outState.putParcelable(RUNTIME_STATE_PENDING_REQUEST_ARGS, pendingRequestArgs);
        }
        outState.putInt(RUNTIME_STATE_PENDING_REQUEST_CODE, this.mPendingActivityRequestCode);
        ActivityResultInfo activityResultInfo = this.mPendingActivityResult;
        if (activityResultInfo != null) {
            outState.putParcelable(RUNTIME_STATE_PENDING_ACTIVITY_RESULT, activityResultInfo);
        }
        super.onSaveInstanceState(outState);
        LauncherCallbacks launcherCallbacks = this.mLauncherCallbacks;
        if (launcherCallbacks != null) {
            launcherCallbacks.onSaveInstanceState(outState);
        }
    }

    @Override // com.android.launcher3.BaseDraggingActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(this.mScreenOffReceiver);
        this.mWorkspace.removeFolderListeners();
        Runnable runnable = this.mCancelTouchController;
        if (runnable != null) {
            runnable.run();
            this.mCancelTouchController = null;
        }
        if (this.mModel.isCurrentCallbacks(this)) {
            this.mModel.stopLoader();
            LauncherAppState.getInstance(this).setLauncher(null);
        }
        this.mRotationHelper.destroy();
        try {
            this.mAppWidgetHost.stopListening();
        } catch (NullPointerException e) {
            Log.w(TAG, "problem while stopping AppWidgetHost during Launcher destruction", e);
        }
        TextKeyListener.getInstance().release();
        clearPendingBinds();
        LauncherAppState.getIDP(this).removeOnChangeListener(this);
        LauncherCallbacks launcherCallbacks = this.mLauncherCallbacks;
        if (launcherCallbacks != null) {
            launcherCallbacks.onDestroy();
        }
    }

    @Override // com.android.launcher3.views.ActivityContext
    public LauncherAccessibilityDelegate getAccessibilityDelegate() {
        return this.mAccessibilityDelegate;
    }

    public DragController getDragController() {
        return this.mDragController;
    }

    @Override // android.app.Activity
    public void startActivityForResult(Intent intent, int requestCode, Bundle options) {
        if (requestCode != -1) {
            this.mPendingActivityRequestCode = requestCode;
        }
        if (requestCode == -1 || !UiFactory.startActivityForResult(this, intent, requestCode, options)) {
            super.startActivityForResult(intent, requestCode, options);
        }
    }

    @Override // android.app.Activity
    public void startIntentSenderForResult(IntentSender intent, int requestCode, Intent fillInIntent, int flagsMask, int flagsValues, int extraFlags, Bundle options) {
        if (requestCode != -1) {
            this.mPendingActivityRequestCode = requestCode;
        }
        if (requestCode == -1 || !UiFactory.startIntentSenderForResult(this, intent, requestCode, fillInIntent, flagsMask, flagsValues, extraFlags, options)) {
            try {
                super.startIntentSenderForResult(intent, requestCode, fillInIntent, flagsMask, flagsValues, extraFlags, options);
            } catch (IntentSender.SendIntentException unused) {
                throw new ActivityNotFoundException();
            }
        }
    }

    @Override // android.app.Activity
    public void startSearch(String initialQuery, boolean selectInitialQuery, Bundle appSearchData, boolean globalSearch) {
        if (appSearchData == null) {
            appSearchData = new Bundle();
            appSearchData.putString("source", "launcher-search");
        }
        LauncherCallbacks launcherCallbacks = this.mLauncherCallbacks;
        if (launcherCallbacks == null || !launcherCallbacks.startSearch(initialQuery, selectInitialQuery, appSearchData)) {
            super.startSearch(initialQuery, selectInitialQuery, appSearchData, true);
        }
        this.mStateManager.goToState(LauncherState.NORMAL);
    }

    public boolean isWorkspaceLocked() {
        return this.mWorkspaceLoading || this.mPendingRequestArgs != null;
    }

    public boolean isWorkspaceLoading() {
        return this.mWorkspaceLoading;
    }

    private void setWorkspaceLoading(boolean value) {
        this.mWorkspaceLoading = value;
    }

    public void setWaitingForResult(PendingRequestArgs args) {
        this.mPendingRequestArgs = args;
    }

    void addAppWidgetFromDropImpl(int appWidgetId, ItemInfo info, AppWidgetHostView boundWidget, WidgetAddFlowHandler addFlowHandler) {
        addAppWidgetImpl(appWidgetId, info, boundWidget, addFlowHandler, 0);
    }

    void addAppWidgetImpl(int appWidgetId, ItemInfo info, AppWidgetHostView boundWidget, WidgetAddFlowHandler addFlowHandler, int delay) {
        if (addFlowHandler.startConfigActivity(this, appWidgetId, info, 5)) {
            return;
        }
        Runnable runnable = new Runnable() { // from class: com.android.launcher3.Launcher.7
            @Override // java.lang.Runnable
            public void run() {
                Launcher.this.mStateManager.goToState(LauncherState.NORMAL, 500L);
            }
        };
        completeAddAppWidget(appWidgetId, info, boundWidget, addFlowHandler.getProviderInfo(this));
        this.mWorkspace.removeExtraEmptyScreenDelayed(true, runnable, delay, false);
    }

    public void addPendingItem(PendingAddItemInfo info, int container, int screenId, int[] cell, int spanX, int spanY) {
        info.container = container;
        info.screenId = screenId;
        if (cell != null) {
            info.cellX = cell[0];
            info.cellY = cell[1];
        }
        info.spanX = spanX;
        info.spanY = spanY;
        int i = info.itemType;
        if (i == 1) {
            processShortcutFromDrop((PendingAddShortcutInfo) info);
        } else {
            if (i == 4 || i == 5) {
                addAppWidgetFromDrop((PendingAddWidgetInfo) info);
                return;
            }
            throw new IllegalStateException("Unknown item type: " + info.itemType);
        }
    }

    private void processShortcutFromDrop(PendingAddShortcutInfo info) {
        setWaitingForResult(PendingRequestArgs.forIntent(1, new Intent(Intent.ACTION_CREATE_SHORTCUT).setComponent(info.componentName), info));
        if (info.activityInfo.startConfigActivity(this, 1)) {
            return;
        }
        handleActivityResult(1, 0, null);
    }

    private void addAppWidgetFromDrop(PendingAddWidgetInfo info) {
        AppWidgetHostView appWidgetHostView = info.boundWidget;
        WidgetAddFlowHandler handler = info.getHandler();
        if (appWidgetHostView != null) {
            getDragLayer().removeView(appWidgetHostView);
            addAppWidgetFromDropImpl(appWidgetHostView.getAppWidgetId(), info, appWidgetHostView, handler);
            info.boundWidget = null;
        } else {
            int iAllocateAppWidgetId = getAppWidgetHost().allocateAppWidgetId();
            if (this.mAppWidgetManager.bindAppWidgetIdIfAllowed(iAllocateAppWidgetId, info.info, info.bindOptions)) {
                addAppWidgetFromDropImpl(iAllocateAppWidgetId, info, null, handler);
            } else {
                handler.startBindFlow(this, iAllocateAppWidgetId, info, 11);
            }
        }
    }

    FolderIcon addFolder(CellLayout layout, int container, final int screenId, int cellX, int cellY) {
        FolderInfo folderInfo = new FolderInfo();
        folderInfo.title = getText(R.string.default_folder_name);
        getModelWriter().addItemToDatabase(folderInfo, container, screenId, cellX, cellY);
        FolderIcon folderIconFromXml = FolderIcon.fromXml(R.layout.folder_icon, this, layout, folderInfo);
        this.mWorkspace.addInScreen(folderIconFromXml, folderInfo);
        this.mWorkspace.getParentCellLayoutForView(folderIconFromXml).getShortcutsAndWidgets().measureChild(folderIconFromXml);
        return folderIconFromXml;
    }

    public boolean removeItem(View v, final ItemInfo itemInfo, boolean deleteFromDb) {
        if (itemInfo instanceof WorkspaceItemInfo) {
            View homescreenIconByItemId = this.mWorkspace.getHomescreenIconByItemId(itemInfo.container);
            if (homescreenIconByItemId instanceof FolderIcon) {
                ((FolderInfo) homescreenIconByItemId.getTag()).remove((WorkspaceItemInfo) itemInfo, true);
            } else {
                this.mWorkspace.removeWorkspaceItem(v);
            }
            if (deleteFromDb) {
                getModelWriter().deleteItemFromDatabase(itemInfo);
            }
        } else if (itemInfo instanceof FolderInfo) {
            FolderInfo folderInfo = (FolderInfo) itemInfo;
            if (v instanceof FolderIcon) {
                ((FolderIcon) v).removeListeners();
            }
            this.mWorkspace.removeWorkspaceItem(v);
            if (deleteFromDb) {
                getModelWriter().deleteFolderAndContentsFromDatabase(folderInfo);
            }
        } else {
            if (!(itemInfo instanceof LauncherAppWidgetInfo)) {
                return false;
            }
            LauncherAppWidgetInfo launcherAppWidgetInfo = (LauncherAppWidgetInfo) itemInfo;
            this.mWorkspace.removeWorkspaceItem(v);
            if (deleteFromDb) {
                getModelWriter().deleteWidgetInfo(launcherAppWidgetInfo, getAppWidgetHost());
            }
        }
        return true;
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchKeyEvent(KeyEvent event) {
        return event.getKeyCode() == 3 || super.dispatchKeyEvent(event);
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        if (finishAutoCancelActionMode()) {
            return;
        }
        LauncherCallbacks launcherCallbacks = this.mLauncherCallbacks;
        if (launcherCallbacks == null || !launcherCallbacks.handleBackPressed()) {
            if (this.mDragController.isDragging()) {
                this.mDragController.cancelDrag();
                return;
            }
            getUserEventDispatcher();
            AbstractFloatingView topOpenView = AbstractFloatingView.getTopOpenView(this);
            if (topOpenView == null || !topOpenView.onBackPressed()) {
                this.mStateManager.getState().onBackPressed(this);
            }
        }
    }

    @Override // com.android.launcher3.BaseDraggingActivity
    public ActivityOptions getActivityLaunchOptions(View v) {
        return this.mAppTransitionManager.getActivityLaunchOptions(this, v);
    }

    public LauncherAppTransitionManager getAppTransitionManager() {
        return this.mAppTransitionManager;
    }

    @Override // com.android.launcher3.BaseDraggingActivity
    protected boolean onErrorStartingShortcut(Intent intent, ItemInfo info) {
        if (intent.getComponent() != null || !Intent.ACTION_CALL.equals(intent.getAction()) || checkSelfPermission(Manifest.permission.CALL_PHONE) == 0) {
            return false;
        }
        setWaitingForResult(PendingRequestArgs.forIntent(14, intent, info));
        requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, 14);
        return true;
    }

    @Override // com.android.launcher3.BaseActivity, com.android.launcher3.logging.StatsLogUtils.LogStateProvider
    public int getCurrentState() {
        if (this.mStateManager.getState() == LauncherState.ALL_APPS) {
            return 3;
        }
        return this.mStateManager.getState() == LauncherState.OVERVIEW ? 2 : 1;
    }

    @Override // com.android.launcher3.BaseActivity, com.android.launcher3.logging.UserEventDispatcher.UserEventDelegate
    public void modifyUserEvent(LauncherLogProto.LauncherEvent event) {
        if (event.srcTarget == null || event.srcTarget.length <= 0 || event.srcTarget[1].containerType != 7) {
            return;
        }
        event.srcTarget = new LauncherLogProto.Target[]{event.srcTarget[0], event.srcTarget[1], LoggerUtils.newTarget(3)};
        LauncherState state = this.mStateManager.getState();
        if (state == LauncherState.ALL_APPS) {
            event.srcTarget[2].containerType = 4;
        } else if (state == LauncherState.OVERVIEW) {
            event.srcTarget[2].containerType = 12;
        }
    }

    @Override // com.android.launcher3.BaseDraggingActivity
    /* JADX INFO: renamed from: startActivitySafely, reason: merged with bridge method [inline-methods] */
    public boolean lambda$startActivitySafely$0$Launcher(final View v, final Intent intent, final ItemInfo item, final String sourceContainer) {
        if (TestProtocol.sDebugTracing) {
            Log.d(TestProtocol.NO_START_TAG, "startActivitySafely outer");
        }
        if (!hasBeenResumed()) {
            addOnResumeCallback(new OnResumeCallback() { // from class: com.android.launcher3.-$$Lambda$Launcher$fa4mu36EU_oqUS-O2_ZOt4ngpLM
                @Override // com.android.launcher3.Launcher.OnResumeCallback
                public final void onLauncherResume() {
                    this.f$0.lambda$startActivitySafely$0$Launcher(v, intent, item, sourceContainer);
                }
            });
            UiFactory.clearSwipeSharedState(true);
            return true;
        }
        boolean zLambda$startActivitySafely$0$Launcher = super.lambda$startActivitySafely$0$Launcher(v, intent, item, sourceContainer);
        if (zLambda$startActivitySafely$0$Launcher && (v instanceof BubbleTextView)) {
            BubbleTextView bubbleTextView = (BubbleTextView) v;
            bubbleTextView.setStayPressed(true);
            addOnResumeCallback(bubbleTextView);
        }
        return zLambda$startActivitySafely$0$Launcher;
    }

    boolean isHotseatLayout(View layout) {
        Hotseat hotseat = this.mHotseat;
        return hotseat != null && layout == hotseat;
    }

    public CellLayout getCellLayout(int container, int screenId) {
        return container == -101 ? this.mHotseat : this.mWorkspace.getScreenWithId(screenId);
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks2
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        if (level >= 20) {
            SQLiteDatabase.releaseMemory();
        }
        LauncherCallbacks launcherCallbacks = this.mLauncherCallbacks;
        if (launcherCallbacks != null) {
            launcherCallbacks.onTrimMemory(level);
        }
        UiFactory.onTrimMemory(this, level);
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent event) {
        String description;
        boolean zDispatchPopulateAccessibilityEvent = super.dispatchPopulateAccessibilityEvent(event);
        List<CharSequence> text = event.getText();
        text.clear();
        if (this.mWorkspace == null) {
            description = getString(R.string.all_apps_home_button_label);
        } else {
            description = this.mStateManager.getState().getDescription(this);
        }
        text.add(description);
        return zDispatchPopulateAccessibilityEvent;
    }

    public void addOnResumeCallback(OnResumeCallback callback) {
        this.mOnResumeCallbacks.add(callback);
    }

    @Override // com.android.launcher3.LauncherModel.Callbacks
    public int getCurrentWorkspaceScreen() {
        Workspace workspace = this.mWorkspace;
        if (workspace != null) {
            return workspace.getCurrentPage();
        }
        return 0;
    }

    @Override // com.android.launcher3.LauncherModel.Callbacks
    public void clearPendingBinds() {
        ViewOnDrawExecutor viewOnDrawExecutor = this.mPendingExecutor;
        if (viewOnDrawExecutor != null) {
            viewOnDrawExecutor.markCompleted();
            this.mPendingExecutor = null;
        }
    }

    @Override // com.android.launcher3.LauncherModel.Callbacks
    public void startBinding() {
        TraceHelper.beginSection("startBinding");
        AbstractFloatingView.closeOpenViews(this, true, 1935);
        setWorkspaceLoading(true);
        this.mDragController.cancelDrag();
        this.mWorkspace.clearDropTargets();
        this.mWorkspace.removeAllWorkspaceScreens();
        this.mAppWidgetHost.clearViews();
        Hotseat hotseat = this.mHotseat;
        if (hotseat != null) {
            hotseat.resetLayout(getWallpaperDeviceProfile().isVerticalBarLayout());
        }
        TraceHelper.endSection("startBinding");
    }

    @Override // com.android.launcher3.LauncherModel.Callbacks
    public void bindScreens(IntArray orderedScreenIds) {
        if (orderedScreenIds.isEmpty()) {
            this.mWorkspace.addExtraEmptyScreen();
        }
        bindAddScreens(orderedScreenIds);
        this.mWorkspace.unlockWallpaperFromDefaultPageOnNextLayout();
    }

    private void bindAddScreens(IntArray orderedScreenIds) {
        int size = orderedScreenIds.size();
        for (int i = 0; i < size; i++) {
            this.mWorkspace.insertNewWorkspaceScreenBeforeEmptyScreen(orderedScreenIds.get(i));
        }
    }

    @Override // com.android.launcher3.LauncherModel.Callbacks
    public void preAddApps() {
        this.mModelWriter.commitDelete();
        final AbstractFloatingView openView = AbstractFloatingView.getOpenView(this, 128);
        if (openView != null) {
            openView.post(new Runnable() { // from class: com.android.launcher3.-$$Lambda$Launcher$rBqjScNiCYn1SJ7j3MON9YJY2fs
                @Override // java.lang.Runnable
                public final void run() {
                    openView.close(true);
                }
            });
        }
    }

    @Override // com.android.launcher3.LauncherModel.Callbacks
    public void bindAppsAdded(IntArray newScreens, ArrayList<ItemInfo> addNotAnimated, ArrayList<ItemInfo> addAnimated) {
        if (newScreens != null) {
            bindAddScreens(newScreens);
        }
        if (addNotAnimated != null && !addNotAnimated.isEmpty()) {
            bindItems(addNotAnimated, false);
        }
        if (addAnimated != null && !addAnimated.isEmpty()) {
            bindItems(addAnimated, true);
        }
        this.mWorkspace.removeExtraEmptyScreen(false, false);
    }

    public void addInScreen(WorkspaceItemInfo itemInfo) {
        Workspace workspace = this.mWorkspace;
        View viewCreateShortcut = createShortcut(itemInfo);
        itemInfo.container = -100;
        itemInfo.screenId = workspace.getScreenIdForPageIndex(workspace.getChildCount() - 1);
        if (calculateItemPosition(itemInfo, getPageLastItem((ViewGroup) ((CellLayout) workspace.getChildAt(workspace.getChildCount() - 1)).getChildAt(0)))) {
            itemInfo.screenId++;
            workspace.insertNewWorkspaceScreen(itemInfo.screenId);
        }
        getModelWriter().addOrMoveItemInDatabase(itemInfo, itemInfo.container, itemInfo.screenId, itemInfo.cellX, itemInfo.cellY);
        workspace.addInScreen(viewCreateShortcut, itemInfo);
        workspace.requestLayout();
    }

    private ItemInfo getPageLastItem(ViewGroup viewGroup) {
        int childCount = viewGroup.getChildCount();
        ItemInfo itemInfo = (ItemInfo) viewGroup.getChildAt(0).getTag();
        for (int i = 1; i < childCount; i++) {
            ItemInfo itemInfo2 = (ItemInfo) viewGroup.getChildAt(i).getTag();
            if (itemInfo.cellY < itemInfo2.cellY || (itemInfo.cellY <= itemInfo2.cellY && itemInfo.cellX < itemInfo2.cellX)) {
                itemInfo = itemInfo2;
            }
        }
        return itemInfo;
    }

    private boolean calculateItemPosition(WorkspaceItemInfo itemInfo, ItemInfo item) {
        itemInfo.cellX = item.cellX + 1;
        itemInfo.cellY = item.cellY;
        if (itemInfo.cellX >= 4) {
            itemInfo.cellX = 0;
            itemInfo.cellY++;
        }
        if (itemInfo.cellY < 4) {
            return false;
        }
        itemInfo.cellX = 0;
        itemInfo.cellY = 0;
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:41:0x00c0  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00c5  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x00d8 A[SYNTHETIC] */
    @Override // com.android.launcher3.LauncherModel.Callbacks
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void bindItems(final java.util.List<com.android.launcher3.ItemInfo> r13, final boolean r14) {
        /*
            Method dump skipped, instruction units count: 274
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.launcher3.Launcher.bindItems(java.util.List, boolean):void");
    }

    public void bindAppWidget(LauncherAppWidgetInfo item) {
        View viewInflateAppWidget = inflateAppWidget(item);
        if (viewInflateAppWidget != null) {
            this.mWorkspace.addInScreen(viewInflateAppWidget, item);
            this.mWorkspace.requestLayout();
        }
    }

    private View inflateAppWidget(LauncherAppWidgetInfo item) {
        LauncherAppWidgetProviderInfo launcherAppWidgetInfo;
        AppWidgetHostView pendingAppWidgetHostView;
        if (this.mIsSafeModeEnabled) {
            PendingAppWidgetHostView pendingAppWidgetHostView2 = new PendingAppWidgetHostView(this, item, this.mIconCache, true);
            prepareAppWidget(pendingAppWidgetHostView2, item);
            return pendingAppWidgetHostView2;
        }
        TraceHelper.beginSection("BIND_WIDGET");
        if (item.hasRestoreFlag(2)) {
            launcherAppWidgetInfo = null;
        } else if (item.hasRestoreFlag(1)) {
            launcherAppWidgetInfo = this.mAppWidgetManager.findProvider(item.providerName, item.user);
        } else {
            launcherAppWidgetInfo = this.mAppWidgetManager.getLauncherAppWidgetInfo(item.appWidgetId);
        }
        if (!item.hasRestoreFlag(2) && item.restoreStatus != 0) {
            if (launcherAppWidgetInfo == null) {
                Log.d(TAG, "Removing restored widget: id=" + item.appWidgetId + " belongs to component " + item.providerName + ", as the provider is null");
                getModelWriter().deleteItemFromDatabase(item);
                return null;
            }
            if (item.hasRestoreFlag(1)) {
                if (!item.hasRestoreFlag(16)) {
                    item.appWidgetId = this.mAppWidgetHost.allocateAppWidgetId();
                    item.restoreStatus = 16 | item.restoreStatus;
                    PendingAddWidgetInfo pendingAddWidgetInfo = new PendingAddWidgetInfo(launcherAppWidgetInfo);
                    pendingAddWidgetInfo.spanX = item.spanX;
                    pendingAddWidgetInfo.spanY = item.spanY;
                    pendingAddWidgetInfo.minSpanX = item.minSpanX;
                    pendingAddWidgetInfo.minSpanY = item.minSpanY;
                    Bundle defaultOptionsForWidget = WidgetHostViewLoader.getDefaultOptionsForWidget(this, pendingAddWidgetInfo);
                    boolean zHasRestoreFlag = item.hasRestoreFlag(32);
                    if (zHasRestoreFlag && item.bindOptions != null) {
                        Bundle extras = item.bindOptions.getExtras();
                        if (defaultOptionsForWidget != null) {
                            extras.putAll(defaultOptionsForWidget);
                        }
                        defaultOptionsForWidget = extras;
                    }
                    boolean zBindAppWidgetIdIfAllowed = this.mAppWidgetManager.bindAppWidgetIdIfAllowed(item.appWidgetId, launcherAppWidgetInfo, defaultOptionsForWidget);
                    item.bindOptions = null;
                    item.restoreStatus &= -33;
                    if (zBindAppWidgetIdIfAllowed) {
                        item.restoreStatus = (launcherAppWidgetInfo.configure == null || zHasRestoreFlag) ? 0 : 4;
                    }
                    getModelWriter().updateItemInDatabase(item);
                }
            } else if (item.hasRestoreFlag(4) && launcherAppWidgetInfo.configure == null) {
                item.restoreStatus = 0;
                getModelWriter().updateItemInDatabase(item);
            }
        }
        if (item.restoreStatus != 0) {
            pendingAppWidgetHostView = new PendingAppWidgetHostView(this, item, this.mIconCache, false);
        } else {
            if (launcherAppWidgetInfo == null) {
                FileLog.e(TAG, "Removing invalid widget: id=" + item.appWidgetId);
                getModelWriter().deleteWidgetInfo(item, getAppWidgetHost());
                return null;
            }
            item.minSpanX = launcherAppWidgetInfo.minSpanX;
            item.minSpanY = launcherAppWidgetInfo.minSpanY;
            pendingAppWidgetHostView = this.mAppWidgetHost.createView((Context) this, item.appWidgetId, launcherAppWidgetInfo);
        }
        prepareAppWidget(pendingAppWidgetHostView, item);
        TraceHelper.endSection("BIND_WIDGET", "id=" + item.appWidgetId);
        return pendingAppWidgetHostView;
    }

    private LauncherAppWidgetInfo completeRestoreAppWidget(int appWidgetId, int finalRestoreFlag) {
        LauncherAppWidgetHostView widgetForAppWidgetId = this.mWorkspace.getWidgetForAppWidgetId(appWidgetId);
        if (widgetForAppWidgetId == null || !(widgetForAppWidgetId instanceof PendingAppWidgetHostView)) {
            Log.e(TAG, "Widget update called, when the widget no longer exists.");
            return null;
        }
        LauncherAppWidgetInfo launcherAppWidgetInfo = (LauncherAppWidgetInfo) widgetForAppWidgetId.getTag();
        launcherAppWidgetInfo.restoreStatus = finalRestoreFlag;
        if (launcherAppWidgetInfo.restoreStatus == 0) {
            launcherAppWidgetInfo.pendingItemInfo = null;
        }
        if (((PendingAppWidgetHostView) widgetForAppWidgetId).isReinflateIfNeeded()) {
            widgetForAppWidgetId.reInflate();
        }
        getModelWriter().updateItemInDatabase(launcherAppWidgetInfo);
        return launcherAppWidgetInfo;
    }

    @Override // com.android.launcher3.LauncherModel.Callbacks
    public void onPageBoundSynchronously(int page) {
        this.mSynchronouslyBoundPage = page;
    }

    @Override // com.android.launcher3.LauncherModel.Callbacks
    public void executeOnNextDraw(ViewOnDrawExecutor executor) {
        ViewOnDrawExecutor viewOnDrawExecutor = this.mPendingExecutor;
        if (viewOnDrawExecutor != null) {
            viewOnDrawExecutor.markCompleted();
        }
        this.mPendingExecutor = executor;
        if (!isInState(LauncherState.ALL_APPS)) {
            this.mAppsView.getAppsStore().enableDeferUpdates(1);
            this.mPendingExecutor.execute(new Runnable() { // from class: com.android.launcher3.-$$Lambda$Launcher$Q8zdPSrUE8Q_YIs0Cgq5DqXIQvY
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$executeOnNextDraw$2$Launcher();
                }
            });
        }
        executor.attachTo(this);
    }

    public /* synthetic */ void lambda$executeOnNextDraw$2$Launcher() {
        this.mAppsView.getAppsStore().disableDeferUpdates(1);
    }

    public void clearPendingExecutor(ViewOnDrawExecutor executor) {
        if (this.mPendingExecutor == executor) {
            this.mPendingExecutor = null;
        }
    }

    @Override // com.android.launcher3.LauncherModel.Callbacks
    public void finishFirstPageBind(final ViewOnDrawExecutor executor) {
        MultiValueAlpha.AlphaProperty alphaProperty = this.mDragLayer.getAlphaProperty(1);
        if (alphaProperty.getValue() >= 1.0f) {
            if (executor != null) {
                executor.onLoadAnimationCompleted();
            }
        } else {
            ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(alphaProperty, MultiValueAlpha.VALUE, 1.0f);
            if (executor != null) {
                objectAnimatorOfFloat.addListener(new AnimatorListenerAdapter() { // from class: com.android.launcher3.Launcher.9
                    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                    public void onAnimationEnd(Animator animation) {
                        executor.onLoadAnimationCompleted();
                    }
                });
            }
            objectAnimatorOfFloat.start();
        }
    }

    @Override // com.android.launcher3.LauncherModel.Callbacks
    public void finishBindingItems(int pageBoundFirst) {
        TraceHelper.beginSection("finishBindingItems");
        this.mWorkspace.restoreInstanceStateForRemainingPages();
        setWorkspaceLoading(false);
        ActivityResultInfo activityResultInfo = this.mPendingActivityResult;
        if (activityResultInfo != null) {
            handleActivityResult(activityResultInfo.requestCode, this.mPendingActivityResult.resultCode, this.mPendingActivityResult.data);
            this.mPendingActivityResult = null;
        }
        InstallShortcutReceiver.disableAndFlushInstallQueue(2, this);
        this.mWorkspace.setCurrentPage(pageBoundFirst, pageBoundFirst);
        TraceHelper.endSection("finishBindingItems");
    }

    private boolean canRunNewAppsAnimation() {
        return System.currentTimeMillis() - this.mDragController.getLastGestureUpTime() > TimedRemoteCaller.DEFAULT_CALL_TIMEOUT_MILLIS;
    }

    private ValueAnimator createNewAppBounceAnimation(View v, int i) {
        ObjectAnimator duration = new PropertyListBuilder().alpha(1.0f).scale(1.0f).build(v).setDuration(450L);
        duration.setStartDelay(i * 85);
        duration.setInterpolator(new OvershootInterpolator(BOUNCE_ANIMATION_TENSION));
        return duration;
    }

    @Override // com.android.launcher3.LauncherModel.Callbacks
    public void bindAllApplications(ArrayList<AppInfo> apps) {
        this.mAppsView.getAppsStore().setApps(apps);
    }

    @Override // com.android.launcher3.LauncherModel.Callbacks
    public void bindDeepShortcutMap(HashMap<ComponentKey, Integer> deepShortcutMapCopy) {
        this.mPopupDataProvider.setDeepShortcutMap(deepShortcutMapCopy);
    }

    @Override // com.android.launcher3.LauncherModel.Callbacks
    public void bindAppsAddedOrUpdated(ArrayList<AppInfo> apps) {
        this.mAppsView.getAppsStore().addOrUpdateApps(apps);
    }

    @Override // com.android.launcher3.LauncherModel.Callbacks
    public void bindPromiseAppProgressUpdated(PromiseAppInfo app) {
        this.mAppsView.getAppsStore().updatePromiseAppProgress(app);
    }

    @Override // com.android.launcher3.LauncherModel.Callbacks
    public void bindWidgetsRestored(ArrayList<LauncherAppWidgetInfo> widgets) {
        this.mWorkspace.widgetsRestored(widgets);
    }

    @Override // com.android.launcher3.LauncherModel.Callbacks
    public void bindWorkspaceItemsChanged(ArrayList<WorkspaceItemInfo> updated) {
        if (updated.isEmpty()) {
            return;
        }
        this.mWorkspace.updateShortcuts(updated);
    }

    @Override // com.android.launcher3.LauncherModel.Callbacks
    public void bindRestoreItemsChange(HashSet<ItemInfo> updates) {
        this.mWorkspace.updateRestoreItems(updates);
    }

    @Override // com.android.launcher3.LauncherModel.Callbacks
    public void bindWorkspaceComponentsRemoved(final ItemInfoMatcher matcher) {
        this.mWorkspace.removeItemsByMatcher(matcher);
        this.mDragController.onAppsRemoved(matcher);
    }

    @Override // com.android.launcher3.LauncherModel.Callbacks
    public void bindAppInfosRemoved(final ArrayList<AppInfo> appInfos) {
        this.mAppsView.getAppsStore().removeApps(appInfos);
    }

    @Override // com.android.launcher3.LauncherModel.Callbacks
    public void bindAllWidgets(final ArrayList<WidgetListRowEntry> allWidgets) {
        this.mPopupDataProvider.setAllWidgets(allWidgets);
    }

    public void refreshAndBindWidgetsForPackageUser(PackageUserKey packageUser) {
        this.mModel.refreshAndBindWidgetsAndShortcuts(packageUser);
    }

    @Override // com.android.launcher3.BaseActivity, android.app.Activity
    public void dump(String prefix, FileDescriptor fd, PrintWriter writer, String[] args) {
        super.dump(prefix, fd, writer, args);
        if (args.length > 0) {
            if (TextUtils.equals(args[0], "--all")) {
                writer.println(prefix + "Workspace Items");
                for (int i = 0; i < this.mWorkspace.getPageCount(); i++) {
                    writer.println(prefix + "  Homescreen " + i);
                    ShortcutAndWidgetContainer shortcutsAndWidgets = ((CellLayout) this.mWorkspace.getPageAt(i)).getShortcutsAndWidgets();
                    for (int i2 = 0; i2 < shortcutsAndWidgets.getChildCount(); i2++) {
                        Object tag = shortcutsAndWidgets.getChildAt(i2).getTag();
                        if (tag != null) {
                            writer.println(prefix + "    " + tag.toString());
                        }
                    }
                }
                writer.println(prefix + "  Hotseat");
                ShortcutAndWidgetContainer shortcutsAndWidgets2 = this.mHotseat.getShortcutsAndWidgets();
                for (int i3 = 0; i3 < shortcutsAndWidgets2.getChildCount(); i3++) {
                    Object tag2 = shortcutsAndWidgets2.getChildAt(i3).getTag();
                    if (tag2 != null) {
                        writer.println(prefix + "    " + tag2.toString());
                    }
                }
            }
        }
        writer.println(prefix + "Misc:");
        writer.print(prefix + "\tmWorkspaceLoading=" + this.mWorkspaceLoading);
        writer.print(" mPendingRequestArgs=" + this.mPendingRequestArgs);
        writer.println(" mPendingActivityResult=" + this.mPendingActivityResult);
        writer.println(" mRotationHelper: " + this.mRotationHelper);
        this.mDragLayer.dump(prefix, writer);
        this.mStateManager.dump(prefix, writer);
        dumpMisc(writer);
        try {
            FileLog.flushAll(writer);
        } catch (Exception unused) {
        }
        this.mModel.dumpState(prefix, fd, writer, args);
        LauncherCallbacks launcherCallbacks = this.mLauncherCallbacks;
        if (launcherCallbacks != null) {
            launcherCallbacks.dump(prefix, fd, writer, args);
        }
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onProvideKeyboardShortcuts(List<KeyboardShortcutGroup> data, Menu menu, int deviceId) {
        ArrayList arrayList = new ArrayList();
        if (isInState(LauncherState.NORMAL)) {
            arrayList.add(new KeyboardShortcutInfo(getString(R.string.all_apps_button_label), 29, 4096));
            arrayList.add(new KeyboardShortcutInfo(getString(R.string.widget_button_text), 51, 4096));
        }
        View currentFocus = getCurrentFocus();
        if (currentFocus != null) {
            if (new CustomActionsPopup(this, currentFocus).canShow()) {
                arrayList.add(new KeyboardShortcutInfo(getString(R.string.custom_actions), 43, 4096));
            }
            if ((currentFocus.getTag() instanceof ItemInfo) && DeepShortcutManager.supportsShortcuts((ItemInfo) currentFocus.getTag())) {
                arrayList.add(new KeyboardShortcutInfo(getString(R.string.shortcuts_menu_with_notifications_description), 47, 4096));
            }
        }
        if (!arrayList.isEmpty()) {
            data.add(new KeyboardShortcutGroup(getString(R.string.home_screen), arrayList));
        }
        super.onProvideKeyboardShortcuts(data, menu, deviceId);
    }

    @Override // android.app.Activity
    public boolean onKeyShortcut(int keyCode, KeyEvent event) {
        if (event.hasModifiers(4096)) {
            if (keyCode != 29) {
                if (keyCode != 43) {
                    if (keyCode == 47) {
                        View currentFocus = getCurrentFocus();
                        if ((currentFocus instanceof BubbleTextView) && (currentFocus.getTag() instanceof ItemInfo) && this.mAccessibilityDelegate.performAction(currentFocus, (ItemInfo) currentFocus.getTag(), R.id.action_deep_shortcuts)) {
                            PopupContainerWithArrow.getOpen(this).requestFocus();
                            return true;
                        }
                    } else if (keyCode == 51 && isInState(LauncherState.NORMAL)) {
                        OptionsPopupView.openWidgets(this);
                        return true;
                    }
                } else if (new CustomActionsPopup(this, getCurrentFocus()).show()) {
                    return true;
                }
            } else if (isInState(LauncherState.NORMAL)) {
                getStateManager().goToState(LauncherState.ALL_APPS);
                return true;
            }
        }
        return super.onKeyShortcut(keyCode, event);
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == 82) {
            if (this.mDragController.isDragging() || this.mWorkspace.isSwitchingState() || !isInState(LauncherState.NORMAL)) {
                return true;
            }
            AbstractFloatingView.closeAllOpenViews(this);
            OptionsPopupView.showDefaultOptions(this, -1.0f, -1.0f);
            return true;
        }
        return super.onKeyUp(keyCode, event);
    }

    public static Launcher getLauncher(Context context) {
        return (Launcher) fromContext(context);
    }

    public static <T extends Launcher> T cast(ActivityContext activityContext) {
        return (T) activityContext;
    }

    public SelectedFolderApp getSelectedFolderApp() {
        return this.selectedFolderApp;
    }
}
