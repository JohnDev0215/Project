package h;

import android.media.MediaDescription;
import android.net.Uri;

/* JADX INFO: compiled from: MediaDescriptionCompatApi23.java */
/* JADX INFO: loaded from: classes.dex */
public class d {

    /* JADX INFO: compiled from: MediaDescriptionCompatApi23.java */
    public static class a {
        public static void a(Object obj, Uri uri) {
            ((MediaDescription.Builder) obj).setMediaUri(uri);
        }
    }

    public static Uri a(Object obj) {
        return ((MediaDescription) obj).getMediaUri();
    }
}
