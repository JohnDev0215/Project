package a.d.a.a.f;

import android.media.session.PlaybackState;
import android.os.Bundle;
import androidx.annotation.RequiresApi;

/* JADX INFO: compiled from: PlaybackStateCompatApi22.java */
/* JADX INFO: loaded from: classes.dex */
@RequiresApi(22)
public class f {
    public static Bundle a(Object obj) {
        return ((PlaybackState) obj).getExtras();
    }
}
