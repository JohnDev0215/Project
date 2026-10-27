package v3;

import android.support.v4.media.MediaBrowserCompat;
import java.util.List;

/* JADX INFO: compiled from: MediaItemCallBack.java */
/* JADX INFO: loaded from: classes.dex */
public class c extends b<MediaBrowserCompat.MediaItem> {
    public c(List<MediaBrowserCompat.MediaItem> list, List<MediaBrowserCompat.MediaItem> list2) {
        super(list, list2);
    }

    @Override // v3.b, androidx.recyclerview.widget.f.b
    public boolean a(int i7, int i8) {
        if (f(this.f9676a, i7) || f(this.f9677b, i8)) {
            return false;
        }
        return i3.c.i((MediaBrowserCompat.MediaItem) this.f9676a.get(i7), (MediaBrowserCompat.MediaItem) this.f9677b.get(i8));
    }

    @Override // v3.b, androidx.recyclerview.widget.f.b
    public boolean b(int i7, int i8) {
        if (f(this.f9676a, i7) || f(this.f9677b, i8)) {
            return false;
        }
        return i3.c.j((MediaBrowserCompat.MediaItem) this.f9676a.get(i7), (MediaBrowserCompat.MediaItem) this.f9677b.get(i8));
    }
}
