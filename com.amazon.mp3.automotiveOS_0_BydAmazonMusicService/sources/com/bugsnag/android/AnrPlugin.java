package com.bugsnag.android;

import android.os.Handler;
import android.os.Looper;
import com.amazon.music.find.metrics.SearchMetricsServiceDefault;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.TypeCastException;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: AnrPlugin.kt */
/* JADX INFO: loaded from: classes5.dex */
@kotlin.Metadata(bv = {1, 0, 3}, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0003\b\u0000\u0018\u0000 \u001d2\u00020\u0001:\u0001\u001dB\u0005¢\u0006\u0002\u0010\u0002J\t\u0010\u000b\u001a\u00020\fH\u0082 J\t\u0010\r\u001a\u00020\fH\u0082 J\b\u0010\u000e\u001a\u00020\fH\u0002J\u0010\u0010\u000f\u001a\u00020\f2\u0006\u0010\u0003\u001a\u00020\u0004H\u0016J\u0016\u0010\u0010\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0002J\u0016\u0010\u0014\u001a\u00020\f2\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00170\u0016H\u0002J\u0010\u0010\u0018\u001a\u00020\f2\u0006\u0010\u0003\u001a\u00020\u0004H\u0002J\u0011\u0010\u0019\u001a\u00020\f2\u0006\u0010\u001a\u001a\u00020\u001bH\u0082 J\b\u0010\u001c\u001a\u00020\fH\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001e"}, d2 = {"Lcom/bugsnag/android/AnrPlugin;", "Lcom/bugsnag/android/Plugin;", "()V", SearchMetricsServiceDefault.CONTENT_SRC_CLIENT, "Lcom/bugsnag/android/Client;", "collector", "Lcom/bugsnag/android/AnrDetailsCollector;", "libraryLoader", "Lcom/bugsnag/android/LibraryLoader;", "oneTimeSetupPerformed", "Ljava/util/concurrent/atomic/AtomicBoolean;", "disableAnrReporting", "", "enableAnrReporting", "initNativePlugin", "load", "loadClass", "Ljava/lang/Class;", "clz", "", "notifyAnrDetected", "nativeTrace", "", "Lcom/bugsnag/android/NativeStackframe;", "performOneTimeSetup", "setUnwindFunction", "unwindFunction", "", "unload", "Companion", "bugsnag-plugin-android-anr_release"}, k = 1, mv = {1, 4, 2})
public final class AnrPlugin implements Plugin {
    private static final String ANR_ERROR_CLASS = "ANR";
    private static final String ANR_ERROR_MSG = "Application did not respond to UI input";

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String LOAD_ERR_MSG = "Native library could not be linked. Bugsnag will not report ANRs. See https://docs.bugsnag.com/platforms/android/anr-link-errors";
    private Client client;
    private final LibraryLoader libraryLoader = new LibraryLoader();
    private final AtomicBoolean oneTimeSetupPerformed = new AtomicBoolean(false);
    private final AnrDetailsCollector collector = new AnrDetailsCollector();

    private final native void disableAnrReporting();

    private final native void enableAnrReporting();

    private final native void setUnwindFunction(long unwindFunction);

    /* JADX INFO: compiled from: AnrPlugin.kt */
    @kotlin.Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0080\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001d\u0010\u0007\u001a\u00020\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u0000¢\u0006\u0004\b\f\u0010\rR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/bugsnag/android/AnrPlugin$Companion;", "", "()V", "ANR_ERROR_CLASS", "", "ANR_ERROR_MSG", "LOAD_ERR_MSG", "doesJavaTraceLeadToNativeTrace", "", "javaTrace", "", "Ljava/lang/StackTraceElement;", "doesJavaTraceLeadToNativeTrace$bugsnag_plugin_android_anr_release", "([Ljava/lang/StackTraceElement;)Z", "bugsnag-plugin-android-anr_release"}, k = 1, mv = {1, 4, 2})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final boolean doesJavaTraceLeadToNativeTrace$bugsnag_plugin_android_anr_release(StackTraceElement[] javaTrace) {
            if (javaTrace.length == 0) {
                return false;
            }
            return ((StackTraceElement) ArraysKt___ArraysKt.first(javaTrace)).isNativeMethod();
        }
    }

    private final Class<?> loadClass(String clz) {
        try {
            return Class.forName(clz);
        } catch (Throwable unused) {
            return null;
        }
    }

    @Override // com.bugsnag.android.Plugin
    public void load(Client client) throws IllegalAccessException, InvocationTargetException {
        this.client = client;
        if (!this.oneTimeSetupPerformed.getAndSet(true)) {
            performOneTimeSetup(client);
        }
        if (this.libraryLoader.isLoaded()) {
            Looper mainLooper = Looper.getMainLooper();
            if (kotlin.jvm.internal.Intrinsics.areEqual(Looper.myLooper(), mainLooper)) {
                initNativePlugin();
                return;
            } else {
                new Handler(mainLooper).postAtFrontOfQueue(new Runnable() { // from class: com.bugsnag.android.AnrPlugin.load.1
                    @Override // java.lang.Runnable
                    public final void run() {
                        AnrPlugin.this.initNativePlugin();
                    }
                });
                return;
            }
        }
        client.logger.e(LOAD_ERR_MSG);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void initNativePlugin() {
        enableAnrReporting();
        Client client = this.client;
        if (client == null) {
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(SearchMetricsServiceDefault.CONTENT_SRC_CLIENT);
        }
        client.logger.i("Initialised ANR Plugin");
    }

    private final void performOneTimeSetup(Client client) throws IllegalAccessException, InvocationTargetException {
        Plugin plugin;
        this.libraryLoader.loadLibrary("bugsnag-plugin-android-anr", client, new OnErrorCallback() { // from class: com.bugsnag.android.AnrPlugin.performOneTimeSetup.1
            @Override // com.bugsnag.android.OnErrorCallback
            public final boolean onError(Event event) {
                Error error = event.getErrors().get(0);
                kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(error, "error");
                error.setErrorClass("AnrLinkError");
                error.setErrorMessage(AnrPlugin.LOAD_ERR_MSG);
                return true;
            }
        });
        Class<?> clsLoadClass = loadClass("com.bugsnag.android.NdkPlugin");
        if (clsLoadClass == null || (plugin = client.getPlugin(clsLoadClass)) == null) {
            return;
        }
        Object objInvoke = plugin.getClass().getMethod("getSignalUnwindStackFunction", new Class[0]).invoke(plugin, new Object[0]);
        if (objInvoke == null) {
            throw new TypeCastException("null cannot be cast to non-null type kotlin.Long");
        }
        setUnwindFunction(((Long) objInvoke).longValue());
    }

    @Override // com.bugsnag.android.Plugin
    public void unload() {
        if (this.libraryLoader.isLoaded()) {
            disableAnrReporting();
        }
    }

    private final void notifyAnrDetected(List<NativeStackframe> nativeTrace) {
        Object next;
        List<Stackframe> stacktrace;
        try {
            Client client = this.client;
            if (client == null) {
                kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(SearchMetricsServiceDefault.CONTENT_SRC_CLIENT);
            }
            if (client.immutableConfig.shouldDiscardError("ANR")) {
                return;
            }
            Looper mainLooper = Looper.getMainLooper();
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(mainLooper, "Looper.getMainLooper()");
            java.lang.Thread thread = mainLooper.getThread();
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(thread, "Looper.getMainLooper().thread");
            StackTraceElement[] stackTrace = thread.getStackTrace();
            Companion companion = INSTANCE;
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(stackTrace, "stackTrace");
            boolean zDoesJavaTraceLeadToNativeTrace$bugsnag_plugin_android_anr_release = companion.doesJavaTraceLeadToNativeTrace$bugsnag_plugin_android_anr_release(stackTrace);
            RuntimeException runtimeException = new RuntimeException();
            runtimeException.setStackTrace(stackTrace);
            Client client2 = this.client;
            if (client2 == null) {
                kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(SearchMetricsServiceDefault.CONTENT_SRC_CLIENT);
            }
            Event eventCreateEvent = NativeInterface.createEvent(runtimeException, client2, SeverityReason.newInstance("anrError"));
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(eventCreateEvent, "NativeInterface.createEv…REASON_ANR)\n            )");
            Error err = eventCreateEvent.getErrors().get(0);
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(err, "err");
            err.setErrorClass("ANR");
            err.setErrorMessage(ANR_ERROR_MSG);
            if (zDoesJavaTraceLeadToNativeTrace$bugsnag_plugin_android_anr_release) {
                ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(nativeTrace, 10));
                Iterator<T> it = nativeTrace.iterator();
                while (it.hasNext()) {
                    arrayList.add(new Stackframe((NativeStackframe) it.next()));
                }
                err.getStacktrace().addAll(0, arrayList);
                List<Thread> threads = eventCreateEvent.getThreads();
                kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(threads, "event.threads");
                Iterator<T> it2 = threads.iterator();
                while (true) {
                    if (!it2.hasNext()) {
                        next = null;
                        break;
                    } else {
                        next = it2.next();
                        if (((Thread) next).getErrorReportingThread()) {
                            break;
                        }
                    }
                }
                Thread thread2 = (Thread) next;
                if (thread2 != null && (stacktrace = thread2.getStacktrace()) != null) {
                    stacktrace.addAll(0, arrayList);
                }
            }
            AnrDetailsCollector anrDetailsCollector = this.collector;
            Client client3 = this.client;
            if (client3 == null) {
                kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(SearchMetricsServiceDefault.CONTENT_SRC_CLIENT);
            }
            anrDetailsCollector.collectAnrErrorDetails$bugsnag_plugin_android_anr_release(client3, eventCreateEvent);
        } catch (Exception e) {
            Client client4 = this.client;
            if (client4 == null) {
                kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(SearchMetricsServiceDefault.CONTENT_SRC_CLIENT);
            }
            client4.logger.e("Internal error reporting ANR", e);
        }
    }
}
