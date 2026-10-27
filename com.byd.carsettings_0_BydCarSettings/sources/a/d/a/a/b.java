package a.d.a.a;

import a.d.a.a.a;
import android.media.browse.MediaBrowser;
import android.os.Bundle;
import android.support.v4.media.session.MediaSessionCompat;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import java.util.List;

/* JADX INFO: compiled from: MediaBrowserCompatApi26.java */
/* JADX INFO: loaded from: classes.dex */
@RequiresApi(26)
public class b {

    /* JADX INFO: compiled from: MediaBrowserCompatApi26.java */
    public interface a extends a.d {
        void a(@NonNull String str, List<?> list, @NonNull Bundle bundle);

        void b(@NonNull String str, @NonNull Bundle bundle);
    }

    /* JADX INFO: renamed from: a.d.a.a.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: MediaBrowserCompatApi26.java */
    public static class C0003b<T extends a> extends a.e<T> {
        public C0003b(T t) {
            super(t);
        }

        @Override // android.media.browse.MediaBrowser.SubscriptionCallback
        public void onChildrenLoaded(@NonNull String str, List<MediaBrowser.MediaItem> list, @NonNull Bundle bundle) {
            MediaSessionCompat.a(bundle);
            ((a) this.f57a).a(str, list, bundle);
        }

        @Override // android.media.browse.MediaBrowser.SubscriptionCallback
        public void onError(@NonNull String str, @NonNull Bundle bundle) {
            MediaSessionCompat.a(bundle);
            ((a) this.f57a).b(str, bundle);
        }
    }

    public static Object a(a aVar) {
        return new C0003b(aVar);
    }
}
