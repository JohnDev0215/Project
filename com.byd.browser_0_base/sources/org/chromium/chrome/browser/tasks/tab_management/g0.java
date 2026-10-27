package org.chromium.chrome.browser.tasks.tab_management;

import J.N;
import android.graphics.Bitmap;
import java.util.ArrayList;
import java.util.List;
import org.chromium.base.Callback;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.tasks.tab_management.h0;
import org.chromium.chrome.browser.ui.favicon.FaviconHelper;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: TabListFaviconProvider.java */
/* JADX INFO: loaded from: classes2.dex */
public final class g0 implements h0.d {
    public final /* synthetic */ List a;
    public final /* synthetic */ boolean b;
    public final /* synthetic */ h0 c;

    public g0(h0 h0Var, ArrayList arrayList, boolean z) {
        this.c = h0Var;
        this.a = arrayList;
        this.b = z;
    }

    @Override // org.chromium.chrome.browser.tasks.tab_management.h0.d
    public final void a(final Callback<h0.c> callback) {
        final h0 h0Var = this.c;
        FaviconHelper faviconHelper = h0Var.k;
        final boolean z = this.b;
        Profile profileF = !z ? h0Var.j : h0Var.j.f(false);
        int i = h0Var.e;
        FaviconHelper.ComposedFaviconImageCallback composedFaviconImageCallback = new FaviconHelper.ComposedFaviconImageCallback() { // from class: b13
            @Override // org.chromium.chrome.browser.ui.favicon.FaviconHelper.ComposedFaviconImageCallback
            public final void onComposedFaviconAvailable(Bitmap bitmap, GURL[] gurlArr) {
                h0.b bVar;
                h0 h0Var2 = h0Var;
                Callback callback2 = callback;
                if (bitmap != null) {
                    callback2.onResult(new h0.a(h0Var2.g(bitmap, h0Var2.g), gurlArr));
                    return;
                }
                if (z) {
                    h0Var2.getClass();
                    bVar = h0.q;
                } else {
                    bVar = h0.n;
                    h0Var2.a(bVar);
                }
                callback2.onResult(bVar);
            }
        };
        faviconHelper.getClass();
        List list = this.a;
        if (list.size() > 1 && list.size() <= 4) {
            N.MYTn7kvC(faviconHelper.a, profileF, (GURL[]) list.toArray(new GURL[0]), i, composedFaviconImageCallback);
        } else {
            throw new IllegalStateException("Only able to compose 2 to 4 favicon, but requested " + list.size());
        }
    }
}
