package c.n;

import android.os.IBinder;

/* JADX INFO: compiled from: WindowIdApi14.java */
/* JADX INFO: loaded from: classes.dex */
class k0 implements m0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final IBinder f2466a;

    k0(IBinder iBinder) {
        this.f2466a = iBinder;
    }

    public boolean equals(Object obj) {
        return (obj instanceof k0) && ((k0) obj).f2466a.equals(this.f2466a);
    }

    public int hashCode() {
        return this.f2466a.hashCode();
    }
}
