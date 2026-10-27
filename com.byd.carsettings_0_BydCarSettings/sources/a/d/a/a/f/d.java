package a.d.a.a.f;

import android.media.session.MediaSession;
import androidx.annotation.RequiresApi;

/* JADX INFO: compiled from: MediaSessionCompatApi21.java */
/* JADX INFO: loaded from: classes.dex */
@RequiresApi(21)
public class d {

    /* JADX INFO: compiled from: MediaSessionCompatApi21.java */
    public static class a {
        public static Object a(Object obj) {
            return ((MediaSession.QueueItem) obj).getDescription();
        }

        public static long b(Object obj) {
            return ((MediaSession.QueueItem) obj).getQueueId();
        }
    }

    public static Object a(Object obj) {
        if (obj instanceof MediaSession.Token) {
            return obj;
        }
        throw new IllegalArgumentException("token is not a valid MediaSession.Token object");
    }
}
