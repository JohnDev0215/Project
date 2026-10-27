package h5;

import java.util.concurrent.Executor;

/* JADX INFO: renamed from: h5.d, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1378d {
    public static final C1378d d = new C1378d();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Runnable f15544a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Executor f15545b;
    public C1378d c;

    public C1378d(Runnable runnable, Executor executor) {
        this.f15544a = runnable;
        this.f15545b = executor;
    }

    public C1378d() {
        this.f15544a = null;
        this.f15545b = null;
    }
}
