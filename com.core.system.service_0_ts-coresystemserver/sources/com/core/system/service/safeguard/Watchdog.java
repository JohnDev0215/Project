package com.core.system.service.safeguard;

import android.content.Context;
import android.os.Binder;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Process;
import android.os.SystemClock;
import com.ts.car.sdk.core.Logger;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes.dex */
public class Watchdog extends Thread {
    private static final String ANT_TRACE_PATH = "/data/anr/";
    private static final long CALL_STACK_WAIT_TIME = 10000;
    private static final long CHECK_INTERVAL = 30000;
    public static final int COMPLETED = 0;
    private static final long DEFAULT_TIMEOUT = 60000;
    private static final int EXIT_NUM = 10;
    public static final int OVERDUE = 3;
    private static final int STRING_BUFFER_SIZE = 128;
    public static final String TAG = "Watchdog";
    public static final int WAITED_HALF = 2;
    public static final int WAITING = 1;
    public static final int WATCHDOG_MONITOR = 10001;
    private static Watchdog sWatchdog;
    private Context mContext;
    private Handler mHandler;
    private final ArrayList<HandlerChecker> mHandlerCheckers;
    private HandlerThread mHandlerThread;
    private AtomicBoolean mIsThreadStarted;
    private final HandlerChecker mMonitorChecker;

    public interface ExServiceMonitor extends Monitor {
        void killSelf();
    }

    public interface Monitor {
        void monitor();
    }

    private static final class BinderThreadMonitor implements Monitor {
        private BinderThreadMonitor() {
        }

        @Override // com.core.system.service.safeguard.Watchdog.Monitor
        public void monitor() {
            try {
                Binder.blockUntilThreadAvailable();
            } catch (Throwable exception) {
                exception.printStackTrace();
            }
        }
    }

    public static synchronized Watchdog getInstance() {
        if (sWatchdog == null) {
            sWatchdog = new Watchdog();
        }
        return sWatchdog;
    }

    @Override // java.lang.Thread
    public synchronized void start() {
        if (!this.mIsThreadStarted.getAndSet(true)) {
            super.start();
        }
    }

    public static void setDebugLog(boolean debug) {
    }

    private Watchdog() {
        super("watchdog");
        this.mHandlerCheckers = new ArrayList<>();
        this.mIsThreadStarted = new AtomicBoolean(false);
        HandlerThread handlerThread = new HandlerThread(TAG);
        this.mHandlerThread = handlerThread;
        handlerThread.start();
        Handler handler = new Handler(this.mHandlerThread.getLooper());
        this.mHandler = handler;
        HandlerChecker handlerChecker = new HandlerChecker(handler, TAG, DEFAULT_TIMEOUT);
        this.mMonitorChecker = handlerChecker;
        this.mHandlerCheckers.add(handlerChecker);
        setHideEnable();
        addMonitor(new BinderThreadMonitor());
    }

    private void setHideEnable() {
        if (Build.VERSION.SDK_INT < 28) {
            return;
        }
        try {
            Method forName = Class.class.getDeclaredMethod("forName", String.class);
            Method getDeclaredMethod = Class.class.getDeclaredMethod("getDeclaredMethod", String.class, Class[].class);
            Class<?> vmRuntimeClass = (Class) forName.invoke(null, "dalvik.system.VMRuntime");
            Method getRuntime = (Method) getDeclaredMethod.invoke(vmRuntimeClass, "getRuntime", null);
            Method setHiddenApiExemptions = (Method) getDeclaredMethod.invoke(vmRuntimeClass, "setHiddenApiExemptions", new Class[]{String[].class});
            Object runtime = getRuntime.invoke(null, new Object[0]);
            setHiddenApiExemptions.invoke(runtime, new String[]{"L"});
        } catch (Throwable exception) {
            Logger.error(TAG, "reflect bootstrap failed:", exception);
        }
    }

    public void init(Context context) {
        this.mContext = context;
    }

    public void addMonitor(Monitor monitor) {
        synchronized (this) {
            this.mMonitorChecker.addMonitor(monitor);
        }
    }

    public void addThread(Handler thread) {
        addThread(thread, DEFAULT_TIMEOUT);
    }

    public void addThread(Handler thread, long timeoutMillis) {
        synchronized (this) {
            String name = thread.getLooper().getThread().getName();
            this.mHandlerCheckers.add(new HandlerChecker(thread, name, timeoutMillis));
        }
    }

    private int evaluateCheckerCompletionLocked() {
        int state = 0;
        for (int i = 0; i < this.mHandlerCheckers.size(); i++) {
            HandlerChecker hc = this.mHandlerCheckers.get(i);
            state = Math.max(state, hc.getCompletionStateLocked());
        }
        return state;
    }

    private ArrayList<HandlerChecker> getBlockedCheckersLocked() {
        ArrayList<HandlerChecker> checkers = new ArrayList<>();
        for (int i = 0; i < this.mHandlerCheckers.size(); i++) {
            HandlerChecker hc = this.mHandlerCheckers.get(i);
            if (hc.isOverdueLocked()) {
                checkers.add(hc);
            }
        }
        return checkers;
    }

    private String describeCheckersLocked(List<HandlerChecker> checkers) {
        StringBuilder builder = new StringBuilder(STRING_BUFFER_SIZE);
        for (int i = 0; i < checkers.size(); i++) {
            if (builder.length() > 0) {
                builder.append(", ");
            }
            builder.append(checkers.get(i).describeBlockedStateLocked());
        }
        return builder.toString();
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        boolean waitedHalf = false;
        while (true) {
            synchronized (this) {
                for (int i = 0; i < this.mHandlerCheckers.size(); i++) {
                    HandlerChecker hc = this.mHandlerCheckers.get(i);
                    hc.scheduleCheckLocked();
                }
                long start = SystemClock.uptimeMillis();
                for (long timeout = CHECK_INTERVAL; timeout > 0; timeout = CHECK_INTERVAL - (SystemClock.uptimeMillis() - start)) {
                    try {
                        wait(timeout);
                    } catch (InterruptedException exception) {
                        Logger.error(TAG, exception);
                    }
                }
                int waitState = evaluateCheckerCompletionLocked();
                if (waitState == 0) {
                    waitedHalf = false;
                } else if (waitState != 1) {
                    if (waitState == 2) {
                        if (!waitedHalf) {
                            Logger.debug(TAG, "run: waitdHalf thread: " + Thread.currentThread().getId());
                            waitedHalf = true;
                        }
                    } else {
                        List<HandlerChecker> blockedCheckers = getBlockedCheckersLocked();
                        String subject = describeCheckersLocked(blockedCheckers);
                        Logger.error(TAG, "WatchDog! info:" + subject);
                        if (this.mMonitorChecker.getCurrentMonitor() instanceof ExServiceMonitor) {
                            Logger.error(TAG, "*** stop ext service");
                            Logger.error(TAG, "*** CORESYSTEM KILL PID " + Binder.getCallingPid());
                            Logger.error(TAG, "*** CORESYSTEM KILL UID " + Binder.getCallingUid());
                            ((ExServiceMonitor) this.mMonitorChecker.getCurrentMonitor()).killSelf();
                        } else {
                            Logger.error(TAG, "*** CORESYSTEM WATCHDOG KILLING SYSTEM PROCESS: " + subject);
                            Logger.error(TAG, "*** CORESYSTEM GOODBYE!");
                            Process.sendSignal(Process.myPid(), 3);
                            try {
                                Thread.sleep(CALL_STACK_WAIT_TIME);
                            } catch (InterruptedException exception2) {
                                exception2.printStackTrace();
                            }
                            Process.killProcess(Process.myPid());
                            System.exit(10);
                            waitedHalf = false;
                        }
                    }
                }
            }
        }
    }
}
