package android.support.v4.media;

import P8.n;
import android.os.Bundle;
import android.os.Parcelable;
import g.C1273d;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
class MediaBrowserCompat$SearchResultReceiver extends C1273d {
    @Override // g.C1273d
    public final void a(int i7, Bundle bundle) {
        if (bundle != null) {
            bundle = n.W(bundle);
        }
        if (i7 != 0 || bundle == null || !bundle.containsKey("search_results")) {
            throw null;
        }
        Parcelable[] parcelableArray = bundle.getParcelableArray("search_results");
        parcelableArray.getClass();
        ArrayList arrayList = new ArrayList(parcelableArray.length);
        for (Parcelable parcelable : parcelableArray) {
            arrayList.add((MediaBrowserCompat$MediaItem) parcelable);
        }
        throw null;
    }
}
