package b.q;

import android.media.session.MediaSessionManager;

/* JADX INFO: compiled from: MediaSessionManagerImplApi28.java */
/* JADX INFO: loaded from: classes.dex */
public final class j implements i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final MediaSessionManager.RemoteUserInfo f3011a;

    public j(String str, int i2, int i3) {
        this.f3011a = new MediaSessionManager.RemoteUserInfo(str, i2, i3);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof j) {
            return this.f3011a.equals(((j) obj).f3011a);
        }
        return false;
    }

    public int hashCode() {
        return b.h.l.b.b(this.f3011a);
    }
}
