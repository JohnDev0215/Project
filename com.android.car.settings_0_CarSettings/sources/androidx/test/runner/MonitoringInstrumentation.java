package androidx.test.runner;

import android.app.Activity;
import android.app.Application;
import android.app.Fragment;
import android.app.Instrumentation;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.MessageQueue;
import android.os.UserHandle;
import android.util.Log;
import androidx.test.internal.platform.app.ActivityLifecycleTimeout;
import androidx.test.internal.runner.InstrumentationConnection;
import androidx.test.internal.runner.hidden.ExposedInstrumentationApi;
import androidx.test.internal.runner.intent.IntentMonitorImpl;
import androidx.test.internal.runner.intercepting.DefaultInterceptingActivityFactory;
import androidx.test.internal.runner.lifecycle.ActivityLifecycleMonitorImpl;
import androidx.test.internal.runner.lifecycle.ApplicationLifecycleMonitorImpl;
import androidx.test.internal.util.Checks;
import androidx.test.internal.util.ProcSummary;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.runner.intent.IntentMonitorRegistry;
import androidx.test.runner.intent.IntentStubberRegistry;
import androidx.test.runner.intercepting.InterceptingActivityFactory;
import androidx.test.runner.lifecycle.ActivityLifecycleMonitorRegistry;
import androidx.test.runner.lifecycle.ApplicationLifecycleMonitorRegistry;
import androidx.test.runner.lifecycle.ApplicationStage;
import androidx.test.runner.lifecycle.Stage;
import com.android.car.ui.utils.RotaryConstants;
import java.io.File;
import java.lang.Thread;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/* JADX INFO: loaded from: classes5.dex */
public class MonitoringInstrumentation extends ExposedInstrumentationApi {
    private static final long MILLIS_TO_POLL_FOR_ACTIVITY_STOP;
    private static final long MILLIS_TO_WAIT_FOR_ACTIVITY_TO_STOP;
    private static final String TAG = "MonitoringInstr";
    private ExecutorService executorService;
    private Handler handlerForMainLooper;
    private volatile InterceptingActivityFactory interceptingActivityFactory;
    private String jsBridgeClassName;
    private Thread.UncaughtExceptionHandler standardHandler;
    private ActivityLifecycleMonitorImpl lifecycleMonitor = new ActivityLifecycleMonitorImpl();
    private ApplicationLifecycleMonitorImpl applicationMonitor = new ApplicationLifecycleMonitorImpl();
    private IntentMonitorImpl intentMonitor = new IntentMonitorImpl();
    private AtomicBoolean anActivityHasBeenLaunched = new AtomicBoolean(false);
    private AtomicLong lastIdleTime = new AtomicLong(0);
    private AtomicInteger startedActivityCounter = new AtomicInteger(0);
    private AtomicBoolean isJsBridgeLoaded = new AtomicBoolean(false);
    private volatile Boolean isOriginalInstr = null;
    private ThreadLocal<Boolean> isDexmakerClassLoaderInitialized = new ThreadLocal<>();
    private MessageQueue.IdleHandler idleHandler = new MessageQueue.IdleHandler() { // from class: androidx.test.runner.MonitoringInstrumentation.1
        @Override // android.os.MessageQueue.IdleHandler
        public boolean queueIdle() {
            MonitoringInstrumentation.this.lastIdleTime.set(System.currentTimeMillis());
            return true;
        }
    };
    private volatile boolean finished = false;

    static {
        long millis = TimeUnit.SECONDS.toMillis(2L);
        MILLIS_TO_WAIT_FOR_ACTIVITY_TO_STOP = millis;
        MILLIS_TO_POLL_FOR_ACTIVITY_STOP = millis / 40;
    }

    @Override // android.app.Instrumentation
    public void onCreate(Bundle arguments) {
        Log.i(TAG, "Instrumentation started!");
        logUncaughtExceptions();
        installMultidex();
        InstrumentationRegistry.registerInstance(this, arguments);
        androidx.test.InstrumentationRegistry.registerInstance(this, arguments);
        ActivityLifecycleMonitorRegistry.registerInstance(this.lifecycleMonitor);
        ApplicationLifecycleMonitorRegistry.registerInstance(this.applicationMonitor);
        IntentMonitorRegistry.registerInstance(this.intentMonitor);
        this.handlerForMainLooper = new Handler(Looper.getMainLooper());
        this.executorService = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 0L, TimeUnit.SECONDS, new SynchronousQueue(), new ThreadFactory(this) { // from class: androidx.test.runner.MonitoringInstrumentation.2
            @Override // java.util.concurrent.ThreadFactory
            public Thread newThread(Runnable runnable) {
                Thread thread = Executors.defaultThreadFactory().newThread(runnable);
                thread.setName(MonitoringInstrumentation.class.getSimpleName());
                return thread;
            }
        });
        Looper.myQueue().addIdleHandler(this.idleHandler);
        super.onCreate(arguments);
        specifyDexMakerCacheProperty();
        setupDexmakerClassloader();
        useDefaultInterceptingActivityFactory();
    }

    protected void installMultidex() {
        if (Build.VERSION.SDK_INT < 21) {
            try {
                try {
                    Class<?> multidex = Class.forName("androidx.multidex.MultiDex");
                    try {
                        Method installInstrumentation = multidex.getDeclaredMethod("installInstrumentation", Context.class, Context.class);
                        installInstrumentation.invoke(null, getContext(), getTargetContext());
                    } catch (NoSuchMethodException e) {
                        installOldMultiDex(multidex);
                    }
                } catch (ClassNotFoundException e2) {
                    Log.i(TAG, "No multidex.");
                } catch (IllegalAccessException iae) {
                    throw new RuntimeException("multidex is available at runtime, but calling it failed.", iae);
                } catch (InvocationTargetException ite) {
                    throw new RuntimeException("multidex is available at runtime, but calling it failed.", ite);
                }
            } catch (NoSuchMethodException nsme) {
                Log.i(TAG, "No multidex.", nsme);
            }
        }
    }

    protected void installOldMultiDex(Class<?> multidexClass) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Method install = multidexClass.getDeclaredMethod("install", Context.class);
        install.invoke(null, getTargetContext());
    }

    protected void specifyDexMakerCacheProperty() {
        File dexCache = getTargetContext().getDir("dxmaker_cache", 0);
        System.getProperties().put("dexmaker.dexcache", dexCache.getAbsolutePath());
    }

    protected final void setJsBridgeClassName(final String className) {
        if (className == null) {
            throw new NullPointerException("JsBridge class name cannot be null!");
        }
        if (this.isJsBridgeLoaded.get()) {
            throw new IllegalStateException("JsBridge is already loaded!");
        }
        this.jsBridgeClassName = className;
    }

    private void setupDexmakerClassloader() {
        if (Boolean.TRUE.equals(this.isDexmakerClassLoaderInitialized.get())) {
            return;
        }
        ClassLoader originalClassLoader = Thread.currentThread().getContextClassLoader();
        ClassLoader newClassLoader = getTargetContext().getClassLoader();
        if (originalClassLoader != newClassLoader) {
            Log.i(TAG, String.format("Setting context classloader to '%s', Original: '%s'", newClassLoader, originalClassLoader));
            Thread.currentThread().setContextClassLoader(newClassLoader);
        }
        this.isDexmakerClassLoaderInitialized.set(Boolean.TRUE);
    }

    private void logUncaughtExceptions() {
        this.standardHandler = Thread.currentThread().getUncaughtExceptionHandler();
        Thread.currentThread().setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() { // from class: androidx.test.runner.MonitoringInstrumentation.3
            @Override // java.lang.Thread.UncaughtExceptionHandler
            public void uncaughtException(Thread t, Throwable e) {
                MonitoringInstrumentation.this.onException(t, e);
                if (MonitoringInstrumentation.this.standardHandler != null) {
                    Log.w(MonitoringInstrumentation.TAG, String.format("Invoking uncaught exception handler %s (a %s)", MonitoringInstrumentation.this.standardHandler, MonitoringInstrumentation.this.standardHandler.getClass()));
                    MonitoringInstrumentation.this.standardHandler.uncaughtException(t, e);
                } else {
                    String strValueOf = String.valueOf(t.getName());
                    Log.w(MonitoringInstrumentation.TAG, strValueOf.length() != 0 ? "Invoking uncaught exception handler for thread: ".concat(strValueOf) : new String("Invoking uncaught exception handler for thread: "));
                    t.getThreadGroup().uncaughtException(t, e);
                }
                if (!"robolectric".equals(Build.FINGERPRINT) && Looper.getMainLooper().getThread().equals(t)) {
                    Log.e(MonitoringInstrumentation.TAG, "The main thread has died and the handlers didn't care, exiting");
                    System.exit(-10);
                }
            }
        });
    }

    protected void restoreUncaughtExceptionHandler() {
        Thread.currentThread().setUncaughtExceptionHandler(this.standardHandler);
    }

    @Override // android.app.Instrumentation
    public void onStart() {
        super.onStart();
        String str = this.jsBridgeClassName;
        if (str != null) {
            tryLoadingJsBridge(str);
        }
        waitForIdleSync();
        setupDexmakerClassloader();
        InstrumentationConnection.getInstance().init(this, new ActivityFinisher());
    }

    @Override // android.app.Instrumentation
    public void finish(int resultCode, Bundle results) {
        if (this.finished) {
            Log.w(TAG, "finish called 2x!");
            return;
        }
        this.finished = true;
        if (shouldWaitForActivitiesToComplete()) {
            this.handlerForMainLooper.post(new ActivityFinisher());
            long startTime = System.currentTimeMillis();
            waitForActivitiesToComplete();
            long endTime = System.currentTimeMillis();
            Log.i(TAG, String.format("waitForActivitiesToComplete() took: %sms", Long.valueOf(endTime - startTime)));
        }
        ActivityLifecycleMonitorRegistry.registerInstance(null);
        restoreUncaughtExceptionHandler();
        super.finish(resultCode, results);
    }

    protected boolean shouldWaitForActivitiesToComplete() {
        return Boolean.parseBoolean(InstrumentationRegistry.getArguments().getString("waitForActivitiesToComplete", "true"));
    }

    protected void waitForActivitiesToComplete() {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            throw new IllegalStateException("Cannot be called from main thread!");
        }
        long endTime = System.currentTimeMillis() + MILLIS_TO_WAIT_FOR_ACTIVITY_TO_STOP;
        int currentActivityCount = this.startedActivityCounter.get();
        while (currentActivityCount > 0 && System.currentTimeMillis() < endTime) {
            try {
                StringBuilder sb = new StringBuilder(37);
                sb.append("Unstopped activity count: ");
                sb.append(currentActivityCount);
                Log.i(TAG, sb.toString());
                Thread.sleep(MILLIS_TO_POLL_FOR_ACTIVITY_STOP);
                currentActivityCount = this.startedActivityCounter.get();
            } catch (InterruptedException ie) {
                Log.i(TAG, "Abandoning activity wait due to interruption.", ie);
            }
        }
        if (currentActivityCount > 0) {
            dumpThreadStateToOutputs("ThreadState-unstopped.txt");
            Log.w(TAG, String.format("Still %s activities active after waiting %s ms.", Integer.valueOf(currentActivityCount), Long.valueOf(MILLIS_TO_WAIT_FOR_ACTIVITY_TO_STOP)));
        }
    }

    @Override // android.app.Instrumentation
    public void onDestroy() {
        Log.i(TAG, "Instrumentation Finished!");
        Looper.myQueue().removeIdleHandler(this.idleHandler);
        InstrumentationConnection.getInstance().terminate();
        super.onDestroy();
    }

    @Override // android.app.Instrumentation
    public void callApplicationOnCreate(Application app) {
        this.applicationMonitor.signalLifecycleChange(app, ApplicationStage.PRE_ON_CREATE);
        super.callApplicationOnCreate(app);
        this.applicationMonitor.signalLifecycleChange(app, ApplicationStage.CREATED);
    }

    @Override // android.app.Instrumentation
    public void runOnMainSync(Runnable runnable) {
        FutureTask<Void> wrapped = new FutureTask<>(runnable, null);
        super.runOnMainSync(wrapped);
        try {
            wrapped.get();
        } catch (InterruptedException e) {
            Log.e(TAG, "An execution is interrupted", e);
            throw new RuntimeException(e);
        } catch (ExecutionException e2) {
            Log.e(TAG, "An exception is thrown from the runnable posted to the main thread", e2);
            throw new RuntimeException(e2.getCause());
        }
    }

    @Override // android.app.Instrumentation
    public Activity startActivitySync(final Intent intent) {
        Checks.checkNotMainThread();
        long lastIdleTimeBeforeLaunch = this.lastIdleTime.get();
        if (this.anActivityHasBeenLaunched.compareAndSet(false, true)) {
            intent.addFlags(RotaryConstants.ACTION_RESTORE_DEFAULT_FOCUS);
        }
        Future<Activity> startedActivity = this.executorService.submit(new Callable<Activity>() { // from class: androidx.test.runner.MonitoringInstrumentation.4
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            public Activity call() {
                return MonitoringInstrumentation.super.startActivitySync(intent);
            }
        });
        try {
            return startedActivity.get(ActivityLifecycleTimeout.getMillis(), TimeUnit.MILLISECONDS);
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("interrupted", ie);
        } catch (ExecutionException ee) {
            throw new RuntimeException("Could not launch activity", ee.getCause());
        } catch (TimeoutException e) {
            dumpThreadStateToOutputs("ThreadState-startActivityTimeout.txt");
            startedActivity.cancel(true);
            throw new RuntimeException(String.format("Could not launch intent %s within %s milliseconds. Perhaps the main thread has not gone idle within a reasonable amount of time? There could be an animation or something constantly repainting the screen. Or the activity is doing network calls on creation? See the threaddump logs. For your reference the last time the event queue was idle before your activity launch request was %s and now the last time the queue went idle was: %s. If these numbers are the same your activity might be hogging the event queue.", intent, Long.valueOf(ActivityLifecycleTimeout.getMillis()), Long.valueOf(lastIdleTimeBeforeLaunch), Long.valueOf(this.lastIdleTime.get())));
        }
    }

    public Instrumentation.ActivityResult execStartActivity(Context who, IBinder contextThread, IBinder token, Activity target, Intent intent, int requestCode) {
        this.intentMonitor.signalIntent(intent);
        Instrumentation.ActivityResult ar = stubResultFor(intent);
        if (ar != null) {
            Log.i(TAG, String.format("Stubbing intent %s", intent));
            return ar;
        }
        return super.execStartActivity(who, contextThread, token, target, intent, requestCode);
    }

    public Instrumentation.ActivityResult execStartActivity(Context who, IBinder contextThread, IBinder token, Activity target, Intent intent, int requestCode, Bundle options) {
        this.intentMonitor.signalIntent(intent);
        Instrumentation.ActivityResult ar = stubResultFor(intent);
        if (ar != null) {
            Log.i(TAG, String.format("Stubbing intent %s", intent));
            return ar;
        }
        return super.execStartActivity(who, contextThread, token, target, intent, requestCode, options);
    }

    public Instrumentation.ActivityResult execStartActivity(Context who, IBinder contextThread, IBinder token, String target, Intent intent, int requestCode, Bundle options) {
        this.intentMonitor.signalIntent(intent);
        Instrumentation.ActivityResult ar = stubResultFor(intent);
        if (ar != null) {
            Log.i(TAG, String.format("Stubbing intent %s", intent));
            return ar;
        }
        return super.execStartActivity(who, contextThread, token, target, intent, requestCode, options);
    }

    public Instrumentation.ActivityResult execStartActivity(Context who, IBinder contextThread, IBinder token, Activity target, Intent intent, int requestCode, Bundle options, UserHandle user) {
        return super.execStartActivity(who, contextThread, token, target, intent, requestCode, options, user);
    }

    public void execStartActivities(Context who, IBinder contextThread, IBinder token, Activity target, Intent[] intents, Bundle options) {
        Log.d(TAG, "execStartActivities(context, ibinder, ibinder, activity, intent[], bundle)");
        for (Intent intent : intents) {
            execStartActivity(who, contextThread, token, target, intent, -1, options);
        }
    }

    public Instrumentation.ActivityResult execStartActivity(Context who, IBinder contextThread, IBinder token, Fragment target, Intent intent, int requestCode, Bundle options) {
        Log.d(TAG, "execStartActivity(context, IBinder, IBinder, Fragment, Intent, int, Bundle)");
        this.intentMonitor.signalIntent(intent);
        Instrumentation.ActivityResult ar = stubResultFor(intent);
        if (ar != null) {
            Log.i(TAG, String.format("Stubbing intent %s", intent));
            return ar;
        }
        return super.execStartActivity(who, contextThread, token, target, intent, requestCode, options);
    }

    private static class StubResultCallable implements Callable<Instrumentation.ActivityResult> {
        private final Intent intent;

        StubResultCallable(Intent intent) {
            this.intent = intent;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.concurrent.Callable
        public Instrumentation.ActivityResult call() {
            return IntentStubberRegistry.getInstance().getActivityResultForIntent(this.intent);
        }
    }

    private Instrumentation.ActivityResult stubResultFor(Intent intent) {
        if (IntentStubberRegistry.isLoaded()) {
            if (Looper.myLooper() != Looper.getMainLooper()) {
                FutureTask<Instrumentation.ActivityResult> task = new FutureTask<>(new StubResultCallable(intent));
                runOnMainSync(task);
                try {
                    return task.get();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException(e);
                } catch (ExecutionException e2) {
                    throw new RuntimeException(String.format("Could not retrieve stub result for intent %s", intent), e2);
                }
            }
            return IntentStubberRegistry.getInstance().getActivityResultForIntent(intent);
        }
        return null;
    }

    @Override // android.app.Instrumentation
    public boolean onException(Object obj, Throwable e) {
        String error = String.format("Exception encountered by: %s. Dumping thread state to outputs and pining for the fjords.", obj);
        Log.e(TAG, error, e);
        dumpThreadStateToOutputs("ThreadState-onException.txt");
        Log.e(TAG, "Dying now...");
        return super.onException(obj, e);
    }

    protected void dumpThreadStateToOutputs(String outputFileName) {
        String threadState = getThreadState();
        Log.e("THREAD_STATE", threadState);
    }

    protected String getThreadState() {
        Set<Map.Entry<Thread, StackTraceElement[]>> threads = Thread.getAllStackTraces().entrySet();
        StringBuilder threadState = new StringBuilder();
        for (Map.Entry<Thread, StackTraceElement[]> threadAndStack : threads) {
            StringBuilder threadMessage = new StringBuilder("  ").append(threadAndStack.getKey());
            threadMessage.append("\n");
            for (StackTraceElement ste : threadAndStack.getValue()) {
                threadMessage.append("    ");
                threadMessage.append(ste.toString());
                threadMessage.append("\n");
            }
            threadMessage.append("\n");
            threadState.append(threadMessage.toString());
        }
        return threadState.toString();
    }

    @Override // android.app.Instrumentation
    public void callActivityOnDestroy(Activity activity) {
        super.callActivityOnDestroy(activity);
        this.lifecycleMonitor.signalLifecycleChange(Stage.DESTROYED, activity);
    }

    @Override // android.app.Instrumentation
    public void callActivityOnRestart(Activity activity) {
        super.callActivityOnRestart(activity);
        this.lifecycleMonitor.signalLifecycleChange(Stage.RESTARTED, activity);
    }

    @Override // android.app.Instrumentation
    public void callActivityOnCreate(Activity activity, Bundle bundle) {
        this.lifecycleMonitor.signalLifecycleChange(Stage.PRE_ON_CREATE, activity);
        super.callActivityOnCreate(activity, bundle);
        this.lifecycleMonitor.signalLifecycleChange(Stage.CREATED, activity);
    }

    @Override // android.app.Instrumentation
    public void callActivityOnStart(Activity activity) {
        this.startedActivityCounter.incrementAndGet();
        try {
            super.callActivityOnStart(activity);
            this.lifecycleMonitor.signalLifecycleChange(Stage.STARTED, activity);
        } catch (RuntimeException re) {
            this.startedActivityCounter.decrementAndGet();
            throw re;
        }
    }

    @Override // android.app.Instrumentation
    public void callActivityOnStop(Activity activity) {
        try {
            super.callActivityOnStop(activity);
            this.lifecycleMonitor.signalLifecycleChange(Stage.STOPPED, activity);
        } finally {
            this.startedActivityCounter.decrementAndGet();
        }
    }

    @Override // android.app.Instrumentation
    public void callActivityOnResume(Activity activity) {
        super.callActivityOnResume(activity);
        this.lifecycleMonitor.signalLifecycleChange(Stage.RESUMED, activity);
    }

    @Override // android.app.Instrumentation
    public void callActivityOnPause(Activity activity) {
        super.callActivityOnPause(activity);
        this.lifecycleMonitor.signalLifecycleChange(Stage.PAUSED, activity);
    }

    @Override // android.app.Instrumentation
    public Activity newActivity(Class<?> clazz, Context context, IBinder token, Application application, Intent intent, ActivityInfo info, CharSequence title, Activity parent, String id, Object lastNonConfigurationInstance) throws IllegalAccessException, InstantiationException {
        String activityClassPackageName = clazz.getPackage().getName();
        String contextPackageName = context.getPackageName();
        ComponentName intentComponentName = intent.getComponent();
        if (!contextPackageName.equals(intentComponentName.getPackageName()) && activityClassPackageName.equals(intentComponentName.getPackageName())) {
            intent.setComponent(new ComponentName(contextPackageName, intentComponentName.getClassName()));
        }
        return super.newActivity(clazz, context, token, application, intent, info, title, parent, id, lastNonConfigurationInstance);
    }

    @Override // android.app.Instrumentation
    public Activity newActivity(ClassLoader cl, String className, Intent intent) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        if (this.interceptingActivityFactory.shouldIntercept(cl, className, intent)) {
            return this.interceptingActivityFactory.create(cl, className, intent);
        }
        return super.newActivity(cl, className, intent);
    }

    public void interceptActivityUsing(InterceptingActivityFactory interceptingActivityFactory) {
        Checks.checkNotNull(interceptingActivityFactory);
        this.interceptingActivityFactory = interceptingActivityFactory;
    }

    public void useDefaultInterceptingActivityFactory() {
        this.interceptingActivityFactory = new DefaultInterceptingActivityFactory();
    }

    private void tryLoadingJsBridge(final String className) {
        if (className == null) {
            throw new NullPointerException("JsBridge class name cannot be null!");
        }
        runOnMainSync(new Runnable() { // from class: androidx.test.runner.MonitoringInstrumentation.5
            @Override // java.lang.Runnable
            public void run() {
                try {
                    Class<?> jsBridge = Class.forName(className);
                    Method install = jsBridge.getDeclaredMethod("installBridge", new Class[0]);
                    install.invoke(null, new Object[0]);
                    MonitoringInstrumentation.this.isJsBridgeLoaded.set(true);
                } catch (ClassNotFoundException | NoSuchMethodException e) {
                    Log.i(MonitoringInstrumentation.TAG, "No JSBridge.");
                } catch (IllegalAccessException | InvocationTargetException ite) {
                    throw new RuntimeException("JSbridge is available at runtime, but calling it failed.", ite);
                }
            }
        });
    }

    public class ActivityFinisher implements Runnable {
        public ActivityFinisher() {
        }

        @Override // java.lang.Runnable
        public void run() {
            List<Activity> activities = new ArrayList<>();
            for (Stage s : EnumSet.range(Stage.CREATED, Stage.STOPPED)) {
                activities.addAll(MonitoringInstrumentation.this.lifecycleMonitor.getActivitiesInStage(s));
            }
            if (activities.size() > 0) {
                int size = activities.size();
                StringBuilder sb = new StringBuilder(60);
                sb.append("Activities that are still in CREATED to STOPPED: ");
                sb.append(size);
                Log.i(MonitoringInstrumentation.TAG, sb.toString());
            }
            for (Activity activity : activities) {
                if (!activity.isFinishing()) {
                    try {
                        String strValueOf = String.valueOf(activity);
                        StringBuilder sb2 = new StringBuilder(String.valueOf(strValueOf).length() + 20);
                        sb2.append("Finishing activity: ");
                        sb2.append(strValueOf);
                        Log.i(MonitoringInstrumentation.TAG, sb2.toString());
                        activity.finish();
                    } catch (RuntimeException e) {
                        Log.e(MonitoringInstrumentation.TAG, "Failed to finish activity.", e);
                    }
                }
            }
        }
    }

    @Deprecated
    protected boolean isPrimaryInstrProcess(String argsProcessName) {
        if (Build.VERSION.SDK_INT == 23) {
            try {
                ProcSummary me = ProcSummary.summarize("self");
                if (argsProcessName == null && (argsProcessName = getTargetContext().getApplicationInfo().processName) == null) {
                    argsProcessName = getTargetContext().getPackageName();
                }
                return isHostingProcess(argsProcessName, me);
            } catch (ProcSummary.SummaryException se) {
                Log.w(TAG, "Could not read our proc data / assumming a sandboxed proc", se);
                return false;
            }
        }
        return isPrimaryInstrProcess();
    }

    protected final boolean isPrimaryInstrProcess() {
        return isOriginalInstrumentationProcess();
    }

    private boolean isHostingProcess(String wantName, ProcSummary ps) {
        int wantLen = wantName.length();
        int cmdLen = ps.cmdline.length();
        if (wantLen == cmdLen) {
            return wantName.equals(ps.cmdline);
        }
        if (wantLen < cmdLen || !wantName.startsWith(ps.cmdline) || !wantName.endsWith(ps.name)) {
            return false;
        }
        String strValueOf = String.valueOf(ps);
        StringBuilder sb = new StringBuilder(String.valueOf(strValueOf).length() + 165 + String.valueOf(wantName).length());
        sb.append("Use smaller processNames in AndroidManifest.xml. Long names are truncated. This process's cmdline is a prefix of the processName and suffix of comm - assuming: ");
        sb.append(strValueOf);
        sb.append(" is: ");
        sb.append(wantName);
        Log.w(TAG, sb.toString());
        return true;
    }

    private boolean isOriginalInstrumentationProcess() {
        Boolean isOriginal = this.isOriginalInstr;
        if (isOriginal == null) {
            isOriginal = Boolean.valueOf(isOriginalUncached());
            this.isOriginalInstr = isOriginal;
        }
        return isOriginal.booleanValue();
    }

    private List<String> getTargetProcessValues() {
        if (Build.VERSION.SDK_INT < 26) {
            return Collections.emptyList();
        }
        try {
            String tpVal = getContext().getPackageManager().getInstrumentationInfo(getComponentName(), 0).targetProcesses;
            if (tpVal == null) {
                tpVal = "";
            }
            String tpVal2 = tpVal.trim();
            if (tpVal2.length() == 0) {
                return Collections.emptyList();
            }
            List<String> tps = new ArrayList<>();
            for (String str : tpVal2.split(",", -1)) {
                String tp = str.trim();
                if (tp.length() > 0) {
                    tps.add(tp);
                }
            }
            return tps;
        } catch (PackageManager.NameNotFoundException unpossible) {
            String strValueOf = String.valueOf(getComponentName());
            StringBuilder sb = new StringBuilder(String.valueOf(strValueOf).length() + 25);
            sb.append("Cannot locate ourselves: ");
            sb.append(strValueOf);
            Log.wtf(TAG, sb.toString(), unpossible);
            String strValueOf2 = String.valueOf(getComponentName());
            StringBuilder sb2 = new StringBuilder(String.valueOf(strValueOf2).length() + 25);
            sb2.append("Cannot locate ourselves: ");
            sb2.append(strValueOf2);
            throw new IllegalStateException(sb2.toString(), unpossible);
        }
    }

    private boolean isOriginalUncached() {
        if (Build.VERSION.SDK_INT < 26) {
            return true;
        }
        List<String> targetProcesses = getTargetProcessValues();
        if (targetProcesses.isEmpty()) {
            return true;
        }
        boolean isWildcard = "*".equals(targetProcesses.get(0));
        if (targetProcesses.size() == 1 && !isWildcard) {
            return true;
        }
        try {
            ProcSummary me = ProcSummary.summarize("self");
            if (isWildcard) {
                String appDefProcessName = getTargetContext().getApplicationInfo().processName;
                if (appDefProcessName == null) {
                    appDefProcessName = getTargetContext().getPackageName();
                }
                return isHostingProcess(appDefProcessName, me);
            }
            return isHostingProcess(targetProcesses.get(0), me);
        } catch (ProcSummary.SummaryException se) {
            Log.w(TAG, "Could not list apps for this user, running in sandbox? Assuming primary", se);
            return false;
        }
    }
}
