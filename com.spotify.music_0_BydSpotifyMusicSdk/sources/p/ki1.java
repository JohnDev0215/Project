package p;

import android.content.Context;
import android.net.Uri;
import com.spotify.mediasession.imageprovider.MediaUriUtil$Transformation;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class ki1 {
    public final String a;
    public final Context b;

    public ki1(Context context, String str) {
        this.a = str;
        this.b = context;
    }

    public final Uri a(int i) {
        Uri.Builder builderScheme = new Uri.Builder().scheme("android.resource");
        Context context = this.b;
        return builderScheme.authority(context.getResources().getResourcePackageName(i)).appendPath(context.getResources().getResourceTypeName(i)).appendPath(context.getResources().getResourceEntryName(i)).build();
    }

    public final Uri b(Uri uri, MediaUriUtil$Transformation mediaUriUtil$Transformation) {
        if (uri.toString().startsWith("android.resource")) {
            return uri;
        }
        if (uri.toString().startsWith("spotify:")) {
            return c(null, Collections.singletonList(uri.toString()), mediaUriUtil$Transformation);
        }
        String authority = uri.getAuthority();
        return v03.y(authority) ? Uri.EMPTY : c(authority, uri.getPathSegments(), mediaUriUtil$Transformation);
    }

    public final Uri c(String str, List list, MediaUriUtil$Transformation mediaUriUtil$Transformation) {
        Uri.Builder builderAuthority = new Uri.Builder().scheme("content").authority(this.a);
        Iterator it = list.iterator();
        while (it.hasNext()) {
            builderAuthority.appendPath((String) it.next());
        }
        if (str != null) {
            builderAuthority.appendQueryParameter("cdn", str);
        }
        builderAuthority.appendQueryParameter("transformation", mediaUriUtil$Transformation.name());
        return builderAuthority.build();
    }
}
