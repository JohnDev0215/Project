package org.chromium.chrome.browser.ntp;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import defpackage.uc2;
import org.chromium.chrome.browser.ntp.n;
import org.chromium.chrome.browser.ui.favicon.FaviconHelper;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: RecentTabsRowAdapter.java */
/* JADX INFO: loaded from: classes2.dex */
public final class m implements FaviconHelper.FaviconImageCallback {
    public final /* synthetic */ n.i e;
    public final /* synthetic */ GURL f;
    public final /* synthetic */ int g;
    public final /* synthetic */ n h;

    public m(n nVar, n.i iVar, GURL gurl, int i) {
        this.h = nVar;
        this.e = iVar;
        this.f = gurl;
        this.g = i;
    }

    @Override // org.chromium.chrome.browser.ui.favicon.FaviconHelper.FaviconImageCallback
    public final void onFaviconAvailable(Bitmap bitmap, GURL gurl) {
        Drawable drawableC;
        n.i iVar = this.e;
        if (this != iVar.e) {
            return;
        }
        n nVar = this.h;
        uc2 uc2Var = nVar.k;
        Resources resources = nVar.a.getResources();
        GURL gurl2 = this.f;
        if (gurl2 == null) {
            FaviconHelper.a aVar = nVar.c;
            aVar.getClass();
            drawableC = new BitmapDrawable(resources, aVar.b(resources, gurl2, true));
        } else {
            int i = nVar.i;
            if (bitmap == null) {
                uc2Var.getClass();
                drawableC = new BitmapDrawable(resources, Bitmap.createScaledBitmap(uc2Var.b(gurl2.j(), false), i, i, true));
            } else {
                drawableC = org.chromium.chrome.browser.ui.favicon.a.c(resources, Bitmap.createScaledBitmap(bitmap, i, i, true));
            }
        }
        ((n.a) nVar.h.get(Integer.valueOf(this.g))).a.put(gurl2, drawableC);
        iVar.c.setImageDrawable(drawableC);
    }
}
