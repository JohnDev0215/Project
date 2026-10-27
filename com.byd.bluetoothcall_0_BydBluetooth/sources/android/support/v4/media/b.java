package android.support.v4.media;

import android.media.browse.MediaBrowser;
import android.os.Bundle;
import android.support.v4.media.a;
import android.support.v4.media.session.MediaSessionCompat;
import java.util.List;

/* JADX INFO: compiled from: MediaBrowserCompatApi26.java */
/* JADX INFO: loaded from: classes.dex */
class b {

    /* JADX INFO: compiled from: MediaBrowserCompatApi26.java */
    interface a extends a.d {
        void a(String str, List<?> list, Bundle bundle);

        void b(String str, Bundle bundle);
    }

    /* JADX INFO: renamed from: android.support.v4.media.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: MediaBrowserCompatApi26.java */
    static class C0004b<T extends a> extends a.e<T> {
        C0004b(T t) {
            super(t);
        }

        @Override // android.media.browse.MediaBrowser.SubscriptionCallback
        public void onChildrenLoaded(String str, List<MediaBrowser.MediaItem> list, Bundle bundle) {
            MediaSessionCompat.a(bundle);
            ((a) this.f106a).a(str, list, bundle);
        }

        @Override // android.media.browse.MediaBrowser.SubscriptionCallback
        public void onError(String str, Bundle bundle) {
            MediaSessionCompat.a(bundle);
            ((a) this.f106a).b(str, bundle);
        }
    }

    static Object a(a aVar) {
        return new C0004b(aVar);
    }

    public static void b(Object obj, String str, Bundle bundle, Object obj2) {
        ((MediaBrowser) obj).subscribe(str, bundle, (MediaBrowser.SubscriptionCallback) obj2);
    }
}
