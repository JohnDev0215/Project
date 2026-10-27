package defpackage;

import android.media.session.MediaSessionManager;

/* JADX INFO: compiled from: MediaSessionManagerImplApi28.java */
/* JADX INFO: loaded from: classes.dex */
public final class ie1 extends je1 {
    public ie1(String str, int i, int i2) {
        super(str, i, i2);
        new MediaSessionManager.RemoteUserInfo(str, i, i2);
    }

    public ie1(MediaSessionManager.RemoteUserInfo remoteUserInfo) {
        super(remoteUserInfo.getPackageName(), remoteUserInfo.getPid(), remoteUserInfo.getUid());
    }
}
