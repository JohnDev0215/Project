package defpackage;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.LruCache;
import org.chromium.base.task.PostTask;
import org.chromium.chrome.R$color;
import org.chromium.chrome.R$dimen;
import org.chromium.components.favicon.LargeIconBridge;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: FaviconFetcher.java */
/* JADX INFO: loaded from: classes2.dex */
public final class mk0 {
    public final LruCache<GURL, Integer> a;
    public final nw2<LargeIconBridge> b;
    public final int c;
    public final uc2 d;

    /* JADX INFO: compiled from: FaviconFetcher.java */
    public class a implements b {
        public final /* synthetic */ GURL e;
        public final /* synthetic */ b f;
        public final /* synthetic */ boolean g;

        public a(GURL gurl, b bVar, boolean z) {
            this.e = gurl;
            this.f = bVar;
            this.g = z;
        }

        @Override // mk0.b
        public final void c(int i, Bitmap bitmap) {
            b bVar = this.f;
            GURL gurl = this.e;
            mk0 mk0Var = mk0.this;
            if (bitmap != null) {
                mk0Var.a.put(gurl, Integer.valueOf(i));
                bVar.c(i, bitmap);
            } else {
                if (i == 1) {
                    mk0Var.a(2, this, gurl);
                    return;
                }
                if (i == 2) {
                    mk0Var.a.put(gurl, 3);
                    if (this.g) {
                        mk0Var.a(3, this, gurl);
                        return;
                    }
                }
                bVar.c(0, null);
            }
        }
    }

    /* JADX INFO: compiled from: FaviconFetcher.java */
    public interface b {
        void c(int i, Bitmap bitmap);
    }

    public mk0(Context context, hs hsVar) {
        this.b = hsVar;
        this.c = context.getResources().getDimensionPixelSize(R$dimen.omnibox_suggestion_favicon_size);
        int dimensionPixelSize = context.getResources().getDimensionPixelSize(R$dimen.tile_view_icon_size);
        this.d = new uc2(dimensionPixelSize, dimensionPixelSize, dimensionPixelSize / 2, context.getColor(R$color.default_favicon_background_color), context.getResources().getDimensionPixelSize(R$dimen.tile_view_icon_text_size));
        this.a = new LruCache<>(256);
    }

    public final void a(final int i, final b bVar, GURL gurl) {
        LargeIconBridge largeIconBridge = this.b.get();
        if ((i == 1 || i == 2) && largeIconBridge == null) {
            bVar.c(i, null);
            return;
        }
        int i2 = this.c;
        if (i == 1) {
            largeIconBridge.b(gurl, i2, new LargeIconBridge.LargeIconCallback() { // from class: lk0
                @Override // org.chromium.components.favicon.LargeIconBridge.LargeIconCallback
                public final void onLargeIconAvailable(Bitmap bitmap, int i3, boolean z, int i4) {
                    bVar.c(i, bitmap);
                }
            });
            return;
        }
        if (i == 2) {
            largeIconBridge.b(gurl, i2 / 2, new c01(i, bVar));
        } else if (i != 3) {
            bVar.c(i, null);
        } else {
            PostTask.c(rh3.a, new vy2(i, bVar, this, gurl));
        }
    }

    public final void b(GURL gurl, boolean z, b bVar) {
        Integer num = this.a.get(gurl);
        if (num == null) {
            num = 1;
        }
        if (num.intValue() == 3 && !z) {
            bVar.c(0, null);
        } else {
            a(num.intValue(), new a(gurl, bVar, z), gurl);
        }
    }
}
