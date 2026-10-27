package f4;

import android.app.Application;
import android.content.Context;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.session.MediaControllerCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.text.TextUtils;
import i3.m;
import java.util.concurrent.CopyOnWriteArraySet;

/* JADX INFO: compiled from: MediaCollectionManager.java */
/* JADX INFO: loaded from: classes.dex */
public class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final CopyOnWriteArraySet<m.f> f6362a = new CopyOnWriteArraySet<>();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private b f6363b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Context f6364c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final i3.m f6365d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final MediaControllerCompat.a f6366e;

    /* JADX INFO: compiled from: MediaCollectionManager.java */
    class a extends MediaControllerCompat.a {
        a() {
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.a
        public void e(PlaybackStateCompat playbackStateCompat) {
            super.e(playbackStateCompat);
            h3.l lVarB = h3.i.b("MediaCollectionManager");
            StringBuilder sb = new StringBuilder();
            sb.append("onPlaybackStateChanged:");
            sb.append(playbackStateCompat == null ? "" : playbackStateCompat.toString());
            lVarB.g(sb.toString());
            c.this.c(playbackStateCompat);
        }
    }

    /* JADX INFO: compiled from: MediaCollectionManager.java */
    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final MediaMetadataCompat f6368a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final PlaybackStateCompat.CustomAction f6369b;

        public b(MediaMetadataCompat mediaMetadataCompat, PlaybackStateCompat.CustomAction customAction) {
            this.f6368a = mediaMetadataCompat;
            this.f6369b = customAction;
        }

        public PlaybackStateCompat.CustomAction a() {
            return this.f6369b;
        }

        public MediaMetadataCompat b() {
            return this.f6368a;
        }
    }

    public c(Application application, i3.m mVar) {
        a aVar = new a();
        this.f6366e = aVar;
        this.f6364c = application;
        this.f6365d = mVar;
        mVar.h(aVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(PlaybackStateCompat playbackStateCompat) {
        if (playbackStateCompat != null && !this.f6362a.isEmpty()) {
            for (m.f fVar : this.f6362a) {
                b bVar = this.f6363b;
                if (bVar != null) {
                    String strK = bVar.b().j().k();
                    PlaybackStateCompat.CustomAction customActionA = this.f6363b.a();
                    if (TextUtils.equals(strK, this.f6365d.n().j().k())) {
                        String strA = com.vivid.spotify.nowplaying.m.a(this.f6364c, customActionA, playbackStateCompat);
                        if (TextUtils.isEmpty(strA)) {
                            h3.i.b("MediaCollectionManager").d("failed to send custom action", new Object[0]);
                        } else {
                            h3.i.b("MediaCollectionManager").b("result:" + strA, new Object[0]);
                            fVar.a(strA);
                        }
                    }
                }
            }
        }
        this.f6363b = null;
    }

    public void b(m.f fVar) {
        this.f6362a.add(fVar);
    }

    public void d(m.f fVar) {
        this.f6362a.remove(fVar);
    }

    public void e(b bVar) {
        h3.i.b("MediaCollectionManager").g("a:" + bVar);
        this.f6363b = bVar;
    }
}
