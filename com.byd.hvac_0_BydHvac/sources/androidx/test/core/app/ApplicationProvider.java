package androidx.test.core.app;

import android.content.Context;
import androidx.test.platform.app.InstrumentationRegistry;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public final class ApplicationProvider {
    private ApplicationProvider() {
    }

    public static <T extends Context> T getApplicationContext() {
        return (T) InstrumentationRegistry.getInstrumentation().getTargetContext().getApplicationContext();
    }
}
