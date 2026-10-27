package androidx.test;

import android.app.Instrumentation;
import android.content.Context;
import android.os.Bundle;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes5.dex */
@Deprecated
public final class InstrumentationRegistry {
    private static final AtomicReference<Instrumentation> instrumentationRef = new AtomicReference<>(null);
    private static final AtomicReference<Bundle> arguments = new AtomicReference<>(null);

    @Deprecated
    public static Instrumentation getInstrumentation() {
        Instrumentation instance = instrumentationRef.get();
        if (instance == null) {
            throw new IllegalStateException("No instrumentation registered! Must run under a registering instrumentation.");
        }
        return instance;
    }

    @Deprecated
    public static Bundle getArguments() {
        Bundle instance = arguments.get();
        if (instance == null) {
            throw new IllegalStateException("No instrumentation arguments registered! Are you running under an Instrumentation which registers arguments?");
        }
        return new Bundle(instance);
    }

    @Deprecated
    public static Context getContext() {
        return getInstrumentation().getContext();
    }

    @Deprecated
    public static Context getTargetContext() {
        return getInstrumentation().getTargetContext();
    }

    @Deprecated
    public static void registerInstance(Instrumentation instrumentation, Bundle arguments2) {
        instrumentationRef.set(instrumentation);
        arguments.set(new Bundle(arguments2));
    }

    private InstrumentationRegistry() {
    }
}
