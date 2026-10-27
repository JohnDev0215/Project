package androidx.test.runner.lifecycle;

import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes5.dex */
public final class ApplicationLifecycleMonitorRegistry {
    private static final AtomicReference<ApplicationLifecycleMonitor> lifecycleMonitor = new AtomicReference<>(null);

    private ApplicationLifecycleMonitorRegistry() {
    }

    public static ApplicationLifecycleMonitor getInstance() {
        ApplicationLifecycleMonitor instance = lifecycleMonitor.get();
        if (instance == null) {
            throw new IllegalStateException("No lifecycle monitor registered! Are you running under an Instrumentation which registers lifecycle monitors?");
        }
        return instance;
    }

    public static void registerInstance(ApplicationLifecycleMonitor monitor) {
        lifecycleMonitor.set(monitor);
    }
}
