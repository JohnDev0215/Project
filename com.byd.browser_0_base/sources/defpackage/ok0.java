package defpackage;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import org.chromium.base.Callback;
import org.chromium.chrome.browser.settings.R$dimen;
import org.chromium.components.favicon.LargeIconBridge;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: FaviconLoader.java */
/* JADX INFO: loaded from: classes2.dex */
public final class ok0 {
    public static void a(Context context, LargeIconBridge largeIconBridge, GURL gurl, Callback<Drawable> callback) {
        Resources resources = context.getResources();
        largeIconBridge.b(gurl, resources.getDimensionPixelSize(R$dimen.default_favicon_min_size), new ig2(gurl, context, resources, resources.getDimensionPixelSize(R$dimen.default_favicon_size), callback));
    }
}
