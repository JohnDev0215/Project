package b.x;

import android.os.IBinder;

/* JADX INFO: compiled from: WindowIdApi14.java */
/* JADX INFO: loaded from: classes.dex */
public class k0 implements m0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final IBinder f3282a;

    public k0(IBinder iBinder) {
        this.f3282a = iBinder;
    }

    public boolean equals(Object obj) {
        return (obj instanceof k0) && ((k0) obj).f3282a.equals(this.f3282a);
    }

    public int hashCode() {
        return this.f3282a.hashCode();
    }
}
