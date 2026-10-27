package b.n;

import android.os.IBinder;

/* JADX INFO: compiled from: WindowIdApi14.java */
/* JADX INFO: loaded from: classes.dex */
class k0 implements m0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final IBinder f2155a;

    k0(IBinder iBinder) {
        this.f2155a = iBinder;
    }

    public boolean equals(Object obj) {
        return (obj instanceof k0) && ((k0) obj).f2155a.equals(this.f2155a);
    }

    public int hashCode() {
        return this.f2155a.hashCode();
    }
}
