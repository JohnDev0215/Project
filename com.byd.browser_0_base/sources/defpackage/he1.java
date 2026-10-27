package defpackage;

import android.media.session.MediaSessionManager;
import android.text.TextUtils;

/* JADX INFO: compiled from: MediaSessionManager.java */
/* JADX INFO: loaded from: classes.dex */
public final class he1 {
    public final ie1 a;

    public he1(String str, int i, int i2) {
        if (str == null) {
            throw new NullPointerException("package shouldn't be null");
        }
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("packageName should be nonempty");
        }
        this.a = new ie1(str, i, i2);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof he1)) {
            return false;
        }
        return this.a.equals(((he1) obj).a);
    }

    public final int hashCode() {
        return this.a.hashCode();
    }

    public he1(MediaSessionManager.RemoteUserInfo remoteUserInfo) {
        String packageName = remoteUserInfo.getPackageName();
        if (packageName != null) {
            if (!TextUtils.isEmpty(packageName)) {
                this.a = new ie1(remoteUserInfo);
                return;
            }
            throw new IllegalArgumentException("packageName should be nonempty");
        }
        throw new NullPointerException("package shouldn't be null");
    }
}
