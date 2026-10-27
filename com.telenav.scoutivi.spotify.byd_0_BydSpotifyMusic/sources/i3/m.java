package i3;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.media.MediaDescriptionCompat;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.session.MediaControllerCompat;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.text.TextUtils;
import i3.x;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: MediaPlayController.java */
/* JADX INFO: loaded from: classes.dex */
public class m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Context f6949a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private MediaMetadataCompat f6950b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private volatile PlaybackStateCompat f6951c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private volatile MediaMetadataCompat f6952d;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final s f6955g;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private final ScheduledExecutorService f6958j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private final MediaControllerCompat.a f6959k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private final i3.a f6960l;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private long f6953e = 0;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private volatile int f6954f = -2;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private final CopyOnWriteArraySet<MediaControllerCompat.a> f6956h = new CopyOnWriteArraySet<>();

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private final CopyOnWriteArraySet<e> f6957i = new CopyOnWriteArraySet<>();

    /* JADX INFO: compiled from: MediaPlayController.java */
    class a extends MediaControllerCompat.a {
        a() {
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.a
        public void d(MediaMetadataCompat mediaMetadataCompat) {
            super.d(mediaMetadataCompat);
            m.this.u(mediaMetadataCompat);
            h3.l lVarB = h3.i.b("MediaPlayController");
            StringBuilder sb = new StringBuilder();
            sb.append("onMetadataChanged:");
            sb.append(mediaMetadataCompat == null ? "" : mediaMetadataCompat.toString());
            lVarB.g(sb.toString());
            m.this.D(mediaMetadataCompat);
            m.this.F();
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.a
        public void e(PlaybackStateCompat playbackStateCompat) {
            super.e(playbackStateCompat);
            m.this.v(playbackStateCompat);
            h3.l lVarB = h3.i.b("MediaPlayController");
            StringBuilder sb = new StringBuilder();
            sb.append("onPlaybackStateChanged:");
            sb.append(playbackStateCompat == null ? "" : playbackStateCompat.toString());
            lVarB.g(sb.toString());
            m.this.E(playbackStateCompat);
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.a
        public void f(List<MediaSessionCompat.QueueItem> list) {
            super.f(list);
            m.this.G(list);
            h3.i.b("MediaPlayController").g("onQueueChanged");
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.a
        public void h(int i7) {
            super.h(i7);
            h3.i.b("MediaPlayController").b("onRepeatModeChanged:%s", Integer.valueOf(i7));
            m.this.f6954f = i7;
            m mVar = m.this;
            mVar.E(mVar.f6951c);
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.a
        public void k() {
            super.k();
            m mVar = m.this;
            mVar.E(mVar.q());
            m mVar2 = m.this;
            mVar2.D(mVar2.n());
            h3.i.b("MediaPlayController").b("onSessionReady", new Object[0]);
        }
    }

    /* JADX INFO: compiled from: MediaPlayController.java */
    class b implements i3.a {
        b() {
        }

        @Override // i3.a
        public void a(s sVar) {
            h3.i.b("MediaPlayController").b("media onConnectionSuspended", new Object[0]);
        }

        @Override // i3.a
        public void b(s sVar) {
            h3.i.b("MediaPlayController").b("media onConnectionFailed", new Object[0]);
        }

        @Override // i3.a
        public void c(s sVar) {
            h3.i.b("MediaPlayController").b("media connect", new Object[0]);
            m mVar = m.this;
            mVar.C(mVar.f6959k);
        }
    }

    /* JADX INFO: compiled from: MediaPlayController.java */
    static /* synthetic */ class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f6963a;

        static {
            int[] iArr = new int[x.c.values().length];
            f6963a = iArr;
            try {
                iArr[x.c.MEDIA.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f6963a[x.c.ALBUM.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f6963a[x.c.GENRE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f6963a[x.c.ARTIST.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f6963a[x.c.PLAYLIST.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    /* JADX INFO: compiled from: MediaPlayController.java */
    public interface d {
        void b();
    }

    /* JADX INFO: compiled from: MediaPlayController.java */
    public interface e {
        void a(int i7);
    }

    /* JADX INFO: compiled from: MediaPlayController.java */
    public interface f {
        void a(String str);
    }

    public m(Application application, s sVar, i3.b bVar) {
        a aVar = new a();
        this.f6959k = aVar;
        b bVar2 = new b();
        this.f6960l = bVar2;
        this.f6958j = Executors.newSingleThreadScheduledExecutor(new ThreadFactory() { // from class: i3.l
            @Override // java.util.concurrent.ThreadFactory
            public final Thread newThread(Runnable runnable) {
                return m.A(runnable);
            }
        });
        V();
        this.f6955g = sVar;
        this.f6949a = application;
        sVar.L(aVar);
        sVar.r(bVar2);
        h(bVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Thread A(Runnable runnable) {
        return new Thread(runnable, "media-controller-update-progress-thread");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void B() {
        F();
        V();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void C(MediaControllerCompat.a aVar) {
        aVar.d(n());
        aVar.e(q());
        aVar.b(w());
        aVar.f(r());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void F() {
        try {
            PlaybackStateCompat playbackStateCompat = this.f6951c;
            MediaMetadataCompat mediaMetadataCompat = this.f6952d;
            if (playbackStateCompat == null || mediaMetadataCompat == null) {
                return;
            }
            long jLongValue = u.a(playbackStateCompat).longValue();
            int iF = t.f(mediaMetadataCompat);
            long j7 = this.f6953e;
            if (j7 != jLongValue) {
                long j8 = iF;
                if (jLongValue <= j8) {
                    this.f6953e = jLongValue;
                } else if (j7 >= j8) {
                    return;
                } else {
                    this.f6953e = j8;
                }
                for (e eVar : this.f6957i) {
                    if (eVar != null) {
                        eVar.a((int) this.f6953e);
                    }
                }
            }
        } catch (Throwable th) {
            h3.i.b("MediaPlayController").e(th, "startCheckPlaybackPositionTask failed", new Object[0]);
        }
    }

    private MediaMetadataCompat l() {
        if (this.f6950b == null) {
            this.f6950b = new MediaMetadataCompat.b().b("android.media.metadata.MEDIA_ID", "#media_id_useless#").b("android.media.metadata.ARTIST", "").b("android.media.metadata.TITLE", this.f6949a.getString(w.unknown_song)).a();
        }
        if (!TextUtils.equals(this.f6950b.m("android.media.metadata.TITLE"), this.f6949a.getString(w.unknown_song))) {
            this.f6950b = null;
            l();
        }
        return this.f6950b;
    }

    private MediaControllerCompat o() {
        s sVar = this.f6955g;
        if (sVar == null) {
            return null;
        }
        return sVar.y();
    }

    public void D(MediaMetadataCompat mediaMetadataCompat) {
        this.f6952d = mediaMetadataCompat;
        if (mediaMetadataCompat == null || TextUtils.isEmpty(mediaMetadataCompat.m("android.media.metadata.TITLE"))) {
            mediaMetadataCompat = l();
        }
        String strM = mediaMetadataCompat.m("android.media.metadata.TITLE");
        h3.l lVarB = h3.i.b("MediaPlayController");
        StringBuilder sb = new StringBuilder();
        sb.append("notifyMetadataChanged:metadata=");
        sb.append(TextUtils.isEmpty(strM) ? "" : strM.substring(0, 1) + "*" + strM.substring(strM.length() - 1, strM.length()));
        lVarB.b(sb.toString(), new Object[0]);
        for (MediaControllerCompat.a aVar : this.f6956h) {
            if (aVar != null) {
                aVar.d(mediaMetadataCompat);
            }
        }
    }

    public void E(PlaybackStateCompat playbackStateCompat) {
        this.f6951c = playbackStateCompat;
        h3.i.b("MediaPlayController").g("notifyPlaybackStateChanged:state=" + playbackStateCompat);
        Iterator<MediaControllerCompat.a> it = this.f6956h.iterator();
        while (it.hasNext()) {
            it.next().e(playbackStateCompat);
        }
    }

    public void G(List<MediaSessionCompat.QueueItem> list) {
        if (list != null) {
            h3.i.b("MediaPlayController").g("notifyQueueChanged:queue=" + list.size());
            Iterator<MediaControllerCompat.a> it = this.f6956h.iterator();
            while (it.hasNext()) {
                it.next().f(list);
            }
        }
    }

    public boolean H() {
        MediaControllerCompat mediaControllerCompatO = o();
        if (mediaControllerCompatO == null) {
            h3.i.b("MediaPlayController").d("execute 2 failed: mediaControllerCompat is null", new Object[0]);
            return false;
        }
        h3.i.b("MediaPlayController").b("execute 2", new Object[0]);
        mediaControllerCompatO.i().a();
        return true;
    }

    public boolean I() {
        MediaControllerCompat mediaControllerCompatO = o();
        if (mediaControllerCompatO == null) {
            h3.i.b("MediaPlayController").d("execute 1 failed: mediaControllerCompat is null", new Object[0]);
            return false;
        }
        h3.i.b("MediaPlayController").b("execute 1", new Object[0]);
        mediaControllerCompatO.i().b();
        return true;
    }

    public boolean J(String str, x xVar) {
        MediaControllerCompat mediaControllerCompatO = o();
        if (mediaControllerCompatO == null) {
            h3.i.b("MediaPlayController").d("execute 17 failed: mediaControllerCompat is null", new Object[0]);
            return false;
        }
        if (str == null) {
            str = "";
        }
        h3.l lVarB = h3.i.b("MediaPlayController");
        StringBuilder sb = new StringBuilder();
        sb.append("execute 17 ");
        sb.append(str.length());
        sb.append(" : ");
        sb.append(xVar == null ? "null" : xVar.e());
        lVarB.b(sb.toString(), new Object[0]);
        Bundle bundle = new Bundle();
        if (xVar != null) {
            int i7 = c.f6963a[xVar.e().ordinal()];
            if (i7 == 1) {
                bundle.putString("android.intent.extra.focus", "vnd.android.cursor.item/audio");
            } else if (i7 == 2) {
                bundle.putString("android.intent.extra.focus", "vnd.android.cursor.item/album");
            } else if (i7 == 3) {
                bundle.putString("android.intent.extra.focus", "vnd.android.cursor.item/genre");
            } else if (i7 == 4) {
                bundle.putString("android.intent.extra.focus", "vnd.android.cursor.item/artist");
            } else if (i7 == 5) {
                bundle.putString("android.intent.extra.focus", "vnd.android.cursor.item/playlist");
            }
            if (!TextUtils.isEmpty(xVar.b())) {
                bundle.putString("android.intent.extra.artist", xVar.b());
            }
            if (!TextUtils.isEmpty(xVar.a())) {
                bundle.putString("android.intent.extra.album", xVar.a());
            }
            if (!TextUtils.isEmpty(xVar.c())) {
                bundle.putString("android.intent.extra.genre", xVar.c());
            }
            if (!TextUtils.isEmpty(xVar.d())) {
                bundle.putString("android.intent.extra.playlist", xVar.d());
            }
            if (!TextUtils.isEmpty(xVar.f())) {
                bundle.putString("android.intent.extra.title", xVar.f());
            }
        }
        mediaControllerCompatO.i().d(str, bundle);
        return true;
    }

    public void K() {
        D(n());
    }

    public void L(MediaDescriptionCompat mediaDescriptionCompat) {
        MediaControllerCompat mediaControllerCompatO = o();
        if (mediaControllerCompatO == null) {
            h3.i.b("MediaPlayController").d("execute 12:%s failed: mediaControllerCompat is null", mediaDescriptionCompat);
        } else {
            h3.i.b("MediaPlayController").b("execute 12:%s", mediaDescriptionCompat);
            mediaControllerCompatO.m(mediaDescriptionCompat);
        }
    }

    public void M(MediaControllerCompat.a aVar) {
        this.f6956h.remove(aVar);
    }

    public void N(e eVar) {
        this.f6957i.remove(eVar);
    }

    public boolean O(long j7) {
        MediaControllerCompat mediaControllerCompatO = o();
        if (mediaControllerCompatO == null) {
            h3.i.b("MediaPlayController").d("execute 5 failed: mediaControllerCompat is null", new Object[0]);
            return false;
        }
        h3.i.b("MediaPlayController").b("execute 5", new Object[0]);
        mediaControllerCompatO.i().e(j7);
        return true;
    }

    public void P(String str, Bundle bundle) {
        MediaControllerCompat mediaControllerCompatO = o();
        if (mediaControllerCompatO == null) {
            h3.i.b("MediaPlayController").d("execute 8 failed: mediaControllerCompat is null", new Object[0]);
        } else {
            h3.i.b("MediaPlayController").b("execute 8:%s", str);
            mediaControllerCompatO.i().f(str, bundle);
        }
    }

    public boolean Q(int i7) {
        if (this.f6954f == i7) {
            h3.i.b("MediaPlayController").b("return to setRepeatMode:%s", Integer.valueOf(i7));
            return true;
        }
        this.f6954f = i7;
        MediaControllerCompat mediaControllerCompatO = o();
        if (mediaControllerCompatO == null) {
            h3.i.b("MediaPlayController").d("execute 14:%s failed: mediaControllerCompat is null", Integer.valueOf(i7));
            return false;
        }
        h3.i.b("MediaPlayController").b("execute 14:%s", Integer.valueOf(i7));
        mediaControllerCompatO.i().g(i7);
        return true;
    }

    public boolean R(int i7) {
        MediaControllerCompat mediaControllerCompatO = o();
        if (mediaControllerCompatO == null) {
            h3.i.b("MediaPlayController").d("execute 15:%s failed: mediaControllerCompat is null", Integer.valueOf(i7));
            return false;
        }
        h3.i.b("MediaPlayController").b("execute 15:%s", Integer.valueOf(i7));
        mediaControllerCompatO.i().h(i7);
        return true;
    }

    public boolean S() {
        MediaControllerCompat mediaControllerCompatO = o();
        if (mediaControllerCompatO == null) {
            h3.i.b("MediaPlayController").d("execute 4 failed: mediaControllerCompat is null", new Object[0]);
            return false;
        }
        h3.i.b("MediaPlayController").b("execute 4", new Object[0]);
        mediaControllerCompatO.i().i();
        return true;
    }

    public boolean T() {
        MediaControllerCompat mediaControllerCompatO = o();
        if (mediaControllerCompatO == null) {
            h3.i.b("MediaPlayController").d("execute 6 failed: mediaControllerCompat is null", new Object[0]);
            return false;
        }
        h3.i.b("MediaPlayController").b("execute 6", new Object[0]);
        mediaControllerCompatO.i().j();
        return true;
    }

    public void U(long j7) {
        MediaControllerCompat mediaControllerCompatO = o();
        if (mediaControllerCompatO == null) {
            h3.i.b("MediaPlayController").d("execute 7 failed: mediaControllerCompat is null", new Object[0]);
        } else {
            h3.i.b("MediaPlayController").b("execute 7", new Object[0]);
            mediaControllerCompatO.i().k(j7);
        }
    }

    public void V() {
        this.f6958j.schedule(new Runnable() { // from class: i3.k
            @Override // java.lang.Runnable
            public final void run() {
                this.f6947e.B();
            }
        }, 500L, TimeUnit.MILLISECONDS);
    }

    public boolean W() {
        MediaControllerCompat mediaControllerCompatO = o();
        if (mediaControllerCompatO == null) {
            h3.i.b("MediaPlayController").d("execute 3 failed: mediaControllerCompat is null", new Object[0]);
            return false;
        }
        h3.i.b("MediaPlayController").b("execute 3", new Object[0]);
        mediaControllerCompatO.i().l();
        return true;
    }

    public void h(MediaControllerCompat.a aVar) {
        this.f6956h.add(aVar);
        C(aVar);
    }

    public void i(e eVar) {
        this.f6957i.add(eVar);
        eVar.a((int) u.a(this.f6951c).longValue());
    }

    public void j(MediaDescriptionCompat mediaDescriptionCompat, d dVar) {
        MediaControllerCompat mediaControllerCompatO = o();
        if (mediaControllerCompatO == null) {
            h3.i.b("MediaPlayController").d("execute 11:%s failed: mediaControllerCompat is null", mediaDescriptionCompat);
            return;
        }
        h3.i.b("MediaPlayController").b("execute 11:%s", mediaDescriptionCompat);
        mediaControllerCompatO.a(mediaDescriptionCompat);
        dVar.b();
    }

    public long k() {
        PlaybackStateCompat playbackStateCompatQ = q();
        if (playbackStateCompatQ == null) {
            return 0L;
        }
        return playbackStateCompatQ.h();
    }

    public Resources m() {
        return this.f6955g.A();
    }

    public MediaMetadataCompat n() {
        MediaControllerCompat mediaControllerCompatO = o();
        MediaMetadataCompat mediaMetadataCompatC = mediaControllerCompatO != null ? mediaControllerCompatO.c() : null;
        return mediaMetadataCompatC == null ? l() : TextUtils.isEmpty(mediaMetadataCompatC.m("android.media.metadata.TITLE")) ? l() : mediaMetadataCompatC;
    }

    public int p(PlaybackStateCompat playbackStateCompat) {
        if (playbackStateCompat != null) {
            return playbackStateCompat.t();
        }
        return 0;
    }

    public PlaybackStateCompat q() {
        if (o() == null) {
            return null;
        }
        return o().e();
    }

    public List<MediaSessionCompat.QueueItem> r() {
        MediaControllerCompat mediaControllerCompatO = o();
        if (mediaControllerCompatO == null) {
            h3.i.b("MediaPlayController").d("execute 9 mediaControllerCompat is null", new Object[0]);
            return new ArrayList();
        }
        h3.i.b("MediaPlayController").b("execute 9", new Object[0]);
        List<MediaSessionCompat.QueueItem> listF = mediaControllerCompatO.f();
        return listF == null ? new ArrayList() : listF;
    }

    public int s() {
        MediaControllerCompat mediaControllerCompatO = o();
        if (mediaControllerCompatO == null) {
            h3.i.b("MediaPlayController").d("getRepeatMode failed mediaControllerCompat is null", new Object[0]);
            return 0;
        }
        int iG = mediaControllerCompatO.g();
        h3.i.b("MediaPlayController").b("getRepeatMode:" + iG, new Object[0]);
        return iG;
    }

    public int t() {
        MediaControllerCompat mediaControllerCompatO = o();
        if (mediaControllerCompatO != null) {
            return mediaControllerCompatO.h();
        }
        h3.i.b("MediaPlayController").d("getShuffleMode failed mediaControllerCompat is null", new Object[0]);
        return 0;
    }

    protected void u(MediaMetadataCompat mediaMetadataCompat) {
    }

    protected void v(PlaybackStateCompat playbackStateCompat) {
    }

    public boolean w() {
        MediaControllerCompat mediaControllerCompatO = o();
        if (mediaControllerCompatO != null) {
            return mediaControllerCompatO.j();
        }
        h3.i.b("MediaPlayController").d("execute 10 mediaControllerCompat is null", new Object[0]);
        return false;
    }

    public boolean x() {
        return y(q());
    }

    public boolean y(PlaybackStateCompat playbackStateCompat) {
        int iP = p(playbackStateCompat);
        h3.i.b("MediaPlayController").h("isPlaying: playbackState =" + iP, new Object[0]);
        return iP == 6 || iP == 3;
    }

    public boolean z() {
        MediaControllerCompat mediaControllerCompatO = o();
        if (mediaControllerCompatO != null) {
            return (mediaControllerCompatO.b() & 4) != 0;
        }
        h3.i.b("MediaPlayController").d("isSupportAddQueItem error mediaControllerCompat is null", new Object[0]);
        return false;
    }
}
