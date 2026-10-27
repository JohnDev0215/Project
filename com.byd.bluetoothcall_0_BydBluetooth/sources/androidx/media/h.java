package androidx.media;

import android.os.Build;

/* JADX INFO: compiled from: MediaSessionManager.java */
/* JADX INFO: loaded from: classes.dex */
public final class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    i f1595a;

    public h(String str, int i, int i2) {
        if (Build.VERSION.SDK_INT >= 28) {
            this.f1595a = new j(str, i, i2);
        } else {
            this.f1595a = new k(str, i, i2);
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof h) {
            return this.f1595a.equals(((h) obj).f1595a);
        }
        return false;
    }

    public int hashCode() {
        return this.f1595a.hashCode();
    }
}
