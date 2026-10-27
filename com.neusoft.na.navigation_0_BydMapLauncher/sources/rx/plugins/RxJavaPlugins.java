package rx.plugins;

import androidx.lifecycle.LifecycleKt$$ExternalSyntheticBackportWithForwarding0;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes4.dex */
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

    public void reset() {
        RxJavaPlugins rxJavaPlugins = INSTANCE;
        rxJavaPlugins.errorHandler.set(null);
        rxJavaPlugins.observableExecutionHook.set(null);
        rxJavaPlugins.singleExecutionHook.set(null);
        rxJavaPlugins.completableExecutionHook.set(null);
        rxJavaPlugins.schedulersHook.set(null);
    }

    public RxJavaErrorHandler getErrorHandler() {
        if (this.errorHandler.get() == null) {
            Object pluginImplementationViaProperty = getPluginImplementationViaProperty(RxJavaErrorHandler.class, getSystemPropertiesSafe());
            if (pluginImplementationViaProperty == null) {
                LifecycleKt$$ExternalSyntheticBackportWithForwarding0.m(this.errorHandler, null, DEFAULT_ERROR_HANDLER);
            } else {
                LifecycleKt$$ExternalSyntheticBackportWithForwarding0.m(this.errorHandler, null, (RxJavaErrorHandler) pluginImplementationViaProperty);
            }
        }
        return this.errorHandler.get();
    }

    public void registerErrorHandler(RxJavaErrorHandler rxJavaErrorHandler) {
        if (!LifecycleKt$$ExternalSyntheticBackportWithForwarding0.m(this.errorHandler, null, rxJavaErrorHandler)) {
            throw new IllegalStateException("Another strategy was already registered: " + this.errorHandler.get());
        }
    }

    public RxJavaObservableExecutionHook getObservableExecutionHook() {
        if (this.observableExecutionHook.get() == null) {
            Object pluginImplementationViaProperty = getPluginImplementationViaProperty(RxJavaObservableExecutionHook.class, getSystemPropertiesSafe());
            if (pluginImplementationViaProperty == null) {
                LifecycleKt$$ExternalSyntheticBackportWithForwarding0.m(this.observableExecutionHook, null, RxJavaObservableExecutionHookDefault.getInstance());
            } else {
                LifecycleKt$$ExternalSyntheticBackportWithForwarding0.m(this.observableExecutionHook, null, (RxJavaObservableExecutionHook) pluginImplementationViaProperty);
            }
        }
        return this.observableExecutionHook.get();
    }

    public void registerObservableExecutionHook(RxJavaObservableExecutionHook rxJavaObservableExecutionHook) {
        if (!LifecycleKt$$ExternalSyntheticBackportWithForwarding0.m(this.observableExecutionHook, null, rxJavaObservableExecutionHook)) {
            throw new IllegalStateException("Another strategy was already registered: " + this.observableExecutionHook.get());
        }
    }

    public RxJavaSingleExecutionHook getSingleExecutionHook() {
        if (this.singleExecutionHook.get() == null) {
            Object pluginImplementationViaProperty = getPluginImplementationViaProperty(RxJavaSingleExecutionHook.class, getSystemPropertiesSafe());
            if (pluginImplementationViaProperty == null) {
                LifecycleKt$$ExternalSyntheticBackportWithForwarding0.m(this.singleExecutionHook, null, RxJavaSingleExecutionHookDefault.getInstance());
            } else {
                LifecycleKt$$ExternalSyntheticBackportWithForwarding0.m(this.singleExecutionHook, null, (RxJavaSingleExecutionHook) pluginImplementationViaProperty);
            }
        }
        return this.singleExecutionHook.get();
    }

    public void registerSingleExecutionHook(RxJavaSingleExecutionHook rxJavaSingleExecutionHook) {
        if (!LifecycleKt$$ExternalSyntheticBackportWithForwarding0.m(this.singleExecutionHook, null, rxJavaSingleExecutionHook)) {
            throw new IllegalStateException("Another strategy was already registered: " + this.singleExecutionHook.get());
        }
    }

    public RxJavaCompletableExecutionHook getCompletableExecutionHook() {
        if (this.completableExecutionHook.get() == null) {
            Object pluginImplementationViaProperty = getPluginImplementationViaProperty(RxJavaCompletableExecutionHook.class, getSystemPropertiesSafe());
            if (pluginImplementationViaProperty == null) {
                LifecycleKt$$ExternalSyntheticBackportWithForwarding0.m(this.completableExecutionHook, null, new RxJavaCompletableExecutionHook() { // from class: rx.plugins.RxJavaPlugins.2
                });
            } else {
                LifecycleKt$$ExternalSyntheticBackportWithForwarding0.m(this.completableExecutionHook, null, (RxJavaCompletableExecutionHook) pluginImplementationViaProperty);
            }
        }
        return this.completableExecutionHook.get();
    }

    public void registerCompletableExecutionHook(RxJavaCompletableExecutionHook rxJavaCompletableExecutionHook) {
        if (!LifecycleKt$$ExternalSyntheticBackportWithForwarding0.m(this.completableExecutionHook, null, rxJavaCompletableExecutionHook)) {
            throw new IllegalStateException("Another strategy was already registered: " + this.singleExecutionHook.get());
        }
    }

    static Properties getSystemPropertiesSafe() {
        try {
            return System.getProperties();
        } catch (SecurityException unused) {
            return new Properties();
        }
    }

    static Object getPluginImplementationViaProperty(Class<?> cls, Properties properties) {
        SecurityException e;
        String property;
        Properties properties2 = (Properties) properties.clone();
        String simpleName = cls.getSimpleName();
        String property2 = properties2.getProperty("rxjava.plugin." + simpleName + ".implementation");
        if (property2 == null) {
            try {
            } catch (SecurityException e2) {
                e = e2;
                property = property2;
            }
            for (Map.Entry entry : properties2.entrySet()) {
                String string = entry.getKey().toString();
                if (string.startsWith("rxjava.plugin.") && string.endsWith(".class") && simpleName.equals(entry.getValue().toString())) {
                    String str = "rxjava.plugin." + string.substring(0, string.length() - 6).substring(14) + ".impl";
                    property = properties2.getProperty(str);
                    if (property == null) {
                        try {
                            throw new IllegalStateException("Implementing class declaration for " + simpleName + " missing: " + str);
                        } catch (SecurityException e3) {
                            e = e3;
                        }
                    }
                    property2 = property;
                }
                e.printStackTrace();
                property2 = property;
            }
        }
        if (property2 == null) {
            return null;
        }
        try {
            return Class.forName(property2).asSubclass(cls).newInstance();
        } catch (ClassCastException e4) {
            throw new IllegalStateException(simpleName + " implementation is not an instance of " + simpleName + ": " + property2, e4);
        } catch (ClassNotFoundException e5) {
            throw new IllegalStateException(simpleName + " implementation class not found: " + property2, e5);
        } catch (IllegalAccessException e6) {
            throw new IllegalStateException(simpleName + " implementation not able to be accessed: " + property2, e6);
        } catch (InstantiationException e7) {
            throw new IllegalStateException(simpleName + " implementation not able to be instantiated: " + property2, e7);
        }
    }

    public RxJavaSchedulersHook getSchedulersHook() {
        if (this.schedulersHook.get() == null) {
            Object pluginImplementationViaProperty = getPluginImplementationViaProperty(RxJavaSchedulersHook.class, getSystemPropertiesSafe());
            if (pluginImplementationViaProperty == null) {
                LifecycleKt$$ExternalSyntheticBackportWithForwarding0.m(this.schedulersHook, null, RxJavaSchedulersHook.getDefaultInstance());
            } else {
                LifecycleKt$$ExternalSyntheticBackportWithForwarding0.m(this.schedulersHook, null, (RxJavaSchedulersHook) pluginImplementationViaProperty);
            }
        }
        return this.schedulersHook.get();
    }

    public void registerSchedulersHook(RxJavaSchedulersHook rxJavaSchedulersHook) {
        if (!LifecycleKt$$ExternalSyntheticBackportWithForwarding0.m(this.schedulersHook, null, rxJavaSchedulersHook)) {
            throw new IllegalStateException("Another strategy was already registered: " + this.schedulersHook.get());
        }
    }
}
