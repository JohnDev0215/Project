package androidx.test.core.app;

import androidx.test.internal.platform.ServiceLoaderWrapper;
import androidx.test.internal.platform.os.ControlledLooper;

/* JADX INFO: loaded from: classes5.dex */
final /* synthetic */ class ActivityScenario$$Lambda$1 implements ServiceLoaderWrapper.Factory {
    static final ServiceLoaderWrapper.Factory $instance = new ActivityScenario$$Lambda$1();

    private ActivityScenario$$Lambda$1() {
    }

    @Override // androidx.test.internal.platform.ServiceLoaderWrapper.Factory
    public Object create() {
        return ControlledLooper.NO_OP_CONTROLLED_LOOPER;
    }
}
