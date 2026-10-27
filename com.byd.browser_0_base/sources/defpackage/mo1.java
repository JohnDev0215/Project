package defpackage;

import android.app.Notification;
import android.media.session.MediaSession;
import android.support.v4.media.session.MediaSessionCompat;

/* JADX INFO: compiled from: NotificationCompat.java */
/* JADX INFO: loaded from: classes.dex */
public final class mo1 extends no1 {
    public int[] d = null;
    public MediaSessionCompat.Token e;

    @Override // defpackage.no1
    public final void b(oo1 oo1Var) {
        Notification.Builder builder = oo1Var.b;
        Notification.MediaStyle mediaStyle = new Notification.MediaStyle();
        int[] iArr = this.d;
        MediaSessionCompat.Token token = this.e;
        if (iArr != null) {
            mediaStyle.setShowActionsInCompactView(iArr);
        }
        if (token != null) {
            mediaStyle.setMediaSession((MediaSession.Token) token.getToken());
        }
        builder.setStyle(mediaStyle);
    }

    @Override // defpackage.no1
    public final void d() {
    }

    @Override // defpackage.no1
    public final void e() {
    }
}
