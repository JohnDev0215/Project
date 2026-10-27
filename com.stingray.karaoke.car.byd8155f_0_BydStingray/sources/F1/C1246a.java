package f1;

import java.util.concurrent.CancellationException;

/* JADX INFO: renamed from: f1.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1246a {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final C1246a f14530b;
    public static final C1246a c;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final CancellationException f14531a;

    static {
        if (AbstractC1252g.o) {
            c = null;
            f14530b = null;
        } else {
            c = new C1246a(false, null);
            f14530b = new C1246a(true, null);
        }
    }

    public C1246a(boolean z6, CancellationException cancellationException) {
        this.f14531a = cancellationException;
    }
}
