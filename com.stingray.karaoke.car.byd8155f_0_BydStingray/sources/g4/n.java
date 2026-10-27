package g4;

import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes.dex */
public final class n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final w4.g f14917a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Executor f14918b;

    public n(w4.g gVar, Executor executor) {
        this.f14917a = gVar;
        this.f14918b = executor;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof n) {
            return this.f14917a.equals(((n) obj).f14917a);
        }
        return false;
    }

    public final int hashCode() {
        return this.f14917a.hashCode();
    }
}
