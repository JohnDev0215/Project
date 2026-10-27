package org.chromium.components.favicon;

import J.N;
import android.graphics.Bitmap;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.content_public.browser.BrowserContextHandle;
import org.chromium.url.GURL;

/* JADX INFO: loaded from: classes2.dex */
public final class LargeIconBridge {
    public final BrowserContextHandle a;
    public long b;
    public org.chromium.components.favicon.a c;

    public interface LargeIconCallback {
        @CalledByNative
        void onLargeIconAvailable(Bitmap bitmap, int i, boolean z, int i2);
    }

    public class a implements LargeIconCallback {
        public final /* synthetic */ GURL e;
        public final /* synthetic */ LargeIconCallback f;

        public a(GURL gurl, LargeIconCallback largeIconCallback) {
            this.e = gurl;
            this.f = largeIconCallback;
        }

        @Override // org.chromium.components.favicon.LargeIconBridge.LargeIconCallback
        public final void onLargeIconAvailable(Bitmap bitmap, int i, boolean z, int i2) {
            LargeIconBridge.this.c.put(this.e, new b(bitmap, i, z, i2));
            this.f.onLargeIconAvailable(bitmap, i, z, i2);
        }
    }

    public static class b {
        public final Bitmap a;
        public final int b;
        public final boolean c;
        public final int d;

        public b(Bitmap bitmap, int i, boolean z, int i2) {
            this.a = bitmap;
            this.b = i;
            this.c = z;
            this.d = i2;
        }
    }

    public LargeIconBridge(BrowserContextHandle browserContextHandle) {
        this.b = N.MwrhffLX();
        this.a = browserContextHandle;
    }

    public final void a() {
        long j = this.b;
        if (j != 0) {
            N.MthPKzcb(j);
            this.b = 0L;
        }
    }

    public final boolean b(GURL gurl, int i, LargeIconCallback largeIconCallback) {
        org.chromium.components.favicon.a aVar = this.c;
        if (aVar == null) {
            return N.M1pABITV(this.b, this.a, gurl, i, largeIconCallback);
        }
        b bVar = aVar.get(gurl);
        if (bVar != null) {
            largeIconCallback.onLargeIconAvailable(bVar.a, bVar.b, bVar.c, bVar.d);
            return true;
        }
        return N.M1pABITV(this.b, this.a, gurl, i, new a(gurl, largeIconCallback));
    }

    public LargeIconBridge() {
        this.b = 0L;
        this.a = null;
    }
}
