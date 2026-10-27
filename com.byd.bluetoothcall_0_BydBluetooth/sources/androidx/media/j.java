package androidx.media;

import android.media.session.MediaSessionManager;

/* JADX INFO: compiled from: MediaSessionManagerImplApi28.java */
/* JADX INFO: loaded from: classes.dex */
final class j implements i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final MediaSessionManager.RemoteUserInfo f1596a;

    j(String str, int i, int i2) {
        this.f1596a = new MediaSessionManager.RemoteUserInfo(str, i, i2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof j) {
            return this.f1596a.equals(((j) obj).f1596a);
        }
        return false;
    }

    public int hashCode() {
        return b.g.j.c.b(this.f1596a);
    }
}
