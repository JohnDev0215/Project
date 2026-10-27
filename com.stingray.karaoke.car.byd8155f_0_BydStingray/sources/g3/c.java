package G3;

import K3.m;
import N3.d;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.net.Uri;

/* JADX INFO: loaded from: classes.dex */
public final class c implements b {
    @Override // G3.b
    public final String a(Object obj, m mVar) {
        Uri uri = (Uri) obj;
        if (!A7.m.a(uri.getScheme(), "android.resource")) {
            return uri.toString();
        }
        StringBuilder sb = new StringBuilder();
        sb.append(uri);
        sb.append('-');
        Configuration configuration = mVar.f3565a.getResources().getConfiguration();
        Bitmap.Config config = d.f4575a;
        sb.append(configuration.uiMode & 48);
        return sb.toString();
    }
}
