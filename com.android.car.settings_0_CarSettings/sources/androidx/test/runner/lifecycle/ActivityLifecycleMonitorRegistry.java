package androidx.test.runner.lifecycle;

import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes5.dex */
public final class ActivityLifecycleMonitorRegistry {
    private static final AtomicReference<ActivityLifecycleMonitor> lifecycleMonitor = new AtomicReference<>(null);

    private ActivityLifecycleMonitorRegistry() {
    }

    public static ActivityLifecycleMonitor getInstance() {
        ActivityLifecycleMonitor instance = lifecycleMonitor.get();
        if (instance == null) {
            throw new IllegalStateException("No lifecycle monitor registered! Are you running under an Instrumentation which registers lifecycle monitors?");
        }
        return instance;
    }

    public static void registerInstance(ActivityLifecycleMonitor monitor) {
        lifecycleMonitor.set(monitor);
    }
}
