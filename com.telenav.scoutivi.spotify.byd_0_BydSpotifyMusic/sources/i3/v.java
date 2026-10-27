package i3;

import android.os.Bundle;
import android.support.v4.media.session.MediaSessionCompat;

/* JADX INFO: compiled from: QueueItemWrapper.java */
/* JADX INFO: loaded from: classes.dex */
public class v {
    public static String a(MediaSessionCompat.QueueItem queueItem) {
        return d(queueItem.h().h());
    }

    public static String b(MediaSessionCompat.QueueItem queueItem) {
        return d(queueItem.h().m());
    }

    public static long c(MediaSessionCompat.QueueItem queueItem) {
        return queueItem.j();
    }

    private static String d(Object obj) {
        return obj != null ? obj.toString() : "";
    }

    public static String e(MediaSessionCompat.QueueItem queueItem) {
        return d(queueItem.h().p());
    }

    public static String f(MediaSessionCompat.QueueItem queueItem) {
        return d(queueItem.h().q());
    }

    public static boolean g(MediaSessionCompat.QueueItem queueItem) {
        Bundle bundleC = queueItem.h().c();
        return bundleC != null && bundleC.getLong("android.media.IS_EXPLICIT", 0L) == 1;
    }
}
