package o2;

import android.media.AudioProfile;
import android.media.MediaDrm;
import android.media.metrics.PlaybackStateEvent;

/* JADX INFO: loaded from: classes.dex */
public abstract /* synthetic */ class i {
    public static /* bridge */ /* synthetic */ AudioProfile c(Object obj) {
        return (AudioProfile) obj;
    }

    public static /* bridge */ /* synthetic */ MediaDrm.PlaybackComponent e(Object obj) {
        return (MediaDrm.PlaybackComponent) obj;
    }

    public static /* synthetic */ PlaybackStateEvent.Builder f() {
        return new PlaybackStateEvent.Builder();
    }
}
