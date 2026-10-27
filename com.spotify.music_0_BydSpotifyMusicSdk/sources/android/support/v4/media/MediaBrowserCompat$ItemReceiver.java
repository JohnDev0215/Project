package android.support.v4.media;

import android.os.Bundle;
import android.os.Parcelable;
import p.b52;
import p.bi1;

/* JADX INFO: loaded from: classes.dex */
class MediaBrowserCompat$ItemReceiver extends b52 {
    @Override // p.b52
    public final void a(int i, Bundle bundle) {
        if (bundle != null) {
            bundle = bi1.f(bundle);
        }
        if (i != 0 || bundle == null || !bundle.containsKey("media_item")) {
            throw null;
        }
        Parcelable parcelable = bundle.getParcelable("media_item");
        if (parcelable != null && !(parcelable instanceof MediaBrowserCompat$MediaItem)) {
            throw null;
        }
        throw null;
    }
}
