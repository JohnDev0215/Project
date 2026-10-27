package org.chromium.content.browser;

import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Handler;
import android.util.LruCache;
import android.util.SparseArray;
import org.chromium.base.Log;
import org.chromium.base.SysUtils;
import org.chromium.base.ThreadUtils;
import org.chromium.base.metrics.RecordHistogram;

/* JADX INFO: loaded from: classes3.dex */
class BindingManagerImpl implements BindingManager {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final long DETACH_AS_ACTIVE_HIGH_END_DELAY_MILLIS = 1000;
    private static final float MODERATE_BINDING_HIGH_REDUCE_RATIO = 0.5f;
    private static final float MODERATE_BINDING_LOW_REDUCE_RATIO = 0.25f;
    private static final long MODERATE_BINDING_POOL_CLEARER_DELAY_MILLIS = 10000;
    private static final String TAG = "cr.BindingManager";
    private ManagedConnection mBoundForBackgroundPeriod;
    private final boolean mIsLowMemoryDevice;
    private ManagedConnection mLastInForeground;
    private ModerateBindingPool mModerateBindingPool;
    private boolean mModerateBindingTillBackgrounded;
    private final boolean mOnTesting;
    private final Object mModerateBindingPoolLock = new Object();
    private final SparseArray<ManagedConnection> mManagedConnections = new SparseArray<>();
    private final Object mLastInForegroundLock = new Object();

    private static class ModerateBindingPool extends LruCache<Integer, ManagedConnection> implements ComponentCallbacks2 {
        private Runnable mDelayedClearer;
        private final Object mDelayedClearerLock;
        private final Handler mHandler;

        @Override // android.content.ComponentCallbacks
        public void onConfigurationChanged(Configuration configuration) {
        }

        public ModerateBindingPool(int i) {
            super(i);
            this.mDelayedClearerLock = new Object();
            this.mHandler = new Handler(ThreadUtils.getUiThreadLooper());
        }

        @Override // android.content.ComponentCallbacks2
        public void onTrimMemory(int i) {
            Log.i(BindingManagerImpl.TAG, "onTrimMemory: level=%d, size=%d", Integer.valueOf(i), Integer.valueOf(size()));
            if (size() > 0) {
                if (i <= 5) {
                    reduce(BindingManagerImpl.MODERATE_BINDING_LOW_REDUCE_RATIO);
                } else if (i <= 10) {
                    reduce(BindingManagerImpl.MODERATE_BINDING_HIGH_REDUCE_RATIO);
                } else {
                    if (i == 20) {
                        return;
                    }
                    evictAll();
                }
            }
        }

        @Override // android.content.ComponentCallbacks
        public void onLowMemory() {
            Log.i(BindingManagerImpl.TAG, "onLowMemory: evict %d bindings", Integer.valueOf(size()));
            evictAll();
        }

        private void reduce(float f) {
            int size = size();
            int i = (int) (size * (1.0f - f));
            Log.i(BindingManagerImpl.TAG, "Reduce connections from %d to %d", Integer.valueOf(size), Integer.valueOf(i));
            if (i == 0) {
                evictAll();
            } else {
                trimToSize(i);
            }
        }

        void addConnection(ManagedConnection managedConnection) {
            ChildProcessConnection childProcessConnection = managedConnection.mConnection;
            if (childProcessConnection == null || !childProcessConnection.isInSandbox()) {
                return;
            }
            managedConnection.addModerateBinding();
            if (childProcessConnection.isModerateBindingBound()) {
                put(Integer.valueOf(childProcessConnection.getServiceNumber()), managedConnection);
            } else {
                remove(Integer.valueOf(childProcessConnection.getServiceNumber()));
            }
        }

        void removeConnection(ManagedConnection managedConnection) {
            ChildProcessConnection childProcessConnection = managedConnection.mConnection;
            if (childProcessConnection == null || !childProcessConnection.isInSandbox()) {
                return;
            }
            remove(Integer.valueOf(childProcessConnection.getServiceNumber()));
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.util.LruCache
        public void entryRemoved(boolean z, Integer num, ManagedConnection managedConnection, ManagedConnection managedConnection2) {
            if (managedConnection != managedConnection2) {
                managedConnection.removeModerateBinding();
            }
        }

        void onSentToBackground(final boolean z) {
            if (size() == 0) {
                return;
            }
            synchronized (this.mDelayedClearerLock) {
                Runnable runnable = new Runnable() { // from class: org.chromium.content.browser.BindingManagerImpl.ModerateBindingPool.1
                    @Override // java.lang.Runnable
                    public void run() {
                        synchronized (ModerateBindingPool.this.mDelayedClearerLock) {
                            if (ModerateBindingPool.this.mDelayedClearer == null) {
                                return;
                            }
                            ModerateBindingPool.this.mDelayedClearer = null;
                            Log.i(BindingManagerImpl.TAG, "Release moderate connections: %d", Integer.valueOf(ModerateBindingPool.this.size()));
                            if (!z) {
                                RecordHistogram.recordCountHistogram("Android.ModerateBindingCount", ModerateBindingPool.this.size());
                            }
                            ModerateBindingPool.this.evictAll();
                        }
                    }
                };
                this.mDelayedClearer = runnable;
                this.mHandler.postDelayed(runnable, 10000L);
            }
        }

        void onBroughtToForeground() {
            synchronized (this.mDelayedClearerLock) {
                Runnable runnable = this.mDelayedClearer;
                if (runnable == null) {
                    return;
                }
                this.mHandler.removeCallbacks(runnable);
                this.mDelayedClearer = null;
            }
        }
    }

    private class ManagedConnection {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private boolean mBoundForBackgroundPeriod;
        private ChildProcessConnection mConnection;
        private boolean mInForeground;
        private boolean mWasOomProtected;

        private boolean removeInitialBinding() {
            ChildProcessConnection childProcessConnection = this.mConnection;
            if (childProcessConnection == null || !childProcessConnection.isInitialBindingBound()) {
                return false;
            }
            this.mConnection.removeInitialBinding();
            return true;
        }

        private void addStrongBinding() {
            ModerateBindingPool moderateBindingPool;
            ChildProcessConnection childProcessConnection = this.mConnection;
            if (childProcessConnection == null) {
                return;
            }
            childProcessConnection.addStrongBinding();
            synchronized (BindingManagerImpl.this.mModerateBindingPoolLock) {
                moderateBindingPool = BindingManagerImpl.this.mModerateBindingPool;
            }
            if (moderateBindingPool != null) {
                moderateBindingPool.removeConnection(this);
            }
        }

        private void removeStrongBinding(final boolean z) {
            final ChildProcessConnection childProcessConnection = this.mConnection;
            if (childProcessConnection == null || !childProcessConnection.isStrongBindingBound()) {
                return;
            }
            Runnable runnable = new Runnable() { // from class: org.chromium.content.browser.BindingManagerImpl.ManagedConnection.1
                @Override // java.lang.Runnable
                public void run() {
                    if (childProcessConnection.isStrongBindingBound()) {
                        childProcessConnection.removeStrongBinding();
                        if (z) {
                            ManagedConnection.this.addConnectionToModerateBindingPool(childProcessConnection);
                        }
                    }
                }
            };
            if (BindingManagerImpl.this.mIsLowMemoryDevice) {
                runnable.run();
            } else {
                ThreadUtils.postOnUiThreadDelayed(runnable, 1000L);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addConnectionToModerateBindingPool(ChildProcessConnection childProcessConnection) {
            ModerateBindingPool moderateBindingPool;
            synchronized (BindingManagerImpl.this.mModerateBindingPoolLock) {
                moderateBindingPool = BindingManagerImpl.this.mModerateBindingPool;
            }
            if (moderateBindingPool == null || childProcessConnection.isStrongBindingBound()) {
                return;
            }
            moderateBindingPool.addConnection(this);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void removeModerateBinding() {
            ChildProcessConnection childProcessConnection = this.mConnection;
            if (childProcessConnection == null || !childProcessConnection.isModerateBindingBound()) {
                return;
            }
            this.mConnection.removeModerateBinding();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addModerateBinding() {
            ChildProcessConnection childProcessConnection = this.mConnection;
            if (childProcessConnection == null) {
                return;
            }
            childProcessConnection.addModerateBinding();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void dropBindings() {
            ChildProcessConnection childProcessConnection = this.mConnection;
            if (childProcessConnection == null) {
                return;
            }
            childProcessConnection.dropOomBindings();
        }

        ManagedConnection(ChildProcessConnection childProcessConnection) {
            this.mConnection = childProcessConnection;
        }

        void setInForeground(boolean z) {
            boolean z2 = this.mInForeground;
            if (!z2 && z) {
                addStrongBinding();
            } else if (z2 && !z) {
                removeStrongBinding(true);
            }
            this.mInForeground = z;
        }

        void determinedVisibility() {
            if (removeInitialBinding() && BindingManagerImpl.this.mModerateBindingTillBackgrounded) {
                addConnectionToModerateBindingPool(this.mConnection);
            }
        }

        void setBoundForBackgroundPeriod(boolean z) {
            boolean z2 = this.mBoundForBackgroundPeriod;
            if (!z2 && z) {
                addStrongBinding();
            } else if (z2 && !z) {
                removeStrongBinding(false);
            }
            this.mBoundForBackgroundPeriod = z;
        }

        boolean isOomProtected() {
            ChildProcessConnection childProcessConnection = this.mConnection;
            return childProcessConnection != null ? childProcessConnection.isOomProtectedOrWasWhenDied() : this.mWasOomProtected;
        }

        void clearConnection() {
            ModerateBindingPool moderateBindingPool;
            this.mWasOomProtected = this.mConnection.isOomProtectedOrWasWhenDied();
            synchronized (BindingManagerImpl.this.mModerateBindingPoolLock) {
                moderateBindingPool = BindingManagerImpl.this.mModerateBindingPool;
            }
            if (moderateBindingPool != null) {
                moderateBindingPool.removeConnection(this);
            }
            this.mConnection = null;
        }

        boolean isConnectionCleared() {
            return this.mConnection == null;
        }
    }

    private BindingManagerImpl(boolean z, boolean z2) {
        this.mIsLowMemoryDevice = z;
        this.mOnTesting = z2;
    }

    public static BindingManagerImpl createBindingManager() {
        return new BindingManagerImpl(SysUtils.isLowEndDevice(), false);
    }

    public static BindingManagerImpl createBindingManagerForTesting(boolean z) {
        return new BindingManagerImpl(z, true);
    }

    @Override // org.chromium.content.browser.BindingManager
    public void addNewConnection(int i, ChildProcessConnection childProcessConnection) {
        synchronized (this.mManagedConnections) {
            this.mManagedConnections.put(i, new ManagedConnection(childProcessConnection));
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0037 A[Catch: all -> 0x003b, TryCatch #0 {, blocks: (B:12:0x0025, B:14:0x0029, B:17:0x002f, B:18:0x0032, B:20:0x0037, B:21:0x0039), top: B:29:0x0025 }] */
    @Override // org.chromium.content.browser.BindingManager
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void setInForeground(int r4, boolean r5) {
        /*
            r3 = this;
            android.util.SparseArray<org.chromium.content.browser.BindingManagerImpl$ManagedConnection> r0 = r3.mManagedConnections
            monitor-enter(r0)
            android.util.SparseArray<org.chromium.content.browser.BindingManagerImpl$ManagedConnection> r1 = r3.mManagedConnections     // Catch: java.lang.Throwable -> L3e
            java.lang.Object r1 = r1.get(r4)     // Catch: java.lang.Throwable -> L3e
            org.chromium.content.browser.BindingManagerImpl$ManagedConnection r1 = (org.chromium.content.browser.BindingManagerImpl.ManagedConnection) r1     // Catch: java.lang.Throwable -> L3e
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L3e
            if (r1 != 0) goto L20
            java.lang.String r5 = "cr.BindingManager"
            java.lang.String r0 = "Cannot setInForeground() - never saw a connection for the pid: %d"
            r1 = 1
            java.lang.Object[] r1 = new java.lang.Object[r1]
            r2 = 0
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
            r1[r2] = r4
            org.chromium.base.Log.w(r5, r0, r1)
            return
        L20:
            java.lang.Object r4 = r3.mLastInForegroundLock
            monitor-enter(r4)
            if (r5 == 0) goto L32
            boolean r0 = r3.mIsLowMemoryDevice     // Catch: java.lang.Throwable -> L3b
            if (r0 == 0) goto L32
            org.chromium.content.browser.BindingManagerImpl$ManagedConnection r0 = r3.mLastInForeground     // Catch: java.lang.Throwable -> L3b
            if (r0 == 0) goto L32
            if (r0 == r1) goto L32
            org.chromium.content.browser.BindingManagerImpl.ManagedConnection.access$1000(r0)     // Catch: java.lang.Throwable -> L3b
        L32:
            r1.setInForeground(r5)     // Catch: java.lang.Throwable -> L3b
            if (r5 == 0) goto L39
            r3.mLastInForeground = r1     // Catch: java.lang.Throwable -> L3b
        L39:
            monitor-exit(r4)     // Catch: java.lang.Throwable -> L3b
            return
        L3b:
            r5 = move-exception
            monitor-exit(r4)     // Catch: java.lang.Throwable -> L3b
            throw r5
        L3e:
            r4 = move-exception
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L3e
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.content.browser.BindingManagerImpl.setInForeground(int, boolean):void");
    }

    @Override // org.chromium.content.browser.BindingManager
    public void determinedVisibility(int i) {
        ManagedConnection managedConnection;
        synchronized (this.mManagedConnections) {
            managedConnection = this.mManagedConnections.get(i);
        }
        if (managedConnection == null) {
            Log.w(TAG, "Cannot call determinedVisibility() - never saw a connection for the pid: %d", Integer.valueOf(i));
        } else {
            managedConnection.determinedVisibility();
        }
    }

    @Override // org.chromium.content.browser.BindingManager
    public void onSentToBackground() {
        ModerateBindingPool moderateBindingPool;
        synchronized (this.mLastInForegroundLock) {
            ManagedConnection managedConnection = this.mLastInForeground;
            if (managedConnection != null) {
                managedConnection.setBoundForBackgroundPeriod(true);
                this.mBoundForBackgroundPeriod = this.mLastInForeground;
            }
        }
        synchronized (this.mModerateBindingPoolLock) {
            moderateBindingPool = this.mModerateBindingPool;
        }
        if (moderateBindingPool != null) {
            moderateBindingPool.onSentToBackground(this.mOnTesting);
        }
    }

    @Override // org.chromium.content.browser.BindingManager
    public void onBroughtToForeground() {
        ModerateBindingPool moderateBindingPool;
        ManagedConnection managedConnection = this.mBoundForBackgroundPeriod;
        if (managedConnection != null) {
            managedConnection.setBoundForBackgroundPeriod(false);
            this.mBoundForBackgroundPeriod = null;
        }
        synchronized (this.mModerateBindingPoolLock) {
            moderateBindingPool = this.mModerateBindingPool;
        }
        if (moderateBindingPool != null) {
            moderateBindingPool.onBroughtToForeground();
        }
    }

    @Override // org.chromium.content.browser.BindingManager
    public boolean isOomProtected(int i) {
        ManagedConnection managedConnection;
        synchronized (this.mManagedConnections) {
            managedConnection = this.mManagedConnections.get(i);
        }
        if (managedConnection != null) {
            return managedConnection.isOomProtected();
        }
        return false;
    }

    @Override // org.chromium.content.browser.BindingManager
    public void clearConnection(int i) {
        ManagedConnection managedConnection;
        synchronized (this.mManagedConnections) {
            managedConnection = this.mManagedConnections.get(i);
        }
        if (managedConnection != null) {
            managedConnection.clearConnection();
        }
    }

    public boolean isConnectionCleared(int i) {
        boolean zIsConnectionCleared;
        synchronized (this.mManagedConnections) {
            zIsConnectionCleared = this.mManagedConnections.get(i).isConnectionCleared();
        }
        return zIsConnectionCleared;
    }

    @Override // org.chromium.content.browser.BindingManager
    public void startModerateBindingManagement(Context context, int i, boolean z) {
        synchronized (this.mModerateBindingPoolLock) {
            if (!this.mIsLowMemoryDevice && this.mModerateBindingPool == null) {
                this.mModerateBindingTillBackgrounded = z;
                Log.i(TAG, "Moderate binding enabled: maxSize=%d", Integer.valueOf(i));
                ModerateBindingPool moderateBindingPool = new ModerateBindingPool(i);
                this.mModerateBindingPool = moderateBindingPool;
                if (context != null) {
                    context.registerComponentCallbacks(moderateBindingPool);
                }
            }
        }
    }

    @Override // org.chromium.content.browser.BindingManager
    public void releaseAllModerateBindings() {
        ModerateBindingPool moderateBindingPool;
        synchronized (this.mModerateBindingPoolLock) {
            moderateBindingPool = this.mModerateBindingPool;
        }
        if (moderateBindingPool != null) {
            Log.i(TAG, "Release all moderate bindings: %d", Integer.valueOf(moderateBindingPool.size()));
            moderateBindingPool.evictAll();
        }
    }
}
