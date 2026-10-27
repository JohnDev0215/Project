package defpackage;

import android.graphics.Point;
import android.util.Size;
import org.chromium.base.Callback;
import org.chromium.chrome.browser.share.long_screenshots.bitmap_generation.LongScreenshotsTabService;
import org.chromium.components.paintpreview.player.PlayerCompositorDelegateImpl;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: LongScreenshotsCompositor.java */
/* JADX INFO: loaded from: classes2.dex */
public final class na1 {
    public PlayerCompositorDelegateImpl a;
    public final Callback<Integer> b;
    public Size c;
    public Point d;

    /* JADX INFO: compiled from: LongScreenshotsCompositor.java */
    public static class a {
    }

    static {
        new a();
    }

    public na1(GURL gurl, LongScreenshotsTabService longScreenshotsTabService, long j, on0 on0Var) {
        this.b = on0Var;
        this.a = new PlayerCompositorDelegateImpl(longScreenshotsTabService, j, gurl, "long_screenshots_dir", true, new pq2(26, this), new dm0(28, this));
    }
}
