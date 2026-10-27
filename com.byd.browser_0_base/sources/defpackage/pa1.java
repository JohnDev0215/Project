package defpackage;

import android.graphics.Bitmap;
import android.graphics.Rect;
import org.chromium.base.Callback;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.content.browser.webcontents.WebContentsImpl;

/* JADX INFO: compiled from: LongScreenshotsEntry.java */
/* JADX INFO: loaded from: classes2.dex */
public final class pa1 {
    public final Rect a;
    public final org.chromium.chrome.browser.share.long_screenshots.bitmap_generation.a b;
    public int c;
    public Bitmap d;
    public a e;
    public final Callback<Integer> f;

    /* JADX INFO: compiled from: LongScreenshotsEntry.java */
    public interface a {
        void a(int i);
    }

    public pa1(org.chromium.chrome.browser.share.long_screenshots.bitmap_generation.a aVar, Rect rect, Callback<Integer> callback) {
        this.a = rect;
        this.b = aVar;
        this.f = callback;
    }

    public final void a() {
        float fCeil;
        org.chromium.chrome.browser.share.long_screenshots.bitmap_generation.a aVar = this.b;
        if (aVar == null) {
            b(2);
            return;
        }
        b(6);
        Rect rect = this.a;
        eu1 eu1Var = new eu1(15, this);
        lj1 lj1Var = new lj1(22, this);
        if (aVar.g == 0.0f) {
            re2 re2Var = aVar.f;
            Tab tab = re2Var.a;
            if (tab.c() == null || re2Var.c.getWidth() == 0) {
                fCeil = 1.0f;
            } else {
                za2 za2Var = ((WebContentsImpl) tab.c()).l;
                fCeil = ((int) Math.ceil(za2Var.a(za2Var.e))) / re2Var.c.getWidth();
            }
            aVar.g = fCeil;
        }
        aVar.a.a.a(null, rect, aVar.g, lj1Var, eu1Var);
    }

    public final void b(int i) {
        this.c = i;
        a aVar = this.e;
        if (aVar != null) {
            aVar.a(i);
        }
    }
}
