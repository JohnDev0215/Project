package android.view.accessibility;

import android.os.Build;
import android.util.ArraySet;
import android.util.Log;
import android.util.LongArray;
import android.util.LongSparseArray;
import android.util.SparseArray;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class AccessibilityCache {
    public static final int CACHE_CRITICAL_EVENTS_MASK = 4307005;
    private static final boolean CHECK_INTEGRITY;
    private static final boolean DEBUG = false;
    private static final String LOG_TAG = "AccessibilityCache";
    private static final boolean VERBOSE;
    private final AccessibilityNodeRefresher mAccessibilityNodeRefresher;
    private boolean mIsAllWindowsCached;
    private final Object mLock = new Object();
    private long mAccessibilityFocus = 2147483647L;
    private long mInputFocus = 2147483647L;
    private int mAccessibilityFocusedWindow = -1;
    private final SparseArray<SparseArray<AccessibilityWindowInfo>> mWindowCacheByDisplay = new SparseArray<>();
    private final SparseArray<LongSparseArray<AccessibilityNodeInfo>> mNodeCache = new SparseArray<>();
    private final SparseArray<AccessibilityWindowInfo> mTempWindowArray = new SparseArray<>();

    static {
        VERBOSE = Log.isLoggable(LOG_TAG, 2) && Build.IS_DEBUGGABLE;
        CHECK_INTEGRITY = Build.IS_ENG;
    }

    public AccessibilityCache(AccessibilityNodeRefresher accessibilityNodeRefresher) {
        this.mAccessibilityNodeRefresher = accessibilityNodeRefresher;
    }

    public void setWindowsOnAllDisplays(SparseArray<List<AccessibilityWindowInfo>> sparseArray) {
        synchronized (this.mLock) {
            clearWindowCacheLocked();
            if (sparseArray == null) {
                return;
            }
            int size = sparseArray.size();
            for (int i = 0; i < size; i++) {
                List<AccessibilityWindowInfo> listValueAt = sparseArray.valueAt(i);
                if (listValueAt != null) {
                    int iKeyAt = sparseArray.keyAt(i);
                    int size2 = listValueAt.size();
                    for (int i2 = 0; i2 < size2; i2++) {
                        addWindowByDisplayLocked(iKeyAt, listValueAt.get(i2));
                    }
                }
            }
            this.mIsAllWindowsCached = true;
        }
    }

    public void addWindow(AccessibilityWindowInfo accessibilityWindowInfo) {
        synchronized (this.mLock) {
            addWindowByDisplayLocked(accessibilityWindowInfo.getDisplayId(), accessibilityWindowInfo);
        }
    }

    private void addWindowByDisplayLocked(int i, AccessibilityWindowInfo accessibilityWindowInfo) {
        SparseArray<AccessibilityWindowInfo> sparseArray = this.mWindowCacheByDisplay.get(i);
        if (sparseArray == null) {
            sparseArray = new SparseArray<>();
            this.mWindowCacheByDisplay.put(i, sparseArray);
        }
        sparseArray.put(accessibilityWindowInfo.getId(), new AccessibilityWindowInfo(accessibilityWindowInfo));
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:31:0x008a A[Catch: all -> 0x00c1, TryCatch #0 {, blocks: (B:4:0x0003, B:5:0x000a, B:37:0x00b8, B:7:0x000f, B:9:0x0017, B:10:0x0020, B:12:0x002a, B:14:0x0032, B:15:0x0040, B:17:0x0046, B:18:0x004d, B:19:0x005f, B:20:0x006b, B:21:0x006d, B:30:0x0089, B:31:0x008a, B:32:0x008e, B:34:0x0094, B:35:0x009d, B:36:0x00ad, B:22:0x006e, B:24:0x007e, B:26:0x0085, B:25:0x0082), top: B:45:0x0003, inners: #1 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onAccessibilityEvent(android.view.accessibility.AccessibilityEvent r9) {
        /*
            Method dump skipped, instruction units count: 242
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.view.accessibility.AccessibilityCache.onAccessibilityEvent(android.view.accessibility.AccessibilityEvent):void");
    }

    private void refreshCachedNodeLocked(int i, long j) {
        AccessibilityNodeInfo accessibilityNodeInfo;
        LongSparseArray<AccessibilityNodeInfo> longSparseArray = this.mNodeCache.get(i);
        if (longSparseArray == null || (accessibilityNodeInfo = longSparseArray.get(j)) == null || this.mAccessibilityNodeRefresher.refreshNode(accessibilityNodeInfo, true)) {
            return;
        }
        clearSubTreeLocked(i, j);
    }

    private void refreshCachedWindowLocked(int i) {
        AccessibilityWindowInfo accessibilityWindowInfo;
        if (i == -1) {
            return;
        }
        int size = this.mWindowCacheByDisplay.size();
        for (int i2 = 0; i2 < size; i2++) {
            SparseArray<AccessibilityWindowInfo> sparseArrayValueAt = this.mWindowCacheByDisplay.valueAt(i2);
            if (sparseArrayValueAt != null && (accessibilityWindowInfo = sparseArrayValueAt.get(i)) != null) {
                if (this.mAccessibilityNodeRefresher.refreshWindow(accessibilityWindowInfo)) {
                    return;
                }
                clearWindowCacheLocked();
                return;
            }
        }
    }

    public AccessibilityNodeInfo getNode(int i, long j) {
        synchronized (this.mLock) {
            LongSparseArray<AccessibilityNodeInfo> longSparseArray = this.mNodeCache.get(i);
            if (longSparseArray == null) {
                return null;
            }
            AccessibilityNodeInfo accessibilityNodeInfo = longSparseArray.get(j);
            if (accessibilityNodeInfo != null) {
                accessibilityNodeInfo = new AccessibilityNodeInfo(accessibilityNodeInfo);
            }
            if (VERBOSE) {
                Log.i(LOG_TAG, "get(0x" + Long.toHexString(j) + ") = " + accessibilityNodeInfo);
            }
            return accessibilityNodeInfo;
        }
    }

    public SparseArray<List<AccessibilityWindowInfo>> getWindowsOnAllDisplays() {
        int size;
        synchronized (this.mLock) {
            if (!this.mIsAllWindowsCached) {
                return null;
            }
            SparseArray<List<AccessibilityWindowInfo>> sparseArray = new SparseArray<>();
            int size2 = this.mWindowCacheByDisplay.size();
            if (size2 <= 0) {
                return null;
            }
            for (int i = 0; i < size2; i++) {
                int iKeyAt = this.mWindowCacheByDisplay.keyAt(i);
                SparseArray<AccessibilityWindowInfo> sparseArrayValueAt = this.mWindowCacheByDisplay.valueAt(i);
                if (sparseArrayValueAt != null && (size = sparseArrayValueAt.size()) > 0) {
                    SparseArray<AccessibilityWindowInfo> sparseArray2 = this.mTempWindowArray;
                    sparseArray2.clear();
                    for (int i2 = 0; i2 < size; i2++) {
                        AccessibilityWindowInfo accessibilityWindowInfoValueAt = sparseArrayValueAt.valueAt(i2);
                        sparseArray2.put(accessibilityWindowInfoValueAt.getLayer(), accessibilityWindowInfoValueAt);
                    }
                    int size3 = sparseArray2.size();
                    ArrayList arrayList = new ArrayList(size3);
                    for (int i3 = size3 - 1; i3 >= 0; i3--) {
                        arrayList.add(new AccessibilityWindowInfo(sparseArray2.valueAt(i3)));
                        sparseArray2.removeAt(i3);
                    }
                    sparseArray.put(iKeyAt, arrayList);
                }
            }
            return sparseArray;
        }
    }

    public AccessibilityWindowInfo getWindow(int i) {
        AccessibilityWindowInfo accessibilityWindowInfo;
        synchronized (this.mLock) {
            int size = this.mWindowCacheByDisplay.size();
            for (int i2 = 0; i2 < size; i2++) {
                SparseArray<AccessibilityWindowInfo> sparseArrayValueAt = this.mWindowCacheByDisplay.valueAt(i2);
                if (sparseArrayValueAt != null && (accessibilityWindowInfo = sparseArrayValueAt.get(i)) != null) {
                    return new AccessibilityWindowInfo(accessibilityWindowInfo);
                }
            }
            return null;
        }
    }

    public void add(AccessibilityNodeInfo accessibilityNodeInfo) {
        synchronized (this.mLock) {
            if (VERBOSE) {
                Log.i(LOG_TAG, "add(" + accessibilityNodeInfo + ")");
            }
            int windowId = accessibilityNodeInfo.getWindowId();
            LongSparseArray<AccessibilityNodeInfo> longSparseArray = this.mNodeCache.get(windowId);
            if (longSparseArray == null) {
                longSparseArray = new LongSparseArray<>();
                this.mNodeCache.put(windowId, longSparseArray);
            }
            long sourceNodeId = accessibilityNodeInfo.getSourceNodeId();
            AccessibilityNodeInfo accessibilityNodeInfo2 = longSparseArray.get(sourceNodeId);
            if (accessibilityNodeInfo2 != null) {
                LongArray childNodeIds = accessibilityNodeInfo.getChildNodeIds();
                int childCount = accessibilityNodeInfo2.getChildCount();
                for (int i = 0; i < childCount; i++) {
                    long childId = accessibilityNodeInfo2.getChildId(i);
                    if (childNodeIds == null || childNodeIds.indexOf(childId) < 0) {
                        clearSubTreeLocked(windowId, childId);
                    }
                    if (longSparseArray.get(sourceNodeId) == null) {
                        clearNodesForWindowLocked(windowId);
                        return;
                    }
                }
                long parentNodeId = accessibilityNodeInfo2.getParentNodeId();
                if (accessibilityNodeInfo.getParentNodeId() != parentNodeId) {
                    clearSubTreeLocked(windowId, parentNodeId);
                }
            }
            AccessibilityNodeInfo accessibilityNodeInfo3 = new AccessibilityNodeInfo(accessibilityNodeInfo);
            longSparseArray.put(sourceNodeId, accessibilityNodeInfo3);
            if (accessibilityNodeInfo3.isAccessibilityFocused()) {
                if (this.mAccessibilityFocus != 2147483647L && this.mAccessibilityFocus != sourceNodeId) {
                    refreshCachedNodeLocked(windowId, this.mAccessibilityFocus);
                }
                this.mAccessibilityFocus = sourceNodeId;
                this.mAccessibilityFocusedWindow = windowId;
            } else if (this.mAccessibilityFocus == sourceNodeId) {
                this.mAccessibilityFocus = 2147483647L;
                this.mAccessibilityFocusedWindow = -1;
            }
            if (accessibilityNodeInfo3.isFocused()) {
                this.mInputFocus = sourceNodeId;
            }
        }
    }

    public void clear() {
        synchronized (this.mLock) {
            clearWindowCacheLocked();
            for (int size = this.mNodeCache.size() - 1; size >= 0; size--) {
                clearNodesForWindowLocked(this.mNodeCache.keyAt(size));
            }
            this.mAccessibilityFocus = 2147483647L;
            this.mInputFocus = 2147483647L;
            this.mAccessibilityFocusedWindow = -1;
        }
    }

    private void clearWindowCacheLocked() {
        int size = this.mWindowCacheByDisplay.size();
        if (size > 0) {
            for (int i = size - 1; i >= 0; i--) {
                int iKeyAt = this.mWindowCacheByDisplay.keyAt(i);
                SparseArray<AccessibilityWindowInfo> sparseArray = this.mWindowCacheByDisplay.get(iKeyAt);
                if (sparseArray != null) {
                    sparseArray.clear();
                }
                this.mWindowCacheByDisplay.remove(iKeyAt);
            }
        }
        this.mIsAllWindowsCached = false;
    }

    private void clearNodesForWindowLocked(int i) {
        if (this.mNodeCache.get(i) == null) {
            return;
        }
        this.mNodeCache.remove(i);
    }

    private void clearSubTreeLocked(int i, long j) {
        LongSparseArray<AccessibilityNodeInfo> longSparseArray = this.mNodeCache.get(i);
        if (longSparseArray != null) {
            clearSubTreeRecursiveLocked(longSparseArray, j);
        }
    }

    private boolean clearSubTreeRecursiveLocked(LongSparseArray<AccessibilityNodeInfo> longSparseArray, long j) {
        AccessibilityNodeInfo accessibilityNodeInfo = longSparseArray.get(j);
        if (accessibilityNodeInfo == null) {
            clear();
            return true;
        }
        longSparseArray.remove(j);
        int childCount = accessibilityNodeInfo.getChildCount();
        for (int i = 0; i < childCount; i++) {
            if (clearSubTreeRecursiveLocked(longSparseArray, accessibilityNodeInfo.getChildId(i))) {
                return true;
            }
        }
        return false;
    }

    public void checkIntegrity() {
        AccessibilityNodeInfo accessibilityNodeInfo;
        int i;
        int i2;
        boolean z;
        synchronized (this.mLock) {
            if (this.mWindowCacheByDisplay.size() > 0 || this.mNodeCache.size() != 0) {
                int size = this.mWindowCacheByDisplay.size();
                AccessibilityNodeInfo accessibilityNodeInfo2 = null;
                AccessibilityWindowInfo accessibilityWindowInfo = null;
                AccessibilityWindowInfo accessibilityWindowInfo2 = null;
                for (int i3 = 0; i3 < size; i3++) {
                    SparseArray<AccessibilityWindowInfo> sparseArrayValueAt = this.mWindowCacheByDisplay.valueAt(i3);
                    if (sparseArrayValueAt != null) {
                        int size2 = sparseArrayValueAt.size();
                        for (int i4 = 0; i4 < size2; i4++) {
                            AccessibilityWindowInfo accessibilityWindowInfoValueAt = sparseArrayValueAt.valueAt(i4);
                            if (accessibilityWindowInfoValueAt.isActive()) {
                                if (accessibilityWindowInfo != null) {
                                    Log.e(LOG_TAG, "Duplicate active window:" + accessibilityWindowInfoValueAt);
                                } else {
                                    accessibilityWindowInfo = accessibilityWindowInfoValueAt;
                                }
                            }
                            if (accessibilityWindowInfoValueAt.isFocused()) {
                                if (accessibilityWindowInfo2 != null) {
                                    Log.e(LOG_TAG, "Duplicate focused window:" + accessibilityWindowInfoValueAt);
                                } else {
                                    accessibilityWindowInfo2 = accessibilityWindowInfoValueAt;
                                }
                            }
                        }
                    }
                }
                int size3 = this.mNodeCache.size();
                AccessibilityNodeInfo accessibilityNodeInfo3 = null;
                int i5 = 0;
                while (i5 < size3) {
                    LongSparseArray<AccessibilityNodeInfo> longSparseArrayValueAt = this.mNodeCache.valueAt(i5);
                    if (longSparseArrayValueAt.size() > 0) {
                        ArraySet arraySet = new ArraySet();
                        int iKeyAt = this.mNodeCache.keyAt(i5);
                        int size4 = longSparseArrayValueAt.size();
                        int i6 = 0;
                        while (i6 < size4) {
                            AccessibilityNodeInfo accessibilityNodeInfoValueAt = longSparseArrayValueAt.valueAt(i6);
                            if (arraySet.add(accessibilityNodeInfoValueAt)) {
                                if (accessibilityNodeInfoValueAt.isAccessibilityFocused()) {
                                    if (accessibilityNodeInfo2 != null) {
                                        Log.e(LOG_TAG, "Duplicate accessibility focus:" + accessibilityNodeInfoValueAt + " in window:" + iKeyAt);
                                    } else {
                                        accessibilityNodeInfo2 = accessibilityNodeInfoValueAt;
                                    }
                                }
                                if (accessibilityNodeInfoValueAt.isFocused()) {
                                    if (accessibilityNodeInfo3 != null) {
                                        Log.e(LOG_TAG, "Duplicate input focus: " + accessibilityNodeInfoValueAt + " in window:" + iKeyAt);
                                    } else {
                                        accessibilityNodeInfo3 = accessibilityNodeInfoValueAt;
                                    }
                                }
                                AccessibilityNodeInfo accessibilityNodeInfo4 = longSparseArrayValueAt.get(accessibilityNodeInfoValueAt.getParentNodeId());
                                if (accessibilityNodeInfo4 != null) {
                                    int childCount = accessibilityNodeInfo4.getChildCount();
                                    int i7 = 0;
                                    while (true) {
                                        if (i7 >= childCount) {
                                            accessibilityNodeInfo = accessibilityNodeInfo3;
                                            z = false;
                                            break;
                                        }
                                        accessibilityNodeInfo = accessibilityNodeInfo3;
                                        if (longSparseArrayValueAt.get(accessibilityNodeInfo4.getChildId(i7)) == accessibilityNodeInfoValueAt) {
                                            z = true;
                                            break;
                                        } else {
                                            i7++;
                                            accessibilityNodeInfo3 = accessibilityNodeInfo;
                                        }
                                    }
                                    if (!z) {
                                        Log.e(LOG_TAG, "Invalid parent-child relation between parent: " + accessibilityNodeInfo4 + " and child: " + accessibilityNodeInfoValueAt);
                                    }
                                } else {
                                    accessibilityNodeInfo = accessibilityNodeInfo3;
                                }
                                int childCount2 = accessibilityNodeInfoValueAt.getChildCount();
                                int i8 = 0;
                                while (i8 < childCount2) {
                                    AccessibilityNodeInfo accessibilityNodeInfo5 = longSparseArrayValueAt.get(accessibilityNodeInfoValueAt.getChildId(i8));
                                    if (accessibilityNodeInfo5 == null || longSparseArrayValueAt.get(accessibilityNodeInfo5.getParentNodeId()) == accessibilityNodeInfoValueAt) {
                                        i2 = size3;
                                    } else {
                                        StringBuilder sb = new StringBuilder();
                                        i2 = size3;
                                        sb.append("Invalid child-parent relation between child: ");
                                        sb.append(accessibilityNodeInfoValueAt);
                                        sb.append(" and parent: ");
                                        sb.append(accessibilityNodeInfo4);
                                        Log.e(LOG_TAG, sb.toString());
                                    }
                                    i8++;
                                    size3 = i2;
                                }
                                i = size3;
                                accessibilityNodeInfo3 = accessibilityNodeInfo;
                            } else {
                                Log.e(LOG_TAG, "Duplicate node: " + accessibilityNodeInfoValueAt + " in window:" + iKeyAt);
                                i = size3;
                            }
                            i6++;
                            size3 = i;
                        }
                    }
                    i5++;
                    size3 = size3;
                }
            }
        }
    }

    public static class AccessibilityNodeRefresher {
        public boolean refreshNode(AccessibilityNodeInfo accessibilityNodeInfo, boolean z) {
            return accessibilityNodeInfo.refresh(null, z);
        }

        public boolean refreshWindow(AccessibilityWindowInfo accessibilityWindowInfo) {
            return accessibilityWindowInfo.refresh();
        }
    }
}
