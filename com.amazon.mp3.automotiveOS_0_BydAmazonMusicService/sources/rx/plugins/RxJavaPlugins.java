package rx.plugins;

import androidx.compose.runtime.CompositionImpl$$ExternalSyntheticBackportWithForwarding0;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes8.dex */
public class RxJavaPlugins {
    private static final RxJavaPlugins INSTANCE = new RxJavaPlugins();
    static final RxJavaErrorHandler DEFAULT_ERROR_HANDLER = new RxJavaErrorHandler() { // from class: rx.plugins.RxJavaPlugins.1
    };
    private final AtomicReference<RxJavaErrorHandler> errorHandler = new AtomicReference<>();
    private final AtomicReference<RxJavaObservableExecutionHook> observableExecutionHook = new AtomicReference<>();
    private final AtomicReference<RxJavaSingleExecutionHook> singleExecutionHook = new AtomicReference<>();
    private final AtomicReference<RxJavaCompletableExecutionHook> completableExecutionHook = new AtomicReference<>();
    private final AtomicReference<RxJavaSchedulersHook> schedulersHook = new AtomicReference<>();

    @Deprecated
    public static RxJavaPlugins getInstance() {
        return INSTANCE;
    }

    RxJavaPlugins() {
    }

    public RxJavaErrorHandler getErrorHandler() {
        if (this.errorHandler.get() == null) {
            Object pluginImplementationViaProperty = getPluginImplementationViaProperty(RxJavaErrorHandler.class, getSystemPropertiesSafe());
            if (pluginImplementationViaProperty == null) {
                CompositionImpl$$ExternalSyntheticBackportWithForwarding0.m(this.errorHandler, null, DEFAULT_ERROR_HANDLER);
            } else {
                CompositionImpl$$ExternalSyntheticBackportWithForwarding0.m(this.errorHandler, null, (RxJavaErrorHandler) pluginImplementationViaProperty);
            }
        }
        return this.errorHandler.get();
    }

    public RxJavaObservableExecutionHook getObservableExecutionHook() {
        if (this.observableExecutionHook.get() == null) {
            Object pluginImplementationViaProperty = getPluginImplementationViaProperty(RxJavaObservableExecutionHook.class, getSystemPropertiesSafe());
            if (pluginImplementationViaProperty == null) {
                CompositionImpl$$ExternalSyntheticBackportWithForwarding0.m(this.observableExecutionHook, null, RxJavaObservableExecutionHookDefault.getInstance());
            } else {
                CompositionImpl$$ExternalSyntheticBackportWithForwarding0.m(this.observableExecutionHook, null, (RxJavaObservableExecutionHook) pluginImplementationViaProperty);
            }
        }
        return this.observableExecutionHook.get();
    }

    public RxJavaSingleExecutionHook getSingleExecutionHook() {
        if (this.singleExecutionHook.get() == null) {
            Object pluginImplementationViaProperty = getPluginImplementationViaProperty(RxJavaSingleExecutionHook.class, getSystemPropertiesSafe());
            if (pluginImplementationViaProperty == null) {
                CompositionImpl$$ExternalSyntheticBackportWithForwarding0.m(this.singleExecutionHook, null, RxJavaSingleExecutionHookDefault.getInstance());
            } else {
                CompositionImpl$$ExternalSyntheticBackportWithForwarding0.m(this.singleExecutionHook, null, (RxJavaSingleExecutionHook) pluginImplementationViaProperty);
            }
        }
        return this.singleExecutionHook.get();
    }

    public RxJavaCompletableExecutionHook getCompletableExecutionHook() {
        if (this.completableExecutionHook.get() == null) {
            Object pluginImplementationViaProperty = getPluginImplementationViaProperty(RxJavaCompletableExecutionHook.class, getSystemPropertiesSafe());
            if (pluginImplementationViaProperty == null) {
                CompositionImpl$$ExternalSyntheticBackportWithForwarding0.m(this.completableExecutionHook, null, new RxJavaCompletableExecutionHook() { // from class: rx.plugins.RxJavaPlugins.2
                });
            } else {
                CompositionImpl$$ExternalSyntheticBackportWithForwarding0.m(this.completableExecutionHook, null, (RxJavaCompletableExecutionHook) pluginImplementationViaProperty);
            }
        }
        return this.completableExecutionHook.get();
    }

    static Properties getSystemPropertiesSafe() {
        try {
            return System.getProperties();
        } catch (SecurityException unused) {
            return new Properties();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x0060, code lost:
    
        r2 = "rxjava.plugin." + r7.substring(0, r7.length() - 6).substring(14) + ".impl";
        r1 = r10.getProperty(r2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0087, code lost:
    
        if (r1 == null) goto L17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x00a8, code lost:
    
        throw new java.lang.IllegalStateException("Implementing class declaration for " + r0 + " missing: " + r2);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    static java.lang.Object getPluginImplementationViaProperty(java.lang.Class<?> r9, java.util.Properties r10) {
        /*
            Method dump skipped, instruction units count: 306
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: rx.plugins.RxJavaPlugins.getPluginImplementationViaProperty(java.lang.Class, java.util.Properties):java.lang.Object");
    }

    public RxJavaSchedulersHook getSchedulersHook() {
        if (this.schedulersHook.get() == null) {
            Object pluginImplementationViaProperty = getPluginImplementationViaProperty(RxJavaSchedulersHook.class, getSystemPropertiesSafe());
            if (pluginImplementationViaProperty == null) {
                CompositionImpl$$ExternalSyntheticBackportWithForwarding0.m(this.schedulersHook, null, RxJavaSchedulersHook.getDefaultInstance());
            } else {
                CompositionImpl$$ExternalSyntheticBackportWithForwarding0.m(this.schedulersHook, null, (RxJavaSchedulersHook) pluginImplementationViaProperty);
            }
        }
        return this.schedulersHook.get();
    }
}
