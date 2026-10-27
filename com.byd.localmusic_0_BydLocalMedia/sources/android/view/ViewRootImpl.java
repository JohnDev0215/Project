package android.view;

import android.Manifest;
import android.animation.LayoutTransition;
import android.app.ActivityManager;
import android.app.ResourcesManager;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Context;
import android.content.res.CompatibilityInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.BLASTBufferQueue;
import android.graphics.Canvas;
import android.graphics.HardwareRenderer;
import android.graphics.Insets;
import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.PorterDuff;
import android.graphics.RecordingCanvas;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.RenderNode;
import android.graphics.drawable.Drawable;
import android.hardware.display.DisplayManager;
import android.hardware.input.InputManager;
import android.media.AudioManager;
import android.media.AudioSystem;
import android.net.TrafficStats;
import android.os.Binder;
import android.os.Bundle;
import android.os.Debug;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import android.os.Process;
import android.os.RemoteException;
import android.os.SystemClock;
import android.os.SystemProperties;
import android.os.Trace;
import android.os.UserHandle;
import android.sysprop.DisplayProperties;
import android.util.AndroidRuntimeException;
import android.util.Log;
import android.util.LongArray;
import android.util.MergedConfiguration;
import android.util.Slog;
import android.util.SparseArray;
import android.util.TypedValue;
import android.view.ActionMode;
import android.view.Choreographer;
import android.view.DisplayCutout;
import android.view.IWindow;
import android.view.InputDevice;
import android.view.InputQueue;
import android.view.KeyCharacterMap;
import android.view.Surface;
import android.view.SurfaceControl;
import android.view.SurfaceHolder;
import android.view.ThreadedRenderer;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowInsets;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.AccessibilityNodeIdManager;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeProvider;
import android.view.accessibility.IAccessibilityEmbeddedConnection;
import android.view.accessibility.IAccessibilityInteractionConnection;
import android.view.accessibility.IAccessibilityInteractionConnectionCallback;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.autofill.AutofillId;
import android.view.autofill.AutofillManager;
import android.view.contentcapture.ContentCaptureManager;
import android.view.contentcapture.ContentCaptureSession;
import android.view.contentcapture.MainContentCaptureSession;
import android.view.inputmethod.InputMethodManager;
import android.widget.Scroller;
import com.android.internal.R;
import com.android.internal.os.IResultReceiver;
import com.android.internal.os.SomeArgs;
import com.android.internal.policy.DecorView;
import com.android.internal.policy.PhoneFallbackEventHandler;
import com.android.internal.util.Preconditions;
import com.android.internal.view.BaseSurfaceHolder;
import com.android.internal.view.RootViewSurfaceTaker;
import com.android.internal.view.SurfaceCallbackHelper;
import java.io.FileDescriptor;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.CountDownLatch;
import java.util.function.Consumer;

/* JADX INFO: loaded from: classes3.dex */
public final class ViewRootImpl implements ViewParent, View.AttachInfo.Callbacks, ThreadedRenderer.DrawCallbacks {
    private static final int CONTENT_CAPTURE_ENABLED_FALSE = 2;
    private static final int CONTENT_CAPTURE_ENABLED_NOT_CHECKED = 0;
    private static final int CONTENT_CAPTURE_ENABLED_TRUE = 1;
    private static final boolean DBG = false;
    private static final boolean DEBUG_CONFIGURATION = false;
    private static final boolean DEBUG_CONTENT_CAPTURE = false;
    private static final boolean DEBUG_DIALOG = false;
    private static final boolean DEBUG_DRAW = false;
    private static final boolean DEBUG_FPS = false;
    private static final boolean DEBUG_IMF = false;
    private static final boolean DEBUG_INPUT_RESIZE = false;
    private static final boolean DEBUG_INPUT_STAGES = false;
    private static final boolean DEBUG_KEEP_SCREEN_ON = false;
    private static final boolean DEBUG_LAYOUT = false;
    private static final boolean DEBUG_ORIENTATION = false;
    private static final boolean DEBUG_SCROLL_CAPTURE = false;
    private static final boolean DEBUG_TRACKBALL = false;
    private static final boolean LOCAL_LOGV = false;
    private static final int MAX_QUEUED_INPUT_EVENT_POOL_SIZE = 10;
    static final int MAX_TRACKBALL_DELAY = 250;
    private static final int MSG_CHECK_FOCUS = 13;
    private static final int MSG_CLEAR_ACCESSIBILITY_FOCUS_HOST = 21;
    private static final int MSG_CLOSE_SYSTEM_DIALOGS = 14;
    private static final int MSG_DIE = 3;
    private static final int MSG_DISPATCH_APP_VISIBILITY = 8;
    private static final int MSG_DISPATCH_DRAG_EVENT = 15;
    private static final int MSG_DISPATCH_DRAG_LOCATION_EVENT = 16;
    private static final int MSG_DISPATCH_GET_NEW_SURFACE = 9;
    private static final int MSG_DISPATCH_INPUT_EVENT = 7;
    private static final int MSG_DISPATCH_KEY_FROM_AUTOFILL = 12;
    private static final int MSG_DISPATCH_KEY_FROM_IME = 11;
    private static final int MSG_DISPATCH_SYSTEM_UI_VISIBILITY = 17;
    private static final int MSG_DISPATCH_WINDOW_SHOWN = 25;
    private static final int MSG_DRAW_FINISHED = 29;
    private static final int MSG_HIDE_INSETS = 35;
    private static final int MSG_INSETS_CHANGED = 30;
    private static final int MSG_INSETS_CONTROL_CHANGED = 31;
    private static final int MSG_INVALIDATE = 1;
    private static final int MSG_INVALIDATE_RECT = 2;
    private static final int MSG_INVALIDATE_WORLD = 22;
    private static final int MSG_LOCATION_IN_PARENT_DISPLAY_CHANGED = 33;
    private static final int MSG_POINTER_CAPTURE_CHANGED = 28;
    private static final int MSG_PROCESS_INPUT_EVENTS = 19;
    private static final int MSG_REQUEST_KEYBOARD_SHORTCUTS = 26;
    private static final int MSG_REQUEST_SCROLL_CAPTURE = 36;
    private static final int MSG_RESIZED = 4;
    private static final int MSG_RESIZED_REPORT = 5;
    private static final int MSG_SHOW_INSETS = 34;
    private static final int MSG_SYNTHESIZE_INPUT_EVENT = 24;
    private static final int MSG_SYSTEM_GESTURE_EXCLUSION_CHANGED = 32;
    private static final int MSG_UPDATE_CONFIGURATION = 18;
    private static final int MSG_UPDATE_POINTER_ICON = 27;
    private static final int MSG_WINDOW_FOCUS_CHANGED = 6;
    private static final int MSG_WINDOW_MOVED = 23;
    private static final boolean MT_RENDERER_AVAILABLE = true;
    public static final int NEW_INSETS_MODE_FULL = 2;
    public static final int NEW_INSETS_MODE_IME = 1;
    public static final int NEW_INSETS_MODE_NONE = 0;
    private static final String PROPERTY_PROFILE_RENDERING = "viewroot.profile_rendering";
    private static final String TAG = "ViewRootImpl";
    private static boolean sAlwaysAssignFocus;
    private IAccessibilityEmbeddedConnection mAccessibilityEmbeddedConnection;
    View mAccessibilityFocusedHost;
    AccessibilityNodeInfo mAccessibilityFocusedVirtualView;
    final AccessibilityInteractionConnectionManager mAccessibilityInteractionConnectionManager;
    AccessibilityInteractionController mAccessibilityInteractionController;
    final AccessibilityManager mAccessibilityManager;
    private ActivityConfigCallback mActivityConfigCallback;
    private boolean mActivityRelaunched;
    boolean mAdded;
    boolean mAddedTouchMode;
    private boolean mAppVisibilityChanged;
    boolean mAppVisible;
    boolean mApplyInsetsRequested;
    final View.AttachInfo mAttachInfo;
    AudioManager mAudioManager;
    final String mBasePackageName;
    private BLASTBufferQueue mBlastBufferQueue;
    private SurfaceControl mBlastSurfaceControl;
    private SurfaceControl mBoundsLayer;
    private int mCanvasOffsetX;
    private int mCanvasOffsetY;
    final Choreographer mChoreographer;
    int mClientWindowLayoutFlags;
    final SystemUiVisibilityInfo mCompatibleVisibilityInfo;
    final ConsumeBatchedInputImmediatelyRunnable mConsumeBatchedInputImmediatelyRunnable;
    boolean mConsumeBatchedInputImmediatelyScheduled;
    boolean mConsumeBatchedInputScheduled;
    final ConsumeBatchedInputRunnable mConsumedBatchedInputRunnable;
    int mContentCaptureEnabled;
    public final Context mContext;
    int mCurScrollY;
    View mCurrentDragView;
    private PointerIcon mCustomPointerIcon;
    private final int mDensity;
    Rect mDirty;
    int mDispatchedSystemUiVisibility;
    Display mDisplay;
    private final DisplayManager.DisplayListener mDisplayListener;
    final DisplayManager mDisplayManager;
    ClipDescription mDragDescription;
    final PointF mDragPoint;
    private boolean mDragResizing;
    boolean mDrawingAllowed;
    int mDrawsNeededToReport;
    private boolean mEnableTripleBuffering;
    FallbackEventHandler mFallbackEventHandler;
    boolean mFirst;
    InputStage mFirstInputStage;
    InputStage mFirstPostImeInputStage;
    private boolean mForceDecorViewVisibility;
    private boolean mForceDisableBLAST;
    private boolean mForceNextConfigUpdate;
    boolean mForceNextWindowRelayout;
    private int mFpsNumFrames;
    private long mFpsPrevTime;
    private long mFpsStartTime;
    boolean mFullRedrawNeeded;
    private final GestureExclusionTracker mGestureExclusionTracker;
    boolean mHadWindowFocus;
    final ViewRootHandler mHandler;
    boolean mHandlingLayoutInLayoutRequest;
    int mHardwareXOffset;
    int mHardwareYOffset;
    int mHeight;
    final HighContrastTextManager mHighContrastTextManager;
    private final ImeFocusController mImeFocusController;
    private boolean mInLayout;
    private final InputEventCompatProcessor mInputCompatProcessor;
    protected final InputEventConsistencyVerifier mInputEventConsistencyVerifier;
    WindowInputEventReceiver mInputEventReceiver;
    InputQueue mInputQueue;
    InputQueue.Callback mInputQueueCallback;
    private final InsetsController mInsetsController;
    final InvalidateOnAnimationRunnable mInvalidateOnAnimationRunnable;
    private boolean mInvalidateRootRequested;
    boolean mIsAmbientMode;
    public boolean mIsAnimating;
    boolean mIsCreating;
    boolean mIsDrawing;
    boolean mIsInTraversal;
    private final Configuration mLastConfigurationFromResources;
    final ViewTreeObserver.InternalInsetsInfo mLastGivenInsets;
    boolean mLastInCompatMode;
    private final MergedConfiguration mLastReportedMergedConfiguration;
    WeakReference<View> mLastScrolledFocus;
    int mLastSystemUiVisibility;
    final PointF mLastTouchPoint;
    int mLastTouchSource;
    private WindowInsets mLastWindowInsets;
    boolean mLayoutRequested;
    ArrayList<View> mLayoutRequesters;
    final IBinder mLeashToken;
    volatile Object mLocalDragState;
    final WindowLeaked mLocation;
    boolean mLostWindowFocus;
    private boolean mNeedsRendererSetup;
    boolean mNewSurfaceNeeded;
    private boolean mNextDrawUseBLASTSyncTransaction;
    private boolean mNextReportConsumeBLAST;
    private final int mNoncompatDensity;
    int mOrigWindowType;
    boolean mPausedForTransition;
    boolean mPendingAlwaysConsumeSystemBars;
    final Rect mPendingBackDropFrame;
    final DisplayCutout.ParcelableWrapper mPendingDisplayCutout;
    int mPendingInputEventCount;
    QueuedInputEvent mPendingInputEventHead;
    String mPendingInputEventQueueLengthCounterName;
    QueuedInputEvent mPendingInputEventTail;
    private final MergedConfiguration mPendingMergedConfiguration;
    private ArrayList<LayoutTransition> mPendingTransitions;
    boolean mPerformContentCapture;
    boolean mPointerCapture;
    private int mPointerIconType;
    final Region mPreviousTransparentRegion;
    boolean mProcessInputEventsScheduled;
    private boolean mProfile;
    private boolean mProfileRendering;
    private QueuedInputEvent mQueuedInputEventPool;
    private int mQueuedInputEventPoolSize;
    private boolean mRemoved;
    private Choreographer.FrameCallback mRenderProfiler;
    private boolean mRenderProfilingEnabled;
    boolean mReportNextDraw;
    private int mResizeMode;
    private HashSet<ScrollCaptureCallback> mRootScrollCaptureCallbacks;
    private SurfaceControl.Transaction mRtBLASTSyncTransaction;
    private ScrollCaptureClient mScrollCaptureClient;
    boolean mScrollMayChange;
    int mScrollY;
    Scroller mScroller;
    private boolean mSendNextFrameToWm;
    SendWindowContentChangedAccessibilityEvent mSendWindowContentChangedAccessibilityEvent;
    int mSeq;
    int mSoftInputMode;
    boolean mStopped;
    public final Surface mSurface;
    private final ArrayList<SurfaceChangedCallback> mSurfaceChangedCallbacks;
    private final SurfaceControl.Transaction mSurfaceChangedTransaction;
    private final SurfaceControl mSurfaceControl;
    BaseSurfaceHolder mSurfaceHolder;
    SurfaceHolder.Callback2 mSurfaceHolderCallback;
    private final SurfaceSession mSurfaceSession;
    private final Point mSurfaceSize;
    InputStage mSyntheticInputStage;
    private String mTag;
    final int mTargetSdkVersion;
    private final Rect mTempBoundsRect;
    private final InsetsSourceControl[] mTempControls;
    HashSet<View> mTempHashSet;
    private final InsetsState mTempInsets;
    final Rect mTempRect;
    final Thread mThread;
    final Rect mTmpFrame;
    final int[] mTmpLocation;
    final Rect mTmpRect;
    final TypedValue mTmpValue;
    private final SurfaceControl.Transaction mTransaction;
    CompatibilityInfo.Translator mTranslator;
    final Region mTransparentRegion;
    int mTraversalBarrier;
    final TraversalRunnable mTraversalRunnable;
    public boolean mTraversalScheduled;
    private int mTypesHiddenByFlags;
    boolean mUnbufferedInputDispatch;
    int mUnbufferedInputSource;
    private final UnhandledKeyManager mUnhandledKeyManager;
    boolean mUpcomingInTouchMode;
    boolean mUpcomingWindowFocus;
    private boolean mUseBLASTAdapter;
    private boolean mUseMTRenderer;
    View mView;
    final ViewConfiguration mViewConfiguration;
    private int mViewLayoutDirectionInitial;
    int mViewVisibility;
    final Rect mVisRect;
    int mWidth;
    boolean mWillDrawSoon;
    final Rect mWinFrame;
    final W mWindow;
    public final WindowManager.LayoutParams mWindowAttributes;
    boolean mWindowAttributesChanged;
    final ArrayList<WindowCallbacks> mWindowCallbacks;
    CountDownLatch mWindowDrawCountDown;
    boolean mWindowFocusChanged;
    final IWindowSession mWindowSession;
    private static final String USE_NEW_INSETS_PROPERTY = "persist.debug.new_insets";
    public static int sNewInsetsMode = SystemProperties.getInt(USE_NEW_INSETS_PROPERTY, 2);
    static final ThreadLocal<HandlerActionQueue> sRunQueues = new ThreadLocal<>();
    static final ArrayList<Runnable> sFirstDrawHandlers = new ArrayList<>();
    static boolean sFirstDrawComplete = false;
    private static final ArrayList<ConfigChangedCallback> sConfigCallbacks = new ArrayList<>();
    private static boolean sCompatibilityDone = false;
    static final Interpolator mResizeInterpolator = new AccelerateDecelerateInterpolator();

    public interface ActivityConfigCallback {
        void onConfigurationChanged(Configuration configuration, int i);
    }

    public interface ConfigChangedCallback {
        void onConfigurationChanged(Configuration configuration);
    }

    interface SurfaceChangedCallback {
        void surfaceCreated(SurfaceControl.Transaction transaction);

        void surfaceDestroyed();

        void surfaceReplaced(SurfaceControl.Transaction transaction);
    }

    @Override // android.view.ViewParent
    public void bringChildToFront(View view) {
    }

    @Override // android.view.ViewParent
    public boolean canResolveLayoutDirection() {
        return true;
    }

    @Override // android.view.ViewParent
    public boolean canResolveTextAlignment() {
        return true;
    }

    @Override // android.view.ViewParent
    public boolean canResolveTextDirection() {
        return true;
    }

    @Override // android.view.ViewParent
    public void childDrawableStateChanged(View view) {
    }

    @Override // android.view.ViewParent
    public void childHasTransientStateChanged(View view, boolean z) {
    }

    @Override // android.view.ViewParent
    public void createContextMenu(ContextMenu contextMenu) {
    }

    @Override // android.view.ViewParent
    public int getLayoutDirection() {
        return 0;
    }

    @Override // android.view.ViewParent
    public ViewParent getParent() {
        return null;
    }

    @Override // android.view.ViewParent
    public ViewParent getParentForAccessibility() {
        return null;
    }

    @Override // android.view.ViewParent
    public int getTextAlignment() {
        return 1;
    }

    @Override // android.view.ViewParent
    public int getTextDirection() {
        return 1;
    }

    @Override // android.view.ViewParent
    public boolean isLayoutDirectionResolved() {
        return true;
    }

    @Override // android.view.ViewParent
    public boolean isTextAlignmentResolved() {
        return true;
    }

    @Override // android.view.ViewParent
    public boolean isTextDirectionResolved() {
        return true;
    }

    @Override // android.view.ViewParent
    public boolean onNestedFling(View view, float f, float f2, boolean z) {
        return false;
    }

    @Override // android.view.ViewParent
    public boolean onNestedPreFling(View view, float f, float f2) {
        return false;
    }

    @Override // android.view.ViewParent
    public boolean onNestedPrePerformAccessibilityAction(View view, int i, Bundle bundle) {
        return false;
    }

    @Override // android.view.ViewParent
    public void onNestedPreScroll(View view, int i, int i2, int[] iArr) {
    }

    @Override // android.view.ViewParent
    public void onNestedScroll(View view, int i, int i2, int i3, int i4) {
    }

    @Override // android.view.ViewParent
    public void onNestedScrollAccepted(View view, View view2, int i) {
    }

    @Override // android.view.ViewParent
    public boolean onStartNestedScroll(View view, View view2, int i) {
        return false;
    }

    @Override // android.view.ViewParent
    public void onStopNestedScroll(View view) {
    }

    @Override // android.view.ViewParent
    public void requestDisallowInterceptTouchEvent(boolean z) {
    }

    @Override // android.view.ViewParent
    public boolean showContextMenuForChild(View view) {
        return false;
    }

    @Override // android.view.ViewParent
    public boolean showContextMenuForChild(View view, float f, float f2) {
        return false;
    }

    @Override // android.view.ViewParent
    public ActionMode startActionModeForChild(View view, ActionMode.Callback callback) {
        return null;
    }

    @Override // android.view.ViewParent
    public ActionMode startActionModeForChild(View view, ActionMode.Callback callback, int i) {
        return null;
    }

    public ImeFocusController getImeFocusController() {
        return this.mImeFocusController;
    }

    public ScrollCaptureClient getScrollCaptureClient() {
        return this.mScrollCaptureClient;
    }

    static final class SystemUiVisibilityInfo {
        int globalVisibility;
        int localChanges;
        int localValue;
        int seq;

        SystemUiVisibilityInfo() {
        }
    }

    public ViewRootImpl(Context context, Display display) {
        this(context, display, WindowManagerGlobal.getWindowSession(), false);
    }

    public ViewRootImpl(Context context, Display display, IWindowSession iWindowSession) {
        this(context, display, iWindowSession, false);
    }

    public ViewRootImpl(Context context, Display display, IWindowSession iWindowSession, boolean z) {
        this.mWindowCallbacks = new ArrayList<>();
        this.mTmpLocation = new int[2];
        this.mTmpValue = new TypedValue();
        this.mWindowAttributes = new WindowManager.LayoutParams();
        this.mAppVisible = true;
        this.mForceDecorViewVisibility = false;
        this.mOrigWindowType = -1;
        this.mStopped = false;
        this.mIsAmbientMode = false;
        this.mPausedForTransition = false;
        this.mLastInCompatMode = false;
        this.mDispatchedSystemUiVisibility = sNewInsetsMode == 2 ? 0 : -1;
        this.mSurfaceSize = new Point();
        this.mTempBoundsRect = new Rect();
        this.mContentCaptureEnabled = 0;
        this.mUnbufferedInputSource = 0;
        this.mPendingInputEventQueueLengthCounterName = "pq";
        this.mUnhandledKeyManager = new UnhandledKeyManager();
        this.mWindowAttributesChanged = false;
        this.mSurface = new Surface();
        this.mSurfaceControl = new SurfaceControl();
        this.mBlastSurfaceControl = new SurfaceControl();
        this.mSurfaceChangedTransaction = new SurfaceControl.Transaction();
        this.mSurfaceSession = new SurfaceSession();
        this.mTransaction = new SurfaceControl.Transaction();
        this.mTmpFrame = new Rect();
        this.mTmpRect = new Rect();
        this.mPendingBackDropFrame = new Rect();
        this.mPendingDisplayCutout = new DisplayCutout.ParcelableWrapper(DisplayCutout.NO_CUTOUT);
        this.mTempInsets = new InsetsState();
        this.mTempControls = new InsetsSourceControl[20];
        this.mLastGivenInsets = new ViewTreeObserver.InternalInsetsInfo();
        this.mTypesHiddenByFlags = 0;
        this.mLastConfigurationFromResources = new Configuration();
        this.mLastReportedMergedConfiguration = new MergedConfiguration();
        this.mPendingMergedConfiguration = new MergedConfiguration();
        this.mDragPoint = new PointF();
        this.mLastTouchPoint = new PointF();
        this.mFpsStartTime = -1L;
        this.mFpsPrevTime = -1L;
        this.mPointerIconType = 1;
        this.mCustomPointerIcon = null;
        this.mAccessibilityInteractionConnectionManager = new AccessibilityInteractionConnectionManager();
        this.mInLayout = false;
        this.mLayoutRequesters = new ArrayList<>();
        this.mHandlingLayoutInLayoutRequest = false;
        this.mInputEventConsistencyVerifier = InputEventConsistencyVerifier.isInstrumentationEnabled() ? new InputEventConsistencyVerifier(this, 0) : null;
        this.mGestureExclusionTracker = new GestureExclusionTracker();
        this.mRtBLASTSyncTransaction = new SurfaceControl.Transaction();
        this.mSendNextFrameToWm = false;
        this.mTag = TAG;
        this.mProfile = false;
        this.mDisplayListener = new DisplayManager.DisplayListener() { // from class: android.view.ViewRootImpl.1
            private int toViewScreenState(int i) {
                return i == 1 ? 0 : 1;
            }

            @Override // android.hardware.display.DisplayManager.DisplayListener
            public void onDisplayAdded(int i) {
            }

            @Override // android.hardware.display.DisplayManager.DisplayListener
            public void onDisplayRemoved(int i) {
            }

            @Override // android.hardware.display.DisplayManager.DisplayListener
            public void onDisplayChanged(int i) {
                int i2;
                int state;
                if (ViewRootImpl.this.mView == null || ViewRootImpl.this.mDisplay.getDisplayId() != i || (i2 = ViewRootImpl.this.mAttachInfo.mDisplayState) == (state = ViewRootImpl.this.mDisplay.getState())) {
                    return;
                }
                ViewRootImpl.this.mAttachInfo.mDisplayState = state;
                ViewRootImpl.this.pokeDrawLockIfNeeded();
                if (i2 != 0) {
                    int viewScreenState = toViewScreenState(i2);
                    int viewScreenState2 = toViewScreenState(state);
                    if (viewScreenState != viewScreenState2) {
                        ViewRootImpl.this.mView.dispatchScreenStateChanged(viewScreenState2);
                    }
                    if (i2 == 1) {
                        ViewRootImpl.this.mFullRedrawNeeded = true;
                        ViewRootImpl.this.scheduleTraversals();
                    }
                }
            }
        };
        this.mSurfaceChangedCallbacks = new ArrayList<>();
        this.mDrawsNeededToReport = 0;
        this.mHandler = new ViewRootHandler();
        this.mTraversalRunnable = new TraversalRunnable();
        this.mConsumedBatchedInputRunnable = new ConsumeBatchedInputRunnable();
        this.mConsumeBatchedInputImmediatelyRunnable = new ConsumeBatchedInputImmediatelyRunnable();
        this.mInvalidateOnAnimationRunnable = new InvalidateOnAnimationRunnable();
        this.mContext = context;
        this.mWindowSession = iWindowSession;
        this.mDisplay = display;
        this.mBasePackageName = context.getBasePackageName();
        this.mThread = Thread.currentThread();
        WindowLeaked windowLeaked = new WindowLeaked(null);
        this.mLocation = windowLeaked;
        windowLeaked.fillInStackTrace();
        this.mWidth = -1;
        this.mHeight = -1;
        this.mDirty = new Rect();
        this.mTempRect = new Rect();
        this.mVisRect = new Rect();
        this.mWinFrame = new Rect();
        this.mWindow = new W(this);
        this.mLeashToken = new Binder();
        this.mTargetSdkVersion = context.getApplicationInfo().targetSdkVersion;
        this.mViewVisibility = 8;
        this.mTransparentRegion = new Region();
        this.mPreviousTransparentRegion = new Region();
        this.mFirst = true;
        this.mPerformContentCapture = true;
        this.mAdded = false;
        this.mAttachInfo = new View.AttachInfo(this.mWindowSession, this.mWindow, display, this, this.mHandler, this, context);
        this.mCompatibleVisibilityInfo = new SystemUiVisibilityInfo();
        AccessibilityManager accessibilityManager = AccessibilityManager.getInstance(context);
        this.mAccessibilityManager = accessibilityManager;
        accessibilityManager.addAccessibilityStateChangeListener(this.mAccessibilityInteractionConnectionManager, this.mHandler);
        HighContrastTextManager highContrastTextManager = new HighContrastTextManager();
        this.mHighContrastTextManager = highContrastTextManager;
        this.mAccessibilityManager.addHighTextContrastStateChangeListener(highContrastTextManager, this.mHandler);
        this.mViewConfiguration = ViewConfiguration.get(context);
        this.mDensity = context.getResources().getDisplayMetrics().densityDpi;
        this.mNoncompatDensity = context.getResources().getDisplayMetrics().noncompatDensityDpi;
        this.mFallbackEventHandler = new PhoneFallbackEventHandler(context);
        this.mChoreographer = z ? Choreographer.getSfInstance() : Choreographer.getInstance();
        this.mDisplayManager = (DisplayManager) context.getSystemService(Context.DISPLAY_SERVICE);
        this.mInsetsController = new InsetsController(new ViewRootInsetsControllerHost(this));
        String string = context.getResources().getString(R.string.config_inputEventCompatProcessorOverrideClassName);
        if (string.isEmpty()) {
            this.mInputCompatProcessor = new InputEventCompatProcessor(context);
        } else {
            try {
                try {
                    this.mInputCompatProcessor = (InputEventCompatProcessor) Class.forName(string).getConstructor(Context.class).newInstance(context);
                } catch (Exception e) {
                    Log.e(TAG, "Unable to create the InputEventCompatProcessor. ", e);
                    this.mInputCompatProcessor = null;
                }
            } catch (Throwable th) {
                this.mInputCompatProcessor = null;
                throw th;
            }
        }
        if (!sCompatibilityDone) {
            sAlwaysAssignFocus = this.mTargetSdkVersion < 28;
            sCompatibilityDone = true;
        }
        loadSystemProperties();
        this.mImeFocusController = new ImeFocusController(this);
    }

    public static void addFirstDrawHandler(Runnable runnable) {
        synchronized (sFirstDrawHandlers) {
            if (!sFirstDrawComplete) {
                sFirstDrawHandlers.add(runnable);
            }
        }
    }

    public static void addConfigCallback(ConfigChangedCallback configChangedCallback) {
        synchronized (sConfigCallbacks) {
            sConfigCallbacks.add(configChangedCallback);
        }
    }

    public void setActivityConfigCallback(ActivityConfigCallback activityConfigCallback) {
        this.mActivityConfigCallback = activityConfigCallback;
    }

    public void setOnContentApplyWindowInsetsListener(Window.OnContentApplyWindowInsetsListener onContentApplyWindowInsetsListener) {
        this.mAttachInfo.mContentOnApplyWindowInsetsListener = onContentApplyWindowInsetsListener;
        if (this.mFirst) {
            return;
        }
        requestFitSystemWindows();
    }

    public void addWindowCallbacks(WindowCallbacks windowCallbacks) {
        synchronized (this.mWindowCallbacks) {
            this.mWindowCallbacks.add(windowCallbacks);
        }
    }

    public void removeWindowCallbacks(WindowCallbacks windowCallbacks) {
        synchronized (this.mWindowCallbacks) {
            this.mWindowCallbacks.remove(windowCallbacks);
        }
    }

    public void reportDrawFinish() {
        CountDownLatch countDownLatch = this.mWindowDrawCountDown;
        if (countDownLatch != null) {
            countDownLatch.countDown();
        }
    }

    public void profile() {
        this.mProfile = true;
    }

    static boolean isInTouchMode() {
        IWindowSession iWindowSessionPeekWindowSession = WindowManagerGlobal.peekWindowSession();
        if (iWindowSessionPeekWindowSession == null) {
            return false;
        }
        try {
            return iWindowSessionPeekWindowSession.getInTouchMode();
        } catch (RemoteException unused) {
            return false;
        }
    }

    public void notifyChildRebuilt() {
        if (this.mView instanceof RootViewSurfaceTaker) {
            SurfaceHolder.Callback2 callback2 = this.mSurfaceHolderCallback;
            if (callback2 != null) {
                this.mSurfaceHolder.removeCallback(callback2);
            }
            SurfaceHolder.Callback2 callback2WillYouTakeTheSurface = ((RootViewSurfaceTaker) this.mView).willYouTakeTheSurface();
            this.mSurfaceHolderCallback = callback2WillYouTakeTheSurface;
            if (callback2WillYouTakeTheSurface != null) {
                TakenSurfaceHolder takenSurfaceHolder = new TakenSurfaceHolder();
                this.mSurfaceHolder = takenSurfaceHolder;
                takenSurfaceHolder.setFormat(0);
                this.mSurfaceHolder.addCallback(this.mSurfaceHolderCallback);
            } else {
                this.mSurfaceHolder = null;
            }
            InputQueue.Callback callbackWillYouTakeTheInputQueue = ((RootViewSurfaceTaker) this.mView).willYouTakeTheInputQueue();
            this.mInputQueueCallback = callbackWillYouTakeTheInputQueue;
            if (callbackWillYouTakeTheInputQueue != null) {
                callbackWillYouTakeTheInputQueue.onInputQueueCreated(this.mInputQueue);
            }
        }
    }

    public void setView(View view, WindowManager.LayoutParams layoutParams, View view2) {
        setView(view, layoutParams, view2, UserHandle.myUserId());
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void setView(View view, WindowManager.LayoutParams layoutParams, View view2, int i) {
        boolean z;
        boolean z2;
        boolean z3;
        PendingInsetsController pendingInsetsControllerProvidePendingInsetsController;
        synchronized (this) {
            if (this.mView == null) {
                this.mView = view;
                this.mAttachInfo.mDisplayState = this.mDisplay.getState();
                this.mDisplayManager.registerDisplayListener(this.mDisplayListener, this.mHandler);
                this.mViewLayoutDirectionInitial = this.mView.getRawLayoutDirection();
                this.mFallbackEventHandler.setView(view);
                this.mWindowAttributes.copyFrom(layoutParams);
                if (this.mWindowAttributes.packageName == null) {
                    this.mWindowAttributes.packageName = this.mBasePackageName;
                }
                this.mWindowAttributes.privateFlags |= 33554432;
                WindowManager.LayoutParams layoutParams2 = this.mWindowAttributes;
                setTag();
                this.mClientWindowLayoutFlags = layoutParams2.flags;
                setAccessibilityFocus(null, null);
                if (view instanceof RootViewSurfaceTaker) {
                    SurfaceHolder.Callback2 callback2WillYouTakeTheSurface = ((RootViewSurfaceTaker) view).willYouTakeTheSurface();
                    this.mSurfaceHolderCallback = callback2WillYouTakeTheSurface;
                    if (callback2WillYouTakeTheSurface != null) {
                        TakenSurfaceHolder takenSurfaceHolder = new TakenSurfaceHolder();
                        this.mSurfaceHolder = takenSurfaceHolder;
                        takenSurfaceHolder.setFormat(0);
                        this.mSurfaceHolder.addCallback(this.mSurfaceHolderCallback);
                    }
                }
                if (!layoutParams2.hasManualSurfaceInsets) {
                    layoutParams2.setSurfaceInsets(view, false, true);
                }
                CompatibilityInfo compatibilityInfo = this.mDisplay.getDisplayAdjustments().getCompatibilityInfo();
                this.mTranslator = compatibilityInfo.getTranslator();
                if (this.mSurfaceHolder == null) {
                    enableHardwareAcceleration(layoutParams2);
                    boolean z4 = this.mAttachInfo.mThreadedRenderer != null;
                    if (this.mUseMTRenderer != z4) {
                        endDragResizing();
                        this.mUseMTRenderer = z4;
                    }
                }
                if (this.mTranslator != null) {
                    this.mSurface.setCompatibilityTranslator(this.mTranslator);
                    layoutParams2.backup();
                    this.mTranslator.translateWindowLayout(layoutParams2);
                    z = true;
                } else {
                    z = false;
                }
                if (!compatibilityInfo.supportsScreen()) {
                    layoutParams2.privateFlags |= 128;
                    this.mLastInCompatMode = true;
                }
                this.mSoftInputMode = layoutParams2.softInputMode;
                this.mWindowAttributesChanged = true;
                this.mAttachInfo.mRootView = view;
                this.mAttachInfo.mScalingRequired = this.mTranslator != null;
                this.mAttachInfo.mApplicationScale = this.mTranslator == null ? 1.0f : this.mTranslator.applicationScale;
                if (view2 != null) {
                    this.mAttachInfo.mPanelParentWindowToken = view2.getApplicationWindowToken();
                }
                this.mAdded = true;
                requestLayout();
                InputChannel inputChannel = (this.mWindowAttributes.inputFeatures & 2) == 0 ? new InputChannel() : null;
                this.mForceDecorViewVisibility = (this.mWindowAttributes.privateFlags & 16384) != 0;
                try {
                    try {
                        this.mOrigWindowType = this.mWindowAttributes.type;
                        this.mAttachInfo.mRecomputeGlobalAttributes = true;
                        collectViewAttributes();
                        adjustLayoutParamsForCompatibility(this.mWindowAttributes);
                    } catch (RemoteException e) {
                        e = e;
                        z2 = false;
                    }
                    try {
                        int iAddToDisplayAsUser = this.mWindowSession.addToDisplayAsUser(this.mWindow, this.mSeq, this.mWindowAttributes, getHostVisibility(), this.mDisplay.getDisplayId(), i, this.mTmpFrame, this.mAttachInfo.mContentInsets, this.mAttachInfo.mStableInsets, this.mAttachInfo.mDisplayCutout, inputChannel, this.mTempInsets, this.mTempControls);
                        setFrame(this.mTmpFrame);
                        if (this.mTranslator != null) {
                            this.mTranslator.translateRectInScreenToAppWindow(this.mAttachInfo.mContentInsets);
                        }
                        this.mPendingDisplayCutout.set(this.mAttachInfo.mDisplayCutout);
                        int i2 = iAddToDisplayAsUser & 4;
                        this.mAttachInfo.mAlwaysConsumeSystemBars = i2 != 0;
                        this.mPendingAlwaysConsumeSystemBars = this.mAttachInfo.mAlwaysConsumeSystemBars;
                        this.mInsetsController.onStateChanged(this.mTempInsets);
                        this.mInsetsController.onControlsChanged(this.mTempControls);
                        if (iAddToDisplayAsUser < 0) {
                            this.mAttachInfo.mRootView = null;
                            this.mAdded = false;
                            this.mFallbackEventHandler.setView(null);
                            unscheduleTraversals();
                            setAccessibilityFocus(null, null);
                            switch (iAddToDisplayAsUser) {
                                case -11:
                                    throw new WindowManager.BadTokenException("Unable to add Window " + this.mWindow + " -- requested userId is not valid");
                                case -10:
                                    throw new WindowManager.InvalidDisplayException("Unable to add window " + this.mWindow + " -- the specified window type " + this.mWindowAttributes.type + " is not valid");
                                case -9:
                                    throw new WindowManager.InvalidDisplayException("Unable to add window " + this.mWindow + " -- the specified display can not be found");
                                case -8:
                                    throw new WindowManager.BadTokenException("Unable to add window " + this.mWindow + " -- permission denied for window type " + this.mWindowAttributes.type);
                                case -7:
                                    throw new WindowManager.BadTokenException("Unable to add window " + this.mWindow + " -- another window of type " + this.mWindowAttributes.type + " already exists");
                                case -6:
                                    return;
                                case -5:
                                    throw new WindowManager.BadTokenException("Unable to add window -- window " + this.mWindow + " has already been added");
                                case -4:
                                    throw new WindowManager.BadTokenException("Unable to add window -- app for token " + layoutParams2.token + " is exiting");
                                case -3:
                                    throw new WindowManager.BadTokenException("Unable to add window -- token " + layoutParams2.token + " is not for an application");
                                case -2:
                                case -1:
                                    throw new WindowManager.BadTokenException("Unable to add window -- token " + layoutParams2.token + " is not valid; is your activity running?");
                                default:
                                    throw new RuntimeException("Unable to add window -- unknown error code " + iAddToDisplayAsUser);
                            }
                        }
                        if ((iAddToDisplayAsUser & 8) != 0) {
                            z3 = true;
                            this.mUseBLASTAdapter = true;
                        } else {
                            z3 = true;
                        }
                        if (i2 != 0) {
                            this.mEnableTripleBuffering = z3;
                        }
                        if (view instanceof RootViewSurfaceTaker) {
                            this.mInputQueueCallback = ((RootViewSurfaceTaker) view).willYouTakeTheInputQueue();
                        }
                        if (inputChannel != null) {
                            if (this.mInputQueueCallback != null) {
                                InputQueue inputQueue = new InputQueue();
                                this.mInputQueue = inputQueue;
                                this.mInputQueueCallback.onInputQueueCreated(inputQueue);
                            }
                            this.mInputEventReceiver = new WindowInputEventReceiver(inputChannel, Looper.myLooper());
                        }
                        view.assignParent(this);
                        this.mAddedTouchMode = (iAddToDisplayAsUser & 1) != 0;
                        this.mAppVisible = (iAddToDisplayAsUser & 2) != 0;
                        if (this.mAccessibilityManager.isEnabled()) {
                            this.mAccessibilityInteractionConnectionManager.ensureConnection();
                        }
                        if (view.getImportantForAccessibility() == 0) {
                            view.setImportantForAccessibility(1);
                        }
                        CharSequence title = layoutParams2.getTitle();
                        this.mSyntheticInputStage = new SyntheticInputStage();
                        EarlyPostImeInputStage earlyPostImeInputStage = new EarlyPostImeInputStage(new NativePostImeInputStage(new ViewPostImeInputStage(this.mSyntheticInputStage), "aq:native-post-ime:" + ((Object) title)));
                        this.mFirstInputStage = new NativePreImeInputStage(new ViewPreImeInputStage(new ImeInputStage(earlyPostImeInputStage, "aq:ime:" + ((Object) title))), "aq:native-pre-ime:" + ((Object) title));
                        this.mFirstPostImeInputStage = earlyPostImeInputStage;
                        this.mPendingInputEventQueueLengthCounterName = "aq:pending:" + ((Object) title);
                        if ((this.mView instanceof RootViewSurfaceTaker) && (pendingInsetsControllerProvidePendingInsetsController = ((RootViewSurfaceTaker) this.mView).providePendingInsetsController()) != null) {
                            pendingInsetsControllerProvidePendingInsetsController.replayAndAttach(this.mInsetsController);
                        }
                    } catch (RemoteException e2) {
                        e = e2;
                        z2 = false;
                        this.mAdded = z2;
                        this.mView = null;
                        this.mAttachInfo.mRootView = null;
                        this.mFallbackEventHandler.setView(null);
                        unscheduleTraversals();
                        setAccessibilityFocus(null, null);
                        throw new RuntimeException("Adding window failed", e);
                    }
                } finally {
                    if (z) {
                        layoutParams2.restore();
                    }
                }
            }
        }
    }

    private void setTag() {
        String[] strArrSplit = this.mWindowAttributes.getTitle().toString().split("\\.");
        if (strArrSplit.length > 0) {
            this.mTag = "ViewRootImpl[" + strArrSplit[strArrSplit.length - 1] + "]";
        }
    }

    public int getWindowFlags() {
        return this.mWindowAttributes.flags;
    }

    public int getDisplayId() {
        return this.mDisplay.getDisplayId();
    }

    public CharSequence getTitle() {
        return this.mWindowAttributes.getTitle();
    }

    public int getWidth() {
        return this.mWidth;
    }

    public int getHeight() {
        return this.mHeight;
    }

    void destroyHardwareResources() {
        ThreadedRenderer threadedRenderer = this.mAttachInfo.mThreadedRenderer;
        if (threadedRenderer != null) {
            if (Looper.myLooper() != this.mAttachInfo.mHandler.getLooper()) {
                this.mAttachInfo.mHandler.postAtFrontOfQueue(new Runnable() { // from class: android.view.-$$Lambda$dj1hfDQd0iEp_uBDBPEUMMYJJwk
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.destroyHardwareResources();
                    }
                });
            } else {
                threadedRenderer.destroyHardwareResources(this.mView);
                threadedRenderer.destroy();
            }
        }
    }

    public void detachFunctor(long j) {
        if (this.mAttachInfo.mThreadedRenderer != null) {
            this.mAttachInfo.mThreadedRenderer.stopDrawing();
        }
    }

    public static void invokeFunctor(long j, boolean z) {
        ThreadedRenderer.invokeFunctor(j, z);
    }

    public void registerAnimatingRenderNode(RenderNode renderNode) {
        if (this.mAttachInfo.mThreadedRenderer != null) {
            this.mAttachInfo.mThreadedRenderer.registerAnimatingRenderNode(renderNode);
            return;
        }
        if (this.mAttachInfo.mPendingAnimatingRenderNodes == null) {
            this.mAttachInfo.mPendingAnimatingRenderNodes = new ArrayList();
        }
        this.mAttachInfo.mPendingAnimatingRenderNodes.add(renderNode);
    }

    public void registerVectorDrawableAnimator(NativeVectorDrawableAnimator nativeVectorDrawableAnimator) {
        if (this.mAttachInfo.mThreadedRenderer != null) {
            this.mAttachInfo.mThreadedRenderer.registerVectorDrawableAnimator(nativeVectorDrawableAnimator);
        }
    }

    public void registerRtFrameCallback(final HardwareRenderer.FrameDrawingCallback frameDrawingCallback) {
        if (this.mAttachInfo.mThreadedRenderer != null) {
            this.mAttachInfo.mThreadedRenderer.registerRtFrameCallback(new HardwareRenderer.FrameDrawingCallback() { // from class: android.view.-$$Lambda$ViewRootImpl$IReiNMSbDakZSGbIZuL_ifaFWn8
                @Override // android.graphics.HardwareRenderer.FrameDrawingCallback
                public final void onFrameDraw(long j) {
                    ViewRootImpl.lambda$registerRtFrameCallback$0(frameDrawingCallback, j);
                }
            });
        }
    }

    static /* synthetic */ void lambda$registerRtFrameCallback$0(HardwareRenderer.FrameDrawingCallback frameDrawingCallback, long j) {
        try {
            frameDrawingCallback.onFrameDraw(j);
        } catch (Exception e) {
            Log.e(TAG, "Exception while executing onFrameDraw", e);
        }
    }

    private void enableHardwareAcceleration(WindowManager.LayoutParams layoutParams) {
        boolean z = false;
        this.mAttachInfo.mHardwareAccelerated = false;
        this.mAttachInfo.mHardwareAccelerationRequested = false;
        if (this.mTranslator != null) {
            return;
        }
        if (((layoutParams.flags & 16777216) != 0) && ThreadedRenderer.isAvailable()) {
            boolean z2 = (layoutParams.privateFlags & 1) != 0;
            boolean z3 = (layoutParams.privateFlags & 2) != 0;
            if (z2) {
                this.mAttachInfo.mHardwareAccelerationRequested = true;
                return;
            }
            if (!ThreadedRenderer.sRendererDisabled || (ThreadedRenderer.sSystemRendererDisabled && z3)) {
                if (this.mAttachInfo.mThreadedRenderer != null) {
                    this.mAttachInfo.mThreadedRenderer.destroy();
                }
                Rect rect = layoutParams.surfaceInsets;
                boolean z4 = layoutParams.format != -1 || (rect.left != 0 || rect.right != 0 || rect.top != 0 || rect.bottom != 0);
                if (this.mContext.getResources().getConfiguration().isScreenWideColorGamut() && layoutParams.getColorMode() == 1) {
                    z = true;
                }
                this.mAttachInfo.mThreadedRenderer = ThreadedRenderer.create(this.mContext, z4, layoutParams.getTitle().toString());
                this.mAttachInfo.mThreadedRenderer.setWideGamut(z);
                updateForceDarkMode();
                if (this.mAttachInfo.mThreadedRenderer != null) {
                    View.AttachInfo attachInfo = this.mAttachInfo;
                    attachInfo.mHardwareAccelerationRequested = true;
                    attachInfo.mHardwareAccelerated = true;
                }
            }
        }
    }

    private int getNightMode() {
        return this.mContext.getResources().getConfiguration().uiMode & 48;
    }

    private void updateForceDarkMode() {
        if (this.mAttachInfo.mThreadedRenderer == null) {
            return;
        }
        boolean z = getNightMode() == 32;
        if (z) {
            boolean z2 = SystemProperties.getBoolean(ThreadedRenderer.DEBUG_FORCE_DARK, false);
            TypedArray typedArrayObtainStyledAttributes = this.mContext.obtainStyledAttributes(R.styleable.Theme);
            boolean z3 = typedArrayObtainStyledAttributes.getBoolean(279, true) && typedArrayObtainStyledAttributes.getBoolean(278, z2);
            typedArrayObtainStyledAttributes.recycle();
            z = z3;
        }
        if (this.mAttachInfo.mThreadedRenderer.setForceDark(z)) {
            invalidateWorld(this.mView);
        }
    }

    public View getView() {
        return this.mView;
    }

    final WindowLeaked getLocation() {
        return this.mLocation;
    }

    void setLayoutParams(WindowManager.LayoutParams layoutParams, boolean z) {
        synchronized (this) {
            int i = this.mWindowAttributes.surfaceInsets.left;
            int i2 = this.mWindowAttributes.surfaceInsets.top;
            int i3 = this.mWindowAttributes.surfaceInsets.right;
            int i4 = this.mWindowAttributes.surfaceInsets.bottom;
            int i5 = this.mWindowAttributes.softInputMode;
            boolean z2 = this.mWindowAttributes.hasManualSurfaceInsets;
            this.mClientWindowLayoutFlags = layoutParams.flags;
            int i6 = this.mWindowAttributes.privateFlags & 128;
            layoutParams.systemUiVisibility = this.mWindowAttributes.systemUiVisibility;
            layoutParams.subtreeSystemUiVisibility = this.mWindowAttributes.subtreeSystemUiVisibility;
            int iCopyFrom = this.mWindowAttributes.copyFrom(layoutParams);
            if ((524288 & iCopyFrom) != 0) {
                this.mAttachInfo.mRecomputeGlobalAttributes = true;
            }
            if ((iCopyFrom & 1) != 0) {
                this.mAttachInfo.mNeedsUpdateLightCenter = true;
            }
            if (this.mWindowAttributes.packageName == null) {
                this.mWindowAttributes.packageName = this.mBasePackageName;
            }
            WindowManager.LayoutParams layoutParams2 = this.mWindowAttributes;
            layoutParams2.privateFlags = i6 | layoutParams2.privateFlags;
            this.mWindowAttributes.privateFlags |= 33554432;
            if (this.mWindowAttributes.preservePreviousSurfaceInsets) {
                this.mWindowAttributes.surfaceInsets.set(i, i2, i3, i4);
                this.mWindowAttributes.hasManualSurfaceInsets = z2;
            } else if (this.mWindowAttributes.surfaceInsets.left != i || this.mWindowAttributes.surfaceInsets.top != i2 || this.mWindowAttributes.surfaceInsets.right != i3 || this.mWindowAttributes.surfaceInsets.bottom != i4) {
                this.mNeedsRendererSetup = true;
            }
            applyKeepScreenOnFlag(this.mWindowAttributes);
            if (z) {
                this.mSoftInputMode = layoutParams.softInputMode;
                requestLayout();
            }
            if ((layoutParams.softInputMode & 240) == 0) {
                this.mWindowAttributes.softInputMode = (this.mWindowAttributes.softInputMode & TrafficStats.TAG_SYSTEM_IMPERSONATION_RANGE_END) | (i5 & 240);
            }
            if (this.mWindowAttributes.softInputMode != i5) {
                requestFitSystemWindows();
            }
            this.mWindowAttributesChanged = true;
            scheduleTraversals();
        }
    }

    void handleAppVisibility(boolean z) {
        if (this.mAppVisible != z) {
            this.mAppVisible = z;
            this.mAppVisibilityChanged = true;
            scheduleTraversals();
            if (this.mAppVisible) {
                return;
            }
            WindowManagerGlobal.trimForeground();
        }
    }

    void handleGetNewSurface() {
        this.mNewSurfaceNeeded = true;
        this.mFullRedrawNeeded = true;
        scheduleTraversals();
    }

    public void onMovedToDisplay(int i, Configuration configuration) {
        if (this.mDisplay.getDisplayId() == i) {
            return;
        }
        updateInternalDisplay(i, this.mView.getResources());
        this.mImeFocusController.onMovedToDisplay();
        this.mAttachInfo.mDisplayState = this.mDisplay.getState();
        this.mView.dispatchMovedToDisplay(this.mDisplay, configuration);
    }

    private void updateInternalDisplay(int i, Resources resources) {
        Display adjustedDisplay = ResourcesManager.getInstance().getAdjustedDisplay(i, resources);
        if (adjustedDisplay == null) {
            Slog.w(TAG, "Cannot get desired display with Id: " + i);
            this.mDisplay = ResourcesManager.getInstance().getAdjustedDisplay(0, resources);
        } else {
            this.mDisplay = adjustedDisplay;
        }
        this.mContext.updateDisplay(this.mDisplay.getDisplayId());
    }

    void pokeDrawLockIfNeeded() {
        int i = this.mAttachInfo.mDisplayState;
        if (this.mView != null && this.mAdded && this.mTraversalScheduled) {
            if (i == 3 || i == 4) {
                try {
                    this.mWindowSession.pokeDrawLock(this.mWindow);
                } catch (RemoteException unused) {
                }
            }
        }
    }

    @Override // android.view.ViewParent
    public void requestFitSystemWindows() {
        checkThread();
        this.mApplyInsetsRequested = true;
        scheduleTraversals();
    }

    void notifyInsetsChanged() {
        View view;
        if (sNewInsetsMode == 0) {
            return;
        }
        this.mApplyInsetsRequested = true;
        requestLayout();
        if (View.sForceLayoutWhenInsetsChanged && (view = this.mView) != null) {
            forceLayout(view);
        }
        if (this.mIsInTraversal) {
            return;
        }
        scheduleTraversals();
    }

    @Override // android.view.ViewParent
    public void requestLayout() {
        if (this.mHandlingLayoutInLayoutRequest) {
            return;
        }
        checkThread();
        this.mLayoutRequested = true;
        scheduleTraversals();
    }

    @Override // android.view.ViewParent
    public boolean isLayoutRequested() {
        return this.mLayoutRequested;
    }

    @Override // android.view.ViewParent
    public void onDescendantInvalidated(View view, View view2) {
        if ((view2.mPrivateFlags & 64) != 0) {
            this.mIsAnimating = true;
        }
        invalidate();
    }

    void invalidate() {
        this.mDirty.set(0, 0, this.mWidth, this.mHeight);
        if (this.mWillDrawSoon) {
            return;
        }
        scheduleTraversals();
    }

    void invalidateWorld(View view) {
        view.invalidate();
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                invalidateWorld(viewGroup.getChildAt(i));
            }
        }
    }

    @Override // android.view.ViewParent
    public void invalidateChild(View view, Rect rect) {
        invalidateChildInParent(null, rect);
    }

    @Override // android.view.ViewParent
    public ViewParent invalidateChildInParent(int[] iArr, Rect rect) {
        checkThread();
        if (rect == null) {
            invalidate();
            return null;
        }
        if (rect.isEmpty() && !this.mIsAnimating) {
            return null;
        }
        if (this.mCurScrollY != 0 || this.mTranslator != null) {
            this.mTempRect.set(rect);
            rect = this.mTempRect;
            int i = this.mCurScrollY;
            if (i != 0) {
                rect.offset(0, -i);
            }
            CompatibilityInfo.Translator translator = this.mTranslator;
            if (translator != null) {
                translator.translateRectInAppWindowToScreen(rect);
            }
            if (this.mAttachInfo.mScalingRequired) {
                rect.inset(-1, -1);
            }
        }
        invalidateRectOnScreen(rect);
        return null;
    }

    private void invalidateRectOnScreen(Rect rect) {
        Rect rect2 = this.mDirty;
        rect2.union(rect.left, rect.top, rect.right, rect.bottom);
        float f = this.mAttachInfo.mApplicationScale;
        boolean zIntersect = rect2.intersect(0, 0, (int) ((this.mWidth * f) + 0.5f), (int) ((this.mHeight * f) + 0.5f));
        if (!zIntersect) {
            rect2.setEmpty();
        }
        if (this.mWillDrawSoon) {
            return;
        }
        if (zIntersect || this.mIsAnimating) {
            scheduleTraversals();
        }
    }

    public void setIsAmbientMode(boolean z) {
        this.mIsAmbientMode = z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setWindowStopped(boolean z) {
        checkThread();
        if (this.mStopped != z) {
            this.mStopped = z;
            ThreadedRenderer threadedRenderer = this.mAttachInfo.mThreadedRenderer;
            if (threadedRenderer != null) {
                threadedRenderer.setStopped(this.mStopped);
            }
            if (!this.mStopped) {
                this.mNewSurfaceNeeded = true;
                scheduleTraversals();
            } else {
                if (threadedRenderer != null) {
                    threadedRenderer.destroyHardwareResources(this.mView);
                }
                if (this.mSurface.isValid()) {
                    if (this.mSurfaceHolder != null) {
                        notifyHolderSurfaceDestroyed();
                    }
                    notifySurfaceDestroyed();
                }
                destroySurface();
            }
        }
        scheduleConsumeBatchedInputImmediately();
    }

    void addSurfaceChangedCallback(SurfaceChangedCallback surfaceChangedCallback) {
        this.mSurfaceChangedCallbacks.add(surfaceChangedCallback);
    }

    void removeSurfaceChangedCallback(SurfaceChangedCallback surfaceChangedCallback) {
        this.mSurfaceChangedCallbacks.remove(surfaceChangedCallback);
    }

    private void notifySurfaceCreated() {
        for (int i = 0; i < this.mSurfaceChangedCallbacks.size(); i++) {
            this.mSurfaceChangedCallbacks.get(i).surfaceCreated(this.mSurfaceChangedTransaction);
        }
    }

    private void notifySurfaceReplaced() {
        for (int i = 0; i < this.mSurfaceChangedCallbacks.size(); i++) {
            this.mSurfaceChangedCallbacks.get(i).surfaceReplaced(this.mSurfaceChangedTransaction);
        }
    }

    private void notifySurfaceDestroyed() {
        for (int i = 0; i < this.mSurfaceChangedCallbacks.size(); i++) {
            this.mSurfaceChangedCallbacks.get(i).surfaceDestroyed();
        }
    }

    public SurfaceControl getBoundsLayer() {
        if (this.mBoundsLayer == null) {
            this.mBoundsLayer = new SurfaceControl.Builder(this.mSurfaceSession).setContainerLayer().setName("Bounds for - " + getTitle().toString()).setParent(getRenderSurfaceControl()).setCallsite("ViewRootImpl.getBoundsLayer").build();
            setBoundsLayerCrop();
            this.mTransaction.show(this.mBoundsLayer).apply();
        }
        return this.mBoundsLayer;
    }

    Surface getOrCreateBLASTSurface(int i, int i2) {
        SurfaceControl surfaceControl;
        SurfaceControl surfaceControl2 = this.mSurfaceControl;
        if (surfaceControl2 == null || !surfaceControl2.isValid() || (surfaceControl = this.mBlastSurfaceControl) == null || !surfaceControl.isValid()) {
            return null;
        }
        BLASTBufferQueue bLASTBufferQueue = this.mBlastBufferQueue;
        if (bLASTBufferQueue == null) {
            BLASTBufferQueue bLASTBufferQueue2 = new BLASTBufferQueue(this.mBlastSurfaceControl, i, i2, this.mEnableTripleBuffering);
            this.mBlastBufferQueue = bLASTBufferQueue2;
            return bLASTBufferQueue2.getSurface();
        }
        bLASTBufferQueue.update(this.mBlastSurfaceControl, i, i2);
        return null;
    }

    private void setBoundsLayerCrop() {
        this.mTempBoundsRect.set(this.mWinFrame);
        this.mTempBoundsRect.offsetTo(this.mWindowAttributes.surfaceInsets.left, this.mWindowAttributes.surfaceInsets.top);
        this.mTransaction.setWindowCrop(this.mBoundsLayer, this.mTempBoundsRect);
    }

    private void updateBoundsLayer() {
        if (this.mBoundsLayer != null) {
            setBoundsLayerCrop();
            this.mTransaction.deferTransactionUntil(this.mBoundsLayer, getRenderSurfaceControl(), this.mSurface.getNextFrameNumber()).apply();
        }
    }

    private void destroySurface() {
        SurfaceControl surfaceControl = this.mBoundsLayer;
        if (surfaceControl != null) {
            surfaceControl.release();
            this.mBoundsLayer = null;
        }
        this.mSurface.release();
        this.mSurfaceControl.release();
        this.mBlastSurfaceControl.release();
        this.mBlastBufferQueue = null;
    }

    public void setPausedForTransition(boolean z) {
        this.mPausedForTransition = z;
    }

    @Override // android.view.ViewParent
    public boolean getChildVisibleRect(View view, Rect rect, Point point) {
        if (view != this.mView) {
            throw new RuntimeException("child is not mine, honest!");
        }
        return rect.intersect(0, 0, this.mWidth, this.mHeight);
    }

    int getHostVisibility() {
        if (this.mAppVisible || this.mForceDecorViewVisibility) {
            return this.mView.getVisibility();
        }
        return 8;
    }

    public void requestTransitionStart(LayoutTransition layoutTransition) {
        ArrayList<LayoutTransition> arrayList = this.mPendingTransitions;
        if (arrayList == null || !arrayList.contains(layoutTransition)) {
            if (this.mPendingTransitions == null) {
                this.mPendingTransitions = new ArrayList<>();
            }
            this.mPendingTransitions.add(layoutTransition);
        }
    }

    void notifyRendererOfFramePending() {
        if (this.mAttachInfo.mThreadedRenderer != null) {
            this.mAttachInfo.mThreadedRenderer.notifyFramePending();
        }
    }

    void scheduleTraversals() {
        if (this.mTraversalScheduled) {
            return;
        }
        this.mTraversalScheduled = true;
        this.mTraversalBarrier = this.mHandler.getLooper().getQueue().postSyncBarrier();
        this.mChoreographer.postCallback(3, this.mTraversalRunnable, null);
        notifyRendererOfFramePending();
        pokeDrawLockIfNeeded();
    }

    void unscheduleTraversals() {
        if (this.mTraversalScheduled) {
            this.mTraversalScheduled = false;
            this.mHandler.getLooper().getQueue().removeSyncBarrier(this.mTraversalBarrier);
            this.mChoreographer.removeCallbacks(3, this.mTraversalRunnable, null);
        }
    }

    void doTraversal() {
        if (this.mTraversalScheduled) {
            this.mTraversalScheduled = false;
            this.mHandler.getLooper().getQueue().removeSyncBarrier(this.mTraversalBarrier);
            if (this.mProfile) {
                Debug.startMethodTracing("ViewAncestor");
            }
            performTraversals();
            if (this.mProfile) {
                Debug.stopMethodTracing();
                this.mProfile = false;
            }
        }
    }

    private void applyKeepScreenOnFlag(WindowManager.LayoutParams layoutParams) {
        if (this.mAttachInfo.mKeepScreenOn) {
            layoutParams.flags |= 128;
        } else {
            layoutParams.flags = (layoutParams.flags & (-129)) | (this.mClientWindowLayoutFlags & 128);
        }
    }

    private boolean collectViewAttributes() {
        if (this.mAttachInfo.mRecomputeGlobalAttributes) {
            this.mAttachInfo.mRecomputeGlobalAttributes = false;
            boolean z = this.mAttachInfo.mKeepScreenOn;
            this.mAttachInfo.mKeepScreenOn = false;
            this.mAttachInfo.mSystemUiVisibility = 0;
            this.mAttachInfo.mHasSystemUiListeners = false;
            this.mView.dispatchCollectViewAttributes(this.mAttachInfo, 0);
            this.mAttachInfo.mSystemUiVisibility &= ~this.mAttachInfo.mDisabledSystemUiVisibility;
            WindowManager.LayoutParams layoutParams = this.mWindowAttributes;
            this.mAttachInfo.mSystemUiVisibility |= getImpliedSystemUiVisibility(layoutParams);
            SystemUiVisibilityInfo systemUiVisibilityInfo = this.mCompatibleVisibilityInfo;
            systemUiVisibilityInfo.globalVisibility = (systemUiVisibilityInfo.globalVisibility & (-2)) | (this.mAttachInfo.mSystemUiVisibility & 1);
            dispatchDispatchSystemUiVisibilityChanged(this.mCompatibleVisibilityInfo);
            if (this.mAttachInfo.mKeepScreenOn != z || this.mAttachInfo.mSystemUiVisibility != layoutParams.subtreeSystemUiVisibility || this.mAttachInfo.mHasSystemUiListeners != layoutParams.hasSystemUiListeners) {
                applyKeepScreenOnFlag(layoutParams);
                layoutParams.subtreeSystemUiVisibility = this.mAttachInfo.mSystemUiVisibility;
                layoutParams.hasSystemUiListeners = this.mAttachInfo.mHasSystemUiListeners;
                this.mView.dispatchWindowSystemUiVisiblityChanged(this.mAttachInfo.mSystemUiVisibility);
                return true;
            }
        }
        return false;
    }

    private int getImpliedSystemUiVisibility(WindowManager.LayoutParams layoutParams) {
        int i = (layoutParams.flags & 67108864) != 0 ? 1280 : 0;
        return (layoutParams.flags & 134217728) != 0 ? i | 768 : i;
    }

    void updateCompatSysUiVisibility(int i, boolean z, boolean z2) {
        if (i == 0 || i == 1) {
            if (sNewInsetsMode != 2) {
                return;
            }
            SystemUiVisibilityInfo systemUiVisibilityInfo = this.mCompatibleVisibilityInfo;
            int i2 = i == 0 ? 4 : 2;
            boolean z3 = (systemUiVisibilityInfo.globalVisibility & i2) == 0;
            if (z) {
                systemUiVisibilityInfo.globalVisibility &= ~i2;
                if (!z3 && z2) {
                    systemUiVisibilityInfo.localChanges |= i2;
                }
            } else {
                systemUiVisibilityInfo.globalVisibility |= i2;
                systemUiVisibilityInfo.localChanges &= ~i2;
            }
            dispatchDispatchSystemUiVisibilityChanged(systemUiVisibilityInfo);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearLowProfileModeIfNeeded(int i, boolean z) {
        SystemUiVisibilityInfo systemUiVisibilityInfo = this.mCompatibleVisibilityInfo;
        if ((i & WindowInsets.Type.systemBars()) == 0 || z || (systemUiVisibilityInfo.globalVisibility & 1) == 0) {
            return;
        }
        systemUiVisibilityInfo.globalVisibility &= -2;
        systemUiVisibilityInfo.localChanges |= 1;
        dispatchDispatchSystemUiVisibilityChanged(systemUiVisibilityInfo);
    }

    private void dispatchDispatchSystemUiVisibilityChanged(SystemUiVisibilityInfo systemUiVisibilityInfo) {
        if (this.mDispatchedSystemUiVisibility != systemUiVisibilityInfo.globalVisibility) {
            this.mHandler.removeMessages(17);
            ViewRootHandler viewRootHandler = this.mHandler;
            viewRootHandler.sendMessage(viewRootHandler.obtainMessage(17, systemUiVisibilityInfo));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleDispatchSystemUiVisibilityChanged(SystemUiVisibilityInfo systemUiVisibilityInfo) {
        if (this.mSeq != systemUiVisibilityInfo.seq && sNewInsetsMode != 2) {
            this.mSeq = systemUiVisibilityInfo.seq;
            this.mAttachInfo.mForceReportNewAttributes = true;
            scheduleTraversals();
        }
        if (this.mView == null) {
            return;
        }
        if (systemUiVisibilityInfo.localChanges != 0) {
            this.mView.updateLocalSystemUiVisibility(systemUiVisibilityInfo.localValue, systemUiVisibilityInfo.localChanges);
            systemUiVisibilityInfo.localChanges = 0;
        }
        int i = systemUiVisibilityInfo.globalVisibility & 7;
        if (this.mDispatchedSystemUiVisibility != i) {
            this.mDispatchedSystemUiVisibility = i;
            this.mView.dispatchSystemUiVisibilityChanged(i);
        }
    }

    public static void adjustLayoutParamsForCompatibility(WindowManager.LayoutParams layoutParams) {
        if (sNewInsetsMode != 2) {
            return;
        }
        int i = layoutParams.systemUiVisibility | layoutParams.subtreeSystemUiVisibility;
        int i2 = layoutParams.flags;
        int i3 = layoutParams.type;
        int i4 = layoutParams.softInputMode & 240;
        if ((layoutParams.privateFlags & 67108864) == 0) {
            layoutParams.insetsFlags.appearance = 0;
            if ((i & 1) != 0) {
                layoutParams.insetsFlags.appearance |= 4;
            }
            if ((i & 8192) != 0) {
                layoutParams.insetsFlags.appearance |= 8;
            }
            if ((i & 16) != 0) {
                layoutParams.insetsFlags.appearance |= 16;
            }
        }
        boolean z = true;
        if ((layoutParams.privateFlags & 134217728) == 0) {
            if ((i & 4096) != 0 || (i2 & 1024) != 0) {
                layoutParams.insetsFlags.behavior = 2;
            } else if ((i & 2048) != 0) {
                layoutParams.insetsFlags.behavior = 1;
            } else {
                layoutParams.insetsFlags.behavior = 0;
            }
        }
        layoutParams.privateFlags &= -1073741825;
        if ((layoutParams.privateFlags & 268435456) != 0) {
            return;
        }
        int fitInsetsTypes = layoutParams.getFitInsetsTypes();
        boolean zIsFitInsetsIgnoringVisibility = layoutParams.isFitInsetsIgnoringVisibility();
        if ((i & 1024) != 0 || (i2 & 256) != 0 || (67108864 & i2) != 0) {
            fitInsetsTypes &= ~WindowInsets.Type.statusBars();
        }
        if ((i & 512) != 0 || (i2 & 134217728) != 0) {
            fitInsetsTypes &= ~WindowInsets.Type.systemBars();
        }
        if (i3 != 2005 && i3 != 2003) {
            if ((WindowInsets.Type.systemBars() & fitInsetsTypes) == WindowInsets.Type.systemBars()) {
                if (i4 == 16) {
                    fitInsetsTypes |= WindowInsets.Type.ime();
                } else {
                    layoutParams.privateFlags |= 1073741824;
                }
            }
            z = zIsFitInsetsIgnoringVisibility;
        }
        layoutParams.setFitInsetsTypes(fitInsetsTypes);
        layoutParams.setFitInsetsIgnoringVisibility(z);
        layoutParams.privateFlags &= -268435457;
    }

    private void controlInsetsForCompatibility(WindowManager.LayoutParams layoutParams) {
        int iStatusBars;
        if (sNewInsetsMode != 2) {
            return;
        }
        int i = layoutParams.systemUiVisibility | layoutParams.subtreeSystemUiVisibility;
        int i2 = layoutParams.flags;
        int iNavigationBars = 0;
        boolean z = layoutParams.width == -1 && layoutParams.height == -1;
        boolean z2 = layoutParams.type >= 1 && layoutParams.type <= 99;
        boolean z3 = (this.mTypesHiddenByFlags & WindowInsets.Type.statusBars()) != 0;
        boolean z4 = (i & 4) != 0 || ((i2 & 1024) != 0 && z && z2);
        boolean z5 = (this.mTypesHiddenByFlags & WindowInsets.Type.navigationBars()) != 0;
        boolean z6 = (i & 2) != 0;
        if (z4 && !z3) {
            iNavigationBars = WindowInsets.Type.statusBars() | 0;
            iStatusBars = 0;
        } else {
            iStatusBars = (z4 || !z3) ? 0 : WindowInsets.Type.statusBars() | 0;
        }
        if (z6 && !z5) {
            iNavigationBars |= WindowInsets.Type.navigationBars();
        } else if (!z6 && z5) {
            iStatusBars |= WindowInsets.Type.navigationBars();
        }
        if (iNavigationBars != 0) {
            getInsetsController().hide(iNavigationBars);
        }
        if (iStatusBars != 0) {
            getInsetsController().show(iStatusBars);
        }
        int i3 = this.mTypesHiddenByFlags | iNavigationBars;
        this.mTypesHiddenByFlags = i3;
        this.mTypesHiddenByFlags = (~iStatusBars) & i3;
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0055  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean measureHierarchy(android.view.View r6, android.view.WindowManager.LayoutParams r7, android.content.res.Resources r8, int r9, int r10) {
        /*
            r5 = this;
            int r0 = r7.width
            r1 = 0
            r2 = 1
            r3 = -2
            if (r0 != r3) goto L55
            android.util.DisplayMetrics r0 = r8.getDisplayMetrics()
            r3 = 17105078(0x10500b6, float:2.4428752E-38)
            android.util.TypedValue r4 = r5.mTmpValue
            r8.getValue(r3, r4, r2)
            android.util.TypedValue r8 = r5.mTmpValue
            int r8 = r8.type
            r3 = 5
            if (r8 != r3) goto L22
            android.util.TypedValue r8 = r5.mTmpValue
            float r8 = r8.getDimension(r0)
            int r8 = (int) r8
            goto L23
        L22:
            r8 = r1
        L23:
            if (r8 == 0) goto L55
            if (r9 <= r8) goto L55
            int r0 = r7.width
            int r0 = getRootMeasureSpec(r8, r0)
            int r3 = r7.height
            int r3 = getRootMeasureSpec(r10, r3)
            r5.performMeasure(r0, r3)
            int r0 = r6.getMeasuredWidthAndState()
            r4 = 16777216(0x1000000, float:2.3509887E-38)
            r0 = r0 & r4
            if (r0 != 0) goto L41
        L3f:
            r8 = r2
            goto L56
        L41:
            int r8 = r8 + r9
            int r8 = r8 / 2
            int r0 = r7.width
            int r8 = getRootMeasureSpec(r8, r0)
            r5.performMeasure(r8, r3)
            int r8 = r6.getMeasuredWidthAndState()
            r8 = r8 & r4
            if (r8 != 0) goto L55
            goto L3f
        L55:
            r8 = r1
        L56:
            if (r8 != 0) goto L78
            int r8 = r7.width
            int r8 = getRootMeasureSpec(r9, r8)
            int r7 = r7.height
            int r7 = getRootMeasureSpec(r10, r7)
            r5.performMeasure(r8, r7)
            int r7 = r5.mWidth
            int r8 = r6.getMeasuredWidth()
            if (r7 != r8) goto L77
            int r7 = r5.mHeight
            int r6 = r6.getMeasuredHeight()
            if (r7 == r6) goto L78
        L77:
            r1 = r2
        L78:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: android.view.ViewRootImpl.measureHierarchy(android.view.View, android.view.WindowManager$LayoutParams, android.content.res.Resources, int, int):boolean");
    }

    void transformMatrixToGlobal(Matrix matrix) {
        matrix.preTranslate(this.mAttachInfo.mWindowLeft, this.mAttachInfo.mWindowTop);
    }

    void transformMatrixToLocal(Matrix matrix) {
        matrix.postTranslate(-this.mAttachInfo.mWindowLeft, -this.mAttachInfo.mWindowTop);
    }

    WindowInsets getWindowInsets(boolean z) {
        if (this.mLastWindowInsets == null || z) {
            this.mLastWindowInsets = this.mInsetsController.calculateInsets(this.mContext.getResources().getConfiguration().isScreenRound(), this.mAttachInfo.mAlwaysConsumeSystemBars, this.mPendingDisplayCutout.get(), this.mWindowAttributes.softInputMode, this.mWindowAttributes.flags, this.mWindowAttributes.systemUiVisibility | this.mWindowAttributes.subtreeSystemUiVisibility);
            this.mAttachInfo.mVisibleInsets.set(this.mInsetsController.calculateVisibleInsets(this.mWindowAttributes.softInputMode));
            this.mAttachInfo.mContentInsets.set(this.mLastWindowInsets.getSystemWindowInsets().toRect());
            this.mAttachInfo.mStableInsets.set(this.mLastWindowInsets.getStableInsets().toRect());
        }
        return this.mLastWindowInsets;
    }

    public void dispatchApplyInsets(View view) {
        Trace.traceBegin(8L, "dispatchApplyInsets");
        this.mApplyInsetsRequested = false;
        WindowInsets windowInsets = getWindowInsets(true);
        if (!shouldDispatchCutout()) {
            windowInsets = windowInsets.consumeDisplayCutout();
        }
        view.dispatchApplyWindowInsets(windowInsets);
        this.mAttachInfo.delayNotifyContentCaptureInsetsEvent(windowInsets.getInsets(WindowInsets.Type.all()));
        Trace.traceEnd(8L);
    }

    private boolean updateCaptionInsets() {
        View view = this.mView;
        if (!(view instanceof DecorView)) {
            return false;
        }
        int captionInsetsHeight = ((DecorView) view).getCaptionInsetsHeight();
        Rect rect = new Rect();
        if (captionInsetsHeight != 0) {
            rect.set(this.mWinFrame.left, this.mWinFrame.top, this.mWinFrame.right, this.mWinFrame.top + captionInsetsHeight);
        }
        if (this.mAttachInfo.mCaptionInsets.equals(rect)) {
            return false;
        }
        this.mAttachInfo.mCaptionInsets.set(rect);
        return true;
    }

    private boolean shouldDispatchCutout() {
        return this.mWindowAttributes.layoutInDisplayCutoutMode == 3 || this.mWindowAttributes.layoutInDisplayCutoutMode == 1;
    }

    public InsetsController getInsetsController() {
        return this.mInsetsController;
    }

    private static boolean shouldUseDisplaySize(WindowManager.LayoutParams layoutParams) {
        return layoutParams.type == 2041 || layoutParams.type == 2011 || layoutParams.type == 2020;
    }

    int dipToPx(int i) {
        return (int) ((this.mContext.getResources().getDisplayMetrics().density * i) + 0.5f);
    }

    /* JADX WARN: Removed duplicated region for block: B:101:0x01ce  */
    /* JADX WARN: Removed duplicated region for block: B:104:0x01d5  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x01e4  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x01ee  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x0224  */
    /* JADX WARN: Removed duplicated region for block: B:127:0x0244  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x024a  */
    /* JADX WARN: Removed duplicated region for block: B:149:0x028a  */
    /* JADX WARN: Removed duplicated region for block: B:155:0x0295  */
    /* JADX WARN: Removed duplicated region for block: B:162:0x02ad  */
    /* JADX WARN: Removed duplicated region for block: B:165:0x02b6  */
    /* JADX WARN: Removed duplicated region for block: B:166:0x02b9  */
    /* JADX WARN: Removed duplicated region for block: B:169:0x02c1  */
    /* JADX WARN: Removed duplicated region for block: B:171:0x02c6  */
    /* JADX WARN: Removed duplicated region for block: B:187:0x02ff  */
    /* JADX WARN: Removed duplicated region for block: B:189:0x0303  */
    /* JADX WARN: Removed duplicated region for block: B:196:0x0310  */
    /* JADX WARN: Removed duplicated region for block: B:199:0x0315  */
    /* JADX WARN: Removed duplicated region for block: B:218:0x0370  */
    /* JADX WARN: Removed duplicated region for block: B:222:0x0382 A[Catch: RemoteException -> 0x0592, TryCatch #16 {RemoteException -> 0x0592, blocks: (B:220:0x0378, B:222:0x0382, B:226:0x038b), top: B:637:0x0378 }] */
    /* JADX WARN: Removed duplicated region for block: B:228:0x0391  */
    /* JADX WARN: Removed duplicated region for block: B:232:0x03a2  */
    /* JADX WARN: Removed duplicated region for block: B:233:0x03a4  */
    /* JADX WARN: Removed duplicated region for block: B:236:0x03ad  */
    /* JADX WARN: Removed duplicated region for block: B:237:0x03af  */
    /* JADX WARN: Removed duplicated region for block: B:240:0x03ba  */
    /* JADX WARN: Removed duplicated region for block: B:244:0x03c7  */
    /* JADX WARN: Removed duplicated region for block: B:251:0x03db  */
    /* JADX WARN: Removed duplicated region for block: B:257:0x03f0  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0041  */
    /* JADX WARN: Removed duplicated region for block: B:341:0x04fc  */
    /* JADX WARN: Removed duplicated region for block: B:342:0x04fe  */
    /* JADX WARN: Removed duplicated region for block: B:345:0x0503  */
    /* JADX WARN: Removed duplicated region for block: B:346:0x0505  */
    /* JADX WARN: Removed duplicated region for block: B:351:0x050d  */
    /* JADX WARN: Removed duplicated region for block: B:354:0x0512  */
    /* JADX WARN: Removed duplicated region for block: B:370:0x0569  */
    /* JADX WARN: Removed duplicated region for block: B:373:0x056f  */
    /* JADX WARN: Removed duplicated region for block: B:395:0x05bf  */
    /* JADX WARN: Removed duplicated region for block: B:397:0x05c7  */
    /* JADX WARN: Removed duplicated region for block: B:400:0x05d7  */
    /* JADX WARN: Removed duplicated region for block: B:445:0x06a8  */
    /* JADX WARN: Removed duplicated region for block: B:447:0x06ac  */
    /* JADX WARN: Removed duplicated region for block: B:448:0x06ae  */
    /* JADX WARN: Removed duplicated region for block: B:457:0x06c9  */
    /* JADX WARN: Removed duplicated region for block: B:459:0x06ed  */
    /* JADX WARN: Removed duplicated region for block: B:460:0x06fc  */
    /* JADX WARN: Removed duplicated region for block: B:463:0x0703  */
    /* JADX WARN: Removed duplicated region for block: B:465:0x0713  */
    /* JADX WARN: Removed duplicated region for block: B:469:0x071e A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:472:0x0724  */
    /* JADX WARN: Removed duplicated region for block: B:474:0x0729  */
    /* JADX WARN: Removed duplicated region for block: B:479:0x0733  */
    /* JADX WARN: Removed duplicated region for block: B:481:0x0736  */
    /* JADX WARN: Removed duplicated region for block: B:485:0x073f  */
    /* JADX WARN: Removed duplicated region for block: B:487:0x0742  */
    /* JADX WARN: Removed duplicated region for block: B:497:0x07a0  */
    /* JADX WARN: Removed duplicated region for block: B:499:0x07a5  */
    /* JADX WARN: Removed duplicated region for block: B:501:0x07b3  */
    /* JADX WARN: Removed duplicated region for block: B:513:0x0814  */
    /* JADX WARN: Removed duplicated region for block: B:529:0x0849  */
    /* JADX WARN: Removed duplicated region for block: B:531:0x084d A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:536:0x0858 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:538:0x085c  */
    /* JADX WARN: Removed duplicated region for block: B:540:0x085f  */
    /* JADX WARN: Removed duplicated region for block: B:543:0x0865  */
    /* JADX WARN: Removed duplicated region for block: B:545:0x0868  */
    /* JADX WARN: Removed duplicated region for block: B:546:0x086c  */
    /* JADX WARN: Removed duplicated region for block: B:551:0x0877 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:554:0x087c  */
    /* JADX WARN: Removed duplicated region for block: B:556:0x0880  */
    /* JADX WARN: Removed duplicated region for block: B:561:0x088b  */
    /* JADX WARN: Removed duplicated region for block: B:564:0x08a8  */
    /* JADX WARN: Removed duplicated region for block: B:567:0x08af  */
    /* JADX WARN: Removed duplicated region for block: B:568:0x08b9  */
    /* JADX WARN: Removed duplicated region for block: B:571:0x08c4 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:574:0x08c9  */
    /* JADX WARN: Removed duplicated region for block: B:576:0x08cc  */
    /* JADX WARN: Removed duplicated region for block: B:586:0x08f6  */
    /* JADX WARN: Removed duplicated region for block: B:599:0x0928  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x0104  */
    /* JADX WARN: Removed duplicated region for block: B:623:0x0329 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:645:0x03cd A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:73:0x0127  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x0144  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x0148  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x01c2  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void performTraversals() {
        /*
            Method dump skipped, instruction units count: 2351
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.view.ViewRootImpl.performTraversals():void");
    }

    private void notifyContentCatpureEvents() {
        Trace.traceBegin(8L, "notifyContentCaptureEvents");
        try {
            MainContentCaptureSession mainContentCaptureSession = this.mAttachInfo.mContentCaptureManager.getMainContentCaptureSession();
            for (int i = 0; i < this.mAttachInfo.mContentCaptureEvents.size(); i++) {
                int iKeyAt = this.mAttachInfo.mContentCaptureEvents.keyAt(i);
                mainContentCaptureSession.notifyViewTreeEvent(iKeyAt, true);
                ArrayList<Object> arrayListValueAt = this.mAttachInfo.mContentCaptureEvents.valueAt(i);
                for (int i2 = 0; i2 < arrayListValueAt.size(); i2++) {
                    Object obj = arrayListValueAt.get(i2);
                    if (obj instanceof AutofillId) {
                        mainContentCaptureSession.notifyViewDisappeared(iKeyAt, (AutofillId) obj);
                    } else if (obj instanceof View) {
                        View view = (View) obj;
                        ContentCaptureSession contentCaptureSession = view.getContentCaptureSession();
                        if (contentCaptureSession == null) {
                            Log.w(this.mTag, "no content capture session on view: " + view);
                        } else {
                            int id = contentCaptureSession.getId();
                            if (id != iKeyAt) {
                                Log.w(this.mTag, "content capture session mismatch for view (" + view + "): was " + iKeyAt + " before, it's " + id + " now");
                            } else {
                                ViewStructure viewStructureNewViewStructure = contentCaptureSession.newViewStructure(view);
                                view.onProvideContentCaptureStructure(viewStructureNewViewStructure, 0);
                                contentCaptureSession.notifyViewAppeared(viewStructureNewViewStructure);
                            }
                        }
                    } else if (obj instanceof Insets) {
                        mainContentCaptureSession.notifyViewInsetsChanged(iKeyAt, (Insets) obj);
                    } else {
                        Log.w(this.mTag, "invalid content capture event: " + obj);
                    }
                }
                mainContentCaptureSession.notifyViewTreeEvent(iKeyAt, false);
            }
            this.mAttachInfo.mContentCaptureEvents = null;
        } finally {
            Trace.traceEnd(8L);
        }
    }

    private void notifyHolderSurfaceDestroyed() {
        this.mSurfaceHolder.ungetCallbacks();
        SurfaceHolder.Callback[] callbacks = this.mSurfaceHolder.getCallbacks();
        if (callbacks != null) {
            for (SurfaceHolder.Callback callback : callbacks) {
                callback.surfaceDestroyed(this.mSurfaceHolder);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void maybeHandleWindowMove(Rect rect) {
        boolean z = (this.mAttachInfo.mWindowLeft == rect.left && this.mAttachInfo.mWindowTop == rect.top) ? false : true;
        if (z) {
            CompatibilityInfo.Translator translator = this.mTranslator;
            if (translator != null) {
                translator.translateRectInScreenToAppWinFrame(rect);
            }
            this.mAttachInfo.mWindowLeft = rect.left;
            this.mAttachInfo.mWindowTop = rect.top;
        }
        if (z || this.mAttachInfo.mNeedsUpdateLightCenter) {
            if (this.mAttachInfo.mThreadedRenderer != null) {
                this.mAttachInfo.mThreadedRenderer.setLightCenter(this.mAttachInfo);
            }
            this.mAttachInfo.mNeedsUpdateLightCenter = false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleWindowFocusChanged() {
        synchronized (this) {
            if (this.mWindowFocusChanged) {
                this.mWindowFocusChanged = false;
                boolean z = this.mUpcomingWindowFocus;
                boolean z2 = this.mUpcomingInTouchMode;
                if (sNewInsetsMode != 0) {
                    if (z) {
                        this.mInsetsController.onWindowFocusGained();
                    } else {
                        this.mInsetsController.onWindowFocusLost();
                    }
                }
                if (this.mAdded) {
                    profileRendering(z);
                    if (z) {
                        ensureTouchModeLocally(z2);
                        if (this.mAttachInfo.mThreadedRenderer != null && this.mSurface.isValid()) {
                            this.mFullRedrawNeeded = true;
                            try {
                                WindowManager.LayoutParams layoutParams = this.mWindowAttributes;
                                this.mAttachInfo.mThreadedRenderer.initializeIfNeeded(this.mWidth, this.mHeight, this.mAttachInfo, this.mSurface, layoutParams != null ? layoutParams.surfaceInsets : null);
                            } catch (Surface.OutOfResourcesException e) {
                                Log.e(this.mTag, "OutOfResourcesException locking surface", e);
                                try {
                                    if (!this.mWindowSession.outOfMemory(this.mWindow)) {
                                        Slog.w(this.mTag, "No processes killed for memory; killing self");
                                        Process.killProcess(Process.myPid());
                                    }
                                } catch (RemoteException unused) {
                                }
                                ViewRootHandler viewRootHandler = this.mHandler;
                                viewRootHandler.sendMessageDelayed(viewRootHandler.obtainMessage(6), 500L);
                                return;
                            }
                        }
                    }
                    this.mAttachInfo.mHasWindowFocus = z;
                    this.mImeFocusController.updateImeFocusable(this.mWindowAttributes, true);
                    this.mImeFocusController.onPreWindowFocus(z, this.mWindowAttributes);
                    if (this.mView != null) {
                        this.mAttachInfo.mKeyDispatchState.reset();
                        this.mView.dispatchWindowFocusChanged(z);
                        this.mAttachInfo.mTreeObserver.dispatchOnWindowFocusChange(z);
                        if (this.mAttachInfo.mTooltipHost != null) {
                            this.mAttachInfo.mTooltipHost.hideTooltip();
                        }
                    }
                    ImeFocusController imeFocusController = this.mImeFocusController;
                    View view = this.mView;
                    imeFocusController.onPostWindowFocus(view != null ? view.findFocus() : null, z, this.mWindowAttributes);
                    if (z) {
                        this.mWindowAttributes.softInputMode &= TrafficStats.TAG_NETWORK_STACK_RANGE_END;
                        ((WindowManager.LayoutParams) this.mView.getLayoutParams()).softInputMode &= TrafficStats.TAG_NETWORK_STACK_RANGE_END;
                        fireAccessibilityFocusEventIfHasFocusedNode();
                    } else if (this.mPointerCapture) {
                        handlePointerCaptureChanged(false);
                    }
                }
                this.mFirstInputStage.onWindowFocusChanged(z);
                if (z) {
                    handleContentCaptureFlush();
                }
            }
        }
    }

    private void fireAccessibilityFocusEventIfHasFocusedNode() {
        View viewFindFocus;
        if (AccessibilityManager.getInstance(this.mContext).isEnabled() && (viewFindFocus = this.mView.findFocus()) != null) {
            AccessibilityNodeProvider accessibilityNodeProvider = viewFindFocus.getAccessibilityNodeProvider();
            if (accessibilityNodeProvider == null) {
                viewFindFocus.sendAccessibilityEvent(8);
                return;
            }
            AccessibilityNodeInfo accessibilityNodeInfoFindFocusedVirtualNode = findFocusedVirtualNode(accessibilityNodeProvider);
            if (accessibilityNodeInfoFindFocusedVirtualNode != null) {
                int virtualDescendantId = AccessibilityNodeInfo.getVirtualDescendantId(accessibilityNodeInfoFindFocusedVirtualNode.getSourceNodeId());
                AccessibilityEvent accessibilityEventObtain = AccessibilityEvent.obtain(8);
                accessibilityEventObtain.setSource(viewFindFocus, virtualDescendantId);
                accessibilityEventObtain.setPackageName(accessibilityNodeInfoFindFocusedVirtualNode.getPackageName());
                accessibilityEventObtain.setChecked(accessibilityNodeInfoFindFocusedVirtualNode.isChecked());
                accessibilityEventObtain.setContentDescription(accessibilityNodeInfoFindFocusedVirtualNode.getContentDescription());
                accessibilityEventObtain.setPassword(accessibilityNodeInfoFindFocusedVirtualNode.isPassword());
                accessibilityEventObtain.getText().add(accessibilityNodeInfoFindFocusedVirtualNode.getText());
                accessibilityEventObtain.setEnabled(accessibilityNodeInfoFindFocusedVirtualNode.isEnabled());
                viewFindFocus.getParent().requestSendAccessibilityEvent(viewFindFocus, accessibilityEventObtain);
                accessibilityNodeInfoFindFocusedVirtualNode.recycle();
            }
        }
    }

    private AccessibilityNodeInfo findFocusedVirtualNode(AccessibilityNodeProvider accessibilityNodeProvider) {
        AccessibilityNodeInfo accessibilityNodeInfoFindFocus = accessibilityNodeProvider.findFocus(1);
        if (accessibilityNodeInfoFindFocus != null) {
            return accessibilityNodeInfoFindFocus;
        }
        if (!this.mContext.isAutofillCompatibilityEnabled()) {
            return null;
        }
        AccessibilityNodeInfo accessibilityNodeInfoCreateAccessibilityNodeInfo = accessibilityNodeProvider.createAccessibilityNodeInfo(-1);
        if (accessibilityNodeInfoCreateAccessibilityNodeInfo.isFocused()) {
            return accessibilityNodeInfoCreateAccessibilityNodeInfo;
        }
        LinkedList linkedList = new LinkedList();
        linkedList.offer(accessibilityNodeInfoCreateAccessibilityNodeInfo);
        while (!linkedList.isEmpty()) {
            AccessibilityNodeInfo accessibilityNodeInfo = (AccessibilityNodeInfo) linkedList.poll();
            LongArray childNodeIds = accessibilityNodeInfo.getChildNodeIds();
            if (childNodeIds != null && childNodeIds.size() > 0) {
                int size = childNodeIds.size();
                for (int i = 0; i < size; i++) {
                    AccessibilityNodeInfo accessibilityNodeInfoCreateAccessibilityNodeInfo2 = accessibilityNodeProvider.createAccessibilityNodeInfo(AccessibilityNodeInfo.getVirtualDescendantId(childNodeIds.get(i)));
                    if (accessibilityNodeInfoCreateAccessibilityNodeInfo2 != null) {
                        if (accessibilityNodeInfoCreateAccessibilityNodeInfo2.isFocused()) {
                            return accessibilityNodeInfoCreateAccessibilityNodeInfo2;
                        }
                        linkedList.offer(accessibilityNodeInfoCreateAccessibilityNodeInfo2);
                    }
                }
                accessibilityNodeInfo.recycle();
            }
        }
        return null;
    }

    private void handleOutOfResourcesException(Surface.OutOfResourcesException outOfResourcesException) {
        Log.e(this.mTag, "OutOfResourcesException initializing HW surface", outOfResourcesException);
        try {
            if (!this.mWindowSession.outOfMemory(this.mWindow) && Process.myUid() != 1000) {
                Slog.w(this.mTag, "No processes killed for memory; killing self");
                Process.killProcess(Process.myPid());
            }
        } catch (RemoteException unused) {
        }
        this.mLayoutRequested = true;
    }

    private void performMeasure(int i, int i2) {
        if (this.mView == null) {
            return;
        }
        Trace.traceBegin(8L, "measure");
        try {
            this.mView.measure(i, i2);
        } finally {
            Trace.traceEnd(8L);
        }
    }

    boolean isInLayout() {
        return this.mInLayout;
    }

    boolean requestLayoutDuringLayout(View view) {
        if (view.mParent == null || view.mAttachInfo == null) {
            return true;
        }
        if (!this.mLayoutRequesters.contains(view)) {
            this.mLayoutRequesters.add(view);
        }
        return !this.mHandlingLayoutInLayoutRequest;
    }

    private void performLayout(WindowManager.LayoutParams layoutParams, int i, int i2) {
        ArrayList<View> validLayoutRequesters;
        this.mScrollMayChange = true;
        this.mInLayout = true;
        View view = this.mView;
        if (view == null) {
            return;
        }
        Trace.traceBegin(8L, "layout");
        try {
            view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
            this.mInLayout = false;
            if (this.mLayoutRequesters.size() > 0 && (validLayoutRequesters = getValidLayoutRequesters(this.mLayoutRequesters, false)) != null) {
                this.mHandlingLayoutInLayoutRequest = true;
                int size = validLayoutRequesters.size();
                for (int i3 = 0; i3 < size; i3++) {
                    View view2 = validLayoutRequesters.get(i3);
                    Log.w("View", "requestLayout() improperly called by " + view2 + " during layout: running second layout pass");
                    view2.requestLayout();
                }
                measureHierarchy(view, layoutParams, this.mView.getContext().getResources(), i, i2);
                this.mInLayout = true;
                view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
                this.mHandlingLayoutInLayoutRequest = false;
                final ArrayList<View> validLayoutRequesters2 = getValidLayoutRequesters(this.mLayoutRequesters, true);
                if (validLayoutRequesters2 != null) {
                    getRunQueue().post(new Runnable() { // from class: android.view.ViewRootImpl.2
                        @Override // java.lang.Runnable
                        public void run() {
                            int size2 = validLayoutRequesters2.size();
                            for (int i4 = 0; i4 < size2; i4++) {
                                View view3 = (View) validLayoutRequesters2.get(i4);
                                Log.w("View", "requestLayout() improperly called by " + view3 + " during second layout pass: posting in next frame");
                                view3.requestLayout();
                            }
                        }
                    });
                }
            }
            Trace.traceEnd(8L);
            this.mInLayout = false;
        } catch (Throwable th) {
            Trace.traceEnd(8L);
            throw th;
        }
    }

    private ArrayList<View> getValidLayoutRequesters(ArrayList<View> arrayList, boolean z) {
        boolean z2;
        int size = arrayList.size();
        ArrayList<View> arrayList2 = null;
        for (int i = 0; i < size; i++) {
            View view = arrayList.get(i);
            if (view != null && view.mAttachInfo != null && view.mParent != null && (z || (view.mPrivateFlags & 4096) == 4096)) {
                View view2 = view;
                while (true) {
                    if (view2 == null) {
                        z2 = false;
                        break;
                    }
                    if ((view2.mViewFlags & 12) == 8) {
                        z2 = true;
                        break;
                    }
                    view2 = view2.mParent instanceof View ? (View) view2.mParent : null;
                }
                if (!z2) {
                    if (arrayList2 == null) {
                        arrayList2 = new ArrayList<>();
                    }
                    arrayList2.add(view);
                }
            }
        }
        if (!z) {
            for (int i2 = 0; i2 < size; i2++) {
                View view3 = arrayList.get(i2);
                while (view3 != null && (view3.mPrivateFlags & 4096) != 0) {
                    view3.mPrivateFlags &= -4097;
                    view3 = view3.mParent instanceof View ? (View) view3.mParent : null;
                }
            }
        }
        arrayList.clear();
        return arrayList2;
    }

    @Override // android.view.ViewParent
    public void requestTransparentRegion(View view) {
        checkThread();
        View view2 = this.mView;
        if (view2 != view) {
            return;
        }
        if ((view2.mPrivateFlags & 512) == 0) {
            this.mView.mPrivateFlags |= 512;
            this.mWindowAttributesChanged = true;
        }
        requestLayout();
    }

    private static int getRootMeasureSpec(int i, int i2) {
        if (i2 == -2) {
            return View.MeasureSpec.makeMeasureSpec(i, Integer.MIN_VALUE);
        }
        if (i2 == -1) {
            return View.MeasureSpec.makeMeasureSpec(i, 1073741824);
        }
        return View.MeasureSpec.makeMeasureSpec(i2, 1073741824);
    }

    @Override // android.view.ThreadedRenderer.DrawCallbacks
    public void onPreDraw(RecordingCanvas recordingCanvas) {
        if (this.mCurScrollY != 0 && this.mHardwareYOffset != 0 && this.mAttachInfo.mThreadedRenderer.isOpaque()) {
            recordingCanvas.drawColor(-16777216);
        }
        recordingCanvas.translate(-this.mHardwareXOffset, -this.mHardwareYOffset);
    }

    @Override // android.view.ThreadedRenderer.DrawCallbacks
    public void onPostDraw(RecordingCanvas recordingCanvas) {
        drawAccessibilityFocusedDrawableIfNeeded(recordingCanvas);
        if (this.mUseMTRenderer) {
            for (int size = this.mWindowCallbacks.size() - 1; size >= 0; size--) {
                this.mWindowCallbacks.get(size).onPostDraw(recordingCanvas);
            }
        }
    }

    void outputDisplayList(View view) {
        view.mRenderNode.output();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void profileRendering(boolean z) {
        if (this.mProfileRendering) {
            this.mRenderProfilingEnabled = z;
            Choreographer.FrameCallback frameCallback = this.mRenderProfiler;
            if (frameCallback != null) {
                this.mChoreographer.removeFrameCallback(frameCallback);
            }
            if (this.mRenderProfilingEnabled) {
                if (this.mRenderProfiler == null) {
                    this.mRenderProfiler = new Choreographer.FrameCallback() { // from class: android.view.ViewRootImpl.3
                        @Override // android.view.Choreographer.FrameCallback
                        public void doFrame(long j) {
                            ViewRootImpl.this.mDirty.set(0, 0, ViewRootImpl.this.mWidth, ViewRootImpl.this.mHeight);
                            ViewRootImpl.this.scheduleTraversals();
                            if (ViewRootImpl.this.mRenderProfilingEnabled) {
                                ViewRootImpl.this.mChoreographer.postFrameCallback(ViewRootImpl.this.mRenderProfiler);
                            }
                        }
                    };
                }
                this.mChoreographer.postFrameCallback(this.mRenderProfiler);
                return;
            }
            this.mRenderProfiler = null;
        }
    }

    private void trackFPS() {
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (this.mFpsStartTime < 0) {
            this.mFpsPrevTime = jCurrentTimeMillis;
            this.mFpsStartTime = jCurrentTimeMillis;
            this.mFpsNumFrames = 0;
            return;
        }
        this.mFpsNumFrames++;
        String hexString = Integer.toHexString(System.identityHashCode(this));
        long j = jCurrentTimeMillis - this.mFpsPrevTime;
        long j2 = jCurrentTimeMillis - this.mFpsStartTime;
        Log.v(this.mTag, "0x" + hexString + "\tFrame time:\t" + j);
        this.mFpsPrevTime = jCurrentTimeMillis;
        if (j2 > 1000) {
            String str = this.mTag;
            Log.v(str, "0x" + hexString + "\tFPS:\t" + ((this.mFpsNumFrames * 1000.0f) / j2));
            this.mFpsStartTime = jCurrentTimeMillis;
            this.mFpsNumFrames = 0;
        }
    }

    void drawPending() {
        this.mDrawsNeededToReport++;
    }

    void pendingDrawFinished() {
        int i = this.mDrawsNeededToReport;
        if (i == 0) {
            throw new RuntimeException("Unbalanced drawPending/pendingDrawFinished calls");
        }
        int i2 = i - 1;
        this.mDrawsNeededToReport = i2;
        if (i2 == 0) {
            reportDrawFinished();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void postDrawFinished() {
        this.mHandler.sendEmptyMessage(29);
    }

    private void reportDrawFinished() {
        try {
            this.mDrawsNeededToReport = 0;
            this.mWindowSession.finishDrawing(this.mWindow, this.mSurfaceChangedTransaction);
        } catch (RemoteException unused) {
        }
    }

    private void performDraw() {
        boolean z;
        if ((this.mAttachInfo.mDisplayState != 1 || this.mReportNextDraw) && this.mView != null) {
            boolean z2 = this.mFullRedrawNeeded || this.mReportNextDraw;
            this.mFullRedrawNeeded = false;
            this.mIsDrawing = true;
            Trace.traceBegin(8L, "draw");
            final boolean z3 = this.mReportNextDraw;
            if (this.mAttachInfo.mThreadedRenderer == null || !this.mAttachInfo.mThreadedRenderer.isEnabled()) {
                z = false;
            } else {
                final ArrayList<Runnable> arrayListCaptureFrameCommitCallbacks = this.mAttachInfo.mTreeObserver.captureFrameCommitCallbacks();
                boolean z4 = this.mNextDrawUseBLASTSyncTransaction || (arrayListCaptureFrameCommitCallbacks != null && arrayListCaptureFrameCommitCallbacks.size() > 0) || this.mReportNextDraw;
                z = this.mReportNextDraw;
                if (z4) {
                    final Handler handler = this.mAttachInfo.mHandler;
                    this.mAttachInfo.mThreadedRenderer.setFrameCompleteCallback(new HardwareRenderer.FrameCompleteCallback() { // from class: android.view.-$$Lambda$ViewRootImpl$vBfxngTfPtkwcFoa96FB0CWn5ZI
                        @Override // android.graphics.HardwareRenderer.FrameCompleteCallback
                        public final void onFrameComplete(long j) {
                            this.f$0.lambda$performDraw$2$ViewRootImpl(handler, z3, arrayListCaptureFrameCommitCallbacks, j);
                        }
                    });
                }
            }
            try {
                if (this.mNextDrawUseBLASTSyncTransaction) {
                    if (this.mAttachInfo.mThreadedRenderer != null) {
                        this.mAttachInfo.mThreadedRenderer.pause();
                    }
                    this.mNextReportConsumeBLAST = true;
                    this.mNextDrawUseBLASTSyncTransaction = false;
                    if (this.mBlastBufferQueue != null) {
                        this.mBlastBufferQueue.setNextTransaction(this.mRtBLASTSyncTransaction);
                    }
                }
                boolean zDraw = draw(z2);
                if (z && !zDraw) {
                    this.mAttachInfo.mThreadedRenderer.setFrameCompleteCallback(null);
                    finishBLASTSync(true);
                    z = false;
                }
                this.mIsDrawing = false;
                Trace.traceEnd(8L);
                if (this.mAttachInfo.mPendingAnimatingRenderNodes != null) {
                    int size = this.mAttachInfo.mPendingAnimatingRenderNodes.size();
                    for (int i = 0; i < size; i++) {
                        this.mAttachInfo.mPendingAnimatingRenderNodes.get(i).endAllAnimators();
                    }
                    this.mAttachInfo.mPendingAnimatingRenderNodes.clear();
                }
                if (this.mReportNextDraw) {
                    this.mReportNextDraw = false;
                    CountDownLatch countDownLatch = this.mWindowDrawCountDown;
                    if (countDownLatch != null) {
                        try {
                            countDownLatch.await();
                        } catch (InterruptedException unused) {
                            Log.e(this.mTag, "Window redraw count down interrupted!");
                        }
                        this.mWindowDrawCountDown = null;
                    }
                    if (this.mAttachInfo.mThreadedRenderer != null) {
                        this.mAttachInfo.mThreadedRenderer.setStopped(this.mStopped);
                    }
                    if (this.mSurfaceHolder != null && this.mSurface.isValid()) {
                        new SurfaceCallbackHelper(new Runnable() { // from class: android.view.-$$Lambda$ViewRootImpl$dznxCZGM2R1fsBljsJKomLjBRoM
                            @Override // java.lang.Runnable
                            public final void run() {
                                this.f$0.postDrawFinished();
                            }
                        }).dispatchSurfaceRedrawNeededAsync(this.mSurfaceHolder, this.mSurfaceHolder.getCallbacks());
                    } else if (!z) {
                        if (this.mAttachInfo.mThreadedRenderer != null) {
                            this.mAttachInfo.mThreadedRenderer.fence();
                        }
                        pendingDrawFinished();
                    }
                }
                if (this.mPerformContentCapture) {
                    performContentCaptureInitialReport();
                }
            } catch (Throwable th) {
                this.mIsDrawing = false;
                Trace.traceEnd(8L);
                throw th;
            }
        }
    }

    public /* synthetic */ void lambda$performDraw$2$ViewRootImpl(Handler handler, final boolean z, final ArrayList arrayList, long j) {
        finishBLASTSync(!this.mSendNextFrameToWm);
        handler.postAtFrontOfQueue(new Runnable() { // from class: android.view.-$$Lambda$ViewRootImpl$DJd0VUYJgsebcnSohO6h8zc_ONI
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$performDraw$1$ViewRootImpl(z, arrayList);
            }
        });
    }

    public /* synthetic */ void lambda$performDraw$1$ViewRootImpl(boolean z, ArrayList arrayList) {
        if (z) {
            pendingDrawFinished();
        }
        if (arrayList != null) {
            for (int i = 0; i < arrayList.size(); i++) {
                ((Runnable) arrayList.get(i)).run();
            }
        }
    }

    private boolean isContentCaptureEnabled() {
        int i = this.mContentCaptureEnabled;
        boolean z = true;
        if (i == 0) {
            boolean zIsContentCaptureReallyEnabled = isContentCaptureReallyEnabled();
            this.mContentCaptureEnabled = zIsContentCaptureReallyEnabled ? 1 : 2;
            return zIsContentCaptureReallyEnabled;
        }
        if (i != 1) {
            z = false;
            if (i != 2) {
                Log.w(TAG, "isContentCaptureEnabled(): invalid state " + this.mContentCaptureEnabled);
            }
        }
        return z;
    }

    private boolean isContentCaptureReallyEnabled() {
        ContentCaptureManager contentCaptureManager;
        return (this.mContext.getContentCaptureOptions() == null || (contentCaptureManager = this.mAttachInfo.getContentCaptureManager(this.mContext)) == null || !contentCaptureManager.isContentCaptureEnabled()) ? false : true;
    }

    private void performContentCaptureInitialReport() {
        this.mPerformContentCapture = false;
        View view = this.mView;
        if (Trace.isTagEnabled(8L)) {
            Trace.traceBegin(8L, "dispatchContentCapture() for " + getClass().getSimpleName());
        }
        try {
            if (isContentCaptureEnabled()) {
                view.dispatchInitialProvideContentCaptureStructure();
            }
        } finally {
            Trace.traceEnd(8L);
        }
    }

    private void handleContentCaptureFlush() {
        if (Trace.isTagEnabled(8L)) {
            Trace.traceBegin(8L, "flushContentCapture for " + getClass().getSimpleName());
        }
        try {
            if (isContentCaptureEnabled()) {
                ContentCaptureManager contentCaptureManager = this.mAttachInfo.mContentCaptureManager;
                if (contentCaptureManager == null) {
                    Log.w(TAG, "No ContentCapture on AttachInfo");
                } else {
                    contentCaptureManager.flush(2);
                }
            }
        } finally {
            Trace.traceEnd(8L);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:59:0x00e8  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean draw(boolean r20) {
        /*
            Method dump skipped, instruction units count: 433
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.view.ViewRootImpl.draw(boolean):boolean");
    }

    private boolean drawSoftware(Surface surface, View.AttachInfo attachInfo, int i, int i2, boolean z, Rect rect, Rect rect2) {
        int i3;
        int i4;
        if (rect2 != null) {
            i3 = rect2.left + i;
            i4 = rect2.top + i2;
        } else {
            i3 = i;
            i4 = i2;
        }
        try {
            rect.offset(-i3, -i4);
            int i5 = rect.left;
            int i6 = rect.top;
            int i7 = rect.right;
            int i8 = rect.bottom;
            Canvas canvasLockCanvas = this.mSurface.lockCanvas(rect);
            canvasLockCanvas.setDensity(this.mDensity);
            try {
                try {
                    if (!canvasLockCanvas.isOpaque() || i2 != 0 || i != 0) {
                        canvasLockCanvas.drawColor(0, PorterDuff.Mode.CLEAR);
                    }
                    rect.setEmpty();
                    this.mIsAnimating = false;
                    this.mView.mPrivateFlags |= 32;
                    canvasLockCanvas.translate(-i, -i2);
                    if (this.mTranslator != null) {
                        this.mTranslator.translateCanvas(canvasLockCanvas);
                    }
                    canvasLockCanvas.setScreenDensity(z ? this.mNoncompatDensity : 0);
                    this.mView.draw(canvasLockCanvas);
                    drawAccessibilityFocusedDrawableIfNeeded(canvasLockCanvas);
                    return true;
                } finally {
                    surface.unlockCanvasAndPost(canvasLockCanvas);
                }
            } catch (IllegalArgumentException e) {
                Log.e(this.mTag, "Could not unlock surface", e);
                this.mLayoutRequested = true;
                return false;
            }
        } catch (Surface.OutOfResourcesException e2) {
            handleOutOfResourcesException(e2);
            return false;
        } catch (IllegalArgumentException e3) {
            Log.e(this.mTag, "Could not lock surface", e3);
            this.mLayoutRequested = true;
            return false;
        } finally {
            rect.offset(i3, i4);
        }
    }

    private void drawAccessibilityFocusedDrawableIfNeeded(Canvas canvas) {
        Rect rect = this.mAttachInfo.mTmpInvalRect;
        if (getAccessibilityFocusedRect(rect)) {
            Drawable accessibilityFocusedDrawable = getAccessibilityFocusedDrawable();
            if (accessibilityFocusedDrawable != null) {
                accessibilityFocusedDrawable.setBounds(rect);
                accessibilityFocusedDrawable.draw(canvas);
                return;
            }
            return;
        }
        if (this.mAttachInfo.mAccessibilityFocusDrawable != null) {
            this.mAttachInfo.mAccessibilityFocusDrawable.setBounds(0, 0, 0, 0);
        }
    }

    private boolean getAccessibilityFocusedRect(Rect rect) {
        View view;
        AccessibilityManager accessibilityManager = AccessibilityManager.getInstance(this.mView.mContext);
        if (accessibilityManager.isEnabled() && accessibilityManager.isTouchExplorationEnabled() && (view = this.mAccessibilityFocusedHost) != null && view.mAttachInfo != null) {
            if (view.getAccessibilityNodeProvider() == null) {
                view.getBoundsOnScreen(rect, true);
            } else {
                AccessibilityNodeInfo accessibilityNodeInfo = this.mAccessibilityFocusedVirtualView;
                if (accessibilityNodeInfo != null) {
                    accessibilityNodeInfo.getBoundsInScreen(rect);
                }
            }
            View.AttachInfo attachInfo = this.mAttachInfo;
            rect.offset(0, attachInfo.mViewRootImpl.mScrollY);
            rect.offset(-attachInfo.mWindowLeft, -attachInfo.mWindowTop);
            if (!rect.intersect(0, 0, attachInfo.mViewRootImpl.mWidth, attachInfo.mViewRootImpl.mHeight)) {
                rect.setEmpty();
            }
            return !rect.isEmpty();
        }
        return false;
    }

    private Drawable getAccessibilityFocusedDrawable() {
        if (this.mAttachInfo.mAccessibilityFocusDrawable == null) {
            TypedValue typedValue = new TypedValue();
            if (this.mView.mContext.getTheme().resolveAttribute(R.attr.accessibilityFocusedDrawable, typedValue, true)) {
                this.mAttachInfo.mAccessibilityFocusDrawable = this.mView.mContext.getDrawable(typedValue.resourceId);
            }
        }
        return this.mAttachInfo.mAccessibilityFocusDrawable;
    }

    void updateSystemGestureExclusionRectsForView(View view) {
        this.mGestureExclusionTracker.updateRectsForView(view);
        this.mHandler.sendEmptyMessage(32);
    }

    void systemGestureExclusionChanged() {
        List<Rect> listComputeChangedRects = this.mGestureExclusionTracker.computeChangedRects();
        if (listComputeChangedRects == null || this.mView == null) {
            return;
        }
        try {
            this.mWindowSession.reportSystemGestureExclusionChanged(this.mWindow, listComputeChangedRects);
            this.mAttachInfo.mTreeObserver.dispatchOnSystemGestureExclusionRectsChanged(listComputeChangedRects);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    void updateLocationInParentDisplay(int i, int i2) {
        View.AttachInfo attachInfo = this.mAttachInfo;
        if (attachInfo == null || attachInfo.mLocationInParentDisplay.equals(i, i2)) {
            return;
        }
        this.mAttachInfo.mLocationInParentDisplay.set(i, i2);
    }

    public void setRootSystemGestureExclusionRects(List<Rect> list) {
        this.mGestureExclusionTracker.setRootSystemGestureExclusionRects(list);
        this.mHandler.sendEmptyMessage(32);
    }

    public List<Rect> getRootSystemGestureExclusionRects() {
        return this.mGestureExclusionTracker.getRootSystemGestureExclusionRects();
    }

    public void requestInvalidateRootRenderNode() {
        this.mInvalidateRootRequested = true;
    }

    /* JADX WARN: Removed duplicated region for block: B:47:0x00ca  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    boolean scrollToRectOrFocus(android.graphics.Rect r7, boolean r8) {
        /*
            Method dump skipped, instruction units count: 246
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.view.ViewRootImpl.scrollToRectOrFocus(android.graphics.Rect, boolean):boolean");
    }

    public View getAccessibilityFocusedHost() {
        return this.mAccessibilityFocusedHost;
    }

    public AccessibilityNodeInfo getAccessibilityFocusedVirtualView() {
        return this.mAccessibilityFocusedVirtualView;
    }

    void setAccessibilityFocus(View view, AccessibilityNodeInfo accessibilityNodeInfo) {
        AccessibilityNodeInfo accessibilityNodeInfo2 = this.mAccessibilityFocusedVirtualView;
        if (accessibilityNodeInfo2 != null) {
            View view2 = this.mAccessibilityFocusedHost;
            this.mAccessibilityFocusedHost = null;
            this.mAccessibilityFocusedVirtualView = null;
            view2.clearAccessibilityFocusNoCallbacks(64);
            AccessibilityNodeProvider accessibilityNodeProvider = view2.getAccessibilityNodeProvider();
            if (accessibilityNodeProvider != null) {
                accessibilityNodeInfo2.getBoundsInParent(this.mTempRect);
                view2.invalidate(this.mTempRect);
                accessibilityNodeProvider.performAction(AccessibilityNodeInfo.getVirtualDescendantId(accessibilityNodeInfo2.getSourceNodeId()), 128, null);
            }
            accessibilityNodeInfo2.recycle();
        }
        View view3 = this.mAccessibilityFocusedHost;
        if (view3 != null && view3 != view) {
            view3.clearAccessibilityFocusNoCallbacks(64);
        }
        this.mAccessibilityFocusedHost = view;
        this.mAccessibilityFocusedVirtualView = accessibilityNodeInfo;
        if (this.mAttachInfo.mThreadedRenderer != null) {
            this.mAttachInfo.mThreadedRenderer.invalidateRoot();
        }
    }

    boolean hasPointerCapture() {
        return this.mPointerCapture;
    }

    void requestPointerCapture(boolean z) {
        if (this.mPointerCapture == z) {
            return;
        }
        InputManager.getInstance().requestPointerCapture(this.mAttachInfo.mWindowToken, z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handlePointerCaptureChanged(boolean z) {
        if (this.mPointerCapture == z) {
            return;
        }
        this.mPointerCapture = z;
        View view = this.mView;
        if (view != null) {
            view.dispatchPointerCaptureChanged(z);
        }
    }

    private boolean hasColorModeChanged(int i) {
        if (this.mAttachInfo.mThreadedRenderer == null) {
            return false;
        }
        boolean z = i == 1;
        if (this.mAttachInfo.mThreadedRenderer.isWideGamut() == z) {
            return false;
        }
        return !z || this.mContext.getResources().getConfiguration().isScreenWideColorGamut();
    }

    @Override // android.view.ViewParent
    public void requestChildFocus(View view, View view2) {
        checkThread();
        scheduleTraversals();
    }

    @Override // android.view.ViewParent
    public void clearChildFocus(View view) {
        checkThread();
        scheduleTraversals();
    }

    @Override // android.view.ViewParent
    public void focusableViewAvailable(View view) {
        checkThread();
        View view2 = this.mView;
        if (view2 != null) {
            if (!view2.hasFocus()) {
                if (sAlwaysAssignFocus || !this.mAttachInfo.mInTouchMode) {
                    view.requestFocus();
                    return;
                }
                return;
            }
            View viewFindFocus = this.mView.findFocus();
            if ((viewFindFocus instanceof ViewGroup) && ((ViewGroup) viewFindFocus).getDescendantFocusability() == 262144 && isViewDescendantOf(view, viewFindFocus)) {
                view.requestFocus();
            }
        }
    }

    @Override // android.view.ViewParent
    public void recomputeViewAttributes(View view) {
        checkThread();
        if (this.mView == view) {
            this.mAttachInfo.mRecomputeGlobalAttributes = true;
            if (this.mWillDrawSoon) {
                return;
            }
            scheduleTraversals();
        }
    }

    void dispatchDetachedFromWindow() {
        InputQueue inputQueue;
        this.mInsetsController.onWindowFocusLost();
        this.mFirstInputStage.onDetachedFromWindow();
        View view = this.mView;
        if (view != null && view.mAttachInfo != null) {
            this.mAttachInfo.mTreeObserver.dispatchOnWindowAttachedChange(false);
            this.mView.dispatchDetachedFromWindow();
        }
        this.mAccessibilityInteractionConnectionManager.ensureNoConnection();
        this.mAccessibilityManager.removeAccessibilityStateChangeListener(this.mAccessibilityInteractionConnectionManager);
        this.mAccessibilityManager.removeHighTextContrastStateChangeListener(this.mHighContrastTextManager);
        removeSendWindowContentChangedCallback();
        destroyHardwareRenderer();
        setAccessibilityFocus(null, null);
        this.mInsetsController.cancelExistingAnimations();
        this.mView.assignParent(null);
        this.mView = null;
        this.mAttachInfo.mRootView = null;
        destroySurface();
        InputQueue.Callback callback = this.mInputQueueCallback;
        if (callback != null && (inputQueue = this.mInputQueue) != null) {
            callback.onInputQueueDestroyed(inputQueue);
            this.mInputQueue.dispose();
            this.mInputQueueCallback = null;
            this.mInputQueue = null;
        }
        try {
            this.mWindowSession.remove(this.mWindow);
        } catch (RemoteException unused) {
        }
        WindowInputEventReceiver windowInputEventReceiver = this.mInputEventReceiver;
        if (windowInputEventReceiver != null) {
            windowInputEventReceiver.dispose();
            this.mInputEventReceiver = null;
        }
        this.mDisplayManager.unregisterDisplayListener(this.mDisplayListener);
        unscheduleTraversals();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void performConfigurationChange(MergedConfiguration mergedConfiguration, boolean z, int i) {
        if (mergedConfiguration == null) {
            throw new IllegalArgumentException("No merged config provided.");
        }
        Configuration globalConfiguration = mergedConfiguration.getGlobalConfiguration();
        Configuration overrideConfiguration = mergedConfiguration.getOverrideConfiguration();
        CompatibilityInfo compatibilityInfo = this.mDisplay.getDisplayAdjustments().getCompatibilityInfo();
        if (!compatibilityInfo.equals(CompatibilityInfo.DEFAULT_COMPATIBILITY_INFO)) {
            Configuration configuration = new Configuration(globalConfiguration);
            compatibilityInfo.applyToConfiguration(this.mNoncompatDensity, configuration);
            globalConfiguration = configuration;
        }
        synchronized (sConfigCallbacks) {
            for (int size = sConfigCallbacks.size() - 1; size >= 0; size--) {
                sConfigCallbacks.get(size).onConfigurationChanged(globalConfiguration);
            }
        }
        this.mLastReportedMergedConfiguration.setConfiguration(globalConfiguration, overrideConfiguration);
        this.mForceNextConfigUpdate = z;
        ActivityConfigCallback activityConfigCallback = this.mActivityConfigCallback;
        if (activityConfigCallback != null) {
            activityConfigCallback.onConfigurationChanged(overrideConfiguration, i);
        } else {
            updateConfiguration(i);
        }
        this.mForceNextConfigUpdate = false;
    }

    public void updateConfiguration(int i) {
        View view = this.mView;
        if (view == null) {
            return;
        }
        Resources resources = view.getResources();
        Configuration configuration = resources.getConfiguration();
        if (i != -1) {
            onMovedToDisplay(i, configuration);
        }
        if (this.mForceNextConfigUpdate || this.mLastConfigurationFromResources.diff(configuration) != 0) {
            updateInternalDisplay(this.mDisplay.getDisplayId(), resources);
            int layoutDirection = this.mLastConfigurationFromResources.getLayoutDirection();
            int layoutDirection2 = configuration.getLayoutDirection();
            this.mLastConfigurationFromResources.setTo(configuration);
            if (layoutDirection != layoutDirection2 && this.mViewLayoutDirectionInitial == 2) {
                this.mView.setLayoutDirection(layoutDirection2);
            }
            this.mView.dispatchConfigurationChanged(configuration);
            this.mForceNextWindowRelayout = true;
            requestLayout();
        }
        updateForceDarkMode();
    }

    public static boolean isViewDescendantOf(View view, View view2) {
        if (view == view2) {
            return true;
        }
        Object parent = view.getParent();
        return (parent instanceof ViewGroup) && isViewDescendantOf((View) parent, view2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void forceLayout(View view) {
        view.forceLayout();
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                forceLayout(viewGroup.getChildAt(i));
            }
        }
    }

    final class ViewRootHandler extends Handler {
        ViewRootHandler() {
        }

        @Override // android.os.Handler
        public String getMessageName(Message message) {
            switch (message.what) {
                case 1:
                    return "MSG_INVALIDATE";
                case 2:
                    return "MSG_INVALIDATE_RECT";
                case 3:
                    return "MSG_DIE";
                case 4:
                    return "MSG_RESIZED";
                case 5:
                    return "MSG_RESIZED_REPORT";
                case 6:
                    return "MSG_WINDOW_FOCUS_CHANGED";
                case 7:
                    return "MSG_DISPATCH_INPUT_EVENT";
                case 8:
                    return "MSG_DISPATCH_APP_VISIBILITY";
                case 9:
                    return "MSG_DISPATCH_GET_NEW_SURFACE";
                case 10:
                case 20:
                case 22:
                case 26:
                default:
                    return super.getMessageName(message);
                case 11:
                    return "MSG_DISPATCH_KEY_FROM_IME";
                case 12:
                    return "MSG_DISPATCH_KEY_FROM_AUTOFILL";
                case 13:
                    return "MSG_CHECK_FOCUS";
                case 14:
                    return "MSG_CLOSE_SYSTEM_DIALOGS";
                case 15:
                    return "MSG_DISPATCH_DRAG_EVENT";
                case 16:
                    return "MSG_DISPATCH_DRAG_LOCATION_EVENT";
                case 17:
                    return "MSG_DISPATCH_SYSTEM_UI_VISIBILITY";
                case 18:
                    return "MSG_UPDATE_CONFIGURATION";
                case 19:
                    return "MSG_PROCESS_INPUT_EVENTS";
                case 21:
                    return "MSG_CLEAR_ACCESSIBILITY_FOCUS_HOST";
                case 23:
                    return "MSG_WINDOW_MOVED";
                case 24:
                    return "MSG_SYNTHESIZE_INPUT_EVENT";
                case 25:
                    return "MSG_DISPATCH_WINDOW_SHOWN";
                case 27:
                    return "MSG_UPDATE_POINTER_ICON";
                case 28:
                    return "MSG_POINTER_CAPTURE_CHANGED";
                case 29:
                    return "MSG_DRAW_FINISHED";
                case 30:
                    return "MSG_INSETS_CHANGED";
                case 31:
                    return "MSG_INSETS_CONTROL_CHANGED";
                case 32:
                    return "MSG_SYSTEM_GESTURE_EXCLUSION_CHANGED";
                case 33:
                    return "MSG_LOCATION_IN_PARENT_DISPLAY_CHANGED";
                case 34:
                    return "MSG_SHOW_INSETS";
                case 35:
                    return "MSG_HIDE_INSETS";
            }
        }

        @Override // android.os.Handler
        public boolean sendMessageAtTime(Message message, long j) {
            if (message.what == 26 && message.obj == null) {
                throw new NullPointerException("Attempted to call MSG_REQUEST_KEYBOARD_SHORTCUTS with null receiver:");
            }
            return super.sendMessageAtTime(message, j);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            boolean z;
            switch (message.what) {
                case 1:
                    ((View) message.obj).invalidate();
                    return;
                case 2:
                    View.AttachInfo.InvalidateInfo invalidateInfo = (View.AttachInfo.InvalidateInfo) message.obj;
                    invalidateInfo.target.invalidate(invalidateInfo.left, invalidateInfo.top, invalidateInfo.right, invalidateInfo.bottom);
                    invalidateInfo.recycle();
                    return;
                case 3:
                    ViewRootImpl.this.doDie();
                    return;
                case 4:
                    SomeArgs someArgs = (SomeArgs) message.obj;
                    if (ViewRootImpl.this.mWinFrame.equals(someArgs.arg1) && ViewRootImpl.this.mPendingDisplayCutout.get().equals(someArgs.arg9) && ViewRootImpl.this.mPendingBackDropFrame.equals(someArgs.arg8) && ViewRootImpl.this.mLastReportedMergedConfiguration.equals(someArgs.arg4) && someArgs.argi1 == 0 && ViewRootImpl.this.mDisplay.getDisplayId() == someArgs.argi3) {
                        return;
                    }
                    break;
                case 5:
                    break;
                case 6:
                    ViewRootImpl.this.handleWindowFocusChanged();
                    return;
                case 7:
                    SomeArgs someArgs2 = (SomeArgs) message.obj;
                    ViewRootImpl.this.enqueueInputEvent((InputEvent) someArgs2.arg1, (InputEventReceiver) someArgs2.arg2, 0, true);
                    someArgs2.recycle();
                    return;
                case 8:
                    ViewRootImpl.this.handleAppVisibility(message.arg1 != 0);
                    return;
                case 9:
                    ViewRootImpl.this.handleGetNewSurface();
                    return;
                case 10:
                case 20:
                default:
                    return;
                case 11:
                    KeyEvent keyEventChangeFlags = (KeyEvent) message.obj;
                    if ((keyEventChangeFlags.getFlags() & 8) != 0) {
                        keyEventChangeFlags = KeyEvent.changeFlags(keyEventChangeFlags, keyEventChangeFlags.getFlags() & (-9));
                    }
                    ViewRootImpl.this.enqueueInputEvent(keyEventChangeFlags, null, 1, true);
                    return;
                case 12:
                    ViewRootImpl.this.enqueueInputEvent((KeyEvent) message.obj, null, 0, true);
                    return;
                case 13:
                    ViewRootImpl.this.getImeFocusController().checkFocus(false, true);
                    return;
                case 14:
                    if (ViewRootImpl.this.mView != null) {
                        ViewRootImpl.this.mView.onCloseSystemDialogs((String) message.obj);
                        return;
                    }
                    return;
                case 15:
                case 16:
                    DragEvent dragEvent = (DragEvent) message.obj;
                    dragEvent.mLocalState = ViewRootImpl.this.mLocalDragState;
                    ViewRootImpl.this.handleDragEvent(dragEvent);
                    return;
                case 17:
                    ViewRootImpl.this.handleDispatchSystemUiVisibilityChanged((SystemUiVisibilityInfo) message.obj);
                    return;
                case 18:
                    Configuration globalConfiguration = (Configuration) message.obj;
                    if (globalConfiguration.isOtherSeqNewer(ViewRootImpl.this.mLastReportedMergedConfiguration.getMergedConfiguration())) {
                        globalConfiguration = ViewRootImpl.this.mLastReportedMergedConfiguration.getGlobalConfiguration();
                    }
                    ViewRootImpl.this.mPendingMergedConfiguration.setConfiguration(globalConfiguration, ViewRootImpl.this.mLastReportedMergedConfiguration.getOverrideConfiguration());
                    ViewRootImpl viewRootImpl = ViewRootImpl.this;
                    viewRootImpl.performConfigurationChange(viewRootImpl.mPendingMergedConfiguration, false, -1);
                    return;
                case 19:
                    ViewRootImpl.this.mProcessInputEventsScheduled = false;
                    ViewRootImpl.this.doProcessInputEvents();
                    return;
                case 21:
                    ViewRootImpl.this.setAccessibilityFocus(null, null);
                    return;
                case 22:
                    if (ViewRootImpl.this.mView != null) {
                        ViewRootImpl viewRootImpl2 = ViewRootImpl.this;
                        viewRootImpl2.invalidateWorld(viewRootImpl2.mView);
                        return;
                    }
                    return;
                case 23:
                    if (ViewRootImpl.this.mAdded) {
                        int iWidth = ViewRootImpl.this.mWinFrame.width();
                        int iHeight = ViewRootImpl.this.mWinFrame.height();
                        int i = message.arg1;
                        int i2 = message.arg2;
                        ViewRootImpl.this.mTmpFrame.left = i;
                        ViewRootImpl.this.mTmpFrame.right = i + iWidth;
                        ViewRootImpl.this.mTmpFrame.top = i2;
                        ViewRootImpl.this.mTmpFrame.bottom = i2 + iHeight;
                        ViewRootImpl viewRootImpl3 = ViewRootImpl.this;
                        viewRootImpl3.setFrame(viewRootImpl3.mTmpFrame);
                        ViewRootImpl.this.mPendingBackDropFrame.set(ViewRootImpl.this.mWinFrame);
                        ViewRootImpl viewRootImpl4 = ViewRootImpl.this;
                        viewRootImpl4.maybeHandleWindowMove(viewRootImpl4.mWinFrame);
                        return;
                    }
                    return;
                case 24:
                    ViewRootImpl.this.enqueueInputEvent((InputEvent) message.obj, null, 32, true);
                    return;
                case 25:
                    ViewRootImpl.this.handleDispatchWindowShown();
                    return;
                case 26:
                    ViewRootImpl.this.handleRequestKeyboardShortcuts((IResultReceiver) message.obj, message.arg1);
                    return;
                case 27:
                    ViewRootImpl.this.resetPointerIcon((MotionEvent) message.obj);
                    return;
                case 28:
                    ViewRootImpl.this.handlePointerCaptureChanged(message.arg1 != 0);
                    return;
                case 29:
                    ViewRootImpl.this.pendingDrawFinished();
                    return;
                case 30:
                    ViewRootImpl.this.mInsetsController.onStateChanged((InsetsState) message.obj);
                    return;
                case 31:
                    SomeArgs someArgs3 = (SomeArgs) message.obj;
                    ViewRootImpl.this.mInsetsController.onStateChanged((InsetsState) someArgs3.arg1);
                    ViewRootImpl.this.mInsetsController.onControlsChanged((InsetsSourceControl[]) someArgs3.arg2);
                    return;
                case 32:
                    ViewRootImpl.this.systemGestureExclusionChanged();
                    return;
                case 33:
                    ViewRootImpl.this.updateLocationInParentDisplay(message.arg1, message.arg2);
                    return;
                case 34:
                    if (ViewRootImpl.this.mView == null) {
                        Object[] objArr = new Object[2];
                        objArr[0] = Integer.valueOf(message.arg1);
                        objArr[1] = Boolean.valueOf(message.arg2 == 1);
                        Log.e(ViewRootImpl.TAG, String.format("Calling showInsets(%d,%b) on window that no longer has views.", objArr));
                    }
                    ViewRootImpl.this.clearLowProfileModeIfNeeded(message.arg1, message.arg2 == 1);
                    ViewRootImpl.this.mInsetsController.show(message.arg1, message.arg2 == 1);
                    return;
                case 35:
                    ViewRootImpl.this.mInsetsController.hide(message.arg1, message.arg2 == 1);
                    return;
                case 36:
                    ViewRootImpl.this.handleScrollCaptureRequest((IScrollCaptureController) message.obj);
                    return;
            }
            if (ViewRootImpl.this.mAdded) {
                SomeArgs someArgs4 = (SomeArgs) message.obj;
                int i3 = someArgs4.argi3;
                MergedConfiguration mergedConfiguration = (MergedConfiguration) someArgs4.arg4;
                boolean z2 = ViewRootImpl.this.mDisplay.getDisplayId() != i3;
                if (ViewRootImpl.this.mLastReportedMergedConfiguration.equals(mergedConfiguration)) {
                    if (z2) {
                        ViewRootImpl viewRootImpl5 = ViewRootImpl.this;
                        viewRootImpl5.onMovedToDisplay(i3, viewRootImpl5.mLastConfigurationFromResources);
                    }
                    z = false;
                } else {
                    ViewRootImpl.this.performConfigurationChange(mergedConfiguration, false, z2 ? i3 : -1);
                    z = true;
                }
                boolean z3 = (ViewRootImpl.this.mWinFrame.equals(someArgs4.arg1) && ViewRootImpl.this.mPendingDisplayCutout.get().equals(someArgs4.arg9)) ? false : true;
                ViewRootImpl.this.setFrame((Rect) someArgs4.arg1);
                ViewRootImpl.this.mPendingDisplayCutout.set((DisplayCutout) someArgs4.arg9);
                ViewRootImpl.this.mPendingBackDropFrame.set((Rect) someArgs4.arg8);
                ViewRootImpl.this.mForceNextWindowRelayout = someArgs4.argi1 != 0;
                ViewRootImpl.this.mPendingAlwaysConsumeSystemBars = someArgs4.argi2 != 0;
                someArgs4.recycle();
                if (message.what == 5) {
                    ViewRootImpl.this.reportNextDraw();
                }
                if (ViewRootImpl.this.mView != null && (z3 || z)) {
                    ViewRootImpl.forceLayout(ViewRootImpl.this.mView);
                }
                ViewRootImpl.this.requestLayout();
            }
        }
    }

    boolean ensureTouchMode(boolean z) {
        if (this.mAttachInfo.mInTouchMode == z) {
            return false;
        }
        try {
            this.mWindowSession.setInTouchMode(z);
            return ensureTouchModeLocally(z);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean ensureTouchModeLocally(boolean z) {
        if (this.mAttachInfo.mInTouchMode == z) {
            return false;
        }
        this.mAttachInfo.mInTouchMode = z;
        this.mAttachInfo.mTreeObserver.dispatchOnTouchModeChanged(z);
        return z ? enterTouchMode() : leaveTouchMode();
    }

    private boolean enterTouchMode() {
        View viewFindFocus;
        View view = this.mView;
        if (view == null || !view.hasFocus() || (viewFindFocus = this.mView.findFocus()) == null || viewFindFocus.isFocusableInTouchMode()) {
            return false;
        }
        ViewGroup viewGroupFindAncestorToTakeFocusInTouchMode = findAncestorToTakeFocusInTouchMode(viewFindFocus);
        if (viewGroupFindAncestorToTakeFocusInTouchMode != null) {
            return viewGroupFindAncestorToTakeFocusInTouchMode.requestFocus();
        }
        viewFindFocus.clearFocusInternal(null, true, false);
        return true;
    }

    private static ViewGroup findAncestorToTakeFocusInTouchMode(View view) {
        ViewParent parent = view.getParent();
        while (parent instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) parent;
            if (viewGroup.getDescendantFocusability() == 262144 && viewGroup.isFocusableInTouchMode()) {
                return viewGroup;
            }
            if (viewGroup.isRootNamespace()) {
                return null;
            }
            parent = viewGroup.getParent();
        }
        return null;
    }

    private boolean leaveTouchMode() {
        View view = this.mView;
        if (view == null) {
            return false;
        }
        if (view.hasFocus()) {
            View viewFindFocus = this.mView.findFocus();
            if (!(viewFindFocus instanceof ViewGroup) || ((ViewGroup) viewFindFocus).getDescendantFocusability() != 262144) {
                return false;
            }
        }
        return this.mView.restoreDefaultFocus();
    }

    abstract class InputStage {
        protected static final int FINISH_HANDLED = 1;
        protected static final int FINISH_NOT_HANDLED = 2;
        protected static final int FORWARD = 0;
        private final InputStage mNext;
        private String mTracePrefix;

        protected int onProcess(QueuedInputEvent queuedInputEvent) {
            return 0;
        }

        public InputStage(InputStage inputStage) {
            this.mNext = inputStage;
        }

        public final void deliver(QueuedInputEvent queuedInputEvent) {
            if ((queuedInputEvent.mFlags & 4) != 0) {
                forward(queuedInputEvent);
                return;
            }
            if (shouldDropInputEvent(queuedInputEvent)) {
                finish(queuedInputEvent, false);
                return;
            }
            traceEvent(queuedInputEvent, 8L);
            try {
                int iOnProcess = onProcess(queuedInputEvent);
                Trace.traceEnd(8L);
                apply(queuedInputEvent, iOnProcess);
            } catch (Throwable th) {
                Trace.traceEnd(8L);
                throw th;
            }
        }

        protected void finish(QueuedInputEvent queuedInputEvent, boolean z) {
            queuedInputEvent.mFlags |= 4;
            if (z) {
                queuedInputEvent.mFlags |= 8;
            }
            forward(queuedInputEvent);
        }

        protected void forward(QueuedInputEvent queuedInputEvent) {
            onDeliverToNext(queuedInputEvent);
        }

        protected void apply(QueuedInputEvent queuedInputEvent, int i) {
            if (i == 0) {
                forward(queuedInputEvent);
                return;
            }
            if (i == 1) {
                finish(queuedInputEvent, true);
            } else {
                if (i == 2) {
                    finish(queuedInputEvent, false);
                    return;
                }
                throw new IllegalArgumentException("Invalid result: " + i);
            }
        }

        protected void onDeliverToNext(QueuedInputEvent queuedInputEvent) {
            InputStage inputStage = this.mNext;
            if (inputStage == null) {
                ViewRootImpl.this.finishInputEvent(queuedInputEvent);
            } else {
                inputStage.deliver(queuedInputEvent);
            }
        }

        protected void onWindowFocusChanged(boolean z) {
            InputStage inputStage = this.mNext;
            if (inputStage != null) {
                inputStage.onWindowFocusChanged(z);
            }
        }

        protected void onDetachedFromWindow() {
            InputStage inputStage = this.mNext;
            if (inputStage != null) {
                inputStage.onDetachedFromWindow();
            }
        }

        protected boolean shouldDropInputEvent(QueuedInputEvent queuedInputEvent) {
            if (ViewRootImpl.this.mView == null || !ViewRootImpl.this.mAdded) {
                Slog.w(ViewRootImpl.this.mTag, "Dropping event due to root view being removed: " + queuedInputEvent.mEvent);
                return true;
            }
            if ((ViewRootImpl.this.mAttachInfo.mHasWindowFocus || queuedInputEvent.mEvent.isFromSource(2) || ViewRootImpl.this.isAutofillUiShowing()) && !ViewRootImpl.this.mStopped && ((!ViewRootImpl.this.mIsAmbientMode || queuedInputEvent.mEvent.isFromSource(1)) && (!ViewRootImpl.this.mPausedForTransition || isBack(queuedInputEvent.mEvent)))) {
                return false;
            }
            if (!ViewRootImpl.isTerminalInputEvent(queuedInputEvent.mEvent)) {
                Slog.w(ViewRootImpl.this.mTag, "Dropping event due to no window focus: " + queuedInputEvent.mEvent);
                return true;
            }
            queuedInputEvent.mEvent.cancel();
            Slog.w(ViewRootImpl.this.mTag, "Cancelling event due to no window focus: " + queuedInputEvent.mEvent);
            return false;
        }

        void dump(String str, PrintWriter printWriter) {
            InputStage inputStage = this.mNext;
            if (inputStage != null) {
                inputStage.dump(str, printWriter);
            }
        }

        private boolean isBack(InputEvent inputEvent) {
            return (inputEvent instanceof KeyEvent) && ((KeyEvent) inputEvent).getKeyCode() == 4;
        }

        private void traceEvent(QueuedInputEvent queuedInputEvent, long j) {
            if (Trace.isTagEnabled(j)) {
                if (this.mTracePrefix == null) {
                    this.mTracePrefix = getClass().getSimpleName();
                }
                Trace.traceBegin(j, this.mTracePrefix + " id=0x" + Integer.toHexString(queuedInputEvent.mEvent.getId()));
            }
        }
    }

    abstract class AsyncInputStage extends InputStage {
        protected static final int DEFER = 3;
        private QueuedInputEvent mQueueHead;
        private int mQueueLength;
        private QueuedInputEvent mQueueTail;
        private final String mTraceCounter;

        public AsyncInputStage(InputStage inputStage, String str) {
            super(inputStage);
            this.mTraceCounter = str;
        }

        protected void defer(QueuedInputEvent queuedInputEvent) {
            queuedInputEvent.mFlags |= 2;
            enqueue(queuedInputEvent);
        }

        @Override // android.view.ViewRootImpl.InputStage
        protected void forward(QueuedInputEvent queuedInputEvent) {
            queuedInputEvent.mFlags &= -3;
            QueuedInputEvent queuedInputEvent2 = this.mQueueHead;
            if (queuedInputEvent2 == null) {
                super.forward(queuedInputEvent);
                return;
            }
            int deviceId = queuedInputEvent.mEvent.getDeviceId();
            QueuedInputEvent queuedInputEvent3 = null;
            boolean z = false;
            while (queuedInputEvent2 != null && queuedInputEvent2 != queuedInputEvent) {
                if (!z && deviceId == queuedInputEvent2.mEvent.getDeviceId()) {
                    z = true;
                }
                queuedInputEvent3 = queuedInputEvent2;
                queuedInputEvent2 = queuedInputEvent2.mNext;
            }
            if (z) {
                if (queuedInputEvent2 == null) {
                    enqueue(queuedInputEvent);
                    return;
                }
                return;
            }
            if (queuedInputEvent2 != null) {
                queuedInputEvent2 = queuedInputEvent2.mNext;
                dequeue(queuedInputEvent, queuedInputEvent3);
            }
            super.forward(queuedInputEvent);
            QueuedInputEvent queuedInputEvent4 = queuedInputEvent3;
            while (true) {
                QueuedInputEvent queuedInputEvent5 = queuedInputEvent2;
                while (queuedInputEvent5 != null) {
                    if (deviceId == queuedInputEvent5.mEvent.getDeviceId()) {
                        if ((queuedInputEvent5.mFlags & 2) != 0) {
                            return;
                        }
                        queuedInputEvent2 = queuedInputEvent5.mNext;
                        dequeue(queuedInputEvent5, queuedInputEvent4);
                        super.forward(queuedInputEvent5);
                    } else {
                        QueuedInputEvent queuedInputEvent6 = queuedInputEvent5;
                        queuedInputEvent5 = queuedInputEvent5.mNext;
                        queuedInputEvent4 = queuedInputEvent6;
                    }
                }
                return;
            }
        }

        @Override // android.view.ViewRootImpl.InputStage
        protected void apply(QueuedInputEvent queuedInputEvent, int i) {
            if (i == 3) {
                defer(queuedInputEvent);
            } else {
                super.apply(queuedInputEvent, i);
            }
        }

        private void enqueue(QueuedInputEvent queuedInputEvent) {
            QueuedInputEvent queuedInputEvent2 = this.mQueueTail;
            if (queuedInputEvent2 == null) {
                this.mQueueHead = queuedInputEvent;
                this.mQueueTail = queuedInputEvent;
            } else {
                queuedInputEvent2.mNext = queuedInputEvent;
                this.mQueueTail = queuedInputEvent;
            }
            int i = this.mQueueLength + 1;
            this.mQueueLength = i;
            Trace.traceCounter(4L, this.mTraceCounter, i);
        }

        private void dequeue(QueuedInputEvent queuedInputEvent, QueuedInputEvent queuedInputEvent2) {
            if (queuedInputEvent2 == null) {
                this.mQueueHead = queuedInputEvent.mNext;
            } else {
                queuedInputEvent2.mNext = queuedInputEvent.mNext;
            }
            if (this.mQueueTail == queuedInputEvent) {
                this.mQueueTail = queuedInputEvent2;
            }
            queuedInputEvent.mNext = null;
            int i = this.mQueueLength - 1;
            this.mQueueLength = i;
            Trace.traceCounter(4L, this.mTraceCounter, i);
        }

        @Override // android.view.ViewRootImpl.InputStage
        void dump(String str, PrintWriter printWriter) {
            printWriter.print(str);
            printWriter.print(getClass().getName());
            printWriter.print(": mQueueLength=");
            printWriter.println(this.mQueueLength);
            super.dump(str, printWriter);
        }
    }

    final class NativePreImeInputStage extends AsyncInputStage implements InputQueue.FinishedInputEventCallback {
        public NativePreImeInputStage(InputStage inputStage, String str) {
            super(inputStage, str);
        }

        @Override // android.view.ViewRootImpl.InputStage
        protected int onProcess(QueuedInputEvent queuedInputEvent) {
            if (ViewRootImpl.this.mInputQueue == null || !(queuedInputEvent.mEvent instanceof KeyEvent)) {
                return 0;
            }
            ViewRootImpl.this.mInputQueue.sendInputEvent(queuedInputEvent.mEvent, queuedInputEvent, true, this);
            return 3;
        }

        @Override // android.view.InputQueue.FinishedInputEventCallback
        public void onFinishedInputEvent(Object obj, boolean z) {
            QueuedInputEvent queuedInputEvent = (QueuedInputEvent) obj;
            if (z) {
                finish(queuedInputEvent, true);
            } else {
                forward(queuedInputEvent);
            }
        }
    }

    final class ViewPreImeInputStage extends InputStage {
        public ViewPreImeInputStage(InputStage inputStage) {
            super(inputStage);
        }

        @Override // android.view.ViewRootImpl.InputStage
        protected int onProcess(QueuedInputEvent queuedInputEvent) {
            if (queuedInputEvent.mEvent instanceof KeyEvent) {
                return processKeyEvent(queuedInputEvent);
            }
            return 0;
        }

        private int processKeyEvent(QueuedInputEvent queuedInputEvent) {
            return ViewRootImpl.this.mView.dispatchKeyEventPreIme((KeyEvent) queuedInputEvent.mEvent) ? 1 : 0;
        }
    }

    final class ImeInputStage extends AsyncInputStage implements InputMethodManager.FinishedInputEventCallback {
        public ImeInputStage(InputStage inputStage, String str) {
            super(inputStage, str);
        }

        @Override // android.view.ViewRootImpl.InputStage
        protected int onProcess(QueuedInputEvent queuedInputEvent) {
            int iOnProcessImeInputStage = ViewRootImpl.this.mImeFocusController.onProcessImeInputStage(queuedInputEvent, queuedInputEvent.mEvent, ViewRootImpl.this.mWindowAttributes, this);
            if (iOnProcessImeInputStage == -1) {
                return 3;
            }
            if (iOnProcessImeInputStage == 0) {
                return 0;
            }
            if (iOnProcessImeInputStage == 1) {
                return 1;
            }
            throw new IllegalStateException("Unexpected result=" + iOnProcessImeInputStage);
        }

        @Override // android.view.inputmethod.InputMethodManager.FinishedInputEventCallback
        public void onFinishedInputEvent(Object obj, boolean z) {
            QueuedInputEvent queuedInputEvent = (QueuedInputEvent) obj;
            if (z) {
                finish(queuedInputEvent, true);
            } else {
                forward(queuedInputEvent);
            }
        }
    }

    final class EarlyPostImeInputStage extends InputStage {
        public EarlyPostImeInputStage(InputStage inputStage) {
            super(inputStage);
        }

        @Override // android.view.ViewRootImpl.InputStage
        protected int onProcess(QueuedInputEvent queuedInputEvent) {
            if (queuedInputEvent.mEvent instanceof KeyEvent) {
                return processKeyEvent(queuedInputEvent);
            }
            if (queuedInputEvent.mEvent instanceof MotionEvent) {
                return processMotionEvent(queuedInputEvent);
            }
            return 0;
        }

        private int processKeyEvent(QueuedInputEvent queuedInputEvent) {
            KeyEvent keyEvent = (KeyEvent) queuedInputEvent.mEvent;
            if (ViewRootImpl.this.mAttachInfo.mTooltipHost != null) {
                ViewRootImpl.this.mAttachInfo.mTooltipHost.handleTooltipKey(keyEvent);
            }
            if (ViewRootImpl.this.checkForLeavingTouchModeAndConsume(keyEvent)) {
                return 1;
            }
            ViewRootImpl.this.mFallbackEventHandler.preDispatchKeyEvent(keyEvent);
            return 0;
        }

        private int processMotionEvent(QueuedInputEvent queuedInputEvent) {
            MotionEvent motionEvent = (MotionEvent) queuedInputEvent.mEvent;
            if (motionEvent.isFromSource(2)) {
                return processPointerEvent(queuedInputEvent);
            }
            int actionMasked = motionEvent.getActionMasked();
            if ((actionMasked == 0 || actionMasked == 8) && motionEvent.isFromSource(8)) {
                ViewRootImpl.this.ensureTouchMode(false);
            }
            return 0;
        }

        private int processPointerEvent(QueuedInputEvent queuedInputEvent) {
            AutofillManager autofillManager;
            MotionEvent motionEvent = (MotionEvent) queuedInputEvent.mEvent;
            if (ViewRootImpl.this.mTranslator != null) {
                ViewRootImpl.this.mTranslator.translateEventInScreenToAppWindow(motionEvent);
            }
            int action = motionEvent.getAction();
            if (action == 0 || action == 8) {
                ViewRootImpl.this.ensureTouchMode(true);
            }
            if (action == 0 && (autofillManager = ViewRootImpl.this.getAutofillManager()) != null) {
                autofillManager.requestHideFillUi();
            }
            if (action == 0 && ViewRootImpl.this.mAttachInfo.mTooltipHost != null) {
                ViewRootImpl.this.mAttachInfo.mTooltipHost.hideTooltip();
            }
            if (ViewRootImpl.this.mCurScrollY != 0) {
                motionEvent.offsetLocation(0.0f, ViewRootImpl.this.mCurScrollY);
            }
            if (!motionEvent.isTouchEvent()) {
                return 0;
            }
            ViewRootImpl.this.mLastTouchPoint.x = motionEvent.getRawX();
            ViewRootImpl.this.mLastTouchPoint.y = motionEvent.getRawY();
            ViewRootImpl.this.mLastTouchSource = motionEvent.getSource();
            return 0;
        }
    }

    final class NativePostImeInputStage extends AsyncInputStage implements InputQueue.FinishedInputEventCallback {
        public NativePostImeInputStage(InputStage inputStage, String str) {
            super(inputStage, str);
        }

        @Override // android.view.ViewRootImpl.InputStage
        protected int onProcess(QueuedInputEvent queuedInputEvent) {
            if (ViewRootImpl.this.mInputQueue == null) {
                return 0;
            }
            ViewRootImpl.this.mInputQueue.sendInputEvent(queuedInputEvent.mEvent, queuedInputEvent, false, this);
            return 3;
        }

        @Override // android.view.InputQueue.FinishedInputEventCallback
        public void onFinishedInputEvent(Object obj, boolean z) {
            QueuedInputEvent queuedInputEvent = (QueuedInputEvent) obj;
            if (z) {
                finish(queuedInputEvent, true);
            } else {
                forward(queuedInputEvent);
            }
        }
    }

    final class ViewPostImeInputStage extends InputStage {
        public ViewPostImeInputStage(InputStage inputStage) {
            super(inputStage);
        }

        @Override // android.view.ViewRootImpl.InputStage
        protected int onProcess(QueuedInputEvent queuedInputEvent) {
            if (queuedInputEvent.mEvent instanceof KeyEvent) {
                return processKeyEvent(queuedInputEvent);
            }
            int source = queuedInputEvent.mEvent.getSource();
            if ((source & 2) != 0) {
                return processPointerEvent(queuedInputEvent);
            }
            if ((source & 4) != 0) {
                return processTrackballEvent(queuedInputEvent);
            }
            return processGenericMotionEvent(queuedInputEvent);
        }

        @Override // android.view.ViewRootImpl.InputStage
        protected void onDeliverToNext(QueuedInputEvent queuedInputEvent) {
            if (ViewRootImpl.this.mUnbufferedInputDispatch && (queuedInputEvent.mEvent instanceof MotionEvent) && ((MotionEvent) queuedInputEvent.mEvent).isTouchEvent() && ViewRootImpl.isTerminalInputEvent(queuedInputEvent.mEvent)) {
                ViewRootImpl.this.mUnbufferedInputDispatch = false;
                ViewRootImpl.this.scheduleConsumeBatchedInput();
            }
            super.onDeliverToNext(queuedInputEvent);
        }

        private boolean performFocusNavigation(KeyEvent keyEvent) {
            int i;
            int keyCode = keyEvent.getKeyCode();
            if (keyCode != 61) {
                switch (keyCode) {
                    case 19:
                        i = !keyEvent.hasNoModifiers() ? 0 : 33;
                        break;
                    case 20:
                        i = !keyEvent.hasNoModifiers() ? 0 : 130;
                        break;
                    case 21:
                        i = !keyEvent.hasNoModifiers() ? 0 : 17;
                        break;
                    case 22:
                        i = !keyEvent.hasNoModifiers() ? 0 : 66;
                        break;
                    default:
                        i = 0;
                        break;
                }
            } else if (keyEvent.hasNoModifiers()) {
                i = 2;
            } else {
                i = keyEvent.hasModifiers(1) ? 1 : 0;
            }
            if (i != 0) {
                View viewFindFocus = ViewRootImpl.this.mView.findFocus();
                if (viewFindFocus != null) {
                    View viewFocusSearch = viewFindFocus.focusSearch(i);
                    if (viewFocusSearch != null && viewFocusSearch != viewFindFocus) {
                        viewFindFocus.getFocusedRect(ViewRootImpl.this.mTempRect);
                        if (ViewRootImpl.this.mView instanceof ViewGroup) {
                            ((ViewGroup) ViewRootImpl.this.mView).offsetDescendantRectToMyCoords(viewFindFocus, ViewRootImpl.this.mTempRect);
                            ((ViewGroup) ViewRootImpl.this.mView).offsetRectIntoDescendantCoords(viewFocusSearch, ViewRootImpl.this.mTempRect);
                        }
                        if (viewFocusSearch.requestFocus(i, ViewRootImpl.this.mTempRect)) {
                            ViewRootImpl.this.playSoundEffect(SoundEffectConstants.getContantForFocusDirection(i));
                            return true;
                        }
                    }
                    if (ViewRootImpl.this.mView.dispatchUnhandledMove(viewFindFocus, i)) {
                        return true;
                    }
                } else if (ViewRootImpl.this.mView.restoreDefaultFocus()) {
                    return true;
                }
            }
            return false;
        }

        private boolean performKeyboardGroupNavigation(int i) {
            View viewKeyboardNavigationClusterSearch;
            View viewFindFocus = ViewRootImpl.this.mView.findFocus();
            if (viewFindFocus == null && ViewRootImpl.this.mView.restoreDefaultFocus()) {
                return true;
            }
            if (viewFindFocus == null) {
                viewKeyboardNavigationClusterSearch = ViewRootImpl.this.keyboardNavigationClusterSearch(null, i);
            } else {
                viewKeyboardNavigationClusterSearch = viewFindFocus.keyboardNavigationClusterSearch(null, i);
            }
            int i2 = (i == 2 || i == 1) ? 130 : i;
            if (viewKeyboardNavigationClusterSearch != null && viewKeyboardNavigationClusterSearch.isRootNamespace()) {
                if (viewKeyboardNavigationClusterSearch.restoreFocusNotInCluster()) {
                    ViewRootImpl.this.playSoundEffect(SoundEffectConstants.getContantForFocusDirection(i));
                    return true;
                }
                viewKeyboardNavigationClusterSearch = ViewRootImpl.this.keyboardNavigationClusterSearch(null, i);
            }
            if (viewKeyboardNavigationClusterSearch == null || !viewKeyboardNavigationClusterSearch.restoreFocusInCluster(i2)) {
                return false;
            }
            ViewRootImpl.this.playSoundEffect(SoundEffectConstants.getContantForFocusDirection(i));
            return true;
        }

        /* JADX WARN: Removed duplicated region for block: B:24:0x0062  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        private int processKeyEvent(android.view.ViewRootImpl.QueuedInputEvent r7) {
            /*
                r6 = this;
                android.view.InputEvent r0 = r7.mEvent
                android.view.KeyEvent r0 = (android.view.KeyEvent) r0
                android.view.ViewRootImpl r1 = android.view.ViewRootImpl.this
                android.view.ViewRootImpl$UnhandledKeyManager r1 = android.view.ViewRootImpl.access$2500(r1)
                boolean r1 = r1.preViewDispatch(r0)
                r2 = 1
                if (r1 == 0) goto L12
                return r2
            L12:
                android.view.ViewRootImpl r1 = android.view.ViewRootImpl.this
                android.view.View r1 = r1.mView
                boolean r1 = r1.dispatchKeyEvent(r0)
                if (r1 == 0) goto L1d
                return r2
            L1d:
                boolean r1 = r6.shouldDropInputEvent(r7)
                r3 = 2
                if (r1 == 0) goto L25
                return r3
            L25:
                android.view.ViewRootImpl r1 = android.view.ViewRootImpl.this
                android.view.ViewRootImpl$UnhandledKeyManager r1 = android.view.ViewRootImpl.access$2500(r1)
                android.view.ViewRootImpl r4 = android.view.ViewRootImpl.this
                android.view.View r4 = r4.mView
                boolean r1 = r1.dispatch(r4, r0)
                if (r1 == 0) goto L36
                return r2
            L36:
                int r1 = r0.getAction()
                r4 = 0
                if (r1 != 0) goto L62
                int r1 = r0.getKeyCode()
                r5 = 61
                if (r1 != r5) goto L62
                int r1 = r0.getMetaState()
                r5 = 65536(0x10000, float:9.1835E-41)
                boolean r1 = android.view.KeyEvent.metaStateHasModifiers(r1, r5)
                if (r1 == 0) goto L53
                r1 = r3
                goto L63
            L53:
                int r1 = r0.getMetaState()
                r5 = 65537(0x10001, float:9.1837E-41)
                boolean r1 = android.view.KeyEvent.metaStateHasModifiers(r1, r5)
                if (r1 == 0) goto L62
                r1 = r2
                goto L63
            L62:
                r1 = r4
            L63:
                int r5 = r0.getAction()
                if (r5 != 0) goto L97
                int r5 = r0.getMetaState()
                boolean r5 = android.view.KeyEvent.metaStateHasNoModifiers(r5)
                if (r5 != 0) goto L97
                int r5 = r0.getRepeatCount()
                if (r5 != 0) goto L97
                int r5 = r0.getKeyCode()
                boolean r5 = android.view.KeyEvent.isModifierKey(r5)
                if (r5 != 0) goto L97
                if (r1 != 0) goto L97
                android.view.ViewRootImpl r5 = android.view.ViewRootImpl.this
                android.view.View r5 = r5.mView
                boolean r5 = r5.dispatchKeyShortcutEvent(r0)
                if (r5 == 0) goto L90
                return r2
            L90:
                boolean r5 = r6.shouldDropInputEvent(r7)
                if (r5 == 0) goto L97
                return r3
            L97:
                android.view.ViewRootImpl r5 = android.view.ViewRootImpl.this
                android.view.FallbackEventHandler r5 = r5.mFallbackEventHandler
                boolean r5 = r5.dispatchKeyEvent(r0)
                if (r5 == 0) goto La2
                return r2
            La2:
                boolean r7 = r6.shouldDropInputEvent(r7)
                if (r7 == 0) goto La9
                return r3
            La9:
                int r7 = r0.getAction()
                if (r7 != 0) goto Lbf
                if (r1 == 0) goto Lb8
                boolean r7 = r6.performKeyboardGroupNavigation(r1)
                if (r7 == 0) goto Lbf
                return r2
            Lb8:
                boolean r7 = r6.performFocusNavigation(r0)
                if (r7 == 0) goto Lbf
                return r2
            Lbf:
                return r4
            */
            throw new UnsupportedOperationException("Method not decompiled: android.view.ViewRootImpl.ViewPostImeInputStage.processKeyEvent(android.view.ViewRootImpl$QueuedInputEvent):int");
        }

        private int processPointerEvent(QueuedInputEvent queuedInputEvent) {
            MotionEvent motionEvent = (MotionEvent) queuedInputEvent.mEvent;
            ViewRootImpl.this.mAttachInfo.mUnbufferedDispatchRequested = false;
            ViewRootImpl.this.mAttachInfo.mHandlingPointerEvent = true;
            boolean zDispatchPointerEvent = ViewRootImpl.this.mView.dispatchPointerEvent(motionEvent);
            maybeUpdatePointerIcon(motionEvent);
            ViewRootImpl.this.maybeUpdateTooltip(motionEvent);
            ViewRootImpl.this.mAttachInfo.mHandlingPointerEvent = false;
            if (ViewRootImpl.this.mAttachInfo.mUnbufferedDispatchRequested && !ViewRootImpl.this.mUnbufferedInputDispatch) {
                ViewRootImpl.this.mUnbufferedInputDispatch = true;
                if (ViewRootImpl.this.mConsumeBatchedInputScheduled) {
                    ViewRootImpl.this.scheduleConsumeBatchedInputImmediately();
                }
            }
            return zDispatchPointerEvent ? 1 : 0;
        }

        private void maybeUpdatePointerIcon(MotionEvent motionEvent) {
            if (motionEvent.getPointerCount() == 1 && motionEvent.isFromSource(8194)) {
                if (motionEvent.getActionMasked() == 9 || motionEvent.getActionMasked() == 10) {
                    ViewRootImpl.this.mPointerIconType = 1;
                }
                if (motionEvent.getActionMasked() == 10 || ViewRootImpl.this.updatePointerIcon(motionEvent) || motionEvent.getActionMasked() != 7) {
                    return;
                }
                ViewRootImpl.this.mPointerIconType = 1;
            }
        }

        private int processTrackballEvent(QueuedInputEvent queuedInputEvent) {
            MotionEvent motionEvent = (MotionEvent) queuedInputEvent.mEvent;
            return ((!motionEvent.isFromSource(InputDevice.SOURCE_MOUSE_RELATIVE) || (ViewRootImpl.this.hasPointerCapture() && !ViewRootImpl.this.mView.dispatchCapturedPointerEvent(motionEvent))) && !ViewRootImpl.this.mView.dispatchTrackballEvent(motionEvent)) ? 0 : 1;
        }

        private int processGenericMotionEvent(QueuedInputEvent queuedInputEvent) {
            MotionEvent motionEvent = (MotionEvent) queuedInputEvent.mEvent;
            return ((motionEvent.isFromSource(1048584) && ViewRootImpl.this.hasPointerCapture() && ViewRootImpl.this.mView.dispatchCapturedPointerEvent(motionEvent)) || ViewRootImpl.this.mView.dispatchGenericMotionEvent(motionEvent)) ? 1 : 0;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void resetPointerIcon(MotionEvent motionEvent) {
        this.mPointerIconType = 1;
        updatePointerIcon(motionEvent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean updatePointerIcon(MotionEvent motionEvent) {
        float x = motionEvent.getX(0);
        float y = motionEvent.getY(0);
        if (this.mView == null) {
            Slog.d(this.mTag, "updatePointerIcon called after view was removed");
            return false;
        }
        if (x < 0.0f || x >= r3.getWidth() || y < 0.0f || y >= this.mView.getHeight()) {
            Slog.d(this.mTag, "updatePointerIcon called with position out of bounds");
            return false;
        }
        PointerIcon pointerIconOnResolvePointerIcon = this.mView.onResolvePointerIcon(motionEvent, 0);
        int type = pointerIconOnResolvePointerIcon != null ? pointerIconOnResolvePointerIcon.getType() : 1000;
        if (this.mPointerIconType != type) {
            this.mPointerIconType = type;
            this.mCustomPointerIcon = null;
            if (type != -1) {
                InputManager.getInstance().setPointerIconType(type);
                return true;
            }
        }
        if (this.mPointerIconType == -1 && !pointerIconOnResolvePointerIcon.equals(this.mCustomPointerIcon)) {
            this.mCustomPointerIcon = pointerIconOnResolvePointerIcon;
            InputManager.getInstance().setCustomPointerIcon(this.mCustomPointerIcon);
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void maybeUpdateTooltip(MotionEvent motionEvent) {
        if (motionEvent.getPointerCount() != 1) {
            return;
        }
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 9 || actionMasked == 7 || actionMasked == 10) {
            AccessibilityManager accessibilityManager = AccessibilityManager.getInstance(this.mContext);
            if (accessibilityManager.isEnabled() && accessibilityManager.isTouchExplorationEnabled()) {
                return;
            }
            View view = this.mView;
            if (view == null) {
                Slog.d(this.mTag, "maybeUpdateTooltip called after view was removed");
            } else {
                view.dispatchTooltipHoverEvent(motionEvent);
            }
        }
    }

    final class SyntheticInputStage extends InputStage {
        private final SyntheticJoystickHandler mJoystick;
        private final SyntheticKeyboardHandler mKeyboard;
        private final SyntheticTouchNavigationHandler mTouchNavigation;
        private final SyntheticTrackballHandler mTrackball;

        public SyntheticInputStage() {
            super(null);
            this.mTrackball = ViewRootImpl.this.new SyntheticTrackballHandler();
            this.mJoystick = ViewRootImpl.this.new SyntheticJoystickHandler();
            this.mTouchNavigation = ViewRootImpl.this.new SyntheticTouchNavigationHandler();
            this.mKeyboard = ViewRootImpl.this.new SyntheticKeyboardHandler();
        }

        @Override // android.view.ViewRootImpl.InputStage
        protected int onProcess(QueuedInputEvent queuedInputEvent) {
            queuedInputEvent.mFlags |= 16;
            if (queuedInputEvent.mEvent instanceof MotionEvent) {
                MotionEvent motionEvent = (MotionEvent) queuedInputEvent.mEvent;
                int source = motionEvent.getSource();
                if ((source & 4) != 0) {
                    this.mTrackball.process(motionEvent);
                    return 1;
                }
                if ((source & 16) != 0) {
                    this.mJoystick.process(motionEvent);
                    return 1;
                }
                if ((source & 2097152) != 2097152) {
                    return 0;
                }
                this.mTouchNavigation.process(motionEvent);
                return 1;
            }
            if ((queuedInputEvent.mFlags & 32) == 0) {
                return 0;
            }
            this.mKeyboard.process((KeyEvent) queuedInputEvent.mEvent);
            return 1;
        }

        @Override // android.view.ViewRootImpl.InputStage
        protected void onDeliverToNext(QueuedInputEvent queuedInputEvent) {
            if ((queuedInputEvent.mFlags & 16) == 0 && (queuedInputEvent.mEvent instanceof MotionEvent)) {
                MotionEvent motionEvent = (MotionEvent) queuedInputEvent.mEvent;
                int source = motionEvent.getSource();
                if ((source & 4) != 0) {
                    this.mTrackball.cancel();
                } else if ((source & 16) != 0) {
                    this.mJoystick.cancel();
                } else if ((source & 2097152) == 2097152) {
                    this.mTouchNavigation.cancel(motionEvent);
                }
            }
            super.onDeliverToNext(queuedInputEvent);
        }

        @Override // android.view.ViewRootImpl.InputStage
        protected void onWindowFocusChanged(boolean z) {
            if (z) {
                return;
            }
            this.mJoystick.cancel();
        }

        @Override // android.view.ViewRootImpl.InputStage
        protected void onDetachedFromWindow() {
            this.mJoystick.cancel();
        }
    }

    final class SyntheticTrackballHandler {
        private long mLastTime;
        private final TrackballAxis mX = new TrackballAxis();
        private final TrackballAxis mY = new TrackballAxis();

        SyntheticTrackballHandler() {
        }

        public void process(MotionEvent motionEvent) {
            int i;
            long j;
            int i2;
            int iGenerate;
            int i3;
            int i4;
            long jUptimeMillis = SystemClock.uptimeMillis();
            if (this.mLastTime + 250 < jUptimeMillis) {
                this.mX.reset(0);
                this.mY.reset(0);
                this.mLastTime = jUptimeMillis;
            }
            int action = motionEvent.getAction();
            int metaState = motionEvent.getMetaState();
            if (action == 0) {
                i = 0;
                this.mX.reset(2);
                this.mY.reset(2);
                j = jUptimeMillis;
                i2 = 2;
                ViewRootImpl.this.enqueueInputEvent(new KeyEvent(jUptimeMillis, jUptimeMillis, 0, 23, 0, metaState, -1, 0, 1024, 257));
            } else if (action != 1) {
                i = 0;
                j = jUptimeMillis;
                i2 = 2;
            } else {
                this.mX.reset(2);
                this.mY.reset(2);
                i = 0;
                ViewRootImpl.this.enqueueInputEvent(new KeyEvent(jUptimeMillis, jUptimeMillis, 1, 23, 0, metaState, -1, 0, 1024, 257));
                j = jUptimeMillis;
                i2 = 2;
            }
            float fCollect = this.mX.collect(motionEvent.getX(), motionEvent.getEventTime(), "X");
            float fCollect2 = this.mY.collect(motionEvent.getY(), motionEvent.getEventTime(), "Y");
            float f = 1.0f;
            if (fCollect > fCollect2) {
                iGenerate = this.mX.generate();
                if (iGenerate != 0) {
                    i4 = iGenerate > 0 ? 22 : 21;
                    f = this.mX.acceleration;
                    this.mY.reset(i2);
                    i3 = i4;
                }
                i3 = i;
            } else if (fCollect2 > 0.0f) {
                iGenerate = this.mY.generate();
                if (iGenerate != 0) {
                    i4 = iGenerate > 0 ? 20 : 19;
                    f = this.mY.acceleration;
                    this.mX.reset(i2);
                    i3 = i4;
                }
                i3 = i;
            } else {
                iGenerate = i;
                i3 = iGenerate;
            }
            if (i3 != 0) {
                if (iGenerate < 0) {
                    iGenerate = -iGenerate;
                }
                int i5 = (int) (iGenerate * f);
                if (i5 > iGenerate) {
                    int i6 = iGenerate - 1;
                    ViewRootImpl.this.enqueueInputEvent(new KeyEvent(j, j, 2, i3, i5 - i6, metaState, -1, 0, 1024, 257));
                    iGenerate = i6;
                }
                long jUptimeMillis2 = j;
                while (iGenerate > 0) {
                    iGenerate--;
                    jUptimeMillis2 = SystemClock.uptimeMillis();
                    int i7 = i3;
                    ViewRootImpl.this.enqueueInputEvent(new KeyEvent(jUptimeMillis2, jUptimeMillis2, 0, i7, 0, metaState, -1, 0, 1024, 257));
                    ViewRootImpl.this.enqueueInputEvent(new KeyEvent(jUptimeMillis2, jUptimeMillis2, 1, i7, 0, metaState, -1, 0, 1024, 257));
                }
                this.mLastTime = jUptimeMillis2;
            }
        }

        public void cancel() {
            this.mLastTime = -2147483648L;
            if (ViewRootImpl.this.mView == null || !ViewRootImpl.this.mAdded) {
                return;
            }
            ViewRootImpl.this.ensureTouchMode(false);
        }
    }

    static final class TrackballAxis {
        static final float ACCEL_MOVE_SCALING_FACTOR = 0.025f;
        static final long FAST_MOVE_TIME = 150;
        static final float FIRST_MOVEMENT_THRESHOLD = 0.5f;
        static final float MAX_ACCELERATION = 20.0f;
        static final float SECOND_CUMULATIVE_MOVEMENT_THRESHOLD = 2.0f;
        static final float SUBSEQUENT_INCREMENTAL_MOVEMENT_THRESHOLD = 1.0f;
        int dir;
        int nonAccelMovement;
        float position;
        int step;
        float acceleration = 1.0f;
        long lastMoveTime = 0;

        TrackballAxis() {
        }

        void reset(int i) {
            this.position = 0.0f;
            this.acceleration = 1.0f;
            this.lastMoveTime = 0L;
            this.step = i;
            this.dir = 0;
        }

        float collect(float f, long j, String str) {
            long j2;
            if (f > 0.0f) {
                j2 = (long) (150.0f * f);
                if (this.dir < 0) {
                    this.position = 0.0f;
                    this.step = 0;
                    this.acceleration = 1.0f;
                    this.lastMoveTime = 0L;
                }
                this.dir = 1;
            } else if (f < 0.0f) {
                j2 = (long) ((-f) * 150.0f);
                if (this.dir > 0) {
                    this.position = 0.0f;
                    this.step = 0;
                    this.acceleration = 1.0f;
                    this.lastMoveTime = 0L;
                }
                this.dir = -1;
            } else {
                j2 = 0;
            }
            if (j2 > 0) {
                long j3 = j - this.lastMoveTime;
                this.lastMoveTime = j;
                float f2 = this.acceleration;
                if (j3 < j2) {
                    float f3 = (j2 - j3) * ACCEL_MOVE_SCALING_FACTOR;
                    if (f3 > 1.0f) {
                        f2 *= f3;
                    }
                    if (f2 >= MAX_ACCELERATION) {
                        f2 = 20.0f;
                    }
                    this.acceleration = f2;
                } else {
                    float f4 = (j3 - j2) * ACCEL_MOVE_SCALING_FACTOR;
                    if (f4 > 1.0f) {
                        f2 /= f4;
                    }
                    this.acceleration = f2 > 1.0f ? f2 : 1.0f;
                }
            }
            float f5 = this.position + f;
            this.position = f5;
            return Math.abs(f5);
        }

        int generate() {
            int i = 0;
            this.nonAccelMovement = 0;
            while (true) {
                int i2 = this.position >= 0.0f ? 1 : -1;
                int i3 = this.step;
                if (i3 != 0) {
                    if (i3 != 1) {
                        if (Math.abs(this.position) < 1.0f) {
                            return i;
                        }
                        i += i2;
                        this.position -= i2 * 1.0f;
                        float f = this.acceleration;
                        float f2 = 1.1f * f;
                        if (f2 < MAX_ACCELERATION) {
                            f = f2;
                        }
                        this.acceleration = f;
                    } else {
                        if (Math.abs(this.position) < SECOND_CUMULATIVE_MOVEMENT_THRESHOLD) {
                            return i;
                        }
                        i += i2;
                        this.nonAccelMovement += i2;
                        this.position -= i2 * SECOND_CUMULATIVE_MOVEMENT_THRESHOLD;
                        this.step = 2;
                    }
                } else {
                    if (Math.abs(this.position) < 0.5f) {
                        return i;
                    }
                    i += i2;
                    this.nonAccelMovement += i2;
                    this.step = 1;
                }
            }
        }
    }

    final class SyntheticJoystickHandler extends Handler {
        private static final int MSG_ENQUEUE_X_AXIS_KEY_REPEAT = 1;
        private static final int MSG_ENQUEUE_Y_AXIS_KEY_REPEAT = 2;
        private final SparseArray<KeyEvent> mDeviceKeyEvents;
        private final JoystickAxesState mJoystickAxesState;

        public SyntheticJoystickHandler() {
            super(true);
            this.mJoystickAxesState = new JoystickAxesState();
            this.mDeviceKeyEvents = new SparseArray<>();
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            int i = message.what;
            if ((i == 1 || i == 2) && ViewRootImpl.this.mAttachInfo.mHasWindowFocus) {
                KeyEvent keyEvent = (KeyEvent) message.obj;
                KeyEvent keyEventChangeTimeRepeat = KeyEvent.changeTimeRepeat(keyEvent, SystemClock.uptimeMillis(), keyEvent.getRepeatCount() + 1);
                ViewRootImpl.this.enqueueInputEvent(keyEventChangeTimeRepeat);
                Message messageObtainMessage = obtainMessage(message.what, keyEventChangeTimeRepeat);
                messageObtainMessage.setAsynchronous(true);
                sendMessageDelayed(messageObtainMessage, ViewConfiguration.getKeyRepeatDelay());
            }
        }

        public void process(MotionEvent motionEvent) {
            int actionMasked = motionEvent.getActionMasked();
            if (actionMasked == 2) {
                update(motionEvent);
                return;
            }
            if (actionMasked != 3) {
                Log.w(ViewRootImpl.this.mTag, "Unexpected action: " + motionEvent.getActionMasked());
                return;
            }
            cancel();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void cancel() {
            removeMessages(1);
            removeMessages(2);
            for (int i = 0; i < this.mDeviceKeyEvents.size(); i++) {
                KeyEvent keyEventValueAt = this.mDeviceKeyEvents.valueAt(i);
                if (keyEventValueAt != null) {
                    ViewRootImpl.this.enqueueInputEvent(KeyEvent.changeTimeRepeat(keyEventValueAt, SystemClock.uptimeMillis(), 0));
                }
            }
            this.mDeviceKeyEvents.clear();
            this.mJoystickAxesState.resetState();
        }

        private void update(MotionEvent motionEvent) {
            int historySize = motionEvent.getHistorySize();
            for (int i = 0; i < historySize; i++) {
                long historicalEventTime = motionEvent.getHistoricalEventTime(i);
                this.mJoystickAxesState.updateStateForAxis(motionEvent, historicalEventTime, 0, motionEvent.getHistoricalAxisValue(0, 0, i));
                this.mJoystickAxesState.updateStateForAxis(motionEvent, historicalEventTime, 1, motionEvent.getHistoricalAxisValue(1, 0, i));
                this.mJoystickAxesState.updateStateForAxis(motionEvent, historicalEventTime, 15, motionEvent.getHistoricalAxisValue(15, 0, i));
                this.mJoystickAxesState.updateStateForAxis(motionEvent, historicalEventTime, 16, motionEvent.getHistoricalAxisValue(16, 0, i));
            }
            long eventTime = motionEvent.getEventTime();
            this.mJoystickAxesState.updateStateForAxis(motionEvent, eventTime, 0, motionEvent.getAxisValue(0));
            this.mJoystickAxesState.updateStateForAxis(motionEvent, eventTime, 1, motionEvent.getAxisValue(1));
            this.mJoystickAxesState.updateStateForAxis(motionEvent, eventTime, 15, motionEvent.getAxisValue(15));
            this.mJoystickAxesState.updateStateForAxis(motionEvent, eventTime, 16, motionEvent.getAxisValue(16));
        }

        final class JoystickAxesState {
            private static final int STATE_DOWN_OR_RIGHT = 1;
            private static final int STATE_NEUTRAL = 0;
            private static final int STATE_UP_OR_LEFT = -1;
            final int[] mAxisStatesHat = {0, 0};
            final int[] mAxisStatesStick = {0, 0};

            private boolean isXAxis(int i) {
                return i == 0 || i == 15;
            }

            private boolean isYAxis(int i) {
                return i == 1 || i == 16;
            }

            private int joystickAxisValueToState(float f) {
                if (f >= 0.5f) {
                    return 1;
                }
                return f <= -0.5f ? -1 : 0;
            }

            JoystickAxesState() {
            }

            void resetState() {
                int[] iArr = this.mAxisStatesHat;
                iArr[0] = 0;
                iArr[1] = 0;
                int[] iArr2 = this.mAxisStatesStick;
                iArr2[0] = 0;
                iArr2[1] = 0;
            }

            void updateStateForAxis(MotionEvent motionEvent, long j, int i, float f) {
                float f2;
                int i2;
                char c;
                int i3;
                int iJoystickAxisAndStateToKeycode;
                int i4 = 1;
                if (isXAxis(i)) {
                    c = 0;
                    f2 = f;
                    i2 = 1;
                } else {
                    if (!isYAxis(i)) {
                        Log.e(ViewRootImpl.this.mTag, "Unexpected axis " + i + " in updateStateForAxis!");
                        return;
                    }
                    f2 = f;
                    i2 = 2;
                    c = 1;
                }
                int iJoystickAxisValueToState = joystickAxisValueToState(f2);
                if (i == 0 || i == 1) {
                    i3 = this.mAxisStatesStick[c];
                } else {
                    i3 = this.mAxisStatesHat[c];
                }
                if (i3 == iJoystickAxisValueToState) {
                    return;
                }
                int metaState = motionEvent.getMetaState();
                int deviceId = motionEvent.getDeviceId();
                int source = motionEvent.getSource();
                if (i3 == 1 || i3 == -1) {
                    int iJoystickAxisAndStateToKeycode2 = joystickAxisAndStateToKeycode(i, i3);
                    if (iJoystickAxisAndStateToKeycode2 != 0) {
                        ViewRootImpl.this.enqueueInputEvent(new KeyEvent(j, j, 1, iJoystickAxisAndStateToKeycode2, 0, metaState, deviceId, 0, 1024, source));
                        deviceId = deviceId;
                        SyntheticJoystickHandler.this.mDeviceKeyEvents.put(deviceId, null);
                    }
                    SyntheticJoystickHandler.this.removeMessages(i2);
                    i4 = 1;
                }
                if ((iJoystickAxisValueToState == i4 || iJoystickAxisValueToState == -1) && (iJoystickAxisAndStateToKeycode = joystickAxisAndStateToKeycode(i, iJoystickAxisValueToState)) != 0) {
                    int i5 = deviceId;
                    KeyEvent keyEvent = new KeyEvent(j, j, 0, iJoystickAxisAndStateToKeycode, 0, metaState, i5, 0, 1024, source);
                    ViewRootImpl.this.enqueueInputEvent(keyEvent);
                    Message messageObtainMessage = SyntheticJoystickHandler.this.obtainMessage(i2, keyEvent);
                    messageObtainMessage.setAsynchronous(true);
                    SyntheticJoystickHandler.this.sendMessageDelayed(messageObtainMessage, ViewConfiguration.getKeyRepeatTimeout());
                    SyntheticJoystickHandler.this.mDeviceKeyEvents.put(i5, new KeyEvent(j, j, 1, iJoystickAxisAndStateToKeycode, 0, metaState, i5, 0, 1056, source));
                }
                if (i == 0 || i == 1) {
                    this.mAxisStatesStick[c] = iJoystickAxisValueToState;
                } else {
                    this.mAxisStatesHat[c] = iJoystickAxisValueToState;
                }
            }

            private int joystickAxisAndStateToKeycode(int i, int i2) {
                if (isXAxis(i) && i2 == -1) {
                    return 21;
                }
                if (isXAxis(i) && i2 == 1) {
                    return 22;
                }
                if (isYAxis(i) && i2 == -1) {
                    return 19;
                }
                if (isYAxis(i) && i2 == 1) {
                    return 20;
                }
                Log.e(ViewRootImpl.this.mTag, "Unknown axis " + i + " or direction " + i2);
                return 0;
            }
        }
    }

    final class SyntheticTouchNavigationHandler extends Handler {
        private static final float DEFAULT_HEIGHT_MILLIMETERS = 48.0f;
        private static final float DEFAULT_WIDTH_MILLIMETERS = 48.0f;
        private static final float FLING_TICK_DECAY = 0.8f;
        private static final boolean LOCAL_DEBUG = false;
        private static final String LOCAL_TAG = "SyntheticTouchNavigationHandler";
        private static final float MAX_FLING_VELOCITY_TICKS_PER_SECOND = 20.0f;
        private static final float MIN_FLING_VELOCITY_TICKS_PER_SECOND = 6.0f;
        private static final int TICK_DISTANCE_MILLIMETERS = 12;
        private float mAccumulatedX;
        private float mAccumulatedY;
        private int mActivePointerId;
        private float mConfigMaxFlingVelocity;
        private float mConfigMinFlingVelocity;
        private float mConfigTickDistance;
        private boolean mConsumedMovement;
        private int mCurrentDeviceId;
        private boolean mCurrentDeviceSupported;
        private int mCurrentSource;
        private final Runnable mFlingRunnable;
        private float mFlingVelocity;
        private boolean mFlinging;
        private float mLastX;
        private float mLastY;
        private int mPendingKeyCode;
        private long mPendingKeyDownTime;
        private int mPendingKeyMetaState;
        private int mPendingKeyRepeatCount;
        private float mStartX;
        private float mStartY;
        private VelocityTracker mVelocityTracker;

        static /* synthetic */ float access$3432(SyntheticTouchNavigationHandler syntheticTouchNavigationHandler, float f) {
            float f2 = syntheticTouchNavigationHandler.mFlingVelocity * f;
            syntheticTouchNavigationHandler.mFlingVelocity = f2;
            return f2;
        }

        public SyntheticTouchNavigationHandler() {
            super(true);
            this.mCurrentDeviceId = -1;
            this.mActivePointerId = -1;
            this.mPendingKeyCode = 0;
            this.mFlingRunnable = new Runnable() { // from class: android.view.ViewRootImpl.SyntheticTouchNavigationHandler.1
                @Override // java.lang.Runnable
                public void run() {
                    long jUptimeMillis = SystemClock.uptimeMillis();
                    SyntheticTouchNavigationHandler syntheticTouchNavigationHandler = SyntheticTouchNavigationHandler.this;
                    syntheticTouchNavigationHandler.sendKeyDownOrRepeat(jUptimeMillis, syntheticTouchNavigationHandler.mPendingKeyCode, SyntheticTouchNavigationHandler.this.mPendingKeyMetaState);
                    SyntheticTouchNavigationHandler.access$3432(SyntheticTouchNavigationHandler.this, SyntheticTouchNavigationHandler.FLING_TICK_DECAY);
                    if (SyntheticTouchNavigationHandler.this.postFling(jUptimeMillis)) {
                        return;
                    }
                    SyntheticTouchNavigationHandler.this.mFlinging = false;
                    SyntheticTouchNavigationHandler.this.finishKeys(jUptimeMillis);
                }
            };
        }

        public void process(MotionEvent motionEvent) {
            long eventTime = motionEvent.getEventTime();
            int deviceId = motionEvent.getDeviceId();
            int source = motionEvent.getSource();
            if (this.mCurrentDeviceId != deviceId || this.mCurrentSource != source) {
                finishKeys(eventTime);
                finishTracking(eventTime);
                this.mCurrentDeviceId = deviceId;
                this.mCurrentSource = source;
                this.mCurrentDeviceSupported = false;
                InputDevice device = motionEvent.getDevice();
                if (device != null) {
                    InputDevice.MotionRange motionRange = device.getMotionRange(0);
                    InputDevice.MotionRange motionRange2 = device.getMotionRange(1);
                    if (motionRange != null && motionRange2 != null) {
                        this.mCurrentDeviceSupported = true;
                        float resolution = motionRange.getResolution();
                        if (resolution <= 0.0f) {
                            resolution = motionRange.getRange() / 48.0f;
                        }
                        float resolution2 = motionRange2.getResolution();
                        if (resolution2 <= 0.0f) {
                            resolution2 = motionRange2.getRange() / 48.0f;
                        }
                        float f = (resolution + resolution2) * 0.5f * 12.0f;
                        this.mConfigTickDistance = f;
                        this.mConfigMinFlingVelocity = MIN_FLING_VELOCITY_TICKS_PER_SECOND * f;
                        this.mConfigMaxFlingVelocity = f * MAX_FLING_VELOCITY_TICKS_PER_SECOND;
                    }
                }
            }
            if (this.mCurrentDeviceSupported) {
                int actionMasked = motionEvent.getActionMasked();
                if (actionMasked == 0) {
                    boolean z = this.mFlinging;
                    finishKeys(eventTime);
                    finishTracking(eventTime);
                    this.mActivePointerId = motionEvent.getPointerId(0);
                    VelocityTracker velocityTrackerObtain = VelocityTracker.obtain();
                    this.mVelocityTracker = velocityTrackerObtain;
                    velocityTrackerObtain.addMovement(motionEvent);
                    this.mStartX = motionEvent.getX();
                    float y = motionEvent.getY();
                    this.mStartY = y;
                    this.mLastX = this.mStartX;
                    this.mLastY = y;
                    this.mAccumulatedX = 0.0f;
                    this.mAccumulatedY = 0.0f;
                    this.mConsumedMovement = z;
                    return;
                }
                if (actionMasked != 1 && actionMasked != 2) {
                    if (actionMasked != 3) {
                        return;
                    }
                    finishKeys(eventTime);
                    finishTracking(eventTime);
                    return;
                }
                int i = this.mActivePointerId;
                if (i < 0) {
                    return;
                }
                int iFindPointerIndex = motionEvent.findPointerIndex(i);
                if (iFindPointerIndex < 0) {
                    finishKeys(eventTime);
                    finishTracking(eventTime);
                    return;
                }
                this.mVelocityTracker.addMovement(motionEvent);
                float x = motionEvent.getX(iFindPointerIndex);
                float y2 = motionEvent.getY(iFindPointerIndex);
                this.mAccumulatedX += x - this.mLastX;
                this.mAccumulatedY += y2 - this.mLastY;
                this.mLastX = x;
                this.mLastY = y2;
                consumeAccumulatedMovement(eventTime, motionEvent.getMetaState());
                if (actionMasked == 1) {
                    if (this.mConsumedMovement && this.mPendingKeyCode != 0) {
                        this.mVelocityTracker.computeCurrentVelocity(1000, this.mConfigMaxFlingVelocity);
                        if (!startFling(eventTime, this.mVelocityTracker.getXVelocity(this.mActivePointerId), this.mVelocityTracker.getYVelocity(this.mActivePointerId))) {
                            finishKeys(eventTime);
                        }
                    }
                    finishTracking(eventTime);
                }
            }
        }

        public void cancel(MotionEvent motionEvent) {
            if (this.mCurrentDeviceId == motionEvent.getDeviceId() && this.mCurrentSource == motionEvent.getSource()) {
                long eventTime = motionEvent.getEventTime();
                finishKeys(eventTime);
                finishTracking(eventTime);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void finishKeys(long j) {
            cancelFling();
            sendKeyUp(j);
        }

        private void finishTracking(long j) {
            if (this.mActivePointerId >= 0) {
                this.mActivePointerId = -1;
                this.mVelocityTracker.recycle();
                this.mVelocityTracker = null;
            }
        }

        private void consumeAccumulatedMovement(long j, int i) {
            float fAbs = Math.abs(this.mAccumulatedX);
            float fAbs2 = Math.abs(this.mAccumulatedY);
            if (fAbs >= fAbs2) {
                if (fAbs >= this.mConfigTickDistance) {
                    this.mAccumulatedX = consumeAccumulatedMovement(j, i, this.mAccumulatedX, 21, 22);
                    this.mAccumulatedY = 0.0f;
                    this.mConsumedMovement = true;
                    return;
                }
                return;
            }
            if (fAbs2 >= this.mConfigTickDistance) {
                this.mAccumulatedY = consumeAccumulatedMovement(j, i, this.mAccumulatedY, 19, 20);
                this.mAccumulatedX = 0.0f;
                this.mConsumedMovement = true;
            }
        }

        private float consumeAccumulatedMovement(long j, int i, float f, int i2, int i3) {
            while (f <= (-this.mConfigTickDistance)) {
                sendKeyDownOrRepeat(j, i2, i);
                f += this.mConfigTickDistance;
            }
            while (f >= this.mConfigTickDistance) {
                sendKeyDownOrRepeat(j, i3, i);
                f -= this.mConfigTickDistance;
            }
            return f;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void sendKeyDownOrRepeat(long j, int i, int i2) {
            if (this.mPendingKeyCode != i) {
                sendKeyUp(j);
                this.mPendingKeyDownTime = j;
                this.mPendingKeyCode = i;
                this.mPendingKeyRepeatCount = 0;
            } else {
                this.mPendingKeyRepeatCount++;
            }
            this.mPendingKeyMetaState = i2;
            ViewRootImpl.this.enqueueInputEvent(new KeyEvent(this.mPendingKeyDownTime, j, 0, this.mPendingKeyCode, this.mPendingKeyRepeatCount, this.mPendingKeyMetaState, this.mCurrentDeviceId, 1024, this.mCurrentSource));
        }

        private void sendKeyUp(long j) {
            if (this.mPendingKeyCode != 0) {
                ViewRootImpl.this.enqueueInputEvent(new KeyEvent(this.mPendingKeyDownTime, j, 1, this.mPendingKeyCode, 0, this.mPendingKeyMetaState, this.mCurrentDeviceId, 0, 1024, this.mCurrentSource));
                this.mPendingKeyCode = 0;
            }
        }

        private boolean startFling(long j, float f, float f2) {
            switch (this.mPendingKeyCode) {
                case 19:
                    float f3 = -f2;
                    if (f3 < this.mConfigMinFlingVelocity || Math.abs(f) >= this.mConfigMinFlingVelocity) {
                        return false;
                    }
                    this.mFlingVelocity = f3;
                    break;
                case 20:
                    if (f2 < this.mConfigMinFlingVelocity || Math.abs(f) >= this.mConfigMinFlingVelocity) {
                        return false;
                    }
                    this.mFlingVelocity = f2;
                    break;
                case 21:
                    float f4 = -f;
                    if (f4 < this.mConfigMinFlingVelocity || Math.abs(f2) >= this.mConfigMinFlingVelocity) {
                        return false;
                    }
                    this.mFlingVelocity = f4;
                    break;
                case 22:
                    if (f < this.mConfigMinFlingVelocity || Math.abs(f2) >= this.mConfigMinFlingVelocity) {
                        return false;
                    }
                    this.mFlingVelocity = f;
                    break;
            }
            boolean zPostFling = postFling(j);
            this.mFlinging = zPostFling;
            return zPostFling;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean postFling(long j) {
            float f = this.mFlingVelocity;
            if (f < this.mConfigMinFlingVelocity) {
                return false;
            }
            postAtTime(this.mFlingRunnable, j + ((long) ((this.mConfigTickDistance / f) * 1000.0f)));
            return true;
        }

        private void cancelFling() {
            if (this.mFlinging) {
                removeCallbacks(this.mFlingRunnable);
                this.mFlinging = false;
            }
        }
    }

    final class SyntheticKeyboardHandler {
        SyntheticKeyboardHandler() {
        }

        public void process(KeyEvent keyEvent) {
            KeyCharacterMap.FallbackAction fallbackAction;
            if ((keyEvent.getFlags() & 1024) == 0 && (fallbackAction = keyEvent.getKeyCharacterMap().getFallbackAction(keyEvent.getKeyCode(), keyEvent.getMetaState())) != null) {
                KeyEvent keyEventObtain = KeyEvent.obtain(keyEvent.getDownTime(), keyEvent.getEventTime(), keyEvent.getAction(), fallbackAction.keyCode, keyEvent.getRepeatCount(), fallbackAction.metaState, keyEvent.getDeviceId(), keyEvent.getScanCode(), keyEvent.getFlags() | 1024, keyEvent.getSource(), null);
                fallbackAction.recycle();
                ViewRootImpl.this.enqueueInputEvent(keyEventObtain);
            }
        }
    }

    private static boolean isNavigationKey(KeyEvent keyEvent) {
        int keyCode = keyEvent.getKeyCode();
        if (keyCode == 61 || keyCode == 62 || keyCode == 66 || keyCode == 92 || keyCode == 93 || keyCode == 122 || keyCode == 123) {
            return true;
        }
        switch (keyCode) {
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
                return true;
            default:
                return false;
        }
    }

    private static boolean isTypingKey(KeyEvent keyEvent) {
        return keyEvent.getUnicodeChar() > 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean checkForLeavingTouchModeAndConsume(KeyEvent keyEvent) {
        if (!this.mAttachInfo.mInTouchMode) {
            return false;
        }
        int action = keyEvent.getAction();
        if ((action != 0 && action != 2) || (keyEvent.getFlags() & 4) != 0) {
            return false;
        }
        if (isNavigationKey(keyEvent)) {
            return ensureTouchMode(false);
        }
        if (isTypingKey(keyEvent)) {
            ensureTouchMode(false);
        }
        return false;
    }

    void setLocalDragState(Object obj) {
        this.mLocalDragState = obj;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleDragEvent(DragEvent dragEvent) {
        if (this.mView != null && this.mAdded) {
            int i = dragEvent.mAction;
            if (i == 1) {
                this.mCurrentDragView = null;
                this.mDragDescription = dragEvent.mClipDescription;
            } else {
                if (i == 4) {
                    this.mDragDescription = null;
                }
                dragEvent.mClipDescription = this.mDragDescription;
            }
            if (i == 6) {
                if (View.sCascadedDragDrop) {
                    this.mView.dispatchDragEnterExitInPreN(dragEvent);
                }
                setDragFocus(null, dragEvent);
            } else {
                if (i == 2 || i == 3) {
                    this.mDragPoint.set(dragEvent.mX, dragEvent.mY);
                    CompatibilityInfo.Translator translator = this.mTranslator;
                    if (translator != null) {
                        translator.translatePointInScreenToAppWindow(this.mDragPoint);
                    }
                    int i2 = this.mCurScrollY;
                    if (i2 != 0) {
                        this.mDragPoint.offset(0.0f, i2);
                    }
                    dragEvent.mX = this.mDragPoint.x;
                    dragEvent.mY = this.mDragPoint.y;
                }
                View view = this.mCurrentDragView;
                if (i == 3 && dragEvent.mClipData != null) {
                    dragEvent.mClipData.prepareToEnterProcess();
                }
                boolean zDispatchDragEvent = this.mView.dispatchDragEvent(dragEvent);
                if (i == 2 && !dragEvent.mEventHandlerWasCalled) {
                    setDragFocus(null, dragEvent);
                }
                if (view != this.mCurrentDragView) {
                    if (view != null) {
                        try {
                            this.mWindowSession.dragRecipientExited(this.mWindow);
                        } catch (RemoteException unused) {
                            Slog.e(this.mTag, "Unable to note drag target change");
                        }
                    }
                    if (this.mCurrentDragView != null) {
                        this.mWindowSession.dragRecipientEntered(this.mWindow);
                    }
                }
                if (i == 3) {
                    try {
                        Log.i(this.mTag, "Reporting drop result: " + zDispatchDragEvent);
                        this.mWindowSession.reportDropResult(this.mWindow, zDispatchDragEvent);
                    } catch (RemoteException unused2) {
                        Log.e(this.mTag, "Unable to report drop result");
                    }
                }
                if (i == 4) {
                    this.mCurrentDragView = null;
                    setLocalDragState(null);
                    this.mAttachInfo.mDragToken = null;
                    if (this.mAttachInfo.mDragSurface != null) {
                        this.mAttachInfo.mDragSurface.release();
                        this.mAttachInfo.mDragSurface = null;
                    }
                }
            }
        }
        dragEvent.recycle();
    }

    public void onWindowTitleChanged() {
        this.mAttachInfo.mForceReportNewAttributes = true;
    }

    public void handleDispatchWindowShown() {
        this.mAttachInfo.mTreeObserver.dispatchOnWindowShown();
    }

    public void handleRequestKeyboardShortcuts(IResultReceiver iResultReceiver, int i) {
        Bundle bundle = new Bundle();
        ArrayList<? extends Parcelable> arrayList = new ArrayList<>();
        View view = this.mView;
        if (view != null) {
            view.requestKeyboardShortcuts(arrayList, i);
        }
        bundle.putParcelableArrayList(WindowManager.PARCEL_KEY_SHORTCUTS_ARRAY, arrayList);
        try {
            iResultReceiver.send(0, bundle);
        } catch (RemoteException unused) {
        }
    }

    public void getLastTouchPoint(Point point) {
        point.x = (int) this.mLastTouchPoint.x;
        point.y = (int) this.mLastTouchPoint.y;
    }

    public int getLastTouchSource() {
        return this.mLastTouchSource;
    }

    public void setDragFocus(View view, DragEvent dragEvent) {
        if (this.mCurrentDragView != view && !View.sCascadedDragDrop) {
            float f = dragEvent.mX;
            float f2 = dragEvent.mY;
            int i = dragEvent.mAction;
            ClipData clipData = dragEvent.mClipData;
            dragEvent.mX = 0.0f;
            dragEvent.mY = 0.0f;
            dragEvent.mClipData = null;
            if (this.mCurrentDragView != null) {
                dragEvent.mAction = 6;
                this.mCurrentDragView.callDragEventHandler(dragEvent);
            }
            if (view != null) {
                dragEvent.mAction = 5;
                view.callDragEventHandler(dragEvent);
            }
            dragEvent.mAction = i;
            dragEvent.mX = f;
            dragEvent.mY = f2;
            dragEvent.mClipData = clipData;
        }
        this.mCurrentDragView = view;
    }

    private AudioManager getAudioManager() {
        View view = this.mView;
        if (view == null) {
            throw new IllegalStateException("getAudioManager called when there is no mView");
        }
        if (this.mAudioManager == null) {
            this.mAudioManager = (AudioManager) view.getContext().getSystemService("audio");
        }
        return this.mAudioManager;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public AutofillManager getAutofillManager() {
        View view = this.mView;
        if (!(view instanceof ViewGroup)) {
            return null;
        }
        ViewGroup viewGroup = (ViewGroup) view;
        if (viewGroup.getChildCount() > 0) {
            return (AutofillManager) viewGroup.getChildAt(0).getContext().getSystemService(AutofillManager.class);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isAutofillUiShowing() {
        AutofillManager autofillManager = getAutofillManager();
        if (autofillManager == null) {
            return false;
        }
        return autofillManager.isAutofillUiShowing();
    }

    public AccessibilityInteractionController getAccessibilityInteractionController() {
        if (this.mView == null) {
            throw new IllegalStateException("getAccessibilityInteractionController called when there is no mView");
        }
        if (this.mAccessibilityInteractionController == null) {
            this.mAccessibilityInteractionController = new AccessibilityInteractionController(this);
        }
        return this.mAccessibilityInteractionController;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private int relayoutWindow(WindowManager.LayoutParams layoutParams, int i, boolean z) throws RemoteException {
        byte b;
        float f = this.mAttachInfo.mApplicationScale;
        if (layoutParams == null || this.mTranslator == null) {
            b = false;
        } else {
            layoutParams.backup();
            this.mTranslator.translateWindowLayout(layoutParams);
            b = true;
        }
        if (layoutParams != null && this.mOrigWindowType != layoutParams.type && this.mTargetSdkVersion < 14) {
            Slog.w(this.mTag, "Window type can not be changed after the window is added; ignoring change of " + this.mView);
            layoutParams.type = this.mOrigWindowType;
        }
        long nextFrameNumber = this.mSurface.isValid() ? this.mSurface.getNextFrameNumber() : -1L;
        Rect rect = this.mTmpFrame;
        Rect rect2 = this.mTmpRect;
        int iRelayout = this.mWindowSession.relayout(this.mWindow, this.mSeq, layoutParams, (int) ((this.mView.getMeasuredWidth() * f) + 0.5f), (int) ((this.mView.getMeasuredHeight() * f) + 0.5f), i, z ? 1 : 0, nextFrameNumber, rect, rect2, rect2, rect2, this.mPendingBackDropFrame, this.mPendingDisplayCutout, this.mPendingMergedConfiguration, this.mSurfaceControl, this.mTempInsets, this.mTempControls, this.mSurfaceSize, this.mBlastSurfaceControl);
        if (this.mSurfaceControl.isValid()) {
            if (!useBLAST()) {
                this.mSurface.copyFrom(this.mSurfaceControl);
            } else {
                Surface orCreateBLASTSurface = getOrCreateBLASTSurface(this.mSurfaceSize.x, this.mSurfaceSize.y);
                if (orCreateBLASTSurface != null) {
                    this.mSurface.transferFrom(orCreateBLASTSurface);
                }
            }
        } else {
            destroySurface();
        }
        this.mPendingAlwaysConsumeSystemBars = (iRelayout & 64) != 0;
        if (b != false) {
            layoutParams.restore();
        }
        CompatibilityInfo.Translator translator = this.mTranslator;
        if (translator != null) {
            translator.translateRectInScreenToAppWinFrame(this.mTmpFrame);
        }
        setFrame(this.mTmpFrame);
        this.mInsetsController.onStateChanged(this.mTempInsets);
        this.mInsetsController.onControlsChanged(this.mTempControls);
        return iRelayout;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setFrame(Rect rect) {
        this.mWinFrame.set(rect);
        this.mInsetsController.onFrameChanged(rect);
    }

    @Override // android.view.View.AttachInfo.Callbacks
    public void playSoundEffect(int i) {
        checkThread();
        try {
            AudioManager audioManager = getAudioManager();
            if (i == 0) {
                audioManager.playSoundEffect(0);
                return;
            }
            if (i == 1) {
                audioManager.playSoundEffect(3);
                return;
            }
            if (i == 2) {
                audioManager.playSoundEffect(1);
                return;
            }
            if (i == 3) {
                audioManager.playSoundEffect(4);
                return;
            }
            if (i == 4) {
                audioManager.playSoundEffect(2);
                return;
            }
            throw new IllegalArgumentException("unknown effect id " + i + " not defined in " + SoundEffectConstants.class.getCanonicalName());
        } catch (IllegalStateException e) {
            Log.e(this.mTag, "FATAL EXCEPTION when attempting to play sound effect: " + e);
            e.printStackTrace();
        }
    }

    @Override // android.view.View.AttachInfo.Callbacks
    public boolean performHapticFeedback(int i, boolean z) {
        try {
            return this.mWindowSession.performHapticFeedback(i, z);
        } catch (RemoteException unused) {
            return false;
        }
    }

    @Override // android.view.ViewParent
    public View focusSearch(View view, int i) {
        checkThread();
        if (this.mView instanceof ViewGroup) {
            return FocusFinder.getInstance().findNextFocus((ViewGroup) this.mView, view, i);
        }
        return null;
    }

    @Override // android.view.ViewParent
    public View keyboardNavigationClusterSearch(View view, int i) {
        checkThread();
        return FocusFinder.getInstance().findNextKeyboardNavigationCluster(this.mView, view, i);
    }

    public void debug() {
        this.mView.debug();
    }

    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        String str2 = str + "  ";
        printWriter.print(str);
        printWriter.println("ViewRoot:");
        printWriter.print(str2);
        printWriter.print("mAdded=");
        printWriter.print(this.mAdded);
        printWriter.print(" mRemoved=");
        printWriter.println(this.mRemoved);
        printWriter.print(str2);
        printWriter.print("mConsumeBatchedInputScheduled=");
        printWriter.println(this.mConsumeBatchedInputScheduled);
        printWriter.print(str2);
        printWriter.print("mConsumeBatchedInputImmediatelyScheduled=");
        printWriter.println(this.mConsumeBatchedInputImmediatelyScheduled);
        printWriter.print(str2);
        printWriter.print("mPendingInputEventCount=");
        printWriter.println(this.mPendingInputEventCount);
        printWriter.print(str2);
        printWriter.print("mProcessInputEventsScheduled=");
        printWriter.println(this.mProcessInputEventsScheduled);
        printWriter.print(str2);
        printWriter.print("mTraversalScheduled=");
        printWriter.print(this.mTraversalScheduled);
        printWriter.print(str2);
        printWriter.print("mIsAmbientMode=");
        printWriter.print(this.mIsAmbientMode);
        printWriter.print(str2);
        printWriter.print("mUnbufferedInputSource=");
        printWriter.print(Integer.toHexString(this.mUnbufferedInputSource));
        if (this.mTraversalScheduled) {
            printWriter.print(" (barrier=");
            printWriter.print(this.mTraversalBarrier);
            printWriter.println(")");
        } else {
            printWriter.println();
        }
        this.mFirstInputStage.dump(str2, printWriter);
        this.mChoreographer.dump(str, printWriter);
        this.mInsetsController.dump(str, printWriter);
        printWriter.print(str);
        printWriter.println("View Hierarchy:");
        dumpViewHierarchy(str2, printWriter, this.mView);
    }

    private void dumpViewHierarchy(String str, PrintWriter printWriter, View view) {
        ViewGroup viewGroup;
        int childCount;
        printWriter.print(str);
        if (view == null) {
            printWriter.println("null");
            return;
        }
        printWriter.println(view.toString());
        if ((view instanceof ViewGroup) && (childCount = (viewGroup = (ViewGroup) view).getChildCount()) > 0) {
            String str2 = str + "  ";
            for (int i = 0; i < childCount; i++) {
                dumpViewHierarchy(str2, printWriter, viewGroup.getChildAt(i));
            }
        }
    }

    static final class GfxInfo {
        public long renderNodeMemoryAllocated;
        public long renderNodeMemoryUsage;
        public int viewCount;

        GfxInfo() {
        }

        void add(GfxInfo gfxInfo) {
            this.viewCount += gfxInfo.viewCount;
            this.renderNodeMemoryUsage += gfxInfo.renderNodeMemoryUsage;
            this.renderNodeMemoryAllocated += gfxInfo.renderNodeMemoryAllocated;
        }
    }

    GfxInfo getGfxInfo() {
        GfxInfo gfxInfo = new GfxInfo();
        View view = this.mView;
        if (view != null) {
            appendGfxInfo(view, gfxInfo);
        }
        return gfxInfo;
    }

    private static void computeRenderNodeUsage(RenderNode renderNode, GfxInfo gfxInfo) {
        if (renderNode == null) {
            return;
        }
        gfxInfo.renderNodeMemoryUsage += renderNode.computeApproximateMemoryUsage();
        gfxInfo.renderNodeMemoryAllocated += renderNode.computeApproximateMemoryAllocated();
    }

    private static void appendGfxInfo(View view, GfxInfo gfxInfo) {
        gfxInfo.viewCount++;
        computeRenderNodeUsage(view.mRenderNode, gfxInfo);
        computeRenderNodeUsage(view.mBackgroundRenderNode, gfxInfo);
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                appendGfxInfo(viewGroup.getChildAt(i), gfxInfo);
            }
        }
    }

    boolean die(boolean z) {
        if (z && !this.mIsInTraversal) {
            doDie();
            return false;
        }
        if (!this.mIsDrawing) {
            destroyHardwareRenderer();
        } else {
            Log.e(this.mTag, "Attempting to destroy the window while drawing!\n  window=" + this + ", title=" + ((Object) this.mWindowAttributes.getTitle()));
        }
        this.mHandler.sendEmptyMessage(3);
        return true;
    }

    void doDie() {
        checkThread();
        synchronized (this) {
            if (this.mRemoved) {
                return;
            }
            boolean z = true;
            this.mRemoved = true;
            if (this.mAdded) {
                dispatchDetachedFromWindow();
            }
            if (this.mAdded && !this.mFirst) {
                destroyHardwareRenderer();
                if (this.mView != null) {
                    int visibility = this.mView.getVisibility();
                    if (this.mViewVisibility == visibility) {
                        z = false;
                    }
                    if (this.mWindowAttributesChanged || z) {
                        try {
                            if ((relayoutWindow(this.mWindowAttributes, visibility, false) & 2) != 0) {
                                this.mWindowSession.finishDrawing(this.mWindow, null);
                            }
                        } catch (RemoteException unused) {
                        }
                    }
                    destroySurface();
                }
            }
            this.mAdded = false;
            WindowManagerGlobal.getInstance().doRemoveView(this);
        }
    }

    public void requestUpdateConfiguration(Configuration configuration) {
        this.mHandler.sendMessage(this.mHandler.obtainMessage(18, configuration));
    }

    public void loadSystemProperties() {
        this.mHandler.post(new Runnable() { // from class: android.view.ViewRootImpl.4
            @Override // java.lang.Runnable
            public void run() {
                ViewRootImpl.this.mProfileRendering = SystemProperties.getBoolean(ViewRootImpl.PROPERTY_PROFILE_RENDERING, false);
                ViewRootImpl viewRootImpl = ViewRootImpl.this;
                viewRootImpl.profileRendering(viewRootImpl.mAttachInfo.mHasWindowFocus);
                if (ViewRootImpl.this.mAttachInfo.mThreadedRenderer != null && ViewRootImpl.this.mAttachInfo.mThreadedRenderer.loadSystemProperties()) {
                    ViewRootImpl.this.invalidate();
                }
                boolean zBooleanValue = DisplayProperties.debug_layout().orElse(false).booleanValue();
                if (zBooleanValue != ViewRootImpl.this.mAttachInfo.mDebugLayout) {
                    ViewRootImpl.this.mAttachInfo.mDebugLayout = zBooleanValue;
                    if (ViewRootImpl.this.mHandler.hasMessages(22)) {
                        return;
                    }
                    ViewRootImpl.this.mHandler.sendEmptyMessageDelayed(22, 200L);
                }
            }
        });
    }

    private void destroyHardwareRenderer() {
        ThreadedRenderer threadedRenderer = this.mAttachInfo.mThreadedRenderer;
        if (threadedRenderer != null) {
            View view = this.mView;
            if (view != null) {
                threadedRenderer.destroyHardwareResources(view);
            }
            threadedRenderer.destroy();
            threadedRenderer.setRequested(false);
            this.mAttachInfo.mThreadedRenderer = null;
            this.mAttachInfo.mHardwareAccelerated = false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispatchResized(Rect rect, Rect rect2, Rect rect3, Rect rect4, boolean z, MergedConfiguration mergedConfiguration, Rect rect5, boolean z2, boolean z3, int i, DisplayCutout.ParcelableWrapper parcelableWrapper) {
        Rect rect6 = rect4;
        MergedConfiguration mergedConfiguration2 = mergedConfiguration;
        Rect rect7 = rect5;
        if (this.mDragResizing && this.mUseMTRenderer) {
            boolean zEquals = rect.equals(rect7);
            synchronized (this.mWindowCallbacks) {
                for (int size = this.mWindowCallbacks.size() - 1; size >= 0; size--) {
                    this.mWindowCallbacks.get(size).onWindowSizeIsChanging(rect7, zEquals, rect3, rect6);
                }
            }
        }
        Message messageObtainMessage = this.mHandler.obtainMessage(z ? 5 : 4);
        CompatibilityInfo.Translator translator = this.mTranslator;
        if (translator != null) {
            translator.translateRectInScreenToAppWindow(rect);
            this.mTranslator.translateRectInScreenToAppWindow(rect2);
            this.mTranslator.translateRectInScreenToAppWindow(rect3);
        }
        SomeArgs someArgsObtain = SomeArgs.obtain();
        boolean z4 = Binder.getCallingPid() == Process.myPid();
        someArgsObtain.arg1 = z4 ? new Rect(rect) : rect;
        someArgsObtain.arg2 = z4 ? new Rect(rect2) : rect2;
        someArgsObtain.arg3 = z4 ? new Rect(rect3) : rect3;
        if (z4 && mergedConfiguration2 != null) {
            mergedConfiguration2 = new MergedConfiguration(mergedConfiguration2);
        }
        someArgsObtain.arg4 = mergedConfiguration2;
        if (z4) {
            rect6 = new Rect(rect6);
        }
        someArgsObtain.arg6 = rect6;
        if (z4) {
            rect7 = new Rect(rect7);
        }
        someArgsObtain.arg8 = rect7;
        someArgsObtain.arg9 = parcelableWrapper.get();
        someArgsObtain.argi1 = z2 ? 1 : 0;
        someArgsObtain.argi2 = z3 ? 1 : 0;
        someArgsObtain.argi3 = i;
        messageObtainMessage.obj = someArgsObtain;
        this.mHandler.sendMessage(messageObtainMessage);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispatchInsetsChanged(InsetsState insetsState) {
        if (Binder.getCallingPid() == Process.myPid()) {
            insetsState = new InsetsState(insetsState, true);
        }
        this.mHandler.obtainMessage(30, insetsState).sendToTarget();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispatchInsetsControlChanged(InsetsState insetsState, InsetsSourceControl[] insetsSourceControlArr) {
        if (Binder.getCallingPid() == Process.myPid()) {
            InsetsState insetsState2 = new InsetsState(insetsState, true);
            if (insetsSourceControlArr != null) {
                for (int length = insetsSourceControlArr.length - 1; length >= 0; length--) {
                    insetsSourceControlArr[length] = new InsetsSourceControl(insetsSourceControlArr[length]);
                }
            }
            insetsState = insetsState2;
        }
        SomeArgs someArgsObtain = SomeArgs.obtain();
        someArgsObtain.arg1 = insetsState;
        someArgsObtain.arg2 = insetsSourceControlArr;
        this.mHandler.obtainMessage(31, someArgsObtain).sendToTarget();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showInsets(int i, boolean z) {
        this.mHandler.obtainMessage(34, i, z ? 1 : 0).sendToTarget();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void hideInsets(int i, boolean z) {
        this.mHandler.obtainMessage(35, i, z ? 1 : 0).sendToTarget();
    }

    public void dispatchMoved(int i, int i2) {
        if (this.mTranslator != null) {
            PointF pointF = new PointF(i, i2);
            this.mTranslator.translatePointInScreenToAppWindow(pointF);
            i = (int) (((double) pointF.x) + 0.5d);
            i2 = (int) (((double) pointF.y) + 0.5d);
        }
        this.mHandler.sendMessage(this.mHandler.obtainMessage(23, i, i2));
    }

    private static final class QueuedInputEvent {
        public static final int FLAG_DEFERRED = 2;
        public static final int FLAG_DELIVER_POST_IME = 1;
        public static final int FLAG_FINISHED = 4;
        public static final int FLAG_FINISHED_HANDLED = 8;
        public static final int FLAG_MODIFIED_FOR_COMPATIBILITY = 64;
        public static final int FLAG_RESYNTHESIZED = 16;
        public static final int FLAG_UNHANDLED = 32;
        public InputEvent mEvent;
        public int mFlags;
        public QueuedInputEvent mNext;
        public InputEventReceiver mReceiver;

        private QueuedInputEvent() {
        }

        public boolean shouldSkipIme() {
            if ((this.mFlags & 1) != 0) {
                return true;
            }
            InputEvent inputEvent = this.mEvent;
            return (inputEvent instanceof MotionEvent) && (inputEvent.isFromSource(2) || this.mEvent.isFromSource(4194304));
        }

        public boolean shouldSendToSynthesizer() {
            return (this.mFlags & 32) != 0;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder("QueuedInputEvent{flags=");
            if (!flagToString("UNHANDLED", 32, flagToString("RESYNTHESIZED", 16, flagToString("FINISHED_HANDLED", 8, flagToString("FINISHED", 4, flagToString("DEFERRED", 2, flagToString("DELIVER_POST_IME", 1, false, sb), sb), sb), sb), sb), sb)) {
                sb.append(AudioSystem.LEGACY_REMOTE_SUBMIX_ADDRESS);
            }
            StringBuilder sb2 = new StringBuilder();
            sb2.append(", hasNextQueuedEvent=");
            sb2.append(this.mEvent != null ? "true" : "false");
            sb.append(sb2.toString());
            StringBuilder sb3 = new StringBuilder();
            sb3.append(", hasInputEventReceiver=");
            sb3.append(this.mReceiver == null ? "false" : "true");
            sb.append(sb3.toString());
            sb.append(", mEvent=" + this.mEvent + "}");
            return sb.toString();
        }

        private boolean flagToString(String str, int i, boolean z, StringBuilder sb) {
            if ((i & this.mFlags) == 0) {
                return z;
            }
            if (z) {
                sb.append("|");
            }
            sb.append(str);
            return true;
        }
    }

    private QueuedInputEvent obtainQueuedInputEvent(InputEvent inputEvent, InputEventReceiver inputEventReceiver, int i) {
        QueuedInputEvent queuedInputEvent = this.mQueuedInputEventPool;
        if (queuedInputEvent != null) {
            this.mQueuedInputEventPoolSize--;
            this.mQueuedInputEventPool = queuedInputEvent.mNext;
            queuedInputEvent.mNext = null;
        } else {
            queuedInputEvent = new QueuedInputEvent();
        }
        queuedInputEvent.mEvent = inputEvent;
        queuedInputEvent.mReceiver = inputEventReceiver;
        queuedInputEvent.mFlags = i;
        return queuedInputEvent;
    }

    private void recycleQueuedInputEvent(QueuedInputEvent queuedInputEvent) {
        queuedInputEvent.mEvent = null;
        queuedInputEvent.mReceiver = null;
        int i = this.mQueuedInputEventPoolSize;
        if (i < 10) {
            this.mQueuedInputEventPoolSize = i + 1;
            queuedInputEvent.mNext = this.mQueuedInputEventPool;
            this.mQueuedInputEventPool = queuedInputEvent;
        }
    }

    void enqueueInputEvent(InputEvent inputEvent) {
        enqueueInputEvent(inputEvent, null, 0, false);
    }

    void enqueueInputEvent(InputEvent inputEvent, InputEventReceiver inputEventReceiver, int i, boolean z) {
        QueuedInputEvent queuedInputEventObtainQueuedInputEvent = obtainQueuedInputEvent(inputEvent, inputEventReceiver, i);
        QueuedInputEvent queuedInputEvent = this.mPendingInputEventTail;
        if (queuedInputEvent == null) {
            this.mPendingInputEventHead = queuedInputEventObtainQueuedInputEvent;
            this.mPendingInputEventTail = queuedInputEventObtainQueuedInputEvent;
        } else {
            queuedInputEvent.mNext = queuedInputEventObtainQueuedInputEvent;
            this.mPendingInputEventTail = queuedInputEventObtainQueuedInputEvent;
        }
        int i2 = this.mPendingInputEventCount + 1;
        this.mPendingInputEventCount = i2;
        Trace.traceCounter(4L, this.mPendingInputEventQueueLengthCounterName, i2);
        if (z) {
            doProcessInputEvents();
        } else {
            scheduleProcessInputEvents();
        }
    }

    private void scheduleProcessInputEvents() {
        if (this.mProcessInputEventsScheduled) {
            return;
        }
        this.mProcessInputEventsScheduled = true;
        Message messageObtainMessage = this.mHandler.obtainMessage(19);
        messageObtainMessage.setAsynchronous(true);
        this.mHandler.sendMessage(messageObtainMessage);
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0038  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    void doProcessInputEvents() {
        /*
            r6 = this;
        L0:
            android.view.ViewRootImpl$QueuedInputEvent r0 = r6.mPendingInputEventHead
            r1 = 0
            if (r0 == 0) goto L44
            android.view.ViewRootImpl$QueuedInputEvent r2 = r0.mNext
            r6.mPendingInputEventHead = r2
            r3 = 0
            if (r2 != 0) goto Le
            r6.mPendingInputEventTail = r3
        Le:
            r0.mNext = r3
            int r2 = r6.mPendingInputEventCount
            int r2 = r2 + (-1)
            r6.mPendingInputEventCount = r2
            r3 = 4
            java.lang.String r5 = r6.mPendingInputEventQueueLengthCounterName
            android.os.Trace.traceCounter(r3, r5, r2)
            android.view.InputEvent r2 = r0.mEvent
            long r2 = r2.getEventTimeNano()
            android.view.InputEvent r4 = r0.mEvent
            boolean r4 = r4 instanceof android.view.MotionEvent
            if (r4 == 0) goto L38
            android.view.InputEvent r4 = r0.mEvent
            android.view.MotionEvent r4 = (android.view.MotionEvent) r4
            int r5 = r4.getHistorySize()
            if (r5 <= 0) goto L38
            long r4 = r4.getHistoricalEventTimeNano(r1)
            goto L39
        L38:
            r4 = r2
        L39:
            android.view.Choreographer r1 = r6.mChoreographer
            android.graphics.FrameInfo r1 = r1.mFrameInfo
            r1.updateInputEventTime(r2, r4)
            r6.deliverInputEvent(r0)
            goto L0
        L44:
            boolean r0 = r6.mProcessInputEventsScheduled
            if (r0 == 0) goto L51
            r6.mProcessInputEventsScheduled = r1
            android.view.ViewRootImpl$ViewRootHandler r0 = r6.mHandler
            r1 = 19
            r0.removeMessages(r1)
        L51:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.view.ViewRootImpl.doProcessInputEvents():void");
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0067, code lost:
    
        r6 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x006b, code lost:
    
        throw r6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x00ae, code lost:
    
        r6 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x00b2, code lost:
    
        throw r6;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void deliverInputEvent(android.view.ViewRootImpl.QueuedInputEvent r6) {
        /*
            r5 = this;
            android.view.InputEvent r0 = r6.mEvent
            int r0 = r0.getId()
            r1 = 8
            java.lang.String r3 = "deliverInputEvent"
            android.os.Trace.asyncTraceBegin(r1, r3, r0)
            boolean r0 = android.os.Trace.isTagEnabled(r1)
            if (r0 == 0) goto L51
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r3 = "deliverInputEvent src=0x"
            r0.append(r3)
            android.view.InputEvent r3 = r6.mEvent
            int r3 = r3.getSource()
            java.lang.String r3 = java.lang.Integer.toHexString(r3)
            r0.append(r3)
            java.lang.String r3 = " eventTimeNano="
            r0.append(r3)
            android.view.InputEvent r3 = r6.mEvent
            long r3 = r3.getEventTimeNano()
            r0.append(r3)
            java.lang.String r3 = " id=0x"
            r0.append(r3)
            android.view.InputEvent r3 = r6.mEvent
            int r3 = r3.getId()
            java.lang.String r3 = java.lang.Integer.toHexString(r3)
            r0.append(r3)
            java.lang.String r0 = r0.toString()
            android.os.Trace.traceBegin(r1, r0)
        L51:
            android.view.InputEventConsistencyVerifier r0 = r5.mInputEventConsistencyVerifier     // Catch: java.lang.Throwable -> Lae
            if (r0 == 0) goto L6c
            java.lang.String r0 = "verifyEventConsistency"
            android.os.Trace.traceBegin(r1, r0)     // Catch: java.lang.Throwable -> Lae
            android.view.InputEventConsistencyVerifier r0 = r5.mInputEventConsistencyVerifier     // Catch: java.lang.Throwable -> L67
            android.view.InputEvent r3 = r6.mEvent     // Catch: java.lang.Throwable -> L67
            r4 = 0
            r0.onInputEvent(r3, r4)     // Catch: java.lang.Throwable -> L67
            android.os.Trace.traceEnd(r1)     // Catch: java.lang.Throwable -> Lae
            goto L6c
        L67:
            r6 = move-exception
            android.os.Trace.traceEnd(r1)     // Catch: java.lang.Throwable -> Lae
            throw r6     // Catch: java.lang.Throwable -> Lae
        L6c:
            boolean r0 = r6.shouldSendToSynthesizer()     // Catch: java.lang.Throwable -> Lae
            if (r0 == 0) goto L75
            android.view.ViewRootImpl$InputStage r0 = r5.mSyntheticInputStage     // Catch: java.lang.Throwable -> Lae
            goto L80
        L75:
            boolean r0 = r6.shouldSkipIme()     // Catch: java.lang.Throwable -> Lae
            if (r0 == 0) goto L7e
            android.view.ViewRootImpl$InputStage r0 = r5.mFirstPostImeInputStage     // Catch: java.lang.Throwable -> Lae
            goto L80
        L7e:
            android.view.ViewRootImpl$InputStage r0 = r5.mFirstInputStage     // Catch: java.lang.Throwable -> Lae
        L80:
            android.view.InputEvent r3 = r6.mEvent     // Catch: java.lang.Throwable -> Lae
            boolean r3 = r3 instanceof android.view.KeyEvent     // Catch: java.lang.Throwable -> Lae
            if (r3 == 0) goto L9e
            java.lang.String r3 = "preDispatchToUnhandledKeyManager"
            android.os.Trace.traceBegin(r1, r3)     // Catch: java.lang.Throwable -> Lae
            android.view.ViewRootImpl$UnhandledKeyManager r3 = r5.mUnhandledKeyManager     // Catch: java.lang.Throwable -> L99
            android.view.InputEvent r4 = r6.mEvent     // Catch: java.lang.Throwable -> L99
            android.view.KeyEvent r4 = (android.view.KeyEvent) r4     // Catch: java.lang.Throwable -> L99
            r3.preDispatch(r4)     // Catch: java.lang.Throwable -> L99
            android.os.Trace.traceEnd(r1)     // Catch: java.lang.Throwable -> Lae
            goto L9e
        L99:
            r6 = move-exception
            android.os.Trace.traceEnd(r1)     // Catch: java.lang.Throwable -> Lae
            throw r6     // Catch: java.lang.Throwable -> Lae
        L9e:
            if (r0 == 0) goto La7
            r5.handleWindowFocusChanged()     // Catch: java.lang.Throwable -> Lae
            r0.deliver(r6)     // Catch: java.lang.Throwable -> Lae
            goto Laa
        La7:
            r5.finishInputEvent(r6)     // Catch: java.lang.Throwable -> Lae
        Laa:
            android.os.Trace.traceEnd(r1)
            return
        Lae:
            r6 = move-exception
            android.os.Trace.traceEnd(r1)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: android.view.ViewRootImpl.deliverInputEvent(android.view.ViewRootImpl$QueuedInputEvent):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void finishInputEvent(QueuedInputEvent queuedInputEvent) {
        Trace.asyncTraceEnd(8L, "deliverInputEvent", queuedInputEvent.mEvent.getId());
        if (queuedInputEvent.mReceiver != null) {
            boolean z = (queuedInputEvent.mFlags & 8) != 0;
            if ((queuedInputEvent.mFlags & 64) != 0) {
                Trace.traceBegin(8L, "processInputEventBeforeFinish");
                try {
                    InputEvent inputEventProcessInputEventBeforeFinish = this.mInputCompatProcessor.processInputEventBeforeFinish(queuedInputEvent.mEvent);
                    if (inputEventProcessInputEventBeforeFinish != null) {
                        queuedInputEvent.mReceiver.finishInputEvent(inputEventProcessInputEventBeforeFinish, z);
                    }
                } finally {
                    Trace.traceEnd(8L);
                }
            } else {
                queuedInputEvent.mReceiver.finishInputEvent(queuedInputEvent.mEvent, z);
            }
        } else {
            queuedInputEvent.mEvent.recycleIfNeededAfterDispatch();
        }
        recycleQueuedInputEvent(queuedInputEvent);
    }

    static boolean isTerminalInputEvent(InputEvent inputEvent) {
        if (inputEvent instanceof KeyEvent) {
            return ((KeyEvent) inputEvent).getAction() == 1;
        }
        int action = ((MotionEvent) inputEvent).getAction();
        return action == 1 || action == 3 || action == 10;
    }

    void scheduleConsumeBatchedInput() {
        if (this.mConsumeBatchedInputScheduled || this.mConsumeBatchedInputImmediatelyScheduled) {
            return;
        }
        this.mConsumeBatchedInputScheduled = true;
        this.mChoreographer.postCallback(0, this.mConsumedBatchedInputRunnable, null);
    }

    void unscheduleConsumeBatchedInput() {
        if (this.mConsumeBatchedInputScheduled) {
            this.mConsumeBatchedInputScheduled = false;
            this.mChoreographer.removeCallbacks(0, this.mConsumedBatchedInputRunnable, null);
        }
    }

    void scheduleConsumeBatchedInputImmediately() {
        if (this.mConsumeBatchedInputImmediatelyScheduled) {
            return;
        }
        unscheduleConsumeBatchedInput();
        this.mConsumeBatchedInputImmediatelyScheduled = true;
        this.mHandler.post(this.mConsumeBatchedInputImmediatelyRunnable);
    }

    boolean doConsumeBatchedInput(long j) {
        WindowInputEventReceiver windowInputEventReceiver = this.mInputEventReceiver;
        boolean zConsumeBatchedInputEvents = windowInputEventReceiver != null ? windowInputEventReceiver.consumeBatchedInputEvents(j) : false;
        doProcessInputEvents();
        return zConsumeBatchedInputEvents;
    }

    final class TraversalRunnable implements Runnable {
        TraversalRunnable() {
        }

        @Override // java.lang.Runnable
        public void run() {
            ViewRootImpl.this.doTraversal();
        }
    }

    final class WindowInputEventReceiver extends InputEventReceiver {
        public WindowInputEventReceiver(InputChannel inputChannel, Looper looper) {
            super(inputChannel, looper);
        }

        @Override // android.view.InputEventReceiver
        public void onInputEvent(InputEvent inputEvent) {
            Trace.traceBegin(8L, "processInputEventForCompatibility");
            try {
                List<InputEvent> listProcessInputEventForCompatibility = ViewRootImpl.this.mInputCompatProcessor.processInputEventForCompatibility(inputEvent);
                Trace.traceEnd(8L);
                if (listProcessInputEventForCompatibility != null) {
                    if (listProcessInputEventForCompatibility.isEmpty()) {
                        finishInputEvent(inputEvent, true);
                        return;
                    }
                    for (int i = 0; i < listProcessInputEventForCompatibility.size(); i++) {
                        ViewRootImpl.this.enqueueInputEvent(listProcessInputEventForCompatibility.get(i), this, 64, true);
                    }
                    return;
                }
                ViewRootImpl.this.enqueueInputEvent(inputEvent, this, 0, true);
            } catch (Throwable th) {
                Trace.traceEnd(8L);
                throw th;
            }
        }

        @Override // android.view.InputEventReceiver
        public void onBatchedInputEventPending(int i) {
            if (ViewRootImpl.this.mUnbufferedInputDispatch || (i & ViewRootImpl.this.mUnbufferedInputSource) != 0 || ViewRootImpl.this.mStopped) {
                if (ViewRootImpl.this.mConsumeBatchedInputScheduled) {
                    ViewRootImpl.this.unscheduleConsumeBatchedInput();
                }
                consumeBatchedInputEvents(-1L);
                return;
            }
            ViewRootImpl.this.scheduleConsumeBatchedInput();
        }

        @Override // android.view.InputEventReceiver
        public void onFocusEvent(boolean z, boolean z2) {
            ViewRootImpl.this.windowFocusChanged(z, z2);
        }

        @Override // android.view.InputEventReceiver
        public void dispose() {
            ViewRootImpl.this.unscheduleConsumeBatchedInput();
            super.dispose();
        }
    }

    final class ConsumeBatchedInputRunnable implements Runnable {
        ConsumeBatchedInputRunnable() {
        }

        @Override // java.lang.Runnable
        public void run() {
            ViewRootImpl.this.mConsumeBatchedInputScheduled = false;
            ViewRootImpl viewRootImpl = ViewRootImpl.this;
            if (viewRootImpl.doConsumeBatchedInput(viewRootImpl.mChoreographer.getFrameTimeNanos())) {
                ViewRootImpl.this.scheduleConsumeBatchedInput();
            }
        }
    }

    final class ConsumeBatchedInputImmediatelyRunnable implements Runnable {
        ConsumeBatchedInputImmediatelyRunnable() {
        }

        @Override // java.lang.Runnable
        public void run() {
            ViewRootImpl.this.mConsumeBatchedInputImmediatelyScheduled = false;
            ViewRootImpl.this.doConsumeBatchedInput(-1L);
        }
    }

    final class InvalidateOnAnimationRunnable implements Runnable {
        private boolean mPosted;
        private View.AttachInfo.InvalidateInfo[] mTempViewRects;
        private View[] mTempViews;
        private final ArrayList<View> mViews = new ArrayList<>();
        private final ArrayList<View.AttachInfo.InvalidateInfo> mViewRects = new ArrayList<>();

        InvalidateOnAnimationRunnable() {
        }

        public void addView(View view) {
            synchronized (this) {
                this.mViews.add(view);
                postIfNeededLocked();
            }
        }

        public void addViewRect(View.AttachInfo.InvalidateInfo invalidateInfo) {
            synchronized (this) {
                this.mViewRects.add(invalidateInfo);
                postIfNeededLocked();
            }
        }

        public void removeView(View view) {
            synchronized (this) {
                this.mViews.remove(view);
                int size = this.mViewRects.size();
                while (true) {
                    int i = size - 1;
                    if (size <= 0) {
                        break;
                    }
                    View.AttachInfo.InvalidateInfo invalidateInfo = this.mViewRects.get(i);
                    if (invalidateInfo.target == view) {
                        this.mViewRects.remove(i);
                        invalidateInfo.recycle();
                    }
                    size = i;
                }
                if (this.mPosted && this.mViews.isEmpty() && this.mViewRects.isEmpty()) {
                    ViewRootImpl.this.mChoreographer.removeCallbacks(1, this, null);
                    this.mPosted = false;
                }
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            int i;
            int size;
            int size2;
            synchronized (this) {
                this.mPosted = false;
                size = this.mViews.size();
                if (size != 0) {
                    this.mTempViews = (View[]) this.mViews.toArray(this.mTempViews != null ? this.mTempViews : new View[size]);
                    this.mViews.clear();
                }
                size2 = this.mViewRects.size();
                if (size2 != 0) {
                    this.mTempViewRects = (View.AttachInfo.InvalidateInfo[]) this.mViewRects.toArray(this.mTempViewRects != null ? this.mTempViewRects : new View.AttachInfo.InvalidateInfo[size2]);
                    this.mViewRects.clear();
                }
            }
            for (int i2 = 0; i2 < size; i2++) {
                this.mTempViews[i2].invalidate();
                this.mTempViews[i2] = null;
            }
            for (i = 0; i < size2; i++) {
                View.AttachInfo.InvalidateInfo invalidateInfo = this.mTempViewRects[i];
                invalidateInfo.target.invalidate(invalidateInfo.left, invalidateInfo.top, invalidateInfo.right, invalidateInfo.bottom);
                invalidateInfo.recycle();
            }
        }

        private void postIfNeededLocked() {
            if (this.mPosted) {
                return;
            }
            ViewRootImpl.this.mChoreographer.postCallback(1, this, null);
            this.mPosted = true;
        }
    }

    public void dispatchInvalidateDelayed(View view, long j) {
        this.mHandler.sendMessageDelayed(this.mHandler.obtainMessage(1, view), j);
    }

    public void dispatchInvalidateRectDelayed(View.AttachInfo.InvalidateInfo invalidateInfo, long j) {
        this.mHandler.sendMessageDelayed(this.mHandler.obtainMessage(2, invalidateInfo), j);
    }

    public void dispatchInvalidateOnAnimation(View view) {
        this.mInvalidateOnAnimationRunnable.addView(view);
    }

    public void dispatchInvalidateRectOnAnimation(View.AttachInfo.InvalidateInfo invalidateInfo) {
        this.mInvalidateOnAnimationRunnable.addViewRect(invalidateInfo);
    }

    public void cancelInvalidate(View view) {
        this.mHandler.removeMessages(1, view);
        this.mHandler.removeMessages(2, view);
        this.mInvalidateOnAnimationRunnable.removeView(view);
    }

    public void dispatchInputEvent(InputEvent inputEvent) {
        dispatchInputEvent(inputEvent, null);
    }

    public void dispatchInputEvent(InputEvent inputEvent, InputEventReceiver inputEventReceiver) {
        SomeArgs someArgsObtain = SomeArgs.obtain();
        someArgsObtain.arg1 = inputEvent;
        someArgsObtain.arg2 = inputEventReceiver;
        Message messageObtainMessage = this.mHandler.obtainMessage(7, someArgsObtain);
        messageObtainMessage.setAsynchronous(true);
        this.mHandler.sendMessage(messageObtainMessage);
    }

    public void synthesizeInputEvent(InputEvent inputEvent) {
        Message messageObtainMessage = this.mHandler.obtainMessage(24, inputEvent);
        messageObtainMessage.setAsynchronous(true);
        this.mHandler.sendMessage(messageObtainMessage);
    }

    public void dispatchKeyFromIme(KeyEvent keyEvent) {
        Message messageObtainMessage = this.mHandler.obtainMessage(11, keyEvent);
        messageObtainMessage.setAsynchronous(true);
        this.mHandler.sendMessage(messageObtainMessage);
    }

    public void dispatchKeyFromAutofill(KeyEvent keyEvent) {
        Message messageObtainMessage = this.mHandler.obtainMessage(12, keyEvent);
        messageObtainMessage.setAsynchronous(true);
        this.mHandler.sendMessage(messageObtainMessage);
    }

    public void dispatchUnhandledInputEvent(InputEvent inputEvent) {
        if (inputEvent instanceof MotionEvent) {
            inputEvent = MotionEvent.obtain((MotionEvent) inputEvent);
        }
        synthesizeInputEvent(inputEvent);
    }

    public void dispatchAppVisibility(boolean z) {
        Message messageObtainMessage = this.mHandler.obtainMessage(8);
        messageObtainMessage.arg1 = z ? 1 : 0;
        this.mHandler.sendMessage(messageObtainMessage);
    }

    public void dispatchGetNewSurface() {
        this.mHandler.sendMessage(this.mHandler.obtainMessage(9));
    }

    public void dispatchLocationInParentDisplayChanged(Point point) {
        this.mHandler.sendMessage(this.mHandler.obtainMessage(33, point.x, point.y));
    }

    public void windowFocusChanged(boolean z, boolean z2) {
        synchronized (this) {
            this.mWindowFocusChanged = true;
            this.mUpcomingWindowFocus = z;
            this.mUpcomingInTouchMode = z2;
        }
        Message messageObtain = Message.obtain();
        messageObtain.what = 6;
        this.mHandler.sendMessage(messageObtain);
    }

    public void dispatchWindowShown() {
        this.mHandler.sendEmptyMessage(25);
    }

    public void dispatchCloseSystemDialogs(String str) {
        Message messageObtain = Message.obtain();
        messageObtain.what = 14;
        messageObtain.obj = str;
        this.mHandler.sendMessage(messageObtain);
    }

    public void dispatchDragEvent(DragEvent dragEvent) {
        int i;
        if (dragEvent.getAction() == 2) {
            i = 16;
            this.mHandler.removeMessages(16);
        } else {
            i = 15;
        }
        this.mHandler.sendMessage(this.mHandler.obtainMessage(i, dragEvent));
    }

    public void updatePointerIcon(float f, float f2) {
        this.mHandler.removeMessages(27);
        this.mHandler.sendMessage(this.mHandler.obtainMessage(27, MotionEvent.obtain(0L, SystemClock.uptimeMillis(), 7, f, f2, 0)));
    }

    public void dispatchSystemUiVisibilityChanged(int i, int i2, int i3, int i4) {
        SystemUiVisibilityInfo systemUiVisibilityInfo = new SystemUiVisibilityInfo();
        systemUiVisibilityInfo.seq = i;
        systemUiVisibilityInfo.globalVisibility = i2;
        systemUiVisibilityInfo.localValue = i3;
        systemUiVisibilityInfo.localChanges = i4;
        ViewRootHandler viewRootHandler = this.mHandler;
        viewRootHandler.sendMessage(viewRootHandler.obtainMessage(17, systemUiVisibilityInfo));
    }

    public void dispatchCheckFocus() {
        if (this.mHandler.hasMessages(13)) {
            return;
        }
        this.mHandler.sendEmptyMessage(13);
    }

    public void dispatchRequestKeyboardShortcuts(IResultReceiver iResultReceiver, int i) {
        this.mHandler.obtainMessage(26, i, 0, iResultReceiver).sendToTarget();
    }

    public void dispatchPointerCaptureChanged(boolean z) {
        this.mHandler.removeMessages(28);
        Message messageObtainMessage = this.mHandler.obtainMessage(28);
        messageObtainMessage.arg1 = z ? 1 : 0;
        this.mHandler.sendMessage(messageObtainMessage);
    }

    private void postSendWindowContentChangedCallback(View view, int i) {
        if (this.mSendWindowContentChangedAccessibilityEvent == null) {
            this.mSendWindowContentChangedAccessibilityEvent = new SendWindowContentChangedAccessibilityEvent();
        }
        this.mSendWindowContentChangedAccessibilityEvent.runOrPost(view, i);
    }

    private void removeSendWindowContentChangedCallback() {
        SendWindowContentChangedAccessibilityEvent sendWindowContentChangedAccessibilityEvent = this.mSendWindowContentChangedAccessibilityEvent;
        if (sendWindowContentChangedAccessibilityEvent != null) {
            this.mHandler.removeCallbacks(sendWindowContentChangedAccessibilityEvent);
        }
    }

    @Override // android.view.ViewParent
    public boolean requestSendAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
        AccessibilityNodeProvider accessibilityNodeProvider;
        SendWindowContentChangedAccessibilityEvent sendWindowContentChangedAccessibilityEvent;
        if (this.mView == null || this.mStopped || this.mPausedForTransition) {
            return false;
        }
        if (accessibilityEvent.getEventType() != 2048 && (sendWindowContentChangedAccessibilityEvent = this.mSendWindowContentChangedAccessibilityEvent) != null && sendWindowContentChangedAccessibilityEvent.mSource != null) {
            this.mSendWindowContentChangedAccessibilityEvent.removeCallbacksAndRun();
        }
        int eventType = accessibilityEvent.getEventType();
        View sourceForAccessibilityEvent = getSourceForAccessibilityEvent(accessibilityEvent);
        if (eventType == 2048) {
            handleWindowContentChangedEvent(accessibilityEvent);
        } else if (eventType != 32768) {
            if (eventType == 65536 && sourceForAccessibilityEvent != null && sourceForAccessibilityEvent.getAccessibilityNodeProvider() != null) {
                setAccessibilityFocus(null, null);
            }
        } else if (sourceForAccessibilityEvent != null && (accessibilityNodeProvider = sourceForAccessibilityEvent.getAccessibilityNodeProvider()) != null) {
            setAccessibilityFocus(sourceForAccessibilityEvent, accessibilityNodeProvider.createAccessibilityNodeInfo(AccessibilityNodeInfo.getVirtualDescendantId(accessibilityEvent.getSourceNodeId())));
        }
        this.mAccessibilityManager.sendAccessibilityEvent(accessibilityEvent);
        return true;
    }

    private View getSourceForAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        return AccessibilityNodeIdManager.getInstance().findView(AccessibilityNodeInfo.getAccessibilityViewId(accessibilityEvent.getSourceNodeId()));
    }

    private void handleWindowContentChangedEvent(AccessibilityEvent accessibilityEvent) {
        View view = this.mAccessibilityFocusedHost;
        if (view == null || this.mAccessibilityFocusedVirtualView == null) {
            return;
        }
        AccessibilityNodeProvider accessibilityNodeProvider = view.getAccessibilityNodeProvider();
        if (accessibilityNodeProvider == null) {
            this.mAccessibilityFocusedHost = null;
            this.mAccessibilityFocusedVirtualView = null;
            view.clearAccessibilityFocusNoCallbacks(0);
            return;
        }
        int contentChangeTypes = accessibilityEvent.getContentChangeTypes();
        if ((contentChangeTypes & 1) != 0 || contentChangeTypes == 0) {
            int accessibilityViewId = AccessibilityNodeInfo.getAccessibilityViewId(accessibilityEvent.getSourceNodeId());
            View view2 = this.mAccessibilityFocusedHost;
            boolean z = false;
            while (view2 != null && !z) {
                if (accessibilityViewId == view2.getAccessibilityViewId()) {
                    z = true;
                } else {
                    Object parent = view2.getParent();
                    view2 = parent instanceof View ? (View) parent : null;
                }
            }
            if (z) {
                int virtualDescendantId = AccessibilityNodeInfo.getVirtualDescendantId(this.mAccessibilityFocusedVirtualView.getSourceNodeId());
                Rect rect = this.mTempRect;
                this.mAccessibilityFocusedVirtualView.getBoundsInScreen(rect);
                AccessibilityNodeInfo accessibilityNodeInfoCreateAccessibilityNodeInfo = accessibilityNodeProvider.createAccessibilityNodeInfo(virtualDescendantId);
                this.mAccessibilityFocusedVirtualView = accessibilityNodeInfoCreateAccessibilityNodeInfo;
                if (accessibilityNodeInfoCreateAccessibilityNodeInfo == null) {
                    this.mAccessibilityFocusedHost = null;
                    view.clearAccessibilityFocusNoCallbacks(0);
                    accessibilityNodeProvider.performAction(virtualDescendantId, AccessibilityNodeInfo.AccessibilityAction.ACTION_CLEAR_ACCESSIBILITY_FOCUS.getId(), null);
                    invalidateRectOnScreen(rect);
                    return;
                }
                Rect boundsInScreen = accessibilityNodeInfoCreateAccessibilityNodeInfo.getBoundsInScreen();
                if (rect.equals(boundsInScreen)) {
                    return;
                }
                rect.union(boundsInScreen);
                invalidateRectOnScreen(rect);
            }
        }
    }

    @Override // android.view.ViewParent
    public void notifySubtreeAccessibilityStateChanged(View view, View view2, int i) {
        postSendWindowContentChangedCallback((View) Preconditions.checkNotNull(view2), i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public View getCommonPredecessor(View view, View view2) {
        if (this.mTempHashSet == null) {
            this.mTempHashSet = new HashSet<>();
        }
        HashSet<View> hashSet = this.mTempHashSet;
        hashSet.clear();
        while (view != null) {
            hashSet.add(view);
            Object obj = view.mParent;
            view = obj instanceof View ? (View) obj : null;
        }
        while (view2 != null) {
            if (hashSet.contains(view2)) {
                hashSet.clear();
                return view2;
            }
            Object obj2 = view2.mParent;
            view2 = obj2 instanceof View ? (View) obj2 : null;
        }
        hashSet.clear();
        return null;
    }

    void checkThread() {
        if (this.mThread != Thread.currentThread()) {
            throw new CalledFromWrongThreadException("Only the original thread that created a view hierarchy can touch its views.");
        }
    }

    @Override // android.view.ViewParent
    public boolean requestChildRectangleOnScreen(View view, Rect rect, boolean z) {
        if (rect == null) {
            return scrollToRectOrFocus(null, z);
        }
        rect.offset(view.getLeft() - view.getScrollX(), view.getTop() - view.getScrollY());
        boolean zScrollToRectOrFocus = scrollToRectOrFocus(rect, z);
        this.mTempRect.set(rect);
        this.mTempRect.offset(0, -this.mCurScrollY);
        this.mTempRect.offset(this.mAttachInfo.mWindowLeft, this.mAttachInfo.mWindowTop);
        try {
            this.mWindowSession.onRectangleOnScreenRequested(this.mWindow, this.mTempRect);
        } catch (RemoteException unused) {
        }
        return zScrollToRectOrFocus;
    }

    public void addScrollCaptureCallback(ScrollCaptureCallback scrollCaptureCallback) {
        if (this.mRootScrollCaptureCallbacks == null) {
            this.mRootScrollCaptureCallbacks = new HashSet<>();
        }
        this.mRootScrollCaptureCallbacks.add(scrollCaptureCallback);
    }

    public void removeScrollCaptureCallback(ScrollCaptureCallback scrollCaptureCallback) {
        HashSet<ScrollCaptureCallback> hashSet = this.mRootScrollCaptureCallbacks;
        if (hashSet != null) {
            hashSet.remove(scrollCaptureCallback);
            if (this.mRootScrollCaptureCallbacks.isEmpty()) {
                this.mRootScrollCaptureCallbacks = null;
            }
        }
    }

    public void dispatchScrollCaptureRequest(IScrollCaptureController iScrollCaptureController) {
        this.mHandler.obtainMessage(36, iScrollCaptureController).sendToTarget();
    }

    private void collectRootScrollCaptureTargets(Queue<ScrollCaptureTarget> queue) {
        for (ScrollCaptureCallback scrollCaptureCallback : this.mRootScrollCaptureCallbacks) {
            Point point = new Point(this.mView.getLeft(), this.mView.getTop());
            queue.add(new ScrollCaptureTarget(this.mView, new Rect(0, 0, this.mView.getWidth(), this.mView.getHeight()), point, scrollCaptureCallback));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleScrollCaptureRequest(final IScrollCaptureController iScrollCaptureController) {
        LinkedList linkedList = new LinkedList();
        collectRootScrollCaptureTargets(linkedList);
        View view = getView();
        Point point = new Point();
        Rect rect = new Rect(0, 0, view.getWidth(), view.getHeight());
        getChildVisibleRect(view, rect, point);
        view.dispatchScrollCaptureSearch(rect, point, linkedList);
        if (linkedList.isEmpty()) {
            lambda$handleScrollCaptureRequest$3$ViewRootImpl(iScrollCaptureController, null);
        } else {
            new ScrollCaptureTargetResolver(linkedList).start(this.mHandler, 1000L, new Consumer() { // from class: android.view.-$$Lambda$ViewRootImpl$q8OEhO4Gpuq2GiPXjd5gNL6TlLg
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    this.f$0.lambda$handleScrollCaptureRequest$3$ViewRootImpl(iScrollCaptureController, (ScrollCaptureTarget) obj);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: dispatchScrollCaptureSearchResult, reason: merged with bridge method [inline-methods] */
    public void lambda$handleScrollCaptureRequest$3$ViewRootImpl(IScrollCaptureController iScrollCaptureController, ScrollCaptureTarget scrollCaptureTarget) {
        if (scrollCaptureTarget == null) {
            try {
                iScrollCaptureController.onClientUnavailable();
                return;
            } catch (RemoteException unused) {
                return;
            }
        }
        ScrollCaptureClient scrollCaptureClient = new ScrollCaptureClient(scrollCaptureTarget, iScrollCaptureController);
        this.mScrollCaptureClient = scrollCaptureClient;
        try {
            iScrollCaptureController.onClientConnected(scrollCaptureClient, scrollCaptureTarget.getScrollBounds(), scrollCaptureTarget.getPositionInWindow());
        } catch (RemoteException unused2) {
            this.mScrollCaptureClient.disconnect();
            this.mScrollCaptureClient = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reportNextDraw() {
        if (!this.mReportNextDraw) {
            drawPending();
        }
        this.mReportNextDraw = true;
    }

    public void setReportNextDraw() {
        reportNextDraw();
        invalidate();
    }

    void changeCanvasOpacity(boolean z) {
        Log.d(this.mTag, "changeCanvasOpacity: opaque=" + z);
        boolean z2 = z & ((this.mView.mPrivateFlags & 512) == 0);
        if (this.mAttachInfo.mThreadedRenderer != null) {
            this.mAttachInfo.mThreadedRenderer.setOpaque(z2);
        }
    }

    public boolean dispatchUnhandledKeyEvent(KeyEvent keyEvent) {
        return this.mUnhandledKeyManager.dispatch(this.mView, keyEvent);
    }

    class TakenSurfaceHolder extends BaseSurfaceHolder {
        @Override // com.android.internal.view.BaseSurfaceHolder
        public void onRelayoutContainer() {
        }

        TakenSurfaceHolder() {
        }

        @Override // com.android.internal.view.BaseSurfaceHolder
        public boolean onAllowLockCanvas() {
            return ViewRootImpl.this.mDrawingAllowed;
        }

        @Override // com.android.internal.view.BaseSurfaceHolder, android.view.SurfaceHolder
        public void setFormat(int i) {
            ((RootViewSurfaceTaker) ViewRootImpl.this.mView).setSurfaceFormat(i);
        }

        @Override // com.android.internal.view.BaseSurfaceHolder, android.view.SurfaceHolder
        public void setType(int i) {
            ((RootViewSurfaceTaker) ViewRootImpl.this.mView).setSurfaceType(i);
        }

        @Override // com.android.internal.view.BaseSurfaceHolder
        public void onUpdateSurface() {
            throw new IllegalStateException("Shouldn't be here");
        }

        @Override // android.view.SurfaceHolder
        public boolean isCreating() {
            return ViewRootImpl.this.mIsCreating;
        }

        @Override // com.android.internal.view.BaseSurfaceHolder, android.view.SurfaceHolder
        public void setFixedSize(int i, int i2) {
            throw new UnsupportedOperationException("Currently only support sizing from layout");
        }

        @Override // android.view.SurfaceHolder
        public void setKeepScreenOn(boolean z) {
            ((RootViewSurfaceTaker) ViewRootImpl.this.mView).setSurfaceKeepScreenOn(z);
        }
    }

    static class W extends IWindow.Stub {
        private final WeakReference<ViewRootImpl> mViewAncestor;
        private final IWindowSession mWindowSession;

        W(ViewRootImpl viewRootImpl) {
            this.mViewAncestor = new WeakReference<>(viewRootImpl);
            this.mWindowSession = viewRootImpl.mWindowSession;
        }

        @Override // android.view.IWindow
        public void resized(Rect rect, Rect rect2, Rect rect3, Rect rect4, boolean z, MergedConfiguration mergedConfiguration, Rect rect5, boolean z2, boolean z3, int i, DisplayCutout.ParcelableWrapper parcelableWrapper) {
            ViewRootImpl viewRootImpl = this.mViewAncestor.get();
            if (viewRootImpl != null) {
                viewRootImpl.dispatchResized(rect, rect2, rect3, rect4, z, mergedConfiguration, rect5, z2, z3, i, parcelableWrapper);
            }
        }

        @Override // android.view.IWindow
        public void locationInParentDisplayChanged(Point point) {
            ViewRootImpl viewRootImpl = this.mViewAncestor.get();
            if (viewRootImpl != null) {
                viewRootImpl.dispatchLocationInParentDisplayChanged(point);
            }
        }

        @Override // android.view.IWindow
        public void insetsChanged(InsetsState insetsState) {
            ViewRootImpl viewRootImpl = this.mViewAncestor.get();
            if (viewRootImpl != null) {
                viewRootImpl.dispatchInsetsChanged(insetsState);
            }
        }

        @Override // android.view.IWindow
        public void insetsControlChanged(InsetsState insetsState, InsetsSourceControl[] insetsSourceControlArr) {
            ViewRootImpl viewRootImpl = this.mViewAncestor.get();
            if (viewRootImpl != null) {
                viewRootImpl.dispatchInsetsControlChanged(insetsState, insetsSourceControlArr);
            }
        }

        @Override // android.view.IWindow
        public void showInsets(int i, boolean z) {
            ViewRootImpl viewRootImpl = this.mViewAncestor.get();
            if (viewRootImpl != null) {
                viewRootImpl.showInsets(i, z);
            }
        }

        @Override // android.view.IWindow
        public void hideInsets(int i, boolean z) {
            ViewRootImpl viewRootImpl = this.mViewAncestor.get();
            if (viewRootImpl != null) {
                viewRootImpl.hideInsets(i, z);
            }
        }

        @Override // android.view.IWindow
        public void moved(int i, int i2) {
            ViewRootImpl viewRootImpl = this.mViewAncestor.get();
            if (viewRootImpl != null) {
                viewRootImpl.dispatchMoved(i, i2);
            }
        }

        @Override // android.view.IWindow
        public void dispatchAppVisibility(boolean z) {
            ViewRootImpl viewRootImpl = this.mViewAncestor.get();
            if (viewRootImpl != null) {
                viewRootImpl.dispatchAppVisibility(z);
            }
        }

        @Override // android.view.IWindow
        public void dispatchGetNewSurface() {
            ViewRootImpl viewRootImpl = this.mViewAncestor.get();
            if (viewRootImpl != null) {
                viewRootImpl.dispatchGetNewSurface();
            }
        }

        @Override // android.view.IWindow
        public void windowFocusChanged(boolean z, boolean z2) {
            ViewRootImpl viewRootImpl = this.mViewAncestor.get();
            if (viewRootImpl != null) {
                viewRootImpl.windowFocusChanged(z, z2);
            }
        }

        private static int checkCallingPermission(String str) {
            try {
                return ActivityManager.getService().checkPermission(str, Binder.getCallingPid(), Binder.getCallingUid());
            } catch (RemoteException unused) {
                return -1;
            }
        }

        @Override // android.view.IWindow
        public void executeCommand(String str, String str2, ParcelFileDescriptor parcelFileDescriptor) throws Throwable {
            View view;
            ViewRootImpl viewRootImpl = this.mViewAncestor.get();
            if (viewRootImpl == null || (view = viewRootImpl.mView) == null) {
                return;
            }
            if (checkCallingPermission(Manifest.permission.DUMP) != 0) {
                throw new SecurityException("Insufficient permissions to invoke executeCommand() from pid=" + Binder.getCallingPid() + ", uid=" + Binder.getCallingUid());
            }
            ParcelFileDescriptor.AutoCloseOutputStream autoCloseOutputStream = null;
            try {
                try {
                    try {
                        ParcelFileDescriptor.AutoCloseOutputStream autoCloseOutputStream2 = new ParcelFileDescriptor.AutoCloseOutputStream(parcelFileDescriptor);
                        try {
                            ViewDebug.dispatchCommand(view, str, str2, autoCloseOutputStream2);
                            autoCloseOutputStream2.close();
                        } catch (IOException e) {
                            e = e;
                            autoCloseOutputStream = autoCloseOutputStream2;
                            e.printStackTrace();
                            if (autoCloseOutputStream != null) {
                                autoCloseOutputStream.close();
                            }
                        } catch (Throwable th) {
                            th = th;
                            autoCloseOutputStream = autoCloseOutputStream2;
                            if (autoCloseOutputStream != null) {
                                try {
                                    autoCloseOutputStream.close();
                                } catch (IOException e2) {
                                    e2.printStackTrace();
                                }
                            }
                            throw th;
                        }
                    } catch (IOException e3) {
                        e = e3;
                    }
                } catch (IOException e4) {
                    e4.printStackTrace();
                }
            } catch (Throwable th2) {
                th = th2;
            }
        }

        @Override // android.view.IWindow
        public void closeSystemDialogs(String str) {
            ViewRootImpl viewRootImpl = this.mViewAncestor.get();
            if (viewRootImpl != null) {
                viewRootImpl.dispatchCloseSystemDialogs(str);
            }
        }

        @Override // android.view.IWindow
        public void dispatchWallpaperOffsets(float f, float f2, float f3, float f4, float f5, boolean z) {
            if (z) {
                try {
                    this.mWindowSession.wallpaperOffsetsComplete(asBinder());
                } catch (RemoteException unused) {
                }
            }
        }

        @Override // android.view.IWindow
        public void dispatchWallpaperCommand(String str, int i, int i2, int i3, Bundle bundle, boolean z) {
            if (z) {
                try {
                    this.mWindowSession.wallpaperCommandComplete(asBinder(), null);
                } catch (RemoteException unused) {
                }
            }
        }

        @Override // android.view.IWindow
        public void dispatchDragEvent(DragEvent dragEvent) {
            ViewRootImpl viewRootImpl = this.mViewAncestor.get();
            if (viewRootImpl != null) {
                viewRootImpl.dispatchDragEvent(dragEvent);
            }
        }

        @Override // android.view.IWindow
        public void updatePointerIcon(float f, float f2) {
            ViewRootImpl viewRootImpl = this.mViewAncestor.get();
            if (viewRootImpl != null) {
                viewRootImpl.updatePointerIcon(f, f2);
            }
        }

        @Override // android.view.IWindow
        public void dispatchSystemUiVisibilityChanged(int i, int i2, int i3, int i4) {
            ViewRootImpl viewRootImpl = this.mViewAncestor.get();
            if (viewRootImpl != null) {
                viewRootImpl.dispatchSystemUiVisibilityChanged(i, i2, i3, i4);
            }
        }

        @Override // android.view.IWindow
        public void dispatchWindowShown() {
            ViewRootImpl viewRootImpl = this.mViewAncestor.get();
            if (viewRootImpl != null) {
                viewRootImpl.dispatchWindowShown();
            }
        }

        @Override // android.view.IWindow
        public void requestAppKeyboardShortcuts(IResultReceiver iResultReceiver, int i) {
            ViewRootImpl viewRootImpl = this.mViewAncestor.get();
            if (viewRootImpl != null) {
                viewRootImpl.dispatchRequestKeyboardShortcuts(iResultReceiver, i);
            }
        }

        @Override // android.view.IWindow
        public void dispatchPointerCaptureChanged(boolean z) {
            ViewRootImpl viewRootImpl = this.mViewAncestor.get();
            if (viewRootImpl != null) {
                viewRootImpl.dispatchPointerCaptureChanged(z);
            }
        }

        @Override // android.view.IWindow
        public void requestScrollCapture(IScrollCaptureController iScrollCaptureController) {
            ViewRootImpl viewRootImpl = this.mViewAncestor.get();
            if (viewRootImpl != null) {
                viewRootImpl.dispatchScrollCaptureRequest(iScrollCaptureController);
            }
        }
    }

    public static final class CalledFromWrongThreadException extends AndroidRuntimeException {
        public CalledFromWrongThreadException(String str) {
            super(str);
        }
    }

    static HandlerActionQueue getRunQueue() {
        HandlerActionQueue handlerActionQueue = sRunQueues.get();
        if (handlerActionQueue != null) {
            return handlerActionQueue;
        }
        HandlerActionQueue handlerActionQueue2 = new HandlerActionQueue();
        sRunQueues.set(handlerActionQueue2);
        return handlerActionQueue2;
    }

    private void startDragResizing(Rect rect, boolean z, Rect rect2, Rect rect3, int i) {
        if (this.mDragResizing) {
            return;
        }
        this.mDragResizing = true;
        if (this.mUseMTRenderer) {
            for (int size = this.mWindowCallbacks.size() - 1; size >= 0; size--) {
                this.mWindowCallbacks.get(size).onWindowDragResizeStart(rect, z, rect2, rect3, i);
            }
        }
        this.mFullRedrawNeeded = true;
    }

    private void endDragResizing() {
        if (this.mDragResizing) {
            this.mDragResizing = false;
            if (this.mUseMTRenderer) {
                for (int size = this.mWindowCallbacks.size() - 1; size >= 0; size--) {
                    this.mWindowCallbacks.get(size).onWindowDragResizeEnd();
                }
            }
            this.mFullRedrawNeeded = true;
        }
    }

    private boolean updateContentDrawBounds() {
        boolean zOnContentDrawn;
        if (this.mUseMTRenderer) {
            zOnContentDrawn = false;
            for (int size = this.mWindowCallbacks.size() - 1; size >= 0; size--) {
                zOnContentDrawn |= this.mWindowCallbacks.get(size).onContentDrawn(this.mWindowAttributes.surfaceInsets.left, this.mWindowAttributes.surfaceInsets.top, this.mWidth, this.mHeight);
            }
        } else {
            zOnContentDrawn = false;
        }
        return zOnContentDrawn | (this.mDragResizing && this.mReportNextDraw);
    }

    private void requestDrawWindow() {
        if (this.mUseMTRenderer) {
            this.mWindowDrawCountDown = new CountDownLatch(this.mWindowCallbacks.size());
            for (int size = this.mWindowCallbacks.size() - 1; size >= 0; size--) {
                this.mWindowCallbacks.get(size).onRequestDraw(this.mReportNextDraw);
            }
        }
    }

    public void reportActivityRelaunched() {
        this.mActivityRelaunched = true;
    }

    public SurfaceControl getSurfaceControl() {
        return this.mSurfaceControl;
    }

    public IBinder getInputToken() {
        WindowInputEventReceiver windowInputEventReceiver = this.mInputEventReceiver;
        if (windowInputEventReceiver == null) {
            return null;
        }
        return windowInputEventReceiver.getToken();
    }

    public IBinder getWindowToken() {
        return this.mAttachInfo.mWindowToken;
    }

    final class AccessibilityInteractionConnectionManager implements AccessibilityManager.AccessibilityStateChangeListener {
        AccessibilityInteractionConnectionManager() {
        }

        @Override // android.view.accessibility.AccessibilityManager.AccessibilityStateChangeListener
        public void onAccessibilityStateChanged(boolean z) {
            if (z) {
                ensureConnection();
                if (ViewRootImpl.this.mAttachInfo.mHasWindowFocus && ViewRootImpl.this.mView != null) {
                    ViewRootImpl.this.mView.sendAccessibilityEvent(32);
                    View viewFindFocus = ViewRootImpl.this.mView.findFocus();
                    if (viewFindFocus != null && viewFindFocus != ViewRootImpl.this.mView) {
                        viewFindFocus.sendAccessibilityEvent(8);
                    }
                }
                if (ViewRootImpl.this.mAttachInfo.mLeashedParentToken != null) {
                    ViewRootImpl.this.mAccessibilityManager.associateEmbeddedHierarchy(ViewRootImpl.this.mAttachInfo.mLeashedParentToken, ViewRootImpl.this.mLeashToken);
                    return;
                }
                return;
            }
            ensureNoConnection();
            ViewRootImpl.this.mHandler.obtainMessage(21).sendToTarget();
        }

        public void ensureConnection() {
            if (ViewRootImpl.this.mAttachInfo.mAccessibilityWindowId != -1) {
                return;
            }
            ViewRootImpl.this.mAttachInfo.mAccessibilityWindowId = ViewRootImpl.this.mAccessibilityManager.addAccessibilityInteractionConnection(ViewRootImpl.this.mWindow, ViewRootImpl.this.mLeashToken, ViewRootImpl.this.mContext.getPackageName(), new AccessibilityInteractionConnection(ViewRootImpl.this));
        }

        public void ensureNoConnection() {
            if (ViewRootImpl.this.mAttachInfo.mAccessibilityWindowId != -1) {
                ViewRootImpl.this.mAttachInfo.mAccessibilityWindowId = -1;
                ViewRootImpl.this.mAccessibilityManager.removeAccessibilityInteractionConnection(ViewRootImpl.this.mWindow);
            }
        }
    }

    final class HighContrastTextManager implements AccessibilityManager.HighTextContrastChangeListener {
        HighContrastTextManager() {
            ThreadedRenderer.setHighContrastText(ViewRootImpl.this.mAccessibilityManager.isHighTextContrastEnabled());
        }

        @Override // android.view.accessibility.AccessibilityManager.HighTextContrastChangeListener
        public void onHighTextContrastStateChanged(boolean z) {
            ThreadedRenderer.setHighContrastText(z);
            ViewRootImpl.this.destroyHardwareResources();
            ViewRootImpl.this.invalidate();
        }
    }

    static final class AccessibilityInteractionConnection extends IAccessibilityInteractionConnection.Stub {
        private final WeakReference<ViewRootImpl> mViewRootImpl;

        AccessibilityInteractionConnection(ViewRootImpl viewRootImpl) {
            this.mViewRootImpl = new WeakReference<>(viewRootImpl);
        }

        @Override // android.view.accessibility.IAccessibilityInteractionConnection
        public void findAccessibilityNodeInfoByAccessibilityId(long j, Region region, int i, IAccessibilityInteractionConnectionCallback iAccessibilityInteractionConnectionCallback, int i2, int i3, long j2, MagnificationSpec magnificationSpec, Bundle bundle) {
            ViewRootImpl viewRootImpl = this.mViewRootImpl.get();
            if (viewRootImpl != null && viewRootImpl.mView != null) {
                viewRootImpl.getAccessibilityInteractionController().findAccessibilityNodeInfoByAccessibilityIdClientThread(j, region, i, iAccessibilityInteractionConnectionCallback, i2, i3, j2, magnificationSpec, bundle);
            } else {
                try {
                    iAccessibilityInteractionConnectionCallback.setFindAccessibilityNodeInfosResult(null, i);
                } catch (RemoteException unused) {
                }
            }
        }

        @Override // android.view.accessibility.IAccessibilityInteractionConnection
        public void performAccessibilityAction(long j, int i, Bundle bundle, int i2, IAccessibilityInteractionConnectionCallback iAccessibilityInteractionConnectionCallback, int i3, int i4, long j2) {
            ViewRootImpl viewRootImpl = this.mViewRootImpl.get();
            if (viewRootImpl != null && viewRootImpl.mView != null) {
                viewRootImpl.getAccessibilityInteractionController().performAccessibilityActionClientThread(j, i, bundle, i2, iAccessibilityInteractionConnectionCallback, i3, i4, j2);
            } else {
                try {
                    iAccessibilityInteractionConnectionCallback.setPerformAccessibilityActionResult(false, i2);
                } catch (RemoteException unused) {
                }
            }
        }

        @Override // android.view.accessibility.IAccessibilityInteractionConnection
        public void findAccessibilityNodeInfosByViewId(long j, String str, Region region, int i, IAccessibilityInteractionConnectionCallback iAccessibilityInteractionConnectionCallback, int i2, int i3, long j2, MagnificationSpec magnificationSpec) {
            ViewRootImpl viewRootImpl = this.mViewRootImpl.get();
            if (viewRootImpl != null && viewRootImpl.mView != null) {
                viewRootImpl.getAccessibilityInteractionController().findAccessibilityNodeInfosByViewIdClientThread(j, str, region, i, iAccessibilityInteractionConnectionCallback, i2, i3, j2, magnificationSpec);
            } else {
                try {
                    iAccessibilityInteractionConnectionCallback.setFindAccessibilityNodeInfoResult(null, i);
                } catch (RemoteException unused) {
                }
            }
        }

        @Override // android.view.accessibility.IAccessibilityInteractionConnection
        public void findAccessibilityNodeInfosByText(long j, String str, Region region, int i, IAccessibilityInteractionConnectionCallback iAccessibilityInteractionConnectionCallback, int i2, int i3, long j2, MagnificationSpec magnificationSpec) {
            ViewRootImpl viewRootImpl = this.mViewRootImpl.get();
            if (viewRootImpl != null && viewRootImpl.mView != null) {
                viewRootImpl.getAccessibilityInteractionController().findAccessibilityNodeInfosByTextClientThread(j, str, region, i, iAccessibilityInteractionConnectionCallback, i2, i3, j2, magnificationSpec);
            } else {
                try {
                    iAccessibilityInteractionConnectionCallback.setFindAccessibilityNodeInfosResult(null, i);
                } catch (RemoteException unused) {
                }
            }
        }

        @Override // android.view.accessibility.IAccessibilityInteractionConnection
        public void findFocus(long j, int i, Region region, int i2, IAccessibilityInteractionConnectionCallback iAccessibilityInteractionConnectionCallback, int i3, int i4, long j2, MagnificationSpec magnificationSpec) {
            ViewRootImpl viewRootImpl = this.mViewRootImpl.get();
            if (viewRootImpl != null && viewRootImpl.mView != null) {
                viewRootImpl.getAccessibilityInteractionController().findFocusClientThread(j, i, region, i2, iAccessibilityInteractionConnectionCallback, i3, i4, j2, magnificationSpec);
            } else {
                try {
                    iAccessibilityInteractionConnectionCallback.setFindAccessibilityNodeInfoResult(null, i2);
                } catch (RemoteException unused) {
                }
            }
        }

        @Override // android.view.accessibility.IAccessibilityInteractionConnection
        public void focusSearch(long j, int i, Region region, int i2, IAccessibilityInteractionConnectionCallback iAccessibilityInteractionConnectionCallback, int i3, int i4, long j2, MagnificationSpec magnificationSpec) {
            ViewRootImpl viewRootImpl = this.mViewRootImpl.get();
            if (viewRootImpl != null && viewRootImpl.mView != null) {
                viewRootImpl.getAccessibilityInteractionController().focusSearchClientThread(j, i, region, i2, iAccessibilityInteractionConnectionCallback, i3, i4, j2, magnificationSpec);
            } else {
                try {
                    iAccessibilityInteractionConnectionCallback.setFindAccessibilityNodeInfoResult(null, i2);
                } catch (RemoteException unused) {
                }
            }
        }

        @Override // android.view.accessibility.IAccessibilityInteractionConnection
        public void clearAccessibilityFocus() {
            ViewRootImpl viewRootImpl = this.mViewRootImpl.get();
            if (viewRootImpl == null || viewRootImpl.mView == null) {
                return;
            }
            viewRootImpl.getAccessibilityInteractionController().clearAccessibilityFocusClientThread();
        }

        @Override // android.view.accessibility.IAccessibilityInteractionConnection
        public void notifyOutsideTouch() {
            ViewRootImpl viewRootImpl = this.mViewRootImpl.get();
            if (viewRootImpl == null || viewRootImpl.mView == null) {
                return;
            }
            viewRootImpl.getAccessibilityInteractionController().notifyOutsideTouchClientThread();
        }
    }

    public IAccessibilityEmbeddedConnection getAccessibilityEmbeddedConnection() {
        if (this.mAccessibilityEmbeddedConnection == null) {
            this.mAccessibilityEmbeddedConnection = new AccessibilityEmbeddedConnection(this);
        }
        return this.mAccessibilityEmbeddedConnection;
    }

    private class SendWindowContentChangedAccessibilityEvent implements Runnable {
        private int mChangeTypes;
        public long mLastEventTimeMillis;
        public StackTraceElement[] mOrigin;
        public View mSource;

        private SendWindowContentChangedAccessibilityEvent() {
            this.mChangeTypes = 0;
        }

        @Override // java.lang.Runnable
        public void run() {
            View view = this.mSource;
            this.mSource = null;
            if (view == null) {
                Log.e(ViewRootImpl.TAG, "Accessibility content change has no source");
                return;
            }
            if (AccessibilityManager.getInstance(ViewRootImpl.this.mContext).isEnabled()) {
                this.mLastEventTimeMillis = SystemClock.uptimeMillis();
                AccessibilityEvent accessibilityEventObtain = AccessibilityEvent.obtain();
                accessibilityEventObtain.setEventType(2048);
                accessibilityEventObtain.setContentChangeTypes(this.mChangeTypes);
                view.sendAccessibilityEventUnchecked(accessibilityEventObtain);
            } else {
                this.mLastEventTimeMillis = 0L;
            }
            view.resetSubtreeAccessibilityStateChanged();
            this.mChangeTypes = 0;
        }

        public void runOrPost(View view, int i) {
            if (ViewRootImpl.this.mHandler.getLooper() != Looper.myLooper()) {
                Log.e(ViewRootImpl.TAG, "Accessibility content change on non-UI thread. Future Android versions will throw an exception.", new CalledFromWrongThreadException("Only the original thread that created a view hierarchy can touch its views."));
                ViewRootImpl.this.mHandler.removeCallbacks(this);
                if (this.mSource != null) {
                    run();
                }
            }
            View view2 = this.mSource;
            if (view2 != null) {
                View commonPredecessor = ViewRootImpl.this.getCommonPredecessor(view2, view);
                if (commonPredecessor != null) {
                    commonPredecessor = commonPredecessor.getSelfOrParentImportantForA11y();
                }
                if (commonPredecessor != null) {
                    view = commonPredecessor;
                }
                this.mSource = view;
                this.mChangeTypes |= i;
                return;
            }
            this.mSource = view;
            this.mChangeTypes = i;
            long jUptimeMillis = SystemClock.uptimeMillis() - this.mLastEventTimeMillis;
            long sendRecurringAccessibilityEventsInterval = ViewConfiguration.getSendRecurringAccessibilityEventsInterval();
            if (jUptimeMillis >= sendRecurringAccessibilityEventsInterval) {
                removeCallbacksAndRun();
            } else {
                ViewRootImpl.this.mHandler.postDelayed(this, sendRecurringAccessibilityEventsInterval - jUptimeMillis);
            }
        }

        public void removeCallbacksAndRun() {
            ViewRootImpl.this.mHandler.removeCallbacks(this);
            run();
        }
    }

    private static class UnhandledKeyManager {
        private final SparseArray<WeakReference<View>> mCapturedKeys;
        private WeakReference<View> mCurrentReceiver;
        private boolean mDispatched;

        private UnhandledKeyManager() {
            this.mDispatched = true;
            this.mCapturedKeys = new SparseArray<>();
            this.mCurrentReceiver = null;
        }

        boolean dispatch(View view, KeyEvent keyEvent) {
            if (this.mDispatched) {
                return false;
            }
            try {
                Trace.traceBegin(8L, "UnhandledKeyEvent dispatch");
                this.mDispatched = true;
                View viewDispatchUnhandledKeyEvent = view.dispatchUnhandledKeyEvent(keyEvent);
                if (keyEvent.getAction() == 0) {
                    int keyCode = keyEvent.getKeyCode();
                    if (viewDispatchUnhandledKeyEvent != null && !KeyEvent.isModifierKey(keyCode)) {
                        this.mCapturedKeys.put(keyCode, new WeakReference<>(viewDispatchUnhandledKeyEvent));
                    }
                }
                return viewDispatchUnhandledKeyEvent != null;
            } finally {
                Trace.traceEnd(8L);
            }
        }

        void preDispatch(KeyEvent keyEvent) {
            int iIndexOfKey;
            this.mCurrentReceiver = null;
            if (keyEvent.getAction() != 1 || (iIndexOfKey = this.mCapturedKeys.indexOfKey(keyEvent.getKeyCode())) < 0) {
                return;
            }
            this.mCurrentReceiver = this.mCapturedKeys.valueAt(iIndexOfKey);
            this.mCapturedKeys.removeAt(iIndexOfKey);
        }

        boolean preViewDispatch(KeyEvent keyEvent) {
            this.mDispatched = false;
            if (this.mCurrentReceiver == null) {
                this.mCurrentReceiver = this.mCapturedKeys.get(keyEvent.getKeyCode());
            }
            WeakReference<View> weakReference = this.mCurrentReceiver;
            if (weakReference == null) {
                return false;
            }
            View view = weakReference.get();
            if (keyEvent.getAction() == 1) {
                this.mCurrentReceiver = null;
            }
            if (view != null && view.isAttachedToWindow()) {
                view.onUnhandledKeyEvent(keyEvent);
            }
            return true;
        }
    }

    void setUseBLASTSyncTransaction() {
        this.mNextDrawUseBLASTSyncTransaction = true;
    }

    private void finishBLASTSync(boolean z) {
        this.mSendNextFrameToWm = false;
        if (this.mNextReportConsumeBLAST) {
            this.mNextReportConsumeBLAST = false;
            if (z) {
                this.mRtBLASTSyncTransaction.apply();
            } else {
                this.mSurfaceChangedTransaction.merge(this.mRtBLASTSyncTransaction);
            }
        }
    }

    SurfaceControl.Transaction getBLASTSyncTransaction() {
        return this.mRtBLASTSyncTransaction;
    }

    public SurfaceControl getRenderSurfaceControl() {
        if (useBLAST()) {
            return this.mBlastSurfaceControl;
        }
        return this.mSurfaceControl;
    }

    @Override // android.view.ViewParent
    public void onDescendantUnbufferedRequested() {
        this.mUnbufferedInputSource = this.mView.mUnbufferedInputSource;
    }

    void forceDisableBLAST() {
        this.mForceDisableBLAST = true;
    }

    boolean useBLAST() {
        return this.mUseBLASTAdapter && !this.mForceDisableBLAST;
    }

    boolean isDrawingToBLASTTransaction() {
        return this.mNextReportConsumeBLAST;
    }
}
