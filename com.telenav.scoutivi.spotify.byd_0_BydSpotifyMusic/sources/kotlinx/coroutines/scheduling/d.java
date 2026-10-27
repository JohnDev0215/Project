package kotlinx.coroutines.scheduling;

import java.util.concurrent.RejectedExecutionException;
import kotlinx.coroutines.e1;
import kotlinx.coroutines.n0;

/* JADX INFO: compiled from: Dispatcher.kt */
/* JADX INFO: loaded from: classes.dex */
public class d extends e1 {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private a f7764h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private final int f7765i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private final int f7766j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private final long f7767k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private final String f7768l;

    public d(int i7, int i8, long j7, String str) {
        this.f7765i = i7;
        this.f7766j = i8;
        this.f7767k = j7;
        this.f7768l = str;
        this.f7764h = d0();
    }

    private final a d0() {
        return new a(this.f7765i, this.f7766j, this.f7767k, this.f7768l);
    }

    @Override // kotlinx.coroutines.e0
    public void a0(k5.g gVar, Runnable runnable) {
        try {
            a.N(this.f7764h, runnable, null, false, 6, null);
        } catch (RejectedExecutionException unused) {
            n0.f7710m.a0(gVar, runnable);
        }
    }

    @Override // kotlinx.coroutines.e0
    public void b0(k5.g gVar, Runnable runnable) {
        try {
            a.N(this.f7764h, runnable, null, true, 2, null);
        } catch (RejectedExecutionException unused) {
            n0.f7710m.b0(gVar, runnable);
        }
    }

    public final void e0(Runnable runnable, j jVar, boolean z6) {
        try {
            this.f7764h.K(runnable, jVar, z6);
        } catch (RejectedExecutionException unused) {
            n0.f7710m.s0(this.f7764h.G(runnable, jVar));
        }
    }

    public /* synthetic */ d(int i7, int i8, String str, int i9, kotlin.jvm.internal.g gVar) {
        this((i9 & 1) != 0 ? l.f7783c : i7, (i9 & 2) != 0 ? l.f7784d : i8, (i9 & 4) != 0 ? "DefaultDispatcher" : str);
    }

    public d(int i7, int i8, String str) {
        this(i7, i8, l.f7785e, str);
    }
}
