package androidx.test.core.app;

import androidx.test.internal.platform.ServiceLoaderWrapper;
import androidx.test.internal.platform.os.ControlledLooper;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class ActivityScenario$$Lambda$1 implements ServiceLoaderWrapper.Factory {
    public static final ServiceLoaderWrapper.Factory $instance = new ActivityScenario$$Lambda$1();

    private ActivityScenario$$Lambda$1() {
    }

    @Override // androidx.test.internal.platform.ServiceLoaderWrapper.Factory
    public Object create() {
        return ControlledLooper.NO_OP_CONTROLLED_LOOPER;
    }
}
