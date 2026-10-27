package androidx.media;

import android.content.Context;
import androidx.annotation.RequiresApi;
import androidx.media.MediaSessionManager;
import androidx.media.MediaSessionManagerImplBase;

/* JADX INFO: loaded from: classes.dex */
@RequiresApi(28)
class MediaSessionManagerImplApi28 extends MediaSessionManagerImplApi21 {
    android.media.session.MediaSessionManager mObject;

    MediaSessionManagerImplApi28(Context context) {
        super(context);
        this.mObject = (android.media.session.MediaSessionManager) context.getSystemService("media_session");
    }

    @Override // androidx.media.MediaSessionManagerImplApi21, androidx.media.MediaSessionManagerImplBase, androidx.media.MediaSessionManager.MediaSessionManagerImpl
    public boolean isTrustedForMediaControl(MediaSessionManager.RemoteUserInfoImpl remoteUserInfoImpl) {
        return super.isTrustedForMediaControl(remoteUserInfoImpl);
    }

    @RequiresApi(28)
    static final class RemoteUserInfoImplApi28 extends MediaSessionManagerImplBase.RemoteUserInfoImplBase {
        final android.media.session.MediaSessionManager$RemoteUserInfo mObject;

        RemoteUserInfoImplApi28(String str, int i, int i2) {
            super(str, i, i2);
            this.mObject = new android.media.session.MediaSessionManager$RemoteUserInfo(str, i, i2);
        }

        RemoteUserInfoImplApi28(android.media.session.MediaSessionManager$RemoteUserInfo mediaSessionManager$RemoteUserInfo) {
            super(mediaSessionManager$RemoteUserInfo.getPackageName(), mediaSessionManager$RemoteUserInfo.getPid(), mediaSessionManager$RemoteUserInfo.getUid());
            this.mObject = mediaSessionManager$RemoteUserInfo;
        }

        static String getPackageName(android.media.session.MediaSessionManager$RemoteUserInfo mediaSessionManager$RemoteUserInfo) {
            return mediaSessionManager$RemoteUserInfo.getPackageName();
        }
    }
}
