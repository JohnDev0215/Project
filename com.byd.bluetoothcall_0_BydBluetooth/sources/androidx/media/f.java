package androidx.media;

import android.content.Context;
import android.media.browse.MediaBrowser;
import android.os.Parcel;
import android.service.media.MediaBrowserService;
import androidx.media.e;

/* JADX INFO: compiled from: MediaBrowserServiceCompatApi23.java */
/* JADX INFO: loaded from: classes.dex */
class f {

    /* JADX INFO: compiled from: MediaBrowserServiceCompatApi23.java */
    static class a extends e.b {
        a(Context context, b bVar) {
            super(context, bVar);
        }

        @Override // android.service.media.MediaBrowserService
        public void onLoadItem(String str, MediaBrowserService.Result<MediaBrowser.MediaItem> result) {
            ((b) this.f1591b).d(str, new e.c<>(result));
        }
    }

    /* JADX INFO: compiled from: MediaBrowserServiceCompatApi23.java */
    public interface b extends e.d {
        void d(String str, e.c<Parcel> cVar);
    }

    public static Object a(Context context, b bVar) {
        return new a(context, bVar);
    }
}
