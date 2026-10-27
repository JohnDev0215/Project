package defpackage;

import android.content.Context;
import android.net.Uri;

/* JADX INFO: renamed from: lN, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1105lN implements AutoCloseable {
    public final Context s;
    public final Uri t;

    public C1105lN(Context context, Uri uri) {
        context.getClass();
        this.s = context;
        uri.getClass();
        this.t = uri;
    }

    @Override // java.lang.AutoCloseable
    public void close() {
    }
}
