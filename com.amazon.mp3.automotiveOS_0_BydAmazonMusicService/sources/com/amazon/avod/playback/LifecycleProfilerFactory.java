package com.amazon.avod.playback;

/* JADX INFO: loaded from: classes2.dex */
public class LifecycleProfilerFactory {
    public static LifecycleProfiler createLifecycleProfiler() {
        return createLifecycleProfiler(ProfilingConfig.getInstance().areMicroTracesEnabled());
    }

    public static LifecycleProfiler createLifecycleProfiler(boolean z) {
        return z ? new LifecycleProfilerImpl() : new NoopLifecycleProfiler();
    }
}
