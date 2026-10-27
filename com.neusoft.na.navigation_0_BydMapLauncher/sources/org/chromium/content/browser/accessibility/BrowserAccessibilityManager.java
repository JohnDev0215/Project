package org.chromium.content.browser.accessibility;

import android.graphics.Rect;
import android.os.Bundle;
import android.provider.Settings;
import android.text.SpannableString;
import android.text.style.URLSpan;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeProvider;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import java.util.ArrayList;
import java.util.List;
import org.chromium.base.annotations.JNINamespace;
import org.chromium.content.browser.ContentViewCore;
import org.chromium.content.browser.RenderCoordinates;

/* JADX INFO: loaded from: classes3.dex */
@JNINamespace("content")
public class BrowserAccessibilityManager {
    private static final String ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE = "ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE";
    private static final int ACTION_SET_TEXT = 2097152;
    private static final String TAG = "BrowserAccessibilityManager";
    private static final int WINDOW_CONTENT_CHANGED_DELAY_MS = 500;
    private Rect mAccessibilityFocusRect;
    private final AccessibilityManager mAccessibilityManager;
    private ContentViewCore mContentViewCore;
    private long mNativeObj;
    private boolean mNotifyFrameInfoInitializedCalled;
    private boolean mPendingScrollToMakeNodeVisible;
    private final RenderCoordinates mRenderCoordinates;
    private int mSelectionEndIndex;
    private int mSelectionGranularity;
    private int mSelectionStartIndex;
    private Runnable mSendWindowContentChangedRunnable;
    private boolean mUserHasTouchExplored;
    private final ViewGroup mView;
    private int mLastHoverId = -1;
    private final int[] mTempLocation = new int[2];
    protected int mAccessibilityFocusId = -1;
    private boolean mIsHovering = false;
    protected int mCurrentRootId = -1;
    private final AccessibilityNodeProvider mAccessibilityNodeProvider = new AccessibilityNodeProvider() { // from class: org.chromium.content.browser.accessibility.BrowserAccessibilityManager.1
        @Override // android.view.accessibility.AccessibilityNodeProvider
        public AccessibilityNodeInfo createAccessibilityNodeInfo(int i) {
            return this.createAccessibilityNodeInfo(i);
        }

        @Override // android.view.accessibility.AccessibilityNodeProvider
        public List<AccessibilityNodeInfo> findAccessibilityNodeInfosByText(String str, int i) {
            return this.findAccessibilityNodeInfosByText(str, i);
        }

        @Override // android.view.accessibility.AccessibilityNodeProvider
        public boolean performAction(int i, int i2, Bundle bundle) {
            return this.performAction(i, i2, bundle);
        }
    };

    protected static boolean isValidMovementGranularity(int i) {
        return i == 1 || i == 2 || i == 4;
    }

    private native boolean nativeAdjustSlider(long j, int i, boolean z);

    private native void nativeBlur(long j);

    private native void nativeClick(long j, int i);

    private native int nativeFindElementType(long j, int i, String str, boolean z);

    private native void nativeFocus(long j, int i);

    private native int nativeGetEditableTextSelectionEnd(long j, int i);

    private native int nativeGetEditableTextSelectionStart(long j, int i);

    private native int nativeGetRootId(long j);

    private native void nativeHitTest(long j, int i, int i2);

    private native boolean nativeIsEditableText(long j, int i);

    private native boolean nativeIsNodeValid(long j, int i);

    private native boolean nativeIsSlider(long j, int i);

    private native boolean nativeNextAtGranularity(long j, int i, boolean z, int i2, int i3);

    private native boolean nativePopulateAccessibilityEvent(long j, AccessibilityEvent accessibilityEvent, int i, int i2);

    private native boolean nativePopulateAccessibilityNodeInfo(long j, AccessibilityNodeInfo accessibilityNodeInfo, int i);

    private native boolean nativePreviousAtGranularity(long j, int i, boolean z, int i2, int i3);

    private native boolean nativeScroll(long j, int i, int i2);

    private native void nativeScrollToMakeNodeVisible(long j, int i);

    private native void nativeSetAccessibilityFocus(long j, int i);

    private native void nativeSetSelection(long j, int i, int i2, int i3);

    private native void nativeSetTextFieldValue(long j, int i, String str);

    protected native String nativeGetSupportedHtmlElementTypes(long j);

    protected void setAccessibilityNodeInfoCollectionInfo(AccessibilityNodeInfo accessibilityNodeInfo, int i, int i2, boolean z) {
    }

    protected void setAccessibilityNodeInfoCollectionItemInfo(AccessibilityNodeInfo accessibilityNodeInfo, int i, int i2, int i3, int i4, boolean z) {
    }

    protected void setAccessibilityNodeInfoKitKatAttributes(AccessibilityNodeInfo accessibilityNodeInfo, boolean z, boolean z2, String str) {
    }

    protected void setAccessibilityNodeInfoLollipopAttributes(AccessibilityNodeInfo accessibilityNodeInfo, boolean z, boolean z2, boolean z3, boolean z4, int i, int i2) {
    }

    protected void setAccessibilityNodeInfoRangeInfo(AccessibilityNodeInfo accessibilityNodeInfo, int i, float f, float f2, float f3) {
    }

    protected void setAccessibilityNodeInfoViewIdResourceName(AccessibilityNodeInfo accessibilityNodeInfo, String str) {
    }

    private static BrowserAccessibilityManager create(long j, ContentViewCore contentViewCore) {
        return new LollipopBrowserAccessibilityManager(j, contentViewCore);
    }

    protected BrowserAccessibilityManager(long j, ContentViewCore contentViewCore) {
        this.mNativeObj = j;
        this.mContentViewCore = contentViewCore;
        this.mView = contentViewCore.getContainerView();
        this.mRenderCoordinates = this.mContentViewCore.getRenderCoordinates();
        this.mAccessibilityManager = (AccessibilityManager) this.mContentViewCore.getContext().getSystemService("accessibility");
        this.mContentViewCore.setBrowserAccessibilityManager(this);
    }

    private void onNativeObjectDestroyed(long j) {
        if (j != this.mNativeObj) {
            return;
        }
        ContentViewCore contentViewCore = this.mContentViewCore;
        if (contentViewCore != null && contentViewCore.getBrowserAccessibilityManager() == this) {
            this.mContentViewCore.setBrowserAccessibilityManager(null);
        }
        this.mNativeObj = 0L;
        this.mContentViewCore = null;
    }

    public AccessibilityNodeProvider getAccessibilityNodeProvider() {
        return this.mAccessibilityNodeProvider;
    }

    protected AccessibilityNodeInfo createAccessibilityNodeInfo(int i) {
        if (this.mAccessibilityManager.isEnabled()) {
            long j = this.mNativeObj;
            if (j != 0) {
                int iNativeGetRootId = nativeGetRootId(j);
                if (i == -1) {
                    return createNodeForHost(iNativeGetRootId);
                }
                if (!isFrameInfoInitialized()) {
                    return null;
                }
                AccessibilityNodeInfo accessibilityNodeInfoObtain = AccessibilityNodeInfo.obtain(this.mView);
                accessibilityNodeInfoObtain.setPackageName(this.mContentViewCore.getContext().getPackageName());
                accessibilityNodeInfoObtain.setSource(this.mView, i);
                if (i == iNativeGetRootId) {
                    accessibilityNodeInfoObtain.setParent(this.mView);
                }
                if (nativePopulateAccessibilityNodeInfo(this.mNativeObj, accessibilityNodeInfoObtain, i)) {
                    return accessibilityNodeInfoObtain;
                }
                accessibilityNodeInfoObtain.recycle();
            }
        }
        return null;
    }

    protected List<AccessibilityNodeInfo> findAccessibilityNodeInfosByText(String str, int i) {
        return new ArrayList();
    }

    protected boolean performAction(int i, int i2, Bundle bundle) {
        int i3;
        int i4;
        String string;
        if (this.mAccessibilityManager.isEnabled()) {
            long j = this.mNativeObj;
            if (j != 0 && nativeIsNodeValid(j, i)) {
                if (i2 == 1) {
                    nativeFocus(this.mNativeObj, i);
                    return true;
                }
                if (i2 != 2) {
                    switch (i2) {
                        case 16:
                            nativeClick(this.mNativeObj, i);
                            break;
                        case 64:
                            if (moveAccessibilityFocusToId(i)) {
                                if (!this.mIsHovering) {
                                    nativeScrollToMakeNodeVisible(this.mNativeObj, this.mAccessibilityFocusId);
                                } else {
                                    this.mPendingScrollToMakeNodeVisible = true;
                                }
                                break;
                            }
                            break;
                        case 128:
                            sendAccessibilityEvent(i, 65536);
                            if (this.mAccessibilityFocusId == i) {
                                this.mAccessibilityFocusId = -1;
                                this.mAccessibilityFocusRect = null;
                            }
                            break;
                        case 256:
                            if (bundle != null) {
                                int i5 = bundle.getInt(AccessibilityNodeInfoCompat.ACTION_ARGUMENT_MOVEMENT_GRANULARITY_INT);
                                boolean z = bundle.getBoolean(AccessibilityNodeInfoCompat.ACTION_ARGUMENT_EXTEND_SELECTION_BOOLEAN);
                                if (isValidMovementGranularity(i5)) {
                                    break;
                                }
                            }
                            break;
                        case 512:
                            if (bundle != null) {
                                int i6 = bundle.getInt(AccessibilityNodeInfoCompat.ACTION_ARGUMENT_MOVEMENT_GRANULARITY_INT);
                                boolean z2 = bundle.getBoolean(AccessibilityNodeInfoCompat.ACTION_ARGUMENT_EXTEND_SELECTION_BOOLEAN);
                                if (isValidMovementGranularity(i6)) {
                                    break;
                                }
                            }
                            break;
                        case 1024:
                            if (bundle != null && (r10 = bundle.getString(AccessibilityNodeInfoCompat.ACTION_ARGUMENT_HTML_ELEMENT_STRING)) != null) {
                            }
                            break;
                        case 2048:
                            if (bundle != null && (r10 = bundle.getString(AccessibilityNodeInfoCompat.ACTION_ARGUMENT_HTML_ELEMENT_STRING)) != null) {
                            }
                            break;
                        case 4096:
                            break;
                        case 8192:
                            break;
                        case 131072:
                            if (nativeIsEditableText(this.mNativeObj, i)) {
                                if (bundle != null) {
                                    int i7 = bundle.getInt(AccessibilityNodeInfoCompat.ACTION_ARGUMENT_SELECTION_START_INT);
                                    i4 = bundle.getInt(AccessibilityNodeInfoCompat.ACTION_ARGUMENT_SELECTION_END_INT);
                                    i3 = i7;
                                } else {
                                    i3 = 0;
                                    i4 = 0;
                                }
                                nativeSetSelection(this.mNativeObj, i, i3, i4);
                                break;
                            }
                            break;
                        case 2097152:
                            if (nativeIsEditableText(this.mNativeObj, i) && bundle != null && (string = bundle.getString("ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE")) != null) {
                                nativeSetTextFieldValue(this.mNativeObj, i, string);
                                nativeSetSelection(this.mNativeObj, i, string.length(), string.length());
                            }
                            break;
                    }
                    return false;
                }
                nativeBlur(this.mNativeObj);
                return true;
            }
        }
        return false;
    }

    public boolean onHoverEvent(MotionEvent motionEvent) {
        if (!this.mAccessibilityManager.isEnabled() || this.mNativeObj == 0) {
            return false;
        }
        if (motionEvent.getAction() == 10) {
            this.mIsHovering = false;
            int i = this.mLastHoverId;
            if (i != -1) {
                sendAccessibilityEvent(i, 256);
                this.mLastHoverId = -1;
            }
            if (this.mPendingScrollToMakeNodeVisible) {
                nativeScrollToMakeNodeVisible(this.mNativeObj, this.mAccessibilityFocusId);
            }
            this.mPendingScrollToMakeNodeVisible = false;
            return true;
        }
        this.mIsHovering = true;
        this.mUserHasTouchExplored = true;
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        nativeHitTest(this.mNativeObj, (int) this.mRenderCoordinates.fromPixToLocalCss(x), (int) this.mRenderCoordinates.fromPixToLocalCss(y));
        return true;
    }

    public void notifyFrameInfoInitialized() {
        if (this.mNotifyFrameInfoInitializedCalled) {
            return;
        }
        this.mNotifyFrameInfoInitializedCalled = true;
        sendWindowContentChangedOnView();
        int i = this.mAccessibilityFocusId;
        if (i != -1) {
            moveAccessibilityFocusToIdAndRefocusIfNeeded(i);
        }
    }

    private boolean jumpToElementType(String str, boolean z) {
        int iNativeFindElementType = nativeFindElementType(this.mNativeObj, this.mAccessibilityFocusId, str, z);
        if (iNativeFindElementType == 0) {
            return false;
        }
        moveAccessibilityFocusToId(iNativeFindElementType);
        nativeScrollToMakeNodeVisible(this.mNativeObj, this.mAccessibilityFocusId);
        return true;
    }

    private void setGranularityAndUpdateSelection(int i) {
        if (this.mSelectionGranularity == 0) {
            this.mSelectionStartIndex = -1;
            this.mSelectionEndIndex = -1;
        }
        this.mSelectionGranularity = i;
        if (nativeIsEditableText(this.mNativeObj, this.mAccessibilityFocusId)) {
            this.mSelectionStartIndex = nativeGetEditableTextSelectionStart(this.mNativeObj, this.mAccessibilityFocusId);
            this.mSelectionEndIndex = nativeGetEditableTextSelectionEnd(this.mNativeObj, this.mAccessibilityFocusId);
        }
    }

    private boolean nextAtGranularity(int i, boolean z) {
        setGranularityAndUpdateSelection(i);
        return nativeNextAtGranularity(this.mNativeObj, this.mSelectionGranularity, z, this.mAccessibilityFocusId, this.mSelectionEndIndex);
    }

    private boolean previousAtGranularity(int i, boolean z) {
        setGranularityAndUpdateSelection(i);
        return nativePreviousAtGranularity(this.mNativeObj, this.mSelectionGranularity, z, this.mAccessibilityFocusId, this.mSelectionEndIndex);
    }

    private void finishGranularityMove(String str, boolean z, int i, int i2, boolean z2) {
        AccessibilityEvent accessibilityEventBuildAccessibilityEvent;
        if (this.mNativeObj == 0 || (accessibilityEventBuildAccessibilityEvent = buildAccessibilityEvent(this.mAccessibilityFocusId, 8192)) == null) {
            return;
        }
        AccessibilityEvent accessibilityEventBuildAccessibilityEvent2 = buildAccessibilityEvent(this.mAccessibilityFocusId, 131072);
        if (accessibilityEventBuildAccessibilityEvent2 == null) {
            accessibilityEventBuildAccessibilityEvent.recycle();
            return;
        }
        if (z2) {
            this.mSelectionEndIndex = i2;
        } else {
            this.mSelectionEndIndex = i;
        }
        if (!z) {
            this.mSelectionStartIndex = this.mSelectionEndIndex;
        }
        if (nativeIsEditableText(this.mNativeObj, this.mAccessibilityFocusId)) {
            nativeSetSelection(this.mNativeObj, this.mAccessibilityFocusId, this.mSelectionStartIndex, this.mSelectionEndIndex);
        }
        accessibilityEventBuildAccessibilityEvent.setFromIndex(this.mSelectionStartIndex);
        accessibilityEventBuildAccessibilityEvent.setToIndex(this.mSelectionStartIndex);
        accessibilityEventBuildAccessibilityEvent.setItemCount(str.length());
        accessibilityEventBuildAccessibilityEvent2.setFromIndex(i);
        accessibilityEventBuildAccessibilityEvent2.setToIndex(i2);
        accessibilityEventBuildAccessibilityEvent2.setItemCount(str.length());
        accessibilityEventBuildAccessibilityEvent2.setMovementGranularity(this.mSelectionGranularity);
        accessibilityEventBuildAccessibilityEvent2.setContentDescription(str);
        if (z2) {
            accessibilityEventBuildAccessibilityEvent2.setAction(256);
        } else {
            accessibilityEventBuildAccessibilityEvent2.setAction(512);
        }
        ViewGroup viewGroup = this.mView;
        viewGroup.requestSendAccessibilityEvent(viewGroup, accessibilityEventBuildAccessibilityEvent);
        ViewGroup viewGroup2 = this.mView;
        viewGroup2.requestSendAccessibilityEvent(viewGroup2, accessibilityEventBuildAccessibilityEvent2);
    }

    private boolean scrollForward(int i) {
        if (nativeIsSlider(this.mNativeObj, i)) {
            return nativeAdjustSlider(this.mNativeObj, i, true);
        }
        return nativeScroll(this.mNativeObj, i, 0);
    }

    private boolean scrollBackward(int i) {
        if (nativeIsSlider(this.mNativeObj, i)) {
            return nativeAdjustSlider(this.mNativeObj, i, false);
        }
        return nativeScroll(this.mNativeObj, i, 1);
    }

    private boolean moveAccessibilityFocusToId(int i) {
        if (i == this.mAccessibilityFocusId) {
            return false;
        }
        this.mAccessibilityFocusId = i;
        this.mAccessibilityFocusRect = null;
        this.mSelectionGranularity = 0;
        this.mSelectionStartIndex = 0;
        this.mSelectionEndIndex = 0;
        if (i == this.mCurrentRootId) {
            nativeSetAccessibilityFocus(this.mNativeObj, -1);
        } else {
            nativeSetAccessibilityFocus(this.mNativeObj, i);
        }
        sendAccessibilityEvent(this.mAccessibilityFocusId, 32768);
        return true;
    }

    private void moveAccessibilityFocusToIdAndRefocusIfNeeded(int i) {
        if (i == this.mAccessibilityFocusId) {
            sendAccessibilityEvent(i, 65536);
            this.mAccessibilityFocusId = -1;
        }
        moveAccessibilityFocusToId(i);
    }

    private void sendDelayedWindowContentChangedEvent() {
        if (this.mNativeObj != 0 && this.mSendWindowContentChangedRunnable == null) {
            Runnable runnable = new Runnable() { // from class: org.chromium.content.browser.accessibility.BrowserAccessibilityManager.2
                @Override // java.lang.Runnable
                public void run() {
                    BrowserAccessibilityManager.this.sendWindowContentChangedOnView();
                }
            };
            this.mSendWindowContentChangedRunnable = runnable;
            this.mView.postDelayed(runnable, 500L);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendWindowContentChangedOnView() {
        ViewGroup viewGroup;
        if (this.mNativeObj == 0 || this.mContentViewCore == null || (viewGroup = this.mView) == null) {
            return;
        }
        Runnable runnable = this.mSendWindowContentChangedRunnable;
        if (runnable != null) {
            viewGroup.removeCallbacks(runnable);
            this.mSendWindowContentChangedRunnable = null;
        }
        this.mView.sendAccessibilityEvent(2048);
    }

    private void sendWindowContentChangedOnVirtualView(int i) {
        sendAccessibilityEvent(i, 2048);
    }

    private void sendAccessibilityEvent(int i, int i2) {
        if (i == -1) {
            this.mView.sendAccessibilityEvent(i2);
            return;
        }
        AccessibilityEvent accessibilityEventBuildAccessibilityEvent = buildAccessibilityEvent(i, i2);
        if (accessibilityEventBuildAccessibilityEvent != null) {
            ViewGroup viewGroup = this.mView;
            viewGroup.requestSendAccessibilityEvent(viewGroup, accessibilityEventBuildAccessibilityEvent);
        }
    }

    private AccessibilityEvent buildAccessibilityEvent(int i, int i2) {
        if (!this.mAccessibilityManager.isEnabled() || this.mNativeObj == 0 || !isFrameInfoInitialized()) {
            return null;
        }
        this.mView.postInvalidate();
        AccessibilityEvent accessibilityEventObtain = AccessibilityEvent.obtain(i2);
        accessibilityEventObtain.setPackageName(this.mContentViewCore.getContext().getPackageName());
        accessibilityEventObtain.setSource(this.mView, i);
        if (nativePopulateAccessibilityEvent(this.mNativeObj, accessibilityEventObtain, i, i2)) {
            return accessibilityEventObtain;
        }
        accessibilityEventObtain.recycle();
        return null;
    }

    private Bundle getOrCreateBundleForAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        Bundle bundle = (Bundle) accessibilityEvent.getParcelableData();
        if (bundle != null) {
            return bundle;
        }
        Bundle bundle2 = new Bundle();
        accessibilityEvent.setParcelableData(bundle2);
        return bundle2;
    }

    private AccessibilityNodeInfo createNodeForHost(int i) {
        AccessibilityNodeInfo accessibilityNodeInfoObtain = AccessibilityNodeInfo.obtain(this.mView);
        AccessibilityNodeInfo accessibilityNodeInfoObtain2 = AccessibilityNodeInfo.obtain(this.mView);
        this.mView.onInitializeAccessibilityNodeInfo(accessibilityNodeInfoObtain2);
        Rect rect = new Rect();
        accessibilityNodeInfoObtain2.getBoundsInParent(rect);
        accessibilityNodeInfoObtain.setBoundsInParent(rect);
        accessibilityNodeInfoObtain2.getBoundsInScreen(rect);
        accessibilityNodeInfoObtain.setBoundsInScreen(rect);
        Object parentForAccessibility = this.mView.getParentForAccessibility();
        if (parentForAccessibility instanceof View) {
            accessibilityNodeInfoObtain.setParent((View) parentForAccessibility);
        }
        accessibilityNodeInfoObtain.setVisibleToUser(accessibilityNodeInfoObtain2.isVisibleToUser());
        accessibilityNodeInfoObtain.setEnabled(accessibilityNodeInfoObtain2.isEnabled());
        accessibilityNodeInfoObtain.setPackageName(accessibilityNodeInfoObtain2.getPackageName());
        accessibilityNodeInfoObtain.setClassName(accessibilityNodeInfoObtain2.getClassName());
        if (isFrameInfoInitialized()) {
            accessibilityNodeInfoObtain.addChild(this.mView, i);
        }
        return accessibilityNodeInfoObtain;
    }

    private boolean isFrameInfoInitialized() {
        return (((double) this.mRenderCoordinates.getContentWidthCss()) == 0.0d && ((double) this.mRenderCoordinates.getContentHeightCss()) == 0.0d) ? false : true;
    }

    private void handlePageLoaded(int i) {
        if (this.mNativeObj == 0 || this.mUserHasTouchExplored || !this.mContentViewCore.shouldSetAccessibilityFocusOnPageLoad()) {
            return;
        }
        moveAccessibilityFocusToIdAndRefocusIfNeeded(i);
    }

    private void handleFocusChanged(int i) {
        if (this.mNativeObj == 0) {
            return;
        }
        sendAccessibilityEvent(i, 8);
        moveAccessibilityFocusToId(i);
    }

    private void handleCheckStateChanged(int i) {
        if (this.mNativeObj == 0) {
            return;
        }
        sendAccessibilityEvent(i, 1);
    }

    private void handleClicked(int i) {
        if (this.mNativeObj == 0) {
            return;
        }
        sendAccessibilityEvent(i, 1);
    }

    private void handleTextSelectionChanged(int i) {
        if (this.mNativeObj == 0) {
            return;
        }
        sendAccessibilityEvent(i, 8192);
    }

    private void handleEditableTextChanged(int i) {
        if (this.mNativeObj == 0) {
            return;
        }
        sendAccessibilityEvent(i, 16);
    }

    private void handleSliderChanged(int i) {
        if (this.mNativeObj == 0) {
            return;
        }
        sendAccessibilityEvent(i, 4096);
    }

    private void handleContentChanged(int i) {
        long j = this.mNativeObj;
        if (j == 0) {
            return;
        }
        int iNativeGetRootId = nativeGetRootId(j);
        if (iNativeGetRootId != this.mCurrentRootId) {
            this.mCurrentRootId = iNativeGetRootId;
            sendWindowContentChangedOnView();
        } else {
            sendWindowContentChangedOnVirtualView(i);
        }
    }

    private void handleNavigate() {
        if (this.mNativeObj == 0) {
            return;
        }
        this.mAccessibilityFocusId = -1;
        this.mAccessibilityFocusRect = null;
        this.mUserHasTouchExplored = false;
        sendWindowContentChangedOnView();
    }

    private void handleScrollPositionChanged(int i) {
        if (this.mNativeObj == 0) {
            return;
        }
        sendAccessibilityEvent(i, 4096);
    }

    private void handleScrolledToAnchor(int i) {
        if (this.mNativeObj == 0) {
            return;
        }
        moveAccessibilityFocusToId(i);
    }

    private void handleHover(int i) {
        if (this.mNativeObj == 0 || this.mLastHoverId == i || !this.mIsHovering) {
            return;
        }
        sendAccessibilityEvent(i, 128);
        int i2 = this.mLastHoverId;
        if (i2 != -1) {
            sendAccessibilityEvent(i2, 256);
        }
        this.mLastHoverId = i;
    }

    private void announceLiveRegionText(String str) {
        this.mView.announceForAccessibility(str);
    }

    private void setAccessibilityNodeInfoParent(AccessibilityNodeInfo accessibilityNodeInfo, int i) {
        accessibilityNodeInfo.setParent(this.mView, i);
    }

    private void addAccessibilityNodeInfoChild(AccessibilityNodeInfo accessibilityNodeInfo, int i) {
        accessibilityNodeInfo.addChild(this.mView, i);
    }

    private void setAccessibilityNodeInfoBooleanAttributes(AccessibilityNodeInfo accessibilityNodeInfo, int i, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, boolean z8, boolean z9, boolean z10) {
        accessibilityNodeInfo.setCheckable(z);
        accessibilityNodeInfo.setChecked(z2);
        accessibilityNodeInfo.setClickable(z3);
        accessibilityNodeInfo.setEnabled(z4);
        accessibilityNodeInfo.setFocusable(z5);
        accessibilityNodeInfo.setFocused(z6);
        accessibilityNodeInfo.setPassword(z7);
        accessibilityNodeInfo.setScrollable(z8);
        accessibilityNodeInfo.setSelected(z9);
        accessibilityNodeInfo.setVisibleToUser(z10);
        accessibilityNodeInfo.setMovementGranularities(7);
        if (this.mAccessibilityFocusId == i) {
            accessibilityNodeInfo.setAccessibilityFocused(true);
        } else {
            accessibilityNodeInfo.setAccessibilityFocused(false);
        }
    }

    protected void addAccessibilityNodeInfoActions(AccessibilityNodeInfo accessibilityNodeInfo, int i, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, boolean z8, boolean z9, boolean z10, boolean z11) {
        accessibilityNodeInfo.addAction(1024);
        accessibilityNodeInfo.addAction(2048);
        accessibilityNodeInfo.addAction(256);
        accessibilityNodeInfo.addAction(512);
        if (z8 && z9) {
            accessibilityNodeInfo.addAction(2097152);
            accessibilityNodeInfo.addAction(131072);
        }
        if (z) {
            accessibilityNodeInfo.addAction(4096);
        }
        if (z2) {
            accessibilityNodeInfo.addAction(8192);
        }
        if (z10) {
            if (z11) {
                accessibilityNodeInfo.addAction(2);
            } else {
                accessibilityNodeInfo.addAction(1);
            }
        }
        if (this.mAccessibilityFocusId == i) {
            accessibilityNodeInfo.addAction(128);
        } else {
            accessibilityNodeInfo.addAction(64);
        }
        if (z7) {
            accessibilityNodeInfo.addAction(16);
        }
    }

    private void setAccessibilityNodeInfoClassName(AccessibilityNodeInfo accessibilityNodeInfo, String str) {
        accessibilityNodeInfo.setClassName(str);
    }

    private void setAccessibilityNodeInfoText(AccessibilityNodeInfo accessibilityNodeInfo, String str, boolean z, boolean z2) {
        CharSequence charSequence = str;
        if (z) {
            SpannableString spannableString = new SpannableString(str);
            spannableString.setSpan(new URLSpan(""), 0, spannableString.length(), 0);
            charSequence = spannableString;
        }
        if (z2) {
            accessibilityNodeInfo.setText(charSequence);
        } else {
            accessibilityNodeInfo.setContentDescription(charSequence);
        }
    }

    private void setAccessibilityNodeInfoLocation(AccessibilityNodeInfo accessibilityNodeInfo, int i, int i2, int i3, int i4, int i5, int i6, int i7, boolean z) {
        Rect rect = new Rect(i4, i5, i4 + i6, i5 + i7);
        if (z) {
            rect.offset(0, (int) this.mRenderCoordinates.getContentOffsetYPix());
        }
        accessibilityNodeInfo.setBoundsInParent(rect);
        Rect rect2 = new Rect(i2, i3, i6 + i2, i7 + i3);
        rect2.offset(-((int) this.mRenderCoordinates.getScrollX()), -((int) this.mRenderCoordinates.getScrollY()));
        rect2.left = (int) this.mRenderCoordinates.fromLocalCssToPix(rect2.left);
        rect2.top = (int) this.mRenderCoordinates.fromLocalCssToPix(rect2.top);
        rect2.bottom = (int) this.mRenderCoordinates.fromLocalCssToPix(rect2.bottom);
        rect2.right = (int) this.mRenderCoordinates.fromLocalCssToPix(rect2.right);
        rect2.offset(0, (int) this.mRenderCoordinates.getContentOffsetYPix());
        int[] iArr = new int[2];
        this.mView.getLocationOnScreen(iArr);
        rect2.offset(iArr[0], iArr[1]);
        int contentOffsetYPix = iArr[1] + ((int) this.mRenderCoordinates.getContentOffsetYPix());
        int viewportHeightPix = this.mContentViewCore.getViewportHeightPix() + contentOffsetYPix;
        if (rect2.top < contentOffsetYPix) {
            rect2.top = contentOffsetYPix;
        }
        if (rect2.bottom > viewportHeightPix) {
            rect2.bottom = viewportHeightPix;
        }
        accessibilityNodeInfo.setBoundsInScreen(rect2);
        if (i != this.mAccessibilityFocusId || i == this.mCurrentRootId) {
            return;
        }
        Rect rect3 = this.mAccessibilityFocusRect;
        if (rect3 == null) {
            this.mAccessibilityFocusRect = rect2;
        } else {
            if (rect3.equals(rect2)) {
                return;
            }
            this.mAccessibilityFocusRect = rect2;
            moveAccessibilityFocusToIdAndRefocusIfNeeded(i);
        }
    }

    private void setAccessibilityEventBooleanAttributes(AccessibilityEvent accessibilityEvent, boolean z, boolean z2, boolean z3, boolean z4) {
        accessibilityEvent.setChecked(z);
        accessibilityEvent.setEnabled(z2);
        accessibilityEvent.setPassword(z3);
        accessibilityEvent.setScrollable(z4);
    }

    private void setAccessibilityEventClassName(AccessibilityEvent accessibilityEvent, String str) {
        accessibilityEvent.setClassName(str);
    }

    private void setAccessibilityEventListAttributes(AccessibilityEvent accessibilityEvent, int i, int i2) {
        accessibilityEvent.setCurrentItemIndex(i);
        accessibilityEvent.setItemCount(i2);
    }

    private void setAccessibilityEventScrollAttributes(AccessibilityEvent accessibilityEvent, int i, int i2, int i3, int i4) {
        accessibilityEvent.setScrollX(i);
        accessibilityEvent.setScrollY(i2);
        accessibilityEvent.setMaxScrollX(i3);
        accessibilityEvent.setMaxScrollY(i4);
    }

    private void setAccessibilityEventTextChangedAttrs(AccessibilityEvent accessibilityEvent, int i, int i2, int i3, String str, String str2) {
        accessibilityEvent.setFromIndex(i);
        accessibilityEvent.setAddedCount(i2);
        accessibilityEvent.setRemovedCount(i3);
        accessibilityEvent.setBeforeText(str);
        accessibilityEvent.getText().add(str2);
    }

    private void setAccessibilityEventSelectionAttrs(AccessibilityEvent accessibilityEvent, int i, int i2, int i3, String str) {
        accessibilityEvent.setFromIndex(i);
        accessibilityEvent.setToIndex(i2);
        accessibilityEvent.setItemCount(i3);
        accessibilityEvent.getText().add(str);
    }

    protected void setAccessibilityEventLollipopAttributes(AccessibilityEvent accessibilityEvent, boolean z, boolean z2, boolean z3, boolean z4, int i, int i2) {
        Bundle orCreateBundleForAccessibilityEvent = getOrCreateBundleForAccessibilityEvent(accessibilityEvent);
        orCreateBundleForAccessibilityEvent.putBoolean("AccessibilityNodeInfo.canOpenPopup", z);
        orCreateBundleForAccessibilityEvent.putBoolean("AccessibilityNodeInfo.contentInvalid", z2);
        orCreateBundleForAccessibilityEvent.putBoolean("AccessibilityNodeInfo.dismissable", z3);
        orCreateBundleForAccessibilityEvent.putBoolean("AccessibilityNodeInfo.multiLine", z4);
        orCreateBundleForAccessibilityEvent.putInt("AccessibilityNodeInfo.inputType", i);
        orCreateBundleForAccessibilityEvent.putInt("AccessibilityNodeInfo.liveRegion", i2);
    }

    protected void setAccessibilityEventCollectionInfo(AccessibilityEvent accessibilityEvent, int i, int i2, boolean z) {
        Bundle orCreateBundleForAccessibilityEvent = getOrCreateBundleForAccessibilityEvent(accessibilityEvent);
        orCreateBundleForAccessibilityEvent.putInt("AccessibilityNodeInfo.CollectionInfo.rowCount", i);
        orCreateBundleForAccessibilityEvent.putInt("AccessibilityNodeInfo.CollectionInfo.columnCount", i2);
        orCreateBundleForAccessibilityEvent.putBoolean("AccessibilityNodeInfo.CollectionInfo.hierarchical", z);
    }

    protected void setAccessibilityEventHeadingFlag(AccessibilityEvent accessibilityEvent, boolean z) {
        getOrCreateBundleForAccessibilityEvent(accessibilityEvent).putBoolean("AccessibilityNodeInfo.CollectionItemInfo.heading", z);
    }

    protected void setAccessibilityEventCollectionItemInfo(AccessibilityEvent accessibilityEvent, int i, int i2, int i3, int i4) {
        Bundle orCreateBundleForAccessibilityEvent = getOrCreateBundleForAccessibilityEvent(accessibilityEvent);
        orCreateBundleForAccessibilityEvent.putInt("AccessibilityNodeInfo.CollectionItemInfo.rowIndex", i);
        orCreateBundleForAccessibilityEvent.putInt("AccessibilityNodeInfo.CollectionItemInfo.rowSpan", i2);
        orCreateBundleForAccessibilityEvent.putInt("AccessibilityNodeInfo.CollectionItemInfo.columnIndex", i3);
        orCreateBundleForAccessibilityEvent.putInt("AccessibilityNodeInfo.CollectionItemInfo.columnSpan", i4);
    }

    protected void setAccessibilityEventRangeInfo(AccessibilityEvent accessibilityEvent, int i, float f, float f2, float f3) {
        Bundle orCreateBundleForAccessibilityEvent = getOrCreateBundleForAccessibilityEvent(accessibilityEvent);
        orCreateBundleForAccessibilityEvent.putInt("AccessibilityNodeInfo.RangeInfo.type", i);
        orCreateBundleForAccessibilityEvent.putFloat("AccessibilityNodeInfo.RangeInfo.min", f);
        orCreateBundleForAccessibilityEvent.putFloat("AccessibilityNodeInfo.RangeInfo.max", f2);
        orCreateBundleForAccessibilityEvent.putFloat("AccessibilityNodeInfo.RangeInfo.current", f3);
    }

    boolean shouldExposePasswordText() {
        return Settings.Secure.getInt(this.mContentViewCore.getContext().getContentResolver(), "speak_password", 0) == 1;
    }
}
