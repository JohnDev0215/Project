package p;

import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.net.Uri;

/* JADX INFO: loaded from: classes.dex */
public final class mr2 implements n81 {
    @Override // p.n81
    public final String a(Object obj, br1 br1Var) {
        Uri uri = (Uri) obj;
        if (!v03.b(uri.getScheme(), "android.resource")) {
            return uri.toString();
        }
        StringBuilder sb = new StringBuilder();
        sb.append(uri);
        sb.append('-');
        Configuration configuration = br1Var.a.getResources().getConfiguration();
        Bitmap.Config[] configArr = i.a;
        sb.append(configuration.uiMode & 48);
        return sb.toString();
    }
}
