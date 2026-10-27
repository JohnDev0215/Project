package p;

import android.app.Application;
import android.app.PendingIntent;
import android.media.session.MediaSession;
import android.os.Bundle;
import android.os.Handler;
import android.os.RemoteCallbackList;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.session.MediaSessionCompat$Token;
import android.support.v4.media.session.PlaybackStateCompat;
import android.util.Log;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class vh1 implements uh1 {
    public final MediaSession a;
    public final android.support.v4.media.session.d b;
    public final MediaSessionCompat$Token c;
    public final Bundle e;
    public PlaybackStateCompat g;
    public List h;
    public MediaMetadataCompat i;
    public int j;
    public int k;
    public th1 l;
    public gi1 m;
    public final Object d = new Object();
    public final RemoteCallbackList f = new RemoteCallbackList();

    public vh1(Application application) {
        MediaSession mediaSessionE = e(application);
        this.a = mediaSessionE;
        android.support.v4.media.session.d dVar = new android.support.v4.media.session.d((wh1) this);
        this.b = dVar;
        this.c = new MediaSessionCompat$Token(mediaSessionE.getSessionToken(), dVar);
        this.e = null;
        mediaSessionE.setFlags(3);
    }

    @Override // p.uh1
    public final PlaybackStateCompat a() {
        return this.g;
    }

    @Override // p.uh1
    public void b(gi1 gi1Var) {
        synchronized (this.d) {
            this.m = gi1Var;
        }
    }

    @Override // p.uh1
    public final th1 c() {
        th1 th1Var;
        synchronized (this.d) {
            th1Var = this.l;
        }
        return th1Var;
    }

    @Override // p.uh1
    public gi1 d() {
        gi1 gi1Var;
        synchronized (this.d) {
            gi1Var = this.m;
        }
        return gi1Var;
    }

    public MediaSession e(Application application) {
        return new MediaSession(application, "spotify-music-auto");
    }

    public final String f() {
        MediaSession mediaSession = this.a;
        try {
            return (String) mediaSession.getClass().getMethod("getCallingPackage", new Class[0]).invoke(mediaSession, new Object[0]);
        } catch (Exception e) {
            Log.e("MediaSessionCompat", "Cannot execute MediaSession.getCallingPackage()", e);
            return null;
        }
    }

    public final void g(th1 th1Var, Handler handler) {
        synchronized (this.d) {
            try {
                this.l = th1Var;
                this.a.setCallback(th1Var == null ? null : th1Var.b, handler);
                if (th1Var != null) {
                    th1Var.t(this, handler);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void h(PendingIntent pendingIntent) {
        this.a.setMediaButtonReceiver(pendingIntent);
    }
}
