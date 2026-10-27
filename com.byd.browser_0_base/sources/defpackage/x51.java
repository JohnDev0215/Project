package defpackage;

import J.N;
import android.graphics.Bitmap;
import org.chromium.chrome.browser.compositor.LayerTitleCache;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.ui.favicon.FaviconHelper;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: LayerTitleCache.java */
/* JADX INFO: loaded from: classes.dex */
public final class x51 implements FaviconHelper.FaviconImageCallback {
    public final /* synthetic */ Tab e;
    public final /* synthetic */ LayerTitleCache f;

    public x51(LayerTitleCache layerTitleCache, Tab tab) {
        this.f = layerTitleCache;
        this.e = tab;
    }

    @Override // org.chromium.chrome.browser.ui.favicon.FaviconHelper.FaviconImageCallback
    public final void onFaviconAvailable(Bitmap bitmap, GURL gurl) {
        int id;
        LayerTitleCache.a aVar;
        LayerTitleCache layerTitleCache = this.f;
        layerTitleCache.getClass();
        Tab tab = this.e;
        if (tab.isInitialized() && (aVar = layerTitleCache.c.get((id = tab.getId()))) != null) {
            boolean z = aVar.c;
            ve veVar = aVar.a;
            boolean z2 = false;
            if (z) {
                veVar.d(bitmap);
                aVar.c = false;
                z2 = true;
            }
            if (z2) {
                long j = layerTitleCache.e;
                if (j != 0) {
                    N.MJ3tyP3k(j, layerTitleCache, id, veVar.e);
                }
            }
        }
    }
}
