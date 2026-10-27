package rx.android.plugins;

import androidx.compose.runtime.CompositionImpl$$ExternalSyntheticBackportWithForwarding0;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes6.dex */
public final class RxAndroidPlugins {
    private static final RxAndroidPlugins INSTANCE = new RxAndroidPlugins();
    private final AtomicReference<RxAndroidSchedulersHook> schedulersHook = new AtomicReference<>();

    public static RxAndroidPlugins getInstance() {
        return INSTANCE;
    }

    RxAndroidPlugins() {
    }

    public RxAndroidSchedulersHook getSchedulersHook() {
        if (this.schedulersHook.get() == null) {
            CompositionImpl$$ExternalSyntheticBackportWithForwarding0.m(this.schedulersHook, null, RxAndroidSchedulersHook.getDefaultInstance());
        }
        return this.schedulersHook.get();
    }
}
