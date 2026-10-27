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
import androidx.annotation.Nullable;
import androidx.appcompat.widget.ActivityChooserView;
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
import com.bydux.api.common.tools.cmd.BydAdbHelper;
import com.bydux.api.common.tools.util.ListUtils;
import java.lang.Thread;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
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

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
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

    /* JADX INFO: compiled from: caradapter-bug */
    public class ActivityFinisher implements Runnable {
        public ActivityFinisher() {
        }

        @Override // java.lang.Runnable
        public void run() {
            ArrayList<Activity> arrayList = new ArrayList();
            Iterator it = EnumSet.range(Stage.CREATED, Stage.STOPPED).iterator();
            while (it.hasNext()) {
                arrayList.addAll(MonitoringInstrumentation.this.lifecycleMonitor.getActivitiesInStage((Stage) it.next()));
            }
            if (arrayList.size() > 0) {
                int size = arrayList.size();
                StringBuilder sb = new StringBuilder(60);
                sb.append("Activities that are still in CREATED to STOPPED: ");
                sb.append(size);
                Log.i(MonitoringInstrumentation.TAG, sb.toString());
            }
            for (Activity activity : arrayList) {
                if (!activity.isFinishing()) {
                    try {
                        String strValueOf = String.valueOf(activity);
                        StringBuilder sb2 = new StringBuilder(strValueOf.length() + 20);
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

    /* JADX INFO: compiled from: caradapter-bug */
    public static class StubResultCallable implements Callable<Instrumentation.ActivityResult> {
        private final Intent intent;

        public StubResultCallable(Intent intent) {
            this.intent = intent;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.concurrent.Callable
        public Instrumentation.ActivityResult call() {
            return IntentStubberRegistry.getInstance().getActivityResultForIntent(this.intent);
        }
    }

    static {
        long millis = TimeUnit.SECONDS.toMillis(2L);
        MILLIS_TO_WAIT_FOR_ACTIVITY_TO_STOP = millis;
        MILLIS_TO_POLL_FOR_ACTIVITY_STOP = millis / 40;
    }

    private List<String> getTargetProcessValues() {
        if (Build.VERSION.SDK_INT < 26) {
            return Collections.emptyList();
        }
        try {
            String str = getContext().getPackageManager().getInstrumentationInfo(getComponentName(), 0).targetProcesses;
            if (str == null) {
                str = "";
            }
            String strTrim = str.trim();
            if (strTrim.length() == 0) {
                return Collections.emptyList();
            }
            ArrayList arrayList = new ArrayList();
            for (String str2 : strTrim.split(ListUtils.DEFAULT_JOIN_SEPARATOR, -1)) {
                String strTrim2 = str2.trim();
                if (strTrim2.length() > 0) {
                    arrayList.add(strTrim2);
                }
            }
            return arrayList;
        } catch (PackageManager.NameNotFoundException e) {
            String strValueOf = String.valueOf(getComponentName());
            StringBuilder sb = new StringBuilder(strValueOf.length() + 25);
            sb.append("Cannot locate ourselves: ");
            sb.append(strValueOf);
            Log.wtf(TAG, sb.toString(), e);
            String strValueOf2 = String.valueOf(getComponentName());
            StringBuilder sb2 = new StringBuilder(strValueOf2.length() + 25);
            sb2.append("Cannot locate ourselves: ");
            sb2.append(strValueOf2);
            throw new IllegalStateException(sb2.toString(), e);
        }
    }

    private boolean isHostingProcess(String str, ProcSummary procSummary) {
        int length = str.length();
        int length2 = procSummary.cmdline.length();
        if (length == length2) {
            return str.equals(procSummary.cmdline);
        }
        if (length < length2 || !str.startsWith(procSummary.cmdline) || !str.endsWith(procSummary.name)) {
            return false;
        }
        String strValueOf = String.valueOf(procSummary);
        StringBuilder sb = new StringBuilder(strValueOf.length() + 165 + str.length());
        sb.append("Use smaller processNames in AndroidManifest.xml. Long names are truncated. This process's cmdline is a prefix of the processName and suffix of comm - assuming: ");
        sb.append(strValueOf);
        sb.append(" is: ");
        sb.append(str);
        Log.w(TAG, sb.toString());
        return true;
    }

    private boolean isOriginalInstrumentationProcess() {
        Boolean boolValueOf = this.isOriginalInstr;
        if (boolValueOf == null) {
            boolValueOf = Boolean.valueOf(isOriginalUncached());
            this.isOriginalInstr = boolValueOf;
        }
        return boolValueOf.booleanValue();
    }

    private boolean isOriginalUncached() {
        if (Build.VERSION.SDK_INT < 26) {
            return true;
        }
        List<String> targetProcessValues = getTargetProcessValues();
        if (targetProcessValues.isEmpty()) {
            return true;
        }
        boolean zEquals = "*".equals(targetProcessValues.get(0));
        if (targetProcessValues.size() == 1 && !zEquals) {
            return true;
        }
        try {
            ProcSummary procSummarySummarize = ProcSummary.summarize("self");
            if (!zEquals) {
                return isHostingProcess(targetProcessValues.get(0), procSummarySummarize);
            }
            String packageName = getTargetContext().getApplicationInfo().processName;
            if (packageName == null) {
                packageName = getTargetContext().getPackageName();
            }
            return isHostingProcess(packageName, procSummarySummarize);
        } catch (ProcSummary.SummaryException e) {
            Log.w(TAG, "Could not list apps for this user, running in sandbox? Assuming primary", e);
            return false;
        }
    }

    private void logUncaughtExceptions() {
        this.standardHandler = Thread.currentThread().getUncaughtExceptionHandler();
        Thread.currentThread().setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() { // from class: androidx.test.runner.MonitoringInstrumentation.3
            @Override // java.lang.Thread.UncaughtExceptionHandler
            public void uncaughtException(Thread thread, Throwable th) {
                MonitoringInstrumentation.this.onException(thread, th);
                if (MonitoringInstrumentation.this.standardHandler != null) {
                    Log.w(MonitoringInstrumentation.TAG, String.format("Invoking uncaught exception handler %s (a %s)", MonitoringInstrumentation.this.standardHandler, MonitoringInstrumentation.this.standardHandler.getClass()));
                    MonitoringInstrumentation.this.standardHandler.uncaughtException(thread, th);
                } else {
                    String strValueOf = String.valueOf(thread.getName());
                    Log.w(MonitoringInstrumentation.TAG, strValueOf.length() != 0 ? "Invoking uncaught exception handler for thread: ".concat(strValueOf) : new String("Invoking uncaught exception handler for thread: "));
                    thread.getThreadGroup().uncaughtException(thread, th);
                }
                if ("robolectric".equals(Build.FINGERPRINT) || !Looper.getMainLooper().getThread().equals(thread)) {
                    return;
                }
                Log.e(MonitoringInstrumentation.TAG, "The main thread has died and the handlers didn't care, exiting");
                System.exit(-10);
            }
        });
    }

    private void setupDexmakerClassloader() {
        Boolean bool = Boolean.TRUE;
        if (bool.equals(this.isDexmakerClassLoaderInitialized.get())) {
            return;
        }
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        ClassLoader classLoader = getTargetContext().getClassLoader();
        if (contextClassLoader != classLoader) {
            Log.i(TAG, String.format("Setting context classloader to '%s', Original: '%s'", classLoader, contextClassLoader));
            Thread.currentThread().setContextClassLoader(classLoader);
        }
        this.isDexmakerClassLoaderInitialized.set(bool);
    }

    private Instrumentation.ActivityResult stubResultFor(Intent intent) {
        if (!IntentStubberRegistry.isLoaded()) {
            return null;
        }
        if (Looper.myLooper() == Looper.getMainLooper()) {
            return IntentStubberRegistry.getInstance().getActivityResultForIntent(intent);
        }
        FutureTask futureTask = new FutureTask(new StubResultCallable(intent));
        runOnMainSync(futureTask);
        try {
            return (Instrumentation.ActivityResult) futureTask.get();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        } catch (ExecutionException e2) {
            throw new RuntimeException(String.format("Could not retrieve stub result for intent %s", intent), e2);
        }
    }

    private void tryLoadingJsBridge(final String str) {
        Objects.requireNonNull(str, "JsBridge class name cannot be null!");
        runOnMainSync(new Runnable() { // from class: androidx.test.runner.MonitoringInstrumentation.5
            @Override // java.lang.Runnable
            public void run() {
                try {
                    Class.forName(str).getDeclaredMethod("installBridge", new Class[0]).invoke(null, new Object[0]);
                    MonitoringInstrumentation.this.isJsBridgeLoaded.set(true);
                } catch (ClassNotFoundException | NoSuchMethodException unused) {
                    Log.i(MonitoringInstrumentation.TAG, "No JSBridge.");
                } catch (IllegalAccessException e) {
                    e = e;
                    throw new RuntimeException("JSbridge is available at runtime, but calling it failed.", e);
                } catch (InvocationTargetException e2) {
                    e = e2;
                    throw new RuntimeException("JSbridge is available at runtime, but calling it failed.", e);
                }
            }
        });
    }

    @Override // android.app.Instrumentation
    public void callActivityOnCreate(Activity activity, Bundle bundle) {
        this.lifecycleMonitor.signalLifecycleChange(Stage.PRE_ON_CREATE, activity);
        super.callActivityOnCreate(activity, bundle);
        this.lifecycleMonitor.signalLifecycleChange(Stage.CREATED, activity);
    }

    @Override // android.app.Instrumentation
    public void callActivityOnDestroy(Activity activity) {
        super.callActivityOnDestroy(activity);
        this.lifecycleMonitor.signalLifecycleChange(Stage.DESTROYED, activity);
    }

    @Override // android.app.Instrumentation
    public void callActivityOnPause(Activity activity) {
        super.callActivityOnPause(activity);
        this.lifecycleMonitor.signalLifecycleChange(Stage.PAUSED, activity);
    }

    @Override // android.app.Instrumentation
    public void callActivityOnRestart(Activity activity) {
        super.callActivityOnRestart(activity);
        this.lifecycleMonitor.signalLifecycleChange(Stage.RESTARTED, activity);
    }

    @Override // android.app.Instrumentation
    public void callActivityOnResume(Activity activity) {
        super.callActivityOnResume(activity);
        this.lifecycleMonitor.signalLifecycleChange(Stage.RESUMED, activity);
    }

    @Override // android.app.Instrumentation
    public void callActivityOnStart(Activity activity) {
        this.startedActivityCounter.incrementAndGet();
        try {
            super.callActivityOnStart(activity);
            this.lifecycleMonitor.signalLifecycleChange(Stage.STARTED, activity);
        } catch (RuntimeException e) {
            this.startedActivityCounter.decrementAndGet();
            throw e;
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
    public void callApplicationOnCreate(Application application) {
        this.applicationMonitor.signalLifecycleChange(application, ApplicationStage.PRE_ON_CREATE);
        super.callApplicationOnCreate(application);
        this.applicationMonitor.signalLifecycleChange(application, ApplicationStage.CREATED);
    }

    public void dumpThreadStateToOutputs(String str) {
        Log.e("THREAD_STATE", getThreadState());
    }

    public void execStartActivities(Context context, IBinder iBinder, IBinder iBinder2, Activity activity, Intent[] intentArr, Bundle bundle) {
        Log.d(TAG, "execStartActivities(context, ibinder, ibinder, activity, intent[], bundle)");
        for (Intent intent : intentArr) {
            execStartActivity(context, iBinder, iBinder2, activity, intent, -1, bundle);
        }
    }

    public Instrumentation.ActivityResult execStartActivity(Context context, IBinder iBinder, IBinder iBinder2, Activity activity, Intent intent, int i) {
        this.intentMonitor.signalIntent(intent);
        Instrumentation.ActivityResult activityResultStubResultFor = stubResultFor(intent);
        if (activityResultStubResultFor == null) {
            return super.execStartActivity(context, iBinder, iBinder2, activity, intent, i);
        }
        Log.i(TAG, String.format("Stubbing intent %s", intent));
        return activityResultStubResultFor;
    }

    @Override // android.app.Instrumentation
    public void finish(int i, Bundle bundle) {
        if (this.finished) {
            Log.w(TAG, "finish called 2x!");
            return;
        }
        this.finished = true;
        if (shouldWaitForActivitiesToComplete()) {
            this.handlerForMainLooper.post(new ActivityFinisher());
            long jCurrentTimeMillis = System.currentTimeMillis();
            waitForActivitiesToComplete();
            Log.i(TAG, String.format("waitForActivitiesToComplete() took: %sms", Long.valueOf(System.currentTimeMillis() - jCurrentTimeMillis)));
        }
        ActivityLifecycleMonitorRegistry.registerInstance(null);
        restoreUncaughtExceptionHandler();
        super.finish(i, bundle);
    }

    public String getThreadState() {
        Set<Map.Entry<Thread, StackTraceElement[]>> setEntrySet = Thread.getAllStackTraces().entrySet();
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Thread, StackTraceElement[]> entry : setEntrySet) {
            StringBuilder sb2 = new StringBuilder("  ");
            sb2.append(entry.getKey());
            sb2.append(BydAdbHelper.COMMAND_LINE_END);
            for (StackTraceElement stackTraceElement : entry.getValue()) {
                sb2.append("    ");
                sb2.append(stackTraceElement.toString());
                sb2.append(BydAdbHelper.COMMAND_LINE_END);
            }
            sb2.append(BydAdbHelper.COMMAND_LINE_END);
            sb.append(sb2.toString());
        }
        return sb.toString();
    }

    public void installMultidex() {
        if (Build.VERSION.SDK_INT < 21) {
            try {
                try {
                    Class<?> cls = Class.forName("androidx.multidex.MultiDex");
                    try {
                        cls.getDeclaredMethod("installInstrumentation", Context.class, Context.class).invoke(null, getContext(), getTargetContext());
                    } catch (NoSuchMethodException unused) {
                        installOldMultiDex(cls);
                    }
                } catch (NoSuchMethodException e) {
                    Log.i(TAG, "No multidex.", e);
                }
            } catch (ClassNotFoundException unused2) {
                Log.i(TAG, "No multidex.");
            } catch (IllegalAccessException e2) {
                throw new RuntimeException("multidex is available at runtime, but calling it failed.", e2);
            } catch (InvocationTargetException e3) {
                throw new RuntimeException("multidex is available at runtime, but calling it failed.", e3);
            }
        }
    }

    public void installOldMultiDex(Class<?> cls) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        cls.getDeclaredMethod("install", Context.class).invoke(null, getTargetContext());
    }

    public void interceptActivityUsing(InterceptingActivityFactory interceptingActivityFactory) {
        Checks.checkNotNull(interceptingActivityFactory);
        this.interceptingActivityFactory = interceptingActivityFactory;
    }

    @Deprecated
    public boolean isPrimaryInstrProcess(@Nullable String str) {
        return isPrimaryInstrProcess();
    }

    @Override // android.app.Instrumentation
    public Activity newActivity(Class<?> cls, Context context, IBinder iBinder, Application application, Intent intent, ActivityInfo activityInfo, CharSequence charSequence, Activity activity, String str, Object obj) throws IllegalAccessException, InstantiationException {
        String name = cls.getPackage().getName();
        String packageName = context.getPackageName();
        ComponentName component = intent.getComponent();
        if (!packageName.equals(component.getPackageName()) && name.equals(component.getPackageName())) {
            intent.setComponent(new ComponentName(packageName, component.getClassName()));
        }
        return super.newActivity(cls, context, iBinder, application, intent, activityInfo, charSequence, activity, str, obj);
    }

    @Override // android.app.Instrumentation
    public void onCreate(Bundle bundle) {
        Log.i(TAG, "Instrumentation started!");
        logUncaughtExceptions();
        installMultidex();
        InstrumentationRegistry.registerInstance(this, bundle);
        androidx.test.InstrumentationRegistry.registerInstance(this, bundle);
        ActivityLifecycleMonitorRegistry.registerInstance(this.lifecycleMonitor);
        ApplicationLifecycleMonitorRegistry.registerInstance(this.applicationMonitor);
        IntentMonitorRegistry.registerInstance(this.intentMonitor);
        this.handlerForMainLooper = new Handler(Looper.getMainLooper());
        this.executorService = new ThreadPoolExecutor(0, ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED, 0L, TimeUnit.SECONDS, new SynchronousQueue(), new ThreadFactory(this) { // from class: androidx.test.runner.MonitoringInstrumentation.2
            @Override // java.util.concurrent.ThreadFactory
            public Thread newThread(Runnable runnable) {
                Thread threadNewThread = Executors.defaultThreadFactory().newThread(runnable);
                threadNewThread.setName(MonitoringInstrumentation.class.getSimpleName());
                return threadNewThread;
            }
        });
        Looper.myQueue().addIdleHandler(this.idleHandler);
        super.onCreate(bundle);
        specifyDexMakerCacheProperty();
        setupDexmakerClassloader();
        useDefaultInterceptingActivityFactory();
    }

    @Override // android.app.Instrumentation
    public void onDestroy() {
        Log.i(TAG, "Instrumentation Finished!");
        Looper.myQueue().removeIdleHandler(this.idleHandler);
        InstrumentationConnection.getInstance().terminate();
        super.onDestroy();
    }

    @Override // android.app.Instrumentation
    public boolean onException(Object obj, Throwable th) {
        Log.e(TAG, String.format("Exception encountered by: %s. Dumping thread state to outputs and pining for the fjords.", obj), th);
        dumpThreadStateToOutputs("ThreadState-onException.txt");
        Log.e(TAG, "Dying now...");
        return super.onException(obj, th);
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

    public void restoreUncaughtExceptionHandler() {
        Thread.currentThread().setUncaughtExceptionHandler(this.standardHandler);
    }

    @Override // android.app.Instrumentation
    public void runOnMainSync(Runnable runnable) {
        FutureTask futureTask = new FutureTask(runnable, null);
        super.runOnMainSync(futureTask);
        try {
            futureTask.get();
        } catch (InterruptedException e) {
            Log.e(TAG, "An execution is interrupted", e);
            throw new RuntimeException(e);
        } catch (ExecutionException e2) {
            Log.e(TAG, "An exception is thrown from the runnable posted to the main thread", e2);
            throw new RuntimeException(e2.getCause());
        }
    }

    public final void setJsBridgeClassName(String str) {
        Objects.requireNonNull(str, "JsBridge class name cannot be null!");
        if (this.isJsBridgeLoaded.get()) {
            throw new IllegalStateException("JsBridge is already loaded!");
        }
        this.jsBridgeClassName = str;
    }

    public boolean shouldWaitForActivitiesToComplete() {
        return Boolean.parseBoolean(InstrumentationRegistry.getArguments().getString("waitForActivitiesToComplete", "true"));
    }

    public void specifyDexMakerCacheProperty() {
        System.getProperties().put("dexmaker.dexcache", getTargetContext().getDir("dxmaker_cache", 0).getAbsolutePath());
    }

    @Override // android.app.Instrumentation
    public Activity startActivitySync(final Intent intent) {
        Checks.checkNotMainThread();
        long j = this.lastIdleTime.get();
        if (this.anActivityHasBeenLaunched.compareAndSet(false, true)) {
            intent.addFlags(67108864);
        }
        Future futureSubmit = this.executorService.submit(new Callable<Activity>() { // from class: androidx.test.runner.MonitoringInstrumentation.4
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            public Activity call() {
                return MonitoringInstrumentation.super.startActivitySync(intent);
            }
        });
        try {
            return (Activity) futureSubmit.get(ActivityLifecycleTimeout.getMillis(), TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("interrupted", e);
        } catch (ExecutionException e2) {
            throw new RuntimeException("Could not launch activity", e2.getCause());
        } catch (TimeoutException unused) {
            dumpThreadStateToOutputs("ThreadState-startActivityTimeout.txt");
            futureSubmit.cancel(true);
            throw new RuntimeException(String.format("Could not launch intent %s within %s milliseconds. Perhaps the main thread has not gone idle within a reasonable amount of time? There could be an animation or something constantly repainting the screen. Or the activity is doing network calls on creation? See the threaddump logs. For your reference the last time the event queue was idle before your activity launch request was %s and now the last time the queue went idle was: %s. If these numbers are the same your activity might be hogging the event queue.", intent, Long.valueOf(ActivityLifecycleTimeout.getMillis()), Long.valueOf(j), Long.valueOf(this.lastIdleTime.get())));
        }
    }

    public void useDefaultInterceptingActivityFactory() {
        this.interceptingActivityFactory = new DefaultInterceptingActivityFactory();
    }

    public void waitForActivitiesToComplete() {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            throw new IllegalStateException("Cannot be called from main thread!");
        }
        long jCurrentTimeMillis = System.currentTimeMillis() + MILLIS_TO_WAIT_FOR_ACTIVITY_TO_STOP;
        int i = this.startedActivityCounter.get();
        while (i > 0 && System.currentTimeMillis() < jCurrentTimeMillis) {
            try {
                StringBuilder sb = new StringBuilder(37);
                sb.append("Unstopped activity count: ");
                sb.append(i);
                Log.i(TAG, sb.toString());
                Thread.sleep(MILLIS_TO_POLL_FOR_ACTIVITY_STOP);
                i = this.startedActivityCounter.get();
            } catch (InterruptedException e) {
                Log.i(TAG, "Abandoning activity wait due to interruption.", e);
            }
        }
        if (i > 0) {
            dumpThreadStateToOutputs("ThreadState-unstopped.txt");
            Log.w(TAG, String.format("Still %s activities active after waiting %s ms.", Integer.valueOf(i), Long.valueOf(MILLIS_TO_WAIT_FOR_ACTIVITY_TO_STOP)));
        }
    }

    public final boolean isPrimaryInstrProcess() {
        return isOriginalInstrumentationProcess();
    }

    public Instrumentation.ActivityResult execStartActivity(Context context, IBinder iBinder, IBinder iBinder2, Activity activity, Intent intent, int i, Bundle bundle) {
        this.intentMonitor.signalIntent(intent);
        Instrumentation.ActivityResult activityResultStubResultFor = stubResultFor(intent);
        if (activityResultStubResultFor != null) {
            Log.i(TAG, String.format("Stubbing intent %s", intent));
            return activityResultStubResultFor;
        }
        return super.execStartActivity(context, iBinder, iBinder2, activity, intent, i, bundle);
    }

    public Instrumentation.ActivityResult execStartActivity(Context context, IBinder iBinder, IBinder iBinder2, String str, Intent intent, int i, Bundle bundle) {
        this.intentMonitor.signalIntent(intent);
        Instrumentation.ActivityResult activityResultStubResultFor = stubResultFor(intent);
        if (activityResultStubResultFor != null) {
            Log.i(TAG, String.format("Stubbing intent %s", intent));
            return activityResultStubResultFor;
        }
        return super.execStartActivity(context, iBinder, iBinder2, str, intent, i, bundle);
    }

    @Override // android.app.Instrumentation
    public Activity newActivity(ClassLoader classLoader, String str, Intent intent) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        if (this.interceptingActivityFactory.shouldIntercept(classLoader, str, intent)) {
            return this.interceptingActivityFactory.create(classLoader, str, intent);
        }
        return super.newActivity(classLoader, str, intent);
    }

    public Instrumentation.ActivityResult execStartActivity(Context context, IBinder iBinder, IBinder iBinder2, Activity activity, Intent intent, int i, Bundle bundle, UserHandle userHandle) {
        return super.execStartActivity(context, iBinder, iBinder2, activity, intent, i, bundle, userHandle);
    }

    public Instrumentation.ActivityResult execStartActivity(Context context, IBinder iBinder, IBinder iBinder2, Fragment fragment, Intent intent, int i, Bundle bundle) {
        Log.d(TAG, "execStartActivity(context, IBinder, IBinder, Fragment, Intent, int, Bundle)");
        this.intentMonitor.signalIntent(intent);
        Instrumentation.ActivityResult activityResultStubResultFor = stubResultFor(intent);
        if (activityResultStubResultFor != null) {
            Log.i(TAG, String.format("Stubbing intent %s", intent));
            return activityResultStubResultFor;
        }
        return super.execStartActivity(context, iBinder, iBinder2, fragment, intent, i, bundle);
    }
}
