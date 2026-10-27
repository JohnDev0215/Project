package y0;

import android.os.IBinder;

/* JADX INFO: compiled from: WindowIdApi14.java */
/* JADX INFO: loaded from: classes.dex */
public class j0 implements l0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final IBinder f5930a;

    public j0(IBinder iBinder) {
        this.f5930a = iBinder;
    }

    public boolean equals(Object obj) {
        return (obj instanceof j0) && ((j0) obj).f5930a.equals(this.f5930a);
    }

    public int hashCode() {
        return this.f5930a.hashCode();
    }
}
