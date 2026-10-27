package a.d.a.b;

import a.d.a.b.a;
import android.media.browse.MediaBrowser;
import android.os.Bundle;
import android.support.v4.media.session.MediaSessionCompat;
import java.util.List;

/* JADX INFO: compiled from: MediaBrowserCompatApi26.java */
/* JADX INFO: loaded from: classes.dex */
public class b {

    /* JADX INFO: compiled from: MediaBrowserCompatApi26.java */
    public interface a extends a.d {
        void a(String str, List<?> list, Bundle bundle);

        void b(String str, Bundle bundle);
    }

    /* JADX INFO: renamed from: a.d.a.b.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: MediaBrowserCompatApi26.java */
    public static class C0003b<T extends a> extends a.e<T> {
        public C0003b(T t) {
            super(t);
        }

        @Override // android.media.browse.MediaBrowser.SubscriptionCallback
        public void onChildrenLoaded(String str, List<MediaBrowser.MediaItem> list, Bundle bundle) {
            MediaSessionCompat.a(bundle);
            ((a) this.f57a).a(str, list, bundle);
        }

        @Override // android.media.browse.MediaBrowser.SubscriptionCallback
        public void onError(String str, Bundle bundle) {
            MediaSessionCompat.a(bundle);
            ((a) this.f57a).b(str, bundle);
        }
    }

    public static Object a(a aVar) {
        return new C0003b(aVar);
    }
}
