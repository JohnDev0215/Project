package b1;

import android.media.session.MediaSessionManager;

/* JADX INFO: compiled from: MediaSessionManagerImplApi28.java */
/* JADX INFO: loaded from: classes.dex */
public final class j implements i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final MediaSessionManager.RemoteUserInfo f2040a;

    public j(String str, int i8, int i9) {
        this.f2040a = new MediaSessionManager.RemoteUserInfo(str, i8, i9);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof j) {
            return this.f2040a.equals(((j) obj).f2040a);
        }
        return false;
    }

    public int hashCode() {
        return n0.c.b(this.f2040a);
    }
}
