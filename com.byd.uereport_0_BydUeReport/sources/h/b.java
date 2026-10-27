package h;

import android.media.browse.MediaBrowser;
import android.os.Bundle;
import android.support.v4.media.session.MediaSessionCompat;
import h.a;
import java.util.List;

/* JADX INFO: compiled from: MediaBrowserCompatApi26.java */
/* JADX INFO: loaded from: classes.dex */
public class b {

    /* JADX INFO: compiled from: MediaBrowserCompatApi26.java */
    public interface a extends a.d {
        void a(String str, List<?> list, Bundle bundle);

        void b(String str, Bundle bundle);
    }

    /* JADX INFO: renamed from: h.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: MediaBrowserCompatApi26.java */
    public static class C0049b<T extends a> extends a.e<T> {
        public C0049b(T t7) {
            super(t7);
        }

        @Override // android.media.browse.MediaBrowser.SubscriptionCallback
        public void onChildrenLoaded(String str, List<MediaBrowser.MediaItem> list, Bundle bundle) {
            MediaSessionCompat.a(bundle);
            ((a) this.f5103a).a(str, list, bundle);
        }

        @Override // android.media.browse.MediaBrowser.SubscriptionCallback
        public void onError(String str, Bundle bundle) {
            MediaSessionCompat.a(bundle);
            ((a) this.f5103a).b(str, bundle);
        }
    }

    public static Object a(a aVar) {
        return new C0049b(aVar);
    }
}
