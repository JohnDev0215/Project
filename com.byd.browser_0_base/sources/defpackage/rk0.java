package defpackage;

import android.graphics.Bitmap;
import android.util.LruCache;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.ui.favicon.FaviconHelper;

/* JADX INFO: compiled from: FaviconProviderImpl.java */
/* JADX INFO: loaded from: classes.dex */
public final class rk0 implements pk0 {
    public final Profile a;
    public final FaviconHelper b = new FaviconHelper();
    public final a c = new a();

    /* JADX INFO: compiled from: FaviconProviderImpl.java */
    public static class a {
        public final LruCache<String, Bitmap> a = new LruCache<>(128);
    }

    public rk0(Profile profile) {
        this.a = profile;
    }
}
