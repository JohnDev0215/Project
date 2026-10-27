package androidx.lifecycle;

import android.os.Handler;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u0001\u0004B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0005"}, d2 = {"Landroidx/lifecycle/ProcessLifecycleOwner;", "Landroidx/lifecycle/A;", "<init>", "()V", "androidx/lifecycle/M", "lifecycle-process_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class ProcessLifecycleOwner implements A {

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public static final ProcessLifecycleOwner f11939s = new ProcessLifecycleOwner();
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f11940m;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public Handler f11942p;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public boolean f11941n = true;
    public boolean o = true;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final C f11943q = new C(this);

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final A2.l f11944r = new A2.l(this, 9);

    private ProcessLifecycleOwner() {
    }

    @Override // androidx.lifecycle.A
    public final A9.b g() {
        return this.f11943q;
    }
}
