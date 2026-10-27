package p;

import android.app.Application;
import android.media.session.MediaSession;

/* JADX INFO: loaded from: classes.dex */
public abstract /* synthetic */ class r2 {
    public static /* synthetic */ MediaSession e(Application application) {
        return new MediaSession(application, "spotify-music-auto", null);
    }
}
