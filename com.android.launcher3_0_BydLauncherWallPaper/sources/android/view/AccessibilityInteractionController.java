package android.view;

import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Parcelable;
import android.os.Process;
import android.os.RemoteException;
import android.os.SystemClock;
import android.text.style.AccessibilityClickableSpan;
import android.text.style.ClickableSpan;
import android.util.LongSparseArray;
import android.util.Slog;
import android.view.accessibility.AccessibilityInteractionClient;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.AccessibilityNodeIdManager;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeProvider;
import android.view.accessibility.AccessibilityRequestPreparer;
import android.view.accessibility.IAccessibilityInteractionConnectionCallback;
import com.android.internal.os.SomeArgs;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

/* JADX INFO: loaded from: classes3.dex */
public final class AccessibilityInteractionController {
    private static final boolean CONSIDER_REQUEST_PREPARERS = false;
    private static final boolean ENFORCE_NODE_TREE_CONSISTENT = false;
    private static final boolean IGNORE_REQUEST_PREPARERS = true;
    private static final String LOG_TAG = "AccessibilityInteractionController";
    private static final long REQUEST_PREPARER_TIMEOUT_MS = 500;
    private final AccessibilityManager mA11yManager;
    private int mActiveRequestPreparerId;
    private AddNodeInfosForViewId mAddNodeInfosForViewId;
    private final PrivateHandler mHandler;
    private List<MessageHolder> mMessagesWaitingForRequestPreparer;
    private final long mMyLooperThreadId;
    private final int mMyProcessId;
    private int mNumActiveRequestPreparers;
    private final AccessibilityNodePrefetcher mPrefetcher;
    private final ViewRootImpl mViewRootImpl;
    private final ArrayList<AccessibilityNodeInfo> mTempAccessibilityNodeInfoList = new ArrayList<>();
    private final Object mLock = new Object();
    private final ArrayList<View> mTempArrayList = new ArrayList<>();
    private final Point mTempPoint = new Point();
    private final Rect mTempRect = new Rect();
    private final Rect mTempRect1 = new Rect();
    private final Rect mTempRect2 = new Rect();
    private final RectF mTempRectF = new RectF();

    public AccessibilityInteractionController(ViewRootImpl viewRootImpl) {
        Looper looper = viewRootImpl.mHandler.getLooper();
        this.mMyLooperThreadId = looper.getThread().getId();
        this.mMyProcessId = Process.myPid();
        this.mHandler = new PrivateHandler(looper);
        this.mViewRootImpl = viewRootImpl;
        this.mPrefetcher = new AccessibilityNodePrefetcher();
        this.mA11yManager = (AccessibilityManager) viewRootImpl.mContext.getSystemService(AccessibilityManager.class);
    }

    private void scheduleMessage(Message message, int i, long j, boolean z) throws Throwable {
        if (z || !holdOffMessageIfNeeded(message, i, j)) {
            if (i == this.mMyProcessId && j == this.mMyLooperThreadId && this.mHandler.hasAccessibilityCallback(message)) {
                AccessibilityInteractionClient.getInstanceForThread(j).setSameThreadMessage(message);
            } else if (!this.mHandler.hasAccessibilityCallback(message) && Thread.currentThread().getId() == this.mMyLooperThreadId) {
                this.mHandler.handleMessage(message);
            } else {
                this.mHandler.sendMessage(message);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isShown(View view) {
        return view != null && view.getWindowVisibility() == 0 && view.isShown();
    }

    public void findAccessibilityNodeInfoByAccessibilityIdClientThread(long j, Region region, int i, IAccessibilityInteractionConnectionCallback iAccessibilityInteractionConnectionCallback, int i2, int i3, long j2, MagnificationSpec magnificationSpec, Bundle bundle) {
        Message messageObtainMessage = this.mHandler.obtainMessage();
        messageObtainMessage.what = 2;
        messageObtainMessage.arg1 = i2;
        SomeArgs someArgsObtain = SomeArgs.obtain();
        someArgsObtain.argi1 = AccessibilityNodeInfo.getAccessibilityViewId(j);
        someArgsObtain.argi2 = AccessibilityNodeInfo.getVirtualDescendantId(j);
        someArgsObtain.argi3 = i;
        someArgsObtain.arg1 = iAccessibilityInteractionConnectionCallback;
        someArgsObtain.arg2 = magnificationSpec;
        someArgsObtain.arg3 = region;
        someArgsObtain.arg4 = bundle;
        messageObtainMessage.obj = someArgsObtain;
        scheduleMessage(messageObtainMessage, i3, j2, false);
    }

    private boolean holdOffMessageIfNeeded(Message message, int i, long j) {
        synchronized (this.mLock) {
            if (this.mNumActiveRequestPreparers != 0) {
                queueMessageToHandleOncePrepared(message, i, j);
                return true;
            }
            if (message.what != 2) {
                return false;
            }
            SomeArgs someArgs = (SomeArgs) message.obj;
            Bundle bundle = (Bundle) someArgs.arg4;
            if (bundle == null) {
                return false;
            }
            List<AccessibilityRequestPreparer> requestPreparersForAccessibilityId = this.mA11yManager.getRequestPreparersForAccessibilityId(someArgs.argi1);
            if (requestPreparersForAccessibilityId == null) {
                return false;
            }
            String string = bundle.getString(AccessibilityNodeInfo.EXTRA_DATA_REQUESTED_KEY);
            if (string == null) {
                return false;
            }
            this.mNumActiveRequestPreparers = requestPreparersForAccessibilityId.size();
            for (int i2 = 0; i2 < requestPreparersForAccessibilityId.size(); i2++) {
                Message messageObtainMessage = this.mHandler.obtainMessage(7);
                SomeArgs someArgsObtain = SomeArgs.obtain();
                someArgsObtain.argi1 = someArgs.argi2 == Integer.MAX_VALUE ? -1 : someArgs.argi2;
                someArgsObtain.arg1 = requestPreparersForAccessibilityId.get(i2);
                someArgsObtain.arg2 = string;
                someArgsObtain.arg3 = bundle;
                Message messageObtainMessage2 = this.mHandler.obtainMessage(8);
                int i3 = this.mActiveRequestPreparerId + 1;
                this.mActiveRequestPreparerId = i3;
                messageObtainMessage2.arg1 = i3;
                someArgsObtain.arg4 = messageObtainMessage2;
                messageObtainMessage.obj = someArgsObtain;
                scheduleMessage(messageObtainMessage, i, j, true);
                this.mHandler.obtainMessage(9);
                this.mHandler.sendEmptyMessageDelayed(9, REQUEST_PREPARER_TIMEOUT_MS);
            }
            queueMessageToHandleOncePrepared(message, i, j);
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void prepareForExtraDataRequestUiThread(Message message) {
        SomeArgs someArgs = (SomeArgs) message.obj;
        ((AccessibilityRequestPreparer) someArgs.arg1).onPrepareExtraData(someArgs.argi1, (String) someArgs.arg2, (Bundle) someArgs.arg3, (Message) someArgs.arg4);
    }

    private void queueMessageToHandleOncePrepared(Message message, int i, long j) {
        if (this.mMessagesWaitingForRequestPreparer == null) {
            this.mMessagesWaitingForRequestPreparer = new ArrayList(1);
        }
        this.mMessagesWaitingForRequestPreparer.add(new MessageHolder(message, i, j));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void requestPreparerDoneUiThread(Message message) {
        synchronized (this.mLock) {
            if (message.arg1 != this.mActiveRequestPreparerId) {
                Slog.e(LOG_TAG, "Surprising AccessibilityRequestPreparer callback (likely late)");
                return;
            }
            int i = this.mNumActiveRequestPreparers - 1;
            this.mNumActiveRequestPreparers = i;
            if (i <= 0) {
                this.mHandler.removeMessages(9);
                scheduleAllMessagesWaitingForRequestPreparerLocked();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void requestPreparerTimeoutUiThread() {
        synchronized (this.mLock) {
            Slog.e(LOG_TAG, "AccessibilityRequestPreparer timed out");
            scheduleAllMessagesWaitingForRequestPreparerLocked();
        }
    }

    private void scheduleAllMessagesWaitingForRequestPreparerLocked() throws Throwable {
        int size = this.mMessagesWaitingForRequestPreparer.size();
        int i = 0;
        while (i < size) {
            MessageHolder messageHolder = this.mMessagesWaitingForRequestPreparer.get(i);
            scheduleMessage(messageHolder.mMessage, messageHolder.mInterrogatingPid, messageHolder.mInterrogatingTid, i == 0);
            i++;
        }
        this.mMessagesWaitingForRequestPreparer.clear();
        this.mNumActiveRequestPreparers = 0;
        this.mActiveRequestPreparerId = -1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void findAccessibilityNodeInfoByAccessibilityIdUiThread(Message message) {
        int i = message.arg1;
        SomeArgs someArgs = (SomeArgs) message.obj;
        int i2 = someArgs.argi1;
        int i3 = someArgs.argi2;
        int i4 = someArgs.argi3;
        IAccessibilityInteractionConnectionCallback iAccessibilityInteractionConnectionCallback = (IAccessibilityInteractionConnectionCallback) someArgs.arg1;
        MagnificationSpec magnificationSpec = (MagnificationSpec) someArgs.arg2;
        Region region = (Region) someArgs.arg3;
        Bundle bundle = (Bundle) someArgs.arg4;
        someArgs.recycle();
        ArrayList<AccessibilityNodeInfo> arrayList = this.mTempAccessibilityNodeInfoList;
        arrayList.clear();
        try {
            if (this.mViewRootImpl.mView != null && this.mViewRootImpl.mAttachInfo != null) {
                this.mViewRootImpl.mAttachInfo.mAccessibilityFetchFlags = i;
                View viewFindViewByAccessibilityId = findViewByAccessibilityId(i2);
                if (viewFindViewByAccessibilityId != null && isShown(viewFindViewByAccessibilityId)) {
                    this.mPrefetcher.prefetchAccessibilityNodeInfos(viewFindViewByAccessibilityId, i3, i, arrayList, bundle);
                }
            }
        } finally {
            updateInfosForViewportAndReturnFindNodeResult(arrayList, iAccessibilityInteractionConnectionCallback, i4, magnificationSpec, region);
        }
    }

    public void findAccessibilityNodeInfosByViewIdClientThread(long j, String str, Region region, int i, IAccessibilityInteractionConnectionCallback iAccessibilityInteractionConnectionCallback, int i2, int i3, long j2, MagnificationSpec magnificationSpec) {
        Message messageObtainMessage = this.mHandler.obtainMessage();
        messageObtainMessage.what = 3;
        messageObtainMessage.arg1 = i2;
        messageObtainMessage.arg2 = AccessibilityNodeInfo.getAccessibilityViewId(j);
        SomeArgs someArgsObtain = SomeArgs.obtain();
        someArgsObtain.argi1 = i;
        someArgsObtain.arg1 = iAccessibilityInteractionConnectionCallback;
        someArgsObtain.arg2 = magnificationSpec;
        someArgsObtain.arg3 = str;
        someArgsObtain.arg4 = region;
        messageObtainMessage.obj = someArgsObtain;
        scheduleMessage(messageObtainMessage, i3, j2, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void findAccessibilityNodeInfosByViewIdUiThread(Message message) {
        int i = message.arg1;
        int i2 = message.arg2;
        SomeArgs someArgs = (SomeArgs) message.obj;
        int i3 = someArgs.argi1;
        IAccessibilityInteractionConnectionCallback iAccessibilityInteractionConnectionCallback = (IAccessibilityInteractionConnectionCallback) someArgs.arg1;
        MagnificationSpec magnificationSpec = (MagnificationSpec) someArgs.arg2;
        String str = (String) someArgs.arg3;
        Region region = (Region) someArgs.arg4;
        someArgs.recycle();
        ArrayList<AccessibilityNodeInfo> arrayList = this.mTempAccessibilityNodeInfoList;
        arrayList.clear();
        try {
            if (this.mViewRootImpl.mView != null && this.mViewRootImpl.mAttachInfo != null) {
                this.mViewRootImpl.mAttachInfo.mAccessibilityFetchFlags = i;
                View viewFindViewByAccessibilityId = findViewByAccessibilityId(i2);
                if (viewFindViewByAccessibilityId != null) {
                    int identifier = viewFindViewByAccessibilityId.getContext().getResources().getIdentifier(str, null, null);
                    if (identifier > 0) {
                        if (this.mAddNodeInfosForViewId == null) {
                            this.mAddNodeInfosForViewId = new AddNodeInfosForViewId();
                        }
                        this.mAddNodeInfosForViewId.init(identifier, arrayList);
                        viewFindViewByAccessibilityId.findViewByPredicate(this.mAddNodeInfosForViewId);
                        this.mAddNodeInfosForViewId.reset();
                    }
                }
            }
        } finally {
            updateInfosForViewportAndReturnFindNodeResult(arrayList, iAccessibilityInteractionConnectionCallback, i3, magnificationSpec, region);
        }
    }

    public void findAccessibilityNodeInfosByTextClientThread(long j, String str, Region region, int i, IAccessibilityInteractionConnectionCallback iAccessibilityInteractionConnectionCallback, int i2, int i3, long j2, MagnificationSpec magnificationSpec) {
        Message messageObtainMessage = this.mHandler.obtainMessage();
        messageObtainMessage.what = 4;
        messageObtainMessage.arg1 = i2;
        SomeArgs someArgsObtain = SomeArgs.obtain();
        someArgsObtain.arg1 = str;
        someArgsObtain.arg2 = iAccessibilityInteractionConnectionCallback;
        someArgsObtain.arg3 = magnificationSpec;
        someArgsObtain.argi1 = AccessibilityNodeInfo.getAccessibilityViewId(j);
        someArgsObtain.argi2 = AccessibilityNodeInfo.getVirtualDescendantId(j);
        someArgsObtain.argi3 = i;
        someArgsObtain.arg4 = region;
        messageObtainMessage.obj = someArgsObtain;
        scheduleMessage(messageObtainMessage, i3, j2, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void findAccessibilityNodeInfosByTextUiThread(Message message) throws Throwable {
        List<AccessibilityNodeInfo> listFindAccessibilityNodeInfosByText;
        int i = message.arg1;
        SomeArgs someArgs = (SomeArgs) message.obj;
        String str = (String) someArgs.arg1;
        IAccessibilityInteractionConnectionCallback iAccessibilityInteractionConnectionCallback = (IAccessibilityInteractionConnectionCallback) someArgs.arg2;
        MagnificationSpec magnificationSpec = (MagnificationSpec) someArgs.arg3;
        int i2 = someArgs.argi1;
        int i3 = someArgs.argi2;
        int i4 = someArgs.argi3;
        Region region = (Region) someArgs.arg4;
        someArgs.recycle();
        List<AccessibilityNodeInfo> list = null;
        try {
            if (this.mViewRootImpl.mView != null && this.mViewRootImpl.mAttachInfo != null) {
                this.mViewRootImpl.mAttachInfo.mAccessibilityFetchFlags = i;
                View viewFindViewByAccessibilityId = findViewByAccessibilityId(i2);
                if (viewFindViewByAccessibilityId != null && isShown(viewFindViewByAccessibilityId)) {
                    AccessibilityNodeProvider accessibilityNodeProvider = viewFindViewByAccessibilityId.getAccessibilityNodeProvider();
                    if (accessibilityNodeProvider != null) {
                        listFindAccessibilityNodeInfosByText = accessibilityNodeProvider.findAccessibilityNodeInfosByText(str, i3);
                    } else if (i3 == -1) {
                        ArrayList<View> arrayList = this.mTempArrayList;
                        arrayList.clear();
                        viewFindViewByAccessibilityId.findViewsWithText(arrayList, str, 7);
                        if (!arrayList.isEmpty()) {
                            listFindAccessibilityNodeInfosByText = this.mTempAccessibilityNodeInfoList;
                            try {
                                listFindAccessibilityNodeInfosByText.clear();
                                int size = arrayList.size();
                                for (int i5 = 0; i5 < size; i5++) {
                                    View view = arrayList.get(i5);
                                    if (isShown(view)) {
                                        AccessibilityNodeProvider accessibilityNodeProvider2 = view.getAccessibilityNodeProvider();
                                        if (accessibilityNodeProvider2 != null) {
                                            List<AccessibilityNodeInfo> listFindAccessibilityNodeInfosByText2 = accessibilityNodeProvider2.findAccessibilityNodeInfosByText(str, -1);
                                            if (listFindAccessibilityNodeInfosByText2 != null) {
                                                listFindAccessibilityNodeInfosByText.addAll(listFindAccessibilityNodeInfosByText2);
                                            }
                                        } else {
                                            listFindAccessibilityNodeInfosByText.add(view.createAccessibilityNodeInfo());
                                        }
                                    }
                                }
                            } catch (Throwable th) {
                                list = listFindAccessibilityNodeInfosByText;
                                th = th;
                                updateInfosForViewportAndReturnFindNodeResult(list, iAccessibilityInteractionConnectionCallback, i4, magnificationSpec, region);
                                throw th;
                            }
                        }
                    }
                    list = listFindAccessibilityNodeInfosByText;
                }
                updateInfosForViewportAndReturnFindNodeResult(list, iAccessibilityInteractionConnectionCallback, i4, magnificationSpec, region);
                return;
            }
            updateInfosForViewportAndReturnFindNodeResult(null, iAccessibilityInteractionConnectionCallback, i4, magnificationSpec, region);
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public void findFocusClientThread(long j, int i, Region region, int i2, IAccessibilityInteractionConnectionCallback iAccessibilityInteractionConnectionCallback, int i3, int i4, long j2, MagnificationSpec magnificationSpec) {
        Message messageObtainMessage = this.mHandler.obtainMessage();
        messageObtainMessage.what = 5;
        messageObtainMessage.arg1 = i3;
        messageObtainMessage.arg2 = i;
        SomeArgs someArgsObtain = SomeArgs.obtain();
        someArgsObtain.argi1 = i2;
        someArgsObtain.argi2 = AccessibilityNodeInfo.getAccessibilityViewId(j);
        someArgsObtain.argi3 = AccessibilityNodeInfo.getVirtualDescendantId(j);
        someArgsObtain.arg1 = iAccessibilityInteractionConnectionCallback;
        someArgsObtain.arg2 = magnificationSpec;
        someArgsObtain.arg3 = region;
        messageObtainMessage.obj = someArgsObtain;
        scheduleMessage(messageObtainMessage, i4, j2, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void findFocusUiThread(Message message) {
        int i = message.arg1;
        int i2 = message.arg2;
        SomeArgs someArgs = (SomeArgs) message.obj;
        int i3 = someArgs.argi1;
        int i4 = someArgs.argi2;
        int i5 = someArgs.argi3;
        IAccessibilityInteractionConnectionCallback iAccessibilityInteractionConnectionCallback = (IAccessibilityInteractionConnectionCallback) someArgs.arg1;
        MagnificationSpec magnificationSpec = (MagnificationSpec) someArgs.arg2;
        Region region = (Region) someArgs.arg3;
        someArgs.recycle();
        try {
            if (this.mViewRootImpl.mView != null && this.mViewRootImpl.mAttachInfo != null) {
                this.mViewRootImpl.mAttachInfo.mAccessibilityFetchFlags = i;
                View viewFindViewByAccessibilityId = findViewByAccessibilityId(i4);
                if (viewFindViewByAccessibilityId != null && isShown(viewFindViewByAccessibilityId)) {
                    if (i2 == 1) {
                        View viewFindFocus = viewFindViewByAccessibilityId.findFocus();
                        if (isShown(viewFindFocus)) {
                            AccessibilityNodeProvider accessibilityNodeProvider = viewFindFocus.getAccessibilityNodeProvider();
                            accessibilityNodeInfoFindFocus = accessibilityNodeProvider != null ? accessibilityNodeProvider.findFocus(i2) : null;
                            if (accessibilityNodeInfoFindFocus == null) {
                                accessibilityNodeInfoFindFocus = viewFindFocus.createAccessibilityNodeInfo();
                            }
                        }
                    } else if (i2 == 2) {
                        View view = this.mViewRootImpl.mAccessibilityFocusedHost;
                        if (view != null && ViewRootImpl.isViewDescendantOf(view, viewFindViewByAccessibilityId) && isShown(view)) {
                            if (view.getAccessibilityNodeProvider() != null) {
                                if (this.mViewRootImpl.mAccessibilityFocusedVirtualView != null) {
                                    accessibilityNodeInfoFindFocus = AccessibilityNodeInfo.obtain(this.mViewRootImpl.mAccessibilityFocusedVirtualView);
                                }
                            } else if (i5 == -1) {
                                accessibilityNodeInfoFindFocus = view.createAccessibilityNodeInfo();
                            }
                        }
                    } else {
                        throw new IllegalArgumentException("Unknown focus type: " + i2);
                    }
                }
                updateInfoForViewportAndReturnFindNodeResult(accessibilityNodeInfoFindFocus, iAccessibilityInteractionConnectionCallback, i3, magnificationSpec, region);
            }
        } finally {
            updateInfoForViewportAndReturnFindNodeResult(null, iAccessibilityInteractionConnectionCallback, i3, magnificationSpec, region);
        }
    }

    public void focusSearchClientThread(long j, int i, Region region, int i2, IAccessibilityInteractionConnectionCallback iAccessibilityInteractionConnectionCallback, int i3, int i4, long j2, MagnificationSpec magnificationSpec) {
        Message messageObtainMessage = this.mHandler.obtainMessage();
        messageObtainMessage.what = 6;
        messageObtainMessage.arg1 = i3;
        messageObtainMessage.arg2 = AccessibilityNodeInfo.getAccessibilityViewId(j);
        SomeArgs someArgsObtain = SomeArgs.obtain();
        someArgsObtain.argi2 = i;
        someArgsObtain.argi3 = i2;
        someArgsObtain.arg1 = iAccessibilityInteractionConnectionCallback;
        someArgsObtain.arg2 = magnificationSpec;
        someArgsObtain.arg3 = region;
        messageObtainMessage.obj = someArgsObtain;
        scheduleMessage(messageObtainMessage, i4, j2, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void focusSearchUiThread(Message message) {
        View viewFocusSearch;
        int i = message.arg1;
        int i2 = message.arg2;
        SomeArgs someArgs = (SomeArgs) message.obj;
        int i3 = someArgs.argi2;
        int i4 = someArgs.argi3;
        IAccessibilityInteractionConnectionCallback iAccessibilityInteractionConnectionCallback = (IAccessibilityInteractionConnectionCallback) someArgs.arg1;
        MagnificationSpec magnificationSpec = (MagnificationSpec) someArgs.arg2;
        Region region = (Region) someArgs.arg3;
        someArgs.recycle();
        try {
            AccessibilityNodeInfo accessibilityNodeInfoCreateAccessibilityNodeInfo = null;
            if (this.mViewRootImpl.mView != null && this.mViewRootImpl.mAttachInfo != null) {
                this.mViewRootImpl.mAttachInfo.mAccessibilityFetchFlags = i;
                View viewFindViewByAccessibilityId = findViewByAccessibilityId(i2);
                if (viewFindViewByAccessibilityId != null && isShown(viewFindViewByAccessibilityId) && (viewFocusSearch = viewFindViewByAccessibilityId.focusSearch(i3)) != null) {
                    accessibilityNodeInfoCreateAccessibilityNodeInfo = viewFocusSearch.createAccessibilityNodeInfo();
                }
                updateInfoForViewportAndReturnFindNodeResult(accessibilityNodeInfoCreateAccessibilityNodeInfo, iAccessibilityInteractionConnectionCallback, i4, magnificationSpec, region);
                return;
            }
            updateInfoForViewportAndReturnFindNodeResult(null, iAccessibilityInteractionConnectionCallback, i4, magnificationSpec, region);
        } catch (Throwable th) {
            updateInfoForViewportAndReturnFindNodeResult(null, iAccessibilityInteractionConnectionCallback, i4, magnificationSpec, region);
            throw th;
        }
    }

    public void performAccessibilityActionClientThread(long j, int i, Bundle bundle, int i2, IAccessibilityInteractionConnectionCallback iAccessibilityInteractionConnectionCallback, int i3, int i4, long j2) {
        Message messageObtainMessage = this.mHandler.obtainMessage();
        messageObtainMessage.what = 1;
        messageObtainMessage.arg1 = i3;
        messageObtainMessage.arg2 = AccessibilityNodeInfo.getAccessibilityViewId(j);
        SomeArgs someArgsObtain = SomeArgs.obtain();
        someArgsObtain.argi1 = AccessibilityNodeInfo.getVirtualDescendantId(j);
        someArgsObtain.argi2 = i;
        someArgsObtain.argi3 = i2;
        someArgsObtain.arg1 = iAccessibilityInteractionConnectionCallback;
        someArgsObtain.arg2 = bundle;
        messageObtainMessage.obj = someArgsObtain;
        scheduleMessage(messageObtainMessage, i4, j2, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0062  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void performAccessibilityActionUiThread(android.os.Message r9) {
        /*
            r8 = this;
            int r0 = r9.arg1
            int r1 = r9.arg2
            java.lang.Object r9 = r9.obj
            com.android.internal.os.SomeArgs r9 = (com.android.internal.os.SomeArgs) r9
            int r2 = r9.argi1
            int r3 = r9.argi2
            int r4 = r9.argi3
            java.lang.Object r5 = r9.arg1
            android.view.accessibility.IAccessibilityInteractionConnectionCallback r5 = (android.view.accessibility.IAccessibilityInteractionConnectionCallback) r5
            java.lang.Object r6 = r9.arg2
            android.os.Bundle r6 = (android.os.Bundle) r6
            r9.recycle()
            r9 = 0
            android.view.ViewRootImpl r7 = r8.mViewRootImpl     // Catch: java.lang.Throwable -> L77
            android.view.View r7 = r7.mView     // Catch: java.lang.Throwable -> L77
            if (r7 == 0) goto L6d
            android.view.ViewRootImpl r7 = r8.mViewRootImpl     // Catch: java.lang.Throwable -> L77
            android.view.View$AttachInfo r7 = r7.mAttachInfo     // Catch: java.lang.Throwable -> L77
            if (r7 == 0) goto L6d
            android.view.ViewRootImpl r7 = r8.mViewRootImpl     // Catch: java.lang.Throwable -> L77
            boolean r7 = r7.mStopped     // Catch: java.lang.Throwable -> L77
            if (r7 != 0) goto L6d
            android.view.ViewRootImpl r7 = r8.mViewRootImpl     // Catch: java.lang.Throwable -> L77
            boolean r7 = r7.mPausedForTransition     // Catch: java.lang.Throwable -> L77
            if (r7 == 0) goto L33
            goto L6d
        L33:
            android.view.ViewRootImpl r7 = r8.mViewRootImpl     // Catch: java.lang.Throwable -> L77
            android.view.View$AttachInfo r7 = r7.mAttachInfo     // Catch: java.lang.Throwable -> L77
            r7.mAccessibilityFetchFlags = r0     // Catch: java.lang.Throwable -> L77
            android.view.View r0 = r8.findViewByAccessibilityId(r1)     // Catch: java.lang.Throwable -> L77
            if (r0 == 0) goto L62
            boolean r1 = r8.isShown(r0)     // Catch: java.lang.Throwable -> L77
            if (r1 == 0) goto L62
            r1 = 16908669(0x102017d, float:2.3878297E-38)
            if (r3 != r1) goto L4f
            boolean r0 = r8.handleClickableSpanActionUiThread(r0, r2, r6)     // Catch: java.lang.Throwable -> L77
            goto L63
        L4f:
            android.view.accessibility.AccessibilityNodeProvider r1 = r0.getAccessibilityNodeProvider()     // Catch: java.lang.Throwable -> L77
            if (r1 == 0) goto L5a
            boolean r0 = r1.performAction(r2, r3, r6)     // Catch: java.lang.Throwable -> L77
            goto L63
        L5a:
            r1 = -1
            if (r2 != r1) goto L62
            boolean r0 = r0.performAccessibilityAction(r3, r6)     // Catch: java.lang.Throwable -> L77
            goto L63
        L62:
            r0 = r9
        L63:
            android.view.ViewRootImpl r8 = r8.mViewRootImpl     // Catch: android.os.RemoteException -> L6c
            android.view.View$AttachInfo r8 = r8.mAttachInfo     // Catch: android.os.RemoteException -> L6c
            r8.mAccessibilityFetchFlags = r9     // Catch: android.os.RemoteException -> L6c
            r5.setPerformAccessibilityActionResult(r0, r4)     // Catch: android.os.RemoteException -> L6c
        L6c:
            return
        L6d:
            android.view.ViewRootImpl r8 = r8.mViewRootImpl     // Catch: android.os.RemoteException -> L76
            android.view.View$AttachInfo r8 = r8.mAttachInfo     // Catch: android.os.RemoteException -> L76
            r8.mAccessibilityFetchFlags = r9     // Catch: android.os.RemoteException -> L76
            r5.setPerformAccessibilityActionResult(r9, r4)     // Catch: android.os.RemoteException -> L76
        L76:
            return
        L77:
            r0 = move-exception
            android.view.ViewRootImpl r8 = r8.mViewRootImpl     // Catch: android.os.RemoteException -> L81
            android.view.View$AttachInfo r8 = r8.mAttachInfo     // Catch: android.os.RemoteException -> L81
            r8.mAccessibilityFetchFlags = r9     // Catch: android.os.RemoteException -> L81
            r5.setPerformAccessibilityActionResult(r9, r4)     // Catch: android.os.RemoteException -> L81
        L81:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: android.view.AccessibilityInteractionController.performAccessibilityActionUiThread(android.os.Message):void");
    }

    public void clearAccessibilityFocusClientThread() {
        Message messageObtainMessage = this.mHandler.obtainMessage();
        messageObtainMessage.what = 101;
        scheduleMessage(messageObtainMessage, 0, 0L, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearAccessibilityFocusUiThread() {
        if (this.mViewRootImpl.mView == null || this.mViewRootImpl.mAttachInfo == null) {
            return;
        }
        try {
            this.mViewRootImpl.mAttachInfo.mAccessibilityFetchFlags = 8;
            View view = this.mViewRootImpl.mView;
            if (view != null && isShown(view)) {
                View view2 = this.mViewRootImpl.mAccessibilityFocusedHost;
                if (view2 != null && ViewRootImpl.isViewDescendantOf(view2, view)) {
                    AccessibilityNodeProvider accessibilityNodeProvider = view2.getAccessibilityNodeProvider();
                    AccessibilityNodeInfo accessibilityNodeInfo = this.mViewRootImpl.mAccessibilityFocusedVirtualView;
                    if (accessibilityNodeProvider != null && accessibilityNodeInfo != null) {
                        accessibilityNodeProvider.performAction(AccessibilityNodeInfo.getVirtualDescendantId(accessibilityNodeInfo.getSourceNodeId()), AccessibilityNodeInfo.AccessibilityAction.ACTION_CLEAR_ACCESSIBILITY_FOCUS.getId(), null);
                    } else {
                        view2.performAccessibilityAction(AccessibilityNodeInfo.AccessibilityAction.ACTION_CLEAR_ACCESSIBILITY_FOCUS.getId(), null);
                    }
                }
            }
        } finally {
            this.mViewRootImpl.mAttachInfo.mAccessibilityFetchFlags = 0;
        }
    }

    public void notifyOutsideTouchClientThread() {
        Message messageObtainMessage = this.mHandler.obtainMessage();
        messageObtainMessage.what = 102;
        scheduleMessage(messageObtainMessage, 0, 0L, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyOutsideTouchUiThread() {
        View view;
        if (this.mViewRootImpl.mView == null || this.mViewRootImpl.mAttachInfo == null || this.mViewRootImpl.mStopped || this.mViewRootImpl.mPausedForTransition || (view = this.mViewRootImpl.mView) == null || !isShown(view)) {
            return;
        }
        long jUptimeMillis = SystemClock.uptimeMillis();
        MotionEvent motionEventObtain = MotionEvent.obtain(jUptimeMillis, jUptimeMillis, 4, 0.0f, 0.0f, 0);
        motionEventObtain.setSource(4098);
        this.mViewRootImpl.dispatchInputEvent(motionEventObtain);
    }

    private View findViewByAccessibilityId(int i) {
        if (i == 2147483646) {
            return this.mViewRootImpl.mView;
        }
        return AccessibilityNodeIdManager.getInstance().findView(i);
    }

    private void applyAppScaleAndMagnificationSpecIfNeeded(List<AccessibilityNodeInfo> list, MagnificationSpec magnificationSpec) {
        if (list != null && shouldApplyAppScaleAndMagnificationSpec(this.mViewRootImpl.mAttachInfo.mApplicationScale, magnificationSpec)) {
            int size = list.size();
            for (int i = 0; i < size; i++) {
                applyAppScaleAndMagnificationSpecIfNeeded(list.get(i), magnificationSpec);
            }
        }
    }

    private void adjustIsVisibleToUserIfNeeded(List<AccessibilityNodeInfo> list, Region region) {
        if (region == null || list == null) {
            return;
        }
        int size = list.size();
        for (int i = 0; i < size; i++) {
            adjustIsVisibleToUserIfNeeded(list.get(i), region);
        }
    }

    private void adjustIsVisibleToUserIfNeeded(AccessibilityNodeInfo accessibilityNodeInfo, Region region) {
        if (region == null || accessibilityNodeInfo == null) {
            return;
        }
        Rect rect = this.mTempRect;
        accessibilityNodeInfo.getBoundsInScreen(rect);
        if (!region.quickReject(rect) || shouldBypassAdjustIsVisible()) {
            return;
        }
        accessibilityNodeInfo.setVisibleToUser(false);
    }

    private boolean shouldBypassAdjustIsVisible() {
        return this.mViewRootImpl.mOrigWindowType == 2011;
    }

    private void adjustBoundsInScreenIfNeeded(List<AccessibilityNodeInfo> list) {
        if (list == null || shouldBypassAdjustBoundsInScreen()) {
            return;
        }
        int size = list.size();
        for (int i = 0; i < size; i++) {
            adjustBoundsInScreenIfNeeded(list.get(i));
        }
    }

    private void adjustBoundsInScreenIfNeeded(AccessibilityNodeInfo accessibilityNodeInfo) {
        if (accessibilityNodeInfo == null || shouldBypassAdjustBoundsInScreen()) {
            return;
        }
        Rect rect = this.mTempRect;
        accessibilityNodeInfo.getBoundsInScreen(rect);
        rect.offset(this.mViewRootImpl.mAttachInfo.mLocationInParentDisplay.x, this.mViewRootImpl.mAttachInfo.mLocationInParentDisplay.y);
        accessibilityNodeInfo.setBoundsInScreen(rect);
    }

    private boolean shouldBypassAdjustBoundsInScreen() {
        return this.mViewRootImpl.mAttachInfo.mLocationInParentDisplay.equals(0, 0);
    }

    private void applyScreenMatrixIfNeeded(List<AccessibilityNodeInfo> list) {
        if (list == null || shouldBypassApplyScreenMatrix()) {
            return;
        }
        int size = list.size();
        for (int i = 0; i < size; i++) {
            applyScreenMatrixIfNeeded(list.get(i));
        }
    }

    private void applyScreenMatrixIfNeeded(AccessibilityNodeInfo accessibilityNodeInfo) {
        if (accessibilityNodeInfo == null || shouldBypassApplyScreenMatrix()) {
            return;
        }
        Rect rect = this.mTempRect;
        RectF rectF = this.mTempRectF;
        Matrix matrix = this.mViewRootImpl.mAttachInfo.mScreenMatrixInEmbeddedHierarchy;
        accessibilityNodeInfo.getBoundsInScreen(rect);
        rectF.set(rect);
        matrix.mapRect(rectF);
        rect.set((int) rectF.left, (int) rectF.top, (int) rectF.right, (int) rectF.bottom);
        accessibilityNodeInfo.setBoundsInScreen(rect);
    }

    private boolean shouldBypassApplyScreenMatrix() {
        Matrix matrix = this.mViewRootImpl.mAttachInfo.mScreenMatrixInEmbeddedHierarchy;
        return matrix == null || matrix.isIdentity();
    }

    private void associateLeashedParentIfNeeded(List<AccessibilityNodeInfo> list) {
        if (list == null || shouldBypassAssociateLeashedParent()) {
            return;
        }
        int size = list.size();
        for (int i = 0; i < size; i++) {
            associateLeashedParentIfNeeded(list.get(i));
        }
    }

    private void associateLeashedParentIfNeeded(AccessibilityNodeInfo accessibilityNodeInfo) {
        if (accessibilityNodeInfo == null || shouldBypassAssociateLeashedParent() || this.mViewRootImpl.mView.getAccessibilityViewId() != AccessibilityNodeInfo.getAccessibilityViewId(accessibilityNodeInfo.getSourceNodeId())) {
            return;
        }
        accessibilityNodeInfo.setLeashedParent(this.mViewRootImpl.mAttachInfo.mLeashedParentToken, this.mViewRootImpl.mAttachInfo.mLeashedParentAccessibilityViewId);
    }

    private boolean shouldBypassAssociateLeashedParent() {
        return this.mViewRootImpl.mAttachInfo.mLeashedParentToken == null && this.mViewRootImpl.mAttachInfo.mLeashedParentAccessibilityViewId == -1;
    }

    private void applyAppScaleAndMagnificationSpecIfNeeded(AccessibilityNodeInfo accessibilityNodeInfo, MagnificationSpec magnificationSpec) {
        Parcelable[] parcelableArray;
        if (accessibilityNodeInfo == null) {
            return;
        }
        float f = this.mViewRootImpl.mAttachInfo.mApplicationScale;
        if (shouldApplyAppScaleAndMagnificationSpec(f, magnificationSpec)) {
            Rect rect = this.mTempRect;
            Rect rect2 = this.mTempRect1;
            accessibilityNodeInfo.getBoundsInParent(rect);
            accessibilityNodeInfo.getBoundsInScreen(rect2);
            if (f != 1.0f) {
                rect.scale(f);
                rect2.scale(f);
            }
            if (magnificationSpec != null) {
                rect.scale(magnificationSpec.scale);
                rect2.scale(magnificationSpec.scale);
                rect2.offset((int) magnificationSpec.offsetX, (int) magnificationSpec.offsetY);
            }
            accessibilityNodeInfo.setBoundsInParent(rect);
            accessibilityNodeInfo.setBoundsInScreen(rect2);
            if (!accessibilityNodeInfo.hasExtras() || (parcelableArray = accessibilityNodeInfo.getExtras().getParcelableArray(AccessibilityNodeInfo.EXTRA_DATA_TEXT_CHARACTER_LOCATION_KEY)) == null) {
                return;
            }
            for (Parcelable parcelable : parcelableArray) {
                RectF rectF = (RectF) parcelable;
                rectF.scale(f);
                if (magnificationSpec != null) {
                    rectF.scale(magnificationSpec.scale);
                    rectF.offset(magnificationSpec.offsetX, magnificationSpec.offsetY);
                }
            }
        }
    }

    private boolean shouldApplyAppScaleAndMagnificationSpec(float f, MagnificationSpec magnificationSpec) {
        return (f == 1.0f && (magnificationSpec == null || magnificationSpec.isNop())) ? false : true;
    }

    private void updateInfosForViewportAndReturnFindNodeResult(List<AccessibilityNodeInfo> list, IAccessibilityInteractionConnectionCallback iAccessibilityInteractionConnectionCallback, int i, MagnificationSpec magnificationSpec, Region region) {
        try {
            this.mViewRootImpl.mAttachInfo.mAccessibilityFetchFlags = 0;
            associateLeashedParentIfNeeded(list);
            applyScreenMatrixIfNeeded(list);
            adjustBoundsInScreenIfNeeded(list);
            adjustIsVisibleToUserIfNeeded(list, region);
            applyAppScaleAndMagnificationSpecIfNeeded(list, magnificationSpec);
            iAccessibilityInteractionConnectionCallback.setFindAccessibilityNodeInfosResult(list, i);
            if (list != null) {
                list.clear();
            }
        } catch (RemoteException unused) {
        } catch (Throwable th) {
            recycleMagnificationSpecAndRegionIfNeeded(magnificationSpec, region);
            throw th;
        }
        recycleMagnificationSpecAndRegionIfNeeded(magnificationSpec, region);
    }

    private void updateInfoForViewportAndReturnFindNodeResult(AccessibilityNodeInfo accessibilityNodeInfo, IAccessibilityInteractionConnectionCallback iAccessibilityInteractionConnectionCallback, int i, MagnificationSpec magnificationSpec, Region region) {
        try {
            this.mViewRootImpl.mAttachInfo.mAccessibilityFetchFlags = 0;
            associateLeashedParentIfNeeded(accessibilityNodeInfo);
            applyScreenMatrixIfNeeded(accessibilityNodeInfo);
            adjustBoundsInScreenIfNeeded(accessibilityNodeInfo);
            adjustIsVisibleToUserIfNeeded(accessibilityNodeInfo, region);
            applyAppScaleAndMagnificationSpecIfNeeded(accessibilityNodeInfo, magnificationSpec);
            iAccessibilityInteractionConnectionCallback.setFindAccessibilityNodeInfoResult(accessibilityNodeInfo, i);
        } catch (RemoteException unused) {
        } catch (Throwable th) {
            recycleMagnificationSpecAndRegionIfNeeded(magnificationSpec, region);
            throw th;
        }
        recycleMagnificationSpecAndRegionIfNeeded(magnificationSpec, region);
    }

    private void recycleMagnificationSpecAndRegionIfNeeded(MagnificationSpec magnificationSpec, Region region) {
        if (Process.myPid() != Binder.getCallingPid()) {
            if (magnificationSpec != null) {
                magnificationSpec.recycle();
            }
        } else if (region != null) {
            region.recycle();
        }
    }

    private boolean handleClickableSpanActionUiThread(View view, int i, Bundle bundle) {
        ClickableSpan clickableSpanFindClickableSpan;
        Parcelable parcelable = bundle.getParcelable(AccessibilityNodeInfo.ACTION_ARGUMENT_ACCESSIBLE_CLICKABLE_SPAN);
        if (!(parcelable instanceof AccessibilityClickableSpan)) {
            return false;
        }
        AccessibilityNodeInfo accessibilityNodeInfoCreateAccessibilityNodeInfo = null;
        AccessibilityNodeProvider accessibilityNodeProvider = view.getAccessibilityNodeProvider();
        if (accessibilityNodeProvider != null) {
            accessibilityNodeInfoCreateAccessibilityNodeInfo = accessibilityNodeProvider.createAccessibilityNodeInfo(i);
        } else if (i == -1) {
            accessibilityNodeInfoCreateAccessibilityNodeInfo = view.createAccessibilityNodeInfo();
        }
        if (accessibilityNodeInfoCreateAccessibilityNodeInfo == null || (clickableSpanFindClickableSpan = ((AccessibilityClickableSpan) parcelable).findClickableSpan(accessibilityNodeInfoCreateAccessibilityNodeInfo.getOriginalText())) == null) {
            return false;
        }
        clickableSpanFindClickableSpan.onClick(view);
        return true;
    }

    private class AccessibilityNodePrefetcher {
        private static final int MAX_ACCESSIBILITY_NODE_INFO_BATCH_SIZE = 50;
        private final ArrayList<View> mTempViewList;

        private AccessibilityNodePrefetcher() {
            this.mTempViewList = new ArrayList<>();
        }

        public void prefetchAccessibilityNodeInfos(View view, int i, int i2, List<AccessibilityNodeInfo> list, Bundle bundle) {
            AccessibilityNodeProvider accessibilityNodeProvider = view.getAccessibilityNodeProvider();
            String string = bundle == null ? null : bundle.getString(AccessibilityNodeInfo.EXTRA_DATA_REQUESTED_KEY);
            if (accessibilityNodeProvider == null) {
                AccessibilityNodeInfo accessibilityNodeInfoCreateAccessibilityNodeInfo = view.createAccessibilityNodeInfo();
                if (accessibilityNodeInfoCreateAccessibilityNodeInfo != null) {
                    if (string != null) {
                        view.addExtraDataToAccessibilityNodeInfo(accessibilityNodeInfoCreateAccessibilityNodeInfo, string, bundle);
                    }
                    list.add(accessibilityNodeInfoCreateAccessibilityNodeInfo);
                    if ((i2 & 1) != 0) {
                        prefetchPredecessorsOfRealNode(view, list);
                    }
                    if ((i2 & 2) != 0) {
                        prefetchSiblingsOfRealNode(view, list);
                    }
                    if ((i2 & 4) != 0) {
                        prefetchDescendantsOfRealNode(view, list);
                        return;
                    }
                    return;
                }
                return;
            }
            AccessibilityNodeInfo accessibilityNodeInfoCreateAccessibilityNodeInfo2 = accessibilityNodeProvider.createAccessibilityNodeInfo(i);
            if (accessibilityNodeInfoCreateAccessibilityNodeInfo2 != null) {
                if (string != null) {
                    accessibilityNodeProvider.addExtraDataToAccessibilityNodeInfo(i, accessibilityNodeInfoCreateAccessibilityNodeInfo2, string, bundle);
                }
                list.add(accessibilityNodeInfoCreateAccessibilityNodeInfo2);
                if ((i2 & 1) != 0) {
                    prefetchPredecessorsOfVirtualNode(accessibilityNodeInfoCreateAccessibilityNodeInfo2, view, accessibilityNodeProvider, list);
                }
                if ((i2 & 2) != 0) {
                    prefetchSiblingsOfVirtualNode(accessibilityNodeInfoCreateAccessibilityNodeInfo2, view, accessibilityNodeProvider, list);
                }
                if ((i2 & 4) != 0) {
                    prefetchDescendantsOfVirtualNode(accessibilityNodeInfoCreateAccessibilityNodeInfo2, accessibilityNodeProvider, list);
                }
            }
        }

        private void enforceNodeTreeConsistent(List<AccessibilityNodeInfo> list) {
            LongSparseArray longSparseArray = new LongSparseArray();
            int size = list.size();
            for (int i = 0; i < size; i++) {
                AccessibilityNodeInfo accessibilityNodeInfo = list.get(i);
                longSparseArray.put(accessibilityNodeInfo.getSourceNodeId(), accessibilityNodeInfo);
            }
            AccessibilityNodeInfo accessibilityNodeInfo2 = (AccessibilityNodeInfo) longSparseArray.valueAt(0);
            AccessibilityNodeInfo accessibilityNodeInfo3 = accessibilityNodeInfo2;
            while (accessibilityNodeInfo2 != null) {
                accessibilityNodeInfo3 = accessibilityNodeInfo2;
                accessibilityNodeInfo2 = (AccessibilityNodeInfo) longSparseArray.get(accessibilityNodeInfo2.getParentNodeId());
            }
            HashSet hashSet = new HashSet();
            LinkedList linkedList = new LinkedList();
            linkedList.add(accessibilityNodeInfo3);
            AccessibilityNodeInfo accessibilityNodeInfo4 = null;
            AccessibilityNodeInfo accessibilityNodeInfo5 = null;
            while (!linkedList.isEmpty()) {
                AccessibilityNodeInfo accessibilityNodeInfo6 = (AccessibilityNodeInfo) linkedList.poll();
                if (!hashSet.add(accessibilityNodeInfo6)) {
                    throw new IllegalStateException("Duplicate node: " + accessibilityNodeInfo6 + " in window:" + AccessibilityInteractionController.this.mViewRootImpl.mAttachInfo.mAccessibilityWindowId);
                }
                if (accessibilityNodeInfo6.isAccessibilityFocused()) {
                    if (accessibilityNodeInfo4 != null) {
                        throw new IllegalStateException("Duplicate accessibility focus:" + accessibilityNodeInfo6 + " in window:" + AccessibilityInteractionController.this.mViewRootImpl.mAttachInfo.mAccessibilityWindowId);
                    }
                    accessibilityNodeInfo4 = accessibilityNodeInfo6;
                }
                if (accessibilityNodeInfo6.isFocused()) {
                    if (accessibilityNodeInfo5 != null) {
                        throw new IllegalStateException("Duplicate input focus: " + accessibilityNodeInfo6 + " in window:" + AccessibilityInteractionController.this.mViewRootImpl.mAttachInfo.mAccessibilityWindowId);
                    }
                    accessibilityNodeInfo5 = accessibilityNodeInfo6;
                }
                int childCount = accessibilityNodeInfo6.getChildCount();
                for (int i2 = 0; i2 < childCount; i2++) {
                    AccessibilityNodeInfo accessibilityNodeInfo7 = (AccessibilityNodeInfo) longSparseArray.get(accessibilityNodeInfo6.getChildId(i2));
                    if (accessibilityNodeInfo7 != null) {
                        linkedList.add(accessibilityNodeInfo7);
                    }
                }
            }
            for (int size2 = longSparseArray.size() - 1; size2 >= 0; size2--) {
                AccessibilityNodeInfo accessibilityNodeInfo8 = (AccessibilityNodeInfo) longSparseArray.valueAt(size2);
                if (!hashSet.contains(accessibilityNodeInfo8)) {
                    throw new IllegalStateException("Disconnected node: " + accessibilityNodeInfo8);
                }
            }
        }

        private void prefetchPredecessorsOfRealNode(View view, List<AccessibilityNodeInfo> list) {
            for (ViewParent parentForAccessibility = view.getParentForAccessibility(); (parentForAccessibility instanceof View) && list.size() < 50; parentForAccessibility = parentForAccessibility.getParentForAccessibility()) {
                AccessibilityNodeInfo accessibilityNodeInfoCreateAccessibilityNodeInfo = ((View) parentForAccessibility).createAccessibilityNodeInfo();
                if (accessibilityNodeInfoCreateAccessibilityNodeInfo != null) {
                    list.add(accessibilityNodeInfoCreateAccessibilityNodeInfo);
                }
            }
        }

        private void prefetchSiblingsOfRealNode(View view, List<AccessibilityNodeInfo> list) {
            AccessibilityNodeInfo accessibilityNodeInfoCreateAccessibilityNodeInfo;
            ViewParent parentForAccessibility = view.getParentForAccessibility();
            if (parentForAccessibility instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) parentForAccessibility;
                ArrayList<View> arrayList = this.mTempViewList;
                arrayList.clear();
                try {
                    viewGroup.addChildrenForAccessibility(arrayList);
                    int size = arrayList.size();
                    for (int i = 0; i < size; i++) {
                        if (list.size() >= 50) {
                            return;
                        }
                        View view2 = arrayList.get(i);
                        if (view2.getAccessibilityViewId() != view.getAccessibilityViewId() && AccessibilityInteractionController.this.isShown(view2)) {
                            AccessibilityNodeProvider accessibilityNodeProvider = view2.getAccessibilityNodeProvider();
                            if (accessibilityNodeProvider == null) {
                                accessibilityNodeInfoCreateAccessibilityNodeInfo = view2.createAccessibilityNodeInfo();
                            } else {
                                accessibilityNodeInfoCreateAccessibilityNodeInfo = accessibilityNodeProvider.createAccessibilityNodeInfo(-1);
                            }
                            if (accessibilityNodeInfoCreateAccessibilityNodeInfo != null) {
                                list.add(accessibilityNodeInfoCreateAccessibilityNodeInfo);
                            }
                        }
                    }
                } finally {
                    arrayList.clear();
                }
            }
        }

        private void prefetchDescendantsOfRealNode(View view, List<AccessibilityNodeInfo> list) {
            if (view instanceof ViewGroup) {
                HashMap map = new HashMap();
                ArrayList<View> arrayList = this.mTempViewList;
                arrayList.clear();
                try {
                    view.addChildrenForAccessibility(arrayList);
                    int size = arrayList.size();
                    for (int i = 0; i < size; i++) {
                        if (list.size() >= 50) {
                            return;
                        }
                        View view2 = arrayList.get(i);
                        if (AccessibilityInteractionController.this.isShown(view2)) {
                            AccessibilityNodeProvider accessibilityNodeProvider = view2.getAccessibilityNodeProvider();
                            if (accessibilityNodeProvider == null) {
                                AccessibilityNodeInfo accessibilityNodeInfoCreateAccessibilityNodeInfo = view2.createAccessibilityNodeInfo();
                                if (accessibilityNodeInfoCreateAccessibilityNodeInfo != null) {
                                    list.add(accessibilityNodeInfoCreateAccessibilityNodeInfo);
                                    map.put(view2, null);
                                }
                            } else {
                                AccessibilityNodeInfo accessibilityNodeInfoCreateAccessibilityNodeInfo2 = accessibilityNodeProvider.createAccessibilityNodeInfo(-1);
                                if (accessibilityNodeInfoCreateAccessibilityNodeInfo2 != null) {
                                    list.add(accessibilityNodeInfoCreateAccessibilityNodeInfo2);
                                    map.put(view2, accessibilityNodeInfoCreateAccessibilityNodeInfo2);
                                }
                            }
                        }
                    }
                    arrayList.clear();
                    if (list.size() < 50) {
                        for (Map.Entry entry : map.entrySet()) {
                            View view3 = (View) entry.getKey();
                            AccessibilityNodeInfo accessibilityNodeInfo = (AccessibilityNodeInfo) entry.getValue();
                            if (accessibilityNodeInfo == null) {
                                prefetchDescendantsOfRealNode(view3, list);
                            } else {
                                prefetchDescendantsOfVirtualNode(accessibilityNodeInfo, view3.getAccessibilityNodeProvider(), list);
                            }
                        }
                    }
                } finally {
                    arrayList.clear();
                }
            }
        }

        private void prefetchPredecessorsOfVirtualNode(AccessibilityNodeInfo accessibilityNodeInfo, View view, AccessibilityNodeProvider accessibilityNodeProvider, List<AccessibilityNodeInfo> list) {
            int size = list.size();
            long parentNodeId = accessibilityNodeInfo.getParentNodeId();
            int accessibilityViewId = AccessibilityNodeInfo.getAccessibilityViewId(parentNodeId);
            while (accessibilityViewId != Integer.MAX_VALUE && list.size() < 50) {
                int virtualDescendantId = AccessibilityNodeInfo.getVirtualDescendantId(parentNodeId);
                if (virtualDescendantId != -1 || accessibilityViewId == view.getAccessibilityViewId()) {
                    AccessibilityNodeInfo accessibilityNodeInfoCreateAccessibilityNodeInfo = accessibilityNodeProvider.createAccessibilityNodeInfo(virtualDescendantId);
                    if (accessibilityNodeInfoCreateAccessibilityNodeInfo == null) {
                        for (int size2 = list.size() - 1; size2 >= size; size2--) {
                            list.remove(size2);
                        }
                        return;
                    }
                    list.add(accessibilityNodeInfoCreateAccessibilityNodeInfo);
                    parentNodeId = accessibilityNodeInfoCreateAccessibilityNodeInfo.getParentNodeId();
                    accessibilityViewId = AccessibilityNodeInfo.getAccessibilityViewId(parentNodeId);
                } else {
                    prefetchPredecessorsOfRealNode(view, list);
                    return;
                }
            }
        }

        private void prefetchSiblingsOfVirtualNode(AccessibilityNodeInfo accessibilityNodeInfo, View view, AccessibilityNodeProvider accessibilityNodeProvider, List<AccessibilityNodeInfo> list) {
            AccessibilityNodeInfo accessibilityNodeInfoCreateAccessibilityNodeInfo;
            long parentNodeId = accessibilityNodeInfo.getParentNodeId();
            int accessibilityViewId = AccessibilityNodeInfo.getAccessibilityViewId(parentNodeId);
            int virtualDescendantId = AccessibilityNodeInfo.getVirtualDescendantId(parentNodeId);
            if (virtualDescendantId != -1 || accessibilityViewId == view.getAccessibilityViewId()) {
                AccessibilityNodeInfo accessibilityNodeInfoCreateAccessibilityNodeInfo2 = accessibilityNodeProvider.createAccessibilityNodeInfo(virtualDescendantId);
                if (accessibilityNodeInfoCreateAccessibilityNodeInfo2 != null) {
                    int childCount = accessibilityNodeInfoCreateAccessibilityNodeInfo2.getChildCount();
                    for (int i = 0; i < childCount && list.size() < 50; i++) {
                        long childId = accessibilityNodeInfoCreateAccessibilityNodeInfo2.getChildId(i);
                        if (childId != accessibilityNodeInfo.getSourceNodeId() && (accessibilityNodeInfoCreateAccessibilityNodeInfo = accessibilityNodeProvider.createAccessibilityNodeInfo(AccessibilityNodeInfo.getVirtualDescendantId(childId))) != null) {
                            list.add(accessibilityNodeInfoCreateAccessibilityNodeInfo);
                        }
                    }
                    return;
                }
                return;
            }
            prefetchSiblingsOfRealNode(view, list);
        }

        private void prefetchDescendantsOfVirtualNode(AccessibilityNodeInfo accessibilityNodeInfo, AccessibilityNodeProvider accessibilityNodeProvider, List<AccessibilityNodeInfo> list) {
            int size = list.size();
            int childCount = accessibilityNodeInfo.getChildCount();
            for (int i = 0; i < childCount; i++) {
                if (list.size() >= 50) {
                    return;
                }
                AccessibilityNodeInfo accessibilityNodeInfoCreateAccessibilityNodeInfo = accessibilityNodeProvider.createAccessibilityNodeInfo(AccessibilityNodeInfo.getVirtualDescendantId(accessibilityNodeInfo.getChildId(i)));
                if (accessibilityNodeInfoCreateAccessibilityNodeInfo != null) {
                    list.add(accessibilityNodeInfoCreateAccessibilityNodeInfo);
                }
            }
            if (list.size() < 50) {
                int size2 = list.size() - size;
                for (int i2 = 0; i2 < size2; i2++) {
                    prefetchDescendantsOfVirtualNode(list.get(size + i2), accessibilityNodeProvider, list);
                }
            }
        }
    }

    private class PrivateHandler extends Handler {
        private static final int FIRST_NO_ACCESSIBILITY_CALLBACK_MSG = 100;
        private static final int MSG_APP_PREPARATION_FINISHED = 8;
        private static final int MSG_APP_PREPARATION_TIMEOUT = 9;
        private static final int MSG_CLEAR_ACCESSIBILITY_FOCUS = 101;
        private static final int MSG_FIND_ACCESSIBILITY_NODE_INFOS_BY_VIEW_ID = 3;
        private static final int MSG_FIND_ACCESSIBILITY_NODE_INFO_BY_ACCESSIBILITY_ID = 2;
        private static final int MSG_FIND_ACCESSIBILITY_NODE_INFO_BY_TEXT = 4;
        private static final int MSG_FIND_FOCUS = 5;
        private static final int MSG_FOCUS_SEARCH = 6;
        private static final int MSG_NOTIFY_OUTSIDE_TOUCH = 102;
        private static final int MSG_PERFORM_ACCESSIBILITY_ACTION = 1;
        private static final int MSG_PREPARE_FOR_EXTRA_DATA_REQUEST = 7;

        public PrivateHandler(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public String getMessageName(Message message) {
            int i = message.what;
            if (i == 101) {
                return "MSG_CLEAR_ACCESSIBILITY_FOCUS";
            }
            if (i == 102) {
                return "MSG_NOTIFY_OUTSIDE_TOUCH";
            }
            switch (i) {
                case 1:
                    return "MSG_PERFORM_ACCESSIBILITY_ACTION";
                case 2:
                    return "MSG_FIND_ACCESSIBILITY_NODE_INFO_BY_ACCESSIBILITY_ID";
                case 3:
                    return "MSG_FIND_ACCESSIBILITY_NODE_INFOS_BY_VIEW_ID";
                case 4:
                    return "MSG_FIND_ACCESSIBILITY_NODE_INFO_BY_TEXT";
                case 5:
                    return "MSG_FIND_FOCUS";
                case 6:
                    return "MSG_FOCUS_SEARCH";
                case 7:
                    return "MSG_PREPARE_FOR_EXTRA_DATA_REQUEST";
                case 8:
                    return "MSG_APP_PREPARATION_FINISHED";
                case 9:
                    return "MSG_APP_PREPARATION_TIMEOUT";
                default:
                    throw new IllegalArgumentException("Unknown message type: " + i);
            }
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) throws Throwable {
            int i = message.what;
            if (i == 101) {
                AccessibilityInteractionController.this.clearAccessibilityFocusUiThread();
                return;
            }
            if (i != 102) {
                switch (i) {
                    case 1:
                        AccessibilityInteractionController.this.performAccessibilityActionUiThread(message);
                        return;
                    case 2:
                        AccessibilityInteractionController.this.findAccessibilityNodeInfoByAccessibilityIdUiThread(message);
                        return;
                    case 3:
                        AccessibilityInteractionController.this.findAccessibilityNodeInfosByViewIdUiThread(message);
                        return;
                    case 4:
                        AccessibilityInteractionController.this.findAccessibilityNodeInfosByTextUiThread(message);
                        return;
                    case 5:
                        AccessibilityInteractionController.this.findFocusUiThread(message);
                        return;
                    case 6:
                        AccessibilityInteractionController.this.focusSearchUiThread(message);
                        return;
                    case 7:
                        AccessibilityInteractionController.this.prepareForExtraDataRequestUiThread(message);
                        return;
                    case 8:
                        AccessibilityInteractionController.this.requestPreparerDoneUiThread(message);
                        return;
                    case 9:
                        AccessibilityInteractionController.this.requestPreparerTimeoutUiThread();
                        return;
                    default:
                        throw new IllegalArgumentException("Unknown message type: " + i);
                }
            }
            AccessibilityInteractionController.this.notifyOutsideTouchUiThread();
        }

        boolean hasAccessibilityCallback(Message message) {
            return message.what < 100;
        }
    }

    private final class AddNodeInfosForViewId implements Predicate<View> {
        private List<AccessibilityNodeInfo> mInfos;
        private int mViewId;

        private AddNodeInfosForViewId() {
            this.mViewId = -1;
        }

        public void init(int i, List<AccessibilityNodeInfo> list) {
            this.mViewId = i;
            this.mInfos = list;
        }

        public void reset() {
            this.mViewId = -1;
            this.mInfos = null;
        }

        @Override // java.util.function.Predicate
        public boolean test(View view) {
            if (view.getId() != this.mViewId || !AccessibilityInteractionController.this.isShown(view)) {
                return false;
            }
            this.mInfos.add(view.createAccessibilityNodeInfo());
            return false;
        }
    }

    private static final class MessageHolder {
        final int mInterrogatingPid;
        final long mInterrogatingTid;
        final Message mMessage;

        MessageHolder(Message message, int i, long j) {
            this.mMessage = message;
            this.mInterrogatingPid = i;
            this.mInterrogatingTid = j;
        }
    }
}
