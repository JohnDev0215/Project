package androidx.test.runner.lifecycle;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public interface ApplicationLifecycleMonitor {
    void addLifecycleCallback(ApplicationLifecycleCallback applicationLifecycleCallback);

    void removeLifecycleCallback(ApplicationLifecycleCallback applicationLifecycleCallback);
}
