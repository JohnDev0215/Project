package com.android.providers.media.fuse;

import android.os.ParcelFileDescriptor;
import android.util.Log;
import com.android.internal.annotations.GuardedBy;
import com.android.providers.media.MediaProvider;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class FuseDaemon extends Thread {
    private static final int POLL_COUNT = 5;
    private static final int POLL_INTERVAL_MS = 1000;
    public static final String TAG = "FuseDaemonThread";
    private final int mFuseDeviceFd;
    private final Object mLock = new Object();
    private final MediaProvider mMediaProvider;
    private final String mPath;

    @GuardedBy({"mLock"})
    private long mPtr;
    private final ExternalStorageServiceImpl mService;

    private native void native_delete(long j);

    private native void native_invalidate_fuse_dentry_cache(long j, String str);

    public static native boolean native_is_fuse_thread();

    private native boolean native_is_started(long j);

    private native long native_new(MediaProvider mediaProvider);

    private native boolean native_should_open_with_fuse(long j, String str, boolean z, int i);

    private native void native_start(long j, int i, String str);

    public FuseDaemon(MediaProvider mediaProvider, ExternalStorageServiceImpl externalStorageServiceImpl, ParcelFileDescriptor parcelFileDescriptor, String str, String str2) {
        Objects.requireNonNull(mediaProvider);
        this.mMediaProvider = mediaProvider;
        Objects.requireNonNull(externalStorageServiceImpl);
        this.mService = externalStorageServiceImpl;
        Objects.requireNonNull(str);
        setName(str);
        Objects.requireNonNull(parcelFileDescriptor);
        this.mFuseDeviceFd = parcelFileDescriptor.detachFd();
        Objects.requireNonNull(str2);
        this.mPath = str2;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        long jNative_new;
        synchronized (this.mLock) {
            jNative_new = native_new(this.mMediaProvider);
            this.mPtr = jNative_new;
            if (jNative_new == 0) {
                throw new IllegalStateException("Unable to create native FUSE daemon");
            }
        }
        Log.i(TAG, "Starting thread for " + getName() + " ...");
        native_start(jNative_new, this.mFuseDeviceFd, this.mPath);
        Log.i(TAG, "Exiting thread for " + getName() + " ...");
        synchronized (this.mLock) {
            native_delete(this.mPtr);
            this.mPtr = 0L;
        }
        this.mService.onExitSession(getName());
        Log.i(TAG, "Exited thread for " + getName());
    }

    @Override // java.lang.Thread
    public synchronized void start() {
        super.start();
        waitForStart();
    }

    private void waitForStart() {
        int i = 5;
        while (true) {
            int i2 = i - 1;
            if (i > 0) {
                synchronized (this.mLock) {
                    if (this.mPtr != 0 && native_is_started(this.mPtr)) {
                        return;
                    }
                }
                try {
                    Log.v(TAG, "Waiting 1000ms for FUSE start. Count " + i2);
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    Log.e(TAG, "Interrupted while starting FUSE", e);
                }
                i = i2;
            } else {
                throw new IllegalStateException("Failed to start FUSE");
            }
        }
    }

    public void waitForExit() {
        Log.i(TAG, "Waiting 5000ms for FUSE " + getName() + " to exit...");
        try {
            join(5000);
            if (isAlive()) {
                throw new IllegalStateException("Failed to exit FUSE " + getName() + " successfully");
            }
            Log.i(TAG, "Exited FUSE " + getName() + " successfully");
        } catch (InterruptedException unused) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException("Interrupted while terminating FUSE " + getName());
        }
    }

    public boolean shouldOpenWithFuse(String str, boolean z, int i) {
        synchronized (this.mLock) {
            if (this.mPtr == 0) {
                Log.i(TAG, "shouldOpenWithFuse failed, FUSE daemon unavailable");
                return false;
            }
            return native_should_open_with_fuse(this.mPtr, str, z, i);
        }
    }

    public void invalidateFuseDentryCache(String str) {
        synchronized (this.mLock) {
            if (this.mPtr == 0) {
                Log.i(TAG, "invalidateFuseDentryCache failed, FUSE daemon unavailable");
            } else {
                native_invalidate_fuse_dentry_cache(this.mPtr, str);
            }
        }
    }
}
