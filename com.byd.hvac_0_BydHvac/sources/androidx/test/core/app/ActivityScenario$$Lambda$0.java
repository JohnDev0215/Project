package androidx.test.core.app;

import androidx.test.internal.platform.ServiceLoaderWrapper;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class ActivityScenario$$Lambda$0 implements ServiceLoaderWrapper.Factory {
    public static final ServiceLoaderWrapper.Factory $instance = new ActivityScenario$$Lambda$0();

    private ActivityScenario$$Lambda$0() {
    }

    @Override // androidx.test.internal.platform.ServiceLoaderWrapper.Factory
    public Object create() {
        return ActivityScenario.lambda$new$0$ActivityScenario();
    }
}
