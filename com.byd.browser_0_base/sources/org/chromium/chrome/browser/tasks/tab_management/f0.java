package org.chromium.chrome.browser.tasks.tab_management;

import android.graphics.drawable.Drawable;
import org.chromium.base.Callback;
import org.chromium.chrome.browser.tasks.tab_management.h0;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: TabListFaviconProvider.java */
/* JADX INFO: loaded from: classes2.dex */
public final class f0 implements h0.d {
    public final /* synthetic */ Drawable a;
    public final /* synthetic */ GURL b;

    public f0(Drawable drawable, GURL gurl) {
        this.a = drawable;
        this.b = gurl;
    }

    @Override // org.chromium.chrome.browser.tasks.tab_management.h0.d
    public final void a(Callback<h0.c> callback) {
        Drawable drawable = this.a;
        callback.onResult(new h0.e(drawable, drawable, false, this.b));
    }
}
