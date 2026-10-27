package org.chromium.content.browser;

import android.R;
import android.content.ActivityNotFoundException;
import android.content.ClipboardManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Pair;
import android.util.TypedValue;
import android.view.ActionMode;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStructure;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.AccessibilityNodeProvider;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.AbsoluteLayout;
import android.widget.FrameLayout;
import androidx.webkit.internal.AssetHelper;
import com.google.android.gms.actions.SearchIntents;
import java.lang.annotation.Annotation;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import org.chromium.base.ApiCompatibilityUtils;
import org.chromium.base.CommandLine;
import org.chromium.base.Log;
import org.chromium.base.ObserverList;
import org.chromium.base.TraceEvent;
import org.chromium.base.annotations.JNINamespace;
import org.chromium.base.metrics.RecordUserAction;
import org.chromium.content.browser.PopupZoomer;
import org.chromium.content.browser.ScreenOrientationListener;
import org.chromium.content.browser.WebActionModeCallback;
import org.chromium.content.browser.accessibility.BrowserAccessibilityManager;
import org.chromium.content.browser.accessibility.captioning.CaptioningBridgeFactory;
import org.chromium.content.browser.accessibility.captioning.SystemCaptioningBridge;
import org.chromium.content.browser.accessibility.captioning.TextTrackSettings;
import org.chromium.content.browser.input.AnimationIntervalProvider;
import org.chromium.content.browser.input.FloatingPastePopupMenu;
import org.chromium.content.browser.input.ImeAdapter;
import org.chromium.content.browser.input.InputMethodManagerWrapper;
import org.chromium.content.browser.input.JoystickScrollProvider;
import org.chromium.content.browser.input.JoystickZoomProvider;
import org.chromium.content.browser.input.LegacyPastePopupMenu;
import org.chromium.content.browser.input.PastePopupMenu;
import org.chromium.content.browser.input.SelectPopup;
import org.chromium.content.browser.input.SelectPopupDialog;
import org.chromium.content.browser.input.SelectPopupDropdown;
import org.chromium.content.browser.input.SelectPopupItem;
import org.chromium.content.common.ContentSwitches;
import org.chromium.content_public.browser.AccessibilitySnapshotCallback;
import org.chromium.content_public.browser.AccessibilitySnapshotNode;
import org.chromium.content_public.browser.GestureStateListener;
import org.chromium.content_public.browser.WebContents;
import org.chromium.content_public.browser.WebContentsObserver;
import org.chromium.device.gamepad.GamepadList;
import org.chromium.ui.base.DeviceFormFactor;
import org.chromium.ui.base.ViewAndroidDelegate;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.ui.gfx.DeviceDisplayInfo;

/* JADX INFO: loaded from: classes3.dex */
@JNINamespace("content")
public class ContentViewCore implements AccessibilityManager.AccessibilityStateChangeListener, ScreenOrientationListener.ScreenOrientationObserver, SystemCaptioningBridge.SystemCaptioningBridgeListener {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final int INVALID_RENDER_PROCESS_PID = 0;
    private static final ZoomControlsDelegate NO_OP_ZOOM_CONTROLS_DELEGATE = new ZoomControlsDelegate() { // from class: org.chromium.content.browser.ContentViewCore.1
        @Override // org.chromium.content.browser.ContentViewCore.ZoomControlsDelegate
        public void dismissZoomPicker() {
        }

        @Override // org.chromium.content.browser.ContentViewCore.ZoomControlsDelegate
        public void invokeZoomPicker() {
        }

        @Override // org.chromium.content.browser.ContentViewCore.ZoomControlsDelegate
        public void updateZoomControls() {
        }
    };
    private static final String TAG = "cr.ContentViewCore";
    private static final float ZOOM_CONTROLS_EPSILON = 0.007f;
    private final AccessibilityManager mAccessibilityManager;
    private WebActionModeCallback.ActionHandler mActionHandler;
    private WebActionMode mActionMode;
    private BrowserAccessibilityManager mBrowserAccessibilityManager;
    private ViewGroup mContainerView;
    private InternalAccessDelegate mContainerViewInternals;
    private final ObserverList<ContainerViewObserver> mContainerViewObservers;
    private ContentViewClient mContentViewClient;
    private final Context mContext;
    private ContextualSearchClient mContextualSearchClient;
    private float mCurrentTouchOffsetX;
    private float mCurrentTouchOffsetY;
    private boolean mDraggingSelection;
    private Boolean mEnableTouchHover;
    private boolean mFloatingActionModeCreationFailed;
    private boolean mFocusedNodeEditable;
    private boolean mFocusedNodeIsPassword;
    private final ObserverList<GestureStateListener> mGestureStateListeners;
    private final ObserverList.RewindableIterator<GestureStateListener> mGestureStateListenersIterator;
    private boolean mHasInsertion;
    private boolean mHasSelection;
    private ImeAdapter mImeAdapter;
    private boolean mIsMobileOptimizedHint;
    private boolean mIsObscuredByAnotherView;
    private final JoystickScrollProvider mJoystickScrollProvider;
    private JoystickZoomProvider mJoystickZoomProvider;
    private float mLastFocalEventX;
    private float mLastFocalEventY;
    private String mLastSelectedText;
    private boolean mNativeAccessibilityAllowed;
    private boolean mNativeAccessibilityEnabled;
    private OverscrollRefreshHandler mOverscrollRefreshHandler;
    private PastePopupMenu mPastePopupMenu;
    private int mPhysicalBackingHeightPix;
    private int mPhysicalBackingWidthPix;
    private PopupZoomer mPopupZoomer;
    private int mPotentiallyActiveFlingCount;
    private boolean mPreserveSelectionOnNextLossOfFocus;
    private final RenderCoordinates mRenderCoordinates;
    private SelectPopup mSelectPopup;
    private boolean mShouldSetAccessibilityFocusOnPageLoad;
    private ShowKeyboardResultReceiver mShowKeyboardResultReceiver;
    private int mSmartClipOffsetX;
    private int mSmartClipOffsetY;
    private final SystemCaptioningBridge mSystemCaptioningBridge;
    private int mTopControlsHeightPix;
    private boolean mTopControlsShrinkBlinkSize;
    private boolean mTouchExplorationEnabled;
    private boolean mTouchScrollInProgress;
    private boolean mUnselectAllOnActionModeDismiss;
    private ContentViewAndroidDelegate mViewAndroidDelegate;
    private int mViewportHeightPix;
    private int mViewportWidthPix;
    private boolean mWasPastePopupShowingOnInsertionDragStart;
    private WebContents mWebContents;
    private WebContentsObserver mWebContentsObserver;
    private float mWheelScrollFactorInPixels;
    private ZoomControlsDelegate mZoomControlsDelegate;
    private final Map<String, Pair<Object, Class>> mJavaScriptInterfaces = new HashMap();
    private final HashSet<Object> mRetainedJavaScriptObjects = new HashSet<>();
    private long mNativeContentViewCore = 0;
    private long mNativeSelectPopupSourceFrame = 0;
    private Runnable mFakeMouseMoveRunnable = null;
    private final Rect mSelectionRect = new Rect();
    private final Rect mFocusPreOSKViewportRect = new Rect();
    private SmartClipDataListener mSmartClipDataListener = null;
    private boolean mFullscreenRequiredForOrientationLock = true;

    public interface InternalAccessDelegate {
        boolean awakenScrollBars();

        void onScrollChanged(int i, int i2, int i3, int i4);

        boolean super_awakenScrollBars(int i, boolean z);

        boolean super_dispatchKeyEvent(KeyEvent keyEvent);

        void super_onConfigurationChanged(Configuration configuration);

        boolean super_onGenericMotionEvent(MotionEvent motionEvent);

        boolean super_onKeyUp(int i, KeyEvent keyEvent);
    }

    public interface SmartClipDataListener {
        void onSmartClipDataExtracted(String str, String str2, Rect rect);
    }

    public interface ZoomControlsDelegate {
        void dismissZoomPicker();

        void invokeZoomPicker();

        void updateZoomControls();
    }

    private static boolean isValidTouchEventActionForNative(int i) {
        return i == 0 || i == 1 || i == 3 || i == 2 || i == 5 || i == 6;
    }

    private native void nativeAddJavascriptInterface(long j, Object obj, String str, Class cls);

    private native void nativeDismissTextHandles(long j);

    private native void nativeDoubleTap(long j, long j2, float f, float f2);

    private native void nativeExtractSmartClipData(long j, int i, int i2, int i3, int i4);

    private native void nativeFlingCancel(long j, long j2);

    private native void nativeFlingStart(long j, long j2, float f, float f2, float f3, float f4, boolean z);

    private static native ContentViewCore nativeFromWebContentsAndroid(WebContents webContents);

    private native int nativeGetCurrentRenderProcessId(long j);

    private native WindowAndroid nativeGetJavaWindowAndroid(long j);

    private native long nativeGetNativeImeAdapter(long j);

    private native WebContents nativeGetWebContentsAndroid(long j);

    private native long nativeInit(WebContents webContents, ViewAndroidDelegate viewAndroidDelegate, long j, HashSet<Object> hashSet);

    /* JADX INFO: Access modifiers changed from: private */
    public native void nativeLongPress(long j, long j2, float f, float f2);

    private native void nativeOnJavaContentViewCoreDestroyed(long j);

    private native boolean nativeOnTouchEvent(long j, MotionEvent motionEvent, long j2, int i, int i2, int i3, int i4, float f, float f2, float f3, float f4, int i5, int i6, float f5, float f6, float f7, float f8, float f9, float f10, float f11, float f12, float f13, float f14, int i7, int i8, int i9, int i10, boolean z);

    private native void nativePinchBegin(long j, long j2, float f, float f2);

    private native void nativePinchBy(long j, long j2, float f, float f2, float f3);

    private native void nativePinchEnd(long j, long j2);

    private native void nativeRemoveJavascriptInterface(long j, String str);

    private native void nativeResetGestureDetection(long j);

    private native void nativeScrollBegin(long j, long j2, float f, float f2, float f3, float f4, boolean z);

    private native void nativeScrollBy(long j, long j2, float f, float f2, float f3, float f4);

    private native void nativeScrollEnd(long j, long j2);

    private native void nativeSelectBetweenCoordinates(long j, float f, float f2, float f3, float f4);

    private native void nativeSelectPopupMenuItems(long j, long j2, int[] iArr);

    private native int nativeSendMouseMoveEvent(long j, long j2, float f, float f2, int i);

    private native int nativeSendMouseWheelEvent(long j, long j2, float f, float f2, float f3, float f4, float f5);

    private native void nativeSendOrientationChangeEvent(long j, int i);

    private native void nativeSetAccessibilityEnabled(long j, boolean z);

    private native void nativeSetAllowJavascriptInterfacesInspection(long j, boolean z);

    private native void nativeSetBackgroundColor(long j, int i);

    private native void nativeSetBackgroundOpaque(long j, boolean z);

    private native void nativeSetDoubleTapSupportEnabled(long j, boolean z);

    private native void nativeSetFocus(long j, boolean z);

    private native void nativeSetMultiTouchZoomSupportEnabled(long j, boolean z);

    private native void nativeSetTextHandlesTemporarilyHidden(long j, boolean z);

    private native void nativeSetTextTrackSettings(long j, boolean z, String str, String str2, String str3, String str4, String str5, String str6, String str7);

    /* JADX INFO: Access modifiers changed from: private */
    public native void nativeSingleTap(long j, long j2, float f, float f2);

    private native void nativeUpdateWindowAndroid(long j, long j2);

    private native void nativeWasResized(long j);

    public boolean performAccessibilityAction(int i, Bundle bundle) {
        return false;
    }

    public boolean supportsAccessibilityAction(int i) {
        return false;
    }

    private static class ContentViewAndroidDelegate implements ViewAndroidDelegate {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private final Map<View, Position> mAnchorViews = new LinkedHashMap();
        private WeakReference<ViewGroup> mCurrentContainerView;
        private final RenderCoordinates mRenderCoordinates;

        private static class Position {
            private final float mHeight;
            private final float mWidth;
            private final float mX;
            private final float mY;

            public Position(float f, float f2, float f3, float f4) {
                this.mX = f;
                this.mY = f2;
                this.mWidth = f3;
                this.mHeight = f4;
            }
        }

        ContentViewAndroidDelegate(ViewGroup viewGroup, RenderCoordinates renderCoordinates) {
            this.mRenderCoordinates = renderCoordinates;
            this.mCurrentContainerView = new WeakReference<>(viewGroup);
        }

        @Override // org.chromium.ui.base.ViewAndroidDelegate
        public View acquireAnchorView() {
            ViewGroup viewGroup = this.mCurrentContainerView.get();
            if (viewGroup == null) {
                return null;
            }
            View view = new View(viewGroup.getContext());
            this.mAnchorViews.put(view, null);
            viewGroup.addView(view);
            return view;
        }

        @Override // org.chromium.ui.base.ViewAndroidDelegate
        public void setAnchorViewPosition(View view, float f, float f2, float f3, float f4) {
            this.mAnchorViews.put(view, new Position(f, f2, f3, f4));
            doSetAnchorViewPosition(view, f, f2, f3, f4);
        }

        private void doSetAnchorViewPosition(View view, float f, float f2, float f3, float f4) {
            ViewGroup viewGroup;
            if (view.getParent() == null || (viewGroup = this.mCurrentContainerView.get()) == null) {
                return;
            }
            float dIPScale = (float) DeviceDisplayInfo.create(viewGroup.getContext()).getDIPScale();
            int iRound = Math.round(f * dIPScale);
            int iRound2 = Math.round(this.mRenderCoordinates.getContentOffsetYPix() + (f2 * dIPScale));
            int iRound3 = Math.round(f3 * dIPScale);
            if (viewGroup instanceof FrameLayout) {
                if (ApiCompatibilityUtils.isLayoutRtl(viewGroup)) {
                    iRound = viewGroup.getMeasuredWidth() - Math.round((f3 + f) * dIPScale);
                }
                if (iRound3 + iRound > viewGroup.getWidth()) {
                    iRound3 = viewGroup.getWidth() - iRound;
                }
                FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(iRound3, Math.round(f4 * dIPScale));
                ApiCompatibilityUtils.setMarginStart(layoutParams, iRound);
                layoutParams.topMargin = iRound2;
                view.setLayoutParams(layoutParams);
                return;
            }
            if (viewGroup instanceof AbsoluteLayout) {
                view.setLayoutParams(new AbsoluteLayout.LayoutParams(iRound3, (int) (f4 * dIPScale), iRound + this.mRenderCoordinates.getScrollXPixInt(), iRound2 + this.mRenderCoordinates.getScrollYPixInt()));
            } else {
                Log.e(ContentViewCore.TAG, "Unknown layout %s", viewGroup.getClass().getName());
            }
        }

        @Override // org.chromium.ui.base.ViewAndroidDelegate
        public void releaseAnchorView(View view) {
            this.mAnchorViews.remove(view);
            ViewGroup viewGroup = this.mCurrentContainerView.get();
            if (viewGroup != null) {
                viewGroup.removeView(view);
            }
        }

        void updateCurrentContainerView(ViewGroup viewGroup) {
            ViewGroup viewGroup2 = this.mCurrentContainerView.get();
            this.mCurrentContainerView = new WeakReference<>(viewGroup);
            for (Map.Entry<View, Position> entry : this.mAnchorViews.entrySet()) {
                View key = entry.getKey();
                Position value = entry.getValue();
                if (viewGroup2 != null) {
                    viewGroup2.removeView(key);
                }
                viewGroup.addView(key);
                if (value != null) {
                    doSetAnchorViewPosition(key, value.mX, value.mY, value.mWidth, value.mHeight);
                }
            }
        }
    }

    private static class ContentViewWebContentsObserver extends WebContentsObserver {
        private final WeakReference<ContentViewCore> mWeakContentViewCore;

        ContentViewWebContentsObserver(ContentViewCore contentViewCore) {
            super(contentViewCore.getWebContents());
            this.mWeakContentViewCore = new WeakReference<>(contentViewCore);
        }

        @Override // org.chromium.content_public.browser.WebContentsObserver
        public void didFailLoad(boolean z, boolean z2, int i, String str, String str2, boolean z3) {
            if (z) {
                determinedProcessVisibility();
            }
        }

        @Override // org.chromium.content_public.browser.WebContentsObserver
        public void didNavigateMainFrame(String str, String str2, boolean z, boolean z2, int i) {
            if (z) {
                resetPopupsAndInput();
            }
        }

        @Override // org.chromium.content_public.browser.WebContentsObserver
        public void renderProcessGone(boolean z) {
            resetPopupsAndInput();
            ContentViewCore contentViewCore = this.mWeakContentViewCore.get();
            if (contentViewCore == null) {
                return;
            }
            contentViewCore.mImeAdapter.resetAndHideKeyboard();
        }

        @Override // org.chromium.content_public.browser.WebContentsObserver
        public void navigationEntryCommitted() {
            determinedProcessVisibility();
        }

        private void resetPopupsAndInput() {
            ContentViewCore contentViewCore = this.mWeakContentViewCore.get();
            if (contentViewCore == null) {
                return;
            }
            contentViewCore.mIsMobileOptimizedHint = false;
            contentViewCore.hidePopupsAndClearSelection();
            contentViewCore.resetScrollInProgress();
        }

        private void determinedProcessVisibility() {
            ContentViewCore contentViewCore = this.mWeakContentViewCore.get();
            if (contentViewCore == null) {
                return;
            }
            ChildProcessLauncher.determinedVisibility(contentViewCore.getCurrentRenderProcessId());
        }
    }

    private static class SystemAnimationIntervalProvider implements AnimationIntervalProvider {
        private SystemAnimationIntervalProvider() {
        }

        @Override // org.chromium.content.browser.input.AnimationIntervalProvider
        public long getLastAnimationFrameInterval() {
            return AnimationUtils.currentAnimationTimeMillis();
        }
    }

    private static class ShowKeyboardResultReceiver extends ResultReceiver {
        private final WeakReference<ContentViewCore> mContentViewCore;

        public ShowKeyboardResultReceiver(ContentViewCore contentViewCore, Handler handler) {
            super(handler);
            this.mContentViewCore = new WeakReference<>(contentViewCore);
        }

        @Override // android.os.ResultReceiver
        public void onReceiveResult(int i, Bundle bundle) {
            ContentViewCore contentViewCore = this.mContentViewCore.get();
            if (contentViewCore == null) {
                return;
            }
            contentViewCore.onShowKeyboardReceiveResult(i);
        }
    }

    public static ContentViewCore fromWebContents(WebContents webContents) {
        return nativeFromWebContentsAndroid(webContents);
    }

    public ContentViewCore(Context context) {
        this.mContext = context;
        RenderCoordinates renderCoordinates = new RenderCoordinates();
        this.mRenderCoordinates = renderCoordinates;
        this.mJoystickScrollProvider = new JoystickScrollProvider(this);
        float f = getContext().getResources().getDisplayMetrics().density;
        String switchValue = CommandLine.getInstance().getSwitchValue(ContentSwitches.FORCE_DEVICE_SCALE_FACTOR);
        renderCoordinates.setDeviceScaleFactor(switchValue != null ? Float.valueOf(switchValue).floatValue() : f);
        this.mAccessibilityManager = (AccessibilityManager) getContext().getSystemService("accessibility");
        this.mSystemCaptioningBridge = CaptioningBridgeFactory.getSystemCaptioningBridge(context);
        ObserverList<GestureStateListener> observerList = new ObserverList<>();
        this.mGestureStateListeners = observerList;
        this.mGestureStateListenersIterator = observerList.rewindableIterator();
        this.mContainerViewObservers = new ObserverList<>();
    }

    public Context getContext() {
        return this.mContext;
    }

    public ViewGroup getContainerView() {
        return this.mContainerView;
    }

    public WebContents getWebContents() {
        return this.mWebContents;
    }

    public WindowAndroid getWindowAndroid() {
        long j = this.mNativeContentViewCore;
        if (j == 0) {
            return null;
        }
        return nativeGetJavaWindowAndroid(j);
    }

    public void setTopControlsHeight(int i, boolean z) {
        if (i == this.mTopControlsHeightPix && z == this.mTopControlsShrinkBlinkSize) {
            return;
        }
        this.mTopControlsHeightPix = i;
        this.mTopControlsShrinkBlinkSize = z;
        long j = this.mNativeContentViewCore;
        if (j != 0) {
            nativeWasResized(j);
        }
    }

    public ViewAndroidDelegate getViewAndroidDelegate() {
        return this.mViewAndroidDelegate;
    }

    public void setImeAdapterForTest(ImeAdapter imeAdapter) {
        this.mImeAdapter = imeAdapter;
    }

    public ImeAdapter getImeAdapterForTest() {
        return this.mImeAdapter;
    }

    private ImeAdapter createImeAdapter() {
        return new ImeAdapter(new InputMethodManagerWrapper(this.mContext), new ImeAdapter.ImeAdapterDelegate() { // from class: org.chromium.content.browser.ContentViewCore.2
            static final /* synthetic */ boolean $assertionsDisabled = false;

            @Override // org.chromium.content.browser.input.ImeAdapter.ImeAdapterDelegate
            public void onImeEvent() {
                ContentViewCore.this.mPopupZoomer.hide(true);
                ContentViewCore.this.getContentViewClient().onImeEvent();
                if (ContentViewCore.this.mFocusedNodeEditable) {
                    ContentViewCore.this.dismissTextHandles();
                }
            }

            @Override // org.chromium.content.browser.input.ImeAdapter.ImeAdapterDelegate
            public void onKeyboardBoundsUnchanged() {
                ContentViewCore.this.mWebContents.scrollFocusedEditableNodeIntoView();
            }

            @Override // org.chromium.content.browser.input.ImeAdapter.ImeAdapterDelegate
            public boolean performContextMenuAction(int i) {
                switch (i) {
                    case R.id.selectAll:
                        ContentViewCore.this.mWebContents.selectAll();
                        break;
                    case R.id.cut:
                        ContentViewCore.this.mWebContents.cut();
                        break;
                    case R.id.copy:
                        ContentViewCore.this.mWebContents.copy();
                        break;
                    case R.id.paste:
                        ContentViewCore.this.mWebContents.paste();
                        break;
                }
                return true;
            }

            @Override // org.chromium.content.browser.input.ImeAdapter.ImeAdapterDelegate
            public View getAttachedView() {
                return ContentViewCore.this.mContainerView;
            }

            @Override // org.chromium.content.browser.input.ImeAdapter.ImeAdapterDelegate
            public ResultReceiver getNewShowKeyboardReceiver() {
                return ContentViewCore.this.getNewShowKeyboardReceiver();
            }
        });
    }

    public void initialize(ViewGroup viewGroup, InternalAccessDelegate internalAccessDelegate, WebContents webContents, WindowAndroid windowAndroid) {
        createContentViewAndroidDelegate();
        setContainerView(viewGroup);
        long nativePointer = windowAndroid.getNativePointer();
        this.mZoomControlsDelegate = NO_OP_ZOOM_CONTROLS_DELEGATE;
        long jNativeInit = nativeInit(webContents, this.mViewAndroidDelegate, nativePointer, this.mRetainedJavaScriptObjects);
        this.mNativeContentViewCore = jNativeInit;
        this.mWebContents = nativeGetWebContentsAndroid(jNativeInit);
        setContainerViewInternals(internalAccessDelegate);
        this.mRenderCoordinates.reset();
        initPopupZoomer(this.mContext);
        this.mImeAdapter = createImeAdapter();
        attachImeAdapter();
        this.mWebContentsObserver = new ContentViewWebContentsObserver(this);
    }

    public void updateWindowAndroid(WindowAndroid windowAndroid) {
        nativeUpdateWindowAndroid(this.mNativeContentViewCore, windowAndroid == null ? 0L : windowAndroid.getNativePointer());
        this.mSelectPopup = null;
        this.mPastePopupMenu = null;
    }

    public void createContentViewAndroidDelegate() {
        this.mViewAndroidDelegate = new ContentViewAndroidDelegate(this.mContainerView, this.mRenderCoordinates);
    }

    public void setContainerView(ViewGroup viewGroup) {
        try {
            TraceEvent.begin("ContentViewCore.setContainerView");
            if (this.mContainerView != null) {
                this.mPastePopupMenu = null;
                hidePopupsAndClearSelection();
            }
            this.mContainerView = viewGroup;
            viewGroup.setClickable(true);
            this.mViewAndroidDelegate.updateCurrentContainerView(this.mContainerView);
            Iterator<ContainerViewObserver> it = this.mContainerViewObservers.iterator();
            while (it.hasNext()) {
                it.next().onContainerViewChanged(this.mContainerView);
            }
        } finally {
            TraceEvent.end("ContentViewCore.setContainerView");
        }
    }

    public void addContainerViewObserver(ContainerViewObserver containerViewObserver) {
        this.mContainerViewObservers.addObserver(containerViewObserver);
    }

    public void removeContainerViewObserver(ContainerViewObserver containerViewObserver) {
        this.mContainerViewObservers.removeObserver(containerViewObserver);
    }

    private void onNativeContentViewCoreDestroyed(long j) {
        this.mNativeContentViewCore = 0L;
    }

    public void setContainerViewInternals(InternalAccessDelegate internalAccessDelegate) {
        this.mContainerViewInternals = internalAccessDelegate;
    }

    void initPopupZoomer(Context context) {
        PopupZoomer popupZoomer = new PopupZoomer(context);
        this.mPopupZoomer = popupZoomer;
        popupZoomer.setOnVisibilityChangedListener(new PopupZoomer.OnVisibilityChangedListener() { // from class: org.chromium.content.browser.ContentViewCore.3
            private final ViewGroup mContainerViewAtCreation;

            {
                this.mContainerViewAtCreation = ContentViewCore.this.mContainerView;
            }

            @Override // org.chromium.content.browser.PopupZoomer.OnVisibilityChangedListener
            public void onPopupZoomerShown(final PopupZoomer popupZoomer2) {
                this.mContainerViewAtCreation.post(new Runnable() { // from class: org.chromium.content.browser.ContentViewCore.3.1
                    @Override // java.lang.Runnable
                    public void run() {
                        if (AnonymousClass3.this.mContainerViewAtCreation.indexOfChild(popupZoomer2) == -1) {
                            AnonymousClass3.this.mContainerViewAtCreation.addView(popupZoomer2);
                        }
                    }
                });
            }

            @Override // org.chromium.content.browser.PopupZoomer.OnVisibilityChangedListener
            public void onPopupZoomerHidden(final PopupZoomer popupZoomer2) {
                this.mContainerViewAtCreation.post(new Runnable() { // from class: org.chromium.content.browser.ContentViewCore.3.2
                    @Override // java.lang.Runnable
                    public void run() {
                        if (AnonymousClass3.this.mContainerViewAtCreation.indexOfChild(popupZoomer2) != -1) {
                            AnonymousClass3.this.mContainerViewAtCreation.removeView(popupZoomer2);
                            AnonymousClass3.this.mContainerViewAtCreation.invalidate();
                        }
                    }
                });
            }
        });
        this.mPopupZoomer.setOnTapListener(new PopupZoomer.OnTapListener() { // from class: org.chromium.content.browser.ContentViewCore.4
            private final ViewGroup mContainerViewAtCreation;

            {
                this.mContainerViewAtCreation = ContentViewCore.this.mContainerView;
            }

            @Override // org.chromium.content.browser.PopupZoomer.OnTapListener
            public boolean onSingleTap(View view, MotionEvent motionEvent) {
                this.mContainerViewAtCreation.requestFocus();
                if (ContentViewCore.this.mNativeContentViewCore == 0) {
                    return true;
                }
                ContentViewCore contentViewCore = ContentViewCore.this;
                contentViewCore.nativeSingleTap(contentViewCore.mNativeContentViewCore, motionEvent.getEventTime(), motionEvent.getX(), motionEvent.getY());
                return true;
            }

            @Override // org.chromium.content.browser.PopupZoomer.OnTapListener
            public boolean onLongPress(View view, MotionEvent motionEvent) {
                if (ContentViewCore.this.mNativeContentViewCore == 0) {
                    return true;
                }
                ContentViewCore contentViewCore = ContentViewCore.this;
                contentViewCore.nativeLongPress(contentViewCore.mNativeContentViewCore, motionEvent.getEventTime(), motionEvent.getX(), motionEvent.getY());
                return true;
            }
        });
    }

    public void setPopupZoomerForTest(PopupZoomer popupZoomer) {
        this.mPopupZoomer = popupZoomer;
    }

    public void destroy() {
        long j = this.mNativeContentViewCore;
        if (j != 0) {
            nativeOnJavaContentViewCoreDestroyed(j);
        }
        this.mWebContentsObserver.destroy();
        this.mWebContentsObserver = null;
        setSmartClipDataListener(null);
        setZoomControlsDelegate(null);
        this.mImeAdapter.resetAndHideKeyboard();
        this.mContentViewClient = new ContentViewClient();
        this.mWebContents = null;
        this.mOverscrollRefreshHandler = null;
        this.mNativeContentViewCore = 0L;
        this.mJavaScriptInterfaces.clear();
        this.mRetainedJavaScriptObjects.clear();
        this.mGestureStateListenersIterator.rewind();
        while (this.mGestureStateListenersIterator.hasNext()) {
            this.mGestureStateListenersIterator.next().onDestroyed();
        }
        this.mGestureStateListeners.clear();
        ScreenOrientationListener.getInstance().removeObserver(this);
        this.mContainerViewObservers.clear();
        hidePopupsAndPreserveSelection();
        this.mPastePopupMenu = null;
    }

    public boolean isAlive() {
        return this.mNativeContentViewCore != 0;
    }

    long getNativeContentViewCore() {
        return this.mNativeContentViewCore;
    }

    public void setContentViewClient(ContentViewClient contentViewClient) {
        if (contentViewClient == null) {
            throw new IllegalArgumentException("The client can't be null.");
        }
        this.mContentViewClient = contentViewClient;
    }

    public ContentViewClient getContentViewClient() {
        if (this.mContentViewClient == null) {
            this.mContentViewClient = new ContentViewClient();
        }
        return this.mContentViewClient;
    }

    private void onBackgroundColorChanged(int i) {
        getContentViewClient().onBackgroundColorChanged(i);
    }

    public int getViewportWidthPix() {
        return this.mViewportWidthPix;
    }

    public int getViewportHeightPix() {
        return this.mViewportHeightPix;
    }

    public int getViewportHeightWithOSKHiddenPix() {
        return this.mViewportHeightPix + getContentViewClient().getSystemWindowInsetBottom();
    }

    private int getPhysicalBackingWidthPix() {
        return this.mPhysicalBackingWidthPix;
    }

    private int getPhysicalBackingHeightPix() {
        return this.mPhysicalBackingHeightPix;
    }

    public boolean doTopControlsShrinkBlinkSize() {
        return this.mTopControlsShrinkBlinkSize;
    }

    public int getTopControlsHeightPix() {
        return this.mTopControlsHeightPix;
    }

    public float getDeviceScaleFactor() {
        return this.mRenderCoordinates.getDeviceScaleFactor();
    }

    public float getPageScaleFactor() {
        return this.mRenderCoordinates.getPageScaleFactor();
    }

    public float getContentHeightCss() {
        return this.mRenderCoordinates.getContentHeightCss();
    }

    public float getContentWidthCss() {
        return this.mRenderCoordinates.getContentWidthCss();
    }

    public String getSelectedText() {
        return this.mHasSelection ? this.mLastSelectedText : "";
    }

    public boolean isSelectionEditable() {
        if (this.mHasSelection) {
            return this.mFocusedNodeEditable;
        }
        return false;
    }

    public boolean isFocusedNodeEditable() {
        return this.mFocusedNodeEditable;
    }

    public boolean isGamepadAPIActive() {
        return GamepadList.isGamepadAPIActive();
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        return onTouchEventImpl(motionEvent, false);
    }

    public boolean onTouchHandleEvent(MotionEvent motionEvent) {
        return onTouchEventImpl(motionEvent, true);
    }

    private boolean onTouchEventImpl(MotionEvent motionEvent, boolean z) {
        MotionEvent motionEventCreateOffsetMotionEvent;
        MotionEvent motionEvent2;
        TraceEvent.begin("onTouchEvent");
        try {
            int actionMasked = motionEvent.getActionMasked();
            if (actionMasked == 0) {
                cancelRequestToScrollFocusedEditableNodeIntoView();
            }
            if (SPenSupport.isSPenSupported(this.mContext)) {
                actionMasked = SPenSupport.convertSPenEventAction(actionMasked);
            }
            int i = actionMasked;
            if (!isValidTouchEventActionForNative(i)) {
                return false;
            }
            if (this.mNativeContentViewCore == 0) {
                return false;
            }
            if (this.mCurrentTouchOffsetX == 0.0f && this.mCurrentTouchOffsetY == 0.0f) {
                motionEventCreateOffsetMotionEvent = null;
                motionEvent2 = motionEvent;
            } else {
                motionEventCreateOffsetMotionEvent = createOffsetMotionEvent(motionEvent);
                motionEvent2 = motionEventCreateOffsetMotionEvent;
            }
            int pointerCount = motionEvent2.getPointerCount();
            float[] fArr = new float[2];
            fArr[0] = motionEvent2.getTouchMajor();
            fArr[1] = pointerCount > 1 ? motionEvent2.getTouchMajor(1) : 0.0f;
            float[] fArr2 = new float[2];
            fArr2[0] = motionEvent2.getTouchMinor();
            fArr2[1] = pointerCount > 1 ? motionEvent2.getTouchMinor(1) : 0.0f;
            for (int i2 = 0; i2 < 2; i2++) {
                float f = fArr[i2];
                float f2 = fArr2[i2];
                if (f < f2) {
                    fArr[i2] = f2;
                    fArr2[i2] = f;
                }
            }
            boolean zNativeOnTouchEvent = nativeOnTouchEvent(this.mNativeContentViewCore, motionEvent2, motionEvent2.getEventTime(), i, pointerCount, motionEvent2.getHistorySize(), motionEvent2.getActionIndex(), motionEvent2.getX(), motionEvent2.getY(), pointerCount > 1 ? motionEvent2.getX(1) : 0.0f, pointerCount > 1 ? motionEvent2.getY(1) : 0.0f, motionEvent2.getPointerId(0), pointerCount > 1 ? motionEvent2.getPointerId(1) : -1, fArr[0], fArr[1], fArr2[0], fArr2[1], motionEvent2.getOrientation(), pointerCount > 1 ? motionEvent2.getOrientation(1) : 0.0f, motionEvent2.getAxisValue(25), pointerCount > 1 ? motionEvent2.getAxisValue(25, 1) : 0.0f, motionEvent2.getRawX(), motionEvent2.getRawY(), motionEvent2.getToolType(0), pointerCount > 1 ? motionEvent2.getToolType(1) : 0, motionEvent2.getButtonState(), motionEvent2.getMetaState(), z);
            if (motionEventCreateOffsetMotionEvent != null) {
                motionEventCreateOffsetMotionEvent.recycle();
            }
            return zNativeOnTouchEvent;
        } finally {
            TraceEvent.end("onTouchEvent");
        }
    }

    private void requestDisallowInterceptTouchEvent() {
        this.mContainerView.requestDisallowInterceptTouchEvent(true);
    }

    public boolean isScrollInProgress() {
        return this.mTouchScrollInProgress || this.mPotentiallyActiveFlingCount > 0;
    }

    private void setTouchScrollInProgress(boolean z) {
        if (this.mTouchScrollInProgress == z) {
            return;
        }
        this.mTouchScrollInProgress = z;
        updateActionModeVisibility();
    }

    private void onFlingStartEventConsumed(int i, int i2) {
        this.mPotentiallyActiveFlingCount++;
        setTouchScrollInProgress(false);
        this.mGestureStateListenersIterator.rewind();
        while (this.mGestureStateListenersIterator.hasNext()) {
            this.mGestureStateListenersIterator.next().onFlingStartGesture(i, i2, computeVerticalScrollOffset(), computeVerticalScrollExtent());
        }
    }

    private void onFlingCancelEventAck() {
        updateGestureStateListener(10);
    }

    private void onScrollBeginEventAck() {
        setTouchScrollInProgress(true);
        hidePastePopup();
        this.mZoomControlsDelegate.invokeZoomPicker();
        updateGestureStateListener(6);
    }

    private void onScrollUpdateGestureConsumed() {
        this.mZoomControlsDelegate.invokeZoomPicker();
        this.mGestureStateListenersIterator.rewind();
        while (this.mGestureStateListenersIterator.hasNext()) {
            this.mGestureStateListenersIterator.next().onScrollUpdateGestureConsumed();
        }
    }

    private void onScrollEndEventAck() {
        setTouchScrollInProgress(false);
        updateGestureStateListener(8);
    }

    private void onPinchBeginEventAck() {
        updateGestureStateListener(12);
    }

    private void onPinchEndEventAck() {
        updateGestureStateListener(14);
    }

    private void onSingleTapEventAck(boolean z, int i, int i2) {
        this.mGestureStateListenersIterator.rewind();
        while (this.mGestureStateListenersIterator.hasNext()) {
            this.mGestureStateListenersIterator.next().onSingleTap(z, i, i2);
        }
        hidePastePopup();
    }

    private void onShowUnhandledTapUIIfNeeded(int i, int i2) {
        ContextualSearchClient contextualSearchClient = this.mContextualSearchClient;
        if (contextualSearchClient != null) {
            contextualSearchClient.showUnhandledTapUIIfNeeded(i, i2);
        }
    }

    private boolean filterTapOrPressEvent(int i, int i2, int i3) {
        if (i == 5 && offerLongPressToEmbedder()) {
            return true;
        }
        updateForTapOrPress(i, i2, i3);
        return false;
    }

    public void sendDoubleTapForTest(long j, int i, int i2) {
        long j2 = this.mNativeContentViewCore;
        if (j2 == 0) {
            return;
        }
        nativeDoubleTap(j2, j, i, i2);
    }

    public void flingViewport(long j, int i, int i2) {
        long j2 = this.mNativeContentViewCore;
        if (j2 == 0) {
            return;
        }
        nativeFlingCancel(j2, j);
        float f = i;
        float f2 = i2;
        nativeScrollBegin(this.mNativeContentViewCore, j, 0.0f, 0.0f, f, f2, true);
        nativeFlingStart(this.mNativeContentViewCore, j, 0.0f, 0.0f, f, f2, true);
    }

    public void cancelFling(long j) {
        long j2 = this.mNativeContentViewCore;
        if (j2 == 0) {
            return;
        }
        nativeFlingCancel(j2, j);
    }

    public void addGestureStateListener(GestureStateListener gestureStateListener) {
        this.mGestureStateListeners.addObserver(gestureStateListener);
    }

    public void removeGestureStateListener(GestureStateListener gestureStateListener) {
        this.mGestureStateListeners.removeObserver(gestureStateListener);
    }

    void updateGestureStateListener(int i) {
        this.mGestureStateListenersIterator.rewind();
        while (this.mGestureStateListenersIterator.hasNext()) {
            GestureStateListener next = this.mGestureStateListenersIterator.next();
            if (i == 6) {
                next.onScrollStarted(computeVerticalScrollOffset(), computeVerticalScrollExtent());
            } else if (i == 8) {
                next.onScrollEnded(computeVerticalScrollOffset(), computeVerticalScrollExtent());
            } else if (i == 14) {
                next.onPinchEnded();
            } else if (i == 11) {
                next.onFlingEndGesture(computeVerticalScrollOffset(), computeVerticalScrollExtent());
            } else if (i == 12) {
                next.onPinchStarted();
            }
        }
    }

    public void onShow() {
        this.mWebContents.onShow();
        setAccessibilityState(this.mAccessibilityManager.isEnabled());
        restoreSelectionPopupsIfNecessary();
    }

    public int getCurrentRenderProcessId() {
        return nativeGetCurrentRenderProcessId(this.mNativeContentViewCore);
    }

    public void onHide() {
        hidePopupsAndPreserveSelection();
        this.mWebContents.onHide();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void hidePopupsAndClearSelection() {
        this.mUnselectAllOnActionModeDismiss = true;
        hidePopups();
    }

    private void hidePopupsAndPreserveSelection() {
        this.mUnselectAllOnActionModeDismiss = false;
        hidePopups();
    }

    private void hidePopups() {
        hideSelectActionMode();
        hidePastePopup();
        hideSelectPopupWithCancelMesage();
        this.mPopupZoomer.hide(false);
        if (this.mUnselectAllOnActionModeDismiss) {
            dismissTextHandles();
        }
    }

    private void restoreSelectionPopupsIfNecessary() {
        if (this.mHasSelection && this.mActionMode == null) {
            showSelectActionMode(true);
        }
    }

    public void hideSelectActionMode() {
        WebActionMode webActionMode = this.mActionMode;
        if (webActionMode != null) {
            webActionMode.finish();
            this.mActionMode = null;
        }
    }

    public boolean isSelectActionBarShowing() {
        return this.mActionMode != null;
    }

    private void resetGestureDetection() {
        long j = this.mNativeContentViewCore;
        if (j == 0) {
            return;
        }
        nativeResetGestureDetection(j);
    }

    public void onAttachedToWindow() {
        setAccessibilityState(this.mAccessibilityManager.isEnabled());
        updateTextSelectionUI(true);
        ScreenOrientationListener.getInstance().addObserver(this, this.mContext);
        GamepadList.onAttachedToWindow(this.mContext);
        this.mAccessibilityManager.addAccessibilityStateChangeListener(this);
        this.mSystemCaptioningBridge.addListener(this);
        this.mImeAdapter.onViewAttachedToWindow();
    }

    public void updateTextSelectionUI(boolean z) {
        setTextHandlesTemporarilyHidden(!z);
        if (z) {
            restoreSelectionPopupsIfNecessary();
        } else {
            hidePopupsAndPreserveSelection();
        }
    }

    public void onDetachedFromWindow() {
        this.mImeAdapter.onViewDetachedFromWindow();
        this.mZoomControlsDelegate.dismissZoomPicker();
        ScreenOrientationListener.getInstance().removeObserver(this);
        GamepadList.onDetachedFromWindow();
        this.mAccessibilityManager.removeAccessibilityStateChangeListener(this);
        updateTextSelectionUI(false);
        this.mSystemCaptioningBridge.removeListener(this);
    }

    public void onVisibilityChanged(View view, int i) {
        if (i != 0) {
            this.mZoomControlsDelegate.dismissZoomPicker();
        }
    }

    public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        return this.mImeAdapter.onCreateInputConnection(editorInfo);
    }

    public boolean onCheckIsTextEditor() {
        return this.mImeAdapter.hasTextInputType();
    }

    public void onConfigurationChanged(Configuration configuration) {
        try {
            TraceEvent.begin("ContentViewCore.onConfigurationChanged");
            this.mImeAdapter.onKeyboardConfigurationChanged(configuration);
            this.mContainerViewInternals.super_onConfigurationChanged(configuration);
            this.mContainerView.requestLayout();
        } finally {
            TraceEvent.end("ContentViewCore.onConfigurationChanged");
        }
    }

    public void onSizeChanged(int i, int i2, int i3, int i4) {
        if (getViewportWidthPix() == i && getViewportHeightPix() == i2) {
            return;
        }
        this.mViewportWidthPix = i;
        this.mViewportHeightPix = i2;
        long j = this.mNativeContentViewCore;
        if (j != 0) {
            nativeWasResized(j);
        }
        updateAfterSizeChanged();
    }

    public void onPhysicalBackingSizeChanged(int i, int i2) {
        if (this.mPhysicalBackingWidthPix == i && this.mPhysicalBackingHeightPix == i2) {
            return;
        }
        this.mPhysicalBackingWidthPix = i;
        this.mPhysicalBackingHeightPix = i2;
        long j = this.mNativeContentViewCore;
        if (j != 0) {
            nativeWasResized(j);
        }
    }

    private void updateAfterSizeChanged() {
        this.mPopupZoomer.hide(false);
        if (this.mFocusPreOSKViewportRect.isEmpty()) {
            return;
        }
        Rect rect = new Rect();
        getContainerView().getWindowVisibleDisplayFrame(rect);
        if (rect.equals(this.mFocusPreOSKViewportRect)) {
            return;
        }
        if (rect.width() == this.mFocusPreOSKViewportRect.width()) {
            this.mWebContents.scrollFocusedEditableNodeIntoView();
        }
        cancelRequestToScrollFocusedEditableNodeIntoView();
    }

    private void cancelRequestToScrollFocusedEditableNodeIntoView() {
        this.mFocusPreOSKViewportRect.setEmpty();
    }

    public void onWindowFocusChanged(boolean z) {
        this.mImeAdapter.onWindowFocusChanged(z);
        if (!z) {
            resetGestureDetection();
        }
        WebActionMode webActionMode = this.mActionMode;
        if (webActionMode != null) {
            webActionMode.onWindowFocusChanged(z);
        }
        this.mGestureStateListenersIterator.rewind();
        while (this.mGestureStateListenersIterator.hasNext()) {
            this.mGestureStateListenersIterator.next().onWindowFocusChanged(z);
        }
    }

    public void onFocusChanged(boolean z) {
        this.mImeAdapter.onViewFocusChanged(z);
        this.mJoystickScrollProvider.setEnabled(z && !this.mFocusedNodeEditable);
        if (z) {
            restoreSelectionPopupsIfNecessary();
        } else {
            cancelRequestToScrollFocusedEditableNodeIntoView();
            if (this.mPreserveSelectionOnNextLossOfFocus) {
                this.mPreserveSelectionOnNextLossOfFocus = false;
                hidePopupsAndPreserveSelection();
            } else {
                hidePopupsAndClearSelection();
                clearSelection();
            }
        }
        long j = this.mNativeContentViewCore;
        if (j != 0) {
            nativeSetFocus(j, z);
        }
    }

    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        if (this.mPopupZoomer.isShowing() && i == 4) {
            this.mPopupZoomer.hide(true);
            return true;
        }
        return this.mContainerViewInternals.super_onKeyUp(i, keyEvent);
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        if (GamepadList.dispatchKeyEvent(keyEvent)) {
            return true;
        }
        if (getContentViewClient().shouldOverrideKeyEvent(keyEvent)) {
            return this.mContainerViewInternals.super_dispatchKeyEvent(keyEvent);
        }
        if (this.mImeAdapter.dispatchKeyEvent(keyEvent)) {
            return true;
        }
        return this.mContainerViewInternals.super_dispatchKeyEvent(keyEvent);
    }

    public boolean onHoverEvent(MotionEvent motionEvent) {
        TraceEvent.begin("onHoverEvent");
        MotionEvent motionEventCreateOffsetMotionEvent = createOffsetMotionEvent(motionEvent);
        try {
            BrowserAccessibilityManager browserAccessibilityManager = this.mBrowserAccessibilityManager;
            if (browserAccessibilityManager != null && !this.mIsObscuredByAnotherView) {
                return browserAccessibilityManager.onHoverEvent(motionEventCreateOffsetMotionEvent);
            }
            if (!this.mTouchExplorationEnabled || motionEventCreateOffsetMotionEvent.getAction() != 10) {
                if (motionEvent.getToolType(0) == 1) {
                    if (this.mEnableTouchHover == null) {
                        this.mEnableTouchHover = Boolean.valueOf(CommandLine.getInstance().hasSwitch(ContentSwitches.ENABLE_TOUCH_HOVER));
                    }
                    if (!this.mEnableTouchHover.booleanValue()) {
                        return false;
                    }
                }
                this.mContainerView.removeCallbacks(this.mFakeMouseMoveRunnable);
                long j = this.mNativeContentViewCore;
                if (j != 0) {
                    nativeSendMouseMoveEvent(j, motionEventCreateOffsetMotionEvent.getEventTime(), motionEventCreateOffsetMotionEvent.getX(), motionEventCreateOffsetMotionEvent.getY(), motionEvent.getToolType(0));
                }
            }
            return true;
        } finally {
            motionEventCreateOffsetMotionEvent.recycle();
            TraceEvent.end("onHoverEvent");
        }
    }

    public boolean onGenericMotionEvent(MotionEvent motionEvent) {
        if (GamepadList.onGenericMotionEvent(motionEvent)) {
            return true;
        }
        if ((motionEvent.getSource() & 2) != 0) {
            this.mLastFocalEventX = motionEvent.getX();
            this.mLastFocalEventY = motionEvent.getY();
            if (motionEvent.getAction() == 8) {
                long j = this.mNativeContentViewCore;
                if (j == 0) {
                    return false;
                }
                nativeSendMouseWheelEvent(j, motionEvent.getEventTime(), motionEvent.getX(), motionEvent.getY(), motionEvent.getAxisValue(10), motionEvent.getAxisValue(9), getWheelScrollFactorInPixels());
                this.mContainerView.removeCallbacks(this.mFakeMouseMoveRunnable);
                final MotionEvent motionEventObtain = MotionEvent.obtain(motionEvent);
                Runnable runnable = new Runnable() { // from class: org.chromium.content.browser.ContentViewCore.5
                    @Override // java.lang.Runnable
                    public void run() {
                        ContentViewCore.this.onHoverEvent(motionEventObtain);
                        motionEventObtain.recycle();
                    }
                };
                this.mFakeMouseMoveRunnable = runnable;
                this.mContainerView.postDelayed(runnable, 250L);
                return true;
            }
        } else if ((motionEvent.getSource() & 16) != 0) {
            if (this.mJoystickScrollProvider.onMotion(motionEvent)) {
                return true;
            }
            if (this.mJoystickZoomProvider == null) {
                this.mJoystickZoomProvider = new JoystickZoomProvider(this, new SystemAnimationIntervalProvider());
            }
            if (this.mJoystickZoomProvider.onMotion(motionEvent)) {
                return true;
            }
        }
        return this.mContainerViewInternals.super_onGenericMotionEvent(motionEvent);
    }

    public void setCurrentMotionEventOffsets(float f, float f2) {
        this.mCurrentTouchOffsetX = f;
        this.mCurrentTouchOffsetY = f2;
    }

    private MotionEvent createOffsetMotionEvent(MotionEvent motionEvent) {
        MotionEvent motionEventObtain = MotionEvent.obtain(motionEvent);
        motionEventObtain.offsetLocation(this.mCurrentTouchOffsetX, this.mCurrentTouchOffsetY);
        return motionEventObtain;
    }

    public void scrollBy(float f, float f2, boolean z) {
        if (this.mNativeContentViewCore == 0) {
            return;
        }
        if (f == 0.0f && f2 == 0.0f) {
            return;
        }
        long jUptimeMillis = SystemClock.uptimeMillis();
        if (this.mPotentiallyActiveFlingCount > 0) {
            nativeFlingCancel(this.mNativeContentViewCore, jUptimeMillis);
        }
        float f3 = z ? this.mLastFocalEventX : 0.0f;
        float f4 = z ? this.mLastFocalEventY : 0.0f;
        float f5 = f3;
        nativeScrollBegin(this.mNativeContentViewCore, jUptimeMillis, f5, f4, -f, -f2, !z);
        nativeScrollBy(this.mNativeContentViewCore, jUptimeMillis, f5, f4, f, f2);
        nativeScrollEnd(this.mNativeContentViewCore, jUptimeMillis);
    }

    public void scrollTo(float f, float f2) {
        if (this.mNativeContentViewCore == 0) {
            return;
        }
        scrollBy(f - this.mRenderCoordinates.getScrollXPix(), f2 - this.mRenderCoordinates.getScrollYPix(), false);
    }

    public int getNativeScrollXForTest() {
        return this.mRenderCoordinates.getScrollXPixInt();
    }

    public int getNativeScrollYForTest() {
        return this.mRenderCoordinates.getScrollYPixInt();
    }

    public int computeHorizontalScrollExtent() {
        return this.mRenderCoordinates.getLastFrameViewportWidthPixInt();
    }

    public int computeHorizontalScrollOffset() {
        return this.mRenderCoordinates.getScrollXPixInt();
    }

    public int computeHorizontalScrollRange() {
        return this.mRenderCoordinates.getContentWidthPixInt();
    }

    public int computeVerticalScrollExtent() {
        return this.mRenderCoordinates.getLastFrameViewportHeightPixInt();
    }

    public int computeVerticalScrollOffset() {
        return this.mRenderCoordinates.getScrollYPixInt();
    }

    public int computeVerticalScrollRange() {
        return this.mRenderCoordinates.getContentHeightPixInt();
    }

    public boolean awakenScrollBars(int i, boolean z) {
        if (this.mContainerView.getScrollBarStyle() == 0) {
            return false;
        }
        return this.mContainerViewInternals.super_awakenScrollBars(i, z);
    }

    private void updateForTapOrPress(int i, float f, float f2) {
        if (i == 3 || i == 2 || i == 5 || i == 16) {
            if (this.mContainerView.isFocusable() && this.mContainerView.isFocusableInTouchMode() && !this.mContainerView.isFocused()) {
                this.mContainerView.requestFocus();
            }
            if (!this.mPopupZoomer.isShowing()) {
                this.mPopupZoomer.setLastTouch(f, f2);
            }
            this.mLastFocalEventX = f;
            this.mLastFocalEventY = f2;
        }
    }

    public void setZoomControlsDelegate(ZoomControlsDelegate zoomControlsDelegate) {
        if (zoomControlsDelegate == null) {
            this.mZoomControlsDelegate = NO_OP_ZOOM_CONTROLS_DELEGATE;
        } else {
            this.mZoomControlsDelegate = zoomControlsDelegate;
        }
    }

    public void updateMultiTouchZoomSupport(boolean z) {
        long j = this.mNativeContentViewCore;
        if (j == 0) {
            return;
        }
        nativeSetMultiTouchZoomSupportEnabled(j, z);
    }

    public void updateDoubleTapSupport(boolean z) {
        long j = this.mNativeContentViewCore;
        if (j == 0) {
            return;
        }
        nativeSetDoubleTapSupportEnabled(j, z);
    }

    public void selectPopupMenuItems(int[] iArr) {
        long j = this.mNativeContentViewCore;
        if (j != 0) {
            nativeSelectPopupMenuItems(j, this.mNativeSelectPopupSourceFrame, iArr);
        }
        this.mNativeSelectPopupSourceFrame = 0L;
        this.mSelectPopup = null;
    }

    void sendOrientationChangeEvent(int i) {
        long j = this.mNativeContentViewCore;
        if (j == 0) {
            return;
        }
        nativeSendOrientationChangeEvent(j, i);
    }

    public WebActionModeCallback.ActionHandler getSelectActionHandler() {
        return this.mActionHandler;
    }

    private void showSelectActionMode(boolean z) {
        ActionMode actionModeStartActionMode;
        WebActionMode webActionMode = this.mActionMode;
        if (webActionMode != null) {
            webActionMode.invalidate();
            return;
        }
        if (this.mActionHandler == null) {
            this.mActionHandler = new WebActionModeCallback.ActionHandler() { // from class: org.chromium.content.browser.ContentViewCore.6
                static final /* synthetic */ boolean $assertionsDisabled = false;
                private static final int MAX_SEARCH_QUERY_LENGTH = 1000;
                private static final int MAX_SHARE_QUERY_LENGTH = 100000;

                @Override // org.chromium.content.browser.WebActionModeCallback.ActionHandler
                public void selectAll() {
                    ContentViewCore.this.mWebContents.selectAll();
                    if (ContentViewCore.this.isFocusedNodeEditable()) {
                        RecordUserAction.record("MobileActionMode.SelectAllWasEditable");
                    } else {
                        RecordUserAction.record("MobileActionMode.SelectAllWasNonEditable");
                    }
                }

                @Override // org.chromium.content.browser.WebActionModeCallback.ActionHandler
                public void cut() {
                    ContentViewCore.this.mWebContents.cut();
                }

                @Override // org.chromium.content.browser.WebActionModeCallback.ActionHandler
                public void copy() {
                    ContentViewCore.this.mWebContents.copy();
                }

                @Override // org.chromium.content.browser.WebActionModeCallback.ActionHandler
                public void paste() {
                    ContentViewCore.this.mWebContents.paste();
                }

                @Override // org.chromium.content.browser.WebActionModeCallback.ActionHandler
                public void share() {
                    RecordUserAction.record("MobileActionMode.Share");
                    String strSanitizeQuery = sanitizeQuery(ContentViewCore.this.getSelectedText(), MAX_SHARE_QUERY_LENGTH);
                    if (TextUtils.isEmpty(strSanitizeQuery)) {
                        return;
                    }
                    Intent intent = new Intent("android.intent.action.SEND");
                    intent.setType(AssetHelper.DEFAULT_MIME_TYPE);
                    intent.putExtra("android.intent.extra.TEXT", strSanitizeQuery);
                    try {
                        Intent intentCreateChooser = Intent.createChooser(intent, ContentViewCore.this.getContext().getString(org.chromium.content.R.string.actionbar_share));
                        intentCreateChooser.setFlags(268435456);
                        ContentViewCore.this.getContext().startActivity(intentCreateChooser);
                    } catch (ActivityNotFoundException unused) {
                    }
                }

                @Override // org.chromium.content.browser.WebActionModeCallback.ActionHandler
                public void processText(Intent intent) {
                    RecordUserAction.record("MobileActionMode.ProcessTextIntent");
                    String strSanitizeQuery = sanitizeQuery(ContentViewCore.this.getSelectedText(), 1000);
                    if (TextUtils.isEmpty(strSanitizeQuery)) {
                        return;
                    }
                    intent.putExtra("android.intent.extra.PROCESS_TEXT", strSanitizeQuery);
                    try {
                        if (ContentViewCore.this.getContentViewClient().doesPerformProcessText()) {
                            ContentViewCore.this.getContentViewClient().startProcessTextIntent(intent);
                        } else {
                            ContentViewCore.this.getWindowAndroid().showIntent(intent, new WindowAndroid.IntentCallback() { // from class: org.chromium.content.browser.ContentViewCore.6.1
                                @Override // org.chromium.ui.base.WindowAndroid.IntentCallback
                                public void onIntentCompleted(WindowAndroid windowAndroid, int i, ContentResolver contentResolver, Intent intent2) {
                                    ContentViewCore.this.onReceivedProcessTextResult(i, intent2);
                                }
                            }, (Integer) null);
                        }
                    } catch (ActivityNotFoundException unused) {
                    }
                }

                @Override // org.chromium.content.browser.WebActionModeCallback.ActionHandler
                public void search() {
                    RecordUserAction.record("MobileActionMode.WebSearch");
                    String strSanitizeQuery = sanitizeQuery(ContentViewCore.this.getSelectedText(), 1000);
                    if (TextUtils.isEmpty(strSanitizeQuery)) {
                        return;
                    }
                    if (ContentViewCore.this.getContentViewClient().doesPerformWebSearch()) {
                        ContentViewCore.this.getContentViewClient().performWebSearch(strSanitizeQuery);
                        return;
                    }
                    Intent intent = new Intent("android.intent.action.WEB_SEARCH");
                    intent.putExtra("new_search", true);
                    intent.putExtra(SearchIntents.EXTRA_QUERY, strSanitizeQuery);
                    intent.putExtra("com.android.browser.application_id", ContentViewCore.this.getContext().getPackageName());
                    intent.addFlags(268435456);
                    try {
                        ContentViewCore.this.getContext().startActivity(intent);
                    } catch (ActivityNotFoundException unused) {
                    }
                }

                @Override // org.chromium.content.browser.WebActionModeCallback.ActionHandler
                public boolean isSelectionPassword() {
                    return ContentViewCore.this.mFocusedNodeIsPassword;
                }

                @Override // org.chromium.content.browser.WebActionModeCallback.ActionHandler
                public boolean isSelectionEditable() {
                    return ContentViewCore.this.mFocusedNodeEditable;
                }

                @Override // org.chromium.content.browser.WebActionModeCallback.ActionHandler
                public boolean isInsertion() {
                    return ContentViewCore.this.mHasInsertion;
                }

                @Override // org.chromium.content.browser.WebActionModeCallback.ActionHandler
                public void onDestroyActionMode() {
                    ContentViewCore.this.mActionMode = null;
                    if (ContentViewCore.this.mUnselectAllOnActionModeDismiss) {
                        ContentViewCore.this.dismissTextHandles();
                        ContentViewCore.this.clearSelection();
                    }
                    if (ContentViewCore.this.supportsFloatingActionMode()) {
                        return;
                    }
                    ContentViewCore.this.getContentViewClient().onContextualActionBarHidden();
                }

                @Override // org.chromium.content.browser.WebActionModeCallback.ActionHandler
                public void onGetContentRect(Rect rect) {
                    rect.set(ContentViewCore.this.mSelectionRect);
                    rect.offset(0, (int) ContentViewCore.this.mRenderCoordinates.getContentOffsetYPix());
                }

                @Override // org.chromium.content.browser.WebActionModeCallback.ActionHandler
                public boolean isIncognito() {
                    return ContentViewCore.this.mWebContents.isIncognito();
                }

                @Override // org.chromium.content.browser.WebActionModeCallback.ActionHandler
                public boolean isSelectActionModeAllowed(int i) {
                    boolean zIsSelectActionModeAllowed = ContentViewCore.this.getContentViewClient().isSelectActionModeAllowed(i);
                    return i == 1 ? zIsSelectActionModeAllowed && isShareAvailable() : i == 2 ? zIsSelectActionModeAllowed && isWebSearchAvailable() : zIsSelectActionModeAllowed;
                }

                private boolean isShareAvailable() {
                    Intent intent = new Intent("android.intent.action.SEND");
                    intent.setType(AssetHelper.DEFAULT_MIME_TYPE);
                    return ContentViewCore.this.getContext().getPackageManager().queryIntentActivities(intent, 65536).size() > 0;
                }

                private boolean isWebSearchAvailable() {
                    if (ContentViewCore.this.getContentViewClient().doesPerformWebSearch()) {
                        return true;
                    }
                    Intent intent = new Intent("android.intent.action.WEB_SEARCH");
                    intent.putExtra("new_search", true);
                    return ContentViewCore.this.getContext().getPackageManager().queryIntentActivities(intent, 65536).size() > 0;
                }

                private String sanitizeQuery(String str, int i) {
                    if (TextUtils.isEmpty(str) || str.length() < i) {
                        return str;
                    }
                    Log.w(ContentViewCore.TAG, "Truncating oversized query (" + str.length() + ").", new Object[0]);
                    return str.substring(0, i) + "…";
                }
            };
        }
        this.mActionMode = null;
        if (this.mContainerView.getParent() != null && (actionModeStartActionMode = startActionMode(z)) != null) {
            this.mActionMode = new WebActionMode(actionModeStartActionMode, this.mContainerView);
        }
        this.mUnselectAllOnActionModeDismiss = true;
        if (this.mActionMode == null) {
            clearSelection();
        } else {
            if (supportsFloatingActionMode()) {
                return;
            }
            getContentViewClient().onContextualActionBarShown();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean supportsFloatingActionMode() {
        return !this.mFloatingActionModeCreationFailed;
    }

    private ActionMode startActionMode(boolean z) {
        WebActionModeCallback webActionModeCallback = new WebActionModeCallback(this.mContainerView.getContext(), this.mActionHandler);
        if (supportsFloatingActionMode()) {
            ActionMode actionModeStartFloatingActionMode = startFloatingActionMode(webActionModeCallback);
            if (actionModeStartFloatingActionMode != null) {
                return actionModeStartFloatingActionMode;
            }
            this.mFloatingActionModeCreationFailed = true;
            if (!z) {
                return null;
            }
        }
        return startDefaultActionMode(webActionModeCallback);
    }

    private ActionMode startDefaultActionMode(WebActionModeCallback webActionModeCallback) {
        return this.mContainerView.startActionMode(webActionModeCallback);
    }

    private ActionMode startFloatingActionMode(WebActionModeCallback webActionModeCallback) {
        return this.mContainerView.startActionMode(new FloatingWebActionModeCallback(webActionModeCallback), 1);
    }

    private void invalidateActionModeContentRect() {
        WebActionMode webActionMode = this.mActionMode;
        if (webActionMode != null) {
            webActionMode.invalidateContentRect();
        }
    }

    private void updateActionModeVisibility() {
        WebActionMode webActionMode = this.mActionMode;
        if (webActionMode == null) {
            return;
        }
        webActionMode.hide(this.mDraggingSelection || this.mTouchScrollInProgress);
    }

    public void clearSelection() {
        if (this.mFocusedNodeEditable) {
            this.mImeAdapter.moveCursorToSelectionEnd();
            return;
        }
        WebContents webContents = this.mWebContents;
        if (webContents != null) {
            webContents.unselect();
        }
    }

    public void preserveSelectionOnNextLossOfFocus() {
        this.mPreserveSelectionOnNextLossOfFocus = true;
    }

    public boolean hasSelection() {
        return this.mHasSelection;
    }

    public boolean hasInsertion() {
        return this.mHasInsertion;
    }

    private void onSelectionEvent(int i, int i2, int i3, int i4, int i5, int i6, int i7) {
        if (i4 == i6) {
            i6++;
        }
        if (i5 == i7) {
            i7++;
        }
        switch (i) {
            case 0:
                this.mSelectionRect.set(i4, i5, i6, i7);
                this.mHasSelection = true;
                this.mUnselectAllOnActionModeDismiss = true;
                showSelectActionMode(true);
                break;
            case 1:
                this.mSelectionRect.set(i4, i5, i6, i7);
                invalidateActionModeContentRect();
                break;
            case 2:
                this.mHasSelection = false;
                this.mDraggingSelection = false;
                this.mUnselectAllOnActionModeDismiss = false;
                hideSelectActionMode();
                this.mSelectionRect.setEmpty();
                break;
            case 3:
                this.mDraggingSelection = true;
                updateActionModeVisibility();
                break;
            case 4:
                this.mDraggingSelection = false;
                updateActionModeVisibility();
                break;
            case 5:
                this.mSelectionRect.set(i4, i5, i6, i7);
                this.mHasInsertion = true;
                break;
            case 6:
                this.mSelectionRect.set(i4, i5, i6, i7);
                if (!isScrollInProgress() && isPastePopupShowing()) {
                    showPastePopup(i2, i3);
                } else {
                    hidePastePopup();
                }
                break;
            case 7:
                if (this.mWasPastePopupShowingOnInsertionDragStart) {
                    hidePastePopup();
                } else {
                    showPastePopup(i2, i3);
                }
                this.mWasPastePopupShowingOnInsertionDragStart = false;
                break;
            case 8:
                hidePastePopup();
                this.mHasInsertion = false;
                this.mSelectionRect.setEmpty();
                break;
            case 9:
                this.mWasPastePopupShowingOnInsertionDragStart = isPastePopupShowing();
                hidePastePopup();
                break;
            case 10:
                if (this.mWasPastePopupShowingOnInsertionDragStart) {
                    showPastePopup(i2, i3);
                }
                this.mWasPastePopupShowingOnInsertionDragStart = false;
                break;
        }
        ContextualSearchClient contextualSearchClient = this.mContextualSearchClient;
        if (contextualSearchClient != null) {
            contextualSearchClient.onSelectionEvent(i, i2, i3);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dismissTextHandles() {
        long j = this.mNativeContentViewCore;
        if (j != 0) {
            nativeDismissTextHandles(j);
        }
    }

    private void setTextHandlesTemporarilyHidden(boolean z) {
        long j = this.mNativeContentViewCore;
        if (j == 0) {
            return;
        }
        nativeSetTextHandlesTemporarilyHidden(j, z);
    }

    private void updateFrameInfo(float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, float f11, boolean z, boolean z2, boolean z3, float f12, float f13, float f14) {
        TraceEvent.begin("ContentViewCore:updateFrameInfo");
        this.mIsMobileOptimizedHint = z;
        float deviceScaleFactor = this.mRenderCoordinates.getDeviceScaleFactor();
        float f15 = deviceScaleFactor * f3;
        float fMax = Math.max(f6, this.mViewportWidthPix / f15);
        float fMax2 = Math.max(f7, this.mViewportHeightPix / f15);
        float fFromDipToPix = this.mRenderCoordinates.fromDipToPix(f11);
        boolean z4 = (fMax == this.mRenderCoordinates.getContentWidthCss() && fMax2 == this.mRenderCoordinates.getContentHeightCss()) ? false : true;
        boolean z5 = (f4 == this.mRenderCoordinates.getMinPageScaleFactor() && f5 == this.mRenderCoordinates.getMaxPageScaleFactor()) ? false : true;
        boolean z6 = (!((f3 > this.mRenderCoordinates.getPageScaleFactor() ? 1 : (f3 == this.mRenderCoordinates.getPageScaleFactor() ? 0 : -1)) != 0) && f == this.mRenderCoordinates.getScrollX() && f2 == this.mRenderCoordinates.getScrollY()) ? false : true;
        boolean z7 = fFromDipToPix != this.mRenderCoordinates.getContentOffsetYPix();
        boolean z8 = z4 || z6;
        boolean z9 = z5 || z6;
        if (z8) {
            this.mPopupZoomer.hide(true);
        }
        if (z6) {
            this.mContainerViewInternals.onScrollChanged((int) this.mRenderCoordinates.fromLocalCssToPix(f), (int) this.mRenderCoordinates.fromLocalCssToPix(f2), (int) this.mRenderCoordinates.getScrollXPix(), (int) this.mRenderCoordinates.getScrollYPix());
        }
        this.mRenderCoordinates.updateFrameInfo(f, f2, fMax, fMax2, f8, f9, f3, f4, f5, fFromDipToPix);
        if (z6 || z7) {
            this.mGestureStateListenersIterator.rewind();
            while (this.mGestureStateListenersIterator.hasNext()) {
                this.mGestureStateListenersIterator.next().onScrollOffsetOrExtentChanged(computeVerticalScrollOffset(), computeVerticalScrollExtent());
            }
        }
        if (z9) {
            this.mZoomControlsDelegate.updateZoomControls();
        }
        getContentViewClient().onOffsetsForFullscreenChanged(f10 * deviceScaleFactor, fFromDipToPix);
        BrowserAccessibilityManager browserAccessibilityManager = this.mBrowserAccessibilityManager;
        if (browserAccessibilityManager != null) {
            browserAccessibilityManager.notifyFrameInfoInitialized();
        }
        this.mImeAdapter.onUpdateFrameInfo(this.mRenderCoordinates, z2, z3, f12, f13, f14);
        TraceEvent.end("ContentViewCore:updateFrameInfo");
    }

    private void updateImeAdapter(long j, int i, int i2, String str, int i3, int i4, int i5, int i6, boolean z, boolean z2) {
        try {
            TraceEvent.begin("ContentViewCore.updateImeAdapter");
            boolean z3 = true;
            boolean z4 = i != 0;
            boolean z5 = i == 2;
            if (!z4) {
                hidePastePopup();
            }
            this.mImeAdapter.attach(j);
            this.mImeAdapter.updateKeyboardVisibility(i, i2, z);
            this.mImeAdapter.updateState(str, i3, i4, i5, i6, z2);
            WebActionMode webActionMode = this.mActionMode;
            if (webActionMode != null) {
                if ((z4 == this.mFocusedNodeEditable && z5 == this.mFocusedNodeIsPassword) ? false : true) {
                    webActionMode.invalidate();
                }
            }
            this.mFocusedNodeIsPassword = z5;
            if (z4 != this.mFocusedNodeEditable) {
                this.mFocusedNodeEditable = z4;
                JoystickScrollProvider joystickScrollProvider = this.mJoystickScrollProvider;
                if (z4) {
                    z3 = false;
                }
                joystickScrollProvider.setEnabled(z3);
                getContentViewClient().onFocusedNodeEditabilityChanged(this.mFocusedNodeEditable);
            }
        } finally {
            TraceEvent.end("ContentViewCore.updateImeAdapter");
        }
    }

    private void forceUpdateImeAdapter(long j) {
        this.mImeAdapter.attach(j);
    }

    private void setTitle(String str) {
        getContentViewClient().onUpdateTitle(str);
    }

    private void showSelectPopup(long j, Rect rect, String[] strArr, int[] iArr, boolean z, int[] iArr2, boolean z2) {
        Context context;
        if (this.mContainerView.getParent() == null || this.mContainerView.getVisibility() != 0) {
            this.mNativeSelectPopupSourceFrame = j;
            selectPopupMenuItems(null);
            return;
        }
        hidePopupsAndClearSelection();
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < strArr.length; i++) {
            arrayList.add(new SelectPopupItem(strArr[i], iArr[i]));
        }
        if (DeviceFormFactor.isTablet(this.mContext) && !z && !isTouchExplorationEnabled()) {
            this.mSelectPopup = new SelectPopupDropdown(this, arrayList, rect, iArr2, z2);
        } else if (getWindowAndroid() == null || (context = getWindowAndroid().getContext().get()) == null) {
            return;
        } else {
            this.mSelectPopup = new SelectPopupDialog(this, context, arrayList, z, iArr2);
        }
        this.mNativeSelectPopupSourceFrame = j;
        this.mSelectPopup.show();
    }

    private void hideSelectPopup() {
        SelectPopup selectPopup = this.mSelectPopup;
        if (selectPopup == null) {
            return;
        }
        selectPopup.hide(false);
        this.mSelectPopup = null;
        this.mNativeSelectPopupSourceFrame = 0L;
    }

    private void hideSelectPopupWithCancelMesage() {
        SelectPopup selectPopup = this.mSelectPopup;
        if (selectPopup != null) {
            selectPopup.hide(true);
        }
    }

    public SelectPopup getSelectPopupForTest() {
        return this.mSelectPopup;
    }

    private void showDisambiguationPopup(Rect rect, Bitmap bitmap) {
        this.mPopupZoomer.setBitmap(bitmap);
        this.mPopupZoomer.show(rect);
    }

    private MotionEventSynthesizer createMotionEventSynthesizer() {
        return new MotionEventSynthesizer(this);
    }

    public void setOverscrollRefreshHandler(OverscrollRefreshHandler overscrollRefreshHandler) {
        this.mOverscrollRefreshHandler = overscrollRefreshHandler;
    }

    private boolean onOverscrollRefreshStart() {
        OverscrollRefreshHandler overscrollRefreshHandler = this.mOverscrollRefreshHandler;
        if (overscrollRefreshHandler == null) {
            return false;
        }
        return overscrollRefreshHandler.start();
    }

    private void onOverscrollRefreshUpdate(float f) {
        OverscrollRefreshHandler overscrollRefreshHandler = this.mOverscrollRefreshHandler;
        if (overscrollRefreshHandler != null) {
            overscrollRefreshHandler.pull(f);
        }
    }

    private void onOverscrollRefreshRelease(boolean z) {
        OverscrollRefreshHandler overscrollRefreshHandler = this.mOverscrollRefreshHandler;
        if (overscrollRefreshHandler != null) {
            overscrollRefreshHandler.release(z);
        }
    }

    private void onOverscrollRefreshReset() {
        OverscrollRefreshHandler overscrollRefreshHandler = this.mOverscrollRefreshHandler;
        if (overscrollRefreshHandler != null) {
            overscrollRefreshHandler.reset();
        }
    }

    private void onSelectionChanged(String str) {
        this.mLastSelectedText = str;
        ContextualSearchClient contextualSearchClient = this.mContextualSearchClient;
        if (contextualSearchClient != null) {
            contextualSearchClient.onSelectionChanged(str);
        }
    }

    private boolean showPastePopupWithFeedback(int i, int i2) {
        if (!showPastePopup(i, i2)) {
            return false;
        }
        WebContents webContents = this.mWebContents;
        if (webContents == null) {
            return true;
        }
        webContents.onContextMenuOpened();
        return true;
    }

    private void performLongPressHapticFeedback() {
        this.mContainerView.performHapticFeedback(0);
    }

    public boolean isPastePopupShowing() {
        PastePopupMenu pastePopupMenu = this.mPastePopupMenu;
        if (pastePopupMenu != null) {
            return pastePopupMenu.isShowing();
        }
        return false;
    }

    private boolean showPastePopup(int i, int i2) {
        if (this.mContainerView.getParent() != null && this.mContainerView.getVisibility() == 0 && this.mHasInsertion && canPaste()) {
            float contentOffsetYPix = this.mRenderCoordinates.getContentOffsetYPix();
            PastePopupMenu pastePopup = getPastePopup();
            if (pastePopup == null) {
                return false;
            }
            try {
                pastePopup.show(i, (int) (i2 + contentOffsetYPix));
                return true;
            } catch (WindowManager.BadTokenException unused) {
            }
        }
        return false;
    }

    private void hidePastePopup() {
        PastePopupMenu pastePopupMenu = this.mPastePopupMenu;
        if (pastePopupMenu != null) {
            pastePopupMenu.hide();
        }
    }

    private PastePopupMenu getPastePopup() {
        if (this.mPastePopupMenu == null) {
            PastePopupMenu.PastePopupMenuDelegate pastePopupMenuDelegate = new PastePopupMenu.PastePopupMenuDelegate() { // from class: org.chromium.content.browser.ContentViewCore.7
                @Override // org.chromium.content.browser.input.PastePopupMenu.PastePopupMenuDelegate
                public void paste() {
                    ContentViewCore.this.mWebContents.paste();
                    ContentViewCore.this.dismissTextHandles();
                }

                @Override // org.chromium.content.browser.input.PastePopupMenu.PastePopupMenuDelegate
                public void onDismiss() {
                    if (ContentViewCore.this.mWebContents != null) {
                        ContentViewCore.this.mWebContents.onContextMenuClosed();
                    }
                }
            };
            Context context = getWindowAndroid().getContext().get();
            if (context == null) {
                return null;
            }
            if (supportsFloatingActionMode()) {
                this.mPastePopupMenu = new FloatingPastePopupMenu(context, getContainerView(), pastePopupMenuDelegate);
            } else {
                this.mPastePopupMenu = new LegacyPastePopupMenu(context, getContainerView(), pastePopupMenuDelegate);
            }
        }
        return this.mPastePopupMenu;
    }

    private boolean canPaste() {
        return ((ClipboardManager) this.mContext.getSystemService("clipboard")).hasPrimaryClip();
    }

    private void onRenderProcessChange() {
        attachImeAdapter();
        this.mSystemCaptioningBridge.syncToListener(this);
    }

    public void attachImeAdapter() {
        ImeAdapter imeAdapter = this.mImeAdapter;
        if (imeAdapter != null) {
            long j = this.mNativeContentViewCore;
            if (j != 0) {
                imeAdapter.attach(nativeGetNativeImeAdapter(j));
            }
        }
    }

    private boolean hasFocus() {
        if (this.mContainerView.isFocusable()) {
            return this.mContainerView.hasFocus();
        }
        return true;
    }

    public boolean canZoomIn() {
        return this.mRenderCoordinates.getMaxPageScaleFactor() - this.mRenderCoordinates.getPageScaleFactor() > ZOOM_CONTROLS_EPSILON;
    }

    public boolean canZoomOut() {
        return this.mRenderCoordinates.getPageScaleFactor() - this.mRenderCoordinates.getMinPageScaleFactor() > ZOOM_CONTROLS_EPSILON;
    }

    public boolean zoomIn() {
        if (canZoomIn()) {
            return pinchByDelta(1.25f);
        }
        return false;
    }

    public boolean zoomOut() {
        if (canZoomOut()) {
            return pinchByDelta(0.8f);
        }
        return false;
    }

    public boolean zoomReset() {
        if (canZoomOut()) {
            return pinchByDelta(this.mRenderCoordinates.getMinPageScaleFactor() / this.mRenderCoordinates.getPageScaleFactor());
        }
        return false;
    }

    public boolean pinchByDelta(float f) {
        if (this.mNativeContentViewCore == 0) {
            return false;
        }
        long jUptimeMillis = SystemClock.uptimeMillis();
        float viewportWidthPix = getViewportWidthPix() / 2;
        float viewportHeightPix = getViewportHeightPix() / 2;
        nativePinchBegin(this.mNativeContentViewCore, jUptimeMillis, viewportWidthPix, viewportHeightPix);
        nativePinchBy(this.mNativeContentViewCore, jUptimeMillis, viewportWidthPix, viewportHeightPix, f);
        nativePinchEnd(this.mNativeContentViewCore, jUptimeMillis);
        return true;
    }

    public boolean pinchBegin(int i, int i2) {
        long j = this.mNativeContentViewCore;
        if (j == 0) {
            return false;
        }
        nativePinchBegin(j, SystemClock.uptimeMillis(), i, i2);
        return true;
    }

    public boolean pinchBy(int i, int i2, float f) {
        long j = this.mNativeContentViewCore;
        if (j == 0) {
            return false;
        }
        nativePinchBy(j, SystemClock.uptimeMillis(), i, i2, f);
        return true;
    }

    public boolean pinchEnd() {
        long j = this.mNativeContentViewCore;
        if (j == 0) {
            return false;
        }
        nativePinchEnd(j, SystemClock.uptimeMillis());
        return true;
    }

    public void invokeZoomPicker() {
        this.mZoomControlsDelegate.invokeZoomPicker();
    }

    public void setAllowJavascriptInterfacesInspection(boolean z) {
        nativeSetAllowJavascriptInterfacesInspection(this.mNativeContentViewCore, z);
    }

    public Map<String, Pair<Object, Class>> getJavascriptInterfaces() {
        return this.mJavaScriptInterfaces;
    }

    public void addJavascriptInterface(Object obj, String str) {
        addPossiblyUnsafeJavascriptInterface(obj, str, JavascriptInterface.class);
    }

    public void addPossiblyUnsafeJavascriptInterface(Object obj, String str, Class<? extends Annotation> cls) {
        if (this.mNativeContentViewCore == 0 || obj == null) {
            return;
        }
        this.mJavaScriptInterfaces.put(str, new Pair<>(obj, cls));
        nativeAddJavascriptInterface(this.mNativeContentViewCore, obj, str, cls);
    }

    public void removeJavascriptInterface(String str) {
        this.mJavaScriptInterfaces.remove(str);
        long j = this.mNativeContentViewCore;
        if (j != 0) {
            nativeRemoveJavascriptInterface(j, str);
        }
    }

    public float getScale() {
        return this.mRenderCoordinates.getPageScaleFactor();
    }

    private void startContentIntent(String str, boolean z) {
        getContentViewClient().onStartContentIntent(getContext(), str, z);
    }

    @Override // android.view.accessibility.AccessibilityManager.AccessibilityStateChangeListener
    public void onAccessibilityStateChanged(boolean z) {
        setAccessibilityState(z);
    }

    public void setBrowserAccessibilityManager(BrowserAccessibilityManager browserAccessibilityManager) {
        this.mBrowserAccessibilityManager = browserAccessibilityManager;
        if (browserAccessibilityManager != null && this.mRenderCoordinates.hasFrameInfo()) {
            this.mBrowserAccessibilityManager.notifyFrameInfoInitialized();
        }
        if (this.mBrowserAccessibilityManager == null) {
            this.mNativeAccessibilityEnabled = false;
        }
    }

    public BrowserAccessibilityManager getBrowserAccessibilityManager() {
        return this.mBrowserAccessibilityManager;
    }

    public AccessibilityNodeProvider getAccessibilityNodeProvider() {
        if (this.mIsObscuredByAnotherView) {
            return null;
        }
        BrowserAccessibilityManager browserAccessibilityManager = this.mBrowserAccessibilityManager;
        if (browserAccessibilityManager != null) {
            return browserAccessibilityManager.getAccessibilityNodeProvider();
        }
        if (this.mNativeAccessibilityAllowed && !this.mNativeAccessibilityEnabled) {
            long j = this.mNativeContentViewCore;
            if (j != 0) {
                this.mNativeAccessibilityEnabled = true;
                nativeSetAccessibilityEnabled(j, true);
            }
        }
        return null;
    }

    public void setObscuredByAnotherView(boolean z) {
        if (z != this.mIsObscuredByAnotherView) {
            this.mIsObscuredByAnotherView = z;
            getContainerView().sendAccessibilityEvent(2048);
        }
    }

    public void onProvideVirtualStructure(ViewStructure viewStructure, final boolean z) {
        if (getWebContents().isIncognito()) {
            viewStructure.setChildCount(0);
            return;
        }
        viewStructure.setChildCount(1);
        final ViewStructure viewStructureAsyncNewChild = viewStructure.asyncNewChild(0);
        getWebContents().requestAccessibilitySnapshot(new AccessibilitySnapshotCallback() { // from class: org.chromium.content.browser.ContentViewCore.8
            @Override // org.chromium.content_public.browser.AccessibilitySnapshotCallback
            public void onAccessibilitySnapshot(AccessibilitySnapshotNode accessibilitySnapshotNode) {
                viewStructureAsyncNewChild.setClassName("");
                viewStructureAsyncNewChild.setHint(ContentViewCore.this.mContentViewClient.getProductVersion());
                if (accessibilitySnapshotNode != null) {
                    ContentViewCore.this.createVirtualStructure(viewStructureAsyncNewChild, accessibilitySnapshotNode, z);
                } else {
                    viewStructureAsyncNewChild.asyncCommit();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void createVirtualStructure(ViewStructure viewStructure, AccessibilitySnapshotNode accessibilitySnapshotNode, boolean z) {
        viewStructure.setClassName(accessibilitySnapshotNode.className);
        if (accessibilitySnapshotNode.hasSelection) {
            viewStructure.setText(accessibilitySnapshotNode.text, accessibilitySnapshotNode.startSelection, accessibilitySnapshotNode.endSelection);
        } else {
            viewStructure.setText(accessibilitySnapshotNode.text);
        }
        int iFromLocalCssToPix = (int) this.mRenderCoordinates.fromLocalCssToPix(accessibilitySnapshotNode.x);
        int iFromLocalCssToPix2 = (int) this.mRenderCoordinates.fromLocalCssToPix(accessibilitySnapshotNode.y);
        int iFromLocalCssToPix3 = (int) this.mRenderCoordinates.fromLocalCssToPix(accessibilitySnapshotNode.width);
        int iFromLocalCssToPix4 = (int) this.mRenderCoordinates.fromLocalCssToPix(accessibilitySnapshotNode.height);
        Rect rect = new Rect(iFromLocalCssToPix, iFromLocalCssToPix2, iFromLocalCssToPix + iFromLocalCssToPix3, iFromLocalCssToPix2 + iFromLocalCssToPix4);
        if (accessibilitySnapshotNode.isRootNode) {
            rect.offset(0, (int) this.mRenderCoordinates.getContentOffsetYPix());
            if (!z) {
                rect.offset(-((int) this.mRenderCoordinates.getScrollXPix()), -((int) this.mRenderCoordinates.getScrollYPix()));
            }
        }
        viewStructure.setDimens(rect.left, rect.top, 0, 0, iFromLocalCssToPix3, iFromLocalCssToPix4);
        viewStructure.setChildCount(accessibilitySnapshotNode.children.size());
        if (accessibilitySnapshotNode.hasStyle) {
            viewStructure.setTextStyle(accessibilitySnapshotNode.textSize, accessibilitySnapshotNode.color, accessibilitySnapshotNode.bgcolor, (accessibilitySnapshotNode.bold ? 1 : 0) | (accessibilitySnapshotNode.italic ? 2 : 0) | (accessibilitySnapshotNode.underline ? 4 : 0) | (accessibilitySnapshotNode.lineThrough ? 8 : 0));
        }
        for (int i = 0; i < accessibilitySnapshotNode.children.size(); i++) {
            createVirtualStructure(viewStructure.asyncNewChild(i), accessibilitySnapshotNode.children.get(i), true);
        }
        viewStructure.asyncCommit();
    }

    @Override // org.chromium.content.browser.accessibility.captioning.SystemCaptioningBridge.SystemCaptioningBridgeListener
    public void onSystemCaptioningChanged(TextTrackSettings textTrackSettings) {
        long j = this.mNativeContentViewCore;
        if (j == 0) {
            return;
        }
        nativeSetTextTrackSettings(j, textTrackSettings.getTextTracksEnabled(), textTrackSettings.getTextTrackBackgroundColor(), textTrackSettings.getTextTrackFontFamily(), textTrackSettings.getTextTrackFontStyle(), textTrackSettings.getTextTrackFontVariant(), textTrackSettings.getTextTrackTextColor(), textTrackSettings.getTextTrackTextShadow(), textTrackSettings.getTextTrackTextSize());
    }

    public void onReceivedProcessTextResult(int i, Intent intent) {
        CharSequence charSequenceExtra;
        if (this.mWebContents == null || !isSelectionEditable() || i != -1 || intent == null || (charSequenceExtra = intent.getCharSequenceExtra("android.intent.extra.PROCESS_TEXT")) == null) {
            return;
        }
        this.mWebContents.replace(charSequenceExtra.toString());
    }

    public boolean isTouchExplorationEnabled() {
        return this.mTouchExplorationEnabled;
    }

    public void setAccessibilityState(boolean z) {
        if (!z) {
            this.mNativeAccessibilityAllowed = false;
            this.mTouchExplorationEnabled = false;
        } else {
            this.mNativeAccessibilityAllowed = true;
            this.mTouchExplorationEnabled = this.mAccessibilityManager.isTouchExplorationEnabled();
        }
    }

    public boolean shouldSetAccessibilityFocusOnPageLoad() {
        return this.mShouldSetAccessibilityFocusOnPageLoad;
    }

    public void setShouldSetAccessibilityFocusOnPageLoad(boolean z) {
        this.mShouldSetAccessibilityFocusOnPageLoad = z;
    }

    public RenderCoordinates getRenderCoordinates() {
        return this.mRenderCoordinates;
    }

    public boolean getIsMobileOptimizedHint() {
        return this.mIsMobileOptimizedHint;
    }

    private static Rect createRect(int i, int i2, int i3, int i4) {
        return new Rect(i, i2, i3, i4);
    }

    public void extractSmartClipData(int i, int i2, int i3, int i4) {
        long j = this.mNativeContentViewCore;
        if (j != 0) {
            nativeExtractSmartClipData(j, i + this.mSmartClipOffsetX, i2 + this.mSmartClipOffsetY, i3, i4);
        }
    }

    public void setSmartClipOffsets(int i, int i2) {
        this.mSmartClipOffsetX = i;
        this.mSmartClipOffsetY = i2;
    }

    private void onSmartClipDataExtracted(String str, String str2, Rect rect) {
        float deviceScaleFactor = this.mRenderCoordinates.getDeviceScaleFactor();
        rect.offset(-((int) (this.mSmartClipOffsetX / deviceScaleFactor)), -((int) (this.mSmartClipOffsetY / deviceScaleFactor)));
        SmartClipDataListener smartClipDataListener = this.mSmartClipDataListener;
        if (smartClipDataListener != null) {
            smartClipDataListener.onSmartClipDataExtracted(str, str2, rect);
        }
    }

    public void setSmartClipDataListener(SmartClipDataListener smartClipDataListener) {
        this.mSmartClipDataListener = smartClipDataListener;
    }

    public void setBackgroundColor(int i) {
        long j = this.mNativeContentViewCore;
        if (j != 0) {
            nativeSetBackgroundColor(j, i);
        }
    }

    public void setBackgroundOpaque(boolean z) {
        long j = this.mNativeContentViewCore;
        if (j != 0) {
            nativeSetBackgroundOpaque(j, z);
        }
    }

    private boolean offerLongPressToEmbedder() {
        return this.mContainerView.performLongClick();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void resetScrollInProgress() {
        if (isScrollInProgress()) {
            boolean z = this.mTouchScrollInProgress;
            int i = this.mPotentiallyActiveFlingCount;
            setTouchScrollInProgress(false);
            this.mPotentiallyActiveFlingCount = 0;
            if (z) {
                updateGestureStateListener(8);
            }
            if (i > 0) {
                updateGestureStateListener(11);
            }
        }
    }

    private float getWheelScrollFactorInPixels() {
        if (this.mWheelScrollFactorInPixels == 0.0f) {
            TypedValue typedValue = new TypedValue();
            if (this.mContext.getTheme().resolveAttribute(R.attr.listPreferredItemHeight, typedValue, true)) {
                this.mWheelScrollFactorInPixels = typedValue.getDimension(this.mContext.getResources().getDisplayMetrics());
            } else {
                this.mWheelScrollFactorInPixels = this.mRenderCoordinates.getDeviceScaleFactor() * 64.0f;
            }
        }
        return this.mWheelScrollFactorInPixels;
    }

    ContentVideoViewEmbedder getContentVideoViewEmbedder() {
        return getContentViewClient().getContentVideoViewEmbedder();
    }

    private boolean shouldBlockMediaRequest(String str) {
        return getContentViewClient().shouldBlockMediaRequest(str);
    }

    private void onNativeFlingStopped() {
        setTouchScrollInProgress(false);
        int i = this.mPotentiallyActiveFlingCount;
        if (i <= 0) {
            return;
        }
        this.mPotentiallyActiveFlingCount = i - 1;
        updateGestureStateListener(11);
    }

    @Override // org.chromium.content.browser.ScreenOrientationListener.ScreenOrientationObserver
    public void onScreenOrientationChanged(int i) {
        if (this.mActionMode != null) {
            hidePopupsAndPreserveSelection();
            showSelectActionMode(true);
        }
        sendOrientationChangeEvent(i);
    }

    public void setFullscreenRequiredForOrientationLock(boolean z) {
        this.mFullscreenRequiredForOrientationLock = z;
    }

    private boolean isFullscreenRequiredForOrientationLock() {
        return this.mFullscreenRequiredForOrientationLock;
    }

    public void setContextualSearchClient(ContextualSearchClient contextualSearchClient) {
        this.mContextualSearchClient = contextualSearchClient;
    }

    public void didOverscroll(boolean z, boolean z2) {
        this.mContentViewClient.onOverScrolled(this.mRenderCoordinates.getScrollXPixInt(), this.mRenderCoordinates.getScrollYPixInt(), z, z2);
    }

    public void onShowKeyboardReceiveResult(int i) {
        WebContents webContents;
        if (i == 2) {
            getContainerView().getWindowVisibleDisplayFrame(this.mFocusPreOSKViewportRect);
        } else if (hasFocus() && i == 0 && (webContents = this.mWebContents) != null) {
            webContents.scrollFocusedEditableNodeIntoView();
        }
    }

    public ResultReceiver getNewShowKeyboardReceiver() {
        if (this.mShowKeyboardResultReceiver == null) {
            this.mShowKeyboardResultReceiver = new ShowKeyboardResultReceiver(this, new Handler());
        }
        return this.mShowKeyboardResultReceiver;
    }
}
