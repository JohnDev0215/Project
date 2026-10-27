package com.android.car;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Binder;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.SystemClock;
import android.util.ArrayMap;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public final class CarServiceUtils {
    private static final String PACKAGE_NOT_FOUND = "Package not found:";
    private static final String TAG = "CAR.UTIL";
    public static final int[] EMPTY_INT_ARRAY = new int[0];
    private static final ArrayMap<String, HandlerThread> sHandlerThreads = new ArrayMap<>();

    private CarServiceUtils() {
    }

    public static void assertPackageName(Context context, String packageName) throws SecurityException, IllegalArgumentException {
        if (packageName == null) {
            throw new IllegalArgumentException("Package name null");
        }
        try {
            ApplicationInfo appInfo = context.getPackageManager().getApplicationInfo(packageName, 0);
            if (appInfo == null) {
                throw new SecurityException(PACKAGE_NOT_FOUND + packageName);
            }
            int uid = Binder.getCallingUid();
            if (uid != appInfo.uid) {
                throw new SecurityException("Wrong package name:" + packageName + ", The package does not belong to caller's uid:" + uid);
            }
        } catch (PackageManager.NameNotFoundException e) {
            String msg = PACKAGE_NOT_FOUND + packageName;
            Log.w(CarLog.TAG_SERVICE, msg, e);
            throw new SecurityException(msg, e);
        }
    }

    public static void runOnMain(Runnable action) {
        runOnLooper(Looper.getMainLooper(), action);
    }

    public static void runOnLooper(Looper looper, Runnable action) {
        new Handler(looper).post(action);
    }

    public static void runOnMainSync(Runnable action) {
        runOnLooperSync(Looper.getMainLooper(), action);
    }

    public static void runOnLooperSync(Looper looper, Runnable action) {
        if (Looper.myLooper() == looper) {
            action.run();
            return;
        }
        Handler handler = new Handler(looper);
        SyncRunnable sr = new SyncRunnable(action);
        handler.post(sr);
        sr.waitForComplete();
    }

    private static final class SyncRunnable implements Runnable {
        private volatile boolean mComplete = false;
        private final Runnable mTarget;

        public SyncRunnable(Runnable target) {
            this.mTarget = target;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.mTarget.run();
            synchronized (this) {
                this.mComplete = true;
                notifyAll();
            }
        }

        public void waitForComplete() {
            synchronized (this) {
                while (!this.mComplete) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                    }
                }
            }
        }
    }

    public static float[] toFloatArray(List<Float> list) {
        int size = list.size();
        float[] array = new float[size];
        for (int i = 0; i < size; i++) {
            array[i] = list.get(i).floatValue();
        }
        return array;
    }

    public static int[] toIntArray(List<Integer> list) {
        int size = list.size();
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = list.get(i).intValue();
        }
        return array;
    }

    public static byte[] toByteArray(List<Byte> list) {
        int size = list.size();
        byte[] array = new byte[size];
        for (int i = 0; i < size; i++) {
            array[i] = list.get(i).byteValue();
        }
        return array;
    }

    public static long getUptimeToElapsedTimeDeltaInMillis() {
        long elapsed1;
        long uptime;
        int retry = 0;
        do {
            elapsed1 = SystemClock.elapsedRealtime();
            uptime = SystemClock.uptimeMillis();
            long elapsed2 = SystemClock.elapsedRealtime();
            if (elapsed1 == elapsed2) {
                return elapsed1 - uptime;
            }
            retry++;
        } while (retry < 2);
        return elapsed1 - uptime;
    }

    public static HandlerThread getHandlerThread(String name) {
        HandlerThread thread;
        synchronized (sHandlerThreads) {
            thread = sHandlerThreads.get(name);
            if (thread == null || !thread.isAlive()) {
                Log.i(TAG, "Starting HandlerThread:" + name);
                thread = new HandlerThread(name);
                thread.start();
                sHandlerThreads.put(name, thread);
            }
        }
        return thread;
    }

    public static void finishAllHandlerTasks() {
        ArrayList<HandlerThread> threads;
        synchronized (sHandlerThreads) {
            threads = new ArrayList<>(sHandlerThreads.values());
        }
        ArrayList<SyncRunnable> syncs = new ArrayList<>(threads.size());
        for (int i = 0; i < threads.size(); i++) {
            Handler handler = new Handler(threads.get(i).getLooper());
            SyncRunnable sr = new SyncRunnable(new Runnable() { // from class: com.android.car.-$$Lambda$CarServiceUtils$00JY2zB9gEavI-m-2JTkAhqi6Hc
                @Override // java.lang.Runnable
                public final void run() {
                    CarServiceUtils.lambda$finishAllHandlerTasks$0();
                }
            });
            handler.post(sr);
            syncs.add(sr);
        }
        for (int i2 = 0; i2 < syncs.size(); i2++) {
            syncs.get(i2).waitForComplete();
        }
    }

    static /* synthetic */ void lambda$finishAllHandlerTasks$0() {
    }
}
