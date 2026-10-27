package o1;

import java.util.Arrays;

/* JADX INFO: compiled from: LottieResult.java */
/* JADX INFO: loaded from: classes.dex */
public final class m<V> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final V f6854a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Throwable f6855b;

    public m(V v7) {
        this.f6854a = v7;
        this.f6855b = null;
    }

    public Throwable a() {
        return this.f6855b;
    }

    public V b() {
        return this.f6854a;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof m)) {
            return false;
        }
        m mVar = (m) obj;
        if (b() != null && b().equals(mVar.b())) {
            return true;
        }
        if (a() == null || mVar.a() == null) {
            return false;
        }
        return a().toString().equals(a().toString());
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{b(), a()});
    }

    public m(Throwable th) {
        this.f6855b = th;
        this.f6854a = null;
    }
}
