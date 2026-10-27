package b.q;

import android.os.Build;

/* JADX INFO: compiled from: MediaSessionManager.java */
/* JADX INFO: loaded from: classes.dex */
public final class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public i f3010a;

    public h(String str, int i2, int i3) {
        if (Build.VERSION.SDK_INT >= 28) {
            this.f3010a = new j(str, i2, i3);
        } else {
            this.f3010a = new k(str, i2, i3);
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof h) {
            return this.f3010a.equals(((h) obj).f3010a);
        }
        return false;
    }

    public int hashCode() {
        return this.f3010a.hashCode();
    }
}
