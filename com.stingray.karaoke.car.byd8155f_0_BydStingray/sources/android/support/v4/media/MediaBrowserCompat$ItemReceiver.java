package android.support.v4.media;

import P8.n;
import android.os.Bundle;
import android.os.Parcelable;
import g.C1273d;

/* JADX INFO: loaded from: classes.dex */
class MediaBrowserCompat$ItemReceiver extends C1273d {
    @Override // g.C1273d
    public final void a(int i7, Bundle bundle) {
        if (bundle != null) {
            bundle = n.W(bundle);
        }
        if (i7 != 0 || bundle == null || !bundle.containsKey("media_item")) {
            throw null;
        }
        Parcelable parcelable = bundle.getParcelable("media_item");
        if (parcelable != null && !(parcelable instanceof MediaBrowserCompat$MediaItem)) {
            throw null;
        }
        throw null;
    }
}
