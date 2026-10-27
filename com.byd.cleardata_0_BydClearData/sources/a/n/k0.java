package a.n;

import android.os.IBinder;

/* JADX INFO: loaded from: classes.dex */
class k0 implements m0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final IBinder f563a;

    k0(IBinder iBinder) {
        this.f563a = iBinder;
    }

    public boolean equals(Object obj) {
        return (obj instanceof k0) && ((k0) obj).f563a.equals(this.f563a);
    }

    public int hashCode() {
        return this.f563a.hashCode();
    }
}
