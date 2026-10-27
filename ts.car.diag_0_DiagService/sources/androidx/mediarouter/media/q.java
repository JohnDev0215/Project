package androidx.mediarouter.media;

import android.os.Bundle;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes2.dex */
public final class q {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Bundle f989a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private t f990b;

    public q(t tVar, boolean z) {
        if (tVar == null) {
            throw new IllegalArgumentException("selector must not be null");
        }
        Bundle bundle = new Bundle();
        this.f989a = bundle;
        this.f990b = tVar;
        bundle.putBundle("selector", tVar.a());
        this.f989a.putBoolean("activeScan", z);
    }

    private void b() {
        if (this.f990b == null) {
            t tVarD = t.d(this.f989a.getBundle("selector"));
            this.f990b = tVarD;
            if (tVarD == null) {
                this.f990b = t.f996c;
            }
        }
    }

    public static q c(Bundle bundle) {
        if (bundle != null) {
            return new q(bundle);
        }
        return null;
    }

    public Bundle a() {
        return this.f989a;
    }

    public t d() {
        b();
        return this.f990b;
    }

    public boolean e() {
        return this.f989a.getBoolean("activeScan");
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof q)) {
            return false;
        }
        q qVar = (q) obj;
        return d().equals(qVar.d()) && e() == qVar.e();
    }

    public boolean f() {
        b();
        return this.f990b.g();
    }

    public int hashCode() {
        return e() ^ d().hashCode();
    }

    public String toString() {
        return "DiscoveryRequest{ selector=" + d() + ", activeScan=" + e() + ", isValid=" + f() + " }";
    }

    private q(Bundle bundle) {
        this.f989a = bundle;
    }
}
