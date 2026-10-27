package defpackage;

import java.util.HashSet;
import java.util.List;
import org.chromium.content.browser.MediaSessionImpl;
import org.chromium.services.media_session.MediaImage;
import org.chromium.services.media_session.MediaMetadata;
import org.chromium.services.media_session.MediaPosition;

/* JADX INFO: compiled from: MediaSessionObserver.java */
/* JADX INFO: loaded from: classes3.dex */
public abstract class ke1 {
    public MediaSessionImpl a;

    public ke1(MediaSessionImpl mediaSessionImpl) {
        if (mediaSessionImpl instanceof MediaSessionImpl) {
            this.a = mediaSessionImpl;
            mediaSessionImpl.l.a(this);
            Boolean bool = mediaSessionImpl.o;
            if (bool != null) {
                f(mediaSessionImpl.n, bool.booleanValue());
            }
            MediaMetadata mediaMetadata = mediaSessionImpl.p;
            if (mediaMetadata != null) {
                d(mediaMetadata);
            }
            List<MediaImage> list = mediaSessionImpl.q;
            if (list != null) {
                b(list);
            }
            MediaPosition mediaPosition = mediaSessionImpl.s;
            if (mediaPosition != null) {
                e(mediaPosition);
            }
            HashSet<Integer> hashSet = mediaSessionImpl.r;
            if (hashSet != null) {
                a(hashSet);
            }
        }
    }

    public void c() {
    }

    public void a(HashSet hashSet) {
    }

    public void b(List<MediaImage> list) {
    }

    public void d(MediaMetadata mediaMetadata) {
    }

    public void e(MediaPosition mediaPosition) {
    }

    public void f(boolean z, boolean z2) {
    }
}
