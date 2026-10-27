package androidx.test;

import android.app.Instrumentation;
import android.content.Context;
import android.os.Bundle;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
@Deprecated
public final class InstrumentationRegistry {
    private static final AtomicReference<Instrumentation> instrumentationRef = new AtomicReference<>(null);
    private static final AtomicReference<Bundle> arguments = new AtomicReference<>(null);

    private InstrumentationRegistry() {
    }

    @Deprecated
    public static Bundle getArguments() {
        Bundle bundle = arguments.get();
        if (bundle != null) {
            return new Bundle(bundle);
        }
        throw new IllegalStateException("No instrumentation arguments registered! Are you running under an Instrumentation which registers arguments?");
    }

    @Deprecated
    public static Context getContext() {
        return getInstrumentation().getContext();
    }

    @Deprecated
    public static Instrumentation getInstrumentation() {
        Instrumentation instrumentation = instrumentationRef.get();
        if (instrumentation != null) {
            return instrumentation;
        }
        throw new IllegalStateException("No instrumentation registered! Must run under a registering instrumentation.");
    }

    @Deprecated
    public static Context getTargetContext() {
        return getInstrumentation().getTargetContext();
    }

    @Deprecated
    public static void registerInstance(Instrumentation instrumentation, Bundle bundle) {
        instrumentationRef.set(instrumentation);
        arguments.set(new Bundle(bundle));
    }
}
