package com.amazon.avod.perf.internal;

import com.amazon.cloud9.android.knobs.Knobs;

/* JADX INFO: loaded from: classes2.dex */
public class DefaultPerfSettings implements PerfSettings {
    @Override // com.amazon.avod.perf.internal.PerfSettings
    public int getTraceLevel() {
        return Knobs.get("aiv_profiler_trace_level", 0);
    }

    @Override // com.amazon.avod.perf.internal.PerfSettings
    public int getMemoryBufferMaxSize() {
        return Knobs.get("aiv_profiler_memory_buffer_size", 50000);
    }

    @Override // com.amazon.avod.perf.internal.PerfSettings
    public boolean isPerfLoggingEnabled() {
        return Knobs.get("aiv_profiler_file_logging", false);
    }

    @Override // com.amazon.avod.perf.internal.PerfSettings
    public boolean isLazyFormattingEnabled() {
        return Knobs.get("aiv_profiler_lazy_formatting", true);
    }
}
