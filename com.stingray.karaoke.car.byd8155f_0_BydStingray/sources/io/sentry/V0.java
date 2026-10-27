package io.sentry;

import h2.AbstractC1347d;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class V0 implements InterfaceC1485m0 {
    public boolean l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public Double f15970m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public boolean f15971n;
    public Double o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public String f15972p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public boolean f15973q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public int f15974r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public ConcurrentHashMap f15975s;

    public V0(F1 f12, B8.t tVar) {
        this.f15971n = ((Boolean) tVar.f808n).booleanValue();
        this.o = (Double) tVar.o;
        this.l = ((Boolean) tVar.f809p).booleanValue();
        this.f15970m = (Double) tVar.f807m;
        this.f15972p = f12.getProfilingTracesDirPath();
        this.f15973q = f12.isProfilingEnabled();
        this.f15974r = f12.getProfilingTracesHz();
    }

    @Override // io.sentry.InterfaceC1485m0
    public final void serialize(C0 c02, K k10) {
        C1489n1 c1489n1 = (C1489n1) c02;
        c1489n1.i();
        c1489n1.w("profile_sampled");
        c1489n1.I(k10, Boolean.valueOf(this.l));
        c1489n1.w("profile_sample_rate");
        c1489n1.I(k10, this.f15970m);
        c1489n1.w("trace_sampled");
        c1489n1.I(k10, Boolean.valueOf(this.f15971n));
        c1489n1.w("trace_sample_rate");
        c1489n1.I(k10, this.o);
        c1489n1.w("profiling_traces_dir_path");
        c1489n1.I(k10, this.f15972p);
        c1489n1.w("is_profiling_enabled");
        c1489n1.I(k10, Boolean.valueOf(this.f15973q));
        c1489n1.w("profiling_traces_hz");
        c1489n1.I(k10, Integer.valueOf(this.f15974r));
        ConcurrentHashMap concurrentHashMap = this.f15975s;
        if (concurrentHashMap != null) {
            for (String str : concurrentHashMap.keySet()) {
                AbstractC1347d.t(this.f15975s, str, c1489n1, str, k10);
            }
        }
        c1489n1.n();
    }
}
