package h;

import android.media.MediaMetadata;
import android.os.Parcel;

/* JADX INFO: compiled from: MediaMetadataCompatApi21.java */
/* JADX INFO: loaded from: classes.dex */
public class e {
    public static void a(Object obj, Parcel parcel, int i8) {
        ((MediaMetadata) obj).writeToParcel(parcel, i8);
    }
}
