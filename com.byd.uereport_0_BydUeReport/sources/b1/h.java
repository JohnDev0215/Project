package b1;

import android.os.Build;

/* JADX INFO: compiled from: MediaSessionManager.java */
/* JADX INFO: loaded from: classes.dex */
public final class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public i f2039a;

    public h(String str, int i8, int i9) {
        if (Build.VERSION.SDK_INT >= 28) {
            this.f2039a = new j(str, i8, i9);
        } else {
            this.f2039a = new k(str, i8, i9);
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof h) {
            return this.f2039a.equals(((h) obj).f2039a);
        }
        return false;
    }

    public int hashCode() {
        return this.f2039a.hashCode();
    }
}
