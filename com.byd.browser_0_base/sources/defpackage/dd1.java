package defpackage;

import android.graphics.Bitmap;
import android.graphics.Rect;
import defpackage.gd1;
import java.util.Iterator;
import java.util.List;
import org.chromium.content_public.browser.ImageDownloadCallback;
import org.chromium.content_public.browser.WebContents;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: MediaImageManager.java */
/* JADX INFO: loaded from: classes2.dex */
public final class dd1 implements ImageDownloadCallback {
    public WebContents a;
    public final int b;
    public int c = -1;
    public cd1 d = null;
    public GURL e;

    public dd1(int i) {
        this.b = i;
    }

    @Override // org.chromium.content_public.browser.ImageDownloadCallback
    public final void a(int i, List list, List list2) {
        if (i != this.c) {
            return;
        }
        Iterator it = list.iterator();
        Iterator it2 = list2.iterator();
        double d = 0.0d;
        Bitmap bitmap = null;
        while (it.hasNext() && it2.hasNext()) {
            Bitmap bitmap2 = (Bitmap) it.next();
            double dB = b((Rect) it2.next());
            if (d < dB) {
                bitmap = bitmap2;
                d = dB;
            }
        }
        de1 de1Var = (de1) this.d;
        de1Var.getClass();
        Bitmap bitmapA = fd1.a(bitmap);
        de1Var.f = bitmapA;
        de1Var.g = null;
        if (de1Var.h != bitmapA) {
            de1Var.h = bitmapA;
            if (!de1Var.g()) {
                gd1.a aVar = de1Var.k;
                aVar.g = de1Var.h;
                aVar.i = de1Var.f;
                de1Var.i();
            }
        }
        this.c = -1;
        this.d = null;
    }

    public final double b(Rect rect) {
        int iMax = Math.max(rect.width(), rect.height());
        double d = 0.8d;
        if (iMax != 0) {
            if (iMax < 114) {
                d = 0.0d;
            } else {
                int i = this.b;
                d = iMax <= i ? ((((double) (iMax - 114)) * 0.8d) / ((double) (i - 114))) + 0.2d : (((double) i) * 1.0d) / ((double) iMax);
            }
        }
        int iWidth = rect.width();
        int iHeight = rect.height();
        return (((double) Math.min(iWidth, iHeight)) / ((double) Math.max(iWidth, iHeight))) * d;
    }
}
