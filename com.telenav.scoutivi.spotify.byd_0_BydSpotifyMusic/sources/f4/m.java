package f4;

import android.os.Bundle;
import android.support.v4.media.MediaBrowserCompat;
import com.spotify.auto.parcel.PlaybackSpeedActionParcelable;
import i3.s;
import java.util.ArrayList;

/* JADX INFO: compiled from: SpotifyMediaManager.java */
/* JADX INFO: loaded from: classes.dex */
public class m extends i3.j {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final ArrayList<String> f6388b;

    /* JADX INFO: compiled from: SpotifyMediaManager.java */
    class a extends MediaBrowserCompat.c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ q4.f f6389a;

        a(q4.f fVar) {
            this.f6389a = fVar;
        }

        @Override // android.support.v4.media.MediaBrowserCompat.c
        public void a(String str, Bundle bundle, Bundle bundle2) {
            super.a(str, bundle, bundle2);
            this.f6389a.a(new Throwable("addFavorite error"));
        }

        @Override // android.support.v4.media.MediaBrowserCompat.c
        public void c(String str, Bundle bundle, Bundle bundle2) {
            super.c(str, bundle, bundle2);
            this.f6389a.b(Boolean.TRUE);
        }
    }

    /* JADX INFO: compiled from: SpotifyMediaManager.java */
    class b extends MediaBrowserCompat.c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ q4.f f6391a;

        b(q4.f fVar) {
            this.f6391a = fVar;
        }

        @Override // android.support.v4.media.MediaBrowserCompat.c
        public void a(String str, Bundle bundle, Bundle bundle2) {
            super.a(str, bundle, bundle2);
            this.f6391a.a(new Throwable("removeFavorite error"));
        }

        @Override // android.support.v4.media.MediaBrowserCompat.c
        public void c(String str, Bundle bundle, Bundle bundle2) {
            super.c(str, bundle, bundle2);
            this.f6391a.b(Boolean.TRUE);
        }
    }

    /* JADX INFO: compiled from: SpotifyMediaManager.java */
    class c extends MediaBrowserCompat.c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ q4.f f6393a;

        c(q4.f fVar) {
            this.f6393a = fVar;
        }

        @Override // android.support.v4.media.MediaBrowserCompat.c
        public void a(String str, Bundle bundle, Bundle bundle2) {
            super.a(str, bundle, bundle2);
            this.f6393a.a(new Throwable("getFavorite error"));
        }

        @Override // android.support.v4.media.MediaBrowserCompat.c
        public void c(String str, Bundle bundle, Bundle bundle2) {
            super.c(str, bundle, bundle2);
            if (bundle2 == null || !bundle2.getBoolean("result")) {
                this.f6393a.b(Boolean.FALSE);
            } else {
                this.f6393a.b(Boolean.TRUE);
            }
        }
    }

    /* JADX INFO: compiled from: SpotifyMediaManager.java */
    class d extends MediaBrowserCompat.c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ q4.f f6395a;

        d(q4.f fVar) {
            this.f6395a = fVar;
        }

        @Override // android.support.v4.media.MediaBrowserCompat.c
        public void a(String str, Bundle bundle, Bundle bundle2) {
            super.a(str, bundle, bundle2);
            this.f6395a.a(new Throwable("getSpeedList error"));
        }

        @Override // android.support.v4.media.MediaBrowserCompat.c
        public void c(String str, Bundle bundle, Bundle bundle2) {
            super.c(str, bundle, bundle2);
            if (bundle2 != null) {
                this.f6395a.b(m.this.R(bundle2.getParcelableArrayList("result")));
            } else {
                this.f6395a.b(new ArrayList());
                h3.i.b("MediaManager").g("speed list is empty");
            }
        }
    }

    public m(s sVar) {
        super(sVar);
        ArrayList<String> arrayList = new ArrayList<>();
        this.f6388b = arrayList;
        arrayList.add("0.8");
        arrayList.add("1.0");
        arrayList.add("1.2");
        arrayList.add("1.5");
        arrayList.add("2.0");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void I(MediaBrowserCompat mediaBrowserCompat, Bundle bundle, q4.f fVar) {
        mediaBrowserCompat.g("collection-add", bundle, new a(fVar));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ q4.i J(final Bundle bundle, final MediaBrowserCompat mediaBrowserCompat) {
        return q4.e.b(new q4.h() { // from class: f4.f
            @Override // q4.h
            public final void a(q4.f fVar) {
                this.f6372a.I(mediaBrowserCompat, bundle, fVar);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void K(MediaBrowserCompat mediaBrowserCompat, Bundle bundle, q4.f fVar) {
        mediaBrowserCompat.g("collection-get", bundle, new c(fVar));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ q4.i L(final Bundle bundle, final MediaBrowserCompat mediaBrowserCompat) {
        return q4.e.b(new q4.h() { // from class: f4.h
            @Override // q4.h
            public final void a(q4.f fVar) {
                this.f6378a.K(mediaBrowserCompat, bundle, fVar);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void M(MediaBrowserCompat mediaBrowserCompat, q4.f fVar) {
        mediaBrowserCompat.g("playbackSpeed-getList", new Bundle(), new d(fVar));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ q4.i N(final MediaBrowserCompat mediaBrowserCompat) {
        return q4.e.b(new q4.h() { // from class: f4.e
            @Override // q4.h
            public final void a(q4.f fVar) {
                this.f6370a.M(mediaBrowserCompat, fVar);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void O(MediaBrowserCompat mediaBrowserCompat, Bundle bundle, q4.f fVar) {
        mediaBrowserCompat.g("collection-remove", bundle, new b(fVar));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ q4.i P(final Bundle bundle, final MediaBrowserCompat mediaBrowserCompat) {
        return q4.e.b(new q4.h() { // from class: f4.g
            @Override // q4.h
            public final void a(q4.f fVar) {
                this.f6375a.O(mediaBrowserCompat, bundle, fVar);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ArrayList<PlaybackSpeedActionParcelable> R(ArrayList<PlaybackSpeedActionParcelable> arrayList) {
        if (arrayList == null || arrayList.size() == 0) {
            return arrayList;
        }
        ArrayList<PlaybackSpeedActionParcelable> arrayList2 = new ArrayList<>();
        for (PlaybackSpeedActionParcelable playbackSpeedActionParcelable : arrayList) {
            if (this.f6388b.contains(playbackSpeedActionParcelable.f5009e)) {
                arrayList2.add(playbackSpeedActionParcelable);
            } else {
                h3.i.b("MediaManager").b("2 " + playbackSpeedActionParcelable.f5009e, new Object[0]);
            }
        }
        return arrayList2;
    }

    public q4.e<Boolean> F(MediaBrowserCompat.MediaItem mediaItem) {
        final Bundle bundle = new Bundle();
        bundle.putString("uri", i3.c.d(mediaItem));
        return this.f6938a.x().c(new v4.e() { // from class: f4.k
            @Override // v4.e
            public final Object a(Object obj) {
                return this.f6384a.J(bundle, (MediaBrowserCompat) obj);
            }
        });
    }

    public q4.e<Boolean> G(MediaBrowserCompat.MediaItem mediaItem) {
        final Bundle bundle = new Bundle();
        bundle.putString("uri", i3.c.d(mediaItem));
        return this.f6938a.x().c(new v4.e() { // from class: f4.j
            @Override // v4.e
            public final Object a(Object obj) {
                return this.f6382a.L(bundle, (MediaBrowserCompat) obj);
            }
        });
    }

    public q4.e<ArrayList<PlaybackSpeedActionParcelable>> H() {
        return this.f6938a.x().c(new v4.e() { // from class: f4.i
            @Override // v4.e
            public final Object a(Object obj) {
                return this.f6381a.N((MediaBrowserCompat) obj);
            }
        });
    }

    public q4.e<Boolean> Q(MediaBrowserCompat.MediaItem mediaItem) {
        final Bundle bundle = new Bundle();
        bundle.putString("uri", i3.c.d(mediaItem));
        return this.f6938a.x().c(new v4.e() { // from class: f4.l
            @Override // v4.e
            public final Object a(Object obj) {
                return this.f6386a.P(bundle, (MediaBrowserCompat) obj);
            }
        });
    }
}
