package defpackage;

import android.graphics.Bitmap;
import defpackage.ax0;
import org.chromium.base.Callback;
import org.chromium.components.favicon.LargeIconBridge;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: WebFeedFaviconFetcher.java */
/* JADX INFO: loaded from: classes.dex */
public final class xq3 {
    public final LargeIconBridge a;
    public final ax0 b;

    /* JADX INFO: compiled from: WebFeedFaviconFetcher.java */
    public class a {
        public GURL a;
        public int b;
        public int c;
        public Callback<Bitmap> d;

        public a() {
        }
    }

    public xq3(LargeIconBridge largeIconBridge, ax0 ax0Var) {
        this.a = largeIconBridge;
        this.b = ax0Var;
    }

    public final void a(int i, int i2, GURL gurl, GURL gurl2, Callback<Bitmap> callback) {
        a aVar = new a();
        aVar.b = i;
        aVar.c = i2;
        aVar.a = gurl;
        aVar.d = callback;
        if (gurl2 == null || !gurl2.b) {
            this.a.b(gurl, i, new gx(11, aVar));
            return;
        }
        String strJ = gurl2.j();
        int i3 = aVar.b;
        this.b.d(ax0.a.a(i3, i3, strJ, "Feed"), new xr(18, aVar));
    }
}
