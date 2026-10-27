package a.d.a.a;

import android.media.MediaMetadata;
import android.os.Parcel;
import androidx.annotation.RequiresApi;

/* JADX INFO: compiled from: MediaMetadataCompatApi21.java */
/* JADX INFO: loaded from: classes.dex */
@RequiresApi(21)
public class e {
    public static void a(Object obj, Parcel parcel, int i) {
        ((MediaMetadata) obj).writeToParcel(parcel, i);
    }
}
