package l3;

import android.media.session.MediaSession;
import android.os.Bundle;
import android.os.Handler;
import android.os.RemoteCallbackList;
import com.stingray.karaoke.car.player.PlayerService;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public abstract class K implements InterfaceC1744I {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final MediaSession f18523a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final J f18524b;
    public final O c;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Bundle f18525e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public X f18527g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public ArrayList f18528h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public C1741F f18529i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f18530j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int f18531k;
    public AbstractC1757m l;
    public final Object d = new Object();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final RemoteCallbackList f18526f = new RemoteCallbackList();

    public K(PlayerService playerService, String str, Bundle bundle) {
        MediaSession mediaSessionC = c(playerService, str, bundle);
        this.f18523a = mediaSessionC;
        J j3 = new J((L) this);
        this.f18524b = j3;
        this.c = new O(mediaSessionC.getSessionToken(), j3);
        this.f18525e = bundle;
        mediaSessionC.setFlags(3);
    }

    @Override // l3.InterfaceC1744I
    public final AbstractC1757m b() {
        AbstractC1757m abstractC1757m;
        synchronized (this.d) {
            abstractC1757m = this.l;
        }
        return abstractC1757m;
    }

    public abstract MediaSession c(PlayerService playerService, String str, Bundle bundle);

    public final void d(AbstractC1757m abstractC1757m, Handler handler) {
        synchronized (this.d) {
            try {
                this.l = abstractC1757m;
                this.f18523a.setCallback(abstractC1757m == null ? null : (C1743H) abstractC1757m.f18586b, handler);
                if (abstractC1757m != null) {
                    abstractC1757m.B(this, handler);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
