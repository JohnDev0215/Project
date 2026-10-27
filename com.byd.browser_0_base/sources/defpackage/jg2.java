package defpackage;

import android.content.res.Resources;
import android.graphics.Bitmap;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.browser.ui.favicon.FaviconHelper;

/* JADX INFO: compiled from: SearchEngineLogoUtils.java */
/* JADX INFO: loaded from: classes2.dex */
public final class jg2 {
    public static jg2 d;
    public static Bitmap e;
    public static String f;
    public static int g;
    public static int h;
    public Boolean a;
    public FaviconHelper b;
    public uc2 c;

    public static jg2 a() {
        Object obj = ThreadUtils.a;
        if (d == null) {
            d = new jg2();
        }
        return d;
    }

    public static int b(Resources resources) {
        if (g == 0) {
            g = resources.getDimensionPixelSize(R$dimen.omnibox_search_engine_logo_favicon_size);
        }
        return g;
    }

    public static void c(int i) {
        oj2.U(i, 6, "AndroidSearchEngineLogo.Events");
    }
}
