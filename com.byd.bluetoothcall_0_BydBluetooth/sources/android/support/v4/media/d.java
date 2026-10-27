package android.support.v4.media;

import android.media.MediaDescription;
import android.net.Uri;

/* JADX INFO: compiled from: MediaDescriptionCompatApi23.java */
/* JADX INFO: loaded from: classes.dex */
class d {

    /* JADX INFO: compiled from: MediaDescriptionCompatApi23.java */
    static class a {
        public static void a(Object obj, Uri uri) {
            ((MediaDescription.Builder) obj).setMediaUri(uri);
        }
    }

    public static Uri a(Object obj) {
        return ((MediaDescription) obj).getMediaUri();
    }
}
